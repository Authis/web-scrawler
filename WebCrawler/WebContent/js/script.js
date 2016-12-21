/**
 * 
 */

var app = angular.module('wcApp', []);
	app.controller('startCrawel', function($scope,$http,$log) {
      alert("====");
		$scope.loadUrls = function(){
			
			var url = $scope.txtURL;
            
			alert(url);
			
			$http
			.post("http://localhost:8080/WebCrawler/WC/StartCrawle", url)
	 			.success(function(data, status, headers, config) {
	 				$log.log("post method is Success" + data);
						

					}).error(
					function(data, status, header, config) {
						$scope.ResponseDetails = "Data: "
								+ data + "<hr />status: "
								+ status
								+ "<hr />headers: "
								+ header + "<hr />config: "
								+ config;
						$log.log("post method is failed");
					});

		}
 
 	});