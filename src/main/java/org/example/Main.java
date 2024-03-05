package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String MatchesData="/home/pavani/Downloads/matches.csv";
        String DeliveriesData="/home/pavani/Downloads/deliveries.csv";

        List<Matches> matchesList=matchesReader(MatchesData);
        List<Deliveries> deliveriesList=deliveryReader(DeliveriesData);

        Map<String,Integer> matchesPlayedPerYear=matchesPerYear(matchesList);
        System.out.println("1.Matches played per year:- "+matchesPlayedPerYear);

        Map<String,Map<String,Integer>> matchesWonAllTeamsPerYear=matchesWonByTeamPerYear(matchesList);
        System.out.println("2.Number of matches won of all teams over all the years of IPL:-");

        for(Map.Entry<String, Map<String, Integer>> yearEntry : matchesWonAllTeamsPerYear.entrySet()){
            String year=yearEntry.getKey();
            System.out.println(year+ ":- ");
            Map<String, Integer> tmp=yearEntry.getValue();
            for(Map.Entry<String,Integer> teamEntry : tmp.entrySet()){
                String team = teamEntry.getKey();
                int matchesWon = teamEntry.getValue();
                System.out.println("\t" + team + " : " + matchesWon);
            }
        }

        ArrayList<String> listOdIds2016=new ArrayList<>();
        ArrayList<String> listOfIds2015=new ArrayList<>();

        for (Matches matches : matchesList) {
            String year = String.valueOf(matches.getSeason());
            String id = String.valueOf(matches.getId());
            if (year.equals("2016")) {
                listOdIds2016.add(id);
            } else if (year.equals("2015")) {
                listOfIds2015.add(id);
            }
        }

        Map<String,Integer> extraRun_2016=extraRuns(listOdIds2016,deliveriesList);
        System.out.println("3.Extra runs in 2016 by each team:- ");
        for(Map.Entry<String,Integer> teamEntry : extraRun_2016.entrySet()){
            String team = teamEntry.getKey();
            int extraRuns = teamEntry.getValue();
            System.out.println("\t" + team + " : " + extraRuns);
        }

        HashMap<String,Double> economyRates=topBowlersIn2015(listOfIds2015,deliveriesList);
        ArrayList<String> top10Bowlers=getTop10Bowlers(economyRates);
        System.out.println("4.top 10 Bowlers:- ");
        for(String bowler: top10Bowlers){
            System.out.println("\t" +bowler);
        }
    }
    public static List<Deliveries> deliveryReader(String DeliveriesData){
        List<Deliveries> deliveries=new ArrayList<>();

        try(BufferedReader br=new BufferedReader(new FileReader(DeliveriesData))){
            String Heading;
            boolean headRow=true;

            while((Heading =br.readLine())!=null){
                if(headRow){
                    headRow=false;
                    continue;
                }

                String[] colName=Heading.split(",");

                Deliveries deliveries1=new Deliveries();

                deliveries1.setMatchId(Integer.parseInt(colName[0]));
                deliveries1.setInning(Integer.parseInt(colName[1]));
                deliveries1.setBattingTeam(colName[2]);
                deliveries1.setBowlingTeam(colName[3]);
                deliveries1.setOver(Integer.parseInt(colName[4]));
                deliveries1.setBall(Integer.parseInt(colName[5]));
                deliveries1.setBatsman(colName[6]);
                deliveries1.setNonStriker(colName[7]);
                deliveries1.setBowler(colName[8]);
                deliveries1.setSuperOver(Integer.parseInt(colName[9]));
                deliveries1.setWideRuns(Integer.parseInt(colName[10]));
                deliveries1.setByeRuns(Integer.parseInt(colName[11]));
                deliveries1.setLegByeRuns(Integer.parseInt(colName[12]));
                deliveries1.setNoBallRuns(Integer.parseInt(colName[13]));
                deliveries1.setPenaltyRuns(Integer.parseInt(colName[14]));
                deliveries1.setBatsman(colName[15]);
                deliveries1.setExtraRuns(Integer.parseInt(colName[16]));
                deliveries1.setTotalRuns(Integer.parseInt(colName[17]));

                deliveries.add(deliveries1);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return deliveries;
    }


    public static List<Matches> matchesReader(String MatchesData){
        List<Matches> matchData=new ArrayList<>();
        try(BufferedReader br=new BufferedReader(new FileReader(MatchesData))){
              String Heading;
              boolean headRow=true;

              while((Heading=br.readLine())!=null){
                  if(headRow){
                      headRow=false;
                      continue;
                  }

                  String[] colName=Heading.split(",");

                  Matches matches=new Matches();
                  matches.setId(Integer.parseInt(colName[0]));
                  matches.setSeason(Integer.parseInt(colName[1]));
                  matches.setCity(colName[2]);
                  matches.setDate(colName[3]);
                  matches.setTeam1(colName[4]);
                  matches.setTeam2(colName[5]);
                  matches.setTossWinner(colName[6]);
                  matches.setTossDecision(colName[7]);
                  matches.setResult(colName[8]);
                  matches.setDlApplied(Integer.parseInt(colName[9]));
                  matches.setWinner(colName[10]);

                  matches.setWinByRuns(Integer.parseInt(colName[11]));
                  matches.setPlayerOfTheMatch(colName[12]);
                  matches.setVenue(colName[13]);
                  matchData.add(matches);
              }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return matchData;
    }


    public static Map<String,Integer> matchesPerYear(List<Matches> matchesList){
        Map<String,Integer> res=new TreeMap<>();
        for (Matches matches : matchesList) {
//              System.out.println(matchesList.get(i).getSeason());
            String year = String.valueOf(matches.getSeason());
            res.put(year, res.getOrDefault(year, 0) + 1);
        }
        return res;
    }
    public static Map<String,Map<String,Integer>> matchesWonByTeamPerYear(List<Matches> matchesList){
        Map<String,Map<String,Integer>> res=new TreeMap<>();

        for (Matches matches : matchesList) {
            String year = String.valueOf(matches.getSeason());
            String winner = matches.getWinner();

            Map<String, Integer> tmp = res.getOrDefault(year, new TreeMap<>());
            tmp.put(winner, tmp.getOrDefault(winner, 0) + 1);
            res.put(year, tmp);
        }

        return res;
    }
    public static Map<String,Integer> extraRuns(ArrayList<String> listOfIds,List<Deliveries> deliveriesList){
        Map<String,Integer> res=new HashMap<>();

        for (Deliveries deliveries : deliveriesList) {
            String matchId = String.valueOf(deliveries.getMatchId());
            if (listOfIds.contains(matchId)) {
                int extraRuns = deliveries.getExtraRuns();
                String team = deliveries.getBattingTeam();


                res.put(team, res.getOrDefault(team, 0) + extraRuns);
            }
        }
        return res;
    }
    public static HashMap<String, Double> topBowlersIn2015(ArrayList<String> listOfIds,List<Deliveries> deliveriesList){
        HashMap<String ,int[]> bowlerStats=new HashMap<>();

        for (Deliveries deliveries : deliveriesList) {
            String matchId = String.valueOf(deliveries.getMatchId());
            if (listOfIds.contains(matchId)) {
                String bowler = deliveries.getBowler();
                int runs = deliveries.getTotalRuns();
                if (bowlerStats.containsKey(bowler)) {
                    bowlerStats.get(bowler)[0] += runs;
                    bowlerStats.get(bowler)[1]++;
                } else {
                    bowlerStats.put(bowler, new int[]{runs, 1});
                }
            }
        }

        HashMap<String,Double> res=new HashMap<>();

        for(Map.Entry<String,int []> entry: bowlerStats.entrySet()){
            String bowler=entry.getKey();
            int runs=entry.getValue()[0];
            int balls=entry.getValue()[1];

            double economyRate=(double) (runs/balls)*6;
            res.put(bowler,economyRate);
        }
        return res;
    }
    public static ArrayList<String> getTop10Bowlers(HashMap<String,Double> economyRates){
        List<Map.Entry<String,Double>> list=new ArrayList<>(economyRates.entrySet());

        list.sort(Map.Entry.comparingByValue());

        ArrayList<String> top10Bowlers=new ArrayList<>();
        int ct=0;

        for(Map.Entry<String,Double> entry : list){
            if(ct>=10){
                break;
            }else{
                top10Bowlers.add(entry.getKey());
                ct++;
            }
        }
        return top10Bowlers;
    }

}