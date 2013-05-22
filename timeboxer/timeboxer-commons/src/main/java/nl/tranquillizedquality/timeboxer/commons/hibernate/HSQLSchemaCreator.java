package nl.tranquillizedquality.timeboxer.commons.hibernate;

import javax.sql.DataSource;

/**
 * Utility class that creates a schema in a HSQL database.
 * 
 * @author Salomo Petrus (salomo.petrus@tele2.com)
 * @since Aug 3, 2012
 * 
 */
public class HSQLSchemaCreator implements InitializingBean {

	/**
	 * schema name.
	 */
	private String schema;

	/**
	 * data source.
	 */
	private DataSource dataSource;

	// setters and getters
	public String getSchema() {
		return schema;
	}

	public void setSchema(final String schema) {
		this.schema = schema;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(final DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Create schema.
	 * 
	 * @throws Exception
	 *             any exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("DROP SCHEMA IF EXISTS " + schema + " CASCADE");
		jdbcTemplate.execute("CREATE SCHEMA " + schema + " AUTHORIZATION DBA;");
	}
}
