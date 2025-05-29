$(document).ready(function () {
  // home page
  if (
    window.location.pathname === "/index.html" ||
    window.location.pathname === "/"
  ) {
    $("#loginForm").on("submit", function (event) {
      event.preventDefault();
      verifyUser();
    });
  }

  if (
    window.location.pathname === "/addUser.html" ||
    window.location.pathname === "/addUser"
  ) {
    $("#addUserForm").on("submit", function (event) {
      event.preventDefault();
      addUser();
    });
  }

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
  if (
    window.location.pathname === "/addReview.html" ||
    window.location.pathname === "/addReview"
  ) {
    const urlParams = new URLSearchParams(window.location.search);
    attractionId = urlParams.get("id");
    loadAttractionForReview(attractionId);
    $("#addReviewForm").on("submit", function (event) {
      event.preventDefault();
      addReview(attractionId);
    });
  }

  // view review page
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
  const user = {
    username: $("#username").val(),
    password: $("#password").val(),
  };
  $.ajax({
    url: "/api/users",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(user),
    success: function () {
      $("#addUserForm")[0].reset();
      alert("User added successfully");
      window.location.href = "/index.html";
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
      localStorage.setItem("loggedInUserId", userId);
      alert("Login successful");
      window.location.href = "/attraction.html";
    },
    error: function () {
      alert("Login failed, please check your username and password");
      window.location.href = "/index.html";
    },
  });
}

function loadAttraction() {
  $.get("/api/attractions", function (data) {
    $("#attractionList").empty();
    data.forEach((attraction) => {
      // add the attraction to the list
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

function loadAttractionForReview(attractionId) {
  $.get("/api/attractions/" + attractionId, function (data) {
    $("#attractionName").text(data.name);
    $("#attractionLocation").text(data.location);
  }).fail(function () {
    alert("Failed to load attraction data");
    window.location.href = "/attraction.html";
  });
}

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

function addReview(attractionId) {
  const userId = localStorage.getItem("loggedInUserId");
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
