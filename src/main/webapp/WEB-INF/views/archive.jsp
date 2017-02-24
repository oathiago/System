<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>System - Thiago Augusto</title>
<style>
.userName.ng-valid {
	background-color: white;
}

.userName.ng-dirty.ng-invalid-required {
	background-color: red;
}

.userName.ng-dirty.ng-invalid-minlength {
	background-color: yellow;
}
</style>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="myApp" class="ng-cloak">
	<style>
#progress_bar {
	margin: 10px 0;
	padding: 3px;
	border: 1px solid #000;
	font-size: 14px;
	clear: both;
	opacity: 0;
	-moz-transition: opacity 1s linear;
	-o-transition: opacity 1s linear;
	-webkit-transition: opacity 1s linear;
}

#progress_bar.loading {
	opacity: 1.0;
}

#progress_bar .percent {
	background-color: #99ccff;
	height: auto;
	width: 0;
}
</style>
	<div class="generic-container"
		ng-controller="ArchiveController as ctrl">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">Cadastro de Arquivo</span>
			</div>
			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">
					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Salvar"
								class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
						</div>
					</div>
					<input type="hidden" ng-model="ctrl.archive.id" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-xs-3 control-lable" for="file">Nome do
								Responsável</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.archive.userName" name="uname"
									class="userName form-control input-sm"
									placeholder="Digite o nome do Responsável" required
									ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.uname.$error.required">Este campo
										é obrigatório.</span> <span ng-show="myForm.uname.$error.minlength">Tamanho
										mínimo de 3 caracteres.</span> <span ng-show="myForm.uname.$invalid">Campo
										inválido</span>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-xs-3 control-lable" for="file">Nome do
								Arquivo</label>
							<div class="col-md-7">
								<at-file file-model="ctrl.archive.teste" file-name="nomeArquivo"></at-file>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-xs-3 control-lable" for="file">Progresso
								do Upload</label>
							<div id="progress_bar">
								<div id="percent" class="percent">0%</div>
							</div>
						</div>
					</div>

					<!-- <div class="row">
						<div>
							Progresso:
							<div class="progress" style="">
								<div class="progress-bar" role="progressbar"
									ng-style="{ 'width': uploader.progress + '%' }"></div>
							</div>
						</div>
					</div> -->
				</form>
			</div>
		</div>
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">Listagem de Arquivos</span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Código</th>
							<th>Nome do Responsável</th>
							<th>Arquivo</th>
							<th>Tamanho</th>
							<th>Status</th>
							<th colspan="2"></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="c in ctrl.listArchives">
							<td><span ng-bind="c.id"></span></td>
							<td><span ng-bind="c.userName"></span></td>
							<td><span ng-bind="c.nameFile"></span></td>
							<td><span ng-bind="c.sizeFileStr"></span></td>
							<td><span ng-bind="c.statusStr"></span></td>
							<td>
								<button type="button" ng-click="ctrl.download(c.id)"
									class="btn btn-primary btn-sm">Download</button>
							</td>
							<td>
								<button type="button" ng-click="ctrl.remove(c.id)"
									class="btn btn-danger btn-sm">Deletar</button>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script src="<c:url value='/static/js/angular.js' />"></script>
	<script src="<c:url value='/static/js/angular-file-upload.min.js' />"></script>
	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/service/archive_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/directiveFile.js' />"></script>
	<script
		src="<c:url value='/static/js/controller/archive_controller.js' />"></script>
</body>
</html>
</html>
