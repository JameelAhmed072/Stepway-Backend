package com.example.Stepway.Controller;


import com.example.Stepway.Domain.Payment;
import com.example.Stepway.Service.impl.PaymentServiceImpl;
import com.example.Stepway.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class PaymentController {

    @Autowired
    PaymentServiceImpl paymentServiceImpl;

    @PostMapping("/payment")
    public ResponseEntity<PaymentDto> addPayment(@Valid @RequestBody PaymentDto paymentDto){

        PaymentDto createPayment = paymentServiceImpl.createPayment(paymentDto);

        return ResponseEntity.ok(createPayment);
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@Valid @PathVariable Long id){

        PaymentDto payment = paymentServiceImpl.getPaymentById(id);

        return ResponseEntity.ok(payment);
    }

    @GetMapping("/allPayments")
    public ResponseEntity<List<PaymentDto>> getAllPayments(){
        List<PaymentDto> allPayments = paymentServiceImpl.getALlPayments();
        return ResponseEntity.ok(allPayments);
    }

    @PutMapping("/updatePayment/{id}")
    public ResponseEntity<PaymentDto> updatePaymentById(@Valid @RequestBody PaymentDto paymentDto,@PathVariable Long id){

        PaymentDto payment = paymentServiceImpl.updatePaymentById(id,paymentDto);

        return ResponseEntity.ok(payment);
    }

    @DeleteMapping("/deletePayment/{id}")
    public ResponseEntity<PaymentDto> deletePaymentById(@Valid @PathVariable Long id){
        paymentServiceImpl.deletePaymentById(id);

        return ResponseEntity.noContent().build();
    }

}
