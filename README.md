# 🎬 Movie List App

A modern Android app built with Kotlin that displays a list of movies fetched from the Movie Database. The app supports infinite scrolling using pagination and leverages best practices such as MVVM architecture, Hilt for dependency injection, and Jetpack Compose for UI.

---

## ✨ Key Features

- 📃 Displays a list of popular movies with:
  - Title
  - Poster image
  - Average rating
- 🔁 Infinite scrolling with **Pagination** (Paging 3)
- ⚙️ Uses **Hilt** for clean dependency injection
- 🌐 Fetches data using **Retrofit** from the TMDb API
- 🎨 Built with modern **Jetpack Compose** UI (or XML if preferred)
- 🧭 Navigation between screens (e.g., movie list → movie detail)
- 🎬 Static video play

---

## 🧰 Technical Stack

| Layer               | Library / Framework     |
|---------------------|-------------------------|
| Language            | Kotlin                  |
| UI                  | Jetpack Compose         |
| Dependency Injection| Hilt                    |
| Networking          | Retrofit                |
| Pagination          | Paging 3 Library        |
| Architecture        | MVVM                    |
| Image Loading       | Coil                    |

---

## 📦 Setup Instructions

1. Clone the repository:

```bash
git clone https://github.com/nksnandhu/EyMovieApp.git
