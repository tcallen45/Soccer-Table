import java.util.ArrayList;

public class Team{
    private String name;
    
    private String filename;

    private int gamesPlayed;
    
    private int wins;
    
    private int losses;
    
    private int draws;
    
    private int points;
    
    private int goalsFor;
    
    private int goalsAgainst;
    
    private int goalDifference;
    
    private ArrayList<Match> matches;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed() {
        this.gamesPlayed = matches.size();
    }

    public int getWins() {
        return wins;
    }

    public void setWins() {
        int tempWins = 0;
        for(Match m : matches) {
            if((this.name.equals(m.getHomeTeam().toString()) && m.getHomeResult().equals("W")) || (this.name.equals(m.getAwayTeam().toString()) && m.getAwayResult().equals("W"))) {
                tempWins++;
            }
        }
        this.wins = tempWins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses() {
        int tempLosses = 0;
        for(Match m : matches) {
            if((this.name.equals(m.getHomeTeam().toString()) && m.getHomeResult().equals("L")) || (this.name.equals(m.getAwayTeam().toString()) && m.getAwayResult().equals("L"))) {
                tempLosses++;
            }
        }
        this.losses = tempLosses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws() {
        int tempDraws = 0;
        for(Match m : matches) {
            if((this.name.equals(m.getHomeTeam().toString()) && m.getHomeResult().equals("D")) || (this.name.equals(m.getAwayTeam().toString()) && m.getAwayResult().equals("D"))) {
                tempDraws++;
            }
        }
        this.draws = tempDraws;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints() {
        this.points = (this.wins * 3) + this.draws;
    }

    @Override
    public String toString() {
        return name;
    }
    
    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor() {
        int tempGoalsFor = 0;
        for(Match m : matches) {
            if(this.name.equals(m.getHomeTeam().toString())) {
                tempGoalsFor += m.getHomeScore();
            } else if(this.name.equals(m.getAwayTeam().toString())) {
                tempGoalsFor += m.getAwayScore();
            }
        }
        this.goalsFor = tempGoalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst() {
        int tempGoalsAgainst = 0;
        for(Match m : matches) {
            if(this.name.equals(m.getHomeTeam().toString())) {
                tempGoalsAgainst += m.getAwayScore();
            } else if(this.name.equals(m.getAwayTeam().toString())) {
                tempGoalsAgainst += m.getHomeScore();
            }
        }
        this.goalsAgainst = tempGoalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference() {
        this.goalDifference = this.goalsFor - this.goalsAgainst;
    }
    
    public ArrayList<Match> getMatches() {
        return matches;
    }
    
    public void addMatch(Match m) {
        this.matches.add(m);
    }
    
    public void removeMatch(Match m) {
        ArrayList<Match> newMatches = new ArrayList<Match>();
        for(Match game : getMatches()) {
            if(!(m.equals(game))) {
                newMatches.add(game);
            }
        }
        this.matches = newMatches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public Team(String name) {
        this.name = name;
        this.gamesPlayed = 0;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
        this.points = 0;
        this.goalsFor = 0;
        this.goalsAgainst = 0;
        this.goalDifference = 0;
        this.matches = new ArrayList<Match>();
    }
    
    public static void main(String[] args) {
        Team test = new Team("Arsenal");
        Match m = new Match("Arsenal", "Chelsea", 2, 1);
        Match m2 = new Match("Brighton", "Arsenal", 1, 3);
        Match m3 = new Match("Arsenal", "ManU", 2, 2);
        test.addMatch(m);
        test.addMatch(m2);
        test.addMatch(m3);
        test.removeMatch(m2);
    }
}
