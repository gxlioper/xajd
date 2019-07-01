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
                    <html:text property="zgh" styleId="zgh" style="width:100px" value="${fdyxx.zgh}" readonly="true"/>
                    <button type="button"
                            onclick="showDialog('��ʦѡ��',680,480,'szdw_fdyjtff.do?method=showFdys&goto=szdw_fdy_fdydkjg.do?method=add');return false;"
                            class="btn_01" id="buttonFindStu">
                        ѡ��
                    </button>
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
                <td width="30%">
                    <html:select property="kclbdm" styleId="kclbdm" style="width:88%">
                        <html:option value=""></html:option>
                        <html:options collection="kcList" property="dm" labelProperty="mc"/>
                    </html:select>
                </td>
                <th width="20%">�γ�����</th>
                <td width="30%"><html:text property="kcmc" styleId="kcmc" styleClass="inputtext" style="width:88%"/>
            </tr>
            <tr>
                <th width="20%">�γ�ʱ��</th>
                <td width="30%">
                    <html:text property="kcsj" value="${minDate}" styleId="kcsj"
                               onclick="return showCalendar('kcsj','y-mm-dd');"
                               maxlength="10"/>
                </td>
                <th width="20%">�γ̵ص�</th>
                <td width="30%"><html:text property="kcdd" styleId="kcdd" styleClass="inputtext" style="width:88%"/>
            </tr>
            <tr>
                <th width="20%">��ʱ</th>
                <td width="30%"><html:text property="ks" styleId="ks" styleClass="inputtext" style="width:88%"/></td>
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

