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
            var url = "gygl_fygl_cwxxgl10698.do?method=plbl&type=save";
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
    <input name="pks" value="${pks}" type="hidden">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>批量保留床位</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    保留床位数
                </th>
                <td>
                    当前共选中<span class="red">${kcws}</span>个空床位
                </td>
            </tr>
            <tr>
                <th>是否保留</th>
                <td>
                    <input type="radio" name="sfbl" checked value="1">&nbsp;是
                    <input type="radio" name="sfbl" value="0">&nbsp;否
                </td>
            </tr>
            <tr>
                <th>保留原因</th>
                <td>
                    <html:select property="blyy" styleId="blyy">
                        <html:options collection="blyyList" property="dm" labelProperty="mc"></html:options>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th>保留说明<br><span class="red">(限100字)</span></th>
                <td>
                    <html:textarea property="blsm" styleId="blsm" rows="4" style="width:98%" onblur="chLengs(this,100);">
                    </html:textarea>
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