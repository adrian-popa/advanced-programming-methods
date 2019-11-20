package Models.Stmts;

import Exceptions.MyException;
import Models.Exps.Exp;
import Models.PrgState;
import Models.Types.StringType;
import Models.Values.StringValue;
import Models.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements IStmt {
    private final Exp exp;

    public closeRFile(Exp exp) {
        this.exp = exp;
    }

    @Override
    public void execute(PrgState state) throws MyException, IOException {
        Value val = this.exp.eval(state.getSymTable());
        if (val.getType() instanceof StringType) {
            StringValue sv = (StringValue) val;
            String expVal = sv.getValue();
            if (state.getFileTable().isDefined(expVal)) {
                BufferedReader associatedFileDescriptor = state.getFileTable().lookup(expVal);
                associatedFileDescriptor.close();
                state.getFileTable().remove(expVal);
            } else
                throw new MyException("Filename doesn't exist!");
        } else
            throw new MyException("Expression doesn't evaluate to a string.");
    }

    @Override
    public String toString() {
        return "closeRFile(" + exp.toString() + ")";
    }
}
