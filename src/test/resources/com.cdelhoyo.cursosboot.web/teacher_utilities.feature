Feature: teachers utilities

  Scenario: client makes call to GET /teachers
    When the teacher client calls teachers endpoint /teachers
    Then the teacher client receives status code of 200
    And the teacher client receives "Roberto Canales" and "Rubén Aguilera Díaz-Heredero" teachers
    And the teacher client receives pagination

  Scenario: client makes filter call to GET /teachers and filter by name
    When the teacher client calls teachers endpoint filter /teachers by "rober"
    Then the teacher client receives status code of 200
    And the teacher client receives teacher "Roberto Canales" and not "Rubén Aguilera Díaz-Heredero"
    And the teacher client receives pagination

  Scenario: client makes call to GET /teachers/{teacherId}
    When the teacher client calls teachers by id endpoint /teachers/1
    Then the teacher client receives status code of 200
    And the teacher client receives "Roberto Canales" teacher

  Scenario: client makes call to GET /teachers/{teacherId}/courses
    When the teacher client calls teachers/courses endpoint /teachers/2/courses
    Then the teacher client receives status code of 200
    And the teacher client receives "Backup y Restore en GitLab", "Instalación de GitLab con HTTPS", "Kubernetes en AWS con Kops" and "Test E2E en Angular con Cypress" courses
    And the teacher client receives pagination

  Scenario: client makes filter call to GET /teachers/{teacherId}/courses and filter by name
    When the teacher client calls teachers/courses endpoint filter /teachers/2/courses by "git"
    Then the teacher client receives status code of 200
    And the teacher client receives courses "Backup y Restore en GitLab" and "Instalación de GitLab con HTTPS" but not "Kubernetes en AWS con Kops" and "Test E2E en Angular con Cypress"
    And the teacher client receives pagination

  Scenario: client makes call to POST /teachers
    When the teacher client calls new teachers endpoint /teachers with "Alejandro"
    Then the teacher client receives status code of 200
    And the teacher client receives "Alejandro" teacher

  Scenario: client makes call to POST /teachers/{teacherId}/courses
    When the teacher client calls new courses endpoint /teachers/1/courses with "Cucumber", active "true" and level "ELEMENTARY"
    Then the teacher client receives status code of 200
    And the teacher client receives "Cucumber" course with active "true" and level "ELEMENTARY"