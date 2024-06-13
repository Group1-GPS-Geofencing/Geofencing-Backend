package com.geofence.geofencing_backend.controllers;

import com.geofence.geofencing_backend.entities.Fence;
import com.geofence.geofencing_backend.services.FenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Fence Controller
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 27-03-2024
 * Last Modified on: 13-06-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

@RestController
@RequestMapping(path = "/api/v1/fence", produces = MediaType.APPLICATION_JSON_VALUE)
public class FenceController {


    @Autowired
    private final FenceService fenceService;

    /**
     * Constructor for FenceController.
     *
     * @param fenceService the service to manage fences
     */
    public FenceController(FenceService fenceService) {
        this.fenceService = fenceService;
    }



    /**
     * Handles HTTP POST requests to create a new fence.
     * <p>
     * Example request:
     * POST <a href="http://localhost:8080/api/v1/fence">...</a>
     * {
     * "id": 1,
     * "name": "My Fence 1",
     * "description": "A description of my fence 1",
     * "boundary": {
     * "type": "Polygon",
     * "coordinates": [
     * [
     * [35.33707220200199, -15.387612283215404],
     * [35.33670930261826, -15.387952247425758],
     * [35.33686244101247, -15.388081284787773],
     * [35.337222766642924, -15.387728913335536],
     * [35.33707220200199, -15.387612283215404]
     * ]
     * ]
     * },
     * "is_active": true
     * }
     *
     * @param fence the fence to be created
     * @return the created fence
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Fence> createFence(@RequestBody Fence fence) {
        Fence createdFence = fenceService.createFence(fence);
        return new ResponseEntity<>(createdFence, HttpStatus.CREATED);
    }



    /**
     * Handles HTTP GET requests to retrieve a fence by its ID.
     * <p>
     * Example request:
     * GET <a href="http://localhost:8080/api/v1/fence/1">...</a>
     *
     * @param id the ID of the fence to retrieve
     * @return the fence with the specified ID, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Fence> getFenceById(@PathVariable Long id) {
        Fence fence = fenceService.getFenceByID(id);
        if (fence != null) {
            return ResponseEntity.ok(fence);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Handles HTTP GET requests to retrieve all fences.
     *
     * @return a list of all fences, or 204 if none exist
     */
    @GetMapping
    public ResponseEntity<List<Fence>> getAllFences() {
        List<Fence> fences = fenceService.getAllFences();
        if (!fences.isEmpty()) {
            return ResponseEntity.ok(fences);
        } else {
            return ResponseEntity.noContent().build();
        }
    }



    /**
     * Handles HTTP PUT requests to update a fence by its ID.
     * <p>
     * Example request:
     * PUT <a href="http://localhost:8080/api/v1/fence/1">...</a>
     * {
     * "id": 1,
     * "name": "My Fence 1",
     * "description": "An updated description of my fence 1",
     * "boundary": {
     * "type": "Polygon",
     * "coordinates": [
     * [
     * [35.33707220200199, -15.387612283215404],
     * [35.33670930261826, -15.387952247425758],
     * [35.33686244101247, -15.388081284787773],
     * [35.337222766642924, -15.387728913335536],
     * [35.33707220200199, -15.387612283215404]
     * ]
     * ]
     * },
     * "is_active": false
     * }
     *
     * @param id the ID of the fence to update
     * @param fence the updated fence
     * @return the updated fence
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Fence> updateFence(@PathVariable Long id, @RequestBody Fence fence) {
        fence.setId(id);
        Fence updatedFence = fenceService.updateFence(fence);
        return ResponseEntity.ok(updatedFence);
    }



    /**
     * Handles HTTP DELETE requests to delete a fence by its ID.
     * <p>
     * Example request:
     * DELETE <a href="http://localhost:8080/api/v1/fence/1">...</a>
     *
     * @param id the ID of the fence to delete
     * @return a response entity with no content
     */
    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteFence(@PathVariable Long id) {
        fenceService.deleteFence(id);
        return ResponseEntity.noContent().build();
    }

}
