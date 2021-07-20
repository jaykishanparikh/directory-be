package au.com.inteqweb.directory.service;

import au.com.inteqweb.directory.dto.PhoneNumberDto;
import au.com.inteqweb.directory.entity.PhoneNumber;
import au.com.inteqweb.directory.helper.PhoneNumberHelper;
import au.com.inteqweb.directory.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneNumberService {
    @Autowired
    PhoneNumberRepository phoneNumberRepository;

    public List<PhoneNumberDto> getPhoneNumbers() {
        return PhoneNumberHelper.buildDtoList(phoneNumberRepository.findAll());
    }

    public PhoneNumberDto addPhoneNumber(PhoneNumberDto phoneNumberDto) {
        PhoneNumber newPhoneNumber = phoneNumberRepository.save(PhoneNumberHelper.buildEntity(phoneNumberDto));
        return PhoneNumberHelper.buildDto(newPhoneNumber);
    }
}
