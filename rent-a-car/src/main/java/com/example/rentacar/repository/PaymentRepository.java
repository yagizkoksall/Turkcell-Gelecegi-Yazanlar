package com.example.rentacar.repository;

import com.example.rentacar.business.dto.requests.create.CreatePaymentRequest;
import com.example.rentacar.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    Payment findByCardNumber(String cardNumber);
    boolean existsByCardNumber(String cardNumber);
    boolean existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
            String cardNumber,String cardHolder,int cardExpirationYear,int cardExpirationMonth,String cardCvv);
    //SPeL -> spring expresssion language
//    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
//    "FROM Payment p where p.cardNumber = :#{paymentRequest.cardNumber}"+
//    " AND p.cardHolder = :#{paymentRequest.cardHolder} AND"+
//    " p.cardExpirationYear = :#{paymentRequest.cardExpirationYear} AND" +
//    " p.cardExpirationMonth = :#{paymentRequest.cardExpirationMonth} AND" +
//    " p.cardCvv = :#{paymentRequest.cardCvv}")
//    boolean existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
//            @Param("paymentRequest")CreatePaymentRequest paymentRequest);
}
