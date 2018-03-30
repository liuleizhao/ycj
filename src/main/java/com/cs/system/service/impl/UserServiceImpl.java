package com.cs.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.common.entityenum.CommonStateEnum;
import com.cs.mvc.dao.BaseDao;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.service.BaseServiceSupport;
import com.cs.system.dao.UserDao;
import com.cs.system.dao.UserRoleRelationDao;
import com.cs.system.entity.User;
import com.cs.system.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
 * @描述：角色资源关联实体DAO
 * @作者：肖佳
 * @开发日期：2011-6-27
 * @版权：永泰软件有限公司
 * @版本：1.0
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceSupport<User, String> implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleRelationDao userRoleRelationDao;

	@Override
	protected BaseDao<User, String> getBaseDao() throws Exception {
		return userDao;
	}
	
	
    public User checkUser(String account, String password) throws Exception {
    	SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleNotNullCriterion("account =",account);
		sqlCondition.addSingleNotNullCriterion("password =",password);
		//sqlCondition.addSingleNotNullCriterion("t.status = ",UserStatus.ENABLE);
		List<User> users = userDao.findByCondition(sqlCondition);
		if(null != users && users.size() > 0)
		{
			return users.get(0);
		}
		
		return null;
    }

    public User findUserByAccount(String account) throws Exception {
    	SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleNotNullCriterion("account =",account);
		List<User> users = userDao.findByCondition(sqlCondition);
		if(null != users && users.size() > 0)
		{
			return users.get(0);
		}
		return null;
    }

	@Override
	public User findByName(String name) throws Exception {
        SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleNotNullCriterion("name =",name);
		List<User> users = userDao.findByCondition(sqlCondition);
		if(null != users && users.size() > 0)
		{
			return users.get(0);
		}
		return null;
	}

	@Override
	public void blockUsers(String[] userIds) {
		for(int i=0 ; i<userIds.length ; i++){
			User user = userDao.selectByPrimaryKey(userIds[i]);
			user.setStatus(CommonStateEnum.DISABLE);
			userDao.updateByPrimaryKey(user);
		}
	}
	
	@Override
	public PageInfo<User> findByRole(String roleId, Integer pageNum,
			Integer pageSize) throws Exception {
		if(pageNum ==null || pageNum <1){
			pageNum = this.pageNum;
		}
		
		if(pageSize == null || pageSize <1){
			pageSize = this.pageSize;
		}
		PageHelper.startPage(pageNum, pageSize);
		List<User> list = userDao.findByRole(roleId);
		PageInfo<User> page = new PageInfo<User>(list);
		return page;
	}

	@Override
	public void saveResourceByRoleIds(String userId, String[] roleIds) {
		//通过用户id和角色ID去删除用户角色关系
		userRoleRelationDao.deletes(userId,roleIds);
		userRoleRelationDao.inserts(userId,roleIds);
	}

}
