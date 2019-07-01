package xgxt.szdw.bjlh.fdycpwj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.szdw.bjlh.SzkhCssz;
import xgxt.szdw.bjlh.fdykh.BjlhFdykhForm;
import xsgzgl.comm.BasicService;

public class BjlhFdycpwjService extends BasicService{

	BjlhFdycpwjDAO dao=new BjlhFdycpwjDAO();
	
	/**
	 * ��ȡ��ͷ
	 * @param type
	 * @return
	 */
	public String[] getTopTr(String type){
		String[] topTr=new String[]{};
		if("cpwjgl".equals(type)){//�����ʾ����
			topTr=new String[]{"ѧ��","�ʾ�ID","�ʾ�����","�Ƿ�����","��������","������"};
		}else if("cpwjst".equals(type)){//�����ʾ�����
			topTr=new String[]{"����","����"};
		}else if("cpwjtj".equals(type)){//�����ʾ�ͳ��
			topTr=new String[]{"����ѧ��","ְ����","����","���ڲ���","ѧ������","�Ѵ������"};
		}
		return topTr;
	}
	
	/**
	 * ��ȡ�����ʾ��б�
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getCpwjList(BjlhFdycpwjForm model,HttpServletRequest request) throws Exception{
		return dao.getCpwjList(model,request);
	}
	
	/**
	 * ��ȡ�����ʾ�����¼
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getCpwjOne(BjlhFdycpwjForm model){
		return dao.getCpwjOne(model);
	}
		
	/**
	 * ��������ʾ���Ϣ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public boolean saveCpwjInfo(BjlhFdycpwjForm model,String type) throws Exception{
		return dao.saveCpwjInfo(model,type);
	}
	
	/**
	 * ɾ�������ʾ���Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String deleteCpwjInfo(BjlhFdycpwjForm model) throws Exception{
		return dao.deleteCpwjInfo(model);
	}
	
	/**
	 * ��֤�����ʾ�Ȩ��
	 * @param model
	 * @return
	 */
	public String checkCpwjQx(BjlhFdycpwjForm model){
		return dao.checkCpwjQx(model);
	}
	
	/**
	 * ���Ʋ����ʾ���Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean copyCpwjInfo(BjlhFdycpwjForm model) throws Exception{
		return dao.copyCpwjInfo(model);
	}
	
	/**
	 * �Ƿ����ò����ʾ�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String sfqyCpwj(BjlhFdycpwjForm model) throws Exception{
		return dao.sfqyCpwj(model);
	}
	
	/**
	 * ��ȡ�����ʾ������б�
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getCpwjStList(BjlhFdycpwjForm model){
		return dao.getCpwjStList(model);
	}
	
	/**
	 * ��������ļ�������Ϣ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean saveCpwjStxx(BjlhFdycpwjForm model,String type) throws Exception{
		return dao.saveCpwjStxx(model,type);
	}
	
	/**
	 * ��ȡ�����ʾ�������Ϣ�б�
	 * @param model
	 * @return
	 */
//	public List<HashMap<String,String>> getCpwjStxxList(BjlhFdycpwjForm model){
//		return dao.getCpwjStxxList(model);
//	}
	
	/**
	 * ��ȡѡ���б�
	 * @param model
	 * @param type
	 * @return
	 */
	public List<HashMap<String,String>> getXxlist(BjlhFdycpwjForm model,String type){
		return dao.getXxlist(model,type);
	}
	
	/**
	 * ��ȡһ���ʾ�ѡ����Ϣ�������Map �Է���ҳ�����ݵ�չ��--Ϊ�˼�ҳ�棬���ǲ�ȡ�ں�ֱ̨�Ӵ����html���ַ�����
	 * @param model
	 * @return
	 */
	public HashMap<String,List<HashMap<String,String>>> getXxMapListMap(BjlhFdycpwjForm model){
		HashMap<String,List<HashMap<String,String>>> map=new HashMap<String, List<HashMap<String,String>>>();
		List<HashMap<String,String>> stList=getCpwjStList(model);
		List<HashMap<String,String>> xxList=getXxlist(model,"wj");
		if(stList==null||stList.size()==0||xxList==null||xxList.size()==0){
			return map;
		}
		int xxindex=0;
		for(int i=0;i<stList.size();i++){
			if("2".equals(stList.get(i).get("stlx"))){//����Ǽ����ֱ������
				continue;
			}
			List<HashMap<String,String>> stxx=new ArrayList<HashMap<String,String>>();
			for(int j=xxindex;j<xxList.size();j++){
				if(!stList.get(i).get("stid").equals(xxList.get(j).get("stid"))){//�������id��һ��ʱ��ֱ�ӽ�����һ������
					break;
				}
				stxx.add(xxList.get(j));
				xxindex++;
			}
			map.put(stList.get(i).get("stid"), stxx);
		}
		return map;
	}
	
