import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

public class League {
    private String name;

    private ArrayList<Team> teams;
    
    private String filename;
    
    private File logo;
    
    private String[] teamList;
    
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
    
    public File getLogo() {
        return this.logo;
    }
    
    public void setLogo(String filename) {
        this.logo = new File(filename);
    }
    
    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }
    
    public ArrayList<Team> getTeams(){
        if(teams.size() == 0 || teams == null) {
            return null;
        }
        else {
            return teams;
        }
    }
    
    public final String[] getTeamList(){
        String[] teamList = new String[this.teams.size()];
        for(int i = 0; i < this.teams.size(); i++) {
            teamList[i] = this.teams.get(i).getName();
        }
        return teamList;
    }
    
    @Override
    public String toString() {
        String retval = "";
        if(teams.size() == 0 || teams == null) {
            return "No Teams";
        }
        else {
            for(int i = 0; i < teams.size(); i++) {
                if(i != teams.size() - 1) {
                    retval += teams.get(i) + ", ";
                } else {
                    retval += teams.get(i);
                }
                
            }
            return  name + " (" + retval + ")";
        }
            
    }

    public League(String name) {
        this.name = name;
        this.teams = null;
        this.filename = null;
        this.logo = new File(name);
    }
    
    public League(String name, ArrayList<Team> teams) {
        this.name = name;
        this.teams = teams;
        this.filename= null;
        this.logo = new File(name);
    }
    
    public static void main(String[] args) {
        League test = new League("PL");
        ArrayList<Team> team = new ArrayList<Team>();
        Team arsenal = new Team("Arsenal");
        Team city = new Team("City");
        team.add(arsenal);
        team.add(city);
        test.setTeams(team);
        System.out.println(test.getTeams());
    }
}
