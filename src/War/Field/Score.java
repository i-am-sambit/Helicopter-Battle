package War.Field;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Score extends JFrame implements Runnable, KeyListener
{
    JLabel ground1,ground2,mountain1,mountain2,mountain3,cloud1,cloud2,cloud3;
    JLabel player,propeller, enemyplayer,enemypropeller;
    JLabel gameinfo;
    
    JLabel title,status1,status2,status3,status4,status5;
    
    Thread thread;
    
    int gx1=0,gx2=1350,mx1=0,mx2=1125,mx3=2250,cloudx1=0,cloudx2=1000,cloudx3=2000;
    int gy1=610,my1=550,cloudy1 = 0;
    int hx = 0,hy = 350;
    int totalscore = 0,rocket = 0, dest = 0, left = 0, level = 0, balance = 0;
    
    Score(int bal, int destroy, int lft, int rock, int lev, int tank)
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
        
        initComp(bal,destroy,lft,rock,lev,tank);
    }
    
    private void initComp(int bal, int destroy, int lft, int rock, int lev, int tank)
    {
        Background(bal,destroy,lft,rock,lev,tank);
        this.setLayout(null);
        this.addKeyListener(this);
        thread = new Thread(this);
        thread.start();
    }   
    
    private void Background(int bal, int destroy, int lft, int rock, int lev, int tank)
    {
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
        
        player = new JLabel("Player");
        player.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/player_helicopter.png")));
        //player.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        
        propeller = new JLabel();
        propeller.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/1_front_propeller_anim_blur.png")));
        
        //setPlayer(hx,hy);
        
        enemyplayer = new JLabel();
        enemyplayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/enemy.png")));
        //enemyplayer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        enemyplayer.setBounds(1400, 300, 270, 70);
        
        enemypropeller = new JLabel();
        enemypropeller.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/2_front_propeller_anim.png")));
        enemypropeller.setBounds(1405, 290, 150, 40);
               
        gameinfo = new JLabel();
        gameinfo.setText("ENTER : Next Level      CTRL : Shop");
        gameinfo.setBounds(500, 700, 500, 70);
        gameinfo.setFont(new Font("David",3, 25));
        
        title = new JLabel();
        title.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/title.png")));
        title.setBounds(400, 150, 700, 100);
        
        dest = destroy;
        left = lft;
        rocket = rock;
        level = lev;
        
        status1 = new JLabel();
        status1.setText("Helicopter Destroyed          "+dest);
        status1.setFont(new Font("David",Font.ITALIC, 30));
        status1.setBounds(500, 300, 500, 50);
        
        status2 = new JLabel();
        status2.setText("Helicopter Runaway            "+left);
        status2.setFont(new Font("David",Font.ITALIC, 30));
        status2.setBounds(500, 360, 500, 50);
        
        status3 = new JLabel();
        status3.setText("Tank Destroyed                "+tank);
        status3.setFont(new Font("David",Font.ITALIC, 30));
        status3.setBounds(500, 420, 500, 50);
        
        totalscore = ((dest*20) - (left*20)) + (tank*10);
        
        status4 = new JLabel();
        status4.setText("SCORE                         "+totalscore);
        status4.setFont(new Font("David",Font.ITALIC, 30));
        status4.setBounds(500, 480, 500, 50);
        
        balance = bal + totalscore * 100;
        
        status5 = new JLabel();
        status5.setText("BALANCE                         "+balance);
        status5.setFont(new Font("David",Font.ITALIC, 30));
        status5.setBounds(500, 540, 500, 50);
        
        this.add(title);
        this.add(status1);
        this.add(status2);
        this.add(status3);
        this.add(status4);
        this.add(status5);
        this.add(gameinfo);
        this.add(enemyplayer);
        this.add(enemypropeller);
        this.add(propeller);
        this.add(player);
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
            cloudx1 = cloudx1 - 1;
            cloud1.setBounds(cloudx1, cloudy1, 1000, 550);
        } else {
            cloudx1 = 999;
        }
        if(cloudx2+1000>0) {
            cloudx2 = cloudx2 - 1;
            cloud2.setBounds(cloudx2, cloudy1, 1000, 550);
        } else {
            cloudx2 = 999;
        }
        if(cloudx3+1000>0) {
            cloudx3 = cloudx3 - 1;
            cloud3.setBounds(cloudx3, cloudy1, 1000, 550);
        } else {
            cloudx3 = 999;
        }
    }

    @Override
    public void run()
    {
        while(true)
        {
            setBackground();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        int keycode = e.getKeyCode();
        if(keycode == KeyEvent.VK_ENTER)
        {
            dispose();
            if(level == 1)
            {
                new Leveltwo(balance,rocket);
            }
            if(level == 2)
            {
                new Levelthree(balance,rocket);
            }
            if(level == 3)
            {
                new Levelfour(balance,rocket);
            }
        }
        
        if(keycode == KeyEvent.VK_SHIFT)
        {
            dispose();
            new Shop();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
