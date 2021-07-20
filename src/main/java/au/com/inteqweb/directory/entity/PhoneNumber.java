package au.com.inteqweb.directory.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "phonenumber", schema = "public")
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}