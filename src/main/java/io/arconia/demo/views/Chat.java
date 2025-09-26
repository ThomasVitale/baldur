package io.arconia.demo.views;

import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

import org.springframework.ai.chat.client.ChatClient;

import java.time.Instant;

@Menu(title = "Chat", icon = "vaadin:chat", order = 2)
@Route("/chat")
@PermitAll
public class Chat extends VerticalLayout {

    private final ChatClient chatClient;

    public Chat(ChatClient.Builder builder) {
        setSizeFull();
        chatClient = builder.build();
        buildView();
    }

    private void buildView() {
        var messages = new MessageList();
        var input = new MessageInput();

        messages.setMarkdown(true);
        input.setWidthFull();

        input.addSubmitListener(event -> {
            var userMessage = event.getValue();

            messages.addItem(new MessageListItem(userMessage, Instant.now(), "You"));

            var response = chatClient.prompt()
                .user(userMessage)
                .call()
                .content();

            messages.addItem(new MessageListItem(response, Instant.now(), "AI"));
        });

        addAndExpand(new Scroller(messages));
        add(input);
    }

}
