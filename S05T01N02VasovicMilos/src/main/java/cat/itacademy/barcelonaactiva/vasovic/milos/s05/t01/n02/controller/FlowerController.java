package cat.itacademy.barcelonaactiva.vasovic.milos.s05.t01.n02.controller;

import cat.itacademy.barcelonaactiva.vasovic.milos.s05.t01.n02.model.dto.FlowerDto;
import cat.itacademy.barcelonaactiva.vasovic.milos.s05.t01.n02.model.service.FlowerService;
import cat.itacademy.barcelonaactiva.vasovic.milos.s05.t01.n02.model.utils.ErrorMessage;
import cat.itacademy.barcelonaactiva.vasovic.milos.s05.t01.n02.model.utils.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/flower")
@Tag(name = "Flowers EndPoints", description = "crud resources from Flowers")
public class FlowerController {

    @Autowired
    FlowerService flowerService;

    @PostMapping("/add")
    @Operation(
            tags = {"checking tags"},
            operationId = "addFlower",
            summary = "method for create flower purpose",
            description = "this is a description about addFlower endpoint",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "id and type are set up automatically"),
            responses ={
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK - flower created successfully.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request - " + "\nFormat error while adding a file to the DB.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error - Some error occurred while adding a flower to the DB.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                    )
            }
    )
    public ResponseEntity<ErrorMessage>addFlower(@RequestBody FlowerDto flowerDto, WebRequest request) throws Exception {
        try{
            flowerService.addFlowerDto(flowerDto);
            ErrorMessage message = new ErrorMessage(HttpStatus.NO_CONTENT.value(), new Date(), "Flower Created", request.getDescription(false));
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }catch(Exception e){
            throw new Exception("error creating flower");
        }
    }

    @PutMapping("/update/{id}")
    @Operation(
            tags = {"checking tags"},
            operationId = "updateFlower",
            summary = "method for update flower purpose",
            description = "this is a description about updateFlower endpoint",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "id and type are set up automatically"),
            responses ={
                    @ApiResponse(
                            responseCode = "200",
                            description = "Updated - flower updated successfully.",
                            content = @Content(schema = @Schema(implementation = FlowerDto.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found-"+"\n May be two reasons:\n 1. The JSON sent don't contain all necessary info." +
                                    "\n 2. Id of the flower is not in the DB.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error-Some error occurred while updating a flower to the DB.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                    )
            }
    )
    public ResponseEntity<FlowerDto>updateFlower(@PathVariable("id") int id, @RequestBody FlowerDto flowerDto, WebRequest request) throws  Exception{
            FlowerDto flowerDto1 =
                    flowerService.updateFlower(id, flowerDto);
            ErrorMessage message = new ErrorMessage(HttpStatus.OK.value(), new Date(), "Flower Updated", request.getDescription(false));
            return new ResponseEntity<>(flowerDto1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            tags = {"checking tags"},
            operationId = "deleteFlower",
            summary = "method for delete flower purpose",
            description = "this is a description about deleteFlower endpoint",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "id and type are set up automatically"),
            responses ={
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK - flower deleted successfully.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found- "+"\nId of the flower is not in the DB.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error - Some error occurred while deleting a flower from the DB.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                    )
            }
    )
    public ResponseEntity<ErrorMessage>deleteFlower(@PathVariable("id")int id, WebRequest request) throws Exception{
        try{
            flowerService.deleteFlower(id);
            ErrorMessage message = new ErrorMessage(HttpStatus.OK.value(), new Date(), "Flower Deleted", request.getDescription(false));
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(ResourceNotFoundException ex){
            throw ex;
        }catch(Exception ex){
            throw new Exception("Error deleting", ex);
        }
    }

    @GetMapping("/getOneById/{id}")
    @Operation(
            tags = {"checking tags"},
            operationId = "getOneById",
            summary = "method for get one flower purpose",
            description = "this is a description about getOneById Flower endpoint",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "no request body"),
            responses ={
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK - flower brought successfully.",
                            content = @Content(schema = @Schema(implementation = FlowerDto.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found- "+"\n Id of the flower is not in the DB.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error - Some error occurred while bringing a flower from the DB.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                    )
            }
    )
    public ResponseEntity<FlowerDto>getFlower(@PathVariable("id")int id) throws Exception{
        try{
            FlowerDto flowerDto = flowerService.getOneFlowerDto(id);
            return new ResponseEntity<>(flowerDto, HttpStatus.OK);
        }catch(ResourceNotFoundException ex){
            throw ex;
        }catch(Exception ex){
            throw new Exception("Getting Flower Error", ex);
        }
    }

    @GetMapping("/getAll")
    @Operation(
            tags = {"checking tags"},
            operationId = "getAllFlower",
            summary = "method for get all flowers purpose",
            description = "this is a description about getAllFlower endpoint",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "no request body"),
            responses ={
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK - flowers brought successfully.",
                            content = @Content(schema = @Schema(implementation = FlowerDto.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found- "+"\nflowers not found in the DB.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error - Some error occurred while getting all flowers from the DB.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                    )
            }
    )
    public ResponseEntity<List<FlowerDto>>getAllFlowers() throws Exception {
        try{
            List<FlowerDto>flowers = flowerService.getAllFlowerDto();
            return new ResponseEntity<>(flowers, HttpStatus.OK);
        }catch(ResourceNotFoundException ex){
            throw ex;
        }catch(Exception ex){
            throw new Exception("Error Getting All Flowers", ex);
        }
    }

}
