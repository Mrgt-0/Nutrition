package org.example.Controller;

import org.example.Config.MyUserDetails;
import org.example.DTO.DailyReportDTO;
import org.example.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @PostMapping("/daily")
    public ResponseEntity<DailyReportDTO> getDailyReport(@RequestParam String date, Authentication authentication) {
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        System.out.println("Email: " + userDetails.getUsername());
        System.out.println("Date: " + date);

        LocalDate localDate = LocalDate.parse(date);
        DailyReportDTO report = reportService.generateDailyReport(userDetails.getUsername(), localDate);
        return ResponseEntity.ok(report);
    }
}
