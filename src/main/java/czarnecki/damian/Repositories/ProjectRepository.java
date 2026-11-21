package czarnecki.damian.Repositories;

import czarnecki.damian.Models.Project;

import java.util.*;

public class ProjectRepository {
    //TODO public static is TMP
    public static Map<String, Project> projects = new LinkedHashMap<>();

    public Project save(Project project) {

        if(project == null)
        {
            throw new IllegalArgumentException("Project cannot be null");
        }
        if(projects.containsKey(project.getName()))
        {
            throw new IllegalArgumentException("Project already exists");
        }
        projects.put(project.getName(), project);
        return project;
    }

    public List<Project> findAll() {
        return  new ArrayList<>(projects.values());
    }

    public Optional<Project> findByShortAlias(String alias)
    {
        if(alias ==null || alias.isEmpty())
        {
            return Optional.empty();
        }

        for(Project project : projects.values())
            System.out.println(project.getShortAlias());

        System.out.println(projects.get(alias).getShortAlias());
        return Optional.ofNullable(projects.get(alias));

    }
}
