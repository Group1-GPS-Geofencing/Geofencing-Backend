package com.geofence.geofencing_backend.services;

/*
 * Fence Service
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 27-03-2024
 * Last Modified on: 28-03-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.geofence.geofencing_backend.entities.CurrentLocation;
import com.geofence.geofencing_backend.entities.Fence;
import com.geofence.geofencing_backend.repositories.FenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FenceService {

    @Autowired
    private FenceRepository fenceRepository;

    //creates a fence
    public Fence createFence(Fence fence){
        return fenceRepository.save(fence);
    }

    //returns a fence after querying by ID
    public Fence getFenceByID(Long id){
        return fenceRepository.findById(id).orElse(null);
    }

    //returns all fences
    public List<Fence> getAllFences(){
        return fenceRepository.findAll();
    }

    //return active fence
    //only one fence will be active at a time
    public Fence getActiveFence(){
            return fenceRepository.findByIsActiveTrue();
    }

    //update fence
    public Fence updateFence(Fence fence){
        // Check if the fence exists
        if (fence.getId() == null || !fenceRepository.existsById(fence.getId())) {
            throw new IllegalArgumentException("Fence not found");
        }
        return fenceRepository.save(fence);
    }

    //delete fence
    public void deleteFence(Long id){
        fenceRepository.deleteById(id);
    }

    //checks if the active fence is breached or not basing on the current location
    //as of now this method will be void since Logic is not yet implemented
    public void isFenceBreached(CurrentLocation currentLocation){
        //TODO: Implement Logic, including method signature
    }

}
