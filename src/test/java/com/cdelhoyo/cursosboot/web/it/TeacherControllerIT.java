package com.cdelhoyo.cursosboot.web.it;

import com.cdelhoyo.cursosboot.domain.Course;
import com.cdelhoyo.cursosboot.domain.Level;
import com.cdelhoyo.cursosboot.domain.Teacher;
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
public class TeacherControllerIT {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;
    private MvcResult result;

    @Before
    public void beforeScenario() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @When("^the teacher client calls teachers endpoint /teachers")
    public void the_teacher_client_issues_GET_teachers() throws Throwable{
        result = mvc.perform(get("/teachers")).andDo(print()).andReturn();
    }

    @When("^the teacher client calls teachers endpoint filter /teachers by \"([^\"]*)\"$")
    public void the_teacher_client_calls_filter_teachers_by(String nameFilter) throws Exception {
        result = mvc.perform(get("/teachers?name="+nameFilter)).andDo(print()).andReturn();
    }

    @When("^the teacher client calls teachers by id endpoint /teachers/(\\d+)$")
    public void the_teacher_client_calls_teachers_by_id_endpoint_teachers(Long teacherId) throws Exception {
        result = mvc.perform(get("/teachers/"+teacherId)).andDo(print()).andReturn();
    }

    @When("^the teacher client calls teachers/courses endpoint /teachers/(\\d+)/courses")
    public void the_teacher_client_issues_GET_teachers_courses(Long teacherId) throws Throwable{
        result = mvc.perform(get("/teachers/"+teacherId+"/courses")).andDo(print()).andReturn();
    }

    @When("^the teacher client calls teachers/courses endpoint filter /teachers/(\\d+)/courses by \"([^\"]*)\"$")
    public void the_teacher_client_calls_filter_teachers_courses_by(Long teacherId, String nameFilter) throws Exception {
        result = mvc.perform(get("/teachers/"+teacherId+"/courses?name="+nameFilter)).andDo(print()).andReturn();
    }

    @When("^the teacher client calls new teachers endpoint /teachers with \"([^\"]*)\"$")
    public void the_teacher_client_calls_new_teachers_endpoint_teachers_with(String teacherName) throws Exception {
        Gson gson = new Gson();
        String teacherJson = gson.toJson(new Teacher(teacherName));
        result = mvc.perform(post("/teachers").contentType(MediaType.APPLICATION_JSON).content(teacherJson)).andDo(print()).andReturn();
    }

    @When("^the teacher client calls new courses endpoint /teachers/(\\d+)/courses with \"([^\"]*)\", active \"([^\"]*)\" and level \"([^\"]*)\"$")
    public void the_teacher_client_calls_new_courses_endpoint_teachers_courses_with_active_and_level(Long teacherId, String courseName, Boolean courseActive, Level level) throws Exception {
        Gson gson = new Gson();
        String courseJson = gson.toJson(new Course(courseName, courseActive, level, null));
        result = mvc.perform(post("/teachers/"+teacherId+"/courses").contentType(MediaType.APPLICATION_JSON).content(courseJson)).andDo(print()).andReturn();
    }

    @Then("^the teacher client receives status code of (\\d+)$")
    public void the_teacher_client_receives_status_code_of(int statusCode) throws Throwable {
        assertThat(result.getResponse().getStatus()).isEqualTo(statusCode);
    }

    @Then("^the teacher client receives \"([^\"]*)\" and \"([^\"]*)\" teachers$")
    public void the_teacher_client_receives_and_teachers(String teacher1, String teacher2) throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains(teacher1);
        assertThat(responseContent).contains(teacher2);
    }

    @Then("^the teacher client receives teacher \"([^\"]*)\" and not \"([^\"]*)\"$")
    public void the_teacher_client_receives_teacher__and_not(String teacherFiltered, String teacherNotFiltered) throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains(teacherFiltered);
        assertThat(responseContent).doesNotContain(teacherNotFiltered);
    }

    @Then("^the teacher client receives \"([^\"]*)\" teacher$")
    public void the_teacher_client_receives_teacher(String teacher) throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains(teacher);
    }

    @Then("^the teacher client receives \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" courses$")
    public void the_teacher_client_receives_and_courses(String course1, String course2, String course3, String course4) throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains(course1);
        assertThat(responseContent).contains(course2);
        assertThat(responseContent).contains(course3);
        assertThat(responseContent).contains(course4);
    }

    @Then("^the teacher client receives courses \"([^\"]*)\" and \"([^\"]*)\" but not \"([^\"]*)\" and \"([^\"]*)\"$")
    public void the_teacher_client_receives_courses_and_but_not_and(String courseFiltered1, String courseFiltered2, String courseNotFiltered1, String courseNotFiltered2) throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains(courseFiltered1);
        assertThat(responseContent).contains(courseFiltered2);
        assertThat(responseContent).doesNotContain(courseNotFiltered1);
        assertThat(responseContent).doesNotContain(courseNotFiltered2);
    }

    @Then("^the teacher client receives \"([^\"]*)\" course with active \"([^\"]*)\" and level \"([^\"]*)\"$")
    public void the_teacher_client_receives_course_with_active_and_level_and_not_null_id(String teacherName, String active, String level) throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains(teacherName);
        assertThat(responseContent).contains(active);
        assertThat(responseContent).contains(level);
    }


    @Then("^the teacher client receives pagination")
    public void the_teacher_client_receives_pagination() throws Throwable {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains("pageable");
        assertThat(responseContent).contains("totalPages");
        assertThat(responseContent).contains("totalElements");
        assertThat(responseContent).contains("numberOfElements");
    }
}
