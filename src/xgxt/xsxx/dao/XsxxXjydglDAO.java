package xgxt.xsxx.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.studentInfo.dao.StudentInfoDao;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.common.dao.XtlcglDAO;

import common.GlobalsVariable;

public class XsxxXjydglDAO extends DAO{
	ArrayList<String> value = new ArrayList<String>();
	
	/**
	 * ��ȡ��ѯ����
	 * */
	public StringBuilder getWhereSql(StudentInfoForm model){
		StringBuilder sb = new StringBuilder(" where 1=1 ");
		String xh = model.getXh();
		String xm = model.getXm();
		String ydqxydm = model.getYdqxydm();
		String xydm = model.getXydm();
		String zydm = model.getYdqzydm();
		String bjdm = model.getYdqbjdm();
		String ydhxydm = model.getYdhxydm();
		String nj = model.getYdqnj();
		String ydlbdm = model.getYdlbdm();
		String[] xtgwidArr = model.getXtgwidArr();
		String[] shztArr = model.getShztArr();
		String ydrqks = model.getQuerygreaterequal_ydrq();
		String ydrqjs = model.getQuerylessequal_ydrq();
		String ydjzrqks = model.getQuerygreaterequal_ydjzrq();
		String ydjzrqjs = model.getQuerylessequal_ydjzrq();
		
		if(!StringUtils.isNull(xh)){
			sb.append( " and xh like '%'||?||'%'");
			value.add(xh);
		}
		if(!StringUtils.isNull(xm)){
			sb.append( " and xm like '%'||?||'%'");
			value.add(xm);
		}
		if(!StringUtils.isNull(ydqxydm)){
			sb.append( " and ydqxydm=?");
			value.add(ydqxydm);
		}
		
		if(!StringUtils.isNull(xydm)){
			sb.append( " and ydqxydm=?");
			value.add(xydm);
		}
		
		if (StringUtils.isNotNull(ydhxydm)) {
			sb.append(" and ydhxydm=?");
			value.add(ydhxydm);
		}
		
		
		if(!StringUtils.isNull(zydm)){
			sb.append( " and ydqzydm=?");
			value.add(zydm);
		}
		if(!StringUtils.isNull(bjdm)){
			sb.append( " and ydqbjdm=?");
			value.add(bjdm);
		}
		if(!StringUtils.isNull(nj)){
			sb.append( " and ydqnj=?");
			value.add(nj);
		}
		if(!StringUtils.isNull(ydlbdm)){
			sb.append( " and ydlbdm=?");
			value.add(ydlbdm);
		}
		if(!StringUtils.isNull(ydrqks)){
			sb.append( " and to_number(substr(ydrq,0,4)||substr(ydrq,6,2)||substr(ydrq,9,2))>=?");
			value.add(ydrqks);
		}
		if(!StringUtils.isNull(ydrqjs)){
			sb.append( " and to_number(substr(ydrq,0,4)||substr(ydrq,6,2)||substr(ydrq,9,2))<=?");
			value.add(ydrqjs);
		}
		if(!StringUtils.isNull(ydjzrqks)){
			sb.append( " and to_number(substr(ydjzrq,0,4)||substr(ydjzrq,6,2)||substr(ydjzrq,9,2))>=?");
			value.add(ydjzrqks);
		}
		if(!StringUtils.isNull(ydjzrqjs)){
			sb.append( " and to_number(substr(ydjzrq,0,4)||substr(ydjzrq,6,2)||substr(ydjzrq,9,2))<=?");
			value.add(ydjzrqjs);
		}
		
		//�������
		if(xtgwidArr != null){
			for(int i=0; i<xtgwidArr.length; i++){
				if(shztArr != null && shztArr.length>i && StringUtils.isNotNull(shztArr[i])){
					sb.append(" and exists( select 1 from xg_xsxx_xjydxx_shb b where a.ydxh=b.ydxh and b.xtgwid='");
					sb.append(xtgwidArr[i]);
					sb.append("' and shzt=?)");				
					value.add(shztArr[i]);
				}
			}
		}
		return sb;
	}
	
