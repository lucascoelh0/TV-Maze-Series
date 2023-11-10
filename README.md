# TV Maze Series
This app displays tv shows, and the user can see it's details and add favorites.

To run the project, create a variable API_KEY="your_key_here" on local.properties. 

# Functionalities

### Home screen

Display the tv shows list. 

Has a search bar to search for the show's name

Has a drawer menu to switch between all tv shows or just the favorites

### Details screen

Here the user can see the details related to the show he chose. He can also see an episode's details when clicking on an episode.

**I'd implement a better favorites feature if I were given more time.**

# I've divided the apps in four modules:

**Data**: contains api service files, utils, and models.

**Domain**: contains the business logic of the application. It has the use-cases and repositories.

**App**: contains files related to the UI, mainly activities, composables and view-models. 

**Core**: common logic between modules.

# Libraries used

Jetpack Compose

Hilt for dependency injection.

JUnit and MockK for unit testing.

Compose destinations for navigation.

Retrofit and OkHttp to handle remote calls.

Gson for deserializing.

Coil for image loading.

Network response adapter for remote response handling.

SwipeRefreshLayout for swipe to update.

# Screenshots

![image](https://github.com/lucascoelh0/TV-Maze-Series/assets/61464123/e7135476-a0f9-4fb5-88c0-6cab9f8b5f6a)

![image](https://github.com/lucascoelh0/TV-Maze-Series/assets/61464123/95ee1203-b3fd-410d-a36e-d3b7c42ad246)

![image](https://github.com/lucascoelh0/TV-Maze-Series/assets/61464123/85a43c20-99c0-48c9-87f4-375b7301d6a8)
