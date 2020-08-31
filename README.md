# This is a Rest API, which calls an API where Basic Authentication enabled

## API which it calls is 

(Exposed Rest API)[https://github.com/arun786/springsecuritycore]

Configuration required for 

    spring:
      profiles: basic
    
    resource:
      server:
        user: user
        password: 7a6bd109-cab2-4cff-8432-2abdacb64398
        
        The above password changes when the exposed rest api server is restarted.
        
        
        
 Client Call
 
 
    package com.arun.springsecuritycoreclient.client;
    
    import com.arun.springsecuritycoreclient.config.ClientApiProperties;
    import com.arun.springsecuritycoreclient.config.ResourceServerProperties;
    import com.arun.springsecuritycoreclient.model.Student;
    import org.apache.tomcat.util.codec.binary.Base64;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Component;
    import org.springframework.web.reactive.function.client.WebClient;
    
    import java.util.ArrayList;
    import java.util.List;
    
    /**
     * @author arun on 8/30/20
     */
    
    @Component
    public class StudentHttpClient {
    
        private final WebClient webClient;
        private final ResourceServerProperties resourceServerProperties;
        private final ClientApiProperties clientApiProperties;
    
        @Autowired
        public StudentHttpClient(WebClient webClient, ResourceServerProperties resourceServerProperties, ClientApiProperties clientApiProperties) {
            this.webClient = webClient;
            this.resourceServerProperties = resourceServerProperties;
            this.clientApiProperties = clientApiProperties;
        }
    
        public List<Student> getSpringSecurityCore(String name) {
            String header = getHeader();
            ResponseEntity<List<Student>> response = webClient.get()
                    .uri(uriBuilder ->
                            uriBuilder
                                    .scheme(clientApiProperties.getSchema())
                                    .host(clientApiProperties.getHostname())
                                    .port(clientApiProperties.getPort())
                                    .path(clientApiProperties.getPath())
                                    .queryParam("name", name)
                                    .build())
                    .header("Authorization", header)
                    .retrieve()
                    .toEntityList(Student.class).block();
    
            List<Student> students = new ArrayList<>();
    
            if (response != null) {
                students = response.getBody();
            }
    
            return students;
        }
    
        private String getHeader() {
            String user = resourceServerProperties.getUser();
            String password = resourceServerProperties.getPassword();
            String base64Credentials = new String(Base64.encodeBase64((user + ":" + password).getBytes()));
            return "Basic " + base64Credentials;
        }
    }

