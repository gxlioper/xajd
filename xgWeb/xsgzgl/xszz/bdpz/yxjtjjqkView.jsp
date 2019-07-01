<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<tbody>
<%
    boolean tempFlg1 = true;
    List<HashMap<String,String>> yxjtjjzkList = (List<HashMap<String,String>>)request.getAttribute("yxjtjjzkList");
%>
<logic:present name="yxjtjjzkList">
    <tr>
    <logic:iterate id="j" name="yxjtjjzkList" indexId="i">
        <logic:notEqual value="textarea" name="j" property="kjlx">
            <th width="16%">
                    ${j.zdmc }
            </th>
        </logic:notEqual>
        <logic:equal value="radio" name="j" property="kjlx">
            <td width="34%" name="radioTd" id = "jtqk_${j.zddm }"  property="${j.zddm }" source="${j.sjy }"
                tempValue='<bean:write name="mkxxForm" property="${j.zddm}"/>'
            ></td>
        </logic:equal>
        <logic:equal value="text" name="j" property="kjlx">
            <td width="34%" class="breakword">
                <bean:write name="mkxxForm" property="${j.zddm}"/>
            </td>
        </logic:equal>
        <logic:equal value="view" name="j" property="kjlx">
            <td>
                <bean:write name="mkxxForm" property="${j.zddm}"/>
            </td>
        </logic:equal>
        <logic:equal value="checkbox" name="j" property="kjlx">
            <td>
                <bean:write name="mkxxForm" property="${j.zddm}"/>
            </td>
        </logic:equal>
        <logic:equal value="select" name="j" property="kjlx">
            <td width="34%" >
                <select name="${j.zddm}" source="${j.sjy }" type="source"
                        tempValue='<bean:write name="mkxxForm" property="${j.zddm}"/>'
                >
                    <option></option>
                </select>
            </td>
        </logic:equal>
        <logic:notEqual value="textarea" name="j" property="kjlx">
            <%
                tempFlg1 =true;
                if ((i+1) % 2 == 0 && i != yxjtjjzkList.size()-1){
            %>
            </tr>
            <tr>
            <%
                }
            %>
        </logic:notEqual>
        <logic:equal value="textarea" name="j" property="kjlx">
            <%
                if ((i-1)%2 == 0 && tempFlg1){
            %>
            <th></th>
            <td></td>
            </tr>
            <%
                }
                tempFlg1 =false;
            %>
            <tr>
                <th>
                        ${j.zdmc }
                </th>
                <td colspan="3" class="breakword">
                    <bean:write name="mkxxForm" property="${j.zddm}" filter="false"/>
                </td>
            </tr>
        </logic:equal>
    </logic:iterate>
    </tr>
</logic:present>
</tbody>