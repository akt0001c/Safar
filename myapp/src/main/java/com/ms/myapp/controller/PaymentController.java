package com.ms.myapp.controller;

import com.ms.myapp.request.CardPaymentRequest;
import com.ms.myapp.request.CardRequestBody;
import com.ms.myapp.response.MasterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @PostMapping("/validateCard")
    public ResponseEntity<MasterResponse<Object>> validateCard(@RequestBody CardRequestBody cardRequestBody){
         return null;
    }


    @PostMapping("/makePayment")
    public ResponseEntity<MasterResponse<Object>>  makePayment(@RequestBody CardPaymentRequest cardPaymentRequest){
        return null;
    }
}
