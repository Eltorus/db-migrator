package com.db.migration.model;

import java.util.Objects;

public class Vessel {
    private Integer id;
    private String name;
    private String vesselClass;
    private String captain;
    private Integer launchedYear;

    public Vessel() {
    }

    public Vessel(Integer id, String name, String vesselClass, String captain, Integer launchedYear) {
        this.id = id;
        this.name = name;
        this.vesselClass = vesselClass;
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

    public String getVesselClass() {
        return vesselClass;
    }

    public void setVesselClass(String vesselClass) {
        this.vesselClass = vesselClass;
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

        Vessel vessel = (Vessel) o;

        if (!Objects.equals(id, vessel.id)) return false;
        if (!Objects.equals(name, vessel.name)) return false;
        if (!Objects.equals(vesselClass, vessel.vesselClass)) return false;
        if (!Objects.equals(captain, vessel.captain)) return false;
        return Objects.equals(launchedYear, vessel.launchedYear);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (vesselClass != null ? vesselClass.hashCode() : 0);
        result = 31 * result + (captain != null ? captain.hashCode() : 0);
        result = 31 * result + (launchedYear != null ? launchedYear.hashCode() : 0);
        return result;
    }
}
