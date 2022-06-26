# Wallpaper - Application show wallpapers and set to phone

[![Flutter](https://img.shields.io/badge/Made%20with-Kotlin-blue.svg)](https://flutter.dev/) ![NodeJs](https://img.shields.io/badge/Node.js-43853D?style=for-the-badge&logo=node.js&logoColor=white)

## Features

- View wallpapers in many categories
- Download wallpapers
- Set images as phone's wallpaper


## Technology used

- Kotlin
- Firebase
- NodeJs

## Environment

<details>
    <summary>Click to expand</summary>
    <br>

- Install nodejs
- Install npm or yarn
- Make sure you are in /BACKEND
- Run:

```bash
yarn
```

- Or with npm

```bash
npm i
```
- Configure your own .env and mongodb.config.json file follow the example ones

- Then run the following to bootstrap the Server
```bash
yarn dev
```

- Or with npm

```bash
npm run dev
```

</details>

## Setup and run

<details>
    <summary>Click to expand</summary>
    <br>

- Download APK
  - [APK - arm64](https://drive.google.com/file/d/1r5BxLCoTn2JqQuEjAtoUxmZeSHIByEKa/view?usp=sharing)
- Setup and run
  - Flutter
    - Install [Flutter](https://flutter.dev/docs/get-started/install).
    - Using **`stable`** channel:
      ```bash
      ❯ flutter channel stable
      ❯ flutter upgrade
      ```
    - Flutter doctor:
      ```bash
      ❯ flutter doctor
      ```
    - Install all the packages by:
      ```bash
      ❯ flutter pub get
      ```
    - Create .env file `assets/.env` has following structure:
      ```bash
      BASE_URL=https://api.smartfood.cooking/api
      ```
    - Run app on real devices or emulator by:
      ```bash
      ❯ flutter run
      ```
      or debug mode in VSCode or some IDEs

</details>

## Screenshots

|                                                |                                                |                                           |
| :--------------------------------------------: | :--------------------------------------------: | :---------------------------------------: |
|                 Splash Screen                  |                    Sign In                     |                Home Screen                |
|       ![](/img/splash.jpg)       |      ![](/img/login.jpg)       |     ![](/img/category.jpg)      |
|            List wallpaper             |           Detail            |        
| ![](/img/list.jpg) | ![](/img/detail.jpg) | ![](mobile/screenshots/choose_recipe.jpg)

## Contributors✨

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tr>
    <td align="center"><img src="https://avatars.githubusercontent.com/u/58522459?v=4" width="100px;" alt=""/><br /><sub><b>Dang Cong Toan</b></sub></a><br /><a href="https://github.com/ichhoa129/Mobile-Final-Exam/commits/dev?author=toanil315" title="Code">💻</a> <a title="Mobile">📱</a> <a href="https://github.com/ichhoa129/Mobile-Final-Exam/commits/dev?author=toanil315" >
    <td align="center"><img src="https://avatars.githubusercontent.com/u/62462715?v=4" width="100px;" alt=""/><br /><sub><b>Nguyen Tri An</b></sub></a><br /><a href="https://github.com/ichhoa129/Mobile-Final-Exam/commits/dev?author=triandut" title="Code">💻</a> <a title="Mobile">📱</a> <a href="https://github.com/ichhoa129/Mobile-Final-Exam/commits/dev?author=triandut" >
    <td align="center"><img src="https://avatars.githubusercontent.com/u/55626329?v=4" width="100px;" alt=""/><br /><sub><b>Nguyen Ich Hoa</b></sub></a><br /><a href="https://github.com/ichhoa129/Mobile-Final-Exam/commits/dev?author=ichhoa129" title="Code">💻</a><a title="Backend">🔗</a>
  </tr>
  
</table>