	public List<HashMap<String,String>> getStxxXxHtml(BjlhFdycpwjForm model,List<HashMap<String,String>> stList){
		List<HashMap<String,String>> xxList=getXxlist(model,"wj");
		HashMap<String,String> daMap=getWjStDaMap(model);//��map
		String da;//��
		if(stList==null||stList.size()==0||xxList==null||xxList.size()==0){
			return stList;
		}
		int xxindex=0;//ѡ��ѭ������
		int dhxxgs=0;//����ѡ�����
		int yxhxxgs=0;//��ѭ��ѡ�����
		String stlx;//��������
		HashMap<String,String> st;//����
		HashMap<String,String> xx;//ѡ��
		
		for(int i=0;i<stList.size();i++){
			st=stList.get(i);
			stlx=st.get("stlx");
			if("1".equals(stlx)){//��ѡ��
				dhxxgs=Integer.parseInt(st.get("dhxxgs"));
				yxhxxgs=1;
				StringBuffer xxHtml=new StringBuffer();
				for(int j=xxindex;j<xxList.size();j++){
					xx=xxList.get(j);
					if(!st.get("stid").equals(xx.get("stid"))){//�������id��һ��ʱ��ֱ�ӽ�����һ������
						break;
					}
					da=Base.chgNull(daMap.get(st.get("stid")+"-"+xx.get("xxid")), "", 0);
					xxHtml.append(xx.get("zfbm")+"��");//�ַ�����
					xxHtml.append("<input type='radio' name='st_"+st.get("stid")+"' value='"+xx.get("xxid")+"' "+da+"/>"+xx.get("xxnr"));
					xxHtml.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					if(yxhxxgs%dhxxgs==0){//���ڿ��ƻ���
						xxHtml.append("<br/>");
					}
					yxhxxgs++;
					xxindex++;
				}
				st.put("xxHtml", xxHtml.toString());
			}else if("2".equals(stlx)){//�����
				da=Base.chgNull(daMap.get(st.get("stid")+"-"), "", 0).replace("checked=\"checked\"", "");
				st.put("xxHtml", "<input type='text' name='st_"+st.get("stid")+"' value='"+da+"' maxlength='500' style='width:600px;'/>");
			}
		}
		return stList;
	}
	
	public HashMap<String,String> getWjStDaMap(BjlhFdycpwjForm model){
		List<HashMap<String,String>> daList=dao.getWjStDaList(model);
		HashMap<String,String> daMap=new HashMap<String, String>();
		if(daList==null||daList.size()==0){
			return daMap;
		}
		String stlx;
		for(int i=0;i<daList.size();i++){
			stlx=daList.get(i).get("stlx");
			if("1".equals(stlx)){
				daMap.put(daList.get(i).get("stxx"),"checked=\"checked\"");
			}else if("2".equals(stlx)){
				daMap.put(daList.get(i).get("stxx"), daList.get(i).get("wbda"));
			}
		}
		return daMap;
	}
	
	/**
	 * ��ȡѧ���Ƿ��������ʾ�
	 * @param model
	 * @return
	 */
	public boolean getXsWjstSfzd(BjlhFdycpwjForm model){
		return dao.getXsWjstSfzd(model);
	}
	