	public StringBuilder getXjydlbshlcSql(StudentInfoForm model){
		StringBuilder sb = new StringBuilder(" where 1=1 ");
		String ydlbdm = model.getYdlbdm();
		String xjzt = model.getXjzt();
		String tjbcyfs = model.getTjbcyfs();
		
		if(StringUtils.isNotNull(ydlbdm)){
			sb.append(" and ydlbm=?");
			value.add(ydlbdm);
		}
		if(StringUtils.isNotNull(xjzt)){
			sb.append(" and xjzt=?");
			value.add(xjzt);
		}
		if(StringUtils.isNotNull(tjbcyfs)){
			sb.append(" and tjbcyfs=?");
			value.add(tjbcyfs);
		}
		return sb;
	}
	
	/**
	 * ѧ���춯�����Ϣ��ѯ
	 * */
	public List<String[]> selectXjydsh(StudentInfoForm model,User user,String[] shgwArr,String[] colList){
		XtlcglDAO lcglDao = new XtlcglDAO();
		StringBuilder whereSql = getWhereSql(model);	
		StringBuilder shCol = new StringBuilder();
		
		//�����Ϣ��ѯ
		for(int i=0; i<shgwArr.length; i++){
			shCol.append(",nvl((select shzt from xg_xsxx_xjydxx_shb b where b.xtgwid='");
			shCol.append(shgwArr[i]);
			shCol.append("' and a.ydxh=b.ydxh),'δ���') col");
			shCol.append(i);
			
			colList = StringUtils.joinStrArr(colList,new String[]{"col" + i});
		}
		//��ǰ��������
		String cdid = GlobalsVariable.GNDM_XSXX_XJYDSH+model.getYdlbdm();
		HashMap<String, String> lcMap = lcglDao.getGnlcxx(cdid);//��ȡ�������������Ϣ
		String lcid = lcMap.get("lcid");
		String tjbcyfs = lcMap.get("tjbcyfs");
		
		//��ǰ�û��ڵ�ǰ���������еĸ�λ��Ϣ
		String xtgwid = model.getXtgwid();
		HashMap<String, String> yhgwxxMap = lcglDao.getYhlcgwxx(xtgwid,cdid);
		
		//�Ƿ�ɲ�������˽���Ƿ�ͨ����ѯ
		String disabled = "(select decode(shzt,'ͨ��','��','�˻�','��','��ͨ��','��','��') sftj from xg_xsxx_xjydxx_shb b where a.ydxh=b.ydxh and b.xtgwid='" + xtgwid + "')";
		
		String bgcolor = "(select shzt from xg_xsxx_xjydxx_shb b where a.ydxh=b.ydxh and b.xtgwid='" + xtgwid + "')";
		
		StringBuilder mainSql = new StringBuilder();
		mainSql.append("select a.ydxh pkValue,");
		mainSql.append(disabled);
		mainSql.append(" disabled,");
		mainSql.append(bgcolor);
		mainSql.append(" bgcolor,a.ydxh,a.xh,a.xm,a.xb,a.clwh,a.ydlbdm,a.ydyy,"); 
		mainSql.append("a.ydsm,a.ydqxydm,a.xszt,a.ztmc,a.ydlbmc,"); 
		mainSql.append("a.ydqxymc,a.ydqzymc,a.ydqzydm,a.ydqbjdm,a.ydqbjmc,a.ydqnj,a.ydqxz,a.ydqxjztm,a.ydqxjztmc,"); 
		mainSql.append("a.ydhxydm,a.ydhxymc,a.ydhzydm,a.ydhzymc,a.ydhbjdm,a.ydhbjmc,a.ydhnj,a.ydhxz,a.ydhxjztm,ydrq,ydjzrq  ");
		mainSql.append(shCol.toString());
		mainSql.append(" from "); 
		mainSql.append("view_xjydjbxx a ");

		//��ǰ�û�ֻ��ѯ���¼����ͨ���ļ�¼
		if(StringUtils.isNull(whereSql.toString())){
			whereSql.append(" where 1=1 ");
		}
		whereSql.append(" and exists(select 1 from xg_xsxx_xjydxx_shb b where a.ydxh=b.ydxh");// and shzt='ͨ��' 
		//ͬ������뷽ʽδ�ɲ�������Բ�ѯȫ��
		if("ȫ������".equalsIgnoreCase(tjbcyfs) && !lcglDao.firstLevel(lcid, xtgwid)){
			whereSql.append(" and shzt='ͨ��' ");
		}else if("���ֲ���".equalsIgnoreCase(tjbcyfs)){
			//TODO ��ʱ��֪������
		}else{
			//�ɲ����룬��������
		}
		whereSql.append(" and exists(select 1 from xg_xtwh_shlcjbgwxxb c where b.xtgwid=c.xtgwid and ");
		whereSql.append("exists(select 1 from xg_xtwh_shlcjbxxb d where d.guid=c.jbid and lcid='");
		whereSql.append(lcMap.get("lcid"));
		whereSql.append("'");
		if(!"1".equalsIgnoreCase(yhgwxxMap.get("jb")) && StringUtils.isNotNull(yhgwxxMap.get("jb"))){
			whereSql.append(" and jb=");
			whereSql.append(Integer.parseInt(yhgwxxMap.get("jb")) - 1);
		}		
		whereSql.append(")))");
		
		String sql = mainSql.toString() + whereSql.toString();
		String[] inputValue = value != null ? value.toArray(new String[0]) : new String[]{};
		//��ҳ��ѯ��ѯ�ܼ�¼��
		String coutSql = "select count(*) num from(" + sql + ")";
		model.getPages().setMaxRecord(Integer.parseInt(getOneRs(coutSql, inputValue, "num")));
		
		//��ҳ��ѯ��ѯ��ҳ��¼
		sql = "select a.* from (select a.*,rownum r from (" 
			  + sql
			  + ") a )a where r>" 
			  + model.getPages().getStart() 
			  + " and r<=" 
			  + (model.getPages().getStart()
			  + model.getPages().getPageSize());
		
		return rsToVator(sql,inputValue, colList);
	}
	
