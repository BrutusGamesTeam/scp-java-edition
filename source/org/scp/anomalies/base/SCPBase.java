// Pacote do Código
package org.scp.anomalies.base;

// Importações Explícitas
import org.scp.api.java.basic.Entity;
import org.scp.anomalies.SCPObjectClass;

// Classe Responsável por Representar uma Anomalia S.C.P
public class SCPBase extends Entity
{
    // Campos Exclusívos da Classe

    // Identificação da Anomalia S.C.P
    private String scp_anomalyId = "Null S.C.P Anomaly";

    // Classe de Objeto da Anomalia S.C.P
    // Indo de Seguro para Nulo
    private SCPObjectClass scp_objectClass = SCPObjectClass.NULL;
}
