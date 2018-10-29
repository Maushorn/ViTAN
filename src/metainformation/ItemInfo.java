package metainformation;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**This class is used to showeach players collected items in the PlayerInfoDialogs TableView.
 *
 */
public class ItemInfo {

    SimpleLongProperty userId;
    SimpleStringProperty twitterhandle;
    SimpleStringProperty adventure;
    SimpleStringProperty item;

    public ItemInfo(){
        userId = new SimpleLongProperty();
        twitterhandle = new SimpleStringProperty();
        adventure = new SimpleStringProperty();
        item = new SimpleStringProperty();
    }

    public long getUserId() {
        return userId.get();
    }

    public SimpleLongProperty userIdProperty() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId.set(userId);
    }

    public String getTwitterhandle() {
        return twitterhandle.get();
    }

    public SimpleStringProperty twitterhandleProperty() {
        return twitterhandle;
    }

    public void setTwitterhandle(String twitterhandle) {
        this.twitterhandle.set(twitterhandle);
    }

    public String getAdventure() {
        return adventure.get();
    }

    public SimpleStringProperty adventureProperty() {
        return adventure;
    }

    public void setAdventure(String adventure) {
        this.adventure.set(adventure);
    }

    public String getItem() {
        return item.get();
    }

    public SimpleStringProperty itemProperty() {
        return item;
    }

    public void setItem(String item) {
        this.item.set(item);
    }
}
