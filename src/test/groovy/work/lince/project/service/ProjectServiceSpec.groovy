package work.lince.predict.service

import spock.lang.Specification
import spock.lang.Unroll
import work.lince.commons.authentication.AuthenticationService
import work.lince.project.model.Project
import work.lince.project.model.ProjectStatus
import work.lince.project.repository.ProjectRepository
/**
 * @author pzatta
 */

class ProjectServiceSpec extends Specification {

    AnalyzeService service;

    def setup() {
        service = Spy(AnalyzeService)
        service.repository = Mock(ProjectRepository)
        service.authenticationService = Mock(AuthenticationService)

    }

    @Unroll
    def "verify with #title"() {
        given:
            1 * service.repository.save(_) >> { Project value ->
                value.id = id
                return value
            }
            1 * service.authenticationService.getAuthenticatedUser() >> { user }
            def project = new Project(
                title: title,
                status: status,
                owner: owner
            )
        when:
            def result = service.create(project)

        then:
            result != null
            result.id == id
            result.title == title
            result.owner == user
            result.status == ProjectStatus.CREATED

        where:
            title             | status               | owner      | user   | id
            "Analyze Title 1" | null                 | "asdfasdf" | "asdf" | 1L
            "Analyze Title 2" | ProjectStatus.CLOSED | null       | "qwer" | 2L
            "Analyze Title 3" | null                 | null       | "asdf" | 3L
            "Analyze Title 4" | ProjectStatus.CLOSED | "asdfasdf" | "qwer" | 4L


    }

}