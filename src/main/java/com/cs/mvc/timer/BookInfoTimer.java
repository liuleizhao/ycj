package com.cs.mvc.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cs.common.entityenum.BookStateEnum;
import com.cs.common.entityenum.InterfaceCodeEnum;
import com.cs.common.entityenum.InterfaceEnum;
import com.cs.common.utils.json.JSONObject;
import com.cs.common.utils.json.XML;
import com.cs.common.utils.webservice.InterfaceUtils;
import com.cs.system.entity.BookInfo;
import com.cs.system.service.BookInfoService;

public class BookInfoTimer {

	@Autowired
	private BookInfoService bookInfoService;

	@SuppressWarnings({"unchecked" })
	public void refreshBookInfo() throws Exception {

		List<BookInfo> bookInfoList = bookInfoService
				.findbyState(BookStateEnum.YYZ);

		StringBuffer bookInfoIds = new StringBuffer();

		for (int i = 0; i < bookInfoList.size(); i++) {
			bookInfoIds.append(bookInfoList.get(i).getBookNumber());
			if (i < bookInfoList.size() - 1) {
				bookInfoIds.append(",");
			}
		}

		String bookInfoXml = InterfaceUtils.callInterface(InterfaceEnum.JK08,
				bookInfoIds.toString());

		JSONObject bookInfoJSONObj = XML.toJSONObject(bookInfoXml);
		JSONObject ResponseMessage = bookInfoJSONObj
				.getJSONObject("ResponseMessage");

		String code = ResponseMessage.get("code").toString();

		if (InterfaceCodeEnum.SUCCEED.getId().equals(code)) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> resultMap = ResponseMessage.getJSONObject(
					"result").toMap();
			if (bookInfoList.size() > 1) {
				list = (List<Map<String, Object>>) resultMap.get("BookInfoVO");
			} else {
				list.add((Map<String, Object>) resultMap.get("BookInfoVO"));
			}
			bookInfoService.updateState(list);
		}
	}
}
