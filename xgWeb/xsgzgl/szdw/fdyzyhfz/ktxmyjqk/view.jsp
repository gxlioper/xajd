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
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript">

    </script>
</head>
<body style="width:100%">
<html:form action="/szdw_fdy_ktxmyjqkwh" method="post" styleId="demoForm">
    <div style='width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
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
                    <th>ְ����</th>
                    <input type="hidden" name="zgh" value="${fdyxx.zgh}">
                    <td>${fdyxx.zgh}</td>
                    <th>����</th>
                    <td>${fdyxx.xm}</td>
                </tr>
                <tr>
                    <th>�Ա�</th>
                    <td>${fdyxx.xb}</td>
                    <th>����</th>
                    <td>${fdyxx.mzmc}</td>
                </tr>
                <tr>
                    <th>���ڲ���</th>
                    <td>${fdyxx.bmmc}</td>
                    <th>������Ժ</th>
                    <td>${fdyxx.symc}</td>
                </tr>
                <tr>
                    <th>������ò</th>
                    <td>${fdyxx.zzmmmc}</td>
                    <th>��ϵ�绰</th>
                    <td>${fdyxx.lxdh}</td>
                </tr>
                <tr>
                    <th>��У����ʱ��</th>
                    <td colspan="3">${fdyxx.rxgzsj}</td>
                </tr>
            </tbody>
            <thead>
                <tr>
                    <th colspan="4">
                        <span>������Ŀ��Ϣ</span>
                    </th>
                </tr>
            </thead>
            <tbody>
            <tr>
                <th>��Ŀ���</th>
                <td colspan="3">
                        ${model.xmbh}
                </td>
            </tr>
            <tr>
                <th>����</th>
                <td>
                    ${model.mc}
                </td>
                <th>��Դ</th>
                <td>
                        ${model.xmly}
                </td>
            </tr>
            <tr>
                <th>��ʼʱ��</th>
                <td>
                        ${model.kssj}
                </td>
                <th>����ʱ��</th>
                <td>
                        ${model.jssj}
                </td>
            </tr>
            <tr>
                <th>��Ŀ����</th>
                <td>
                        ${model.xmjf}
                </td>
                <th>����ʱ��</th>
                <td>
                        ${model.sqsj}
                </td>
            </tr>
            <tr>
                <th>���˳е���ɫ</th>
                <td>
                        ${model.cdjsmc}
                </td>
                <th>�Ƿ����</th>
                <td>
                        ${model.sfjxmc}
                </td>
            </tr>
            <tr id="jxsjTr">
                <th>����ʱ��</th>
                <td colspan="3">
                        ${model.jxsj}
                </td>
            </tr>
            <tr>
                <th>������</th>
                <td colspan="3">
                        ${model.jxjg}
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
                    <div class="btn">
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

