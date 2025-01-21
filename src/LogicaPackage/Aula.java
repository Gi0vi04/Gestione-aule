package LogicaPackage;

import java.io.Serializable;

public class Aula implements Serializable {
    private int numeroAula;
    private int capienza;
    private String nomeAula;

    public Aula(int numeroAula, int capienza, String nomeAula){
        this.numeroAula = numeroAula;
        this.capienza = capienza;
        this.nomeAula = nomeAula;
    }

    public int getNumeroAula() {
        return numeroAula;
    }

    public void setNumeroAula(int numeroAula) {
        this.numeroAula = numeroAula;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public String getNomeAula() {
        return nomeAula;
    }

    @Override
    public String toString(){
        return nomeAula;
    }
}
