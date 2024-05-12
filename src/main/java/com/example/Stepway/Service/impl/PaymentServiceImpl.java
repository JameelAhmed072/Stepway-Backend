package com.example.Stepway.Service.impl;

import com.example.Stepway.Domain.Enrollment;
import com.example.Stepway.Domain.Payment;
import com.example.Stepway.Domain.User;
import com.example.Stepway.Exception.ResourceNotFound;
import com.example.Stepway.Repository.EnrollmentRespository;
import com.example.Stepway.Repository.PaymentRepository;
import com.example.Stepway.dto.EnrollmentDto;
import com.example.Stepway.dto.PaymentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements com.example.Stepway.Service.PaymentServiceImpl {

    @Autowired
    EnrollmentRespository enrollmentRespository;


    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<PaymentDto> getALlPayments() {
        List<Payment> payments = paymentRepository.findAll();

        return payments.stream().map(payment -> modelMapper.map(payment, PaymentDto.class)).collect(Collectors.toList());
    }

    @Override
    public PaymentDto getPaymentById(Long id) {

        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        Payment payment = optionalPayment.orElseThrow(()->new ResourceNotFound("Payment with this id not Found :" + id));
        return modelMapper.map(payment,PaymentDto.class);
    }

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {

        Payment payment = modelMapper.map(paymentDto,Payment.class);
        Payment savedPayment = paymentRepository.save(payment);
        return modelMapper.map(savedPayment,PaymentDto.class);
    }

    @Override
    public PaymentDto updatePaymentById(Long id, PaymentDto paymentDto) {

        Payment payment = paymentRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Payment with this id not found"));

        Enrollment enrollment = enrollmentRespository.findById(paymentDto.getEnrollmentId())
                .orElseThrow(()->new ResourceNotFound("Enrollment Not found with this id: +"+  paymentDto.getEnrollmentId()));


        payment.setAmount(paymentDto.getAmount());
        payment.setTranscationDate(paymentDto.getTranscationDate());
        payment.setEnrollmentId(enrollment);


        Payment updatedPayment = paymentRepository.save(payment);
        return modelMapper.map(updatedPayment,PaymentDto.class);
    }

    @Override
    public void deletePaymentById(Long id) {

        if(!paymentRepository.existsById(id)){
            throw new ResourceNotFound("Payment with this id is not found");
        }
        paymentRepository.deleteById(id);

    }
}
