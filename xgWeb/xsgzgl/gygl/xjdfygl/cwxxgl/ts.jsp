<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">
        function save(){
            if(typeof (jQuery("#xn").val()) == "undefined" || jQuery("#tssj").val() == ""){
                showAlert("�뽫��������д������");
                return false;
            }
            var url = "gygl_fygl_cwxxgl10698.do?method=ts&type=save";
            ajaxSubFormWithFun("demoForm", url, function(data) {
                if(data["message"]=="����ɹ���"){
                    showAlert(data["message"],{},{"clkFun":function(){
                        if (parent.window){
                            refershParent();
                        }
                    }});
                }else{
                    showAlert(data["message"]);
                }
            });
        }
        jQuery(function(){

        })

    </script>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_fygl_cwxxgl10698">
    <input type="hidden" name="pks" value="${pks}">
    <input type="hidden" name="xhs" value="${xhs}">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
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
                <th>
                    <span class="red">*</span>���޲���˵��
                </th>
                <td colspan="3">
                    ��ǰ��ѡ��<span class="red">${xzgs}</span>������ס��λ����ִ�����޲���
                </td>
            </tr>
            <logic:notEmpty name="xsxx">
            <tr>
                <th>
                    ����ѧ����Ϣ
                </th>
                <td colspan="4">
                    <style>
                        #shlccx_table th{text-align: center;}
                        #shlccx_table tr{text-align: center;}
                    </style>
                    <div class="con_overlfow">
                        <table id="shlccx_table" width="100%" class="formlist" >
                            <tr>
                                <th>ѧ��</th>
                                <th>����</th>
                                <th>�Ա�</th>
                                <th>�꼶</th>
                                <th>ѧԺ</th>
                                <th>¥��</th>
                                <th>����</th>
                                <th>��λ</th>
                            </tr>
                            <tbody id="lcTd">
                                <tr>
                                    <td>${xsxx.xh}</td>
                                    <td>${xsxx.xm}</td>
                                    <td>${xsxx.xb}</td>
                                    <td>${xsxx.nj}</td>
                                    <td>${xsxx.xymc}</td>
                                    <td>${xsxx.ldmc}</td>
                                    <td>${xsxx.qsh}</td>
                                    <td>${xsxx.cwh}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </td>
            </tr>
            </logic:notEmpty>
            <tr>
                <th><span class="red">*</span>����ԭ��</th>
                <td>
                    <html:select property="tsyy">
                        <html:options property="tsyydm" labelProperty="tsyymc" collection="tsyyList"></html:options>
                    </html:select>
                </td>
                <th><span class="red">*</span>����ʱ��</th>
                <td>
                    <html:text property="tssj" styleId="tssj" readonly="true"
                               onclick="return showCalendar('tssj','yyyy-MM-dd');"></html:text>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>ѧ��/ѧ��</th>
                <td>
                    <html:select property="xn" styleId="xn">
                        <html:options property="xn" labelProperty="xn" collection="xnList"></html:options>
                    </html:select>
                    <html:select property="xq">
                        <html:options property="xqdm" labelProperty="xqmc" collection="xqList"></html:options>
                    </html:select>
                </td>
                <th><span class="red">*</span>��λ�Ƿ��ʼ��</th>
                <td>
                    <html:radio property="sfcshcw" value="1">&nbsp;��</html:radio>
                    <html:radio property="sfcshcw" value="0">&nbsp;��</html:radio>
                </td>
            </tr>
            <tr>
                <th>��ע<br><span class="red">(��500��)</span></th>
                <td colspan="3">
                    <html:textarea property="bz" styleId="bz" rows="4" style="width:98%"></html:textarea>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="height:30px;"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                    </div>
                    <div class="btn">
                        <button type="button" onclick="save();" id="buttonSave">
                            �� ��
                        </button>
                        <button type="button" onclick="iFClose();"  id="buttonClose">
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