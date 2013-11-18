var myModule = angular.module('Angello', []);

myModule.controller('MainCtrl', function($scope) { 
	$scope.currentStory = null;
	$scope.newStoryTitle = '';
	$scope.newStoryDesc = '';

	$scope.setCurrentStory = function(story) {
		$scope.currentStory = story
	};

	$scope.getNewStoryTitle = function() { 
		return $scope.newStoryTitle;
	};

	$scope.getNewStoryDesc = function() { 
		return $scope.newStoryDesc;
	};

	$scope.stories = [
			{title: 'Story 00', description: 'Description pending.'},
			{title: 'Story 01', description: 'Description pending.'},
			{title: 'Story 02', description: 'Description pending.'},
			{title: 'Story 03', description: 'Description pending.'},
			{title: 'Story 04', description: 'Description pending.'},
			{title: 'Story 05', description: 'Description pending.'}
		];

	$scope.addNewStory = function(t, d) { 
		console.log("in create story");
		const newStory = [{title: t, description: d}];
		console.log("adding newStory: " + JSON.stringify(newStory));
		$scope.stories = Array.prototype.concat($scope.stories, newStory);
	};
});
