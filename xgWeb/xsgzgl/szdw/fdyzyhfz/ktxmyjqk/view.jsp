<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript">

    </script>
</head>
<body style="width:100%">
<html:form action="/szdw_fdy_ktxmyjqkwh" method="post" styleId="demoForm">
    <div style='width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>基本信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <th>职工号</th>
                    <input type="hidden" name="zgh" value="${fdyxx.zgh}">
                    <td>${fdyxx.zgh}</td>
                    <th>姓名</th>
                    <td>${fdyxx.xm}</td>
                </tr>
                <tr>
                    <th>性别</th>
                    <td>${fdyxx.xb}</td>
                    <th>名族</th>
                    <td>${fdyxx.mzmc}</td>
                </tr>
                <tr>
                    <th>所在部门</th>
                    <td>${fdyxx.bmmc}</td>
                    <th>所在书院</th>
                    <td>${fdyxx.symc}</td>
                </tr>
                <tr>
                    <th>政治面貌</th>
                    <td>${fdyxx.zzmmmc}</td>
                    <th>联系电话</th>
                    <td>${fdyxx.lxdh}</td>
                </tr>
                <tr>
                    <th>到校工作时间</th>
                    <td colspan="3">${fdyxx.rxgzsj}</td>
                </tr>
            </tbody>
            <thead>
                <tr>
                    <th colspan="4">
                        <span>课题项目信息</span>
                    </th>
                </tr>
            </thead>
            <tbody>
            <tr>
                <th>项目编号</th>
                <td colspan="3">
                        ${model.xmbh}
                </td>
            </tr>
            <tr>
                <th>名称</th>
                <td>
                    ${model.mc}
                </td>
                <th>来源</th>
                <td>
                        ${model.xmly}
                </td>
            </tr>
            <tr>
                <th>开始时间</th>
                <td>
                        ${model.kssj}
                </td>
                <th>结束时间</th>
                <td>
                        ${model.jssj}
                </td>
            </tr>
            <tr>
                <th>项目经费</th>
                <td>
                        ${model.xmjf}
                </td>
                <th>申请时间</th>
                <td>
                        ${model.sqsj}
                </td>
            </tr>
            <tr>
                <th>本人承担角色</th>
                <td>
                        ${model.cdjsmc}
                </td>
                <th>是否结项</th>
                <td>
                        ${model.sfjxmc}
                </td>
            </tr>
            <tr id="jxsjTr">
                <th>结项时间</th>
                <td colspan="3">
                        ${model.jxsj}
                </td>
            </tr>
            <tr>
                <th>结项结果</th>
                <td colspan="3">
                        ${model.jxjg}
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div>
        <table width="100%" border="0" class="formlist">
            <tfoot>
            <tr>
                <td colspan="4" >
                    <div class="btn">
                        <button type="button" name="关 闭" onclick="iFClose();">
                            关 闭
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

