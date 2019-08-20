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
    <script type="text/javascript">
        function saveMm(){
            var checkId = 'pwd-password';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("�뽫��������д������");
                return false;
            }
            var pwd = jQuery("#pwd").val();
            var password = jQuery("#password").val();
            if(pwd!=password){
                showAlertDivLayer("�������벻һ�£�");
                return false;
            }
            save();
        }
        function save(){
            var url = "hdgl_bmdwh.do?method=updateBmd&type=update";
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlert(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }


    </script>
</head>
<body style="width:100%">
<html:form action="/hdgl_bmdwh" method="post" styleId="demoForm">
    <input type="hidden" id="id" name="id" value="${model.id}"/>
    <div style='width:100%;height:150px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="2">
                    <span>��������</span>
                </th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <th><span class="red">*</span>����������</th>
                    <td>
                        <input type="password" id="pwd" name="pwd"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>���ٴ���������</th>
                    <td>
                        <input type="password" id="password" name="password"/>
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
                    <div class="bz">"<span class="red">*</span>"Ϊ������</div>
                    <div class="btn">
                        <button type="button" type="button" onclick="saveMm();return false;" >
                            ����
                        </button>
                        <button type="button" name="�� ��" onclick="iFClose();">
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

