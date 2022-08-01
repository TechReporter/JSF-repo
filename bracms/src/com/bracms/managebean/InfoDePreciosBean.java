package com.bracms.managebean;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
//import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.bracms.dto.InformPreciosResponse;
import com.bracms.dto.InformePreciosDTO;
import com.bracms.utility.APIConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.io.FilenameUtils;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


@Named
@RequestScoped
@ManagedBean(name = "infoDePreciosBean")
@Configuration
public class InfoDePreciosBean {

	private UploadedFile file;
	private String option;
	private String selectedPreciosArchivo;
	private List<InformePreciosDTO> informeDePreciosList = new ArrayList<InformePreciosDTO>();
	
	private List<InformePreciosDTO> dummy = new ArrayList<>();
	
	private String titulo;
	private String description;
	
	@PostConstruct
	public void clear() {
		file = null;
		selectedPreciosArchivo = null;
		option = null;
		
		InformePreciosDTO dto = new InformePreciosDTO();
		dto.setIdRetrospectiva(1);
		dto.setActivo("activo 1");
		dto.setDescripcion("description 1");
		dto.setTitulo("Titulo 1");
		dummy.add(dto);
		dto = new InformePreciosDTO();
		dto.setIdRetrospectiva(2);
		dto.setActivo("activo 2");
		dto.setDescripcion("description 2");
		dto.setTitulo("Titulo 2");
		dummy.add(dto);
		dto = new InformePreciosDTO();
		dto.setIdRetrospectiva(3);
		dto.setActivo("activo 3");
		dto.setDescripcion("description 3");
		dto.setTitulo("Titulo 3");
		dummy.add(dto);
		dto = new InformePreciosDTO();
		dto.setIdRetrospectiva(4);
		dto.setActivo("activo 4");
		dto.setDescripcion("description 4");
		dto.setTitulo("Titulo 4");
		dummy.add(dto);
		dto = new InformePreciosDTO();
		dto.setIdRetrospectiva(5);
		dto.setActivo("activo 5");
		dto.setDescripcion("description 5");
		dto.setTitulo("Titulo 5");
		dummy.add(dto);
	}

	public void saveFile() {
		FacesMessage facesMessage = null;
		FacesContext context = FacesContext.getCurrentInstance();
		int count = 0;

		if (file.getSize() > 0) {
			count++;
			String fileName = file.getFileName();
			String ext = fileName.substring(fileName.lastIndexOf('.'), fileName.length());

			if (!ext.equalsIgnoreCase(".pdf")) {
				facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please upload a pdf file", null);
				context.addMessage("", facesMessage);
			} else {
				// rest post call
			}
		} else if (count == 0) {
			RequestContext.getCurrentInstance().showMessageInDialog(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Please Select afile to process."));
		}

	}
	
	public void save() {
		
		System.out.println("Dummy method for test" );
	}

	public static void loadDetails() {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			ResponseEntity<InformPreciosResponse> response = restTemplate.getForEntity(APIConstants.GET_INFORM_DE_PRECIOS,
	                        InformPreciosResponse.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void prepareInformeDePreciosList(InformePreciosDTO[] list, List<InformePreciosDTO> informeDePreciosList ){
		for(int index = 0; index < list.length; index++){
			InformePreciosDTO bean = new InformePreciosDTO();
			bean.setIdRetrospectiva(list[index].getIdRetrospectiva());
			bean.setActivo(list[index].getActivo());
			bean.setDescripcion(list[index].getDescripcion());
			bean.setTitulo(list[index].getTitulo());
		}	
	}
	
	public void execute() {
		String param1 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param1");
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (param1 != null || id != null) {
           if(param1.equalsIgnoreCase("edit")){
        	   FacesContext context = FacesContext.getCurrentInstance();
               try {
           		setTitulo("Titulo"+" "+id);
           		setDescription("description"+" "+id);
				context.getExternalContext().redirect("infodepreciosarchivo.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           }
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", "Using RemoteCommand."));
        }

    }

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getSelectedPreciosArchivo() {
		return selectedPreciosArchivo;
	}

	public void setSelectedPreciosArchivo(String selectedPreciosArchivo) {
		this.selectedPreciosArchivo = selectedPreciosArchivo;
	}

	public List<InformePreciosDTO> getInformeDePreciosList() {
		return informeDePreciosList;
	}

	public void setInformeDePreciosList(List<InformePreciosDTO> informeDePreciosList) {
		this.informeDePreciosList = informeDePreciosList;
	}

	public List<InformePreciosDTO> getDummy() {
		return dummy;
	}

	public void setDummy(List<InformePreciosDTO> dummy) {
		this.dummy = dummy;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
}
