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
    <script type="text/javascript">
        function save(){
            if(typeof (jQuery("#xn").val()) == "undefined" || jQuery("#tssj").val() == ""){
                showAlert("请将必填项填写完整！");
                return false;
            }
            var url = "gygl_fygl_cwxxgl10698.do?method=ts&type=save";
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
        jQuery(function(){

        })

    </script>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_fygl_cwxxgl10698">
    <input type="hidden" name="pks" value="${pks}">
    <input type="hidden" name="xhs" value="${xhs}">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>退宿信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    <span class="red">*</span>退宿操作说明
                </th>
                <td colspan="3">
                    当前共选中<span class="red">${xzgs}</span>个已入住床位，可执行退宿操作
                </td>
            </tr>
            <logic:notEmpty name="xsxx">
            <tr>
                <th>
                    退宿学生信息
                </th>
                <td colspan="4">
                    <style>
                        #shlccx_table th{text-align: center;}
                        #shlccx_table tr{text-align: center;}
                    </style>
                    <div class="con_overlfow">
                        <table id="shlccx_table" width="100%" class="formlist" >
                            <tr>
                                <th>学号</th>
                                <th>姓名</th>
                                <th>性别</th>
                                <th>年级</th>
                                <th>学院</th>
                                <th>楼栋</th>
                                <th>寝室</th>
                                <th>床位</th>
                            </tr>
                            <tbody id="lcTd">
                                <tr>
                                    <td>${xsxx.xh}</td>
                                    <td>${xsxx.xm}</td>
                                    <td>${xsxx.xb}</td>
                                    <td>${xsxx.nj}</td>
                                    <td>${xsxx.xymc}</td>
                                    <td>${xsxx.ldmc}</td>
                                    <td>${xsxx.qsh}</td>
                                    <td>${xsxx.cwh}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </td>
            </tr>
            </logic:notEmpty>
            <tr>
                <th><span class="red">*</span>退宿原因</th>
                <td>
                    <html:select property="tsyy">
                        <html:options property="tsyydm" labelProperty="tsyymc" collection="tsyyList"></html:options>
                    </html:select>
                </td>
                <th><span class="red">*</span>退宿时间</th>
                <td>
                    <html:text property="tssj" styleId="tssj" readonly="true"
                               onclick="return showCalendar('tssj','yyyy-MM-dd');"></html:text>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>学年/学期</th>
                <td>
                    <html:select property="xn" styleId="xn">
                        <html:options property="xn" labelProperty="xn" collection="xnList"></html:options>
                    </html:select>
                    <html:select property="xq">
                        <html:options property="xqdm" labelProperty="xqmc" collection="xqList"></html:options>
                    </html:select>
                </td>
                <th><span class="red">*</span>床位是否初始化</th>
                <td>
                    <html:radio property="sfcshcw" value="1">&nbsp;是</html:radio>
                    <html:radio property="sfcshcw" value="0">&nbsp;否</html:radio>
                </td>
            </tr>
            <tr>
                <th>备注<br><span class="red">(限500字)</span></th>
                <td colspan="3">
                    <html:textarea property="bz" styleId="bz" rows="4" style="width:98%"></html:textarea>
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
                        <button type="button" onclick="save();" id="buttonSave">
                            保 存
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