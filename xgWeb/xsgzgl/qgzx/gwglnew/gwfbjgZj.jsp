<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type='text/javascript' src="js/comm/message.js"></script>
    <script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
    <script type='text/javascript' src='/xgxt/dwr/util.js'></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type='text/javascript' src="js/uicomm.js"></script>
    <script type='text/javascript' src="js/String.js"></script>
    <script type='text/javascript' src="js/xgutil.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="comm/editor/kindeditor-min.js"></script>
    <script type="text/javascript" src="comm/editor/zh_CN.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">
        //����
        var gsjjeditor;

        function yrdwDivSave(type){
            var checkIds = "dwid-gwmc-gwryyq-fjid";

            if(!checkNotNull(checkIds)){
                showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����!");
                return false;
            }
            var url="qgzx_gwglnew.do?method=gwfbjgZj&type="+type;
            ajaxSubFormWithFun("qgzxGwglNewForm",url,function(data){
                if (data["message"] == "����ɹ���") {
                    showAlert(data["message"], {}, {
                        "clkFun" : function() {
                            if (parent.window) {
                                refershParent();
                            }
                        }
                    });
                } else {
                    showAlert(data["message"]);
                }
            });
        }

        function checkGz() {
            var gzsx = '${gsgz.cjsx}';
            var gwcjsx = jQuery("#gwcjsx").val();
            if(gwcjsx>gzsx||gwcjsx==''){
                var gwcjsx = jQuery("#gwcjsx").val(gzsx);
                jQuery("#gwcjsx").focus();
                return false;
            }
        }
        function checkGs() {
            var gssx = '${gsgz.gzscsx}';
            var gzscsx = jQuery("#gssx").val();
            if(gzscsx>gssx||gzscsx==''){
                var gzscsx = jQuery("#gssx").val(gssx);
                jQuery("#gssx").focus();
                return false;
            }
        }
      /*  //��λ���ı�
        function gwlbChange(target){
            var v = jQuery(target).val();
            var url = "qgzx_gwglnew.do?method=gwlbChange";
            jQuery.post(url,{id:v},function(data){
                jQuery("#gwcjsx").val(data["gwxcsx"]);
                jQuery("#gssx").val(data["gssx"]);
                jQuery("#label").html(data["label"]);
            },'json');
        }*/
        function selectDw(){
            showDialog('��ѡ��һ����λ',800,500,'qgzx_gwglnew.do?method=selectDw');
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
                afterBlur:function(){this.sync();}
            };
            gsjjeditor = KindEditor.create('textarea[name="gwryyq"]',simpleOption);

            jQuery("#gwlb").trigger('change');
        })
    </script>
</head>
<body >

