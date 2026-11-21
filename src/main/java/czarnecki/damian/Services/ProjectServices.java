package czarnecki.damian.Services;

import czarnecki.damian.Models.Project;
import czarnecki.damian.Models.Ticket;
import czarnecki.damian.Repositories.ProjectRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProjectServices {

    private final ProjectRepository projectRepository;

    public ProjectServices(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    //TODO TMP look
    public Project createProject(Project project) {return null;}


    public List<Project> findAll()
    {
        return projectRepository.findAll();
    }

    public void CountTicketsPerProject()
    {
        Map<String,Long> ticketsPerProject = Ticket.getTickets().values().stream()
                .collect(Collectors.groupingBy(
                        ticket -> ticket.getProject().getShortAlias(),
                        Collectors.counting()
                ));
        System.out.println(ticketsPerProject);

    }

    public Optional<Project> findByShortAlias(String alias) {
        return projectRepository.findByShortAlias(alias);
    }
}
