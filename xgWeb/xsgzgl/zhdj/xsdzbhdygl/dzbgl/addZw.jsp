<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/dzbgl/js/zwwh.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">
	    function setTargetIndex(name, index) {
	        document.getElementsByName(name)[0].selectedIndex = index;
	    }
    </script>
    <style type="text/css">
    </style>
</head>
<body>
<html:form action="/dzdy_zwwh" method="post" styleId="DzbzwwhForm">

    <div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;">
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>增加职务</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <th><font color="red">*</font>代码</th>
            <td>
                <html:text property="dm" styleId="dm" onkeyup="checkInput(this)" maxlength="10" style="width:90%"/>
            </td>
            </tr>
            <th><font color="red">*</font>名称</th>
            <td>
                <html:text property="mc" styleId="mc" style="width:90%"/>
            </td>
            </tr>
            <th><font color="red">*</font>职务所属</th>
			<td colspan="3">
                <html:select property="zwss" style="width:152px" styleId="zwss" onchange="setTargetIndex('zwlx', this.selectedIndex)">
                    <option value='教工党支部'>教工党支部</option>
                    <option value='学生党支部'>学生党支部</option>
                    <option value='党总支'>党总支</option>
                </html:select>            
            </td>
            </tr>
                <th><font color="red">*</font>职务类型</th>
			<td>
                <html:select property="zwlx" style="width:152px" styleId="zwlx" onchange="setTargetIndex('zwss', this.selectedIndex)">
                    <option value='教师'>教师</option>
                    <option value='学生'>学生</option>
                    <option value='教师'>教师</option>
                </html:select>     
            </td>
            </tr>

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
                        <button type="button" onclick="saveDm();">
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