/**
 * Created by Christoffer on 11-05-2015.
 */
mini_project.controller('AppCtrl', ['CountriesFactory', '$scope', function(CountriesFactory, $scope) {
    //CountriesFactory.quickAdd();
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

    //$scope.getCountry = function (id, name) {
    //    CountryService.setId(id);
    //    CountryService.setName(name);
    //    $state.go('countries/country');
    //    //CountriesFactory.getCountry(id).then(function (response) {
    //    //    $scope.countries = response.data;
    //    //});
    //}
}]);

mini_project.controller('CountryCtrl', ['CountriesFactory', '$scope', '$stateParams', function (CountriesFactory, $scope, $stateParams) {
    CountriesFactory.getCountry($stateParams.id).then(function (response) {
        $scope.country = response.data;
    });

    CountriesFactory.getFromWiki($stateParams.country).then(function (response) {
        $scope.wikiData = response.data;
    })

}]);

mini_project.controller('AddCountryCtrl', ['CountriesFactory', 'CountryService', '$scope', '$state', function (CountriesFactory, CountryService, $scope, $state) {
    $scope.addCountry = function (country) {
        CountriesFactory.addCountry(country.name, country.alpha2, country.alpha3).then(function (response) {
            if(response.status == 200) {
                $scope.message = "Successfully added country";
                $state.go('countries');
            }
            else {
                $scope.message = response.status;
            }
        })
    };

    $scope.getRestCountries = function () {
        CountriesFactory.getFromRestCountries().then(function (response) {
            $scope.restCountries = response.data;
        })
    }

}]);

mini_project.controller('EditCountryCtrl', ['$scope', '$stateParams', function($scope, $stateParams) {
    $scope.params = $stateParams;
}]);