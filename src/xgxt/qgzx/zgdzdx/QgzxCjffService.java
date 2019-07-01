package xgxt.qgzx.zgdzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �й����ʴ�ѧ�ڹ���ѧ��𷢷�Service
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-02-10</p>
 */
public class QgzxCjffService {
	QgzxCjffDAO  dao = new QgzxCjffDAO();
	
	/**
	 * ��ѯ��λ�µ��ڹ���ѧѧ���б�
	 * @param model
	 * @return List
	 * */
	public List<String[]> getQgzxStuList(QgzxCjffForm model){
		return dao.getGdgwStuList(model);
	}
	
	/**
	 * �����¹��ʷ�����Ϣ
	 * @param model
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveYgzffInfo(QgzxCjffForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String count = model.getCount();
		HashMap<String, String> gwMap = dao.getGwinfo(model.getPk(),model.getPkValue());
		count = count == null || "".equalsIgnoreCase(count) ? "0" : count;
		String nd = model.getNd();
		String yf = model.getYf();		
		String xn = model.getXn();
		String xq = model.getXq();
		String userName = model.getUserName();
		String ffsj = gwMap.get("nowtime");
		String[] columns = {"xh", "gwdm", "cjje", "bz", "sqsj", "gzsj", "xn", "nd", "xq", "yf"};
		
		for(int i=0; i<Integer.parseInt(count); i++){
			String xh = request.getParameter("xh" + i);
			String gzsj = DealString.toGBK(request.getParameter("gzsj" + i));
			String cjje = DealString.toGBK(request.getParameter("cjje" + i));
			String gwdm = gwMap.get("gwdm");
			String gwsbsj = gwMap.get("gwsbsj");
			String yrdwdm = gwMap.get("yrdwdm");
			
			String bz = DealString.toGBK(request.getParameter("bz" + i));
			if((cjje!=null && !"".equalsIgnoreCase(cjje)) || (gzsj !=null && !"".equalsIgnoreCase(gzsj))){
				if(dao.checkExists("xscjffb", "gwdm||sqsj||xh||nd||yf||fflx", model.getPkValue()+xh+nd+yf+"")){//��¼�Ѿ�����
					flag = StandardOperation.update("xscjffb", new String[]{"gzsj", "cjje", "bz", "ffsj"}, new String[]{gzsj,cjje,bz,ffsj},"gwdm||sqsj||xh||nd||yf||fflx", model.getPkValue()+xh+nd+yf+"", request);
				}else{//����
					flag = StandardOperation.insert("xscjffb", columns, new String[]{xh,gwdm,cjje,bz,gwsbsj,gzsj,xn,nd,xq,yf}, request);
					if(flag){
						if(dao.checkExists("cjffsjb", "yrdwdm", yrdwdm)){//�������˵�λ���ų���ʱ��
							StandardOperation.update("cjffsjb", new String[]{"ffsj", "ffr"}, new String[]{ffsj, userName}, "yrdwdm", yrdwdm, request);
						}else{
							StandardOperation.insert("cjffsjb", new String[]{"yrdwdm","ffsj", "ffr"}, new String[]{yrdwdm, ffsj, userName}, request);
						}
					}
				}
			}
		}
		return flag;
	}
	
	/**
	 * ����ҳ��������ѯ��λ��Ϣ
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwInfo(String pk, String pkValue){
		return dao.getGwinfo(pk, pkValue);
	}
	
	/**
	 * ��ȡ����ܽ��
	 * @param model
	 * @return String
	 * */
	public String getCjzje(QgzxCjffForm model){
		String zje = dao.getZcjje(model);
		zje = zje == null ? "0" : zje;
		return zje;
	}
	
