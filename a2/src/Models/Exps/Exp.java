package Models.Exps;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Values.Value;

public interface Exp {

    Value eval(MyIDictionary<String, Value> tbl) throws MyException;
}
