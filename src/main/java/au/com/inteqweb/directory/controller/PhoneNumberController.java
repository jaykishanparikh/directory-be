package au.com.inteqweb.directory.controller;

import au.com.inteqweb.directory.dto.PhoneNumberDto;
import au.com.inteqweb.directory.service.PhoneNumberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.basepath.uri}")
@Tag(name = "PhoneNumber", description = "PhoneNumber API")
public class PhoneNumberController {
    @Autowired
    PhoneNumberService phoneNumberService;

    @Operation(summary = "AddPhoneNumber", description = "Adds a new phone number", tags = {"phonenumber"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class))))})
    @PostMapping(value = "/phoneNumber")
    public ResponseEntity<PhoneNumberDto> addPhoneNumber(@RequestBody PhoneNumberDto phoneNumberDto) {
        PhoneNumberDto newPhoneNumberDto = phoneNumberService.addPhoneNumber(phoneNumberDto);
        return new ResponseEntity<PhoneNumberDto>(newPhoneNumberDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all phone numbers", description = "Returns list of all phone numbers from the directory", tags = {"phonenumber"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class))))})
    @GetMapping(path = "/phoneNumbers")
    public ResponseEntity<List<PhoneNumberDto>> getPhoneNumbers() {
        List<PhoneNumberDto> phoneNumberDtoList = phoneNumberService.getPhoneNumbers();
        return new ResponseEntity<List<PhoneNumberDto>>(phoneNumberDtoList, HttpStatus.OK);
    }
}