	/**
	 * ��ȡ���Ƶ���߳����
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getQgzxConfig(){
		return dao.getQgzxConfig();		
	}
	
	/**
	 * ���ʲ�����Ϣ����
	 * @param model
	 * @param request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveGzbfInfo(QgzxCjffForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String count = model.getCount();
		HashMap<String, String> gwMap = dao.getGwinfo(model.getPk(),model.getPkValue());
		count = count == null || "".equalsIgnoreCase(count) ? "0" : count;
		
		String tableName = "xscjffb";
		String fflx = "����";
		String yrdwdm = gwMap.get("yrdwdm");
		String ffsj = gwMap.get("nowtime");
		String ffr = model.getUserName();
		String xn = model.getXn();
		String xq = model.getXq();
		String nd = model.getNd();
		String yf = model.getYf();
		
		yf = yf == null || "".equalsIgnoreCase(yf) ? "0" : yf;
		yf = String.valueOf(Integer.parseInt(yf));
		
		String[] columns = new String[]{"xh", "gwdm", "cjje", "bz", "sqsj", "gzsj", "xn","nd", "xq", "yf", "fflx"};
		String[] values = {};
		
		for(int i=0; i<Integer.parseInt(count); i++){
			String xh = request.getParameter("xh" + i);
			String gzsj = DealString.toGBK(request.getParameter("gzsj" + i));
			String cjje = DealString.toGBK(request.getParameter("cjje" + i));
			String gwdm = gwMap.get("gwdm");
			String gwsbsj = gwMap.get("gwsbsj");
			String bz = DealString.toGBK(request.getParameter("bz" + i));
			if((cjje!=null && !"".equalsIgnoreCase(cjje)) || (gzsj !=null && !"".equalsIgnoreCase(gzsj))){
				values = new String[]{xh, gwdm, cjje, bz, gwsbsj,  gzsj, xn, nd, xq, yf, fflx};
				if(dao.checkExists("xscjffb", "xh||gwdm||sqsj||xn||nd||xq||yf||fflx", xh+gwdm+gwsbsj+xn+nd+xq+yf+fflx)){
					//���¹����Ѿ�����
					String xxdm = StandardOperation.getXxdm();
					if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
						// ============begin ���ΰ 2009/4/20 =========
						columns = new String[] { "cjje", "bz", "gzsj", "ffsj"};
						values = new String[] { cjje, bz, gzsj, ffsj };
						flag = StandardOperation.update(tableName, columns,
								values, "xh||gwdm||sqsj||xn||nd||xq||yf||fflx",
								xh + gwdm + gwsbsj + xn + nd + xq + yf + fflx,
								request);
						request.setAttribute("msg", "�Ѹ���ǰ�β�������");
						// ============end ���ΰ 2009/4/20 =========
					} else {
						request.setAttribute("msg", "���¹����Ѿ�����,�����ٽ��в���������");
					}
				}else{
					flag = StandardOperation.insert(tableName, columns, values, request);
					if(flag){
						if(flag){
							if(dao.checkExists("cjffsjb", "yrdwdm", yrdwdm)){//�������˵�λ���ų���ʱ��
								StandardOperation.update("cjffsjb", new String[]{"ffsj", "ffr"}, new String[]{ffsj, ffr}, "yrdwdm", yrdwdm, request);
							}else{
								StandardOperation.insert("cjffsjb", new String[]{"yrdwdm","ffsj", "ffr"}, new String[]{yrdwdm, ffsj, ffr}, request);
							}
						}
					}
				}
			}
		}
		return flag;
	}
	
	/**
	 * ��ѯ����������Ϣ
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getConfigParamter(){
		return dao.selectGwsbsjb();
	}
	
	/**
	 * ��ѯ���ų���ѧ���б�
	 * @param pkValue
	 * @return List
	 * */
	public List<HashMap<String, String>> getCjffInfoOfStu(String pkValue){
		return dao.selectCjffInfoOfStu(pkValue);
	}
	
	/**
	 * ɾ����𷢷���Ϣ
	 * @param pk
	 * @param pkValue
	 * @param request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean deleteCjffInfo(QgzxCjffForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String fflx = model.getFflx();
		fflx = fflx == null ? "" : fflx.trim();
		String pk = model.getPk();
		String[] pkV = model.getPkV();
		String pkValue = "";
		
		if(fflx != null && "��ʱ".equalsIgnoreCase(fflx)){
			for(int i=0; i<pkV.length; i++){
				pkValue = pkV[i];
				flag = StandardOperation.delete("xslsgcjffb", "xh||gwdm||gwsbsj||nd||yf", pkValue, request);
			}
		}else{
			for(int i=0; i<pkV.length; i++){
				pkValue = pkV[i];
				flag = StandardOperation.delete("xscjffb", pk, pkValue, request);
			}
		}
		return flag;
	}

	/**
	 * ��ȡ���˵�λ�б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getYrdwList(){
		return dao.getYrdwList();
	}
		
	/**
	 * ��ȡ�·��б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getYfList(){
		return dao.getYfList();		
	}
	
	/**
	 * ��ȡ�·��б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getGwList(){
		return dao.getGwList();		
	}
	
	/**
	 * ��ʱ�ڳ�𷢷���Ϣ
	 * @param pk
	 * @param pkValue
	 * @return List
	 * */
	public List<String[]> getLsgcjffInfo(QgzxCjffForm model,String page){
		return dao.getLsgcjffInfo(model,page);
	}
	
	/**
	 * ��ȡ��ʱ�ڳ���ܶ�
	 * @param model
	 * @return String
	 * */
	public String getLsgcjze(QgzxCjffForm model){
		return dao.getLsgcjze(model);
	}
	
