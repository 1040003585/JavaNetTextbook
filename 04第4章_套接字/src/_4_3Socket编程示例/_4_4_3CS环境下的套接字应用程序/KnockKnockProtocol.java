package _4_3Socket编程示例._4_4_3CS环境下的套接字应用程序;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-8/下午10:06:07
 * Description:
 */
public class KnockKnockProtocol {
    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;
    private static final int NUMJOKES = 5;
    private int state = WAITING;
    private int currentJoke = 0;
    private String[] clues = {
        "Turnip", "Little Old Lady", "Atch", "Who", "Who"
    };
    private String[] answers = {
        "Turnip the heat, it's cold in here!",
        "I didn't know you could yodel!",
        "Bless you!",
        "Is there an owl in here?",
        "Is there an echo in here?"
    };
    
    public String processInput(String theInput) {
        String theOutput = null;
        if(state == WAITING) {
            theOutput = "Knock!Knock!";
            state = SENTKNOCKKNOCK;
        } else if(state == SENTKNOCKKNOCK) {
            if(theInput.equalsIgnoreCase("Who's there?")) {
                theOutput = clues[currentJoke];
                state = SENTCLUE;
            } else {
                theOutput = "You're supposed to say \"Who's there?\"! "+"Try again. Knock!Knock!";
            }
        } else if(state == SENTCLUE) {
            if(theInput.equalsIgnoreCase(clues[currentJoke] + " who?")) {
                theOutput = answers[currentJoke] + " Want another?(y/n)";
                state = ANOTHER;
            } else {
                theOutput = "You're supposed to say \"" +clues[currentJoke] + " who?\"" +"! Try again. Knock!Knock!";
            }
        } else if(state == ANOTHER) {
            if(theInput.equalsIgnoreCase("y")) {
                theOutput = "Knock!Knock!";
                if(currentJoke == (NUMJOKES - 1)) {
                    currentJoke = 0;
                } else {
                    currentJoke++;
                }
                state = SENTKNOCKKNOCK;
            } else {
                theOutput = "Bye.";
                state = WAITING;
            }
        }
        return theOutput;
    }
}