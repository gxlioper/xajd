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
                showAlert("请将必填项填写完整！");
                return false;
            }
            var url = "shsjjl_sh.do?method=shsjjlDgsh&type=save";
            ajaxSubFormWithFun("form",url,function(data){
                if(data["message"]=="保存成功！"){
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
<html:form action="/shsjjl_sq" method="post" styleId="form">
    <html:hidden name="model" property="sqid" styleId="sqid"/>
    <html:hidden name="model" property="xh" styleId="xh"/>
    <html:hidden name="model" property="splc" styleId="splc"/>
    <html:hidden name="model" property="shid" styleId="shid"/>
    <div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生信息</span>
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
                    学年
                </th>
                <td>
                        ${rs.xn}
                </td>
                <th>
                    学期
                </th>
                <td>
                        ${rs.xqmc}
                </td>
            </tr>
            <tr>
                <th>
                    活动名称
                </th>
                <td  colspan="3">
                        ${rs.hdmc}
                </td>

            </tr>
            <tr>
                <th >时间</th>
                <td >
                        ${rs.sj}
                </td>
                <th>
                    主办单位
                </th>
                <td>
                        ${rs.zbdw}
                </td>
            </tr>
            <tr>
                <th >
                    地点
                </th>
                <td colspan="3">
                    <html:hidden  property="ddssx" styleId="ddssx"/>
                        ${rs.dd}
                </td>
            </tr>
            <tr>
            <tr>
                <th>附件</th>
                <td colspan="3">
                    <%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
                    <html:hidden property="fjid" styleId="fjid" value="${rs.fjid}"/>
                    <script type="text/javascript">
                        //调用附件
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
                    <span>审批信息</span>
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
                    <span>审核信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
            <tr>
                <th >
                    审核结果
                </th>
                <td id="shjgSpan" colspan="3">

                </td>
            </tr>
            </tr>
            <tr>
                <th width="20%">
                    <font color="red">*&nbsp;</font> 审核意见
                    <br />
                    <font color="red">(限200字)</font>
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
                    <button type="button" name="保存"  onclick="saveSh();return false;">
                        保 存
                    </button>
                    <button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
                        关 闭
                    </button>
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
    <!-- 提示信息 -->
    <%@ include file="/comm/other/tsxxNew.jsp"%>
</html:form>
</body>
</html>
