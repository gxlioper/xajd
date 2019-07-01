<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type='text/javascript' src="js/check.js"></script>
    <script type='text/javascript'>
        function save(){
            var checkIds = "mc-lxdm-cd-kd-gd-cjmc-gxny";
            if(!checkNotNull(checkIds)){
                showAlert("请将必填项填写完整！");
                return false;
            }
            var url = "gygl_zcgl_zcxxgl.do?method=add&type=save";
            ajaxSubFormWithFun("demoForm", url, function(data) {
                if(data["message"]=="保存成功！"){
                    showAlert(data["message"],{},{"clkFun":function(){
                        if (parent.window){
                            refershParent();
                        }
                    }});
                }else{
                    showAlert(data["message"]);
                }
            });
        }
    </script>
</head>
<body style="width: 100%">
<html:form action="/gygl_zcgl_zcxxgl" method="post" styleId="demoForm" onsubmit="return false;">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>资产信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    <span class="red">*</span>名称
                </th>
                <td>
                    <html:text property="mc" styleId="mc"></html:text>
                </td>
                </td>
                <th><span class="red">*</span>类型</th>
                <td>
                    <html:select property="lxdm" styleId="lxdm">
                        <html:options labelProperty="mc" property="dm" collection="lxList"></html:options>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>厂家
                </th>
                <td>
                    <html:text property="cjmc" styleId="cjmc" ></html:text>
                </td>
                </td>
                <th><span class="red">*</span>资产参数(米)</th>
                <td>
                    长：<html:text property="cd" styleId="cd" style="width:50px;"></html:text>
                    宽：<html:text property="kd" styleId="kd" style="width:50px;"></html:text>
                    高：<html:text property="gd" styleId="gd" style="width:50px;"></html:text>
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>更新年月
                </th>
                <td>
                    <html:text property="gxny" styleId="gxny" readonly="true" onfocus="showCalendar('gxny','y-mm-dd');"></html:text>
                </td>
                <th>
                    价格(元)
                </th>
                <td>
                    <html:text property="jg" styleId="jg" onblur="checkInputNum(this)"></html:text>
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
                        <button type="button" onclick="save();">
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
    <%@ include file="/comm/other/tsxx.jsp"%>
</html:form>
</body>
</html>

