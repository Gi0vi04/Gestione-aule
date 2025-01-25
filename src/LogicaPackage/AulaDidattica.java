package LogicaPackage;

import java.io.Serializable;

public class AulaDidattica extends Aula implements Serializable {
    private boolean presenzaLavagna;
    private boolean presenzaVideoproiettore;

    public AulaDidattica(int numeroAula, int capienza, boolean presenzaLavagna, boolean presenzaVideoproiettore, String nomeAula) {
        super(numeroAula, capienza, "Aula didattica", nomeAula);
        this.presenzaLavagna = presenzaLavagna;
        this.presenzaVideoproiettore = presenzaVideoproiettore;
    }

    public boolean getPresenzaLavagna() {
        return presenzaLavagna;
    }

    public void setPresenzaLavagna(boolean presenzaLavagna) {
        this.presenzaLavagna = presenzaLavagna;
    }

    public boolean getPresenzaVideoproiettore() {
        return presenzaVideoproiettore;
    }

    public void setPresenzaVideoproiettore(boolean presenzaVideoproiettore) {
        this.presenzaVideoproiettore = presenzaVideoproiettore;
    }
}
