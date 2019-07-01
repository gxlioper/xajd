<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/mrgzkh/khsq/khsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function editKhsq(type) {
			if("0" == jQuery("#gs").val()) {
				showAlert("工时不能为零！");
				return false;
			}
            if(parseInt(jQuery("#gs").val())  > 8){
                showAlert("每天工作时长<=8小时！");
                return false;
            }
			if(checkZdz()){
			var url = "mrgzkhKhsq.do?method=saveEditKhsq&type=" + type;
			ajaxSubFormWithFun("GzkhKhsqForm", url, function(data) {
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
		}
		jQuery(function(){
			var isopen = jQuery("#sqkg").val();
			var shzt = jQuery("#shzt").val();
			if('3' != shzt && (isopen==null||isopen==''||"0" == isopen)){
				jQuery("#btn_submit").hide();
			}
		});
		</script>
	</head>
	<body>
		<html:form action="/mrgzkhKhsq" method="post" styleId="GzkhKhsqForm">
		<html:hidden property="sqid" />
		<html:hidden property="xh" styleId="xh" value="${jbxx.xh}"/>
		<input type="hidden" id="sqkg" value="${sqkg}"/>
		<html:hidden styleId="shzt" property="shzt" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>填写信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>用人单位</th>
							<td>
							
								<html:select property="yrdw" styleId="yrdw" onclick="checkXh();" onchange="getGwxx();" style="width:200px">
										<html:options collection="yrdwList" property="bmdm" labelProperty="bmmc" />
									</html:select>
							</td>
							<th><font color="red">*</font>工时</th>
							<td>
								<html:text property="gs" styleId="gs" maxlength="10" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(5)?(?:\d*)?/ig,'$1$2$3')"
                                ></html:text>（小时）
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>岗位</th>
							<td>
								<html:select  property="gwdm" styleId="gwdm" style="width:200px">
									<html:options collection="gwList" property="gwdm" labelProperty="gwmc" />
								</html:select>
							</td>
							<th><font color="red">*</font>工作日期</th>
							<td colspan="3">
								<html:text  property="gzrq" styleId="gzrq" readonly="true" onfocus="showCalendar('gzrq','yyyy-MM-dd');"/>
							</td>
						</tr>
						<tr>
						<tr>
							<th><font color="red">*</font>工作时间段</th>
							<td colspan="3">
								<html:text  property="gzkssj" styleId="gzkssj" onfocus="showCalendar('gzkssj','HH:mm');" />
								-
								<html:text  property="gzjssj" styleId="gzjssj" onfocus="showCalendar('gzjssj','HH:mm');" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								<span style="font-size: larger; color: orchid">温情提示：</span>
								<br>
								<span>1.学生每天完成工作后必须当天在系统中维护工作的时长，若未及时维护，请联系部门管理员老师代维护。</span>
								<br>
								<span>2.每天工作时长<=8小时，每月工作时长<=40小时，请合理安排工作时间。</span>
							</div>
						</td>
					</tr>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="editKhsq('save');">
										保存草稿
									</button>
									<button type="button" id="btn_submit" onclick="editKhsq('submit');">
										提交申请
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