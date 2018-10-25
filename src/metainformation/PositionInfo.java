package metainformation;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class PositionInfo {

    SimpleLongProperty userId;
    SimpleStringProperty twitterhandle;
    SimpleStringProperty adventure;
    SimpleStringProperty location;

    public PositionInfo(){
        userId = new SimpleLongProperty();
        twitterhandle = new SimpleStringProperty();
        adventure = new SimpleStringProperty();
        location = new SimpleStringProperty();
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

    public String getLocation() {
        return location.get();
    }

    public SimpleStringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }
}