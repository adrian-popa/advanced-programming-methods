package GUI;

import Controllers.Controller;
import Exceptions.MyException;
import Models.Collections.MyIStack;
import Models.PrgState;
import Models.Stmts.IStmt;
import Models.Values.Value;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MainWindowController implements Initializable {

    @FXML
    private ListView<String> exeStackView;
    @FXML
    private TableView<Map.Entry<String, Value>> symTableView;
    @FXML
    private TableColumn<Map.Entry<String, Value>, String> symTableNames;
    @FXML
    private TableColumn<Map.Entry<String, Value>, String> symTableValues;
    @FXML
    private Label prgStatesCount;
    @FXML
    private Button exeButton;
    @FXML
    private TableView<Map.Entry<Integer, Value>> heapTableView;
    @FXML
    private TableColumn<Map.Entry<Integer, Value>, Integer> heapTableAddr;
    @FXML
    private TableColumn<Map.Entry<Integer, Value>, String> heapTableValues;
    @FXML
    private ListView<String> outView;
    @FXML
    private ListView<String> fileTableView;
    @FXML
    private ListView<Integer> prgIdentifiersView;
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
        populatePrgStatesCount();
        populateIdentifiersView();
        exeButton.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.controller = null;

        heapTableAddr.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        heapTableValues.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue() + " "));

        symTableNames.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey() + " "));
        symTableValues.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue() + " "));

        prgIdentifiersView.setOnMouseClicked(mouseEvent -> changePrgStateHandler(getSelectedPrgState()));

        exeButton.setDisable(true);
    }

    private void changePrgStateHandler(PrgState currentPrgState) {
        if (currentPrgState == null)
            return;
        try {
            populatePrgStatesCount();
            populateIdentifiersView();
            populateExeStackView(currentPrgState);
            populateSymTableView(currentPrgState);
            populateOutView(currentPrgState);
            populateFileTableView(currentPrgState);
            populateHeapTableView(currentPrgState);
        } catch (MyException e) {
            Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage());
            error.show();
        }
    }

    public void oneStepHandler(ActionEvent actionEvent) {
        if (controller == null) {
            Alert error = new Alert(Alert.AlertType.ERROR, "No program selected!");
            error.show();
            exeButton.setDisable(true);
            return;
        }

        PrgState prgState = getSelectedPrgState();

        if (prgState != null && !prgState.isNotCompleted()) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Nothing left to execute!");
            error.show();
            return;
        }

        try {
            controller.oneStep();
            changePrgStateHandler(prgState);

            if (controller.getRepo().getPrgList().size() == 0)
                exeButton.setDisable(true);
        } catch (MyException e) {
            Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage());
            error.show();
        }
    }

    private void populatePrgStatesCount() {
        prgStatesCount.setText("Number of PrgStates: " + controller.getRepo().getPrgList().size());
    }

    private void populateExeStackView(PrgState givenPrgState) throws MyException {
        MyIStack<IStmt> stk = givenPrgState.getExeStack();
        List<String> stkOutput = new ArrayList<>();

        for (IStmt stm : stk.getValues()) {
            stkOutput.add(stm.toString());
        }

        Collections.reverse(stkOutput);
        exeStackView.setItems(FXCollections.observableArrayList(stkOutput));
    }

    private void populateSymTableView(PrgState givenPrgState) throws MyException {
        symTableView.setItems(FXCollections.observableList(new ArrayList<>(givenPrgState.getSymTable().getContent().entrySet())));
        symTableView.refresh();
    }

    private void populateOutView(PrgState givenPrgState) throws MyException {
        outView.setItems(FXCollections.observableArrayList(givenPrgState.getOutList().getContent()));
    }

    private void populateFileTableView(PrgState givenPrgState) throws MyException {
        fileTableView.setItems(FXCollections.observableArrayList(givenPrgState.getFileTable().getContent().keySet()));
    }

    private void populateHeapTableView(PrgState givenPrgState) throws MyException {
        heapTableView.setItems(FXCollections.observableList(new ArrayList<>(givenPrgState.getHeap().getContent().entrySet())));
        heapTableView.refresh();
    }

    private void populateIdentifiersView() {
        prgIdentifiersView.setItems(FXCollections.observableArrayList(controller.getRepo().getPrgList().stream().map(PrgState::getId).collect(Collectors.toList())));
        prgIdentifiersView.refresh();
    }

    private PrgState getSelectedPrgState() {
        if (prgIdentifiersView.getSelectionModel().getSelectedIndex() == -1)
            return null;

        int id = prgIdentifiersView.getSelectionModel().getSelectedItem();

        return controller.getRepo().getPrgById(id);
    }
}
