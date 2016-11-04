var XERP_REST_API_STUDENT = "/api/v1/student/";

function addStudent() {

    var student = new Object();
    student.name = $("#studentName").val();
    student.surname = $("#surname").val();
    student.dateOfBirth = $("#dateOfBirth").val();
    student.gender = $("#gender").val();
    student.semester = $("#semester").val();
    student.grade = $("#grade").val();
    student.universityID = $("#university").val();
    student.facultyID = $("#faculty").val();

    //Make the add call
    $.ajax({
        type: "POST",
        data: JSON.stringify(student),
        url: XERP_REST_API_STUDENT,
        contentType: "application/json; charset=utf-8"
    }).success(function (data, status, xhr) {
        //Check if the new account is created
        if ("SUCCESS" === data.code) {
            console.log("Student has been added successfully");

            window.location.href="/student";

        } else {
            alert(data.message);
        }

    });

}