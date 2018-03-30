package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;

import com.cs.common.utils.EnumUtils;
import com.cs.mvc.mybatis.enumhandler.Identifiable;
/**
 * 机动车类型
 * @author LLZ
 */
public enum VehicleTypeEnum implements Identifiable<String>{
	
		SMALL_CAR("1","小型汽车"), 
		LARGER_CAR("2","大型汽车"), 
		TRAILER("3","挂车");
     
	    private String id;  
	    private String description;
	      
	    private VehicleTypeEnum(String type ,String description){  
	        this.id = type;  
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
		
		
		public static List<VehicleTypeEnum> getAll() {
			return Arrays.asList(VehicleTypeEnum.class.getEnumConstants());
		}

		public static VehicleTypeEnum findByIndex(String index) {
			return EnumUtils.getEnum(VehicleTypeEnum.class, index);
		}

		public String getValue() {
			return this.name();
		}

		public String toString() {
			return this.name();
		}
		

}
