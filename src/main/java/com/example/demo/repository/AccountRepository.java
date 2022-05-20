package com.example.demo.repository;

import com.example.demo.entity.Account;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

  @Query("select coalesce(sum(a.balance*coalesce (p.price,1.0)),0.0) "
      + "from Account a "
      + "left join Product p "
      + "on p.quote_currency = 'USDT' and  p.base_currency = a.currency ")
  BigDecimal calculateAssets();
}
