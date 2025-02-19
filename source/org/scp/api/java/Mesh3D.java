// Pacote do Código
package org.scp.api.java;

// Importações Explícitas
import java.util.ArrayList;
import java.util.List;

// Classe Responsável pela Representação de Modelos Tri-Dimensionais
public class Mesh3D
{
    // Campos Importantes

    // Lista de Vértices do Modelo
    private List<Float> vertices = new ArrayList<>();

    // Lista de Triangulos do Modelo
    private List<Interger> triangles = new ArrayList<>();

    // Lista de Normais do Modelo
    private List<Float> normals = new ArrayList<>();

    // Funções Básicas

    // Adiciona um novo Vértice
    public void addVertice(float x, float y, float z)
    {
        // Adiciona os novos Elementos Solicitados (Coordenadas do Vértice)
        this.vertices.add(x);
        this.vertices.add(y);
        this.vertices.add(z);
    }

    // Adiciona um novo Triangulo
    public void addTriangle(int index1, int index2, int index3)
    {
        // Adiciona os novos Elementos Solicitados (Índices dos Vértices)
        this.triangles.add(index1);
        this.triangles.add(index2);
        this.triangles.add(index3);
    }

    // Obtenha uma Array de Vértices
    public Float[] getVerticesArray()
    {
        // Cria uma Array Temporária de Números com Vírgula
        Float[] v = new Float[this.vertices.size()];

        // Execute um Laço para Iterar sobre cada Vértice
        for(int i = 0; i < this.vertices.size(); i++)
        {
            v[i] = this.vertices.get(i);
        }

        // Retorne o Valor Final da Array Temporária de Número com Vírgula
        return v;
    }
}
