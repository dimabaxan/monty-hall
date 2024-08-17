package org.dima;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Random;

@Slf4j
public class Main {
    public static void main(String[] args) {
        int simulations = 1000;
        HashMap<Boolean, Integer> results = new HashMap<>();
        results.put(true, 0);
        results.put(false, 0);

        for (int i = 0; i < simulations; i++) {
            boolean switchDoor = i % 2 == 0;
            Result result = playGame(switchDoor);
            if (result == Result.WIN) {
                results.put(switchDoor, results.get(switchDoor) + 1);
            }
        }

        log.info("Results after {} simulations:", simulations);
        log.info("Wins when switching the door: {}", results.get(true));
        log.info("Wins when not switching the door: {}", results.get(false));
    }

    private static Result playGame(boolean switchDoor) {
        Random random = new Random();
        int winningDoor = random.nextInt(3);
        int chosenDoor = random.nextInt(3);

        log.info("Player initially chose door {}", chosenDoor + 1);

        int revealedDoor;
        do {
            revealedDoor = random.nextInt(3);
        } while (revealedDoor == winningDoor || revealedDoor == chosenDoor);

        log.info("Host opens door {} to reveal a goat", revealedDoor + 1);

        if (switchDoor) {
            chosenDoor = 3 - chosenDoor - revealedDoor;
            log.info("Player decides to switch to door {}", chosenDoor + 1);
        } else {
            log.info("Player decides to stick with door {}", chosenDoor + 1);
        }

        if (chosenDoor == winningDoor) {
            log.info("Player wins! The car was behind door {}", winningDoor + 1);
            return Result.WIN;
        } else {
            log.info("Player loses. The car was behind door {}", winningDoor + 1);
            return Result.LOSE;
        }
    }
}