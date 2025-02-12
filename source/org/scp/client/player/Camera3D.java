// Pacote do Código
package org.scp.client.player;

// Importações Explícitas
import org.scp.api.java.Instance3D;
import org.scp.api.java.ScreenBuffer;
import org.scp.api.java.Transform;

// Classe da Câmera Tri-Dimensional (Lado do Cliente)
public class Camera3D extends Instance3D
{
	// Campos Importantes
	
	// Buffer da Tela
    public ScreenBuffer camera_myBuffer = null;

    // Transformação da Câmera
    public Transform camera_myTransform = null;
    
    // Construtores

    // Cria uma nova Câmera Tri-Dimensional com o Nome, Buffer da Tela e Transformação
    public Camera3D(ScreenBuffer buffer, Transform transform)
    {
        // Definição dos Campos
        this.camera_myBuffer = buffer; // Buffer de Tela
        this.camera_myTransform = transform; // Transformação
    }

    // Funções Importantes

    // Obtenha o Buffer da Tela
    public ScreenBuffer camera_getBuffer()
    {
        // Retorne o Valor do Buffer de Tela atual
        return this.camera_myBuffer;
    }

    // Mude o Buffer da Tela
    public void camera_setBuffer(String value)
    {
        // Altere o Valor do Buffer de Tela atual
        this.camera_myBuffer = value;
    }

    // Obtenha a Transforação da Câmera
    public Transform camera_getTransform()
    {
        // Retorne o Valor de Transformação
        return this.camera_myTransform;
    }

    // Mude a Transformação da Câmera
    public void camera_setTransform(Transform value)
    {
        // Altere o Valor de Transformação
        this.camera_myTransform = value;
    }
}
