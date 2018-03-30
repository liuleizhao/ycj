package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;

import com.cs.common.utils.EnumUtils;
import com.cs.mvc.mybatis.enumhandler.Identifiable;

/**
 * 公共状态枚举
 * @author llz
 */
public enum CommonStateEnum implements Identifiable<Integer>{
	
	USABLE(1,"启用"), DISABLE(2,"停用");
	
	private int id;  
    private String description;
      
    private CommonStateEnum(int id ,String description){  
        this.id = id;  
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
	
	public static List<CommonStateEnum> getAll() {
		return Arrays.asList(CommonStateEnum.class.getEnumConstants());
	}

	public static CommonStateEnum findByIndex(Integer index) {
		return EnumUtils.getEnum(CommonStateEnum.class, index);
	}

	public String getValue() {
		return this.name();
	}

	public String toString() {
		return this.name();
	}
}
