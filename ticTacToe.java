import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ticTacToe extends JFrame implements ActionListener {

    String playNum = "One";
    public JButton[] board = new JButton[9];
    public JMenuItem tryAgain = new JMenuItem();

    public ImageIcon symbol;
    JLabel turnTitle = new JLabel("Player " + playNum + " please choose your location");

    public ticTacToe() {
        super("TicTacToe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLookAndFeel();

        setLayout(new BorderLayout());

        //create menu bar
        tryAgain.setText("Try Again");
        tryAgain.addActionListener(this);
        JMenuBar bar = new JMenuBar();
        JMenu m1 = new JMenu("File");
        m1.add(tryAgain);
        bar.add(m1);


        //Title indicating player - need to work on getting it to change for each player
        JPanel pane = new JPanel();

        pane.add(turnTitle);
        add(pane, BorderLayout.NORTH);
        setJMenuBar(bar);

        // set up grid
        JPanel gridPanel = new JPanel();
        GridLayout grid = new GridLayout(3, 3, 10, 10);
        gridPanel.setLayout(grid);
        for (int i = 0; i < board.length; i++) {
            board[i] = new JButton(" ");
            board[i].addActionListener(this);

            gridPanel.add(board[i]);
        }
        add(gridPanel);
        symbol();


        setVisible(true);
        return;


    }

    public void resetGame(){
        for (int i = 0; i < board.length; i++) {
            board[i].setEnabled(true);
            board[i].setIcon(null);
            turnTitle.setText("Player " + playNum + " please choose your location" );

        }
    }

    public void game() {
        //method checking win conditions, will return as true if came won
        checkWin();
        //if game won display victory message and exit game
        if (checkWin()) {
            turnTitle.setText("Player " + playNum + " is the winner!");
            //disable buttons to stop game
            for (int i = 0; i < board.length; i++) {
                board[i].setEnabled(false);
            }
            return;
        }

        //if not won finish turn and switch player and symbol
        if (playNum == "One") {
            playNum = "Two";
            symbol();
        } else if (playNum == "Two") {
            playNum = "One";
            symbol();
        }
        //display message informing next player is their turn
        turnTitle.setText("Player " + playNum + " please choose your location" );

    }

    public void symbol(){
        ImageIcon exIcon = new ImageIcon("icons/icons8-x-64.png");
        ImageIcon oIcon = new ImageIcon("icons/icons8-o-64.png");
        if(playNum == "One"){
            symbol = exIcon;
        } else if (playNum == "Two"){
            symbol = oIcon;
        }
    }

    //action if button pushed
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        if (tryAgain == source) {
            resetGame();
            return;
        } else {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == source) {
                //if button already taken inform player to go again
                if (board[i].getIcon() != (null)){
                    turnTitle.setText("Player " + playNum + " square taken, try again" );
                   return;
                } else {
                    //if square available load symbol onto square
                    board[i].setIcon(symbol);
                    //call game() will check win condition and switch player
                    game();
                    return;
                }
            }
            }
        }
    }
    //goes through win conditions
   public boolean checkWin(){
        //System.out.println(board[0].getIcon() + " " + board[1].getIcon() + " " + board[2].getIcon());
       if ((board[0].getIcon() == symbol) /*&& (board[1].getIcon() == symbol) && board[2].getIcon() == symbol*/) {
            System.out.println(board[0].getIcon() + " " + symbol);
        }
        /*if (board[0].getIcon() == symbol && board[1].getIcon() ==symbol && board[2].getIcon() == symbol) {
            return true;
        } else if (board[3].getIcon() == symbol && board[4].getIcon() == symbol && board[5].getIcon() == symbol){
            return true;
        } else if (board[6].getIcon() == symbol && board[7].getIcon() == symbol && board[8].getIcon() == symbol){
            return true;
        } else if (board[0].getIcon() == symbol && board[3].getIcon() == symbol && board[6].getIcon() == symbol){
            return true;
        } else if (board[1].getIcon() == symbol && board[4].getIcon() == symbol && board[7].getIcon() == symbol){
            return true;
        } else if (board[2].getIcon() == symbol && board[5].getIcon() == symbol && board[8].getIcon() == symbol){
            return true;
        } else if (board[0].getIcon() == symbol && board[4].getIcon() == symbol && board[8].getIcon() == symbol){
            return true;
        } else if (board[2].getIcon() == symbol && board[4].getIcon() == symbol && board[6].getIcon() == symbol){
            return true;
        }*/
       return false;

   }

    private static void setLookAndFeel () {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

            public static void main (String[]arguments){
                ticTacToe game = new ticTacToe();

            }


        }


