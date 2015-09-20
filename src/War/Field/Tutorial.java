package War.Field;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Tutorial extends JFrame implements Runnable,KeyListener,MouseListener
{
    JLabel ground1,ground2,mountain1,mountain2,mountain3,cloud1,cloud2,cloud3;
    JLabel player,propeller, enemyplayer,enemypropeller;
    JLabel status,rocketstatus,enemystatus,enemystatus1,gamestatus,rockavailstatus,bombavailstatus,gameinfo;
    JLabel rocket,smoke,explosion,enemyrocket,playerexplosion;
    JLabel enemytank,enemybomb,bomb,bomstatus;
    Thread thread;
    
    int gx1=0,gx2=1350,mx1=0,mx2=1125,mx3=2250,cloudx1=0,cloudx2=1000,cloudx3=2000;
    int gy1=610,my1=550,cloudy1 = 0;
    int hx = 0,hy = 350;
    int health = 100,roc = 10, dest = 0, left = 0,bom = 5;
    int balance = 0, explosionheight = 10;
    int dx = 1, dy = 1;
    
    boolean goup = false, godown = false, goleft = false,goright = false;
    boolean rocketavail = false, pause = false,enemydead = false,gameover = false;
    boolean enemyattack = false,stand = false;
    boolean helicoptermoving = true, levelcomplete = false;
    boolean tankavailable = false,enemybombavailable = false,enemybombhit = false,enbomb = true;
    boolean playerbomb = false,playerbombhitground = false;
    
    public Tutorial()
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
        
        if(true)
        {
            BufferedImage blankCursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(blankCursorImg, new Point(0, 0), null);
            this.setCursor(blankCursor);
        }
        
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
        
        enemyrocket = new JLabel();
        enemyrocket.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/rocket.png")));
        
        explosion = new JLabel();
        explosion.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/explosion.png")));
        
        playerexplosion = new JLabel();
        playerexplosion.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/explosion1.png")));
        
        status = new JLabel();
        status.setText("Health >> "+health);
        status.setFont(new Font("David",3, 20));
        status.setBounds(10, 10, 150, 50);
        
        rocketstatus = new JLabel();
        rocketstatus.setText("Rockets "+roc);
        rocketstatus.setFont(new Font("David",3, 20));
        rocketstatus.setBounds(10, 50, 250, 50);
        
        bomstatus = new JLabel();
        bomstatus.setText("Bombs "+bom);
        bomstatus.setFont(new Font("David",3, 20));
        bomstatus.setBounds(10, 80, 250, 50);
        
        enemystatus = new JLabel();
        enemystatus.setText("Destroyed  "+dest);
        enemystatus.setFont(new Font("David",3, 20));
        enemystatus.setBounds(10, 110, 250, 50);
        
        enemystatus1 = new JLabel();
        enemystatus1.setText("left "+left);
        enemystatus1.setFont(new Font("David",3, 20));
        enemystatus1.setBounds(10, 140, 250, 50);
        
        rockavailstatus = new JLabel();
        rockavailstatus.setText("Rocket Available");
        rockavailstatus.setFont(new Font("David",3, 20));
        rockavailstatus.setBounds(10, 170, 250, 50);
        
        bombavailstatus = new JLabel();
        bombavailstatus.setText("Bomb Available");
        bombavailstatus.setFont(new Font("David",3, 20));
        bombavailstatus.setBounds(10, 200, 250, 50);
        
        gamestatus = new JLabel();
        gamestatus.setText(" SPACE : pause       R : restart        BALANCE : "+balance+"         CTRL : Shop");
        gamestatus.setFont(new Font("David",3, 25));
        gamestatus.setBounds(250, 690, 1000, 50);
        
        enemytank = new JLabel();
        enemytank.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/tank_enemy.png")));
        enemytank.setBounds(1400, 500, 500, 300);
        
        enemybomb = new JLabel();
        enemybomb.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/bomb.png")));
        enemybomb.setBounds(1000, 500, 10, 10);
        //enemybomb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        
        bomb = new JLabel();
        bomb.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/bomb.png")));
        
        gameinfo = new JLabel();
        gameinfo.setText("Right Click to activate to Rocket attack");
        gameinfo.setBounds(450, 400, 500, 70);
        gameinfo.setFont(new Font("David",3, 25));
        
        this.add(gameinfo);
        this.add(enemytank);
        this.add(rockavailstatus);
        this.add(bombavailstatus);
        this.add(status);
        this.add(rocketstatus);
        this.add(bomstatus);
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
            enemystatus1.setText("Left "+left);
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
            
            explosion.setBounds(playerpoint.x-400, playerpoint.y, 1400, 100);
            this.add(explosion);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
//                    Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            this.remove(explosion);
            
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
                
                explosion.setBounds((enemypoint.x - 400), enemypoint.y, 1400, 100);
                this.add(explosion);
                rockavailstatus.setText("Rocket Available");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
//                    Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.remove(explosion);
                
                dest++;
                if(dest == 1)
                {
                    gameinfo.setText("Left Click to bomb activate");
                    this.add(gameinfo);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(Tutorial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.remove(gameinfo);
                }
                if(dest == 1 )
                {
                    tankavailable = true;
                }
                if(dest <= 5)
                {
                    enemystatus.setText("Destroyed "+dest);
                }
                else
                {
                    levelcomplete = true;
                    JLabel over = new JLabel();
                    over.setText("Tutorial Completed!!!   Press ENTER");
                    over.setFont(new Font("David",3,35));
                    over.setBounds(400, 400, 800, 50);
                    this.add(over);
                    this.remove(enemyplayer);
                    this.remove(enemypropeller);
                }
            }
        }
        
        if(enemyattack == true)
        {
            Point enemyrocketpoint = enemyrocket.getLocation();
            
            if(enemyrocketpoint.y >=playerpoint.y && enemyrocketpoint.y < playerpoint.y+70 && 
                    (enemyrocketpoint.x == playerpoint.x || enemyrocketpoint.x+1 == playerpoint.x || enemyrocketpoint.x-1 == playerpoint.x ||
                    enemyrocketpoint.x+2 == playerpoint.x || enemyrocketpoint.x-2 == playerpoint.x ||
                    enemyrocketpoint.x+3 == playerpoint.x || enemyrocketpoint.x-3 == playerpoint.x ||
                    enemyrocketpoint.x+4 == playerpoint.x || enemyrocketpoint.x-4 == playerpoint.x ||
                    enemyrocketpoint.x+5 == playerpoint.x || enemyrocketpoint.x-5 == playerpoint.x ||
                    enemyrocketpoint.x+6 == playerpoint.x || enemyrocketpoint.x-6 == playerpoint.x ||
                    enemyrocketpoint.x+7 == playerpoint.x || enemyrocketpoint.x-7 == playerpoint.x ||
                    enemyrocketpoint.x+8 == playerpoint.x || enemyrocketpoint.x-8 == playerpoint.x))
            {
                this.remove(enemyrocket);
                this.remove(player);
                this.remove(propeller);
                enemyattack = false;
                gameover = true;
                
                explosion.setBounds(playerpoint.x-400, playerpoint.y, 1400, 100);
                this.add(explosion);
                JLabel over = new JLabel();
                over.setText("Game Over");
                over.setFont(new Font("David",3,35));
                over.setBounds(600, 400, 200, 50);
                this.add(over);
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
            rockavailstatus.setText("Rocket Available");
            rocketavail = false;
        }
    }
    
    public void checkenemyRocket()
    {
        Point enemypoint = enemyplayer.getLocation();
        Point playerpoint = player.getLocation();
        
        if(enemypoint.y > playerpoint.y && enemypoint.y <= playerpoint.y+70 && 
                enemypoint.y + 60 > playerpoint.y && enemypoint.y + 60 <= playerpoint.y+70)
        {
            enemyattack = true;
            enemyrocket.setBounds(enemypoint.x+30, enemypoint.y+40, 30, 10);
            this.add(enemyrocket);
        }
    }
    
    void setenemyrocket()
    {
        Point enerockpoint = enemyrocket.getLocation();
        enerockpoint.x = enerockpoint.x - 8;
        if(enerockpoint.x > -40)
        {
            enemyrocket.setBounds(enerockpoint.x, enerockpoint.y, 30, 10);
            //smoke.setBounds(x, y, 60, 10);
        }
        else
        {
            enemyattack = false;
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
    
    public void tankmove()
    {
        Point tankpoint = enemytank.getLocation();
        tankpoint.x--;
        if(tankpoint.x > 1000)
        {
            enemytank.setBounds(tankpoint.x, tankpoint.y, 500, 300);
        }
        else{
            //stand = true;
            enemybombavailable = true;
        }
    }
    
    public void enemybombattack()
    {
        Point enemybombpoint = enemybomb.getLocation();
        enemybombpoint.x = enemybombpoint.x - dx;
        enemybombpoint.y = enemybombpoint.y - dy;
        if(enbomb = true)
        {
            if(enemybombpoint.y > -15)
            {
                enemybomb.setBounds(enemybombpoint.x, enemybombpoint.y, 10, 10);
                enemybombcollision();
            }
            else
            {
                enemybomb.setBounds(1000, 500, 10, 10);
                Random ran = new Random();
                dx = 1;
                dy = ran.nextInt(5);
                if(dy == 0)
                    dy=dy+1;
            }
        }
    }
    
    public void enemybombcollision()
    {
        Point enemybombpoint = enemybomb.getLocation();
        Point playerpoint = player.getLocation();
        //System.out.println(playerpoint);
        //System.out.println(enemybombpoint);
        if(enemybombpoint.x > playerpoint.x && enemybombpoint.x < playerpoint.x+270 &&
                enemybombpoint.y > playerpoint.y && enemybombpoint.y < playerpoint.y+70)
        {
            stand = false;
            gameover = true;
            this.remove(player);
            this.remove(propeller);
            explosion.setBounds(playerpoint.x-400, playerpoint.y, 1400, 100);
            this.add(explosion);
            JLabel over = new JLabel();
            over.setText("Game Over");
            over.setFont(new Font("David",3,35));
            over.setBounds(600, 400, 200, 50);
            this.add(over);
            enemybombhit = true;
            this.remove(enemybomb);
        }
    }
    
    public void setplayerbomb(int x, int y)
    {
        if(y <730)
        {
            bomb.setBounds(x, y, 10, 10);
            bombavailstatus.setText("  ");
        }
        else if(x>=1150)
        {
//            enbomb = false;
            //this.remove(enemytank);
            enemytank.setBounds(1400, 500, 500, 300);
            this.remove(enemybomb);
 //           tankavailable = false;
            //stand = false;
            //JOptionPane.showMessageDialog(null, "Tank Destroyed");
            playerexplosion.setBounds(bomb.getLocation().x  , bomb.getLocation().y, 400, explosionheight);
            this.add(playerexplosion);
            this.remove(bomb);
            playerbombhitground = true;
            playerbomb = false;
            bombavailstatus.setText("Bomb Available");
        }
        else
        {
            playerexplosion.setBounds(bomb.getLocation().x , bomb.getLocation().y, 400, explosionheight);
            this.add(playerexplosion);
            this.remove(bomb);
            playerbombhitground = true;
            playerbomb = false;
            bombavailstatus.setText("Bomb Available");
        }
    }
    
    public void setExplosion()
    {
        Point explosionpoint = playerexplosion.getLocation();
        //System.out.println(explosionpoint);
        explosionheight += 5;
        explosionpoint.y -= 5;
        if(explosionheight < 300)
        {
            playerexplosion.setBounds(explosionpoint.x, explosionpoint.y, 500, explosionheight);
        }
        else
        {
            this.remove(playerexplosion);
            playerbombhitground = false;
            explosionheight = 10;
        }
    }
    
    @Override
    public void run() 
    {
        while(true)
        {
           if(helicoptermoving == false)
            {
            //    System.out.println("stand = "+stand);
                if(stand == false)
                {
                    gameinfo.setText("");
                    setBackground();
                }
                if(tankavailable == true)
                {
                    tankmove();
                }
                if(enemybombavailable == true && enemybombhit == false && enbomb == true)
                {
                    this.add(enemybomb);
                    enemybombattack();
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
                    
                    if(playerbomb == true)
                    {
                        Point bombpoint = bomb.getLocation();
                        setplayerbomb(bombpoint.x+5, bombpoint.y+5);
                    }
                    
                    if(playerbombhitground == true)
                    {
                        //System.out.println("Hey");
                        setExplosion();
                    }
                    else
                    {
                        //System.out.println("Bye");
                    }
            
                    checkCollision();
                    checkenemyRocket();
                    if(enemyattack == true)
                    {
                        setenemyrocket();
                    }
                }
            }
            else
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
            dispose();
            new Levelone();
        }
        
        if(keycode == KeyEvent.VK_UP || keycode == KeyEvent.VK_W)
        {
            goup = true;
        }
        
        if(keycode == KeyEvent.VK_DOWN || keycode == KeyEvent.VK_S)
        {
            godown = true;
        }
        
        if(keycode == KeyEvent.VK_LEFT)
        {
            goleft = true;
        }
        
        if(keycode == KeyEvent.VK_RIGHT)
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
        
        if(levelcomplete == true || gameover == true)
        {
            if(keycode == KeyEvent.VK_ENTER)
            {
                dispose();
                new Main();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        int keycode = e.getKeyCode();
        
        if(keycode == KeyEvent.VK_UP)
        {
            goup = false;
        }
        
        if(keycode == KeyEvent.VK_DOWN)
        {
            godown = false;
        }
        
        if(keycode == KeyEvent.VK_LEFT)
        {
            goleft = false;
        }
        
        if(keycode == KeyEvent.VK_RIGHT)
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
            if(mousecode == MouseEvent.BUTTON1 && roc > 0 && rocketavail == false)
            {
                int plx = (int)player.getLocation().getX();
                int ply = (int)player.getLocation().getY();
                this.add(rocket);
                this.add(smoke);
                roc--;
                rocketstatus.setText("Rockets "+roc); 
            
                setRocket(plx + 100,ply + 70);
            }
            else
            {
                rocketstatus.setText("Rocket Out of Stock"); 
            }
            
            if(mousecode == MouseEvent.BUTTON3 && playerbomb == false && bom > 0)
            {
                bom--;
                bomstatus.setText("Bombs  "+bom);
                int bombX = (int)player.getLocation().getX();
                int bombY = (int)player.getLocation().getY();
                this.add(bomb);
                setplayerbomb(bombX + 100,bombY + 70);
            }
            else
            {
                bomstatus.setText("Bomb out of stuck");
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
        if(mousecode == MouseEvent.BUTTON3)
        {
            playerbomb = true;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
