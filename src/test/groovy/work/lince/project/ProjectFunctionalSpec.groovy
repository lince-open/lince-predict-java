package work.lince.predict

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import work.lince.project.model.Project
import work.lince.project.model.ProjectStatus
import work.lince.project.repository.ProjectRepository
/**
 * @author pzatta
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProjectFunctionalSpec extends Specification {

    @Shared
    RESTClient client

    @LocalServerPort
    int port;

    @Autowired
    ProjectRepository projectRepository

    def setup() {
        client = new RESTClient("http://localhost:${port}/")
        client.contentType = ContentType.JSON
    }

    @Unroll
    def "get Success"() {
        given:
            projectRepository.save(new Project(title: title))

        when:
            def result = client.get(path: "projects")

        then:
            result != null


        where:
            title            | _
            "Projet Title 1" | _


    }


    @Unroll
    def "Create Projetc #title"() {
        given:
            def body = [
                title: title,
                status : status
            ]


        when:
            def result = client.post(path: "projects", body: body, headers: ["lince.user.name": userName])

        then:
            result != null
            result.data.id != null
            result.data.title == title
            result.data.status == ProjectStatus.CREATED.toString()
            result.data.owner == expectedOwner

        where:
            title             | userName   | status                || expectedOwner
            "Analyze Title 1" | null       | null                  || 'anonymous'
            "Analyze Title 2" | 'x1324'    | ProjectStatus.CREATED || 'x1324'
            "Analyze Title 3" | 'zxcvasdf' | ProjectStatus.CLOSED  || 'zxcvasdf'


    }

}