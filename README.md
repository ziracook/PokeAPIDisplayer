# PokeAPIDisplayer

## App Architecture / Code Walkthrough
### Overview
MVVM based architecture
NavHostController used to change between the list screen and detail screen
Data Layer, Repository, View Model, and Views follow unidirectional flow, each layer has specific responsibilities

### Data Layer
Uses Retrofit
Fetches data from PokeAPI
Exposes data to the UI layer
Error handling

### Repository
PokemonRepository.kt
- Sits between UI and Data Layer 
- Allows data to be loosely couped from the ViewModel 
- Used VM Factory and DI to access data in the UI

### View Model
PokemonViewModel.kt 
- Holds UI data, and communicates with the data layer
- Calls to the PokemonRepository to fetch Pokemon

### Views
MainActivity.kt
- Used to Display the PokemonListApp composable

PokemonListApp.kt
- Contains the main composable that displays contents on the screen (top bar and PokemonListScreen composable)
- 
PokemonListScreen.kt
- Endless scrolling list of all pokemon
- Search bar at the top where user can search for a pokemon by name
- Each item can be clicked and the app will navigate to a detail screen

PokemonDetailScreen.kt
- Screen with details about a specific pokemon
- Displays an image loaded from the network and other pokemon stats

### Navigation
PokemonListApp.kt
- Holds NavGraph that routes between screens
- PokemonScreen enum holds an id for each screen

### Networking
PokemonApiService.kt
- Sets up GET calls to PokeAPI
PokemonListResponse.kt and PokemonResponse.kt
- Used to deserialize API responses into usable objects
AppContainer.kt
- Sets up Retrofit classes needed to make API calls (client, logging interceptor, base endpoint)

## How to build/ run
Sync Gradle and run with default config 
Tests run on Junit4

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
- JUnit 4
  - Used for unit testing

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

## Dev Considerations/ Changes If Given More Time
- Include Pokemon abilities and moves on the detail page
- Option to play the Pokemon cry
- Load in multiple sprites that can be swiped between? Or do something fun and animated with them
- Allow users to search an ability/move and display pokemon that have it
- Switch to Roboelectric for testing?
  - May run faster than JUnit
  - Implement Mockito instead of manually mocking objects