package czarnecki.damian.Repositories;

import czarnecki.damian.Models.Project;
import czarnecki.damian.Models.Ticket;

import java.util.List;

public interface ITicketRepository {

    Ticket Save(Ticket ticket);
    public List<Ticket> ListAllOpenedTicketsOfProject(Project project);
}
