
package xgxt.studentInfo.zzdx;

import java.util.List;

import xgxt.DAO.DAO;


/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ݴ�ѧ�߻�����Dao</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-26</p>
 */
public class XsxxZzdxDao {
	DAO dao = DAO.getInstance();
	
	/**
	 * ��ȡһ������
	 * @return sql
	 * */
	public int getOneCount(String sql){
		int iCount= 0;
		iCount = Integer.parseInt(dao.getOneRs(sql, new String[]{}, "count"));
		return iCount;
	}	
	
	/**
	 * Method getReportList ��ȡ�����б����� 
	 * @return list
	 */
	public List getReportList(){		
		List list = null;
		String sql = "";
		sql = "select distinct  bbbh, bbmc from gjbbxxb";
		list = dao.getList(sql, new String[]{}, new String[]{"bbbh", "bbmc"});
		return list;
	}
	
	/**
	 * Method getCountListOfGjbb222 ��ȡGjbb222����ĺ����ܼ���У���� 
	 * @return countList
	 */
	public List getLevCountListOfGjbb222(){
		String sql ="select count(a.xh) count,xlccmc from xlccdmb b left join  (" +
					"SELECT xh,pycc FROM bks_xsjbxx a WHERE sfzx='��У' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)"+
					" UNION "+ 
					"SELECT xh,pycc FROM xsxxb where sfzx='��У'" +
					") a on a.pycc=b.xlccmc group by xlccmc,xlccdm order by b.xlccdm";
		return dao.getList(sql, new String[]{}, new String[]{"count"});		 
	}
	
	/**
	 * Method getVerCountListOfGjbb222 ��ȡGjbb222����������ܼ���У���� 
	 * @return countList
	 */
	public List getVerCountListOfGjbb222(String condition){
		String sql ="select count(a.xh) count,sydq from dmk_sydq b left join ("+
				    "select xh,jg from ("+
				    "SELECT xh,jgm jg,pycc FROM bks_xsjbxx a WHERE sfzx='��У' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)"+
				    " UNION "+ 
				    "SELECT xh,jg, pycc FROM xsxxb where sfzx='��У'"+
				    ")"+ condition +
                    ") a on a.jg=b.sydq where sydqdm<710000 group by sydq,sydqdm order by sydqdm";
		return dao.getList(sql, new String[]{}, new String[]{"count"});		 
	}
	
	/**
	 * Method getSydListOfGjbb222 ��ȡGjbb222����ĵ������� 
	 * @return countList
	 */
	public List getSydListOfGjbb222(){
		String sql ="select distinct sydqdm, sydq from dmk_sydq where sydqdm<710000 order by sydqdm";
		return dao.getList(sql, new String[]{}, new String[]{"sydq"});		 
	}
	
	/**
	 * Method getGatListOfGjbb222 ��ȡGjbb222����ĸ۰�̨������У���� 
	 * @return countList
	 */
	public List getGatListOfGjbb222(){
		String sql ="select count(a.xh) count,xlccmc from xlccdmb b left join (" +
                    "select xh,jg,pycc from(" +
                    "SELECT xh,jgm jg,pycc FROM bks_xsjbxx a WHERE sfzx='��У' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)"+
                    " UNION " +  
                    "SELECT xh,jg, pycc FROM xsxxb where sfzx='��У'" + 
                    ") where jg='̨��ʡ' or jg='����ر�������' or jg='�����ر�������'" + 
                    ") a  on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";		
		return dao.getList(sql, new String[]{}, new String[]{"count"});		
	}
	
	/**
	 * Method getBzkGatListOfGjbb222 ��ȡGjbb222����ĸ�������ͨ����ר���������� 
	 * @return countList
	 */
	public List getBzkListOfGjbb222(){
		String sql ="select count(a.xh) count,sydq from dmk_sydq b left join " + 
                    "(select xh,lys,pycc from(" + 
                    "SELECT xh,lys,cc pycc FROM xsjbxxlsb a " + 
                    ")" + 
                    "where pycc='��ͨ����' or pycc='��ͨר��'" + 
                    ") a on a.lys=b.sydq where sydqdm<710000  group by sydq,sydqdm order by sydqdm";		
		return dao.getList(sql, new String[]{}, new String[]{"count"});		
	}
	
