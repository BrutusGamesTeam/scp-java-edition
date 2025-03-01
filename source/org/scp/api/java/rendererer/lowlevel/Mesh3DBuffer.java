// Pacote do Código
package org.scp.api.java.renderer.lowlevel;

// Importações Explícitas
import org.scp.api.java.Mesh3D;

import org.lwjgl.memory.MemoryUtil;

// Importações Estáticas
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
    public Mesh3DBuffer(IntBuffer b)
    {
        vaoIdentifier = glGenVertexArrays();
        vboIdentifier = glGenBuffers();
        eboIdentifier = glGenBuffers();
    }
}
