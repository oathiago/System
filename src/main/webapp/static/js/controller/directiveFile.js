'use strict';

angular.module('myApp').directive(
		'atFile',
		function() {
			return {
				templateUrl : 'static/directives/at-file.jsp',
				restrict : 'E',
				replace : false,
				transclude : true,
				scope : {
					textButton : "=",
					fileModel : "=",
					fileId : "=",
					fileName : "=",
					fileHash : "=",
					fileSize : "=",
					fileExtension : "=",
					fileExtensionMessage : "=",
					readFile : "=",
					largura : "@" 	
				},
				link : function(scope, element) {
					if (!scope.largura) {
						scope.largura = 10;
					}

					// Função para fazer o download do arquivo
					scope.baixarArquivo = function() {
						var idArqo = 'id=' + scope.fileId;
						var hashArqo = 'hash=' + scope.fileHash;
						var url = idArqo + '&' + hashArqo;
						window.open(
								'/sgr-resources/webresources/arquivo/findBy?'
										+ url, '_blank');
					};

					// Evento de mudança do arquivo
					element[0].addEventListener('change', function(e) {
						var arquivo = e.target.files[0];

						var reader = new FileReader();

						scope.fileModel.id = null;
						scope.fileModel.mimetype = arquivo.type;
						scope.fileModel.nome = arquivo.name;
						scope.fileModel.tamanho = arquivo.size;
						
						reader.onload = function() {
							handleFileSelect(0);
							scope.fileModel.conteudo = reader.result.split(
									'base64,').pop();
							scope.$apply();
						};

						reader.readAsDataURL(e.target.files[0]);

						scope.$apply();
					});
					
					var reader;
					var progress = document.querySelector('.percent');
					
					function updateProgress(evt) {
						// evt is an ProgressEvent.
						if (evt.lengthComputable) {
							var percentLoaded = Math
									.round((evt.loaded / evt.total) * 100);
							// Increase the progress bar length.
							if (percentLoaded < 100) {
								progress.style.width = percentLoaded
										+ '%';
								progress.textContent = percentLoaded
										+ '%';
							}
						}
					}

					function handleFileSelect(evt) {
						// Reset progress indicator on new file
						// selection.
						progress.style.width = '0%';
						progress.textContent = '0%';

						reader = new FileReader();
						reader.onprogress = updateProgress;
						reader.onabort = function(e) {
							alert('File read cancelled');
						};
						reader.onloadstart = function(e) {
							document.getElementById('progress_bar').className = 'loading';
						};
						reader.onload = function(e) {
							// Ensure that the progress bar displays
							// 100% at the end.
							progress.style.width = '100%';
							progress.textContent = '100%';
							setTimeout(
									"document.getElementById('progress_bar').className='';",
									2000);
						}

						// Read in the image file as a binary string.
						reader
								.readAsBinaryString(document
										.getElementById('archiveUpload').files[0]);
					}
				}
			};
		});