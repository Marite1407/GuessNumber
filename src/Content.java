import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * klass, mis on mõeldud edetabeli faili sisuga majandamiseks
 */
public class Content implements Comparable<Content> {
    private final String name;  // mängija nimi
    private final int steps;    // sammude arv
    private final LocalDateTime playedAt; // mängu hetk
    private final long durationMillis;    // mängu kestvus millisekundites

    // Uus tulemuse loomine
    public Content(String name, int steps, long durationMillis) {
        this.name = name;
        this.steps = steps;
        this.playedAt = LocalDateTime.now();
        this.durationMillis = durationMillis;
    }

    // Failist laadimiseks
    public Content(String name, int steps, LocalDateTime playedAt, long durationMillis) {
        this.name = name;
        this.steps = steps;
        this.playedAt = playedAt;
        this.durationMillis = durationMillis;
    }

    // Getterid
    public String getName() {
        return name;
    }

    public int getSteps() {
        return steps;
    }

    public LocalDateTime getPlayedAt() {
        return playedAt;
    }

    public long getDurationMillis() {
        return durationMillis;
    }

    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return playedAt.format(formatter);
    }

    public String getFormattedDuration() {
        return String.format("%.2f sek", durationMillis / 1000.0);
    }

    @Override
    public int compareTo(Content o) {
        int bySteps = Integer.compare(this.steps, o.steps);
        if (bySteps != 0) return bySteps;
        return this.playedAt.compareTo(o.playedAt); // varasem aeg eespool
    }


    public String formattedData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");   // teeme kuupäeva ja kellaaja eestipäraseks
        String date = playedAt.format(formatter);
        String displayName = name.length() > 15 ? name.substring(0, 15) : String.format("%-15s", name);
        String n = String.format("%-15s", displayName);
        String s = String.format("%3d", steps);
        String time = durationMillis + " ms";   // Näitab edetabelis millisekundeid
        return date + " | " + n + s + " sammu | " + time;
    }
}
