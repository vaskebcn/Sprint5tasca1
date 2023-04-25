package cat.itacademy.barcelonaactiva.vasovic.milos.s05.t01.n02.model.service;

import cat.itacademy.barcelonaactiva.vasovic.milos.s05.t01.n02.model.domain.FlowerEntity;
import cat.itacademy.barcelonaactiva.vasovic.milos.s05.t01.n02.model.dto.FlowerDto;
import cat.itacademy.barcelonaactiva.vasovic.milos.s05.t01.n02.model.repository.FlowerRepository;
import cat.itacademy.barcelonaactiva.vasovic.milos.s05.t01.n02.model.utils.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlowerServiceImpl implements FlowerService{

    @Autowired
    FlowerRepository flowerRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<FlowerDto> getAllFlowerDto() {
        return flowerRepository.findAll()
                .stream()
                .map(this::convertFromFlowerToDto)
                .collect(Collectors.toList());
    }

    @Override
    public FlowerDto getOneFlowerDto(int id) {
        FlowerEntity flowerEntity = flowerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("flower not found with id " +id));
        FlowerDto flowerDto = convertFromFlowerToDto(flowerEntity);
        return flowerDto;
    }

    @Override
    public void addFlowerDto(FlowerDto flowerDto) {
        FlowerEntity flowerEntity = convertFromDtoToFlower(flowerDto);
        flowerRepository.save(flowerEntity);
    }

    @Override
    public FlowerDto updateFlower(int id, FlowerDto flowerDto) {
        FlowerEntity flowerEntity = flowerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("flower not found with id "+id));
        FlowerDto flowerDto1 = null;
            flowerEntity.setFlowerName(flowerDto.getFlowerName());
            flowerEntity.setFlowerCountry(flowerDto.getFlowerCountry());
            flowerRepository.save(flowerEntity);
            flowerDto1 = convertFromFlowerToDto(flowerEntity);
            return flowerDto1;
    }

    @Override
    public void deleteFlower(int id) {
        Optional<FlowerEntity>flowerOptional = flowerRepository.findById(id);
        if(flowerOptional.isPresent()){
            flowerRepository.delete(flowerOptional.get());
        }
    }

    private FlowerDto convertFromFlowerToDto(FlowerEntity flowerEntity){
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        FlowerDto flowerDto  = modelMapper.map(flowerEntity, FlowerDto.class);
        flowerDto.viewType();
        return flowerDto;
    }

    private FlowerEntity convertFromDtoToFlower(FlowerDto flowerDto){
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        FlowerEntity flowerEntity = modelMapper.map(flowerDto, FlowerEntity.class);
        return flowerEntity;
    }
}
