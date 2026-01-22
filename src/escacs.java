import java.util.Scanner;
import java.util.ArrayList;

public class escacs {
    static final Scanner sc = new Scanner(System.in);

    // Arrays i llistes
    private char[][] board;
    private ArrayList<String> players;

    // Variables globals
    private String playerWhite;
    private String playerBlack;

    public static void main(String[] args) {
        escacs game = new escacs();
        game.start();
    }

    public void start() {
        gameBoard();
        getPlayersName();
        playGame();
    }

    public void gameBoard() {
        // Inicialitzar el tauler d'escacs
        board = new char[8][8];

        // Omplir espais buits
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '.';
            }
        }

        // Inicialitzar les peces blanques
        board[7][0] = 'T'; // Torre blanca
        board[7][7] = 'T'; // Torre blanca
        board[7][1] = 'C'; // Cavall blanc
        board[7][6] = 'C'; // Cavall blanc
        board[7][2] = 'A'; // Alfil blanc
        board[7][5] = 'A'; // Alfil blanc
        board[7][3] = 'Q'; // Reina blanca
        board[7][4] = 'K'; // Rei blanc

        for (int i = 0; i < 8; i++) {
            board[6][i] = 'P'; // Peons blancs
        }

        // Inicialitzar les peces negres
        board[0][0] = 't'; // Torre negre
        board[0][7] = 't'; // Torre negre
        board[0][1] = 'c'; // Cavall negre
        board[0][6] = 'c'; // Cavall negre
        board[0][2] = 'a'; // Alfil negre
        board[0][5] = 'a'; // Alfil negre
        board[0][3] = 'q'; // Reina negre
        board[0][4] = 'k'; // Rei negre

        for (int i = 0; i < 8; i++) {
            board[1][i] = 'p'; // Peons negres
        }
    }

    public void printBoard() {
        System.out.println("\n--------Tauler d'escacs--------");
        System.out.println("  a b c d e f g h");
        for (int i = 0; i < 8; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void playGame() {
        boolean gameOn = true;
        boolean whiteTurn = true;

        while (gameOn) {
            printBoard();
            gameOn = getMove(whiteTurn);
            whiteTurn = !whiteTurn; // Canviar de torn
        }
    }

    public void getPlayersName() {
        players = new ArrayList<String>();

        // Demanar noms dels jugadors
        System.out.print("\nIntrodueix el nom del jugador 1 (blanques): ");
        playerWhite = sc.nextLine();
        players.add(playerWhite);

        System.out.print("Introdueix el nom del jugador 2 (negres): ");
        playerBlack = sc.nextLine();
        players.add(playerBlack);

        System.out.println("\nJugadors registrats:");
        System.out.println(playerWhite + " (blanques)");
        System.out.println(playerBlack + " (negres)");
    }

    public boolean getMove(boolean whiteTurn) {
        String move;

        if (whiteTurn) {
            System.out.println("\n" + playerWhite + ", és el teu torn (blanques).");
            System.out.println("Escriu 'Abandonar' per sortir del joc.");
            System.out.print("Introdueix la teva jugada. Format 'e2 e4': ");
            move = sc.nextLine();

            if (move.equalsIgnoreCase("Abandonar")) {
                System.out.println(playerWhite + " ha abandonat el joc. " + playerBlack + " guanya!");
                return false; // Joc acabat
            } else {
                if (validateMove(move, whiteTurn)) {
                    System.out.println(playerWhite + " ha jugat: " + move);
                    return true; // Joc continua
                } else {
                    System.out.println("Moviment invàlid. Torna-ho a intentar.");
                    return getMove(whiteTurn); // Tornar a demanar la jugada
                }
            }

        } else {
            System.out.println("\n" + playerBlack + ", és el teu torn (negres).");
            System.out.println("Escriu 'Abandonar' per sortir del joc.");
            System.out.print("Introdueix la teva jugada. Format 'e2 e4': ");
            move = sc.nextLine();

            if (move.equalsIgnoreCase("Abandonar")) {
                System.out.println(playerBlack + " ha abandonat el joc. " + playerWhite + " guanya!");
                return false; // Joc acabat
            } else {
                if (validateMove(move, whiteTurn)) {
                    System.out.println(playerBlack + " ha jugat: " + move);
                    return true; // Joc continua
                } else {
                    System.out.println("Moviment invàlid. Torna-ho a intentar.");
                    return getMove(whiteTurn); // Tornar a demanar la jugada
                }
            }
        }
    }

    public boolean validateMove(String move, boolean whiteTurn) {
        // Validar format
        String[] parts = move.split(" ");

        if (parts.length != 2) {
            System.out.println("Error. Introdueix la jugada en format 'e2 e4'.");
            return false;
        }

        // Separar origen i destí
        String origin = parts[0]; // e2
        String destination = parts[1]; // e4

        // Validar que cada part tingui 2 caràcters
        if (origin.length() != 2 || destination.length() != 2) {
            System.out.println("Error. Introdueix la jugada en format 'e2 e4'.");
            return false;
        }

        // Convertir coordenades
        int[] originCoords = convertCoordinates(origin);
        int[] destCoords = convertCoordinates(destination);

        if (originCoords == null || destCoords == null) {
            System.out.println("Error. Coordenades invàlides.");
            return false;
        }

        int originRow = originCoords[0];
        int originCol = originCoords[1];
        int destRow = destCoords[0];
        int destCol = destCoords[1];

        // Validar que hi ha una peça a l'origen
        char piece = board[originRow][originCol];
        if (piece == '.') {
            System.out.println("ERROR: No hi ha cap peça a " + origin);
            return false;
        }

        // Validar que la peça és del color correcte
        if (whiteTurn && Character.isLowerCase(piece)) {
            System.out.println("ERROR: No pots moure peces negres.");
            return false;
        }
        if (!whiteTurn && Character.isUpperCase(piece)) {
            System.out.println("ERROR: No pots moure peces blanques.");
            return false;
        }

        // Validar moviment segons la peça
        if(!validatePieceMovement(piece, originRow, originCol, destRow, destCol)) {
            return false;
        }

        // Actualitzar el tauler
        updateBoard(move);
        return true;
    }

    public boolean validatePieceMovement(char piece, int originRow, int originCol, int destRow, int destCol) {
        // Validar moviment segons la peça
        switch (Character.toUpperCase(piece)) {
            // Peó
            case 'P':
                return validatePeo(originRow, originCol, destRow, destCol);
            // Torre
            case 'T':
                return validateTorre(originRow, originCol, destRow, destCol);
            // Cavall
            case 'C':
                return validateCavall(originRow, originCol, destRow, destCol);
            // Alfil
            case 'A':
                return validateAlfil(originRow, originCol, destRow, destCol);
            // Reina
            case 'Q':
                return validateReina(originRow, originCol, destRow, destCol);
            // Rei
            case 'K':
                return validateRei(originRow, originCol, destRow, destCol);
            default:
                System.out.println("ERROR: Peça desconeguda.");
                return false;
        }
    }


    public int[] convertCoordinates(String coord) {
        // Convertir coordenades d'escacs a índexs d'array
        char file = coord.charAt(0); // lletra
        char rank = coord.charAt(1); // número

        // Validar que la columna sigui entre 'a' i 'h'
        if (file < 'a' || file > 'h' || rank < '1' || rank > '8') {
            return null; // Coordenades invàlides
        }

        // Validar que la fila sigui entre '1' i '8'
        if (file < 'a' || file > 'h' || rank < '1' || rank > '8') {
            return null; // Coordenades invàlides
        }

        int row = Character.getNumericValue(rank) - 1; // Fila (0-7)
        int col = file - 'a'; // Columna (0-7)

        return new int[] { row, col };
    }

    public void updateBoard(String move) {
        String[] parts = move.split(" ");
        String origin = parts[0];
        String destination = parts[1];

        int[] originCoords = convertCoordinates(origin);
        int[] destCoords = convertCoordinates(destination);

        int originRow = originCoords[0];
        int originCol = originCoords[1];
        int destRow = destCoords[0];
        int destCol = destCoords[1];

        // Moure la peça
        board[destRow][destCol] = board[originRow][originCol];
        board[originRow][originCol] = '.';
    }

    public boolean validatePeo(int originRow, int originCol, int destRow, int destCol) {
        return true;
    }

    public boolean validateTorre(int originRow, int originCol, int destRow, int destCol) {
        // Moviment en línia recta horitzontal  (mateixa fila)
        if (originRow == destRow) {
            // Comprovar que el camí està lliure
            int start = Math.min(originCol, destCol) + 1;
            int end = Math.max(originCol, destCol);

            for (int col = start; col < end; col++) {
                if (board[originRow][col] != '.') {
                    System.out.println("ERROR: Hi ha una peça en el camí horitzontal.");
                    return false;
                }
            }
            return true;

            // Moviment en línia recta vertical (mateixa columna)
        } else if (originCol == destCol) {
            // Comprovar que el camí està lliure
            int start = Math.min(originRow, destRow) + 1;
            int end = Math.max(originRow, destRow);

            for (int row = start; row < end; row++) {
                if (board[row][originCol] != '.') {
                    System.out.println("ERROR: Hi ha una peça en el camí vertical.");
                    return false;
                }
            }
            return true;

        } else {
            System.out.println("ERROR: La torre només es pot moure en línea recta.");
            return false;
        }
    }

    public boolean validateCavall(int originRow, int originCol, int destRow, int destCol) {
        return true;
    }

    public boolean validateAlfil(int originRow, int originCol, int destRow, int destCol) {
        return true;
    }

    public boolean validateReina(int originRow, int originCol, int destRow, int destCol) {
        return true;
    }

    public boolean validateRei(int originRow, int originCol, int destRow, int destCol) {
        int rowDiff = Math.abs(destRow - originRow);
        int colDiff = Math.abs(destCol - originCol);

        // El rei es pot moure una casella en qualsevol direcció
        if (rowDiff > 1 || colDiff > 1) {
            return true;
        }

        System.out.println("ERROR: El rei només es pot moure una casella en qualsevol direcció.");
        return false;
    }
}