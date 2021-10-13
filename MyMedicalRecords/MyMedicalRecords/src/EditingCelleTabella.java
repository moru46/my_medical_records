import java.awt.event.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class EditingCelleTabella extends TableCell<Prestazioni, String> {
private TextField textField;

public EditingCelleTabella() {} 

@Override
public void startEdit() {//2
    super.startEdit();

    if( textField == null ) {
        createTextField();
    }
    setText(null);
    setGraphic(textField);
    textField.selectAll();
}

@Override
public void cancelEdit() {//2
    super.cancelEdit();
    setText((String) getItem());
    setGraphic(null);
}

@Override
public void updateItem(String item, boolean empty) { //3
    super.updateItem(item, empty);
    if (empty) {
        setText(null);
        setGraphic(null);
    } else {
        if (isEditing()) {
            if (textField != null) {
                textField.setText(getString());
            }
            setText(null);
            setGraphic(textField);
        } else {
            setText(getString());
            setGraphic(null);
        }
    }
}


public void createTextField() { //4
    textField = new TextField(getString());
    textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
    textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
        public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
            if (!arg2) { commitEdit(textField.getText()); }
        }
    });

  textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent t) {
            if (t.getCode() == KeyCode.ENTER) {
                commitEdit(textField.getText());
               // String value = textField.getText();
                //if (value != null) { commitEdit(value); } 
                   // else { commitEdit(null); }
            } else if (t.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        }
    });
}

public String getString() {
    return getItem() == null ? "" : getItem().toString();
}
}


/*
2)motodo per iniziare o terminare l'editing di una cella prima di passare alla successiva
3)metodo per l'aggiornamento della cella se essa è stata modificata
4)medoto utilizzato per confermare o meno l'editing di una cella, aggiungendo un key event legato all'editing della cella
*/