import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class NHLListDemo {
    public static void main(String[] args) {
        NHLStats stats = new NHLStats();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the filename to read from:");
        String filename = input.nextLine();

        File file = new File(filename);
        try (Scanner fileScanner = new Scanner(file);
             PrintWriter output = new PrintWriter("nhlstatsoutput.txt")) {

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;  // Skip empty lines

                StringTokenizer tokenizer = new StringTokenizer(line, "\t");
                String name = tokenizer.nextToken();
                String position = tokenizer.nextToken();
                String team = tokenizer.nextToken();
                int gamesPlayed = Integer.parseInt(tokenizer.nextToken());
                int goals = Integer.parseInt(tokenizer.nextToken());
                int assists = Integer.parseInt(tokenizer.nextToken());
                int penaltyMinutes = Integer.parseInt(tokenizer.nextToken());
                int shotsOnGoal = Integer.parseInt(tokenizer.nextToken());
                int gameWinningGoals = Integer.parseInt(tokenizer.nextToken());

                PlayerRecord player = new PlayerRecord(name, position, team, gamesPlayed, goals, assists, penaltyMinutes, shotsOnGoal, gameWinningGoals);
                stats.addPlayer(player);
            }

            output.println("NHL Results Summary");
            output.println(" ");
            output.println("Players with highest points and their teams:");
            stats.displayTopScorers(output);

            output.println("Most aggressive players, their teams and their positions:");
            stats.displayMostAggressivePlayers(output);

            output.println("Most valuable players and their teams:");
            stats.displayMVPs(output);

            output.println("Most promising players and their teams:");
            stats.displayMostPromisingPlayers(output);

            output.println("Teams that had the most number of penalty minutes:");
            stats.displayTeamsWithMostPenaltyMinutes(output);

            output.println("Teams that had the most number of game winning goals:");
            stats.displayTeamsWithMostGWGoals(output);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
