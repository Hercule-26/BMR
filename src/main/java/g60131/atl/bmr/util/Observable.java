package g60131.atl.bmr.util;

import g60131.atl.bmr.model.LifeStyle;
import g60131.atl.bmr.model.Sexe;

public interface Observable {

    void register(Observer obs);
    void unregister(Observer obs);
}
