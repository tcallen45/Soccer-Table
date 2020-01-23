import java.util.Comparator;
public class Ranking implements Comparator<Team>{
    public int compare(Team t1, Team t2) {
        if(t1.getPoints() == t2.getPoints()) {
            if(t1.getGoalDifference() == t2.getGoalDifference()) {
                if(t1.getGoalsFor() == t2.getGoalsFor()) {
                    return 0;
                } else if(t1.getGoalsFor() > t2.getGoalsFor()) {
                    return -1;
                } else if(t1.getGoalsFor() < t2.getGoalsFor()) {
                    return 1;
                }
            } else if(t1.getGoalDifference() > t2.getGoalDifference()) {
                return -1;
            } else if(t1.getGoalDifference() < t2.getGoalDifference()) {
                return 1;
            }
        } else if(t1.getPoints() > t2.getPoints()) {
            return -1;
        } else if(t2.getPoints() < t2.getPoints()) {
            return 1;
        }
        return 0;
    }
}
