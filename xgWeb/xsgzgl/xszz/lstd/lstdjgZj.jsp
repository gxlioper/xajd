<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript"
            src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/xszz/lstd/js/lstd.js"></script>
    <script type="text/javascript" defer="defer">

        jQuery(function(){
            jQuery("#hjfs").trigger('change');
        });
        /**
         * ��֤�Ƿ���ڿ���
         * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
         * @return
         */
        function check(ids){
            var id=ids.split("-");
            for(var i=0;i<id.length;i++){
                var lddm=jQuery("#"+id[i]).val();
                if(lddm==null||""==lddm){
                    return false;
                }
            }
            return true;
        }
        function saveForm(type){
            var checkId ="xh";
            if(!check(checkId)){
                return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
            }
            var url = "xszz_lstd.do?method=lstdjgZj&type="+type;


            ajaxSubFormWithFun("lstdForm", url, function(data) {
                showAlert(data["message"], {}, {
                    "clkFun" : function() {
                        if (parent.window) {
                            refershParent();
                        }
                    }
                });
            });
        }
    </script>
</head>
<body>
<html:form action="/xszz_lstd" method="post" styleId="lstdForm" onsubmit="return false">

    <div style='tab;width:100%;height:650px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>ѧ����Ϣ</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span>������Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <th>������ʽ</th>
                    <td>
                        <select name="hjfs" id="hjfs" onchange="selectChange(this);">
                            <option value="01">��Դ�ع�����ѧ����</option>
                            <option value="02">У԰�ع�����ѧ����(������:8000Ԫ)</option>
                            <option value="03">�ڹ���ѧ</option>
                            <option value="04">������ѧ��</option>
                            <option value="05">��ͥ�������</option>
                            <option value="06"> ������������</option>
                        </select>
                    </td>
                    <th id="dkjeTh">���������(Ԫ)</th>
                    <td id="dkjeTd">
                        <input id="dkje" name="dkje" onblur="checkMoneyForKeyup(this);jshjje();"/>
                    </td>
                </tr>
                <tr>
                    <th>��ͨ���ý��</th>
                    <td colspan="3">
                        <input name="jtfyje" onblur="checkMoneyForKeyup(this);"/>�����޳����������𳵵ĵ��̷��ã�
                    </td>
                </tr>
                <tr>
                    <th>���뻺�����</th>
                    <td colspan="3">
                        <input name="sqhjje" id="sqhjje" onblur="checkMoneyForKeyup(this);"/>&nbsp;ѧ��+ס�ޡ�8000Ԫ������8000Ԫ�����ڱ������Ѵ����ɲ��ַ��ɰ�����������
                    </td>
                </tr>
                <%--<tr>
                    <th>������ֹʱ��</th>
                    <td colspan="3">
                        ${jssj}
                    </td>
                </tr>--%>
                <%--<tr>
                    <th><span class="red">*</span>��������</th>
                    <td colspan="3">
                        <textarea rows="5" style="width: 95%" name="sqly" id="sqly"></textarea>
                    </td>
                </tr>--%>
                <tr>
                    <th>����</th>
                    <td colspan="3">
                        <html:hidden property="filepath" styleId="filepath" />
                        <%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
                        <script type="text/javascript">
                            //���ø���
                            jQuery(function(){
                                jQuery.MultiUploader({
                                    maxcount : 3,
                                    //��׺
                                    accept : 'png|gif|jpg',
                                    //����ļ���С ��λM
                                    maxsize: 10,
                                    //��Ÿ������������id
                                    elementid : 'filepath'
                                });
                            });
                        </script>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div style="height:35px;" ></div>
    <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">
                    "<span class="red">*</span>"Ϊ������
                </div>
                <div class="btn">
                    <button type="button" id="save_button" type="button"
                            onclick="saveForm('save');">
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
</html:form>
</body>
</html>

