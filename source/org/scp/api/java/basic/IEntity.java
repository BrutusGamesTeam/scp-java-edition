// Pacote do Código
package org.scp.api.java.basic;

// Importações Explícitas
import org.scp.api.java.basic.Entity;
import org.scp.api.java.Item;

// Interface Responsável por Permitir a Manipulação de Entidades
public interface IEntity
{
    // Funções Declaradas

    // Evento de quando a Entidade é Abatida
    public void entity_onDeath(Entity attacker, Item inflictor);

    // Evento de quando a Entidade Abate uma Outra
    public void entity_onKill(Entity victim);

    // Chamado quando a Entidade Encontra um Alvo
    public boolean entity_onFindTarget(Entity target);

    // Chamado quando a Entidade Persegue o Alvo
    public boolean entity_onChaseTarget(Entity target);
}
