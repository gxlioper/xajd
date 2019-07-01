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
    <script type="text/javascript" src="xsgzgl/szdw/jfxx/js/jfxx.js"></script>
    <script type="text/javascript">
        function saveZwsq(){
            var checkId = 'xh-jfsj-jfxz-yy-nr-csjy';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("�뽫��������д������");
                return false;
            }
            var url = "szdw_jfxx.do?method=add&type=save";
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }
        function selectDialog(){
            showDialog('��ѡ��һ��ѧ��',800,500,'szdw_jfxx.do?method=selectStudent&goToPath=szdw_jfxx.do?method=add');
        }
        jQuery(function(){
            onShow("add");
        });
    </script>
    <style type="text/css">
        #shlccx_table th{text-align: center;}
        #shlccx_table tr{text-align: center;}
    </style>
</head>
<body style="width:100%">
<html:form action="/szdw_jfxx" method="post" styleId="demoForm">
    <div style='width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>������Ϣ</span>
                </th>
            </tr>
            </thead>
            <%--<tbody>
                <tr>
                    <th><span class="red">*</span>ѧ��</th>
                    <td>
                        <input name="xh" id="xh" style="width:120px;">
                        <button class="btn_01" type="button" onclick="selectDialog();">ѡ��</button>
                    </td>
                    <th>����</th>
                    <td id="xmTd">
                    </td>
                </tr>
                <tr>
                    <th>�Ա�</th>
                    <td id="xbTd">
                    </td>
                    <th>�꼶</th>
                    <td id="njTd">
                    </td>
                </tr>
                <tr>
                    <th>��Ժ</th>
                    <td id="syTd">
                    </td>
                    <th>ѧԺ</th>
                    <td id="xyTd">
                    </td>
                </tr>
                <tr>
                    <th>�����༶</th>
                    <td id="bjTd">
                    </td>
                    <th>רҵ�༶</th>
                    <td id="zybjTd">
                    </td>
                </tr>
                <tr>
                    <th>����</th>
                    <td id="mzTd">
                    </td>
                    <th>����Ա</th>
                    <td id="fdyTd">
                    </td>
                </tr>
            </tbody>
            --%>
            <%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span>
                        �ҷö���&nbsp;&nbsp;
                        <a class="name" href="javascript:void(0);" onclick="addTr();return false;">����</a>
                    </span>
                </th>
            </tr>
            </thead>
            <tbody id="�ҷö���">
                <tr>
                    <td colspan="4">
                        <table id="shlccx_table" width="100%">
                            <tr>
                                <th>����</th>
                                <th>��ҷ��˹�ϵ</th>
                                <th>��ϵ�绰</th>
                                <th>��ע</th>
                                <th>����</th>
                            </tr>
                        </table>
                    </td>
                </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>�ҷ���Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <th><span class="red">*</span>�ҷ�ʱ��</th>
                    <td>
                        <html:text property="jfsj" onclick="return showCalendar('jfsj','yyyy-MM-dd');" styleId="jfsj" readonly="true"/>
                    </td>
                    <th>�ص�</th>
                    <td>
                        <html:text property="dd" styleId="dd" />
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>�ҷ�����</th>
                    <td>
                        <html:select property="jfxz" styleId="jfxz">
                            <html:option value="01">ʵ��</html:option>
                            <html:option value="02">�绰</html:option>
                            <html:option value="03">΢��</html:option>
                        </html:select>
                    </td>
                    <th>��������</th>
                    <td>
                        <html:text property="rs" styleId="rs" maxlength="2" onblur="checkInt(this);"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>�ҷ�ԭ��</th>
                    <td colspan="3">
                        <html:textarea property='yy' style="width:95%" styleId="yy" rows='5' onblur="checkLenBtw(this,50,1000);"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>�ҷ�����</th>
                    <td colspan="3">
                        <html:textarea property='nr' style="width:95%" styleId="nr" rows='5' onblur="checkLenBtw(this,100,1000);"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>��ʩ����</th>
                    <td colspan="3">
                        <html:textarea property='csjy' style="width:95%" styleId="csjy" rows='5' onblur="checkLenBtw(this,50,1000);"/>
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

