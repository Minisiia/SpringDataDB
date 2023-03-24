package database.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "genshin_heroes")
public class GenshinHero {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "element")
    private String element;


    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "weapon")
    private String weapon;

    @Min(value = 0, message = "genshin_region_id should be 1...5")
    @Column(name = "genshin_region_id")
    private int genshinRegionId;

    @Min(value = 0, message = "Rarity should be 4 or 5")
    @Column(name = "rarity")
    private int rarity;

    public int getGenshinRegionId() {
        return genshinRegionId;
    }

    public void setGenshinRegionId(int genshinRegionId) {
        this.genshinRegionId = genshinRegionId;
    }

    public GenshinHero() {

    }

    public GenshinHero(int id, String name, String element, String weapon, int genshinRegionId, int rarity) {
        this.id = id;
        this.name = name;
        this.element = element;
        this.weapon = weapon;
        this.genshinRegionId = genshinRegionId;
        this.rarity = rarity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    @Override
    public String toString() {
        return "GenshinHero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", element='" + element + '\'' +
                ", weapon='" + weapon + '\'' +
                ", genshinRegionId=" + genshinRegionId +
                ", rarity=" + rarity +
                '}';
    }
}
