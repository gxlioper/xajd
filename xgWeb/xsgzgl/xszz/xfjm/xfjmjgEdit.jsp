<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript"
            src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
    <script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <style type="text/css">
        .xfjm_tb tr th{
            width: 120px;
        }
        .xfjm_tb tr td{
            width: 252px;
        }
    </style>
    <script type="text/javascript">

        jQuery(function(){
            jQuery("#xh").change(function(){
                whenXhChange(jQuery(this).val());
            });
            var xh;
            if('${status}'=='update'){
                xh = '${sqinfo.xh}';
                jQuery("#xh").val(xh);
                showXsxxAjax(xh);
                jQuery(".xh").html(xh);
                jQuery("#xfjmje").val('${sqinfo.xfjmje}');
            }else{
                xh = jQuery("#xh").val();
            }
            loadJtqk(xh);
            console.log('ѧ��:'+xh);
            //����������
            if(xh != null && xh.length > 0 && "view" == "${status}"){
                jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${sqinfo.id}&tt="+new Date().getTime());
            }
            //��������ѡ��
            loadMkxxSelectOptions();
            //����radioѡ��
           loadMkxxRadioOptions();

        });

        //չ����ͥ���ʱ��Ҫ���ص�����
        function loadJtqk(xh){
            if (jQuery.trim(xh) != ""){
                //��ȡ��ͥ�����Ϣ
                jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh});
            }
        }
        //�鿴��ͥ�����Ϣ
        function showJtqk(obj){
            var xh = jQuery("#xh").val();
            if (jQuery.trim(xh) != ""){
                var className = jQuery(obj).attr("class");
                var newClass = className == "up" ? "down" : "up";

                jQuery(obj).attr("class",newClass);
                jQuery("#t_jtqk").toggle();
            }else{
                showAlertDivLayer("����ѡ��ѧ����");
            }
        }

        function whenXhChange(xh){
            //��֤�Ƿ��ظ�����
            jQuery.ajax({
                url:"xszz_new_xfjm.do?method=getknsrdInfo&xh="+xh,
                dataType: "JSON",
                type: "POST",
                success: function(res){
                    if(res.code == 0){
                        showAlertDivLayer("��������������϶�");
                        jQuery("#tj").hide();
                        return false;
                    }else{
                        var data = res.data;
                        jQuery("#knsrdxn").html(data.xn);
                        jQuery("#knsrddc").html(data.dcmc);
                        jQuery("#knsrdly").html(data.ylzd5);
                        jQuery("#tj").show();
                    }
                }
            });

            loadJtqk(xh);
        }
        function showXsxxAjax(xh) {
            jQuery.ajax({
                url: "xsxx_xsgl.do?method=getXsjbxxMore&xh=" + xh,
                dataType: "JSON",
                type: "POST",
                success: function (data) {
                    //����ҳ������
                    jQuery("td").each(function (i) {
                        var td = jQuery(this);
                        var id = td.attr("class");
                        if (id == "xh") {
                            td.children("input").val(data[id]);
                        } else {
                            td.text(data[id]);
                        }
                    });

                    //����change�¼�����δ֪ԭ��ֻ�ܴ���һ�ΰ󶨺�����������ʱ������
                    try {
                        whenXhChange(xh);
                    } catch (e) {

                    }
                }
            });
        }
        //ѧ�Ѽ���������
        function tjSave(){
            var data = {};
            var sqly = jQuery("#sqly").val();
            if(sqly == null || sqly.trim().length==0){
                showAlertDivLayer("�������ɲ���Ϊ�գ�");
                return false;
            }
            var xh = null;
            var status = jQuery("#status").val();
            if(status == 'update'){
                xh = jQuery("#hxh").val();
            }else{
                xh = jQuery("#xh").val();
            }
            if(xh == null || xh.trim().length == 0){
                showAlertDivLayer("ѧ����Ϣ����");
                return false;
            }
            data.xh = xh;
            var id = jQuery("#id").val();
            if(id != null && id.trim().length > 0){
                data.id = id;
            }
            data.sqly = sqly;
            data.shzt='1';
            data.xfjmje = jQuery("#xfjmje").val();
            var url = "xszz_new_xfjm.do?&method=jgSave";
            jQuery.post(url,data,function(result){
                if (result.code == 1) {
                    showAlert(result.msg,{},{"clkFun":function(){
                            refershParent();
                        }});
                } else {
                    //�洢ʧ��
                    showAlert(result.msg);
                    return false;
                }
            },'json');

        }


    </script>
