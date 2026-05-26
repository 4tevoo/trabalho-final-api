package org.serratec.coldmart.model;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorMessage(List<String> message, LocalDateTime data) {
}
