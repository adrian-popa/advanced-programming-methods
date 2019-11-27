package Models.Exps;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Collections.MyIHeap;
import Models.Values.Value;

public interface Exp {

    Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> hp) throws MyException;
}
