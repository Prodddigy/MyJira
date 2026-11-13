package czarnecki.damian.Models;

import java.util.ArrayList;

public class Project {

    private String name;
    private String shortAlias;
    private String description;

    private static ArrayList<Project> projects =new ArrayList<>();

    public Project() {
        projects.add(this);
    }

    public Project(String name, String shortAlias, String description) {
        this.name = name;
        this.shortAlias = shortAlias;
        this.description = description;
        projects.add(this);
    }

    public static ArrayList<Project> getProjects() {
        return projects;
    }

    public static void setProjects(ArrayList<Project> projects) {
        Project.projects = projects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortAlias() {
        return shortAlias;
    }

    public void setShortAlias(String shortAlias) {

        if(shortAlias.length()<5)
            this.shortAlias = shortAlias;
        else
        {
            System.out.println("Alias was too long so it was shortened.");
            this.shortAlias = shortAlias.substring(0, 5);
        }

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
