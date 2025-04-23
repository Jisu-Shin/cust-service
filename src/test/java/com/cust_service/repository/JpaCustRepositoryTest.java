package com.cust_service.repository;

import com.cust_service.domain.Cust;
import com.cust_service.domain.CustSmsConsentType;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

    @Test
    public void 고객저장() {
        //given
        String name = "고길동";
        String phoneNumber = "01012345678";
        CustSmsConsentType type = CustSmsConsentType.ALL_ALLOW;

        custRepository.save(Cust.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .smsConsentType(type)
                .build());

        //when
        List<Cust> custList = custRepository.findAll();

        //then
        Cust cust = custList.get(0);
        assertEquals(cust.getPhoneNumber(),phoneNumber);
        assertEquals(cust.getSmsConsentType(),type);

    }

    @Test
    @Rollback(false)
    public void BaseTimeEntity등록() {
        //given
        LocalDateTime now = LocalDateTime.now();
        custRepository.save(Cust.builder()
                .name("홍길동")
                .phoneNumber("01023456789")
                .smsConsentType(CustSmsConsentType.ALL_ALLOW)
                .build());

        //when
        Cust cust = custRepository.findAll().get(0);

        //then
        System.out.println(">>>>>>>>> createDate = "+cust.getCreatedDate()+ ", modifiedDate = " + cust.getModifiedDate());

        assertThat(cust.getCreatedDate()).isAfter(now);
        assertThat(cust.getModifiedDate()).isAfter(now);
    }

}