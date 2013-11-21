lol-jclient
===========

A Java implementation of the League of Legends PVP.net Client. [/r/loljclient](http://www.reddit.com/r/loljclient)


*Goal: Create a Java implementation of the League of Legends PVP.net Client.*


## Features
### Login
 * News display with clickable links (the links are readable without opening your browser)
 * Region select
 * Remember Username, Password, and Region and auto-login.

### Chat Client
 * Start & recieve chats with people on your friends list
 * Listens for friends' status changes
 * Show & display your friends' statuses
 * Accept game invites
 * Highlight & be able to click links
 * Set & display your status

### Champions
 * List all champions
 * Filter by owned, free, playable
 * Icon size settable
 * Open champion detail list by double clicking an icon

### Champion detail
 * Display all skins and splash arts
 * Lore and tipps
 * Play champion selection audio

### Profile
 * Recent games with all stats
 * *Autosave recent games so you can see more than 10 games*

### Custom game list
 * Display all custom games
 * Refresh and filter
 * Join and create games

### Custom game lobby
 * Start and leave
 * Switch teams
 * Listen for updates to team select

### Champion Select
 * Pick champion and lock in
 * Change runes, masteries, and spells
 * Starts the game

## Platforms
Tested on Windows 7 & 8 and OS X Mountain Lion. Linux is also supported, except for running the game for now.

## Requirements
On Windows you need to install [swftools](http://www.swftools.org/swftools-2013-04-0
9-1007.exe).

On Mac use Homebrew to install it with `brew install swftools`


## Why create an alternative client to the PVP.net client?
I decided to make one because I was fed up by the multitude of bugs in the official client. Hopefully lol-jclient will provide a bug-free version with more frequent updates.
