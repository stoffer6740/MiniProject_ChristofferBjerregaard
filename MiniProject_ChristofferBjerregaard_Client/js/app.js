/**
 * Created by Christoffer on 11-05-2015.
 */
var mini_project = angular.module('miniproject', ['gridshore.c3js.chart', 'ui.router', 'ui.bootstrap']);

//Hosted in the cloud
var BASE_URL = 'http://api.c-bjerregaard.dk:47852';

// Local hosted
//var BASE_URL = "http://localhost:8080";



var WIKI_URL = "https://www.googleapis.com/freebase/v1/text/en/";
var CURRENCY_API_KEY = 'jr-bc776d027bb8b0781b9d4d3d8cd138c6';
var CURRENCY_API_URL = "http://jsonrates.com/get/";


mini_project.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('countries');
    $stateProvider
        .state('countries', {
            url:'/countries',
            templateUrl:'templates/countries.html',
            controller:'CountriesCtrl'
        })
        .state('countries/country', {
            url:'/country/:country/:id',
            templateUrl:'templates/countries.country.html',
            controller:'CountryCtrl'
        })
        .state('countries/addcountry', {
            url:'/addcountry',
            templateUrl:'templates/countries.addcountry.html',
            controller:'AddCountryCtrl'
        })
        .state('edit', {
            url:'/edit/:country/:id',
            templateUrl:'templates/countries.editcountry.html',
            controller:'EditCountryCtrl'
        });
});