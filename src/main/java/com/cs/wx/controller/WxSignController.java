package com.cs.wx.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.mvc.web.BaseController;
import com.cs.wx.service.impl.WxServiceImpl;

@Controller
@RequestMapping(value = "/")
public class WxSignController extends BaseController{
	
	@Autowired
	WxServiceImpl WxServiceImpl;
	
	/**
	 * 验证服务器地址的有效性
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/sign")
	@ResponseBody
	public String sign(HttpServletRequest request,Model model){
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (WxServiceImpl.checkSignature(signature, timestamp, nonce, echostr)) {
            return echostr;
        }
        return "";
	}
	
	/**
	 * 授权文件验证
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/MP_verify_rf7dLOCv6dCGiZ01.txt")
	public void mp(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		request.setCharacterEncoding("UTF-8");
		 // 下载本地文件
        String fileName = "MP_verify_rf7dLOCv6dCGiZ01.txt"; // 文件的默认保存名
        // 读到流中
        InputStream inStream = new FileInputStream(
        		request.getSession().getServletContext().getRealPath(fileName));// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 测试页面的接口
	 * @param request
	 * @param code
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/test/{code}",method = RequestMethod.GET)
	public String testCode(HttpServletRequest request,@PathVariable String code,Model model){
		System.out.println(code);
		return "app/"+code;
	}
}
