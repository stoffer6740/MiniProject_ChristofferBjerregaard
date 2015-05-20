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

mini_project.controller('AddCountryCtrl', ['CountriesFactory', 'CountryService', '$scope', function (CountriesFactory, CountryService, $scope) {
    $scope.message = "Hello world";

    $scope.addCountry = function (country) {
        CountriesFactory.addCountry(country.name, country.alpha2, country.alpha3).then(function (response) {
            if(response.status == 200) {
                $scope.message = "Successfully added country"
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


//mini_project.controller('AddCountriesCtrl', ['CountriesFactory', '$scope', function (CountriesFactory, $scope) {
//    $scope.message = "Hello world";
//    CountriesFactory.getCountries().then(function (response) {
//        $scope.result = response.data;
//    });
//}]);

mini_project.controller('FibonacciCtrl', function ($scope) {

    $scope.fib = function (n) {
        $scope.fibres = [];
        console.log('Run fibonacci');
        $scope.result = fibonacci(n);
    };

    function fibonacci (n) {
        var a = 0;
        var b = 1;
        var i = 0;
        if (n < 0 ) {
            n =  -1 * n;
            console.log('Fibonacci complete')
        }
        while((i < n ) && (a >= 0) ){
            i++;
            var temp = a;
            a = b;
            b = temp + b;
            $scope.fibres.push(b);
        }
        if( a >= 0) {
            return a;
        }else {
            return -1;
        }
    }
});