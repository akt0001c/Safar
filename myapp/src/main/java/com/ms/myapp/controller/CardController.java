package com.ms.myapp.controller;

import com.ms.myapp.entities.Card;
import com.ms.myapp.entities.Transaction;
import com.ms.myapp.response.MasterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CardController {

@PostMapping("/add/card")
public ResponseEntity<MasterResponse<Object>> addCard(){
    return null;
}

@DeleteMapping("/remove/card")
public ResponseEntity<MasterResponse<Object>> removeCard(){
    return null;
}

@PatchMapping("/change/status")
public ResponseEntity<MasterResponse<Object>> changeCardStatus(@RequestParam("cId") Integer cId){
    return null;
}

@GetMapping("/cards")
public ResponseEntity<MasterResponse<List<Card>>> getAllCardList(){
    return null;
}

@GetMapping("/transactions")
public ResponseEntity<MasterResponse<List<Transaction>>> getAllTransaction(@RequestParam("cId") Integer cId){
    return null;
}

}
