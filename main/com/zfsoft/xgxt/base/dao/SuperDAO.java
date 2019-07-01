package com.zfsoft.xgxt.base.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;


/**
 * 基础增、删、改、查接口
 * @author qph
 * 日期 2013-4-10
 */
public interface SuperDAO <T>{

	/**
	 * 普通查询
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getPageList(T t) throws Exception;
	
	
	/**
	 * 按用户权限查询
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getPageList(T t, User user) throws Exception;
	
	
	/**
	 * 增加
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public boolean runInsert(T t) throws Exception;
	
	public boolean runInsertNotCommit(T t) throws Exception;
	
	
	/**
	 * 修改（不修改主键值 ）
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public boolean runUpdate(T t) throws Exception;
	
	public boolean runUpdateNotCommit(T t) throws Exception;
	
	
	/**
	 * 修改（可修改主键值 ）
	 * @param t
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public boolean runUpdate(T t ,String keyValue) throws Exception;
	
	public boolean runUpdateNotCommit(T t ,String keyValue) throws Exception;
	
	
	/**
	 * 批量删除
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public int runDelete(String[] values) throws Exception;
	
	
	/**
	 * 单个查询
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public T getModel(T t) throws Exception;
	
	
	
	/**
	 * 
	 * @描述: 单个查询，参数为主键值
	 * @作者：Penghui.Qu
	 * @日期：2013-5-8 上午11:11:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param keyValue
	 * @return
	 * @throws Exception
	 * T 返回类型 
	 * @throws
	 */
	public T getModel(String keyValue) throws Exception;
	
	
	
	
	/**
	 * 
	 * @描述: 查询全部数据
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-23 下午05:33:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getAllList(T t, User user) throws Exception;
	
	
	
	/**
	 * 
	 * @描述: 查询全部数据，普通查询模式
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-28 上午09:54:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getAllList(T t)  throws Exception;
}
