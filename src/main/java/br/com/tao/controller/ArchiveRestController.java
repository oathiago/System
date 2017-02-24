package br.com.tao.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.tao.model.Archive;
import br.com.tao.service.IArchiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/archive")
@Api(value = "/archive", description = "Serviço que controla todo o Rest para as ações sobre o Arquivo")
@MultipartConfig(fileSizeThreshold = 1024 * 1024
		* 1/*
			 * , // 1 MB maxFileSize = 1024 * 1024 * 10, // 10 MB maxRequestSize
			 * = 1024 * 1024 * 15 // 15 MB
			 */)
@Produces({ "application/json", "application/xml" })
public class ArchiveRestController {

	@Autowired
	IArchiveService archiveService;

	@RequestMapping(value = "/listAllArchives", method = RequestMethod.GET)
	@ApiOperation(value = "Listar todos os Arquivos", notes = "Método que lista todos os Arquivos cadastrados no sistema", response = Archive.class, responseContainer = "List"

	)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Não foi encontrado nenhum registro.") })
	public ResponseEntity<List<Archive>> listAllArchives() {
		List<Archive> listArchive = archiveService.findAllArchives();
		if (listArchive.isEmpty()) {
			return new ResponseEntity<List<Archive>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Archive>>(listArchive, HttpStatus.OK);
	}

	@RequestMapping(value = "/createArchive", method = RequestMethod.POST)
	@ApiOperation(value = "Listar todos os Arquivos", notes = "Método que realiza o upload de um novo arquivo utilizando AngularJs", consumes = "application/json", produces = "application/json"

	)
	public ResponseEntity<Void> createArchive(@RequestBody Archive archive, UriComponentsBuilder ucBuilder) {
		System.out.println("Inserindo Arquivo " + archive.getUserName());
		archiveService.saveArchive(archive);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/archive/{id}").buildAndExpand(archive.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/deleteArchive/{id}", method = RequestMethod.POST)
	@ApiOperation(value = "Deletar Arquivo Selecionado", notes = "Método remove o arquivo selecionado na listagem de Arquivos e devolve a lista atualizada para o usuário.")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Não foi possível localizar o arquivo selecionado.") })
	@ApiParam(name = "id", value = "Id do arquivo selecionado para e Exclusao")
	public ResponseEntity<Archive> deleteArchive(
			@ApiParam(value = "Id do arquivo que será excluído", required = true) @PathVariable("id") long id) {
		System.out.println("Deletando Arquivo com id " + id);
		archiveService.deleteArchiveById(id);
		return new ResponseEntity<Archive>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Donwload do Arquivo", notes = "Método recebe o Id do Arquivo selecionado na listae prepara o arquivo para download em AngularJs. "

	)
	@ApiParam(name = "id", value = "Id do arquivo selecionado para Download.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Download realizado com sucesso.", response = InputStreamResource.class),
			@ApiResponse(code = 500, message = "Algo inesperado ocorreu no servidor.") })
	public ResponseEntity<InputStreamResource> downloadFile(
			@ApiParam(value = "Id do arquivo será baixado.", required = true) @PathVariable("id") long id) {
		Archive archive = archiveService.getArchive(id);
		return ResponseEntity.ok()
				.header("Content-Disposition", "attachment; filename=\"" + archive.getNameFile() + "\"")
				.contentLength(archive.getContentFile().length)
				.contentType(!"".equals(archive.getMimetypeFile()) ? MediaType.parseMediaType(archive.getMimetypeFile())
						: new MediaType(""))
				.body(new InputStreamResource(new ByteArrayInputStream(archive.getContentFile())));

	}
}