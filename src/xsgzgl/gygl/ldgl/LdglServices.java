package xsgzgl.gygl.ldgl;

import org.apache.struts.util.MessageResources;
import xgxt.action.Base;
import xgxt.form.User;
import xsgzgl.gygl.comm.GyglNewInit;
import xsgzgl.gygl.comm.GyglNewService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LdglServices extends GyglNewService{
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");
	private LdglDao dao=new LdglDao();
	/**
	 * ¥�����������б�
	 * @return
	 */
	public List<HashMap<String,String>> getLdcsList(){
		ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		for(int i=1;i<=30;i++){//��ǰ���ó�20�㣬���������޸�
			HashMap<String,String> map=new HashMap<String, String>();
			map.put("dm", i+"");
			map.put("mc", i+"");
			list.add(map);
		}
		return list;
	}
	/**
	 * ��ȡ¥���б��ͷ
	 * @param model
	 * @return
	 */
	public String[] getTopTr(LdglModel model){
		Base.GYPZYQ_KEY = message.getMessage("lable.yuanqu");
		String[] outPutString=null;
		if(Base.xxdm.equals("10351")){ // ���ݴ�ѧ
			if(!"0".equals(GyglNewInit.XQBJ)&&!"0".equals(GyglNewInit.YQBJ)){//У����԰��
				outPutString =new String[]{"¥������","¥������","¥���Ա�","¥������","��ʼ���","У������",Base.GYPZYQ_KEY+"����","����Ա","��ע"};
			}else if(!"0".equals(GyglNewInit.XQBJ)){//У��
				outPutString =new String[]{"¥������","¥������","¥���Ա�","¥������","��ʼ���","У������","����Ա","��ע"};
			}else if(!"0".equals(GyglNewInit.YQBJ)){//԰��
				outPutString =new String[]{"¥������","¥������","¥���Ա�","¥������","��ʼ���",Base.GYPZYQ_KEY+"����","����Ա","��ע"};
			}else{
				outPutString =new String[]{"¥������","¥������","¥���Ա�","¥������","��ʼ���","����Ա","��ע"};
			}
		}else if(Base.xxdm.equals("12861")){ // �㽭����ְҵ����ѧԺ
			if(!"0".equals(GyglNewInit.XQBJ)&&!"0".equals(GyglNewInit.YQBJ)){//У����԰��
				outPutString =new String[]{"¥������","¥������","¥���Ա�","¥������","��ʼ���","У������",Base.GYPZYQ_KEY+"����","����Ա","����Ա","��ע"};
			}else if(!"0".equals(GyglNewInit.XQBJ)){//У��
				outPutString =new String[]{"¥������","¥������","¥���Ա�","¥������","��ʼ���","У������","����Ա","����Ա","��ע"};
			}else if(!"0".equals(GyglNewInit.YQBJ)){//԰��
				outPutString =new String[]{"¥������","¥������","¥���Ա�","¥������","��ʼ���",Base.GYPZYQ_KEY+"����","����Ա","����Ա","��ע"};
			}else{
				outPutString =new String[]{"¥������","¥������","¥���Ա�","¥������","��ʼ���","����Ա","����Ա","��ע"};
			}
		}else{
			if(!"0".equals(GyglNewInit.XQBJ)&&!"0".equals(GyglNewInit.YQBJ)){//У����԰��
				outPutString =new String[]{"¥������","¥������","¥���Ա�","¥������","��ʼ���","�Ƿ�0��","У������",Base.GYPZYQ_KEY+"����","����Ա","��ע"};
			}else if(!"0".equals(GyglNewInit.XQBJ)){//У��
				outPutString =new String[]{"¥������","¥������","¥���Ա�","¥������","��ʼ���","�Ƿ�0��","У������","����Ա","��ע"};
			}else if(!"0".equals(GyglNewInit.YQBJ)){//԰��
				outPutString =new String[]{"¥������","¥������","¥���Ա�","¥������","��ʼ���","�Ƿ�0��",Base.GYPZYQ_KEY+"����","����Ա","��ע"};
			}else{
				outPutString =new String[]{"¥������","¥������","¥���Ա�","¥������","��ʼ���","�Ƿ�0��","����Ա","��ע"};
			}
		}
		return outPutString;
	}
	
	public List<String[]> getLdglInfoList(LdglModel model) throws Exception{
		
		return dao.getLdglInfoList(model);
	}
	/**
	 * ����¥����Ϣ
	 * @param request
	 * @param model
	 * @return
	 */
	public boolean saveLdxx(HttpServletRequest request,LdglModel model){
		String qsh_ws=Base.chgNull(request.getParameter("qsh_ws"), "3", 0);//���Һ�λ��
		String qsh_sfhszm=Base.chgNull(request.getParameter("qsh_sfhszm"), "��", 0);//���Һ��Ƿ�����ĸ
		String qsh_szm=Base.chgNull(request.getParameter("qsh_szm"), "", 0);//���Һ�����ĸ
		String qsh_ws_zero="";//���λ�����㣬�����Һ�λ������
		
		
		String[] ch=request.getParameterValues("ch");//���
		String[] scqss=request.getParameterValues("scqss");//����������
		String[] cws=request.getParameterValues("cws");//��λ��
		String[] sfbz=request.getParameterValues("sfbz");//�շѱ�׼
//		String[] qsxb=request.getParameterValues("qsxb");//�����Ա�
		ArrayList<String> sqls=new ArrayList<String>();
		//¥��������Ϣ
		sqls.add("insert into xg_gygl_new_ldxxb values('"+
				model.getLddm()+"','"+model.getLdmc()+"','"+model.getLdxb()+"','"+
				model.getLdcs()+"','"+model.getQsch()+"','"+model.getSfhlc()+"','"+
				model.getXqdm()+"','"+model.getYqdm()+"','"+model.getBz()+"')");
		
		String qsh=null;//���Һ�
		String qsxbOne=null;//�����Ա�
		int ch_num=0;//���
		int t=ch.length;
		for(int i=0;i<ch.length;i++){
			t=t-1;
			//�������Һ�λ������
			//����4λ����10�㱨������ by yyp
			if(/*Integer.parseInt(ch[i])<9&&*/"4".equals(qsh_ws)){
				qsh_ws_zero="0";
			}else{
				qsh_ws_zero="";
			}
			for(int j=0;j<Integer.parseInt(scqss[i]);j++){
				//�������Һ�
				ch_num=Integer.parseInt(ch[i]);
				if(ch_num<0){
					if(j<9){
						qsh="B"+(-ch_num)+qsh_ws_zero+"0"+(j+1);
					}else{
						qsh="B"+(-ch_num)+qsh_ws_zero+""+(j+1);
					}
				}else{
					if("12301".equals(Base.xxdm)){
						if (j <= 9) {
							qsh = (ch_num) + qsh_ws_zero + "0" + (j);
						} else {
							qsh = (ch_num) + qsh_ws_zero + "" + (j);
						}
					}else{
						if (j < 9) {
							qsh = (ch_num) + qsh_ws_zero + "0" + (j + 1);
						} else {
							qsh = (ch_num) + qsh_ws_zero + "" + (j + 1);
						}
					}
				}
				if("��".equals(qsh_sfhszm)&&qsh_szm!=null&&qsh_szm.trim().length()>0){
					qsh=qsh_szm.trim()+qsh;
				}
				//���������Ա�
				if("��ס".equals(model.getLdxb())){
					qsxbOne=request.getParameter("qsxb"+t);//qsxb[i];
				}else{
					qsxbOne=model.getLdxb();
				}
				//����������Ϣ
				sqls.add("insert into xg_gygl_new_qsxxb(lddm,qsh,ch,qsxb,cws,sfbz) values('"+
						model.getLddm()+"','"+qsh+"','"+ch[i]+"','"+qsxbOne+"','"+cws[i]+"','"+sfbz[i]+"')");
				
				//���봲λ��Ϣ
				for(int k=1;k<=Integer.parseInt(cws[i]);k++){
					sqls.add("insert into xg_gygl_new_cwxxb(lddm,qsh,cwh) values('"+model.getLddm()
							+"','"+qsh+"','"+k+"')");
				}
				
			}
		}
		return dao.saveLdxx(request, model,sqls.toArray(new String[]{}));
	}
	/**
	 * ��ȡ����¥����Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getLdxxOne(LdglModel model){
		return dao.getLdxxOne(model);
	}
	/**
	 * ��ȡ¥��ͳ����Ϣ
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getLdtjxx(LdglModel model){
		return dao.getLdtjxx(model);
	}
	/**
	 * �޸�¥����Ϣ
	 * @param request
	 * @param model
	 * @return
	 */
	public boolean updateLdxx(HttpServletRequest request,LdglModel model){
		String newldmark=request.getParameter("newldmark");
		String[] ch=request.getParameterValues("ch");//���
		List<String> chList = new ArrayList<String>();
		ArrayList<String> sqls=new ArrayList<String>();
		//�޸�¥��������Ϣ
		
		String qsxbOne=null;//�����Ա�
		if("yes".equals(newldmark)){//��������¥�����Ҵ�λ��Ϣ

			String qsh_ws=Base.chgNull(request.getParameter("qsh_ws"), "3", 0);//���Һ�λ��
			String qsh_sfhszm=Base.chgNull(request.getParameter("qsh_sfhszm"), "��", 0);//���Һ��Ƿ�����ĸ
			String qsh_szm=Base.chgNull(request.getParameter("qsh_szm"), "", 0);//���Һ�����ĸ
			String qsh_ws_zero="";//���λ�����㣬�����Һ�λ������
			
			sqls.add("update xg_gygl_new_ldxxb set ldmc='"+model.getLdmc()+"',ldxb='"+model.getLdxb()+"'," +
					"bz='"+model.getBz()+"',ldcs='"+model.getLdcs()+"',qsch='"+model.getQsch()+"'," +
					"sfhlc='"+model.getSfhlc()+"' where lddm='"+model.getLddm()+"'");

			List<String> paramList = new ArrayList<String>();
			paramList.add(model.getLddm());
			String countSql = "select count(1) num from view_xg_gygl_new_cwxx where lddm=? and (qsxydm is not null or " +
					" qsnj is not null or xydm is not null or nj is not null or xh is not null)";

			//�����Ӳ�������
			if("1".equals(model.getSfkzjlc())){
				countSql += " and to_number(ch) > ?";
				paramList.add(model.getOriginalLdcs());

				for(String c:ch){
					if(Integer.parseInt(c)>Integer.parseInt(model.getOriginalLdcs())){
						chList.add(c);
					}
				}
				ch = chList.toArray(new String[]{});
			}

			String num=dao.getOneRs(countSql, paramList.toArray(new String[]{}), "num");

			if(!"0".equals(num)){//���ݳ��쳣�������ɾ���޸�
				return false;
			}

			//������¥��ʱ����ɾ��
			if(!"1".equals(model.getSfkzjlc())){
				String delCwSql = "delete from xg_gygl_new_cwxxb where lddm='"+model.getLddm()+"'";
				String delQsSql = "delete from xg_gygl_new_qsxxb where lddm='"+model.getLddm()+"'";

				sqls.add(delCwSql);
				sqls.add(delQsSql);
			}

			String[] scqss=request.getParameterValues("scqss");//����������
			String[] cws=request.getParameterValues("cws");//��λ��
			String[] sfbz=request.getParameterValues("sfbz");//�շѱ�׼
			
			String qsh=null;//���Һ�
			int ch_num=0;//���
			for(int i=0;i<ch.length;i++){
				//�������Һ�λ������
				if("4".equals(qsh_ws)){
					qsh_ws_zero="0";
				}else{
					qsh_ws_zero="";
				}				
				for(int j=0;j<Integer.parseInt(scqss[i]);j++){
					//�������Һ�
					ch_num=Integer.parseInt(ch[i]);
					if(ch_num<0){
						if(j<9){
							qsh="B"+(-ch_num)+qsh_ws_zero+"0"+(j+1);
						}else{
							qsh="B"+(-ch_num)+qsh_ws_zero+""+(j+1);
						}
					}else{
						if("12301".equals(Base.xxdm)){
							if (j <= 9) {
								qsh = (ch_num) + qsh_ws_zero + "0" + (j);
							} else {
								qsh = (ch_num) + qsh_ws_zero + "" + (j);
							}
						}else{
							if (j < 9) {

								qsh = (ch_num) + qsh_ws_zero + "0" + (j + 1);
							} else {
								qsh = (ch_num) + qsh_ws_zero + "" + (j + 1);
							}
						}
						
					}
					if("��".equals(qsh_sfhszm)&&qsh_szm!=null&&qsh_szm.trim().length()>0){
						qsh=qsh_szm.trim()+qsh;
					}
					//���������Ա�
					if("��ס".equals(model.getLdxb())){
						qsxbOne=request.getParameter("qsxb"+i);//qsxb[i];
					}else{
						qsxbOne=model.getLdxb();
					}

					//����������Ϣ
					sqls.add("insert into xg_gygl_new_qsxxb(lddm,qsh,ch,qsxb,cws,sfbz) values('"+
							model.getLddm()+"','"+qsh+"','"+ch[i]+"','"+qsxbOne+"','"+cws[i]+"','"+sfbz[i]+"')");

					//���봲λ��Ϣ
					for(int k=1;k<=Integer.parseInt(cws[i]);k++){
						sqls.add("insert into xg_gygl_new_cwxxb(lddm,qsh,cwh) values('"+model.getLddm()
								+"','"+qsh+"','"+k+"')");
					}

				}
			}
		}else{
			sqls.add("update xg_gygl_new_ldxxb set ldmc='"+model.getLdmc()+"',ldxb='"+model.getLdxb()+"'," +
					"bz='"+model.getBz()+"' where lddm='"+model.getLddm()+"'");
			if(ch!=null&&ch.length>0){//����Ų�Ϊ�գ���˵��¥�������Һʹ�λ����������
				for(int i=0;ch!=null&&i<ch.length;i++){
					//���������Ա�
					if("��ס".equals(model.getLdxb())){
						qsxbOne=request.getParameter("qsxb_"+ch[i]+"_��");
						if(!Base.isNull(qsxbOne)){
							sqls.add("update xg_gygl_new_qsxxb set qsxb='"+qsxbOne+"' where qsxb='��' and ch='"+ch[i]+"' and lddm='"+model.getLddm()+"'");
						}
						
						qsxbOne=request.getParameter("qsxb_"+ch[i]+"_Ů");
						if(!Base.isNull(qsxbOne)){
							sqls.add("update xg_gygl_new_qsxxb set qsxb='"+qsxbOne+"' where qsxb='Ů' and ch='"+ch[i]+"' and lddm='"+model.getLddm()+"'");
						}
					}else{
						qsxbOne=model.getLdxb();
						sqls.add("update xg_gygl_new_qsxxb set qsxb='"+qsxbOne+"' where lddm='"+model.getLddm()+"'");
						break;
					}
				}
			}
		}
		return dao.updateLdxx(request, model,sqls.toArray(new String[]{}));
	}
	/**
	 * ɾ��¥����Ϣ
	 * @param request
	 * @param model
	 * @return
	 */
	public boolean deleteLdxx(HttpServletRequest request,LdglModel model){
		return dao.deleteLdxx(request, model);
	}
	
	/**
	 * ��ѯ�û�
	 * @param form
	 * @return
	 */
	public List<String[]> queryYh(LdglForm form){
		return dao.queryYh(form);
	}
	
	public boolean saveFdyFp(String[] yhs,String[] lds){
		List<String[]> params = new ArrayList<String[]>(); 
		List<String[]> delParams = new ArrayList<String[]>();
		
		for(int i=0; i<lds.length; i++){
			delParams.add(new String[]{lds[i]});
			for(int j=0; j<yhs.length; j++){
				params.add(new String[]{yhs[j],lds[i]});
			}
		}
		
		return dao.saveFdyFp(params, delParams);
	}
	public boolean saveFdyFp_12861(String[] yhs,String[] lds){
		List<String[]> params = new ArrayList<String[]>(); 
		List<String[]> delParams = new ArrayList<String[]>();
		
		for(int i=0; i<lds.length; i++){
			delParams.add(new String[]{lds[i]});
			for(int j=0; j<yhs.length; j++){
				params.add(new String[]{yhs[j],lds[i]});
			}
		}
		
		return dao.saveFdyFp_12861(params, delParams);
	}
	
	/**
	 * ��ȡ¥�������б� �ô�����Ϣ�б��Ǿ��������
	 * @param form
	 * @return
	 */
	public List<List<HashMap<String,String>>> getLdqsxxList(LdglForm form){
		List<List<HashMap<String,String>>> ld_list=new ArrayList<List<HashMap<String,String>>>();
		List<HashMap<String,String>> qsxxList=dao.getLdqsxxList(form);
		if(qsxxList==null||qsxxList.size()==0){
			return ld_list;
		}
		String curr_ch=qsxxList.get(0).get("ch");//��ǰ���
		List<HashMap<String,String>> ch_list=new ArrayList<HashMap<String,String>>();//��ż���
		for(int i=0;i<qsxxList.size();i++){
			if(!curr_ch.equals(qsxxList.get(i).get("ch"))){//�жϵ�ǰ�����������
				ld_list.add(ch_list);
				ch_list=new ArrayList<HashMap<String,String>>();
				curr_ch=qsxxList.get(i).get("ch");
			}
			ch_list.add(qsxxList.get(i));
			if(i==qsxxList.size()-1){//��������һ���Լ���ӽ�ȥ
				ld_list.add(ch_list);
			}
		}
		
		return ld_list;
	}
	
	/**
	 * ��ȡ¥���в�������������
	 * @param form
	 * @return
	 */
	public String getChMaxQss(LdglForm form){
		return dao.getChMaxQss(form);
	}
	
	/**
	 * 
	 * @param request
	 * @param form
	 * @return
	 */
	public String saveLdQsxx(HttpServletRequest request,LdglModel form){
		//������֤��¥���Ƿ���޸�
		String lddm=form.getLddm();//¥������
		String[] qsh_old_s=request.getParameterValues("qsh_old_s");//�����Һ�
		String[] qsh_new_s=request.getParameterValues("qsh_new_s");//�����Һ�
		String[] cws_s=request.getParameterValues("cws_s");//��λ��
		String[] xb_s=request.getParameterValues("xb_s");//�Ա�
		
		if(qsh_old_s==null||qsh_new_s==null||cws_s==null||qsh_old_s.length==0||
				qsh_old_s.length!=qsh_new_s.length||qsh_old_s.length!=cws_s.length){
			return "¥��������������쳣��";
		}
		String ldxb=getLdxxOne(form).get("ldxb");
		List<String> sql_s=new ArrayList<String>();
		for(int i=0;i<qsh_old_s.length;i++){
			//ɾ���ɵĴ�λ
			sql_s.add("delete from xg_gygl_new_cwxxb where lddm='"+lddm+"' and qsh='"+qsh_old_s[i]+"'");
			//�����µĴ�λ
			for(int j=0;j<Integer.parseInt(cws_s[i]);j++){
				sql_s.add("insert into xg_gygl_new_cwxxb(lddm,qsh,cwh) values('"+lddm+"','"+qsh_new_s[i]+"','"+(j+1)+"')");
			}
			//����������Ϣ
			if("��ס".equals(ldxb)){
				sql_s.add("update xg_gygl_new_qsxxb set qsh='"+qsh_new_s[i]+"',cws='"+cws_s[i]+"',qsxb='"+xb_s[i]+"' where lddm='"+lddm+"' and qsh='"+qsh_old_s[i]+"'");
			}else{
				sql_s.add("update xg_gygl_new_qsxxb set qsh='"+qsh_new_s[i]+"',cws='"+cws_s[i]+"' where lddm='"+lddm+"' and qsh='"+qsh_old_s[i]+"'");
			}
		}
		boolean b=dao.saveLdQsxx(sql_s.toArray(new String[]{}));
		return "";
	}
	
	/**
	 * ����¥��������Ϣ�Ƿ�����޸�
	 * @param form
	 * @return
	 */
	public boolean checkLdQsxxSfkxg(LdglForm form){
		boolean b=dao.checkLdQsxxSfkxg(form);
		return b;
	}
	
	/**
	 * ¥����Ϣ�����Զ��嵼��
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getLdglInfoExportList(LdglModel model) throws Exception{
		
		return dao.getLdglInfoExportList(model);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-24 ����09:54:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getFpryList(User user,LdglForm ldglform) throws Exception{
		LdglDaoFp daoFp = new LdglDaoFp();
		return daoFp.getFpryList(user, ldglform);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-25 ����02:48:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveLdfp(LdglForm ldglform) throws Exception{
		String[] lddmArray = ldglform.getCheckVal();
		String[] yhms = ldglform.getYhms();
		if(lddmArray == null || yhms == null ){
			return false;
		}
		return dao.checkBatch(dao.saveLdfp(lddmArray, yhms));
	}
	
	/**
	 * 
	 * @����: ȡ������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-25 ����04:27:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ldglform
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelLdfp(LdglForm ldglform) throws Exception{
		String lddm = ldglform.getLddm();
		String[] yhms = ldglform.getYhms();
		if(lddm == null || yhms == null ){
			return false;
		}
		return dao.cancelFp(lddm, yhms);
	}
}
