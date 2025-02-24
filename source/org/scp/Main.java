// Pacote de Código
package org.scp;

// Importações Implícitas
import java.nio.*;
import java.nio.file.*;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;
import org.lwjgl.stb.*;

// Importações Estáticas
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

// Importações Explícitas
import java.io.IOException;

// Classe Principal do Jogo
public final class Main
{
    // Campos Exclusivos da Classe

    // Textura da Fonte
    private int fontTexture;

    // Dados dos Caracteres da Fonte
    private STBTTBakedChar.Buffer cdata;

    // Manipulador da Janela
    private long window;

    // Determinador de Cardápio Principal
    private boolean isMenuState = true;

    // Funções Declaradas

    // Desenhe o Texto Solicitado
    private void drawText(String text, long x, long y)
    {
        // Cria um Alinhamento de Texto
        STBTTAlignedQuad q = STBTTAlignedQuad.malloc();

        // Cria um Laço para Iterar sobre os Caracteres do Texto
        for(char c : text.toCharArray())
        {
            // Cria um Texto com Fonte
            STBTruetype.nstbtt_GetBakedQuad(cdata, 480, 320, c - 32, x, y, q, false);

            // Inicializa o Modo de Desenho com o Alinhamento
            glBegin(GL_QUADS);

            // Desenhe o Texto com as Coordenadas Solicitadas
            glTexCoord2f(q.s0(), q.t0()); glVertex2f(x + q.x0(), y + q.y0());
            glTexCoord2f(q.s1(), q.t0()); glVertex2f(x + q.x1(), y + q.y0());
            glTexCoord2f(q.s1(), q.t1()); glVertex2f(x + q.x1(), y + q.y1());
            glTexCoord2f(q.s0(), q.t1()); glVertex2f(x + q.x0(), y + q.y1());

            // Termina o Modo de Desenho com Alinhamento
            glEnd();

            // Mude a Coordenada X do Próximo Caractére
            x += q.x1() - q.x0();
        }
    }

    // Carregue uma Fonte pelos Arquivos
    private void loadFont(String filePath)
    {
        // Tente fazer o Carregamento da Fonte
        try
        {
            // Reserva de Bytes da Fonte
            ByteBuffer fontBuffer = BufferUtils.createByteBuffer(512 * 512);

            // Array de Bytes Carregados dos Dados da Fonte e a Reserva
            byte[] fontData = Files.readAllBytes(Paths.get(filePath));
            ByteBuffer buffer = BufferUtils.createByteBuffer(fontData.length).put(fontData);

            // Vira a Reserva Temporária
            buffer.flip();

            // Mude o Valor dos Dados de Caracteres
            cdata = STBTTBakedChar.malloc(96);

            // Pré-Processa a Fonte de Formato Verdadeiro (TTF em Inglês)
            STBTruetype.stbtt_BakeFontBitmap(buffer, 32, fontBuffer, 512, 512, 32, cdata);

            // Carregue a Textura da Fonte
            fontTexture = glGenTextures();

            // Ligue a Textura da Fonte para o Uso em Texturas Bi-Dimensionais
            glBindTexture(GL_TEXTURE_2D, fontTexture);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_ALPHA, 512, 512, 0, GL_ALPHA, GL_UNSIGNED_BYTE, fontTexture);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        }
        catch(IOException e)
        {
            // Lance uma nova Exceção do Tempo de Execução
            throw new RuntimeException("[Exceção] > Lamentamos lhe informar mas infelizmente não foi possível carregar a textura.", e);
        }
    }

    // Execute o Jogo
    public void run()
    {
        // Imprime as Mensagens no Console
        System.out.println("[Inicialização] > S.C.P - Java Edition");
        System.out.println("[Agradecimentos] > Agradecemos por testar a fase atual de nosso jogo! Esperamos um ótimo dia para o senhor!");

        // Inicializa o Estado de Fluxo do Jogo
        init();
        loadFont("fonts/belaonima-regular.ttf");
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

            // Imprime a Mensagem no Console
            System.err.println("[Erro] > Lamentamos que houve uma falha ao executar o jogo: " + message + ".");
        });

        // Verifica se o GLFW não Inicializou
        if(!glfwInit())
        {
            // Lance uma Exceção de Estado Ilegal
            throw new IllegalStateException("[Exceçao] > Infelizmente não foi possível inicializar o GLFW.");
        }

        // Configuração do GLFW
        glfwDefaultWindowHints(); // Use as Indicações Padronizadas do GLFW
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // Não Mostre a Janela após a Criação
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // Não Permite que a Janela seja Redimensionada

        // Criação da Janela
        window = glfwCreateWindow(800, 600, "|| [S.C.P] - Java Edition ||", NULL, NULL);

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
                System.out.println("[Mensagem] > Isto é uma mensagem de testes, se divirta!");
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
        // Inicializa as Capacidades da Biblioteca Aberta de Gráficos (OpenGL)
        GL.createCapabilities();

        // Limpa o Quadro com uma Cor Preta
        glClearColor(0f, 0f, 0f, 0f);

        // Execute o Laço de Renderização até Pressionar a Tecla de Escape (ESC)
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
        // Enquanto o Laço da Tela Principal estiver Marcado
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

    // Função de Renderização da Tela Principal
    private void menuRender()
    {
        // Habilita a Textura Bi-Dimensional
        glEnable(GL_TEXTURE_2D);

        // Mude a Cor de Desenho para Branco
        glColor3f(1f, 1f, 1f);

        // Ligue a Textura Bi-Dimensional para Desenhar-la
        glBindTexture(GL_TEXTURE_2D, fontTexture);

        // Cria um Texto para Desenhar
        drawText("|| S.C.P - Java Edition ||", 2l, 2l);

        // Desabilita a Textura Bi-Dimensional
        glDisable(GL_TEXTURE_2D);
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
        // Imprima uma Mensagem Semi-Final
        System.out.println("[Finalização] > Encerrando o GLFW, aguarde.");

        // Processo de Destruir o Manipulador de Janela
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Encerre o GLFW
        glfwTerminate();
        glfwSetErrorCallback(null).free();

        // Imprima a Mensagem Final
        System.out.println("[Encerramento] > O GLFW foi totalmente finalizado, tenha um bom dia!");
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
