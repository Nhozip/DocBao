package model;

/**
 * Created by Nhozip on 6/14/2016.
 */
public class ItemRSS {
    private String _title;
    private String _link;
    private String _pubdate;
    private String _img;


    public ItemRSS(String _title, String _link, String _pubdate, String _img) {
        this._title = _title;
        this._link = _link;
        this._pubdate = _pubdate;
        this._img = _img;
    }

    @Override
    public String toString() {
        return "ItemRSS{" +
                "_title='" + _title + '\'' +
                ", _link='" + _link + '\'' +
                ", _pubdate='" + _pubdate + '\'' +
                ", _img='" + _img + '\'' +
                '}';
    }

    public String get_title() {
        return _title;
    }

    public String get_link() {
        return _link;
    }

    public String get_pubdate() {
        return _pubdate;
    }

    public String get_img() {
        return _img;
    }
}
