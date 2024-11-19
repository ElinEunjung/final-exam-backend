package no.kristiania.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //Entity class is mapping to DB table. This class is JPA entity.
@Data // Automatically generates getters, setters, toString(), equals() and hashCode() method
@NoArgsConstructor // Automatically generates constructors with and without arguments
@AllArgsConstructor
public class Order {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
