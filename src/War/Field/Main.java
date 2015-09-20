
package War.Field;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame implements Runnable,KeyListener
{
    JLabel ground1,ground2,mountain1,mountain2,mountain3,cloud1,cloud2,cloud3;
    JLabel titleLabel,newgameLabel,logoLabel,background,tutorialLable;
    Thread thread;
    
    int gx1=0,gx2=1350,mx1=0,mx2=1125,mx3=2250,cloudx1=0,cloudx2=1000,cloudx3=2000;
    int gy1=610,my1=550,cloudy1 = 0;
    int hx = 350,hy = 350;
    int bal = 20000, roc = 30;
    private JLabel quitLabel;
    
    boolean over = false;
    
    public Main()
    {
        this.setTitle("Battel Field");
        if(true)
        {
            this.setUndecorated(true);
            this.setExtendedState(Main.MAXIMIZED_BOTH);
        }
        else
        {
            this.setSize(1024, 768);
            this.setLocationRelativeTo(null);
            this.setResizable(true);
        }
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initComp();
    }
    
    private void initComp()
    {
        Background();
        this.setLayout(null);
        this.addKeyListener(this);
        thread = new Thread(this);
        thread.start();
    }   
    
    private void Background()
    {
        logoLabel = new JLabel("Sam & Rehaan");
        logoLabel.setFont(new Font("David", Font.ITALIC, 30));
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/SR logo .png")));
        logoLabel.setBounds(300, 100, 1200, 600);
        
        background = new JLabel();
        background.setBounds(0, 0, 1500, 800);
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/background_logo.png")));
        
        ground1 = new JLabel();
        ground2 = new JLabel();
        
        ground1.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/ground.png")));
        ground2.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/ground.png")));
        
        mountain1 = new JLabel();
        mountain2 = new JLabel();
        mountain3 = new JLabel();
        
        mountain1.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/mountains.png")));
        mountain2.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/mountains.png")));
        mountain3.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/mountains.png")));
        
        cloud1 = new JLabel();
        cloud2 = new JLabel();
        cloud3 = new JLabel();
        
        cloud1.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/cloud_layer_2.png")));
        cloud2.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/cloud_layer_2.png")));
        cloud3.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/cloud_layer_2.png")));
        
        setBackground();
        
        titleLabel = new JLabel();
        titleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/title.png")));
        titleLabel.setBounds(400, 250, 700, 100);
        
        newgameLabel = new JLabel();
        newgameLabel.setText("Press SPACE to Start the Game");
        newgameLabel.setFont(new Font("David", 3, 25));
        newgameLabel.setBounds(500, 450, 400, 50);
        
        tutorialLable = new JLabel();
        tutorialLable.setText("Press ENTER to Start the Game");
        tutorialLable.setFont(new Font("David", 3, 25));
        tutorialLable.setBounds(500, 500, 400, 50);
        
        quitLabel = new JLabel();
        quitLabel.setText("Press ESC to quit");
        quitLabel.setFont(new Font("David", 3, 25));
        quitLabel.setBounds(550, 550, 300, 50);
        
        this.add(logoLabel);
        this.add(background);
        this.add(ground1);
        this.add(ground2);
        this.add(mountain1);
        this.add(mountain2);
        this.add(mountain3);
        this.add(cloud1);
        this.add(cloud2);
        this.add(cloud3);
    }
    
    private void setBackground()
    {
        if(gx1+1350>0){
            gx1 = gx1-1;
            ground1.setBounds(gx1, gy1, 1350, 250);
        } else {
            gx1 = 1349;
        }
        if(gx2+1350>0) {
            gx2 = gx2 - 1;
            ground2.setBounds(gx2, gy1, 1350, 250);
        } else {
            gx2 = 1349;
        }
        
        if(mx1+1125>0) {
            mx1 = mx1 - 1;
            mountain1.setBounds(mx1, my1, 1125, 250);
        } else {
            mx1 = 2249;
        }
        if(mx2+1125>0) {
            mx2 = mx2 - 1;
            mountain2.setBounds(mx2, my1, 1125, 250);
        } else {
            mx2 = 2249;
        }
        if(mx3+1125>0) {
            mx3 = mx3 - 1;
            mountain3.setBounds(mx3, my1, 1125, 250);
        } else {
            mx3 = 2249;
        }
        
        if(cloudx1+1000>0) {
            cloudx1 = cloudx1 - 2;
            cloud1.setBounds(cloudx1, cloudy1, 1000, 550);
        } else {
            cloudx1 = 999;
            over = true;
        }
        if(cloudx2+1000>0) {
            cloudx2 = cloudx2 - 2;
            cloud2.setBounds(cloudx2, cloudy1, 1000, 550);
        } else {
            cloudx2 = 999;
        }
        if(cloudx3+1000>0) {
            cloudx3 = cloudx3 - 2;
            cloud3.setBounds(cloudx3, cloudy1, 1000, 550);
        } else {
            cloudx3 = 999;
        }
    }
    
    public static void main(String[] args)
    {
        new Main();
    }
    
    public void removeLogo()
    {
        this.remove(logoLabel);
        this.remove(background);
        this.add(titleLabel);
        this.add(newgameLabel);
        this.add(tutorialLable);
        this.add(quitLabel);
    }
    
    @Override
    public void run() 
    {
        while(true)
        {
            setBackground();
            if(over == true)
            {
                removeLogo();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        int keycode = e.getKeyCode();
        if(over == true)
        {
            if(keycode == KeyEvent.VK_ENTER)
            {
                dispose();
                new Tutorial();
            }
            
            if(keycode == KeyEvent.VK_SPACE)
            {
                dispose();
                new Levelone();
            }
        }
        if(keycode == KeyEvent.VK_ESCAPE)
        {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}