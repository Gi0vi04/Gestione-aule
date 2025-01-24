package LogicaPackage;

public class Laboratorio extends Aula {
    private boolean presenzaPc;
    private boolean presenzaPreseElettriche;

    public Laboratorio(int numeroAula, String tipologia, int capienza, boolean presenzaPc, boolean presenzaPreseElettriche, String nomeAula) {
        super(numeroAula, capienza, tipologia, nomeAula);
        this.presenzaPc = presenzaPc;
        this.presenzaPreseElettriche = presenzaPreseElettriche;
    }

    public boolean isPresenzaPc() {
        return presenzaPc;
    }

    public void setPresenzaPc(boolean presenzaPc) {
        this.presenzaPc = presenzaPc;
    }

    public boolean isPresenzaPreseElettriche() {
        return presenzaPreseElettriche;
    }

    public void setPresenzaPreseElettriche(boolean presenzaPreseElettriche) {
        this.presenzaPreseElettriche = presenzaPreseElettriche;
    }
}
