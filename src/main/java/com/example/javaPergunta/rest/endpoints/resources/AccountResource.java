package com.example.javaPergunta.rest.endpoints.resources;

import org.springframework.hateoas.RepresentationModel;
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResource extends RepresentationModel<AccountResource> {
    private String id;

    public String getid(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }
}
