# Description of General Idea

The project will be a fantasy draft game for the S.League, Singapore’s local professional football league. The game will allow users to create an account, and join a fantasy league with up to three other users over the internet. The project will also allow users to play a simulated season of the league, instead of waiting for the actual season to conclude. A graphical user interface will be implemented, which will look different depending on the stage of the game. The game is split into four stages, the setup, the draft, the season, and the results.

## Phase 1: The Setup

The setup process of the game presents the user with a GUI that prompts the user to create an account, with a username and password unique to that user. This will be used to access any of the fantasy leagues they have created or are participating in. Once an account is created, the user will have four options; to check the rules of the league, to access any of their existing leagues, to start their own league, and to join an existing league. 
	
## Phase 2: The Draft

Once at least one player has joined a league, the initial creator of the league will have the option to start the league. Once the host starts the league, the draft process will begin. Due to the limitation of this project, the draft will require all players to be present and online during the draft process. On their homepage with the three original options, players will be notified that the draft is going to occur for one of their leagues. Once all players involved in the league access the league undergoing a draft, the draft will begin. In a fair random order, players will choose players to draft onto their teams. The selection screen will allow all players to see in real-time, which players are still available, their position, their team, photo and basic statistics. Players will draft until their teams are full, with the bench included, and will be prompted to fill positions that are not filled. 

## Phase 3: The Game

Once all players have draft their teams, an entire season will be simulated with a prepared set of statistics for an entire season. For the purposes of this utilization of the program, the next week of the league will only proceed once all players have confirmed that they have made the necessary changes that they desired. If the data was sourced from the actual source itself (real S.League statistics), then the next week of the league would simply proceed as the next week actually started. Each team accumulates points by finishing as high as possible on a weekly leaderboard. The performance of all the players on a player’s team create a weekly score that is then compared to the scores of other players. The first player for the week will earn the maximum number of points, with each subsequent player earning one less point, with the last player earning 1 point. A set of rules will define how points are counted. During each week, players will have the option of swapping their starting lineup and strategy for the subsequent week. This allows the league to simulate strategy. The starting lineup scores additional points at a fixed multiplier, and choosing from a set of strategies allows for additional multipliers applied to that specific strategy (ex. Offensive, defensive, etc). This repeats itself until the end of the league, with no knockout phase.

## Phase 4: Conclusion of Game

Once the league concludes, the program will notify all players of the team rankings, and a set of interesting statistics for their team. These might include a score breakdown (how many times they ranked first, second, third, etc) and score source breakdown (where their points came from, goals, saves, etc)

# Important Features
## Update Results from Data Files
The program should be able to read input files detailing the match results for each week of the league’s season. It is anticipated that these input files will be in .csv format, which will simplify their creation and processing, since they are just plain text files with data delimited by comma characters. Other file formats may be experimented with, if it is found that .csv files present particular difficulties. One issue that may be a problem is that .csv files are only able to represent data in 2 dimensions, and thus not able to offer a rich enough structure to store complex league result array-based data by day, by match, by team and by player.

## Multiplayer
Multiplayer functionality is integral to our game, since there are no AI’s available for users to compete with. The multiplayer functionality should not be overly difficult to implement, as it does not require any type of low-latency, real-time communication. In some sense, each user can be seen to be playing a game individually, and their current score is compared with that of other users in the league, via a leaderboard. However, there are still some shared elements within the game, such as the players chosen for each team (players cannot appear in multiple teams of the same league).
Since multiplayer leagues will be accessed using a shared code, there will be no sophisticated security measures available to prevent strangers from accessing a league that they have not been invited to. This should not be a problem though, as it seems unlikely that the game would be subject to any hacking attempts.

## GUI
Most parts of the client GUI will comprise of simple buttons and text fields. However, the GUI for team selection will include images of the players, as well as tables of their statistics (e.g. height, weight, position). Selection of the players may be done via drag-and-drop selection or by clicking on the images of two players to be swapped. No animations are necessary in any part of the GUI. Swing will be used for programming the GUI.

## Algorithms for drafting order and scoring scheme
The final algorithms for drafting order and scoring scheme will be decided after analysing those of other fantasy league games. The chosen algorithms should not be overly complicated, to ensure that all target users can quickly understand and play the game.

The initial drafting order will probably be selected randomly, and continue using this order until all positions in the league’s teams are filled. An alternative to this would be to use a random order through every round of player selection, to increase the relative importance of luck in the game, which may increase the excitement of the drafting rounds for users. Whether or not users will be required to select players of specific positions (e.g. at least one goalkeeper per team) remains to be decided.

The scoring system will be based primarily on goals scored, but should also attribute points fairly to defensive players.

## Client-Server Communication
The client-server link does not require any type of low-latency, real-time communication. In fact, since communication between clients and servers is required only to update scores and team selections, a delay of even a few seconds when sending data is probably acceptable. This would give our game a greater appeal in the general public, as users with slow Internet connections would still enjoy a satisfactory game experience.

The client-server communication will take place almost solely via data synchronisation operations. Hence, the necessary communication between client and server may be accomplished via only file transfer operations. This should be relatively straightforward to implement, especially if the files are in a standard format like .csv.

# Timeline

## Week 10: Research and Gameplay
Sourcing of all relevant information: Players’ information, statistics etc. and creating a database for future use

## Week 11: Back-end
Finalizing user experience and game flow (The process of creating a game, joining a game, changing line up etc.)
Creating algorithms for drafting order and scoring scheme
Enable data to be pulled from database

## Week 12: Back-end, start on front-end (Class Presentation on Friday or the following Monday)
* Work on coming up with a MVP for the presentation
Creating basic network: sockets and client-server interactions
Create basic GUI
Do slides for class presentation

## Week 13: Back-end and front-end
Improve on MVP
Work on making the GUI easy to use and accessible 
Troubleshoot 
Usability tests

## Reading Week: Testing and Troubleshooting
Make changes based on usability tests
Troubleshoot again

## Exam Week (Final Submission on Tues)
Final minor touches to be done

# Suggested Libraries
java.swing, java.awt, java.util, java.lang, java.net, java.io, javax, libgdx, Ice, SLF4J, apache-commons, JUnit

