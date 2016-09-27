var XERP_REST_API_CITY = "/api/v1/city/";

function addCity() {

    var city = new Object();
    city.name = $("#cityName").val();
    city.country = $("#countryName").val();

    //Make the add call
    $.ajax({
        type: "POST",
        data: JSON.stringify(city),
        url: XERP_REST_API_CITY,
        contentType: "application/json; charset=utf-8"
    }).success(function (data, status, xhr) {
        //Check if the new account is created
        if ("SUCCESS" === data.code) {
            console.log("City has been added successfully");

            window.location.href="/city";

        } else {

            alert(data.message);
        }

    });

}