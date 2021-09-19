package com.coronavirustracker.controller;

import com.coronavirustracker.model.Stats;
import com.coronavirustracker.service.CovidTrackerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    final CovidTrackerService covidTrackerService;

    public HomeController(CovidTrackerService covidTrackerService) {
        this.covidTrackerService = covidTrackerService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Stats> allStats = covidTrackerService.getStats();
        int totalReportedCases = allStats.stream().mapToInt(Stats::getLatestTotalCases).sum();
        int totalNewCases = allStats.stream().mapToInt(Stats::getDiffFromPreviousDay).sum();
        model.addAttribute("stats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}
