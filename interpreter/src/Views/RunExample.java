package Views;

import Controllers.Controller;
import Exceptions.MyException;
import Models.Commands.Command;

import java.io.IOException;

public class RunExample extends Command {
    private Controller ctr;

    RunExample(String key, String desc, Controller ctr) {
        super(key, desc);
        this.ctr = ctr;
    }

    @Override
    public void execute() {
        try {
            this.ctr.allSteps();
        } catch (MyException | IOException e) {
            e.printStackTrace();
        }
    }
}