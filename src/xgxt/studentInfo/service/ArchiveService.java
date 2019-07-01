package xgxt.studentInfo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.ShgcForm;
import xgxt.studentInfo.dao.ArchiveDAO;
import xgxt.studentInfo.model.StudentInfoForm;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ѧ��ת��Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-12</p>
 */
public class ArchiveService {
	ArchiveDAO dao = new ArchiveDAO();
	/**
	 * ��ʼ��ѡ��ҳ���б�
	 * @param type
	 * @return List
	 * */
	@SuppressWarnings("unchecked")
	public List getPageList(String type){
		List list = new ArrayList();
		String xxdm = StandardOperation.getXxdm();
		HashMap<String, String> map = new HashMap<String, String>();
		if(type!=null && type.equalsIgnoreCase("app")){
			//����ҳ��
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SJXY)) {
				map.put("dyym", "stu_archives_apply.do?zdlb=sx");
				map.put("gnmc", "��ѧת������");
				list.add(map);
				map = new HashMap<String, String>();
				map.put("dyym", "stu_archives_apply.do?zdlb=zx");
				map.put("gnmc", "תѧת������");
				list.add(map);
				map = new HashMap<String, String>();
				map.put("dyym", "stu_archives_apply.do?zdlb=tx");
				map.put("gnmc", "��ѧת������");
				list.add(map);
			}else{
				map.put("dyym", "stu_archives_apply.do");
				map.put("gnmc", "������ת������");
				list.add(map);
				map = new HashMap<String, String>();
				map.put("dyym", "business.do?method=gradArchivesApply");
				map.put("gnmc", "��ҵ��ת������");
				list.add(map);
			}
			
		}else if(type!=null && type.equalsIgnoreCase("que")){
			//��ѯҳ��
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SJXY)) {
				map.put("dyym", "archives_apply_query.do?zdlb=sx&act=query");
				map.put("gnmc", "��ѧת�������ѯ");
				list.add(map);
				map = new HashMap<String, String>();
				map.put("dyym", "archives_apply_query.do?zdlb=zx&act=query");
				map.put("gnmc", "תѧת�������ѯ");
				list.add(map);
				map = new HashMap<String, String>();
				map.put("dyym", "archives_apply_query.do?zdlb=tx&act=query");
				map.put("gnmc", "��ѧת�������ѯ");
				list.add(map);
				map = new HashMap<String, String>();
				map.put("dyym", "archives_apply_query.do?zdlb=by&act=query");
				map.put("gnmc", "��ҵת�������ѯ");
				list.add(map);
			}else{
				map.put("dyym", "archives_apply_query.do?act=query");
				map.put("gnmc", "������ת�������ѯ");
				list.add(map);
				map = new HashMap<String, String>();
				map.put("dyym", "business.do?method=gradArchivesQuerry");
				map.put("gnmc", "��ҵ��ת�������ѯ");
				list.add(map);
			}	
		}else if(type!=null && type.equalsIgnoreCase("exa")){
			//���ҳ��
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SJXY)) {
				map.put("dyym", "archives_apply_query.do?zdlb=sx&act=auditing");
				map.put("gnmc", "��ѧת�����");
				list.add(map);
				map = new HashMap<String, String>();
				map.put("dyym", "archives_apply_query.do?zdlb=zx&act=auditing");
				map.put("gnmc", "תѧת�����");
				list.add(map);
				map = new HashMap<String, String>();
				map.put("dyym", "archives_apply_query.do?zdlb=tx&act=auditing");
				map.put("gnmc", "��ѧת�����");
				list.add(map);
			}else{
				map.put("dyym", "archives_apply_query.do?act=auditing");
				map.put("gnmc", "����ѧת�����");
				list.add(map);
				map = new HashMap<String, String>();
				map.put("dyym", "business.do?method=gradArchivesAuding");
				map.put("gnmc", "��ҵ��ת�����");
				list.add(map);
			}	
		}
		return list;
	}
	
	/**
	 * ����ѧ����������
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveHistoryArchive(ShgcForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String xh = model.getXh();
		String zddwmc = DealString.toGBK(model.getZddwmc());
		String zddwdz = DealString.toGBK(model.getZddwdz());
		String hzh = DealString.toGBK(model.getHzh());
		String zdsj = model.getZysj();
		String dasfzx = model.getDasfzx();
		String zdyy = DealString.toGBK(model.getZdyy());
		String zddwyb = DealString.toGBK(model.getZddwyb());
		String zdfs = DealString.toGBK(model.getZdfs());
		String bynf = DealString.toGBK(model.getBynf());
		String gkcj = DealString.toGBK(model.getGkcj());
		String gkyycj = DealString.toGBK(model.getGkyycj());
		String dxyysp = DealString.toGBK(model.getDxyysp());
		String jsjsp = DealString.toGBK(model.getJsjsp());		
		String dkjl = DealString.toGBK(model.getDkjl());
		String snbjpm = DealString.toGBK(model.getSnbjpm());
		String daly = DealString.toGBK(model.getDaly());
		String lxdh = DealString.toGBK(model.getLxdh());
		String jyh = model.getJyh();
		String daclr = model.getDaclr();
		String daclrlxfs = model.getDaclrlxfs();
		String bz = model.getBz();
		String xl = model.getXl();
		String dwxz = model.getDwxz();
		String daqx = model.getDaqx();
		String tableName = "stu_history_lab";//����ѧ��������		
		
		String[] columns = { "xh", "zddwdz", "zddwmc", "zysj", "dasfzx", "hzh", 
				             "zdyy", "zddwyb", "lxdh", "zdfs", "bynf", "gkcj", 
				             "gkyycj", "dxyysp", "jsjsp", "dkjl", "snbjpm", 
				             "daly", "jyh", "daclr", "daclrlxfs","bz","xl","dwxz",
				             "zlsfqq","sfydycl", "dabh","sjr","sjjgmc","sjsj","daqx"};
		String[] values = { xh, zddwdz, zddwmc, zdsj, dasfzx, hzh, zdyy, zddwyb,
				            lxdh, zdfs, bynf, gkcj, gkyycj, dxyysp, jsjsp, dkjl, 
				            snbjpm, daly, jyh, daclr, daclrlxfs,bz,xl,dwxz,
				            model.getZlsfqq(),model.getSfydycl(),model.getDabh(),
				            model.getSjr(),model.getSjjgmc(),model.getSjsj(),daqx};
		if(dao.isExists(tableName, "xh", xh)){//���ڼ�¼��Ϣִ���޸Ĳ���
			//update
			flag = StandardOperation.update(tableName, columns, values, "xh", xh, request);
		}else{
			//insert
			flag = StandardOperation.insert(tableName, columns, values, request);
		}		
		return flag;
	}
	
	/**
	 * ɾ������ѧ��ת����Ϣ
	 * @param request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean delHistoryArchive(HttpServletRequest request) throws Exception{
		boolean flag = false;
		String pkValue = request.getParameter("pkValue");
		String[] val = pkValue.split("!!SplitOneSplit!!");
		for (int i = 1; i < val.length; i++) {
			flag = StandardOperation.delete("stu_history_lab", "xh",val[i], request);
		}		
		return flag;
	}
	
	/**
	 * ��Уѧ����������
	 * @param model
	 * @param request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveArchive(ShgcForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		ArchiveService service =new ArchiveService();
		String tableName = "stu_archives";
		String pk = "xh";
		
		String xh = model.getXh();
		String zddwdz = DealString.toGBK(model.getZddwdz());
		String zddwmc = DealString.toGBK(model.getZddwmc());
		String zdsj = DealString.toGBK(model.getZysj());
		String gzdasfzx = DealString.toGBK(model.getGzdasfzx());
		String zdyy = DealString.toGBK(model.getZdyy());
		String zddwyb = DealString.toGBK(model.getZddwyb());
		String jsr = DealString.toGBK(model.getJsr());
		String jsnr = DealString.toGBK(model.getJsnr());
		String glr = DealString.toGBK(model.getGlr());
		String dabcwz = DealString.toGBK(model.getDabcwz());
		String dah = DealString.toGBK(model.getDah());
		String sfydycl = DealString.toGBK(model.getSfydycl());
		String bz = DealString.toGBK(model.getBz());
		String daly = DealString.toGBK(model.getDaly());
		String xl = DealString.toGBK(model.getXl());
		String zdqk = DealString.toGBK(model.getZdqk());
		String []dadj = model.getDadj();
		HashMap<String, String> tmpMap = dao.getStuInfo(xh);
		String xm = tmpMap.get("xm");
		String sfzh = tmpMap.get("sfzh");
		String nj = tmpMap.get("nj");
		String bjmc = tmpMap.get("bjmc");
		String zymc = tmpMap.get("zymc");
		String xymc = tmpMap.get("xymc");
		String bjdm = tmpMap.get("bjdm");
		String zydm = tmpMap.get("zydm");
		String xydm = tmpMap.get("xydm");
		String xxdm = Base.xxdm;
		String[] columns = { "xh", "xm", "sfzh", "nj", "bjmc", "zymc","xymc", 
							 "zddwdz", "zddwmc", "zysj", "bjdm", "zydm", "xydm",
							 "gzdasfzx", "zdyy", "zddwyb", "jsr", "jsnr", "glr",
							 "dabcwz", "dah", "sfydycl", "bz" ,"daly", "xl","zdqk",
							 "zlsfqq","dayjr","jyh","xndabh","djr","jssj","sjr"};
		String[] values = { xh, xm, sfzh, nj, bjmc, zymc, xymc, zddwdz,zddwmc, 
							zdsj, bjdm, zydm, xydm, gzdasfzx, zdyy, zddwyb,
							jsr, jsnr, glr, dabcwz, dah, sfydycl, bz, daly,
							xl,zdqk,model.getZlsfqq(),model.getDayjr(),model.getJyh(),
							model.getXndabh(),model.getDjr(),model.getJssj(),model.getSjr()};
		if(Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)){
			 columns = new String[]{ "xh", "xm", "sfzh", "nj", "bjmc", "zymc","xymc", "zddwdz", "zddwmc", "zysj", "bjdm", "zydm", "xydm",
					"gzdasfzx", "zdyy", "zddwyb", "jsr", "jsnr", "glr","dabcwz", "dah", "sfydycl", "bz" ,"daly","xl","zdqk","dadj"};
			 values = new String [] { xh, xm, sfzh, nj, bjmc, zymc, xymc, zddwdz,zddwmc, zdsj, bjdm, zydm, xydm, gzdasfzx, zdyy, zddwyb,
					jsr, jsnr, glr, dabcwz, dah, sfydycl, bz, daly,xl,zdqk,service.dadjPj(dadj)};
		}
		if(dao.isExists(tableName, pk, xh)){
			//update
			flag = StandardOperation.update(tableName, columns, values, pk, xh, request);
		}else{
			//insert
			flag = StandardOperation.insert(tableName, columns, values, request);
		}
		return flag;
	}
	
	/**
	 * ��ѯ��������ѧ��ת����Ϣ
	 * @param ShgcForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryLjxsdaxxOne(ShgcForm model){
		return dao.selectLjxsdaxxOne(model);
	}
	
	/**
	 *���ɵ���ת�����
	 * @param ShgcForm model
	 * @return HashMap<String, String>
	 * */
	public String getZcbh(){
		return dao.getZcbh_db();
	}
	
	/**
	 * ����鵵�����ύ��Ϣ
	 * @param StudentInfoForm model
	 * @param String pkValue
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveGdzltj(StudentInfoForm model, String pkValue) throws Exception{
		return dao.saveGdzltj(model,pkValue);
	}
	
	/**
	 * ����ѧ����У������Ϣ
	 * @param StudentInfoForm model
	 * @param String pkValue
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveXszxda(StudentInfoForm model, String pkValue) throws Exception{
		return dao.saveXszxda(model,pkValue);
	}
	
	/**
	 * �����Ǽ�
	 * 
	 */
	public List<String[]>getDadjList(){
		return dao.getDadjList();
	}
	
	/**
	 * �����Ǽ�ƴ��
	 * 
	 */
	public String dadjPj(String[]dadj){
		StringBuilder dadjStr = new StringBuilder();
		
		if(dadj != null){
			for(int i=0;i<dadj.length;i++){
				if(i==0){
					dadjStr.append(dadj[i]);
				}else{
					dadjStr.append("!!spl!!");
					dadjStr.append(dadj[i]);
				}
			}
		}
		return dadjStr.toString();
	}
	
	/**
	 * ��ֵ����Ǽ�
	 */
	public List<String[]> dadjCf(String xh){
		ArchiveService service =new ArchiveService();
		HashMap<String,String>map=dao.getDadjXx(xh);
		String dadj= map.get("dadj");
		String dadjCf[]=null;
		List<String[]> list =new ArrayList();
		if(!"".equals(dadj)&& !Base.isNull(dadj)){
			
			dadjCf= dadj.split("!!spl!!");
			
			for(int i=0;i<service.getDadjList().size();i++){
				String[] dadjStr=service.getDadjList().get(i);
				String[] dadjs=new String[3];
				for(int j=0;j<dadjCf.length;j++){
					if(dadjStr[0].equals(dadjCf[j])){
						dadjs[0]=dadjStr[0];//MC
						dadjs[1]=dadjStr[1];//DM
						dadjs[2]="checked";
					}
				}	
				if("".equals(dadjs[2]) || Base.isNull(dadjs[2])){
					dadjs[0]=dadjStr[0];//MC
					dadjs[1]=dadjStr[1];//DM
					dadjs[2]="";
				}
				list.add(dadjs);
			}
		}else{
			list=service.getDadjList();
		}
		return list;
	}
	
}
