# PokeAPIDisplayer

## Code Walkthrough
-  MainActivity.kt 
  - Used to Display the PokemonListApp composable
- ui/PokemonListApp.kt
  - Contains the main composable that displays contents on the screen (top bar and PokemonListScreen composable)
- ui/PokemonViewModel.kt
  - ViewModel for PokemonListApp.kt
  - Holds UI data, and communicates with the data layer

## App Architecture
### Data Layer
Uses Retrofit
Fetches data from PokeAPI
Error handling

## How to build/ run

## Libraries used
- Retrofit
  - Library used to make REST calls to PokeAPI 
- Retrofit Scalar Converter
  - Allows returning JSON results as a String
- Kotlin Serialization
  - Parses JSON from PokeAPI

## User Stories
- I'd like to view a list of Pokemon and their relevant data
- I'd like to select a pokemon to view its detailed data
- I'd like to search for a pokemon by name

## Requirements
- Interface with PokeAPI
- List of pokemon names
- Detailed screen for each Pokemon
  - Image
  - Id
  - Name
  - Weight
  - Height
  - Type
  - Stats
- Search box
- Handle pagination
- Implement error handling
- Tests
