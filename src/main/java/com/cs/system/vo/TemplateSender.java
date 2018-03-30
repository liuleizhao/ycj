package com.cs.system.vo;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


public class TemplateSender {


	private String touser;
	private String template_id;
	private String url;
	private List<TemplateParam> templateParamList;
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public List<TemplateParam> getTemplateParamList() {
		return templateParamList;
	}
	public void setTemplateParamList(List<TemplateParam> templateParamList) {
		this.templateParamList = templateParamList;
	}
	
	
	@Override
	public String toString() {
		return "TemplateSender [touser=" + touser + ", template_id=" + template_id + ", url=" + url + ", data=" + templateParamList
				+ "]";
	}
	
	/**
	 * obj --> json
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String senderToJSON(){
		StringBuffer buffer = new StringBuffer();  
        buffer.append("{");  
        buffer.append(String.format("\"touser\":\"%s\"",this.touser)).append(",");  
        buffer.append(String.format("\"template_id\":\"%s\"",this.template_id)).append(",");  
        buffer.append(String.format("\"url\":\"%s\"",this.url)).append(",");  
        //buffer.append(String.format("\"topcolor\":\"%s\"", sender.)).append(",");  
        buffer.append("\"data\":{");  
        TemplateParam param = null;  
        for (int i = 0; i < this.templateParamList.size(); i++) {  
             param = templateParamList.get(i);  
            // 判断是否追加逗号  
            if (i < this.templateParamList.size() - 1){  
                  
                buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"},", param.getName(), param.getValue(), param.getColor()));  
            }else{  
                buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"}", param.getName(), param.getValue(), param.getColor()));  
            }  
          
        }  
        buffer.append("}");  
        buffer.append("}");  
        return buffer.toString();  
	}

}
