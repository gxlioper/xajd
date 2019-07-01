/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-10-27 ����03:15:13</p>
 */
package xgxt.xsgygl.nblgxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;

public class GyglNblgxyDAO {
	/**
	 * @return ����������˲�ѯ�ı�ͷ
	 */
	public ArrayList<HashMap<String, String>> getwmQsShTitle(String userType) {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		colList = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","xxsh"};//��ѯ��ʾ�ֶ� 
		colListCN = new String[]{ "����", "����ɫ", "ѧ��", "ѧ��", "¥��", "�����",  "����ʱ��","ѧУ���"};
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	/**����ѧԺ��˲�ѯ���*/
	public ArrayList<String[]> getwmQsSbXxShSearch(GyglNblgxyWmqsModel mgawModel) {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String xn     = mgawModel.getXn();//ѧ��
		String xq     = mgawModel.getXq();//ѧ��
		String lddm   = mgawModel.getLddm();//¥������
		String qsh    = mgawModel.getQsh();//���Һ�
		String pysj   = mgawModel.getPysj();//����ʱ��
		String yesNo  = mgawModel.getYesNo();//��˱�־
		String sql    =  "";
		String[] colList = null;
		StringBuffer querry = new StringBuffer();
		//��ѯ����
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(qsh)?"":" and qsh='"+qsh+"' ");
		querry.append(Base.isNull(pysj)?"":" and pysj='"+pysj+"' ");
		querry.append(Base.isNull(yesNo)?"":" and xxsh='"+yesNo+"' ");
		//querry.append(" and xysh='ͨ��'");
		querry.append(" order by pysj asc ");
		colList = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","xxsh"};//��ѯ��ʾ�ֶ� 
		sql     = " select xn||xq||ssbh prkey,(case when xxsh='ͨ��' then '#FFFFFF' else '#CCCCCC' end )bgcolor,"
			+"  xn,xq,ldmc,qsh,pysj,xysh,xxsh from view_wmqssbxx ";  
//		ִ�в�ѯ
		rs = dao.rsToVator(sql + querry.toString(), new String[]{},colList);
		return rs;
	}
	public HashMap<String,String> wmSbOneInfo(String pkValue,String userType){
		DAO dao                    = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String pk                  = "xn||xq||ssbh";
		String[] colList           = {"xn","xq","pysj","ssbh","bz","xm","yesNo","lddm","ldmc","qsh","lbmc","qslb"};
		String sql                 = "";
		sql = "select xn,xq,pysj,ssbh,bz,xm,xxsh yesNo,lddm,ldmc,qsh,lbmc,qslb from view_wmqssbxx where "+pk+"=? ";
		map = dao.getMap(sql,new String[]{pkValue},colList);
		return map;	
	}
	/**���ظ�����ѧ��Υ�ʹ�����Ϣ*/
	public HashMap<String,String>wmQsWjInfo(String pkValue){
		DAO dao       = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String pk     = "xn||xq||ssbh";
		String sql    = "select xn,xq,ssbh from wmqssbb where "+pk+" =?";
		String[] temA = dao.getOneRs(sql,new String[]{pkValue},new String[]{"xn","xq","ssbh"});
		if(temA != null){
			//��ѧ��ѧ�ڸ�������Υ�ͼ�¼��������
			sql = " select count(a.xh) wjcfrs from( select distinct(a.xh),b.ssbh from xszsxxb a,wmqssbb b where a.ssbh=b.ssbh  "
				+ " ) a,view_wjcf b where a.xh=b.xh and b.xn||b.xq||a.ssbh=?  ";
			String wjrs = dao.getOneRs(sql,new String[]{pkValue},"wjcfrs");
			map.put("wjrs",wjrs);
			//������Ŀǰ��ס����
			sql = " select count(*) rzrs from xszsxxb where ssbh=? ";
			String rzrs = dao.getOneRs(sql,new String[]{temA[2]},"rzrs");
			map.put("rzrs",rzrs);
		}
		return map;
	}
	/**���ر�ѧ�����������ұ������������Ϣ*/
	public HashMap<String,String> wmQsBlInf(){
		DAO dao    = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String sql = "select (select count(*) from view_wmqspbxx d where d.xn=b.xn)ybqss,qszs ,wmqsbz||'%'  bz ,xn,"
			+" to_char(qszs*wmqsbz/100,'99999999999999') yxqss from (select count(*) qszs from ssxxb ) a,gygl_csszb b where xn =? and xq=?";
		map = dao.getMap(sql, new String[]{Base.currXn,Base.currXq},new String[]{"xn","ybqss","qszs","bz","yxqss"});

		if(map.size()!=0){
			if(Integer.parseInt(map.get("ybqss").trim())-Integer.parseInt(map.get("yxqss").trim())>=0){
				map.put("sxbz","1");//"���ޱ�־",�������������Ѵﵽ�򳬹�����
			}else{
				map.put("sxbz","0");//������������δ�ﵽ����
			}
			map.put("Set","yes");
		}else{
			map.put("Set","no");
		}
		return map;
	}
	/**���������걨ѧУ���*/
	public boolean wmqsXxShSave(String pkValue,String yesNo) throws Exception{
		DAO dao       = DAO.getInstance();
		boolean done = false;
		String pk     = "xn||xq||ssbh";
		String sql    = " update wmqssbb set xxsh=? where "+pk+"=? ";
		done =  dao.runUpdate(sql,new String[]{yesNo,pkValue});
		if(done){//���ͨ��ʱ����Ϣ���浽����������Ϣά����
			if(yesNo.equalsIgnoreCase("ͨ��")){
				done = dao.runUpdate("delete from wmqspbb where "+pk+"=?",new String[]{pkValue});
				if(done){
					sql = "insert into wmqspbb(xn,xq,pysj,ssbh,bz,qslb) select xn,xq,pysj,ssbh,bz,qslb from wmqssbb where "+pk+"=?";
					done = dao.runUpdate(sql, new String[]{pkValue});
				}
			}else {//
				done = dao.runUpdate("delete from wmqspbb where "+pk+"=?",new String[]{pkValue});
			}
		}	
		return done;
	}
	/**����Ա�±���д����
	 * @throws Exception */
	public boolean  dao_ybAdd(GyglNblgxyYbModel model,String userName) throws Exception{
		DAO             dao = DAO.getInstance();
		String xm           = DealString.toGBK(model.getXm());
		String nf           = DealString.toGBK(model.getNf());
		String yf           = DealString.toGBK(model.getYf());
		String gzksrq       = DealString.toGBK(model.getGzksrq());
		String gzjsrq       = DealString.toGBK(model.getGzjsrq());
		String rzqsh        = DealString.toGBK(model.getRzqsh());
		String fzld         = DealString.toGBK(model.getFzld());
		String gznr_jyfk    = DealString.toGBK(model.getGznr_jyfk());
		String jgznr_jyjl   = DealString.toGBK(model.getJgznr_jyjl());
		String gznr_jhynr   = DealString.toGBK(model.getGznr_jhynr());
		String gznr_jxsdt   = DealString.toGBK(model.getGznr_jxsdt());
		String gznr_jqtnr   = DealString.toGBK(model.getGznr_jqtnr());
		String fkyj_xyfk    = DealString.toGBK(model.getFkyj_xyfk());
		String fkyj_xgbfk   = DealString.toGBK(model.getFkyj_xgbfk());
		String fkyj_fwzxfk  = DealString.toGBK(model.getFkyj_fwzxfk());
		String bz           = DealString.toGBK(model.getBz());
		StringBuffer sql    = new StringBuffer();
		sql.append(" insert into gyfdyybb(nf,yf,dlm,gzksrq,gzjsrq,xm,fzld,rzqsh,gznr_jyfk,jgznr_jyjl,gznr_jhynr,gznr_jxsdt");
		sql.append(" ,gznr_jqtnr,fkyj_xyfk,fkyj_xgbfk,fkyj_fwzxfk,bz) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ") ;
		boolean done = dao.runUpdate(sql.toString(),new String[]{nf,yf,userName,gzksrq,gzjsrq,xm,fzld,
			rzqsh,gznr_jyfk,jgznr_jyjl,gznr_jhynr,gznr_jxsdt,gznr_jqtnr,fkyj_xyfk,fkyj_xgbfk,fkyj_fwzxfk,bz});
		return done;
	}
	public ArrayList<HashMap<String, String>> getybMTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		colList = new String[]{"prkey", "nf","yf","xm","fzld","gznr"};//��ѯ��ʾ�ֶ� 		
		colListCN = new String[]{ "����","���","�·�","����Ա","����¥��","�����������",};
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	/**���������걨��ѯ�����û���ѯ���*/
	public ArrayList<String[]>  getybMResult(String nf,String yf,String userName,String userType ){
		ArrayList<String[]> rs = new ArrayList<String[]>();
		DAO dao = DAO.getInstance();

		String sql    =  "";
		String[] colList = null;
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		//��ѯ����
		if(userType.equalsIgnoreCase("xy")){ //ѧԺ���û�
			querry.append(" and dlm='"+userName+"' ");
		} 			
		querry.append(Base.isNull(nf)?"":" and nf='"+nf+"' ");
		querry.append(Base.isNull(yf)?"":" and yf='"+yf+"' ");
		querry.append(" order by gzksrq desc ");
		colList = new String[]{"prkey", "nf","yf","xm","fzld","gznr"};//��ѯ��ʾ�ֶ� 
		sql     = " select nf||yf||dlm prkey,nf,yf,xm,fzld,(case when gznr_jyfk is null then '' else substr(gznr_jyfk,1,15)||'...' end)gznr from gyfdyybb";  
//		ִ�в�ѯ
		rs = dao.rsToVator(sql + querry.toString(), new String[]{},colList);
		return rs;
	}
	public HashMap<String,String>getYbInfo(String pkValue){
		DAO dao       = DAO.getInstance();
		String pk     = "nf||yf||dlm";
		String sql    = " select nf,yf,dlm,gzksrq,gzjsrq,xm,fzld,rzqsh,gznr_jyfk,jgznr_jyjl,gznr_jhynr,gznr_jxsdt,gznr_jqtnr,fkyj_xyfk,fkyj_xgbfk,fkyj_fwzxfk,bz from gyfdyybb where "+pk+"=?";
		return dao.getMap(sql,new String[]{pkValue},new String[]{"nf","yf","dlm","gzksrq","gzjsrq","xm","fzld","rzqsh","gznr_jyfk","jgznr_jyjl","gznr_jhynr","gznr_jxsdt","gznr_jqtnr","fkyj_xyfk","fkyj_xgbfk","fkyj_fwzxfk","bz"});
	}
	/**����Ա�±��޸ı���
	 * @throws Exception */
	public boolean  dao_ybModi(GyglNblgxyYbModel model,String pkValue) throws Exception{
		DAO             dao = DAO.getInstance();
		String pk     = "nf||yf||dlm";
		String gzksrq       = DealString.toGBK(model.getGzksrq());
		String gzjsrq       = DealString.toGBK(model.getGzjsrq());
		String rzqsh        = DealString.toGBK(model.getRzqsh());
		String fzld         = DealString.toGBK(model.getFzld());
		String gznr_jyfk    = DealString.toGBK(model.getGznr_jyfk());
		String jgznr_jyjl   = DealString.toGBK(model.getJgznr_jyjl());
		String gznr_jhynr   = DealString.toGBK(model.getGznr_jhynr());
		String gznr_jxsdt   = DealString.toGBK(model.getGznr_jxsdt());
		String gznr_jqtnr   = DealString.toGBK(model.getGznr_jqtnr());
		String fkyj_xyfk    = DealString.toGBK(model.getFkyj_xyfk());
		String fkyj_xgbfk   = DealString.toGBK(model.getFkyj_xgbfk());
		String fkyj_fwzxfk  = DealString.toGBK(model.getFkyj_fwzxfk());
		String bz           = DealString.toGBK(model.getBz());
		StringBuffer sql    = new StringBuffer();
		sql.append(" update gyfdyybb set gzksrq=?,gzjsrq=?,fzld=?,rzqsh=?,gznr_jyfk=?,jgznr_jyjl=?,gznr_jhynr=?,gznr_jxsdt=?");
		sql.append(" ,gznr_jqtnr=?,fkyj_xyfk=?,fkyj_xgbfk=?,fkyj_fwzxfk=?,bz=? where "+pk+"=?") ;
		boolean done = dao.runUpdate(sql.toString(),new String[]{gzksrq,gzjsrq,fzld,rzqsh,gznr_jyfk,jgznr_jyjl,
			gznr_jhynr,gznr_jxsdt,gznr_jqtnr,fkyj_xyfk,fkyj_xgbfk,fkyj_fwzxfk,bz,pkValue});
		return done;
	}
	public boolean dao_ybDel(String pkValue) throws Exception{
		DAO             dao = DAO.getInstance();
		String sql          = "delete from gyfdyybb where nf||yf||dlm=? ";
		return dao.runUpdate(sql, new String[]{pkValue});
	}
	/**����Ա�±���д����
	 * @throws Exception */
	public boolean  dao_zrrAddSave(GyglFzqLxrModel model) throws Exception{
		DAO             dao = DAO.getInstance();
		String xh           = DealString.toGBK(model.getXh());
		String bz           = model.getBz();
		String dzyx           = model.getDzyx();
		String fzssbh       = model.getFzssbh();
		String lxfs       = model.getLxfs();
		String rq        = model.getRq();
		String sfzz         = model.getSfzz();
		String ssbhcn   = model.getSsbhcn();
		if(Base.isNull(sfzz)){
			sfzz = "��";
		}
		boolean done=false;
		String sql    = "insert into zrqfzrxxb(xh,fzssbh,lxfs,ssbhcn,dzyx,rq,bz,sfzz)values(?,?,?,?,?,?,?,?)";
		done = dao.runUpdate(sql, new String[]{xh,fzssbh,lxfs,ssbhcn,dzyx,rq,bz,sfzz});
		return done;
	}
	public ArrayList<HashMap<String, String>> dao_getZrrTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		colList = new String[]{"prkey", "xh","xm","rq","sfzz","ssbhcn"};//��ѯ��ʾ�ֶ� 
		colListCN = new String[]{ "����","ѧ��","����","��������","�Ƿ���ְ","������"};
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	public ArrayList<String[]> dao_getZrrResult(GyglFzqLxrModel model) {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String sql    =  "";
		String[] colList = null;
		StringBuffer querry = new StringBuffer();
		//��ѯ����		
		String xh = model.getXh().trim();
		String xm   = model.getXm().trim();
		String lddm = DealString.toGBK(model.getLddm());
		String sfzz =model.getSfzz();
		String ssbh   = DealString.toGBK(model.getSsbh());
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(lddm)?"":" and ssbhcn like '%'||(select ldmc from sslddmb where lddm='"+lddm+"')||'%' ");
		querry.append(Base.isNull(ssbh)?"":" and fzssbh like  '%"+ssbh+"%' ");	
		querry.append(Base.isNull(xh)?"":" and xh = '"+xh+"'");	
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm+"%' ");
		querry.append(Base.isNull(sfzz)?"":" and sfzz = '"+sfzz+"' ");

		colList = new String[]{"prkey", "xh","xm","rq","sfzz","ssbhcn"};//��ѯ��ʾ�ֶ�  
		sql     = " select pk prkey,xh,xm,rq,sfzz,(case when ssbhcn is null then '' else substr(ssbhcn,1,20)||'...' end) ssbhcn from view_zrqfzrxx ";  
