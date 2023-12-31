package edu.ucsb.cs156.mobility.controllers;

import edu.ucsb.cs156.mobility.entities.ChatMessage;
import edu.ucsb.cs156.mobility.models.ChatMessageWithUserInfo;
import edu.ucsb.cs156.mobility.repositories.ChatMessageRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;

@Tag(name = "Chat Message")
@RequestMapping("/api/chat")
@RestController

public class ChatMessageController extends ApiController {

    @Autowired
    ChatMessageRepository chatMessageRepository;

    @Operation(summary = "Create a new message")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DRIVER')")
    @PostMapping("/post")
    public ChatMessage postMessage(
        @Parameter(name="content", description="The message you want to send", example="Hi", required = true)
        @RequestParam String content
        )
        {

        ChatMessage message = new ChatMessage();
        
        message.setUserId(getCurrentUser().getUser().getId());
        message.setPayload(content);

        ChatMessage savedMessage = chatMessageRepository.save(message);

        return savedMessage;
    }

    @Operation(summary = "List all messages with user info")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DRIVER')")
    @GetMapping("/get")
    public Page<ChatMessageWithUserInfo> allMessagesNewWay(
         @Parameter(name="page") @RequestParam int page,
         @Parameter(name="size") @RequestParam int size
    ) {
        Page<ChatMessageWithUserInfo> messages = chatMessageRepository.findAllWithUserInfo(PageRequest.of(page, size, Sort.by("timestamp").descending()));
        return messages;
    }
}
