/**
 * mõõdab taustaks mängu aega
 */
public class Stopwatch {
    //3 muutujat
    private long startTime;
    private long elapsedTime;
    private boolean running;

    //MEETODID

    /**
     * käivitab stopperi
     */
    public void start() { //kui ei tööta, siis käivitab
        if(!running) {
            startTime = System.currentTimeMillis();
            running = true;
        }
    }

    /**
     * peatab stopperi
     */
    public void stop() { //kui töötab
        if(running) {
            elapsedTime = System.currentTimeMillis() - startTime; //olemasolevast ajast on vaja lahtudada stardiaeg ja aeg seisma jätta
            running = false;
        }
    }

    /**
     * tagastab vormindatud mänguaja
     * @return vormindatud aeg
     */
    public String getElapsedTime() {
        long totalMillis = elapsedTime;
        if(running) {
            totalMillis = System.currentTimeMillis() - startTime;
        }
        long seconds = totalMillis / 1000;
        long hours = seconds / 3600;
        long minutes = seconds % 3600 / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs); //00:00:00 (02 tähendab kahekohalist numbrit)
    }

    /**
     * tagastab mänguaja millisekundites
     * @return mängu aeg
     */
    public long getElapsedMillis() {
        long totalMillis = elapsedTime;
        if(running) {
            totalMillis = System.currentTimeMillis() - startTime;
        }
        return totalMillis;
    }
}
