package com.ikth.apps.msreserve.reservation.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DataAccessObjectConfiguration 
{
/**	
	@Bean
	public SqlSessionFactory getSqlSessionFactory(DataSource dataSource)
	{
		SqlSessionFactoryBean sqlSessionFactory= new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setTypeHandlers(new TypeHandler[] {
				new CustomEnumTypeHandler()
				, new CustomOffSetDateHandler()
		});
		
		try {
			return sqlSessionFactory.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Bean
	public SqlSessionTemplate getSqlSessionTemplate(@Qualifier("getSqlSessionFactory") SqlSessionFactory sqlSessionFactory)
	{
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	@Value("${jndiName}")
	private String jndiName;
	
	@Value("${jdbc.driver}")
	private String driver;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.user}")
	private String user;
	
	@Value("${jdbc.pass}")
	private String password;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer getPropertyConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	@Profile("PRODUCT")
	public DataSource getDataSource()
	{
		return loadJdbcDataSource();
	}
	
	@Bean("h2DataSource")
	@Profile("DEVELOP")
	public DataSource getEmbeddedDataSource()
	{
		EmbeddedDatabaseBuilder builder= new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db= builder
								.setType(EmbeddedDatabaseType.H2)
								.setName("reservationDB;DATABASE_TO_UPPER=false;MODE=MYSQL")
								.addScript("classpath:sqls/reservation_ddl.sql")
								.addScript("classpath:sqls/reservation_dml.sql")
								.build();
		
		return db;
	}

	private DataSource loadJdbcDataSource() {
		BasicDataSource dataSource= new BasicDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		
		return dataSource;
	}
*/
}
