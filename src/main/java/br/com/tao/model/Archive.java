package br.com.tao.model;

import java.text.DecimalFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Archive {

	@JsonProperty("id")
	private long id;

	@JsonProperty("userName")
	private String userName;

	@JsonProperty("mimetypeFile")
	private String mimetypeFile;

	@JsonProperty("nameFile")
	private String nameFile;

	@JsonProperty("idFile")
	private long idFile;

	@JsonProperty("contentFile")
	private byte[] contentFile;

	@JsonProperty("sizeFile")
	private Long sizeFile;

	@JsonProperty("sizeFileStr")
	private String sizeFileStr;

	@JsonProperty("Status")
	private Status Status;

	@JsonProperty("timeUpload")
	private String timeUpload;

	public Archive() {
		this.id = 0;
	}

	@JsonIgnore
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonIgnore
	public long getIdFile() {
		return idFile;
	}

	public void setIdFile(long idFile) {
		this.idFile = idFile;
	}

	@JsonIgnore
	public String getMimetypeFile() {
		return mimetypeFile;
	}

	public void setMimetypeFile(String mimetypeFile) {
		this.mimetypeFile = mimetypeFile;
	}

	public String getNameFile() {
		return nameFile;
	}

	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

	@JsonIgnore
	public byte[] getContentFile() {
		return contentFile;
	}

	public void setContentFile(byte[] contentFile) {
		this.contentFile = contentFile;
	}

	@JsonIgnore
	public Long getSizeFile() {
		return sizeFile;
	}

	public void setSizeFile(Long sizeFile) {
		this.sizeFile = sizeFile;
	}

	public String getSizeFileStr() {
		return sizeFileStr;
	}

	public void setSizeFileStr(long sizeFile) {
		DecimalFormat dec = new DecimalFormat("0.00");
		if (sizeFile > 0) {
			this.sizeFileStr = dec.format((double) sizeFile / 100).concat("Bytes");
		}
		if (sizeFile > 1000) {
			this.sizeFileStr = dec.format((double) sizeFile / 1000).concat("KB");
		}
		if (sizeFile > 1000000) {
			this.sizeFileStr = dec.format((double) sizeFile / 1000000).concat("MB");
		}
		if (sizeFile > 1000000000) {
			this.sizeFileStr = dec.format((double) sizeFile / 1000000000).concat("GB");
		}
	}

	@JsonIgnore
	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status status) {
		Status = status;
	}

	@JsonProperty("statusStr")
	public String getStatusStr() {
		return Status.toString();
	}

	public String getTimeUpload() {
		return timeUpload;
	}

	public void setTimeUpload(String timeUpload) {
		this.timeUpload = timeUpload;
	}
}
