package au.com.inteqweb.directory.controller;

import au.com.inteqweb.directory.dto.CustomerDto;
import au.com.inteqweb.directory.dto.PhoneNumberDto;
import au.com.inteqweb.directory.helper.CustomerHelper;
import au.com.inteqweb.directory.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
@ActiveProfiles("test")
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    private List<CustomerDto> customerDtoList;

    @BeforeEach
    void setUp() {
        customerDtoList = new ArrayList<>();
        CustomerDto testCustomerDto1 = new CustomerDto(50L, "testemailid1@testdomain.com", "Test Name1", null);
        customerDtoList.add(testCustomerDto1);

        CustomerDto testCustomerDto2 = new CustomerDto(60L, "testemailid1@testdomain.com", "Test Name2", null);
        customerDtoList.add(testCustomerDto2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCustomers() throws Exception {
        ResponseEntity response = new ResponseEntity(customerDtoList, HttpStatus.OK);
        given(customerService.getCustomers()).willReturn(customerDtoList);

        this.mockMvc.perform(get("/api/app/directory/customers"))
                .andExpect(status().isOk());
    }

    @Test
    void addCustomer() throws Exception {
        given(customerService.addCustomer(any(CustomerDto.class))).willAnswer((invocation) -> invocation.getArgument(0));

        CustomerDto inputDto = new CustomerDto();
        inputDto.setName("Test Name55");
        inputDto.setEmailId("testmailid55@gmail.com");

        CustomerDto createdDto = new CustomerDto();
        createdDto.setName("Test Name55");
        createdDto.setId(55L);

        this.mockMvc.perform(post("/api/app/directory/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(createdDto.getName())));
    }

    @Test
    void getCustomerPhoneNumbers() throws Exception {
        List<PhoneNumberDto> phoneNumberDtoList = new ArrayList<>();
        phoneNumberDtoList.add(new PhoneNumberDto(20L, "0456345987", CustomerHelper.buildEntity(customerDtoList.get(0))));

        ResponseEntity response = new ResponseEntity(phoneNumberDtoList, HttpStatus.OK);
        given(customerService.getCustomerPhoneNumbers(any(Long.class))).willReturn(phoneNumberDtoList);

        this.mockMvc.perform(get("/api/app/directory/customer/50/phoneNumbers"))
                .andExpect(status().isOk());
    }

    @Test
    void activatePhoneNumber() throws Exception {
        CustomerDto inputDto = new CustomerDto();
        List<PhoneNumberDto> phoneNumberDtoList = new ArrayList<>();
        PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
        phoneNumberDto.setPhone("0444333222");
        phoneNumberDto.setId(1L);
        inputDto.setPhoneNumbers(phoneNumberDtoList);

        customerDtoList.get(0).setPhoneNumbers(phoneNumberDtoList);
        given(customerService.activatePhoneNumber(any(Long.class), any(CustomerDto.class))).willReturn(customerDtoList.get(0));

        this.mockMvc.perform(patch("/api/app/directory/customer/50")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().is2xxSuccessful());
    }
}