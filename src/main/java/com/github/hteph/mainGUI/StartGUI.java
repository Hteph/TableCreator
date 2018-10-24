package com.github.hteph.mainGUI;
/**
 * @author Mikael Hansson {@literal <mailto:mikael.hansson@so4it.com/>}
 */

import com.github.hteph.TableLoader.DirectoryLoader;
import com.github.hteph.domain.TableArchive;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StartGUI extends Application {

    @Override
    public void start(Stage primaryStage) {

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Tables Utilities");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File selectedDirectory = chooser.showDialog(primaryStage);

        DirectoryLoader.collectAllFiles(selectedDirectory.getPath());

        Scene scene = getPeopleScene();

        //Setting title to the Stage
        primaryStage.setTitle("Sample Application");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private Scene getPeopleScene() {
        //Creating a Text object
        Text text = new Text();

        StringBuilder baseText=new StringBuilder("A People\n");
        baseText.append(TableArchive.getTable("Demeanour").getRandomLine()).append("\n");
        baseText.append(TableArchive.getTable("Skin appearance").getRandomLine()).append("\n");
        baseText.append(TableArchive.getTable("Skin colour patterns").getRandomLine()).append("\n");
        baseText.append(TableArchive.getTable("Hair colour").getRandomLine()).append("\n");
        baseText.append(TableArchive.getTable("Body shape").getRandomLine()).append("\n");
        baseText.append(TableArchive.getTable("Arms").getRandomLine()).append("\n");
        baseText.append(TableArchive.getTable("Arm resemblance").getRandomLine()).append("\n");
        baseText.append(TableArchive.getTable("Hand appearance").getRandomLine()).append("\n");
        baseText.append(TableArchive.getTable("Ear quantity").getRandomLine()).append("\n");
        baseText.append(TableArchive.getTable("Eye quantity").getRandomLine()).append("\n");


        //Setting the text to be added.
        text.setText(baseText.toString());

        //setting the position of the text
        text.setX(50);
        text.setY(50);

        //Creating a Group object
        Group root = new Group(text);

        //Creating a scene object
        return new Scene(root, 600, 300);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
