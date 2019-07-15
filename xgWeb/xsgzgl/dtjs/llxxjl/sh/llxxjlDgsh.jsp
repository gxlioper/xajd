<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/provicecitylocal.js"></script>
    <script type="text/javascript" src="xsgzgl/xstgl/comm/js/comm.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
            jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
            proviceCiyyLocalMain({type:"view",id:"ddssx",flag:"yxxdz"});
        });


        function saveSh(){
            var shzt = jQuery("#shjg").val();
            if (jQuery("#shzt").val() == "" || jQuery("#shyj").val() == ""){
                showAlert("�뽫��������д������");
                return false;
            }
            var url = "llxxjl_sh.do?method=llxxjlDgsh&type=save";
            ajaxSubFormWithFun("form",url,function(data){
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
    </script>
</head>
<body>
<html:form action="/llxxjl_sq" method="post" styleId="form">
    <html:hidden name="model" property="sqid" styleId="sqid"/>
    <html:hidden name="model" property="xh" styleId="xh"/>
    <html:hidden name="model" property="splc" styleId="splc"/>
    <html:hidden name="model" property="shid" styleId="shid"/>
    <div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>ѧ����Ϣ</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span><font id="gnmkmc_prompt_span"></font></span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    ѧ��
                </th>
                <td>
                        ${rs.xn}
                </td>
                <th>
                    ѧ��
                </th>
                <td>
                        ${rs.xqmc}
                </td>
            </tr>
            <tr>
                <th>
                    �����
                </th>
                <td  colspan="3">
                        ${rs.hdmc}
                </td>

            </tr>
            <tr>
                <th >ʱ��</th>
                <td >
                        ${rs.sj}
                </td>
                <th>
                    ���쵥λ
                </th>
                <td>
                        ${rs.zbdw}
                </td>
            </tr>
            <tr>
                <th >
                    �ص�
                </th>
                <td colspan="3">
                    <html:hidden name="rs"  property="ddssx" styleId="ddssx"/>
                        ${rs.dd}
                </td>
            </tr>
            <tr>
            <tr>
                <th>����</th>
                <td colspan="3">
                    <%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
                    <html:hidden property="fjid" styleId="fjid" value="${rs.fjid}"/>
                    <script type="text/javascript">
                        //���ø���
                        jQuery(function(){
                            var gid = jQuery('#fjid').val();
                            jQuery.MultiUploader_q({
                                gid : gid
                            });
                        });
                    </script>
                </td>
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
                <td colspan="4" id="shlccx">

                </td>
            </tr>

            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>�����Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
            <tr>
                <th >
                    ��˽��
                </th>
                <td id="shjgSpan" colspan="3">

                </td>
            </tr>
            </tr>
            <tr>
                <th width="20%">
                    <font color="red">*&nbsp;</font> ������
                    <br />
                    <font color="red">(��200��)</font>
                </th>
                <td colspan="3">
                    <jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=dtjs&id=shyj" />
                    <textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="height: 30px"></div>
    <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="btn">
                    <button type="button" name="����"  onclick="saveSh();return false;">
                        �� ��
                    </button>
                    <button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
                        �� ��
                    </button>
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
    <!-- ��ʾ��Ϣ -->
    <%@ include file="/comm/other/tsxxNew.jsp"%>
</html:form>
</body>
</html>
