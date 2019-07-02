<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            jQuery('input[name="yxzt"]').click(function () {
                jQuery("#qkmstr").empty();
                var yxzt = jQuery('input[name="yxzt"]:checked').val();
                if(yxzt=="3"){
                    jQuery("#qkmstr").append("<th><span class='red'>*</span>情况描述</th>");
                    jQuery("#qkmstr").append("<td colspan='3'><textarea name='qkms' id='qkms' style='width: 500px;height: 100px;'></textarea></td>");
                }
            })
        })
        function saveFds(){
            var checkId = 'fdsmc-fdsdd-syksrq-syjsrq-sykssj-syjssj';
            var yxzt = jQuery('input[name="yxzt"]:checked').val();
            if(yxzt=="3"){
                checkId += '-qkms';
            }
            if(!checkNotNull(checkId)){
                showAlertDivLayer("请将必填项填写完整！");
                return false;
            }
            save();
        }
        function save(){
            var url = "xyfd_fdswh.do?method=updatefds&type=update";
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }


    </script>
</head>
<body style="width:100%">
<html:form action="/xyfd_fdswh" method="post" styleId="demoForm">
    <div style='width:100%;height:300px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>辅导室信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="16%"><span class="red">*</span>辅导室名称</th>
                <td width="34%">
                    <html:hidden property="id" styleId="id"/>
                    <html:text property="fdsmc" styleId="fdsmc"/>
                </td>
                <th width="16%"><span class="red">*</span>辅导室地点</th>
                <td width="34%">
                    <html:text property="fdsdd" styleId="fdsdd"/>
                </td>
            </tr>
            <tr>
                <th width="16%"><span class="red">*</span>使用开始日期</th>
                <td width="34%">
                    <html:text property="syksrq" styleId="syksrq" onclick="showCalendar('syksrq','yyyyMMdd');" readonly="true"/>
                </td>
                <th width="16%"><span class="red">*</span>使用结束日期</th>
                <td width="34%">
                    <html:text property="syjsrq" styleId="syjsrq" onclick="showCalendar('syjsrq','yyyyMMdd');" readonly="true"/>
                </td>
            </tr>
            <tr>
                <th width="16%"><span class="red">*</span>使用开始时间</th>
                <td width="34%">
                    <html:text property="sykssj" styleId="sykssj" onclick="showCalendar('sykssj','HH:mm');" readonly="true"/>
                </td>
                <th width="16%"><span class="red">*</span>使用结束时间</th>
                <td width="34%">
                    <html:text property="syjssj" styleId="syjssj" onclick="showCalendar('syjssj','HH:mm');" readonly="true"/>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>运行情况</th>
                <td colspan="3">
                    <logic:equal property="yxzt" name="fdsxx" value="1">
                        <input type="radio" name="yxzt" value="1" checked="checked"/>正常运行
                        <input type="radio" name="yxzt" value="2"/>停止运行
                        <input type="radio" name="yxzt" value="3"/>其他
                    </logic:equal>
                    <logic:equal property="yxzt" name="fdsxx" value="2">
                        <input type="radio" name="yxzt" value="1"/>正常运行
                        <input type="radio" name="yxzt" value="2" checked="checked"/>停止运行
                        <input type="radio" name="yxzt" value="3"/>其他
                    </logic:equal>
                    <logic:equal property="yxzt" name="fdsxx" value="3">
                        <input type="radio" name="yxzt" value="1"/>正常运行
                        <input type="radio" name="yxzt" value="2"/>停止运行
                        <input type="radio" name="yxzt" value="3" checked="checked"/>其他
                    </logic:equal>
                </td>
            </tr>
            <tr id="qkmstr">
                <logic:equal value="3" property="yxzt" name="fdsxx">
                    <th><span class="red">*</span>情况描述</th>
                    <td colspan="3">
                        <html:textarea property="qkms" styleId="qkms" style="width: 500px;height: 100px;"></html:textarea>
                    </td>
                </logic:equal>
            </tr>
            </tbody>
        </table>
    </div>
    <div>
        <table width="100%" border="0" class="formlist">
            <tfoot>
            <tr>
                <td colspan="4" >
                    <div class="bz">"<span class="red">*</span>"为必填项</div>
                    <div class="btn">
                        <button type="button" type="button" onclick="saveFds();return false;" >
                            保存
                        </button>
                        <button type="button" name="关 闭" onclick="iFClose();">
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