	/**
	 * ѧ���춯�����Ϣ��ѯ
	 * */
	public List<String[]> selectXjydsqxx(StudentInfoForm model,User user,String[] shgwArr,String[] colList){
		StringBuilder whereSql = getWhereSql(model);	
		StringBuilder shCol = new StringBuilder();
		
		//�����Ϣ��ѯ
		for(int i=0; i<shgwArr.length; i++){
			shCol.append(",nvl((select shzt from xg_xsxx_xjydxx_shb b where b.xtgwid='");
			shCol.append(shgwArr[i]);
			shCol.append("' and a.ydxh=b.ydxh),'δ���') col");
			shCol.append(i);
			
			colList = StringUtils.joinStrArr(colList,new String[]{"col" + i});
		}
		
		StringBuilder mainSql = new StringBuilder();
		mainSql.append("select a.ydxh pkValue,");
		mainSql.append(" a.ydxh,a.xh,a.xm,a.xb,a.clwh,a.ydlbdm,a.ydyy,"); 
		mainSql.append("a.ydsm,a.ydqxydm,a.xszt,a.ztmc,a.ydlbmc,"); 
		mainSql.append("a.ydqxymc,a.ydqzymc,a.ydqzydm,a.ydqbjdm,a.ydqbjmc,a.ydqnj,a.ydqxz,a.ydqxjztm,a.ydqxjztmc,"); 
		mainSql.append("a.ydhxydm,a.ydhxymc,a.ydhzydm,a.ydhzymc,a.ydhbjdm,a.ydhbjmc,a.ydhnj,a.ydhxz,a.ydhxjztm,ydrq,ydjzrq  ");
		mainSql.append(shCol.toString());
		mainSql.append(" from "); 
		mainSql.append("view_xjydjbxx a ");
		
		String sql = mainSql.toString() + whereSql.toString();
		String[] inputValue = value != null ? value.toArray(new String[0]) : new String[]{};
		//��ҳ��ѯ��ѯ�ܼ�¼��
		String coutSql = "select count(*) num from(" + sql + ")";
		model.getPages().setMaxRecord(Integer.parseInt(getOneRs(coutSql, inputValue, "num")));
		
		//��ҳ��ѯ��ѯ��ҳ��¼
		sql = "select a.* from (select a.*,rownum r from (" 
			  + sql
			  + ") a )a where r>" 
			  + model.getPages().getStart() 
			  + " and r<=" 
			  + (model.getPages().getStart()
			  + model.getPages().getPageSize());
		
		return rsToVator(sql,inputValue, colList);
	}
	
