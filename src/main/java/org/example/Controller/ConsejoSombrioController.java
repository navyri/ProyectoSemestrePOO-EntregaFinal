package org.example.Controller;

import org.example.Models.ConsejoSombrio;
import org.example.Services.ConsejoSombrioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/consejosombrio")
public class ConsejoSombrioController {

    private ConsejoSombrioService objConsejoSombrio;

    @GetMapping
    public List<ConsejoSombrio> getAll() {
        return objConsejoSombrio.getAllConsejoSombrio();
    }

}
