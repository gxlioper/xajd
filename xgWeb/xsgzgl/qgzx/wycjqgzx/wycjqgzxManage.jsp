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

    </script>
</head>
<body style="width:100%">
<input type="hidden" name="sfxg" id="sfxg" value="${sfxg}"/>
<html:form action="/qgzx_wycjqgzx" method="post" styleId="demoForm">
    <input type="hidden" name="qgxmSize" id="qgxmSize" value="${qgxmSize }"/>

    <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
        <div class="toolbox">
        <div class="buttonbox">
            <ul>
                <logic:equal value="yes" name="sfxg">
                    <logic:equal value="1" name="dgzt">
                        <li>
                            <a href="javascript:void(0);" class="btn_sc" onclick="qxbm();">取消报名</a>
                        </li>
                    </logic:equal>
                    <logic:notEqual value="1" name="dgzt">
                        <li>
                            <a href="javascript:void(0);" class="btn_zj" onclick="save();">报名</a>
                        </li>
                    </logic:notEqual>
                </logic:equal>
            </ul>
        </div>
        <table width="100%" border="0" class="formlist" >
                <thead>
                <tr>
                    <th colspan="4">
                        <span>请勾选你空余时间</span>
                        <label>
                            <input id="qx" type="checkbox" onclick="checkAll(this);" checked="checked"/>全选/取消全选
                        </label>

                        <logic:notEqual value="yes" name="sfxg">
                            <label style="color: red;margin-left: 15px;">您已经有岗位或有岗位在审核中，不能报名</label>
                        </logic:notEqual>
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
                                    <input type="hidden" value="<%=qgmxList.get(i).get("qgrq") %>" name="mxrq"/>
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
</body>
</html>

