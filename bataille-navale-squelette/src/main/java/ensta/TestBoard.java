package ensta;


import ensta.controller.Game;


public class TestBoard {
    public static void main(String args[]) {
        Game game = new Game();
        game = game.init();
        game.run();

    }
    
}
