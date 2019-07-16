<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/xstgl/comm/js/comm.js"></script>
    <script type="text/javascript" src="js/provicecitylocal.js"></script>

</head>
<body style="width: 100%">
<html:form action="/llxxjl_jg" method="post" styleId="form" onsubmit="return false;">
    <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;height: 500px'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生基本信息</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
        </table>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="6">
                    <span><font >理论学习类记录</font></span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="15%">学年</th>
                <th width="10%">学期</th>
                <th width="25%">活动名称</th>
                <th width="10%">活动时间</th>
                <th width="25%">活动地点</th>
                <th width="15%">举办单位</th>
            </tr>
            <logic:iterate id="j" name="llxxList" indexId="i">
                <tr>
                    <td>${j.xn}</td>
                    <td>${j.xq}</td>
                    <td>${j.hdmc}</td>
                    <td>${j.sj}</td>
                    <td>${j.ddxxdz}</td>
                    <td>${j.zbdw}</td>
                </tr>
            </logic:iterate>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="6">
                    <span><font >社会实践类记录</font></span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="15%">学年</th>
                <th width="10%">学期</th>
                <th width="25%">活动名称</th>
                <th width="10%">活动时间</th>
                <th width="25%">活动地点</th>
                <th width="15%">举办单位</th>
            </tr>
            <logic:iterate id="j" name="shsjList" indexId="i">
                <tr>
                    <td>${j.xn}</td>
                    <td>${j.xq}</td>
                    <td>${j.hdmc}</td>
                    <td>${j.sj}</td>
                    <td>${j.ddxxdz}</td>
                    <td>${j.zbdw}</td>
                </tr>
            </logic:iterate>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="5">
                    <span><font >志愿服务类记录</font></span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="30%">活动名称</th>
                <th width="15%">活动时间</th>
                <th width="25%">服务地点</th>
                <th width="10%">服务时长</th>
                <th width="20%">举办单位</th>
            </tr>
            <logic:iterate id="j" name="zyfwList" indexId="i">
            <tr>
                <td>${j.hdmc}</td>
                <td>${j.fwsj}</td>
                <td>${j.fwddxxdz}</td>
                <td>${j.fwsc}</td>
                <td>${j.zbdw}</td>
            </tr>
            </logic:iterate>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist"></table>
    </div>
    <div style="height:35px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="btn">
                        <button type="button" onclick="Close();return false;">
                            关 闭
                        </button>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>

    </div>
    <%@ include file="/comm/other/tsxx.jsp"%>
</html:form>
</body>
</html>

