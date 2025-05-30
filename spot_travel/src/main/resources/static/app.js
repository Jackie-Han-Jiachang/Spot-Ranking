$(document).ready(function () {
  // login page. I set it as the default page so that when the user opens the app, they will see the login page
  if (
    window.location.pathname === "/index.html" ||
    window.location.pathname === "/" // check current path
  ) { // $ means jQuery manages the get and post requests
    // on means a listener for the submit event
    $("#loginForm").on("submit", function (event) {
      event.preventDefault(); // prevent the default form submission
      verifyUser();
    });
  }
  // register page
  // send the data to the server so that the user can be added to the database
  if (
    window.location.pathname === "/addUser.html" ||
    window.location.pathname === "/addUser"
  ) {
    $("#addUserForm").on("submit", function (event) {
      event.preventDefault();
      addUser();
    });
  }
  // attraction page
  // load all the attractions and listen for the submit event of the form
  if (
    window.location.pathname === "/attraction.html" ||
    window.location.pathname === "/attraction"
  ) {
    loadAttraction();
    $("#addAttractionForm").on("submit", function (event) {
      event.preventDefault();
      addAttraction();
    });
  }

  // add review page
  // entering review submission page. each review is connected to an attraction
  if (
    window.location.pathname === "/addReview.html" ||
    window.location.pathname === "/addReview"
  ) {
    const urlParams = new URLSearchParams(window.location.search);
    attractionId = urlParams.get("id"); // get the attraction id from the url
    $("#addReviewForm").on("submit", function (event) {
      event.preventDefault();
      addReview(attractionId);
    });
  }

  // view review page
  // load the reviews for the attraction according to the attraction id
  if (
    window.location.pathname === "/viewReview.html" ||
    window.location.pathname === "/viewReview"
  ) {
    const urlParams = new URLSearchParams(window.location.search);
    attractionId = urlParams.get("id");
    loadAttractionForReview(attractionId);
    loadReviews(attractionId);
  }
});

// add a new user
function addUser() {
  // define the user first. This is the same as user model in backend
  const user = {
    username: $("#username").val(),
    password: $("#password").val(),
  };
  $.ajax({ // use ajax to post the data to the server
    url: "/api/users", // define where to post. The link between backend and frontend
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(user),
    success: function () {
      $("#addUserForm")[0].reset(); // reset and wait for the next register of the form after successful submission
      alert("User added successfully");
      window.location.href = "/index.html"; // redirect to the login page
    },
    error: function () {
      alert("Add failed, please try again...");
      window.location.href = "/index.html";
    },
  });
}

// verify the user login
function verifyUser() {
  const user = {
    username: $("#username").val(),
    password: $("#password").val(),
  };
  $.ajax({
    url: "/api/users/verify",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(user),
    success: function (userId) {
      localStorage.setItem("loggedInUserId", userId); // keep login state in webpage. The username can be retrieved directly from the localStorage
      alert("Login successful");
      window.location.href = "/attraction.html";
    },
    error: function () {
      alert("Login failed, please check your username and password");
      window.location.href = "/index.html";
    },
  });
}

// load all the attractions from the server and display them in the list
function loadAttraction() {
  $.get("/api/attractions", function (data) {
    $("#attractionList").empty(); // clean the list before adding new attractions
    data.forEach((attraction) => { 
      // add the attractions to the list and update their display format
      $("#attractionList").append(`
                    <li>
                    ${attraction.name} - ${attraction.location}
                    <button onclick="removeAttraction('${attraction.id}')"> Remove </button>
                    <a href="addReview.html?id=${attraction.id}"> <button> New Review </button </a>
                    <a href="viewReview.html?id=${attraction.id}"> <button> View Review </button </a>
                    </li>
                `);
    });
  });
}

// add an location (name and location)
function addAttraction() {
  const attraction = {
    name: $("#attractionName").val(),
    location: $("#attractionLocation").val(),
  };
  // use ajax to post the data to the server
  $.ajax({
    // api post and get the datas from the server
    url: "/api/attractions",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(attraction),
    success: function () {
      $("#addAttractionForm")[0].reset();
      loadAttraction();
    },
    error: function () {
      alert("Add failed, please try again...");
    },
  });
}

// delete an attraction
function removeAttraction(id) {
  $.ajax({
    url: "/api/attractions/" + id,
    type: "DELETE",
    success: function () {
      loadAttraction();
    },
    error: function () {
      alert("Delete failed, please try again...");
    },
  });
}

// load all the reviews for a specific attraction. Quite similar to the loadAttraction function
function loadReviews(attractionId) {
  $.get("/api/reviews/" + attractionId, function (reviews) {
    $("#reviewList").empty();
    reviews.forEach((review) => {
      // add the review to the list
      $("#reviewList").append(`
          <li>
            <p>User: ${review.user.username}</p>
            <p>Review: ${review.comment}</p>
            <p>Grade: ${review.grade}</p>
            <button onclick="removeReview('${review.id}')"> Remove </button>
          </li>
        `);
    });
  }).fail(function () {
    alert("Failed to load reviews");
    window.location.href = "/attraction.html";
  });
}

// add a new review for a specific attraction
function addReview(attractionId) {
  const userId = localStorage.getItem("loggedInUserId");  // the user id is stored in the localStorage after login
  // check if the user is logged in
  if (!userId || userId === "undefined") { 
    alert("You must be logged in");
    return;
  }
  const review = {
    attraction: { id: attractionId },
    user: { id: userId },
    comment: $("#comment").val(),
    grade: parseInt($("#grade").val()),
  };
  $.ajax({
    url: "/api/reviews",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(review),
    success: function () {
      alert("Review added successfully");
      window.location.href = "/attraction.html";
    },
    error: function () {
      alert("Failed to add review");
    },
  });
}

//remove a review by its id
function removeReview(id) {
  $.ajax({
    url: "/api/reviews/" + id,
    type: "DELETE",
    success: function () {
      loadReviews(attractionId);
    },
    error: function () {
      alert("Delete failed, please try again...");
    },
  });
}
