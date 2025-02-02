package LogicaPackage;

import java.io.Serializable;

/**
 * Classe destinata a generalizzare l'aula didattica e l'aula laboratorio
 */
public abstract class Aula implements Serializable {
    /**
     * Variabile che contiene il nome dell'aula
     */
    private final String nomeAula;
    /**
     * Variabile che contiene la tipologia dell'aula
     */
    private final String tipologia;
    /**
     * Variabile che contiene il numero dell'aula
     */
    private final int numeroAula;
    /**
     * Variabile che contiene la capienza dell'aula
     */
    private final int capienza;

    public Aula(int numeroAula, String nomeAula, String tipologia, int capienza){
        this.numeroAula = numeroAula;
        this.nomeAula = nomeAula;
        this.tipologia = tipologia;
        this.capienza = capienza;
    }

    /**
     * Getter del numero aula
     * @return numero aula
     */
    public int getNumeroAula() {
        return numeroAula;
    }
    /**
     * Getter del nome aula
     * @return nome aula
     */
    public String getNomeAula() {
        return nomeAula;
    }
    /**
     * Getter della tipologia aula
     * @return tipologia aula
     */
    public String getTipologia() {
        return tipologia;
    }

    public abstract String getDettagli();

    @Override
    public String toString(){
        return nomeAula + " (" + capienza + " posti)";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Aula aula = (Aula) obj;
        return nomeAula != null && nomeAula.equals(aula.nomeAula);
    }
}
