package au.com.inteqweb.directory.dto;

import au.com.inteqweb.directory.entity.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneNumberDto {
    @JsonProperty
    private Long id;

    @JsonProperty
    private String phone;

    @JsonProperty
    private Customer customer;
}