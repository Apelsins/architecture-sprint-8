package com.yandex.API;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class ReportsController {

    @GetMapping("/reports")
    public Map<String, Object> getReports() {
        Map<String, Object> result = new HashMap<>();
        result.put("reportTime", LocalDateTime.now().toString());
        result.put("reportData", Arrays.asList(
                Map.of("id", 1, "value", "Test data 1"),
                Map.of("id", 2, "value", "Test data 2")
        ));

        return result;
    }
}