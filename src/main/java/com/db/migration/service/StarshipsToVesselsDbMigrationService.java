package com.db.migration.service;

import com.db.migration.model.Starship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StarshipsToVesselsDbMigrationService implements DbMigrationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StarshipsToVesselsDbMigrationService.class);
    private final JdbcTemplate starshipsJdbcTemplate;
    private final JdbcTemplate vesselsJdbcTemplate;

    public StarshipsToVesselsDbMigrationService(JdbcTemplate starshipsJdbcTemplate,
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

    public void performMigration() {
        LOGGER.info("Starting migration...");
        String selectQuery = "SELECT id, name, class, captain, launched_year FROM starships";
        List<Starship> starships = starshipsJdbcTemplate.query(selectQuery, vesselRowMapper);

        LOGGER.info("Count of entities to migrate: {}", starships.size());
        String insertQuery = "INSERT INTO vessels (name, class, captain, launched_year) VALUES (?, ?, ?, ?)";

        List<Integer> notTransferredRecordsIds = new ArrayList<>();
        for (Starship starship : starships) {
            try {
                vesselsJdbcTemplate.update(insertQuery,
                        starship.getName(),
                        starship.getStarshipClass(),
                        starship.getCaptain(),
                        starship.getLaunchedYear()
                );
            } catch (Exception e) {
                LOGGER.error("Failed to insert record with id: {}. Exception: ", starship.getId(), e);
                notTransferredRecordsIds.add(starship.getId());
            }
        }
        if (!notTransferredRecordsIds.isEmpty()) {
            LOGGER.info("Migration result: transferred {} records out of {} records. IDs that weren't transferred: {}",
                    starships.size() - notTransferredRecordsIds.size(), starships.size(), notTransferredRecordsIds);
        } else {
            LOGGER.info("Migration finished successfully");
        }
    }
}
