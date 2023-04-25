package cat.itacademy.barcelonaactiva.vasovic.milos.s05.t01.n02.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="flowers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlowerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="flower_name", nullable = false, length = 30)
    private String flowerName;
    @Column(name="flower_country", nullable = false, length = 30)
    private String flowerCountry;

    public FlowerEntity(String flowerName, String flowerCountry){
        this.flowerName = flowerName;
        this.flowerCountry = flowerCountry;
    }
}
