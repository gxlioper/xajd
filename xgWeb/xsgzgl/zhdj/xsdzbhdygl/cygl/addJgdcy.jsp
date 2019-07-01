<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/cygl/js/jgdcygl.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">

        jQuery(function () {
            djztgb();
        });

    </script>
    <style type="text/css">
    </style>
</head>
<body>
<html:form action="/dzdy_cygl" method="post" styleId="JgdcyglForm">

    <div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;">
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>增加教工党支部成员</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>职工号</th>
                <td>
                    <input type="text" name="xh" value="" id="xh" style="width:120px;" readonly="readonly"
                           class="text_nor">
                    <button class="btn_01" type="button"
                            onclick="showDialog('请选择一个教师',800,500,'dzdy_jgdcygl.do?method=getJg');">选择
                    </button>
                </td>
                <th>姓名</th>
                <td><span id="xm"></span></td>
            </tr>
            <tr>
                <th width="20%">性别</th>
                <td width="30%"><span id="xb"></span></td>
                <th width="20%">所属部门</th>
                <td width="30%"><span id="bmmc"></span></td>
            </tr>
            <tr>
                <th width="20%">籍贯</th>
                <td width="30%"><span id="jg"></span></td>
                <th width="20%">书院</th>
                <td width="30%"><span id="symc"></span></td>
            </tr>
            <tr>
                <th width="20%">联系电话</th>
                <td width="30%"><span id="lxdh"></span></td>
                <th width="20%">家庭住址</th>
                <td width="30%"><span id="jtzz"></span></td>
            </tr>
            <tr>
                <th width="20%">电子邮箱</th>
                <td colspan="3"><span id="dzyx"></span></td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>党组织部信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%">入党介绍人</th>
                <td width="30%"><html:text property="rdjsr" styleId="rdjsr" style="width:90%"/>
                </td>
                <th width="20%">党支部</th>
                <td width="30%"><html:select property="dzbid" styleId="dzbid" style="width:152px;">
                    <html:options collection="dzbList" property="dzbid" labelProperty="dzbmc"/>
                </html:select>
                </td>

            </tr>
            <tr>
                <th width="20%">参加党课时间</th>
                <td width="30%"><html:text property="cjdksj" value="${minDate}" styleId="cjdksj"
                                           onclick="return showCalendar('cjdksj','y-mm-dd');"
                                           maxlength="10"></html:text></td>
                <th width="20%">积极分子时间</th>
                <td width="30%"><html:text property="jjfzsj" value="${minDate}" styleId="jjfzsj"
                                           onclick="return showCalendar('jjfzsj','y-mm-dd');"
                                           maxlength="10"></html:text></td>
            </tr>
            <tr>
                <th width="20%">预备党员时间</th>
                <td width="30%"><html:text property="ybdysj" value="${minDate}" styleId="ybdysj"
                                           onclick="return showCalendar('ybdysj','y-mm-dd');"
                                           maxlength="10"></html:text></td>
                <th width="20%">正式党员时间</th>
                <td width="30%"><html:text property="zsygsj" value="${minDate}" styleId="zsygsj"
                                           onclick="return showCalendar('zsygsj','y-mm-dd');"
                                           maxlength="10"></html:text></td>
            </tr>

            <tr>
                <th width="20%">政治面貌</th>
                <td width="30%">
                    <html:select property="zzmmdm" style="width:152px" styleId="zzmmdm">
                        <option value='01'>中国共产党党员</option>
                        <option value='02'>中国共产党预备党员</option>
                    </html:select>
                </td>
                <th width="20%">党籍状态</th>
                <td width="30%" colspan="3">
                    <html:select property="djzt" style="width:152px" styleId="djzt" onchange="djztgb();">
                        <option value='正常'>正常</option>
                        <option value='不正常'>不正常</option>
                    </html:select>
                </td>

            </tr>
            <tr id="sl" style="display: none">
                <th width="20%">是否失联</th>
                <td width="30%">
                    <html:select property="sfsl" style="width:152px" styleId="sfsl" onchange="djslgb();">
                        <option value='0'>否</option>
                        <option value='1'>是</option>
                    </html:select>
                </td>

                <th width="20%" id="sl2" style="display: none">失联日期</th>
                <td width="30%" id="sl3" style="display: none"><html:text property="slsj" value="${minDate}"
                                                                          styleId="slsj"
                                                                          onclick="return showCalendar('slsj','y-mm-dd');"
                                                                          maxlength="10"></html:text></td>

            </tr>
            <tr id="ld" style="display: none">
                <th width="20%">是否流动</th>
                <td width="30%">
                    <html:select property="sfld" style="width:152px" styleId="sfld" onchange="djldgb();">
                        <option value='0'>否</option>
                        <option value='1'>是</option>
                    </html:select>
                </td>

                <th width="20%" id="lc2" style="display: none">流出地</th>
                <td width="30%" id="lc3" style="display: none"><html:text property="lcd" styleId="lcd" maxlength="50"
                                                                          style="width:98%"></html:text></td>

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
                        <button type="button" onclick="saveCy();">
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