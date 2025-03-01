// Pacote do Código
package org.scp.api.java.renderer.lowlevel;

// Importações Explícitas
import org.scp.api.java.Mesh3D;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL30;
import org.lwjgl.memory.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

// Importações Estáticas
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;

// Classe Utilitária do Sistema de Reserva de Modelos
public final class Mesh3DBuffer
{
    // Campos Exclusívos

    // Objetos da Reserva
    private int vaoIdentifier;
    private int vboIdentifier;
    private int eboIdentifier;

    // Construtores Básicos

    // Cria uma nova Instância da Reserva de Modelos
    private Mesh3DBuffer(IntBuffer b)
    {
        super(b.limit(), b.mark(), b.position(), b.capacity(), b.array(), b.arrayOffset());
    }

    // Funções Declaradas

    // Cria uma Reserva de Modelos
    public static Mesh3DBuffer create()
    {
        vaoIdentifier = glGenVertexArrays();
        vboIdentifier = glGenBuffers();
        eboIdentifier = glGenBuffers();
    }
}
