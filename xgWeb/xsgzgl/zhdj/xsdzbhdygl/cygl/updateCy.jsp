<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/cygl/js/cygl.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">

        jQuery(function ($) {
            var zzmm = $('#selzzmmdm').val();
            //政治面貌
            jQuery("#zzmmdm option").each(function () {
                if (zzmm == ($(this).val())) {
                    $(this).attr('selected', true);
                }
            });
            //党籍状态
            var djzt = $('#seldjzt').val();
            jQuery("#djzt option").each(function () {
                if (djzt == ($(this).val())) {
                    $(this).attr('selected', true);
                }
            });

            //是否失联
            var sfsl = $('#selsfsl').val();
            jQuery("#sfsl option").each(function () {
                if (sfsl == ($(this).val())) {
                    $(this).attr('selected', true);
                }
            });


            //是否流动

            var sfld = $('#selsfld').val();
            jQuery("#sfld option").each(function () {
                if (sfld == ($(this).val())) {
                    $(this).attr('selected', true);
                }
            });

            //党支部
            var dabid = $('#seldzbid').val();
            jQuery("#dzbid option").each(function () {
                if (dabid == ($(this).val())) {
                    $(this).attr('selected', true);
                }
            });

            djztgb();
            djslgb();
            djldgb();

        });

        //            jQuery(function(){
        //                djztgb();
        //            });

    </script>
    <style type="text/css">
    </style>
