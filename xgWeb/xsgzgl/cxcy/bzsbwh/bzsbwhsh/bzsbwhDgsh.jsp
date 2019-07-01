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
    <script type="text/javascript" src="xsgzgl/cxcy/bzsbwh/bzsbwhsh/js/bzsbwhsh.js"></script>
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
<html:form action="/cxcy_bzsbwhsh" method="post" styleId="form">
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
                    <span>ѧ��������Ϣ</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span>
                        ������Ϣ
                    </span>
                </th>
            </tr>
            </thead>
            <tbody>
                <tr style="">
                    <th width="14%">
                        ��Ŀ����
                    </th>
                    <td width="36%">
                            ${map.xmmc}
                    </td>
                    <th width="14%">
                        �������
                    </th>
                    <td width="36%">
                            ${map.bzje}
                    </td>
                </tr>
                <tr style="">
                    <th width="14%">
                        ѧ��
                    </th>
                    <td width="36%">
                            ${map.xn}
                    </td>
                    <th width="14%">
                        ѧ��
                    </th>
                    <td width="36%">
                            ${xqmc}
                    </td>
                </tr>
                <tr style="">
                    <th width="14%">
                        ���
                    </th>
                    <td width="36%">
                            ${tbrmc}
                    </td>
                    <th width="14%">
                       �ʱ��
                    </th>
                    <td width="36%">
                            ${map.sqsj}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        ��������
                    </th>
                    <td   colspan="3">
                            ${map.sqly}
                    </td>
                </tr>
                <tr>
                    <th>
                        ����
                    </th>
                    <td colspan="3">
                        <html:hidden property="fj" styleId="fj" value="${map.fj}"/>
                        <%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
                        <script type="text/javascript">
                            //���ø���
                            jQuery(function(){
                                var gid = jQuery('#fj').val();
                                jQuery.MultiUploader_q({
                                    gid : gid
                                });
                            });
                        </script>
                    </td>
                </tr>
            </tbody>
        </table>

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
                    <button type="button" name="����"  onclick="saveSh();return false;">
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
