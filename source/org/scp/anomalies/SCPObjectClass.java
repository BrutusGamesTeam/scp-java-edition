// Pacote do Código
package org.scp.anomalies;

// Enumerador (Grupo de Definições Diferentes) de Classes de Anomalias S.C.P
public enum SCPObjectClass
{
    // Seguro: Não Exigem Cuidados Excessivos para Manipulação
    SAFE,

    // Euclídeo: Exigem um Cuidado Intermediário ao Manipular
    EUCLID,

    // Keter (do Indonésio: "Ter"): Extremamente Difíceis de se Manipular
    KETER,

    // Apocalíptico: Não devem ser Contidas devido seu Risco de Destruição Planetária
    APOLLYON,

    // Thaumiel: Úteis na Contenção de Anomalias, Principalmente em Contra-Ataques
    THAUMIEL,

    // Neutralizado: Foram Perdidas ou Radicalizadas com Sucesso
    NEUTRALIZED,

    // Desconhecido: Não há Enquadramendo Claro de qual Classe deve-se Colocar
    UNKNOWN,

    // Nulo: Não foram Especificadas no Código
    NULL
}
