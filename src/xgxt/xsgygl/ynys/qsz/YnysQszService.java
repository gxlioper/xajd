package xgxt.xsgygl.ynys.qsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.SearchUtils;

import com.zfsoft.basic.BasicService;

public class YnysQszService {
	/**
	 * ��ȡ���ҳ�Ա��Ϣ
	 * @throws Exception 
	 *
	 */
	public void getQscyXx(HttpServletRequest request,String pkValue) throws Exception{
		YnysQszModel model=new YnysQszModel();
		BasicService basicService=new BasicService();
		StringBuilder sql=new StringBuilder();
		sql.append("select pk,a.xh,a.xm,a.lddm,a.ldmc,a.xqdm,a.xqmc,a.cs,a.qsh,(case when b.xh is null then '' else 'checked' end)checked from (");
		sql.append("select (a.qsh|| a.cs|| a.lddm|| a.xqdm)pk,a.xh,b.xm,a.lddm,a.ldmc,a.xqdm,a.xqmc,a.cs,a.qsh from");
		sql.append(" (select * from view_xszsxx a where (a.qsh|| a.cs|| a.lddm|| a.xqdm)= ? )a,view_xsjbxx b where a.xh=b.xh");
		sql.append(")a left join ynys_qszb b on a.xh=b.xh");
		String []colList={"xh","checked","xh","xm","xqmc","ldmc","cs","qsh"};
		List<String[]>list=CommonQueryDAO.commonQueryNotFy("", "", new String[]{pkValue}, colList, sql.toString(), model);
		request.setAttribute("topTr", basicService.getTopTr("",new String[]{"ѧ��","����","У������","¥������","¥��","���Һ�"}));
		request.setAttribute("rs",list);
		request.setAttribute("rsNum",list.size());
	}
	
