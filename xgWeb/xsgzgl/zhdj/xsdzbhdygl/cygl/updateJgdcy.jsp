<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/cygl/js/jgdcygl.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">

        jQuery(function ($) {
            var zzmm = $('#selzzmmdm').val();
            //������ò
            jQuery("#zzmmdm option").each(function () {
                if (zzmm == ($(this).val())) {
                    $(this).attr('selected', true);
                }
            });
            //����״̬
            var djzt = $('#seldjzt').val();
            jQuery("#djzt option").each(function () {
                if (djzt == ($(this).val())) {
                    $(this).attr('selected', true);
                }
            });

            //�Ƿ�ʧ��
            var sfsl = $('#selsfsl').val();
            jQuery("#sfsl option").each(function () {
                if (sfsl == ($(this).val())) {
                    $(this).attr('selected', true);
                }
            });


            //�Ƿ�����

            var sfld = $('#selsfld').val();
            jQuery("#sfld option").each(function () {
                if (sfld == ($(this).val())) {
                    $(this).attr('selected', true);
                }
            });

            //��֧��
            var dabid = $('#seldzbid').val();
            jQuery("#dzbid option").each(function () {
                if (dabid == ($(this).val())) {
                    $(this).attr('selected', true);
                }
            });

            djztgb();
            djslgb();
            djldgb();

        });

        //            jQuery(function(){
        //                djztgb();
        //            });

    </script>
    <style type="text/css">
    </style>
</head>
<body>
<html:form action="/dzdy_cygl" method="post" styleId="JgdcyglForm">

    <div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;">
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>�޸Ľ̹���֧����Ա��Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>ѧ��</th>
                <td>
                        <%--<input type="text" name="xh" value="${cyMap.xh}" id="xh" style="width:120px;" readonly="readonly" class="text_nor">--%>
                        ${cyMap.xh}
                    <html:hidden property="xh" styleId="xh" value="${cyMap.xh}"></html:hidden>
                        <%--<button class="btn_01" type="button" onclick="showDialog('��ѡ��һ��ѧ��',800,500,'dzdy_cygl.do?method=getXx');">ѡ��</button>--%>
                </td>
                <th>����</th>
                <td><span id="xm">${cyMap.xm}</span></td>
            </tr>
            <tr>
                <th width="20%">�Ա�</th>
                <td width="30%"><span id="xb">${cyMap.xb}</span></td>
                <th width="20%">��������</th>
                <td width="30%"><span id="bmmc">${cyMap.bmmc}</span></td>
            </tr>
            <tr>
                <th width="20%">����</th>
                <td width="30%"><span id="jg">${cyMap.jg}</span></td>
                <th width="20%">��Ժ</th>
                <td width="30%"><span id="symc">${cyMap.symc}</span></td>
            </tr>
            <tr>
                <th width="20%">��ϵ�绰</th>
                <td width="30%"><span id="lxdh">${cyMap.lxdh}</span></td>
                <th width="20%">��ͥסַ</th>
                <td width="30%"><span id="jtzz">${cyMap.jtzz}</span></td>
            </tr>
            <tr>
                <th width="20%">��������</th>
                <td colspan="3"><span id="dzyx">${cyMap.dzyx}</span></td>
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
                <td width="30%"><html:text property="rdjsr" styleId="rdjsr" style="width:90%" value="${cyMap.rdjsr}"/></td>
                <th width="20%">��֧��</th>
                <td width="30%">
                    <input type="hidden" id="seldzbid" value="${cyMap.dzbid}">
                    <html:select property="dzbid" styleId="dzbid" style="width:152px;">
                        <html:options collection="dzbList" property="dzbid" labelProperty="dzbmc"/>
                    </html:select>
                </td>

            </tr>
            <tr>
                <th width="20%">�μӵ���ʱ��</th>
                <td width="30%"><html:text property="cjdksj" value="${cyMap.cjdksj}" styleId="cjdksj"
                                           onclick="return showCalendar('cjdksj','y-mm-dd');"
                                           maxlength="10"></html:text></td>
                <th width="20%">��������ʱ��</th>
                <td width="30%"><html:text property="jjfzsj" value="${cyMap.jjfzsj}" styleId="jjfzsj"
                                           onclick="return showCalendar('jjfzsj','y-mm-dd');"
                                           maxlength="10"></html:text></td>
            </tr>
            <tr>
                <th width="20%">Ԥ����Աʱ��</th>
                <td width="30%"><html:text property="ybdysj" value="${cyMap.ybdysj}" styleId="ybdysj"
                                           onclick="return showCalendar('ybdysj','y-mm-dd');"
                                           maxlength="10"></html:text></td>
                <th width="20%">��ʽ��Աʱ��</th>
                <td width="30%"><html:text property="zsygsj" value="${cyMap.zsygsj}" styleId="zsygsj"
                                           onclick="return showCalendar('zsygsj','y-mm-dd');"
                                           maxlength="10"></html:text></td>
            </tr>

            <tr>
                <th width="20%">������ò</th>
                <td width="30%">
                    <input type="hidden" id="selzzmmdm" value="${cyMap.zzmmdm}"/>
                    <html:select property="zzmmdm" style="width:152px" styleId="zzmmdm">
                        <option value='01'>�й���������Ա</option>
                        <option value='02'>�й�������Ԥ����Ա</option>
                    </html:select>
                </td>
                <th width="20%">����״̬</th>
                <td width="30%" colspan="3">
                    <input type="hidden" id="seldjzt" value="${cyMap.djzt}"/>
                    <html:select property="djzt" style="width:152px" styleId="djzt" onchange="djztgb();">
                        <option value='����'>����</option>
                        <option value='������'>������</option>
                    </html:select>
                </td>

            </tr>
            <tr id="sl" style="display: none">
                <th width="20%">�Ƿ�ʧ��</th>
                <td width="30%">
                    <input type="hidden" id="selsfsl" value="${cyMap.sfsl}"/>
                    <html:select property="sfsl" style="width:152px" styleId="sfsl" onchange="djslgb();">
                        <option value='0'>��</option>
                        <option value='1'>��</option>
                    </html:select>
                </td>

                <th width="20%" id="sl2" style="display: none">ʧ������</th>
                <td width="30%" id="sl3" style="display: none"><html:text property="slsj" value="${cyMap.slsj}"
                                                                          styleId="slsj"
                                                                          onclick="return showCalendar('slsj','y-mm-dd');"
                                                                          maxlength="10"></html:text></td>

            </tr>
            <tr id="ld" style="display: none">
                <th width="20%">�Ƿ�����</th>
                <td width="30%">
                    <input type="hidden" id="selsfld" value="${cyMap.sfld}"/>
                    <html:select property="sfld" style="width:152px" styleId="sfld" onchange="djldgb();">
                        <option value='0'>��</option>
                        <option value='1'>��</option>
                    </html:select>
                </td>

                <th width="20%" id="lc2" style="display: none">������</th>
                <td width="30%" id="lc3" style="display: none"><html:text property="lcd" styleId="lcd" maxlength="50" value="${cyMap.lcd}"
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
                        <button type="button" onclick="updateSaveCy();">
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