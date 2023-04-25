package cat.itacademy.barcelonaactiva.vasovic.milos.s05.t01.n02.model.service;

import cat.itacademy.barcelonaactiva.vasovic.milos.s05.t01.n02.model.domain.FlowerEntity;
import cat.itacademy.barcelonaactiva.vasovic.milos.s05.t01.n02.model.dto.FlowerDto;

import java.util.List;

public interface FlowerService {
    List<FlowerDto> getAllFlowerDto();
    FlowerDto getOneFlowerDto(int id);
    void addFlowerDto(FlowerDto flowerDto);
    FlowerDto updateFlower(int id, FlowerDto flowerDto);
    void deleteFlower(int id);
}
