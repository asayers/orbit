Orbit
=====

Simulates the motion of a free body in the classical gravitational field of fixed masses.

Instructions
------------

Press `Space` to restart the simulation (randomises planet locations). Use the arrow keys to impart a force to the free body. Use `w`, `a`, `s`, and `d` to move the camera independantly of the body. Press `f` to toggle whether the camera follows the body. Use `n` and `m` to decrement and increment the precision (warning: framerate drops if you go too high). Press `h` to view the help screen, and any key thereafter to return to the simulation. Press `Escape` to quit.

Usage
-----

Download the source, `cd` into the project root directory, and compile with `ant`. Unfortunately the buildfile is a bit of a mess, and requires Eclipse to be installed in /usr/share/eclipse. If you don't want to use Eclipse, or have it installed elsewhere, feel free to modify (a pull request would be welcome!) By default the buildfile will create an executable jar in dist/, which can be run with `java -jar orbit.jar`. Enjoy!

Future Versions
---------------

Features to be implimented:

 - Different planet sprites (to be selected from at random)
 - Ability to add new planets on the fly (by clicking, perhaps)
 - Planet sizes proportional to their mass. Would require adding a little complexity to the collision checker.
 - Better collisions. Ideally the target body should bounce and lose some speed.

Author
------
 - Alex Sayers (alex.sayers@gmail.com)

