package uz.pdp.task_2_6_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_2_6_2.entity.Kupyura;
import uz.pdp.task_2_6_2.repository.KupyuraRepository;

import java.util.List;

@RestController
@RequestMapping("/api/kupyura")
public class KupyuraController {
    @Autowired
    KupyuraRepository kupyuraRepository;

    @PostMapping
    public HttpEntity<?> addK(@RequestBody Kupyura kupyuraDto){
        Kupyura kupyura = new Kupyura();
        kupyura.setName(kupyuraDto.getName());
        kupyura.setSoni(kupyuraDto.getSoni());
        long jami = kupyura.getName()*kupyura.getSoni();
        kupyuraRepository.save(kupyura);
        return ResponseEntity.ok(jami + " sum qabul qilindi");
    }

    @GetMapping
    public HttpEntity<?> getKup(){
        List<Kupyura> all = kupyuraRepository.findAll();
        return ResponseEntity.ok(all);
    }
}
