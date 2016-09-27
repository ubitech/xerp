var XERP_REST_API_FACULTY = "/api/v1/faculty/";

function addFaculty() {

    var faculty = new Object();
    faculty.name = $("#facultyName").val();

    //Make the add call
    $.ajax({
        type: "POST",
        data: JSON.stringify(faculty),
        url: XERP_REST_API_FACULTY,
        contentType: "application/json; charset=utf-8"
    }).success(function (data, status, xhr) {
        //Check if the new account is created
        if ("SUCCESS" === data.code) {
            console.log("Faculty has been added successfully");

            window.location.href="/faculty";

        } else {
            alert(data.message);
        }

    });

}