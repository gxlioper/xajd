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
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
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
        function xgForm(type){
            var checkId ="sqly";
            if(!check(checkId)){
                return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
            }
            var url;
            if(type == 'save'){
                url = "xszz_lstd.do?method=lstdsqXg&type="+type + "&shzt=0";
            } else {
                url = "xszz_lstd.do?method=lstdsqXg&type="+type + "&shzt=5";
            }

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
    <html:hidden property="sqid"  styleId="sqid"/>
    <div style='tab;width:100%;height:650px;overflow-x:hidden;overflow-y:auto;'>
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
                <th>������ʽ</th>
                <td>
                    <select name="hjfs" id="hjfs" onchange="selectChange(this);">
                        <option value="01" <logic:equal value="01" name="hjfs">selected = "selected" </logic:equal>>��Դ�ع�����ѧ����</option>
                        <option value="02" <logic:equal value="02" name="hjfs">selected = "selected" </logic:equal>>У԰�ع�����ѧ����(������:8000Ԫ)</option>
                        <option value="03" <logic:equal value="03" name="hjfs">selected = "selected" </logic:equal>>�ڹ���ѧ</option>
                        <option value="04" <logic:equal value="04" name="hjfs">selected = "selected" </logic:equal>>������ѧ��</option>
                        <option value="05" <logic:equal value="05" name="hjfs">selected = "selected" </logic:equal>>��ͥ�������</option>
                        <option value="06" <logic:equal value="06" name="hjfs">selected = "selected" </logic:equal>> ������������</option>
                    </select>
                </td>
                <th id="dkjeTh">���������(Ԫ)</th>
                <td id="dkjeTd">
                    <html:text property="dkje" styleId="dkje" onblur="checkMoneyForKeyup(this);"/>
                </td>
            </tr>
            <tr>
                <th>��ͨ���ý��</th>
                <td colspan="3">
                    <html:text property="jtfyje" styleId="jtfyje" onblur="checkMoneyForKeyup(this);"/>�����޳����������𳵵ĵ��̷��ã�
                </td>
            </tr>
            <tr>
                <th>���뻺�����</th>
                <td colspan="3">
                    <html:text property="sqhjje" styleId="sqhjje" onblur="checkMoneyForKeyup(this);"/>&nbsp;ѧ��+ס�ޡ�8000Ԫ������8000Ԫ�����ڱ������Ѵ����ɲ��ַ��ɰ�����������
                </td>
            </tr>
            <tr>
                <th>������ֹʱ��</th>
                <td colspan="3">
                        ${jssj}
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>��������</th>
                <td colspan="3">
                    <html:textarea rows="5" style="width: 95%" property="sqly" styleId="sqly"/>
                </td>
            </tr>
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
                            onclick="xgForm('save');">
                        ����ݸ�
                    </button>
                    <button type="button" id="btn_submit" type="button"
                            onclick="xgForm('submit');">
                        �ύ����
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

