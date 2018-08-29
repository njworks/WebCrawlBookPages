import java.util.ArrayList;
import java.util.List;

public class Person {
    private String url;
    private List<String> names = new ArrayList<>();

    public Person(String name, String url) {
        names.add(name);
        this.url = url;
    }

    public void setNames(String name) {
        names.add(name);
    }

    @Override
    public String toString() {
        String joinNames = String.join(",", names);
        return "URL: " + this.url + "  || Names: " + joinNames;
    }
}
