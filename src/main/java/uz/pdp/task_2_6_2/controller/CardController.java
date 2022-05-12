package uz.pdp.task_2_6_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_2_6_2.entity.Bank;
import uz.pdp.task_2_6_2.entity.Card;
import uz.pdp.task_2_6_2.payload.CardDto;
import uz.pdp.task_2_6_2.repository.BankRepository;
import uz.pdp.task_2_6_2.repository.CardRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/card")
public class CardController {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    BankRepository bankRepository;

    @PostMapping
    public HttpEntity<?> addCard(@RequestBody CardDto cardDto){
        Optional<Bank> optionalBank = bankRepository.findById(cardDto.getBankId());
        if (!optionalBank.isPresent()){
            return ResponseEntity.status(400).body("bank not found");
        }
        Bank bank = optionalBank.get();
        Card card = new Card();
        card.setBank(bank);
        card.setMaxsusRaqam(cardDto.getMaxsusRaqam());
        card.setCvv(cardDto.getCvv());
        card.setFullName(cardDto.getFullName());
        card.setAmalQilishMuddati(cardDto.getAmalQilishMuddati());
        card.setPassword(cardDto.getPassword());
        card.setKartaTuri(cardDto.getKartaTuri());
        cardRepository.save(card);
        return ResponseEntity.ok("card added");
    }

    @GetMapping
    public HttpEntity<?> getCard(){
        List<Card> all = cardRepository.findAll();
        return ResponseEntity.ok(all);
    }
}
