package database.repositories;

import database.models.GenshinRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GenshinRegionsRepository extends JpaRepository<GenshinRegion, Integer> {
    @Transactional
    @Query(value = "SELECT genshin_heroes.name FROM genshin_regions JOIN genshin_heroes ON genshin_regions.id = genshin_heroes.genshin_region_id WHERE genshin_regions.location=?;", nativeQuery = true)
    List<String> getGenshinHeroesNamesFromSomeLocationAndSomeWeapon(String location);

}
