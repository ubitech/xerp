$(document).ready(function () {


    // Server Side DataTable
    var table = $('#invoicesTable').DataTable({
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