package au.com.inteqweb.directory.service;

import au.com.inteqweb.directory.dto.CustomerDto;
import au.com.inteqweb.directory.dto.PhoneNumberDto;
import au.com.inteqweb.directory.entity.Customer;
import au.com.inteqweb.directory.entity.PhoneNumber;
import au.com.inteqweb.directory.helper.CustomerHelper;
import au.com.inteqweb.directory.helper.PhoneNumberHelper;
import au.com.inteqweb.directory.repository.CustomerRepository;
import au.com.inteqweb.directory.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PhoneNumberRepository phoneNumberRepository;

    public List<CustomerDto> getCustomers() {
        return CustomerHelper.buildDtoList(customerRepository.findAll());
    }

    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer newCustomer = customerRepository.save(CustomerHelper.buildEntity(customerDto));
        return CustomerHelper.buildDto(newCustomer);
    }

    public List<PhoneNumberDto> getCustomerPhoneNumbers(@PathVariable("id") Long customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        return PhoneNumberHelper.buildDtoList(customer.getPhoneNumbers());
    }

    public CustomerDto activatePhoneNumber(@PathVariable("id") Long customerId, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerId).get();

        List<PhoneNumber> phoneNumbers = fetchPhoneNumberList(
                PhoneNumberHelper.buildPhoneNumberIdList(customerDto.getPhoneNumbers()));
        customer.getPhoneNumbers().addAll(phoneNumbers);

        return CustomerHelper.buildDto(customerRepository.save(customer));
    }

    private List<PhoneNumber> fetchPhoneNumberList(List<Long> ids) {
        Spliterator<PhoneNumber> phoneNumberSpliterator = phoneNumberRepository.findAllById(ids).spliterator();
        return StreamSupport.stream(phoneNumberSpliterator, false)
                .collect(Collectors.toList());
    }
}
