package com.db.migration.service;

import com.db.migration.model.Starship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbMigrationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbMigrationService.class);
    private final JdbcTemplate starshipsJdbcTemplate;
    private final JdbcTemplate vesselsJdbcTemplate;

    public DbMigrationService(JdbcTemplate starshipsJdbcTemplate,
                              JdbcTemplate vesselsJdbcTemplate) {
        this.starshipsJdbcTemplate = starshipsJdbcTemplate;
        this.vesselsJdbcTemplate = vesselsJdbcTemplate;
    }

    private final RowMapper<Starship> vesselRowMapper = (rs, rowNum) -> new Starship(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("class"),
            rs.getString("captain"),
            rs.getInt("launched_year")
    );


    // retry??
    // pagination??
    public void migrateEntities() {
        LOGGER.info("Starting migration...");
        String selectQuery = "SELECT id, name, class, captain, launched_year FROM starships";
        List<Starship> starships = starshipsJdbcTemplate.query(selectQuery, vesselRowMapper);

        LOGGER.info("Count of entities to migrate: {}", starships.size());
        String insertQuery = "INSERT INTO vessels (name, class, captain, launched_year) VALUES (?, ?, ?, ?)";
        for (Starship starship : starships) {
            vesselsJdbcTemplate.update(insertQuery,
                    starship.getName(),
                    starship.getStarshipClass(),
                    starship.getCaptain(),
                    starship.getLaunchedYear()
            );
        }
        LOGGER.info("Migration finished successfully");
    }
}
