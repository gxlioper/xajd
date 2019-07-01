<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">
        function save(){
            var url = "gygl_fygl_cwxxgl10698.do?method=cwsscsh&type=save";
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
        jQuery(function(){

        })

    </script>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_fygl_cwxxgl10698">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>初始化床位所属</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    初始化床位数量
                </th>
                <td>
                    <logic:notEmpty name="xzgs">
                        <input value="${pks}" name="pks" type="hidden" id="pk"/>
                        当前共选中<span class="red">${xzgs}</span>个未入住且已分配的床位，可执行床位所属初始化操作
                    </logic:notEmpty>
                    <logic:notEmpty name="cxgs">
                        当前查询结果中共有<span class="red">${cxgs}</span>个未入住且已分配的床位，可执行床位所属初始化操作
                    </logic:notEmpty>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>初始化类型</th>
                <td>
                    <input type="radio" name="cshlx" checked value="all">&nbsp;清空全部所属
                    <input type="radio" name="cshlx" value="bj">&nbsp;清空班级所属
                </td>
            </tr>
            <tr>
                <th>是否初始化寝室所属</th>
                <td>
                    <input type="radio" name="sfcshqs" value="1">&nbsp;是
                    <input type="radio" name="sfcshqs" checked value="0">&nbsp;否
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