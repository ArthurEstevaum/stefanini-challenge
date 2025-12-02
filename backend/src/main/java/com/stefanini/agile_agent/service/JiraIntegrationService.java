package com.stefanini.agile_agent.service;

import com.stefanini.agile_agent.client.jira.JiraClient;
import com.stefanini.agile_agent.domain.UserStoryDto;
import com.stefanini.agile_agent.client.jira.request.JiraIssueRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JiraIntegrationService {
    private final JiraClient jiraClient;
    private final String projectKey;

    public JiraIntegrationService(JiraClient jiraClient, @Value("${jira.project-key}") String projectKey) {
        this.jiraClient = jiraClient;
        this.projectKey = projectKey;
    }

    public void criarCardsNoJira(List<UserStoryDto> stories) {
        for (UserStoryDto story : stories) {
            var request = montarPayloadJira(story);
            jiraClient.createIssue(request);
        }
    }

    private JiraIssueRequest montarPayloadJira(UserStoryDto story) {
        // Montando o ADF (Rich Text do Jira) na mão via Map
        Map<String, Object> adfDescription = Map.of(
                "type", "doc",
                "version", 1,
                "content", List.of(
                        Map.of(
                                "type", "paragraph",
                                "content", List.of(
                                        Map.of(
                                                "type", "text",
                                                "text", story.descricao() + "\n\nCritérios:\n" + String.join("\n- ", story.criteriosAceite())
                                        )
                                )
                        )
                )
        );

        return new JiraIssueRequest(new JiraIssueRequest.Fields(
                new JiraIssueRequest.Project(this.projectKey),
                story.titulo(),
                adfDescription,
                new JiraIssueRequest.Issuetype("10004")
        ));
    }
}
