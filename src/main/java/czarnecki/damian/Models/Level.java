package czarnecki.damian.Models;

public enum Level {
    LOW(3),
    MEDIUM(2),
    HIGH(1),
    CRITICAL(0);

    private final int priority;
    Level(int priority)
    {this.priority = priority;}

    public int getPriority()
    {return this.priority;}
}
