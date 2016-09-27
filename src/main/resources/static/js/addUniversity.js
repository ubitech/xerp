var XERP_REST_API_UNIVERSITY = "/api/v1/university/";

function addUniversity() {

    var university = new Object();
    university.name = $("#universityName").val();
    university.numberOfLectureHalls = $("#numberOfLectureHalls").val();
    university.city = $("#city").val();

    //Make the add call
    $.ajax({
        type: "POST",
        data: JSON.stringify(university),
        url: XERP_REST_API_UNIVERSITY,
        contentType: "application/json; charset=utf-8"
    }).success(function (data, status, xhr) {
        //Check if the new account is created
        if ("SUCCESS" === data.code) {
            console.log("University has been added successfully");

            window.location.href="/university";

        } else {
            alert(data.message);
        }

    });

}