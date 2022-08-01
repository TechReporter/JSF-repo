package com.bracms.dto;

import java.util.List;

public class InformPreciosResponse {

    private List<InformePreciosDTO> body;
    private String code;

    public InformPreciosResponse() {
    }

    public InformPreciosResponse(List<InformePreciosDTO> body, String code) {
        this.body = body;
        this.code = code;
    }

	public List<InformePreciosDTO> getBody() {
		return body;
	}

	public void setBody(List<InformePreciosDTO> body) {
		this.body = body;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
}
