DROP TABLE IF EXISTS vehicle;

CREATE TABLE vehicle
(
  vehicle_id INTEGER(10) NOT NULL AUTO_INCREMENT,
  year VARCHAR(64) NOT NULL,
  make VARCHAR(64) NOT NULL,
  model VARCHAR(64) NOT NULL,
  PRIMARY KEY (vehicle_id)
)