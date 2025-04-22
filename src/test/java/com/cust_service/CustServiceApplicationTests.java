package com.cust_service;

import com.cust_service.domain.Cust;
import com.cust_service.domain.CustSmsConsentType;
import com.cust_service.dto.CustListResponseDto;
import com.cust_service.dto.CustSaveRequestDto;
import com.cust_service.repository.JpaCustRepository;
import com.cust_service.service.CustService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

}
