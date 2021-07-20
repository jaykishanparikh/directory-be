package au.com.inteqweb.directory.controller;

import au.com.inteqweb.directory.dto.CustomerDto;
import au.com.inteqweb.directory.dto.PhoneNumberDto;
import au.com.inteqweb.directory.service.CustomerService;
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
@Tag(name = "Customer", description = "Customer API")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Operation(summary = "GetCustomers", description = "Returns all customers", tags = {"customer"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class))))})
    @GetMapping(path = "/customers")
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        List<CustomerDto> customerDtoList = customerService.getCustomers();
        return new ResponseEntity<List<CustomerDto>>(customerDtoList, HttpStatus.OK);
    }

    @Operation(summary = "AddCustomer", description = "Adds new customer", tags = {"customer"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class))))})
    @PostMapping(path = "/customer")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto createdCustomerDto = customerService.addCustomer(customerDto);
        return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);

    }

    @Operation(summary = "GetCustomerPhoneNumbersList", description = "Returns all phone numbers allocated to the customer", tags = {"customer"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class))))})
    @GetMapping(path = "/customer/{id}/phoneNumbers")
    public ResponseEntity<List<PhoneNumberDto>> getCustomerPhoneNumbers(@PathVariable("id") Long customerId) {
        List<PhoneNumberDto> phoneNumberDtoList = customerService.getCustomerPhoneNumbers(customerId);
        return new ResponseEntity<>(phoneNumberDtoList, HttpStatus.OK);
    }

    @Operation(summary = "ActivatePhoneNumber", description = "Activates phone number to the customer", tags = {"customer"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class))))})
    @PatchMapping(path = "/customer/{id}")
    public ResponseEntity<CustomerDto> activatePhoneNumber(@PathVariable("id") Long customerId, @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomerDto = customerService.activatePhoneNumber(customerId, customerDto);
        return new ResponseEntity<>(updatedCustomerDto, HttpStatus.NO_CONTENT);
    }
}
