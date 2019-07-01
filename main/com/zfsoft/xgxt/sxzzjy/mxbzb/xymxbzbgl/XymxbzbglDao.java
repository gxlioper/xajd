package com.zfsoft.xgxt.sxzzjy.mxbzb.xymxbzbgl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import oracle.sql.CLOB;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-27 11:41
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XymxbzbglDao extends SuperDAOImpl<XymxbzbglForm> {
    @Override
    protected void setTableInfo() {
        super.setTableName("xg_sxzzjy_xymxbzb");
        super.setKey("newsid");
        super.setClass(XymxbzbglForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(XymxbzbglForm xymxbzbglForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(XymxbzbglForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.* from ( ");
        sql.append(" select a.*,b.typemc newstypemc,c.bmmc fbbmmc from xg_sxzzjy_xymxbzb a " +
                " left join xg_sxzzjy_xymxbzb_type b on b.typedm=a.newstype " +
                " left join ZXBZ_XXBMDM c on c.BMDM = a.fbbmdm");
        sql.append(" ) t where 1 = 1 ");
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public List<HashMap<String,String>> getAlllb() {
        String sql = "select * from xg_sxzzjy_xymxbzb_type order by typexh";
        return dao.getListNotOut(sql,new String[]{});
    }

    public List<HashMap<String,String>> getAlbForView() {
        String sql = "select * from xg_sxzzjy_xymxbzb_type where rownum < 5 order by typexh";
        return dao.getListNotOut(sql,new String[]{});
    }

    public boolean xymxbzbglInsert(XymxbzbglForm t) throws Exception {
        return dao.runInsert(getTableName(),
                new String[]{"newstitle" , "newstype" , "newscont" , "sfzd" , "sffb" , "fbsj","fbr","fbbmdm"},
                new String[]{
                        t.getNewstitle() ,
                        t.getNewstype() ,
                        t.getNewscont() ,
                        t.getSfzd() ,
                        t.getSffb() ,
                        t.getFbsj(),
                        t.getFbr(),
                        t.getFbbmdm()});
    };

    public boolean xymxbzbglUpdate(XymxbzbglForm t) throws Exception {
        String sql = "update xg_sxzzjy_xymxbzb set newstitle = ? ,newstype=?, newscont = ? , " +
                    "sfzd = ? , sffb = ? ,xgsj = ?  where newsid = ?";
        return dao.runUpdate(sql, new String[]{ t.getNewstitle() ,
                t.getNewstype() ,
                t.getNewscont() ,
                t.getSfzd() ,
                t.getSffb() ,
                t.getXgsj() ,
                t.getNewsid()});
    };

    public HashMap<String,String> getxymxbzbglInfo(XymxbzbglForm model) throws SQLException {
        String sql = "select a.*,b.typemc newstypemc,c.bmmc fbbmmc from xg_sxzzjy_xymxbzb a " +
                " left join xg_sxzzjy_xymxbzb_type b on b.typedm=a.newstype " +
                " left join ZXBZ_XXBMDM c on c.BMDM = a.fbbmdm" +
                " where a.newsid=?";
        HashMap<String,String> map = dao.getMapNotOut(sql,new String[]{model.getNewsid()});
        CLOB clob = dao.getOneClob(sql, new String[] { model.getNewsid() }, "newscont");
        String msg = clob.getSubString(1L, (int) clob.length());
        map.put("newscont",msg);
        return map;
    }

    /**
     * @����:����
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/28 15:02
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [ids, xgsj]
     * @return: boolean
     */
    public boolean xymxbzbglFb(String[] ids, String xgsj) throws Exception {
        String[] sqlArr=new String[ids.length];
        for(int i=0;i<ids.length;i++){
            String sql = "update xg_sxzzjy_xymxbzb set xgsj='"+xgsj+"',sffb='��' where newsid='"+ids[i]+"'";
            sqlArr[i]=sql;
        }
        int[] result = dao.runBatch(sqlArr);
        return  dao.checkBatch(result);
    }
    /**
     * @����:ȡ������
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/28 15:02
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [ids, xgsj]
     * @return: boolean
     */
    public boolean xymxbzbglQxfb(String[] ids, String xgsj) throws Exception {
        String[] sqlArr=new String[ids.length];
        for(int i=0;i<ids.length;i++){
            String sql = "update xg_sxzzjy_xymxbzb set xgsj='"+xgsj+"',sffb='��' where newsid='"+ids[i]+"'";
            sqlArr[i]=sql;
        }
        int[] result = dao.runBatch(sqlArr);
        return  dao.checkBatch(result);
    }

    /**
     * @����:�ö�
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/28 15:03
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [ids, xgsj]
     * @return: boolean
     */
    public boolean xymxbzbglZd(String[] ids, String xgsj) throws Exception {
        String[] sqlArr=new String[ids.length];
        for(int i=0;i<ids.length;i++){
            String sql = "update xg_sxzzjy_xymxbzb set xgsj='"+xgsj+"',sfzd='��' where newsid='"+ids[i]+"'";
            sqlArr[i]=sql;
        }
        int[] result = dao.runBatch(sqlArr);
        return  dao.checkBatch(result);
    }
    /**
     * @����:ȡ���ö�
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/28 15:03
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [ids, xgsj]
     * @return: boolean
     */
    public boolean xymxbzbglQxzd(String[] ids, String xgsj) throws Exception {
        String[] sqlArr=new String[ids.length];
        for(int i=0;i<ids.length;i++){
            String sql = "update xg_sxzzjy_xymxbzb set xgsj='"+xgsj+"',sfzd='��' where newsid='"+ids[i]+"'";
            sqlArr[i]=sql;
        }
        int[] result = dao.runBatch(sqlArr);
        return  dao.checkBatch(result);
    }

    public List<HashMap<String,String>> getNewsList(String typedm,String size) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append("select a.* from xg_sxzzjy_xymxbzb a ");
        sql.append("where a.newstype = ? and sffb = '��' order by sfzd desc, fbsj desc");
        sql.append(") where rownum<=?");
        return dao.getListNotOut(sql.toString(),new String[]{typedm,size});
    }

    public List<HashMap<String,String>> getNewsmore(XymxbzbglForm t) throws  Exception{
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from xg_sxzzjy_xymxbzb where sffb='��'");
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public boolean addYdr(XymxbzbglForm t,User user) throws Exception {
        String llsj= GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss");
        String sql = "insert into xg_sxzzjy_xymxbzb_lljlb (yhm,llsj,newsid,ip,yhlx) values(?,?,?,?,?)";
        return dao.runUpdate(sql,new String[]{user.getUserName(),llsj,t.getNewsid(),user.getIp(),user.getUserType()});
    }

    public List<HashMap<String,String>> getYydmd(XymxbzbglForm t) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM (");
        sql.append("SELECT t.*,z.BMMC FROM ");
        sql.append("(select a.*,(case when a.yhlx='stu' then 'ѧ��' else '��ʦ' end) yhlxmc,");
        sql.append("CASE WHEN a.yhlx = 'stu' THEN (SELECT xm FROM XSXXB WHERE xh = a.YHM)");
        sql.append("ELSE (SELECT xm FROM YHB WHERE YHM = a.YHM) END xm,");
        sql.append("CASE WHEN a.yhlx = 'stu' THEN (SELECT XYDM FROM VIEW_XSJBXX WHERE xh = a.YHM) ");
        sql.append("ELSE (SELECT SZBM FROM YHB WHERE YHM = a.YHM) END bmdm ");
        sql.append("from xg_sxzzjy_xymxbzb_lljlb");
        sql.append(" a) t LEFT JOIN ZXBZ_XXBMDM z ON t.bmdm = z.BMDM ");
        sql.append("where t.newsid=?");
        sql.append(") t");
        return getPageList(t, sql.toString(), new String[]{t.getNewsid()});
    }
}
