/**
 * Hrishikesh Moholkar
 */

    import javafx.application.Application;
    import javafx.collections.ObservableList;
    import javafx.geometry.Insets;
    import javafx.geometry.Pos;
    import javafx.scene.Node;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.layout.BorderPane;
    import javafx.scene.layout.GridPane;
    import javafx.scene.layout.VBox;
    import javafx.scene.paint.Color;
    import javafx.scene.shape.Rectangle;
    import javafx.scene.text.Font;
    import javafx.scene.text.Text;
    import javafx.stage.Stage;

    import java.util.ArrayList;
    import java.util.Observable;
    import java.util.Observer;


    public class MastermindGraphicalVC extends Application implements Observer {
        ArrayList<Rectangle>new1;
        ArrayList<Rectangle>new2;
        private GridPane mainGrid;
        private GridPane grid1;
        ArrayList<Character> solutionclue1;
        ArrayList<Integer>solution;
        ArrayList<Integer>guesses;
        ArrayList<Rectangle>options;
        private int maxGuesses;
        private MastermindModel model;
        Rectangle b;
        Rectangle solb;
        Button Peek;
        Button guess;
        Button Reset;
        Text textfield;
        GridPane solutionarray;
        GridPane gridPeg;
        ArrayList<ArrayList<Rectangle>> allButtons;

        public void start(Stage primarystage) throws Exception{
            allButtons = new ArrayList<>();
            maxGuesses = model.MAX_GUESSES;
            primarystage.setTitle("Mastermind");
            BorderPane background=new BorderPane();

            makeGrid();
            background.setCenter(this.mainGrid);
            background.setLeft(this.peg());

            VBox container=new VBox();
            Reset=new Button("Reset");
            Reset.setOnAction(event->this.model.reset());

            Peek=new Button("Peek/Unpeek");
            Peek.setOnAction(event -> this.model.peek());

            guess=new Button("Guess");
            guess.setOnAction(event->this.model.makeGuess());

            container.getChildren().addAll(Reset,Peek,guess);

            background.setRight(container);

            Label alabel = new Label( "Mastermind" );
            alabel.setFont( new Font( "Helvetica", 50 ) );
            alabel.setAlignment( Pos.CENTER );
            background.setTop(alabel);

            textfield=new Text("You have "+""+this.model.getRemainingGuesses()+" "+"guesses remaining");
            textfield.setFont(new Font( "Helvetica", 20 ));
            background.setBottom(textfield);

            /**
            solutionarray=new GridPane();
            for (int i = 0; i < solutionclue1.size(); i++) {
                if (solutionclue1.get(i)=='R'){
                    solb=new Rectangle(20,20, Color.RED);
                    solutionarray.add(solb,i,0);
                   // solb.setOnMouseClicked(event ->model.getClueData() );
                }
                else if(solutionclue1.get(i)=='B'){
                    solb=new Rectangle(20,20,Color.BLUE);
                    solutionarray.add(solb,i,0);
                   // solb.setOnMouseClicked(event ->model.getClueData() );
                }

                else if(solutionclue1.get(i)=='G'){
                    solb=new Rectangle(20,20,Color.GREEN);
                    solutionarray.add(solb,i,0);
                  //  solb.setOnMouseClicked(event ->model.getClueData() );
                }

                else if(solutionclue1.get(i)=='Y') {
                    solb = new Rectangle(20, 20, Color.YELLOW);
                    solutionarray.add( solb,i,0);
                  //  solb.setOnMouseClicked(event ->model.getClueData() );
                }
                else if(solutionclue1.get(i)=='W'){
                    solb=new Rectangle(20,20,Color.WHITE);
                    solutionarray.add(solb,i,0);
                  //  solb.setOnMouseClicked(event ->model.getClueData() );
                }
                else if(solutionclue1.get(i)=='B'){
                    solb=new Rectangle(20,20,Color.BLUE);
                    solutionarray.add(solb,i,0);
                   // solb.setOnMouseClicked(event ->model.getClueData() );
                }



            }

            background.setBottom(solutionarray);
            **/




            primarystage.setScene(new Scene(background));
            primarystage.show();




        }
        private void makeGrid(){
            this.mainGrid = new GridPane();
            mainGrid.setVgap( 10 ); // gap between grid cells
            mainGrid.setHgap( 10 );
            mainGrid.setPadding( new Insets( 5 ) );
            //int curRow = model.get
            for ( int row = 1; row <=11; row++ ) {
                for (int col = 1; col <= 4; col++) {
                    Rectangle b;

                    b = new Rectangle(50, 50, Color.GREY);

                    final int finalRow = row;
                    final int finalCol = col;
                    b.setOnMouseClicked((event)-> model.choose(finalRow, finalCol));

                    mainGrid.add(b,finalCol,11-finalRow);
                }
            }





        }

        private GridPane peg(){
            GridPane grid1=new GridPane();
            grid1.setVgap( 10 ); // gap between grid cells
            grid1.setHgap( 10 );
            grid1.setPadding( new Insets( 5 ) );
            for (int row = 0; row <20 ; row++) {

                for(int col=0;col<2;col++){

                    b=new Rectangle(20,20,Color.GREY);
                    grid1.add(b,col,20-row);
                }

            }
            grid1.setAlignment(Pos.BOTTOM_CENTER);
            gridPeg = grid1;
            return grid1;
        }




        public void init() {
            this.model = new MastermindModel();
            model.addObserver(this);
            solutionclue1 = this.model.getClueData();
            solution = this.model.getSolution();
            guesses = this.model.getGuessData();

        }




        public static void main(String[] args){
            launch(args);
        }

        public void update(Observable o , Object arg) {

               ObservableList<Node> children = mainGrid.getChildren();
            ArrayList guessData = model.getGuessData();

            for (int i = 0; i < 40; i++) {
                Rectangle child = (Rectangle) children.get(i);
                final Integer colorNum = (int) guessData.get(i);
                switch (colorNum) {
                    case 0:
                        child.setFill(Color.GRAY);
                        break;
                    case 1:
                        child.setFill(Color.RED);
                        break;
                    case 2:
                        child.setFill(Color.GREEN);
                        break;
                    case 3:
                        child.setFill(Color.YELLOW);
                        break;
                    case 4:
                        child.setFill(Color.BLUE);
                        break;
                    case 5:
                        child.setFill(Color.WHITE);
                        break;
                    case 6:
                        child.setFill(Color.BLACK);
                        break;
                }
            }

                ArrayList<Integer> solution1 = this.model.getSolution();
                //System.out.println(solution1);
            for (int i = 0; i < solution1.size(); i++) {
                    if (solution1.get(i) == 1) {
                        Rectangle rect = (Rectangle) children.get(40+i);
                        rect.setFill(Color.RED);
                    } else if (solution1.get(i) == 2) {
                        Rectangle rect = (Rectangle) children.get(40+i);
                        rect.setFill(Color.GREEN);
                    } else if (solution1.get(i) == 3) {
                        Rectangle rect = (Rectangle) children.get(40+i);
                        rect.setFill(Color.YELLOW);
                    } else if (solution1.get(i) == 4) {
                        Rectangle rect = (Rectangle) children.get(40+i);
                        rect.setFill(Color.BLUE);
                    } else if (solution1.get(i) == 5) {
                        Rectangle rect = (Rectangle) children.get(40+i);
                        rect.setFill(Color.WHITE);
                    } else if (solution1.get(i) == 6) {
                        Rectangle rect = (Rectangle) children.get(40+i);
                        rect.setFill(Color.BLACK);
                    }else {
                        Rectangle rect = (Rectangle) children.get(40+i);
                        rect.setFill(Color.GRAY);
                    }
            }

            ObservableList<Node> pegchildern = gridPeg.getChildren();
            ArrayList<Character> cluesdata = model.getClueData();
            for (int i=0; i<40;i++ ){
                Rectangle pchild = (Rectangle) pegchildern.get(i);
                char clue = cluesdata.get(i);
                if(clue=='W'){
                    pchild.setFill(Color.WHITE);
                }
                else if(clue=='B'){
                    pchild.setFill(Color.BLACK);
                }
                else{
                    pchild.setFill(Color.GREY);
                }
            }




        }
    }






