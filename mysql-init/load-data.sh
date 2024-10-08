#!/bin/bash
echo "Waiting for MySQL to be ready..."
until mysql -h"localhost" -u"root" -p"$MYSQL_ROOT_PASSWORD" -e "status"; do
  sleep 5
done

echo "Load JSON data and insert into the table..."
cat /docker-entrypoint-initdb.d/starships.json | jq -c '.[]' | while read -r row; do
  name=$(echo $row | jq -r '.name')
  class=$(echo $row | jq -r '.class')
  captain=$(echo $row | jq -r '.captain')
  launched_year=$(echo $row | jq -r '.launched_year')

  mysql -h"localhost" -u"root" -p"$MYSQL_ROOT_PASSWORD" -D"$MYSQL_DATABASE" -e "
  INSERT INTO starships (name, class, captain, launched_year)
  VALUES ('$name', '$class', '$captain', $launched_year);
  "
done

echo "Data inserted into starships table."
