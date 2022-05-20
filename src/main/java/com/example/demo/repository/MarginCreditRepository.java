package com.example.demo.repository;

import com.example.demo.entity.MarginCredit;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MarginCreditRepository extends JpaRepository<MarginCredit, Long>, JpaSpecificationExecutor<MarginCredit> {

    @Query("select coalesce(sum((mc.amount+mc.interest)*coalesce(p.price,1.0)),0.0) "
        + "from MarginCredit mc "
        + "left join Product p "
        + "on p.quote_currency = 'USDT' and p.base_currency = mc.currency")
    BigDecimal calculateDebts();

    @Query("select coalesce(sum(mc.interest*coalesce(p.price,1.0)),0.0) "
        + "from MarginCredit mc "
        + "left join Product p "
        + "on p.quote_currency = 'USDT' and p.base_currency = mc.currency")
    BigDecimal calculateInterests();
}
