package GUI;

import Controllers.Controller;
import Models.Exps.ArithExp;
import Models.Exps.ValueExp;
import Models.Exps.VarExp;
import Models.Exps.rH;
import Models.PrgState;
import Models.Stmts.*;
import Models.Types.IntType;
import Models.Types.RefType;
import Models.Values.IntValue;
import Repositories.IRepository;
import Repositories.Repository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

        // Ref int v1; Ref int v2; Ref int v3;
        // new(v1,2); new(v2,3); new(v3,4); newLatch(cnt,rH(v2));
        // fork(wh(v1,rh(v1)*10)); print(rh(v1)); countDown(cnt);
        //     fork(wh(v2,rh(v2)*10)); print(rh(v2)); countDown(cnt);
        //         fork(wh(v3,rh(v3)*10)); print(rh(v3)); countDown(cnt))));
        // await(cnt);
        // print(100);
        // countDown(cnt);
        // print(100);
        IStmt ex1 = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("v2", new RefType(new IntType())),
                        new CompStmt(new VarDeclStmt("v3", new RefType(new IntType())),
                                new CompStmt(new NewStmt("v1", new ValueExp(new IntValue(2))),
                                        new CompStmt(new NewStmt("v2", new ValueExp(new IntValue(3))),
                                                new CompStmt(new NewStmt("v3", new ValueExp(new IntValue(4))),
                                                        new CompStmt(new NewLatchStmt("cnt", new rH(new VarExp("v2"))),
                new CompStmt(new forkStmt(new CompStmt(new wH("v1", new ArithExp('*', new rH(new VarExp("v1")), new ValueExp(new IntValue(10)))),
                        new CompStmt(new PrintStmt(new rH(new VarExp("v1"))),
                                new CompStmt(new CountDownStmt("cnt"),
                                        new forkStmt(new CompStmt(new wH("v2", new ArithExp('*', new rH(new VarExp("v2")), new ValueExp(new IntValue(10)))),
                                                new CompStmt(new PrintStmt(new rH(new VarExp("v2"))),
                                                        new CompStmt(new CountDownStmt("cnt"),
                                                                new forkStmt(new CompStmt(new wH("v3", new ArithExp('*', new rH(new VarExp("v3")), new ValueExp(new IntValue(10)))),
                                                                        new CompStmt(new PrintStmt(new rH(new VarExp("v3"))), new CountDownStmt("cnt")))))))))))),
                        new CompStmt(new AwaitStmt("cnt"),
                                new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))),
                                        new CompStmt(new CountDownStmt("cnt"), new PrintStmt(new ValueExp(new IntValue(100))))))))))))));

        list.add(ex1);

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
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayPrograms();
    }
}
