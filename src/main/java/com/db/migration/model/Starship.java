package com.db.migration.model;

import java.util.Objects;

public class Starship {
    private Integer id;
    private String name;
    private String starshipClass;
    private String captain;
    private Integer launchedYear;

    public Starship() {
    }

    public Starship(Integer id, String name, String starshipClass, String captain, Integer launchedYear) {
        this.id = id;
        this.name = name;
        this.starshipClass = starshipClass;
        this.captain = captain;
        this.launchedYear = launchedYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStarshipClass() {
        return starshipClass;
    }

    public void setStarshipClass(String starshipClass) {
        this.starshipClass = starshipClass;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public Integer getLaunchedYear() {
        return launchedYear;
    }

    public void setLaunchedYear(Integer launchedYear) {
        this.launchedYear = launchedYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Starship starship = (Starship) o;

        if (!Objects.equals(id, starship.id)) return false;
        if (!Objects.equals(name, starship.name)) return false;
        if (!Objects.equals(starshipClass, starship.starshipClass))
            return false;
        if (!Objects.equals(captain, starship.captain)) return false;
        return Objects.equals(launchedYear, starship.launchedYear);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (starshipClass != null ? starshipClass.hashCode() : 0);
        result = 31 * result + (captain != null ? captain.hashCode() : 0);
        result = 31 * result + (launchedYear != null ? launchedYear.hashCode() : 0);
        return result;
    }
}