</head>
<body>
<input type="hidden" name="shzt" id="shzt" value="${sqinfo.shzt}" />
<input type="hidden" id="id" value="${sqinfo.id}" />
<input type="hidden" id="hxh" value="${sqinfo.xh}" />
<input type="hidden" id="status" value="${status}" />
<html:form action="/xszz_new_xfjm" method="post" styleId="xfjmForm" onsubmit="return false">
    <div style='tab;width:100%;height:480px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>ѧ��������Ϣ</span>
                </th>
            </tr>
            </thead>
               <%@ include file="/xsgzgl/xszz/bdpz/selectStudentForKnsrd.jsp"%>
            <thead>
            <tr>
                <th colspan="4">
                    <span>��ͥ���
                            <a id="showJtqk" onclick="showJtqk(this);" class="up"
                               href="javascript:void(0);"> <font color="blue">���չ��/����</font>
                            </a>
                           <%-- |
                            <a onclick="editJtqk();" class="btn_xg"
                               href="javascript:void(0);"> <font color="blue">�༭��ͥ���</font>
                            </a>--%>
                    </span>
                </th>
            </tr>
            </thead>
            <tbody id="t_jtqk" style="display: none;">
            <tr>
                <td colspan="4">
                    <div id="div_jtqk">

                    </div>
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>�������϶����</span>
                </th>

            </tr>
            </thead>
            <tbody class="xfjm_tb">
                <tr>
                    <th width="16%">ѧ��</th>
                    <td id="knsrdxn">${knsrdjg.xn}</td>
                    <th width="34%">�϶�����</th>
                    <td id="knsrddc">${knsrdjg.dcmc}</td>
                </tr>
                <tr>
                    <th width="16%">�϶�����</th>
                    <td width="84%" colspan="3" id="knsrdly">${knsrdjg.ylzd5}</td>
                </tr>

            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>ѧ�Ѽ�����Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>

                <tr>
                    <th><span>������(Ԫ)</span></th>
                    <td colspan="3">
                        <logic:notEqual name="status" value="view">
                            <html:text property="xfjmje" styleId="xfjmje" style="width:155px;" maxlength="5" styleClass="text_nor" onblur="checkInputNum(this)"></html:text>
                        </logic:notEqual>
                        <logic:equal name="status" value="view">
                            ${sqinfo.sqly}
                        </logic:equal>
                    </td>
                </tr>
            <tr>
                <logic:notEqual name="status" value="view">
                    <th name="th_sqly">
                        <span class="red">*</span>��������
                        <br>
                        <span color="red">
                            &lt;��150��&gt;
                        </span>
                    </th>
                    <td colspan="3" style="word-break:break-all;">
                        <textarea oninput="chCount(this,0,150)" rows="5" style="width:98%;" id="sqly" name="sqly" onblur="checkLenBtw(this,0,30);" >${sqinfo.sqly}</textarea>
                    </td>
                </logic:notEqual>
                <logic:equal name="status" value="view">
                    <th name="th_sqly">
                        ��������<br>
                    </th>
                    <td colspan="3" style="word-break:break-all;">${sqinfo.sqly}</td>
                </logic:equal>
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
                    <logic:notEqual name="status" value="view">

                        <button type="button" id="tj" type="button"
                                onclick="tjSave();">
                            ����
                        </button>
                    </logic:notEqual>
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

