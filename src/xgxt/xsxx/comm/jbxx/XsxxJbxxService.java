package xgxt.xsxx.comm.jbxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.xsxx.comm.jbsz.XsxxJbszForm;
import xgxt.xsxx.comm.jbsz.XsxxJbszService;
import xgxt.xtwh.xtwhOther.DmwhSelectMethod;

public class XsxxJbxxService extends XsxxJbszService {
	
	/** 
	 * ���������ʾ���б�
	 * @author qlj
	 */
	public  XsxxJbxxDAO dao=new XsxxJbxxDAO();
	
	//ʡ���ؼ��
	public final HashMap<String,String>SSXJC=new HashMap<String,String>();
	//�����ֶ�����
	public final HashMap<String,String>TSZDMC=new HashMap<String,String>();
	
	public List<HashMap<String, String>> getXsqxxList(XsxxJbszForm model) {
		model.setXsqsfqy("��");
		return getXsqList(model);
	}
	
	/**
	 * ����ֶ�λ��
	 * @author qlj
	 */
	public List<HashMap<String, String>> getZdwzList(XsxxJbxxForm myForm) {
		
		String tableName = "xg_xsxx_xsqwzb";
		StringBuilder query=new StringBuilder();
		ArrayList<String> inPutList = new ArrayList<String>();
		
		String[] colList = new String[] { "xsqdm", "zd", "szh", "szl"};
		String sql = "";

		return getRsList(tableName, query.toString(), inPutList.toArray(new String[]{}), colList, sql);
	}
	
