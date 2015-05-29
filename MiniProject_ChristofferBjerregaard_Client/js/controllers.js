/**
 * Created by Christoffer on 11-05-2015.
 */
mini_project.controller('AppCtrl', ['CountriesFactory', 'RMIFactory', '$scope', function(CountriesFactory, RMIFactory, $scope) {
    //Get status of the RMI Server
    RMIFactory.getRmiStatus().then(function (response) {
        $scope.status = response.data.status;
    });
}]);

mini_project.controller('CountriesCtrl', ['CountriesFactory', 'CountryService', '$scope', '$state', function (CountriesFactory, CountryService, $scope, $state) {
    //Gets a list of countries
    CountriesFactory.getCountries()
                    .then(function (response) {
                        $scope.countries = response.data;
                    });

    // Deletes country from the database.
    $scope.deleteCountry = function (id) {
        CountriesFactory.deleteCountry(id).then(function (response) {
            $state.reload();
        });
    };
}]);

mini_project.controller('CountryCtrl', ['CountriesFactory', '$scope', '$stateParams', '$modal', function (CountriesFactory, $scope, $stateParams, $modal) {

    //Get country info from the database, using the parameters from the URL.
    CountriesFactory.getCountry($stateParams.id).then(function (response) {
        $scope.country = response.data;
        CountriesFactory.getDataFromRestCountries(response.data.alpha2).then(function (response) {
            $scope.countryInformation = response.data;
        })
            .then(function() {
                initialize($scope.countryInformation.latlng[0], $scope.countryInformation.latlng[1], $scope.country.country)
            });
    });

    //Get country description from Freebase
    CountriesFactory.getFromWiki($stateParams.country).then(function (response) {
        $scope.countryDescription = response.data;
    });

    //Get list of currencies from RMI
    CountriesFactory.getJSONCurrencyList().then(function (response) {
        $scope.JSONCurrencyList = response.data;
    });

    // Google maps
    function initialize(coord1, coord2, country) {
        var mapCanvas = document.getElementById('maps');
        var myLatlng = new google.maps.LatLng(coord1, coord2);
        var mapOptions = {
            center: myLatlng,
            zoom: 5,
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
        console.log("CONVERT!");
        CountriesFactory.convertFromRmi(sourceCurrency, targetCurrency).then(function (response) {
            console.log(response);
            $scope.convertedResult = response.data;
        })
    };

    // Currency converter with amount
    $scope.convertAmount = function (source, target, amount) {
        CountriesFactory.convertFromRmiAmount(source, target, amount).then(function (response) {
            $scope.convertedResultAmount = response.data;
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
    //Add button below the input
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

    //Add button on every country
    $scope.addCountryBtn = function (name, alpha2, alpha3) {
        CountriesFactory.addCountry(name, alpha2, alpha3).then(function (response) {
            if(response.status == 200) {
                $scope.message = "Successfully added country";
                $state.go('countries');
            }
            else {
                $scope.message = response.status;
            }
        })
    };

    //Get list of countries from Restcountries to fast add them
    $scope.getRestCountries = function () {
        CountriesFactory.getFromRestCountries().then(function (response) {
            $scope.restCountries = response.data;
        })
    }

}]);

mini_project.controller('EditCountryCtrl', ['CountriesFactory', '$state', '$scope', '$stateParams', '$modalInstance', function(CountriesFactory, $state, $scope, $stateParams, $modalInstance) {
    // Loads the parameters from the URL.
    $scope.params = $stateParams;

    //Edits a country
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

    //Closes modal without saving data
    $scope.cancel = function () {
        $modalInstance.close();
    }
}]);