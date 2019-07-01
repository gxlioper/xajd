package xgxt.xszz.tjgy;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.db.GetSysData;

public class XfjmService {

	private XfjmDAO dao = new XfjmDAO();
	
	
	/**
	 * 保存项目设置信息
	 * @param model
	 * @return
	 */
	public boolean saveXmsz(XfjmForm model){
		
		String xmid = model.getXmid();
		
		if (StringUtils.isNotNull(xmid)){
			try {
				boolean result = dao.updateXmsz(model);//修改项目设置信息
				if ("no".equals(model.getXgtj())){
					//不修改条件
					return result;
				} else {
					//修改项目条件信息-先删后增加了
					return result ? dao.delXmtj(model.getXmid()) && dao.addXmtj(model) : result;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			model.setXmid(GetSysData.getGuid());
			try {
				//增加项目设置信息
				boolean result = dao.addXmsz(model);
				//增加项目条件信息
				return result ? dao.addXmtj(model) : result;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	/**
	 * 项目设置统计查询
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getXmszList(XfjmForm model){
		
		return dao.getXmszList(model);
	}
	
	
	/**
	 * 查询项目设置信息
	 * @param xmid
	 * @return
	 */
	public HashMap<String,String> getXmszInfo(String xmid){
		
		return dao.getXmszInfo(xmid);
	}
	
	
	/**
	 * 查询项目所设置的条件
	 * @param xmid
	 * @return
	 */
	public List<HashMap<String,String>> getXmtjList(String xmid){
		
		return dao.getXmtjList(xmid);
	}
	
	
	/**
	 * 删除项目设置相关信息
	 * @param xmid
	 * @return
	 */
	public boolean delXmsz(String[] xmid){
		
		if (null != xmid && xmid.length > 0){
			try {
				return dao.delXmsz(xmid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	/**
	 * 根据学号查询项目申请相关信息
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getXmsqInfoByXh(String xh){
		
		return dao.getXmsqInfoByXh(xh);
	}


	
	/**
	 * 保存项目申请信息
	 * @param model
	 * @return
	 */
	public boolean saveXmsqInfo(XfjmForm model){
		
		if ("modify".equals(model.getDoType())){
			String[] input = new String[]{model.getYjxf(),model.getJmxf(),model.getSjhm(),
					StringUtils.joinStrByArray(model.getTjid(), ","),model.getBz(),
					model.getXmid(),model.getXh(),Base.currXn};
			try {
				//修改
				return dao.updateXmsqInfo(input);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String[] input = new String[]{model.getXmid(),model.getXh(),
						Base.currXn,model.getYjxf(),model.getJmxf(),
						model.getSjhm(),StringUtils.joinStrByArray(model.getTjid(), ","),
						model.getBz()};
			try {
				//增加
				return dao.saveXmsqInfo(input);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

	
	
	
	/**
	 * 查询单条项目申请信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getXmsqInfo(XfjmForm model){
		
		String pkValue = model.getPkValue();
		
		if (StringUtils.isNull(pkValue)){
			
			model.setPkValue(model.getXmid()+model.getXh()+model.getXn());
		}
		
		return dao.getXmsqInfo(model);
	}
	
	
	
	/**
	 * 项目申请记录（审核查询）
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getXmsqList(XfjmForm model,User user) throws Exception{
		
		
		return dao.getXmsqList(model,user);
	}
	
	
	
	/**
	 * 学费减免单个审核
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean saveDgsh(XfjmForm model,User user) throws Exception{
		
		String[] field = null;
		String[] input = null;
		String sysTime = GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss");
		
		if ("xy".equals(user.getUserType())){
			field = new String[]{"xysh","xyshsj","xyshr","xyshyj"};
			input = new String[]{model.getShzt(),sysTime,user.getUserName(),
					model.getXyshyj(),model.getXmid(),model.getXh(),model.getXn()};
		} else {
			field = new String[]{"xxsh","xxshsj","xxshr","xxshyj"};
			input = new String[]{model.getShzt(),sysTime,user.getUserName(),
					model.getXxshyj(),model.getXmid(),model.getXh(),model.getXn()};
		}
		
		return dao.saveDgsh(field, input);
	}
	
	
	/**
	 *判断当前审核项目是否可被通过
	 * @param model
	 * @return
	 */
	public boolean checkSfktg(XfjmForm model){
		
		String count = dao.getYshNotThisXm(model);
		
		if (Integer.valueOf(count) == 0){
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * 批量保存项目审核
	 * @param model
	 * @param pkValue
	 * @param user
	 * @return
	 */
	public boolean plxmsh(XfjmForm model , String[] pkValue ,User user){
		
		if (null != pkValue && pkValue.length > 0){
			String[] field = null;
			String[] input = null;
			String sysTime = GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss");
			
			if ("xy".equals(user.getUserType())){
				field = new String[]{"xysh","xyshsj","xyshr","xyshyj"};
				input = new String[]{model.getShzt(),sysTime,user.getUserName(),model.getShyj()};
			} else {
				field = new String[]{"xxsh","xxshsj","xxshr","xxshyj"};
				input = new String[]{model.getShzt(),sysTime,user.getUserName(),model.getShyj()};
			}
			try {
				return dao.plxmsh(field, input, pkValue);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	/**
	 * 结果查询
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getJgcxList(XfjmForm model,User user) throws Exception{
		
		
		return dao.getXmsqList(model,user);
	}
	
	
	
	/**
	 * 删除项目申请信息
	 * @param pkValues
	 * @return
	 */
	public boolean delXmsq(String[] pkValues){
		
		if (null != pkValues && pkValues.length > 0){
			try {
				return dao.delXmsq(pkValues);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	
	/**
	 * 汇总统计查询
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getExpXmsqList(XfjmForm model,User user) throws Exception{
		model.getPages().setPageSize(Integer.MAX_VALUE);
		return dao.getExpXmsqList(model, user);
	}
}
