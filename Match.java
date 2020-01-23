public class Match {
    private Object homeTeam;
    
    private Object awayTeam;
    
    private int homeScore;
    
    private int awayScore;
    
    private String homeResult; //0 draw; >0 home win; <0 away win
    
    private String awayResult;
    
    public Object getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Object homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Object getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Object awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public String getHomeResult() {
        return homeResult;
    }
    
    public String getAwayResult() {
        return awayResult;
    }

    public void setHomeResult() {
        if(this.homeScore == this.awayScore) {
            this.homeResult = "D";
        } else if(this.homeScore > this.awayScore) {
            this.homeResult = "W";
        } else if(this.homeScore < this.awayScore) {
            this.homeResult = "L";
        }
    }
    
    public void setAwayResult() {
        if(this.homeScore == this.awayScore) {
            this.awayResult = "D";
        } else if(this.homeScore > this.awayScore) {
            this.awayResult = "L";
        } else if(this.homeScore < this.awayScore) {
            this.awayResult = "W";
        }
    }
    
    @Override
    public String toString() {
        return this.homeTeam + " " + this.homeScore + " v. " + this.awayScore + " " + this.awayTeam;
    }

    public Match(Object homeTeam, Object awayTeam, int homeScore, int awayScore) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.homeResult = "";
        this.awayResult = "";
    }
}
