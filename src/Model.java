import java.io.*;
import java.util.*;

/**
 * kogu mängu loogika asub siin
 */
public class Model {
    //muutujad (enne konstandid ja siis tavamuutujad):
    private final int MINIMUM = 1;
    private final int MAXIMUM = 100;
    private final String filename = "scoreboard.txt";
    private final List<Content> scoreboard = new ArrayList<>(); //massiiv, mis hoidab infot, mis on edetabeli failis. Edetabeli faili sisu

    //3 muutujat puhtalt mängu jaoks
    private int pc_number; //arvuti mõeldud number
    private int steps; //käikude lugeja, mitme sammuga numbri ära arvasid
    private boolean game_over; //kas mäng on läbi

    /**
     * uue mängu loomine
     */
    public void initGame() { //loo mäng
        pc_number = new Random().nextInt(MAXIMUM - MINIMUM + 1) + MINIMUM; //juhuslik number vahemikus 1-100
        game_over = false; //mängu algseis
        steps = 0; //mängu algseis
    }
    // GETTERS 3 meetodit ehk getterit (alt+ insert nupuga saab)

    /**
     * arvuti mõeldud number
     * @return juhuslik number vahemikus 1-100
     */
    public int getPc_number() {
        return pc_number;
    }

    /**
     * kas mäng on läbi?
     * @return true on läbi, false ei ole mäng läbi
     */
    public boolean isGame_over() { return game_over; }

    /**
     * kontrollib kasutaja sisestust ja tagastab sobiva teksti
     * @param guess number mida kontrollida
     * @return tekst, mida näidatakse
     */
    public String checkGuess(int guess) {
        steps++; //sammude arv kasvab
        if(guess == pc_number) {
            game_over = true;
            return "Sa võitsid " + steps + " sammuga!";
        } else if (guess < pc_number) {
            return "Liiga väike";

        } else {
            return "Liiga suur";
        }
    }

    /**
     * salvestab listi sisu (edetabel) uuesti faili (kirjutab üle)
     * @param name mängija nimi
     */
    public void saveScore(String name) {
        loadScores(); //lae failist sisu listi
        scoreboard.add(new Content(name, steps)); //lisa nimi ja sammude arv listi
        Collections.sort(scoreboard); //sorteerib listi (Content compareTo() omaga)
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (Content c : scoreboard) {
                out.println(c.getName() + ";" + c.getSteps()); //semikoolon jutumärkides
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * loeb edetabeli faili sisu ja lisab sisu listi
     * @return edetabeli list
     */
    public List<Content> loadScores() {
        scoreboard.clear(); //tühjenda list
        File file = new File(filename);
        if (!file.exists()) return scoreboard; //kui faili ei ole, siis tagastab listi

        try(Scanner sc =  new Scanner(file)) {
            while(sc.hasNextLine()) { //kui failis on järgmine rida
                String[] parts = sc.nextLine(). split(";"); //semikoolon jutumärkides
                if(parts.length == 2) {
                    String name = parts[0];
                    int steps = Integer.parseInt(parts[1]);
                    scoreboard.add(new Content(name, steps));
                }
            }
            Collections.sort(scoreboard); //sorteerib listi (ilmselt siin üleliigne)

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return scoreboard;
    }
}
