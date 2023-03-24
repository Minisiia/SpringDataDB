package database.repositories;

import database.models.GenshinHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GenshinHeroesRepository extends JpaRepository<GenshinHero, Integer> {

    @Transactional
    @Query(value = "SELECT * FROM genshin_heroes WHERE weapon = ?", nativeQuery = true)
    List<GenshinHero> getGenshinHeroesWithSomeWeapon(String weapon);
}
