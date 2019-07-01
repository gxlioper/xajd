<%@ page language="java" import="java.util.*,xgxt.utils.String.StringUtils" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/qgzx/wycjqgzx/js/wycjqgzx.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">
    jQuery(function(){
        jQuery("input[type='checkbox']").attr("disabled","disabled");
    });
    </script>
</head>
<body style="width:100%">
<html:form action="/qgzx_dgxsk" method="post" styleId="demoForm">
    <input type="hidden" name="qgxmSize" id="qgxmSize" value="${qgxmSize }"/>
    <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
        <div class="toolbox">
            <div class="buttonbox">
            </div>
            <table width="100%" border="0" class="formlist" >
                <thead>
                <tr>
                    <th colspan="4">
                        <span>基本信息</span>

                    </th>
                </tr>
                </thead>
                <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
                <thead>
                <tr>
                    <th colspan="4">
                        <span>学生空余时间</span>
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td colspan="4">
                        <table style="width:100%;">
                            <thead >
                            <tr id="qgmxTr">
                                <th style="text-align: center;">日期</th>
                                <logic:iterate id="q" name="qgxmList">
                                    <th style="text-align: center;" xmdm="${q.dm }">${q.mc }</th>
                                </logic:iterate>
                            </tr>
                            </thead>
                            <tbody id="qgmxTbody">
                            <%
                                List<HashMap<String,String>> qgmxList = (List<HashMap<String,String>>)request.getAttribute("qgmxList");
                                List<HashMap<String,String>> qgxmList = (List<HashMap<String,String>>)request.getAttribute("qgxmList");
                                if (qgmxList != null){
                                    for (int i = 0 ; i < qgmxList.size() ; i++){

                                        String[] qgmxArr = null ;
                                        if(StringUtils.isNotNull(qgmxList.get(i).get("qgmx"))){
                                            qgmxArr = qgmxList.get(i).get("qgmx").split(",");
                                        }
                            %>
                            <tr>
                                <td align="center">
                                    <%=qgmxList.get(i).get("qgrq") %>
                                    <input type="hidden" value="<%=qgmxList.get(i).get("qgrq") %>"  name="mxrq"/>
                                </td>
                                <%
                                    for (int j = 0 ; j < qgxmList.size() ; j++){
                                %>
                                <td align="center">
                                    <input type="checkbox" value="<%=qgxmList.get(j).get("dm") %>" name="mxxm<%=i %>"
                                            <%
                                                if (StringUtils.stringExistArray(qgxmList.get(j).get("dm"),qgmxArr)){
                                            %>
                                           checked="checked"
                                            <%
                                                }
                                            %>

                                    />
                                </td>
                                <%
                                    }
                                %>
                            </tr>
                            <%
                                    }
                                }
                            %>
                            </tbody>
                        </table>
                    </td>
                </tr>
                </tbody>
            </table>

        </div>
    </div>
</html:form>
<table width="100%" border="0" class="formlist">
    <tfoot>
    <tr>
        <td colspan="4">
            <div class="btn">
                <button type="button" type="button" onclick="iFClose();">
                    关 闭
                </button>
            </div>
        </td>
    </tr>
    </tfoot>
</table>
</body>
</html>

