package it.linksmt.rental.controller;

import it.linksmt.rental.dto.CreateVehicleRequest;
import it.linksmt.rental.dto.UpdateVehicleRequest;
import it.linksmt.rental.entity.VehicleEntity;
import it.linksmt.rental.enums.UserType;

import it.linksmt.rental.exception.ServiceException;
import it.linksmt.rental.security.SecurityBean;
import it.linksmt.rental.security.SecurityContext;
import it.linksmt.rental.service.AuthenticationService;
import it.linksmt.rental.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;


    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createVehicle(@RequestBody CreateVehicleRequest createVehicleRequest) {
     try {
         VehicleEntity createdVehicle = vehicleService.createVehicle(createVehicleRequest);
         return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicle);
     } catch (ServiceException e) {
        throw e;
     }
    }

    @GetMapping
    public ResponseEntity<List<VehicleEntity>> getAllVehicles() {
        try {
            List<VehicleEntity> vehicleList = vehicleService.findAllVehicle();

            return ResponseEntity.status(HttpStatus.OK).body(vehicleList);
        }catch (ServiceException e) {
            throw e;
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<VehicleEntity> getVehicleById(@PathVariable Long id) {
        try {
            VehicleEntity vehicle = vehicleService.findVehicleById(id);

            return ResponseEntity.status(HttpStatus.OK).body(vehicle);
        }catch (ServiceException e) {
            throw e;
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        try{
     vehicleService.deleteVehicle(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (ServiceException e) {
            throw e;
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleEntity> updateVehicle(@PathVariable Long id,@Valid @RequestBody UpdateVehicleRequest updateVehicleRequest) {

            VehicleEntity vehicle = vehicleService.updateVehicle(id, updateVehicleRequest);

            return ResponseEntity.status(HttpStatus.OK).body(vehicle);

}


}
