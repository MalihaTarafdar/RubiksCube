# RubiksCube

Some random Rubik's cube programs. These were created during my sophomore year of high school and most are unfinished.

---

## CubeSimulation.java
This is a CLI program that is able to scramble and solve a Rubik's cube. It displays a 2-D visualization of a Rubik's cube with a standard color scheme. 

It also allows for user control:
* `r` = right face clockwise
* `r'` = right face counterclockwise
* `l` = left face clockwise
* `l'` = left face counterclockwise
* `u` = top face clockwise
* `u'` = top face counterclockwise
* `d` = bottom face clockwise
* `d'` = bottom face counterclockwise
* `f` = front face clockwise
* `f'` = front face counterclockwise
* `b` = back face clockwise
* `b'` = back face counterclockwise
* `q` = quit

---

## CubeSolver.ino
This is an Arduino program that is the same as CubeSimulation.java, but it utilizes 6 DC motors to solve a real Rubik's cube. It has not been tested. 
