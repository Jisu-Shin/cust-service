package com.cust_service.repository;

import com.cust_service.domain.Cust;
import com.cust_service.domain.CustSmsConsentType;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaCustRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    JpaCustRepository custRepository;

    @Test
    public void 고객이름조회() throws Exception {
        //given
        Cust cust1 = Cust.createCust("윤두준","01012345678", CustSmsConsentType.ALL_ALLOW);
        Cust cust2 = Cust.createCust("이준","01012345678", CustSmsConsentType.ALL_ALLOW);
        em.persist(cust1);
        em.persist(cust2);

        //when
        List<Cust> list = custRepository.findByNameLike("%준%");

        //then
        assertEquals(list.size(), 2);
    }

}