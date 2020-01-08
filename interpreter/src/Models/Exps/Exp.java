package Models.Exps;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Collections.MyIHeap;
import Models.Types.Type;
import Models.Values.Value;

public interface Exp {

    Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> hp) throws MyException;

    Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException;
}
