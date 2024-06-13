package com.geofence.geofencing_backend.services;

import com.geofence.geofencing_backend.entities.Fence;
import com.geofence.geofencing_backend.repositories.FenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Fence Service.
 * Service class for managing Fence entities.
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 27-03-2024
 * Last Modified on: 13-06-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

@Service
public class FenceService {

    @Autowired
    private FenceRepository fenceRepository;

    /**
     * Saves a new Fence entity.
     *
     * @param fence The Fence object to be saved.
     * @return The saved Fence object.
     */
    public Fence createFence(Fence fence){
        return fenceRepository.save(fence);
    }

    /**
     * Retrieves a Fence by its ID.
     *
     * @param id The ID of the Fence to retrieve.
     * @return The found Fence, or null if not found.
     */
    public Fence getFenceByID(Long id){
        return fenceRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves all Fence entities.
     *
     * @return A list of all Fence entities.
     */
    public List<Fence> getAllFences(){
        return fenceRepository.findAll();
    }

    /**
     * Retrieves the active Fence.
     * Only one Fence can be active at a time.
     *
     * @return The active Fence, or null if none is active.
     */
    public Fence getActiveFence(){
            return fenceRepository.findByIsActiveTrue();
    }

    /**
     * Updates an existing Fence.
     *
     * @param fence The updated Fence object.
     * @return The updated Fence object.
     * @throws IllegalArgumentException If the Fence ID is null or does not exist in the database.
     */
    public Fence updateFence(Fence fence){
        // Check if the fence exists
        if (fence.getId() == null || !fenceRepository.existsById(fence.getId())) {
            throw new IllegalArgumentException("Fence not found");
        }
        return fenceRepository.save(fence);
    }

    /**
     * Deletes a Fence by its ID.
     *
     * @param id The ID of the Fence to delete.
     */
    public void deleteFence(Long id){
        fenceRepository.deleteById(id);
    }

}
