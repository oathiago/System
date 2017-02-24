package br.com.tao.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.tao.model.Archive;
import br.com.tao.model.Status;

@Service("archiveService")
public class ArchiveServiceImpl implements IArchiveService {

	private static final AtomicLong counter = new AtomicLong();
	private static final AtomicLong counterIdFile = new AtomicLong();

	private static List<Archive> listArchive;

	static {
		listArchive = new ArrayList<>();
	}

	public List<Archive> findAllArchives() {
		return listArchive;
	}

	/**
	 * @author thiagoado Method that saves the file and the user informed on the
	 *         screen
	 * 
	 */
	public void saveArchive(Archive archive) {
		archive.setId(counter.incrementAndGet());
		archive.setIdFile(counterIdFile.incrementAndGet());

		if (validarArquivoEnviado(archive) && validarMimeType(archive)) {
			archive.setStatus(Status.CONCLUIDO);
		} else {
			archive.setStatus(Status.FALHOU);
		}
		archive.setSizeFileStr(archive.getSizeFile());
		listArchive.add(archive);
	}

	private Boolean validarArquivoEnviado(Archive archive) {
		if (archive.getNameFile() == null || "".equals(archive.getNameFile())) {
			return Boolean.FALSE;
		}
		if (archive.getSizeFile() == null || "".equals(archive.getSizeFile())) {
			return Boolean.FALSE;
		}
		if (archive.getContentFile() == null || "".equals(archive.getContentFile())) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	private Boolean validarMimeType(Archive archive) {
		if ("".equals(archive.getMimetypeFile())) {
			String[] splitName = archive.getNameFile().split("\\.");
			if (splitName != null && splitName.length > 1) {
				archive.setMimetypeFile("application/" + splitName[1]);
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		} else {
			return Boolean.TRUE;
		}
	}

	public void deleteArchiveById(long id) {
		for (Iterator<Archive> iterator = listArchive.iterator(); iterator.hasNext();) {
			Archive archive = iterator.next();
			if (archive.getId() == id) {
				iterator.remove();
			}
		}
	}

	/*
	 * public Archive getArchiveWithFile(long id) { for (Iterator<Archive>
	 * iterator = listArchive.iterator(); iterator.hasNext();) { Archive archive
	 * = iterator.next(); if (archive.getId() == id) { String nameDirTemp =
	 * System.getProperty("java.io.tmpdir"); File file = new File(nameDirTemp +
	 * File.separator + archive.getNameFile()); FileWriter fw; try { fw = new
	 * FileWriter(file, true); BufferedWriter bw = new BufferedWriter(fw); if
	 * (archive.getContentFile() != null) bw.write(new
	 * String(archive.getContentFile())); bw.close(); fw.close();
	 * archive.setFile(file); return archive; } catch (IOException e) {
	 * e.printStackTrace(); } } } return null; }
	 */

	public Archive getArchive(long id) {
		for (Iterator<Archive> iterator = listArchive.iterator(); iterator.hasNext();) {
			Archive archive = iterator.next();
			if (archive.getId() == id) {
				return archive;
			}
		}
		return null;
	}

	/*
	 * private static List<Archive> populateDummyUsers() { List<Archive>
	 * listArchive = new ArrayList<Archive>(); listArchive.add(new
	 * Archive(counter.incrementAndGet(), "Thiago Augusto", null));
	 * listArchive.add(new Archive(counter.incrementAndGet(),
	 * "Rosana Valadares", null)); return listArchive; }
	 */
}
