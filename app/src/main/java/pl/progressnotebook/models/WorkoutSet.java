package pl.progressnotebook.models;

/**
 * Created by piotr on 14.03.16.
 */
public class WorkoutSet {

    private long id;
    private String name;

    public WorkoutSet(){}

    public WorkoutSet(long id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
