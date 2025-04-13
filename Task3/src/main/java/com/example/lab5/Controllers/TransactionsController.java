package com.example.lab5.Controllers;

import com.example.lab5.DTO.createTransactionDTO;
import com.example.lab5.DTO.updateTransactionDTO;
import com.example.lab5.Entities.Transactions;
import com.example.lab5.Services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transactions>> getAllTransactions() {
        List<Transactions> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transactions> getTransactionById(@PathVariable long id) {
        Optional<Transactions> transaction = transactionService.getTransactionById(id);
        if (transaction.isPresent()) {
            return ResponseEntity.ok(transaction.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Transactions> createTransaction(@Valid @RequestBody createTransactionDTO createTransactionDTO) {
        Transactions transaction = transactionService.createTransaction(createTransactionDTO);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transactions> updateTransaction(@PathVariable long id, @Valid @RequestBody updateTransactionDTO updateTransactionDTO){
        try{
            Transactions updatedTransaction = transactionService.updateTransaction(id, updateTransactionDTO);
            return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transactions> deleteTransaction(@PathVariable long id){
        try {
            transactionService.getTransactionById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
