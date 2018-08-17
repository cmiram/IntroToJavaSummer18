import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/***
 * This class opens a gui prompting the user to choose options for a pizza
 * and displays a dynamically changing price depending on current options chosen
 */

public class PizzaBuilderGui extends Application
{
    private ButtonBar sizeBar;

    private RadioButton largeBtn;

    private RadioButton mediumBtn;

    private RadioButton smallBtn;

    private ButtonBar toppingsBar;

    private Text totalCost;

    /***
     * Entry point of the program
     * @param primaryStage main stage of fxml
     */
    @Override
    public void start(Stage primaryStage) {
        // base pane to add all gui elements to
        BorderPane base = new BorderPane();
        
        // pane to hold buttons related to pizza size
        StackPane topPane = new StackPane();
        topPane.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        topPane.setMaxHeight(75);
        topPane.setAlignment(Pos.CENTER);
    
        // create header for size bar
        TextArea sizeHeader = new TextArea("Pizza Size");
        sizeHeader.setFont(new Font("System Bold", 14.0));
        
        // bar to hold buttons selecting pizza size
        sizeBar = new ButtonBar();
        sizeBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        sizeBar.setPrefHeight(80);
        sizeBar.setStyle("-fx-border-color: grey; -fx-border-width: 1;");
    
        // setup 3 buttons for each size and set action when clicked
        largeBtn = new RadioButton();
        largeBtn.setMnemonicParsing(false);
        largeBtn.setOnAction(this::onSizeSelected);
        largeBtn.setText("Large");
    
        mediumBtn = new RadioButton();
        mediumBtn.setMnemonicParsing(false);
        mediumBtn.setOnAction(this::onSizeSelected);
        mediumBtn.setText("Medium");
        
        smallBtn = new RadioButton();
        // set this one to always be selected to start
        smallBtn.setSelected(true);
        smallBtn.setMnemonicParsing(false);
        smallBtn.setOnAction(this::onSizeSelected);
        smallBtn.setText("Small");
    
        // add buttons to bar then add bar to base pane
        sizeBar.getButtons().addAll(largeBtn, mediumBtn, smallBtn);
        topPane.getChildren().addAll(sizeHeader, sizeBar);
        base.setTop(topPane);
    
        // pane to hold items related to pizza toppings
        StackPane centerPane = new StackPane();
        centerPane.setMaxHeight(75);
        centerPane.setAlignment(Pos.CENTER);
    
        // header for pane
        TextArea toppingsHeader = new TextArea("Toppings");
        toppingsHeader.setFont(new Font("System Bold", 14));
        toppingsHeader.setPrefHeight(72);
        
        //bar to hold buttons allowing toppings to be chosen
        toppingsBar = new ButtonBar();
        toppingsBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        toppingsBar.setPrefHeight(40);
        toppingsBar.setStyle("-fx-border-color: grey; -fx-border-width: 1;");
    
        // 4 buttons for each topping choice
        // on clicked recalculate price for new pizza combo
        CheckBox pepperoniBtn = new CheckBox();
        pepperoniBtn.setOnAction(e -> calculatePrice());
        pepperoniBtn.setMnemonicParsing(false);
        pepperoniBtn.setText("Pepperoni");
    
        CheckBox sausageBtn = new CheckBox();
        sausageBtn.setOnAction(e -> calculatePrice());
        sausageBtn.setMnemonicParsing(false);
        sausageBtn.setText("Sausage");
    
        CheckBox mushroomBtn = new CheckBox();
        mushroomBtn.setOnAction(e -> calculatePrice());
        mushroomBtn.setMnemonicParsing(false);
        mushroomBtn.setText("Mushroom");
    
        CheckBox plainBtn = new CheckBox();
        plainBtn.setOnAction(e -> calculatePrice());
        plainBtn.setMnemonicParsing(false);
        plainBtn.setText("Plain");
        // always have this one selected to start
        plainBtn.setSelected(true);
    
        // add buttons to bar then add bar to base pane
        toppingsBar.getButtons().addAll(pepperoniBtn, sausageBtn,
                mushroomBtn, plainBtn);
        centerPane.getChildren().addAll(toppingsHeader, toppingsBar);
        base.setCenter(centerPane);
    
        // bar to hold cost, using bar to allow common styling to all panes
        ToolBar costBar = new ToolBar();
        costBar.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        costBar.setPrefHeight(30);
        costBar.setStyle("-fx-border-color: grey; -fx-border-width: 1;");
        
        // text prompt displaying pizza cost of currently selected combo
        totalCost = new Text("Price: $5.00");
        totalCost.setTextAlignment(TextAlignment.CENTER);
        costBar.getItems().add(totalCost);
        base.setBottom(costBar);
        
        // add base pane to main stage
        primaryStage.setScene(new Scene(base, 360, 220));
        // set minimum size of gui to prevent elements overlapping
        // or going out of sight
        primaryStage.setMinHeight(220);
        primaryStage.setMinWidth(360);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /***
     * unselects everything but selected and recalculates the price
     */
    private void onSizeSelected(ActionEvent e)
    {
        sizeBar.getButtons().forEach(button -> ((RadioButton) button)
                .setSelected(((RadioButton) button).getText()
                        .equals(((RadioButton) e.getSource()).getText())));
        calculatePrice();
    }

    /***
     * calculates the price of selected pizza and updates the view
     */
    private void calculatePrice()
    {
        double price = 0;
        double toppingModifier;
        // find selected size and grab text of that button
        // guaranteed one and only one size is selected at a time
        String sizeStr = "";
        for(Node button : sizeBar.getButtons()) {
            if(((RadioButton) button).selectedProperty().getValue()) {
                sizeStr = ((RadioButton) button).getText();
                break;
            }
        }

        // add base price of size and set modifier of individual toppings price
        switch(sizeStr)
        {
        case "Large":
            price += 12;
            toppingModifier = 1.5;
            break;
        case "Medium":
            price += 8;
            toppingModifier = 1;
            break;
        case "Small":
            price += 5;
            toppingModifier = 0.5;
            break;
        default:
            throw new IllegalStateException("Unknown size is selected");
        }

        // count selected toppings while skipping plain at end of list
        double numToppings = 0;
        for(int i=0; i<toppingsBar.getButtons().size()-1; i++) {
            if(((CheckBox) toppingsBar.getButtons().get(i))
                    .selectedProperty().getValue()) {
                numToppings += 1;
            }
        }

        // set cost to base price plus topping modifier times number chosen
        totalCost.textProperty().set(String.format("Price: $%.2f",
                price + (numToppings * toppingModifier)));
    }

}
