package com.bootcamp.demo.project_heatmap_ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration 
public class AppConfig {
  /*@Bean
  RestTemplate restTemplate(){
    return new RestTemplate();
  }*/

  @Bean
  public RestTemplate restTemplate() {
      RestTemplate rt = new RestTemplate();
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
      converter.setObjectMapper(mapper);
      rt.getMessageConverters().add(converter);
      return rt;
  }
}
