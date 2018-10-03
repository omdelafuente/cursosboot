package com.cdelhoyo.cursosboot.web.it;

import com.cdelhoyo.cursosboot.domain.Subject;
import com.google.gson.Gson;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
public class CourseControllerIT {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;
    private MvcResult result;

    @Before
    public void beforeScenario() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @When("^the course client calls courses endpoint /courses")
    public void the_course_client_issues_GET_courses() throws Throwable{
        result = mvc.perform(get("/courses")).andDo(print()).andReturn();
    }

    @When("^the course client calls courses endpoint filter /courses by \"([^\"]*)\"$")
    public void the_course_client_calls_filter_courses_by(String nameFilter) throws Exception {
        result = mvc.perform(get("/courses?name="+nameFilter)).andDo(print()).andReturn();
    }

    @When("^the course client calls courses by id endpoint /courses/(\\d+)$")
    public void the_course_client_calls_courses_by_id_endpoint_courses(Long courseId) throws Exception {
        result = mvc.perform(get("/courses/"+courseId)).andDo(print()).andReturn();
    }

    @When("^the course client calls courses/subjects endpoint /courses/(\\d+)/subjects")
    public void the_course_client_issues_GET_courses_subjects(Long courseId) throws Throwable{
        result = mvc.perform(get("/courses/"+courseId+"/subjects")).andDo(print()).andReturn();
    }

    @When("^the course client calls courses/subjects endpoint filter /courses/(\\d+)/subjects by \"([^\"]*)\"$")
    public void the_course_client_calls_filter_courses_subjects_by(Long courseId, String nameFilter) throws Exception {
        result = mvc.perform(get("/courses/"+courseId+"/subjects?name="+nameFilter)).andDo(print()).andReturn();
    }

    @When("^the course client calls new subjects endpoint /courses/(\\d+)/subjects with \"([^\"]*)\"$")
    public void the_course_client_calls_new_subjects_endpoint_courses_subjects_with(Long courseId, String subjectName) throws Exception {
        Gson gson = new Gson();
        String subjectJson = gson.toJson(new Subject(subjectName, null));
        result = mvc.perform(post("/courses/"+courseId+"/subjects").contentType(MediaType.APPLICATION_JSON).content(subjectJson)).andDo(print()).andReturn();
    }

    @Then("^the course client receives status code of (\\d+)$")
    public void the_course_client_receives_status_code_of(int statusCode) throws Throwable {
        assertThat(result.getResponse().getStatus()).isEqualTo(statusCode);
    }

    @Then("the course client receives \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" courses$")
    public void the_course_client_receives_and_courses(String course1, String course2, String course3, String course4, String course5) throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains(course1);
        assertThat(responseContent).contains(course2);
        assertThat(responseContent).contains(course3);
        assertThat(responseContent).contains(course4);
        assertThat(responseContent).contains(course5);
    }

    @Then("^the course client receives course \"([^\"]*)\" and \"([^\"]*)\" but not \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void the_course_client_receives_course(String courseFiltered1, String courseFiltered2, String courseNotFiltered1, String courseNotFiltered2, String courseNotFiltered3) throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains(courseFiltered1);
        assertThat(responseContent).contains(courseFiltered2);
        assertThat(responseContent).doesNotContain(courseNotFiltered1);
        assertThat(responseContent).doesNotContain(courseNotFiltered2);
        assertThat(responseContent).doesNotContain(courseNotFiltered3);
    }

    @Then("^the course client receives \"([^\"]*)\" course$")
    public void the_course_client_receives_course(String course) throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains(course);
    }

    @Then("^the course client receives \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" subjects$")
    public void the_course_client_receives_and_subjects(String subject1, String subject2, String subject3) throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains(subject1);
        assertThat(responseContent).contains(subject2);
        assertThat(responseContent).contains(subject3);
    }

    @Then("^the course client receives subject \"([^\"]*)\" but not \"([^\"]*)\" and \"([^\"]*)\"$")
    public void the_course_client_receives_subjects_and_but_not_and(String subjectFiltered, String subjectNotFiltered1, String subjectNotFiltered2) throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains(subjectFiltered);
        assertThat(responseContent).doesNotContain(subjectNotFiltered1);
        assertThat(responseContent).doesNotContain(subjectNotFiltered2);
    }

    @Then("^the course client receives \"([^\"]*)\" subject$")
    public void the_course_client_receives_subject(String subjectName) throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains(subjectName);
    }

    @Then("^the course client receives pagination")
    public void the_course_client_receives_pagination() throws Throwable {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains("pageable");
        assertThat(responseContent).contains("totalPages");
        assertThat(responseContent).contains("totalElements");
        assertThat(responseContent).contains("numberOfElements");
    }
}
