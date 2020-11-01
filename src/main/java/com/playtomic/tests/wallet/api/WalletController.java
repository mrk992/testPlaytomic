package com.playtomic.tests.wallet.api;

import com.playtomic.tests.wallet.domain.BalanceDTO;
import com.playtomic.tests.wallet.domain.TransactionDTO;
import com.playtomic.tests.wallet.domain.request.ChargeBody;
import com.playtomic.tests.wallet.domain.request.RechargeBody;
import com.playtomic.tests.wallet.model.Transaction;
import com.playtomic.tests.wallet.service.IWalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
@Slf4j
public class WalletController {

    @Autowired
    IWalletService walletService;

    @GetMapping(value = "/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getWallet(@RequestParam(value = "id") int walletId) {

        BalanceDTO balanceDTO = walletService.balanceWallet(walletId);
        if (balanceDTO != null) {
            return new ResponseEntity<>(balanceDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/charge", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity doCharge(
            @RequestBody ChargeBody chargeBody) {

        Transaction transaction = walletService.charge(chargeBody.getWalletId(), chargeBody.getAmount());
        if (transaction != null && transaction.isValid()) {
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setId(transaction.getId());
            transactionDTO.setUserId(transaction.getUserId());
            transactionDTO.setWalletId(transaction.getWalletId());
            transactionDTO.setType(transaction.getType());
            transactionDTO.setAmount(transaction.getAmount());
            transactionDTO.setValid(transaction.isValid());

            return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
        } else if(transaction != null){
            return new ResponseEntity<>("Error doing a charge", HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/recharge", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity doRecharge(
            @RequestBody RechargeBody rechargeBody) {

        Transaction transaction = walletService.recharge(rechargeBody.getWalletId(), rechargeBody.getAmount());
        if (transaction != null && transaction.isValid()) {
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setId(transaction.getId());
            transactionDTO.setUserId(transaction.getUserId());
            transactionDTO.setWalletId(transaction.getWalletId());
            transactionDTO.setType(transaction.getType());
            transactionDTO.setAmount(transaction.getAmount());
            transactionDTO.setValid(transaction.isValid());

            return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
        } else if(transaction != null){
            return new ResponseEntity<>("Error doing a recharge", HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
