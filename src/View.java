import java.util.List;
import java.util.Scanner;

/**
 * kõik, mida kasutaja näeb konsoolis
 */
public class View {
    private final Scanner scanner = new Scanner(System.in); //muutuja, mis hoiab üleval küsimist

    /**
     * mängu menüü näitamine
     */
    public void showMenu() {
        System.out.println("1. Mängima");
        System.out.println("2. Edetabel");
        System.out.println("3. Välju");
        System.out.print("Tee valik: ");
    }

    /**
     * tagastab kasutaja sisestuse
     * @return kasutaja sisestus
     */
    public int getMenuChoice() {
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * väljastab ette antud teate konsooli
     * @param message teade, mida väljastada
     */
    public void showMessage(String message) { //void ei tagasta midagi
        System.out.println(message);
    }

    /**
     * küsib kasutajalt numbrit
     * @return kasutaja sisestatud number
     */
    public int askGuess() {
        System.out.print("Sisesta number: "); //kui oleks print ln, siis tuleks reavahetus ja jääks ootama, aga me tahame sama rea peale seda
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * küsib mängija nime mängu lõpus
     * @return sisestatud nimi
     */
    public String askName() {
        System.out.print("Sisesta nimi: ");
        return scanner.nextLine();
    }

    /**
     * näitab konsoolis edetabelit
     * @param scores faili sisu listina
     */
    public void showScoreboard(List<Content> scores) {
        System.out.println("EDETABEL");
        System.out.println("-----------");
        for (Content c : scores) {
            System.out.println(c.formattedData());
        }
        System.out.println(); //tühi rida
    }
}
