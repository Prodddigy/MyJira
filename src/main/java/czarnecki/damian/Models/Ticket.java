package czarnecki.damian.Models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;
public class Ticket {

    private static LinkedHashMap<Long, Ticket> tickets = new LinkedHashMap<>();

    private static Long nextId = 1L;


    private Long id;
    private String ticketKey;
    private String title;
    private String description;
    private Level priority;
    private Level severity;
    private Status status;
    private LocalDate createdAt;
    private Project project;
    private Task task;



    public Ticket(Long id, String ticketKey, String title,
                  String description, Level priority,
                  Level severity, Status status,LocalDate createdAt , Project project) {
        setId(id);
        setProject(project);
        setTicketKey(ticketKey);
        setStatus(status);
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.severity = severity;
        setCreatedAt(createdAt);
        tickets.put(getId(), this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = nextId++;
    }




    public String getTicketKey() {
        return ticketKey;
    }

    public void setTicketKey(String ticketKey) {
        this.ticketKey = this.project.getShortAlias() + getId();
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Level getPriority() {
        return priority;
    }

    public void setPriority(Level priority) {
        this.priority = priority;
    }

    public Level getSeverity() {
        return severity;
    }

    public void setSeverity(Level severity) {
        this.severity = severity;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {

        if(project != null)
            this.project = project;
        else
            throw new NullPointerException("project cannot be null");
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public static LinkedHashMap<Long, Ticket> getTickets() {
        return tickets;
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(ticketKey);
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Ticket ticket = (Ticket)o;
        return id != null && id.equals(ticket.id);
    }



    @Override
    public String toString() {
        return """
            ============================================================
                               TICKET DETAILS
            ============================================================
             ID          : %s
             Ticket Key  : %s
             Title       : %s

            ------------------------------------------------------------
             DESCRIPTION
            ------------------------------------------------------------
             %s

            ------------------------------------------------------------
             STATUS      : %s
             PRIORITY    : %s
             SEVERITY    : %s
            ============================================================
            """.formatted(
                Objects.toString(id, "N/A"),
                Objects.toString(ticketKey, "N/A"),
                Objects.toString(title, "N/A"),
                Objects.toString(description, "N/A"),
                Objects.toString(status, "N/A"),
                Objects.toString(priority, "N/A"),
                Objects.toString(severity, "N/A")
        );
    }


}
