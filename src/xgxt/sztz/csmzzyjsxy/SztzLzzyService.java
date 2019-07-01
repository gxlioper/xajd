package xgxt.sztz.csmzzyjsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.pjpy.ghxy.bjryf.GhxyBjryfModel;
import xgxt.pjpy.ghxy.bjryf.GhxyBjryfService;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;
import xgxt.sztz.form.SztzForm;

public class SztzLzzyService {
	/**
	 * ����ְҵ�ڶ�����
	 * author ������
	 */
	
	
	public List<HashMap<String,String>>getHdxm(String xn,String xq,String kmdm,String xmjb){
		DAO dao=DAO.getInstance();
		List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		HashMap<String,String>hashMap =new HashMap<String,String>();
		hashMap.put("dm", "");
		hashMap.put("mc", "----��ѡ��----");
		list.add(hashMap);
		StringBuilder sql=new StringBuilder();
		sql.append("select id dm,xmmc mc from view_csmz_tzxmxx where ");
		sql.append(Base.isNull(xn) || "".equals(xn) ? " 1=1 " : " xn='"+xn+"' ");
		sql.append(Base.isNull(xq) || "".equals(xq) ? "" : "and  xq='"+xq+"' ");
		sql.append(Base.isNull(kmdm) || "".equals(kmdm) ? "" : "and  kmdm='"+kmdm+"' ");
		sql.append(Base.isNull(xmjb) || "".equals(xmjb) ? "" : "and  xmjb='"+xmjb+"' ");
		list.addAll(dao.getList(sql.toString(), new String[]{}, new String[]{"dm","mc"}));
		return list;
	}
	
	/**
	 * ��ȡ��չ��Ŀ��Ϣ
	 * @param xmdm
	 * @return
	 */
	public HashMap<String,String> getTzxmXx(String xmdm){
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		List<HashMap<String,String>>list=new ArrayList<HashMap<String,String>>();
		HashMap<String,String>hashMap=new HashMap<String,String>();
		sb.append("select id,xn,(select xqmc from xqdzb b where b.xqdm=xq and rownum=1 )xq,xmmc,kmmc,xymc bmmc,zzdw,zbsj,xmjb,xmms, ");
		sb.append(" (case when xmjb='�༶' then (select xm from view_xsjbxx b where b.xh=xmsbr)  else (select xm from yhb b where b.yhm=xmsbr) end)xmsbr ");
		sb.append(" from view_csmz_tzxmxx where id=?");
		String[] output = new String[] { "id", "xn", "xq", "xmmc", "kmmc",
		"bmmc", "zzdw", "zbsj", "xmjb", "xmms","xmsbr" };
		list=dao.getList(sb.toString(), new String[]{xmdm}, output);
		if(list.size()>0){
			hashMap=list.get(0);
		}
		return hashMap;
		
	}
	
	/**
	 * ����ѧԺ��ȡѧ����Ϣ
	 */
	public List<HashMap<String,String>>getStuXxByXy(String xydm,String userType,
			String userDep,String userName,boolean fdyQx,boolean bzrQx){
		SztzLzzyService service = new SztzLzzyService();
		StringBuilder sb=new StringBuilder();
		sb=service.getQxTj(bzrQx, fdyQx, userType, userDep, userName);
		DAO dao=DAO.getInstance();
		String sql="select ('��'||xh||','||xm)xh,('��'||xh||','||xm)xm from view_xsjbxx b where xydm=?";
		sql+=sb.toString();
		return dao.getList(sql, new String[]{xydm}, new String[]{"xh","xm"});
	}
	
	/**
	 * ����רҵ��ȡѧ����Ϣ
	 */
	public List<HashMap<String,String>>getStuXxByZy(String zydm,String userType,
			String userDep,String userName,boolean fdyQx,boolean bzrQx){
		SztzLzzyService service = new SztzLzzyService();
		StringBuilder sb=new StringBuilder();
		sb=service.getQxTj(bzrQx, fdyQx, userType, userDep, userName);
		DAO dao=DAO.getInstance();
		String sql="select ('��'||xh||','||xm)xh,('��'||xh||','||xm)xm from view_xsjbxx b where zydm=?";
		sql+=sb.toString();
		return dao.getList(sql, new String[]{zydm}, new String[]{"xh","xm"});
	}
	
