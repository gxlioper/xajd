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
        function saveZwsq(){
            var checkId = 'jxmc-hjsj-jldj-bjdw-filepath';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("�뽫��������д������");
                return false;
            }
            var url = "szdw_fdy_hjqkwh.do?method=add&type=save";
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
<html:form action="/szdw_fdy_hjqkwh" method="post" styleId="demoForm">
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
                        <span>������Ϣ</span>
                    </th>
                </tr>
            </thead>
            <tbody>
            <tr>
                <th><span class="red">*</span>��������</th>
                <td>
                    <html:text property="jxmc" styleId="jxmc" />
                </td>
                <th><span class="red">*</span>��ʱ��</th>
                <td>
                <html:text property="hjsj" styleId="hjsj" onclick="return showCalendar('hjsj','yyyy-MM-dd');" readonly="true"/>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>�����ȼ�</th>
                <td>
                    <html:select property="jldj" styleId="jldj">
                        <html:options property="dm" collection="jldjList" labelProperty="mc"></html:options>
                    </html:select>
                </td>
                <th><span class="red">*</span>�佱��λ</th>
                <td>
                    <html:text property="bjdw"  styleId="bjdw" />
                </td>
            </tr>
            <tr>
                <th>��������</th>
                <td colspan="3">
                    <html:text property="pm"  styleId="pm" maxlength="100"/>
                </td>
            </tr>
            <tr>
                <th>��ע</th>
                <td colspan="3">
                    <html:textarea property='bz' style="width:95%" styleId="bz" rows='5' onblur="checkLen(this,1000);"/>
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
                                eid : 'filepath_f',
                                label:'�ϴ�֤��ɨ���'
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

