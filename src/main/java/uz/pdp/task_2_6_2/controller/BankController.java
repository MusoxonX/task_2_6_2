package uz.pdp.task_2_6_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.task_2_6_2.entity.Bank;
import uz.pdp.task_2_6_2.repository.BankRepository;

@RestController
@RequestMapping("/api/bank")
public class BankController {
    @Autowired
    BankRepository bankRepository;

    @PostMapping
    public HttpEntity<?> addBank(@RequestBody Bank bank){
        bankRepository.save(bank);
        return ResponseEntity.ok("bank added");
    }
}
