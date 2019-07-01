package xgxt.studentInfo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.form.CommanForm;
import xgxt.studentInfo.model.XsxxtjcxglForm;
import xgxt.utils.String.StringUtils;

public class XsxxtjcxglDAO extends DAO{
	ArrayList<String> value = new ArrayList<String>();
	/**
	 * ��ȡ��ѯ����
	 * @return StringBuffer
	 * */
	public StringBuffer getWhereSql(CommanForm model){		
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		String xn = model.getXn();
		String xq = model.getXq();
		String xh = model.getXh();
		String xm = model.getXm();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		
		if(!StringUtils.isNull(xn)){
			sb.append( " and xn=?");
			value.add(xn);
		}
		if(!StringUtils.isNull(xq)){
			sb.append( " and xq=?");
			value.add(xq);
		}
		if(!StringUtils.isNull(xh)){
			sb.append( " and xh like '%'||?||'%'");
			value.add(xh);
		}
		if(!StringUtils.isNull(xm)){
			sb.append( " and xm like '%'||?||'%'");
			value.add(xm);
		}
		if(!StringUtils.isNull(xydm)){
			sb.append( " and xydm=?");
			value.add(xydm);
		}
		if(!StringUtils.isNull(zydm)){
			sb.append( " and zydm=?");
			value.add(zydm);
		}
		if(!StringUtils.isNull(bjdm)){
			sb.append( " and bjdm=?");
			value.add(bjdm);
		}
		if(!StringUtils.isNull(nj)){
			sb.append( " and nj=?");
			value.add(nj);
		}
		return sb;		
	}
	
	/**
	 * ��ȡרҵ��Ϣ
	 * @param xydm
	 * @param fdyQx
	 * @param userName
	 * @return List<ArrayList<HashMap<String, String>>>
	 * */
	public List<ArrayList<HashMap<String, String>>> getZyGoupList(String xydm, String fdyQx, String userName){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> szList = getXxtjszxx();
		String[] outputValue = {"רҵ����", "רҵ����"};
		StringBuilder sql = new StringBuilder("select ");
		//��ѯҪ�ύ��������Ϣ
		for(HashMap<String, String> map : szList){
			sql.append("(select count(*) from view_xsbfxx b where a.zydm = b.zydm and b.");
			sql.append(map.get("tjzd"));
			sql.append(" = '");
			sql.append(map.get("tjzdz"));
			sql.append("')");
			sql.append(map.get("xsmc"));
			sql.append(",");
			
			outputValue = StringUtils.joinStrArr(outputValue, new String[]{map.get("xsmc")});
		}
		
		sql.append(" zydm רҵ����,zymc רҵ����  from view_njxyzybj a where xydm=?");		
		//����Ա����
		if("true".equalsIgnoreCase(fdyQx)){
			sql.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='");
			sql.append(DealString.replaceImmitStr(userName));
			sql.append("')");
		}
		//��רҵ����
		sql.append(" group by zydm,zymc order by zydm ");
		list = getList(sql.toString(), new String[]{xydm}, outputValue);		
		
		return listToList(list);
	}
	
	/**
	 * ��ȡ�༶��Ϣ
	 * @param xydm
	 * @param fdyQx
	 * @param userName
	 * @return List<ArrayList<HashMap<String, String>>>
	 * */
	public List<ArrayList<HashMap<String, String>>> getBjGoupList(String zydm, String fdyQx, String userName){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> szList = getXxtjszxx();
		String[] outputValue = {"�༶����", "�༶����"};
		StringBuilder sql = new StringBuilder("select ");
		//��ѯҪ�ύ��������Ϣ
		for(HashMap<String, String> map : szList){
			sql.append("(select count(*) from view_xsbfxx b where a.bjdm = b.bjdm and b.");
			sql.append(map.get("tjzd"));
			sql.append(" = '");
			sql.append(map.get("tjzdz"));
			sql.append("')");
			sql.append(map.get("xsmc"));
			sql.append(",");
			
			outputValue = StringUtils.joinStrArr(outputValue, new String[]{map.get("xsmc")});
		}
		
		sql.append(" bjdm �༶����,bjmc �༶����  from view_njxyzybj a where zydm=?");		
		//����Ա����
		if("true".equalsIgnoreCase(fdyQx)){
			sql.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='");
			sql.append(DealString.replaceImmitStr(userName));
			sql.append("')");
		}
		//���༶����
		sql.append(" group by bjdm,bjmc order by bjdm");
		list = getList(sql.toString(), new String[]{zydm}, outputValue);		
		
		return listToList(list);
	}
	
	/**
	 * ����ת��
	 * @param list
	 * @return List<ArrayList<HashMap<String, String>>>
	 * */
	public List<ArrayList<HashMap<String, String>>> listToList(List<HashMap<String, String>> list){
		List<ArrayList<HashMap<String, String>>> result = new ArrayList<ArrayList<HashMap<String,String>>>();
		ArrayList<HashMap<String, String>> tmpList = new ArrayList<HashMap<String,String>>();
		for(HashMap<String, String> oneMap : list){
			tmpList = new ArrayList<HashMap<String,String>>();
			HashMap<String, String> valMap = new HashMap<String, String>();
			for(String key : oneMap.keySet()){
				valMap = new HashMap<String, String>();
				valMap.put("en", key);
				valMap.put("cn", oneMap.get(key));
				tmpList.add(valMap);
			}
			result.add(tmpList);
		}
		return result;
	}
	
	/**
	 * ��ѯѧ���ɲ���Ϣ
	 * @param model
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectXsgbList(XsxxtjcxglForm model){
		String sql = "select a.*,b.xm,b.xb,b.nj,b.xz,b.xymc,b.zymc,b.bjmc,c.bjgbmc from" +
				     " sxjy_bjgbxxb a left join view_xsbfxx b on a.xh=b.xh " +
				     "left join sxjy_bjgbzlb c on a.bjgbdm=c.bjgbdm where b.bjdm=?";
		String[] outputValue = {"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "xz"};
		return getList(sql, new String[]{model.getBjdm()}, outputValue);
	}
	
	
	/**
	 * return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXxtjszxx(){
		String sql = "select * from xsxx_tjsz_szb order by tjzd";
		return getList(sql, new String[]{}, new String[]{"tjzd","tjzdz","xsmc"});
	}
	
	/**
	 * �жϼ�¼�Ƿ����
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		String sql = "select count(*) num from " + tableName + " where " + pk + "=?";
		return Integer.parseInt(getOneRs(sql, new String[]{pkValue}, "num")) >0 ? true : false;
	}
}