	/**
	 * ѧ���춯������̲�ѯ
	 * */
	public List<String[]> selectXjydshlc(StudentInfoForm model){
		XtlcglDAO lcglDao = new XtlcglDAO();
		StringBuilder sql = new StringBuilder("select * from (select ydlbm,ydlbmc,xjzt,sfzx,shlcid ");
		sql.append(",(select lcmc from xg_xtwh_shlckxxb b where a.shlcid=b.guid)shlcmc,");
		sql.append("(select tjbcyfs from xg_xtwh_shlckxxb b where a.shlcid=b.guid)tjbcyfs");
		sql.append(" from dm_ydlb a )");
		//��ѯ����
		sql.append(getXjydlbshlcSql(model));
		String[] inputValue =  value != null ? value.toArray(new String[0]) : new String[]{};
			
		List<String[]> rs = rsToVator(sql.toString(), inputValue, 
								new String[]{"ydlbm","ydlbmc","xjzt","sfzx","shlcmc","tjbcyfs"});
		List<String[]> result = new ArrayList<String[]>();
		
		//����˸�λ��Ϣ���뵽��ѯ�����
		for(int i=0; i<rs.size(); i++){
			String[] arr = rs.get(i);
			String shgw = StringUtils.joinStrByArray(lcglDao.getGnshgwmcxx(GlobalsVariable.GNDM_XSXX_XJYDSH+arr[0]), ",");
			arr = StringUtils.joinStrArr(arr,new String[] {shgw});
			result.add(arr);
		}
		return result;
	}
	
	/**
	 * ��ȡѧ���춯�����Ϣ
	 * */
	public HashMap<String, String> selectXjydlbxx(String ydlbm){
		String sql = "select ydlbm,ydlbmc,(select zxdmmc from dm_zju_xjzt b where a.xjzt=b.zxdm)xjzt,sfzx,shlcid," 
					+ "(select lcmc from xg_xtwh_shlckxxb b where a.shlcid=b.guid)shlcmc," 
					+ "(select tjbcyfs from xg_xtwh_shlckxxb b where a.shlcid=b.guid)tjbcyfs from dm_ydlb a " 
					+ "where ydlbm=?";
		return getMap(sql, new String[]{ydlbm}, new String[]{"ydlbm","ydlbmc","xjzt","sfzx","shlcid","shlcmc","tjbcyfs"});
	}
	
	/**
	 * ��ѯ�û�������
	 * */
	public HashMap<String, String> selectYhshyj(List<HashMap<String, String>> yhgwList, User user,String pkValue){
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		String[] outputValue = {"shzt", "shr", "shsj", "shyj", "xtgwid", "xtgwmc"};
		for(int i=0; i<yhgwList.size(); i++){
			if(i == yhgwList.size()-1){//���һ��
				sql = "select shzt,shr,shsj,shyj,xtgwid,(select xtgwmc from xg_xtwh_xtgwxxb b where a.xtgwid=b.guid)xtgwmc " 
					  + " from xg_xsxx_xjydxx_shb a where ydxh=? and xtgwid=?";
				map = getMap(sql, new String[]{pkValue, yhgwList.get(i).get("xtgwid")}, outputValue);
			}else{//�û����λ����£���ѯδ��˼�����͵�������
				sql =  "select shzt,shr,shsj,shyj,xtgwid,(select xtgwmc from xg_xtwh_xtgwxxb b where a.xtgwid=b.guid)xtgwmc" 
					   + " from xg_xsxx_xjydxx_shb a where ydxh=? and xtgwid=? and (shzt='δ���' or shzt='������')";
				map = getMap(sql, new String[]{pkValue, yhgwList.get(i).get("xtgwid")}, outputValue);
				if(map != null && map.size()>0) break;
			}
		}
		return map;
	}
	
	/**
	 * ��ѯ�û�������
	 * */
	public HashMap<String, String> selectXjydshyj(String xtgwid,String pkValue){
		String sql = "select a.*,(select xtgwmc from xg_xtwh_xtgwxxb b where a.xtgwid=b.guid)xtgwmc from xg_xsxx_xjydxx_shb a where xtgwid=? and ydxh=?";
		HashMap<String, String> map = new HashMap<String, String>();
		String[] outputValue = {"shzt", "shr", "shsj", "shyj", "xtgwid", "xtgwmc"};
		
		map = getMap(sql, new String[]{xtgwid, pkValue}, outputValue);
		return map;
	}
	
