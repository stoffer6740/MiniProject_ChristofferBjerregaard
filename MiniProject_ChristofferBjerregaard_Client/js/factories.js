/**
 * Created by Christoffer on 11-05-2015.
 */
mini_project.factory('CountriesFactory', function ($http) {
    return {
        addCountry: function (name, alpha2, alpha3) {
            return $http({
                    url: BASE_URL + '/post/country',
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
                    url: BASE_URL + '/get/countries',
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
                    url: BASE_URL + '/get/country',
                    method: 'GET',
                    cache: true,
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
                    url: BASE_URL + '/put/country',
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
                    console.log('quickAdd success.');
                })
                .error(function () {
                    console.log('quickAdd error.');
                })
        },
        getFromWiki: function (name) {
            return $http({
                    url: WIKI_URL + angular.lowercase(name),
                    method: 'GET',
                    cache:true
                })
                .success(function () {
                    console.log('getFromWiki success.');
                })
                .error(function () {
                    console.log('getFromWiki error.');
                })
        },
        getFromRestCountries: function() {
                return $http({
                    url: "https://restcountries.eu/rest/v1/all",
                    method: 'GET',
                    cache: true
                })
                .success(function () {
                    console.log('getFromRestCountries success.');
                })
                .error(function () {
                    console.log('getFromRestCountries error.');
                })
        },
        getCurrencies: function(fromCountry, toCountry) {
            return $http({
                    url: CURRENCY_API_URL,
                    method: 'JSONP',
                    cache:true,
                    params: {from:fromCountry, to:toCountry, apiKey:CURRENCY_API_KEY, callback:'JSON_CALLBACK'}
                })
                .success(function (data) {
                    console.log(data);
                    console.log('getFromRestCountries success.');
                })
                .error(function (data) {
                    console.log(data);
                    console.log('getFromRestCountries error.');
                })
        },
        convertFromRmi: function(sourceCurrency, targetCurrency) {
                return $http({
                    url: BASE_URL + '/get/JSONCountryCurrencies',
                    method: 'GET',
                    params: { sourceCurrency:sourceCurrency, targetCurrency:targetCurrency }
                })
                .success(function (data) {
                    console.log('getFromRestCountries success.');
                })
                .error(function (data) {
                    console.log('getFromRestCountries error.');
                })
        },
        convertFromRmiAmount: function(sourceCurrency, targetCurrency, amount) {
            return $http({
                url: BASE_URL + '/get/JSONCountryCurrencies',
                method: 'GET',
                params: { sourceCurrency:sourceCurrency, targetCurrency:targetCurrency, amount:amount }
            })
                .success(function (data) {
                    console.log('getFromRestCountries success.');
                })
                .error(function (data) {
                    console.log('getFromRestCountries error.');
                })
        },
        getDataFromRestCountries: function(alpha2) {
            return $http({
                    url: "https://restcountries.eu/rest/v1/alpha/" + alpha2,
                    method: 'GET',
                    cache: true
                })
                .success(function () {
                    console.log('getFromRestCountries success.');
                })
                .error(function () {
                    console.log('getFromRestCountries error.');
                })
        }
    }
});

mini_project.factory('RMIFactory', function ($http) {
   return {
       getRmiStatus: function () {
           return $http({
                   url: BASE_URL + '/get/RmiServer',
                   method: 'GET',
               params: {status:"yes", currStatus:"yes"}
               })
               .success(function () {
                   console.log('getRmiStatus success.');
               })
               .error(function () {
                   console.log('getRmiStatus error.');
               })
       }
   }
});