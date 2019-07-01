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
        jQuery(function(){
            jQuery('input[name="yxzt"]').click(function () {
                jQuery("#qkmstr").empty();
                var yxzt = jQuery('input[name="yxzt"]:checked').val();
                if(yxzt=="3"){
                    jQuery("#qkmstr").append("<th><span class='red'>*</span>�������</th>");
                    jQuery("#qkmstr").append("<td colspan='3'><textarea name='qkms' id='qkms' style='width: 500px;height: 100px;'></textarea></td>");
                }
            })
        })
        function saveFds(){
            var checkId = 'fdsmc-fdsdd-syksrq-syjsrq-sykssj-syjssj';
            var yxzt = jQuery('input[name="yxzt"]:checked').val();
            if(yxzt=="3"){
                checkId += '-qkms';
            }
            if(!checkNotNull(checkId)){
                showAlertDivLayer("�뽫��������д������");
                return false;
            }
            save();
        }
        function save(){
            var url = "xyfd_fdswh.do?method=addfds&type=save";
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
<html:form action="/xyfd_fdswh" method="post" styleId="demoForm">
    <div style='width:100%;height:300px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>��������Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="16%"><span class="red">*</span>����������</th>
                <td width="34%">
                    <input id="fdsmc" name="fdsmc"/>
                </td>
                <th width="16%"><span class="red">*</span>�����ҵص�</th>
                <td width="34%">
                    <input id="fdsdd" name="fdsdd"/>
                </td>
            </tr>
            <tr>
                <th width="16%"><span class="red">*</span>ʹ�ÿ�ʼ����</th>
                <td width="34%">
                    <input id="syksrq" name="syksrq" onclick="showCalendar('syksrq','yyyyMMdd');" readonly="true"/>
                </td>
                <th width="16%"><span class="red">*</span>ʹ�ý�������</th>
                <td width="34%">
                    <input id="syjsrq" name="syjsrq" onclick="showCalendar('syjsrq','yyyyMMdd');" readonly="true"/>
                </td>
            </tr>
            <tr>
                <th width="16%"><span class="red">*</span>ʹ�ÿ�ʼʱ��</th>
                <td width="34%">
                    <input id="sykssj" name="sykssj" onclick="showCalendar('sykssj','HH:mm');" readonly="true"/>
                </td>
                <th width="16%"><span class="red">*</span>ʹ�ý���ʱ��</th>
                <td width="34%">
                    <input id="syjssj" name="syjssj" onclick="showCalendar('syjssj','HH:mm');" readonly="true"/>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>�������</th>
                <td colspan="3">
                    <input type="radio" name="yxzt" value="1" checked="checked"/>��������
                    <input type="radio" name="yxzt" value="2"/>ֹͣ����
                    <input type="radio" name="yxzt" value="3"/>����
                </td>
            </tr>
            <tr id="qkmstr">

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
                        <button type="button" type="button" onclick="saveFds();return false;" >
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

