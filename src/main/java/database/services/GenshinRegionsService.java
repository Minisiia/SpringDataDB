package database.services;

import database.models.GenshinHero;
import database.models.GenshinRegion;
import database.repositories.GenshinHeroesRepository;
import database.repositories.GenshinRegionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GenshinRegionsService {

    private final GenshinRegionsRepository genshinRegionsRepository;

    @Autowired
    public GenshinRegionsService(GenshinRegionsRepository genshinRegionsRepository) {
        this.genshinRegionsRepository = genshinRegionsRepository;
    }


    public List<GenshinRegion> findAll() {
        return genshinRegionsRepository.findAll();
    }

    public GenshinRegion findOne(int id) {
        Optional<GenshinRegion> foundRegion = genshinRegionsRepository.findById(id);
        return foundRegion.orElse(null);
    }

    @Transactional
    public void save(GenshinRegion genshinRegion) {
        genshinRegionsRepository.save(genshinRegion);
    }

    @Transactional
    public void update(int id, GenshinRegion genshinRegion) {
        genshinRegion.setId(id);
        genshinRegionsRepository.save(genshinRegion);
    }

    @Transactional
    public void delete(int id) {
        genshinRegionsRepository.deleteById(id);
    }

    @Transactional
    public List<String> getGenshinHeroesNamesFromSomeLocationAndSomeWeapon(String location) {
        return genshinRegionsRepository.getGenshinHeroesNamesFromSomeLocationAndSomeWeapon(location);
    }
}