	/**
	 * ��������ֶ��б�
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, String>> getQyzdList(XsxxJbxxForm model) {

		String tableName = "xg_view_xsxx_zdszb";
		String query = " where search_sfqy = ? ";
		String[] inPutList = new String[] { "��" };
		String[] colList = new String[] { "search_zd", "search_ymxs","search_lrxz","search_wkxz",
				"search_lrxs","search_sfqy"};
		String sql = "";
		return getRsList(tableName, query, inPutList, colList, sql);
	}
	
	public List<HashMap<String,Object>>getXsqnrList(XsxxJbxxForm model) throws Exception{
		
		//����ʡ���ؼ����Ϣ
		setTszdmc();
		//��ʾ��������
		List<HashMap<String, Object>> xsqnrList =new ArrayList<HashMap<String, Object>>();
		//��ʾ����
		List<HashMap<String, String>> xsqList=getXsqxxList(model);
		//�ϲ���
		List<HashMap<String, String>> hbhList=getXsqhbhList(model);
		//��ʾ���ֶ���ϸ��Ϣ(�����ֶδ���)
		List<HashMap<String, String>>xsqzdList=setTszd(getXsqZdwzList(model));
		
		//��֤����
		xsqzdList=fieldValidate(xsqzdList,model);
		
		for(int i=1;i<=xsqList.size();i++){
			//��ʾ������
			HashMap<String,Object>xsqnrMap=new HashMap<String, Object>();
			
			HashMap<String,String>xsqMap=xsqList.get(i-1);
			
			//����ʾ����Ϣ��ӵ���ʾ��������
			xsqnrMap.putAll(xsqMap);
			
			//��ʾ�� ��ʾ��¼�ϲ���Ϣ
			List<HashMap<String, Object>> xshList=new ArrayList<HashMap<String, Object>> ();
			int szhs=Integer.parseInt(xsqMap.get("szhs"));//��ʾ����ռ��
			int zpszhs=Integer.parseInt(xsqMap.get("zpszhs"));//��Ƭ��ռ��
			String zpxs=xsqMap.get("zpxs");//��Ƭ��ʾ
			for(int j=1;j<=szhs;j++){
				//�Ƿ�ϲ�
				String sfhb="��";
				HashMap<String, Object> xshMap=new HashMap<String,Object>();
				for(int m=0;m<hbhList.size();m++){
					HashMap<String,String>hbhMap=hbhList.get(m);
					
					
					if(hbhMap.get("xsqdm").equalsIgnoreCase(xsqMap.get("xsqdm"))
							&& hbhMap.get("hbh").equalsIgnoreCase(""+j)){
						sfhb="��";
						break;
					}else{
						sfhb="��";
					}
					
				}
				
				xshMap.put("dygleft", "");
				xshMap.put("dygright", "");
				HashMap<String,String>leftMap=new HashMap<String,String>();
				HashMap<String,String>rightMap=new HashMap<String,String>();
					//��ʾ���е�����Ϣ�е�Ԫ����Ϣ
					for(int m=0;m<xsqzdList.size();m++){
						HashMap<String,String>xsqzdMap=xsqzdList.get(m);
						
							if(xsqzdMap.get("xsqdm").equalsIgnoreCase(xsqMap.get("xsqdm"))
									&& xsqzdMap.get("zdszh").equalsIgnoreCase(""+j)){
								
								if("1".equalsIgnoreCase(xsqzdMap.get("zdszl"))){
									leftMap.put("zd",xsqzdMap.get("zd"));//��Ԫ����;
									leftMap.put("jc",xsqzdMap.get("jc"));//�ֶμ��;
									leftMap.put("dygh",xsqzdMap.get("zdszh"));//��Ԫ����;
									leftMap.put("dygl",xsqzdMap.get("zdszl"));//��Ԫ����;
									leftMap.put("zdm",xsqzdMap.get("zdm"));//�ֶ���;
									leftMap.put("zszh",xsqzdMap.get("zszh"));//��Ԫ����;
									leftMap.put("zszl",xsqzdMap.get("zszl"));//��Ԫ����;
									leftMap.put("lrxs",xsqzdMap.get("lrxs"));//�ֶ���;
									leftMap.put("wkxz",xsqzdMap.get("wkxz"));//Ϊ������;
									leftMap.put("methods",xsqzdMap.get("methods"));//��֤����;
									if("��".equalsIgnoreCase(xsqzdMap.get("xgwz"))){
										leftMap.put("disabled","");
									}else{
										leftMap.put("disabled","true");
									}
								}else{
									rightMap.put("jc",xsqzdMap.get("jc"));//�ֶμ��;
									rightMap.put("zd",xsqzdMap.get("zd"));//��Ԫ����;
									rightMap.put("dygh",xsqzdMap.get("zdszh"));//��Ԫ����;
									rightMap.put("dygl",xsqzdMap.get("zdszl"));//��Ԫ����;
									rightMap.put("zdm",xsqzdMap.get("zdm"));//�ֶ���;
									rightMap.put("zszh",xsqzdMap.get("zszh"));//��Ԫ����;
									rightMap.put("zszl",xsqzdMap.get("zszl"));//��Ԫ����;
									rightMap.put("lrxs",xsqzdMap.get("lrxs"));//�ֶ���;
									rightMap.put("wkxz",xsqzdMap.get("wkxz"));//Ϊ������;
									rightMap.put("methods",xsqzdMap.get("methods"));//��֤����;
									if("��".equalsIgnoreCase(xsqzdMap.get("xgwz"))){
										rightMap.put("disabled","");
									}else{
										rightMap.put("disabled","true");
									}
								}
								
							}
						
							
					}
					
				xshMap.put("leftMap",leftMap);//��Ԫ����;
				xshMap.put("rightMap",rightMap);//��Ԫ����;
				//��Ҫ��ʾ��Ƭ,���ҵ���ռ�д�����Ƭ��ռ��(ֻ����Ƭ���Ժ������)
				if("��".equalsIgnoreCase(zpxs) && zpszhs<j){
					xshMap.put("hbh", j);
					xshMap.put("sfhb", sfhb);
				}else if("��".equalsIgnoreCase(zpxs)){
					xshMap.put("hbh", j);
					xshMap.put("sfhb", sfhb);
					
				}
				xshList.add(xshMap);
			}
			xsqnrMap.put("xshxx", xshList);
			xsqnrList.add(xsqnrMap);
		}
		return xsqnrList;
	}
	
	public void setXlkList(XsxxJbxxForm model, HttpServletRequest request) {

		// �����б�
		List<HashMap<String, String>> xllbList = dao.getXllbList(model);

		for (int i = 0; i < xllbList.size(); i++) {
			
			HashMap<String, String> xllbMap = xllbList.get(i);
			List<HashMap<String,String>>whzdList=new ArrayList<HashMap<String,String>>();
			if("yes".equalsIgnoreCase(xllbMap.get("lydmwh"))){
				try {
					whzdList = (ArrayList<HashMap<String, String>>)DmwhSelectMethod.class.getMethod(xllbMap.get("lyb"),
							(Class[]) null).invoke(null,(Object[]) null);
					
				} catch (Exception e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}else {
				model.setTableName(xllbMap.get("lyb"));
				model.setZddm(xllbMap.get("dm"));
				model.setZdmc(xllbMap.get("mc"));
				whzdList=dao.getXlkxx(model);
			}
			request.setAttribute(xllbMap.get("zd") + "List",whzdList);
		}
	}
	
	/**
	 * ʡ�������������⴦��
	 * @param xsqzdList
	 * @return  List<HashMap<String, String>>
	 * @author qlj
	 */
	public List<HashMap<String, String>>setTszd(List<HashMap<String, String>>xsqzdList){
		List<HashMap<String, String>>xszdList=new ArrayList<HashMap<String,String>>();
		for(int j=0;j<xsqzdList.size();j++){
			HashMap<String,String>xsqzdMap=xsqzdList.get(j);
			//ֻ����¼����ʽΪ�����ʽʱ��������
			if("�����ʽ".equalsIgnoreCase(xsqzdMap.get("lrxs"))){
				xsqzdMap.put("jc",SSXJC.get(xsqzdMap.get("zd")));
			}
			xszdList.add(xsqzdMap);
		}
		return xszdList;
	}
	
