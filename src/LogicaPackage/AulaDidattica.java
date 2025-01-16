package LogicaPackage;

import java.io.Serializable;

public class AulaDidattica extends Aula implements Serializable {
    private boolean presenzaLavagna;
    private boolean presenzaVideoproiettore;

    public AulaDidattica(int numeroAula, int capienza, boolean presenzaLavagna, boolean presenzaVideoproiettore) {
        super(numeroAula, capienza);
        this.presenzaLavagna = presenzaLavagna;
        this.presenzaVideoproiettore = presenzaVideoproiettore;
    }

    public boolean isPresenzaLavagna() {
        return presenzaLavagna;
    }

    public void setPresenzaLavagna(boolean presenzaLavagna) {
        this.presenzaLavagna = presenzaLavagna;
    }

    public boolean isPresenzaVideoproiettore() {
        return presenzaVideoproiettore;
    }

    public void setPresenzaVideoproiettore(boolean presenzaVideoproiettore) {
        this.presenzaVideoproiettore = presenzaVideoproiettore;
    }
}
