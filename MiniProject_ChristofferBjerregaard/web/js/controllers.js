/**
 * Created by Christoffer on 11-05-2015.
 */
mini_project.controller('AppCtrl', ['CountriesFactory', '$scope', function(CountriesFactory, $scope) {
    CountriesFactory.quickAdd();
}]);

mini_project.controller('CountriesCtrl', ['CountriesFactory', 'CountryService', '$scope', '$state', function (CountriesFactory, CountryService, $scope, $state) {
    $scope.message = "Hello world";
    CountriesFactory.getCountries().then(function (response) {
        $scope.countries = response.data;
    });

    $scope.deleteCountry = function (id) {
        alert('Removing country with id ' + id);
        CountriesFactory.deleteCountry(id).then(function (response) {
            console.log(response);
            $state.reload();
        });
    };

    $scope.getCountry = function (id, name) {
        CountryService.setId(id);
        CountryService.setName(name);
        $state.go('countries/country');
        //CountriesFactory.getCountry(id).then(function (response) {
        //    $scope.countries = response.data;
        //});
    }
}]);

mini_project.controller('CountryCtrl', ['CountriesFactory', 'CountryService', '$scope', function (CountriesFactory, CountryService, $scope) {
    $scope.message = "Hello world";
    CountriesFactory.getCountry(CountryService.getId()).then(function (response) {
        $scope.country = response.data;
    });

    CountriesFactory.getFromWiki(CountryService.getName()).then(function (response) {
        $scope.wikiData = response.data;
    })

}]);

mini_project.controller('AddCountryCtrl', ['CountriesFactory', 'CountryService', '$scope', function (CountriesFactory, CountryService, $scope) {
    $scope.message = "Hello world";

}]);


mini_project.controller('AddCountriesCtrl', ['CountriesFactory', '$scope', function (CountriesFactory, $scope) {
    $scope.message = "Hello world";
    CountriesFactory.getCountries().then(function (response) {
        $scope.result = response.data;
    });
}]);