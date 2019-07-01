package xsgzgl.xtwh.general.cxjgpz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_��ѯ�������
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author xucy
 * @version 1.0
 */
public class CxjgpzDao extends DAO{
	DAO dao = DAO.getInstance();	
	
	/**
	 * δ�����ֶ�list
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWpzzdlist(CxjgpzForm model) throws Exception {
		String sql = " select * from xg_xsxx_cxjgpzb where sfjgxs = '��' and gnlj=? order by to_number(ybzdsx)";
		String[] inputValue = new String[] {model.getGnlj()};
		String[] outputValue = new String[] { "zd", "zdmc" };
		return getList(sql, inputValue, outputValue);
	}
	
	/**
	 * �������ֶ�list
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYpzzdlist(CxjgpzForm model) throws Exception {
		String sql = "select zd,case when xgzdmc is not null then xgzdmc else zdmc end zdmc ," +
					 "sfjgxs,xssx from xg_xsxx_cxjgpzb where sfjgxs = '��' and gnlj=? order by to_number(xssx)";
		String[] inputValue = new String[] {model.getGnlj()};
		String[] outputValue = new String[] { "zd", "zdmc"};
		return getList(sql, inputValue, outputValue);
	}
	
	//�޸��Ƿ���ʾΪ��
	public boolean xgCxjgSfxs() throws Exception{
		String sql = "update xg_xsxx_cxjgpzb set sfjgxs='��',xssx=''";
		return dao.runUpdate(sql, new String[]{});
	}
	
	//����
	public boolean bcCxjg(CxjgpzForm model) throws Exception{
		boolean flag = true;
		String[] csjgs = model.getCxjg();
		if(null!=csjgs&&csjgs.length>0){
			String[] str=new String[csjgs.length];
			for(int i = 0;i<model.getCxjg().length;i++){
				str[i] ="update xg_xsxx_cxjgpzb set sfjgxs='��',xssx = '"+i+"' where zd = '"+csjgs[i]+"'";
			}
			flag=dao.saveArrDate(str);
		}
		return flag;
	}
	
	//�������ֶ�
	public boolean xgZdmc(CxjgpzForm model) throws Exception{
		String sql = "update xg_xsxx_cxjgpzb set xgzdmc=? where zd=? and gnlj=?";
		return dao.runUpdate(sql, new String[]{model.getXgzdmc(),model.getZd(),model.getGnlj()});
	}
	
	//��ȡһ������
	public HashMap<String, String> getZd(CxjgpzForm model) {
		String sql = "select * from xg_xsxx_cxjgpzb where zd=? and gnlj=?";
		String[] inputValue = new String[] {model.getZd(),model.getGnlj()};
		return getMapNotOut(sql, inputValue);
	}
	
	//���������չʾ��ֻչʾǰ10����
	public ArrayList<String[]> getXsxxlist(CxjgpzForm model) throws Exception {
		List<HashMap<String, String>> ypzd = getYpzzdlist(model);
		String zd="";
		String sczd="";
		ArrayList<String[]> xsxxlist = null;
		if(null!=ypzd&&ypzd.size()>0){
			String[] colList = new String[ypzd.size()];
			for(int i=0;i<ypzd.size();i++){
				if(i!=ypzd.size()-1){
					zd+=ypzd.get(i).get("zd")+",";
				}else{
					zd+=ypzd.get(i).get("zd");
					sczd+=""+ypzd.get(i).get("zd")+"";
				}
				colList[i] = ypzd.get(i).get("zd");
			}
			String sql = "select "+zd+" from (select e.*,a.*,";
			if("10698".equals(Base.xxdm)){//������ͨ��ѧ
				sql += "k.symc symc,";
			}
			sql += " (select pyccmc from xg_xsxx_pyccdmb " +
					"c where a.pycc=c.pyccdm) pyccmc,(select rxfsmc from xg_xsxx_rxfsdmb c " +
					"where a.rxfs=c.rxfsdm) rxfsmc,(select kslbmc from xg_xsxx_kslbdmb c " +
					"where a.kslb=c.kslbdm) kslbmc from view_xsxxb a  ";
			if("10698".equals(Base.xxdm)){//������ͨ��ѧ
				sql +=" left join xg_xtwh_sybjglb j on a.bjdm=j.bjdm   ";
				sql +=" left join xg_xtwh_sydmb k on j.sydm=k.sydm   ";
				sql +=" left join view_njxyzybj_all l on a.zybj = l.bjdm ";
			}
					// ====================�����Ρ�����Ա begin======================
					String s2 = "left join " +
					"(select nvl(t1.bjdm,t2.bjdm) as bjdm2 , t1.fdyxms , t2.bzrxms from (select a.bjdm ,  WM_CONCAT(b.xm) fdyxms from fdybjb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) t1 " +
					" left join " +
					"(select a.bjdm ,  WM_CONCAT(b.xm) bzrxms from bzrbbb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) t2 on t1.bjdm = t2.bjdm " +
					") e on a.bjdm = e.bjdm2 " + 
					// ====================�����Ρ�����Ա end======================
					"where sfzx='��У' or sfzx is null " + 
					")  where  rownum <=10";
			sql = sql+s2;
			String[] inputValue = new String[] {};
			xsxxlist = CommonQueryDAO.commonQueryNotFy(sql,"",inputValue,colList,model);		
		}
		return xsxxlist;
	}
	
}
