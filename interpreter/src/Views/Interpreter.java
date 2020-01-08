package Views;

import Controllers.Controller;
import Exceptions.MyException;
import Models.Collections.MyDictionary;
import Models.Exps.*;
import Models.PrgState;
import Models.Stmts.*;
import Models.Types.*;
import Models.Values.BoolValue;
import Models.Values.IntValue;
import Models.Values.StringValue;
import Repositories.IRepository;
import Repositories.Repository;

class Interpreter {

    public static void main(String[] args) {
        //  int v; v=2; Print(v)
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        PrgState prg1 = new PrgState(ex1);
        IRepository repo1 = new Repository(prg1, "log1.txt");
        Controller ctr1 = new Controller(repo1);

        // int a; int b; a=2+3*5; b=a+1; Print(b)
        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)),
                                new ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"),
                                        new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        PrgState prg2 = new PrgState(ex2);
        IRepository repo2 = new Repository(prg2, "log2.txt");
        Controller ctr2 = new Controller(repo2);

        // bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v)
        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        PrgState prg3 = new PrgState(ex3);
        IRepository repo3 = new Repository(prg3, "log3.txt");
        Controller ctr3 = new Controller(repo3);

        // string varf; varf="test.in"; openRFile(varf); int varc; readFile(varf, varc); print(varc); readFile(varf, varc); print(varc); closeRFile(varf)
        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStmt(new openRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new closeRFile(new VarExp("varf"))))))))));
        PrgState prg4 = new PrgState(ex4);
        IRepository repo4 = new Repository(prg4, "log4.txt");
        Controller ctr4 = new Controller(repo4);

        // Ref int v; new(v,20); Ref Ref int a; new(a,v); print(v); print(a)
        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new VarExp("a")))))));
        PrgState prg5 = new PrgState(ex5);
        IRepository repo5 = new Repository(prg5, "log5.txt");
        Controller ctr5 = new Controller(repo5);

        // Ref int v; new(v,20); Ref Ref int a; new(a,v); print(rH(v)); print(rH(rH(a))+5)
        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new rH(new VarExp("v"))),
                                                new PrintStmt(new ArithExp('+', new rH(new rH(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));
        PrgState prg6 = new PrgState(ex6);
        IRepository repo6 = new Repository(prg6, "log6.txt");
        Controller ctr6 = new Controller(repo6);

        // Ref int v; new(v,20); print(rH(v)); wH(v,30); print(rH(v)+5);
        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new rH(new VarExp("v"))),
                                new CompStmt(new wH("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp('+', new rH(new VarExp("v")), new ValueExp(new IntValue(5))))))));
        PrgState prg7 = new PrgState(ex7);
        IRepository repo7 = new Repository(prg7, "log7.txt");
        Controller ctr7 = new Controller(repo7);

        // Ref int v; new(v,20); Ref Ref int a; new(a,v); new(v,30); print(rH(rH(a)))
        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new rH(new rH(new VarExp("a")))))))));
        PrgState prg8 = new PrgState(ex8);
        IRepository repo8 = new Repository(prg8, "log8.txt");
        Controller ctr8 = new Controller(repo8);

        // int v; v=4; (while (v>0) print(v); v=v-1); print(v)
        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelExp(">", new VarExp("v"), new ValueExp(new IntValue(0))),
                                new CompStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));
        PrgState prg9 = new PrgState(ex9);
        IRepository repo9 = new Repository(prg9, "log9.txt");
        Controller ctr9 = new Controller(repo9);

        // int v; Ref int a; v=10; new(a,22);
        // fork(wH(a,30); v=32; print(v); print(rH(a)));
        // print(v); print(rH(a))
        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new forkStmt(new CompStmt(new wH("a", new ValueExp(new IntValue(30))),
                                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rH(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rH(new VarExp("a")))))))));
        PrgState prg10 = new PrgState(ex10);
        IRepository repo10 = new Repository(prg10, "log10.txt");
        Controller ctr10 = new Controller(repo10);

        TextMenu menu = new TextMenu();

        try {
            ex1.typecheck(new MyDictionary<>());
            menu.addCommand(new RunExample("1", ex1.toString(), ctr1));

            ex2.typecheck(new MyDictionary<>());
            menu.addCommand(new RunExample("2", ex2.toString(), ctr2));

            ex3.typecheck(new MyDictionary<>());
            menu.addCommand(new RunExample("3", ex3.toString(), ctr3));

            ex4.typecheck(new MyDictionary<>());
            menu.addCommand(new RunExample("4", ex4.toString(), ctr4));

            ex5.typecheck(new MyDictionary<>());
            menu.addCommand(new RunExample("5", ex5.toString(), ctr5));

            ex6.typecheck(new MyDictionary<>());
            menu.addCommand(new RunExample("6", ex6.toString(), ctr6));

            ex7.typecheck(new MyDictionary<>());
            menu.addCommand(new RunExample("7", ex7.toString(), ctr7));

            ex8.typecheck(new MyDictionary<>());
            menu.addCommand(new RunExample("8", ex8.toString(), ctr8));

            ex9.typecheck(new MyDictionary<>());
            menu.addCommand(new RunExample("9", ex9.toString(), ctr9));

            ex10.typecheck(new MyDictionary<>());
            menu.addCommand(new RunExample("10", ex10.toString(), ctr10));

            menu.addCommand(new ExitCommand("0", "exit"));

            menu.show();
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}
