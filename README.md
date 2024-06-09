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
### Overview
MVVM based architecture
NavHostController used to change between the list screen and detail screen
Data Layer, Repository, View Model, and Views follow unidirectional flow, each layer has specific responsiblities

### Data Layer
Uses Retrofit
Fetches data from PokeAPI
Exposes data to the UI layer
Error handling

### Repository
Sits between UI and Data Layer
Allows data to be loosely couped from the ViewModel
Used VM Factory and DI to access data in the UI

### View Model
Holds UI state for Views
Calls to the PokemonRepository to fetch Pokemon

## How to build/ run

## Libraries used
- Retrofit
  - Library used to make REST calls to PokeAPI 
- Retrofit Scalar Converter
  - Allows returning JSON results as a String
- Logging Interceptor
  - Helps with debugging networking layer issues
- Kotlin Serialization
  - Parses JSON from PokeAPI
- Material
  - Used to create a theme for the app
  - Provides basic UI components
- Coil
  - Loads images from a URL into a composable using AsyncImage
  - Displays the pokemon sprites
  - 

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
