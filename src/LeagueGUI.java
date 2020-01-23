import java.awt.*;
import java.util.Collections;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

public class LeagueGUI extends JFrame {

    private JButton eplButton;
    private JButton bundesligaButton;
    private JButton laLigaButton;
    private JButton ligue1Button;
    private JButton seriaAButton;
    private JButton mlsButton;

    private JButton enterResult;
    private JButton submitResult;
    private JButton goBack;
    private JButton deleteMatch;
    private JButton editMatch;

    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;

    private JLabel pos;
    private JLabel gp;
    private JLabel points;
    private JLabel wins;
    private JLabel losses;
    private JLabel draws;
    private JLabel gf;
    private JLabel ga;
    private JLabel gd;

    private JLabel home;
    private JLabel away;
    private JLabel sHome;
    private JLabel sAway;

    private JLabel team;

    private ArrayList<JButton> leagueButton;
    private ArrayList<JButton> teamButton;
    private ArrayList<JButton> deleteButton;
    private ArrayList<JButton> editButton;

    private static String[] premTeamName = new String[] { "Arsenal", "Aston Villa", "Bournemouth", "Brighton", "Burnley",
            "Chelsea", "Crystal Palace", "Everton", "Leicester", "Liverpool", "Man City", "Man United", "Newcastle", "Norwich",
            "Sheffield United", "Southampton", "Tottenham", "Watford", "West Ham", "Wolves" };
    private static String[] bundesligaTeamName = new String[] { "Augsburg", "B. Monchengladbach", "Bayer Leverkusen",
            "Bayern Munich", "Dortmund", "Dusseldorf", "Eintracht Frankfurt", "Koln", "Freiburg", "Hertha Berlin", "Hoffenheim",
            "Mainz", "Paderborn", "RB Leipzig", "Schalke", "Union Berlin", "Werder Bremen", "Wolfsburg" };
    private static String[] laLigaTeamName = new String[] { "Alaves", "Athletic Bilbao", "Athletico Madrid", "Barcelona",
            "Real Betis", "Celta Vigo", "Eibar", "Espanyol", "Getafe", "Granada", "Leganes", "Levante", "Mallorca", "Osasuna",
            "Real Madrid", "Real Sociedad", "Sevilla", "Valencia", "Valladolid", "Villarreal" };
    private static String[] ligue1TeamName = new String[] { "Amiens", "Angers", "Bordeaux", "Brest", "Dijon", "Lille", "Lyon",
            "Marseille", "Metz", "Monaco", "Montpellier", "Nantes", "Nice", "Nimes", "PSG", "Reims", "Rennes", "St Etienne",
            "Strasbourg", "Toulouse" };
    private static String[] seriaATeamName = new String[] { "AC Milan", "Roma", "Atalanta", "Bologna", "Brescia", "Cagliari",
            "Fiorentina", "Genoa", "Inter Milan", "Juventus", "Lazio", "Lecce", "Napoli", "Parma", "Sampdoria", "Sassuolo",
            "Spal", "Torino", "Udinese", "Verona" };

    private JComboBox<String[]> homeList;
    private JComboBox<String[]> awayList;
    private String[] alphaTeams;

    private JTextField homeScore;
    private JTextField awayScore;

    private int selectedLeague;
    private Team selectedTeam;
    private Match selectedMatch;

    private Color background = new Color(220, 220, 220);

