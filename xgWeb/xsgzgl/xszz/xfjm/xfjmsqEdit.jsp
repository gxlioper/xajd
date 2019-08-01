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
    <script type="text/javascript" src="xsgzgl/xszz/knsrd/js/knsrd.js"></script>
    <style type="text/css">
        .xfjm_tb tr th{
            width: 120px;
        }
        .xfjm_tb tr td{
            width: 252px;
        }
    </style>
    <script type="text/javascript" defer="defer">

        jQuery(function(){

            var xh = jQuery("#xh").val();
            loadJtqk(xh);

            jQuery("#xh").change(function(){
                whenXhChange(jQuery(this).val());
            });

            //��������ѡ��
            loadMkxxSelectOptions();
            //����radioѡ��
            loadMkxxRadioOptions();

            var isopen = jQuery("#isopen").val();
            var shzt = jQuery("#shzt").val();
            if('3' != shzt && (isopen==null||isopen==''||"false" == isopen)){
                jQuery("#btn_submit").hide();
            }
            //����������
            if(xh != null && xh.length > 0 && "view" == "${status}"){
                jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${sqinfo.id}&tt="+new Date().getTime());
            }
        });

        //չ����ͥ���ʱ��Ҫ���ص�����
        function loadJtqk(xh){
            if (jQuery.trim(xh) != ""){
                //��ȡ��ͥ�����Ϣ
                jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh});
            }
        }


        //����������ݸ塿�͡��ύ���롿��ť
        function lock(){
            jQuery(".btn").find("button").slice(0,2).each(function(){
                jQuery(this).attr("disabled","disabled");
                jQuery(this).css({color:"#cccccc"});
            });
        }

        //����������ݸ塿�͡��ύ���롿��ť
        function unlock(){
            jQuery(".btn").find("button").slice(0,2).each(function(){
                jQuery(this).attr("disabled",false);
                jQuery(this).css({color:"#ffffff"});
            });
        }
        //�ύ������Ϣ
        function sqSave(type){
            var data = {};
            var sqly = jQuery("#sqly").val();
            if(sqly == null || sqly.trim().length==0){
                showAlertDivLayer("�������ɲ���Ϊ�գ�");
                return false;
            }
            var xh = jQuery("#xh").val();
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
            if(type=='notj'){
                data.shzt='0';
            }else{
                data.shzt='5';
            }
            var url = "xszz_new_xfjm.do?&method=sqSave";
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
<input type="hidden" id="xh" value="${xsjbxx.xh}" />
<input type="hidden" id="id" value="${sqinfo.id}" />

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
            <tbody class="xfjm_tb">
            <tr>
                <th>ѧ��</th>
                <td>${xsjbxx.xh}</td>
                <th>����</th>
                <td>${xsjbxx.xm}</td>
            </tr>
            <tr>
                <th>�꼶</th>
                <td>${xsjbxx.nj}</td>
                <th>ѧԺ</th>
                <td>${xsjbxx.xymc}</td>
            </tr>
            <tr>
                <th>רҵ</th>
                <td>${xsjbxx.zymc}</td>
                <th>��Ժ</th>
                <td>${xsjbxx.symc}</td>
            </tr>
            <tr>
                <th>�����༶</th>
                <td>${xsjbxx.bjmc}</td>
                <th>רҵ�༶</th>
                <td>${xsjbxx.zybjmc}</td>
            </tr>
            <tr>
                <th>������ò</th>
                <td>${xsjbxx.zzmmmc}</td>
                <th>֤������</th>
                <td>${xsjbxx.zd1}</td>
            </tr>
            <tr>
                <th>����</th>
                <td>${xsjbxx.mzmc}</td>
                <th>֤������</th>
                <td>${xsjbxx.sfzh}</td>
            </tr>
            <tr>
                <th>רҵ�༶����</th>
                <td>${pminfo.zybjpm}</td>
                <th>רҵ����</th>
                <td>${pminfo.zypm}</td>
            </tr>
            </tbody>
           <%-- <%@ include file="/xsgzgl/xszz/bdpz/selectStudentForKnsrd.jsp"%>--%>
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
            <logic:equal name="sfrdkns" value="1">
                <tr>
                    <th width="16%">ѧ��</th>
                    <td>${knsrdjg.xn}</td>
                    <th width="34%">�϶�����</th>
                    <td>${knsrdjg.dcmc}</td>
                </tr>
                <tr>
                    <th width="16%">�϶�����</th>
                    <td width="84%" colspan="3">${knsrdjg.ylzd5}</td>
                </tr>
            </logic:equal>
            <logic:notEqual name="sfrdkns" value="1">
                <tr>
                    <th colspan="4">��ѧ�������������϶������¼��</th>
                </tr>
            </logic:notEqual>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>ѧ�Ѽ�������</span>
                </th>
            </tr>
            </thead>

            <tbody>
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
            <logic:equal name="shzt" value="1">
                <tr>
                    <th><span>������(Ԫ)</span></th>
                    <td colspan="3">
                        <span>${sqinfo.xfjmje}</span>
                    </td>
                </tr>
            </logic:equal>
            </tbody>

            <logic:equal name="status" value="view">
                <thead>
                    <tr>
                        <th colspan="4">
                            <span>�����Ϣ</span>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="4" id="shlccx"></td>
                    </tr>
                </tbody>
            </logic:equal>
                <%--<%@ include file="/xsgzgl/xszz/bdpz/mkxxUpdate.jsp"%>--%>
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
                        <button type="button" id="btj" type="button"
                                onclick="sqSave('notj');">
                            ����ݸ�
                        </button>
                        <button type="button" id="tj" type="button"
                                onclick="sqSave('tj');">
                            �ύ����
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

