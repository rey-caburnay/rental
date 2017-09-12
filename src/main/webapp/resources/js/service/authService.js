(function () {

    var injectParams = ['$http', '$rootScope', '$window'];

    var authFactory = function ($http, $rootScope, $window) {
        var serviceBase = '/mst',
            factory = {
                loginPath: '/login',
                user: {
                    isAuthenticated: false,
                    roles: null
                }
            };

        factory.login = function (email, password) {
            return $http.post(serviceBase + '/login', { userLogin: { username: email, password: password } }).then(
                function (results) {
                    var loggedIn = results.data.status;;
                    changeAuth(loggedIn);
                    return loggedIn;
                });
        };

        factory.logout = function () {
            return $http.post(serviceBase + 'logout').then(
                function (results) {
                    var loggedIn = !results.data.status;
                    changeAuth(loggedIn);
                    return loggedIn;
                });
        };

        factory.redirectToLogin = function () {
            $rootScope.$broadcast('redirectToLogin', null);
        };

        function changeAuth(loggedIn) {
            factory.user.isAuthenticated = loggedIn;
            $rootScope.$broadcast('loginStatusChanged', loggedIn);
        }

        return factory;
    };

    authFactory.$inject = injectParams;

    angular.module('rental').factory('authService', authFactory);

}());

