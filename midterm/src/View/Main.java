package View;

import Controllers.Controller;
import Exps.*;
import Exps.ValueExp;
import Repository.IRepository;
import Repository.Repository;

public class Main {

    public static void main(String args[]) {
        Exp exp1 = new IFNEG(new ValueExp(-20), new IFPOS(new ValueExp(-2), new ValueExp(-28)));
        Exp exp2 = new IFNEG(new ValueExp(-20), new IFPOS(new ValueExp(2), new Abs(new ValueExp(28))));

        Exp exp3 = new Max(new ValueExp(2), new ValueExp(10));
        Exp exp4 = new Min(new ValueExp(3), new ValueExp(10));
        Exp exp5 = new Neg(new ValueExp(2));
        Exp exp6 = new Min(new Max(new ValueExp(-22), new ValueExp(-10)), new Min(new ValueExp(2), new ValueExp(-1)));

        IRepository repository = new Repository(exp2);
        Controller controller = new Controller(repository);

        System.out.println(controller.getExp() + " evaluates to " + controller.getResult() + ".");
    }
}
