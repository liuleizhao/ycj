package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;

import com.cs.common.utils.EnumUtils;
import com.cs.mvc.mybatis.enumhandler.Identifiable;
/**
 * 机动车类型
 * @author LLZ
 */
public enum IdTypeEnum implements Identifiable<String>{
	
		JMSFZ("e4e48584399473d20139947f125e2b2c","居民身份证"), 
		ZZJGDMZS("40288282463ceca50146462942d3055c","组织机构代码证书"), 
		TYSHXYDM("4028823f51d79d4d0151f1ebb1dc361e","统一社会信用代码车"), 
		JWRYSFZM("e4e48584399b293601399b60996b55e6","境外人员身份证明");
     
	    private String id;  
	    private String description;
	      
	    private IdTypeEnum(String type ,String description){  
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
		
		
		public static List<IdTypeEnum> getAll() {
			return Arrays.asList(IdTypeEnum.class.getEnumConstants());
		}

		public static IdTypeEnum findByIndex(String index) {
			return EnumUtils.getEnum(IdTypeEnum.class, index);
		}

		public String getValue() {
			return this.name();
		}

		public String toString() {
			return this.name();
		}
		

}
