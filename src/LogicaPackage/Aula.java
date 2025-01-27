package LogicaPackage;

import java.io.Serializable;

public class Aula implements Serializable {
    private String nomeAula;
    private String tipologia;
    private int numeroAula;
    private int capienza;

    /**
     *
     * @param numeroAula codice identificativo dell'aula
     * @param nomeAula nome dell'aula
     * @param tipologia tipologia dell'aula (Didattica o laboratorio)
     * @param capienza capienza dell'aula
     */
    public Aula(int numeroAula, String nomeAula, String tipologia, int capienza){
        this.numeroAula = numeroAula;
        this.capienza = capienza;
        this.tipologia = tipologia;
        this.nomeAula = nomeAula;
    }

    public int getNumeroAula() {
        return numeroAula;
    }
    public String getNomeAula() {
        return nomeAula;
    }
    public String getTipologia() {
        return tipologia;
    }

    @Override
    public String toString(){
        return nomeAula;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Aula aula = (Aula) obj;
        return nomeAula != null && nomeAula.equals(aula.nomeAula);
    }
    @Override
    public int hashCode() {
        return nomeAula != null ? nomeAula.hashCode() : 0;
    }

}
