package Views;

import Controllers.Controller;
import Exceptions.MyException;

public class RunExample extends Command {
    private final Controller ctr;

    RunExample(String key, String desc, Controller ctr) {
        super(key, desc);
        this.ctr = ctr;
    }

    @Override
    public void execute() {
        try {
            this.ctr.allStep();
        } catch (MyException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
