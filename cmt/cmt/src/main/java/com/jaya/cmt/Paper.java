package com.jaya.cmt;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Paper entity class representing a research paper submission.
 * Includes title, abstract, collaborators, and file path.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "papers")
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Abstract is mandatory")
    private String abstractContent;

    private List<String> collaborators;
    private String filePath;
    private String userMail;
    private Integer confId;
}
