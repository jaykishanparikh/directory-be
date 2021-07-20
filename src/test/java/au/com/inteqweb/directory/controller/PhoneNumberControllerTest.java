package au.com.inteqweb.directory.controller;

import au.com.inteqweb.directory.dto.PhoneNumberDto;
import au.com.inteqweb.directory.service.PhoneNumberService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PhoneNumberController.class)
@ActiveProfiles("test")
class PhoneNumberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PhoneNumberService phoneNumberService;

    private List<PhoneNumberDto> PhoneNumberDtoList;

    @BeforeEach
    void setUp() {
        PhoneNumberDtoList = new ArrayList<>();
        PhoneNumberDtoList.add(new PhoneNumberDto(1L, "0465234896", null));
        PhoneNumberDtoList.add(new PhoneNumberDto(2L, "0458297490", null));
        PhoneNumberDtoList.add(new PhoneNumberDto(3L, "0478564967", null));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addPhoneNumber() throws Exception {
        given(phoneNumberService.addPhoneNumber(any(PhoneNumberDto.class))).willAnswer((invocation) -> invocation.getArgument(0));

        PhoneNumberDto inputDto = new PhoneNumberDto();
        inputDto.setPhone("0456879345");

        PhoneNumberDto createdDto = new PhoneNumberDto();
        createdDto.setPhone("0456879345");
        createdDto.setId(10L);


        this.mockMvc.perform(post("/api/app/directory/phoneNumber")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.phone", is(createdDto.getPhone())));
    }

    @Test
    void failAddPhoneNumber() {

    }

    @Test
    void shouldFetchAllPhoneNumbers() throws Exception {
        ResponseEntity<List<PhoneNumberDto>> response = new ResponseEntity(PhoneNumberDtoList, HttpStatus.OK);
        given(phoneNumberService.getPhoneNumbers()).willReturn(PhoneNumberDtoList);

        this.mockMvc.perform(get("/api/app/directory/phoneNumbers"))
                .andExpect(status().isOk());
    }

    @Test
    void failFetchPhoneNumbers() {

    }
}