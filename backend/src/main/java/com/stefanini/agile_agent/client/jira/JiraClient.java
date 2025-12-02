package com.stefanini.agile_agent.client.jira;

import com.stefanini.agile_agent.client.jira.request.JiraIssueRequest;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "jiraClient",
        url = "${jira.url}",
        configuration = JiraClient.JiraConfig.class
)
public interface JiraClient {
    @PostMapping("/rest/api/3/issue")
    void createIssue(@RequestBody JiraIssueRequest request);

    // Classe de configuração interna para Autenticação
    class JiraConfig {
        @Bean
        public BasicAuthRequestInterceptor basicAuthRequestInterceptor(
                @Value("${jira.username}") String username,
                @Value("${jira.token}") String token) {
            return new BasicAuthRequestInterceptor(username, token);
        }
    }
}
