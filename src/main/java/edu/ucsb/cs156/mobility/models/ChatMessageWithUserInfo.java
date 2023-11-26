package edu.ucsb.cs156.mobility.models;

import edu.ucsb.cs156.mobility.entities.ChatMessage;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageWithUserInfo {
  private ChatMessage chatMessage;
  private String email;
}