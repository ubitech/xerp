$(document).ready(function () {


    // Server Side DataTable
    var table = $('#facultiesTable').DataTable({
//        "serverSide": true,
//        "ajax": {
//            "url": "/paasword/rest/v1/typesafeclients",
//            "type": "GET",
//            "dataSrc": "data"
//        },
        "bFilter": true,
        "ordering": true,
        "scrollX": true
//        "bProcessing": true,
//        "fnPreDrawCallback": function () {
//            $("#loadingValues").show();
//            $("#clientsTable").hide();
//        },
//        "fnDrawCallback": function () {
//            $("#clientsTable").show();
//            $("#loadingValues").hide();
//        },
//        "fnInitComplete": function () {
//
//            $("#clientsTable").show();
//            $("#loadingValues").hide();
//        }
    });

});

var XERP_REST_API_FACULTY = "/api/v1/faculty/";

function removeFaculty(facultyID) {

    //Make the add call
    $.ajax({
        type: "DELETE",
        url: XERP_REST_API_FACULTY + facultyID,
        contentType: "application/json; charset=utf-8"
    }).success(function (data, status, xhr) {
        //Check if the new account is created
        if ("SUCCESS" === data.code) {
            console.log("Faculty has been deleted successfully");

            window.location.href="/faculty";

        } else {
            alert(data.message);
        }

    });

}