</head>
<body>
<html:form action="/dzdy_cygl" method="post" styleId="CyglForm">

    <div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;">
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>修改学生党支部成员信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>学号</th>
                <td>
                        <%--<input type="text" name="xh" value="${cyMap.xh}" id="xh" style="width:120px;" readonly="readonly" class="text_nor">--%>
                        ${cyMap.xh}
                    <html:hidden property="xh" styleId="xh" value="${cyMap.xh}"></html:hidden>
                        <%--<button class="btn_01" type="button" onclick="showDialog('请选择一个学生',800,500,'dzdy_cygl.do?method=getXx');">选择</button>--%>
                </td>
                <th>姓名</th>
                <td><span id="xm">${cyMap.xm}</span></td>
            </tr>
            <tr>
                <th width="20%">性别</th>
                <td width="30%"><span id="xb">${cyMap.xb}</span></td>
                <th width="20%">出生日期</th>
                <td width="30%"><span id="csrq">${cyMap.csrq}</span></td>
            </tr>
            <tr>
                <th width="20%">籍贯</th>
                <td width="30%"><span id="jg">${cyMap.jg}</span></td>
                <th width="20%">年级</th>
                <td width="30%"><span id="nj">${cyMap.nj}</span></td>
            </tr>
            <tr>
                <th width="20%">民族</th>
                <td width="30%"><span id="mzmc">${cyMap.mzmc}</span></td>
                <th width="20%">入学时间</th>
                <td width="30%"><span id="rxrq">${cyMap.rxrq}</span></td>
            </tr>
            <tr>
                <th width="20%">书院</th>
                <td width="30%"><span id="symc">${cyMap.symc}</span></td>
                <th width="20%">专业</th>
                <td width="30%"><span id="zymc">${cyMap.zymc}</span></td>
            </tr>
            <tr>
                <th width="20%">身份证号</th>
                <td width="30%"><input id="sfzh" name="sfzh" value="${cyMap.sfzh}" readonly="readonly" style="border-style: none"/></td>
            </tr>
            <tr>
                <th width="20%">专业班级</th>
                <td width="30%"><span id="zybjmc">${cyMap.zybjmc}</span></td>
                <th width="20%">行政班级</th>
                <td width="30%"><span id="bjmc">${cyMap.bjmc}</span></td>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
							<span>联系方式
							</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%">联系电话</th>
                <td width="30%"><span id="lxdh">${cyMap.lxdh}</span>
                </td>
                <th width="20%">固定电话</th>
                <td width="30%"><span id="jtdh">${cyMap.jtdh}</span></td>
            </tr>
            <tr>
                <th width="20%">家庭住址</th>
                <td width="30%"><span id="jtdz">${cyMap.jtdz}</span></td>
                <th width="20%">邮箱</th>
                <td width="30%"><span id="dzyx">${cyMap.dzyx}</span></td>
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
                <td width="30%"><html:text property="rdjsr" styleId="rdjsr" style="width:90%" value="${cyMap.rdjsr}"/></td>
                <th width="20%">党支部</th>
                <td width="30%">
                    <input type="hidden" id="seldzbid" value="${cyMap.dzbid}">
                    <html:select property="dzbid" styleId="dzbid" style="width:152px;">
                    <html:options collection="dzbList" property="dzbid" labelProperty="dzbmc"/>
                </html:select>
                </td>

            </tr>
            <tr>
                <th width="20%">参加党课时间</th>
                <td width="30%"><html:text property="cjdksj" value="${cyMap.cjdksj}" styleId="cjdksj"
                                           onclick="return showCalendar('cjdksj','y-mm-dd');"
                                           maxlength="10"></html:text></td>
                <th width="20%">积极分子时间</th>
                <td width="30%"><html:text property="jjfzsj" value="${cyMap.jjfzsj}" styleId="jjfzsj"
                                           onclick="return showCalendar('jjfzsj','y-mm-dd');"
                                           maxlength="10"></html:text></td>
            </tr>
            <tr>
                <th width="20%">预备党员时间</th>
                <td width="30%"><html:text property="ybdysj" value="${cyMap.ybdysj}" styleId="ybdysj"
                                           onclick="return showCalendar('ybdysj','y-mm-dd');"
                                           maxlength="10"></html:text></td>
                <th width="20%">正式党员时间</th>
                <td width="30%"><html:text property="zsygsj" value="${cyMap.zsygsj}" styleId="zsygsj"
                                           onclick="return showCalendar('zsygsj','y-mm-dd');"
                                           maxlength="10"></html:text></td>
            </tr>

            <tr>
                <th width="20%">政治面貌</th>
                <td width="30%">
                    <input type="hidden" id="selzzmmdm" value="${cyMap.zzmmdm}"/>
                    <html:select property="zzmmdm" style="width:152px" styleId="zzmmdm">
                        <option value='01'>中国共产党党员</option>
                        <option value='02'>中国共产党预备党员</option>
                    </html:select>
                </td>
                <th width="20%">党籍状态</th>
                <td width="30%" colspan="3">
                    <input type="hidden" id="seldjzt" value="${cyMap.djzt}"/>
                    <html:select property="djzt" style="width:152px" styleId="djzt" onchange="djztgb();">
                        <option value='正常'>正常</option>
                        <option value='不正常'>不正常</option>
                    </html:select>
                </td>

            </tr>
            <tr id="sl" style="display: none">
                <th width="20%">是否失联</th>
                <td width="30%">
                    <input type="hidden" id="selsfsl" value="${cyMap.sfsl}"/>
                    <html:select property="sfsl" style="width:152px" styleId="sfsl" onchange="djslgb();">
                        <option value='0'>否</option>
                        <option value='1'>是</option>
                    </html:select>
                </td>

                <th width="20%" id="sl2" style="display: none">失联日期</th>
                <td width="30%" id="sl3" style="display: none"><html:text property="slsj" value="${cyMap.slsj}"
                                                                          styleId="slsj"
                                                                          onclick="return showCalendar('slsj','y-mm-dd');"
                                                                          maxlength="10"></html:text></td>

            </tr>
            <tr id="ld" style="display: none">
                <th width="20%">是否流动</th>
                <td width="30%">
                    <input type="hidden" id="selsfld" value="${cyMap.sfld}"/>
                    <html:select property="sfld" style="width:152px" styleId="sfld" onchange="djldgb();">
                        <option value='0'>否</option>
                        <option value='1'>是</option>
                    </html:select>
                </td>

                <th width="20%" id="lc2" style="display: none">流出地</th>
                <td width="30%" id="lc3" style="display: none"><html:text property="lcd" styleId="lcd" maxlength="50" value="${cyMap.lcd}"
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
                        <button type="button" onclick="updateSaveCy();">
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