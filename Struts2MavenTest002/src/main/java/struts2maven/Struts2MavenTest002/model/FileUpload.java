package struts2maven.Struts2MavenTest002.model;

import java.io.File;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

public class FileUpload {

	private File document;
	private String documentContentType;
	private String documentFileName;

	public void setDocument(File document) {
		this.document = document;
	}
	public File getDocument() {
		return document;
	}
	public void setDocumentContentType(String documentContentType) {
		this.documentContentType = documentContentType;
	}
	public String getDocumentContentType() {
		return documentContentType;
	}
	public void setDocumentFileName(String documentFileName) {
		this.documentFileName = documentFileName;
	}
	public String getDocumentFileName() {
		return documentFileName;
	}
}
