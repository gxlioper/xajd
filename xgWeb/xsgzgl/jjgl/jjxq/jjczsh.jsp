<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/jjgl/jjxq/js/jjczsh.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${jjxqForm.sqid}&tt="+new Date().getTime());
            jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${jjxqForm.splc}&shid=${jjxqForm.shid}");
        });

    </script>
</head>
<body>
<html:form action="/jjgl_jjxq" method="post" styleId="jjxqForm">
    <html:hidden  property="sqid" styleId="sqid"/>
    <html:hidden  property="xh" styleId="xh"/>
    <html:hidden  property="splc" styleId="splc"/>
    <html:hidden  property="shid" styleId="shid"/>
    <html:hidden  property="gwid" styleId="gwid"/>
    <html:hidden  property="jjcz" styleId="jjcz"/>
    <html:hidden  property="xqid" styleId="xqid"/>
    <div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="5">
                    <span>学生信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr style="height: 45px;">
                <th width="15%">学号</th>
                <td width="25%">${jbxx.xh }</td>
                <th width="15%">姓名</th>
                <td width="25%">${jbxx.xm }</td>
                <td rowspan="3" align="center">
                    <img src="xsxx_xsgl.do?method=showPhoto&xh=${jbxx.xh }" width="100" height="120" >
                </td>
            </tr>
            <tr style="height: 45px;">
                <th>性别</th>
                <td>${jbxx.xb }</td>
                <th>年级</th>
                <td>${jbxx.nj }</td>
            </tr>
            <tr style="height: 45px;">
                <th>学院</th>
                <td>${jbxx.xymc }</td>
                <th>班级</th>
                <td>${jbxx.bjmc }</td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="5">
                    <span>家教信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr style="height: 45px;">
                <th>擅长科目</th>
                <td colspan="4">
                        ${jjzgForm.sckmmcs }
                </td>
            </tr>
            <tr style="height: 45px;">
                <th>针对年级</th>
                <td>
                        ${jjzgForm.jjnjmc }
                </td>
                <th>联系电话</th>
                <td colspan="2">
                        ${jjzgForm.lxdh }
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="5">
                    <span>家教经历</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="5">
                    <table width="100%">
                        <thead>
                        <tr>
                            <td>
                                家教编号
                            </td>
                            <td>
                                家教学科
                            </td>
                            <td>
                                开始时间
                            </td>
                            <td>
                                结束时间
                            </td>
                        </tr>
                        </thead>
                        <logic:empty name="jjjlList">
                            <tr>
                                <td colspan="5" style="text-align:center;">
                                    暂无!
                                </td>
                            </tr>
                        </logic:empty>
                        <logic:notEmpty name="jjjlList">
                            <logic:iterate id="jjjj" name="jjjlList">
                                <tr>
                                    <td>${jjjj.xqid}</td>
                                    <td>[${jjjj.jjxkmc}]-[${jjjj.jjnjmc}]</td>
                                    <td>${jjjj.kssj}</td>
                                    <td>${jjjj.jssj}</td>
                                </tr>
                            </logic:iterate>
                        </logic:notEmpty>
                    </table>
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="5">
                    <span>家教申请信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr style="height: 45px;">
                <th>
                    家教操作
                </th>
                <td colspan="4">
                    ${jjxqForm.jjczmc}
                </td>
            </tr>
            <tr style="height: 45px;">
                <th>
                    申请理由
                </th>
                <td colspan="4">
                    ${jjxqForm.sqly}
                </td>
            </tr>

            </tbody>
            <thead>
            <tr>
                <th colspan="5">
                    <span>家教投递审核情况</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="5" id="shlccx">

                </td>
            </tr>

            </tbody>
            <thead>
            <tr>
                <th colspan="5">
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
                <td id="shjgSpan" colspan="4">

                </td>
            </tr>
            </tr>
            <tr>
                <th width="20%">
                    <font color="red">*&nbsp;</font> 审核意见
                    <br />
                    <font color="red">(限200字)</font>
                </th>
                <td colspan="4">
                    <jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=jjgl&id=shyj" />
                    <textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="height: 50px"></div>
    <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
        <tfoot>
        <tr>
            <td colspan="5">
                <div class="btn">
                    <button type="button" name="保存"  onclick="jjczshSave();return false;">
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
</html:form>
</body>
</html>