	/**
	 * ��ʱ���ʲ�����Ϣ����
	 * 
	 * @param model
	 * @param request
	 * @return boolean
	 * @throws Exception
	 * @author luo
	 */
	public boolean saveGzLsInfo(QgzxCjffForm model, String addRowNum,
			HttpServletRequest request) throws Exception {
		boolean flag = false;
		String count = model.getCount();
		HashMap<String, String> gwMap = dao.getGwinfo(model.getPk(), model
				.getPkValue());
		count = count == null || "".equalsIgnoreCase(count) ? "0" : count;
		String tableName = "xslsgcjffb";
//		String fflx = "��ʱ";
		String yrdwdm = gwMap.get("yrdwdm");
		String ffsj = gwMap.get("nowtime");
		String ffr = model.getUserName();
		String nd = model.getNd();
		String yf = model.getYf();
		addRowNum = addRowNum == null || "".equalsIgnoreCase(addRowNum) ? "0" : addRowNum;

		yf = yf == null || "".equalsIgnoreCase(yf) ? "0" : yf;
		yf = String.valueOf(Integer.parseInt(yf));

		String[] columns = new String[] { "xh", "gwdm", "cjje", "bz", "gwsbsj",
				"gzsj", "nd", "yf" , "ffsj"};
		String[] values = {};

		for (int i = 0; i < Integer.parseInt(count); i++) {
			String xh = request.getParameter("xh" + i);
			String gzsj = DealString.toGBK(request.getParameter("gzsj" + i));
			String cjje = DealString.toGBK(request.getParameter("cjje" + i));
			String gwdm = gwMap.get("gwdm");
			String gwsbsj = gwMap.get("gwsbsj");
			String bz = DealString.toGBK(request.getParameter("bz" + i));
			if ((cjje != null && !"".equalsIgnoreCase(cjje))
					|| (gzsj != null && !"".equalsIgnoreCase(gzsj))) {
				values = new String[] { xh, gwdm, cjje, bz, gwsbsj, gzsj, nd,
						yf, ffsj };
				flag = StandardOperation.delete(tableName, "xh||gwdm", xh
						+ gwdm, request);
				if (flag) {
					flag = StandardOperation.insert(tableName, columns, values,
							request);
				}
				if (flag) {
					if (flag) {
						if (dao.checkExists("cjffsjb", "yrdwdm", yrdwdm)) {// �������˵�λ���ų���ʱ��
							StandardOperation.update("cjffsjb", new String[] {
									"ffsj", "ffr" },
									new String[] { ffsj, ffr }, "yrdwdm",
									yrdwdm, request);
						} else {
							StandardOperation.insert("cjffsjb", new String[] {
									"yrdwdm", "ffsj", "ffr" }, new String[] {
									yrdwdm, ffsj, ffr }, request);
						}
					}
				}
			}
		}

		for (int i = Integer.parseInt(count); i <= Integer.parseInt(count)
				+ Integer.parseInt(addRowNum); i++) {
			String xh = request.getParameter("xh" + i);
			String gzsj = DealString.toGBK(request.getParameter("gzsj" + i));
			String cjje = DealString.toGBK(request.getParameter("cjje" + i));
			String gwdm = gwMap.get("gwdm");
			String gwsbsj = gwMap.get("gwsbsj");
			String bz = DealString.toGBK(request.getParameter("bz" + i));
			if ((cjje != null && !"".equalsIgnoreCase(cjje))
					|| (gzsj != null && !"".equalsIgnoreCase(gzsj))) {
				values = new String[] { xh, gwdm, cjje, bz, gwsbsj, gzsj, nd,
						yf,ffsj };
				flag = StandardOperation.insert(tableName, columns, values,
						request);
				if (flag) {
					if (flag) {						
						if (dao.checkExists("cjffsjb", "yrdwdm", yrdwdm)) {// �������˵�λ���ų���ʱ��
							StandardOperation.update("cjffsjb", new String[] {
									"ffsj", "ffr"},
									new String[] { ffsj, ffr }, "yrdwdm",
									yrdwdm, request);
						} else {
							StandardOperation.insert("cjffsjb", new String[] {
									"yrdwdm", "ffsj", "ffr" }, new String[] {
									yrdwdm, ffsj, ffr }, request);
						}
					}
				}
			}
		}
		return flag;
	}
	
	/**
	 * ��ȡѧ����𷢷Ų�ѯ��ͷ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXscjffTorTr(CommanForm model){
		String tabName = "view_xscjff";
		String[] colList = dao.getXscjffbCol(model);
		String[] colListCN  = dao.getColumnNameCN(colList, tabName);
		return dao.arrayToList(colList, colListCN);
	}
	
	/**
	 * ѧ����𷢷���Ϣ��ѯ
	 * @param CommanForm model
	 * @return List<String>
	 * */
	public List<String[]> queryXscjffb(CommanForm model){
		String xxdm = StandardOperation.getXxdm();
		List<String[]> rs = new ArrayList<String[]>();
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			//�й����ʴ�ѧ
			rs = dao.selectXscjffbByZgdzdx(model);
		}else if(Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)){
			//�㽭��ýѧԺ
			rs = dao.selectXscjffbByZjcmxy(model);
		}else{
			rs = dao.selectXscjffb(model);
		}
		return rs;
	}
	
	/**
	 * ��ʱ��λ��Ϣ
	 * @param pk
	 * @param pkValue
	 * @return List
	 * @author luo
	 */
	public HashMap<String, String> getGwinfoOne(QgzxCjffForm model) {
		return dao.getGwinfoOne(model);
	}
	
	/**
	 * ��ȡ��ǰʱ��,��ʽ:****��**��**��
	 * @return String 
	 * */
	public String getNowTime() {
		return dao.getNowTime();
	}
}
