In this project, we developed an application that reads ppm files (and png) to shift the pixel densities of each pixel of that ppm / png file. 

Some modifications to the images include : brightening an image, darkening an image, flipping an image horizontally and vertically, and retrieving a greyscale image (red, green, blue, value, intensity, luma). 

Throughout this application, we employed several Object-Oriented principles. For one, we used the MVC (Model, View, Controller Paradigm). Using this pattern allows to build a scalable program as well as making it efficient. 

Our model incorporates a 2d array of pixels, where each pixel has a unique red, green, and blue integer. Some of the methods in the pixel class include getting a pixel's values. Within our model directory we included an interface of modifications as well as individual classes such that we can modify an image's pixels. We apply this method in the ImageModel class, where we return a new instance of that image. 

Our view is quite simple - we simply display a 2d array of all the image's contents in text form (each of the r, g, and b values). 

In our controller directory, we used the command design pattern to make executing commands in the controller easier. Each command links up back to a a versions hash map in our controller class where we manage all versions of the images. In our commands interface, we made an abstract ACommand class since it would be much easier to implement each command class. 

To run the script demonstrating the program's functionality, edit the main method's run configuration and enter the following as the single argument:
src/ImageProcessingScript.txt

The sample image used in our script was pulled from unsplash.com, a website offering free images downloadable for any use.