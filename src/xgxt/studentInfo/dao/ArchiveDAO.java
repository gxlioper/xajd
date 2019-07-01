package xgxt.studentInfo.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.ShgcForm;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 学生档案管理DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-12</p>
 */
public class ArchiveDAO extends DAO {
	
	/**
	 * 判断表中的记录是否存在
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean isExists(String tableName,String pk, String pkValue){
		boolean flag = false;
		String sql = "select count(*) num from " + tableName + " where " + pk + " =? ";
		String result = getOneRs(sql, new String[]{pkValue}, "num");
		flag = Integer.parseInt(result) > 0 ? true : false;
		return flag;
	}
	
	/**
	 * 查询学生个人基本信息
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuInfo(String xh){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select xh, xm, xb, nj, xydm, xymc, zydm, zymc, bjdm, bjmc,mz, mzmc, zzmm, " +
				"zzmmmc, csrq, sfzh,lxdh,jg from view_xsxxb where xh=?";
		String[] outputValue = {"xh", "xm", "xb", "nj", "xydm", "xymc", "zydm", "zymc","bjdm",
				"bjmc", "mz", "mzmc", "zzmm", "zzmmmc", "csrq", "sfzh", "lxdh", "jg"};
		
		map = getMap(sql, new String[]{xh}, outputValue);
		return map;
	}
	
	/**
	 * 根据条件查询单条学生历届学生档案信息
	 * @param ShgcForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectLjxsdaxxOne(ShgcForm model){
		String sql = "select a.* from view_stu_his_archives a where xh=?";
		String[] colList = {"xh","xm","xb","nj","xydm","xymc","zydm","zymc",
				            "bjdm","bjmc","xz","jg","lxdh","mz","mzmc","sfzh",
				            "csrq","bynf","daly","dasfzx","dkjl","dxyysp","gkcj",
				            "gkyycj","hzh","jsjsp","snbjpm","zddwdz","zddwmc",
				            "zddwyb","zdfs","zdyy","zysj", "jyh", "daclr", 
				            "daclrlxfs","dabh","sjr","sjjgmc","sjsj"};;
		return getMap(sql, new String[]{model.getXh()}, colList);
	}
	
	/**
	 *生成档案转出编号
	 * @param ShgcForm model
	 * @return HashMap<String, String>
	 * */
	public String getZcbh_db(){
		String sql = "select nvl(max(zcbh),?||'0000')+1 num from stu_archives_apply where nd=?";
		return getOneRs(sql, new String[]{Base.currNd,Base.currNd}, "num");
	}
	
	/**
	 * 保存归档资料提交信息
	 * @param StudentInfoForm model
	 * @param String pk
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveGdzltj(StudentInfoForm model,String pk) throws Exception{
		String[] pkVal = pk.split("!!");		
		String gdzldm = model.getGdzldm();
		String sql = "delete from gdzltjb where instr(?,'!!'||xh||'!!')>0 and gdzldm=?";
		boolean flag = runUpdate(sql, new String[]{pk,gdzldm});
		
		if (flag) {
			String[] sqls = new String[pkVal.length];
			for (int i = 1; i < pkVal.length; i++) {
				sqls[i]="insert into gdzltjb (xh,gdzldm,sftj) values('" 
					    + pkVal[i] 
					    + "'," 
					    + "'" + gdzldm + "','已经提交')";
			}
			int[] nums = runBatch(sqls);
			flag =  checkBatch(nums);
		}
		return flag;
	}
	
	/**
	 * 保存学生在校档案信息
	 * @param StudentInfoForm model
	 * @param String pkValue
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveXszxda(StudentInfoForm model, String pkValue) throws Exception{
		String[] pk = pkValue.split("!!");
		String[] delArr = new String[pk.length];
		String[] sqlArr = new String[pk.length];
		String ddqkdm = model.getDdqkdm();
		boolean flag = false;
		
		for(int i=0; i < pk.length; i++){
			delArr[i] = "delete from xbemy_xszxdab where xh='" + pk[i] + "'";
			sqlArr[i] = "insert into xbemy_xszxdab(xh,ddqkdm) values('" 
				        + pk[i] + "','" + ddqkdm + "')";
		}
		int[] nums = runBatch(delArr);
		flag =  checkBatch(nums);
		if(flag){
		 runBatch(sqlArr);
		}
		return flag;
	}
	

	/**
	 * 档案登记
	 * 
	 */
	public List<String[]>getDadjList(){
		String sql="select dm,mc,'' checked from xsxx_daxxdj";
		return CommonQueryDAO.commonQueryNotFy("xsxx_daxxdj", "", new String[]{}, new String[]{"dm","mc","checked"}, sql);
	}
	
	/**
	 * 根据学号查询
	 * 档案登记信息
	 */
	public HashMap<String,String>getDadjXx(String xh){
		DAO dao=DAO.getInstance();
		String sql="select dadj from stu_archives where xh=?";
		return dao.getMap(sql, new String[]{xh},new String[]{"dadj"});
	}
}
