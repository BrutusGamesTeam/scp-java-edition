// Pacote do Código
package org.scp.api.java.math;

// Importações Explícitas
import java.util.Objects;

/**
 * Vetor Bi-Dimensional Comum
 */
public class Vector2
{
    // Campos Exclusívos da Classe

    // Fator de Épsilon
    private static final float EPSILON = 1e-6f;

    // Coordenada X do Vetor
    private float x;

    // Coordenada Y do Vetor
    private float y;

    // Construtores Básicos

    /**
     * Cria uma nova Instância do Vetor
     * @param xAxis O Eixo X do novo Vetor
     * @param yAxis O Eixo Y do novo Vetor
     */
    public Vector2(float xAxis, float yAxis)
    {
        this.x = xAxis;
        this.y = yAxis;
    }

    // Funções Declaradas
    
    /**
     * Obtém a Distância entre este Vetor e outro
     * @param other Indica o Vetor para ser Calculado
     * @return A Distância Totalizada entre Ambos
     */
    public float vector_getDistanceTo(Vector2 other)
    {
        float dx = this.vector_getXCoord() - other.vector_getXCoord();
        float dy = this.vector_getYCoord() - other.vector_getYCoord();
        
        return (float)(Math.sqrt(dx * dx + dy * dy));
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
     * Mude o Valor da Coordenada X
     * @param value A nova Coordenada X deste Vetor
     */
    public void vector_setXCoord(float value)
    {
        x = value;
    }

    /**
     * Mude o Valor da Coordenada Y
     * @param value A nova Coordenada Y deste Vetor
     */
    public void vector_setYCoord(float value)
    {
        y = value;
    }

    /**
     * Normalize o Vetor Globalmente
     * @return A Versão Normalizada deste Vetor
     */
    public Vector2 vector_getNormalized()
    {
        // Valor de Magnitude do Vetor
        float magnitude = (float)(Math.sqrt(this.x * x + this.y * y));

        // Caso o Vetor não seja Próximo de 0f
        if(magnitude <= EPSILON)
        {
            return new Vector2(0f, 0f);
        }

        return new Vector2(this.x / magnitude, this.y / magnitude);
    }

    /**
     * Incremente este Vetor com Outro
     * @param other O Vetor que irá Incrementar este
     */
    public void vector_increment(Vector2 other)
    {
        // Caso o Vetor seja Inválido
        Objects.requireNonNull(other, "[Exceção] > Lamentamos informar mas o vetor incrementador está nulo.");
        
        // Incrementação
        x += other.vector_getXCoord();
        y += other.vector_getYCoord();
    }

    /**
     * Decremente este Vetor com Outro
     * @param other O Vetor que irá Decrementar este
     */
    public void vector_decrement(Vector2 other)
    {
        // Caso o Vetor seja Inválido
        Objects.requireNonNull(other, "[Exceção] > Lamentamos informar mas o vetor decrementador está nulo.");
        
        // Decrementação
        x -= other.vector_getXCoord();
        y -= other.vector_getYCoord();
    }

    /**
     * Multiplica este Vetor com Outro
     * @param other O Vetor que irá Multiplicar este
     */
    public void vector_multiply(Vector2 other)
    {
        // Caso o Vetor seja Inválido
        Objects.requireNonNull(other, "[Exceção] > Lamentamos informar mas o vetor multiplicador está nulo.");
        
        // Multiplicação
        x *= other.vector_getXCoord();
        y *= other.vector_getYCoord();
    }

    /**
     * Divide este Vetor com Outro
     * @param other O Vetor que irá Dividir este
     */
    public void vector_divide(Vector2 other)
    {
        // Caso o Vetor seja Inválido
        Objects.requireNonNull(other, "[Exceção] > Lamentamos informar mas o vetor divisor está nulo.");

        // Evita a Divisão por 0
        if(other.vector_getXCoord() == 0 || other.vector_getYCoord() == 0)
        {
            throw new ArithmeticException("[Exceção] > Infelizmente não pode-se dividir um número por zero");
        }
        
        // Divisão
        x /= other.vector_getXCoord();
        y /= other.vector_getYCoord();
    }
}
