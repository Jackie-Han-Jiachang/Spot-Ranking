$(docuemnt).ready(function() {
    function loadAttraction() {
        $.get("/api/attractions", function(data) {
            $("#attractionList").empty();
            data.array.forEach(attraction => {
                // add the attraction to the list
                <li>
                ${attraction.name} - ${attraction.location}
                </li>
            });
        });
    }
    // add an location (name and location)
    function addAttraction() {
        const attraction ={
            name: $("#attractionName").val(),
            location: $("#attractionLocation").val()
        };
    // use ajax to post the data to the server
    $.ajax({
        // api post and get the datas from the server
        url: "/api/attractions",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(attraction),
        success: function() {
            $("#addAttractionForm")[0].reset();
            loadAttraction();
        },error: function() {
            alert("Add failed, please try again...");
        }
    });
    }

    // operatior
    if (window.location.pathname === "/index.html" || window.location.pathname === "/") {
        loadAttraction();
        $("#addAttractionForm").on("submit", function(event) {
            event.preventDefault();
            addAttraction();
        });
    }
});