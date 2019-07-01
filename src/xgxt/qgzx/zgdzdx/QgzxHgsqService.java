package xgxt.qgzx.zgdzdx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class QgzxHgsqService {
	QgzxHgsqDAO qgzxhgsqdao = new QgzxHgsqDAO();
	/**获取岗位允许申请时间*/
	public String[] getStuTimeService(){
		return qgzxhgsqdao.getStuTime();
	}
	
	/**获取岗位名称列表*/
	public List<HashMap<String, String>> getGwNameListService(String isLsgw){
		return qgzxhgsqdao.getGwNameList(isLsgw);
	}
	
	/**获取岗位名称*/
	public String[] getGwNameService(String tmp){
		return qgzxhgsqdao.getGwName(tmp);
	}
	
	/**获取学生通过学院推荐记录*/
	public String getXytjcountService(String xh,String xn,String nd,String xq){
		String result = qgzxhgsqdao.getXytjcount(xh, xn, nd, xq);
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		return result;
	}
	
	/**获取学生岗位审核记录*/
	public boolean getGwshcountService(String xh,String xn,String nd,String xq){
		boolean flag = false;
		String xxdm = StandardOperation.getXxdm();
		String result = qgzxhgsqdao.getGwshcount(xh, xn, nd, xq);
		flag = Integer.parseInt(StringUtils.isNull(result) ? "0" : result)>0 ? true : false;
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//广州大学
			flag = qgzxhgsqdao.checkExists("xsgwxxb", "xh||xn||nd||xq||sfyx", xh+xn+nd+xq+"1");//sfyx='1'有效
		}
		return flag;
	}
	
	/**获取学生在岗岗位*/
	public String getGwdmService(String xh,String xn,String nd,String xq){
		return qgzxhgsqdao.getGwdm(xh, xn, nd, xq);
	}
	
	/**学生勤工助学时间更新
	 * @throws Exception */
	public void updateQgzxTime(String xh,HttpServletRequest request) throws Exception{
		String[][] kxf = new String[4][7];
		for(int i=0; i<4; i++){
			for(int j=0; j<7; j++){
				kxf[i][j] = request.getParameter(String.valueOf(i)+String.valueOf(j+1))==null?"0":"1";
			}
		}
		if(kxf!=null){
			qgzxhgsqdao.deleteQgzxTime(xh);
			for(int i=0; i<4; i++){
				for(int j=0; j<7; j++){
					qgzxhgsqdao.saveQgzxTime(xh, String.valueOf(i+1), String.valueOf(j+1), kxf[i][j]);
				}
			}
		}
	}
	
	/**保存学生勤工助学换岗的其他信息
	 * @throws SQLException */
	public boolean hgxx_saveService(String tmp[]) throws SQLException{
		return qgzxhgsqdao.hg_save(tmp);
	}
	
	/**根据学院代码获取岗位列表*/
	public List<HashMap<String, String>> getGwListForXydmService(String userName){
		return qgzxhgsqdao.getGwListForXydm(userName);
	}
	
	/**获取岗位列表*/
	public List<HashMap<String, String>> getGwListService(String userName){
		return qgzxhgsqdao.getGwList(userName);
	}
	
	/**获取岗位性质列表*/
	public List<HashMap<String, String>> getGwXzListService(){
		return qgzxhgsqdao.getGwXzList();
	}
	
	/**获取申请换岗的学生列表*/
	public ArrayList<String[]> getHgXsService(StringBuffer querry,String[] colList,String userType){
		if("xx".equalsIgnoreCase(userType)){
			querry.append(" and xyyj='通过'");
		}
		return qgzxhgsqdao.getHgXs(querry, colList);
	}
	
	/**获取学生单条换岗详细信息*/
	public HashMap<String, String> getQgzxOneService(String pkValue){
		return qgzxhgsqdao.getQgzxOne(pkValue);
	}
	
	/**学生换岗信息更新
	 * @throws Exception */
	public boolean hgxx_updateService(String[] tmp,String pkValue) throws Exception{
		String[] tmp1 = pkValue.split("-");
		String[] tmp2 = new String[tmp.length+tmp1.length];
		for(int i=0;i <tmp.length;i++) 
		{ 
		  tmp2[i]=tmp[i]; 
		} 
		for(int j=0;j <tmp1.length;j++) 
		{ 
		  tmp2[tmp.length+j]=tmp1[j]; 
		}
		return qgzxhgsqdao.hgxx_update(tmp2);
	}
	
	/**学生换岗审核
	 * @throws Exception */
	public boolean hgsqshService(String[] pkTmp,String userType,String type) throws Exception{
		boolean res = false;
		if("pass".equalsIgnoreCase(type)){
			type="通过";
		}else{
			type="不通过";
		}
		for(int i=0;i<pkTmp.length;i++){
			String[] tmp = pkTmp[i].split("-");
			res=qgzxhgsqdao.hgsqsh(tmp,userType,type);//审核
			if(!res){
				break;
			}
			if("xx".equalsIgnoreCase(userType)&&"通过".equalsIgnoreCase(type)){
				HashMap<String, String> rsMapGw = qgzxhgsqdao.getXsgwxx(tmp[0], tmp[1], tmp[2]);
				HashMap<String, String> rsMapHg = qgzxhgsqdao.getXshgxx(tmp);
				if(rsMapGw.size()!=0){
					//更新岗位信息表中的详细信息，将岗位变化的记录插入到岗位调整表
					res = qgzxhgsqdao.updateXsgwxx(rsMapHg,tmp[0], tmp[1], tmp[2]);//更新岗位信息
					if(!res){//更新成功则将信息插入调岗记录
						break;
					}
					//删除岗位信息表中学生现在在岗记录
					res = qgzxhgsqdao.deleteXsgwxxb(tmp[0], tmp[1], tmp[2]);//更新岗位信息
					qgzxhgsqdao.updateQgzxsqb(tmp[0]);//更新辞岗学生在勤工助学表中的审核状态
					if(!res){//更新成功则将信息插入调岗记录
						break;
					}
					qgzxhgsqdao.insertDgjl(Base.currXn,Base.currXq,rsMapGw);
					//删除学生空闲时间表中的辞岗学生记录
					qgzxhgsqdao.deleteQgzxTime(tmp[0]);
					
					qgzxhgsqdao.hgsqshOther(tmp,userType,"不通过");//审核通过后其他申请设置为不通过
					qgzxhgsqdao.insertDgjl(Base.currXn,Base.currXq,rsMapGw,rsMapHg);
				}
			}
		}
		return res;
	}
	
	/**保存学生辞岗信息
	 * @throws Exception */
	public boolean cgxx_saveService(String[] tmp,String modi) throws Exception{
		if("yes".equalsIgnoreCase(modi)){			
			return qgzxhgsqdao.cgxx_update(tmp);
		}
		return qgzxhgsqdao.cgxx_save(tmp);
	}
	
	/**获取申请辞岗的学生列表*/
	public ArrayList<String[]> getCgXsService(StringBuffer querry,String[] colList,String userType){
		if("xx".equalsIgnoreCase(userType)){
			querry.append(" and xyyj='通过'");
		}
		return qgzxhgsqdao.getCgXs(querry, colList);
	}
	
	/**学生辞岗审核
	 * @throws Exception */
	public boolean cgsqshService(String[] pkTmp,String userType,String type) throws Exception{
		boolean res = false;
		boolean delFlag = true;
		String xxdm = StandardOperation.getXxdm();
		if("pass".equalsIgnoreCase(type)){
			type="通过";
		}else{
			type="不通过";
		}
		
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//广州大学 
			delFlag = false;
		}
		for(int i=0;i<pkTmp.length;i++){
			String[] tmp = pkTmp[i].split("-");
			res=qgzxhgsqdao.cgsqsh(tmp,userType,type);//审核
			if(!res){
				break;
			}
			if("xx".equalsIgnoreCase(userType)&&"通过".equalsIgnoreCase(type)){
				HashMap<String, String> rsMapGw = qgzxhgsqdao.getXsgwxx(tmp[0], tmp[1], tmp[2]);
				
				if(delFlag){
					//删除岗位信息表中辞岗学生记录
					res = qgzxhgsqdao.deleteXsgwxxb(tmp[0], tmp[1], tmp[2]);//更新岗位信息
					qgzxhgsqdao.updateQgzxsqb(tmp[0]);//更新辞岗学生在勤工助学表中的审核状态
					if(!res){//更新成功则将信息插入调岗记录
						break;
					}
				}else{
					qgzxhgsqdao.runUpdate("update xsgwxxb set sfyx='0' where xh=? and gwdm=? and gwsbsj=?", tmp);
				}
				qgzxhgsqdao.insertDgjl(Base.currXn,Base.currXq,rsMapGw);
				//删除学生空闲时间表中的辞岗学生记录
				qgzxhgsqdao.deleteQgzxTime(tmp[0]);
			}
		}
		return res;
	}
	
	/**获取学生单条辞岗详细信息*/
	public HashMap<String, String> getQgzxcgOneService(String pkValue){
		return qgzxhgsqdao.getQgzxcgOne(pkValue);
	}
	
	/**获取用人单位信息*/
	public HashMap<String, String> getYrdwxxService(String userDep){
		return qgzxhgsqdao.getYrdwxx(userDep);
	}
	
	/**中国地质大学获取用人单位辞退学生临时信息*/
	public ArrayList<String[]> getYrdwctxslsxxserivce(String userName){
		return qgzxhgsqdao.getYrdwctxslsxx(userName);
	}
	/**中国地质大学获取用人单位更换学生临时信息*/
	public ArrayList<String[]> getYrdwghxslsxxserivce(String userName){
		return qgzxhgsqdao.getYrdwghxslsxx(userName);
	}
	/**中国地质大学获取用人单位申请学生临时信息*/
	public ArrayList<String[]> getYrdwsqxslsxxserivce(String userName){
		return qgzxhgsqdao.getYrdwsqxslsxx(userName);
	}
	
	/**中国地质大学用人单位更换学生正式数据保存
	 * @throws Exception */
	public boolean insertYrdwghXsService(String[] tmp,String xn,String nd,String xq,String sqly,String bz) throws Exception{
		String xh=tmp[0];
		String gwdm=tmp[1];
		String gwsbsj=tmp[2];
		String lxdh=tmp[3];
		String bj=tmp[4];
		String yhm=tmp[5];
		String num = qgzxhgsqdao.getYrdwghXs(xh,gwdm,gwsbsj,xn,nd,xq);
		if(!"0".equalsIgnoreCase(num)){
			return false;
		}
		return qgzxhgsqdao.insertYrdwghXs(xh,gwdm,gwsbsj,lxdh,bj,yhm,xn,nd,xq,sqly,bz);
	}
	
	/**清理临时表中的学生记录
	 * @throws Exception */
	public boolean deleteYrdwghxslsbSerivce(String userName) throws Exception{
		return qgzxhgsqdao.deleteYrdwghxslsb(userName);
	}
	
	
	/**获取用人单位更换的学生列表*/
	public ArrayList<String[]> getYrdwGhXsService(StringBuffer querry,String[] colList,String userType){
		return qgzxhgsqdao.getYrdwGhXs(querry, colList);
	}
	
	/**获取用人单位更换的学生详细信息*/
	public HashMap<String, String> getYrdwGhXsOneService(String pkValue){
		return qgzxhgsqdao.getYrdwGhXsOne(pkValue);
	}
	
	
	/**用人单位更换学生审核
	 * @throws Exception */
	public boolean yrdwGhxsshService(String[] pkTmp,String userType,String type) throws Exception{
		boolean res = false;
		if("pass".equalsIgnoreCase(type)){
			type="通过";
		}else{
			type="不通过";
		}
		for(int i=0;i<pkTmp.length;i++){
			String[] tmp = pkTmp[i].split("-");
			res=qgzxhgsqdao.yrdwGhxssh(tmp,type);//审核
			if(!res){
				break;
			}
			if("辞退".equalsIgnoreCase(tmp[3])){
				if("xx".equalsIgnoreCase(userType)&&"通过".equalsIgnoreCase(type)){
					HashMap<String, String> rsMapGw = qgzxhgsqdao.getXsgwxx(tmp[0], tmp[1], tmp[2]);
				
					//删除岗位信息表中辞岗学生记录
					res = qgzxhgsqdao.deleteXsgwxxb(tmp[0], tmp[1], tmp[2]);//更新岗位信息
					qgzxhgsqdao.updateQgzxsqb(tmp[0]);//更新辞岗学生在勤工助学表中的审核状态
					if(!res){//更新成功则将信息插入调岗记录
						break;
					}
					qgzxhgsqdao.insertDgjl(Base.currXn,Base.currXq,rsMapGw);
					//删除学生空闲时间表中的辞岗学生记录
					qgzxhgsqdao.deleteQgzxTime(tmp[0]);
				}
			}
			if("换岗".equalsIgnoreCase(tmp[3])){
				if("xx".equalsIgnoreCase(userType)&&"通过".equalsIgnoreCase(type)){
					HashMap<String, String> rsMapGw = qgzxhgsqdao.getXsgwxx(tmp[0], tmp[1], tmp[2]);
					HashMap<String, String> rsMapHg = qgzxhgsqdao.getYrdwGhXsOne(tmp[0], tmp[1], tmp[2],tmp[3]);
					
					//更新岗位信息表中的详细信息，将岗位变化的记录插入到岗位调整表
					res = qgzxhgsqdao.updateXsgwxxb(tmp[0], tmp[1], tmp[2]);//更新岗位信息
					if(!res){//更新成功则将信息插入调岗记录
						break;
					}
					qgzxhgsqdao.yrdwHgsqshOther(tmp,userType,"不通过");//审核通过后其他申请设置为不通过
					qgzxhgsqdao.insertYrdwDgjl2(Base.currXn,Base.currXq,rsMapGw,rsMapHg);
				}
			}
			if("申请".equalsIgnoreCase(tmp[3])){
				if("xx".equalsIgnoreCase(userType)&&"通过".equalsIgnoreCase(type)){
					HashMap<String, String> rsMapHg = qgzxhgsqdao.getYrdwGhXsOne(tmp[0], tmp[1], tmp[2],tmp[3]);
					if(!qgzxhgsqdao.checkExists("xsgwxxb", "xh||gwdm||gwsbsj", rsMapHg.get("xh")+rsMapHg.get("gwdm")+rsMapHg.get("gwsbsj"))){
						res = qgzxhgsqdao.insertXsgwxxb(rsMapHg);
					}					
					if(!res){
						break;
					}
				}
			}
		}
		return res;
	}
	
	/**
	 * 判断用户是否是用人单位用户
	 * @param String userName
	 * @return boolean
	 * */
	public boolean checkIsYrdwUser(String userName){
		return qgzxhgsqdao.isYrdw(userName);
	}
	
	/**
	 * 查询学生在的岗位列表
	 * @param String xh
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXsgwList(String xh){
		return qgzxhgsqdao.selectXsgwList(xh);
	}
}
