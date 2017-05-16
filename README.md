# WeatherAppClean

An Android Clean Architecture app with RxJava (end to end observables) written in Kotlin.

# Clean architecture layers

<p align="center">
    <img src="images/rings.png" alt="cleanrings"/>
</p>

# Architecture overview and rules

<p align="center">
    <img src="images/architecture.png" alt="architecture"/>
</p>


## Build Instructions

In order to run this project, you'll need to setup several things beforehand:

- This application uses the [OpenWeatherMap API](http://openweathermap.org) to obtain information about current weather and forecasts,
you'll need to register and obtain an API Key

- You'll need to set the values found in the [gradle.properties](gradle.properties) file.
This involves the OpenWeatherMap Api Key (`apiToken`)


```
Copyright 2017 Alexandros Koufatzis.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.