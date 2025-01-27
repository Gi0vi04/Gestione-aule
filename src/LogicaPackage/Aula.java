package LogicaPackage;

import java.io.Serializable;

public class Aula implements Serializable {
    private String nomeAula;
    private String tipologia;
    private int numeroAula;
    private int capienza;

    public Aula(int numeroAula, int capienza, String tipologia, String nomeAula){
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
