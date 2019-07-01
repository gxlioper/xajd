<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/hdgl/js/hdbljg.js"></script>
    <script type='text/javascript' src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
</head>
<body style="width: 100%">
    <html:form action="/hdgl_hdxx" method="post" styleId="hdxxForm" onsubmit="return false;">
        <html:hidden property="hdid" styleId="hdid"/>
        <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;height: 500px' >
            <table width="100%" border="0" class="formlist">
                <thead>
                    <tr>
                        <th width="15%" colspan="2">
                            <span>活动信息</span>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th width="15%">
                            <span>活动名称</span>
                        </th>
                        <td width="35%">
                                ${data.hdmc}
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span>活动时间</span>
                        </th>
                        <td>
                            ${data.hdkssj} ~ ${data.hdjssj}
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span><font color='red'>*</font>是否需要报名</span>
                        </th>
                        <td>
                            <html:select property="bmsf" styleId="bmsf" style="width:50px;" onchange="bmChange()">
                                <html:option value="1">是</html:option>
								<html:option value="0">否</html:option>
                            </html:select>
                        </td>
                    </tr>
                    <tr name="bmxs">
                        <th>
                            <span><font color='red'>*</font>报名是否需要审核</span>
                        </th>
                        <td>
                            <html:select property="bmsfsh" styleId="bmsfsh" style="width:50px;">
                                <html:option value="1">是</html:option>
                                <html:option value="0">否</html:option>
                            </html:select>
                        </td>
                    </tr>
                    <logic:equal name="data" property="bmlx" value="0">
                        <tr>
                            <th>
                                <span><font color='red'>*</font>队伍人数</span>
                            </th>
                            <td>
                                <html:text name="data" property="dwrs" styleId="dwrs" maxlength="4" onblur="checkInputData(this)"></html:text>
                            </td>
                        </tr>
                    </logic:equal>
                    <tr name="bmxs">
                        <th>
                            <span><font color='red'>*</font>报名名单简历信息必填字段设置</span>
                        </th>
                        <td>
                            <logic:iterate id="i" name="jldxList">
                                <input type="checkbox" name="btzd" value="${i.zd}" <logic:equal value="1" name="i" property="sfbt">checked</logic:equal>>${i.mc}
                            </logic:iterate>
                        </td>
                    </tr>
                    <tr name="bmxs">
                        <th>
                            <span><font color='red'>*</font>报名时间</span>
                        </th>
                        <td>
                            <html:text  property="bmkssj" styleId="bmkssj" value="${data.bmkssj}"
                                        onclick="showCalendar('bmkssj','yyyy-MM-dd HH:mm:ss');" readonly="true"></html:text>
                            ~
                            <html:text  property="bmjssj" styleId="bmjssj" value="${data.bmjssj}"
                                        onclick="showCalendar('bmjssj','yyyy-MM-dd HH:mm:ss');" readonly="true"></html:text>
                        </td>
                    </tr>
                </tbody>
            </table>
            <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
                <tfoot>
                <tr>
                    <td colspan="4">
                        <div class="bz">
                            "<span class="red">*</span>"为必填项
                        </div>
                        <div class="btn">
                            <button type="button" onclick="save()">
                                保存
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
<script type="text/javascript">
    function save() {
        var checkids ="bmkssj-bmjssj";
        if(!checkNotNull(checkids)){
            showAlert("请将带<font color='red'>*</font>的项目填写完整!");
            return false;
        }
        var dwrsInput = jQuery("#dwrs");
        if(typeof dwrsInput != "undefined"){
            if(dwrsInput.val() == "0" || dwrsInput.val() == ""){
                showAlert("请将带<font color='red'>*</font>的项目填写完整!");
                return false;
            }
        }
        //开始时间大于结束时间check
        var bmkssj = jQuery("#bmkssj").val();
        var bmjssj = jQuery("#bmjssj").val();
        if(bmkssj > bmjssj){
            showAlert("报名开始时间大于结束时间！");
            return false;
        }
        var url = "hdgl_hdxq.do?method=saveBmsj";
        ajaxSubFormWithFun("hdxxForm", url, function(data) {
            if (data["message"] == "保存成功！") {
                showAlert(data["message"], {}, {
                    "clkFun" : function() {
                        if (parent.window) {
                            refershParent();
                        }
                    }
                });
            } else {
                showAlert(data["message"]);
            }
        });
    }
    
    function bmChange(){
    	if(jQuery("#bmsf").val()=="0"){
    		jQuery('tr[name="bmxs"]').hide();
    	}else{
    		jQuery('tr[name="bmxs"]').show();
    	}
    }
    
    jQuery(function(){
    	if(jQuery("#bmsf").val()=="0"){
    		jQuery('tr[name="bmxs"]').hide();
    	}else{
    		jQuery('tr[name="bmxs"]').show();
    	}
    })
    
    
</script>
</html>
