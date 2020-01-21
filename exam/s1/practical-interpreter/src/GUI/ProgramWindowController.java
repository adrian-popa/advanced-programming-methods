package GUI;

import Controllers.Controller;
import Exceptions.MyException;
import Models.Collections.MyDictionary;
import Models.Exps.ArithExp;
import Models.Exps.ValueExp;
import Models.Exps.VarExp;
import Models.Exps.rH;
import Models.PrgState;
import Models.Stmts.*;
import Models.Types.BoolType;
import Models.Types.IntType;
import Models.Types.RefType;
import Models.Values.BoolValue;
import Models.Values.IntValue;
import Models.Values.RefValue;
import Repositories.IRepository;
import Repositories.Repository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProgramWindowController implements Initializable {
    @FXML
    private Button selectBtn;
    @FXML
    private ListView<IStmt> selectPrgListView;

    private MainWindowController mainWindowController;

    public MainWindowController getMainWindowController() {
        return mainWindowController;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    @FXML
    private IStmt selectProgram(ActionEvent actionEvent) {
        return selectPrgListView.getItems().get(selectPrgListView.getSelectionModel().getSelectedIndex());
    }

    private List<IStmt> initPrograms() {
        List<IStmt> list = new ArrayList<>();

        // Ref int v1; int cnt; new(v1,2); newSemaphore(cnt,rH(v1),1);
        // fork(acquire(cnt); wh(v1,rh(v1)*10)); print(rh(v1)); release(cnt));
        // fork(acquire(cnt); wh(v1,rh(v1)*10)); wh(v1,rh(v1)*2)); print(rh(v1)); release(cnt));
        // acquire(cnt); print(rh(v1)-1); release(cnt)
        IStmt ex1 = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("cnt", new IntType()),
                        new CompStmt(new NewStmt("v1", new ValueExp(new IntValue(2))),
                                new CompStmt(new NewSemaphoreStmt(new VarExp("cnt"), new rH(new VarExp("v1")), new ValueExp(new IntValue(1))),
                new CompStmt(new forkStmt(new AcquireStmt(new VarExp("cnt"))),
                        new CompStmt(new wH("v1", new ArithExp('*', new rH(new VarExp("v1")), new ValueExp(new IntValue(10)))),
                                new CompStmt(new PrintStmt(new rH(new VarExp("v1"))),
                                        new CompStmt(new ReleaseStmt(new VarExp("cnt")),
                new CompStmt(new forkStmt(new AcquireStmt(new VarExp("cnt"))),
                        new CompStmt(new wH("v1", new ArithExp('*', new rH(new VarExp("v1")), new ValueExp(new IntValue(10)))),
                                new CompStmt(new wH("v1", new ArithExp('*', new rH(new VarExp("v1")), new ValueExp(new IntValue(2)))),
                                        new CompStmt(new PrintStmt(new rH(new VarExp("v1"))),
                                                new CompStmt(new ReleaseStmt(new VarExp("cnt")),
                                                        new CompStmt(new AcquireStmt(new VarExp("cnt")),
                                                                new CompStmt(new PrintStmt(new ArithExp('-', new rH(new VarExp("v1")), new ValueExp(new IntValue(1)))),
                                                                        new ReleaseStmt(new VarExp("cnt")))))))))))))))));

        // bool b; int c; b=true; c=b?100:200; print(c); c=(false)?100:200; print(c);
        IStmt ex2 = new CompStmt(new VarDeclStmt("b", new BoolType()),
                new CompStmt(new VarDeclStmt("c", new IntType()),
                        new CompStmt(new AssignStmt("b", new ValueExp(new BoolValue(true))),
                                new CompStmt(new CondAssignStmt("c", new VarExp("b"), new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200))),
                                        new CompStmt(new PrintStmt(new VarExp("c")),
                                                new CompStmt(new CondAssignStmt("c", new ValueExp(new BoolValue(false)), new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200))), new PrintStmt(new VarExp("c"))))))));

        list.add(ex1);
        list.add(ex2);

        return list;
    }

    private void displayPrograms() {
        List<IStmt> programs = initPrograms();

        selectPrgListView.setItems(FXCollections.observableArrayList(programs));
        selectPrgListView.getSelectionModel().select(0);

        selectBtn.setOnAction(actionEvent -> {
            int index = selectPrgListView.getSelectionModel().getSelectedIndex();
            IStmt selectedPrg = selectPrgListView.getItems().get(index);
            index += 1;

            PrgState prg = new PrgState(selectedPrg);
            IRepository repo = new Repository(prg, "log" + index + ".txt");
            Controller ctr = new Controller(repo);

            mainWindowController.setController(ctr);

//            try {
//                selectedPrg.typecheck(new MyDictionary<>());
//                mainWindowController.setController(ctr);
//            } catch (MyException e) {
//                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
//                alert.show();
//            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayPrograms();
    }
}
