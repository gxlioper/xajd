<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/qgzx/xsgw/js/wdgwsq.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">
        function save(){
            if(jQuery("#rzsj").val() == "" || jQuery("#xh").val() == ""){
                showAlert("请将必填项填写完整！");
                return false;
            }
            var url = "qgzx_xsgwsh.do?method=zjzgxs&type=save";
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
<body>
<html:form method="post" styleId="demoForm" action="/qgzx_xsgwsh">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生信息</span>
                </th>
            </tr>
            </thead>
            <%@include file="/xsgzgl/comm/bdpz/selectStudent.jsp"%>
            <thead>
            <tr>
                <th colspan="4">
                    <span>岗位信息</span>
                </th>
            </tr>
            </thead>
            <tbody id="tbody_gwxx">
            <tr>
                <td colspan="4">
                    <button class="btn_01" onclick="wdgwxzCx();return false;" type="button">选择岗位</button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="height:30px;"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                    </div>
                    <div class="btn">
                        <button type="button" onclick="save();" id="buttonSave">
                            保 存
                        </button>
                        <button type="button" onclick="iFClose();"  id="buttonClose">
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