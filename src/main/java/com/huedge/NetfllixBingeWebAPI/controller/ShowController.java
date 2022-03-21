package com.huedge.NetfllixBingeWebAPI.controller;

import com.huedge.NetfllixBingeWebAPI.model.Netflix;
import com.huedge.NetfllixBingeWebAPI.service.CSVParserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowController {

    @GetMapping("/allData")
    public List<Netflix> allData(){
        return CSVParserService.AllShows();
    }

}
