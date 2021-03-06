package com.huedge.NetfllixBingeWebAPI.service;

import com.huedge.NetfllixBingeWebAPI.model.Netflix;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CSVParserService {

    static List<Netflix> showList = new ArrayList<>();

    static {
        String fileName = "/Users/santonayak/Downloads/NetfllixBingeWebAPI/src/main/resources/netflix.csv";
//        String line = "";
//
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new FileReader(file));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        while (true) {
//            try {
//                if (!((line = reader.readLine()) != null)) break;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            String[] rows = line.split(",");
//            Netflix netflix = new Netflix(rows[0], rows[1], rows[2], rows[3], rows[4], rows[5], rows[6], rows[7], rows[8], rows[9], rows[10], rows[11]);
//            showList.add(netflix);
//        }
        Path pathToFile = Paths.get(fileName);
        List<List<String>> results = null;
        try {
            results = Files.readAllLines(pathToFile)
                    .stream()
                    .map(line -> Arrays.asList(line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = 0;
        for (List<String> result : results) {
            if (i != 0) {
                showList.add(new Netflix(result.get(0),result.get(1),result.get(2),result.get(3),result.get(4),result.get(5),result.get(6),result.get(7),result.get(8),result.get(9),result.get(10),result.get(11)));
            }
            i++;
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

//        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
//
//
//        Date date1=formatter1.parse(startDateStr);
//
//        Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDateStr);  ;
        Date startDate =null;
        Date endDate = null; // if endDateStr == null then return new Date

        return showList.stream().filter(e -> {
            Date showDate = null; //e.getDate_added()
            return  showDate.after(startDate) && showDate.before(endDate);
        }).collect(Collectors.toList());
    }
}
