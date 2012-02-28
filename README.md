Orbit
=====

Simulates the motion of a free body in the classical gravitational field of fixed masses.

Usage
-----

Press `Space` to restart the simulation (randomises planet locations and masses). Use the arrow keys to impart a force to the free body. Use `w`, `a`, `s`, and `d` to move the camera independantly of the body. Press `f` to toggle whether the camera follows the body. Use `n` and `m` to decrement and increment the precision (warning: framerate drops if you go too high). Use `j` and `k` to change the colour-depth of the field visualisation. Press `h` to view the help screen, and any key thereafter to return to the simulation. Press `Escape` to quit.

Installation
------------

There's a binary available [here](http://cl.ly/Ec5J) (dated 28/2/12), which can be run with `java -jar orbit.jar` (or by double-clicking on Windows or OS X). The linked binary might not be up-to-date; if not, you might be better off doing a fresh compile:

[Download](https://github.com/asayers/orbit/zipball/master) or [fork][fork] the source, `cd` into the project root directory, and compile it with `ant`. By default the buildfile will create an executable jar in dist/, which can be run with `java -jar orbit.jar`. Enjoy!

Future Versions
---------------

Features which might be implimented:

 - Different planet sprites (to be selected from at random)
 - Ability to add new planets on the fly (by clicking, perhaps)
 - Planet sizes proportional to their mass. Would require adding a little complexity to the collision checker.
 - Better collisions. Ideally the target body should bounce and lose some speed.

License
-------

> This program is free software: you can redistribute it and/or modify
> it under the terms of the GNU General Public License as published by
> the Free Software Foundation, either version 3 of the License, or
> (at your option) any later version.

> This program is distributed in the hope that it will be useful,
> but WITHOUT ANY WARRANTY; without even the implied warranty of
> MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
> GNU General Public License for more details.

> You should have received a copy of the GNU General Public License
> along with this program.  If not, see <http://www.gnu.org/licenses/>

Author
------
 - Alex Sayers (alex.sayers@gmail.com)

[fork]: "`git clone git://github.com/asayers/orbit.git`"
