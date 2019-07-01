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
    <script type="text/javascript" src="xsgzgl/sxzzjygl/bjxfjs/bjxfjssh/js/bjxfjsshEdit.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var dybl = 0;
            var dyrs = "${bjmap.dyrs}";
            var bjzrs = "${bjmap.bjzrs}";
            if(dyrs > 0 && bjzrs>0)
                dybl = (dyrs*100/bjzrs).toFixed(2)+"%";
            jQuery("#dyblTD").html(dybl);

            var sqid = jQuery("#sqid").val();
            var splc = jQuery("#splc").val();
            var shid = jQuery("#shid").val();
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+sqid+"&tt="+new Date().getTime());
            jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+splc+"&shid="+shid);
        });
    </script>
</head>
<body>
<html:form action="/sxzzjy_bjxfjssh" method="post" styleId="BjxfjsshForm">
    <div id="filepathHiddenDiv" style="display: none;">
        <div id="commonfileupload-list-0" style="padding: 5px;"></div>
    </div>
    <html:hidden property="sqid" styleId="sqid"/>
    <html:hidden property="splc" styleId="splc"/>
    <html:hidden property="shid" styleId="shid"/>
    <html:hidden property="sqlx" styleId="sqlx"/>
    <div style="height:460px;overflow-x:hidden;overflow-y:auto;">
        <div class="tab"  id="content" style="margin-top: 5px; overflow-x:hidden;" >

        </div>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>�༶��Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr style="">
                <th width="14%">
                     �༶
                </th>
                <td colspan="3" >
                        ${bjmap.bjmc}
                    <input type="hidden" id="bjmc" name="bjmc" value="${bjmap.bjmc}"/>
                    <html:hidden property="bjdm" styleId="bjdm" />
                </td>
            </tr>
            <tr style="">
                <th width="14%">
                    ѧԺ
                </th>
                <td width="36%" id="xyTd">
                        ${bjmap.xymc}
                </td>
                <th width="14%">
                    �༶����
                </th>
                <td width="36%" id="bjrsTd">
                    ��${bjmap.bjzrs}�ˣ���${bjmap.nansrs}�ˣ�Ů${bjmap.nvsrs}�ˣ�

                </td>
            </tr>
            <tr>
                <th >
                    ��Ա��
                </th>
                <td  id="dysTD">
                        ${bjmap.dyrs}
                </td>
                <th >
                    ��Ա����
                </th>
                <td  id="dyblTD">

                </td>

            </tr>
            <tr>
                <th >
                    ����Ա
                </th>
                <td  id="fdyTD">
                        ${bjmap.fdyxm}
                </td>
                <th >
                    ������
                </th>
                <td  id="bzrTD">
                        ${bjmap.bzrxm}
                </td>

            </tr>
            <tr>
                <th >
                    �೤
                </th>
                <td  id="bzTD">
                        ${bjmap.bzxm}
                </td>
                <th >
                    ��֧��
                </th>
                <td  id="tzsTD">
                        ${bjmap.tzsxm}
                </td>

            </tr>
            </tbody>
        </table>
    <logic:equal name="sqlx" value="sq">
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>�༶ѧ�罨��</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr width="14%">
                <th >
                    �� ��
                </th>
                <td colspan="3">
                        ${map.xfjsmc}
                </td>
            </tr>
            <tr style="">
                <th width="14%">
                    <span style="color: red">*</span>�걨����
                </th>
                <td width="36%">
                        ${map.sblxmc}
                            <html:hidden property="sblx" styleId="sblx" value="${map.sblx}"/>
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
                            <th>�༶ƽ��ѧ�ֻ�</th>
                            <td width="20%">${map.pjxfj}</td>
                            <td colspan="2">�༶ѧ�ֻ����꼶��רҵ��
                                    ${map.njzy}��
                                    ${map.zyxbgs}��С����������
                                    ${map.pjxfjpm}��
                            </td>
                        </tr>
                        <tr>
                            <th>�༶Ӣ���ļ�ͨ����</th>
                            <td>${map.sjtgl}</td>
                            <td colspan="2">�༶Ӣ���ļ�ͨ�������꼶
                                    ${map.njxbgs}��С����������
                                    ${map.sjtglpm}��
                            </td>
                        </tr>
                        <tr>
                            <th>�������Ŵ�</th>
                            <td>${map.bjgmc}��</td>
                            <th>����������</th>
                            <td>${map.bjgrs}��</td>
                        </tr>
                        <tr>
                            <th>�������˴�</th>
                            <td>${map.bjgrc}�˴�</td>
                            <th>��ɲ�ѧϰ�ɼ�ǰ����</th>
                            <td>${map.bgbqwrs}��</td>

                        </tr>
                        <tr>
                            <th>��ɲ�ѧϰ�ɼ�ǰʮ��</th>
                            <td>${map.bgbqsrs}��</td>
                            <th>��ѧ��</th>
                            <td>${map.hjxsrs}��</td>
                        </tr>
                        <tr>
                            <th>���影</th>
                            <td>${map.hjtjgs}��</td>
                            <th>���ʵ����</th>
                            <td>${map.shsjhjrc}�˴�</td>
                        </tr>
                        <tr>
                            <th>�����</th>
                            <td>${map.sshjcs}��</td>
                            <th>��֯ȫ�༯��</th>
                            <td>${map.qbjthdcs}��</td>
                        </tr>
                        <tr>
                            <th>�Ƽ�ѧ����</th>
                            <td>${map.kjxshjrc}�˴�</td>
                            <th>��֯�༶ͬѧ�μ�УԺ�</th>
                            <td>${map.cjxyhdcs}��</td>
                        </tr>
                        <tr>
                            <th>����ͬѧ</th>
                            <td>${map.jjtxrs}��</td>
                            <th>�Զ�ѧ��</th>
                            <td>${map.sdxsrs}��</td>
                        </tr>
                        <tr>
                            <th>��ѧ</th>
                            <td colspan="3">${map.txrs}��</td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr style="">
                <th>
                    ����˼·�ͼƻ�

                </th>
                <td   colspan="3">
                        ${map.jssl}
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
    </logic:equal>
    <logic:notEqual name="sqlx" value="sq">
        <table width="100%" border="0" class="formlist">
            <tbody>
            <tr style="">
                <th colspan="4"  style="text-align: left">
                    <logic:equal name="sqlx" value="nzhb">���ڻ㱨</logic:equal>
                    <logic:equal name="sqlx" value="nzzj">����ܽ�</logic:equal>

                </th>
            </tr>
            <tr>
                <th width="21%">�༶ƽ��ѧ�ֻ�</th>
                <td width="5%">${map.pjxfj}</td>
                <td colspan="2">�༶ѧ�ֻ����꼶��רҵ��
                        ${map.njzy}��
                        ${map.zyxbgs}��С����������
                        ${map.pjxfjpm}��
                </td>
            </tr>
            <tr>
                <th width="21%">�༶Ӣ���ļ�ͨ����</th>
                <td width="5%">${map.sjtgl}</td>
                <td colspan="2" >�༶Ӣ���ļ�ͨ�������꼶
                        ${map.njxbgs}��С����������
                        ${map.sjtglpm}��
                </td>
            </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist bxnmb" >
            <tbody>
            <tr>
                <th width="27%">�������Ŵ�</th>
                <td width="23%">${map.bjgmc}��</td>
                <th width="27%">����������</th>
                <td width="23%">${map.bjgrs}��</td>
            </tr>
            <tr>
                <th>�������˴�</th>
                <td>${map.bjgrc}�˴�</td>
                <th>��ɲ�ѧϰ�ɼ�ǰ����</th>
                <td>${map.bgbqwrs}��</td>

            </tr>
            <tr>
                <th>��ɲ�ѧϰ�ɼ�ǰʮ��</th>
                <td>${map.bgbqsrs}��</td>
                <th>��ѧ��</th>
                <td>${map.hjxsrs}��</td>
            </tr>
            <tr>
                <th>���影</th>
                <td>${map.hjtjgs}��</td>
                <th>���ʵ����</th>
                <td>${map.shsjhjrc}�˴�</td>
            </tr>
            <tr>
                <th>�����</th>
                <td>${map.sshjcs}��</td>
                <th>��֯ȫ�༯��</th>
                <td>${map.qbjthdcs}��</td>
            </tr>
            <tr>
                <th>�Ƽ�ѧ����</th>
                <td>${map.kjxshjrc}�˴�</td>
                <th>��֯�༶ͬѧ�μ�УԺ�</th>
                <td>${map.cjxyhdcs}��</td>
            </tr>
            <tr>
                <th>����ͬѧ</th>
                <td>${map.jjtxrs}��</td>
                <th>�Զ�ѧ��</th>
                <td>${map.sdxsrs}��</td>
            </tr>
            <tr>
                <th>��ѧ</th>
                <td colspan="3">${map.txrs}��</td>
            </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist">
            <tbody>
            <tr style="">
                <th width="14%">
                    <logic:equal name="sqlx" value="nzhb">�����ܽ�</logic:equal>
                    <logic:equal name="sqlx" value="nzzj">��Ȱ༶�����ܽ�</logic:equal>

                </th>
                <td   colspan="3">
                        ${map.zd1}
                </td>
            </tr>
            <tr style="">
                <th>
                    <logic:equal name="sqlx" value="nzhb">�༶��������������</logic:equal>
                    <logic:equal name="sqlx" value="nzzj">�༶������̵����¼�</logic:equal>

                </th>
                <td   colspan="3">
                        ${map.zd2}
                </td>
            </tr>
            <tr style="">
                <th>
                    <logic:equal name="sqlx" value="nzhb">�༶�������ķ���</logic:equal>
                    <logic:equal name="sqlx" value="nzzj">�༶����͸��˻���ϸ</logic:equal>

                </th>
                <td   colspan="3">
                        ${map.zd3}
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
