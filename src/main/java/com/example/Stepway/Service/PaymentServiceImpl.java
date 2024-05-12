package com.example.Stepway.Service;


import com.example.Stepway.dto.EnrollmentDto;
import com.example.Stepway.dto.PaymentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentServiceImpl {

    List<PaymentDto> getALlPayments();

    public PaymentDto getPaymentById(Long id);

    public PaymentDto createPayment(PaymentDto paymentDto);
    PaymentDto updatePaymentById(Long id,PaymentDto paymentDto);
    public void deletePaymentById(Long id);
}