	/**
	 * ��ѯѧ���춯�����Ϣ
	 * */
	public List<HashMap<String, String>> selectXjydshxx(String pkValue){
		String sql = "select a.*,(select xtgwmc from xg_xtwh_xtgwxxb b where a.xtgwid=b.guid)xtgwmc from xg_xsxx_xjydxx_shb a where ydxh=?";
		String[] outputValue = {"shzt", "shr", "shsj", "shyj", "xtgwid", "xtgwmc"};
		return getList(sql, new String[]{pkValue}, outputValue);
	}
	
	
	/**
	 * ����ת��
	 * */
	public List<String[]> listTransform(List<String[]> rs){
		for(int f=0; f<rs.size(); f++){
			String[] arr = rs.get(f);
			String[] tmp = new String[arr.length-1];
			String zt = "";
			for(int i=0; i<arr.length; i++){
				if(i == arr.length-1){
					zt = arr[i];
				}else{
					tmp[i] = arr[i];
				}
			}
			String[] shArr = zt.split(",");
			tmp = StringUtils.joinStrArr(tmp, shArr);
			rs.set(f, tmp);
		}
		
		return rs;
	}
	
	public boolean saveXjydshtg(StudentInfoForm model, String pkValue){
		boolean result = false;
		
		return result;
	}
	
	public boolean saveXjydshth(StudentInfoForm model, String pkValue){
		boolean result = false;
		
		return result;
	}
	
