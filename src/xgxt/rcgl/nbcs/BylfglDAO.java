package xgxt.rcgl.nbcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

public class BylfglDAO extends DAO{
	ArrayList<String> value = new ArrayList<String>();
	
	/**
	 * ��ȡ��ѯ����
	 * */
	public StringBuffer getWhereSql(BylfglForm model){
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		
		String xydm = model.getXydm();
		String nd = model.getNd();
		String yf = model.getYf();
		
		if(!StringUtils.isNull(xydm)){
			sb.append( " and xydm=?");
			value.add(xydm);
		}
		if(!StringUtils.isNull(nd)){
			sb.append( " and nd=?");
			value.add(nd);
		}
		if(!StringUtils.isNull(yf)){
			sb.append( " and yf=?");
			value.add(yf);
		}
		return sb;
	}
	
	/**
	 * ����¼�Ƿ����
	 * @param String tableName
	 * @param String pk
	 * @param String pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		String sql = "select count(*)num from " + tableName + " where " + pk + " =?";
		String result = getOneRs(sql, new String[]{pkValue},"num");
		return Integer.parseInt(StringUtils.isNull(result) ? "0" : result) >0 ? true : false;
	}
	
	
	
	/**
	 * ��ѯѧԺ��ҵ�����Ϣ
	 * @param BylfglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectBylfglb(BylfglForm model,String[] outputValue){
		outputValue = outputValue ==  null ? new String[]{"pk","bgcolor","nd","xymc","sqsj","xxsh","shrxm","shsj"} : outputValue;
		String whereSql = getWhereSql(model).toString();
		String sql = "select  rownum r, a.* ,decode(xxsh,'ͨ��','#99CCFF','��ͨ��','#FF9999','δ���','#FFFFFF') bgcolor from (select nd||xydm pk,a.nd ,a.xymc,a.sqsj,a.xxsh,a.shrxm,a.shsj,a.pcje,shfz,shmaozi,shlingjie,shlingdai from view_bylfglb a" + whereSql + ")a";
		Pages paganitionModel = model.getPages();
		paganitionModel.setMaxRecord(Integer.parseInt(getOneRs("select count(*) num from view_bylfglb" + whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, "num")));
		sql = "select * from (" + sql + ") where r>" + model.getPages().getStart() + " and r<=" + (model.getPages().getStart()+model.getPages().getPageSize()); 
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * ��ѯѧԺ��ҵ�����Ϣ����
	 * @param BylfglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectBylfglbForExp(BylfglForm model){
		String[] outputValue = {"nd","xydm","xymc","sqsj","lqr","xxsh","shryhm","shrxm","shsj","bkfxl","bkfl","bkfm","bkfs","zkfxl","zkfl","zkfm","zkfs","dsfl","dsfm","dsfs","dsfxl","ghbkfl","xzfxl","xzfl","xzfm","xzfs","ghbkfxl","ghbkfl","ghbkfm","ghbkfs","ghzkfxl","ghzkfl","ghzkfm","ghzkfs","ghdsfxl","ghdsfl","ghdsfm","ghdsfs","ghxzfxl","ghxzfl","ghxzfm","ghxzfs","ghmaozi","ghpijian","ghlingdai","ghlingjie","lingdai","lingjie","maozi","pcje","pijian","shfz","shlingdai","shlingjie","shmaozi"};
		String whereSql = getWhereSql(model).toString();
		String sql = "select bkfl,bkfm,bkfs,bkfxl,dsfl,dsfm,dsfs,dsfxl,ghbkfl,ghbkfm,ghbkfs,ghbkfxl,ghdsfl,ghdsfm,ghdsfs,ghdsfxl,ghxzfl,ghxzfm,ghxzfs,ghxzfxl,ghzkfl,ghzkfm,ghzkfs,ghzkfxl,ghmaozi,ghpijian,ghlingdai,ghlingjie,lingdai,lingjie,lqr,maozi,nd,pcje,pijian,shfz,shlingdai,shlingjie,shmaozi,shryhm,shrxm,shsj,sqsj,xxsh,xydm,xymc,xzfl,xzfm,xzfs,xzfxl,zkfl,zkfm,zkfs,zkfxl from view_bylfglb ";
		
		return rsToVator(sql+whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	
	/**
	 * ����������ѯѧԺ��ҵ�����Ϣ
	 * @param BylfglForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectBylfglbOne(BylfglForm model){
		String[] outputValue = {"pk","bkfl","bkfm","bkfs","bkfxl","dsfl","dsfm","dsfs","dsfxl","ghbkfl","ghbkfm","ghbkfs","ghbkfxl","ghdsfl","ghdsfm","ghdsfs","ghdsfxl","ghxzfl","ghxzfm","ghxzfs","ghxzfxl","ghzkfl","ghzkfm","ghzkfs","ghzkfxl","lingdai","lingjie","lqr","maozi","nd","pcje","pijian","shfz","shlingdai","shlingjie","shmaozi","shryhm","shrxm","shsj","sqsj","xxsh","xydm","xymc","xzfl","xzfm","xzfs","xzfxl","zkfl","zkfm","zkfs","zkfxl","ghmaozi","ghpijian","ghlingdai","ghlingjie"};
		String sql = "select nd||xydm pk,bkfl,bkfm,bkfs,bkfxl,dsfl,dsfm,dsfs,dsfxl,ghbkfl,ghbkfm,ghbkfs,ghbkfxl,ghdsfl,ghdsfm,ghdsfs,ghdsfxl,ghxzfl,ghxzfm,ghxzfs,ghxzfxl,ghzkfl,ghzkfm,ghzkfs,ghzkfxl,lingdai,lingjie,lqr,maozi,nd,pcje,pijian,shfz,shlingdai,shlingjie,shmaozi,shryhm,shrxm,shsj,sqsj,xxsh,xydm,xymc,xzfl,xzfm,xzfs,xzfxl,zkfl,zkfm,zkfs,zkfxl,ghmaozi,ghpijian,ghlingdai,ghlingjie from view_bylfglb where nd||xydm=?";
		
		return getMap(sql, new String[]{model.getPkValue()}, outputValue);
	}
	
	/**
	 * ��ѯ��ҵ����黹��Ϣ
	 * @param BylfglForm model
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectBylfghForAll(BylfglForm model){
		String[] outputValue = {"bkfl","bkfm","bkfs","bkfxl","dsfl","dsfm","dsfs","dsfxl","ghbkfl","ghbkfm","ghbkfs","ghbkfxl","ghdsfl","ghdsfm","ghdsfs","ghdsfxl","ghxzfl","ghxzfm","ghxzfs","ghxzfxl","ghzkfl","ghzkfm","ghzkfs","ghzkfxl","ghmaozi","ghpijian","ghlingdai","ghlingjie","lingdai","lingjie","lqr","maozi","nd","pcje","pijian","shfz","shlingdai","shlingjie","shmaozi","shryhm","shrxm","shsj","sqsj","xxsh","xydm","xymc","xzfl","xzfm","xzfs","xzfxl","zkfl","zkfm","zkfs","zkfxl"};
		String whereSql = getWhereSql(model).toString();
		String sql = "select bkfl,bkfm,bkfs,bkfxl,dsfl,dsfm,dsfs,dsfxl,ghbkfl,ghbkfm,ghbkfs,ghbkfxl,ghdsfl,ghdsfm,ghdsfs,ghdsfxl,ghxzfl,ghxzfm,ghxzfs,ghxzfxl,ghzkfl,ghzkfm,ghzkfs,ghzkfxl,ghmaozi,ghpijian,ghlingdai,ghlingjie,lingdai,lingjie,lqr,maozi,nd,pcje,pijian,shfz,shlingdai,shlingjie,shmaozi,shryhm,shrxm,shsj,sqsj,xxsh,xydm,xymc,xzfl,xzfm,xzfs,xzfxl,zkfl,zkfm,zkfs,zkfxl from view_bylfglb ";
		
		return getList(sql+whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * ���ѧԺ��ҵ�����Ϣ�Ƿ��Ѿ�����
	 * @param HashMap<String, String> map
	 * @return boolean
	 * */
	public boolean checkBylf(HashMap<String, String> map){		
		return checkExists("bylfglb", "nd||xydm", map.get("nd")+map.get("xydm"));
	}	
}
