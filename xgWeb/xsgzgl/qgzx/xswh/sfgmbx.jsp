<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="xsgzgl/qgzx/xswh/js/xsWh.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">
        function saveForm(){
            var xh=jQuery("#xh").val();
            var sfgmbx=jQuery("#sfgmbx").val();
            if(sfgmbx=="" || xh==""){
                showAlert("请将带*的项目填写完整！");
                return false;
            }
            var url = "qgzx_xsgl.do?method=add";
            ajaxSubFormWithFun("qgzxXsglForm",url,function(data){
                refershParent();
            });
        }
    </script>
    <title>Title</title>
</head>
<body>
<html:form action="/qgzx_xsgl" method="post" styleId="qgzxXsglForm" onsubmit="return false;">
    <div style='tab'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="2">
                    <span>维护购买保险情况</span>
                </th>
            </tr>
            </thead>
            <tbody id="tbody_jbxx">
            <tr>
                <th width="16%">
                    <font color="red">*</font>学号
                </th>
                <td width="34%">
                    <html:text property="xh" value="${dataModel.xh}" readonly="true"/>
                </td>
            </tr>
            <tr>
                <th width="16%">
                    <font color="red">*</font>是否购买保险
                </th>
                <td width="34%">
                   <%-- <input type="radio" name="sfgmbx" value="0" checked="checked">否
                    <input type="radio" name="sfgmbx" value="1">是--%>
                    <html:radio name="dataModel" property="sfgmbx" styleId="sfgmbx" value="0">否</html:radio>
                    <html:radio name="dataModel" property="sfgmbx" styleId="sfgmbx" value="1">是</html:radio>
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="2">
                    <div class="bz">"<span class="red">*</span>"为必填项</div>
                    <div class="btn">
                        <button type="button" type="button" onclick="saveForm();return false;">
                            保 存
                        </button>
                        <button type="button" type="button" onclick="iFClose();return false;">
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