	/**
	 * ����ʡ�����������
	 * @author qlj
	 */
	public void setSsxjc(){
		SSXJC.put("syd", "sy");
		SSXJC.put("jg", "jg");
		SSXJC.put("hkszd", "hk");
	}
	
	/**
	 * �����ֶ����ƴ���
	 * @author qlj
	 */
	public void setTszdmc(){
		TSZDMC.put("syd", "sydmc");
		TSZDMC.put("jg", "jgmc");
		TSZDMC.put("hkszd", "hkszdmc");
		
		TSZDMC.put("xydm", "xymc");
		TSZDMC.put("zydm", "zymc");
		TSZDMC.put("bjdm", "bjmc");
		TSZDMC.put("nj", "nj");
	}
	
	/**
	 * �ֶ���֤
	 * @param request
	 * @param model
	 * @return List<HashMap<String,Object>>
	 * @throws Exception
	 * @author qlj
	 */
	public List<HashMap<String, String>> fieldValidate(List<HashMap<String, String>>xsqzdList,
			XsxxJbxxForm model) throws Exception{
		
		String tableName="xsxxb";
		//��ȡѧ����Ϣ���ֶ���Ϣ(�ֶ�,�ֶ���,�ֶγ���)
		List<HashMap<String,String>>zdxxList=getTableZdList(tableName);
		//��Ҫ��ʾ�ֶε���Ϣ
		List<HashMap<String,String>>xxszdList=new ArrayList<HashMap<String,String>>();
		StringBuilder pk=new StringBuilder();
		int m=0;
			for(int j=0;j<xsqzdList.size();j++){
				HashMap<String,String>xsqzdMap=xsqzdList.get(j);
				for (int i=0;i<zdxxList.size();i++){
					HashMap<String,String>zdxxMap=zdxxList.get(i);
					if(xsqzdMap.get("lrxs").equalsIgnoreCase("�����")
						&& zdxxMap.get("dm").equalsIgnoreCase(xsqzdMap.get("zd"))){
					
						String lrffxz="";
						if("ֻ������".equalsIgnoreCase(xsqzdMap.get("lrxz"))
								|| "������".equalsIgnoreCase(xsqzdMap.get("lrxz"))){
							int length=Integer.parseInt(zdxxMap.get("len"));
							lrffxz+="checkLen($('"+xsqzdMap.get("zd")+"'),'"+length/2+"');";
						}else{
							lrffxz+="checkLen($('"+xsqzdMap.get("zd")+"'),'"+zdxxMap.get("len")+"');";
						}
					
					
						if("��������".equalsIgnoreCase(xsqzdMap.get("lrxz"))){//����
							lrffxz+="checkNum($('"+xsqzdMap.get("zd")+"'));";
						}else if("��������".equalsIgnoreCase(xsqzdMap.get("lrxz"))){//�ɴ�С����
							lrffxz+="checkNumber($('"+xsqzdMap.get("zd")+"'));";
						}else if("Ӣ��������".equalsIgnoreCase(xsqzdMap.get("lrxz"))){//Ӣ����
							lrffxz+="checkWordNum($('"+xsqzdMap.get("zd")+"'));";
						}else if("��������".equalsIgnoreCase(xsqzdMap.get("lrxz"))){//����
							lrffxz+="checkInputIsZw($('"+xsqzdMap.get("zd")+"'));";
						}
						xsqzdMap.put("methods", lrffxz);
					
						if("����Ϊ��".equalsIgnoreCase(xsqzdMap.get("wkxz"))){
							if(m==0){
								pk.append(xsqzdMap.get("zd"));
							}else{
								pk.append("-"+xsqzdMap.get("zd"));
							}
							m++;
						}
						
					}else{
						if("����Ϊ��".equalsIgnoreCase(xsqzdMap.get("wkxz"))
								){
							if(m==0){
								pk.append(xsqzdMap.get("zd"));
							}else{
								pk.append("-"+xsqzdMap.get("zd"));
							}
							m++;
						}
					}
					break;
			}
				xxszdList.add(xsqzdMap);
				
		}
		model.setPk(pk.toString());
		return xxszdList;
	}
	
	
	
	
	
