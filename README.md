# Game of Life 
The *Game of Life* is a cellular automaton devised by the British mathematician John Horton **Conway** (1937-2020) in 1970.



## Rules
The *Game of Life* is a **two-dimensional grid of square cells**, each of which is in one of two possible states, *live or dead*, (or populated and unpopulated, respectively). Every cell interacts with its eight neighbours (adjacent cells). After each step in the game, the following *rules* apply: 

* Any live cell with fewer than two live neighbours dies, i.e. underpopulation.

   <img width="227" alt="Bildschirmfoto 2022-01-12 um 12 03 04" src="https://user-images.githubusercontent.com/92008916/149128846-48707612-7703-4cf5-a51d-762e989cb0c2.png">
   
* Any live cell with two or three live neighbours lives on to the next generation.

   <img width="215" alt="Bildschirmfoto 2022-01-12 um 12 03 23" src="https://user-images.githubusercontent.com/92008916/149130913-31dfd8fd-6c35-4e5c-aec4-9b6a6cf007bf.png">
* Any live cell with more than three live neighbours dies, i.e. overpopulation.

   <img width="220" alt="Bildschirmfoto 2022-01-12 um 12 03 14" src="https://user-images.githubusercontent.com/92008916/149129631-6be8feb7-174b-454e-89d1-3794628ec0b6.png">
* Any dead cell with exactly three live neighbours becomes a live cell, i.e. reproduction.

   <img width="213" alt="Bildschirmfoto 2022-01-12 um 12 03 30" src="https://user-images.githubusercontent.com/92008916/149131608-b603b54d-d989-464b-b8a5-dcb9abf09bf5.png">



### Source
Further information regarding [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway's_Game_of_Life) and its origin.

# More details about our project
## How to start our game
* After you have copied our repository link in IntelliJ you have to run our game by clicking "Gradle" -> "Tasks" -> "Application" -> "Run"
<img width="357" alt="Bildschirmfoto 2022-01-12 um 11 53 41" src="https://user-images.githubusercontent.com/92008916/149127862-6cb2f2d4-5f45-4849-83b6-8061516ebd50.png">


* Now you can play our fabulous game!


<img width="801" alt="Bildschirmfoto 2022-01-12 um 11 41 08" src="https://user-images.githubusercontent.com/92008916/149126323-b28f4481-e9b2-4740-ab19-fe8538b45a47.png">

* After you see this window (previous Screenshot), you have the opportunity to make some personal settings by clicking on ***Menu***:

   
   - **Gridsize** - choose between two different grid sizes (50x30, 25x15)
   - **Speed** - choose how fast you want the cells to change from one generation to the next generation (1sec, 0.5sec, 0.1sec)
   - **Color** - choose your favourite color for the cells (black, blue, green, red)
   - **Load**
   - **Save**
   
   -> The settings *Gridsize 25x15*, *Load* and *Save* still need to be implemented in the game!
   
   Idea of "Save" setting: after you press the Save Button, the cells which are "alive" will be shown in the arie.txt file as a 1, cells which are "dead" as a 0.


