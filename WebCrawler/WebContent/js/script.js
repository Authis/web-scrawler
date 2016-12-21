/**
 * 
 */

var app = angular.module('wcApp', []);
	app.controller('startCrawel', function($scope,$http,$log) {
           //alert("====");
           $scope.txtURL = "http://wiprodigital.com";
		$scope.loadUrls = function(){
			
			var url = $scope.txtURL;
            
			//alert("URL is: "+url);
			
			$http.post("http://localhost:8090/WebCrawler/WC/StartCrawle", url)
	 			.success(function(data, status, headers, config) {
	 				$log.log("post method is Success" + JSON.stringify(data));
	 				    
	 				    
	 				
						$scope.urlValues =data;
						if($scope.urlValues.length == 1 && $scope.urlValues[0]['DOM'] == 'ERROR'){
							//alert("1");
							$scope.error = "Y";
						}else{
							//alert("2");
							$scope.error = "N";
							$scope.urlValues = data
						}
						

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
