package dong.com.weixin.bean;

/**
 * Created by dong on 2015/10/8.
 */
public class Contant {
    String name;
    int icon;
    String firstLitter;

    public String getFirstLitter() {
        return firstLitter;
    }

    public void setFirstLitter(String firstLitter) {
        this.firstLitter = firstLitter;
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Contant{" +
                "name='" + name + '\'' +
                ", icon=" + icon +
                ", firstLitter='" + firstLitter + '\'' +
                '}';
    }
}