	/**
	 * ��ȡ�����ʾ����ⵥ����¼
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getCpwjStxxOne(BjlhFdycpwjForm model){
		return dao.getCpwjStxxOne(model);
	}
	
	/**
	 * ��������ʾ��
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean saveCpwjDa(HttpServletRequest request,BjlhFdycpwjForm model) throws Exception{
		boolean b=false;
		List<HashMap<String,String>> stList=getCpwjStList(model);
		if(stList==null||stList.size()==0){
			return b;
		}
		String xh=model.getXh();//ѧ��
		String wjid=model.getWjid();//�ʾ�id
		
		//String fdy=dao.getFdyInfo(model.getXh()).get("zgh");//����Աid
		String fdy = model.getFdyid();
		HashMap<String,String> st;//����
		String stlx;//��������
		String stid;//����id
		String da;//��
		
		List<String> sqls=new ArrayList<String>();
		sqls.add("delete from xg_szdw_bjlh_fdycpwjdab where xh='"+xh+"' and wjid='"+wjid+"' and fdyid='" + fdy + "'");
		for(int i=0;i<stList.size();i++){
			st=stList.get(i);
			stlx=st.get("stlx");
			stid=st.get("stid");
			da=request.getParameter("st_"+stid);
			if("1".equals(stlx)){//��ѡ��
				sqls.add("insert into xg_szdw_bjlh_fdycpwjdab(xh,wjid,stid,xxid,fdyid) values('"+xh+"','"+wjid+"','"+stid+"','"+da+"','"+fdy+"')");
			}else if("2".equals(stlx)){//�����
				sqls.add("insert into xg_szdw_bjlh_fdycpwjdab(xh,wjid,stid,wbda,fdyid) values('"+xh+"','"+wjid+"','"+stid+"','"+da+"','"+fdy+"')");
			}
		}
		
		return new CommDAO().saveArrDate(sqls.toArray(new String[]{}));
	}
	
	/**
	 * ��ȡ����ѡ��ͳ����ϢHTML
	 * @param model
	 * @param stList
	 * @return
	 */
	public List<HashMap<String,String>> getStxxTjxxHtml(BjlhFdycpwjForm model,List<HashMap<String,String>> stList){
		List<HashMap<String,String>> xxList=dao.getWjstTjInfoList(model);//getXxlist(model,"wj")
		if(stList==null||stList.size()==0||xxList==null||xxList.size()==0){
			return stList;
		}
		int xxindex=0;//ѡ��ѭ������
		String stlx;//��������
		HashMap<String,String> st;//����
		HashMap<String,String> xx;//ѡ��
		
		for(int i=0;i<stList.size();i++){
			st=stList.get(i);
			stlx=st.get("stlx");
			if("1".equals(stlx)){//��ѡ��
				StringBuffer xxHtml=new StringBuffer();
				for(int j=xxindex;j<xxList.size();j++){
					xx=xxList.get(j);
					if(!st.get("stid").equals(xx.get("stid"))){//�������id��һ��ʱ��ֱ�ӽ�����һ������
						break;
					}
					xxHtml.append(xx.get("zfbm")+"��");//�ַ�����
					xxHtml.append(xx.get("xxnr"));
					xxHtml.append("(������"+xx.get("xxrs")+"/"+xx.get("xxrsbfb")+")");
					xxHtml.append("<br/>");
					xxindex++;
					st.put("dtrs", xx.get("dtrs"));
				}
				st.put("xxHtml", xxHtml.toString());
			}else{
				//����ǷǼ���⣬ֱ���Ƴ�
				stList.remove(i);
				i--;
			}
		}
		return stList;
	}
	
	public List<HashMap<String,String>> getAddXnList(){
		return dao.getAddXnList();
	}
	
	/**
	 * ��ȡ�����ʾ�ͳ�Ʋ�ѯ�б�
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getCpwjTjQueryList(BjlhFdycpwjForm model,HttpServletRequest request) throws Exception{
		return dao.getCpwjTjQueryList(model,request);
	}
	
	/**
	 * ��ȡѧ���ĸ���Ա��Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getFdyInfo(BjlhFdycpwjForm model){
		return dao.getFdyInfo(model.getXh());
	}
	
	/**
	 * ��ò������ñ��е�Ĭ������
	 * @return
	 */
	public HashMap<String, String> getMrsz() {
		return dao.getMrsz();
	}

	public String getDate(){
		return dao.getNowTime("YYYYMMDD");
	}

	/** 
	 * @����:��ȡѧ����Ҫ�����Ľ�ʦ��Ϣ
	 * @���ߣ�cmj
	 * @���ڣ�2013-12-13 ����04:29:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCpFdyList(BjlhFdycpwjForm myForm) {
		String lx="";
		if("fdy".equalsIgnoreCase(SzkhCssz.KHDX)){//���ֶ��󸨵�Ա
			lx="����Ա";
		} else if("bzr".equalsIgnoreCase(SzkhCssz.KHDX)){//���ֶ��������
			lx="������";
		}
		
		return dao.getCpFdyList(myForm,lx);
	}
	
	/**
	 * ��ȡ�����ʾ��Ƿ��������ʾ�
	 * @param model
	 * @return
	 */
	public boolean getCpwjSfzd(BjlhFdycpwjForm myForm){
		return dao.getCpwjSfzd(myForm);
	}
	
}
