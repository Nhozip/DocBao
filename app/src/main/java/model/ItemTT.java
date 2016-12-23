package model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Nhozip on 6/15/2016.
 */
@Table(name= "VnXpress")
public class ItemTT extends Model{
    private static final String LINK="link";
    private static final String TITLE="title";

    @Column(name=LINK)
    private String name;

    @Column(name=TITLE)
    private String link;
    public ItemTT(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public ItemTT() {
    }

    @Override
    public String toString() {
        return "ItemTT{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }
}
