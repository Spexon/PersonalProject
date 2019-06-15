# Personal Project
Problem Statement: Ideally, my project would run bug free and have a well developed GUI with user-friendly features that will make me feel proud on what I've worked on. Realistically my program will have almost no bugs, with comments explaining what certain lines of code do, and keep the users intreagued for a while.

## Outline goals: 
<ul>
  <li> Use GUIs to create a game </li>
  <li> Use java to build a game from scratch </li>
</ul>

## Chart Boundaries: 
<ul>
  <li> limitations to OS </li>
  <li> Don't own a Raspberry Pi </li>
</ul>

## Success Criteria: 
<ul>
  <li> Minimal bugs </li>
  <li> Be proud of what I have created </li>
  <li>Learned about GUIs </li>
</ul>

## Constraints: 
<ul>
  <li> Knowledge in GUIs </li>
</ul>

## Assumptions: 

<ul>
  <li> Everything working on time </li>
</ul>

## Stakeholders: 

<ul>
  <li> Professor </li>
  <li> Classmates </li>
  <li> Potential future employers </li>
</ul>

## Timelines:
<ul>
  <li>6 weeks </li>
</ul>

## Week 1:
Do tutorials to learn about GUIs more, and get a Java project working. (3 hours)
So far I've learned that there are more tutorials for Java and it is a lot easier to get set up for a java project.
I've spend about an hour setting up and trying to figure out how to make eclipse work. Every javaFX program starts at the start() method, and needs to be extended from application for certain things to work. Typing ctrl + shift + o imports all required packages, when importing make sure to import javaFX items, and not awt or anything else. Can use grid.setGridLinesVisible(true) to see gridlines when debugging GUIs.

## Week 2:
Work on a kilobolt tutorial, see if it's what you want.http://www.kilobolt.com/game-development-tutorial.html Integrate your knowledge from the tutorial into code. (2 hours)
I've learned that you can call javaFX applications using javafx.application.Application.launch(className.class); In java, the origin is at the top left of a screen (0,0). When 'moving' a character through a game, you are actually scrolling the background and animating the character to make it look like he's moving. Stopped at Day 4: Figure 2-14.

## Week 3:
Work on unit 2, and integrate that information into your own project (Skip around to things that you dont know). (3 Hours)
You can add your own images into a java project by making an image (in paint), and then setup the image by typing: character = getImage(base, "fileLocation/imageName.png"); When the application starts, the program's background will stay still until the user moves right with their keyboard, then the program scrolls to the left. When creating my background, I used variables, constructors with objects, update() method to allow movement, and getters/setters. Stopped at day 5: Getters and setters.

## Week 4:
Finish up unit 2. (5 Hours) As a general rule when creating a new object, you must create a class for the object, we must create objects using this class in startingClass, call the object's update() method within te run method, and paint the new object in the paint() method. Images are painted in the order they appear, similar to the layers in photoshop. If you have issues with getting your character/background to show up, right click the data folder and press refresh, or go to source/clean. Started to derail from the tutorial, adding my own features along the way. Added a feature where the character image will correspond with the direction the user moves, but is still pretty buggy.

## Week 5:
(4 hours) This week I began working more with my character's functionality, trying to make the snake one object that is used repetitively. I read through the tutorial for creating an enemy and will be using similar features discussed in the tutorial (making a new enemy class which will have other classes inherit from it). I created a constructor for the Snake class so that each link of the snake will have its own dimentions, and will be able to change relative to the direction it is travelling. Eventually new links will be created automatically after the user completes a certain task.

## Week 6:
Additional unit 3 tutorials, if necessary; go back and try to understand the code. Test program out, add necessary comments, javadocs, and spot bugs.
