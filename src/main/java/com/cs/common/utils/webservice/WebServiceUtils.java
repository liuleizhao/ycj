package com.cs.common.utils.webservice;

import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.cs.common.utils.CommonAESUtil;

/**
 * WebService请求封装类
 * @author
 * 
 */
public class WebServiceUtils {
	
	public static String namespace="http://tempuri1.org/";
	
	public static String sendRequest(String url,String jkId,String account,String password,String src) throws Exception {
		
		String responseXml = null;
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(url));
			call.setUseSOAPAction(true);
			call.setSOAPActionURI(namespace + "uniformAccess");
			call.setOperationName(new QName(namespace, "uniformAccess"));// 访问地址
			
			call.addParameter(new QName(namespace, "jkId"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(namespace, "account"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(namespace, "password"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(namespace, "src"), XMLType.XSD_STRING, ParameterMode.IN);
			
			call.setReturnType(XMLType.XSD_STRING);// 返回值类型
			call.setTimeout(2000000); // 20秒调用时间
			responseXml = (String) call.invoke(new Object[] {jkId,account,password,src});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return URLDecoder.decode(responseXml,"UTF-8");
	}
 	
}
