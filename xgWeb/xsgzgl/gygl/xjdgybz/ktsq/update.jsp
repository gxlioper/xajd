<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/gygl/xjdgybz/ktsq/js/ktsq.js"></script>
    <script type="text/javascript">
        function save(type){
            var url = "gygl_gybz_wh.do?method=update&type="+type;
            ajaxSubFormWithFun("demoForm", url, function(data) {
                if(data["message"]=="保存成功！"){
                    showAlert(data["message"],{},{"clkFun":function(){
                        if (parent.window){
                            refershParent();
                        }
                    }});
                }else{
                    showAlert(data["message"]);
                }
            });
        }
    </script>
    <style type="text/css">
        #shlccx_table th{text-align: center;}
        #shlccx_table tr{text-align: center;}
    </style>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_gybz_wh">
    <html:hidden property="sqid"></html:hidden>
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>寝室信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    学年
                </th>
                <td>
                    <html:select property="xn" styleId="xn">
                        <html:options collection="xnList" property="xn" labelProperty="xn"></html:options>
                    </html:select>
                </td>
                <th>
                    校区
                </th>
                <td>
                    <html:select property="xqdm" styleId="xqdm" onchange="xqChange()">
                        <html:options collection="xqList" property="dm" labelProperty="xqmc"></html:options>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th>
                    宿舍楼栋号
                </th>
                <td>
                    <html:select property="lddm" styleId="lddm" onchange="ldChange();">
                        <html:options collection="ldList" property="lddm" labelProperty="ldmc"></html:options>
                    </html:select>
                </td>
                <th>房间号</th>
                <td>
                    <html:select property="qsh" styleId="qsh" onchange="qshChange();">
                        <html:options collection="qsList" property="qsh" labelProperty="qsh"></html:options>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th>宿舍人数</th>
                <td>
                    <html:text styleId="rs" property="rs"/>
                </td>
                <th>宿舍长</th>
                <td>
                    <html:text styleId="sszmc" property="sszmc"/>
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>寝室成员</span>
                </th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td colspan="4">
                        <table id="shlccx_table" width="100%">
                            <tr>
                                <th>学院</th>
                                <th>姓名</th>
                                <th>班级</th>
                                <th>学号</th>
                                <th>联系电话</th>
                                <th>网址</th>
                                <th>电费分摊比例</th>
                            </tr>
                            <logic:iterate id="i" name="qscyList" indexId="index">
                                <tr>
                                    <td>${i.symc}</td>
                                    <td>${i.xm}</td>
                                    <td><input type="hidden" name="jfxx[<%=index%>].cwh" value="${i.cwh}">${i.bjmc}</td>
                                    <td><input type="hidden" name="jfxx[<%=index%>].xh" value="${i.xh}">${i.xh}</td>
                                    <td>${i.lxdh}</td>
                                    <td><input name="jfxx[<%=index%>].wz" value="${i.wz}" style="width: 80px;"></td>
                                    <td><input name="jfxx[<%=index%>].ftbl" value="${i.ftbl}" style="width: 60px;"></td>
                                </tr>
                            </logic:iterate>
                        </table>
                    </td>
                </tr>
                <tr>
                    <th>功率</th>
                    <td>
                        <html:radio property="gl" value="1.0P">1.0P</html:radio>
                        <html:radio property="gl" value="1.2P">1.2P</html:radio>
                    </td>
                    <th>使用方式</th>
                    <td>
                        <html:radio property="syfs" value="租用">租用</html:radio>
                        <html:radio property="syfs" value="自购">自购</html:radio>
                    </td>
                </tr>
                <tr>
                    <th>使用年限</th>
                    <td>
                        <html:text property="synx" styleId="synx" maxlength="2"/>
                    </td>
                    <th>安装日期</th>
                    <td>
                        <html:text property="azrq" styleId="azrq" readonly="true" onclick="return showCalendar('azrq','yyyy-MM-dd');"/>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div style="height:30px;"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                    </div>
                    <div class="btn">
                        <button type="button" onclick="save('save');" id="buttonSave">
                            保存草稿
                        </button>
                        <button type="button" onclick="save('submit');" id="buttonSave">
                            提交申请
                        </button>
                        <button type="button" onclick="iFClose();"  id="buttonClose">
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