# Spot-Ranking

## The initiation of the project

### Scenario

The project is an online notebook that can keep records of places people have traveled before.

- Users can check their travel history and current journeys.
- Traveled places can be categorized by country or continent.
- Users (especially students) can rate and comment on locations.
- Ratings and comments help others decide whether a place is worth visiting.
- Users can search for places, view reviews, and add new locations to the site.
- Places can be rated based on price and overall score.

### Purpose

To make travel planning easier and more efficient. Since people are busy with work and study, their travel time is precious. The website aims to:

- Help users avoid uninteresting destinations.
- Assist in choosing travel spots effectively.
- Maximize enjoyment by minimizing decision-making time.

### Technical Requirements

- Use **H2 database** to store user identity (name and password).
- Include **administrator account** for managing content and removing inappropriate comments.
- Create a **second database** for storing travel spots (description, rating, ranking, comments).
- Use **Spring Boot** to set up the server.
- Develop a **user-friendly interface** with basic **HTML**.

---

## Success Criteria

- More than **15 users** can log in; one must be an administrator.
- Users can **view travel spots** on the website.
- Users can **add/update comments and ratings** (at least 5 per spot).
- Administrators can **delete user content**.
- A **ranking system** is implemented, with at least **2/3 of users agreeing** with the rankings.
- At least **3 new spots** are added by users and approved by the administrator.

## Plan of the work based on MVP

| Task Number | Planned Action                          | Planned Outcome                                                                                                                                                                                                                                                                                                                                                                                                                                           | Time Estimated | Target Completion Date |
|-------------|------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------|-------------------------|
| 1           | Make a plan for frontend, backend, and connection. | A simple HTTPServer and HTML cannot handle complex user-webpage interactions (add new attraction, add reviews, view reviews, etc.). A bigger project plan is needed. Frontend uses HTML for structure and CSS for styling. Backend uses Spring Boot (replacing HTTPServer), with controllers and DAOs to interact with a remote H2 database. JavaScript connects frontend and backend, handling data exchange between user input and storage.            | 1 day          | 21-May                 |
| 2           | Set up Spring Boot                       | Spring Boot handles routing and headers automatically. Using `@SpringBootApplication`, the main class can run `index.html` by default and handle JSON files.                                                                                                                                                                                                                                                                                            | 2 days         | 23-May                 |
| 3           | Learn basic JavaScript                   | Required for DOM manipulation and frontend-backend interaction. JavaScript converts objects to JSON and supports operations like opening windows and adding/removing reviews. Each attraction includes Name, Location, and reviews.                                                                                                         | 2 days         | 23-May                 |
| 4           | Set up an H2 database                    | Install and set up an H2 TCP database for persistent storage and remote access. DAO enables access to data. "Attractions" include Name, Location, and a foreign key to "reviews". Each review includes username, comment, and a foreign key to the related attraction.                                                               | 3 days         | 26-May                 |
| 5           | Update "Add Attraction" button           | Adds a button to post a window with a "submit" form. JavaScript sends user input (Name, Location, Reviews) in JSON to Spring Boot, which stores it in the database. HTML then displays the new attraction.                                                                                                                       | 3 days         | 26-May                 |
| 6           | Update "Add reviews" button              | Adds a button that opens `add-review.html` via JavaScript. Users submit reviews (username, comment, grade), sent as JSON to Spring Boot, then stored in the database.                                                                                                                                                                                                                                                                                                  | 3 days         | 26-May                 |
| 7           | Update "View Reviews" button             | Clicking "View reviews" opens `view-reviews.html`. Spring Boot fetches reviews as JSON, JavaScript displays them in the new window.                                                                                                                                                                                                                                                                                                         | 1 day          | 27-May                 |
| 8           | Make the webpage more beautiful and readable | Use CSS to style buttons, colors, fonts, and improve layout and readability.                                                                                                                                                                                                                                                                                                                                                                                                     | 1 day          | 28-May                 |
| 9           | Test and feedback                        | Share the site with classmates for feedback and test if it can handle 10+ reviews. Update based on their input.                                                                                                                                                                                                                                                                                                                                                                   | 1 day          | 29-May                 |

## Functions so Far

### Frontend

- A basic HTML can be displayed. (5/20/2025)
- Users can type in their new attractions with name and location. (5/22/2025)
- The new attraction can be displayed underneath. (5/22/2025)
- Add remove attraction to the website. After adding it to the website, the user can add or remove the attractions. (5/23/2025)
- A new `addReveiw.html` is added to the frontend. User can type in new review for each attraction. However, they still cannot see what comments have been posted. (5/24/2025)
- JavaSript can send the review to the backend. (5/24/2025)

### Backend

- Basic controller is setup. JavaScript works well. (5/22/2025)
- Dao is set up with hard coded SQL, but it can access to database and do some basic operations. (5/22/2025)
- Dao is upsdated with `deleteAttraction(String id)` method to help fulfill the remove method. (5/23/2025)
- Dao can insert a new review to the database. Two tables are stored in the database `Review` and `Attraction`. (5/24/2025)  
- A new model `Review` is intiated. (5/24/2025)
