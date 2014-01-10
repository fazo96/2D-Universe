# 2D-Universe
A simple game like program aimed to simulate a 2D universe. Made using LibGDX. Currently it's very early, it's just a few lines of code. I suggest trying it!

## Features
A video is worth more than a thousant words: http://youtu.be/XNm_BNWc7XM
You can spawn circular particles that are attracked to each other in a 2D world.
Resizable window! (Every game should have this)
A simple collision system. When two particles collide, the big one absorbs the smaller one.
You can move the camera around.
## Usage
Once you start it there's a black screen. You can resize the window as you wish.
Try clicking to spawn a particle. You can click and drag to decide where the particle will go and how fast.
Try using WASD to move the camera. If you press R everything will be erased (you can use this instead of restarting)

# How to import in Eclipse
## From scratch
First download libgdx (http://libgdx.badlogicgames.com/download.html) if you don't have it already. I just use the latest release (not nightly).
You will need to know how to set up a basic LibGDX project. You can learn it in the documentation of the library (https://github.com/libgdx/libgdx/wiki).
Just use gdx-setup-ui to create an eclipse project, then delete the .java source files automatically generated and pull this repo inside the main project folder.
You will need a libgdx desktop starter class/project to try the program. It is automatically generated when you use gdx-setup-ui.
## Faster way
Download this (https://www.mediafire.com/?pzztvpc7dc12663) which is a zip folder with all the files needed. Then go into the 2D-Universe directory and 'git pull' so you have the latest commits. You can then import "2D-Universe" and "2D-Universe-Desktop" in eclipse. If you have problems try the first way.

# Licence
Copyright (C) 2013-2014 Enrico Fasoli

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
