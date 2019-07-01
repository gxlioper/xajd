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
    <script type="text/javascript" src="comm/editor/kindeditor-min.js"></script>
    <script type="text/javascript" src="comm/editor/zh_CN.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="xsgzgl/gygl/sfqs/sfqskh/sfqskhsq/js/sfqskh.js"></script>
    <script type="text/javascript">
        function save(type){
            if(!isTelephone("lxfs")){
                showAlert("��ϵ��ʽ����ȷ��");
                return false;
            }
            var url = "gygl_sfqskh_wh.do?method=update&type="+type;
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
            //����ͼ
            var simpleOption = {
                name:'simple',
                resizeType : 1,
                themeType : 'simple',//��ʽ���
                items : [
                    'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                    'insertunorderedlist', '|', 'emoticons','link'
                ],
                newlineTag:'br',
                afterBlur:function(){this.sync();}
            };
            KindEditor.create('textarea[name="zysj"]',simpleOption);
        })
    </script>
    <style type="text/css">
        #shlccx_table th{text-align: center;}
        #shlccx_table tr{text-align: center;}
    </style>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_sfqskh_wh">
    <html:hidden property="sqid"></html:hidden>
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
                    <span class="red">*</span>ѧ��
                </th>
                <td>
                    <html:select property="xn" styleId="xn">
                        <html:options collection="xnList" property="xn" labelProperty="xn"></html:options>
                    </html:select>
                </td>
                <th>
                    <span class="red">*</span>У��
                </th>
                <td>
                    <html:select property="xqdm" styleId="xqdm" onchange="xqChange()">
                        <html:options collection="xqList" property="dm" labelProperty="xqmc"></html:options>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>����¥����
                </th>
                <td>
                    <html:select property="lddm" styleId="lddm" onchange="ldChange();">
                        <html:options collection="ldList" property="lddm" labelProperty="ldmc"></html:options>
                    </html:select>
                </td>
                <th><span class="red">*</span>���Һ�</th>
                <td>
                    <html:select property="qsh" styleId="qsh" onchange="qshChange();">
                        <html:options collection="qsList" property="qsh" labelProperty="qsh"></html:options>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th>������Ժ</th>
                <td id="sssyTd">

                </td>
                <th><span class="red">*</span>��ϵ��ʽ</th>
                <td>
                    <html:text styleId="lxfs" property="lxfs"/>
                </td>
            </tr>
            <tr>
                <th>���ҳ�Ա</th>
                <td colspan="3">
                    <style type="text/css">
                        #shlccx_table th{text-align: center;}
                        #shlccx_table tr{text-align: center;}
                    </style>
                    <table id="shlccx_table" width="100%">
                        <tr id="xmTr">
                            <th>����</th>
                            <logic:notEmpty name="qscyList">
                            <logic:iterate id="i" name="qscyList">
                                <td>${i.xm}</td>
                            </logic:iterate>
                            </logic:notEmpty>
                        </tr>
                        <tr id="xhTr">
                            <th>ѧ��</th>
                            <logic:notEmpty name="qscyList">
                            <logic:iterate id="i" name="qscyList" indexId="index">
                                <td><input type="hidden" value="${i.xh}" name="qscyxx[${index}].xh">${i.xh}</td>
                            </logic:iterate>
                            </logic:notEmpty>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>��Ҫ�¼�</th>
                <td colspan="3">
                    <textarea name="zysj" id="zysj" rows="6" style="width:98%">${zysj}</textarea>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>�����</th>
                <td colspan="3">
                    <html:textarea property="hjqk" rows="6" style="width:98%"></html:textarea>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>����</th>
                <td colspan="3">
                    <html:hidden property="filepath" styleId="filepath" />
                    <input type="file" id="filepath_f" name="filepath" value="${filepath}"/>
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
    <div style="height:30px;"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                    </div>
                    <div class="btn">
                        <button type="button" onclick="save('save');" id="buttonSave">
                            ����ݸ�
                        </button>
                        <button type="button" onclick="save('submit');" id="buttonSave">
                            �ύ����
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