package me.wiput.eurekaclient;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class ServiceDiscoveryClient {

  @Autowired
  private DiscoveryClient discoveryClient;

  public Student getStudent(int studentId) {
    RestTemplate restTemplate = new RestTemplate();
    List<ServiceInstance> instances =
        discoveryClient.getInstances("studentservice");
    String serviceUri = String.format("%s/v1/student/%d"
        , instances.get(0).getUri().toString(), studentId);
    ResponseEntity<Student> restExchange =
        restTemplate.exchange(serviceUri, HttpMethod.GET,null, Student.class);

    return restExchange.getBody();
  }
}
