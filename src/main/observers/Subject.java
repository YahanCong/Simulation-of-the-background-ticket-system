package observers;

import observers.Observer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    private List<Observer> observerList = new ArrayList<>();


    public void notifyObservers() throws IOException {
        for (Observer observer : observerList) {
            observer.update();
        }

    }

    public void addObservers(Observer observer) {
        if (! observerList.contains(observer)) {
            observerList.add(observer);
        }

    }


}
