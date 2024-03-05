package org.example;

public class Matches {

    private int id;
    private int season;
    private String city;
    private String date;
    private String team1;
    private String team2;
    private String tossWinner;
    private  String tossDecision;
    private String result;
    private int dlApplied;
    private String winner;
    private int WinByRuns;

    private int winByWickets;

    private String playerOfTheMatch;
    private String venue;

    public Matches() {
    }



    @Override
    public String toString() {
        return "Matches{" +
                "id=" + id +
                ", season=" + season +
                ", city='" + city + '\'' +
                ", date='" + date + '\'' +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", tossWinner='" + tossWinner + '\'' +
                ", result='" + result + '\'' +
                ", dlApplied=" + dlApplied +
                ", winner='" + winner + '\'' +
                ", WinByRuns=" + WinByRuns +
                ", winByWickets=" + winByWickets +
                ", playerOfTheMatch='" + playerOfTheMatch + '\'' +
                ", venue='" + venue + '\'' +
                '}';
    }

    public String getTossDecision() {
        return tossDecision;
    }

    public void setTossDecision(String tossDecision) {
        this.tossDecision = tossDecision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTossWinner() {
        return tossWinner;
    }

    public void setTossWinner(String tossWinner) {
        this.tossWinner = tossWinner;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getDlApplied() {
        return dlApplied;
    }

    public void setDlApplied(int dlApplied) {
        this.dlApplied = dlApplied;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getWinByRuns() {
        return WinByRuns;
    }

    public void setWinByRuns(int winByRuns) {
        WinByRuns = winByRuns;
    }

    public int getWinByWickets() {
        return winByWickets;
    }

    public void setWinByWickets(int winByWickets) {
        this.winByWickets = winByWickets;
    }

    public String getPlayerOfTheMatch() {
        return playerOfTheMatch;
    }

    public void setPlayerOfTheMatch(String playerOfTheMatch) {
        this.playerOfTheMatch = playerOfTheMatch;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
