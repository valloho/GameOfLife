# Game of Life 
The *Game of Life* is a cellular automaton devised by the British mathematician John Horton **Conway** (1937-2020) in 1970.



## Rules
Furthermore, the *Game of Life* is a **two-dimensional grid of square cells**, each of which is in one of two possible states, *live or dead*, (or populated and unpopulated, respectively). Every cell interacts with its eight neighbours (adjacent cells). After each step in the game, the following *rules* apply: 

* Any live cell with fewer than two live neighbours dies, i.e. underpopulation.

   <img width="227" alt="Bildschirmfoto 2022-01-12 um 12 03 04" src="https://user-images.githubusercontent.com/92008916/149128846-48707612-7703-4cf5-a51d-762e989cb0c2.png">
   
* Any live cell with two or three live neighbours lives on to the next generation.

   <img width="215" alt="Bildschirmfoto 2022-01-12 um 12 03 23" src="https://user-images.githubusercontent.com/92008916/149130913-31dfd8fd-6c35-4e5c-aec4-9b6a6cf007bf.png">
* Any live cell with more than three live neighbours dies, i.e. overpopulation.

   <img width="220" alt="Bildschirmfoto 2022-01-12 um 12 03 14" src="https://user-images.githubusercontent.com/92008916/149129631-6be8feb7-174b-454e-89d1-3794628ec0b6.png">
* Any dead cell with exactly three live neighbours becomes a live cell, i.e. reproduction.

   <img width="213" alt="Bildschirmfoto 2022-01-12 um 12 03 30" src="https://user-images.githubusercontent.com/92008916/149131608-b603b54d-d989-464b-b8a5-dcb9abf09bf5.png">



An example of cell interaction:


 ![Game_of_life_pulsar](https://user-images.githubusercontent.com/92008916/149153657-7384faf9-ad97-4561-b5a1-319abdf5d1b3.gif)

### Source
Further information regarding [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway's_Game_of_Life) and its origin.





# More details about our project
## Technology Used
* IntelliJ IDEA (Integrated development environment) 
* Java 
* Gradle (build tool)
* JavaFX (for the GUI)
   * JavaFX SceneBuilder (visual layout tool). This is where you can download [SceneBuilder](https://gluonhq.com/products/scene-builder/)
     
     To connect SceneBuilder with IntelliJ you have to:
     - Download SceneBuilder 
     - IntelliJ -> Settings(Einstellungen/Preferences) -> Languages & Frameworks -> JavaFX -> Path to SceneBuilder (which you have just downladed) -> Apply -> OK
     - Right mouse click on fxml.file (necessary for JavaFX) -> Open in SceneBuilder

## How to start our game
### Step 1:
After you have copied our repository link in IntelliJ you have to run our game by clicking "Gradle" -> "Tasks" -> "Application" -> "Run"

<img width="357" alt="Bildschirmfoto 2022-01-12 um 11 53 41" src="https://user-images.githubusercontent.com/92008916/149127862-6cb2f2d4-5f45-4849-83b6-8061516ebd50.png">


Now you can play our fabulous game!


<img width="801" alt="Bildschirmfoto 2022-01-12 um 11 41 08" src="https://user-images.githubusercontent.com/92008916/149126323-b28f4481-e9b2-4740-ab19-fe8538b45a47.png">

### Step 2:
After you see this window (previous Screenshot), you have the opportunity to make some personal settings by clicking on ***Menu***:

   
   - **Gridsize** - choose between two different grid sizes (50x30, 25x15)
   - **Speed** - choose how fast you want the cells to change from one generation to the next generation (1sec, 0.5sec, 0.1sec)
   - **Color** - choose your favourite color for the cells (black, blue, green, red)
   - **Load**
   - **Save**
   
   !!! Attention: the function *Load* still needs to be implemented in the game !!!
   
   Idea of "Save" setting: after you press the Save Button, the cells which are "alive" will be shown in the arie.txt file as a 1, cells which are "dead" as a 0.
   
### Step 3:

   * Click on a random number of cells (but be aware of the rules to create a lot of next generations) to turn them *alive*
   * Now click on the *Play Button* to start the game and watch how the cells change. The *Play Button* will change to a *Pause Button*. If you would like to see only a single next generation, press the *Forward Button*. If you would like to see the previous generation, press the *Back Button*.
   * To pause the game, press the *Pause Button*

## Classes, Files, Images
* Classes
  * GameOfLifeApp          (includes main method)
  * Cell                   (defines the cell state, depending on the rules,  color settings etc.)
  * SaveLoad               (creates a txt file -> arie.txt to save and load arrays)
  * ArrayConverter         (2D Arrays)
  * StartscreenController (to control Scene Settings, linked with fxml file)
* Files 
   * arie.txt
   * startscreen.fxml
   * styles.css (stylesheet; format settings of scenes)
   * images (import from external sources, used in SceneBuilder)

Our code includes detailed comments. This should make it easier for you to understand all implemented methods, created variables etc. 



