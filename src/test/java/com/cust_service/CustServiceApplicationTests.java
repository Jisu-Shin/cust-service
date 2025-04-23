package com.cust_service;

import com.cust_service.domain.Cust;
import com.cust_service.domain.CustSmsConsentType;
import com.cust_service.dto.CustListResponseDto;
import com.cust_service.dto.CustSaveRequestDto;
import com.cust_service.repository.JpaCustRepository;
import com.cust_service.service.CustService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class CustServiceApplicationTests {

	@Autowired
	CustService custService;

	@Autowired
	JpaCustRepository jpaCustRepository;

	@Autowired
	EntityManager em;

	@Test
	public void 회원가입() throws Exception {
		//given
		CustSaveRequestDto custSaveRequestDto = new CustSaveRequestDto("kim","01012345678", CustSmsConsentType.ALL_ALLOW);

		//when
		Long saveId = custService.save(custSaveRequestDto);

		//then
		Optional<Cust> cust = jpaCustRepository.findById(saveId);
		assertEquals(custSaveRequestDto.getName(), cust.get().getName());
	}

	@Test
	public void 중복_회원_예외() throws Exception {
		//given
		CustSaveRequestDto cust = new CustSaveRequestDto("kim1","1234",CustSmsConsentType.ALL_ALLOW);
		CustSaveRequestDto cust2 = new CustSaveRequestDto("kim1","4567",CustSmsConsentType.ALL_ALLOW);

		//when
		custService.save(cust);
		assertThrows(IllegalStateException.class, () -> custService.save(cust2));
	}

	@Test
	public void idList로조회() throws Exception {
	    //given
		Cust c1 = Cust.createCust("kim", "1234", CustSmsConsentType.ALL_ALLOW);
		em.persist(c1);

		Cust c2 = Cust.createCust("lee", "1234", CustSmsConsentType.ALL_ALLOW);
		em.persist(c2);

		List<Long> idList = List.of(c1.getId());

	    //when
		List<CustListResponseDto> byIdList = custService.findByIdList(idList);

		//then
		assertEquals(byIdList.size(),1);
		assertEquals(byIdList.get(0).getName(),"kim");
	}

}
