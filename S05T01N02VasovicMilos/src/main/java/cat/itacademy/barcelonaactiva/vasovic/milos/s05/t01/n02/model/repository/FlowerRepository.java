package cat.itacademy.barcelonaactiva.vasovic.milos.s05.t01.n02.model.repository;

import cat.itacademy.barcelonaactiva.vasovic.milos.s05.t01.n02.model.domain.FlowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerRepository extends JpaRepository<FlowerEntity, Integer> {
}
