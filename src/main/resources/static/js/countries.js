$(document).ready(function () {


    // Server Side DataTable
    var table = $('#countriesTable').DataTable({
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

var XERP_REST_API_COUNTRY = "/api/v1/country/";

function removeCountry(countryID) {

    //Make the add call
    $.ajax({
        type: "DELETE",
        url: XERP_REST_API_COUNTRY + countryID,
        contentType: "application/json; charset=utf-8"
    }).success(function (data, status, xhr) {
        //Check if the new account is created
        if ("SUCCESS" === data.code) {
            console.log("Country has been deleted successfully");

            window.location.href="/country";

        } else {
            alert(data.message);
        }

    });

}