package org.example.comic_manga_polica.controller.web;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PocetnaController {
    @GetMapping("/pocetna")
    public String pocetna(Model model){
        model.addAttribute("ime", "Marko");
        return "pocetna";
    }
}