//		ִ�в�ѯ
		rs = dao.rsToVator(sql + querry.toString(), new String[]{},colList);
		return rs;
	}
	public boolean dao_zrrDel(String pkValue) throws Exception{
		DAO             dao = DAO.getInstance();
		String sql          = "delete from zrqfzrxxb where rowid=? ";
		return dao.runUpdate(sql, new String[]{pkValue});
	}
	public HashMap<String,String> zrrInfo(String pkValue){
		DAO dao                    = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String[] colList           = {"xh","xm","nj","xymc","zymc","bjmc","xb","fzssbh","lxfs","ssbhcn","dzyx","rq","bz","sfzz","lzrq"};
		String sql = "select xh,xm,nj,xymc,zymc,bjmc,xb,fzssbh,lxfs,ssbhcn,dzyx,rq,bz,sfzz,lzrq from view_zrqfzrxx where pk=? ";
		map = dao.getMap(sql,new String[]{pkValue},colList);
		return map;	
	}
	public boolean  dao_zrrModiSave(GyglFzqLxrModel model,String pkValue) throws Exception{
		DAO             dao = DAO.getInstance();
		String bz           = model.getBz();
		String dzyx           = model.getDzyx();
		String fzssbh       = model.getFzssbh();
		String lxfs       = model.getLxfs();
		String rq        = model.getRq();
		String sfzz         = model.getSfzz();
		String ssbhcn   = model.getSsbhcn();
		String lzrq     = model.getLzrq();

		boolean done=false;
		String sql    = "update zrqfzrxxb set fzssbh=?,lxfs=?,ssbhcn=?,dzyx=?,rq=?,bz=?,sfzz=?,lzrq=? where rowid=?";
		done = dao.runUpdate(sql, new String[]{fzssbh,lxfs,ssbhcn,dzyx,rq,bz,sfzz,lzrq,pkValue});
		return done;
	}
	public boolean dao_tsxsAddSave(GyglNblgTsxsModel model) throws Exception{
		DAO dao = DAO.getInstance();
		String xh = model.getXh();
		String rq = model.getRq();
		String lxfs_ch = model.getLxfs_ch();
		String lxfs_dh = model.getLxfs_dh();
		String ahtc    = model.getAhtc();
		String jtzk    = model.getJtzk();
		String xfcj    = model.getXfcj();
		String hjry    = model.getHjry();
		String wjqk    = model.getWjqk();
		String xgtzxg  = model.getXgtzxg();
		String rjjwshhd = model.getRjjwshhd();
		String gzqk     = model.getGzqk();
		String ssbh    = model.getSsbh();
		String bz     = model.getBz();
		boolean done = false;
		String sql =" delete from gygl_tsxsxxb where xh||rq=? ";
		done = dao.runUpdate(sql,new String[]{xh+rq});
		if(done){
			sql = " insert into gygl_tsxsxxb(xh,rq,lxfs_ch,lxfs_dh,ahtc,jtzk,xfcj,hjry,wjqk,xgtzxg,rjjwshhd,gzqk,bz,ssbh) "
				+" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			done = dao.runUpdate(sql, new String[]{xh,rq,lxfs_ch,lxfs_dh,ahtc,jtzk,xfcj,hjry,wjqk,xgtzxg,rjjwshhd,gzqk,bz,ssbh});
		}
		return done;
	}
	public ArrayList<HashMap<String, String>> dao_getTsxsTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		colList = new String[]{"prkey", "xh","xm","xymc","bjmc","rq","mzmc","zzmmmc"};//��ѯ��ʾ�ֶ� 
		colListCN = new String[]{ "����","ѧ��","����",Base.YXPZXY_KEY+"����","�༶����","����","����","������ò"};
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	} 
	public ArrayList<String[]> dao_getTsxsResult(GyglNblgTsxsModel model) {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String sql    =  "";
		String pk     = "xh||rq";
		String[] colList = null;
		StringBuffer querry = new StringBuffer();
		//��ѯ����		
		String xh = model.getXh().trim();
		String xm   = model.getXm().trim();
		String lddm = DealString.toGBK(model.getLddm());
		String nj =model.getNj();
		String xydm   = model.getXy();
		String zydm   = model.getZy();
		String bjdm   = model.getBj();
		String ssbh   = model.getSsbh();
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(lddm)?"":" and  lddm = '"+lddm+"' ");
		querry.append(Base.isNull(ssbh)?"":" and  ssbh = '"+ssbh+"' ");	
		querry.append(Base.isNull(xh)?"":" and xh = '"+xh+"'");	
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm+"%' ");
		querry.append(Base.isNull(nj)?"":" and nj = '"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm = '"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm = '"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm = '"+bjdm+"' ");

		colList = new String[]{"prkey", "xh","xm","xymc","bjmc","rq","mzmc","zzmmmc"};//��ѯ��ʾ�ֶ� 
		sql     = " select "+pk+" prkey,xh,xm,xymc,bjmc,rq,mzmc,zzmmmc from view_tsxsxx ";  
