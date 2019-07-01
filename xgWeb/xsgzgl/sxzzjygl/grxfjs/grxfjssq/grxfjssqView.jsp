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
    <script type="text/javascript" src="xsgzgl/sxzzjygl/grxfjs/grxfjssq/js/grxfjssqEdit.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#sqid").val()+"&tt="+ new Date().getTime());
        })
    </script>
    <style type="text/css">
        *{font-size: 14px;}
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/sxzzjy_grxfjssq" method="post"  styleId="form">
    <html:hidden property="sqid" styleId="sqid" />
    <div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>������Ϣ</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
            <tbody id="">
            <tr style="">
                <th colspan="4"  style="text-align: left">
                    ����ѧ�罨��
                </th>
            </tr>
            <tr style="">
                <th >
                   �� ��
                </th>
                <td colspan="3">
                        ${map.xfjsmc}
                </td>
            </tr>
            <tr style="">
                <th >
                    �걨����
                </th>
                <td >
                        ${map.sblxmc}
                </td>
                <th >
                    ѧ��ѧ��
                </th>
                <td>
                        ${map.xn}${map.xqmc}
                </td>
            </tr>
            <tr style="">
                <th>
                    ��ѧ��Ŀ��
                </th>
                <td   colspan="3">
                    <table id="bxnmbTable">
                        <tr>
                            <th width="27%">ƽ��ѧ�ֻ�</th>
                            <td width="23%">${map.pjxfj}��</td>
                            <th width="27%">ƽ��ѧ�ֻ�С������</th>
                            <td width="23%">${map.pjxfjpm}��</td>
                        </tr>
                        <tr>
                            <th width="27%">�ۺϲ����ɼ�</th>
                            <td width="23%">${map.zhcpcj}��</td>
                            <th width="27%">�ۺϲ����ɼ�����</th>
                            <td width="23%">${map.zhcpcjpm}��</td>
                        </tr>
                        <tr>
                            <th width="27%">�ۺϲ����ɼ��꼶��רҵ���ٷֱ�</th>
                            <td >${map.njbfb}%</td>
                            <th width="27%">��������񽱴���</th>
                            <td >${map.sshjcs}��</td>
                        </tr>
                        <tr>
                            <th width="27%">
                                ����<br>
                                <span style="color: red">(��100��)</span>
                            </th>
                            <td colspan="3">
                                    ${map.qt}
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr style="">
                <th>
                    ����˼·�ͼƻ�

                </th>
                <td   colspan="3">${map.jssl}
                </td>
            </tr>
            <tr>
                <th>
                    ����
                </th>
                <td colspan="3">
                    <html:hidden property="fjid" styleId="fjid" value="${map.fjid}"/>
                    <%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
                    <script type="text/javascript">
                        //���ø���
                        jQuery(function(){
                            var gid = jQuery('#fjid').val();
                            jQuery.MultiUploader_q({
                                gid : gid
                            });
                        });
                    </script>
                </td>
            </tr>
            </tbody>
        </table>
        <logic:notEqual value="�������" name="shztmc">
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
                    <td colspan="4" id="shlccx">

                    </td>
                </tr>

                </tbody>

            </table>
        </logic:notEqual>
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

