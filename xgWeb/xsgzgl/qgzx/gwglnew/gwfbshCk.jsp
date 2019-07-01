<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type='text/javascript' src="js/comm/message.js"></script>
    <script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
    <script type='text/javascript' src='/xgxt/dwr/util.js'></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type='text/javascript' src="js/uicomm.js"></script>
    <script type='text/javascript' src="js/String.js"></script>
    <script type='text/javascript' src="js/xgutil.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="comm/editor/kindeditor-min.js"></script>
    <script type="text/javascript" src="comm/editor/zh_CN.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function(){
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.gwdm}&tt="+new Date().getTime());
            jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
        });
    </script>
</head>
<body >

<html:form styleId="qgzxGwglNewForm" action="/qgzx_gwglnew" method="post" onsubmit="return false;">
    <input type="hidden" name="gwdm" value="${model.gwdm}">
    <input type="hidden" name="yrdwid" value="${yrdwid}"/>
    <input type="hidden" name="splc" value="${model.splc}"/>
    <table align="center" class="formlist">
        <thead>
        <tr>
            <th colspan="4" style="width: 25%">
                <span>岗位基本信息</span>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>
                学年学期
            </th>
            <td>
                    ${model.xn}${model.xqmc}
            </td>
            <th>
                用人单位
            </th>
            <td>
                ${yrdwmc}
            </td>
        </tr>
        </tbody>
        <tbody>
        <tr>
            <th>
                岗位名称
            </th>
            <td>
                    ${model.gwmc}
            </td>
            <th>工作性质</th>
            <td>
                <logic:equal name="model" property="gwxzdm" value="0"> 临时</logic:equal>
                <logic:equal name="model" property="gwxzdm" value="1">正式</logic:equal>
            </td>
        </tr>
        <tr>
            <th>
                招聘人数
            </th>
            <td>
                    ${model.xqrs}人
            </td>
            <th>
                岗位类型
            </th>
            <td>
                <logic:equal name="model" property="gwlx" value="0">临时</logic:equal>
                <logic:equal name="model" property="gwlx" value="1">长期</logic:equal>
            </td>
        </tr>
        <tr>
            <th>
                岗位类别
            </th>
            <td >
                    ${model.gwxzmc}
            </td>
            <th>
                工资上限/月
            </th>
            <td>
                    ${model.cjsx}元
            </td>
        </tr>
        <tr>
            <th>
                工时上限/月
            </th>
            <td colspan="3">
                    ${gsgz.gzscsx}小时
                <span id="label"></span>
            </td>
        </tr>
        <tr>
            <th>
                招聘开始时间
            </th>
            <td>
                    ${model.zpkssj}
            </td>
            <th>
                招聘结束时间
            </th>
            <td>
                <logic:equal name="model" property="cq" value="1">长期</logic:equal>
                <logic:notEqual name="model" property="cq" value="1">${model.zpjssj}</logic:notEqual>
            </td>
        </tr>
        <tr>
            <th>
                是否置顶
            </th>
            <td>
                <logic:equal name="model" property="sfzd" value="1">是</logic:equal>
                <logic:notEqual name="model" property="sfzd" value="1">否</logic:notEqual>
            </td>
            <th>
                是否显示工资
            </th>
            <td>
                <logic:equal name="model" property="sfxsgz" value="1">是</logic:equal>
                <logic:notEqual name="model" property="sfxsgz" value="1">否</logic:notEqual>
            </td>
        </tr>
        <tr>
            <th>
                招聘要求
            </th>
            <td colspan="3">
                    ${model.gwryyq}
            </td>
        </tr>
        </tbody>
        <thead>
        <tr>
            <th colspan="4">
                <span>审核信息</span>
            </th>
        </tr>
        </thead>
        <tr>
            <td colspan="4" id="shlccx">

            </td>
        </tr>
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="btn">
                    <button type="button" name="关闭" onclick="Close();return false;">
                        关 闭
                    </button>
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
</html:form>
</body>
</html>
