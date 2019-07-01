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
    <script type="text/javascript" src="xsgzgl/gygl/sfqs/sfqscj/sfqscjsq/js/sfqscj.js"></script>
    <script type="text/javascript">

        jQuery(document).ready(function(){
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
        });
    </script>
    <style type="text/css">
        #shlccx_table th{text-align: center;}
        #shlccx_table tr{text-align: center;}
    </style>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_sfqscj_wh">
    <input type="hidden" name="sqid" value="${model.sqid}"/>
    <input type="hidden" name="splc" value="${model.splc}"/>
    <input type="hidden" name="xh" value="${model.xh}"/>
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>������Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    ѧ��
                </th>
                <td>
                    ${model.xn}
                </td>
                <th>
                    У��
                </th>
                <td>
                        ${model.xqmc}
                </td>
            </tr>
            <tr>
                <th>
                    ����¥����
                </th>
                <td>
                    <input type="hidden" id="lddm" value="${model.lddm}"/>
                        ${model.ldmc}
                </td>
                <th>���Һ�</th>
                <td>
                    <input type="hidden" id="qsh" value="${model.qsh}"/>
                        ${model.qsh}
                </td>
            </tr>
            <tr>
                <th>������Ժ</th>
                <td id="sssyTd">

                </td>
                <th>��ϵ��ʽ</th>
                <td>
                    ${model.lxfs}
                </td>
            </tr>
            <tr>
                <th>�걨����</th>
                <td colspan="3">
                    ${model.sblx}
                </td>
            </tr>
            <tr>
                <th>���ҳ�Ա</th>
                <td colspan="3">
                    <style type="text/css">
                        #shlccx_table th{text-align: center;}
                        #shlccx_table tr{text-align: center;}
                    </style>
                    <table id="shlccx_table" width="100%">
                        <tr id="xmTr">
                            <th>����</th>
                            <logic:iterate id="i" name="qscyList">
                                <td>${i.xm}</td>
                            </logic:iterate>
                        </tr>
                        <tr id="xhTr">
                            <th>ѧ��</th>
                            <logic:iterate id="i" name="qscyList" indexId="index">
                                <td>${i.xh}</td>
                            </logic:iterate>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <th>�����ں�</th>
                <td colspan="3">
                    ${model.cjkh}
                </td>
            </tr>
            <tr>
                <th>�����ƻ�</th>
                <td colspan="3">
                    ${model.cjjh}
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>�����Ϣ</span>
                </th>
            </tr>
            </thead>
            <tr>
                <td colspan="4" id="shlccx">

                </td>
            </tr>
        </table>
    </div>
    <div style="height:30px;"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="btn">
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