    public static void main(String[] args) {
        String imageDirectory = "logos\\";
        LeagueGUI leagues = new LeagueGUI();
        ArrayList<League> allLeagues = new ArrayList<>();
        ArrayList<Team> premTeams = new ArrayList<Team>();
        ArrayList<Team> bundTeams = new ArrayList<Team>();
        ArrayList<Team> laLigaTeams = new ArrayList<Team>();
        ArrayList<Team> ligue1Teams = new ArrayList<Team>();
        ArrayList<Team> seriaATeams = new ArrayList<Team>();

        League EPL = new League("Premier League");
        League Bundesliga = new League("Bundesliga");
        League LaLiga = new League("La Liga");
        League Ligue1 = new League("Ligue 1");
        League SeriaA = new League("Seria A");
        League MLS = new League("MLS");

        EPL.setFilename(imageDirectory + "epl.png");
        Bundesliga.setFilename(imageDirectory + "bundesliga.png");
        LaLiga.setFilename(imageDirectory + "laliga.png");
        Ligue1.setFilename(imageDirectory + "ligue1.png");
        SeriaA.setFilename(imageDirectory + "seriaA.png");
        MLS.setFilename(imageDirectory + "mls.jpg");

        allLeagues.add(EPL);
        allLeagues.add(Bundesliga);
        allLeagues.add(LaLiga);
        allLeagues.add(Ligue1);
        allLeagues.add(SeriaA);
        // allLeagues.add(MLS);

        for (String s : premTeamName) {
            Team newTeam = new Team(s);
            newTeam.setFilename(imageDirectory + s + ".png");
            premTeams.add(newTeam);
        }

        for (String s : bundesligaTeamName) {
            Team newTeam = new Team(s);
            newTeam.setFilename(imageDirectory + s + ".png");
            bundTeams.add(newTeam);
        }

        for (String s : laLigaTeamName) {
            Team newTeam = new Team(s);
            newTeam.setFilename(imageDirectory + s + ".png");
            laLigaTeams.add(newTeam);
        }

        for (String s : ligue1TeamName) {
            Team newTeam = new Team(s);
            newTeam.setFilename(imageDirectory + s + ".png");
            ligue1Teams.add(newTeam);
        }

        for (String s : seriaATeamName) {
            Team newTeam = new Team(s);
            newTeam.setFilename(imageDirectory + s + ".png");
            seriaATeams.add(newTeam);
        }

        EPL.setTeams(premTeams);
        Bundesliga.setTeams(bundTeams);
        LaLiga.setTeams(laLigaTeams);
        Ligue1.setTeams(ligue1Teams);
        SeriaA.setTeams(seriaATeams);
        Collections.sort(premTeams, new Ranking());
        Collections.sort(bundTeams, new Ranking());
        Collections.sort(laLigaTeams, new Ranking());
        Collections.sort(ligue1Teams, new Ranking());
        Collections.sort(seriaATeams, new Ranking());

        javax.swing.SwingUtilities.invokeLater(() -> leagues.createAndShowGUI(allLeagues));
    }

