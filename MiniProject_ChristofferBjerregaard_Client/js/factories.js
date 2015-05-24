/**
 * Created by Christoffer on 11-05-2015.
 */
mini_project.factory('CountriesFactory', function ($http) {
    return {
        addCountry: function (name, alpha2, alpha3) {
            return $http({
                    url: BASE_URL + '/add/country',
                    method: 'POST',
                    headers : { 'Content-Type': 'application/x-www-form-urlencoded' },
                    params: { name:name, alpha2:angular.lowercase(alpha2), alpha3:alpha3 }
                })
                .success(function () {
                    console.log('addCountry success.');
                })
                .error(function () {
                    console.log('addCountry error.');
                })
        },
        getCountries: function () {
            return $http({
                    url: BASE_URL + '/countries',
                    method: 'GET',
                    headers:  {'Content-Type' : 'application/json;charset=UTF-8'}
                })
                .success(function () {
                    console.log('getCountries success.');
                })
                .error(function () {
                    console.log('getCountries error.');
                })
        },
        getCountry: function(id) {
            return $http({
                    url: BASE_URL + '/country',
                    method: 'GET',
                    params: {id: id}
                })
                .success(function () {
                    console.log('getCountry success.');
                })
                .error(function () {
                    console.log('getCountry error.');
                })
        },
        deleteCountry: function (id) {
            return $http({
                    url: BASE_URL + '/delete/country',
                    method: 'DELETE',
                    params: {id: id}
                })
                .success(function () {
                    console.log('deleteCountry success.');
                })
                .error(function () {
                    console.log('deleteCountry error.');
                })
        },
        editCountry: function (id, country) {
            return $http({
                    url: BASE_URL + '/countries',
                    method: 'PUT',
                    params: { id:id, name:country.name, alpha2:country.alpha2, alpha3:country.alpha3 }
                })
                .success(function () {
                    console.log('editCountry success.');
                })
                .error(function () {
                    console.log('editCountry error.');
                })
        },
        quickAdd: function () {
            return $http({
                    url: BASE_URL + '/quickadd',
                    method: 'POST'
                })
                .success(function () {
                    console.log('editCountry success.');
                })
                .error(function () {
                    console.log('editCountry error.');
                })
        },
        getFromWiki: function (name) {
            return $http({
                    url: WIKI_URL + "/w/api.php",
                    method: 'GET',                                                                       // Limit to 5 sentences, and only 1 extract
                    //headers:  {'Api-User-Agent': 'CountryDataApp/1.0 (http://c-bjerregaard.dk', 'origin':'http://c-bjerregaard.dk', 'Content-Type' : 'application/json;charset=UTF-8' },
                    //headers: {"Access-Control-Request-Headers": "accept, origin, authorization"},
                    headers: { 'Api-User-Agent': 'Example/1.0' },
                    params: { format:'jsonp', action:'query', prop:'extracts', exintro:'', explaintext:'', exsentences:'5', exlimit:'1', titles:name, origin:'advisory.wikimedia.org' }
                })
                .success(function () {
                    console.log('editCountry success.');
                })
                .error(function () {
                    console.log('editCountry error.');
                })
        },
        getFromRestCountries: function() {
                return $http({
                    url: "https://restcountries.eu/rest/v1/all",
                    method: 'GET'
                    //header: {"X-Mashape-Key":"DVMxyYsUXGmshYwFyBe8vLzx5Bpbp1dZ2e7jsnMtbngHizgTzD"},
                    //params: {}
                })
                .success(function () {
                    console.log('editCountry success.');
                })
                .error(function () {
                    console.log('editCountry error.');
                })
        }
    }
});