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
                if(data["message"]=="����ɹ���"){
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
                    <span>����������λ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    ������λ��
                </th>
                <td>
                    ��ǰ��ѡ��<span class="red">${kcws}</span>���մ�λ
                </td>
            </tr>
            <tr>
                <th>�Ƿ���</th>
                <td>
                    <input type="radio" name="sfbl" checked value="1">&nbsp;��
                    <input type="radio" name="sfbl" value="0">&nbsp;��
                </td>
            </tr>
            <tr>
                <th>����ԭ��</th>
                <td>
                    <html:select property="blyy" styleId="blyy">
                        <html:options collection="blyyList" property="dm" labelProperty="mc"></html:options>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th>����˵��<br><span class="red">(��100��)</span></th>
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
                            �� ��
                        </button>
                        <button type="button" onclick="iFClose();"  id="buttonClose">
                            �� ��
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