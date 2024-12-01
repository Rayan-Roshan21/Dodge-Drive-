Dodge & Drive
Dodge & Drive is an engaging arcade-style game where you play as a getaway driver. Your goal is to avoid obstacles like banana peels, dodge police cars, and collect fuel tanks to keep the car running as long as possible. Survive the chaos on the road while aiming for the highest score!

Features
Dynamic Gameplay:

Avoid banana peels to preserve your health.
Dodge police cars to prevent instant game over.
Collect fuel tanks to keep your journey going.
Multiple Scenes:

Start screen with user input for name.
Gameplay stage with scrolling backgrounds and animated objects.
Game-over screen with high scores and leaderboard.
Player Interactions:

Intuitive controls for moving left or right.
Countdown and fuel management to heighten strategy.
Audio Effects:

Background music for immersive gameplay.
Sound effects for key events, like collisions and fuel pickups.
Gameplay Instructions
Start the game and enter your name.
Use the A key to move left and the D key to move right.
Avoid colliding with obstacles and police cars.
Collect fuel tanks to keep your car moving.
Try to survive as long as possible and get a high score!
Code Structure
Key Classes
Main:

Initializes the game and manages the primary game loop.
Handles user input, animations, and scene transitions.
fuelClass:

Represents fuel tanks with attributes like position and dimensions.
Handles object movement and collision detection.
policeCar:

Represents police cars that pursue the player.
Handles object movement and position setting.
Dependencies
JavaFX: For GUI elements, animations, and event handling.
Java I/O: For saving and retrieving leaderboard data.
File Overview
Main.java: Entry point of the game, handles logic and scene transitions.
fuelClass.java: Defines properties and behavior of fuel objects.
policeCar.java: Defines properties and behavior of police cars.
How to Run
Install Java Development Kit (JDK) 11 or later.
Download or clone this repository:
bash
Copy code
git clone https://github.com/<your-username>/dodge-and-drive.git
Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
Ensure JavaFX is set up in your environment.
Run the Main.java file to start the game.
Future Enhancements
Add more obstacles and power-ups.
Include difficulty levels for varied gameplay.
Implement multiplayer or competitive modes.
Acknowledgments
Created by Rayan Roshan as part of a high school computer science project under the guidance of Mr. Conway. Special thanks to all the beta testers who helped refine the gameplay experience.
