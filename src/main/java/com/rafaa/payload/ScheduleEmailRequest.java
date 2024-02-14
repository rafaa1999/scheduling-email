package com.rafaa.payload;


import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.ZoneId;

@Getter @Setter @Builder @ToString
public class ScheduleEmailRequest {
   @Email
   @NotEmpty
   private String email;
   @NotEmpty
   private String subject;
   @NotEmpty
   private String body;
   @NotEmpty
   private LocalDate localDate;
   @NotEmpty
   private ZoneId zoneId;
}
