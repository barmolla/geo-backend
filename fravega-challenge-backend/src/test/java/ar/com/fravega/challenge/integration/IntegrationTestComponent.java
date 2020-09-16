package ar.com.fravega.challenge.integration;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import ar.com.fravega.challenge.entity.Sucursal;
import ar.com.fravega.challenge.repository.SucursalRepository;

@SpringBootTest
@ContextConfiguration(initializers = {IntegrationTestComponent.Initializer.class})
public class IntegrationTestComponent {

	@Autowired
	public SucursalRepository sucursalRepository;

	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

		@Container
		private static MySQLContainer<?> mySQLContainer = new MySQLContainer<>();

		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
			mySQLContainer.start();
			TestPropertyValues.of(
					"spring.datasource.url=" + mySQLContainer.getJdbcUrl(),
					"spring.datasource.username=" + mySQLContainer.getUsername(),
					"spring.datasource.password=" + mySQLContainer.getPassword(),
					"spring.jpa.hibernate.naming-strategy=org.hibernate.cfg.ImprovedNamingStrategy"
					).applyTo(configurableApplicationContext.getEnvironment());
		}

		@AfterClass
		public void stop() {
			mySQLContainer.stop();
		}
	}

	@BeforeEach
	public void prepareScenario() {
		insertSucursales();
	}

	@AfterEach
	public void cleanUp() {
		this.sucursalRepository.deleteAll();
	}

	private void insertSucursales() {
		Sucursal suc1 = new Sucursal();
		Sucursal suc2 = new Sucursal();
		Sucursal suc3 = new Sucursal();
		Sucursal suc4 = new Sucursal();

		suc1.setId(1);
		suc2.setId(2);
		suc3.setId(3);
		suc4.setId(4);
		suc1.setAddress("Angel Gallardo");
		suc2.setAddress("Medrano");
		suc3.setAddress("Centenario");
		suc4.setAddress("Malabia");
		suc1.setLatitude(-34.602232);
		suc2.setLatitude(-34.603204);
		suc3.setLatitude(-34.60421);
		suc4.setLatitude(-34.599247);
		suc1.setLongitude(-58.431625);
		suc2.setLongitude(-58.420766);
		suc3.setLongitude(-58.435829);
		suc4.setLongitude(-58.439641);

		ArrayList<Sucursal> sucursales = new ArrayList<>();

		sucursales.add(suc1);
		sucursales.add(suc2);
		sucursales.add(suc3);
		sucursales.add(suc4);

		sucursales.stream().forEach(suc -> sucursalRepository.save(suc));
	}
}
