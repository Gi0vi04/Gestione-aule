package LogicaPackage;

import LogicaPackage.Utils.Costanti;

import java.io.Serializable;

public class AulaDidattica extends Aula implements Serializable {
    private boolean isLavagnaPresente;
    private boolean isVideoproiettorePresente;

    /**
     * @param numeroAula codice identificativo dell'aula
     * @param nomeAula nome dell'aula
     * @param capienza capienza dell'aula
     * @param isLavagnaPresente presenza o meno della lavagna
     * @param isVideoproiettorePresente presenza o meno del video-proiettore
     */
    public AulaDidattica(int numeroAula, String nomeAula, int capienza, boolean isLavagnaPresente, boolean isVideoproiettorePresente) {
        super(numeroAula, nomeAula, Costanti.DIDATTICA, capienza);
        this.isLavagnaPresente = isLavagnaPresente;
        this.isVideoproiettorePresente = isVideoproiettorePresente;
    }

    public boolean isLavagnaPresente() {
        return isLavagnaPresente;
    }
    public boolean isVideoproiettorePresente() {
        return isVideoproiettorePresente;
    }

    public void setLavagnaPresente(boolean lavagnaPresente) {
        this.isLavagnaPresente = lavagnaPresente;
    }
    public void setVideoproiettorePresente(boolean videoproiettorePresente) {
        this.isVideoproiettorePresente = videoproiettorePresente;
    }
}
