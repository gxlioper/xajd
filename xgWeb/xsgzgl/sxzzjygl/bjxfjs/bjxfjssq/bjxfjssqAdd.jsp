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
    <script type="text/javascript" src="xsgzgl/sxzzjygl/bjxfjs/bjxfjssq/js/bjxfjssqEdit.js"></script>
    <script type="text/javascript">
        jQuery(function(){
        })
    </script>
    <style type="text/css">
        *{font-size: 14px;}
        textarea{width: 98%}
        #bxnmbTable input{width: 50px;}
    </style>
</head>
<body>
<html:form action="/sxzzjy_bjxfjssq" method="post"  styleId="form">
    <div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
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
                        <input type="text" name="bjmc" id="bjmc" readonly="readonly" style="width: 200px;"/>
                        <button onclick="selectBj();return false;">ѡ��</button>
                        <html:hidden property="bjdm" styleId="bjdm" />
                    </td>
                </tr>
                <tr style="">
                    <th width="14%">
                        ѧԺ
                    </th>
                    <td width="36%" id="xyTd">

                    </td>
                    <th width="14%">
                       �༶����
                    </th>
                    <td width="36%" id="bjrsTd">


                    </td>
                </tr>
                <tr>
                    <th >
                       ��Ա��
                    </th>
                    <td  id="dysTD">

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

                    </td>
                    <th >
                        ������
                    </th>
                    <td  id="bzrTD">

                    </td>

                </tr>
                <tr>
                    <th >
                        �೤
                    </th>
                    <td  id="bzTD">

                    </td>
                    <th >
                        ��֧��
                    </th>
                    <td  id="tzsTD">

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
                    <html:text property="xfjsmc" styleId="xfjsmc" style="width:60%;" />
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span>�걨����
                </th>
                <td >
                    <html:select property="sblx" styleId="sblx" style="width:150px" styleClass="select">
                        <html:option value=""></html:option>
                        <html:options collection="sblxList" property="sblxdm"
                                      labelProperty="sblxmc" />
                    </html:select>
                </td>
                <th >
                    ѧ��ѧ��
                </th>
                <td>
                   ${xnxq}
                    <html:hidden property="xn" styleId="xn"/>
                    <html:hidden property="xq" styleId="xq"/>
                </td>
            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>��ѧ��Ŀ��
                </th>
                <td   colspan="3">
                   <table id="bxnmbTable">
                       <tr>
                           <th>�༶ƽ��ѧ�ֻ�</th>
                           <td width="20%"><html:text property="pjxfj" styleId="pjxfj" onblur="checkLen(this,10);"/></td>
                           <td colspan="2">�༶ѧ�ֻ����꼶��רҵ��
                               <html:text property="njzy" styleId="njzy" onblur="checkLen(this,50);"/>��
                               <html:text property="zyxbgs" styleId="zyxbgs" onblur="checkLen(this,10);"/>��С����������
                               <html:text property="pjxfjpm" styleId="pjxfjpm" onblur="checkLen(this,10);"/>��
                           </td>
                       </tr>
                       <tr>
                           <th>�༶Ӣ���ļ�ͨ����</th>
                           <td><html:text property="sjtgl" styleId="sjtgl" onblur="checkLen(this,10);"/></td>
                           <td colspan="2">�༶Ӣ���ļ�ͨ�������꼶
                               <html:text property="njxbgs" styleId="njxbgs" onblur="checkLen(this,10);"/>��С����������
                               <html:text property="sjtglpm" styleId="sjtglpm" onblur="checkLen(this,10);"/>��
                           </td>
                       </tr>
                       <tr>
                           <th>�������Ŵ�</th>
                           <td><html:text property="bjgmc" styleId="bjgmc" onblur="checkLen(this,10);"/>��</td>
                           <th>����������</th>
                           <td><html:text property="bjgrs" styleId="bjgrs" onblur="checkLen(this,10);"/>��</td>
                       </tr>
                       <tr>
                           <th>�������˴�</th>
                           <td><html:text property="bjgrc" styleId="bjgrc" onblur="checkLen(this,10);"/>�˴�</td>
                           <th>��ɲ�ѧϰ�ɼ�ǰ����</th>
                           <td><html:text property="bgbqwrs" styleId="bgbqwrs" onblur="checkLen(this,10);"/>��</td>

                       </tr>
                       <tr>
                           <th>��ɲ�ѧϰ�ɼ�ǰʮ��</th>
                           <td><html:text property="bgbqsrs" styleId="bgbqsrs" onblur="checkLen(this,10);"/>��</td>
                           <th>��ѧ��</th>
                           <td><html:text property="hjxsrs" styleId="hjxsrs" onblur="checkLen(this,10);"/>��</td>
                       </tr>
                       <tr>
                           <th>���影</th>
                           <td><html:text property="hjtjgs" styleId="hjtjgs" onblur="checkLen(this,10);"/>��</td>
                           <th>���ʵ����</th>
                           <td><html:text property="shsjhjrc" styleId="shsjhjrc" onblur="checkLen(this,10);"/>�˴�</td>
                       </tr>
                       <tr>
                           <th>�����</th>
                           <td><html:text property="sshjcs" styleId="sshjcs" onblur="checkLen(this,10);"/>��</td>
                           <th>��֯ȫ�༯��</th>
                           <td><html:text property="qbjthdcs" styleId="qbjthdcs" onblur="checkLen(this,10);"/>��</td>
                       </tr>
                       <tr>
                           <th>�Ƽ�ѧ����</th>
                           <td><html:text property="kjxshjrc" styleId="kjxshjrc" onblur="checkLen(this,10);"/>�˴�</td>
                           <th>��֯�༶ͬѧ�μ�УԺ�</th>
                           <td><html:text property="cjxyhdcs" styleId="cjxyhdcs" onblur="checkLen(this,10);"/>��</td>
                       </tr>
                       <tr>
                           <th>����ͬѧ</th>
                           <td><html:text property="jjtxrs" styleId="jjtxrs" onblur="checkLen(this,10);"/>��</td>
                           <th>�Զ�ѧ��</th>
                           <td><html:text property="sdxsrs" styleId="sdxsrs" onblur="checkLen(this,10);"/>��</td>
                       </tr>
                       <tr>
                           <th>��ѧ</th>
                           <td colspan="3"><html:text property="txrs" styleId="txrs"/>��</td>
                       </tr>
                   </table>
                </td>
            </tr>
                <tr style="">
                    <th>
                        <span style="color: red">*</span>����˼·�ͼƻ�<br>
                        <span style="color: red">(��500��)</span>
                    </th>
                    <td   colspan="3">
                        <html:textarea property="jssl" style="width:98%;margin-top:5px" rows="5"
                                       onblur="checkLen(this,500);" styleId="jssl"
                        ></html:textarea>
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
                    <button type="button" type="button" onclick="saveForm_add('save');">
                        ����ݸ�
                    </button>

                    <button type="button" type="button" onclick="saveForm_add('submit');">
                        �ύ����
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

