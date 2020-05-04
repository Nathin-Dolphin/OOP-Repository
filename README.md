# OOP-Repository

Programs for Object-Oriented Programming.

[!] IMPORTANT PLEASE READ [!] NOTES: 

	'...' is the path from your storage device to the location of the repository (for Windows terminal at least).
	The program 'Hub' allows quick and easier access to the other programs in the repository.

	[!] The method of compiling and running was done on a Windows computer, if you are using a MAC, the instructions may be confusing. If this is so, anything before the '>' is the terminal on Windows Powershell showing the current directory. After that, it is the actual command, however when doing 'javac' make sure to compile into the 'class' folder from the 'javacode' folder. [!]


## Create Class Files:
At the specified locations in the terminal, enter these commands in order,

	...> cd ...\OOP-Repository\javacode
	...\OOP-Repository\javacode> javac -d '...\OOP-Repository\javacode\class' *.java
	...\OOP-Repository\javacode> cd class

## Execute Program

	...\OOP-Repository\javacode\class> java Hub

## PokemonSearch
	## Version 1.0 Features:
		(Implemented) - Search Pokemon by name, number, type and/or region
		(Implemented) - Implement a list for type and region
		(Implemented) - Print the results to the terminal (for now)
		(Implemented) - Create PokedexWriter to assist in producing the necessary json files

	## Version 2.0 Features:
		(In Prpgress) - Use threading while reading the many json files
		(Implemented) - Print the output to the frame instead of the terminal
		(Implemented) - Finish adding pokemon to the json files
		(Implemented) - Implement method to automatically find and download the needed json files via URLs
		(Implemented) - Print more information about each pokemon
		(Implemented) - Implement option to search by evolution

	## Version 3.0 Features:
		Put PokemonSearch and PokedexWriter into its own repository
		Implement option to search by weaknessess
		Allow PokedexWriter to modify already existing files and add size and weight
		Implement option to search by size and weight