	/**
	 * �ֶεĳ�����֤
	 * @param request
	 * @param model
	 * @return List<HashMap<String,Object>>
	 * @throws Exception
	 * @author qlj
	 */
	public void checkZdSize(HttpServletRequest request,XsxxJbszForm model) throws Exception{
		String tableName="xsxxb";
		//��ȡѧ����Ϣ���ֶ���Ϣ(�ֶ�,�ֶ���,�ֶγ���)
		List<HashMap<String,String>>zdxxList=getTableZdList(tableName);
		//��ȡѧ����Ϣ�ֶ����ñ���Ϣ
		List<HashMap<String,String>>szzdList=getXsqZdwzList(model);
		//��Ҫ��ʾ�ֶε���Ϣ
		List<HashMap<String,String>>xxszdList=new ArrayList<HashMap<String,String>>();
		StringBuilder pk=new StringBuilder();
		StringBuilder zdmc=new StringBuilder();
		int m=0;
		boolean blog=false;
		for(int i=0;i<szzdList.size();i++){
			HashMap<String,String>szzdMap=szzdList.get(i);
			HashMap<String,String>xxszdMap=new HashMap<String,String>();
			for(int j=0;j<zdxxList.size();j++){
				HashMap<String,String>zdxxMap=zdxxList.get(j);
				
				if(szzdMap.get("lrxs").equalsIgnoreCase("�����")
					&& szzdMap.get("zd").equalsIgnoreCase(zdxxMap.get("dm"))){
					xxszdMap.put("zd", szzdMap.get("zd"));
					xxszdMap.put("mc", zdxxMap.get("mc"));
					//����¼�����ƿ����ֶ����볤��(����������״̬,����Ϊһ��)
					if("ֻ������".equalsIgnoreCase(szzdMap.get("lrxz"))
							|| "������".equalsIgnoreCase(szzdMap.get("lrxz"))){
						int length=Integer.parseInt(zdxxMap.get("len"));
						xxszdMap.put("len", ""+length/2);
					}else{
						xxszdMap.put("len", zdxxMap.get("len"));
					}
					xxszdMap.put("lrxz", szzdMap.get("lrxz"));
					xxszdMap.put("lrxs", szzdMap.get("lrxs"));
					xxszdList.add(xxszdMap);
					if("����Ϊ��".equalsIgnoreCase(szzdMap.get("wkxz"))){
						if(m==0){
							pk.append(szzdMap.get("zd"));
						}else{
							pk.append("-"+szzdMap.get("zd"));
						}
						m++;
					}
					break;
				}else{
					if("����Ϊ��".equalsIgnoreCase(szzdMap.get("wkxz"))
							&& !blog){
						if(m==0){
							pk.append(szzdMap.get("zd"));
						}else{
							pk.append("-"+szzdMap.get("zd"));
						}
						blog=true;
						m++;
					}
				}
				
			}
			
		}
		request.setAttribute("pk", pk);
		request.setAttribute("zdmc", zdmc);
		request.setAttribute("xxszdList", xxszdList);
	}
	
