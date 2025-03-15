// Pacote do Código
package org.scp.api.java.renderer;

// Importações Explícitas
import org.scp.api.java.Mesh3D;

// Classe de Utilidade Tri-Dimensional
public class ModelRenderer3D
{
    // Campos Privados

    // Modelo Anexado ao Renderizador
    private Mesh3D mesh3D = null;
    
    // Construtores Básicos

    /**
     * Cria uma nova Instância do Renderizador
     */
    public ModelRenderer3D()
    {
        
    }

    // Funções Declaradas

    /**
     * Obtém o Modelo do Renderizador Tri-Dimensional
     * @return O Modelo que está Anexado
     */
    public Mesh3D mr_getMesh3D()
    {
        return this.mesh3D;
    }

    /**
     * Mude o Modelo do Renderizador Tri-Dimensional
     * @param value O novo Modelo para Anexar
     */
    public void mr_setMesh3D(Mesh3D value)
    {
        // Valide o Valor para Anexar
        if(value == null)
        {
            // Lance uma Exceção de Ponteiro Nulo
            throw new NullPointerException("[Exceção] > Não foi possível mudar o valor do modelo anexado ao renderizador atual.");
        }
        
        this.mesh3D = value;
    }
}
