package com.portfolio.socialbooksotre.files;

import javax.persistence.*;

@Table(name = "social_localfiles")
@Entity
public class LocalFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "files_id")
    private long id;

    private String filePath;

    private String originFileName;

    private String newFileName;
}
