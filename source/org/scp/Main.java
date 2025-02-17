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

    // Funções Declaradas

    // Execute o Jogo
    public void run()
    {
        // Imprime as Mensagens no Console
        System.out.println("> S.C.P - Java Edition");
        System.out.println("> Obrigado por testar nosso jogo!");

        // Inicializa o Estado de Fluxo do Jogo
        init();
        loop();
    }

    // Inicialize apenas em um Único Quadro
    private void init()
    {
        // Configura uma Callback de Erro
        GLFWErrorCallback.createPrint(System.err).set();

        // Verifica se o GLFW não Inicializou
        if(!glfwInit())
        {
            // Lance uma Exceção de Estado Ilegal
            throw new IllegalStateException("Infelizmente, não foi possível inicializar o GLFW.");
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
            throw new RuntimeException("Desculpe, mas foi impossível criar a janela.");
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
                System.out.println("Isto é uma mensagem de testes, nada de mais!");
            }
        });

        // Tente obter a Pilha do Fio de Execução para Puxar um novo Quadro
        try(MemoryStack stack = stackPush())
        {
            // Largura do Quadro Puxado
            IntBuffer pushWidth = stack.mallocInt(1); // Número Inteiro

            // Altura do Quadro Puxado
            IntBuffer pushHeight = stack.mallocInt(1); // Número Inteiro

            // Obtenha o Tamanho atual da Janela
            glfwGetWindowSize(window, pushWidth, pushHeight);

            // Obtenha a Resolução do Monitor Primário
            GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Tamanho Total da Janela
            int totalWidth = (videoMode.width() - pushWidth.get(0)) / 2;
            int totalHeight = (videoMode.height() - pushHeight.get(0)) / 2;

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
        glClearColor(1f, 0f, 0f, 0f);

        // Execute o Laço de Renderização até Pressionar a Tecla ESC
        while(!glfwWindowShouldClose(window))
        {
            // Limpa o Buffer do Quadro
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Troque os Buffers de Memória da Janela
            glfwSwapBuffers(window);

            // Crie um Cíclo de Eventos, Permitindo assim as Callbacks
            glfwPollEvents();
        }
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
