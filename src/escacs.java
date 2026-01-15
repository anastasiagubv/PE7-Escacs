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
        System.out.println("\nIntrodueix el nom del jugador 1 (blanques): ");
        playerWhite = sc.nextLine();
        players.add(playerWhite);

        System.out.println("Introdueix el nom del jugador 2 (negres): ");
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
            System.out.print("Introdueix la teva jugada: ");
            move = sc.nextLine();

            if (move.equalsIgnoreCase("Abandonar")) {
                System.out.println(playerWhite + " ha abandonat el joc. " + playerBlack + " guanya!");
                return false; // Joc acabat
            } else {
                System.out.println(playerWhite + " ha jugat: " + move);
                return true; // Joc continua
            }

        } else {
            System.out.println("\n" + playerBlack + ", és el teu torn (negres).");
            System.out.println("Escriu 'Abandonar' per sortir del joc.");
            System.out.print("Introdueix la teva jugada: ");
            move = sc.nextLine();

            if (move.equalsIgnoreCase("Abandonar")) {
                System.out.println(playerBlack + " ha abandonat el joc. " + playerWhite + " guanya!");
                return false; // Joc acabat
            } else {
                System.out.println(playerBlack + " ha jugat: " + move);
                return true; // Joc continua
            }
        }
    }

    public void validateMove(String move, boolean whiteTurn) {
        // Aquesta funció validarà la jugada introduïda
        // Implementació futura
    }
}
