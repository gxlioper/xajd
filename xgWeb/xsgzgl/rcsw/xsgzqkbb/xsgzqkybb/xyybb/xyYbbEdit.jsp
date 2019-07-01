<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <%@ include file="/syscommon/autocomplete.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/rcsw/xsgzqkbb/xsgzqkybb/xyybb/js/xyYbb.js"></script>
    <script type='text/javascript' src="js/check.js"></script>
</head>
<body style="width: 100%">
<html:form action="/rcsw_xsgzqkbb_xyybbgl" method="post" styleId="xsgzqkXyYbbForm" onsubmit="return false;">
    <html:hidden property="id"></html:hidden>
    <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生工作情况学院月报表信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="16%">
                    填表人
                </th>
                <td width="34%">
                    <html:hidden property="tbr"></html:hidden>
                        ${xsgzqkXyYbbForm.tbrmc}
                </td>
                <th width="16%">
                    <bean:message key="lable.xy" />
                </th>
                <td width="34%">
                      <html:hidden property="xydm"></html:hidden>
                      ${xsgzqkXyYbbForm.xymc}
                </td>
            </tr>
            <tr>
                <th width="16%">
                    <span class="red">*</span>月份
                </th>
                <td width="34%">
                    <html:text property="yf"
                               onclick="return showCalendar('yf','yyyy年MM月');" styleId="yf" ></html:text>
                </td>
                <th><span class="red">*</span>学生工作负责人</th>
                <td>
                    <html:hidden property="xsgzfzr" styleId="xsgzfzr"></html:hidden>
                    <html:text property="xsgzfzrmc" styleId="xsgzfzrmc" readonly="true"></html:text>
                    <button type="button" onclick="showDialog('教师选择',720,480,'szdw_fdyjtff.do?method=showFdysNotF5');return false;" class="btn_01" id="buttonFindStu">
                        选择
                    </button>
                </td>
            </tr>
            <tr>
                <th width="16%">
                    <span class="red">*</span>学年
                </th>
                <td width="34%">
                    <html:select  property="xn" styleId="xn" style="width:100px" >
                        <html:options collection="xnList" labelProperty="xn" property="xn"/>
                    </html:select>
                </td>
                <th><span class="red">*</span>学期</th>
                <td>
                    <html:select  property="xq" styleId="xq" >
                        <html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
                    </html:select>
                </td>
            </tr>

            </tbody>
        </table>
    </div>
    <div style="height:35px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "<span class="red">*</span>"为必填项
                    </div>
                    <div class="btn">
                        <button type="button" onclick="saveForEdit();">
                            保存
                        </button>
                        <button type="button" onclick="iFClose();">
                            关闭
                        </button>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</html:form>

<script type="text/javascript">
//    jQuery(function(){
//        initXsgzfzrmcAutocomplete();
//    });

    function getSqlTj(){
        var bmdm = "${userDep}";
        var sqlTj;
        if(bmdm != ''){
            sqlTj = " and bmdm = '"+bmdm+"' ";
        }
        return sqlTj;
    }

</script>
</body>
</html>

