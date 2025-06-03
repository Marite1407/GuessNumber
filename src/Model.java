import java.io.*;
import java.util.*;
import java.time.LocalDateTime;

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

    private Stopwatch stopwatch = new Stopwatch(); // stopper mängu kestvuse mõõtmiseks


    /**
     * uue mängu loomine
     */
    public void initGame() { //loo mäng
        pc_number = new Random().nextInt(MAXIMUM - MINIMUM + 1) + MINIMUM; //juhuslik number vahemikus 1-100
        game_over = false; //mängu algseis
        steps = 0; //mängu algseis
        stopwatch.start();  //Käivitame stopwatchi
    }
    // GETTERS 3 meetodit ehk getterit (alt+ insert nupuga saab)

    /**
     * arvuti mõeldud number
     *
     * @return juhuslik number vahemikus 1-100
     */
    public int getPc_number() {
        return pc_number;
    }

    /**
     * kas mäng on läbi?
     *
     * @return true on läbi, false ei ole mäng läbi
     */
    public boolean isGame_over() {
        return game_over;
    }

    /**
     * kontrollib kasutaja sisestust ja tagastab sobiva teksti
     *
     * @param guess number mida kontrollida
     * @return tekst, mida näidatakse
     */
    public String checkGuess(int guess) {
        if (guess == 1000) {
            game_over = true; // mäng lõpeb
            stopwatch.stop(); // stopper kinni
            return "TAGAUKS: arvuti mõtles numbri: " + pc_number;
        }
        steps++; //sammude arv kasvab
        if (guess == pc_number) {
            game_over = true;
            stopwatch.stop(); // peata stopper kui võitis
            return "Sa võitsid " + steps + " sammuga!";
        } else if (guess < pc_number) {
            return "Liiga väike";

        } else {
            return "Liiga suur";
        }
    }

    /**
     * salvestab listi sisu (edetabel) uuesti faili (kirjutab üle)
     *
     * @param name mängija nimi
     */
    public void saveScore(String name) {
        // Tagauks: ära salvesta
        if (!game_over || steps == 0) {
            System.out.println("Tuvastati tagauks! Tulemust edetabelisse ei salvestata.");
            return;
        }

        loadScores(); // lae olemasolev edetabel
        long durationMillis = stopwatch.getElapsedMillis(); // millisekundid
        Content newScore = new Content(name, steps, durationMillis); // Content lisab kuupäeva ise
        scoreboard.add(newScore);
        Collections.sort(scoreboard); // sorteerib sammude järgi

        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (Content c : scoreboard) {
                out.println(c.getName() + ";" + c.getSteps() + ";" + c.getPlayedAt() + ";" + c.getDurationMillis());
                ;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * loeb edetabeli faili sisu ja lisab sisu listi
     *
     * @return edetabeli list
     */
    public List<Content> loadScores() {
        scoreboard.clear();
        File file = new File(filename);
        if (!file.exists()) return scoreboard;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(";");
                if (parts.length == 4) {
                    String name = parts[0];
                    int steps = Integer.parseInt(parts[1]);
                    LocalDateTime playedAt = LocalDateTime.parse(parts[2]);
                    long duration = Long.parseLong(parts[3]);
                    scoreboard.add(new Content(name, steps, playedAt, duration));
                } else if (parts.length == 3) {
                    String name = parts[0];
                    int steps = Integer.parseInt(parts[1]);
                    LocalDateTime playedAt = LocalDateTime.parse(parts[2]);
                    scoreboard.add(new Content(name, steps, playedAt, 0));
                } else if (parts.length == 2) {
                    String name = parts[0];
                    int steps = Integer.parseInt(parts[1]);
                    scoreboard.add(new Content(name, steps, LocalDateTime.now(), 0));
                }
            }
            Collections.sort(scoreboard);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return scoreboard;
    }

}
