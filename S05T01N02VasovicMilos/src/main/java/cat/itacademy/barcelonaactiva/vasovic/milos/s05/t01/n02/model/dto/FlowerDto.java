package cat.itacademy.barcelonaactiva.vasovic.milos.s05.t01.n02.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlowerDto {

    private int id;
    private String flowerName;
    private String flowerCountry;
    private String flowerType;

    public FlowerDto(String flowerName, String flowerCountry){
        this.flowerName = flowerName;
        this.flowerCountry = flowerCountry;
    }

    public void viewType(){
//        String type = "non UE";
//        if(UECountries.contains(type.toLowerCase())){
//            type = "UE";
//        }
//        setFlowerType(type);
        this.flowerType = (UECountries.contains(flowerCountry.toLowerCase())) ? "UE" : "non UE";
    }

    private static final ArrayList<String> UECountries = new ArrayList<>(Arrays.asList("austria", "belgium", "bulgaria"
            , "croatia", "Republic of cyprus", "czech republic", "denmark", "estonia", "finland", "france"
            , "germany", "greece", "hungary", "ireland", "italy", "latvia", "lithuania", "luxembourg", "malta"
            , "netherlands", "poland", "portugal", "romania", "slovakia", "slovenia", "spain", "sweden"));
}
