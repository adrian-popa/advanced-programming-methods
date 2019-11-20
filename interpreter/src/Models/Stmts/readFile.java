package Models.Stmts;

import Exceptions.MyException;
import Models.Exps.Exp;
import Models.PrgState;
import Models.Types.IntType;
import Models.Types.StringType;
import Models.Values.IntValue;
import Models.Values.StringValue;
import Models.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements IStmt {
    private final Exp exp;
    private final String name;

    public readFile(Exp exp, String name) {
        this.exp = exp;
        this.name = name;
    }

    @Override
    public void execute(PrgState state) throws MyException, IOException {
        if (state.getSymTable().isDefined(name)) {
            if (state.getSymTable().lookup(name).getType() instanceof IntType) {
                Value val = this.exp.eval(state.getSymTable());
                if (val.getType() instanceof StringType) {
                    StringValue sv = (StringValue) val;
                    String expValue = sv.getValue();
                    if (state.getFileTable().isDefined(expValue)) {
                        BufferedReader fileDescriptor = state.getFileTable().lookup(expValue);
                        String line = fileDescriptor.readLine();
                        IntValue readValue;
                        if (line == null)
                            readValue = new IntValue(0);
                        else
                            readValue = new IntValue(Integer.parseInt(line));
                        state.getSymTable().put(name, readValue);
                    } else
                        throw new MyException("No entry associated in the file table.");
                } else
                    throw new MyException("Expression didn't evaluate to a string.");
            } else
                throw new MyException("Associated value type is not int.");
        } else
            throw new MyException("Variable name is not defined in the symbol table.");
    }

    @Override
    public String toString() {
        return "readFile(" + exp.toString() + ", " + name + ")";
    }
}
