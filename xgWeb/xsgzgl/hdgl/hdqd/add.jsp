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
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript">
		jQuery(function(){
			jQuery("#xh").attr("style","width:120px;");
			jQuery("#xh").attr("readonly",false);
			jQuery(".btn_01").show();
		})
        function saveZwsq(){
            var checkId = 'xh-hdid';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("请将必填项填写完整！");
                return false;
            }
			var bmlx = jQuery("#bmlx").val();
			if(bmlx=="0"){
				var dwid = jQuery("#dwid").val();
				if(dwid==""||dwid==null){
					confirmInfo("未选择队伍，确定创建队伍吗?",function(ok){
							if(ok=="ok"){		
								save();
							}
					});
				}else{
					save();
				}
			}
			else{
				save();
			}
        }
		function save(){
			var url = "hdgl_hdgl_hdqd_wh.do?method=add&type=save";
			ajaxSubFormWithFun("demoForm",url,function(data){
			    showAlertDivLayer(data["message"],{},{"clkFun":function(){
			        if (parent.window){
			            refershParent();
			        }
			        iFClose();
			    }});
			});
		}
        // function selectCallBack(rowData){
        //     jQuery("#hdid").val(rowData["hdid"]);
        //     jQuery("#hdmc").val(rowData["hdmc"]);
        //     jQuery("#bmlx").val(rowData["bmlx"]);
        //     jQuery("#hdddTr").html(rowData["hddd"]);
        //     jQuery("#kssjTr").html(rowData["hdkssj"]);
        //     jQuery("#jssjTr").html(rowData["hdjssj"]);
        // }
		function selectHd(){
			var xh = jQuery("#xh").val();
			var goto = encodeURIComponent('${path}'+'&xh='+xh);
		    showDialog("选择活动",800,500,"hdgl_hdgl_hdqd_wh.do?method=getHdxxList&goto="+goto);
		}
		function selectDw(){
			var xh = jQuery("#xh").val();
			var hdid = jQuery("#hdid").val();
			var goto = encodeURIComponent('${path}'+'&xh='+xh+'&hdid='+hdid);
			showDialog("选择队伍",600,500,"hdgl_hdgl_hdqd_wh.do?method=getDwList&xh="+xh+"&hdid="+hdid+"&goto="+goto);
		}
		function chearDw(){
			jQuery("#dwid").val("");
			jQuery("#dzxm").val("");
		}
    </script>
</head>
<body style="width:100%">
<html:form action="/hdgl_hdgl_hdqd_wh" method="post" styleId="demoForm">
    <div style='width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生信息</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/hdgl/hdqd/selectStudentforqd.jsp" %>
            <thead>
                <tr>
                    <th colspan="4">
                        <span>活动信息</span>
                    </th>
                </tr>
            </thead>
            <tbody>
            <tr>
                <th><span class="red">*</span>活动名称</th>
                <td>
                    <html:hidden property="hdid" styleId="hdid" />
                    <html:hidden property="bmlx" styleId="bmlx" />
                    <html:text property="hdmc" styleId="hdmc" readonly="true" />
                    <button class="btn_01" type="button" onclick="selectHd();">选择</button>
                </td>
                <th>活动地点</th>
                <td>
    				<html:text property="hddd" styleId="hddd" readonly="true" style="border: none;"/>
                </td>
            </tr>
    		<tr>
    		    <th>活动开始时间</th>
    		    <td>
    				<html:text property="hdkssj" styleId="kssj" readonly="true" style="border: none;"/>
    		    </td>
    		    <th>活动结束时间</th>
    		    <td>
    				<html:text property="hdjssj" styleId="jssj" readonly="true" style="border: none;"/>
    		    </td>
    		</tr>
            </tbody>
    		<logic:equal name="hdxx" property="bmlx" value="0">
    			<thead>
    			    <tr>
    			        <th colspan="4">
    			            <span>该活动为团队活动，请选择队伍（创建队伍请勿选）</span>
    			        </th>
    			    </tr>
    			</thead>
    			<tbody>
    			<tr>
    			    <th>队伍编号</th>
    			    <td>
    			    	<html:text  property="dwid" styleId="dwid" readonly="true" style="border: none;width: 120px;"></html:text>
    			    	<button class="btn_01" type="button" onclick="selectDw();" >选择</button>
						<button class="btn_01" type="button" onclick="chearDw();" >清除</button>
    			    </td>
    			    <th>队长姓名</th>
    			    <td>
    			    	<html:text  property="dzxm" styleId="dzxm" readonly="true" style="border: none;"></html:text>
    			    </td>
    			</tr>
    			</tbody>
    		</logic:equal>
    		<thead>
    		    <tr>
    		        <th colspan="4">
    		            <span>签到签退时间</span>
    		        </th>
    		    </tr>
    		</thead>
    		<tbody>
    		<tr>
    		    <th>签到时间</th>
    		    <td>
    				<html:text  property="qdsj" styleId="qdsj"
    				            onclick="showCalendar('qdsj','yyyy-MM-dd HH:mm:ss');" ></html:text>
    		    </td>
    		    <th>签退时间</th>
    		    <td>
    				<html:text  property="qtsj" styleId="qtsj"
    				            onclick="showCalendar('qtsj','yyyy-MM-dd HH:mm:ss');" ></html:text>
    		    </td>
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
                        <button type="button" type="button" onclick="saveZwsq();return false;" >
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