	/**
	 * ���ݰ༶��ȡѧ����Ϣ
	 */
	public List<HashMap<String,String>>getStuXxByBj(String bjdm,String userType,
			String userDep,String userName,boolean fdyQx,boolean bzrQx){
		SztzLzzyService service = new SztzLzzyService();
		StringBuilder sb=new StringBuilder();
		sb=service.getQxTj(bzrQx, fdyQx, userType, userDep, userName);
		DAO dao=DAO.getInstance();
		String sql="select ('��'||xh||','||xm)xh,('��'||xh||','||xm)xm from view_xsjbxx b where bjdm=?";
		sql+=sb.toString();
		return dao.getList(sql, new String[]{bjdm}, new String[]{"xh","xm"});
	}
	
	/**
	 * ����ѧ�Ż�ȡѧ����Ϣ
	 */
	public List<HashMap<String,String>>getStuXxByXh(String xh,String userType,
			String userDep,String userName,boolean fdyQx,boolean bzrQx){
		SztzLzzyService service = new SztzLzzyService();
		StringBuilder sb=new StringBuilder();
		sb=service.getQxTj(bzrQx, fdyQx, userType, userDep, userName);
		DAO dao=DAO.getInstance();
		String sql="select ('��'||xh||','||xm)xh,('��'||xh||','||xm)xm from view_xsjbxx b where xh like '%"+xh+"%'";
		sql+=sb.toString();
		return dao.getList(sql, new String[]{}, new String[]{"xh","xm"});
	}
	
	/**
	 * ����������ȡѧ����Ϣ
	 */
	public List<HashMap<String,String>>getStuXxByXm(String xm,String userType,
			String userDep,String userName,boolean fdyQx,boolean bzrQx){
		SztzLzzyService service = new SztzLzzyService();
		StringBuilder sb=new StringBuilder();
		sb=service.getQxTj(bzrQx, fdyQx, userType, userDep, userName);
		DAO dao=DAO.getInstance();
		String sql="select  ('��'||xh||','||xm)xh,('��'||xh||','||xm)xm from view_xsjbxx b where xm like '%"+xm+"%'";
		sql+=sb.toString();
		return dao.getList(sql, new String[]{}, new String[]{"xh","xm"});
	}
	
	/**
	 * 
	 * 
	 */
	public List<HashMap<String,String>>getJxjbList(HttpServletRequest request,String xmdm){
		String sql="";
		DAO dao=DAO.getInstance();
		SztzLzzyService service =new SztzLzzyService();
		if(!"".equalsIgnoreCase(xmdm)){
			sql = " select distinct jxm,jxid from csmz_tzxmjxb where xmid=? ";
			request.setAttribute("rs", service.getTzxmXx(xmdm));
			
		}else{
			sql = " select distinct jxm,jxid from csmz_tzxmjxb where 1=2 and xmid=? ";
		}
		return dao.getList(sql, new String[] {xmdm}, new String[] {
				"jxid", "jxm" });
	}
	
