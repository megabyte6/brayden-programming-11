// I know that this is not following typical coding conventions but when I
// tried to use a for loop, the GridPane.getChildren() kept giving me an
// ObservableList of null nodes

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Easy_Controller {
    // Set to true for debugging info
    private Random random = new Random();

    private boolean gameInitialized = false;
    volatile boolean gamePaused = true;
    public int timerDuration = 10;
    private int turnsTaken = 0;

    // Keeps a list of numbers called to check player's choices later
    // store as an ArrayList of Strings for comparisons later
    private ArrayList<String> numbersCalled = new ArrayList<String>();

    // Goes [column][row]
    private int[][] playerCellValues = new int[5][5];
    private int[][] computerCellValues = new int[5][5];
    private boolean[][] playerCellStates = new boolean[5][5];
    private boolean[][] computerCellStates = new boolean[5][5];

    // Components in the UI
    @FXML
    Button button_back;
    @FXML
    Button button_overlay;
    @FXML
    Button button_bingo;
    @FXML
    AnchorPane anchorPane_root;
    @FXML
    GridPane gridPane_root;

    @FXML
    Label label_number;
    @FXML
    Label label_timer;

    // All of the cells in the GridPane
    // See line 1 for more information
    @FXML
    Label player_0_0;
    @FXML
    Label player_0_1;
    @FXML
    Label player_0_2;
    @FXML
    Label player_0_3;
    @FXML
    Label player_0_4;
    @FXML
    Label player_1_0;
    @FXML
    Label player_1_1;
    @FXML
    Label player_1_2;
    @FXML
    Label player_1_3;
    @FXML
    Label player_1_4;
    @FXML
    Label player_2_0;
    @FXML
    Label player_2_1;
    @FXML
    Label player_free;
    @FXML
    Label player_2_3;
    @FXML
    Label player_2_4;
    @FXML
    Label player_3_0;
    @FXML
    Label player_3_1;
    @FXML
    Label player_3_2;
    @FXML
    Label player_3_3;
    @FXML
    Label player_3_4;
    @FXML
    Label player_4_0;
    @FXML
    Label player_4_1;
    @FXML
    Label player_4_2;
    @FXML
    Label player_4_3;
    @FXML
    Label player_4_4;

    @FXML
    Label computer_0_0;
    @FXML
    Label computer_0_1;
    @FXML
    Label computer_0_2;
    @FXML
    Label computer_0_3;
    @FXML
    Label computer_0_4;
    @FXML
    Label computer_1_0;
    @FXML
    Label computer_1_1;
    @FXML
    Label computer_1_2;
    @FXML
    Label computer_1_3;
    @FXML
    Label computer_1_4;
    @FXML
    Label computer_2_0;
    @FXML
    Label computer_2_1;
    @FXML
    Label computer_free;
    @FXML
    Label computer_2_3;
    @FXML
    Label computer_2_4;
    @FXML
    Label computer_3_0;
    @FXML
    Label computer_3_1;
    @FXML
    Label computer_3_2;
    @FXML
    Label computer_3_3;
    @FXML
    Label computer_3_4;
    @FXML
    Label computer_4_0;
    @FXML
    Label computer_4_1;
    @FXML
    Label computer_4_2;
    @FXML
    Label computer_4_3;
    @FXML
    Label computer_4_4;

    // Use to open a new window
    public Stage openWindow(String fxmlFile, String titleName, Stage currentStage) {
        // Close old window
        currentStage.close();

        // Create a new stage
        Stage newStage = new Stage();
        newStage.setTitle(titleName);

        // Try to get fxml file for UI
        try {
            newStage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlFile))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Show the new stage
        newStage.show();

        return newStage;
    }

    // Toggle full screen state
    public void setFullScreen() {
        Stage currentStage = (Stage) anchorPane_root.getScene().getWindow();
        if (currentStage.isFullScreen()) {
            currentStage.setFullScreen(false);
        } else {
            currentStage.setFullScreen(true);
        }
    }

    // Set full screen state
    public void setFullScreen(boolean fullScreen) {
        Stage currentStage = (Stage) anchorPane_root.getScene().getWindow();
        currentStage.setFullScreen(fullScreen);
    }

    // Set the timer duration
    public void setTimerDuration(int duration) {
        this.timerDuration = duration;
    }

    private void setPause(boolean pause) {
        if (pause) {
            gridPane_root.setDisable(true);
            button_overlay.setDisable(false);
            button_overlay.setVisible(true);
        } else {
            gridPane_root.setDisable(false);
            button_overlay.setDisable(true);
            button_overlay.setVisible(false);
        }
        this.gamePaused = pause;
    }

    private int randomNum(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    // Generate random values for letter and number
    // Returns a letter and a number separated by a space
    private String generateRandomNumberLetter() {
        String result = "";
        int letter = 0;
        int randomNumber = 0;

        // Loop until new number
        do {
            // Generate random number
            // add it to the list of called numbers
            // return the letter-number combo
            switch (random.nextInt(5)) {
                case 0:
                    letter = 0;
                    randomNumber = randomNum(1, 20);
                    numbersCalled.add(0 + " " + randomNumber);
                    result += "B " + randomNumber;
                    break;
                case 1:
                    letter = 1;
                    randomNumber = randomNum(20, 40);
                    numbersCalled.add(1 + " " + randomNumber);
                    result += "I " + randomNumber;
                    break;
                case 2:
                    letter = 2;
                    randomNumber = randomNum(40, 60);
                    numbersCalled.add(2 + " " + randomNumber);
                    result += "N " + randomNumber;
                    break;
                case 3:
                    letter = 3;
                    randomNumber = randomNum(60, 80);
                    numbersCalled.add(3 + " " + randomNumber);
                    result += "G " + randomNumber;
                    break;
                case 4:
                    letter = 4;
                    randomNumber = randomNum(80, 99);
                    numbersCalled.add(4 + " " + randomNumber);
                    result += "O " + randomNumber;
                    break;
            }
        } while (numbersCalled.contains(letter + " " + randomNumber));

        return result;
    }

    // Returns an int of num of occurrences
    private int existsInArray(int[][] array, int value) {
        int numOfOccurrences = 0;
        for (int[] row : array) {
            for (int item : row) {
                if (item == value) {
                    numOfOccurrences++;
                }
            }
        }
        return numOfOccurrences;
    }

    // Check the user's choices
    // Returns: 1 if user won, otherwise return 0
    private int checkCard(String player) {
        /*
        Table of IDs in each card

        00|10|20|30|40
        01|11|21|31|41
        02|12|22|32|42
        03|13|23|33|34
        04|14|24|34|44
        */

        // List of coordinates
        // store as an ArrayList of Strings for comparisons later
        ArrayList<String> coordinates = new ArrayList<String>();

        // Create a HashMap to store the occurences of each number
        HashMap<String, Integer> occurrences = new HashMap<String, Integer>();
        occurrences.put("sumToFour", 0);
        occurrences.put("doubleNum", 0);
        occurrences.put("col_0", 0);
        occurrences.put("col_1", 0);
        occurrences.put("col_2", 0);
        occurrences.put("col_3", 0);
        occurrences.put("col_4", 0);
        occurrences.put("row_0", 0);
        occurrences.put("row_1", 0);
        occurrences.put("row_2", 0);
        occurrences.put("row_3", 0);
        occurrences.put("row_4", 0);
        
        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 5; row++) {
                // If current cell is selected, add its position to the list of
                // selected cells
                if (player.equals("player")) {
                    if (playerCellStates[col][row] == true) {
                        // Add selected cells to a list of positions
                        coordinates.add(col + " " + row);

                        if (col == row) {
                            occurrences.put("doubleNum", occurrences.get("doubleNum") + 1);
                        } else if (col + row == 4) {
                            occurrences.put("sumToFour", occurrences.get("sumToFour") + 1);
                        }
                        
                        // Check col
                        switch (col) {
                            case 0:
                                occurrences.put("col_0", occurrences.get("col_0") + 1);
                                break;
                            case 1:
                                occurrences.put("col_1", occurrences.get("col_1") + 1);
                                break;
                            case 2:
                                occurrences.put("col_2", occurrences.get("col_2") + 1);
                                break;
                            case 3:
                                occurrences.put("col_3", occurrences.get("col_3") + 1);
                                break;
                            case 4:
                                occurrences.put("col_4", occurrences.get("col_4") + 1);
                        }

                        // Check row
                        switch (row) {
                            case 0:
                                occurrences.put("row_0", occurrences.get("row_0") + 1);
                                break;
                            case 1:
                                occurrences.put("row_1", occurrences.get("row_1") + 1);
                                break;
                            case 2:
                                occurrences.put("row_2", occurrences.get("row_2") + 1);
                                break;
                            case 3:
                                occurrences.put("row_3", occurrences.get("row_3") + 1);
                                break;
                            case 4:
                                occurrences.put("row_4", occurrences.get("row_4") + 1);
                        }
                    }

                } else if (player.equals("computer")) {
                    if (computerCellStates[col][row] == true) {
                        // Add selected cells to a list of positions
                        coordinates.add(col + " " + row);

                        if (col == row) {
                            occurrences.put("doubleNum", occurrences.get("doubleNum") + 1);
                        } else if (col + row == 4) {
                            occurrences.put("sumToFour", occurrences.get("sumToFour") + 1);
                        }
                        
                        // Check col
                        switch (col) {
                            case 0:
                                occurrences.put("col_0", occurrences.get("col_0") + 1);
                                break;
                            case 1:
                                occurrences.put("col_1", occurrences.get("col_1") + 1);
                                break;
                            case 2:
                                occurrences.put("col_2", occurrences.get("col_2") + 1);
                                break;
                            case 3:
                                occurrences.put("col_3", occurrences.get("col_3") + 1);
                                break;
                            case 4:
                                occurrences.put("col_4", occurrences.get("col_4") + 1);
                        }

                        // Check row
                        switch (row) {
                            case 0:
                                occurrences.put("row_0", occurrences.get("row_0") + 1);
                                break;
                            case 1:
                                occurrences.put("row_1", occurrences.get("row_1") + 1);
                                break;
                            case 2:
                                occurrences.put("row_2", occurrences.get("row_2") + 1);
                                break;
                            case 3:
                                occurrences.put("row_3", occurrences.get("row_3") + 1);
                                break;
                            case 4:
                                occurrences.put("row_4", occurrences.get("row_4") + 1);
                        }
                    }
                }
            }
        }

        // Check if the user had selected cells that were not called
        for (String coords : coordinates) {
            if (!numbersCalled.contains(coords)) {
                return 0;
            }
        }
        
        return 1;
    }



    private void play() {
        // Create a new timer thread so the timer won't freeze the UI
        Thread timer = new Thread(new Runnable(){
            @Override
            public void run() {
                // Create a variable to count seconds
                int seconds = 0;

                // Timer loop
                while (!gamePaused) {
                    // Add 1 to the number of seconds
                    seconds++;
                    
                    // Try to sleep for 1 second
                    // Account for the drift of seconds while the code is running
                    try {
                        Thread.sleep(995);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                    
                    // If it has been 10 seconds, generate a new letter and number,
                    // set seconds to 0, and add 1 to turnsTaken
                    if (seconds >= timerDuration) {
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                label_number.setText(generateRandomNumberLetter());
                            }
                        });
                        
                        // Computer takes a turn
                        computerTurn();

                        // Check the computer's card to see if it won
                        checkCard("computer");

                        turnsTaken++;
                        seconds = 0;
                    }
                    
                    // Create a final variable to use in Platform.runLater()
                    final int SECONDS = timerDuration - seconds;

                    // Update the timer label to show seconds left using
                    // Platform.runLater() so the timer thread can change
                    // label_timer
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run() {
                            // Update the time display on the UI
                            label_timer.setText("Seconds left:\n" + SECONDS);
                        }
                    });
                }
            }
        });

        // Start the timer
        timer.start();
    }



    // Check computer's card for matches
    private void computerTurn() {
        String[] drawnValue = (label_number.getText()).split(" ");
        String letter = drawnValue[0];
        int number = Integer.parseInt(drawnValue[1]);

        // Select the one that matches (if any)
        if (letter.equals("B")) {
            if (computerCellValues[0][0] == number) {
                computer_0_0.setStyle("-fx-background-color: lightgrey");
                computerCellStates[0][0] = true;
            } else if (computerCellValues[0][1] == number) {
                computer_0_1.setStyle("-fx-background-color: lightgrey");
                computerCellStates[0][1] = true;
            } else if (computerCellValues[0][2] == number) {
                computer_0_2.setStyle("-fx-background-color: lightgrey");
                computerCellStates[0][2] = true;
            } else if (computerCellValues[0][3] == number) {
                computer_0_3.setStyle("-fx-background-color: lightgrey");
                computerCellStates[0][3] = true;
            } else if (computerCellValues[0][4] == number) {
                computer_0_4.setStyle("-fx-background-color: lightgrey");
                computerCellStates[0][4] = true;
            }

        } else if (letter.equals("I")) {
            if (computerCellValues[1][0] == number) {
                computer_1_0.setStyle("-fx-background-color: lightgrey");
                computerCellStates[1][0] = true;
            } else if (computerCellValues[1][1] == number) {
                computer_1_1.setStyle("-fx-background-color: lightgrey");
                computerCellStates[1][1] = true;
            } else if (computerCellValues[1][2] == number) {
                computer_1_2.setStyle("-fx-background-color: lightgrey");
                computerCellStates[1][2] = true;
            } else if (computerCellValues[1][3] == number) {
                computer_1_3.setStyle("-fx-background-color: lightgrey");
                computerCellStates[1][3] = true;
            } else if (computerCellValues[1][4] == number) {
                computer_1_4.setStyle("-fx-background-color: lightgrey");
                computerCellStates[1][4] = true;
            }

        } else if (letter.equals("N")) {
            if (computerCellValues[2][0] == number) {
                computer_2_0.setStyle("-fx-background-color: lightgrey");
                computerCellStates[2][0] = true;
            } else if (computerCellValues[2][1] == number) {
                computer_2_1.setStyle("-fx-background-color: lightgrey");
                computerCellStates[2][1] = true;
            } else if (computerCellValues[2][3] == number) {
                computer_2_3.setStyle("-fx-background-color: lightgrey");
                computerCellStates[2][3] = true;
            } else if (computerCellValues[2][4] == number) {
                computer_2_4.setStyle("-fx-background-color: lightgrey");
                computerCellStates[2][4] = true;
            }

        } else if (letter.equals("G")) {
            if (computerCellValues[3][0] == number) {
                computer_3_0.setStyle("-fx-background-color: lightgrey");
                computerCellStates[3][0] = true;
            } else if (computerCellValues[3][1] == number) {
                computer_3_1.setStyle("-fx-background-color: lightgrey");
                computerCellStates[3][1] = true;
            } else if (computerCellValues[3][2] == number) {
                computer_3_2.setStyle("-fx-background-color: lightgrey");
                computerCellStates[3][2] = true;
            } else if (computerCellValues[3][3] == number) {
                computer_3_3.setStyle("-fx-background-color: lightgrey");
                computerCellStates[3][3] = true;
            } else if (computerCellValues[3][4] == number) {
                computer_3_4.setStyle("-fx-background-color: lightgrey");
                computerCellStates[3][4] = true;
            }

        } else if (letter.equals("O")) {
            if (computerCellValues[4][0] == number) {
                computer_4_0.setStyle("-fx-background-color: lightgrey");
                computerCellStates[4][0] = true;
            } else if (computerCellValues[4][1] == number) {
                computer_4_1.setStyle("-fx-background-color: lightgrey");
                computerCellStates[4][1] = true;
            } else if (computerCellValues[4][2] == number) {
                computer_4_2.setStyle("-fx-background-color: lightgrey");
                computerCellStates[4][2] = true;
            } else if (computerCellValues[4][3] == number) {
                computer_4_3.setStyle("-fx-background-color: lightgrey");
                computerCellStates[4][3] = true;
            } else if (computerCellValues[4][4] == number) {
                computer_4_4.setStyle("-fx-background-color: lightgrey");
                computerCellStates[4][4] = true;
            }
        }
    }



    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Methods for processing user interactions with the game
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    // Initialize game board when user starts the game
    // Returns: 1 on success, -1 on error, or 0 on no result
    public int overlayFunction() {
        if (!gameInitialized) {
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
            // Add an event handler to every cell in the player's bingo card
            // See line 1 for more information
            player_0_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[0][0] == true) {
                        player_0_0.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[0][0] = false;
                    } else {
                        player_0_0.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[0][0] = true;
                    }
                }
            });
            player_0_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[0][1] == true) {
                        player_0_1.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[0][1] = false;
                    } else {
                        player_0_1.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[0][1] = true;
                    }
                }
            });
            player_0_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[0][2] == true) {
                        player_0_2.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[0][2] = false;
                    } else {
                        player_0_2.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[0][2] = true;
                    }
                }
            });
            player_0_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[0][3] == true) {
                        player_0_3.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[0][3] = false;
                    } else {
                        player_0_3.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[0][3] = true;
                    }
                }
            });
            player_0_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[0][4] == true) {
                        player_0_4.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[0][4] = false;
                    } else {
                        player_0_4.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[0][4] = true;
                    }
                }
            });
            player_1_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[1][0] == true) {
                        player_1_0.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[1][0] = false;
                    } else {
                        player_1_0.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[1][0] = true;
                    }
                }
            });
            player_1_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[1][1] == true) {
                        player_1_1.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[1][1] = false;
                    } else {
                        player_1_1.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[1][1] = true;
                    }
                }
            });
            player_1_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[1][2] == true) {
                        player_1_2.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[1][2] = false;
                    } else {
                        player_1_2.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[1][2] = true;
                    }
                }
            });
            player_1_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[1][3] == true) {
                        player_1_3.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[1][3] = false;
                    } else {
                        player_1_3.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[1][3] = true;
                    }
                }
            });
            player_1_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[1][4] == true) {
                        player_1_4.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[1][4] = false;
                    } else {
                        player_1_4.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[1][4] = true;
                    }
                }
            });
            player_2_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[2][0] == true) {
                        player_2_0.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[2][0] = false;
                    } else {
                        player_2_0.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[2][0] = true;
                    }
                }
            });
            player_2_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[2][1] == true) {
                        player_2_1.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[2][1] = false;
                    } else {
                        player_2_1.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[2][1] = true;
                    }
                }
            });
            player_2_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[2][3] == true) {
                        player_2_3.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[2][3] = false;
                    } else {
                        player_2_3.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[2][3] = true;
                    }
                }
            });
            player_2_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[2][4] == true) {
                        player_2_4.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[2][4] = false;
                    } else {
                        player_2_4.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[2][4] = true;
                    }
                }
            });
            player_3_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[3][0] == true) {
                        player_3_0.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[3][0] = false;
                    } else {
                        player_3_0.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[3][0] = true;
                    }
                }
            });
            player_3_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[3][1] == true) {
                        player_3_1.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[3][1] = false;
                    } else {
                        player_3_1.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[3][1] = true;
                    }
                }
            });
            player_3_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[3][2] == true) {
                        player_3_2.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[3][2] = false;
                    } else {
                        player_3_2.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[3][2] = true;
                    }
                }
            });
            player_3_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[3][3] == true) {
                        player_3_3.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[3][3] = false;
                    } else {
                        player_3_3.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[3][3] = true;
                    }
                }
            });
            player_3_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[3][4] == true) {
                        player_3_4.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[3][4] = false;
                    } else {
                        player_3_4.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[3][4] = true;
                    }
                }
            });
            player_4_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[4][0] == true) {
                        player_4_0.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[4][0] = false;
                    } else {
                        player_4_0.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[4][0] = true;
                    }
                }
            });
            player_4_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[4][1] == true) {
                        player_4_1.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[4][1] = false;
                    } else {
                        player_4_1.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[4][1] = true;
                    }
                }
            });
            player_4_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[4][2] == true) {
                        player_4_2.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[4][2] = false;
                    } else {
                        player_4_2.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[4][2] = true;
                    }
                }
            });
            player_4_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[4][3] == true) {
                        player_4_3.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[4][3] = false;
                    } else {
                        player_4_3.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[4][3] = true;
                    }
                }
            });
            player_4_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[4][4] == true) {
                        player_4_4.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[4][4] = false;
                    } else {
                        player_4_4.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[4][4] = true;
                    }
                }
            });
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

            // Create array to store states of the player's and computer's cells
            // Make sure to not have duplicate numbers
            // Generate random numbers for cards
            // B: 1 - 20
            // I: 20 - 40
            // N: 40 - 60
            // G: 60 - 80
            // O: 80 - 99
            for (int col = 0; col < 5; col++) {
                for (int row = 0; row < 5; row++) {
                    if (col == 0) {
                        while (existsInArray(playerCellValues, playerCellValues[col][row]) != 1) {
                            playerCellValues[col][row] = randomNum(1, 20);
                        }
                        while (existsInArray(computerCellValues, computerCellValues[col][row]) != 1) {
                            computerCellValues[col][row] = randomNum(1, 20);
                        }

                    } else if (col == 1) {
                        while (existsInArray(playerCellValues, playerCellValues[col][row]) != 1) {
                            playerCellValues[col][row] = randomNum(20, 40);
                        }
                        while (existsInArray(computerCellValues, computerCellValues[col][row]) != 1) {
                            computerCellValues[col][row] = randomNum(20, 40);
                        }

                    } else if (col == 2 && row != 2) {
                        while (existsInArray(playerCellValues, playerCellValues[col][row]) != 1) {
                            playerCellValues[col][row] = randomNum(40, 60);
                        }
                        while (existsInArray(computerCellValues, computerCellValues[col][row]) != 1) {
                            computerCellValues[col][row] = randomNum(40, 60);
                        }

                    } else if (col == 3) {
                        while (existsInArray(playerCellValues, playerCellValues[col][row]) != 1) {
                            playerCellValues[col][row] = randomNum(60, 80);
                        }
                        while (existsInArray(computerCellValues, computerCellValues[col][row]) != 1) {
                            computerCellValues[col][row] = randomNum(60, 80);
                        }

                    } else if (col == 4) {
                        while (existsInArray(playerCellValues, playerCellValues[col][row]) != 1) {
                            playerCellValues[col][row] = randomNum(80, 99);
                        }
                        while (existsInArray(computerCellValues, computerCellValues[col][row]) != 1) {
                            computerCellValues[col][row] = randomNum(80, 99);
                        }
                    }

                    if (col == 2 && row == 2) {
                        playerCellStates[col][row] = true;
                        computerCellStates[col][row] = true;
                    } else {
                        playerCellStates[col][row] = false;
                        computerCellStates[col][row] = false;
                    }
                }
            }

            // Randomly generate numbers for player's card
            player_0_0.setText("" + playerCellValues[0][0]);
            player_0_1.setText("" + playerCellValues[0][1]);
            player_0_2.setText("" + playerCellValues[0][2]);
            player_0_3.setText("" + playerCellValues[0][3]);
            player_0_4.setText("" + playerCellValues[0][4]);
            player_1_0.setText("" + playerCellValues[1][0]);
            player_1_1.setText("" + playerCellValues[1][1]);
            player_1_2.setText("" + playerCellValues[1][2]);
            player_1_3.setText("" + playerCellValues[1][3]);
            player_1_4.setText("" + playerCellValues[1][4]);
            player_2_0.setText("" + playerCellValues[2][0]);
            player_2_1.setText("" + playerCellValues[2][1]);
            player_2_3.setText("" + playerCellValues[2][3]);
            player_2_4.setText("" + playerCellValues[2][4]);
            player_3_0.setText("" + playerCellValues[3][0]);
            player_3_1.setText("" + playerCellValues[3][1]);
            player_3_2.setText("" + playerCellValues[3][2]);
            player_3_3.setText("" + playerCellValues[3][3]);
            player_3_4.setText("" + playerCellValues[3][4]);
            player_4_0.setText("" + playerCellValues[4][0]);
            player_4_1.setText("" + playerCellValues[4][1]);
            player_4_2.setText("" + playerCellValues[4][2]);
            player_4_3.setText("" + playerCellValues[4][3]);
            player_4_4.setText("" + playerCellValues[4][4]);

            // Randomly generate numbers for computer's card
            computer_0_0.setText("" + computerCellValues[0][0]);
            computer_0_1.setText("" + computerCellValues[0][1]);
            computer_0_2.setText("" + computerCellValues[0][2]);
            computer_0_3.setText("" + computerCellValues[0][3]);
            computer_0_4.setText("" + computerCellValues[0][4]);
            computer_1_0.setText("" + computerCellValues[1][0]);
            computer_1_1.setText("" + computerCellValues[1][1]);
            computer_1_2.setText("" + computerCellValues[1][2]);
            computer_1_3.setText("" + computerCellValues[1][3]);
            computer_1_4.setText("" + computerCellValues[1][4]);
            computer_2_0.setText("" + computerCellValues[2][0]);
            computer_2_1.setText("" + computerCellValues[2][1]);
            computer_2_3.setText("" + computerCellValues[2][3]);
            computer_2_4.setText("" + computerCellValues[2][4]);
            computer_3_0.setText("" + computerCellValues[3][0]);
            computer_3_1.setText("" + computerCellValues[3][1]);
            computer_3_2.setText("" + computerCellValues[3][2]);
            computer_3_3.setText("" + computerCellValues[3][3]);
            computer_3_4.setText("" + computerCellValues[3][4]);
            computer_4_0.setText("" + computerCellValues[4][0]);
            computer_4_1.setText("" + computerCellValues[4][1]);
            computer_4_2.setText("" + computerCellValues[4][2]);
            computer_4_3.setText("" + computerCellValues[4][3]);
            computer_4_4.setText("" + computerCellValues[4][4]);

            // Randomly generate a letter and a number
            label_number.setText(generateRandomNumberLetter());

            // Computer takes it's first turn
            computerTurn();

            // Remove overlay and enable the UI
            setPause(false);

            gameInitialized = true;
            play();

            return 1;
        } else if (gamePaused = true) {
            // TODO : Add code to unpause the game
        }

        return 0;
    }

    // This is called when the user clicks the back button
    public void returnHome(ActionEvent actionEvent) {
        Stage currentStage = (Stage) button_back.getScene().getWindow();
        openWindow("Start.fxml", "Bingo", currentStage);
    }

    // Pause game
    public void pause() {
        // TODO : Create a pause function
    }

    // Check user choices when the bingo button is pressed
    public void bingo() {
        switch (checkCard("player")) {
            case 0:
                // TODO : Show user's incorrect choices
                System.out.println("User lost");
                break;
            case 1:
                // TODO : Show user won
                System.out.println("User won");
        }
    }
}