	/**
	 * ��ȡѧ�������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String>getXsxgxx(XsxxJbxxForm model){
		return dao.getXsxgxx(model);
	}
	
	
	public List<HashMap<String,Object>>designXsxx(XsxxJbxxForm model){
		//����ʡ���ؼ����Ϣ
		setTszdmc();
		//��ʾ������
		List<HashMap<String, Object>> xsqnrList =new ArrayList<HashMap<String, Object>>();
		//��ʾ��
		List<HashMap<String, String>> xsqList=getXsqxxList(model);
		//�ϲ���
		List<HashMap<String, String>> hbhList=getXsqhbhList(model);
		//��ʾ���ֶ���ϸ��Ϣ(�����ֶ���ʾ����)
		List<HashMap<String, String>>xsqzdList=getTszdmc(getXxyxx(model));
		
		for(int i=1;i<=xsqList.size();i++){
			//��ʾ������
			HashMap<String,Object>xsqnrMap=new HashMap<String, Object>();
			
			HashMap<String,String>xsqMap=xsqList.get(i-1);
			
			//����ʾ����Ϣ��ӵ���ʾ��������
			xsqnrMap.putAll(xsqMap);
			
			//��ʾ�� ��ʾ��¼�ϲ���Ϣ
			List<HashMap<String, Object>> xshList=new ArrayList<HashMap<String, Object>> ();
			int szhs=Integer.parseInt(xsqMap.get("szhs"));//��ʾ����ռ��
			int zpszhs=Integer.parseInt(xsqMap.get("zpszhs"));//��Ƭ��ռ��
			String zpxs=xsqMap.get("zpxs");//��Ƭ��ʾ
			for(int j=1;j<=szhs;j++){
				//�Ƿ�ϲ�
				String sfhb="��";
				HashMap<String, Object> xshMap=new HashMap<String,Object>();
				for(int m=0;m<hbhList.size();m++){
					HashMap<String,String>hbhMap=hbhList.get(m);
					
					
					if(hbhMap.get("xsqdm").equalsIgnoreCase(xsqMap.get("xsqdm"))
							&& hbhMap.get("hbh").equalsIgnoreCase(""+j)){
						sfhb="��";
						break;
					}else{
						sfhb="��";
					}
					
				}
				
				xshMap.put("dygleft", "");
				xshMap.put("dygright", "");
				HashMap<String,String>leftMap=new HashMap<String,String>();
				HashMap<String,String>rightMap=new HashMap<String,String>();
					//��ʾ���е�����Ϣ�е�Ԫ����Ϣ
					for(int m=0;m<xsqzdList.size();m++){
						HashMap<String,String>xsqzdMap=xsqzdList.get(m);
						
							if(xsqzdMap.get("xsqdm").equalsIgnoreCase(xsqMap.get("xsqdm"))
									&& xsqzdMap.get("zdszh").equalsIgnoreCase(""+j)){
								
								if("1".equalsIgnoreCase(xsqzdMap.get("zdszl"))){
									
									leftMap.put("zd",xsqzdMap.get("mc"));//��Ԫ����;
									leftMap.put("dygh",xsqzdMap.get("zdszh"));//��Ԫ����;
									leftMap.put("dygl",xsqzdMap.get("zdszl"));//��Ԫ����;
									leftMap.put("zdm",xsqzdMap.get("zdm"));//�ֶ���;
									leftMap.put("zszh",xsqzdMap.get("zszh"));//��Ԫ����;
									leftMap.put("zszl",xsqzdMap.get("zszl"));//��Ԫ����;
									leftMap.put("lrxs",xsqzdMap.get("lrxs"));//�ֶ���;
									leftMap.put("wkxz",xsqzdMap.get("wkxz"));//Ϊ������;
									if("��".equalsIgnoreCase(xsqzdMap.get("xgwz"))){
										leftMap.put("disabled","");
									}else{
										leftMap.put("disabled","true");
									}
									
								}else{
									rightMap.put("zd",xsqzdMap.get("mc"));//��Ԫ����;
									rightMap.put("dygh",xsqzdMap.get("zdszh"));//��Ԫ����;
									rightMap.put("dygl",xsqzdMap.get("zdszl"));//��Ԫ����;
									rightMap.put("zdm",xsqzdMap.get("zdm"));//�ֶ���;
									rightMap.put("zszh",xsqzdMap.get("zszh"));//��Ԫ����;
									rightMap.put("zszl",xsqzdMap.get("zszl"));//��Ԫ����;
									rightMap.put("lrxs",xsqzdMap.get("lrxs"));//�ֶ���;
									rightMap.put("wkxz",xsqzdMap.get("wkxz"));//Ϊ������;
									if("��".equalsIgnoreCase(xsqzdMap.get("xgwz"))){
										rightMap.put("disabled","");
									}else{
										rightMap.put("disabled","true");
									}
								}
								
							}
						
							
					}
					
				xshMap.put("leftMap",leftMap);//��Ԫ����;
				xshMap.put("rightMap",rightMap);//��Ԫ����;
				//��Ҫ��ʾ��Ƭ,���ҵ���ռ�д�����Ƭ��ռ��(ֻ����Ƭ���Ժ������)
				if("��".equalsIgnoreCase(zpxs) && zpszhs<j){
					xshMap.put("hbh", j);
					xshMap.put("sfhb", sfhb);
				}else if("��".equalsIgnoreCase(zpxs)){
					xshMap.put("hbh", j);
					xshMap.put("sfhb", sfhb);
					
				}
				xshList.add(xshMap);
			}
			xsqnrMap.put("xshxx", xshList);
			xsqnrList.add(xsqnrMap);
		}
		return xsqnrList;
	}
	
	/**
	 * ��ȡѧ����ϸҳ��Ϣ
	 * @author qlj
	 */
	public List<HashMap<String, String>> getXxyxx(XsxxJbszForm model) {

		return dao.getXxyxx(model);
	}
	
	/**
	 * ʡ�������������⴦��
	 * @param xsqzdList
	 * @return  List<HashMap<String, String>>
	 * @author qlj
	 */
	public List<HashMap<String, String>>getTszdmc(List<HashMap<String, String>>xsqzdList){

		List<HashMap<String, String>>xszdList=new ArrayList<HashMap<String,String>>();
		for(int j=0;j<xsqzdList.size();j++){
			HashMap<String,String>xsqzdMap=xsqzdList.get(j);
			//ֻ����¼����ʽΪ�����ʽʱ��������
			if("�����ʽ".equalsIgnoreCase(xsqzdMap.get("lrxs"))){
				xsqzdMap.put("mc",TSZDMC.get(xsqzdMap.get("zd")));
			}
			xszdList.add(xsqzdMap);
		}
		return xszdList;
	}

}
