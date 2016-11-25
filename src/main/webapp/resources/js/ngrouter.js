'use strict';

var ControllerApp = {};

var App = angular.module('ControllerApp', ['ControllerApp.filters', 'ControllerApp.services', 'ControllerApp.directives']);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/news', {
        templateUrl: 'cars/layout',
        controller: CarController
    });

    $routeProvider.when('/news/:id', {
        templateUrl: 'trains/layout',
        controller: TrainController
    });
    
    $routeProvider.when('/products', {
        templateUrl: 'railwaystations/layout',
        controller: RailwayStationController
    });
	
	$routeProvider.when('/products/:id', {
        templateUrl: 'trains/layout',
        controller: TrainController
    });
	$routeProvider.when('/downloads', {
        templateUrl: 'railwaystations/layout',
        controller: RailwayStationController
    });
	
	$routeProvider.when('/downloads/:id', {
        templateUrl: 'trains/layout',
        controller: TrainController
    });
	$routeProvider.when('/teams', {
        templateUrl: 'railwaystations/layout',
        controller: RailwayStationController
    });
	
	$routeProvider.when('/teams/:id', {
        templateUrl: 'trains/layout',
        controller: TrainController
    });

    $routeProvider.otherwise({redirectTo: '/home'});
}]);