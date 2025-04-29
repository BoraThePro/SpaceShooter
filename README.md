# Space Shooter Game (Java)

A simple Java game where the player controls a spaceship dodging asteroids and collecting power-ups. Built with Java and Swing.

## 💡 Features
- Spaceship controlled with arrow keys
- Falling asteroids with random speed and position
- Collision detection between spaceship and asteroids
- Game Over screen with visual overlay and centered message
- Press 'R' to restart the game after Game Over
- Clean OOP structure using SOLID principles
- Game loop using separate thread for ~60 FPS rendering

## 🧠 Technologies
- Java 17+
- Java Swing (JFrame, JPanel)
- Multithreading (Thread, Runnable)
- AABB Collision

## 🗂️ Structure
- `Main.java` – Entry point
- `GameFrame.java` – Window setup
- `GamePanel.java` – Main game logic & rendering
- `GameObject.java` – Base object class
- `Spaceship.java` – Player ship
- `Asteroid.java`, `PowerUp.java` – Game objects
- `InputHandler.java` – Key inputs

## 🚀 How to Run
Open in Eclipse as a Java Project and run `Main.java`.

## 🔧 TODO
- [x] Add spaceship movement
- [x] Add falling asteroids
- [x] Implement asteroid collision and game over screen
- [x] Restart game with 'R' key
- [ ] Add scoring system (based on time or number of asteroids dodged)
- [ ] Add power-ups (e.g. shield, slow motion, score boost)
- [ ] Add sound effects
- [ ] Add lives system instead of instant game over

