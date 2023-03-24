package database.repositories;

import database.models.GenshinHero;
import database.models.GenshinRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GenshinRegionsRepository extends JpaRepository<GenshinRegion, Integer> {
    @Transactional
    @Query(value = "SELECT genshin_heroes.name FROM genshin_regions JOIN genshin_heroes ON genshin_regions.id = genshin_heroes.genshin_region_id WHERE genshin_regions.location=?;", nativeQuery = true)
    List<String> getGenshinHeroesNamesFromSomeLocationAndSomeWeapon(String location);

/*    @Transactional
    @Query(value = "UPDATE genshin_regions SET location=''", nativeQuery = true)
    List<String> updateLocation();*/
}
