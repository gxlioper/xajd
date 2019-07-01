<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#sqid").val()+"&tt="+ new Date().getTime());

        })
    </script>
</head>
<body style="width: 100%">
<html:form action="/cxcy_bzsbwhsq" method="post" styleId="form" onsubmit="return false;">
    <html:hidden property="sqid" styleId="sqid" value="${map.sqid}"/>
    <html:hidden property="splc" styleId="splc" value="${map.splc}"/>
    <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;height: 400px' >
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生基本信息</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span>创新创业补助申请信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="15%">
                    学年
                </th>
                <td width="35%">
                        ${map.xn}
                </td>
                <th width="15%">学期</th>
                <td width="35%">
                        ${map.xqmc}
                </td>
            </tr>
            <tr>
                <th width="15%">
                    <span>项目名称</span>
                </th>
                <td >
                    ${map.xmmc}
                </td>
                <th>
                    补助金额（元）
                </th>
                <td>
                    ${map.bzje}
                </td>
            </tr>
            <tr>
                <th width="15%">
                    <span>填报人</span>
                </th>
                <td >
                        ${map.tbrmc}
                </td>
                <th>
                    填报时间
                </th>
                <td>
                        ${map.sqsj}
                </td>
            </tr>
            <tr>
                <th>
                    <span>附件</span>
                </th>
                <td colspan="3">
                    <%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
                    <script type="text/javascript">
                        //调用附件
                        jQuery(function(){
                            var gid = "${map.fj}";
                            jQuery.MultiUploader_q({
                                gid : gid
                            });
                        });
                    </script>
                </td>
            </tr>
            <tr>
                <th>
                    申请理由
                </th>
                <td colspan="3">
                    ${map.sqly}
                </td>
            </tr>
            </tbody>
        </table>
        <logic:notEqual value="无需审核" name="shztmc">
            <table width="100%" border="0" class="formlist">
                <thead>
                <tr>
                    <th colspan="4">
                        <span>审核信息</span>
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td colspan="4" id="shlccx">
                    </td>
                </tr>
                </tbody>
            </table>
        </logic:notEqual>
    </div>
    <div style="height:25px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="btn">
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
</body>
</html>

