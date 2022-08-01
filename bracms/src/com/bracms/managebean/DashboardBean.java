package com.bracms.managebean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "dashboardBean")
@SessionScoped
public class DashboardBean {

    public static final Logger logger = LoggerFactory.getLogger(DashboardBean.class);
    
    private boolean showSubBtns = false;
    
    public void doRedirect(String url) {
        try {
        	if(url.equalsIgnoreCase("generalproductors.xhtml")){
        		if(showSubBtns == true)
        			showSubBtns = false;
        		else
        			showSubBtns = true;
        	}
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect(url);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	public boolean isShowSubBtns() {
		return showSubBtns;
	}

	public void setShowSubBtns(boolean showSubBtns) {
		this.showSubBtns = showSubBtns;
	}
    
}
