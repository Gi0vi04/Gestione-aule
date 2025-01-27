package LogicaPackage;

import LogicaPackage.Utils.Costanti;

public class Laboratorio extends Aula {
    private boolean isPcPresente;
    private boolean isPreseElettrichePresente;

    /**
     * @param numeroAula codice identificativo dell'aula
     * @param nomeAula nome dell'aula
     * @param capienza capienza dell'aula
     * @param isPcPresente presenza o meno dei pc
     * @param isPreseElettrichePresente presenza o meno delle prese elettriche
     */
    public Laboratorio(int numeroAula, String nomeAula, int capienza, boolean isPcPresente, boolean isPreseElettrichePresente) {
        super(numeroAula, nomeAula, Costanti.LABORATORIO, capienza );
        this.isPcPresente = isPcPresente;
        this.isPreseElettrichePresente = isPreseElettrichePresente;
    }

    public boolean isPcPresente() {
        return isPcPresente;
    }
    public boolean isPreseElettrichePresente() {
        return isPreseElettrichePresente;
    }

    public void setPcPresente(boolean pcPresente) {
        this.isPcPresente = pcPresente;
    }
    public void setPreseElettrichePresente(boolean preseElettrichePresente) {
        this.isPreseElettrichePresente = preseElettrichePresente;
    }
}