    private ImageIcon scaleImage(Image image, int w, int h) {
        Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaled);
        return scaledIcon;
    }

    private void createAndShowGUI(ArrayList<League> leagues) {
        LeagueGUI frame = new LeagueGUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponentsToPane(frame.getContentPane(), leagues);
        frame.pack();
        frame.setVisible(true);
    }

    public void addComponentsToPane(Container pane, ArrayList<League> leagues) {

        leagueButton = new ArrayList<JButton>();
        eplButton = new JButton(leagues.get(0).getName());
        bundesligaButton = new JButton(leagues.get(1).getName());
        laLigaButton = new JButton(leagues.get(2).getName());
        ligue1Button = new JButton(leagues.get(3).getName());
        seriaAButton = new JButton(leagues.get(4).getName());
        // mlsButton = new JButton(leagues.get(5).getName());
        leagueButton.add(eplButton);
        leagueButton.add(bundesligaButton);
        leagueButton.add(laLigaButton);
        leagueButton.add(ligue1Button);
        leagueButton.add(seriaAButton);
        // leagueButton.add(mlsButton);

        mainPanel = new JPanel();

        class ButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                //System.out.println(e.getActionCommand());
                //System.out.println(selectedMatch);
                for (League l : leagues) {
                    if (e.getActionCommand().equals(l.getName())) {
                        selectedLeague = leagues.indexOf(l);
                        alphaTeams = leagues.get(selectedLeague).getTeamList();
                        Arrays.sort(alphaTeams);

                        topPanel = new JPanel();
                        showTableScreen(leagues.get(selectedLeague));

                        File input = new File(l.getName() + ".txt");
                        try {
                            Scanner in = new Scanner(input);
                            while (in.hasNextLine()) {
                                String teamInfo = in.nextLine();

                                getTeamInfo(teamInfo, leagues.get(selectedLeague));
                                Collections.sort(leagues.get(selectedLeague).getTeams(), new Ranking());
                                showTableScreen(leagues.get(selectedLeague));

                                for (JButton b : teamButton) {
                                    b.addActionListener(new ButtonListener());
                                }
                            }
                            in.close();
                        } catch (FileNotFoundException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                        //pane.remove(topPanel);
                        insertTopPanel();
                        pane.add(topPanel, BorderLayout.NORTH);

                        bottomPanel = new JPanel();

                        goBack = new JButton("League Selection");
                        goBack.setActionCommand("homeBack");
                        goBack.addActionListener(new ButtonListener());
                        bottomPanel.add(goBack);

                        enterResult = new JButton("Enter Result");
                        enterResult.setActionCommand("resultFromTable");
                        enterResult.addActionListener(new ButtonListener());
                        bottomPanel.add(enterResult);

                        bottomPanel.setBackground(background);

                        pane.add(bottomPanel, BorderLayout.SOUTH);
                    }
                }

                for (League l : leagues) {
                    for (Team t : l.getTeams()) {
                        if (e.getActionCommand().equals(t.getName())) {

                            selectedTeam = t;

                            pane.remove(mainPanel);
                            
                            goBack.setText("Go Back");
                            goBack.setActionCommand("tableBack");
                            
                            pane.add(bottomPanel, BorderLayout.SOUTH);
                            
                            showMatchScreen(t);
                            
                            for(JButton d : deleteButton) {
                                d.addActionListener(new ButtonListener());
                            }
                            
                            for(JButton edit : editButton) {
                                edit.addActionListener(new ButtonListener());
                            }

                            pane.add(mainPanel, BorderLayout.CENTER);

                        }

                    }
                }

                for(League l : leagues) {
                    for(Team t : l.getTeams()) {
                        for(Match m : t.getMatches()) {
                            if(e.getActionCommand().equals("delete" + m.toString())) {// + t.getName())) {
                                t.removeMatch(m);
                                setTeamValues(t);
                                pane.remove(mainPanel);
                                
                                //System.out.println(t.getMatches().isEmpty());
                                
                                goBack.setText("Go Back");
                                goBack.setActionCommand("tableBack");
                                
                                showMatchScreen(selectedTeam);
                                
                                for(JButton d : deleteButton) {
                                    d.addActionListener(new ButtonListener());
                                }
                                for(JButton edit : editButton) {
                                    edit.addActionListener(new ButtonListener());
                                }
                                
                                pane.add(mainPanel, BorderLayout.CENTER);
                                pane.revalidate();
                            }
                            if(e.getActionCommand().equals("edit" + m.toString())) {
                                //t.removeMatch(m);
                                selectedMatch = m;
                                pane.remove(mainPanel);
                                pane.remove(topPanel);
                                pane.remove(bottomPanel);
                                mainPanel = new JPanel();
                                mainPanel.setBackground(background);
                                
                                bottomPanel = new JPanel();
                                mainPanel.setBackground(background);
                                
                                home = new JLabel("Enter Home Team");
                                home.setHorizontalAlignment(SwingConstants.CENTER);
          
                                away = new JLabel("Enter Away Team");
                                away.setHorizontalAlignment(SwingConstants.CENTER);

                                homeList = new JComboBox(alphaTeams);
                                awayList = new JComboBox(alphaTeams);
                                
                                homeList.setSelectedItem(m.getHomeTeam());
                                awayList.setSelectedItem(m.getAwayTeam());
                                
                                sHome = new JLabel("Home Score");
                                sHome.setHorizontalAlignment(SwingConstants.CENTER);
                                
                                homeScore = new JTextField(2);
                                homeScore.setText(Integer.toString(m.getHomeScore()));

                                sAway = new JLabel("Away Score");
                                sAway.setHorizontalAlignment(SwingConstants.CENTER);
                                
                                awayScore = new JTextField(2);
                                awayScore.setText(Integer.toString(m.getAwayScore()));
                                
                                mainPanel.add(home);
                                mainPanel.add(away);
                                mainPanel.add(homeList);
                                mainPanel.add(awayList);
                                mainPanel.add(sHome);
                                mainPanel.add(sAway);
                                mainPanel.add(homeScore);
                                mainPanel.add(awayScore);
                                
                                mainPanel.setLayout(new GridLayout(4, 2));
                                pane.add(mainPanel, BorderLayout.CENTER);
                                
                                bottomPanel.remove(enterResult);
                                
                                bottomPanel.add(goBack);
                                goBack.setActionCommand("teamBack");
                                
                                submitResult = new JButton("Edit Result");
                                submitResult.setActionCommand("submitTeam");
                                submitResult.addActionListener(new ButtonListener());
                                
                                bottomPanel.add(submitResult);
                                
//                                submitResult.setText("Edit Result");
                                pane.add(bottomPanel, BorderLayout.SOUTH);
                                pane.revalidate();
                            }
                            //System.out.println(selectedMatch);
                        }
                    }
                }

                if (e.getActionCommand().equals("resultFromTable")) {
                    mainPanel.setLayout(new GridLayout(1, 1));
                    mainPanel.removeAll();
                    mainPanel.revalidate();

                    home = new JLabel("Enter Home Team");
                    home.setHorizontalAlignment(SwingConstants.CENTER);

                    away = new JLabel("Enter Away Team");
                    away.setHorizontalAlignment(SwingConstants.CENTER);

                    homeList = new JComboBox(alphaTeams);
                    awayList = new JComboBox(alphaTeams);

                    sHome = new JLabel("Home Score");
                    sHome.setHorizontalAlignment(SwingConstants.CENTER);
                    homeScore = new JTextField(2);

                    sAway = new JLabel("Away Score");
                    sAway.setHorizontalAlignment(SwingConstants.CENTER);
                    awayScore = new JTextField(2);

                    mainPanel.add(home);
                    mainPanel.add(away);
                    mainPanel.add(homeList);
                    mainPanel.add(awayList);
                    // mainPanel.add(homeTeam);
                    // mainPanel.add(awayTeam);
                    mainPanel.add(sHome);
                    mainPanel.add(sAway);
                    mainPanel.add(homeScore);
                    mainPanel.add(awayScore);

                    // System.out.println(homeList.getSelectedItem());

                    mainPanel.setLayout(new GridLayout(4, 2));
                    removeTopPanel();
                    // removeBottomPanel();

                    submitResult = new JButton("Submit Result");
                    submitResult.setActionCommand("submitTable");
                    submitResult.addActionListener(new ButtonListener());

                    goBack = new JButton("Go Back");
                    goBack.setActionCommand("tableBack");
                    goBack.addActionListener(new ButtonListener());

                    pane.remove(bottomPanel);

                    bottomPanel = new JPanel();
                    bottomPanel.add(goBack);
                    bottomPanel.add(submitResult);
                    bottomPanel.setBackground(background);
                    pane.add(bottomPanel, BorderLayout.SOUTH);

                }

                if (e.getActionCommand().equals("resultFromTeam")) {
                    mainPanel.setLayout(new GridLayout(1, 1));
                    mainPanel.removeAll();
                    mainPanel.revalidate();

                    home = new JLabel("Enter Home Team");
                    home.setHorizontalAlignment(SwingConstants.CENTER);
                    // JTextField homeTeam = new JTextField(30);

                    away = new JLabel("Enter Away Team");
                    away.setHorizontalAlignment(SwingConstants.CENTER);
                    // JTextField awayTeam = new JTextField(30);

                    homeList = new JComboBox(alphaTeams);
                    homeList.setSelectedItem(selectedTeam.toString());
                    awayList = new JComboBox(alphaTeams);
                    awayList.setSelectedItem(selectedTeam.toString());

                    sHome = new JLabel("Home Score");
                    sHome.setHorizontalAlignment(SwingConstants.CENTER);
                    homeScore = new JTextField(2);

                    sAway = new JLabel("Away Score");
                    sAway.setHorizontalAlignment(SwingConstants.CENTER);
                    awayScore = new JTextField(2);

                    mainPanel.add(home);
                    mainPanel.add(away);
                    mainPanel.add(homeList);
                    mainPanel.add(awayList);
                    // mainPanel.add(homeTeam);
                    // mainPanel.add(awayTeam);
                    mainPanel.add(sHome);
                    mainPanel.add(sAway);
                    mainPanel.add(homeScore);
                    mainPanel.add(awayScore);

                    // System.out.println(homeList.getSelectedItem());

                    mainPanel.setLayout(new GridLayout(4, 2));
                    removeTopPanel();
                    // removeBottomPanel();

                    submitResult = new JButton("Submit Result");
                    submitResult.setActionCommand("submitTeam");
                    submitResult.addActionListener(new ButtonListener());

                    goBack = new JButton("Go Back");
                    goBack.setActionCommand("teamBack");
                    goBack.addActionListener(new ButtonListener());

                    pane.remove(bottomPanel);

                    bottomPanel = new JPanel();
                    bottomPanel.add(goBack);
                    bottomPanel.add(submitResult);
                    bottomPanel.setBackground(background);
                    pane.add(bottomPanel, BorderLayout.SOUTH);

                }

                if (e.getActionCommand().equals("submitTable")) {
                    Match game = new Match(homeList.getSelectedItem(), awayList.getSelectedItem(),
                            Integer.parseInt(homeScore.getText()), Integer.parseInt(awayScore.getText()));
                    game.setHomeResult();
                    game.setAwayResult();
                    for (Team t : leagues.get(selectedLeague).getTeams()) {
                        if (game.getHomeTeam() != game.getAwayTeam()) {
                            if (homeList.getSelectedItem().equals(t.getName())) {
                                t.addMatch(game);
                                setTeamValues(t);
                            } else if (awayList.getSelectedItem().equals(t.getName())) {
                                t.addMatch(game);
                                setTeamValues(t);
                            }
                        }
                    }

                    Collections.sort(leagues.get(selectedLeague).getTeams(), new Ranking());
                    showTableScreen(leagues.get(selectedLeague));
                    for (JButton b : teamButton) {
                        b.addActionListener(new ButtonListener());
                    }

                    insertTopPanel();
                    pane.add(topPanel, BorderLayout.NORTH);

                    bottomPanel.removeAll();
                    goBack = new JButton("League Selection");
                    goBack.setActionCommand("homeBack");
                    goBack.addActionListener(new ButtonListener());
                    bottomPanel.add(goBack);

                    enterResult = new JButton("Enter Result");
                    enterResult.setActionCommand("resultFromTable");
                    enterResult.addActionListener(new ButtonListener());
                    bottomPanel.add(enterResult);

                    bottomPanel.setBackground(background);
                    
                    saveMatches(leagues);

                    pane.add(bottomPanel, BorderLayout.SOUTH);
                }

                if (e.getActionCommand().equals("submitTeam")) {
                    Match game = new Match(homeList.getSelectedItem(), awayList.getSelectedItem(),
                            Integer.parseInt(homeScore.getText()), Integer.parseInt(awayScore.getText()));
                    game.setHomeResult();
                    game.setAwayResult();
                    //System.out.println(selectedMatch.toString());
                    for (Team t : leagues.get(selectedLeague).getTeams()) {
                        //System.out.println(t.getName());
                        //System.out.println(selectedMatch);
                        if(t.getMatches().contains(selectedMatch)) {
                            t.removeMatch(selectedMatch);
                        }
                        
                        if (game.getHomeTeam() != game.getAwayTeam()) {
                            if (homeList.getSelectedItem().equals(t.getName())) {
                                t.addMatch(game);
                                setTeamValues(t);
                            } else if (awayList.getSelectedItem().equals(t.getName())) {
                                //t.removeMatch(selectedMatch);
                                t.addMatch(game);
                                setTeamValues(t);
                            }
                        }
                    }

                    Collections.sort(leagues.get(selectedLeague).getTeams(), new Ranking());
                    
                    
                    pane.remove(mainPanel);
                    pane.remove(topPanel);
                    pane.remove(bottomPanel);
                    mainPanel = new JPanel();
                    mainPanel.setBackground(background);
                    
                    bottomPanel = new JPanel();

                    showMatchScreen(selectedTeam);
                    
                    for(JButton d : deleteButton) {
                        d.addActionListener(new ButtonListener());
                    }
                    for(JButton edit : editButton) {
                        edit.addActionListener(new ButtonListener());
                    }
                    
                    insertTopPanel();

                    bottomPanel.removeAll();
                    goBack = new JButton("Go Back");
                    goBack.setActionCommand("tableBack");
                    goBack.addActionListener(new ButtonListener());
                    bottomPanel.add(goBack);

                    enterResult = new JButton("Enter Result");
                    enterResult.setActionCommand("resultFromTeam");
                    enterResult.addActionListener(new ButtonListener());
                    bottomPanel.add(enterResult);

                    bottomPanel.setBackground(background);
                    
                    saveMatches(leagues);

                    pane.add(mainPanel, BorderLayout.CENTER);
                    pane.add(bottomPanel, BorderLayout.SOUTH);
                    
                    pane.revalidate();
                }
                
                if (e.getActionCommand().equals("homeBack")) {
                    mainPanel.removeAll();
                    mainPanel.revalidate();
                    pane.revalidate();
                    pane.remove(topPanel);
                    pane.remove(bottomPanel);
                    for (JButton b : leagueButton) {
                        mainPanel.add(b);
                        ImageIcon logo = new ImageIcon(leagues.get(leagueButton.indexOf(b)).getFilename());
                        ImageIcon scaledLogo = scaleImage(logo.getImage(), 150, 150);
                        b.setIcon(scaledLogo);
                        b.setHorizontalTextPosition(AbstractButton.CENTER);
                        b.setVerticalTextPosition(AbstractButton.BOTTOM);
                        b.setActionCommand(leagues.get(leagueButton.indexOf(b)).getName());
                        b.addActionListener(new ButtonListener());
                    }
                    mainPanel.setLayout(new GridLayout(2, 3));
                    pane.add(mainPanel, BorderLayout.CENTER);
                }

                if (e.getActionCommand().equals("tableBack")) {

                    pane.remove(mainPanel);
                    mainPanel = new JPanel();
                    mainPanel.setBackground(background);

                    Collections.sort(leagues.get(selectedLeague).getTeams(), new Ranking());
                    showTableScreen(leagues.get(selectedLeague));
                    for (JButton b : teamButton) {
                        b.addActionListener(new ButtonListener());
                    }
                    topPanel.removeAll();
                    insertTopPanel();
                    pane.add(topPanel, BorderLayout.NORTH);

                    removeBottomPanel();

                    goBack = new JButton("League Selection");
                    goBack.setActionCommand("homeBack");
                    goBack.addActionListener(new ButtonListener());
                    bottomPanel.add(goBack);

                    enterResult = new JButton("Enter Result");
                    enterResult.setActionCommand("resultFromTable");
                    enterResult.addActionListener(new ButtonListener());
                    bottomPanel.add(enterResult);

                    bottomPanel.setBackground(background);

                    pane.add(mainPanel, BorderLayout.CENTER);
                    pane.add(bottomPanel, BorderLayout.SOUTH);
                }

                if (e.getActionCommand().equals("teamBack")) {
                    for (Team t : leagues.get(selectedLeague).getTeams()) {
                        if (t.getName().equals(selectedTeam.getName())) {
                            pane.remove(mainPanel);
                            pane.remove(bottomPanel);
                            
                            bottomPanel = new JPanel();
                            bottomPanel.setBackground(background);

                            goBack.setText("Go Back");
                            goBack.setActionCommand("tableBack");

                            enterResult.setActionCommand("resultFromTeam");

                            bottomPanel.add(goBack);
                            bottomPanel.add(enterResult);
                            
                            pane.add(bottomPanel, BorderLayout.SOUTH);

                            showMatchScreen(selectedTeam);
                            
                            for(JButton d : deleteButton) {
                                d.addActionListener(new ButtonListener());
                            }
                            for(JButton edit : editButton) {
                                edit.addActionListener(new ButtonListener());
                            }

                            pane.add(mainPanel, BorderLayout.CENTER);
                            pane.revalidate();
                        }
                    }
                }
            }
        }

        for (JButton b : leagueButton) {
            mainPanel.add(b);
            ImageIcon logo = new ImageIcon(leagues.get(leagueButton.indexOf(b)).getFilename());
            ImageIcon scaledLogo = scaleImage(logo.getImage(), 150, 150);
            b.setIcon(scaledLogo);
            b.setHorizontalTextPosition(AbstractButton.CENTER);
            b.setVerticalTextPosition(AbstractButton.BOTTOM);
            b.setActionCommand(leagues.get(leagueButton.indexOf(b)).getName());
            b.addActionListener(new ButtonListener());
        }

        mainPanel.setLayout(new GridLayout(2, 3));
        mainPanel.setBackground(background);
        pane.add(mainPanel, BorderLayout.CENTER);
    }

    public void insertTopPanel() {
        String[] labelArray = new String[] { "Pos.", "Teams", "GP", "Wins", "Draws", "Losses", "For",
                "Against", "GD", "Points" };
        for (String s : labelArray) {
            JLabel label = new JLabel(s, SwingConstants.CENTER);
            topPanel.add(label);
        }
        topPanel.setLayout(new GridLayout(1, 9));
        topPanel.setBackground(background);
    }

    public void removeTopPanel() {
        topPanel.removeAll();
        topPanel.revalidate();
        topPanel.setLayout(new GridLayout(1, 1));
    }

    public void removeBottomPanel() {
        bottomPanel.removeAll();
        bottomPanel.revalidate();
        // bottomPanel.setLayout(new GridLayout(1, 1));
    }

    public void showTableScreen(League l) {
        ArrayList<Team> leagueTeams = new ArrayList<Team>();
        teamButton = new ArrayList<JButton>();
        mainPanel.setLayout(new GridLayout(1, 1));
        mainPanel.removeAll();
        mainPanel.revalidate();
        leagueTeams = l.getTeams();
        for (Team t : leagueTeams) {
            JButton team = new JButton(t.getName());
            teamButton.add(team);
            team.setActionCommand(t.getName());

            ImageIcon logo = new ImageIcon(leagueTeams.get(leagueTeams.indexOf(t)).getFilename());
            ImageIcon scaledLogo = scaleImage(logo.getImage(), 50, 50);
            team.setIcon(scaledLogo);
            team.setHorizontalAlignment(SwingConstants.LEFT);
            team.setHorizontalTextPosition(SwingConstants.RIGHT);
            team.setVerticalTextPosition(SwingConstants.CENTER);

            pos = new JLabel(Integer.toString(leagueTeams.indexOf(t) + 1));
            pos.setHorizontalAlignment(SwingConstants.CENTER);

            gp = new JLabel(Integer.toString(t.getGamesPlayed()));
            gp.setHorizontalAlignment(SwingConstants.CENTER);

            points = new JLabel(Integer.toString(t.getPoints()));
            points.setHorizontalAlignment(SwingConstants.CENTER);

            wins = new JLabel(Integer.toString(t.getWins()));
            wins.setHorizontalAlignment(SwingConstants.CENTER);

            losses = new JLabel(Integer.toString(t.getLosses()));
            losses.setHorizontalAlignment(SwingConstants.CENTER);

            draws = new JLabel(Integer.toString(t.getDraws()));
            draws.setHorizontalAlignment(SwingConstants.CENTER);

            gf = new JLabel(Integer.toString(t.getGoalsFor()));
            gf.setHorizontalAlignment(SwingConstants.CENTER);

            ga = new JLabel(Integer.toString(t.getGoalsAgainst()));
            ga.setHorizontalAlignment(SwingConstants.CENTER);

            gd = new JLabel(Integer.toString(t.getGoalDifference()));
            gd.setHorizontalAlignment(SwingConstants.CENTER);

            mainPanel.add(pos);
            mainPanel.add(team);
            mainPanel.add(gp);

            mainPanel.add(wins);
            mainPanel.add(draws);
            mainPanel.add(losses);

            mainPanel.add(gf);
            mainPanel.add(ga);
            mainPanel.add(gd);

            mainPanel.add(points);
        }
        mainPanel.setLayout(new GridLayout(l.getTeams().size(), 9));
    }
    
    public void showMatchScreen(Team t) {
        deleteButton = new ArrayList<JButton>();
        editButton = new ArrayList<JButton>();
        
        mainPanel = new JPanel();
        mainPanel.setBackground(background);

        removeTopPanel();

        enterResult.setActionCommand("resultFromTeam");

        team = new JLabel(t.getName());

        if (t.getMatches().size() > 0) {
            mainPanel.setLayout(new GridLayout(t.getMatches().size(), 4));
            
            for (Match m : t.getMatches()) {
                JLabel match = new JLabel();
                JLabel result = new JLabel();
                
                deleteMatch = new JButton("Delete Match");
                editMatch = new JButton("Edit Match");
                
                if (m.getHomeTeam().toString().equals(t.getName())) {
                    deleteMatch.setActionCommand("delete" + m.toString());// + m.getHomeTeam());
                    deleteButton.add(deleteMatch);
                    editMatch.setActionCommand("edit" + m.toString());
                    editButton.add(editMatch);
                    match.setText(m.toString());
                    result.setText(m.getHomeResult());
                    match.add(result);
                    if(m.getHomeResult().equals("W")) {
                        result.setForeground(new Color(0, 153, 0));
                    } else if(m.getHomeResult().equals("D")) {
                        result.setForeground(Color.YELLOW);
                    } else if(m.getHomeResult().equals("L")) {
                        result.setForeground(Color.RED);
                    }
                }
                if (m.getAwayTeam().toString().equals(t.getName())) {
                    deleteMatch.setActionCommand("delete" + m.toString());
                    deleteButton.add(deleteMatch);
                    editMatch.setActionCommand("edit" + m.toString());
                    editButton.add(editMatch);
                    match.setText(m.toString());
                    result.setText(m.getAwayResult());
                    if(m.getAwayResult().equals("W")) {
                        result.setForeground(new Color(0, 153, 0));
                    } else if(m.getAwayResult().equals("D")) {
                        result.setForeground(Color.YELLOW);
                    } else if(m.getAwayResult().equals("L")) {
                        result.setForeground(Color.RED);
                    }
                }
                match.setHorizontalAlignment(SwingConstants.CENTER);
                result.setHorizontalAlignment(SwingConstants.CENTER);
                mainPanel.add(match);
                mainPanel.add(result);
                mainPanel.add(deleteMatch);
                mainPanel.add(editMatch);
                team.setHorizontalAlignment(SwingConstants.CENTER);
                topPanel.add(team);

            }

        } else {
            JLabel noGameMessage = new JLabel(
                    "No games have been entered for " + selectedTeam + ", please go back or enter a result");
            noGameMessage.setHorizontalAlignment(SwingConstants.CENTER);
            noGameMessage.setVerticalAlignment(SwingConstants.CENTER);
            mainPanel.add(noGameMessage);
            mainPanel.setLayout(new GridLayout(1, 1));
        }
    }

    public void setTeamValues(Team t) {
        t.setGamesPlayed();
        t.setGoalsFor();
        t.setGoalsAgainst();
        t.setGoalDifference();
        t.setWins();
        t.setLosses();
        t.setDraws();
        t.setPoints();
    }

    public void getTeamInfo(String s, League l) {
        String teamName = s.substring(0, s.indexOf(":"));
        String games = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
        String eachGame;
        String homeTeam;
        String awayTeam;
        int homeScore;
        int awayScore;

        ArrayList<Match> loadMatches = new ArrayList<Match>();
        for (Team t : l.getTeams()) {
            if (teamName.equals(t.getName())) {
                if (games.contains(", ")) {
                    while (games.contains(", ")) {
                        eachGame = games.substring(0, games.indexOf(", "));
                        games = games.substring(games.indexOf(", ") + 2, games.length());

                        // For each game
                        homeTeam = eachGame.substring(0, eachGame.lastIndexOf("v.") - 1);

                        awayTeam = eachGame.substring(eachGame.lastIndexOf("v.") + 3, eachGame.length());
                        
                        homeScore = Integer.parseInt(homeTeam.substring(homeTeam.lastIndexOf(" ") + 1, homeTeam.length()));

                        awayScore = Integer.parseInt(awayTeam.substring(0, awayTeam.indexOf(" ")));

                        homeTeam = homeTeam.substring(0, homeTeam.lastIndexOf(" "));
                        awayTeam = awayTeam.substring(awayTeam.indexOf(" ") + 1, awayTeam.length());

                        Match newMatch = new Match(homeTeam, awayTeam, homeScore, awayScore);
                        newMatch.setHomeResult();
                        newMatch.setAwayResult();
                        loadMatches.add(newMatch);

                    }
                    eachGame = games;
                    homeTeam = eachGame.substring(0, eachGame.lastIndexOf("v.") - 1);

                    awayTeam = eachGame.substring(eachGame.lastIndexOf("v.") + 3, eachGame.length());

                    homeScore = Integer.parseInt(homeTeam.substring(homeTeam.lastIndexOf(" ") + 1, homeTeam.length()));

                    awayScore = Integer.parseInt(awayTeam.substring(0, awayTeam.indexOf(" ")));

                    homeTeam = homeTeam.substring(0, homeTeam.lastIndexOf(" "));
                    awayTeam = awayTeam.substring(awayTeam.indexOf(" ") + 1, awayTeam.length());

                    Match newMatch = new Match(homeTeam, awayTeam, homeScore, awayScore);
                    newMatch.setHomeResult();
                    newMatch.setAwayResult();
                    loadMatches.add(newMatch);
                    t.setMatches(loadMatches);
                    setTeamValues(t);
                } else {
                    eachGame = games;
                    if (eachGame.length() > 0) {
                        homeTeam = eachGame.substring(0, eachGame.lastIndexOf("v.") - 1);

                        awayTeam = eachGame.substring(eachGame.lastIndexOf("v.") + 3, eachGame.length());

                        homeScore = Integer.parseInt(homeTeam.substring(homeTeam.lastIndexOf(" ") + 1, homeTeam.length()));

                        awayScore = Integer.parseInt(awayTeam.substring(0, awayTeam.indexOf(" ")));

                        homeTeam = homeTeam.substring(0, homeTeam.lastIndexOf(" "));
                        awayTeam = awayTeam.substring(awayTeam.indexOf(" ") + 1, awayTeam.length());

                        Match newMatch = new Match(homeTeam, awayTeam, homeScore, awayScore);
                        newMatch.setHomeResult();
                        newMatch.setAwayResult();
                        loadMatches.add(newMatch);
                        t.setMatches(loadMatches);
                        setTeamValues(t);
                    }
                }
            }
        }
    }
    
    public void saveMatches(ArrayList<League> leagues) {
        try {
            for (League l : leagues) {
                if (l.getName().equals(leagues.get(selectedLeague).getName())) {
                    PrintWriter matches = new PrintWriter(l.getName() + ".txt");
                    for (Team t : l.getTeams()) {
                        matches.println(t.getName() + ": " + t.getMatches());
                    }
                    matches.close();
                }
            }
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
