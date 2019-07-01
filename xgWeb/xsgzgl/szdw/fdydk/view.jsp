<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript">
        function saveDkxx() {
            var url = "szdw_fdy_fdydkjg.do?method=addDkxx";
            ajaxSubFormWithFun("fdydkForm", url, function (data) {
                showAlertDivLayer(data["message"], {}, {
                    "clkFun": function () {
                        if (parent.window) {
                            refershParent();
                        }
                        iFClose();
                    }
                });
            });
        }

    </script>
</head>
<body style="width:100%">
<html:form action="/szdw_fdy_fdydkjg" method="post" styleId="fdydkForm">
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
                <th width="20%">ְ����</th>
                <td width="30%">
                        ${fdyxx.zgh}
                </td>
                <th width="20%">����</th>
                <td width="30%">${fdyxx.xm}</td>
            </tr>
            <tr>
                <th width="20%">�Ա�</th>
                <td width="30%">${fdyxx.xb}</td>
                <th width="20%">����</th>
                <td width="30%">${fdyxx.mzmc}</td>
            </tr>
            <tr>
                <th width="20%">���ڲ���</th>
                <td width="30%">${fdyxx.bmmc}</td>
                <th width="20%">������Ժ</th>
                <td width="30%">${fdyxx.symc}</td>
            </tr>
            <tr>
                <th width="20%">������ò</th>
                <td width="30%">${fdyxx.zzmmmc}</td>
                <th width="20%">��ϵ�绰</th>
                <td width="30%">${fdyxx.yddh}</td>
            </tr>
            <tr>
                <th>��У����ʱ��</th>
                <td colspan="3">${fdyxx.rxgzsj}</td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>������Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%">�γ����</th>
                <td width="30%">${fdyDkxx.kclbmc}</td>
                <th width="20%">�γ�����</th>
                <td width="30%">${fdyDkxx.kcmc}</td>
            </tr>
            <tr>
                <th width="20%">�γ�ʱ��</th>
                <td width="30%">${fdyDkxx.kcsj}</td>
                <th width="20%">�γ̵ص�</th>
                <td width="30%">${fdyDkxx.kcdd}</td>
            </tr>
            <tr>
                <th width="20%">��ʱ</th>
                <td width="30%" colspan="3">${fdyDkxx.ks}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div>
        <table width="100%" border="0" class="formlist">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">"<span class="red">*</span>"Ϊ������</div>
                    <div class="btn">
                        <button type="button" type="button" onclick="saveDkxx();return false;">
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

