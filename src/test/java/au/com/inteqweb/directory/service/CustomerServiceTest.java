package au.com.inteqweb.directory.service;

import au.com.inteqweb.directory.dto.CustomerDto;
import au.com.inteqweb.directory.dto.PhoneNumberDto;
import au.com.inteqweb.directory.entity.Customer;
import au.com.inteqweb.directory.entity.PhoneNumber;
import au.com.inteqweb.directory.helper.CustomerHelper;
import au.com.inteqweb.directory.helper.PhoneNumberHelper;
import au.com.inteqweb.directory.repository.CustomerRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PhoneNumberRepository phoneNumberRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCustomers() {
        List<Customer> customerList = new ArrayList<>();
        Customer testCustomer1 = new Customer();
        testCustomer1.setName("Test Name1");
        testCustomer1.setId(1L);
        testCustomer1.setEmailId("testname1@testdomain.com");
        customerList.add(testCustomer1);

        Customer testCustomer2 = new Customer();
        testCustomer2.setName("Test Name2");
        testCustomer2.setId(2L);
        testCustomer2.setEmailId("testname2@testdomain.com");
        customerList.add(testCustomer2);

        List<CustomerDto> customerDtoList = CustomerHelper.buildDtoList(customerList);

        given(customerRepository.findAll()).willReturn(customerList);

        List<CustomerDto> returnedCustomerDtoList = customerService.getCustomers();
        assertThat(returnedCustomerDtoList).isNotNull();
        assertThat(returnedCustomerDtoList.size()).isEqualTo(2);

        verify(customerRepository).findAll();
    }

    @Test
    void addCustomer() {
        Customer testCustomer1 = new Customer();
        testCustomer1.setName("Test Name1");
        testCustomer1.setEmailId("testname1@testdomain.com");
        CustomerDto customerDto = CustomerHelper.buildDto(testCustomer1);
        given(customerRepository.save(any(Customer.class))).willAnswer((invocation) -> invocation.getArgument(0));

        CustomerDto savedCustomer = customerService.addCustomer(customerDto);
        assertThat(savedCustomer).isNotNull();

        verify(customerRepository).save(testCustomer1);
    }

    @Test
    void getCustomerPhoneNumbers() {
        List<PhoneNumber> phoneNumberList = new ArrayList<>();
        PhoneNumber testNumber1 = new PhoneNumber();
        testNumber1.setPhone("0444333222");
        testNumber1.setId(1L);

        Customer testCustomer1 = new Customer();
        testCustomer1.setName("Test Name1");
        testCustomer1.setId(1L);
        testCustomer1.setEmailId("testname1@testdomain.com");
        testCustomer1.setPhoneNumbers(phoneNumberList);
        testNumber1.setCustomer(testCustomer1);

        phoneNumberList.add(testNumber1);
        given(customerRepository.findById(any(Long.class))).willReturn(java.util.Optional.of(testCustomer1));

        List<PhoneNumberDto> returnedPhoneNumberDtoList = customerService.getCustomerPhoneNumbers(1L);
        assertThat(returnedPhoneNumberDtoList).isNotNull();
        assertThat(returnedPhoneNumberDtoList.size()).isEqualTo(1);

        verify(customerRepository).findById(1L);
    }

    @Test
    void activatePhoneNumber() {
        // preparing request object
        List<PhoneNumberDto> phoneNumberDtoList = new ArrayList<>();
        PhoneNumberDto testNumberDto1 = new PhoneNumberDto();
        testNumberDto1.setId(1L);
        phoneNumberDtoList.add(testNumberDto1);
        CustomerDto activatePhoneNumberInputDto = new CustomerDto();
        activatePhoneNumberInputDto.setPhoneNumbers(phoneNumberDtoList);

        Customer testCustomer = new Customer();
        testCustomer.setId(2L);
        testCustomer.setPhoneNumbers(new ArrayList<>());

        List<PhoneNumber> phoneNumberList = new ArrayList<>();
        phoneNumberList.add(PhoneNumberHelper.buildEntity(testNumberDto1));

        when(customerRepository.findById(2L)).thenReturn(java.util.Optional.of(testCustomer));
        given(phoneNumberRepository.findAllById(any(List.class))).willReturn(phoneNumberList);
        given(customerRepository.save(any(Customer.class))).willAnswer((invocation) -> invocation.getArgument(0));

        CustomerDto activatedCustomerDto = customerService.activatePhoneNumber(2L, activatePhoneNumberInputDto);
        assertThat(activatedCustomerDto).isNotNull();
        assertThat(activatedCustomerDto.getPhoneNumbers()).isNotNull();
        assertThat(activatedCustomerDto.getPhoneNumbers().size()).isEqualTo(1);
    }
}