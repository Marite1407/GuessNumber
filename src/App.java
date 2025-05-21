/**
 * põhi fail rakenduse käivitamiseks
 */
public class App {
    /**
     * sellega käivitatakse rakendus
     * @param args käsurea argumendid
     */
    public static void main(String[] args) {
        Model model =  new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.start(); //käivita mäng
    }
}
