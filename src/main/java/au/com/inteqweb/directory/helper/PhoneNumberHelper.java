package au.com.inteqweb.directory.helper;

import au.com.inteqweb.directory.dto.PhoneNumberDto;
import au.com.inteqweb.directory.entity.PhoneNumber;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberHelper {

    public static PhoneNumber buildEntity(PhoneNumberDto dto) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhone(dto.getPhone());
        phoneNumber.setCustomer(dto.getCustomer());
        phoneNumber.setId(dto.getId());
        return phoneNumber;
    }

    public static PhoneNumberDto buildDto(PhoneNumber entity) {
        return new PhoneNumberDto(entity.getId(), entity.getPhone(), entity.getCustomer());
    }

    public static List<PhoneNumberDto> buildDtoList(List<PhoneNumber> entities) {
        List<PhoneNumberDto> dtoList = new ArrayList();
        for (PhoneNumber phoneNumber : entities) {
            dtoList.add(new PhoneNumberDto(phoneNumber.getId(), phoneNumber.getPhone(), phoneNumber.getCustomer()));
        }
        return dtoList;
    }

    public static List<String> phoneNumberList(Iterable<PhoneNumber> phoneNumbers) {
        List<String> phoneNumberList = new ArrayList<String>();
        phoneNumbers.iterator().forEachRemaining(
                phoneNumber -> {
                    phoneNumberList.add(phoneNumber.getPhone());
                }
        );
        return phoneNumberList;
    }

    public static List<Long> buildPhoneNumberIdList(List<PhoneNumberDto> dtoList) {
        List<Long> phoneNumberIds = new ArrayList();
        if (dtoList != null) {
            for (PhoneNumberDto dto : dtoList) {
                phoneNumberIds.add(dto.getId());
            }
        }
        return phoneNumberIds;
    }
}
