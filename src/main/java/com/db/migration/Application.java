package com.db.migration;

import com.db.migration.service.DbMigrationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    private final DbMigrationService dbMigrationService;

    public Application(DbMigrationService dbMigrationService) {
        this.dbMigrationService = dbMigrationService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            dbMigrationService.migrateEntities();
        };
    }
}
