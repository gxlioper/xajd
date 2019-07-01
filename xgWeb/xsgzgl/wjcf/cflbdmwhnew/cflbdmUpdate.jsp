<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%><%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/wjcf/cflbdmwhnew/js/cflbdm.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			sscsh();
		});

		
		function sscsh(){
			var sfkss=jQuery("#sfkss").val();
			var sfksqjc=jQuery("#sfksqjc").val();
			if(sfkss!=""){
				if(sfkss.indexOf("no") > -1){
					jQuery("#bkss").attr("checked","checked");
				}
				if(sfkss.indexOf("xs") > -1){
					jQuery("#xskss").attr("checked","checked");
					jQuery("#ssgzr").css("display","");
				}
				if(sfkss.indexOf("js") > -1){
					jQuery("#jskss").attr("checked","checked");
					jQuery("#ssgzr").css("display","");
				}
			}
			if(sfksqjc!=""){
				if(sfksqjc.indexOf("no") > -1){
					jQuery("#bksqjc").attr("checked","checked");
				}
				if(sfksqjc.indexOf("xs") > -1){
					jQuery("#xsksqjc").attr("checked","checked");
					jQuery("#jcgzr").css("display","");
				}
				if(sfksqjc.indexOf("js") > -1){
					jQuery("#jsksqjc").attr("checked","checked");
					jQuery("#jcgzr").css("display","");
				}
			}
			
			var sfszcfqx = jQuery("#sfszcfqx").val();
			var qxnsfkzz = jQuery("#qxnsfkzz").val();
			var cfqx = jQuery("#cfqx").val();
			if(sfszcfqx!=""){
				if(sfszcfqx=="1"){
					jQuery("tr[name=showCfqx]").css("display", "");
					jQuery("input[type=radio][name=cfqxsz][value=1]").attr("checked","checked");
					jQuery("input[type=radio][name=cfqxsz][value=2]").removeAttr("checked");
				}else if(sfszcfqx=="0"){
					jQuery("tr[name=showCfqx]").css("display", "none");
					jQuery("input[type=radio][name=cfqxsz][value=2]").attr("checked","checked");
					jQuery("input[type=radio][name=cfqxsz][value=1]").removeAttr("checked");
				}
			}
			
			if(qxnsfkzz!=""){
				if(qxnsfkzz=="1"){
					jQuery("input[type=radio][name=sfkzz][value=1]").attr("checked","checked");
					jQuery("input[type=radio][name=sfkzz][value=2]").removeAttr("checked");
				}else if(qxnsfkzz=="0"){
					jQuery("input[type=radio][name=sfkzz][value=2]").attr("checked","checked");
					jQuery("input[type=radio][name=sfkzz][value=1]").removeAttr("checked");
				}
			}
			if(cfqx!=""){
				var year = cfqx.split("-")[0];
				var month = cfqx.split("-")[1];
				var day = cfqx.split("-")[2];
				jQuery("input[name=cfqxV][id=year]").val(year);
				jQuery("input[name=cfqxV][id=month]").val(month);
				jQuery("input[name=cfqxV][id=day]").val(day);
			}
			
		}
		
		</script>
	</head>
	<body>
		<html:form action="/wjcf_cflbdmwh" styleId="cflbdmwhForm" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="cflbdm" styleId="cflbdm"/>
			<input type="hidden" id="wjcf_text" value="<bean:message key="wjcf.text" />"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>处分代码信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:30%">
								<font color="red">*</font>
								处分类别名称
							</th>
							<td style="width:70%">
								<html:text property="cflbmc" styleId="cflbmc" style="width:235px" maxlength="100"/>
							</td>
						</tr>
						<tr>
							<th style="width:30%" >
								设置处分期限
							</th>
							<td style="width:70%" >
								<html:hidden property="sfszcfqx" styleId="sfszcfqx"/>
								<input type="radio"  name="cfqxsz" value="1" onclick="sfFlag('cfqxsz');" />是
								<input type="radio"  name="cfqxsz" value="2" onclick="sfFlag('cfqxsz');" checked="checked" />否
							</td>
						</tr>
						<tr name="showCfqx" style="display: none">
							<th style="width:30%">
								<font color="red">*</font>处分期限
							</th>
							<td style="width:70%">
								<html:hidden property="cfqx" styleId="cfqx"/>
								<input type="text" name="cfqxV" id="year" onkeyup="onyInt(this);" value="1" maxlength="2" style="width:50px;"/>年
								<input type="text" name="cfqxV" id="month" onkeyup="onyInt(this);" value="0" maxlength="2" style="width:50px;"/>月
								<input type="text" name="cfqxV" id="day" onkeyup="onyInt(this);" value="0" maxlength="2" style="width:50px;"/>日
							</td>
						</tr>
						<tr name="showCfqx" style="display: none">
							<th style="width:30%">
								期限内是否可终止
							</th>
							<td style="width:70%" >
								<html:hidden property="qxnsfkzz" styleId="qxnsfkzz"/>
								<input type="radio"  name="sfkzz" value="1" onclick="sfFlag('sfkzz');" checked="checked">是
								<input type="radio"  name="sfkzz" value="2" onclick="sfFlag('sfkzz');">否
							</td>
						</tr>
						<tr>
							<th style="width:30%">
								<font color="red">*</font>
								审核流
							</th>
							<td  style="width:70%">
									
								<html:select property="spl" styleId="spl" style="width:240px">
									<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th style="width:30%">处分发文权限控制</th>
							<td style="width:70%">
								<html:select property="cffwqx" styleId="cffwqx" style="width:240px">
									<html:options collection="spjbList" property="spgwdm" labelProperty="spgwmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th style="width:30%">
							<font color="red">*</font>
								是否可申诉
							</th>
							<td  style="width:70%">
								<input type="checkbox"  id="bkss" value="no" onclick="sfkyss('sfkss',this);"/>不可申诉
								<input type="checkbox"  id="xskss" value="xs" onclick="sfkyss('sfkss',this);"/>学生可申诉
								<input type="checkbox"  id="jskss" value="js" onclick="sfkyss('sfkss',this);"/>教师可申诉
								<html:hidden property="sfkss" styleId="sfkss"/>
 							</td>
						</tr>
						
						<tr id="ssgzr" style="display: none">
							<logic:equal name="xxdm" value="14073">
								<th style="width:30%">
									申诉受理时长
								</th>
							</logic:equal>
							<logic:notEqual name="xxdm" value="14073">
								<th style="width:30%">
									申诉受理截止日
								</th>
							</logic:notEqual>
							<td  style="width:70%">
								<html:text property="ssslgzr" styleId="ssslgzr" onkeyup="onyInt(this);" maxlength="3" style="width:100px;"/>天
							</td>
						</tr>
						<tr>
							<th style="width:30%">
							<font color="red">*</font>
								是否可申请<bean:message key="wjcf.text" />
							</th>
							<td  style="width:70%">
								<input type="checkbox"  id="bksqjc" value="no" onclick="sfkyss('sfksqjc',this);"/>不可<logic:notEqual name="xxdm" value="14073"><bean:message key="wjcf.text" /></logic:notEqual><logic:equal name="xxdm" value="14073">申请</logic:equal>
								<input type="checkbox"  id="xsksqjc" value="xs" onclick="sfkyss('sfksqjc',this);"/>学生可<logic:notEqual name="xxdm" value="14073"><bean:message key="wjcf.text" /></logic:notEqual><logic:equal name="xxdm" value="14073">申请</logic:equal>
								<input type="checkbox"  id="jsksqjc" value="js" onclick="sfkyss('sfksqjc',this);"/>教师可<logic:notEqual name="xxdm" value="14073"><bean:message key="wjcf.text" /></logic:notEqual><logic:equal name="xxdm" value="14073">申请</logic:equal>
								<html:hidden property="sfksqjc" styleId="sfksqjc"/>
							</td>
						</tr>
						<tr id="jcgzr" style="display: none">
							<logic:equal name="xxdm" value="14073">
								<th style="width:30%">
									<bean:message key="wjcf.text" />受理时长
								</th>
							</logic:equal>
							<logic:notEqual name="xxdm" value="14073">
								<th style="width:30%">
									<bean:message key="wjcf.text" />受理起始日
								</th>
							</logic:notEqual>
							<td  style="width:70%">
								<html:text property="jsslqsr" styleId="jsslqsr" onkeyup="onyInt(this);" style="width:100px;"/>天
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
									"<font color="red">*</font>"为必填项
								</div>
								<div class="btn">
									<button class="button2" id="btn_bc" type="button" onclick="saveUpdate()">
										保 存
									</button>
									<button class="button2" type="button" onclick="Close()">
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
