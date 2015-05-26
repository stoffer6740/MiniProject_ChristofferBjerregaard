/**
 * Created by Christoffer on 11-05-2015.
 */
mini_project.controller('AppCtrl', ['CountriesFactory', 'RMIFactory', '$scope', function(CountriesFactory, RMIFactory, $scope) {
    //CountriesFactory.quickAdd();
    RMIFactory.getRmiStatus().then(function (response) {
        $scope.status = response.data.status;
    });
}]);

mini_project.controller('ManageCtrl', function () {

});

mini_project.controller('CountriesCtrl', ['CountriesFactory', 'CountryService', '$scope', '$state', function (CountriesFactory, CountryService, $scope, $state) {
    $scope.message = "Hello world";
    CountriesFactory.getCountries().then(function (response) {
        $scope.countries = response.data;
    });

    $scope.deleteCountry = function (id) {
        CountriesFactory.deleteCountry(id).then(function (response) {
            console.log(response);
            $state.reload();
        });
    };
}]);

mini_project.controller('CountryCtrl', ['CountriesFactory', '$scope', '$stateParams', '$modal', function (CountriesFactory, $scope, $stateParams, $modal) {
    CountriesFactory.getCountry($stateParams.id).then(function (response) {
        $scope.country = response.data;
        CountriesFactory.getDataFromRestCountries(response.data.alpha2).then(function (response) {
            $scope.countryInformation = response.data;
        })
            .then(function() {
                initialize($scope.countryInformation.latlng[0], $scope.countryInformation.latlng[1], $scope.country.country, 5)
            });
    });

    CountriesFactory.getFromWiki($stateParams.country).then(function (response) {
        $scope.countryDescription = response.data;
    });

    CountriesFactory.getCurrencies('DKK', 'SEK').then(function (response) {
        console.log(response);
        $scope.currencies = response.data;
    });

    function initialize(coord1, coord2, country, zoom) {
        var mapCanvas = document.getElementById('maps');
        var myLatlng = new google.maps.LatLng(coord1, coord2);
        var mapOptions = {
            center: myLatlng,
            zoom: zoom,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            scrollwheel: false
        };

        var map = new google.maps.Map(mapCanvas, mapOptions);

        var marker = new google.maps.Marker({
            position: myLatlng,
            map: map,
            title: country
        });

        marker.setMap(map);
    }

    // Currency converter
    $scope.convert = function (sourceCurrency, targetCurrency) {
        CountriesFactory.convertFromRmi(sourceCurrency, targetCurrency).then(function (response) {
            console.table(response);
        })
    };

    $scope.convertAmount = function (test) {
        CountriesFactory.convertFromRmiAmount(test.source, test.target, test.amount).then(function (response) {
            console.table(response);
            $scope.resultOfConvert = response.data;
        })
    };

    //Open modal
    $scope.open = function () {
        $modal.open({
            animation: true,
            templateUrl: 'templates/modal/editcountry-modal.html',
            controller: 'EditCountryCtrl'
        });
    };
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

mini_project.controller('EditCountryCtrl', ['CountriesFactory', '$state', '$scope', '$stateParams', '$modalInstance', function(CountriesFactory, $state, $scope, $stateParams, $modalInstance) {
    // Loads the parameters from the URL.
    $scope.params = $stateParams;

    $scope.editCountry = function (id, country) {
        CountriesFactory.editCountry(id, country).then(function (response) {
            if(response.status == 200) {
                $scope.message = "Successfully edited country";
                $modalInstance.close();
                $state.go('countries');
            }
            else {
                $scope.message = response.status;
            }
        })
    };

    $scope.cancel = function () {
        $modalInstance.close();
    }
}]);