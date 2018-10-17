package sudokuplayer;

/**
 * @author Daniel Copley
 * @version 1.0
 */
class Sudoku {
    private char[][] board = new char[9][9];

    /**
     * Constructor - No Parameters
     */
    Sudoku() {
        for (char[] row : board) {
            for (int col = 0; col < board[0].length; col++) {
                row[col] = ' ';
            }
        }
    }

    /**
     * Constructor
     *
     * @param starting_position position of squares to start
     */
    Sudoku(String starting_position) {
        int rowIndex = 0;
        for (String row : starting_position.split("\n")) {
            for (int colIndex = 0; colIndex < board[0].length; colIndex++) {
                board[rowIndex][colIndex] = row.charAt(colIndex);
            }
            rowIndex++;
        }
    }

    /**
     * @param row sudoku board row
     * @param col sudoku board col
     * @return returns val from board[row][col]
     */
    char getSquare(int row, int col) {
        return board[row][col];
    }

    /**
     * @param row sudoku board row
     * @param col sudoku board col
     * @param val val to set board[row][col] to
     */
    void setSquare(int row, int col, char val) {
        board[row][col] = val;
    }

    /**
     * @param token character to check
     * @return true if invalid; false if valid
     */
    private boolean invalidInput(char token) {
        return Character.getNumericValue(token) < 1 || Character.getNumericValue(token) > 9;
    }

    /**
     * @param checkString string to check
     * @param token       token to check for
     * @return true if token is in string else false
     */
    private boolean charInString(String checkString, char token) {
        return checkString.contains(Character.toString(token));
    }

    /**
     * @return true if rows contain no duplicates else false
     */
    private boolean rowsValid() {
        for (int row = 0; row < board.length; row++) {
            String inRow = "";
            for (int col = 0; col < board[0].length; col++) {
                char currentSquare = getSquare(row, col);
                if (currentSquare == ' ') {
                    continue;
                }
                if (charInString(inRow, currentSquare) || invalidInput(currentSquare)) {
                    return false;
                } else {
                    inRow += currentSquare;
                }
            }
        }
        return true;
    }

    /**
     * @return true if columns contain no duplicates else false
     */
    private boolean colsValid() {
        for (int col = 0; col < board[0].length; col++) {
            String inCol = "";
            for (int row = 0; row < board[0].length; row++) {
                char currentSquare = getSquare(row, col);
                if (currentSquare == ' ') {
                    continue;
                }
                if (charInString(inCol, currentSquare) || invalidInput(currentSquare)) {
                    return false;
                }
                inCol += currentSquare;
            }
        }
        return true;
    }

    /**
     * @return true if all spaces are filled else false
     */
    private boolean spacesFilled() {
        for (char row[] : board) {
            for (int col = 0; col < board[0].length; col++) {
                if (invalidInput(row[col])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Function generates a string of the character elements in a given 3x3 box
     *
     * @param boxNum the box to generate a string for (box 0 through 8)
     * @return String of character elements in a given 3x3 box
     */
    private String getBox(int boxNum) {
        int startingCol = (boxNum % 3) * 3;
        int startingRow = boxNum < 3 ? 0 : boxNum < 6 ? 3 : 6;
        StringBuilder result = new StringBuilder();
        for (int row = startingRow; row < startingRow + 3; row++) {
            for (int col = startingCol; col < startingCol + 3; col++) {
                result.append(board[row][col]);
            }
        }
        return result.toString();
    }

    /**
     * Check validity of all 3x3 boxes
     *
     * @return true if all 3x3 squares are valid else false
     */
    private boolean squaresValid() {
        for (int i = 0; i < 9; i++) {
            String currentBox = getBox(i);
            StringBuilder inBox = new StringBuilder();
            for (int strIndex = 0; strIndex < currentBox.length(); strIndex++) {
                if (charInString(inBox.toString(), currentBox.charAt(strIndex))) {
                    return false;
                }
                if (currentBox.charAt(strIndex) != ' ') {
                    inBox.append(currentBox.charAt(strIndex));
                }
            }
        }
        return true;
    }

    /**
     * @return true if sudoku puzzle is solved else false
     */
    boolean isSolved() {
        return isValid() && spacesFilled();
    }

    /**
     * @return true if input is valid
     */
    boolean isValid() {
        return rowsValid() && colsValid() && squaresValid();
    }
}
