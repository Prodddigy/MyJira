package czarnecki.damian.Models.DTO;

import czarnecki.damian.Models.Level;
import czarnecki.damian.Models.Project;
import czarnecki.damian.Models.Status;
import czarnecki.damian.Models.Task;

import java.time.LocalDate;


public record TicketDTO(
        String title,
        String description,
        Level priority,
        Level severity,
        Status status,
        LocalDate date,
        Project project,
        Task task
) {}