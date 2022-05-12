package uz.pdp.task_2_6_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_2_6_2.entity.*;
import uz.pdp.task_2_6_2.payload.BankomatDto;
import uz.pdp.task_2_6_2.payload.GetMoneyDto;
import uz.pdp.task_2_6_2.repository.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bankomat")
public class BankomatController {

    @Autowired
    BankomatRepository bankomatRepository;


    @Autowired
    BankRepository bankRepository;

    @Autowired
    KupyuraRepository kupyuraRepository;

    @Autowired
    CardRepository cardRepository;

//  ------bankomat qo'shish uchun--------
    @PostMapping
    public HttpEntity<?> addBankomat(@RequestBody BankomatDto bankomatDto){
        Optional<Bank> optionalBank = bankRepository.findById(bankomatDto.getBankId());
        if (!optionalBank.isPresent()){
            return ResponseEntity.status(400).body("bank not found");
        }
        Bank bank = optionalBank.get();
        Bankomat bankomat = new Bankomat();
        bankomat.setBank(bank);
        bankomat.setKartaTuri(bankomatDto.getKartaTuri());
        bankomat.setAddress(bankomatDto.getAddress());
        bankomatRepository.save(bankomat);
        return ResponseEntity.status(201).body("bankomat added");
    }

//  -------bankomatlarni ko'rish uchun--------
    @GetMapping
    public HttpEntity<?> getBankomat(){
        List<Bankomat> all = bankomatRepository.findAll();
        return ResponseEntity.ok(all);
    }


    //------bankomatni tahrirlash va hisobini to'ldirish uchun---------
    @PutMapping("/{id}")
    public HttpEntity<?> editBankomat(@PathVariable Integer id,@RequestBody BankomatDto bankomatDto){
        Optional<Bankomat> optionalBankomat = bankomatRepository.findById(id);
        if (!optionalBankomat.isPresent()){
            return ResponseEntity.status(400).body("bankomat not found");
        }
        List<Kupyura> kupyuraList = kupyuraRepository.findAllById(bankomatDto.getKupyuraListId());
        Bankomat bankomat = optionalBankomat.get();
        Kupyura ming = kupyuraList.get(0);
        Kupyura beshming = kupyuraList.get(1);
        Kupyura onming = kupyuraList.get(2);
        Kupyura ellikming = kupyuraList.get(3);
        Kupyura yuzming = kupyuraList.get(4);
        long mingsum = ming.getName() * ming.getSoni();
        long bashmingsum = beshming.getName() * beshming.getSoni();
        long onmingsum = onming.getName() * onming.getSoni();
        long ellikmingsum = ellikming.getName() * ellikming.getSoni();
        long yuzmingsum = yuzming.getName() * yuzming.getSoni();
        long jami = mingsum+bashmingsum+onmingsum+ellikmingsum+yuzmingsum;
        bankomat.setKupyuraList(kupyuraList);
        bankomat.setPulMiqdori(jami);
        bankomatRepository.save(bankomat);
        return  ResponseEntity.ok("bankomat miqdori to'ldirildi");
    }


//    ---------bankomatdan pul olish uchun--------
    @PostMapping("/getMoney/{bankomatId}")
    public HttpEntity<?> getMoney(@PathVariable Integer bankomatId, @RequestBody GetMoneyDto getMoneyDto){
        Optional<Bankomat> optionalBankomat = bankomatRepository.findById(bankomatId);
        if (!optionalBankomat.isPresent()){
            return ResponseEntity.status(400).body("bankomat not found");
        }
        Optional<Card> optionalCard = cardRepository.findById(getMoneyDto.getCardId());
        if (!optionalCard.isPresent()){
            return ResponseEntity.status(400).body("card not found");
        }

        Bankomat bankomat = optionalBankomat.get();
        long pulMiqdori = bankomat.getPulMiqdori();

        Card card = optionalCard.get();
        long cardSummma = card.getSumma();

        long summa = getMoneyDto.getSumma();

        if (cardSummma == 0 && cardSummma < summa){
            return ResponseEntity.status(400).body("there is not enough money available in card");
        }
        if (pulMiqdori < summa && summa > 3000000){
            return ResponseEntity.status(400).body("there is not enough money in bankomat");
        }

        long leftMoney = cardSummma - summa;
        card.setSumma(leftMoney);
        Card savedCard = cardRepository.save(card);
        bankomat.setOnlineMoney(bankomat.getOnlineMoney()+summa);
        long leftMoneyBankomat = bankomat.getPulMiqdori() - summa;
        bankomat.setPulMiqdori(leftMoneyBankomat);
        bankomatRepository.save(bankomat);
        GetMoney getMoney = new GetMoney();
        getMoney.setBankomat(bankomat);
        getMoney.setCard(savedCard);
        getMoney.setSummma(summa);
        return ResponseEntity.status(200).body("bankomatdan pul yechildi");
    }
}