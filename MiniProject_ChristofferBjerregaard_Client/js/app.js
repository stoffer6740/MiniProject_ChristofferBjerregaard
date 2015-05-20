/**
 * Created by Christoffer on 11-05-2015.
 */
var mini_project = angular.module('miniproject', ['gridshore.c3js.chart', 'ui.router']);

var BASE_URL = 'http://localhost:8080';
var WIKI_URL = "https://en.wikipedia.org";
var RESTCOUNTRIES_URL = "https://restcountries-v1.p.mashape.com/all";


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