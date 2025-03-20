# Online-Crazy-Eights

## Game description

### What is Crazy-Eights?

Crazy Eights is a card game for two to seven players. Players are given cards at the start of the game and then they take turns playing them so that they match the top card in either rank or suit. If a player plays an Eight, they can declare the next suit. The game continues until a player reaches the winning score threshold. The game can consist of multiple rounds, a round ends when player gets rid of all of his cards.

---
## How to play?

### Rules

1. Each player is dealt 5 cards (or 7 in a two-player game).
  
2. The top card from the deck is placed face-up to start the stockpile.

3. Players take turns matching the top card by rank or suit.

4. If a player cannot play, they must draw a card.

5. Players can play an 8 at any time, which allows them to choose the next suit.

6. Points are earned by winning rounds. After a round ends, other players card's are checked 
  and scores are added as follows:
    - Each 8 is worth 50 points.
    - Each ACE is worth 1 point.
    - Each 10, J, Q, K is worth 10 points.

7. The first player to reach the winning score is declared the ultimate winner.

### Commands

1. `PLAY <one-based index of card> <suit>` - Plays a card. If playing an Eight, specify the new suit. Possible options: HEARTS, DIAMONDS, CLUBS, SPADES (lowercase also accepted)

2. `DRAW` - Draws a card from the deck

3. `CHAT <message>` - Sends a chat message to all players.

4. `START` - Lets any player start the game

Example:
```
PLAY 2               // plays 2nd card
PLAY 5 HEARTS        // plays 5th card, which is 8 and specifies what suit to change to
DRAW                 // draws card
CHAT Hello everyone! // sends "Hello everyone!" 
```

### Gameplay loop

1. Players join the game.

2. Any player starts the game using START command.

3. Players take turns playing, drawing, and chatting.

4. When a round ends, scores are calculated.

5. The game continues until a player reaches the winning score.

6. The game announces the winner and shuts down.

---
## How to start the game?

### Required installations

Ensure you have the following installed before running the game:

- Java Development Kit (JDK 23 or later) - required to run the Java program

- Apache Maven - Required to compile and run the game using Maven commands

- Command prompt or terminal (also an java IDE is possible)

### Game setup

1. Open up your command prompt or terminal

2. Go to the file Online Crazy Eights

3. Compile the program using:

```
mvn compile
```

4. To run the server, run:

```
mvn exec:java@run-server
```

- Once the game ends, the server will shutdown, however, if you wish to shut it down earlier, run:

```
CTRL + C
```

5. To run the client, run:
   
```
mvn exec:java@run-client
```

6. If you wish to participate with more players, open up more terminals and do the same

---
### Features

- Chat functionality - players can chat at the time

- Automatic game reset - after a rounds ends, a new one is being started already.

### Possible improvements

Although I'm quite happy how the program turned out, there is always room for improvement. Currently, I just implemented the classic version of the game, but there are different variations like Queens skip, Aces reverse direction or Draw 2. Another improvement could be to implement simple bots in the game.

---
### Author

Oliver Tomáš Cenker

### Event

This game was created during the winter semester of 2024/25 as part of my studies at Charles University, as a credit program for Programming in Java.

---
### License

This project is open-source and free to use.