	/**
	 * ��ȡ������ò
	 * 
	 */
	public List<HashMap<String,String>>getZzmmList(){
		DAO dao=DAO.getInstance();
		String sql="select zzmmdm dm,zzmm mc from dmk_zzmm";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * ��������ѧ������걨
	 * @throws Exception 
	 */
	public void plSave(SztzForm form,HttpServletRequest request) throws Exception{
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();

		//�������ݲ��� �ı���
		String realTable ="csmz_tzcgb";
		//��ȡѧ����Ϣ����
		String []stuInfo=form.getStuInfo();
		String []stuOk=new String[stuInfo.length];
		
		int len=0;
		for(int i=0;i<stuInfo.length;i++){
			if("��".equals(stuInfo[i].substring(0,1))){
				stuOk[len]=stuInfo[i].substring(1, stuInfo[i].indexOf(","));
				System.out.println(stuOk[len]);
				len++;
			}
		}
		String []checkVal=new String[len];
		for(int i=0;i<len;i++){
			checkVal[i]=stuOk[i];
		}
		
		String []xmid=new String[len];//��ĿID
		String []cyjs=new String[len];//�����ɫ
		String []cgms=new String[len];//�ɹ�����
		String []jxlb=new String[len];//�����
		//�������Ӽ�ȡ��
		
		if (checkVal != null && checkVal.length > 0) {
			String pk = "xh || xmid || cyjs  || jxlb  ";
			String[] arrzd = new String[] {"xh","xmid","cyjs","cgms","jxlb"};
			
			for (int i = 0; i < checkVal.length; i++) {
				xmid[i]=form.getXmmc();
				cyjs[i]=form.getCyjs();
				cgms[i]=form.getCgms();
				jxlb[i]=form.getJxlb();
			}
			
				
			String[] pkVal=new String[checkVal.length];
			for(int i=0; i<checkVal.length;i++){
				pkVal[i]=checkVal[i]+xmid[i]+cyjs[i]+jxlb[i];
			}
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkVal);
			saveForm.setArrzd(arrzd);
				
			SztzLzzyModel model=new SztzLzzyModel();
			model.setXh(checkVal);
			model.setXmid(xmid);
			model.setCgms(cgms);
			model.setCyjs(cyjs);
			model.setJxlb(jxlb);
			
			boolean result;
			result = ghxyNjzrwhService.saveTyxx(saveForm, model);
			request.setAttribute("result", result);
	
		}
	}
	
	/**
	 * ��ȡ��½�û�Ȩ��
	 *
	 */
	public String getYhlx(boolean bzrQx,boolean fdyQx,
			String userType,String userDep){
		
		String lx="";
		
		
		if (bzrQx && fdyQx) {// �����μ渨��Ա
			lx = "jd";
		} else if (fdyQx) {// ����Ա
			lx = "fdy";
		} else if (bzrQx) {// ������
			lx = "bzr";
		} else if ("xy".equalsIgnoreCase(userType)) {// ѧԺ
			lx = "xy";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// ѧУ�û�������Ա��
			lx = "xx";
		}
		return lx;
	}
	
	/**
	 * ����Ȩ����������
	 */
	public StringBuilder getQxTj(boolean bzrQx,boolean fdyQx,
			String userType,String userDep,String userName){
		SztzLzzyService service=new SztzLzzyService();
		StringBuilder yhqx=new StringBuilder();
		String lx=service.getYhlx(bzrQx, fdyQx, userType, userDep);
		if ("fdy".equalsIgnoreCase(lx)) {
			yhqx.append(" and exists(select 1 from fdybjb d ");
			yhqx.append(" where b.bjdm = d.bjdm ");
			yhqx.append(" and d.zgh = '" + userName + "')");
		} else if ("bzr".equalsIgnoreCase(lx)) {
			yhqx.append(" and exists(select 1 from bzrbbb d ");
			yhqx.append(" where b.bjdm = d.bjdm");
			yhqx.append(" and d.zgh = '" + userName + "')");
		} else if("jd".equalsIgnoreCase(lx)){
			yhqx.append(" and (");
			yhqx.append(" exists(select 1 from fdybjb d");
			yhqx.append(" where b.bjdm = d.bjdm");
			yhqx.append(" and d.zgh = '" + userName + "')");
			
			yhqx.append(" or exists(select 1 from bzrbbb d ");
			yhqx.append(" where b.bjdm = d.bjdm");
			yhqx.append(" and d.zgh = '" + userName + "')");
			
			yhqx.append(" )");
		}
		return yhqx;
	}
}