	/**
	 * Method getTotalStudentCount ��ȡ�ܵ���Уѧ����
	 * @return iTotal
	 */
	public String getTotalStudentCount(){
		String sql = "select count(xh) count from (select xh from(" +
				"SELECT xh FROM bks_xsjbxx a WHERE sfzx='��У' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" +
				" UNION " +
				"SELECT xh FROM xsxxb where sfzx='��У'" + 
				")) ";
		
		return dao.getOneRs(sql, new String[]{}, "count");		
	}
	
	/**
	 * Method getGatTotalStuCount ��ȡ�۰�̨�ܵ���Уѧ����
	 * @return iTotal
	 */
	public String getGatTotalStuCount(){
		String sql = "select count(*) count from (" + 
                     "SELECT xh,jgm jg FROM bks_xsjbxx a WHERE sfzx='��У' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" + 
                     " UNION " +  
                     "SELECT xh,jg FROM xsxxb WHERE sfzx='��У'" + 
                     ") where jg='̨��ʡ' or jg='����ر�������' or jg='�����ر�������'";		
		return dao.getOneRs(sql, new String[]{}, "count");		
	}	
	
	/**
	 * Method getBzkTotalStuCount ��ȡ�ܵ���ͨ��ר������ѧ����
	 * @return iTotal
	 */
	public String getBzkTotalStuCount(){
		String sql = "select count(a.xh)count from " +
                     "(select xh,pycc from(" + 
                     "SELECT xh,cc pycc FROM xsjbxxlsb" + 
                     ")" + 
                     "where pycc='��ͨ����' or pycc='��ͨר��'" + 
                     ") a";		
		return dao.getOneRs(sql, new String[]{}, "count");		
	}	
	
	/**
	 * Method getBzkGatTotal ��ȡ�۰�̨�ܵ���ͨ��ר��������
	 * @return iTotal
	 */
	public String getBzkGatTotal(){
		String sql = "select count(*) count from (" + 
                     "SELECT xh,lys FROM xsjbxxlsb a WHERE NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" + 
                     " UNION " +  
                     "SELECT xh,jg FROM xsxxb" + 
                     ") where lys='̨��ʡ' or lys='̨��' or lys='����ر�������' or lys='���'  or lys='�����ر�������' or lys='����'";		
		return  dao.getOneRs(sql, new String[]{}, "count");		
	}
	
	/**
	 * Method getOtherInfo ��ȡ��Уѧ�����������
	 * @param condition
	 * @return list
	 */
	public List getOtherInfo(String condition){
		String sql = "select count(a.xh) count,xlccmc from xlccdmb b left join(" + 
                     "select xh,zzmm,mz,pycc from(" + 
                     "SELECT a.xh,b.zzmmm zzmm,b.mzdm mz,a.pycc FROM bks_xsjbxx a left join bks_xsqtxx b on a.xh=b.xh WHERE a.sfzx='��У' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" + 
                     " UNION " +  
                     "SELECT xh,zzmm,mz,pycc FROM xsxxb where sfzx='��У'" + 
                     ") where 1=1" +  condition+
                     ")a on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});		
	}
	