<html:form styleId="qgzxGwglNewForm" action="/qgzx_gwglnew" method="post" onsubmit="return false;">
    <table align="center" class="formlist">
        <thead>
        <tr>
            <th colspan="4" style="width: 25%">
                <span>��λ������Ϣ</span>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>
                ѧ��ѧ��
            </th>
            <td>
                ${xn}${xqmc}
            </td>
            <th>
                <span class="red">*</span>���˵�λ
            </th>
            <td>
                <input type="hidden" name="yrdwid" id="dwid">
                <span id="mc"></span>
                <button class="btn_01" type="button" onclick="selectDw();">ѡ��</button>
            </td>
        </tr>
        </tbody>
        <tbody>
        <tr>
            <th>
                <span class="red">*</span>��λ����
            </th>
            <td>
                <input name="gwmc" id="gwmc" value="${model.gwmc}" maxlength="50">
            </td>
            <th>��������</th>
            <td>
                <input type="radio" name="gwxzdm" value="0" checked="checked">��ʱ
                <input type="radio" name="gwxzdm" value="1">��ʽ
            </td>
        </tr>
        <tr>
            <th>
                ��Ƹ����
            </th>
            <td>
                <input name="xqrs" id="xqrs" value="${model.xqrs}" onblur="checkMoney(this)" maxlength="3">
            </td>
            <th>
                ��λ����
            </th>
            <td>
                <input type="radio" name="gwlx" value="0" checked="checked">��ʱ
                <input type="radio" name="gwlx" value="1">����
            </td>
        </tr>
        <tr>
            <th>
                ��λ���
            </th>
            <td >
                <select name="gwlb" id="gwlb">
                    <logic:iterate id="i" name="gwlblist">
                        <option value="${i.gwxzdm}">${i.gwxzmc}</option>
                    </logic:iterate>
                </select>
            </td>
            <th>
                ��������/��
            </th>
            <td>
                <input type="text" name="gwcjsx" id="gwcjsx" value="${gsgz.cjsx}" onblur="checkGz()"  maxlength="7" placeholder="���ô��ڹ������ޣ�${gsgz.cjsx}��">
            </td>
        </tr>
        <tr>
            <th>
                ��ʱ����/��
            </th>
            <td colspan="3">
                <input type="text" name="gssx" id="gssx" value="${gsgz.gzscsx}" onblur="checkGs()" maxlength="4" placeholder="���ô��ڹ�ʱ���ޣ�${gsgz.gzscsx}��">&nbsp;&nbsp;
                <span id="label"></span>
            </td>
        </tr>
        <tr>
            <th>
                ��Ƹ��ʼʱ��
            </th>
            <td>
                <input name="zpkssj" id="zpkssj" readonly="true" style="width:100px;"
                           onfocus="showCalendar('zpkssj','y-mm-dd');" value="${defalutStart}">
                </input>
            </td>
            <th>
                ��Ƹ����ʱ��
            </th>
            <td>
                <input name="zpjssj" id="zpjssj" readonly="readonly" style="width:100px;"
                           onfocus="showCalendar('zpjssj','y-mm-dd');" value="${defalutEnd}">
                </input>
                <input type="checkbox" name="cq" value="1"/>����
            </td>
        </tr>
        <tr>
            <th>
                �Ƿ��ö�
            </th>
            <td>
                <input type="radio" name="sfzd" value="0" checked="checked">��
                <input type="radio" name="sfzd" value="1">��
            </td>
            <th>
                �Ƿ���ʾ����
            </th>
            <td>
                <input type="radio" name="sfxsgz" value="0" checked="checked">��
                <input type="radio" name="sfxsgz" value="1">��
            </td>
        </tr>
        <%--<tr>
            <th><span class="red">*</span>Э����</th>
            <td colspan="3">
                <html:hidden property="fjid" styleId="fjid" />
                <%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
                <script type="text/javascript">
                    //���ø���
                    jQuery(function(){
                        jQuery.MultiUploader({
                            maxcount : 1,
                            //��׺
                            accept : 'doc| docx| jpg| gif| png| bmp',
                            //����ļ���С ��λM
                            maxsize: 5,
                            //��Ÿ������������id
                            elementid : 'fjid'
                        });
                    });
                </script>
            </td>
        </tr>--%>
        <tr>
            <th>
                <span class="red">*</span>��ƸҪ��</br><span class="red">��������1500���ڣ�</span>
            </th>
            <td colspan="3">
                <textarea name="gwryyq" id="gwryyq" style="width:100%;height:270px" maxlength="2000"></textarea>
            </td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">
                    <span style="font-size: larger; color: purple;">������ʾ��</br>1.У�ڵ�λ�ĸ�λ����Ĭ��Ϊ</span>
                    <span style="font-size: larger; color: red;">����</span>
                    <span style="font-size: larger; color: purple;">����</span>
                    <span style="font-size: larger; color: red;">����</span>
                    <span style="font-size: larger; color: purple;">����淶��д</br>2.��ְλ�ѹ�����û���������������ӳ���ֹ����</span>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <div class="bz">
                    "<span class="red">*</span>"Ϊ������
                </div>
                <div class="btn">
                    <button type="button" name="����" onclick="yrdwDivSave('save');return false;">
                        ����
                    </button>
                    <button type="button" name="�ر�" onclick="Close();return false;">
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