	/**
	 * �޸�ѧ���춯�����Ϣ
	 * */
	public boolean updateXjydsh(StudentInfoForm model, String pkValue){
		boolean result = false;
		String sql = "update xg_xsxx_xjydxx_shb set shzt=?,shr=?,shyj=?,shsj=? where ydxh=? and xtgwid=?";
		
		try{
			result = runUpdate(sql, new String[]{model.getShjg(), model.getUserName(), model.getShyj(),
								GetTime.getSystemTime(), pkValue, model.getXtgwid()});
		} catch(Exception e){
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	/**
	 * �жϵ�ǰ��˵ļ�¼�Ƿ����ϼ��˻صļ�¼
	 * */
	public boolean isBack(StudentInfoForm model, String pkValue){
		XtlcglDAO lcglDao = new XtlcglDAO();
		//�жϵ�ǰ��λ�����״̬�Ƿ�������
		String sql = "";//"select count(*) num from xg_xsxx_xjydxx_shb a where a.xtgwid=? and ydxh=? and shzt='������'";
		int flag = 0;//Integer.parseInt(getOneRs(sql, new String[]{model.getXtgwid(), pkValue}, "num"));
//		if(flag >0 ){
			//�ж��ϼ��Ƿ��˻�
			String shlcid = lcglDao.getGnlcxx(GlobalsVariable.GNDM_XSXX_XJYDSH+model.getYdlbdm()).get("lcid");
			//��λ�����������Ϣ
			HashMap<String, String> gwylcxgxx = lcglDao.selectGwylcxgxx(shlcid, model.getXtgwid());
			String jbStr = gwylcxgxx.get("jb");
			//����
			int jb = Integer.parseInt(StringUtils.isNull(jbStr) ? "0" : jbStr);
			
			sql = "select count(*) num from xg_xsxx_xjydxx_shb a where ydxh=? and shzt='�˻�' and exists(select 1" 
				+ " from (select a.*, b.xtgwid from xg_xtwh_shlcjbxxb a left join xg_xtwh_shlcjbgwxxb b on a.guid = b.jbid) b " 
				+ "  where a.xtgwid=b.xtgwid and b.lcid = ?  and b.jb=? )";
			flag = Integer.parseInt(getOneRs(sql, new String[]{pkValue,shlcid,(jb+1)+""}, "num"));
//		}
		
		return flag>0 ? true : false;
	}
	
	
	/**
	 * ����ѧ���춯�ϼ����״̬
	 * */
	public boolean updateXjydsjshzt(StudentInfoForm model, String pkValue){
		XtlcglDAO lcglDao = new XtlcglDAO();
		boolean result = false;
		
		String sql = "update xg_xsxx_xjydxx_shb a set shzt='������',shsj='',shr='',shyj='' where ydxh=? and exists (select 1 from " 
					+ "(select a.*, b.xtgwid from xg_xtwh_shlcjbxxb a left join xg_xtwh_shlcjbgwxxb b on a.guid = b.jbid)" 
					+ " a where a.xtgwid=b.xtgwid and a.lcid=? and a.jb=?)";
		String shlcid = lcglDao.getGnlcxx(GlobalsVariable.GNDM_XSXX_XJYDSH+model.getYdlbdm()).get("lcid");
		//��λ�����������Ϣ
		HashMap<String, String> gwylcxgxx = lcglDao.selectGwylcxgxx(shlcid, model.getXtgwid());
		String jbStr = gwylcxgxx.get("jb");
		//����
		int jb = Integer.parseInt(StringUtils.isNull(jbStr) ? "0" : jbStr);
		try{
			result = runUpdate(sql, new String[]{pkValue,shlcid,(jb+1)+""});
		}catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}
	
	/**
	 * ����ѧ���춯�¼����״̬
	 * */
	public boolean updateXjydxjshzt(StudentInfoForm model, String pkValue){
		XtlcglDAO lcglDao = new XtlcglDAO();
		boolean result = false;
		
		String sql = "update xg_xsxx_xjydxx_shb a set shzt='������',shsj='',shr='',shyj='' where ydxh=? and exists (select 1 from " 
					+ "(select a.*, b.xtgwid from xg_xtwh_shlcjbxxb a left join xg_xtwh_shlcjbgwxxb b on a.guid = b.jbid)" 
					+ " b where a.xtgwid=b.xtgwid and b.lcid=? and b.jb=?)";
		String shlcid = lcglDao.getGnlcxx(GlobalsVariable.GNDM_XSXX_XJYDSH+model.getYdlbdm()).get("shlcid");
		//��λ�����������Ϣ
		HashMap<String, String> gwylcxgxx = lcglDao.selectGwylcxgxx(shlcid, model.getXtgwid());
		String jbStr = gwylcxgxx.get("jb");
		//����
		int jb = Integer.parseInt(StringUtils.isNull(jbStr) ? "0" : jbStr);
		try{
			result = runUpdate(sql, new String[]{pkValue,shlcid,(jb-1)+""});
		}catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}
	
	
	/**
	 * ѧ���춯����ѧ����Ϣ
	 * */
	public boolean updateXjxx(String ydxh){
		boolean result = false;
		String edition = Base.getInitProperties().get("edition");
		
		String sql = "select * from bks_xjydxx a where ydxh=?";
		String[] outputValue = {"ydxh","xh","clwh","ydlbm","ydyy","ydsj","ydsm","ydqxydm","ydqxymc","ydqzydm","ydqzymc",
								"ydqbdm","ydqbjmc","ydqnj","ydhxydm","ydhxymc","ydhzydm","ydhzymc","ydhbdm","ydhbjmc",
								"ydhnj","xn","xq","ydrq","ywxtbh","zhgxsj","ydqxz","ydqxjztm","ydhxz","ydhxjztm","xszt",
								"ydjzrq","ydqsfzx","ydhsfzx"};
		HashMap<String, String> xjydMap = getMap(sql, new String[]{ydxh}, outputValue);
		try{
			if("old".equalsIgnoreCase(edition)){
				//�ϰ汾�����Ѿ����ڵ���Ϣ���µ�xsxxb���ڽ����޸Ĳ���
				StudentInfoDao xsxxDao = new StudentInfoDao();
				result = xsxxDao.addStuInfoFromView(xjydMap.get("xh"));			
			}
			//����xsxxb
			sql = "update xsxxb set xydm=?,zydm=?,bjdm=?,nj=?,xjztm=?,sfzx=? where xh=?";
			result = runUpdate(sql,new String[]{xjydMap.get("ydhxydm"),xjydMap.get("ydhzydm"),xjydMap.get("ydhbdm"),
					xjydMap.get("ydhnj"),xjydMap.get("ydhxjztm"),xjydMap.get("ydhsfzx"),xjydMap.get("xh")});
		}catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	/**
	 * �ж��Ƿ��ȡ�����
	 * */
	public String checkSfkqxsh(String[] pkV,String userName, String xtgwid, String ydlbm){
		XtlcglDAO lcglDao = new XtlcglDAO();
		String sql = "";
		String message = "";
		for(int i=0; i<pkV.length; i++){
			//�ж��Ƿ��˲����ļ�¼
			sql = "select count(*) num from xg_xsxx_xjydxx_shb where ydxh=? and xtgwid=? and shr=?";
			String num = getOneRs(sql, new String[]{pkV[i], xtgwid, userName}, "num");
			if(Integer.parseInt(num)<1){
				message = "ֻ��ȡ�����û��������ļ�¼";
				break;
			}
			//�ж��ϼ��Ƿ��Ѿ���˹�
			String shlcid = lcglDao.getGnlcxx(GlobalsVariable.GNDM_XSXX_XJYDSH+ydlbm).get("lcid");
			//��λ�����������Ϣ
			HashMap<String, String> gwylcxgxx = lcglDao.selectGwylcxgxx(shlcid, xtgwid);
			String jbStr = gwylcxgxx.get("jb");
			//����
			int jb = Integer.parseInt(StringUtils.isNull(jbStr) ? "0" : jbStr);
			
			sql = "select count(*) num from xg_xsxx_xjydxx_shb a where shzt<>'δ���' and shzt <> '�˻�' and ydxh=? and exists(select 1 from " 
				  + "(select a.*, b.xtgwid from xg_xtwh_shlcjbxxb a left join xg_xtwh_shlcjbgwxxb b on a.guid = b.jbid)" 
				  + " b where a.xtgwid=b.xtgwid and b.lcid=? and b.jb=?)";
			num = getOneRs(sql, new String[]{pkV[i],shlcid,(jb+1)+""}, "num");
			if(Integer.parseInt(num)>0){
				message = "������Ϣ�ϼ���λ�Ѿ���˹�,��ʱ����ȡ�����";
				break;
			}
			
		}
		return message;
	}
	
	/**
	 * ����ѧ���춯��˳�ʼ��¼
	 * */
	public boolean saveXjydshjl(StudentInfoForm model,List<HashMap<String, String>> list, User user){
		boolean flag = false;
		String[] sqlArr = new String[list.size()+1];
		sqlArr[0] = "delete from xg_xsxx_xjydxx_shb where ydxh='" + model.getYdxh() + "'";
		int num = 1;		
		for(HashMap<String, String> map : list){
			sqlArr[num++] = "insert into xg_xsxx_xjydxx_shb(ydxh,xtgwid,shzt) values('" + model.getYdxh()
							+ "','" + map.get("xtgwid") + "','δ���')";
		}
		try{
			int[] result = runBatch(sqlArr);
			flag = checkBatch(result);
		}catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * ��ѯѧ���춯����˼�¼��
	 * */
	public int getXjydyshjls(String ydxh){
		String sql = "select count(*) num from xg_xsxx_xjydxx_shb where ydxh=? and (shzt='ͨ��' or shzt = '��ͨ��')";
		return Integer.parseInt(getOneRs(sql, new String[]{ydxh}, "num"));
	}
	
	/**
	 * ����˵���Ӧ���������
	 * */
	public boolean saveShlcdyxxb(StudentInfoForm model, User user){
		boolean flag = false;
		String[] sqlArr = new String[2];
		sqlArr[0] = "delete from xg_xtwh_shlcdyxxb where cdid='" + GlobalsVariable.GNDM_XSXX_XJYDSH + model.getYdlbdm() + "'";
		sqlArr[1] = "insert into xg_xtwh_shlcdyxxb(lcid,cdid) values('" + model.getSave_shlcid()+ "','" + GlobalsVariable.GNDM_XSXX_XJYDSH + model.getYdlbdm() + "')";
		
		
		try{
			int[] result = runBatch(sqlArr,"xg_xtwh_shlcdyxxb",user);
			flag = checkBatch(result);
		}catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	
	/*����
	public static void main(String[] args){
		XsxxXjydglDAO dao = new XsxxXjydglDAO();
		
		List<String[]> rs = new ArrayList<String[]>();
		String[] arr = {"a","b","c,d"};
		String[] arrStr = {"a","c"};
		rs.add(arr);
		
		rs = dao.listTransform(rs);
		for(String[] arrs : rs){
			for(String str : arrs){
				System.out.println(str);
			}
		}
	}
	*/
}