	/**
	 * Method getStuAgeInfo ��ȡ��Уѧ���������
	 * @param condition
	 * @return list
	 */
	public List getStuAgeInfo(String condition){
		String sql = "select count(a.xh) count,xlccmc from xlccdmb b left join(" +
                     "select xh,csrq,pycc,xb,nl from (" + 
                     "select xh,csrq,pycc,xb,(to_number(dqrq)-to_number(csrq)) nl from (" + 
                     "select xh,to_number(nvl(csrq,'0')) csrq,pycc,xb,substr((select to_char(sysdate,'yyyy-dd-mm') from dual),0,4) dqrq from(" + 
                     "SELECT a.xh,substr(0,4,b.csrq) csrq,a.pycc,a.xbm xb FROM bks_xsjbxx a left join bks_xsqtxx b on a.xh=b.xh WHERE a.sfzx='��У' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" +
                     " UNION " +  
                     "SELECT xh,substr(csrq,0,4) csrq,pycc,xb FROM xsxxb where sfzx='��У'" + 
                     ")" + 
                     ")" + 
                     ")where 1=1 " + condition + 
                     ")a on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});		
	}
	
	/**
	 * ��ȡ��Уѧ���м�����
	 * @return List
	 * */
	public List getCjrInfo(){
		//��Уѧ���м��������ؼ�ѧԺѧ����
		String sql = "select count(a.xh) count,xlccmc from xlccdmb b left join( " +
                     "select xh,pycc,xydm from(" + 
                     "SELECT a.xh,pycc,bmdm xydm  FROM bks_xsjbxx a left join bks_xsqtxx b on a.xh=b.xh WHERE a.sfzx='��У'  and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" + 
                     " UNION " +    
                     "SELECT xh,pycc,xydm  FROM xsxxb where sfzx='��У'" + 
                     ") where xydm=(select distinct xydm from view_njxyzybj where xymc='�������ѧ�뼼��ѧԺ')" + 
                     ") a on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});		
	}
	
	/**
	 * ��ȡ��ͨ��ר��רҵ�����б�
	 * @return List
	 * */
	public List getZymcList(){
		String sql = "select distinct a.zydm,a.zymc,(select distinct xz from zydmb b where a.zydm=b.zydm) xz from view_njxyzybj a order by a.zydm";
		return dao.getList(sql, new String[]{}, new String[]{"zymc","zydm","xz"});		
	}
	
	/**
	 * ��ͨ��ר�Ʊ�ҵ������
	 * @return List
	 * */
	public List getBysCountByZy(){
		String sql = "select distinct count(a.xh) count,b.zydm from view_njxyzybj b left join(" + 
                     "select xh,zydm from(" + 
                     "SELECT a.xh,(select zydm from view_xsjbxx b where a.xh=b.xh ) zydm FROM bysfzxxb@dblink_jw a where byjr='��ҵ' " +     
                     ")" + 
                     ") a on a.zydm = b.zydm group by b.zydm order by b.zydm";
		return dao.getList(sql, new String[]{}, new String[]{"count"});		
	}	
	
