package com.emrecaliskan.instagramclonejava.model;

public class Post {

    private String email;
    private String comment;
    private String downloadUri;

    public Post(String email, String comment, String downloadUri) {
        this.email = email;
        this.comment = comment;
        this.downloadUri = downloadUri;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }


}
