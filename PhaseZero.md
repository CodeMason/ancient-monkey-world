##### THE ADVENTURE BEGIN #####
##### Phase 0 #####
#### Arianne ####
{{ ![http://stendhalgame.org/wiki/images/3/34/Stendhal_0_65_fuzzy.png](http://stendhalgame.org/wiki/images/3/34/Stendhal_0_65_fuzzy.png) }}
Another framework which is much small scale than Three rings is Arianne.
http://sourceforge.net/projects/arianne/

In the phase Zero, I decided to use Arianne instead of Three rings. Why?

As I'm working in a game company, I've learnt by heart the importance of Game Design document and Workflow. And I also have fews failure attempts in the past as hobbist game developer with small team try to make BIG thing due to lack of these.

### So Why Arianne? ###
Because it's Open source, simple, have enough features, easy to deploy, test, and most important very well documented compare to Three rings.

### Features ###
  * 
  * Stendhal - MORPG featuring hundreds of NPCs and quests
  * Stendhal - Huge and beautiful world to explore
  * Stendhal - Statistics website and Hall of Fame
  * Marauroa - Game server handling client-server communication
  * Marauroa - Database persistence with asychronous access
  * Marauroa - Flexibility of game rules. Apply to drawing boards, card games, PacMan ...
  * Keep It Simple approach
  * Release early, release often
  * Automatic client updates
  * Detailed tutorials to extend Stendhal or get started with Marauroa
  * Supportive development team in IRC

It's wiki with a lot of tutorials of making Quest and NPC, scripting:
http://stendhalgame.org/wiki/

#### The idea ####
In Oriental culture, we all love the legend of Monkey king who traveled to the West and become a Buddha.
http://en.wikipedia.org/wiki/Sun_Wukong
https://www.google.com/search?q=Monkey+King
( Songoku in Japanese )

I compose the idea with wild jungle scenes in fictional oriental - western mixed scenery and theme, and adventures along the jouney.

#### Plan ####
My initial plan for the phase Zero is to create a port to 3d version of the existed game Stendhal with free 3d models and 3d gameplay. Then I let the team go in, try and discuss, learn, design as much as possible before we going futher.

In the first attempt, 3d enviroment and models will use open art website such as:
Blendswap http://blendswap.com
OpengameArt http://opengameart.com

and other resources to quick made up a workable game (client and server) to test and enjoy.

The reusable:
  * Network: Good for small scale game
  * DB: MySQL or integrated H2
  * Almost gameplay: Enities definition, Quest, Scripting
  * They also have a complete website for the game with tutorials, wiki

The different between a 2d and 3d, Arianne an jME3 engine:
  * Replace the Arianne game loop with jME3 states and update loop.
  * Delete the Render task of the game view JPanel
  * Terrain : I have my own tiled Terrain implement in jME3 for almost top-down game.
  * Characters: Use 3D Models of Open Art resource
  * GUI : Use pure swing gui (port to Nifty later)
  * 2d to 3d Gameplay: Map entities, trigger
  * 2d to  3d Picking : I use a simple translation

Then a lot of optimization needs (or can be skipped eventually) but the game should workable after less then 3 weeks.

http://fc09.deviantart.net/fs70/i/2011/004/d/5/monkey_king_by_saryth-d36e92m.jpg?400