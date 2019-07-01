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
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/bjxfjs/bjxfjshb/js/bjxfjshbEdit.js"></script>

    <script type="text/javascript">
        jQuery(function(){
            var sfNzhb="${sfNzhb}";
            var sfNzzj="${sfNzzj}";
            if("1" == sfNzhb){
                jQuery("#shlccx_nzhb").load("comm_spl.do?method=lccx&sqid="+jQuery("#nzhbsqid").val()+"&tt="+ new Date().getTime());
            }
            if("1" == sfNzzj){
                jQuery("#shlccx_nzzj").load("comm_spl.do?method=lccx&sqid="+jQuery("#nzzjsqid").val()+"&tt="+ new Date().getTime());
            }

        })
    </script>
    <style type="text/css">
        *{font-size: 14px;}
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/sxzzjy_grxfjshb" method="post"  styleId="form">
    <input type="hidden" id="nzhbsqid" name="nzhbsqid" value="${nzhbMap.sqid}"/>
    <input type="hidden" id="nzzjsqid" name="nzzjsqid" value="${nzzjMap.sqid}"/>
    <div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>ѧ��������Ϣ</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
        </table>
        <logic:equal value="1" name="sfNzhb">
            <table width="100%" border="0" class="formlist">
                <tbody>
                <tr style="">
                    <th colspan="4"  style="text-align: left">
                        ���ڻ㱨
                    </th>
                </tr>
                <tr>
                    <th width="27%">ƽ��ѧ�ֻ�</th>
                    <td width="23%">${nzhbMap.pjxfj}��</td>
                    <th width="27%">ƽ��ѧ�ֻ�С������</th>
                    <td width="23%">${nzhbMap.pjxfjpm}��</td>
                </tr>
                <tr>
                    <th width="27%">�ۺϲ����ɼ�</th>
                    <td width="23%">${nzhbMap.zhcpcj}��</td>
                    <th width="27%">�ۺϲ����ɼ�����</th>
                    <td width="23%">${nzhbMap.zhcpcjpm}��</td>
                </tr>
                <tr>
                    <th width="27%">�ۺϲ����ɼ��꼶��רҵ���ٷֱ�</th>
                    <td colspan="3">${nzhbMap.njbfb}%</td>
                </tr>
                <tr>
                    <th width="27%">
                        ����<br>
                        <span style="color: red">(��100��)</span>
                    </th>
                    <td colspan="3">
                            ${nzhbMap.qt}
                    </td>
                </tr>
                </tbody>
            </table>
            <table width="100%" border="0" class="formlist">
                <tbody>
                <tr style="">
                    <th width="14%">
                        ��һѧ�ڿγ̳ɼ�
                    </th>
                    <td   colspan="3">
                            ${nzhbMap.zd1}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        �����ܽ�
                    </th>
                    <td   colspan="3">
                            ${nzhbMap.zd2}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        ���˽�������������
                    </th>
                    <td   colspan="3">
                            ${nzhbMap.zd3}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        ���˽������ķ���
                    </th>
                    <td   colspan="3">
                            ${nzhbMap.zd4}
                    </td>
                </tr>
                </tbody>
            </table>
            <logic:notEqual value="�������" name="nzhbMap" property="shztmc" >
                <table width="100%" border="0" class="formlist">
                    <thead>
                    <tr>
                        <th colspan="4">
                            <span>�����Ϣ</span>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td colspan="4" id="shlccx_nzhb">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </logic:notEqual>
        </logic:equal>
        <logic:equal value="1" name="sfNzzj">
            <table width="100%" border="0" class="formlist">
                <tbody>
                <tr style="">
                    <th colspan="4"  style="text-align: left">
                        ����ܽ�
                    </th>
                </tr>
                <tr>
                    <th width="27%">ƽ��ѧ�ֻ�</th>
                    <td width="23%">${nzzjMap.pjxfj}��</td>
                    <th width="27%">ƽ��ѧ�ֻ�С������</th>
                    <td width="23%">${nzzjMap.pjxfjpm}��</td>
                </tr>
                <tr>
                    <th width="27%">�ۺϲ����ɼ�</th>
                    <td width="23%">${nzzjMap.zhcpcj}��</td>
                    <th width="27%">�ۺϲ����ɼ�����</th>
                    <td width="23%">${nzzjMap.zhcpcjpm}��</td>
                </tr>
                <tr>
                    <th width="27%">�ۺϲ����ɼ��꼶��רҵ���ٷֱ�</th>
                    <td colspan="3">${nzzjMap.njbfb}%</td>
                </tr>
                <tr>
                    <th width="27%">
                        ����<br>
                        <span style="color: red">(��100��)</span>
                    </th>
                    <td colspan="3">
                            ${nzzjMap.qt}
                    </td>
                </tr>
                </tbody>
            </table>
            <table width="100%" border="0" class="formlist">
                <tbody>
                <tr style="">
                    <th width="14%">
                        ��ѧ��γ̳ɼ�
                    </th>
                    <td   colspan="3">
                            ${nzzjMap.zd1}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        ��ȸ��˽����ܽ�
                    </th>
                    <td   colspan="3">
                            ${nzzjMap.zd2}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        ���˽�����̵����¼�
                    </th>
                    <td   colspan="3">
                            ${nzzjMap.zd3}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        ���˻���ϸ
                    </th>
                    <td   colspan="3">
                            ${nzzjMap.zd4}
                    </td>
                </tr>
                </tbody>
            </table>
            <logic:notEqual value="�������" name="nzhbMap" property="shztmc" >
                <table width="100%" border="0" class="formlist">
                    <thead>
                    <tr>
                        <th colspan="4">
                            <span>�����Ϣ</span>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td colspan="4" id="shlccx_nzzj">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </logic:notEqual>
        </logic:equal>
    </div>
</html:form>
<table width="100%" border="0" class="formlist">
    <tfoot>
    <tr>
        <td colspan="4">
            <div class="btn">
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

