package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {
    @FXML
    Button btnSend;
    @FXML
    TextArea txtAreaMsg;
    @FXML
    TextArea txtAreaChat;

    StringBuilder msg = new StringBuilder();

    @FXML
    public void initialize() {
        txtAreaMsg.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                btnSend.setDisable(observable.getValue().isEmpty());
            }
        });
    }

    public void btnClicked(ActionEvent actionEvent) {
        sendMessage();
    }

    public void txtAreaSendMsg(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            keyEvent.consume();
            sendMessage();
        }
    }

    public void sendMessage() {
        if (!txtAreaMsg.getText().isEmpty()) {
            msg.append("[You]: ").append(txtAreaMsg.getText()).append("\n");
            txtAreaMsg.clear();

            txtAreaChat.setText(msg.toString());
            txtAreaChat.setScrollTop(Double.MAX_VALUE);
        }
    }
}
