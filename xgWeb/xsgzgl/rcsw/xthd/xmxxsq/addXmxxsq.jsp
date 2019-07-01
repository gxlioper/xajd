<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script	type="text/javascript">
			function saveForm(type){
				var xmmc = jQuery("#xmmc").val();
				var hdkssj = jQuery("#hdkssj").val();
				var hdjssj = jQuery("#hdjssj").val();
				var lbdm = jQuery("#lbdm").val();
				var hddd = jQuery("#hddd").val();
				if (jQuery.trim(xmmc) == "") {
					showAlert("项目名称不能为空！");
					return false;
				}
				if (jQuery.trim(hdkssj) == "" || jQuery.trim(hdjssj) == "") {
					showAlert("请输入活动时间");
					return false;
				}
				if (jQuery.trim(lbdm) == "") {
					showAlert("活动类别不能为空！");
					return false;
				}
				if (jQuery.trim(hddd) == "") {
					showAlert("活动地点不能为空！");
					return false;
				}
				if(jQuery("#hdmdyy").val().length > 200){
					showAlert("活动目的及意义不能超过200字！");
					return false;
				}
				if(jQuery("#hdfa").val().length > 500){
					showAlert("活动方案不能超过500字！");
					return false;
				}
				if(jQuery("#hdsm").val().length > 1000){
					showAlert("活动说明不能超过1000字！");
					return false;
				}
				var url = "rcsw_txhd_xmxxsqgl.do?method=addXmxxsq&type="+type;
		      	ajaxSubFormWithFun("xmxxsqForm",url,function(data){
		    	 if(data["message"]=="保存成功！" || data["message"]=="提交成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }});
		  	}
			jQuery(function(){
			});
		</script>
	</head>
	<body>
		<html:form action="/rcsw_txhd_xmxxsqgl" method="post" styleId="xmxxsqForm">
			<% String xxdm = (String) request.getAttribute("xxdm"); %>
			<div style='width:100%;height:440px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist" >
					<thead>
						<tr>
							<th colspan="4">
								<span>项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							
							<th width="16%">
								<font color="red">*</font>项目名称
							</th>
							<td width="34%">
								<html:text property="xmmc" styleId="xmmc" maxlength="20" styleClass="text_nor"></html:text>
							</td>
							<th width="18%">
								<font color="red">*</font>活动时间
							</th>
							<td width="32%" >
								<html:text property="hdkssj" styleId="hdkssj"  style="width: 80px;" styleClass="text_nor" onclick="return showCalendar('hdkssj','yyyy-MM-dd',true,'hdjssj');" readonly="true" ></html:text>&nbsp;至
								<html:text property="hdjssj" styleId="hdjssj"  style="width: 80px;" styleClass="text_nor" onclick="return showCalendar('hdjssj','yyyy-MM-dd',false,'hdkssj');" readonly="true" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>活动类别
							</th>
							<td width="34%">
								<html:select property="lbdm" styleId="lbdm" style="width:155px">
								<html:options collection="lbList" property="lbdm"
									labelProperty="lbmc" />
								</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>活动地点
							</th>
							<td width="34%" >
								<html:text property="hddd"  styleId="hddd"  maxlength="20" styleClass="text_nor"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								申请人数上限
							</th>
							<td width="34%" >
								<html:text property="sqrssx"  styleId="sqrssx"  maxlength="4" onkeyup="checkInputNum(this);" styleClass="text_nor"></html:text>&nbsp;人
							</td>
							<th width="16%">
								审核人数上限
							</th>
							<td width="34%" >
								<html:text property="shrssx"  styleId="shrssx"  maxlength="4" onkeyup="checkInputNum(this);" styleClass="text_nor"></html:text>&nbsp;人
							</td>
							
						</tr>
						<tr>
					   <th align="right">
							负责人姓名
						</th>
						<td width="34%">
							<html:text  property="fzrxm" styleId="fzrxm"  maxlength="10" style="" styleClass="text_nor"></html:text>
						</td>
						<th align="right" width="10%">
						           联系电话
						</th>
						<td align="left">
							<html:text  property="lxdh" styleId="lxdh"  onkeyup="checkInputLxfx(this);" maxlength="20" style="" styleClass="text_nor"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
						           承办单位
						</th>
						<td align="left"  >
							<html:text  property="cbdw" styleId="cbdw"  style="width:97%" maxlength="50" styleClass="text_nor"></html:text>
						</td>
						<th align="right" width="10%">
							活动规格
						</th>
						<td align="left">
							<html:select property="hdggdm" style="width:158px;" styleId="hdggdm">
								<html:options property="dm" labelProperty="mc" collection="hdgglist"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">活动主题</th>
						<td align="left" colspan="3" >
							<html:text  property="hdzt" styleId="hdzt"  style="width:97%" maxlength="100" styleClass="text_nor"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							活动目的及意义
							<br /><font color="red">(限200字)</font>
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="hdmdyy" styleId="hdmdyy" style="width:97%" onblur="checkLen(this,200);"></html:textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							活动方案
							<br /><font color="red">(限500字)</font>
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="hdfa" styleId="hdfa" style="width:97%" onblur="checkLen(this,500);"></html:textarea>
						</td>
					</tr>
					<% if("13023".equals(xxdm)){ %>
					<tr>
						<th width="16%">
							授予学分
						</th>
						<td width="34%" >
							<html:text property="syxf"  styleId="syxf"  maxlength="5" onkeyup="checkInputNum(this);"></html:text>
						</td>
						<th width="16%">
						</th>
						<td width="34%" >
						</td>
					</tr>
					<% } %>
					<tr>
						<th width="16%">
							活动说明
							<br /><font color="red">(限1000字)</font>
						</th>
						<td width="34%" colspan="3">
							<html:textarea property="hdsm" styleId="hdsm"  style="width:97%;height: 100px;" onblur="checkLen(this,1000);"></html:textarea>
						</td>
					</tr>
				</table>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm('save');">
										保存草稿
									</button>
									
									<button type="button" type="button" onclick="saveForm('submit');">
										提交申请
									</button>
									
									<button type="button" type="button" onclick="iFClose();">
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

