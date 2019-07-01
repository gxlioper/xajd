package com.zfsoft.xgxt.qgzx.xsgw;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;
import xgxt.DAO.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

/**
 * @������
 * ѧ����λ����ɹ�������1Сʱ�ڽ������ԣ�
 * ��������ʦ���и�λ���ͨ���󣬸�ѧ����ʽ�ϸڣ�
 * �糬��1Сʱ��ø�λ�Զ����ͷ�
 * @���ߣ�WANCHEN
 * @���ڣ�2018-11-01
 */
public class XsgwsqTimeOutTask extends TimerTask{
    protected Log logger = LogFactory.getLog(XsgwsqTimeOutTask.class);
    private DAO dao = null;
    StringBuilder sb;
    public XsgwsqTimeOutTask(){
        dao = DAO.getInstance();
        //��ѯδ��˵����ݳ�ʱ1Сʱ������
        sb = new StringBuilder();
        sb.append("select sqbh");
        sb.append("  from (select t.sqbh,");
        sb.append("                ROUND(TO_NUMBER(sysdate -");
        sb.append("                                to_date(t.sqsj, 'yyyy-MM-dd HH24:mi:ss')) * 24) sc");
        sb.append("        from xg_qgzx_xsgwsqb t");
        sb.append("        where t.shzt = '5'");
        sb.append("          and not exists (select *");
        sb.append("                    from (select distinct t.ywid");
        sb.append("                            from XG_XTWH_SHZTB t");
        sb.append("                            left join XG_XTWH_SPBZ t1");
        sb.append("                              on t.lcid = t1.splc");
        sb.append("                             and t.gwid = t1.spgw");
        sb.append("                           where t1.xh = '1'");
        sb.append("                             and (t.shzt = '1' or t.shzt = '2')) a");
        sb.append("                    where a.ywid = t.sqbh))");
        sb.append(" where sc >= 1");
    }
    @Override
    public void run() {
        try {
            logger.debug("��ʼ������ѧ���ڹ���λ����ʧЧ��ʱ����");
            synchronize();
        } catch (SQLException e) {
            logger.error("�������쳣",e);
            e.printStackTrace();
        }
    }
    public void synchronize() throws SQLException {

        List<HashMap<String, String>> sqidList = dao.getListNotOut(sb.toString(),new String[]{});

        if(!CollectionUtils.isEmpty(sqidList)){
            List<String> list = new ArrayList<String>();
            for(HashMap<String,String> sqid : sqidList){
                //ɾ�����״̬���е�����
                String sql1 = "delete from xg_xtwh_shztb where ywid='"+sqid.get("sqbh")+"' and shzt='0'";
                //ɾ��������Ϣ
                String sql2 = "delete from xg_qgzx_xsgwsqb where sqbh = '"+sqid.get("sqbh")+"'";

                list.add(sql1);
                list.add(sql2);
            }

            dao.runBatch(list.toArray(new String[]{}));
        }
    }
}
