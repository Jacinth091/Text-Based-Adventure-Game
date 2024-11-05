package Other;

public class Main {
    public static void main(String[] args) {
        MainThread gameThread = new MainThread();
        GameScreen gs = new GameScreen();
        UI ui = new UI();
        App app = new App(gs, ui);




        app.pack();
        app.setVisible(true);
        app.setLocationRelativeTo(null);

        gameThread.start();





    }
}
