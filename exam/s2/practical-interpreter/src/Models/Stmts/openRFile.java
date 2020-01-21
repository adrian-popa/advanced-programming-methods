package Models.Stmts;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Exps.Exp;
import Models.PrgState;
import Models.Types.BoolType;
import Models.Types.StringType;
import Models.Types.Type;
import Models.Values.StringValue;
import Models.Values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class openRFile implements IStmt {

    private final Exp exp;

    public openRFile(Exp exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        Value val = this.exp.eval(state.getSymTable(), state.getHeap());
        if (val.getType() instanceof StringType) {
            StringValue sv = (StringValue) val;
            String expVal = sv.getValue();
            if (!state.getFileTable().isDefined(expVal)) {
                BufferedReader fileDescriptor = new BufferedReader(new FileReader(expVal));
                state.getFileTable().put(expVal, fileDescriptor);
            } else
                throw new MyException("Filename already exists.");
        } else
            throw new MyException("Expression doesn't evaluate to a string.");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp = exp.typecheck(typeEnv.clone());
        if (typexp.equals(new StringType()))
            return typeEnv;
        else
            throw new MyException("openRFile: expression type is not a string");
    }

    @Override
    public String toString() {
        return "openRFile(" + exp.toString() + ")";
    }
}
