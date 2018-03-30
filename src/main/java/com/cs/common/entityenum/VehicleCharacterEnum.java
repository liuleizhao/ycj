package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;
import com.cs.mvc.mybatis.enumhandler.Identifiable;
import com.cs.common.utils.EnumUtils;
/**
 * 号牌种类
 * @author LLZ
 */
public enum VehicleCharacterEnum implements Identifiable<Integer>{
	
	XXZKQC(1,"small","小型载客汽车"), 
	XXZHQC(2,"small","小型载货汽车(含专项作业车)"), 
	XXXC(3,"small","校车（小型）"), 
	ZXZKQC(4,"big","中型载客汽车"), 
	DXZKQC(5,"big","大型载客汽车"), 
	ZXZHQC(6,"big","中型载货汽车"), 
	DXZHQC(7,"big","重型载货汽车"), 
	ZXZYC(8,"big","专项作业车"), 
	DXXC(9,"big","校车（大型）"), 
	WGDC(10,"big","无轨电车"),
	PTGC(12,"big","普通挂车"),
	
	DSZHQC(11,"trailer","低速载货汽车"),
	WHPYSC(13,"","危化品运输车");
     
	    private Integer id;  
	    private String belong;/*vehicleType*/
	    private String description;
	      
	    private VehicleCharacterEnum(Integer type,String belong ,String description){  
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
		
		public static List<VehicleCharacterEnum> getAll() {
			return Arrays.asList(VehicleCharacterEnum.class.getEnumConstants());
		}

		public static VehicleCharacterEnum findByIndex(Integer index) {
			return EnumUtils.getEnum(VehicleCharacterEnum.class, index);
		}

		public String getValue() {
			return this.name();
		}

		public String toString() {
			return this.name();
		}
		

}
