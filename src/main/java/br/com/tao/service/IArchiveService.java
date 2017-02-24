package br.com.tao.service;

import java.util.List;

import br.com.tao.model.Archive;

public interface IArchiveService {

	void saveArchive(Archive archive);

	void deleteArchiveById(long id);

	List<Archive> findAllArchives();

	/* public Archive getArchiveWithFile(long id); */

	public Archive getArchive(long id);

}
