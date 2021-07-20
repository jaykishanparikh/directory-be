package au.com.inteqweb.directory.helper;

import au.com.inteqweb.directory.dto.CustomerDto;
import au.com.inteqweb.directory.dto.PhoneNumberDto;
import au.com.inteqweb.directory.entity.Customer;
import au.com.inteqweb.directory.entity.PhoneNumber;

import java.util.ArrayList;
import java.util.List;

public class CustomerHelper {

    public static Customer buildEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmailId(dto.getEmailId());

        return customer;
    }

    public static CustomerDto buildDto(Customer entity) {
        return new CustomerDto(entity.getId(), entity.getName(), entity.getEmailId(), buildPhoneNumberList(entity.getPhoneNumbers()));
    }

    public static List<CustomerDto> buildDtoList(List<Customer> entities) {
        List<CustomerDto> dtoList = new ArrayList();
        for (Customer customer : entities) {
            dtoList.add(new CustomerDto(customer.getId(), customer.getName(), customer.getEmailId(),
                    buildPhoneNumberList(customer.getPhoneNumbers())));
        }
        return dtoList;
    }

    private static List<PhoneNumberDto> buildPhoneNumberList(List<PhoneNumber> phoneNumberList) {
        List<PhoneNumberDto> PhoneNumberDtoList = new ArrayList<PhoneNumberDto>();
        if (phoneNumberList != null) {
            for (PhoneNumber entity : phoneNumberList) {
                PhoneNumberDtoList.add(new PhoneNumberDto(entity.getId(), entity.getPhone(), entity.getCustomer()));
            }
        }
        return PhoneNumberDtoList;
    }

}
