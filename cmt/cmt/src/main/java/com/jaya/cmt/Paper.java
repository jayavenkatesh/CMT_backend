package com.jaya.cmt;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Abstract is mandatory")
    private String abstractContent; 

    //@ManyToMany
    private List<String> collaborators; 
    
   

    private String filePath;

    private String userMail;
    
    public String getUserMail() {
		return userMail;
	}


	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}


	public Integer getConfId() {
		return confId;
	}


	public void setConfId(Integer confId) {
		this.confId = confId;
	}


	private Integer confId;
    
    public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAbstractContent() {
		return abstractContent;
	}


	public void setAbstractContent(String abstractContent) {
		this.abstractContent = abstractContent;
	}


	public List<String> getCollaborators() {
		return collaborators;
	}


	public void setCollaborators(List<String> collaborators) {
		this.collaborators = collaborators;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public Paper() {
    }
}
