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