	/**
	 * ��ͨ��ר������ѧλ��
	 * @return List
	 * */
	public List getXwCountList(){
		String sql = "select distinct count(a.xh) count,b.zydm from view_njxyzybj b left join(" + 
                     "select xh,zydm from(" + 
                     "SELECT a.xh,(select zydm from view_xsjbxx b where a.xh=b.xh ) zydm FROM bysfzxxb@dblink_jw a where xwzsh is not null " +     
                     ")" + 
                     ") a on a.zydm = b.zydm group by b.zydm order by b.zydm";
		return dao.getList(sql, new String[]{}, new String[]{"count"});		
	}
	
	
	/**
	 * ��רҵ������
	 * @param nd
	 * @return List
	 * */
	public List getZsList(String nd){
		String sql = "select distinct count(a.xh) count,b.zydm,max(b.zymc) from view_njxyzybj b left join(" + 
                     "select xh,zydm from(" + 
                     "SELECT a.xh,(select zydm from view_xsjbxx b where a.xh=b.xh ) zydm FROM xsjbxxlsb a where substr(zssj,0,4)='"+ nd +"'" + 
                     ")" + 
                     ") a on a.zydm = b.zydm group by b.zydm order by b.zydm";
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	/**
	 * Ӧ����������
	 * @param nd
	 * @return List
	 * */
	public List getYjsList(String nd){
		String sql = "select distinct count(a.xh) count,b.zydm,max(b.zymc) from view_njxyzybj b left join(" + 
			        "select xh,zydm from(" + 
			        "SELECT a.xh,(select zydm from view_xsjbxx b where a.xh=b.xh ) zydm FROM xsjbxxlsb a where substr(zssj,0,4)='"+ nd +"' and zslb='Ӧ����'" + 
			        ")" + 
			        ") a on a.zydm = b.zydm group by b.zydm order by b.zydm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	/**
	 * ����������
	 * @param nd
	 * @return List
	 * */	
	public List getCjzsList(String nd){
		String sql = "select distinct count(a.xh) count,b.zydm,max(b.zymc) from view_njxyzybj b left join(" + 
			        "select xh,zydm from(" + 
			        "SELECT a.xh,(select zydm from view_xsjbxx b where a.xh=b.xh ) zydm FROM xsjbxxlsb a where substr(zssj,0,4)='"+ nd +"' and zsjj like '%��%'" + 
			        ")" + 
			        ") a on a.zydm = b.zydm group by b.zydm order by b.zydm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	/**
	 * ��Уѧ����
	 * @param nd 
	 * @return List
	 * */
	public List getZxListByZy(String nd){
		String sql = "select b.zydm,count(distinct a.xh) count from view_njxyzybj b  left join (" +                      
                     "select distinct a.zydm,a.xh from (" + 
                     "SELECT a.xh,a.xbm xb,zydm FROM bks_xsjbxx a  WHERE a.sfzx='��У' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" + 
                     " UNION " +  
                     "SELECT xh,xb,zydm FROM xsxxb where sfzx='��У'" + 
                     ") a " +      
                     ") a on a.zydm = b.zydm group by b.zydm order by b.zydm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	/**
	 * ���꼶ѧ����
	 * @param condition
	 * @return List
	 * */
	public List getZxxsByGrade(String condition){
		String sql = "select b.zydm,count(distinct a.xh)count from view_njxyzybj b  left join (" +   
                     "select zydm,xh,xb,dqnj from ( " +       
                     "select zydm,xh,xb,(dqn-nj) dqnj from ( " +              
                     "select distinct a.zydm,a.xh,xb,nj,(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) dqn from dual)dqn from (" + 
                     "SELECT a.xh,a.xbm xb,zydm,to_char(nj)nj FROM bks_xsjbxx a  WHERE a.sfzx='��У' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" + 
                     " UNION " +  
                     "SELECT xh,xb,zydm,nj FROM xsxxb where sfzx='��У' " + 
                     ") a " +      
                     ")" + 
                     ")  where 1=1 " + condition + 
                     ") a on a.zydm = b.zydm  group by b.zydm order by b.zydm";
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	/**
	 * Ԥ�Ʊ�ҵ����
	 * */
	public List getYjbysList(){
		String sql = "select b.zydm,count(distinct a.xh)count from view_njxyzybj b  left join (" +   
                     "select zydm,xh,xb,nj,dqn,xz from ( " +              
                     "select distinct a.zydm,a.xh,xb,nj,xz,(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) dqn from dual)dqn from ( " + 
                     "SELECT a.xh,a.xbm xb,zydm,to_char(nj)nj,to_char(xz)xz FROM bks_xsjbxx a  WHERE a.sfzx='��У' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh) " + 
                     " UNION " +  
                     "SELECT xh,xb,zydm,nj,xz FROM xsxxb where sfzx='��У' " + 
                     ") a " +      
                     ") where (nj+xz) = dqn " + 
                     ") a on a.zydm = b.zydm  group by b.zydm order by b.zydm"; 
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	/**
	 * ��ѯ��ҵŮ������
	 * @return int
	 * */
	public int getByCountOfGirl(){		
		String sql = "select count(*) count from (select a.xh,(select b.xb from view_xsjbxx b where a.xh=b.xh)xb from bysfzxxb@dblink_jw a where byjr='��ҵ') where xb='Ů' or xb='2'";
		return this.getOneCount(sql);		
	}
	
	/**
	 * ����ѧλŮ������
	 * @return int
	 * */
	public int getTotalGirlOfXw(){		
		String sql = "select count(*) count from (select a.xh,(select b.xb from view_xsjbxx b where a.xh=b.xh)xb from bysfzxxb@dblink_jw a where xwzsh is not null) where xb='Ů' or xb='2'";
		return this.getOneCount(sql);		
	}
	
	/**
	 * ��Ů������
	 * @param nd
	 * @return int
	 * */
	public int getTotalGirlOfZs(String nd){
		String sql = "select count(*) count from xsjbxxlsb where xb='Ů' and substr(zssj,0,4)='"+ nd +"' ";
		return this.getOneCount(sql);
	}
	
	/**
	 * ����Ӧ������Ů������
	 * @param nd
	 * @return int
	 * */
	public int getTotalGirlOfZsyjs(String nd){
		String sql = "select count(*) count from xsjbxxlsb where xb='Ů' and zslb='Ӧ����' and substr(zssj,0,4)='"+ nd +"' ";
		return this.getOneCount(sql);
	}
	
	/**
	 * ������Ů������
	 * @param nd
	 * @return int
	 * */
	public int getTotalGirlOfCjzs(String nd){
		String sql = "select count(*) count from xsjbxxlsb where xb='Ů' and (zsjj='��' or zsjj='����') and substr(zssj,0,4)='"+ nd +"' ";
		return this.getOneCount(sql);
	}
	
	/**
	 * ��Уѧ��Ů������
	 * @param nd
	 * @return int
	 * */
	public int getTotalGirlOfZx(){
		String sql = "select max(xb),count(distinct a.xh) count from (" + 
                     "SELECT a.xh,a.xbm xb FROM bks_xsjbxx a  WHERE a.sfzx='��У' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh) " + 
                     " UNION " +  
                     "SELECT xh,xb FROM xsxxb where sfzx='��У' " + 
                     ") a  where xb='Ů' or xb='2'  ";
		return this.getOneCount(sql);
	}
	
	/**
	 * ���꼶Ů����
	 * @param condition
	 * @return int
	 * */
	public int getTotalGrilByGrade(String condition){
		String sql = "select count(xh)count from (" + 
                     "select xh,xb,dqnj from (" +    
                     "select zydm,xh,xb,(dqn-nj) dqnj from ( " +              
                     "select distinct a.zydm,a.xh,xb,nj,(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) dqn from dual)dqn from ( " + 
                     "SELECT a.xh,a.xbm xb,zydm,to_char(nj)nj FROM bks_xsjbxx a  WHERE a.sfzx='��У' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh) " + 
                     " UNION " +  
                     "SELECT xh,xb,zydm,nj FROM xsxxb where sfzx='��У' " + 
                     ") a " +      
                     ")" + 
                     ")  where 1=1 and xb='Ů'" + condition +  
                     ")";
		return this.getOneCount(sql);
	}
	
	/**
	 * Ԥ�Ʊ�ҵŮ����
	 * @return int
	 * */
	public int getGirlOfYjby(){
		String sql = "select count(xh)count from (" + 
                     "select zydm,xh,xb,nj,dqn,xz from (" +                
                     "select distinct a.zydm,a.xh,xb,nj,xz,(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) dqn from dual)dqn from (" +  
                     "SELECT a.xh,a.xbm xb,zydm,to_char(nj)nj,to_char(xz)xz FROM bks_xsjbxx a  WHERE a.sfzx='��У' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" + 
                     " UNION " +   
                     "SELECT xh,xb,zydm,nj,xz FROM xsxxb where sfzx='��У'" + 
                     ") a" +       
                     ") where (nj+xz) = dqn and (xb='Ů' or xb='2')" + 
                     ")";
		return this.getOneCount(sql);
	}
	
	/**
	 * ��ѧ���������Уѧ����
	 * @return List
	 * */
	public List getSuperData(){
		String sql = "select count(a.xh) count,xlccmc from xlccdmb b left join( " + 
                     "select xh,nj,dqn,xz,pycc from (" +                 
                     "select distinct a.xh,xb,nj,xz,(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) dqn from dual)dqn,pycc from (" +   
                     "SELECT a.xh,a.xbm xb,to_char(nj)nj,to_char(xz)xz,pycc FROM bks_xsjbxx a  WHERE NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" +  
                     " UNION " +     
                     "SELECT xh,xb,nj,xz,pycc FROM xsxxb" +   
                     ") a" +       
                     ") where (nj+xz) <= dqn-1 " + 
                     ")a on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	/**
	 * ����ѧ������
	 * @return List
	 * */
	public List getIncrease(){
		String sql = "select count(a.xh) count,xlccmc from xlccdmb b left join( " + 
                     "select xh,pycc from (" +                 
                        "select distinct xh,xm,ydlbmc ,(select pycc from view_xsxxb b where b.xh=a.xh)pycc from view_xjydjbxx a where (ydlbmc='��ѧ' or ydlbmc='ת��') and substr(ydrq,0,4)=(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) from dual)" +  
                     ")" +  
                     " union all " +  
                     "select xh,cc pycc from (" + 
                            "select xh,xm,cc from xsjbxxlsb where substr(zssj,0,4) = (select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) from dual)" + 
                     ")" + 
                     ")a on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	/**
	 * �ֲ��������
	 * @return List
	 * */
	public List getZszxByCc(){
		String sql = "select count(a.xh) count,xlccmc from xlccdmb b left join( " +                     
                     "select xh,cc pycc from (" + 
                            "select xh,xm,cc from xsjbxxlsb where substr(zssj,0,4) = (select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) from dual)" + 
                     ")" + 
                     ")a on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	/**
	 * �ֲ���춯ѧ����
	 * @return List
	 * */
	public List getYdxxByCc(String condition){
		String sql = "select count(a.xh) count,xlccmc from xlccdmb b left join( " + 
                     "select xh,pycc from (" +                 
                        "select distinct xh,xm,ydlbmc ,(select pycc from view_xsxxb b where b.xh=a.xh)pycc from view_xjydjbxx a where  1=1 and substr(ydrq,0,4)=(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) from dual)" +  condition + 
                     ")" +                      
                     ")a on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	
	 /**
	  * �ֲ�μ���ѧ���ܼ���
	  * @return List
	  * */
	public List getJsxxList(){
		String sql = "select count(a.xh) count,xlccmc from xlccdmb b left join( " + 
                     "select xh,pycc from (" +                 
                        "select distinct xh,xm,(select pycc from view_xsxxb b where b.xh=a.xh)pycc from bysfzxxb@dblink_jw a where  (byjr='��ҵ' or byjr='��ҵ') and substr(byrq,0,4) = (select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) from dual)" + 
                     ")" +                          
                     " union all " +                     
                     "select xh, pycc from (" + 
                           "select distinct xh,xm,(select pycc from view_xsxxb b where b.xh=a.xh)pycc from view_xjydjbxx a where (ydlbmc='��ѧ' or ydlbmc='��ѧ' or ydlbmc='����' or ydlbmc='ת��' or ydlbmc='����') and substr(ydrq,0,4)=(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) from dual)" + 
                     ")" +                  
                     ")a on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	/**
	 * ��ҵ��ҵѧ����
	 * @return List
	 * */
	public List getBjyList(String condition){
		String sql = "select count(a.xh) count,xlccmc from xlccdmb b left join( " + 
                     "select xh,pycc from ( " +                 
                        "select distinct xh,xm,(select pycc from view_xsxxb b where b.xh=a.xh)pycc from bysfzxxb@dblink_jw a where 1=1 and substr(byrq,0,4) = (select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) from dual)" + condition + 
                     ")" +             
                     ")a on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";		
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	
	/**
	 * ��ѧ���������Уѧ����
	 *@return List
	 * */
	public List getCurrYear(){
		return this.getLevCountListOfGjbb222();//��Уѧ����
	}
}
