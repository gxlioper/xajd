<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/dzbgl/js/dzbgl.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">
        function myfun() {
            var value = document.getElementById("seldzblx").value;

            var jgdzb = document.getElementsByName("jgdzb");
            var xsdzb = document.getElementsByName("xsdzb");

            if (value == "教工党支部") {
                for (var i = 0; i < jgdzb.length; i++) {
                    jgdzb[i].style.display = "";
                }
                ;
                for (var i = 0; i < xsdzb.length; i++) {
                    xsdzb[i].style.display = "none";
                }
                ;
            }
            ;
            if (value == "学生党支部") {
                for (var i = 0; i < xsdzb.length; i++) {
                    xsdzb[i].style.display = "";
                }
                ;
                for (var i = 0; i < jgdzb.length; i++) {
                    jgdzb[i].style.display = "none";
                }
                ;
            }
            ;
        }

        window.onload = myfun;

        jQuery(function ($) {
            var dzblx = $('#seldzblx').val();
            //党支部类型
            jQuery("#dzblx option").each(function () {
                if (dzblx == ($(this).val())) {
                    $(this).attr('selected', true);
                }
            });
        });


    </script>
    <style type="text/css">
    </style>
</head>
<body>
<html:form action="/dzdy_dzbgl" method="post" styleId="DzbglForm">
    <input type="hidden" value="${dzbid}" name="dzbid" id="dzbid"/>
    <input type="hidden" value="${dzbhjid}" name="dzbhjid" id="dzbhjid"/>
    <div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;">
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>修改党支部信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>党支部代码</th>
                <td colspan="3">
                        ${dzbid}
                </td>
            </tr>
            <tr>
                <th>名称</th>
                <td colspan="3">
                        ${dzbmc}
                </td>
            </tr>
            <tr>
                <th>所属基层党委</th>
                <td colspan="3">
                    <html:select property="jcdwdm" name="jcdwdm" value="dzbdm" styleId="jcdwdm" style="width:90%">
                        <html:options collection="jcdwList" property="dm" labelProperty="mc"/>
                    </html:select>
                        <%--${dzbdm}--%>
                </td>
            </tr>
            <tr>
                <th>所属书院</th>
                <td colspan="3">
                        ${symc}
                </td>
            </tr>
            <tr>
                <th>成立时间</th>
                <td colspan="3">
                    <html:text property="clsj" value="${clsj}" styleId="clsj"
                               onclick="return showCalendar('clsj','y-mm-dd');" maxlength="10"></html:text>
                        <%--${clsj}--%>
                </td>
            </tr>
            <tr>
                <th>换届时间</th>
                <td colspan="3">
                        <%--<html:text property="hjsj"  styleId="hjsj" onclick="return showCalendar('hjsj','y-mm-dd');"  maxlength="10" ></html:text>--%>
                        ${hjsj}
                </td>
            </tr>
            <tr>
                <th>党支部类型</th>
                <td colspan="3">
                        ${dzblx}
                    <input type="hidden" id="seldzblx" value="${dzblx}" name="dzblx"/>
                </td>
            </tr>

            <logic:iterate id="item" name="jgdzbList" indexId="number">
                <tr class="list" id="jgdzb" name="jgdzb" style="display: none">
                    <th><font color="red">*</font><bean:write name="item" property="jgzwmc"/>
                        <input type="hidden" value="${item.jgzwmc}" name="jgzwmcs" id="jgzwmc${number}"/>
                        <input type="hidden" value="${item.jgzwdm}" name="jgzwdms" id="jgzwdm${number}"/>
                    </th>
                    <td><input type="text" name="jzgmcs" id="jzgmc${number}" value="${item.lxrmc}"
                               style="width:120px;" readonly="readonly" class="text_nor">
                        <button class="btn_01" type="button"
                                onclick="showDialog('请选择一个职工',800,500,'dzdy_dzbgl.do?method=getZg&zwlx=1&aaa=${number}');">
                            选择
                        </button>
                        <input name="lxrzghs" value="${item.lxrid}" id="lxrzgh${number}" type="hidden">
                    <th width="20%"><font color="red">*</font>联系方式</th>
                    <td>
                        <input name="jzgdhs" value="${item.lxrdh}" id="jzgdh${number}">
                    </td>
                </tr>
            </logic:iterate>

            <logic:iterate id="item" name="xsdzbList" indexId="index">
                <tr class="list" id="xsdzb" name="xsdzb" style="display: none">
                    <th><font color="red">*</font><bean:write name="item" property="zwmc"/>
                        <input type="hidden" value="${item.zwmc}" name="zwmcs" id="zwmc${index}"/>
                        <input type="hidden" value="${item.zwdm}" name="zwdms" id="zwdm${index}"/>

                    </th>
                    <td><input type="text" name="xsmcs" id="xsmc${index}" value="${item.lxrmc}"
                               style="width:120px;" readonly="readonly" class="text_nor">
                        <button class="btn_01" type="button"
                                onclick="showDialog('请选择一个学生',800,500,'dzdy_dzbgl.do?method=getXx&bbb=${index}');">选择
                        </button>
                        <input name="xhs" value="${item.lxrid}" id="xh${index}" type="hidden">
                    <th width="20%"><font color="red">*</font>联系方式</th>
                    <td>
                        <input name="xsdhs"  value="${item.lxrdh}" id="xsdh${index}">
                    </td>
                </tr>
            </logic:iterate>
                <%--<tr>
                    <th>党支部书记</th>
                    <td>
                        <input type="text" name="sjxm" value="${sjxm}" id="sjxm" style="width:120px;" readonly="readonly"
                               class="text_nor">
                            <input  name="xh" value="" id="xh"  type="hidden" >
                        <html:hidden property="dzbsj" styleId="dzbsj"/>
                        <button class="btn_01" type="button"
                                onclick="showDialog('请选择一个职工',800,500,'dzdy_dzbgl.do?method=getZg&zwlx=1');">选择
                        </button>
                    </td>
                    <th width="20%">联系方式</th>
                    <td><html:text property="sjlxdh" styleId="sjlxdh" onkeyup="checkInput(this)" maxlength="11"
                                   style="width:90%"/></td>
                </tr>
                <tr>
                    <th width="20%">组织委员</th>
                    <td><input type="text" name="zzwyxm" id="zzwyxm" value="${zzwyxm}" style="width:120px;"
                               readonly="readonly" class="text_nor">
                            <input  name="xh" value="" id="xh"  type="hidden" >
                        <html:hidden property="zzwy" styleId="zzwy"/>
                        <button class="btn_01" type="button"
                                onclick="showDialog('请选择一个职工',800,500,'dzdy_dzbgl.do?method=getZg&zwlx=2');">选择
                        </button>
                    </td>
                    <th width="20%">联系方式</th>
                    <td><html:text property="zzwylxdh" styleId="zzwylxdh" onkeyup="checkInput(this)" maxlength="11"
                                   style="width:90%"/></td>
                </tr>

                <tr>
                    <th width="20%">宣传委员</th>
                    <td><input type="text" name="xcwyxm" id="xcwyxm" value="${xcwyxm}" style="width:120px;"
                               readonly="readonly" class="text_nor">
                            <input  name="xh" value="" id="xh"  type="hidden" >
                        <html:hidden property="xcwy" styleId="xcwy"/>
                        <button class="btn_01" type="button"
                                onclick="showDialog('请选择一个职工',800,500,'dzdy_dzbgl.do?method=getZg&zwlx=3');">选择
                        </button>
                    </td>
                    <th width="20%">联系方式</th>
                    <td><html:text property="xcwylxdh" styleId="xcwylxdh" onkeyup="checkInput(this)" maxlength="11"
                                   style="width:90%"/></td>
                </tr>
                <tr>
                    <th width="20%">纪检委员</th>
                    <td><input type="text" name="jjwyxm" id="jjwyxm" style="width:120px;" value="${jjwyxm}"
                               readonly="readonly" class="text_nor">
                            <input  name="xh" value="" id="xh"  type="hidden" >
                        <html:hidden property="jjwy" styleId="jjwy"/>
                        <button class="btn_01" type="button"
                                onclick="showDialog('请选择一个职工',800,500,'dzdy_dzbgl.do?method=getZg&zwlx=4');">选择
                        </button>
                    </td>
                    <th width="20%">联系方式</th>
                    <td><html:text property="jjwylxdh" styleId="jjwylxdh" onkeyup="checkInput(this)" maxlength="11"
                                   style="width:90%"/></td>
                </tr>
    --%>

            </tbody>
        </table>
    </div>
    <div style="height:30px;"></div>
    <%--;height:520px --%>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <input type="hidden" value="${jgcount}" name="jgcount" id="jgcount"/>
            <input type="hidden" value="${xscount}" name="xscount" id="xscount"/>
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "<span class="red">*</span>"为必填项
                    </div>
                    <div class="btn">
                        <button type="button" onclick="updateSaveDzb(dzblx,jgcount,xscount);">
                            保 存
                        </button>
                        <button type="button" onclick="iFClose();">
                            关 闭
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