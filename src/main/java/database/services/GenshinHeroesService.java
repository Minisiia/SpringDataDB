package database.services;

import database.models.GenshinHero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import database.repositories.GenshinHeroesRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GenshinHeroesService {

    private final GenshinHeroesRepository genshinHeroesRepository;

    @Autowired
    public GenshinHeroesService(GenshinHeroesRepository genshinHeroesRepository) {
        this.genshinHeroesRepository = genshinHeroesRepository;
    }

    public List<GenshinHero> findAll() {
        return genshinHeroesRepository.findAll();
    }

    public GenshinHero findOne(int id) {
        Optional<GenshinHero> foundPerson = genshinHeroesRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public List<GenshinHero> findByName(String name) {
        List<GenshinHero> findByNameList = genshinHeroesRepository.findByName(name);
        return findByNameList;
    }

    public List<GenshinHero> findByRarity(Integer rarity) {
        List<GenshinHero> findByRarityList = genshinHeroesRepository.findByRarity(rarity);
        return findByRarityList;
    }

    public void save(GenshinHero genshinHero) {
        genshinHeroesRepository.save(genshinHero);
    }

    public void update(int id, GenshinHero updatedGenshinHero) {
        updatedGenshinHero.setId(id);
        genshinHeroesRepository.save(updatedGenshinHero);
    }

    public void updateHeroNameById(int id, String name) {
        genshinHeroesRepository.updateHeroNameById(name, id);
    }

    public void delete(int id) {
        genshinHeroesRepository.deleteById(id);
    }

    public void deleteByName(String name) {
        genshinHeroesRepository.deleteByName(name);
    }

    public List<String> getGenshinHeroesWithSomeWeapon(String location, String weapon) {
        return genshinHeroesRepository.getGenshinHeroesWithSomeWeaponFromSomeRegion(location, weapon);
    }
}
