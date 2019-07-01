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
        function saveZwsq(type,shzt){
            var checkId = 'mc-xmly-kssj-xmjf-sqsj-cdjs';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("�뽫��������д������");
                return false;
            }
            if(jQuery("input[name='sfjx']:checked").val() == "1"){
                if(jQuery("#jxjg").val() == ""){
                    showAlertDivLayer("�뽫��������д������");
                    return false;
                }
            }
            var url = "szdw_fdy_ktxmyjqkwh.do?method=add&type=save";
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }
        function sfjxChange(){
            var v = jQuery("input[name='sfjx']:checked").val();
            if(v == "0"){
                jQuery("#jxsjTr").attr("style","display:none");
                jQuery("#jxsj").val("");
                jQuery("#jxjgTr").attr("style","display:none");
                jQuery("#jxjg").val("");
            } else {
                jQuery("#jxsjTr").removeAttr("style");
                jQuery("#jxjgTr").removeAttr("style");
            }
        }
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
                    <html:text property="xmbh" styleId="xmbh" />
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>����</th>
                <td>
                    <html:text property="mc" styleId="mc" />
                </td>
                <th><span class="red">*</span>��Դ</th>
                <td>
                    <html:text property="xmly" styleId="xmly" />
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>��ʼʱ��</th>
                <td>
                    <html:text property="kssj" onclick="return showCalendar('kssj','yyyy-MM-dd');" styleId="kssj" readonly="true"/>
                </td>
                <th>����ʱ��</th>
                <td>
                    <html:text property="jssj" onclick="return showCalendar('jssj','yyyy-MM-dd');" styleId="jssj" readonly="true"/>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>��Ŀ����</th>
                <td>
                    <html:text property="xmjf" styleId="xmjf" />
                </td>
                <th><span class="red">*</span>����ʱ��</th>
                <td>
                    <html:text property="sqsj" onclick="return showCalendar('sqsj','yyyy-MM-dd');" styleId="sqsj" readonly="true"/>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>���˳е���ɫ</th>
                <td>
                    <html:select property="cdjs" styleId="cdjs">
                        <html:options collection="jsList" property="dm" labelProperty="mc"></html:options>
                    </html:select>
                </td>
                <th>�Ƿ����</th>
                <td>
                    <html:radio property="sfjx" value="1" onchange="sfjxChange();">��</html:radio>
                    <html:radio property="sfjx" value="0" onchange="sfjxChange();">��</html:radio>
                </td>
            </tr>
            <tr id="jxsjTr">
                <th>����ʱ��</th>
                <td colspan="3">
                    <html:text property="jxsj" onclick="return showCalendar('jxsj','yyyy-MM-dd');" styleId="jxsj" readonly="true"/>
                </td>
            </tr>
            <tr id="jxjgTr">
                <th><span class="red">*</span>������</th>
                <td colspan="3">
                    <html:textarea property='jxjg' style="width:95%" styleId="jxjg" rows='5' onblur="checkLen(this,1000);"/>
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
                    <div class="bz">"<span class="red">*</span>"Ϊ������</div>
                    <div class="btn">
                        <button type="button" type="button" onclick="saveZwsq();return false;" >
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

