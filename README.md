# Mobile Engineer Online Test Submission

This application is developed using Kotlin and Jetpack Compose. Jetpack Compose is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.

## Screenshots

![Eratani Test](https://github.com/rwnhrmwn23/TestEratani/assets/25237512/f88f28b9-399b-4304-80cc-b190b188fb4c)

## Introduction

This repository contains the submission for the Mobile Engineer position at Eratani. The application includes the following features:

1. Word Search
2. Data Processing
3. Animation
4. API Calling

Each feature demonstrates different aspects of mobile application development, including algorithm implementation, data processing, animation, and API interaction.

## Features

### Word Search

This feature allows users to search for a string within a given array of strings. The search is case-insensitive.

- Input: A random string or multiple set array strings.
- Search: A text field to input the search string.
- Response: Indicates whether the input string is present in the array.
- Case Insensitivity: The application detects the input string regardless of case (upper or lower).
- Temporary Data Addition: Users can add data temporarily to the list for searching.

Usage :
1. Navigate to the Word Search section.
2. Enter a string in the search field.
3. The application will display whether the string is present in the array.
4. You can also add data temporarily to the list by entering the string and clicking the "Tambah" button.

### Data Processing

This feature processes a set of data, with the data generated using [Mockaroo](https://mockaroo.com/).

- Case Example: Stock management where daily purchase data is recorded, and stock levels are reduced accordingly. At the end of the month, the remaining stock is calculated.
- Data Source: Dummy data generated from [Mockaroo](https://mockaroo.com/).

Usage :
1. Navigate to the Data Processing section.
2. Click the "Hitung Stok Akhir Bulan" button
3. The application will process the dummy data and display the remaining stock at the end of the month.

### Animation

This feature creates an animation that mimics a heartbeat, with the speed of the beat adjustable based on BPM (Beats Per Minute).

- Animation Type: Heartbeat animation.
- Adjustable Speed: The speed of the heartbeat is adjustable based on BPM.

Usage :
1. Navigate to the Animation section.
2. Adjust the BPM slider to change the heartbeat animation speed.

### API Calling

This feature interacts with an external API to fetch and display user data, and allows for user registration.

- API Endpoint: https://gorest.co.in/public/v2/users
- Methods:
  - GET: Fetches and displays user data in a table (Name, Email, Gender).
  - POST: Provides a form to register a new user (name, email, gender, status).

Usage :
1. Navigate to the User List section.
2. The application will fetch and display user data from the API.
3. To register a new user, fill out the registration form and submit.
4. The last data you input, will be on the top of the list.

## Download APK

You can download the APK of the application from the following link [Download APK](https://github.com/rwnhrmwn23/TestEratani/releases/download/v1.0.0/app-debug.apk)


