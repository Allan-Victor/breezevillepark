package com.breezevillepark.Kitchen.and.Bar.System.material;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepoImpl inventoryRepo;

    @Autowired
    public InventoryService(InventoryRepoImpl inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    public Material addNewMaterial(Material material){
        if (material == null){
            throw new IllegalArgumentException("Material can not be null");
        }
        return inventoryRepo.saveMaterial(material);
    }

    public List<Material> showAllMaterials(){
        return inventoryRepo.getAllMaterials();
    }

    public Material updateMaterial(Material material){
        return inventoryRepo.editMaterial(material);
    }

    public void deleteMaterial(Material material){
        inventoryRepo.removeMaterial(material);

    }
}
