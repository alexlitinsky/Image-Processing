Script commands supported using this application:
1. save - saves a file to a given directory 
   1. Argument 1: File being saved needs to be saved in an applicable directory within the file and has an appropriate file type. (eg. res/sheep.ppm)
   2. Argument 2:The file name being saved needs to have a name in the script.
   3. Example: save Images/sheep.ppm sheep
2. load - loads a file from a given directly
   1. Argument 1: File being loaded needs to be available in a directory.
   2. Argument 2: an applicable name of the file being loaded.
   3. Example: load Images/Sheep.ppm sheep
3. blue-comp - converts an image to a greyscale given the blue component
   1. Argument 1: the file being applied to the command. 
   2. Argument 2: the new name of the file
   3. Example: blue-comp sheep sheep-filter
4. red-comp - converts an image to a greyscale given the red component
   1. Argument 1: the file being applied to the command.
   2. Argument 2: the new name of the file
   3. Example: red-comp sheep sheep-filter
5. green-comp - converts an image to a greyscale given the green component
   1. Argument 1: the file being applied to the command.
   2. Argument 2: the new name of the file
   3. Example: green-comp sheep sheep-filter
6. luma-comp - converts an image to greyscale given the luma component of all three RGB values
   1. Argument 1: the file being applied to the command.
   2. Argument 2: the new name of the file
   3. Example: luma-comp sheep sheep-filter
7. intensity-comp - converts an image to greyscale given the total intensity of the three RGBS values
   1. Argument 1: the file being applied to the command.
   2. Argument 2: the new name of the file
   3. Example: intensity-comp sheep sheep-filter
8. value-comp - converts an image to greyscale given the highest value of the three RGB values
   1. Argument 1: the file being applied to the command.
   2. Argument 2: the new name of the file
   3. Example: value-comp sheep sheep-filter
9. flip-horizontal - flips an image horizontally
   1. Argument 1: the file being applied to the command.
   2. Argument 2: the new name of the file
   3. Example: flip-horizontal sheep sheep-filter
10. flip-vertical - flips an image vertically
    1. Argument 1: the file being applied to the command.
    2. Argument 2: the new name of the file
    3. Example: flip-vertical sheep sheep-filter
11. brighten - brightens an image to a certain level of brightness
    1. Argument 1: an integer representing the brightness factor
    2. Argument 2: the file being applied to the command.
    3. Argument 3: the new name of the file
    4. Example: brighten 10 sheep sheep-filter
12. blur - blurs an image
    1. Argument 1: the file being applied to the command.
    2. Argument 2: the new name of the file
    3. Example: blur sheep sheep-filter
13. sharpen - sharpens an image
    1. Argument 1: the file being applied to the command.
    2. Argument 2: the new name of the file
    3. Example: sharpen sheep sheep-filter
14. sepia - converts an image using a sepia filter
    1. Argument 1: the file being applied to the command.
    2. Argument 2: the new name of the file
    3. Example: sepia sheep sheep-filter
15. greyscale - converts an image using a greyscale filter
    1. Argument 1: the file being applied to the command.
    2. Argument 2: the new name of the file
    3. Example: greyscale sheep sheep-filter
