angular.module('app', [])
    .controller('EventListController', ['$scope', '$http', function($scope, $http) {

      $http.get('categories').
          then(function(response) {
              $scope.categories = response.data;
          });


        $scope.sortType     = 'date'; // set the default sort type
        $scope.sortReverse  = false;  // set the default sort order
        $scope.searchFish   = '';     // set the default search/filter term
        $scope.events = [];           // initialise the event list
        $scope.inputPageNumber = "1";
        $scope.inputPageSize = "10";
        $scope.inputCategory = "music";

     $scope.submit = function() {

        var formData = {
                "location" : $scope.inputLocation,
                "category" : $scope.inputCategory,
                "pageSize" : $scope.inputPageSize,
                "pageNumber" : $scope.inputPageNumber
        };

       var response = $http.get('eventsandweather', {
            params: {location : formData.location, category : formData.category, page_size: formData.pageSize, page_number: formData.pageNumber}
        });
        response.success(function(data, status, headers, config) {
            $scope.events = data;
        });

        response.error(function(data, status, headers, config) {
            alert( "Exception details: " + JSON.stringify({data: data}));
        });

     };

 }]);
