package com.breezevillepark.Kitchen.and.Bar.System.menugroup;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuGroup, Integer> {
    @Override
    boolean existsById(Integer integer);
}
