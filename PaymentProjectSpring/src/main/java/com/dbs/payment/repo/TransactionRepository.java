package com.dbs.payment.repo;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dbs.payment.model.Transaction;
@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{

 public List<Transaction> findAllByCustomerCustomerid(String id); 
}