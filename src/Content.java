/**
 * klass, mis on mõeldud edetabeli faili sisuga majandamiseks
 */
public class Content implements Comparable<Content>{
    private final String name; //mängija nimi
    private final int steps; //sammude arv

    //2 muutujat

    /**
     * objekti loomise konstruktor
     * @param name mängija nimi
     * @param steps sammude arv
     */
    public Content(String name, int steps) {
        this.name = name; //this. on klassi sisene. Vaata värvide järgi, lilla ja must.
        this.steps = steps;
    }

    //GETTERID

    /**
     * tagastab mängija nime failist
     * @return mängija nimi
     */
    public String getName() {
        return name;
    }

    /**
     * tagastab sammude arvu
     * @return sammude arv
     */
    public int getSteps() {
        return steps;
    }

    /**
     * sorteerimine sammude järgi kahanevalt
     * @param o objekt, mida võrrelda
     * @return täisarv
     */
    @Override
    public int compareTo(Content o) {
        //return Integer.compare(o.steps, steps); //kahanevalt
        return Integer.compare(steps, o.steps); //o on selle sama objekti. Kasvavalt (vaikimisi) .sort() .reversed()
    }

    /**
     * vormindatud edetabel konsooli näitamiseks
     * @return vormindatud rida
     */
    public String formattedData(){
        String displayName = name.length() > 15 ? name.substring(0, 15) : String.format("%-15s", name); //võetakse nimi ja kontrollitakse.
        // Kui nime pikkus on üle 15 märgi, siis sellest võetakse ainult esimesed 15 märki.
        // Juhul, kui ei ole üle 15 märgi, siis võetakse esimesed 15 märki
        String n = String.format("%-15s", displayName); //-15s on string ehk teksti vormindamine
        String s = String.format("%3d", steps); //kolmekohaline number
        return n + s;
    }
}
