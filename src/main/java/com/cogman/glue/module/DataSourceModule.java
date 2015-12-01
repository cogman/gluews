package com.cogman.glue.module;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

@Module
public class DataSourceModule
{
	@Provides @Singleton DataSource provideDataSource()
	{
		HikariConfig config = new HikariConfig();

		// The URL and driver should be changed to match your database
		// for MSSQL, this is a valid URI, cwad is the address of the database server and LM is the database name
		// Similar to saying "USE LM" in your sql code
		config.setJdbcUrl("jdbc:sqlserver://cwad;database=LM");
		config.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		config.setConnectionTimeout(20000);
		config.setMaximumPoolSize(20);

		// Change this to an easy to identify thread pool name.  This helps with debugging
		config.setPoolName("LM-DB-POOL");

		// This should probably be gotten in a different way, ideally it would be something like
		// an environment variable or a config file value rather than a hard coded value.
		config.setUsername("perseus");
		config.setPassword("perseus");

		return new HikariDataSource(config);
	}

	@Provides @Singleton JdbcTemplate provideJdbcTemlate(DataSource dataSource)
	{
		return new JdbcTemplate(dataSource);
	}
}
