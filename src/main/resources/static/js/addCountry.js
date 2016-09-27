var XERP_REST_API_COUNTRY = "/api/v1/country/";

function addCountry() {

    var country = new Object();
    country.name = $("#countryName").val();
    country.inhabitants = $("#inhabitants").val();

    //Make the add call
    $.ajax({
        type: "POST",
        data: JSON.stringify(country),
        url: XERP_REST_API_COUNTRY,
        contentType: "application/json; charset=utf-8"
    }).success(function (data, status, xhr) {
        //Check if the new account is created
        if ("SUCCESS" === data.code) {
            console.log("Country has been added successfully");

            window.location.href="/country";

        } else {
            alert(data.message);
        }

    });

}