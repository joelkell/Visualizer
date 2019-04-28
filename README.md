# Audio Visualizer Project

Name: Joel Kell

Student Number: C17431012

# Description of the assignment

This assignment was to create an audio visualizer using the Java Minim and Processing libraries. 
Variables, loops, methods, arrays, lists, objects, inheritance, polymorphism, trigonometry and the unit circle, were all expected to be used in creating the visualizer.

# Instructions

* Main.java
    * The main class for the program
	* Calls the Visualizer class

* Visualizer.java
    * Extends the PApplet class.
	* Contains the settings, setup and draw methods
	* Creates an arraylist of Buttons which are drawn to screen
	* Contains method to select a song by first creating a FileChooser object
	* Draw methods displays:
	    * Background
		* Buttons
		* Other Ui elements
		* Song Meta Data
		* Spiderman
	* Checks for key presses and mouse clicks on buttons or sliders

* UIElement.java
    * Abstract class
	* Has render and update methods
	* has Pvector with x and y coordinates

* Button.java
    * Abstract Class
	* Extends UIElement class
	* Has is Clicked method

* Slider.java
    * Abstract Class
	* Extends Button

* PlayPause.java
    * Extends Button
	* Draws a Play/Pause button to the screen which changes states depending on whether a song is playing or not

* RewindButton.java
    * Extends Button
	* Draws a Rewind Button to the screen which rewinds the sonf when clicked

* FastForward.java
    * Extends Button
	* Draws a Fast Forward Button to the screen which fast forwards the song when clicked

* ChooseSongButton.java 
    * Extends Button
	* Draws a button to the screen which can be clicked to choose a new song to play
	* Calls the select song method from the Visualizer class when clicked

* Fullscreen.java 
    * Extends Button
	* Draws a button to the screen which changes visualizer to fullscreen when clicked

* VisualizerButton.java
    * Extends Button
	* Draws a button to the screen which changes whether the visualizer is of spiderman or colours when clicked

* VisualizerStyleButton.java
    * Extends Button
	* Draws a button to the screen which changes whether the visualizer is solid or multiple coloured when clicked

* TimeSlider.java
    * Extends Slider
	* Draws the time line of the osngs position to the screen

* VolumeSlider.java
    * Extends Slider
	* Draws a volume slider to the screen which can be dragged up and down to change volume

* FileChooser.java
    * Extends JFrame
	* Opens a JFileChooser window which allows the user to select only an mp3 file
	* a FileChooser object is created in the Visualizer class in which the path of the song chosen is returned

* AlbumArt.java
    * Class which contains method to get album art from an mp3 file
	* The method is passed the path of an mp3, creates a buffered image of the album art if any is available, converts it to a PImage and passes it back to the Visualizer class
	* An AlbumArt object is created in the Visualizer class which calls the getAlbumArt method whenever a new song is chosen

* GetColours.java
    * Class which analyzes an image to find the most common colours
	* Contains an array of ColourFreq objects which it fills with data
	* Has a method which returns an array of the most common colours in an image in R,G,B format to the Visualizer class.
	* Also has a method to return a preset of rainbow colours

* ColourFreq.java
    * Class which contains r,g,b and frequency fields
	* An array of objects of this class is used in the GetColours class to find the most common colours in an image

* QuickSort.java
    * Version of quicksort which is used to sort the array of colours in GetColours class by least to most frequent

* Spiderman.java
    * Extends UIElement
	* Draws a gif of a dancing Spiderman to the screen frame by frame
	* Loads into array images found in data folder

* Circle.java
    * Extends UIElement
	* Draws a circle to the screen and lines from the circle to other circles
	* Circle move to the left on the screen and at a random vertical direction
	* Circle objects are created initially from the Visualizer class, and thereafter from the VBackground class
	* A circle object has a random chance every frame to draw a line from itself to another circle which is added to an array list in both objects
	* The circle objects call the render and update methods on these line objects

* Line.java
    * Extends UIElement
	* Draws a line on the screen from one circle to another

* Fourier.java
    * Creates a fourier transform of the song
	* Has a method which returns the average amplitude of one of the found bands also in the class
	* The values of the amplitudes are used by circle objects to change their radius

* VBackground.java
    * Extends UIElement
	* Draws the background of the visualizer to the screen
	* Creates an array list of VBackgroundBox objects
	* Displays either a solid background or background of VBackgroundBox's
	* Creates more Circle objects which are added to a list of UIElements on the Visualizer class every 5 seconds

* VBackgroundBox
    * Extends UIElement
    * Draws a box to the screen which changes colour to colours in the most common colour array in the Visualizer class

## Compilation and Running
- Run the compile.sh file
- Run the run.sh file

## Using the visualizer
* The visualizer can be used by selecting any mp3 file from our pc.
* First click the choose song button or press the *'P'* key on your keyboard. This will bring up the file chooser nad allow you to select a song from your pc
* After the song has been chosen you can enjoy the visualizer and it's various modes.
* The following hotkeys and buttons can be used to perform various functions
    * *SPACE* or the PLAY/PAUSE Button will play or pause the music
	* *LEFT* or the REWIND Button will rewind the song to the beginning
	* *RIGHT* or the FAST FORWARD Button will fast forward the song
	* *UP* and *DOWN* arrow keys, or dragging the right hand slider will increase or decrease the volume respectively
	* *'P'* Or the Choose Song button will open a menu to choose a new mp3 file
	* *'F'* or the top button on the left hand side of the screen will change the visualiser to fullscreen mode
	* *'T'* or the middle button on the left hand side will change the visualizer type between the colour pallete of the album artwork(if available) and a dancing spiderman
	* *'S'* or the botton most button on the left hand side will change the visualizer style between a solid background and a boxed background when the visualizer is in the colour pallete mode.
	* *ESC* Key can be used to exit the program


# How it works

# What I am most proud of in the assignment

The thing that I am most proud of in this assignment is the algorithm I created which analyzes an image and then gets the most common colours from the image. Using this in conjunction with the visualizer and the file chooser to analyze songs to get the colour pallete from their album artwork turned out really well and is exactly how I hoped it look like. Whenever I listen to music, the colours I envision in my mind are generally those of the album artwork.
If I had had more time to work on the algorithm I would have improved it to make it more accurate and efficient, but regardless I am happy with it's functionality.

# Youtube Link and Screenshots

### Youtube: 
[![YouTube](https://img.youtube.com/vi/VV8DcWIRpRQ/0.jpg)](https://youtu.be/VV8DcWIRpRQ)

### Screenshots:

![An image](images/FileChooser.png)

![An image](images/Initial.png)

![An image](images/Spiderman.png)

![An image](images/RunningFull.png)