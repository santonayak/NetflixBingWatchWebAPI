package com.huedge.NetfllixBingeWebAPI.service;

import com.huedge.NetfllixBingeWebAPI.model.Netflix;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CSVParserService {

    static List<Netflix> showList = new ArrayList<>();

    static {
        String file = "/Users/santonayak/IdeaProjects/BingeWatch/src/netflix.csv";
        String line = "";

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] rows = line.split(",");
            Netflix netflix = new Netflix(rows[0], rows[1], rows[2], rows[3], rows[4], rows[5], rows[6], rows[7], rows[8], rows[9], rows[10], rows[11]);
            showList.add(netflix);
        }
    }

    public static void main(String[] args) throws IOException {


        showList.stream().forEach(a -> System.out.println(a));
    }

    public static List<Netflix> AllShows() {
        return showList;
    }

    public static List<Netflix> getNShows(int n) {
        return showList.stream().filter(e -> e.getType().equalsIgnoreCase("TV Show")).limit(n).collect(Collectors.toList());
    }
    public static List<Netflix> getMovieByType(String movieType) {
        System.out.println(movieType);
        return showList.stream().filter(e -> {
            System.out.println(e.getCategory());
          return  e.getCategory().contains(movieType);
        }).collect(Collectors.toList());
    }
    public static List<Netflix> getByCountry(String country) {
        return showList.stream().filter(e -> e.getCountry().equalsIgnoreCase(country)).collect(Collectors.toList());
    }

    public static List<Netflix> getByDateRange(String startDateStr, String endDateStr) {
        Date startDate = null;
        Date endDate = null; // if endDateStr == null then return new Date

        return showList.stream().filter(e -> {
            Date showDate = null; //e.getDate_added()
            return  showDate.after(startDate) && showDate.before(endDate);
        }).collect(Collectors.toList());
    }
}
