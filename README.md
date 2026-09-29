# Assignment 4 - *Popular Actors Browser*

Submitted by: **Caleb Harris**

**Popular Actors Browser** is an Android application that fetches and displays popular actors and media personalities from The Movie Database (TMDB) API, and provides a multi-screen detail page experience using Intents.

Time spent: **10** hours spent in total

## User Stories

The following **required** functionality is completed:

- [x] **Choose any endpoint on The MovieDB API except now_playing**
  - Used the TMDB Popular People endpoint (`/3/person/popular`).
- [x] **Make a request to your chosen endpoint**
  - Successfully fetches data using API key `a07e22bc18f5cb106bfe4cc1f83ad8ed`.
- [x] **Parse through JSON data and implement a RecyclerView to display all entries**
  - Deserialized JSON response with Gson into custom Kotlin data models (`Actor`, `KnownFor`, `PopularPeopleResponse`) and populated a `RecyclerView`.
- [x] **Use Glide to load and display at least one image per entry**
  - Rendered actor profile pictures dynamically in each list item using Glide.
- [x] **Click on an entry to view specific details about that entry using Intents**
  - Implemented an `Intent` to pass selected actor data and launch `DetailActivity`.
- [x] **Detail page includes at least 3 new pieces of data not shown in main view**:
  1. [x] **Popularity Rating** (e.g. `Popularity Rating: 180.5`)
  2. [x] **Gender** (e.g. `Gender: Female`)
  3. [x] **TMDB Actor ID** (e.g. `TMDB Actor ID: 224513`)
  4. [x] **Known For Works List** (Full summary of known movies and TV shows, including media type, release date, and rating)

The following **additional** features are implemented:

- [x] Material Card View styling for clean visual separation.
- [x] Progress Bar loading indicator displayed while fetching data.
- [x] Error handling for network failures.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

*(Add your GIF / screen recording link here)*

## Notes

- Ensuring serializability of nested data structures for Intent extras between activities.
- Formatting floating-point popularity and rating numbers gracefully across different screen sizes.

## License

    Copyright 2026 Caleb Harris

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