	/**
	 * �����������ҳ�
	 * @throws Exception 
	 * 
	 */
	public void saveQsz(HttpServletRequest request,YnysQszForm form) throws Exception{
			//�������ݲ��� �ı���
			GhxyNjzrwhService service = new GhxyNjzrwhService();
			YnysQszModel model=new YnysQszModel();
			String realTable ="ynys_qszb";
			DAO dao=DAO.getInstance();
			
			//�����ѯ����������Ϣ��XH����
			String[] xh = form.getPrimarykey_cbv();
			//������ѡ�����Ϣ������
			String[] checkVal = form.getCheckVal();
			
			String sql="select xqdm,lddm,cs,qsh from view_xszsxx where xh=?";
			List<HashMap<String,String>>list=dao.getList(sql, new String[]{checkVal[0]}, new String[]{"xqdm","lddm","cs","qsh"});
			
			HashMap<String,String>hashMap=list.get(0);
			
			String xqdm=hashMap.get("xqdm");
			String lddm=hashMap.get("lddm");
			String cs=hashMap.get("cs");
			String qsh=hashMap.get("qsh");
			String []pkValue=new String[checkVal.length];
			for(int i=0;i<checkVal.length;i++){
				pkValue[i]=checkVal[i]+xqdm+lddm+cs+qsh;
			}
			
			//�жϲ����Ƿ�ɹ�
			boolean reslut = false;
			
			//�������Ӽ�ȡ��
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xh|| xqdm || lddm || cs || qsh";
				String[] arrzd = new String[] {"xh","xqdm","lddm","cs","qsh"};
				String[] sxh = new String[checkVal.length];
				int m = 0;
				for (int i = 0; i < checkVal.length; i++) {
					if (xh != null && xh.length > 0) {
						for (int j = 0; j < xh.length; j++) {
							if (xh[j].equalsIgnoreCase(checkVal[i])) {
								sxh[i] = checkVal[i];
								m++;
								break;
							}else{
								sxh[i]="";
							}
						}
					}
				}
				
				String[] xhArr = new String[m];
				String[] xqdms=new String[m];
				String[] lddms=new String[m];
				String[] css=new String[m];
				String[] qshs=new String[m];
				int f = 0;
				for(int i=0; i<sxh.length;i++){
					if(!Base.isNull(sxh[i])){
						xhArr[f] = sxh[i];
						xqdms[f]=xqdm;
						lddms[f]=lddm;
						css[f]=cs;
						qshs[f]=qsh;
						f++;
					}
				}
				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(pkValue);
				saveForm.setArrzd(arrzd);
				
			
				model.setXh(xhArr);
				model.setLddm(lddms);
				model.setQsh(qshs);
				model.setXqdm(xqdms);
				model.setCs(css);
				reslut = service.saveTyxx(saveForm, model);
				request.setAttribute("result", reslut);
			}
	}
	
	/**
	 * ��ʼ��
	 * @throws Exception 
	 */
	public void deleteQsz(HttpServletRequest request,YnysQszForm form) throws Exception{
		DAO dao=DAO.getInstance();
		SaveForm saveForm = new SaveForm();
		YnysQszModel model=new YnysQszModel();
		String realTable="ynys_qszb";
		String pk="qsh|| cs|| lddm|| xqdm";
		String []pkValue=form.getPrimarykey_cbv();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		boolean result=dao.delDate(saveForm, model);
		request.setAttribute("result", result);
	}
	
	/**
	 * ���ҳ�
	 * 
	 */
	public String getQsz(String xh){
		xh=Base.isNull(xh)?"":xh;
		String qsz="";
		String sql="select lddm,xqdm,qsh,cs from view_xszsxx where xh=?";
		DAO dao=DAO.getInstance();
		List<HashMap<String,String>>list=dao.getList(sql, new String[]{xh}, new String[]{"cs","lddm","xqdm","qsh"});
		
		if(list.size()>0){
			HashMap<String,String>map=list.get(0);
			StringBuilder pk=new StringBuilder();
			pk.append(map.get("xqdm"));
			pk.append(map.get("lddm"));
			pk.append(map.get("cs"));
			pk.append(map.get("qsh"));
			String qszsql="select * from view_ynys_qszb  where qs=?";
			List<HashMap<String,String>>qszlist=dao.getList(qszsql, new String[]{pk.toString()}, new String[]{"qsz"});
			if(qszlist.size()>0){
				HashMap<String,String>qszMap=qszlist.get(0);
				qsz=qszMap.get("qsz");
			}else{
				qsz="����";
			}
		}else{
			qsz="����";
		}
		return qsz;
	}
	
	
	/**
	 * 
	 * 
	 */
	public HashMap<String,String> getSsxx(String xh){
		String sql="select * from view_xszsxx where xh=?";
		DAO dao=DAO.getInstance();
		return dao.getMap(sql, new String[]{xh}, new String[]{"lddm","xqdm","cs","qsh"});
		
	}
	/**
	 * 
	 * �㽭��ý �������
	 */
	public void getWsjcList(HttpServletRequest request,String xh){ 
		YnysQszService service=new YnysQszService();
		List<String[]>list=new ArrayList<String[]>();
		HashMap<String,String>hashMap=service.getSsxx(xh);
		String lddm=hashMap.get("lddm");
		String xqdm= hashMap.get("xqdm");
		String cs = hashMap.get("cs");
		String qsh = hashMap.get("qsh");
		String sql="(select xn,xqmcc,jcsj,fs from view_zjcm_wsjc where lddm=? and qsh=? and cs=? and xqdm=?)";
		list= CommonQueryDAO.commonQueryNotFy(sql, "", new String[]{lddm,qsh,cs,xqdm}, new String[]{"xn","xqmcc","jcsj","fs"}, null);
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr("view_xszsxx", new String[]{"ѧ��","ѧ��","���ʱ��","����"}, null);
		request.setAttribute("wsjcTr", topTr);
		request.setAttribute("wsjc", list);
		request.setAttribute("rsNum", list.size());
	}
	
	/**
	 * �㽭��ý ס�޼���
	 */
	public void getZsjlList(HttpServletRequest request,String xh){ 
		List<String[]>list=new ArrayList<String[]>();
		String sql="(select a.xn,a.wjsj,a.wjsy,(select b.xqmc from xqdzb b where a.xq=b.xqdm)xqmcc from zsjlb a where xh=?)";
		list= CommonQueryDAO.commonQueryNotFy(sql, "", new String[]{xh}, new String[]{"xn","xqmcc","wjsj","wjsy"}, null);
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr("xqmcc", new String[]{"ѧ��","ѧ��","Υ��ʱ��","Υ������"}, null);
		request.setAttribute("zsjlTr", topTr);
		request.setAttribute("zsjl", list);
		request.setAttribute("rsNum", list.size());
	}
	 
}
