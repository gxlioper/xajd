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
            <th name="th_${j.zddm}">
                <logic:equal value="yes" name="j" property="sfbt">
                    <font color="red">*</font>
                </logic:equal>
                    ${j.zdmc }
            </th>
        </logic:notEqual>
        <logic:equal value="radio" name="j" property="kjlx">
            <td name="radioTd" property="${j.zddm }" source="${j.sjy }"
                tempValue='<bean:write name="mkxxForm" property="${j.zddm}"/>'
                    <logic:equal value="yes" name="j" property="sfbt">
                        sfbt="yes"
                    </logic:equal>
            ></td>
        </logic:equal>
        <logic:equal value="text" name="j" property="kjlx">
            <td>
                <input id="jtqk_${j.zddm}" type="text" name="${j.zddm}" maxlength="${j.zdcd}" style="${j.kjys}"
                        <logic:equal value="yes" name="j" property="sfbt">
                            sfbt="yes"
                        </logic:equal>
                       value='<bean:write name="mkxxForm" property="${j.zddm}"/>'
                       onblur="${j.zdgs }"
                       style="${j.kjys }"
                />
            </td>
        </logic:equal>
        <logic:equal value="view" name="j" property="kjlx">
            <td>
                <bean:write name="mkxxForm" property="${j.zddm}"/>
            </td>
        </logic:equal>
        <logic:equal value="select" name="j" property="kjlx">
            <td>
                <select name="${j.zddm}" style="${j.kjys}" source="${j.sjy }" type="source"
                        tempValue='<bean:write name="mkxxForm" property="${j.zddm}"/>'
                        <logic:equal value="yes" name="j" property="sfbt">
                            sfbt="yes"
                        </logic:equal>
                >
                    <option></option>
                </select>
            </td>
        </logic:equal>

        <logic:equal value="checkbox" name="j" property="kjlx">
            <%--<logic:equal value="10026" name="xxdm">
                <td name="checkboxTd" colspan="3" style="width:98%;${j.kjys}"
                    property="${j.zddm }" source="${j.sjy }"
                    tempValue='<bean:write name="mkxxForm" property="${j.zddm}"/>'
                    <logic:equal value="yes" name="j" property="sfbt">
                        sfbt="yes"
                    </logic:equal>
                    >
                </td>
            </logic:equal>
                <logic:notEqual value="10026" name="xxdm">--%>
            <td name="ckbTd"  rowspan="1" style="${j.kjys}"
                property="${j.zddm }" source="${j.sjy }"
                tempValue='<bean:write name="mkxxForm" property="${j.zddm}"/>'
                    <logic:equal value="yes" name="j" property="sfbt">
                        sfbt="yes"
                    </logic:equal>
            >
            </td>
            <%--</logic:notEqual>--%>

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
                <th name="th_${j.zddm}">
                    <logic:equal value="yes" name="j" property="sfbt">
                        <font color="red">*</font>
                    </logic:equal>
                        ${j.zdmc }
                    <br/>
                    <font color="red">
                        &lt;²»³¬¹ý100¸ö×Ö·û&gt;
                    </font>
                </th>
                    <%--						<logic:notEqual value="12930" name="xxdm">
                                                <td colspan="3" style="word-break:break-all;">
                                                    <textarea rows="5" style="width:98%;${j.kjys}" name="${j.zddm}"
                                                              onblur="checkLen(this,${j.zdcd });"
                                                              <logic:equal value="yes" name="j" property="sfbt">
                                                                  sfbt="yes"
                                                              </logic:equal>
                                                    ><bean:write name="mkxxForm" property="${j.zddm}"/></textarea>
                                                </td>
                                            </logic:notEqual>--%>
                <td colspan="3" style="word-break:break-all;">
								<textarea oninput="chCount(this,0,100)" rows="5" style="width:98%;${j.kjys}" name="${j.zddm}"
                                          onblur="checkLen(this,100);"
                                        <logic:equal value="yes" name="j" property="sfbt">
                                            sfbt="yes"
                                        </logic:equal>
                                ><bean:write name="mkxxForm" property="${j.zddm}"/></textarea>
                </td>
            </tr>
        </logic:equal>
    </logic:iterate>
    </tr>
</logic:present>
</tbody>