# db-migrator
Application that provides Data Migration between MySQL and PostgreSQL using Docker and Java

### Setup instructions: 
1. Prerequisites: installed Java 17, Docker, Maven
2. Build application using `mvn clean package`
3. Run databases using `docker-compose up -d mysql-db postgres-db`. This will run databases and containers and creates needed tables and data in table.
4. Verify that MySQL DB is present and there's table starfleet_db.starships which is filled with data from [starships.json](mysql-init/starships.json).
5. Verify that PostgreSQL DB is present and federation_db.vessels table is created.

### Running the application
1. Run db-migrator using `docker-compose up db-migrator`. It will transfer records from MySQL to PostgreSQL.
2. Wait for db-migrator container to stop. 
3. After that check logs of the container, it should contain "Migration finished successfully" in case of successful migration.
4/ Verify that PostgreSQL federation_db.vessels table is filled with migrated data.

### Assumptions and Decisions:
- Some records may fail to transfer. Since, there's no good backoff way to fix that during migration, we need to log error and log ids that were failed to transfer
- As future improvements, in case of large dataset, batch insert can be added to reduce number of calls to DB and query pagination to not hold all records inside memory. But, as provided data set relatively small and in order to not overcomplicate code and eliminate preliminary optimization, this change was omitted.

### Troubleshooting:
- In case you failed to connect to MySQL, make sure that your connection has these properties set to according values:
  - allowPublicKeyRetrieval=true
  - useSSL=false
