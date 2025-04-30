import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

public class Boss {
    public int bossHealth = 100;
    public int bossx = 0;
    public int bossy = 0;
    private Random rand = new Random();
   

    private BufferedImage phase1, phase2, phase3;
    private GamePanel gp;
    private Player player;

    private int shootCooldown = 0;
    private int shootInterval = 400; // 60 frames
    private List<BossProjectile> projectiles = new ArrayList<>();

    public Boss(GamePanel gp, int bossHealth, int bossx, int bossy, Player player) {
        this.gp = gp;
        this.bossHealth = bossHealth;
        this.bossx = bossx;
        this.bossy = bossy;
        this.player = player;

        try {
            phase1 = ImageIO.read(getClass().getResourceAsStream("/Boss/bossPhase1.png"));
            phase2 = ImageIO.read(getClass().getResourceAsStream("/Boss/bossPhase2.png"));
            phase3 = ImageIO.read(getClass().getResourceAsStream("/Boss/bossPhase3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        shootCooldown--;
        int randomValue = rand.nextInt(5) + 1;
        if (shootCooldown <= 0) {
            if(randomValue == 1) {
        	shootPattern();
            shootCooldown = shootInterval;
            //pat1 = true;
            }
            else if(randomValue == 2) {
            	shootPattern2();
            	shootCooldown = shootInterval;
            }
            else if(randomValue == 3) {
            	shootPattern3();
            	shootCooldown = shootInterval;
            }
            else if(randomValue == 4) {
            	shootPattern4();
            	shootCooldown = shootInterval;
            }
            else if(randomValue == 5) {
            	shootPattern5();
            	shootCooldown = shootInterval;
            }
        }

        projectiles.removeIf(BossProjectile::isOffScreen);
        for (BossProjectile p : projectiles) {
            p.update();
        }
    }

    public void drawBoss(Graphics2D g2) {
        BufferedImage image = null;
 
        
        if (bossHealth >= 70) {
            image = phase1;
        } else if (bossHealth >= 30) {
            image = phase2;
            shootInterval = 300;
        } else if (bossHealth > 0) {
            image = phase3;
            shootInterval = 250;
            
        } else {
            System.out.println("You Win!");
            return;
        }

        g2.drawImage(image, bossx, bossy, 100, 100, null);

        for (BossProjectile p : projectiles) {
            p.draw(g2);
        }
    }

    private void shootPattern() {
        String[] directions = {
            "up", "down", "left", "right",
            "upleft", "upright", "downleft", "downright"
        };

        for (String dir : directions) {
            projectiles.add(new BossProjectile(dir, bossx, bossy, gp, player));
        }
    }
    
    private void shootPattern2() {
    	int tempy = 135;
        String[] directions = {
            "left", "left", "left", "left",
            "left", "left", "left", "left", 
            "left", "left", "left", "left",
            "left", "left", "left", "left" 
        };

        for (String dir : directions) {
            projectiles.add(new BossProjectile(dir, bossx, tempy, gp, player));
            tempy += 20;
        }
    }
    private void shootPattern3() {
    	int tempy = -100;
        String[] directions = {
            "left", "left", "left", "left",
            "left", "left", "left", "left", 
            "left", "left", "left", "left",
            "left", "left", "left", "left" 
        };
        for (String dir : directions) {
            projectiles.add(new BossProjectile(dir, bossx, tempy, gp, player));
            tempy += 20;
            if(tempy >= 150) {
            	tempy = -100;
            }
        }
    }
    private void shootPattern4() {
    	int tempy = -100;
        String[] directions = {
            "left", "left", "left", "left",
            "left", "left", "left", "left", 
            "left", "left", "left", "left",
            "left", "left", "left", "left",  "left", "left", "left", "left",
            "left", "left", "left", "left", 
            "left", "left", "left", "left",
            "left", "left", "left", "left",
        };
        for (String dir : directions) {
            projectiles.add(new BossProjectile(dir, bossx, tempy, gp, player));
            tempy += 20;
            if(tempy == 140) {
            	tempy = 270;
            }
        }
    }
    private void shootPattern5() {
    	int tempy = -300;
        String[] directions = {
            "left", "left", "left", "left",
            "left", "left", "left", "left", 
            "left", "left", "left", "left",
            "left", "left", "left", "left" 
        };
        for (String dir : directions) {
            projectiles.add(new BossProjectile(dir, bossx, tempy, gp, player));
            tempy += 150;
        }
    }

    // Getter/setter methods
    public int getBossHealth() { return bossHealth; }
    public void setBossHealth(int bossHealth) { this.bossHealth = bossHealth; }
    public int getBossx() { return bossx; }
    public void setBossx(int bossx) { this.bossx = bossx; }
    public int getBossy() { return bossy; }
    public void setBossy(int bossy) { this.bossy = bossy; }
}