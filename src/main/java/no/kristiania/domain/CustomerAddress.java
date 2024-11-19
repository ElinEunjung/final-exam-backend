package no.kristiania.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerAddress {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;
    private String website;
    private long phoneNumber;
    private String name;

    //@OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
    public CustomerAddress(String website, long phoneNumber, String name) {
        this.website = website;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }
}
