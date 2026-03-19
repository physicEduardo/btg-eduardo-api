package com.ceiba.btg.configurations;

import com.ceiba.btg.persistence.entities.Fund;
import com.ceiba.btg.persistence.repository.FundRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FundsDataProviderConfiguration {

    @Autowired
    private FundRepository fundRepository;

    @PostConstruct
    void storeFundsData() {
        fundRepository.save(new Fund("FPV_BTG_PACTUAL_RECAUDADORA", 75000L, "FPV"));
        fundRepository.save(new Fund("FPV_BTG_PACTUAL_ECOPETROL", 125000L, "FPV"));
        fundRepository.save(new Fund("DEUDAPRIVADA", 50000L, "FIC"));
        fundRepository.save(new Fund("FDO-ACCIONES", 250000L, "FIC"));
        fundRepository.save(new Fund("FPV_BTG_PACTUAL_DINAMICA", 100000L, "FPV"));
    }

    @PreDestroy
    void cleanData() {
        fundRepository.deleteAll();
    }
}
