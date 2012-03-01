Orbit
=====

Simulates the motion of massive bodies using a numerical approximation to Newton's gravitational field equations.

Synopsis
--------

The setup is an infinite 2D space in which can be placed massive bodies. The classical potential field is calculated each frame for the region spanned by the viewport, the calculation being performed with a particular coarseness (the maximum precision is 1 square-per-pixel). The value of the potential in each grid square is scaled and then taken as an RGB888 value, which is used to draw the background. The bodies each recalculate the field locally (in the 4 surrounding squares). This calculation omits the field of the body in question, and also ensures that simulation can continue off-frame. These local fields are then used to calculate accelerations.

Usage
-----

Press `Space` to restart the simulation (randomises planet locations and masses). Use the arrow keys or `w`, `a`, `s`, and `d` to move the camera. Press `Enter` to add bodies randomly, or use the mouse to place them. Press `f` to toggle whether the camera follows the target body. Use `n` and `m` to decrement and increment the precision (warning: framerate drops if you go too high). Use `j` and `k` to change the colour-depth of the field visualisation. Hold `comma` to decrease the speed of the simulation and `.` and `/` to increase it (warning: see bugs section). You can also press `p` to pause it entirely. Press `h` to view the help screen, and any key thereafter to return to the simulation. Press `Escape` to quit.

Installation
------------

There's a binary available [here](http://cl.ly/Edjf) (dated 1/3/12), which can be run with `java -jar orbit.jar` (or by double-clicking on Windows or OS X). The linked binary might not be up-to-date; if not, you might be better off doing a fresh compile:

[Download][zipball] or [fork][fork] the source, `cd` into the project root directory, and compile it with `ant`. By default the buildfile will create an executable jar in dist/, which can be run with `java -jar orbit.jar`. Enjoy!

[zipball]: https://github.com/asayers/orbit/zipball/master
[fork]: # "`git clone git://github.com/asayers/orbit.git`"

Bugs
----

 - At high speeds (or low framerates), approaching bodies tend to slingshot rather than coalesce. This is because rather than move smoothly into a range where they can merge, fast-moving bodies move in jumps (as a result of relatively low framerate). This often means landing deep in a potential well, but not deep enough to coalesce; hence they pick up a lot of energy and sligshot off into space.
 - Despite its name, orbits are rarely stable in this simulation. Due to approximations made, they tend to decay, eventually resulting in coalescence. You can see this for yourself by pressing `o` during the simulation. A binary system will be created which should be stable; alas, it is not.
 - Missing features: there should ideally be a selection of planet sprites. There should be elastic collisions as well as inelastic.

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

