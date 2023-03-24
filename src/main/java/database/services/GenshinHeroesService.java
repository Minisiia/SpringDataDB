package database.services;

import database.models.GenshinHero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import database.repositories.GenshinHeroesRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
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

    @Transactional
    public void save(GenshinHero genshinHero) {
        genshinHeroesRepository.save(genshinHero);
    }

    @Transactional
    public void update(int id, GenshinHero updatedGenshinHero) {
        updatedGenshinHero.setId(id);
        genshinHeroesRepository.save(updatedGenshinHero);
    }

    @Transactional
    public void delete(int id) {
        genshinHeroesRepository.deleteById(id);
    }

    @Transactional
    public List<GenshinHero> getGenshinHeroesWithSomeWeapon(String weapon) {
        return genshinHeroesRepository.getGenshinHeroesWithSomeWeapon(weapon);
    }
}
