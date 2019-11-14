package Views;

import Controllers.Controller;

import Exceptions.MyException;

import Models.Collections.*;
import Models.Exps.ArithExp;
import Models.Exps.ValueExp;
import Models.Exps.VarExp;
import Models.PrgState;
import Models.Stmts.*;
import Models.Types.BoolType;
import Models.Types.IntType;
import Models.Values.BoolValue;
import Models.Values.IntValue;
import Models.Values.Value;

import Repositories.IRepository;
import Repositories.Repository;

import java.util.Scanner;

public class View {

    public static void main(String[] args) {
        //  int v; v=2; Print(v)
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));

        // int a; int b; a=2+3*5; b=a+1; Print(b)
        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)),
                                new ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"),
                                        new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));

        // bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v)
        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));

        while (true) {
            displayMainMenu();

            Scanner scanner = new Scanner(System.in);

            System.out.print("a2:$ ");
            int option = scanner.nextInt();
            System.out.println();

            switch (option) {
                case 0:
                    return;
                case 1:
                    executeProgram(ex1);
                    break;
                case 2:
                    executeProgram(ex2);
                    break;
                case 3:
                    executeProgram(ex3);
                    break;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
        }
    }

    private static void executeProgram(IStmt e) {
        MyIStack<IStmt> exeStack = new MyStack<>();
        MyIDictionary<String, Value> symbTable = new MyDictionary<>();
        MyIList<Value> outList = new MyList<>();
        PrgState progr = new PrgState(exeStack, symbTable, outList, e);

        IRepository repository = new Repository(progr);
        Controller controller = new Controller(repository);

        Scanner scanner = new Scanner(System.in);

        while (!exeStack.isEmpty()) {
            displayStepsMenu();

            System.out.print("a2:$ ");
            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    try {
                        progr = controller.oneStep(progr);
                        System.out.println(progr);
                    } catch (MyException exc) {
                        System.out.println(exc.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println(controller.allSteps());
                    } catch (MyException exc) {
                        System.out.println(exc.getMessage());
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("Please choose an option:");
        System.out.println("1: Execute Program 1");
        System.out.println("2: Execute Program 2");
        System.out.println("3: Execute Program 3");
        System.out.println("0: Exit");
    }

    private static void displayStepsMenu() {
        System.out.println("Choose the execution method:");
        System.out.println("1: One step execution");
        System.out.println("2: All steps execution");
        System.out.println("0: Back");
    }
}
