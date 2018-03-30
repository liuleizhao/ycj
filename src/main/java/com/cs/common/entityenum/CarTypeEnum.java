package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;
import com.cs.mvc.mybatis.enumhandler.Identifiable;
import com.cs.common.utils.EnumUtils;
/**
 * 号牌种类
 * @author LLZ
 */
public enum CarTypeEnum implements Identifiable<String>{
	
	XXQC("312AED23657445C194540C794DBDBDB9","small","小型汽车（蓝底白字）"), 
	XXXNYQC("763FF1EEE4BB4C3995B402E8A7D2C550","small","小型新能源汽车（渐变绿底黑字）"), 
	
	DXQC("5AA667F13C2143F0A41C6940E74B127E","big","大型汽车（黄底黑字）"), 
	DSC("0D7E3ABB86774FD1927EE05CF82FDA4B","big","低速车（黄底黑字黑框线）"), 
	DXXNYQC("0EBEC3DB9EAA40A7B97DDD547FF58F51","big","大型新能源汽车（黄绿双拼色底黑字）"), 
	
	GC("0AAA03BC4AE74531BF1FE45A03C38577","trailer","挂车"), 
	
	WJQC("A4FA9722C81C408B8A5BB65F8BD9C9B1","small_big","外籍汽车（黑底白字）"), 
	JLQC("D7FAFC5A68004845864C42345B58D7BC","small_big","教练汽车（黄底黑字黑框线）"), 
	JYQC("31CCBA351E0A4B7AA1BAFBDE2AA93161","small_big","警用汽车"), 
	LGQC("B4394B3F2F3B4E78911713C3D54D4196","small_big","领馆汽车（黑底白字、红领字）");
     
	    private String id;  
	    private String belong;/*vehicleType*/
	    private String description;
	      
	    private CarTypeEnum(String id ,String belong,String description){  
	        this.id = id;  
	        this.belong = belong;
	        this.description = description;
	    }  
	      
	    @Override  
	    public String getId(){  
	        return this.id;  
	    }

		@Override
		public String getDescription() {
			return  this.description;
		}  
		
		public String getBelong() {
			return  this.belong;
		}  
		
		public static List<CarTypeEnum> getAll() {
			return Arrays.asList(CarTypeEnum.class.getEnumConstants());
		}

		public static CarTypeEnum findByIndex(String index) {
			return EnumUtils.getEnum(CarTypeEnum.class, index);
		}

		public String getValue() {
			return this.name();
		}

		public String toString() {
			return this.name();
		}
		

}
