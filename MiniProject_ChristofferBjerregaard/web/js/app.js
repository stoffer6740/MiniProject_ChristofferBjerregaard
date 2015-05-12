/**
 * Created by Christoffer on 11-05-2015.
 */
var mini_project = angular.module('miniproject', ['gridshore.c3js.chart', 'ui.router']);

var BASE_URL = 'http://localhost:8080';
var WIKI_URL = "https://en.wikipedia.org";


mini_project.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('countries');

    $stateProvider
        .state('countries', {
            url:'/countries',
            templateUrl:'templates/countries.html',
            controller:'CountriesCtrl'
        })
        .state('countries/country', {
            url:'/countries/country',
            templateUrl:'templates/countries.country.html',
            controller:'CountryCtrl'
        })
        .state('countries/addcountry', {
            url:'/countries/addcountry',
            templateUrl:'templates/countries.addcountry.html',
            controller:'AddCountryCtrl'
        });
});