<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/cygl/js/cygl.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">

        jQuery(function () {
            djztgb();
        });

    </script>
    <style type="text/css">
    </style>
</head>
<body>
<html:form action="/dzdy_cygl" method="post" styleId="CyglForm">

    <div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;">
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>���ӵ�֧����Ա</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>ѧ��</th>
                <td>
                    <input type="text" name="xh" value="" id="xh" style="width:120px;" readonly="readonly"
                           class="text_nor">
                    <button class="btn_01" type="button"
                            onclick="showDialog('��ѡ��һ��ѧ��',800,500,'dzdy_cygl.do?method=getXx');">ѡ��
                    </button>
                </td>
                <th>����</th>
                <td><span id="xm"></span></td>
            </tr>
            <tr>
                <th width="20%">�Ա�</th>
                <td width="30%"><span id="xb"></span></td>
                <th width="20%">��������</th>
                <td width="30%"><span id="csrq"></span></td>
            </tr>
            <tr>
                <th width="20%">����</th>
                <td width="30%"><span id="jg"></span></td>
                <th width="20%">�꼶</th>
                <td width="30%"><span id="nj"></span></td>
            </tr>
            <tr>
                <th width="20%">����</th>
                <td width="30%"><span id="mzmc"></span></td>
                <th width="20%">��ѧʱ��</th>
                <td width="30%"><span id="rxrq"></span></td>
            </tr>
            <tr>
                <th width="20%">��Ժ</th>
                <td width="30%"><span id="symc"></span></td>
                <th width="20%">רҵ</th>
                <td width="30%"><span id="zymc"></span></td>
            </tr>
            <tr>
                <th width="20%">���֤��</th>
                <td width="30%"><input type="text" id="sfzh" name="sfzh" readonly="readonly" style="border-style: none"/></td>
            </tr>
            <tr>
                <th width="20%">רҵ�༶</th>
                <td width="30%"><span id="zybjmc"></span></td>
                <th width="20%">�����༶</th>
                <td width="30%"><span id="bjmc"></span></td>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
							<span>��ϵ��ʽ
							</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%">��ϵ�绰</th>
                <td width="30%"><span id="lxdh"></span>
                </td>
                <th width="20%">�̶��绰</th>
                <td width="30%"><span id="jtdh"></span></td>
            </tr>
            <tr>
                <th width="20%">��ͥסַ</th>
                <td width="30%"><span id="jtdz"></span></td>
                <th width="20%">����</th>
                <td width="30%"><span id="dzyx"></span></td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>����֯����Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%">�뵳������</th>
                <td width="30%"><html:text property="rdjsr" styleId="rdjsr" style="width:90%"/>
                </td>
                <th width="20%">��֧��</th>
                <td width="30%"><html:select property="dzbid" styleId="dzbid" style="width:152px;">
                    <html:options collection="dzbList" property="dzbid" labelProperty="dzbmc"/>
                </html:select>
                </td>

            </tr>
            <tr>
                <th width="20%">�μӵ���ʱ��</th>
                <td width="30%"><html:text property="cjdksj" value="${minDate}" styleId="cjdksj"
                                           onclick="return showCalendar('cjdksj','y-mm-dd');"
                                           maxlength="10"></html:text></td>
                <th width="20%">��������ʱ��</th>
                <td width="30%"><html:text property="jjfzsj" value="${minDate}" styleId="jjfzsj"
                                           onclick="return showCalendar('jjfzsj','y-mm-dd');"
                                           maxlength="10"></html:text></td>
            </tr>
            <tr>
                <th width="20%">Ԥ����Աʱ��</th>
                <td width="30%"><html:text property="ybdysj" value="${minDate}" styleId="ybdysj"
                                           onclick="return showCalendar('ybdysj','y-mm-dd');"
                                           maxlength="10"></html:text></td>
                <th width="20%">��ʽ��Աʱ��</th>
                <td width="30%"><html:text property="zsygsj" value="${minDate}" styleId="zsygsj"
                                           onclick="return showCalendar('zsygsj','y-mm-dd');"
                                           maxlength="10"></html:text></td>
            </tr>

            <tr>
                <th width="20%">������ò</th>
                <td width="30%">
                    <html:select property="zzmmdm" style="width:152px" styleId="zzmmdm">
                        <option value='01'>�й���������Ա</option>
                        <option value='02'>�й�������Ԥ����Ա</option>
                    </html:select>
                </td>
                <th width="20%">����״̬</th>
                <td width="30%" colspan="3">
                    <html:select property="djzt" style="width:152px" styleId="djzt" onchange="djztgb();">
                        <option value='����'>����</option>
                        <option value='������'>������</option>
                    </html:select>
                </td>

            </tr>
            <tr id="sl" style="display: none">
                <th width="20%">�Ƿ�ʧ��</th>
                <td width="30%">
                    <html:select property="sfsl" style="width:152px" styleId="sfsl" onchange="djslgb();">
                        <option value='0'>��</option>
                        <option value='1'>��</option>
                    </html:select>
                </td>

                <th width="20%" id="sl2" style="display: none">ʧ������</th>
                <td width="30%" id="sl3" style="display: none"><html:text property="slsj" value="${minDate}"
                                                                          styleId="slsj"
                                                                          onclick="return showCalendar('slsj','y-mm-dd');"
                                                                          maxlength="10"></html:text></td>

            </tr>
            <tr id="ld" style="display: none">
                <th width="20%">�Ƿ�����</th>
                <td width="30%">
                    <html:select property="sfld" style="width:152px" styleId="sfld" onchange="djldgb();">
                        <option value='0'>��</option>
                        <option value='1'>��</option>
                    </html:select>
                </td>

                <th width="20%" id="lc2" style="display: none">������</th>
                <td width="30%" id="lc3" style="display: none"><html:text property="lcd" styleId="lcd" maxlength="50"
                                                                          style="width:98%"></html:text></td>

            </tr>
            </tbody>

        </table>
    </div>
    <div style="height:30px;"></div>
    <%--;height:520px --%>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "<span class="red">*</span>"Ϊ������
                    </div>
                    <div class="btn">
                        <button type="button" onclick="saveCy();">
                            �� ��
                        </button>
                        <button type="button" onclick="iFClose();">
                            �ر�
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