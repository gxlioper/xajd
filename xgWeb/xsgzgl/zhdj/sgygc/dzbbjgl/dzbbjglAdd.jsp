<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script language="javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/sgygc/dzbbjgl/js/dzbbjglEdit.js"></script>

    <script type="text/javascript">

    </script>
    <style type="text/css">
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/zhdj_dzbbjgl" method="post"  styleId="form">
    <div style='width:100%; height:220px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <tbody id="">
            <tr style="">
                <th width="20%">
                    <span style="color: red">*</span>支部名称
                </th>
                <td  colspan="3">
                    <logic:equal value="true" name="isDzbsj">
                        ${map.dzbmc}
                        <html:hidden property="dzbid" styleId="dzbid" value="${map.dzbid}"/>
                    </logic:equal>
                    <logic:equal value="false" name="isDzbsj">
                        <html:text property="dzbmc" styleId="dzbmc" style="width:150px;" />
                        <button id="btn_dzb" onclick="selectDzb();return false;">
                            选 择
                        </button>
                        <html:hidden property="dzbid" styleId="dzbid" />
                    </logic:equal>

                </td>
            </tr>
            <tr>
                <th width="20%">
                    支部书记
                </th>
                <td  id="zbsjTd" width="30%">
                    <logic:equal value="true" name="isDzbsj">
                        ${map.dzbsjxm}
                    </logic:equal>
                </td>
                <th  width="20%">
                    宣传委员
                </th>
                <td id="xcwyTd"  width="30%">
                    <logic:equal value="true" name="isDzbsj">
                        ${map.xcwyxm}
                    </logic:equal>
                </td>
            </tr>
            <tr>
                <th width="20%">
                    组织委员
                </th>
                <td  id="zzwyTd" width="30%">
                    <logic:equal value="true" name="isDzbsj">
                        ${map.zzwyxm}
                    </logic:equal>
                </td>
                <th  width="20%">
                    纪律委员
                </th>
                <td id="jlwyTd"  width="30%">
                    <logic:equal value="true" name="isDzbsj">
                        ${map.jlwyxm}
                    </logic:equal>
                </td>
            </tr>
            <tr style="">
                <th colspan="4" style="text-align: left">
                    联系班级
                </th>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span> 联系班级
                </th>
                <td colspan="3">
                    <html:text property="bjmc" styleId="bjmc" readonly="true"  style="width:150px; " />
                    <%--<logic:equal value="xx" name="userType">--%>
                        <button id="btn_bj" onclick="selectBj();return false;">
                            选 择
                        </button>
                   <%-- </logic:equal>
                    <logic:equal value="admin" name="userType">
                        <button id="btn_bj" onclick="selectBj();return false;">
                            选 择
                        </button>
                    </logic:equal>
                    <logic:notEqual value="xx" name="userType">
                        <logic:equal value="true" name="isDzbsj">
                            <button id="btn_bj" onclick="selectBj();return false;">
                               选 择
                            </button>
                        </logic:equal>
                    </logic:notEqual>--%>
                    <html:hidden property="bjdm" styleId="bjdm" />
                </td>
            </tr>
            </tbody>


        </table>
    </div>
    <table width="100%" border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">
                    "<span class="red">*</span>"为必填项
                </div>
                <div class="btn">
                    <button id="buttonSave" onclick="save('add');return false;">
                        保 存
                    </button>
                    <button onclick="Close();return false;">
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

