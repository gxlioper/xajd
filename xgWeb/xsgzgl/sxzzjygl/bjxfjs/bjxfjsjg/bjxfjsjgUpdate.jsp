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
    <script type="text/javascript" src="xsgzgl/sxzzjygl/bjxfjs/bjxfjsjg/js/bjxfjsjgEdit.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var dybl = 0;
            var dyrs = "${map.dyrs}";
            var bjzrs = "${map.bjzrs}";
            if(dyrs > 0 && bjzrs>0)
                dybl = (dyrs*100/bjzrs).toFixed(2)+"%";
            jQuery("#dyblTD").html(dybl);

        })
    </script>
    <style type="text/css">
        *{font-size: 14px;}
        textarea{width: 98%}
        #bxnmbTable input{width: 50px;}
    </style>
</head>
<body>
<html:form action="/sxzzjy_bjxfjsjg" method="post"  styleId="form">
    <div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
        <html:hidden property="jgid" styleId="jgid" />
        <table width="100%" border="0" class="formlist">
            <tbody id="">
            <tr>
                <th colspan="4" style="text-align: left">
                    �༶��Ϣ
                </th>

            </tr>

            <tr style="">
                <th width="14%">
                    <span style="color: red">*</span> �༶
                </th>
                <td colspan="3">
                        ${map.bjmc}
                    <input type="hidden" id="bjmc" name="bjmc" value="${map.bjmc}"/>
                    <html:hidden property="bjdm" styleId="bjdm" />
                </td>
            </tr>
            <tr style="">
                <th width="14%">
                    ѧԺ
                </th>
                <td width="36%" id="xyTd">
                        ${map.xymc}
                </td>
                <th width="14%">
                    �༶����
                </th>
                <td width="36%" id="bjrsTd">
                    ��${map.bjzrs}�ˣ���${map.nansrs}�ˣ�Ů${map.nvsrs}�ˣ�

                </td>
            </tr>
            <tr>
                <th >
                    ��Ա��
                </th>
                <td  id="dysTD">
                        ${map.dyrs}
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
                        ${map.fdyxm}
                </td>
                <th >
                    ������
                </th>
                <td  id="bzrTD">
                        ${map.bzrxm}
                </td>

            </tr>
            <tr>
                <th >
                    �೤
                </th>
                <td  id="bzTD">
                        ${map.bzxm}
                </td>
                <th >
                    ��֧��
                </th>
                <td  id="tzsTD">
                        ${map.tzsxm}
                </td>

            </tr>
            <tr style="">
                <th colspan="4"  style="text-align: left">
                    �༶ѧ�罨��
                </th>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span>�� ��
                </th>
                <td colspan="3">
                    <logic:equal name="sjly" value="1">
                        ${jgmap.xfjsmc}
                    </logic:equal>
                    <logic:equal name="sjly" value="0">
                        <html:text property="xfjsmc" styleId="xfjsmc" style="width:60%;" />
                    </logic:equal>
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span>�걨����
                </th>
                <td >
                    <logic:equal name="sjly" value="1">
                        ${jgmap.sblxmc}
                    </logic:equal>
                    <logic:equal name="sjly" value="0">
                        <html:select property="sblx" styleId="sblx" style="width:150px" styleClass="select">
                            <html:option value=""></html:option>
                            <html:options collection="sblxList" property="sblxdm"
                                          labelProperty="sblxmc" />
                        </html:select>
                    </logic:equal>

                </td>
                <th >
                    ѧ��ѧ��
                </th>
                <td>
                        ${jgmap.xn}${jgmap.xqmc}
                    <html:hidden property="xn" styleId="xn"/>
                    <html:hidden property="xq" styleId="xq"/>
                </td>
            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>��ѧ��Ŀ��
                </th>
                <td   colspan="3">
                    <logic:equal name="sjly" value="1">
                        <table id="bxnmbTable">
                            <tr>
                                <th>�༶ƽ��ѧ�ֻ�</th>
                                <td width="20%">${map.pjxfj}</td>
                                <td colspan="2">�༶ѧ�ֻ����꼶��רҵ��
                                        ${jgmap.njzy}��
                                        ${jgmap.zyxbgs}��С����������
                                        ${jgmap.pjxfjpm}��
                                </td>
                            </tr>
                            <tr>
                                <th>�༶Ӣ���ļ�ͨ����</th>
                                <td>${jgmap.sjtgl}</td>
                                <td colspan="2">�༶Ӣ���ļ�ͨ�������꼶
                                        ${jgmap.njxbgs}��С����������
                                        ${jgmap.sjtglpm}��
                                </td>
                            </tr>
                            <tr>
                                <th>�������Ŵ�</th>
                                <td>${jgmap.bjgmc}��</td>
                                <th>����������</th>
                                <td>${jgmap.bjgrs}��</td>
                            </tr>
                            <tr>
                                <th>�������˴�</th>
                                <td>${jgmap.bjgrc}�˴�</td>
                                <th>��ɲ�ѧϰ�ɼ�ǰ����</th>
                                <td>${jgmap.bgbqwrs}��</td>

                            </tr>
                            <tr>
                                <th>��ɲ�ѧϰ�ɼ�ǰʮ��</th>
                                <td>${jgmap.bgbqsrs}��</td>
                                <th>��ѧ��</th>
                                <td>${jgmap.hjxsrs}��</td>
                            </tr>
                            <tr>
                                <th>���影</th>
                                <td>${jgmap.hjtjgs}��</td>
                                <th>���ʵ����</th>
                                <td>${jgmap.shsjhjrc}�˴�</td>
                            </tr>
                            <tr>
                                <th>�����</th>
                                <td>${jgmap.sshjcs}��</td>
                                <th>��֯ȫ�༯��</th>
                                <td>${jgmap.qbjthdcs}��</td>
                            </tr>
                            <tr>
                                <th>�Ƽ�ѧ����</th>
                                <td>${jgmap.kjxshjrc}�˴�</td>
                                <th>��֯�༶ͬѧ�μ�УԺ�</th>
                                <td>${jgmap.cjxyhdcs}��</td>
                            </tr>
                            <tr>
                                <th>����ͬѧ</th>
                                <td>${jgmap.jjtxrs}��</td>
                                <th>�Զ�ѧ��</th>
                                <td>${jgmap.sdxsrs}��</td>
                            </tr>
                            <tr>
                                <th>��ѧ</th>
                                <td colspan="3">${jgmap.txrs}��</td>
                            </tr>
                        </table>
                    </logic:equal>
                    <logic:equal name="sjly" value="0">
                        <table id="bxnmbTable">
                            <tr>
                                <th>�༶ƽ��ѧ�ֻ�</th>
                                <td width="20%"><html:text property="pjxfj" styleId="pjxfj"/></td>
                                <td colspan="2">�༶ѧ�ֻ����꼶��רҵ��
                                    <html:text property="njzy" styleId="njzy"/>��
                                    <html:text property="zyxbgs" styleId="zyxbgs"/>��С����������
                                    <html:text property="pjxfjpm" styleId="pjxfjpm"/>��
                                </td>
                            </tr>
                            <tr>
                                <th>�༶Ӣ���ļ�ͨ����</th>
                                <td><html:text property="sjtgl" styleId="sjtgl"/></td>
                                <td colspan="2">�༶Ӣ���ļ�ͨ�������꼶
                                    <html:text property="njxbgs" styleId="njxbgs"/>��С����������
                                    <html:text property="sjtglpm" styleId="sjtglpm"/>��
                                </td>
                            </tr>
                            <tr>
                                <th>�������Ŵ�</th>
                                <td><html:text property="bjgmc" styleId="bjgmc"/>��</td>
                                <th>����������</th>
                                <td><html:text property="bjgrs" styleId="bjgrs"/>��</td>
                            </tr>
                            <tr>
                                <th>�������˴�</th>
                                <td><html:text property="bjgrc" styleId="bjgrc"/>�˴�</td>
                                <th>��ɲ�ѧϰ�ɼ�ǰ����</th>
                                <td><html:text property="bgbqwrs" styleId="bgbqwrs"/>��</td>

                            </tr>
                            <tr>
                                <th>��ɲ�ѧϰ�ɼ�ǰʮ��</th>
                                <td><html:text property="bgbqsrs" styleId="bgbqsrs"/>��</td>
                                <th>��ѧ��</th>
                                <td><html:text property="hjxsrs" styleId="hjxsrs"/>��</td>
                            </tr>
                            <tr>
                                <th>���影</th>
                                <td><html:text property="hjtjgs" styleId="hjtjgs"/>��</td>
                                <th>���ʵ����</th>
                                <td><html:text property="shsjhjrc" styleId="shsjhjrc"/>�˴�</td>
                            </tr>
                            <tr>
                                <th>�����</th>
                                <td><html:text property="sshjcs" styleId="sshjcs"/>��</td>
                                <th>��֯ȫ�༯��</th>
                                <td><html:text property="qbjthdcs" styleId="qbjthdcs"/>��</td>
                            </tr>
                            <tr>
                                <th>�Ƽ�ѧ����</th>
                                <td><html:text property="kjxshjrc" styleId="kjxshjrc"/>�˴�</td>
                                <th>��֯�༶ͬѧ�μ�УԺ�</th>
                                <td><html:text property="cjxyhdcs" styleId="cjxyhdcs"/>��</td>
                            </tr>
                            <tr>
                                <th>����ͬѧ</th>
                                <td><html:text property="jjtxrs" styleId="jjtxrs"/>��</td>
                                <th>�Զ�ѧ��</th>
                                <td><html:text property="sdxsrs" styleId="sdxsrs"/>��</td>
                            </tr>
                            <tr>
                                <th>��ѧ</th>
                                <td colspan="3"><html:text property="txrs" styleId="txrs"/>��</td>
                            </tr>
                        </table>
                    </logic:equal>

                </td>
            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>����˼·�ͼƻ�<br>
                    <span style="color: red">(��500��)</span>
                </th>
                <td   colspan="3">
                    <logic:equal name="sjly" value="1">
                        ${jgmap.jssl}
                    </logic:equal>
                    <logic:equal name="sjly" value="0">
                        <html:textarea property="jssl" style="width:98%;margin-top:5px" rows="5"
                                       onblur="checkLen(this,500);" styleId="jssl"
                        ></html:textarea>
                    </logic:equal>


                </td>
            </tr>
            <tr>
                <th>
                    ����
                </th>
                <td colspan="3">
                    <html:hidden property="fjid" styleId="fjid" />
                    <input type="file" id="filepath_f" name="filepath" />
                    <script type="text/javascript">
                        //���ø���
                        jQuery(function(){
                            jQuery('#filepath_f').multiUploader({
                                maxcount : 3,
                                //��׺
                                accept : 'png|gif|jpg|zip|rar|doc|docx',
                                //����ļ���С ��λM
                                maxsize: 10,
                                //��Ÿ������������id
                                elementid : 'fjid',

                                eid : 'filepath_f'
                            });
                        });
                    </script>
                </td>
            </tr>
            </tbody>


        </table>
    </div>
</html:form>
    <table width="100%" border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">
                    "<span class="red">*</span>"Ϊ������
                </div>
                <div class="btn">

                    <button type="button" type="button" onclick="save('update');">
                         �� ��
                    </button>

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

