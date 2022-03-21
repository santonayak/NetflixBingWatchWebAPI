package com.huedge.NetfllixBingeWebAPI.service;

import com.huedge.NetfllixBingeWebAPI.model.Netflix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParserService {

    static List<Netflix> showList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        String file = "/Users/santonayak/IdeaProjects/BingeWatch/src/netflix.csv";
        String line = "";

        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null){
            String[] rows = line.split(",");
            Netflix netflix = new Netflix(rows[0],rows[1],rows[2],rows[3],rows[4],rows[5],rows[6],rows[7],rows[8],rows[9],rows[10],rows[11]);
            showList.add(netflix);
        }
        showList.stream().forEach(a->System.out.println(a));
    }

    public static List<Netflix> AllShows(){
        return  showList;
    }
}
