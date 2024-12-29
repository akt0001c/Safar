package com.ms.myapp.controller;

import com.ms.myapp.entities.Card;
import com.ms.myapp.entities.Transaction;
import com.ms.myapp.exceptions.RequestInvalidException;
import com.ms.myapp.models.CardRequest;
import com.ms.myapp.models.request.Request;
import com.ms.myapp.response.MasterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * {
 *     "metaData":"",
 *     "requestData":{
 *        "requestType":"Add",
 *        "payload":{
 *         "key":"abb",
 *         "content":"",
 *          "instruction":"some instruction in few condition"
 *
 *        }
 *
 *
 *     }
 *   }
 */

@RestController
@RequestMapping("/api")
public class CardController {




/**
*
* @param cardRequest
* @return
 *
 *
*/
@PostMapping("/add/card")
public ResponseEntity<MasterResponse<Object>> addCard(@RequestBody CardRequest<Request> cardRequest){
if(cardRequest==null || cardRequest.getRequest().getRequestData() ==null || (cardRequest.getRequest().getRequestData().getRequestType()!=null && cardRequest.getRequest().getRequestData().getPayload()==null))
      throw new RequestInvalidException("Invalid request or Request does not have valid values.");


    return null;
}

@DeleteMapping("/remove/card")
public ResponseEntity<MasterResponse<Object>> removeCard(@RequestBody CardRequest<Request> cardRequest){
    return null;
}

@PatchMapping("/change/status")
public ResponseEntity<MasterResponse<Object>> changeCardStatus(@RequestBody CardRequest<Object> cardRequest){
    return null;
}

@GetMapping("/cards")
public ResponseEntity<MasterResponse<List<Card>>> getAllCardList(){
    return null;
}

@GetMapping("/transactions")
public ResponseEntity<MasterResponse<List<Transaction>>> getAllTransaction(@RequestBody CardRequest<Object> cardRequest){
    return null;
}

}
