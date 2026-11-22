package czarnecki.damian.Repositories;

import czarnecki.damian.Models.Project;
import czarnecki.damian.Models.Status;
import czarnecki.damian.Models.Ticket;

import java.util.List;

public class InMemoryTicketRepository implements ITicketRepository {


    @Override
    public Ticket Save(Ticket ticket) {
        return null;
    }

    public List<Ticket> ListAllOpenedTicketsOfProject(Project project) {
       List<Ticket> list =  Ticket.getTickets().values().stream()
                .filter(t->t.getProject().equals(project))
                .filter(t->t.getStatus().equals(Status.OPEN))
                .toList();
        if (list.isEmpty())
        {
            return null;
        }
        return list;
    }
}
