<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript">
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
        //����--����
        function saveForm(){
            var checkId ="jzzt-jzzjr";
            if(!check(checkId)){
                return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
            }
            var url = "hdgl_hdgl_jzjh.do?method=add&type=save";
            ajaxSubFormWithFun("jzjhForm", url, function(data) {
                if (data["message"] == "����ɹ���") {
                    showAlert(data["message"], {}, {
                        "clkFun" : function() {
                            if (parent.window) {
                                refershParent();
                            }
                        }
                    });
                } else {
                    showAlert(data["message"], {}, {
                        "clkFun" : function() {
                            if (parent.window) {
                                refershParent();
                            }
                        }
                    });
                }
            });
        }

    </script>
</head>
<body>
<html:form method="post" styleId="jzjhForm" action="/hdgl_hdgl_jzjh"
           enctype="multipart/form-data">
    <div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
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
                <th align="right" >
                    <span class="red">*</span>����
                </th>
                <td align="left" >
                    <html:text styleId="jzzt" property="jzzt"  maxlength="50"/>
                </td>
                <th align="right">
                    <span class="red">*</span>������
                </th>
                <td align="left" >
                    <html:text styleId="jzzjr" property="jzzjr"  maxlength="50"/>
                </td>
            </tr>
            <tr>
                <th align="right">
                    �ⶨʱ��
                </th>
                <td align="left" >
                    <html:text property="jzndsj" styleId="jzndsj" onfocus="return showCalendar(this.id,'yyyy-MM-dd HH:mm');" maxlength="15" readonly="true"></html:text>
                </td>
                <th align="right" >
                    �ⶨ�ص�
                </th>
                <td align="left" >
                    <html:text styleId="jznddd" property="jznddd"  maxlength="50"/>
                </td>
            </tr>
            <tr>
                <th align="right">
                    ���쵥λ
                </th>
                <td align="left" colspan="3">
                    <html:text styleId="jzzbdw" property="jzzbdw"  maxlength="50"/>
                </td>
            </tr>
            <tr>
                <th align="right" >
                    �����˽���<font color="red">(��200��)</font>
                </th>
                <td align="left" colspan="3">
                    <html:textarea property="jzzjrjs" style="width:98%;margin-top:5px;" rows="5"
                                   onblur="checkLen(this,200);" styleId="shyj"
                    ></html:textarea>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="height:50px;"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "
                        <span class="red">*</span>"Ϊ������
                    </div>
                    <div class="btn">
                        <button type="button" onclick="saveForm();">
                            ��    ��
                        </button>
                        <button type="button" onclick="iFClose();">
                            �ر�
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
