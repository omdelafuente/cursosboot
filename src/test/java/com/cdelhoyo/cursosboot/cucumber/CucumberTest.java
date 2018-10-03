package com.cdelhoyo.cursosboot.cucumber;

import com.cdelhoyo.cursosboot.CourseApplication;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = {"com.cdelhoyo.cursosboot.web.it"})
@ContextConfiguration(classes = CourseApplication.class)
public class CucumberTest {

}