package etiennedesticourt.cravenkings.Combat.Engine.Game.AI;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

import etiennedesticourt.cravenkings.Combat.Engine.Game.Player;

public class Spawner implements Runnable {
    private Player computer;
    private boolean run;
    private final int DELAY = 10000;

    public Spawner(Player computer) {
        this.computer = computer;
    }

    @Override
    public void run() {
        Random rand = new Random();


        while (run){

            int  n = rand.nextInt(3) + 1;

            try {
                if (n == 1) {
                    computer.spawnKnight();
                }
                else if (n==2) {
                    computer.spawnArcher();
                }
                else {
                    computer.spawnMage();
                }

            }
            catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(DELAY);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void start(){
        run = true;
        new Thread(this).start();
    }

    public void stop(){
        run = false;
    }
}
