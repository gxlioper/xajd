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
            var checkId = 'pxmc-pxsj-zzbm-xs-pxnr-pxxd';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("�뽫��������д������");
                return false;
            }
            var url = "szdw_fdy_ywxxypx.do?method=add&type="+type+"&shzt="+shzt;
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }

    </script>
</head>
<body style="width:100%">
<html:form action="/szdw_fdy_ywxxypx" method="post" styleId="demoForm">
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
                    <th width="30%">�Ա�</th>
                    <td width="30%">${fdyxx.xb}</td>
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
                        <span>��ѵ��Ϣ</span>
                    </th>
                </tr>
            </thead>
            <tbody>
            <tr>
                <th><span class="red">*</span>��ѵ����</th>
                <td>
                    <html:text property="pxmc" styleId="pxmc" />
                </td>
                <th>��ѵʱ��</th>
                <td>
                    <html:text property="pxsj" onclick="return showCalendar('pxsj','yyyy-MM-dd');" styleId="pxsj" readonly="true"/>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>��֯����</th>
                <td>
                    <html:select property="zzbm" styleId="zzbm">
                        <html:options collection="bmList" property="bmdm" labelProperty="bmmc"></html:options>
                    </html:select>
                </td>
                <th><span class="red">*</span>ѧʱ</th>
                <td>
                    <html:text property="xs" styleId="xs" />
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>��ѵ����</th>
                <td colspan="3">
                    <html:textarea property='pxnr' style="width:95%" styleId="pxnr" rows='5' onblur="checkLen(this,1000);"/>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>��ѵ�ĵ�</th>
                <td colspan="3">
                    <html:textarea property='pxxd' style="width:95%" styleId="pxxd" rows='5' onblur="checkLen(this,1000);"/>
                </td>
            </tr>
            <tr>
                <th>
                    ����
                </th>
                <td  colspan="3">
                    <html:hidden property="filepath" styleId="filepath" />
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
                                elementid : 'filepath',
                                eid : 'filepath_f'
                            });
                        });
                    </script>
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
                        <button type="button" type="button" onclick="saveZwsq('save','0');return false;" >
                            ����ݸ�
                        </button>
                        <button type="button" type="button" onclick="saveZwsq('submit','5');return false;" >
                            �ύ���
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

