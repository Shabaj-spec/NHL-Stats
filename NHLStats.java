import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.PrintWriter;

public class NHLStats {
    private ArrayList<PlayerRecord> players;

    public NHLStats() {
        players = new ArrayList<>();
    }

    public void addPlayer(PlayerRecord player) {
        players.add(player);
    }

    // Display the player(s) with the most points
    public void displayTopScorers(PrintWriter output) {
        int maxPoints = 0;
        for (PlayerRecord player : players) {
            int points = player.getTotalPoints();
            if (points > maxPoints) {
                maxPoints = points;
            }
        }

        for (PlayerRecord player : players) {
            if (player.getTotalPoints() == maxPoints) {
                output.println(player.getName() + " - " + player.getTeam());
            }
        }
    }

    // Display the most aggressive player(s)
    public void displayMostAggressivePlayers(PrintWriter output) {
        int maxPenaltyMinutes = 0;
        for (PlayerRecord player : players) {
            if (player.getPenaltyMinutes() > maxPenaltyMinutes) {
                maxPenaltyMinutes = player.getPenaltyMinutes();
            }
        }

        for (PlayerRecord player : players) {
            if (player.getPenaltyMinutes() == maxPenaltyMinutes) {
                output.println(player.getName() + " - " + player.getTeam() + " (" + player.getPosition() + ")");
            }
        }
    }

    // Display the MVP(s) for game-winning goals
    public void displayMVPs(PrintWriter output) {
        int maxGWGoals = 0;
        for (PlayerRecord player : players) {
            if (player.getGameWinningGoals() > maxGWGoals) {
                maxGWGoals = player.getGameWinningGoals();
            }
        }

        for (PlayerRecord player : players) {
            if (player.getGameWinningGoals() == maxGWGoals) {
                output.println(player.getName() + " - " + player.getTeam());
            }
        }
    }

    // Display the most promising player(s)
    public void displayMostPromisingPlayers(PrintWriter output) {
        int maxShots = 0;
        for (PlayerRecord player : players) {
            if (player.getShotsOnGoal() > maxShots) {
                maxShots = player.getShotsOnGoal();
            }
        }

        for (PlayerRecord player : players) {
            if (player.getShotsOnGoal() == maxShots) {
                output.println(player.getName() + " - " + player.getTeam());
            }
        }
    }

    // Display the team(s) with the most penalty minutes
    public void displayTeamsWithMostPenaltyMinutes(PrintWriter output) {
        HashMap<String, Integer> teamPenalties = new HashMap<>();
        for (PlayerRecord player : players) {
            teamPenalties.merge(player.getTeam(), player.getPenaltyMinutes(), Integer::sum);
        }

        int maxPenalty = teamPenalties.values().stream().max(Integer::compareTo).orElse(0);

        teamPenalties.forEach((team, penalty) -> {
            if (penalty == maxPenalty) {
                output.println(team);
            }
        });
    }

    // Display the team(s) with the most game-winning goals
    public void displayTeamsWithMostGWGoals(PrintWriter output) {
        HashMap<String, Integer> teamGWGoals = new HashMap<>();
        for (PlayerRecord player : players) {
            teamGWGoals.merge(player.getTeam(), player.getGameWinningGoals(), Integer::sum);
        }

        int maxGWGoals = teamGWGoals.values().stream().max(Integer::compareTo).orElse(0);

        teamGWGoals.forEach((team, goals) -> {
            if (goals == maxGWGoals) {
                output.println(team);
            }
        });
    }
}


