package au.com.inteqweb.directory.repository;

import au.com.inteqweb.directory.entity.PhoneNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneNumberRepository extends CrudRepository<PhoneNumber, Long> {
    List<PhoneNumber> findAll();
}
