package au.com.inteqweb.directory.service;

import au.com.inteqweb.directory.dto.PhoneNumberDto;
import au.com.inteqweb.directory.entity.PhoneNumber;
import au.com.inteqweb.directory.helper.PhoneNumberHelper;
import au.com.inteqweb.directory.repository.PhoneNumberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PhoneNumberServiceTest {
    @Mock
    private PhoneNumberRepository phoneNumberRepository;

    @InjectMocks
    private PhoneNumberService phoneNumberService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPhoneNumbers() {
        List<PhoneNumber> phoneNumberList = new ArrayList<>();
        PhoneNumber testNumber1 = new PhoneNumber();
        testNumber1.setPhone("0444333222");
        testNumber1.setId(1L);
        phoneNumberList.add(testNumber1);

        PhoneNumber testNumber2 = new PhoneNumber();
        testNumber2.setPhone("0456789123");
        testNumber2.setId(2L);
        phoneNumberList.add(testNumber2);

        List<PhoneNumberDto> phoneNumberDtoList = PhoneNumberHelper.buildDtoList(phoneNumberList);

        given(phoneNumberRepository.findAll()).willReturn(phoneNumberList);


        List<PhoneNumberDto> returnedPhoneNumberList = phoneNumberService.getPhoneNumbers();
        assertThat(returnedPhoneNumberList).isNotNull();
        assertThat(returnedPhoneNumberList.size()).isEqualTo(2);

        verify(phoneNumberRepository).findAll();
    }

    @Test
    void addPhoneNumber() {
        PhoneNumber testNumber1 = new PhoneNumber();
        testNumber1.setPhone("0444333222");
        testNumber1.setId(1L);
        PhoneNumberDto phoneNumberDto = PhoneNumberHelper.buildDto(testNumber1);

        given(phoneNumberRepository.save(any(PhoneNumber.class))).willAnswer((invocation) -> invocation.getArgument(0));

        PhoneNumberDto savedPhoneNumber = phoneNumberService.addPhoneNumber(phoneNumberDto);
        assertThat(savedPhoneNumber).isNotNull();
        assertThat(savedPhoneNumber.getId()).isEqualTo(1L);

        verify(phoneNumberRepository).save(testNumber1);
    }
}