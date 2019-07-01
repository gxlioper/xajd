package com.zfsoft.xgxt.comm.zdydr.dao;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.comm.zdydr.model.ZdydrModel;
import xgxt.action.Base;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * �Զ�����Ի�����dao.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2017-10-10 10:09
 */
public class ZdydrDao extends SuperDAOImpl<ZdydrModel> {

    /**
     * ��������DAO����Ӧ�ı�
     */
    @Override
    protected void setTableInfo() {
        super.setTableName("ZFXG_DRSJ_DRLPZ");
        super.setKey("drlpzid");
        super.setClass(ZdydrModel.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZdydrModel zdydrModel) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZdydrModel zdydrModel, User user) throws Exception {
        return null;
    }

    /**
     *  ��ѯ����ģ����Ϣ.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-10 14:01
     * @param drmkdm
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     * @throw
     */
    public HashMap<String,String> getDrmbxx(String drmkdm) {

        String sql = "select drmkdm,drmkmc,drbmc from zfxg_drsj_drpz where drmkdm = ?";
        return dao.getMapNotOut(sql, new String[]{drmkdm});
    }

    /**
     *  ��ѯ���������Ϣ.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-10 14:02
     * @param drmkdm
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @throw
     */
    public List<HashMap<String,String>> getDrgzxxList(String drmkdm) {

        String sql = "select drl,drlmc,lsjgsh,gshxx,sfzj,sfbt,zdcd,sfwy from ZFXG_DRSJ_DRLPZ where drmkdm = ? order by to_number(xsxx)";
        return dao.getListNotOut(sql, new String[]{drmkdm});
    }

    /**
     *  ��ѯ���븨������Ϣ.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-10 17:18
     * @param drmkdm
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @throw
     */
    public List<HashMap<String,String>> getDrfzxxList(String drmkdm) {

        String sql = "select pz,fzmc from ZFXG_DRSJ_FZB where drmkdm = ?";
        return dao.getListNotOut(sql, new String[]{drmkdm});
    }

    /**
     *  ��ȡ���븨������������Ϣ�б�.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-10 17:20
     * @param tableName
     * @param outputValue
     * @param sortCol
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @throw
     */
    public List<HashMap<String,String>> getFzdmxxList(String tableName, String[] outputValue,String sortCol) {

        String sql = "select * from " + tableName;
        if(!StringUtil.isNull(sortCol)){
            sql += " order by "+sortCol;
        }
        return dao.getList(sql, new String[]{}, outputValue);
    }

    /**
     *  ���������ֶβ�ѯ�Ƿ���ڼ�¼�����ڼ�false.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-11 11:58
     * @param zjDrlList
     * @param excelData
     * @param tableName
     * @return boolean
     * @throw
     */
    public boolean checkZj(List<String> zjDrlList, HashMap<String, String> excelData,String tableName) {

        List<String> paraList = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("select count(1) count from ");
        sql.append(tableName+" where ");
        for(int i=0;i<zjDrlList.size();i++){
            sql.append(zjDrlList.get(i));
            paraList.add(excelData.get(zjDrlList.get(i)));
            sql.append(" = ? ");
            if(i != zjDrlList.size()-1){
                sql.append(" and ");
            }
        }

        return Integer.valueOf(dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}),"count")) == 0;
    }

    public boolean checkJCFFSQBZj(HashMap<String, String> excelData){
        List<String> paraList = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("select count(1) count from XG_QGZX_JCFFSQB where xh = ? and gwdm = ? and yf = ? ");

//        excelData.values().toArray(new String[]{});
        paraList.add(excelData.get("xh"));
        paraList.add(excelData.get("gwdm"));
        paraList.add(excelData.get("ffny"));

        return Integer.valueOf(dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}),"count")) == 0;
    }

    public boolean checkGWFFZTSQBZj(HashMap<String, String> excelData){
        List<String> paraList = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("select count(1) count from XG_QGZX_GWFFZTSQB where yrdwdm = ? and ffny = ? and xn = ?");

        paraList.add(excelData.get("yrdwdm"));
        paraList.add(excelData.get("ffny"));
        paraList.add(Base.currXn);

        return Integer.valueOf(dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}),"count")) == 0;
    }

    /**
     *  ����ָ�������ֶε�ָ�����в�ѯ�Ƿ���ڼ�¼�������ڼ�false.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-11 14:50
     * @param lhDrlArr
     * @param excelData
     * @param tableName
     * @return boolean
     * @throw
     */
    public boolean checkLh(String[] lhDrlArr, HashMap<String, String> excelData, String tableName) {

        List<String> paraList = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("select count(1) count from ");
        sql.append(tableName+" where ");
        for(int i=0;i<lhDrlArr.length;i++){
            sql.append(lhDrlArr[i]);
            paraList.add(excelData.get(lhDrlArr[i]));
            sql.append(" = ? ");
            if(i != lhDrlArr.length-1){
                sql.append(" and ");
            }
        }

        return Integer.valueOf(dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}),"count")) > 0;
    }

    /**
     *  ��֤�����ֶε�ΨһԼ��.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-11 14:54
     * @param drl
     * @param cellContents
     * @param tableName
     * @return boolean
     * @throw
     */
    public boolean isRepeatForDr(String drl, String cellContents, String tableName) {

        StringBuilder sql = new StringBuilder("select count(1) count");
        sql.append(" from ");
        sql.append(tableName);
        sql.append(" where ");
        sql.append(drl);
        sql.append("=?");

        return Integer.valueOf(dao.getOneRs(sql.toString(),new String[]{cellContents},"count")) > 0;
    }

    /**
     *  ����ʱ��֤����sql���ò�ѯĳ��Ԫ�����ݲ����أ�����������滻����������������.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-11 15:25
     * @param sql
     * @param cellContents
     * @param drl
     * @return java.lang.String
     * @throw
     */
    public String changeCellData(String sql, String cellContents, String drl) {

        return dao.getOneRs(sql.toString(), new String[]{cellContents}, drl);
    }
}

