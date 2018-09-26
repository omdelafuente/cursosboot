package com.cdelhoyo.cursosboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "spring.jmx.enabled:true", "spring.datasource.jmx-enabled:true" })
@ActiveProfiles("scratch")
public class CourseApplicationTests {

	@Test
	public void testJmx() throws Exception {
		assertThat(ManagementFactory.getPlatformMBeanServer()
				.queryMBeans(new ObjectName("jpa.sample:type=HikariDataSource,*"), null))
						.hasSize(1);
	}

}
