import java.io.*;
import java.util.*;

public class HTrain {
    static class Train {
        String originalLine;
        String trainName;
        int departureTimeMinutes; // time in minutes since 00:00

        Train(String line) {
            this.originalLine = line;
            // parse train name: before " will"
            int willIdx = line.indexOf(" will");
            this.trainName = line.substring(0, willIdx);

            // parse time: after last "at "
            int atIdx = line.lastIndexOf("at ");
            String timeStr = line.substring(atIdx + 3).trim();
            this.departureTimeMinutes = timeToMinutes(timeStr);
        }

        // Converts HH:MM -> total minutes since 00:00
        static int timeToMinutes(String time) {
            String[] parts = time.split(":");
            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);
            return hour * 60 + minute;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader task = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(task.readLine().trim());
        List<Train> trains = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String line = task.readLine();
            trains.add(new Train(line));
        }

        trains.sort((a, b) -> {
            int nameComp = a.trainName.compareTo(b.trainName);
            if (nameComp != 0) return nameComp;
            // For same train names, latest time first (later time is larger)
            return Integer.compare(b.departureTimeMinutes, a.departureTimeMinutes);
            // Stable sort: input order preserved automatically
        });

        for (Train train : trains) {
            System.out.println(train.originalLine);
        }
    }
}
