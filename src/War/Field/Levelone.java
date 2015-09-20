package War.Field;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Levelone extends JFrame implements Runnable,KeyListener,MouseListener
{
    JLabel ground1,ground2,mountain1,mountain2,mountain3,cloud1,cloud2,cloud3;
    JLabel player,propeller, enemyplayer,enemypropeller;
    JLabel status,rocketstatus,enemystatus,enemystatus1,gamestatus,rockavailstatus,gameinfo;
    JLabel rocket,smoke,explosion;
    Thread thread;
    
    int gx1=0,gx2=1350,mx1=0,mx2=1125,mx3=2250,cloudx1=0,cloudx2=1000,cloudx3=2000;
    int gy1=610,my1=550,cloudy1 = 0;
    int hx = 0,hy = 350;
    int health = 100,roc = 80, dest = 0, left = 0;
    int balance = 0;
    
    boolean goup = false, godown = false, goleft = false,goright = false;
    boolean rocketavail = false, pause = false,enemydead = false,gameover = false;
    boolean helicoptermoving = true,levelcomplete = false, stand = false;
    
    Levelone()
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
        this.addMouseListener(this);
        thread = new Thread(this);
        thread.start();
    }   
    
    private void Background()
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
        
        setPlayer(hx,hy);
        
        enemyplayer = new JLabel();
        enemyplayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/enemy.png")));
        //enemyplayer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        enemyplayer.setBounds(1400, 300, 270, 70);
        
        enemypropeller = new JLabel();
        enemypropeller.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/2_front_propeller_anim.png")));
        enemypropeller.setBounds(1405, 290, 150, 40);
        
        rocket = new JLabel();
        rocket.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/rocket.png")));
        
        smoke = new JLabel();
        smoke.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/rocket_smoke.png")));
        
        explosion = new JLabel();
        explosion.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/explosion.png")));
        
        status = new JLabel();
        status.setText("Health >> "+health);
        status.setFont(new Font("David",3, 25));
        status.setBounds(10, 10, 150, 50);
        
        rocketstatus = new JLabel();
        rocketstatus.setText("Rockets "+roc);
        rocketstatus.setFont(new Font("David",3, 25));
        rocketstatus.setBounds(10, 50, 250, 50);
        
        enemystatus = new JLabel();
        enemystatus.setText("Destroyed  "+dest);
        enemystatus.setFont(new Font("David",3, 25));
        enemystatus.setBounds(10, 90, 250, 50);
        
        enemystatus1 = new JLabel();
        enemystatus1.setText("Runaway "+left);
        enemystatus1.setFont(new Font("David",3, 25));
        enemystatus1.setBounds(10, 130, 300, 50);
        
        rockavailstatus = new JLabel();
        rockavailstatus.setText("Rocket Available");
        rockavailstatus.setFont(new Font("David",3, 25));
        rockavailstatus.setBounds(10, 170, 250, 50);
        
        gamestatus = new JLabel();
        gamestatus.setText(" SPACE : pause       C : Hold    R : restart        BALANCE : "+balance+"         CTRL : Shop");
        gamestatus.setFont(new Font("David",3, 25));
        gamestatus.setBounds(250, 690, 1000, 50);
        
        gameinfo = new JLabel();
        gameinfo.setText("Destroy five enemy helicopters");
        gameinfo.setBounds(500, 400, 500, 70);
        gameinfo.setFont(new Font("David",3, 25));
        
        this.add(status);
        this.add(gameinfo);
        this.add(rockavailstatus);
        this.add(rocketstatus);
        this.add(enemystatus);
        this.add(enemystatus1);
        this.add(gamestatus);
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
    
    private void setPlayer(int x, int y)
    {
        player.setBounds(x, y, 260, 100);
        propeller.setBounds(x + 75, y, 200, 40);
    }
    
    private void setenemy()
    {
        Point enemypoint = enemyplayer.getLocation();
        
        if(enemypoint.x+260 == 0)
        {
            left++;
            if(left < 3)
            {
                enemystatus1.setText("Runaway  "+left);
            }
            else
            {
                gameover = true;
                JLabel over = new JLabel();
                over.setText("Game Over");
                over.setFont(new Font("David",3,35));
                over.setBounds(600, 400, 200, 50);
                this.add(over);
            }
        }
        if(enemypoint.x + 260 > 0 && enemydead == false)
        {
            enemyplayer.setBounds(enemypoint.x - 2, enemypoint.y, 270, 70);
            enemypropeller.setBounds((enemypoint.x+5) - 2, enemypoint.y - 10, 150, 40);
        }
        else 
        {
            enemydead = false;
            Random ran = new Random();
            int x = 1400;
            int y = ran.nextInt(700);
            //System.out.println(x+ "\t\t\t\t\t\t\t\t\t"+y);
            enemyplayer.setBounds(x, y, 270, 70);
            enemypropeller.setBounds((x+5), y-10, 150, 40);
        }
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
    
    public void checkCollision()
    {
        Point playerpoint = player.getLocation();
        Point enemypoint = enemyplayer.getLocation();
        
        if((playerpoint.y>= enemypoint.y && playerpoint.y< enemypoint.y+70 && playerpoint.x + 260 == enemypoint.x) ||
                (enemypoint.y >=playerpoint.y && enemypoint.y < playerpoint.y+70 && playerpoint.x + 260 == enemypoint.x) ||
                (playerpoint.y == enemypoint.y+70 && playerpoint.x>= enemypoint.x && playerpoint.x < enemypoint.x+260) ||
                (playerpoint.y == enemypoint.y+70 && enemypoint.x>= playerpoint.x && enemypoint.x < playerpoint.x+260) ||
                (enemypoint.y == playerpoint.y+70 && playerpoint.x>= enemypoint.x && playerpoint.x < enemypoint.x+260) ||
                (enemypoint.y == playerpoint.y+70 && enemypoint.x>= playerpoint.x && enemypoint.x < playerpoint.x+260))
        {
            health = 0;
            status.setText("Health >>"+health);
            enemydead = true;
            this.remove(player);
            this.remove(propeller);
            this.remove(enemyplayer);
            this.remove(enemypropeller);
            
            explosion.setBounds(playerpoint.x-400, playerpoint.y, 1400, 150);
            this.add(explosion);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Levelone.class.getName()).log(Level.SEVERE, null, ex);
                }
            //this.remove(explosion);
            
            gameover = true;
            JLabel over = new JLabel();
            over.setText("Game Over");
            over.setFont(new Font("David",3,35));
            over.setBounds(600, 400, 200, 50);
            this.add(over);
        }
        
        if(rocketavail == true)
        {
            Point rock = rocket.getLocation();
            
            if(rock.y >=enemypoint.y && rock.y < enemypoint.y+70 && 
                    (rock.x == enemypoint.x || rock.x+1 == enemypoint.x || rock.x-1 == enemypoint.x ||
                    rock.x+2 == enemypoint.x || rock.x-2 == enemypoint.x ||
                    rock.x+3 == enemypoint.x || rock.x-3 == enemypoint.x ||
                    rock.x+4 == enemypoint.x || rock.x-4 == enemypoint.x ||
                    rock.x+5 == enemypoint.x || rock.x-5 == enemypoint.x ||
                    rock.x+6 == enemypoint.x || rock.x-6 == enemypoint.x ||
                    rock.x+7 == enemypoint.x || rock.x-7 == enemypoint.x ||
                    rock.x+8 == enemypoint.x || rock.x-8 == enemypoint.x))
            {
                
                this.remove(rocket);
                this.remove(smoke);
                rocketavail = false;
                enemydead = true;
                
                explosion.setBounds((enemypoint.x - 400), enemypoint.y, 1400, 150);
                this.add(explosion);
                
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Levelone.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.remove(explosion);
                rockavailstatus.setText("Rocket Available");
                dest++;
                if(dest < 5)
                {
                    enemystatus.setText("Destroyed "+dest);
                }
                else
                {
                    gameover = true;
                    JLabel over = new JLabel();
                    over.setText("Level 1 Completed Press ENTER to Next");
                    over.setFont(new Font("David",3,35));
                    over.setBounds(400, 400, 800, 50);
                    this.add(over);
                    levelcomplete = true;
                    //helicoptermoving = true;
                    //this.remove(player);
                    //this.remove(propeller);
                    this.remove(enemyplayer);
                    this.remove(enemypropeller);
                }
            }
        }
    }
    
    public void setRocket(int x, int y)
    {
        if(x < 1400)
        {
            rocket.setBounds(x, y, 30, 10);
            smoke.setBounds(x, y, 60, 10);
            rockavailstatus.setText("  ");
        }
        else
        {
            rocketavail = false;
            rockavailstatus.setText("Rocket Available");
        }
    }
    
    public void moveHelicopter()
    {
        Point playerpoint = player.getLocation();
        playerpoint.x = playerpoint.x + 2;
        if(levelcomplete == false)
        {
            if(playerpoint.x <= 300)
            {
                setPlayer(playerpoint.x, playerpoint.y);
            }
            else
            {
                helicoptermoving = false;
            }
        }
        else
        {
            if(playerpoint.x <= 1450)
            {
                setPlayer(playerpoint.x, playerpoint.y);
            }
            else
            {
                helicoptermoving = false;
            }
        }
    }
    
    @Override
    public void run() 
    {
        while(true)
        {
            if(helicoptermoving == false)
            {
                if(stand == false)
                {
                    setBackground();
                    this.remove(gameinfo);
                }
                if(gameover == false)
                {
                    setenemy();
            
                    if(goup == true)
                    {
                        Point point = player.getLocation();
                        setPlayer(point.x, point.y - 2);
                    }
                    if(godown == true)
                    {
                        Point point = player.getLocation();
                        setPlayer(point.x, point.y + 2);
                    }
                
                    if(goleft == true)
                    {
                        Point point = player.getLocation();
                        setPlayer(point.x - 2, point.y);
                    }
                
                    if(goright == true)
                    {
                        Point point = player.getLocation();
                        setPlayer(point.x + 2, point.y);
                    }
                
                    if(rocketavail == true)
                    {
                        Point rocketpoint = rocket.getLocation();
                        setRocket(rocketpoint.x + 8, rocketpoint.y);
                    }
            
                    checkCollision();
                }
            }
            else
            {
                moveHelicopter();
            }
            if(levelcomplete == true)
            {
                moveHelicopter();
            }
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
        
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        int keycode = e.getKeyCode();
        if(keycode == KeyEvent.VK_ESCAPE)
        {
            System.exit(0);
        }
        
        if(keycode == KeyEvent.VK_UP || keycode == KeyEvent.VK_W)
        {
            goup = true;
        }
        
        if(keycode == KeyEvent.VK_DOWN || keycode == KeyEvent.VK_S)
        {
            godown = true;
        }
        
        if(keycode == KeyEvent.VK_LEFT || keycode == KeyEvent.VK_A)
        {
            goleft = true;
        }
        
        if(keycode == KeyEvent.VK_RIGHT || keycode == KeyEvent.VK_D)
        {
            goright = true;
        }
        
        if(keycode == KeyEvent.VK_SPACE)
        {
            if(pause == false)
            {
                pause = true;
                thread.stop();
            }
            else
            {
                pause = false;
                thread = new Thread(this);
                thread.start();
            }
        }
        
        if(keycode == KeyEvent.VK_R)
        {
            dispose();
            new Levelone();
        }
        
        if(keycode == KeyEvent.VK_C)
        {
            stand = !stand;
        }
        
        if(levelcomplete == true)
        {
            if(keycode == KeyEvent.VK_ENTER)
            {
                dispose();
                new Score(balance,dest,left,roc,1,0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        int keycode = e.getKeyCode();
        
        if(keycode == KeyEvent.VK_UP || keycode == KeyEvent.VK_W)
        {
            goup = false;
        }
        
        if(keycode == KeyEvent.VK_DOWN || keycode == KeyEvent.VK_S)
        {
            godown = false;
        }
        
        if(keycode == KeyEvent.VK_LEFT || keycode == KeyEvent.VK_A)
        {
            goleft = false;
        }
        
        if(keycode == KeyEvent.VK_RIGHT || keycode == KeyEvent.VK_D)
        {
            goright = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        if(gameover == false)
        {
            int mousecode = e.getButton();
            if(mousecode == MouseEvent.BUTTON1 && rocketavail == false)
            {
                int plx = (int)player.getLocation().getX();
                int ply = (int)player.getLocation().getY();
                this.add(rocket);
                this.add(smoke);
                roc--;
                if(roc > 0)
                {
                    rocketstatus.setText("Rockets "+roc); 
                    setRocket(plx + 100,ply + 70);
                }
                else
                {
                    rocketstatus.setText("Out of Stock"); 
                }
            }
            else 
            {
                
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        int mousecode = e.getButton();
        if(mousecode == MouseEvent.BUTTON1)
        {
            rocketavail = true;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
