package database.repositories;

import database.models.GenshinHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GenshinHeroesRepository extends JpaRepository<GenshinHero, Integer> {

    @Transactional
    @Query(value = "SELECT * FROM genshin_heroes WHERE weapon = ?", nativeQuery = true)
    List<GenshinHero> getGenshinHeroesWithSomeWeapon(String weapon);

    List<GenshinHero> findByName(String name);

    List<GenshinHero> findByRarity(Integer rarity);

    @Transactional
    @Modifying
    @Query(value = "UPDATE genshin_heroes SET name = ? WHERE id = ?", nativeQuery = true)
    void updateHeroNameById(String name, int id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM genshin_heroes WHERE name = ?", nativeQuery = true)
    void deleteByName(String name);
}
