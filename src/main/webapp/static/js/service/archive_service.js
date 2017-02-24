'use strict';

angular.module('myApp').factory('ArchiveService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'archive/';

    var factory = {
        fetchAllArchives: fetchAllArchives,
        createArchive: createArchive,
        deleteArchive:deleteArchive,
        downloadArchive:downloadArchive
    };

    return factory;

    function fetchAllArchives() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'listAllArchives')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
            	console.error('Erro ao buscar Arquivos.');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createArchive(archive) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI + 'createArchive', archive)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
            	console.error('Erro ao criar um novo Arquivo.');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteArchive(id) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI + 'deleteArchive/'+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
            	console.error('Erro ao deletar Arquivo.');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function downloadArchive(id) {
        var deferred = $q.defer();
        
        window.open(
        		REST_SERVICE_URI
						+ 'download/' + id);
    }

}]);
