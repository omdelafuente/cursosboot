Feature: course utilities

  Scenario: client makes call to GET /courses
    When the course client calls courses endpoint /courses
    Then the course client receives status code of 200
    And the course client receives "Metodologías ágiles", "Backup y Restore en GitLab", "Instalación de GitLab con HTTPS", "Kubernetes en AWS con Kops" and "Test E2E en Angular con Cypress" courses
    And the course client receives pagination

  Scenario: client makes filter call to GET /courses and filter by name
    When the course client calls courses endpoint filter /courses by "git"
    Then the course client receives status code of 200
    And the course client receives course "Backup y Restore en GitLab" and "Instalación de GitLab con HTTPS" but not "metodologías ágiles", "Kubernetes en AWS con Kops" and "Test E2E en Angular con Cypress"
    And the course client receives pagination

  Scenario: client makes call to GET /courses/{courseId}
    When the course client calls courses by id endpoint /courses/1
    Then the course client receives status code of 200
    And the course client receives "Metodologías ágiles" course

  Scenario: client makes call to GET /courses/{courseId}/subjects
    When the course client calls courses/subjects endpoint /courses/1/subjects
    Then the course client receives status code of 200
    And the course client receives "Scrum", "Kanban" and "Historias de usuario" subjects
    And the course client receives pagination

  Scenario: client makes filter call to GET /courses/{courseId}/subjects and filter by name
    When the course client calls courses/subjects endpoint filter /courses/1/subjects by "scru"
    Then the course client receives status code of 200
    And the course client receives subject "Scrum" but not "Kanban" and "Historias de usuario"
    And the course client receives pagination

  Scenario: client makes call to POST /courses/{courseId}/subjects
    When the course client calls new subjects endpoint /courses/1/subjects with "Cucumber"
    Then the course client receives status code of 200
    And the course client receives "Cucumber" subject