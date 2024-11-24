// Pacote do Código
package org.scp.client;

// Classe Responsável para ouvir por Atualizações e Patches
public interface UpdateCheckListener
{
    // Caso o Ouvinte obtém um Sinal da Atualização
    public void onUpdateSignalObtained(String update, String url);

    // Caso o Ouvinte não obtém um Sinal de Atualização
    public void onUpdateSignalNotObtained();

    // Caso o Ouvinte encontra um Erro tentando obter o Sinal de Atualização
    public void onUpdateSignalError(Exception exception);
}
