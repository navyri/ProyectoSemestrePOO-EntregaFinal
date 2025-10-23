package org.example.Controller;

import org.example.Models.Duenia;
import org.example.Services.DueniaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/duenia")
public class DueniaController {

    private DueniaService objDuenia;

    @GetMapping
    public List<Duenia> getAll() {
        return objDuenia.getAllDuenia();
    }


}
