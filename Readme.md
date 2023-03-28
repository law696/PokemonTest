### Senior Android Developer Test Project

This is a development task for a Senior Android developer role at Zap-Map. If you have any questions, please email benrosen@zap-map.com.

## The task

A junior  developer wrote a Pokemon app according to the [requirements (described in a following section)](#the-pokemon-app-requirements).

Whilst the app is functional, it is not written in a way that is testable or flexible for future changes.

We would like you to try and improve it by doing the following:

**We completely understand if you do not have time to complete everything, if there was anything you would have done if you had more time, please document it.**

- Make sure all requirements are met correctly, and fix any issues found.
- Refactor the code so it is clean and testable.
- Make sure the code is written so that in the future, we could easily switch to use different 3rd party libraries.
- Make sure the code is written so that in the future, we could easily add a database backup for the Pokemon data.
- Add unit tests, the higher the coverage the better.
- Bonus: make sure the code is written so that the list and details code don't know about each other.
- Document the approach to your changes, and anything else you would change if you had more time.

Rules:

- Don't change any code in the zoogle_analytics package
- Don't add any more functionality, we want the code to be ready for more features, but not for them to be added.
- Keep using the traditional View System. No need for Jetpack Compose in this project

## The Pokemon App Requirements

I would like to be presented with a list of Pokemon names from the https://pokeapi.co/ API and be able to find out some details about them. The API does not require an API key.

**Note, please capitalise the `name` values in the JSON rather than fetching the localised version.**

### Pokemon list

On first opening the app I would like to see a list of Pokemon names.

These will come from the `https://pokeapi.co/api/v2/pokemon` endpoint.

Upon tapping on a Pokemon name I would like to be presented with the Pokemon's details. The Pokemon id selected must be logged in Zoogle Analytics.

The list should have infinite scrolling to go through all the Pokemon names.

### Pokemon details

The Pokemon details page will use the `https://pokeapi.co/api/v2/pokemon/{id}/` endpoint, e.g. https://pokeapi.co/api/v2/pokemon/1/.

This page should show the following details once loaded:
- The Pokemon's name
- A single image of the pokemon from the front (found under `sprites`)
- The Pokemon's "types" e.g. grass
- The Pokemon's weight in kg
- The Pokemon's height in cm

The page should also have some form of back button to go back to the Pokemon list.