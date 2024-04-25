package com.geofence.geofencing_backend.controllers;

/*
 * Fence Controller
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 27-03-2024
 * Last Modified on: 25-04-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.geofence.geofencing_backend.entities.Fence;
import com.geofence.geofencing_backend.services.FenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/fence")
public class FenceController {


    @Autowired
    private final FenceService fenceService;

    //arg constructor
    public FenceController(FenceService fenceService) {
        this.fenceService = fenceService;
    }


    /*
        HTTP POST request handler

        i.e., http://localhost:8080/api/v1/fence
        Receives the following parameters to create a fence instance
        {
            "id": 1,
            "name": "My Fence 1",
            "description": "A description of my fence 1",
            "boundary": {
                "type": "Polygon",
                "coordinates": [
                    [
                        [
                            10.0,
                            20.0
                        ],
                        [
                            15.0,
                            25.0
                        ],
                        [
                            20.0,
                            30.0
                        ],
                        [
                            10.0,
                            20.0
                        ]
                    ]
                ]
            },
            "is_active": true
        }
     */
    @PostMapping
    public ResponseEntity<Fence> createLocation(@RequestBody Fence fence) {
        Fence createdFence = fenceService.createFence(fence);
        return new ResponseEntity<>(createdFence, HttpStatus.CREATED);
    }


    /*
        HTTP GET/id request handler
        Receives the id parameter to return a specific fence instance if it exists
     */
    @GetMapping("/{id}")
    public ResponseEntity<Fence> getEventLogById(@PathVariable Long id) {
        Fence fence = fenceService.getFenceByID(id);
        if (fence != null) {
            return ResponseEntity.ok(fence);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /*
        HTTP GET request handler
        Receives no parameters to return a list of all fences instances
     */
    @GetMapping
    public ResponseEntity<List<Fence>> getAllEventLogs() {
        List<Fence> fences = fenceService.getAllFences();
        if (!fences.isEmpty()) {
            return ResponseEntity.ok(fences);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    /*
        HTTP PUT/id request handler
        Receives the id parameters to update a fence instance by id if it exists

        i.e., http://localhost:8080/api/v1/fence/2

        Receives the following parameters to update an existing fence instance
        {
            "id": 1,
            "name": "My Fence 1",
            "description": "An updated description of my fence 1",
            "boundary": {
                "type": "Polygon",
                "coordinates": [
                    [
                        [
                            10.0,
                            20.0
                        ],
                        [
                            15.0,
                            25.0
                        ],
                        [
                            20.0,
                            30.0
                        ],
                        [
                            10.0,
                            20.0
                        ]
                    ]
                ]
            },
            "is_active": false
        }
     */
    @PutMapping("/{id}")
    public ResponseEntity<Fence> updateFence(@PathVariable Long id, @RequestBody Fence fence) {
        fence.setId(id);
        Fence updatedFence = fenceService.updateFence(fence);
        return ResponseEntity.ok(updatedFence);
    }


    /*
        HTTP DELETE/id request handler
        Receives the id parameter to delete a fence  instance by id if it exists

        i.e., http://localhost:8080/api/v1/fence/2
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFence(@PathVariable Long id) {
        fenceService.deleteFence(id);
        return ResponseEntity.noContent().build();
    }

}