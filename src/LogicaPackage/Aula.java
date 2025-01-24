package LogicaPackage;

import java.io.Serializable;

public class Aula implements Serializable {
    private int numeroAula;
    private int capienza;
    private String tipologia;
    private String nomeAula;

    public Aula(int numeroAula, int capienza, String tipologia, String nomeAula){
        this.numeroAula = numeroAula;
        this.capienza = capienza;
        this.tipologia = tipologia;
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

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    @Override
    public String toString(){
        return nomeAula;
    }
}
