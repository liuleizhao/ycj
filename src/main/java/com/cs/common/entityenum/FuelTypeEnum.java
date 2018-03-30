package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;

import com.cs.common.utils.EnumUtils;
import com.cs.mvc.mybatis.enumhandler.Identifiable;
/**
 * 号牌种类
 * @author LLZ
 */
public enum FuelTypeEnum implements Identifiable<Integer>{
	
		QY_D(1,"small_big","汽油、混合动力、纯电"), 
		X_CY_D(2,"small_big","柴油、柴电混合（总质量小于等于3500KG）"), 
		D_CY_D(3,"","柴油、柴电混合（总质量大于3500KG）");
     
	    private Integer id;  
	    private String belong;  
	    private String description;
	      
	    private FuelTypeEnum(Integer type,String belong,String description){  
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
			return belong;
		}

		public static List<FuelTypeEnum> getAll() {
			return Arrays.asList(FuelTypeEnum.class.getEnumConstants());
		}

		public static FuelTypeEnum findByIndex(Integer index) {
			return EnumUtils.getEnum(FuelTypeEnum.class, index);
		}

		public String getValue() {
			return this.name();
		}

		public String toString() {
			return this.name();
		}
		

}
