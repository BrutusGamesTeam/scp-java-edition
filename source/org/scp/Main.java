// Pacote de Código
package org.scp;

// Importações Explícitas
import java.nio.*;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

// Importações Estáticas
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

// Classe Principal do Jogo
public final class Main
{
    // Campos Exclusívos da Classe

    // Manipulador da Janela
    private long window;

    // Determinador de Cardápio Principal
    private boolean isMenuState = true;

    // Funções Declaradas

    // Execute o Jogo
    public void run()
    {
        // Imprime as Mensagens no Console
        System.out.println("[Inicialização] > S.C.P - Java Edition");
        System.out.println("[Agradecimentos] > Obrigado por testar nosso jogo!");

        // Inicializa o Estado de Fluxo do Jogo
        init();
        menuLoop();
        loop();
        cleanup();
    }

    // Inicialize apenas em um Único Quadro
    private void init()
    {
        // Configura uma Callback de Erro
        glfwSetErrorCallback((error, description) -> {
            // Campo Temporário para Armazenar a Descrição da Callback de Erro
            String message = (description != NULL ? GLFWErrorCallback.getDescription(description) : "Erro não especificado na descrição");

            // Imprime a Menssagem no Console
            System.err.println("[Erro] > Houve uma falha ao executar o jogo: " + message + ".");
        });

        // Verifica se o GLFW não Inicializou
        if(!glfwInit())
        {
            // Lance uma Exceção de Estado Ilegal
            throw new IllegalStateException("[Exceçao] > Infelizmente, não foi possível inicializar o GLFW.");
        }

        // Configuração do GLFW
        glfwDefaultWindowHints(); // Use as Indicações Padronizadas do GLFW
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // Não Mostre a Janela após a Criação
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // Não Permite que a Janela seja Redimensionada

        // Criação da Janela
        window = glfwCreateWindow(800, 600, "S.C.P - Java Edition", NULL, NULL);

        // Cheque se a Janela é Nula
        if(window == NULL)
        {
            // Lance uma Exceção do Runtime
            throw new RuntimeException("[Exceção] > Desculpe, mas foi impossível criar a janela.");
        }

        // Configura uma Callback para o Teclado
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            // Verifica qual Tecla e qual a Ação da Tecla
            if(key == GLFW_KEY_ESCAPE && action == GLFW_PRESS)
            {
                // Define se a Janela deve Fechar
                glfwSetWindowShouldClose(window, true);
            }
            else if(key == GLFW_KEY_APOSTROPHE && action == GLFW_PRESS)
            {
                // Imprima uma Mensagem de Testes
                System.out.println("[Mensagem] > Isto é uma mensagem de testes, nada de mais!");
            }
        });

        // Tente obter a Pilha do Fio de Execução para Puxar um novo Quadro
        try(MemoryStack stack = stackPush())
        {
            // Largura do Quadro Puxado
            int[] width = new int[1]; // Array de Número Inteiro

            // Altura do Quadro Puxado
            int[] height = new int[1]; // Array de Número Inteiro

            // Obtenha o Tamanho atual da Janela
            glfwGetWindowSize(window, width, height);

            // Obtenha a Resolução do Monitor Primário
            GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Tamanho Total da Janela
            int totalWidth = (videoMode.width() - width[0]) / 2;
            int totalHeight = (videoMode.height() - height[0]) / 2;

            // Centralize a Janela em Relação ao Monitor
            glfwSetWindowPos(window, totalWidth, totalHeight);
        } // O Quadro da Pilha é Liberado Automaticamente

        // Cria o Contexto da Biblioteca de Gráficos Abertos
        glfwMakeContextCurrent(window);

        // Habilite a Sincronização Vertical
        glfwSwapInterval(1);

        // Faz a Janela Visivel
        glfwShowWindow(window);
    }

    // Repete o Código em todas as Fotogramas
    private void loop()
    {
        // Cria as Capacidades da Biblioteca de Gráficos Abertos
        GL.createCapabilities();

        // Limpa o Quadro com uma Cor
        glClearColor(0f, 0f, 0f, 0f);

        // Execute o Laço de Renderização até Pressionar a Tecla ESC
        while(!glfwWindowShouldClose(window))
        {
            // Limpa o Buffer de Quadro com a Reserva de Cor e Profundidade
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            // Execute a Função de Renderização
            render();

            // Troque as Reservas de Memória da Janela
            glfwSwapBuffers(window);

            // Crie um Cíclo de Eventos, Permitindo assim as Callbacks
            glfwPollEvents();
        }
    }

    // Função de Laço do Cardápio Principal
    private void menuLoop()
    {
        // Enquanto o Laço do Cardápio Principal estiver Marcado
        while(isMenuState && !glfwWindowShouldClose(window))
        {
            // Limpa o Reserva de Quadro com a Reserva de Cor e Profundidade
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Execute a Função de Renderizar o Cardápio Principal
            menuRender();

            // Troque as Reservas de Memória da Janela
            glfwSwapBuffers(window);

            // Crie um Cíclo de Eventos, Permitindo assim as Callbacks
            glfwPollEvents();
        }
    }

    // Função de Renderização do Cardápio Principal
    private void menuRender()
    {
        // Mude a Cor de Desenho
        glColor3f(1f, 1f, 1f);

        // Rasteriza a Posição de Desenho
        glRasterPos2f(-0.1f, 0f);

        // Cria um Laço de Impressão de Caractéres
        for(char character : "|| S.C.P - Java Edition ||".toCharArray())
        {
            // Execute a Função de Desenhar o Texto de Título
            glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18, character);
        }
    }

    // Função de Renderização do Fluxo de Jogo
    private void render()
    {
        // Limpa o Buffer de Quadro
        glClear(GL_COLOR_BUFFER_BIT);
    }

    // Função de Finalizar o Fluxo de Jogo
    private void cleanup()
    {
        // Imprima uma Mensagem Final
        System.out.println("[Finalização] > Encerrando o GLFW.");

        // Processo de Destruir o Manipulador de Janela
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Encerre o GLFW
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    // Função Principal
    public static void main(String[] args)
    {
        // Crie uma nova Instância da Classe Principal
        Main main = new Main();

        // Execute o Estado de Jogo
        main.run();
    }
}
