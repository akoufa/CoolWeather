# CoolWeather

An Android Clean Architecture app written in Kotlin, using Kotlin Coroutines and Android Architecture Components.

### Presentation Layer

The presentation layer was implemented using:

MVVM with ViewModels exposing LiveData that the Views consume. The ViewModel does not know anything about it's consumers.
It exposes a single source of truth as a LiveData that the consumers can observe to get the events emitted.
These events consist of entities, that are wrapped in a `Result` class that leverages the powerful Kotlin feature of sealed classes.
This enables us to express the different states of the application's individual screens in an concise an expressive manner.

### Domain Layer

The domain layer contains the UseCases that encapsulate a single and very specific task that can be performed. This task is part of the business logic of the application. A UseCase exposes `suspend operator fun invoke` that returns
a `Result` after invocation. The ViewModels of the presentation layer use the UseCases to complete a business case. This layer also contains the Repository
interfaces and the domain entities. These are the basic building blocks of the application and the ones that are least likely to change when something external changes.
That also means that this layer should not depend on any other layer. The other layers must depend and reference the domain layer.

### Data Layer

The data layer implements the repository interface that the domain layer defines. The components in this layer provide a single source of truth for data and hide the origin of the data.
This allows effective caching using a custom cache implementation or/and the Sqlite Db without polluting the other layers with the implementation details.

## Clean architecture layers

<p align="center">
    <img src="images/rings.png" alt="cleanrings"/>
</p>

## Architecture overview and rules

<p align="center">
    <img src="images/architecture.png" alt="architecture"/>
</p>

### Build Instructions

In order to run this project, you'll need to setup several things beforehand:

- This application uses the [OpenWeatherMap API](http://openweathermap.org) to obtain information about current weather and forecasts,
  you'll need to register and obtain an API Key

- You'll need to set the values found in the [gradle.properties](gradle.properties) file.
  This involves the OpenWeatherMap Api Key (`apiToken`)

### License

See LICENSE file
