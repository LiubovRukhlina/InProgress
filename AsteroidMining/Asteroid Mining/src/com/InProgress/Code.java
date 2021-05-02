import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

class ProperImageFrame
{
    public static void main(String args[])
    {
        SwingUtilities.invokeLater(() -> createAndShowGui());
    }

    private static void createAndShowGui()
    {
        tryToSetLookAndFeel();

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.getContentPane().setLayout(new BorderLayout());

        ImagePanel imagePanel = new ImagePanel();
        f.getContentPane().add(imagePanel, BorderLayout.CENTER);

        JButton travelButton = new JButton("Travel");
        travelButton.addActionListener(e -> loadAndShowImage(imagePanel));

        JButton robotButton = new JButton("build robot");
        travelButton.addActionListener(e -> loadAndShowImage(imagePanel));

        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel1.add(travelButton);
        f.getContentPane().add(buttonPanel1, BorderLayout.NORTH);

        f.setSize(1000, 700);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
/**
    public static JPanel drawGamePanel(){
        //Create game panel and attributes
        JPanel gamePanel = new JPanel();
        Image background = Toolkit.getDefaultToolkit().createImage("Background.png");
        gamePanel.drawImage(background, 0, 0, null);
        //Set Return
        return gamePanel;
    }
**/
    private static void loadAndShowImage(ImagePanel imagePanel)
    {
        try
        {
            BufferedImage image = ImageIO.read(new File("settler.png"));
            imagePanel.setImage(image);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    private static void tryToSetLookAndFeel()
    {
        try
        {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    return;
                }
            }
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException
                | InstantiationException | IllegalAccessException ex)
        {
            ex.printStackTrace();
        }
    }

    private static class ImagePanel extends JPanel
    {
        private BufferedImage image;

        void setImage(BufferedImage image)
        {
            this.image = image;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            if (image != null)
            {
                g.drawImage(image, 500, 250, null);
            }
        }

    }
}

