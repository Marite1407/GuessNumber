import java.util.List;

/**
 * käivitab mängu
 */
public class Controller {
    private final Model model;
    private final View view;

    //loome ühe mudeli, mida kasutame kogu rakenduse ulatuses

    /**
     * controlleri konstruktor. Ehk kasutame terves failis sama model ja view konstruktorit
     * @param model App failis loodud mudel
     * @param view App failis loodud view
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * käivitab mängu loogika
     */
    public void start() { //kogu loogika, mida küsitakse jne
        boolean running = true; // kas asi käib või mitte. Meetod, mis tõmbab asja käima, et kogu asja mängida
        while (running) {
            view.showMenu(); //näita menüüd
            int choice = view.getMenuChoice(); //küsi kasutajaöt menüü valikut (valib kas 1, 2 või 3)
            switch (choice) {
                case 1: //kui tegemist on mängimisega
                    model.initGame(); //loo uus mäng (sammude arv 0 jne)
                    view.showMessage(String.valueOf(model.getPc_number())); //CHEAT!
                    Stopwatch stopwatch = new Stopwatch(); //loome stopperi
                    stopwatch.start(); //käivitame stopperi
                    while (!model.isGame_over()) { //kui mäng ei ole läbi (mudeli mäng). ! tähendab eitust
                        int guess = view.askGuess(); //küsi kasutajalt numbrit
                        view.showMessage(model.checkGuess(guess)); //kontrolli ja väljasta tulemus. Kõigepealt teeb sulgudes oleva osa ära, kas liiga suur või väike.
                    }
                    stopwatch.stop(); //peata stopper
                    //näita aega järgnevalt: "Mängu aeg: 00:00:00 (0000)"
                    view.showMessage("Mängu aeg: " + stopwatch.getElapsedTime() + " ("+ stopwatch.getElapsedMillis() +")");
                    String name = view.askName(); //küsi nime
                    model.saveScore(name);
                    break;
                case 2: //edetabel
                    //näita edetabelit
                    List<Content> myScores = model.loadScores(); //kahe reaga
                    view.showScoreboard(myScores); //kahe reaga
                    //view.showScoreboard(model.loadScores()); ühe reaga
                    break; //case peab alati breakiga lõppema
                case 3:
                    running = false;
                    view.showMessage("Head aega");
                    break;
                default:
                    view.showMessage("Vigane valik");
            }
        }
    }
}
