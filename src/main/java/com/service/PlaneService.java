package com.service;

import com.dto.PlaneDTO;
import com.entity.Plane;
import com.repository.PlaneRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@RequiredArgsConstructor
public class PlaneService {
    private final PlaneRepository planeRepository;

    public Collection<PlaneDTO> getPlaneList(Long idProfile) {
        Collection<Plane> planes = planeRepository.getByIdProfile(idProfile);
        Collection<PlaneDTO> planeDTOS = new ArrayList<>();
        for (Plane plane : planes) {
            planeDTOS.add(mapToPlaneDTO(plane));
        }
        return planeDTOS;
    }

    public PlaneDTO getPlane(Long id) {
        Plane plane = planeRepository.getById(id);
        return mapToPlaneDTO(plane);
    }

    public void addPlane(Plane plane) {
        planeRepository.insert(plane);
    }

    public void removePlane(Long id) {
        planeRepository.delete(id);
    }

    public void updatePlane(Plane plane) {
        planeRepository.update(plane);
    }

    public boolean canModifyPlane(Long idProfile, Long idVehicle){
        Plane plane = planeRepository.getById(idVehicle);
        if (isPlaneExists(plane)){
            return isIdProfilesEquals(idProfile, plane);
        }
        return false;
    }

    private boolean isIdProfilesEquals(Long idProfile, Plane plane) {
        return Objects.equals(plane.getIdProfile(), idProfile);
    }

    private boolean isPlaneExists(Plane plane) {
        return plane != null;
    }

    private PlaneDTO mapToPlaneDTO(Plane plane) {
        return new PlaneDTO(
                plane.getId(),
                plane.getMark(),
                plane.getModel(),
                plane.getType(),
                plane.getMileage(),
                plane.getPrice(),
                plane.getIdProfile()
        );
    }


}
