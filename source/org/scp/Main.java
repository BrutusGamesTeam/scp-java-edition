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
public class Main
{
    // Campos Importantes

    // Manipulador da Janela
    public long window;

    // Funções Importantes

    // Execute o Jogo
    public void run()
    {
        // Imprime as Mensagens no Console
        System.out.println("/ S.C.P - Java Edition /");
    }

    // Inicialize apenas em uma Única Fotograma
    public void init()
    {
        // Configura uma Callback de Erro
        GLFWErrorCallback.createPrint(System.err).set();

        // Verifica se o GLFW não Inicializou
        if(!glfwInit())
        {
            // Lance uma Exceção de Estado Ilegal
            throw new IllegalStateException("Infelizmente, não foi possível inicializar o GLFW");
        }

        // Configura o GLFW
        glfwDefaultWindowHints(); // Use as Hints Padrões do GLFW
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // não Mostre a Janela após a Criação
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // Define que a Janela não Redimensione

        // Criação da Janela
        window = glfwCreateWindow(800, 600, "S.C.P - Java Edition", NULL, NULL);

        // Verifica se a Janela é Nula
        if(window == NULL)
        {
            // Lance uma Exceção do Runtime
            throw new RuntimeException("Desculpe, mas foi impossível criar a janela");
        }

        // Configura uma Callback para o Teclado
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            // Verifica qual Tecla e qual a Ação da Tecla
            if(key == GLFW_KEY_ESCAPE && action == GLFW_PRESS)
            {
                // Define se a Janela deve Fechar
                glfwSetWindowShouldClose(window, true);
            }
        });

        // Tente Obter a Pilha do Fio para Puxar um novo Quadro
        try(MemoryStack stack = stackPush())
        {
            // Largura do Quadro Puxado
            IntBuffer pushWidth = stack.mallocInt(1); // Número Inteiro

            // Altura do Quadro Puxado
            IntBuffer pushHeight = stack.mallocInt(1); // Número Inteiro

            // Obtenha o Tamanho atual da Janela
            glfwGetWindowSize(window, pushWidth, pushHeight);

            // Obtenha a Resolução do Monitor Primário
            GLFWVidMode videoMode = glfwGetVideoMode(gglfwGetPrimaryonitor());

            // Tamanho Total da Janela
            int totalWidth = (videoMode.width() - pushWidth.get(0)) / 2;
            int totalHeight = (videoMode.height() - pushHeight.get(0)) / 2;

            // Centralize a Janela
            glfwSetWindowPos(window, totalWidth, totalHeight);
        } // o Quadro da Pilha é Estourado Automaticamente

        // Cria o Contexto do OpenGL
        glfwMakeContextCurrent(window);

        // Ativa a Sincronização Vertical
        glfwSwapInterval(1);

        // faz a Janela Visivel
        glfwShowWindow(window);
    }

    // Repete o Código em todas as Fotogramas
    public void loop()
    {
        // Cria as Capacidades do OpenGL
        GL.createCapabilities();

        // Limpa a Cor
        glClearColor(1f, 0f, 0f, 0f);

        // Execute o Laço de Renderização até Pressionar a Tecla ESC
        while(!glfwWindowShouldClose(window))
        {
            // Limpa o Buffer do Quadro
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Troque os Buffers
            glfwSwapBuffers(window);

            // Crie um Poll de Eventos, Permitindo Callbacks
            glfwPollEvents();
        }
    }

    // Função Principal
    public static void main(String[] args)
    {
        // Crie uam nova Instância da Classe Principal
        new Main().run();
    }
}