//		ִ�в�ѯ
		rs = dao.rsToVator(sql + querry.toString(), new String[]{},colList);
		return rs;
	}
	public HashMap<String,String> tsxsInfo(String pkValue){
		DAO dao                    = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String[] colList           = {"xh","rq","lxfs_ch","lxfs_dh","ahtc","jtzk","xfcj","hjry","wjqk","xgtzxg","rjjwshhd","gzqk","bz","ssbh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","qsdh","lddm","ssmc","zzmmmc","mzmc"};
		String sql = "select * from view_tsxsxx where xh||rq=? ";
		map = dao.getMap(sql,new String[]{pkValue},colList);
		return map;	
	}
	public boolean dao_tsxsModiSave(GyglNblgTsxsModel model,String pkValue) throws Exception{
		DAO dao = DAO.getInstance();
		String lxfs_ch = model.getLxfs_ch();
		String lxfs_dh = model.getLxfs_dh();
		String ahtc    = model.getAhtc();
		String jtzk    = model.getJtzk();
		String xfcj    = model.getXfcj();
		String hjry    = model.getHjry();
		String wjqk    = model.getWjqk();
		String xgtzxg  = model.getXgtzxg();
		String rjjwshhd = model.getRjjwshhd();
		String gzqk     = model.getGzqk();
		String ssbh    = model.getSsbh();
		String bz     = model.getBz();
		boolean done = false;
		String sql = " update gygl_tsxsxxb set lxfs_ch=?,lxfs_dh=?,ahtc=?,jtzk=?,xfcj=?,hjry=?,wjqk=?,xgtzxg=?,rjjwshhd=?,gzqk=?,bz=?,ssbh=? where xh||rq=? ";				
		done = dao.runUpdate(sql, new String[]{lxfs_ch,lxfs_dh,ahtc,jtzk,xfcj,hjry,wjqk,xgtzxg,rjjwshhd,gzqk,bz,ssbh,pkValue});
		return done;
	}
	public boolean dao_tsxsDel(String pkValue) throws Exception{
		DAO             dao = DAO.getInstance();
		String sql          = "delete from gygl_tsxsxxb where xh||rq=? ";
		return dao.runUpdate(sql, new String[]{pkValue});
	}
	public boolean dao_getsjxzInfo() throws SQLException{
		DAO dao  = DAO.getInstance();
		Boolean rettag = false;
		String sql = "select * from gygl_sjszb where to_char(sysdate,'yyyymmddhh24miss') between kssj and jssj";
		String tag = dao.returntag(sql, new String[] {});
		if ("notempty".equalsIgnoreCase(tag)) {
			rettag = true;
		}
        return rettag;
	}
}
