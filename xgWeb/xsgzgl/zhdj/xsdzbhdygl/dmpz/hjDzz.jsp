<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/dmpz/js/dmpz.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">


    </script>
    <style type="text/css">
    </style>
</head>
<body>
<html:form action="/dzdy_dmpz" method="post" styleId="dzbdmpzForm">
    <input type="hidden" value="${dzzid}" name="dzzid" id="dzzid"/>
    <input type="hidden" value="${clsj}" name="clsj" id="clsj"/>
    <input type="hidden" value="${dzzmc}" name="dzzmc" id="dzzmc"/>
    <input type="hidden" value="${jcdwdm}" name="jcdwdm" id="jcdwdm"/>
    <div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;">
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>换届信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>党支部代码</th>
                <td colspan="3">
                        ${dzzid}
                </td>
            </tr>
            <tr>
                <th><font color="red">*</font>名称</th>
                <td colspan="3">
                        ${dzzmc}
                </td>
            </tr>
            <tr>
                <th><font color="red">*</font>所属书院</th>
                <td colspan="3">
                        ${symc}
                </td>
            </tr>
            <tr>
                <th><font color="red">*</font>历届时间</th>
                <td colspan="3">
                        ${clsj}
                </td>
            </tr>
            <tr>
                <th><font color="red">*</font>换届时间</th>
                <td colspan="3">
                    <input type="hidden" value="${hjsj}" name="hjsjOld" id="hjsjOld"/>
                    <html:text property="hjsj" styleId="hjsj"
                               onclick="return showCalendar('hjsj','yyyy-MM-dd',false,'hjsj');" maxlength="10"></html:text>
                </td>
            </tr>
             
           <logic:iterate id="item" name="dzzList" indexId="number">
				<tr class="list"  id ="dzz" name = "dzz">
					<th><font color="red">*</font><bean:write name="item" property="zwmc" />
					<input type="hidden" value="${item.zwmc}" name="zwmcs" id="zwmc${number}"/>
					<input type="hidden" value="${item.zwdm}" name="zwdms" id="zwdm${number}"/>
					</th>
					<td><input type="text"  name="jzgmcs" id="jzgmc${number}" value="${item.lxrxm}"
						style="width:120px;" readonly="readonly" class="text_nor">           
	                    <button class="btn_01" type="button"
	                            onclick="showDialog('请选择一个职工',800,500,'dzdy_dzbgl.do?method=getZg&zwlx=1&aaa=${number}');">选择
	                    </button>
	                   <input  name="lxrzghs" value="${item.lxrid}" id="lxrzgh${number}"  type="hidden" >
					<th width="20%"><font color="red">*</font>联系方式</th>
	                <td>
	                    <input  name="jzgdhs" value ="${item.lxrdh}" id="jzgdh${number}" >
	                </td>
                </tr>
		    </logic:iterate>

            </tbody>
        </table>
    </div>
    <div style="height:30px;"></div>
    <%--;height:520px --%>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">

            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "<span class="red">*</span>"为必填项
                    </div>
                    <div class="btn">
                        <button type="button" onclick="hjAddDzz();">
                            保 存
                        </button>
                        <button type="button" onclick="iFClose();">
                            关闭
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