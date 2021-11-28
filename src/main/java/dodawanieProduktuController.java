import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class dodawanieProduktuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void noweOkno() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dodawanieProduktuWindow.fxml"));
        Stage newStage = new Stage();
        newStage.setTitle("Tytul");
        newStage.setScene(new Scene(root,200,200));
        newStage.show();
    }
}
