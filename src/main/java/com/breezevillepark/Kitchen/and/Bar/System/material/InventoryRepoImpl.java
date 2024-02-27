package com.breezevillepark.Kitchen.and.Bar.System.material;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InventoryRepoImpl {
    private final InventoryRepository inventoryRepository;


    public InventoryRepoImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Material saveMaterial(Material material) {
        return inventoryRepository.save(material);
    }

    public List<Material> getAllMaterials() {
        return inventoryRepository.findAll();
    }

    public Material editMaterial(Material material) {
        return inventoryRepository.save(material);
    }

    public void removeMaterial(Material material) {
        inventoryRepository.delete(material);
    }
}
