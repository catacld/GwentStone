
# GwentStone

A 1v1 card game that combines features from some of the most popular
card games on the market. The commands executed by the game are
given by an AI inside a JSON file.

# Cards
There are 3 types of cards:
* **Minion Card**  - is placed on the game board, and can either attack
                     or use its ability(if it has one). Some minion
                     cards can be of a special type, "tank" - if such
                     a card is present on the game board, it must be 
                     attacked first. Attacking or using this type of
                     card's ability does not consume the owner player's mana.
                     Each card can be placed on either the front or the
                     back row.
* **Environment Card** - can not be placed on the game board, has no
                         attack damage and can only use its ability,
                         which consumes the owner player's mana.
* **Hero Card** - each hero card has 30 health, no attack damage, and can
                  only use its ability, consuming the owner player's mana.
                  If the hero is killed, the enemy wins the game.

# Game Setup
The game board has 4 rows, rows 0 and 1 belong to player 2,
and rows 2 and 3 to player 2. There can be no more than 5 cards on
each row.

At the beginning of each game, every player is assigned a randomly
chosen hero card and a number of card decks from which the deck used
for the current game will be selected. Also, the starting player will
be decided.

At the beginning of each round, every player will get a card from
their deck(if it has any cards left) and an amount of mana which
increases by one after each round, but can be no more than 10. A new
round starts when both players ended their turns.

# Commands
The commands can be separated into 3 main categories:

#### **Gameplay Commands**:

* **placeCard** - a card from the player's hand is placed on the game
                  board

* **cardUsesAttack** - card attacks an enemy card

* **cardUsesAttack** - card uses its special ability

* **useAttackHero** - card attacks the enemy's hero

* **useHeroAbility** - player's hero uses its ability

* **useEnvironmentCard** - an environment card from the player's hand
                           uses its ability

* **endPlayerTurn** - the player ends their turn, and all their cards are
                      unfrozen.


#### **Debug Commands**:

* **getCardsInHand** - prints the cards in the player's hand

* **getPlayerDeck** - prints the cards from the player's deck

* **getCardsOnTable** - prints the cards placed on the game board

* **getPlayerTurn** - prints the id of the active player

* **getPlayerHero** - prints the hero of a player

* **getCardAtPosition** - prints the card placed at (x,y) on the
                          game board

* **getPlayerMana** - prints the mana of the player

* **getEnvironmentCardsInHand** - prints the environment cards
                                  available in the player's hand

* **getFrozenCardsOnTable** - prints the cards placed on the game board
                              that are frozen


#### **Statistics Commands**:

* **getTotalGamesPlayed** - prints the total number of games that
                            the players have played

* **getPlayerOneWins** - prints the number of games that player 1 
                         has won

* **getPlayerTwoWins** - prints the number of games that player 2 
                         has won