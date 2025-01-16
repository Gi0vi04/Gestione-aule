package LogicaPackage;

public class Aula {
    private int numeroAula;
    private int capienza;

    public Aula(int numeroAula, int capienza){
        this.numeroAula = numeroAula;
        this.capienza = capienza;
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
}
