package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;

import com.cs.common.utils.EnumUtils;
import com.cs.mvc.mybatis.enumhandler.Identifiable;
/**
 * 机动车类型
 * @author LLZ
 */
public enum DriverTypeEnum implements Identifiable<Integer>{
	
	LQ(0,"small","两驱（含非全时四驱）"), 
	QSSQ(1,"small","全时四驱"), 
	LZYX(2,"trailer","两轴及以下"), 
	LZ(3,"big","两轴"), 
	SZYSGC(4,"trailer","三轴及以上（挂车）"), 
	SZYSDXQC(5,"big","三轴及以上（大型汽车）"), 
	DZCZ(6,"big","单轴轴重超15吨"), 
	BZSZYS(7,"trailer","并装双轴及以上");
     
	    private Integer id;  
	    private String belong;
	    private String description;
	      
	    private DriverTypeEnum(Integer type,String belong ,String description){  
	        this.id = type;  
	        this.belong = belong;
	        this.description = description;
	    }  
	      
	    @Override  
	    public Integer getId(){  
	        return this.id;  
	    }

		@Override
		public String getDescription() {
			return  this.description;
		}  
		public String getBelong() {
			return  this.belong;
		}  
		
		public static List<DriverTypeEnum> getAll() {
			return Arrays.asList(DriverTypeEnum.class.getEnumConstants());
		}

		public static DriverTypeEnum findByIndex(Integer index) {
			return EnumUtils.getEnum(DriverTypeEnum.class, index);
		}

		public String getValue() {
			return this.name();
		}

		public String toString() {
			return this.name();
		}
		

}
