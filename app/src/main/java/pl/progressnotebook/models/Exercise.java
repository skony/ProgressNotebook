package pl.progressnotebook.models;

/**
 * Created by piotr on 09.03.16.
 */
public class Exercise {

    private int id;
    private String name;
    private int series;
    private int[] loads;
    private int[] repeats;

    public int[] getRepeats() {
        return repeats;
    }

    public void setRepeats(int[] repeats) {
        this.repeats = repeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int[] getLoads() {
        return loads;
    }

    public void setLoads(int[] loads) {
        this.loads = loads;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
