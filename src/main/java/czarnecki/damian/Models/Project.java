package czarnecki.damian.Models;

public class Project {

    private String name;
    private String shortAlias;
    private String description;

    public Project(String name, String shortAlias, String description) {
        this.name = name;
        this.shortAlias = shortAlias;
        this.description = description;
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
