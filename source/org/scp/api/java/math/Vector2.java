// Pacote do Código
package org.scp.api.java.math;

/**
 * Vetor Bi-Dimensional Comum
 */
public class Vector2
{
    // Campos Exclusívos da Classe

    // Coordenada X do Vetor
    private float x;

    // Coordenada Y do Vetor
    private float y;

    // Funções Declaradas
    
    /**
     * Obtém a Distância entre este Vetor e outro
     * @param other Indica o Vetor para ser Calculado
     * @return A Distância Totalizada entre Ambos
     */
    public float vector_getDistanceTo(Vector2 other)
    {
        float 
        return 0f;
    }

    /**
     * Obtém o Valor da Coordenada X
     * @return O Valor da Coordenada X deste Vetor
     */
    public float vector_getXCoord()
    {
        return x;
    }

    /**
     * Obtém o Valor da Coordenada Y
     * @return O Valor da Coordenada Y deste Vetor
     */
    public float vector_getYCoord()
    {
        return y;
    }

    /**
     * Obtém o Valor da Coordenada X
     * @param value A nova Coordenada X deste Vetor
     */
    public void vector_setXCoord(float value)
    {
        x = value;
    }

    /**
     * Obtém o Valor da Coordenada Y
     * @param value A nova Coordenada Y deste Vetor
     */
    public void vector_setYCoord(float value)
    {
        y = value;
    }
}
