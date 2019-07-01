<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script language="javascript" src="comm/editor/kindeditor.js"></script>
    <script language="javascript" src="comm/editor/zh_CN.js"></script>
    <script language="javascript" src="comm/editor/editor.js"></script>
    <script type="text/javascript">

        jQuery(function(){
        });

        /**
         �����������
         */
        function save(){
            editor.sync();
            var html=editor.html();
            if(html==null||html==""){
                showAlert("����д�������ģ�");
                return false;
            }
            var url = "xlzx_xlzxxzwh.do?method=save";
            ajaxSubFormWithFun("form",url,function(data){
                showAlertDivLayer(data["message"]);
            });
        }

    </script>
</head>
<body >
<html:form action="/xlzx_xlzxxzwh" method="post" styleId="form" >
    <html:hidden property="id" styleId="id"/>
    <div class="tab_cur">
        <p class="location">
            <em>���ĵ�ǰλ��:</em><a>${title }</a>
        </p>
    </div>
    <div class="tab">
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="2"><span>��ѯ��֪����</span></th>
            </tr>
            </thead>
            <tbody>
            <tr  id="splcTr1">
                <th width="25%">
                    <span class="red">*</span>
                    <span style="font-weight: bold">��ѯ��֪</span>
                </th>
                <td>
                   <%--<html:textarea property="zxxz" styleId="zxxz"
                                  rows="9" style="width:97%" onblur="chLeng(this,500)"/>--%>
                       <textarea id="editorid" name="editorid" style="width:700px;height:280px;">
                               ${editorid }
						</textarea>
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
                    <div class="btn">
                        <logic:equal name="writeAble" value="yes">
                            <button type="button" class="button2" onclick="save();return false;" id="btn_save">
                                �� ��
                            </button>
                        </logic:equal>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</html:form>
</body>
</html>
