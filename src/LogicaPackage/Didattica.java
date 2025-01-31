package LogicaPackage;

import LogicaPackage.Utils.Costanti;

import java.io.Serializable;

/**
 * Classe destinata a estendere una semplice aula in un'aula didattica
 */
public class Didattica extends Aula implements Serializable {
    /**
     * Variabile che specifica o meno la presenza della lavagna
     */
    private boolean isLavagnaPresente;
    /**
     * Variabile che specifica o meno la presenza del videoproiettore
     */
    private boolean isVideoproiettorePresente;

    public Didattica(int numeroAula, String nomeAula, int capienza, boolean isLavagnaPresente, boolean isVideoproiettorePresente) {
        super(numeroAula, nomeAula, Costanti.DIDATTICA, capienza);
        this.isLavagnaPresente = isLavagnaPresente;
        this.isVideoproiettorePresente = isVideoproiettorePresente;
    }

    /**
     * Getter della presenza della lavagna
     * @return presenza della lavagna
     */
    public boolean isLavagnaPresente() {
        return isLavagnaPresente;
    }
    /**
     * Getter della presenza del videoproiettore
     * @return presenza del videoproiettore
     */
    public boolean isVideoproiettorePresente() {
        return isVideoproiettorePresente;
    }

    /**
     * Setter della preseza della lavagna
     * @param lavagnaPresente valore di presenza della lavagna
     */
    public void setLavagnaPresente(boolean lavagnaPresente) {
        this.isLavagnaPresente = lavagnaPresente;
    }
    /**
     * Setter della presenza del videoProiettore
     * @param videoproiettorePresente valore di presenza del videoproiettore
     */
    public void setVideoproiettorePresente(boolean videoproiettorePresente) {
        this.isVideoproiettorePresente = videoproiettorePresente;
    }
}
