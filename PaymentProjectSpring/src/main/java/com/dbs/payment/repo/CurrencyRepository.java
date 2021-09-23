package com.dbs.payment.repo;
import org.springframework.stereotype.Repository;
import com.dbs.payment.model.Currency;
import org.springframework.data.repository.CrudRepository;
@Repository
public interface CurrencyRepository extends CrudRepository<Currency, String> {

}
