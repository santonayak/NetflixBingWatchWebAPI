package com.huedge.NetfllixBingeWebAPI.controller;

import com.huedge.NetfllixBingeWebAPI.model.Netflix;
import com.huedge.NetfllixBingeWebAPI.service.CSVParserService;
import com.huedge.NetfllixBingeWebAPI.service.TimingMiddleware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ShowController {

    @GetMapping("/allData")
    public List<Netflix> allData(){
        return CSVParserService.AllShows();
    }
//    @GetMapping("/tvshows")
//    public  List<Netflix> getNShows(@RequestParam("count") int n){
//        return  CSVParserService.getNShows(n);
//    }

    @GetMapping("/tvshows")
    public  List<Netflix> getMovieBytype(@RequestParam Map<String, String> dataMap, HttpServletRequest request, HttpServletResponse response){

        TimingMiddleware timingMiddleware = (TimingMiddleware) request.getAttribute("timer");


        List<Netflix> shows = null;
        if(dataMap.containsKey("movieType")){
            String type = dataMap.get("movieType");
            shows = CSVParserService.getMovieByType(type);
            Long value = timingMiddleware.end();
            response.setHeader("X-TIME-TO-EXECUTE",value.toString());
            return shows;
        }
        if(dataMap.containsKey("count")){
            Integer n = Integer.valueOf(dataMap.get("count"));
            Long value = timingMiddleware.end();

            shows = CSVParserService.getNShows(n);

            response.setHeader("X-TIME-TO-EXECUTE",value.toString());
            return  shows;
        }

        if(dataMap.containsKey("country")){
            String country = dataMap.get("country");
            Long value = timingMiddleware.end();

            shows = CSVParserService.getByCountry(country);

            response.setHeader("X-TIME-TO-EXECUTE",value.toString());
            return  shows;
        }
        if(dataMap.containsKey("startDate")){
            String startDate = dataMap.get("startDate");
            String endDate = dataMap.get("endDate");

            Long value = timingMiddleware.end();

            shows = CSVParserService.getByDateRange(startDate, endDate);
            response.setHeader("X-TIME-TO-EXECUTE",value.toString());
            return  shows;
        }
        return  new ArrayList<>();
    }
}
