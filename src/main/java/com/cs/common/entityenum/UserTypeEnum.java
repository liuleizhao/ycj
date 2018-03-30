package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;
import com.cs.mvc.mybatis.enumhandler.Identifiable;
import com.cs.common.utils.EnumUtils;
/**
 * 用户类型
 * @author llz
 *
 */
public enum UserTypeEnum implements Identifiable<Integer>{
	
		ADMIN(1,"管理员"), NORMAL(2,"中心人员"), STATION(3,"检测站用户");
     
	    private int id;  
	    private String description;
	      
	    private UserTypeEnum(int type ,String description){  
	        this.id = type;  
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
		
		
		public static List<UserTypeEnum> getAll() {
			return Arrays.asList(UserTypeEnum.class.getEnumConstants());
		}

		public static UserTypeEnum findByIndex(Integer index) {
			return EnumUtils.getEnum(UserTypeEnum.class, index);
		}

		public String getValue() {
			return this.name();
		}

		public String toString() {
			return this.name();
		}
		

}
