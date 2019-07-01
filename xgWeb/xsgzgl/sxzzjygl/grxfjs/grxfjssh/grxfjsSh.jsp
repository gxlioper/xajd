<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/grxfjs/grxfjssh/js/grxfjsshEdit.js"></script>
    <script type="text/javascript">
        jQuery(function(){

            var sqid = jQuery("#sqid").val();
            var splc = jQuery("#splc").val();
            var shid = jQuery("#shid").val();
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+sqid+"&tt="+new Date().getTime());
            jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+splc+"&shid="+shid);
        });
    </script>
</head>
<body>
<html:form action="/sxzzjy_grxfjssh" method="post" styleId="GrxfjsshForm">
    <div id="filepathHiddenDiv" style="display: none;">
        <div id="commonfileupload-list-0" style="padding: 5px;"></div>
    </div>
    <html:hidden property="sqid" styleId="sqid"/>
    <html:hidden property="splc" styleId="splc"/>
    <html:hidden property="shid" styleId="shid"/>
    <div style="height:460px;overflow-x:hidden;overflow-y:auto;">
        <div class="tab"  id="content" style="margin-top: 5px; overflow-x:hidden;" >

        </div>
        <table width="100%" border="0" class="formlist">
            <thead>
            <thead>
            <tr>
                <th colspan="4">
                    <span>������Ϣ</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span>
                        <logic:equal name="sqlx" value="sq">����ѧ�罨��</logic:equal>
                        <logic:equal name="sqlx" value="nzhb">���ڻ㱨</logic:equal>
                        <logic:equal name="sqlx" value="nzzj">�����ܽ�</logic:equal>
                    </span>
                </th>
            </tr>
            </thead>
            <tbody>
        <logic:equal name="sqlx" value="sq">
            <tr style="">
                <th width="14%">
                    �� ��
                </th>
                <td colspan="3">
                        ${map.xfjsmc}
                </td>
            </tr>
            <tr style="">
                <th width="14%">
                    �걨����
                </th>
                <td width="36%">
                        ${map.sblxmc}
                </td>
                <th width="14%">
                    ѧ��ѧ��
                </th>
                <td width="36%">
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
                    ����˼·�ͼƻ�<br>
                    <span style="color: red">(��500��)</span>
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
        </logic:equal>
            </tbody>
        </table>
        <logic:notEqual name="sqlx" value="sq">
            <table width="100%" border="0" class="formlist">
                <tbody>
                <tr style="">
                    <th colspan="4"  style="text-align: left">
                        ����ܽ�
                    </th>
                </tr>
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
                    <td colspan="3">${map.njbfb}%</td>

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
                </tbody>
            </table>
            <table width="100%" border="0" class="formlist">
                <tbody>
                <tr style="">
                    <th width="14%">
                        <logic:equal name="sqlx" value="nzhb">��һѧ�ڿγ̳ɼ�</logic:equal>
                        <logic:equal name="sqlx" value="nzzj">��ѧ��γ̳ɼ�</logic:equal>
                    </th>
                    <td   colspan="3">
                            ${map.zd1}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        <logic:equal name="sqlx" value="nzhb">�����ܽ�</logic:equal>
                        <logic:equal name="sqlx" value="nzzj">��ȸ��˽����ܽ�</logic:equal>
                    </th>
                    <td   colspan="3">
                            ${map.zd2}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        <logic:equal name="sqlx" value="nzhb">���˽�������������</logic:equal>
                        <logic:equal name="sqlx" value="nzzj">���˽�����̵����¼�</logic:equal>
                    </th>
                    <td   colspan="3">
                            ${map.zd3}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        <logic:equal name="sqlx" value="nzhb">���˽������ķ���</logic:equal>
                        <logic:equal name="sqlx" value="nzzj">���˻���ϸ</logic:equal>
                    </th>
                    <td   colspan="3">
                            ${map.zd4}
                    </td>
                </tr>
                </tbody>
            </table>
        </logic:notEqual>

        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>�����ʷ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="4" id="shlccx">

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
            <tbody>
            <tr>
                <th>
                    <font color="red">*</font>��˽��
                </th>
                <td colspan="3" id="shjgSpan">

                </td>
            </tr>
            <tr>
                <th width="20%">
                    <font color="red">*&nbsp;</font> ������
                    <br />
                    <font color="red">(��200��)</font>
                </th>
                <td colspan="3">
                    <jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=ylbx&id=shyj" />
                    <textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <table width="100%" border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="btn">
                    <button type="button" name="����"  onclick="saveShzt();return false;">
                        �� ��
                    </button>
                    <button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
                        �� ��
                    </button>
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
    <!-- ��ʾ��Ϣ -->
    <%@ include file="/comm/other/tsxxNew.jsp"%>
</html:form>
</body>
</html>
