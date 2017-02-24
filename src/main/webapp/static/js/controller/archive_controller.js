'use strict';

angular
		.module('myApp')
		.controller(
				'ArchiveController',
				[
						'$scope',
						'ArchiveService',
						function($scope, ArchiveService) {
							var self = this;
							self.archive = {
								id : null,
								userName : '',
								teste : {},
								mimetypeFile : '',
								nameFile : '',
								idFile : '',
								contentFile : '',
								sizeFile : ''
							};

							self.listArchive = [];

							self.submit = submit;
							self.remove = remove;
							self.reset = reset;
							self.download = download;

							self.fileUpload = {};

							$scope.$watch("fileUpload", function(novo) {
								console.log(novo);
							});

							fetchAllArchives();

							function fetchAllArchives() {
								ArchiveService
										.fetchAllArchives()
										.then(
												function(d) {
													self.listArchives = d;
												},
												function(errResponse) {
													console
															.error('Erro ao buscar Arquivos.');
												});
							}

							function createArchive(archive) {
								ArchiveService
										.createArchive(archive)
										.then(
												fetchAllArchives,
												function(errResponse) {
													console
															.error('Erro ao criar um novo Arquivo.');
												});
							}

							function deleteArchive(id) {
								ArchiveService
										.deleteArchive(id)
										.then(
												fetchAllArchives,
												function(errResponse) {
													console
															.error('Erro ao deletar Arquivo.');
												});
							}

							function downloadArchive(id) {
								ArchiveService
										.downloadArchive(id)
										.then(
												fetchAllArchives,
												function(errResponse) {
													console
															.error('Erro ao fazer o download do Arquivo.');
												});
							}

							function submit() {
								console.log('Salvando novo Arquivo',
										self.archive);
								/*handleFileSelect(0);*/
								convertFile();
								/*
								 * self.archive.file = document
								 * .getElementById('fileUpload').files[0];
								 */
								createArchive(self.archive);
								reset();
							}

							function remove(id) {
								console.log('Id do arquivo deletado ', id);
								if (self.archive.id === id) {
									reset();
								}
								deleteArchive(id);
							}

							function download(id) {
								console.log('Preparando o Download ', id);
								downloadArchive(id);
								reset();
							}

							function reset() {
								self.archive = {
									id : null,
									userName : '',
									teste : {},
									mimetypeFile : '',
									nameFile : '',
									idFile : '',
									contentFile : '',
									sizeFile : ''
								};
								document.getElementById('archiveUpload').value = '';
								$scope.myForm.$setPristine();
							}

							/*
							 * function convertFile(){ var file = document
							 * .getElementById('fileUpload').files[0];
							 * self.archive.nameFile = file.name;
							 * self.archive.sizeFile = file.size;
							 * self.archive.mimetypeFile = file.type;
							 * self.archive.contentFile = file.content; }
							 */

							function convertFile() {
								self.archive.nameFile = self.archive.teste.nome;
								self.archive.sizeFile = self.archive.teste.tamanho;
								self.archive.mimetypeFile = self.archive.teste.mimetype;
								self.archive.contentFile = self.archive.teste.conteudo;
							}

							

						} ]);
