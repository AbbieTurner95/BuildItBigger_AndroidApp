package android.example.com.javajoker;

import java.util.Random;

public class Joker {

    private String[] computerJokes;
    private String[] normalJokes;
    private Random random;

    public Joker(){
        computerJokes = new String[4];
        computerJokes[0] = "Q: What do you get when you cross a computer with an elephant? " + "\n A: Lots of memory!";
        computerJokes[1] = "Q: Why was the computer cold?" + "\n A: It left it's Windows open!";
        computerJokes[2] = "Q: Why did the computer keep sneezing?+ " + "\n A: It had a virus! ";
        computerJokes[3] = "Q: What do you get when you cross a computer and a life guard?" + "\n A: A screensaver!";

        normalJokes = new String[4];
        normalJokes[0] = "Q: Can a kangaroo jump higher than a house?" + "\n A: Of course, a house doesn’t jump at all";
        normalJokes[1] = "Q: Anton, do you think I’m a bad mother?" + "\n A: My name is Paul.";
        normalJokes[2] = "My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.";
        normalJokes[3] = "Google request: How to disable autocorrect in wife?";

        random = new Random();
    }


    public String tellComputerJoke() {
        return computerJokes[random.nextInt(computerJokes.length)];
    }

    public String tellNormalJoke(){
     return normalJokes[random.nextInt(normalJokes.length)];
    }


}