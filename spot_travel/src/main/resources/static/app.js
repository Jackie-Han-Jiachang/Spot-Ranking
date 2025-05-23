$(document).ready(function() {
    // function loadAttraction() {
    //     $.get("/api/attractions", function(data) {
    //         $("#attractionList").empty();
    //         data.forEach(attraction => {
    //             // add the attraction to the list
    //             $("#attractionList").append(`
    //                 <li>
    //                 ${attraction.name} - ${attraction.location}
    //                 <button onclick="removeAttraction('${attraction.id}')"> Remove </button>
    //                 </li>
    //             `);
    //         });
    //     });
    // }
    // // add an location (name and location)
    // function addAttraction() {
    //     const attraction ={
    //         name: $("#attractionName").val(),
    //         location: $("#attractionLocation").val()
    //     };
    // // use ajax to post the data to the server
    // $.ajax({
    //     // api post and get the datas from the server
    //     url: "/api/attractions",
    //     type: "POST",
    //     contentType: "application/json",
    //     data: JSON.stringify(attraction),
    //     success: function() {
    //         $("#addAttractionForm")[0].reset();
    //         loadAttraction();
    //     },error: function() {
    //         alert("Add failed, please try again...");
    //     }
    // });
    // }

    // delete an attraction
    // function removeAttraction(id) {
    //     $.ajax({
    //         url: "/api/attractions/" + id,
    //         type: "DELETE",
    //         success: function() {
    //             loadAttraction();
    //         },
    //         error: function() {
    //             alert("Delete failed, please try again...");
    //         }
    //     });
    // }

    // operator
    if (window.location.pathname === "/index.html" || window.location.pathname === "/") {
        loadAttraction();
        $("#addAttractionForm").on("submit", function(event) {
            event.preventDefault();
            addAttraction();
        });
    }
});

function loadAttraction() {
        $.get("/api/attractions", function(data) {
            $("#attractionList").empty();
            data.forEach(attraction => {
                // add the attraction to the list
                $("#attractionList").append(`
                    <li>
                    ${attraction.name} - ${attraction.location}
                    <button onclick="removeAttraction('${attraction.id}')"> Remove </button>
                    </li>
                `);
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

// delete an attraction
    function removeAttraction(id) {
        $.ajax({
            url: "/api/attractions/" + id,
            type: "DELETE",
            success: function() {
                loadAttraction();
            },
            error: function() {
                alert("Delete failed, please try again...");
            }
        });
    }