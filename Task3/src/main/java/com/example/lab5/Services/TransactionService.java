package com.example.lab5.Services;

import com.example.lab5.DTO.createTransactionDTO;
import com.example.lab5.DTO.updateTransactionDTO;
import com.example.lab5.Entities.Transactions;
import com.example.lab5.Entities.User;
import com.example.lab5.Repositories.TransactionRepository;
import com.example.lab5.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Transactions> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public Optional<Transactions> getTransactionById(Long id){
        return transactionRepository.findById(id);
    }

    public Transactions createTransaction(createTransactionDTO createTransactionDTO){
        User user = userRepository.findById(createTransactionDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found with ID: " + createTransactionDTO.getUserId()));
        Transactions transaction = new Transactions(
                user,
                createTransactionDTO.getAmount(),
                createTransactionDTO.getType(),
                createTransactionDTO.getDate()
        );
        return transactionRepository.save(transaction);
    }

    public Transactions updateTransaction(Long id, updateTransactionDTO updateTransactionDTO){
        return transactionRepository.findById(id).map(transaction -> {
            transaction.setAmount(updateTransactionDTO.getAmount());
            transaction.setType(updateTransactionDTO.getType());
            transaction.setDate(updateTransactionDTO.getDate());
            return transactionRepository.save(transaction);
        }).orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + id));
    }

    public void deleteTransaction(Long id){
        Transactions transaction = transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + id));
        transactionRepository.deleteById(id);
    }
}
