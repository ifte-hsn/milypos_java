package com.helloshishir.milypos.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    String dashboard() {
        return "dashboard.html";
    }
}
