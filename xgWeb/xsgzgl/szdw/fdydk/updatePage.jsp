<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript">
        function updateDkxx() {
            var url = "szdw_fdy_fdydkjg.do?method=update";
            ajaxSubFormWithFun("fdydkForm", url, function (data) {
                showAlertDivLayer(data["message"], {}, {
                    "clkFun": function () {
                        if (parent.window) {
                            refershParent();
                        }
                        iFClose();
                    }
                });
            });
        }

    </script>
</head>
<body style="width:100%">
<html:form action="/szdw_fdy_fdydkjg" method="post" styleId="fdydkForm">
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
                <th width="20%">职工号</th>
                <td width="30%">
                        ${fdyxx.zgh}
                </td>
                <th width="20%">姓名</th>
                <td width="30%">${fdyxx.xm}</td>
            </tr>
            <tr>
                <th width="20%">性别</th>
                <td width="30%">${fdyxx.xb}</td>
                <th width="20%">民族</th>
                <td width="30%">${fdyxx.mzmc}</td>
            </tr>
            <tr>
                <th width="20%">所在部门</th>
                <td width="30%">${fdyxx.bmmc}</td>
                <th width="20%">所在书院</th>
                <td width="30%">${fdyxx.symc}</td>
            </tr>
            <tr>
                <th width="20%">政治面貌</th>
                <td width="30%">${fdyxx.zzmmmc}</td>
                <th width="20%">联系电话</th>
                <td width="30%">${fdyxx.yddh}</td>
            </tr>
            <tr>
                <th>到校工作时间</th>
                <td colspan="3">${fdyxx.rxgzsj}</td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>带课信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%">课程类别</th>
                <td width="30%">
                    <html:hidden property="id" styleId="id" value="${fdyDkxx.id}"/>
                    <html:select property="kclbdm" styleId="kclbdm" style="width:88%"  value="${fdyDkxx.kclbdm}">
                        <html:option value=""></html:option>
                        <html:options collection="kcList" property="dm" labelProperty="mc"/>
                    </html:select>
                </td>
                <th width="20%">课程名称</th>
                <td width="30%"><html:text property="kcmc" styleId="kcmc" value="${fdyDkxx.kcmc}" styleClass="inputtext" style="width:88%"/>
            </tr>
            <tr>
                <th width="20%">课程时间</th>
                <td width="30%">
                    <html:text property="kcsj" value="${fdyDkxx.kcsj}" styleId="kcsj"
                               onclick="return showCalendar('kcsj','y-mm-dd');"
                               maxlength="10"/>
                </td>
                <th width="20%">课程地点</th>
                <td width="30%"><html:text property="kcdd" styleId="kcdd"  value="${fdyDkxx.kcdd}" styleClass="inputtext" style="width:88%"/>
            </tr>
            <tr>
                <th width="20%">课时</th>
                <td width="30%"><html:text property="ks" styleId="ks" value="${fdyDkxx.ks}" styleClass="inputtext" style="width:88%"/></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div>
        <table width="100%" border="0" class="formlist">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">"<span class="red">*</span>"为必填项</div>
                    <div class="btn">
                        <button type="button" type="button" onclick="updateDkxx();return false;">
                            保存
                        </button>
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

