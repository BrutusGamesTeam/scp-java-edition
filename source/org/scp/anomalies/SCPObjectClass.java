// Pacote do Código
package org.scp.anomalies;

// Enumerador (Grupo de Definições Diferentes) de Classes de Anomalias S.C.P
public enum SCPObjectClass
{
    // Seguro: Anomalias que não Exigem Cuidados Excessivos para Manipulação
    SAFE,

    // Euclídeo: Anomalias que Exigem um Cuidado Intermediário ao Manipular
    EUCLID,

    // Keter (do Indonédio: "Ter"): Anomalias que são Extremamente Difíceis de se Manipular
    KETER,

    // Apocalíptico: Anomalias que NÃO devem ser Contidas em Hipótese Alguma devido seu Risco de Destruição Planetária
    APOLLYON,

    // Thaumiel: Anomalias que podem ser Úteis na Contenção de Anomalias, Principalmente em Contra-Ataques
    THAUMIEL,

    // Neutralizado: Anomalias Extintas que foram Perdidas ou Radicalizadas com Sucesso
    NEUTRALIZED,

    // Desconhecido: Anomalias que não há Enquadramendo Claro de qual Classe deve-se Colocar
    UNKNOWN,

    // Nulo: Anomalias que não foram Especificadas no Código
    NULL
}
