<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script language="javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script language="javascript" src="js/function.js"></script>
    <script language="javascript" src="js/Basic.js"></script>
    <script language="javascript" src="js/comm/commFunction.js"></script>
    <script language="javascript" src="comm/editor/kindeditor.js"></script>
    <script language="javascript" src="comm/editor/zh_CN.js"></script>
    <script language="javascript" src="comm/editor/editor.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/mxbzb/xymxbzbgl/js/xymxbzbglEdit.js"></script>
    <script type="text/javascript">
        jQuery(function(){


        })
    </script>
    <style type="text/css">
        *{font-size: 14px;}
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/sxzzjy_xymxbzbgl" method="post"  styleId="form">
    <div style='width:100%; height:470px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <tbody id="">

            <tr style="">
                <th >
                    <span style="color: red">*</span>�� ��
                </th>
                <td colspan="3">
                    <html:text property="newstitle" styleId="newstitle" style="width:80%;" />
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span>���
                </th>
                <td colspan="3">
                    <html:select property="newstype" styleId="newstype" style="width:200px" styleClass="select">
                        <html:option value=""></html:option>
                        <html:options collection="lbList" property="typedm"
                                      labelProperty="typemc" />
                    </html:select>
                </td>

            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>�༭����
                </th>
                <td   colspan="3">
                    <html:textarea property="newscont" styleId="editorid" rows="10" style="width:95%;height:320px"></html:textarea>
                </td>
            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>�Ƿ�ֱ�ӷ���
                </th>
                <td >
                    <html:radio property="sffb" value="��">��</html:radio>
                    <html:radio property="sffb" value="��">��</html:radio>
                </td>
                <th>
                    <span style="color: red">*</span>�Ƿ��ö�
                </th>
                <td >
                    <html:radio property="sfzd" value="��">��</html:radio>
                    <html:radio property="sfzd" value="��">��</html:radio>
                </td>
            </tr>
            </tbody>


        </table>
    </div>
</html:form>
    <table width="100%" border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">
                    "<span class="red">*</span>"Ϊ������
                </div>
                <div class="btn">

                    <button type="button" type="button" onclick="save('add');">
                        �� ��
                    </button>

                    <button type="button" type="button" onclick="iFClose();">
                        �� ��
                    </button>
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
</body>
</html>

