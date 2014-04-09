package com.tennissetapp.config;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.googlecode.flyway.core.Flyway;
import com.googlecode.flyway.core.api.MigrationVersion;

/**
 * created by DataConfig
 * 
 * @author meir
 */
public class FlywayMigrate {
	protected DataSource dataSource;
	protected Logger logger = Logger.getLogger(FlywayMigrate.class);
	
	public void migrate(){
		logger.debug("Flyway.migrate");
		Flyway flyway = new Flyway();
		flyway.setDataSource(dataSource);
		flyway.setInitVersion(new MigrationVersion("1.0"));
		flyway.setInitOnMigrate(true);
		flyway.setLocations("db/migrations"); //the directory of sql scripts
		flyway.setSchemas("tennissetapp");
		flyway.setTable("schema_version");
		flyway.migrate();
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
