#### Prelude ####

I cant count how many times people saying that you can not build a MMORPG with just two hands and a computer… That mean no chance a one man army can even write that kind of game, or it going to be a failure. I’m not a fearless programmer nor a naive kid try to impress. I just think I can!

I was from a Database center solution then come to Game industry in a strangest kind of accident :p As I was a pure Java programmer than become a fulltime designer. That’s kind of weird but teach me one thing “You can do extraordinary thing with the right tool”… not mentioned efford, time, knowledge, exprerience and passion…

The right tools weapon I choose is JMonkeyEngine and other opensources project to start to learn.

So did I tell you we can make a MMORPG with just an idea and a few thousand hours of working? Indeed. Here is the story:

### 0) Your idea: ###
Let’s say you have a good idea of a Game… an imagination world that people interact and fight, meet, share… you have a story to tell, and want to draw some impressive scenery or beatiful characters. Spend like a few years for the ideas, may be research and play a lot of games, use papers, talk with friends, travel around the world… Until you have concentrade enough for only one good idea.

### 1) Your skills: ###
Question: I can draw, can compose music, video, can code, can sing, can write script, can do Java stuff… Is that enough?
Answer: Ummh, NO!
MMORPG is a freaking black-hole of knowledges and experiences requirement. No estimatable amount of knowledge consider enough.
But then I tell you is ENOUGH. At least you can do Java stuff and can draw, you can. The other you will learn when you are on the journey.

  * + Programming: For the start, you need to “know” Java, the more the better. If you just write your first “Hello world” program 3 months ago. You are too hurry for a big challenge. Let make it at least 3 year exprience. I have 13 and consider people with much more expriences still facing problems of course. The years does not matter!
  * + Assets: May be you should know how to draw and 3d stuff, the more the better. I recommend if you are at least 1 year with 3D modelling and animating, and should play around with tools. First is Concept. Then other things come quick : Sound, Sprite,…
  * + Researching skill: This is very important if you are a one man army or indie team. You have to do research, brain-storming or ask people every time. So try to be active to get knowledge.

### 2) Your workflow: ###

try Extreme programing: http://en.wikipedia.org/wiki/Extreme_programming

If you never in a professinal workflow before, try to do it simplier as your only have limited man power. Most valuable advice if you’re an artist do programming: Do it like Zen :p
  * - Smart and active: Research first, try to call out for help if need! Opensource are the key.
  * - Flexible but manageable: Try to use SVN.
  * - Shoot with both hands: Do both assets and programming can cause a mesh, do one at a time. After finish review, get approved by yourself or the leader. Continue developing.
  * - Pirate spirit: Use place holder as much as your can. Skip concept, may use existed assets. There are plenty of free assets.
  * - Avoid premature optimization: If still concerning about design, make it work first.
  * … [to come](more.md)

### 3) Your tools: ###
Of course this is big task, Your tools are various from DDC to Image/sound editor… But let’s name a few with zero cost! (mean open-source or free)
  * - JME SDK and its plugins (Programming, database tools… a God-like tool for smart monkey)
  * - GIMP (2d: concept, texture…)
  * - Blender (3d)… and there are a lot of good 3d tools. Blender are the best integrated tool to JME
  * - [assets tool like sound editors, sprite... come later](Other.md)

### 4) Your framework: ###
This is the most important thing that can make your dream possible. So let me speak a bit slowly:
I spend years for researching in this area (MMORPG), I came across WorldForge, Darkstar,… write my own Network engine and related DB stuff using Hypertable, ORM… (bad mislead time)

And finally I found a nice, free but powerful framework: The Threerings project http://www.threerings.net/code/ 2 year ago.

It taken time to research and admit that they do it nicely and scalable (i’m not going to blow it up). If you think you are better than me in reviewing go ahead, i also need valuabe comperations of framework at the moment:

### OOO Core Libraries ###
  * Narya, Nenya, Vilya, and Clyde: a collection of Java tools and frameworks useful for making games, 2D and 3D, networked and otherwise.
  * Getdown: a system for deploying Java-based applications and updating them over the network following installation.
  * Depot: a declarative Java object-relational mapping library that uses EJB3 annotations but is not managed like Hibernate and friends.
  * GWT Utils: a collection of utility and user interface routines that make developing games and applications in GWT more pleasant.
  * Honeybird: a library that makes interacting with the Google Analytics API more pleasant.

So what I tell you that amount of tool are pretty enough for thousand players game. I’m not going to do Three rings advertisment, for short, it’s your chance to build a MMORPG.

What you will see at first that the OOO even support 3D stuff, as some of their developer also contribute in JME version2, then write their own engine. It’s quite bad compare to JME at the moment. So maybe you want to use JME3 to do graphics stuff and other tools for Network. Deploying and DB. AI stuff are often quite difficult to write your own but in the end, I will offer you a choice.

### But is it real you can make a MMORPG game with just that? ###
Of course not. It will take more than thousands of hours to code and to draw, do experiments, fix bugs… This is just advice point out a good way before start your own journey.

People may come up with different levels of knowledge and experience. So here and there, they may want to replace an open-source project by their own library. I also write almost every modules of the architure, but for myself I can not provide enough efforts for an opensource project maintaining. I just can keep bad code, release a few good one and write down articles.

For people who are exciting of community project as MMORPG, may be this time you can gather up. I’m not guaranty that I’m enough of abitily to make it to the end, but at least we have a working base to start with.