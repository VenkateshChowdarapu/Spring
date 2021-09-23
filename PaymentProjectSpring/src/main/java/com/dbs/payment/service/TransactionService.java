package com.dbs.payment.service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.dbs.payment.model.Customer;
import com.dbs.payment.model.Message;
import com.dbs.payment.model.Transaction;
import com.dbs.payment.repo.CustomerRepository;
import com.dbs.payment.repo.MessageRepository;
import com.dbs.payment.repo.TransactionRepository;
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repo;
    @Autowired
    private MessageRepository mrepo;
    @Autowired
    private CustomerRepository crepo;
    
    public int postTransaction(Transaction tran) {
        
            if(this.repo.findById(tran.getTransactionid()).isPresent() || tran.getInramount()<0)
                return -1;
            try {
            	
//            	Optional<Message> m=mrepo.findById(tran.getMessage().getMessagecode());
//            	tran.setMessage(m.orElse(null));
            	
            	Optional<Customer> c=crepo.findById(tran.getCustomer().getCustomerid());
            	tran.setCustomer(c.orElse(null));
            	
            	tran.settransferdate(LocalDate.now());
            	System.out.println(tran);
            	double total=tran.getInramount()+tran.getTransferfees();
            	if(tran.getCustomer().getClearbalance()<0)
            	{
            		System.out.print("Inside if"+tran.getCustomer().getClearbalance());
            		return -2;
            	}
            	
            	if(tran.getCustomer().getClearbalance()>total || tran.getCustomer().getOverdraftflag()==1)
            	{
            		
            		System.out.println(total);
            		System.out.println(tran.getCustomer().getClearbalance());
            		tran.getCustomer().setClearbalance(tran.getCustomer().getClearbalance()-total);	
            		this.crepo.save(tran.getCustomer());
            		return this.repo.save(tran).getTransactionid();
            	}
            	
            	else {
            		System.out.println("Cannot send money");
            		return -2;
            	}
            }catch(IllegalArgumentException e )
            {
                return -1;
            }
        
    }
    public List<Transaction> getAllBycustomerId(String id){
        return this.repo.findAllByCustomerCustomerid(id);
    }
}