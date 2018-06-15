
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Wr extends JPanel implements KeyListener,
        ActionListener, Runnable {
    private static boolean right = false;
    private static boolean left = false;

    static int posXGlobe = 160;
    static int posYGlobe = 218;

    int posXPlat = 160;
    int posYPlat = 245;

    int BlockX = 70;
    int BlockY = 50;

    int BlockWidth = 30;
    int BlockHeight = 20;

    static Globe globe = new Globe(posXGlobe, posYGlobe, 10, 10);
    static Plat plat = new Plat(160, 245, 40, 5);
    static Block[] blocks = new Block[20];


    int courseX = -1;
    int courseY = -1;
    static boolean fallingDown = false;
    static boolean blocksOver = false;
    int count = 0;
    static String text;

    public static class Main {

        public static Painter painter = new Painter();

        private static String heroName;
        static int points = 0;

        public static void main(String[] args) {
            JFrame frame = new JFrame();
            Wr game = new Wr();
            JButton button = new JButton("RESTART");
            frame.setSize(350, 305);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.add(game);
            frame.add(button, BorderLayout.SOUTH);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);
            button.addActionListener(game);

            game.addKeyListener(game);
            game.setFocusable(true);
            Thread t = new Thread(game);

            heroName = JOptionPane.showInputDialog(null, "Please enter your name:",
                    "Wrecking Ball", JOptionPane.QUESTION_MESSAGE);
            if (heroName == null) {
                System.exit(0);
            }
            if (heroName.toUpperCase().equals("LZ") || heroName.equals("LizaZaretskaya")
                    || heroName.equals("Liza") || heroName.toUpperCase().equals("PM")) {
                points += 50;
                JOptionPane.showMessageDialog(null, "You got secret 50 points",
                        "50 Points", JOptionPane.INFORMATION_MESSAGE);

            }


            t.start();
        }
    }


    public void paint(Graphics g) {

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 350, 450);
        g.setColor(Color.white);
        g.setFont(new Font("Times New Roman", Font.BOLD, 15));
        g.drawString("Points: " + Main.points, 260, 20);
        g.setColor(Color.blue);
        g.fillOval(globe.x, globe.y, globe.width, globe.height);
        g.setColor(Color.black);
        g.fill3DRect(plat.x, plat.y, plat.width, plat.height, true);
        //g.setColor(Color.GRAY);
        //g.fillRect(0, 251, 450, 200);
        g.setColor(Color.black);
        g.drawRect(0, 0, 343, 250);
        for (Block block : blocks) {
            if (block != null) {
                g.fill3DRect(block.getBlockX(), block.getBlockY(), block.getBlockWidth(),
                        block.getBlockHeight(), true);
            }
        }

        if (fallingDown || blocksOver) {
            Font font = new Font("Times New Roman", Font.BOLD, 15);
            g.setFont(font);
            g.drawString(text, 40, 200);
            fallingDown = false;
            blocksOver = false;
        }

    }


    public void run() {

        createBlocks();

        while (true) {
            for (int i = 0; i < blocks.length; i++) {
                if (blocks[i] != null) {
                    if (blocks[i].intersects(globe)) {
                        blocks[i] = null;
                        courseY = -courseY;
                        Main.points += 25;
                        count++;
                    }
                }
            }

            if (count == blocks.length) {
                blocksOver = true;
                text = "YOU WON THE GAME! POINTS: " + Main.points;
                repaint();

                courseY = 0;
                courseX = 0;
            }

            repaint();
            globe.x += courseX;
            globe.y += courseY;

            if (left) {
                plat.x -= 3;
                right = false;
            }
            if (right) {
                plat.x += 3;
                left = false;
            }
            if (plat.x <= 4) {
                plat.x = 4;
            } else if (plat.x >= 298) {
                plat.x = 298;
            }
            if (plat.intersects(globe)) {
                courseY = -courseY;
            }
            if (globe.x <= 0 || globe.x + globe.height >= 343) {
                courseX = -courseX;
            }
            if (globe.y <= 0) {
                courseY = -courseY;
            }
            if (globe.y >= 250) {
                fallingDown = true;
                text = "YOU LOST THE GAME! POINTS: " + Main.points;
                repaint();

            }
            try {
                Thread.sleep(7);
            } catch (Exception ex) {
            }
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            left = true;
        }

        if (keyCode == KeyEvent.VK_RIGHT) {
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            left = false;
        }

        if (keyCode == KeyEvent.VK_RIGHT) {
            right = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if (str.equals("RESTART")) {
            this.restart();
        }
    }

    public void restart() {
        requestFocus(true);
        initializeVariables();
        createBlocks();
        repaint();
        Main.points = 0;

    }

    public void initializeVariables() {

        posXGlobe = 160;
        posYGlobe = 218;

        posXPlat = 160;
        posYPlat = 245;

        BlockX = 70;
        BlockY = 50;
        globe = new Globe(posXGlobe, posYGlobe, 10, 10);
        plat = new Plat(posXPlat, posYPlat, 40, 5);

        blocks = new Block[20];

        courseX = -1;
        courseY = -1;
        fallingDown = false;
        blocksOver = false;
        count = 0;
        text = null;

    }

    public void createBlocks() {
        for (int i = 0; i < blocks.length; i++) {
            if (i == 5) {
                BlockX = 80;
                BlockY = (BlockY + BlockHeight + 3);

            }
            if (i == 9) {
                BlockX = 100;
                BlockY = (BlockY + BlockHeight + 3);

            }
            if (i == 12) {
                BlockX = 25;
                BlockY = (BlockY + BlockHeight + 3);

            }
            blocks[i] = new Block(BlockX, BlockY, BlockWidth, BlockHeight);
            BlockX += (BlockWidth + 3);
        }
    }
}