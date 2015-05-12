/**
 * Created by Christoffer on 11-05-2015.
 */
mini_project.factory('CountriesFactory', function ($http) {
    return {
        addCountry: function () {
            return $http({
                    url: BASE_URL + '/countries',
                    method: 'POST'
                })
                .success(function () {
                    console.log('addCountry success.');
                })
                .error(function () {
                    console.log('addCountry error.')
                })
        },
        getCountries: function () {
            return $http({
                    url: BASE_URL + '/countries',
                    method: 'GET'
                })
                .success(function () {
                    console.log('getCountries success.');
                })
                .error(function () {
                    console.log('getCountries error.')
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
                    console.log('getCountry error.')
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
                    console.log('deleteCountry error.')
                })
        },
        editCountry: function (id) {
            return $http({
                    url: BASE_URL + '/countries',
                    method: 'PUT'
                })
                .success(function () {
                    console.log('editCountry success.');
                })
                .error(function () {
                    console.log('editCountry error.')
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
                    console.log('editCountry error.')
                })
        },
        getFromWiki: function (name) {
            return $http({
                url: WIKI_URL + "/w/api.php",
                method: 'GET',                                                                       // Limit to 5 sentences, and only 1 extract
                params: {format:'json', action:'query', prop:'extracts', exintro:'', explaintext:'', exsentences:'5', exlimit:'1', titles:name}
            })
                .success(function () {
                    console.log('editCountry success.');
                })
                .error(function () {
                    console.log('editCountry error.')
                })
        }
    }
});