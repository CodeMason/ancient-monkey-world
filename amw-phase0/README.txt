#### INTRODUCTION ###
Ancient Monkey World
This is a community project, that mean Open-source and non-profit, code name : Ancient Monkey World. 

#### DISCLAIM ###

- Use code base of Stendhal as the startup.
- Use mainly Open arts!

#### LICENCES ###
- Open sources as New BSD
- Free to use assets as CC for 2D and 3D assets

#### PHASE ZERO PLAN ###

In the phase Zero, I decided to use Arianne instead of Three rings

My initial plan for the phase Zero is to create a port to 3d version of the existed game Stendhal with free 3d models and 3d gameplay. Then I let the team go in, try and discuss, learn, design as much as possible before we going futher.

In the first attempt, 3d enviroment and models will use open art website such as: 

Blendswap http://blendswap.com 

OpengameArt? http://opengameart.com

and other resources to quick made up a workable game (client and server) to test and enjoy.

#### PORT STEPS ###
The reusable:
    Network: Good for small scale game
    DB: MySQL or integrated H2
    Almost gameplay: Enities definition, Quest, Scripting
    They also have a complete website for the game with tutorials, wiki 

The different between a 2d and 3d, Arianne an jME3 engine:
    Replace the Arianne game loop with jME3 states and update loop.
    Delete the Render task of the game view JPanel
    Terrain : I have my own tiled Terrain implement in jME3 for almost top-down game.
    Characters: Use 3D Models of Open Art resource
    GUI : Use pure swing gui (port to Nifty later)
    2d to 3d Gameplay: Map entities, trigger
    2d to 3d Picking : I use a simple translation 
	
	