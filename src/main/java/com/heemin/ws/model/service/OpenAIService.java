package com.heemin.ws.model.service;

import org.springframework.stereotype.Service;

@Service
public class OpenAIService{

    private final ChatClient chatClient;

    public OpenAIServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Override
    public Answer getCapital(GetCapitalRequest getCapitalRequest) {
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));
        ChatResponse response = chatClient.call(prompt);

        return new Answer(response.getResult().getOutput().getContent());
    }
}