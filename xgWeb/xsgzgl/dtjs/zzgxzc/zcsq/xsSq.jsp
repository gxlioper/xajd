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
			jQuery(function(){
				jQuery("#shlccx").load(
						"comm_spl.do?method=lccx&sqid=${rs.sqid}&tt="
								+ new Date().getTime());
				if( jQuery("[name='sfkjhyzm']").length == 2&&jQuery("[name='sfkjhyzm']:checked").length == 0){
					jQuery("#no").attr("checked","checked");
				}
			});
			//撤销
			function cancelZdy() {
				var shzt = jQuery("#shzt").val();
				if (shzt != '5') {
					showAlertDivLayer("只有审核中的记录才能被撤销！");
					return false;
				}

				showConfirmDivLayer("您确定要撤销选择的记录吗？", {
					"okFun" : function() {
						jQuery.post("dzzgxsq.do?method=cancelSq", {
							values : jQuery("#sqid").val(),
							splcid :  jQuery("#splcid").val()
						}, function(data) {
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
									document.location.href = "dtjs_dzzgxsq_xs.do";
								}});
						}, 'json');
					}
				});

			}
			/**
			 * 保存申请
			 * @param type
			 * @return
			 */
			function saveSq(type){
				var ids = "xh"+"-"+"szdzb"+"-"+"sfsn"+"-"+"jsdzz"+"-"+"sqdw"+"-"+"dfjzrq";
				if(checkNotNull(ids) == false){
					showAlertDivLayer("请将带<font color='red'>*</font>的项目填写完整");
					return false;
				}
				if(jQuery("[name='sfkjhyzm']:checked").length == 0){
					showAlertDivLayer("请将带<font color='red'>*</font>的项目填写完整");
					return false;
				}
				var url = "dzzgxsq.do?method=save&type=" + type;
				ajaxSubFormWithFun("ZcsqForm", url, function(data) {
					 if(data["message"]=="保存成功！" || data["message"]=="提交成功！"){
						 showAlertDivLayer(data["message"],{},{"clkFun":function(){
								document.location.href = "dtjs_dzzgxsq_xs.do";
							}});
			    	 }else{
			    		 showAlertDivLayer(data["message"]);
			    		}
					});
			}
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/dzzgxsq" method="post" styleId="ZcsqForm">
			<html:hidden property="sqid" styleId="sqid"  value="${rs.sqid}" />
			<html:hidden property="xh" styleId="xh"  value="${xh}" />
			<html:hidden property="shzt" styleId="shzt"  value="${rs.shzt}" />
			<html:hidden property="splcid" styleId="splcid" value="${rs.splcid}" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>登记信息</span>&nbsp;
							</th>
						</tr>
					</thead>
					<logic:equal value="1" name="sqkg">
					<logic:notEqual value="1" name="rs" property="shzt" >
					<logic:notEqual value="2" name="rs" property="shzt" >
					<logic:notEqual value="5" name="rs" property="shzt" >
						<tbody>
							<tr>
								<th><font color="red">*</font>所在党支部</th>
								<td>
									<html:select name="rs" property="szdzb" styleId="szdzb" style="width:90%">
										<html:options collection="dzbList" property="dzbdm" labelProperty="dzbmc"/>
									</html:select>							
								</td>
								<th><font color="red">*</font>是否省内</th>
								<td>
									 <html:select name="rs" property="sfsn" styleId="sfsn" style="width:90%">
										<html:option value="省内">省内</html:option>
										<html:option value="省外">省外</html:option>
									 </html:select>
								</td>
							</tr>
							<tr>
								<th><font color="red">*</font>接收本人组织关系的党组织</th>
								<td colspan="3">
									<html:text name="rs" property="jsdzz" styleId="jsdzz" style="width:90%" maxlength="50"/>
								</td>
							</tr>
							<tr>
								<th><font color="red">*</font>本人组织关系所去单位</th>
								<td colspan="3">
									<html:text name="rs" property="sqdw" styleId="sqdw"  maxlength="50" style="width:90%"/>
								</td>
							</tr>
							<tr>
								<th><font color="red">*</font>党费交至日期</th>
								<td >
									<html:text name="rs" property="dfjzrq" styleId="dfjzrq" onclick="return showCalendar('dfjzrq','y-mm-dd',true);" style="width:90%"  />
								</td>
								<th><font color="red">*</font>是否需要开具婚姻证明</th>
								<td >
									<html:radio name="rs" property="sfkjhyzm" value="是" styleId="yes"/><label for="yes">是</label>
									<html:radio name="rs" property="sfkjhyzm" value="否" styleId="no"/><label for="no">否</label>
								</td>
							</tr>
						</tbody>
					</logic:notEqual>
					</logic:notEqual>
					</logic:notEqual>
					<logic:notEmpty name="rs" property="shzt">
					<logic:notEqual value="0" name="rs" property="shzt" >
					<logic:notEqual value="3" name="rs" property="shzt" >
					<tbody>
						<tr>
							<th>所在党支部</th>
							<td>
								<bean:write name="rs"  property="dzbmc"/>					
							</td>
							<th>是否省内</th>
							<td>
								<bean:write name="rs"  property="sfsn"/>
							</td>
						</tr>
						<tr>
							<th>接收本人组织关系的党组织</th>
							<td colspan="3">
								<bean:write name="rs"  property="jsdzz"/>
							</td>
						</tr>
						<tr>
							<th>本人组织关系所去单位</th>
							<td colspan="3">
								<bean:write name="rs"  property="sqdw"/>
							</td>
						</tr>
						<tr>
							<th>党费交至日期</th>
							<td >
								<bean:write name="rs"  property="dfjzrq"/>
							</td>
							<th>是否需要开具婚姻证明</th>
							<td >
								<bean:write name="rs"  property="sfkjhyzm"/>
							</td>
						</tr>
					</tbody>
					</logic:notEqual>
					</logic:notEqual>
					</logic:notEmpty>
					</logic:equal>
					
					<logic:equal value="0" name="sqkg">
					<tbody>
						<tr>
							<th>所在党支部</th>
							<td>
								<bean:write name="rs"  property="dzbmc"/>					
							</td>
							<th>是否省内</th>
							<td>
								<bean:write name="rs"  property="sfsn"/>
							</td>
						</tr>
						<tr>
							<th>接收本人组织关系的党组织</th>
							<td colspan="3">
								<bean:write name="rs"  property="jsdzz"/>
							</td>
						</tr>
						<tr>
							<th>本人组织关系所去单位</th>
							<td colspan="3">
								<bean:write name="rs"  property="sqdw"/>
							</td>
						</tr>
						<tr>
							<th>党费交至日期</th>
							<td >
								<bean:write name="rs"  property="dfjzrq"/>
							</td>
							<th>是否需要开具婚姻证明</th>
							<td >
								<bean:write name="rs"  property="sfkjhyzm"/>
							</td>
						</tr>
					</tbody>
					</logic:equal>
					<thead>
							<tr>
								<th colspan="4">
									<span>审核信息</span>
								</th>
					       </tr>
				     </thead>
					<tbody>
						<tr>
								<td colspan="4" id="shlccx">

								</td>
						</tr>

					</tbody>
				</table>
				</div>	
			<div>
				<table width="100%" border="0" class="formlist" >
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
								<logic:equal value="1" name="sqkg">
									<logic:notEqual value="1" name="rs" property="shzt" >
					                <logic:notEqual value="2" name="rs" property="shzt" >
					                <logic:notEqual value="5" name="rs" property="shzt" >
									<button type="button" onclick="saveSq('${saveType}');">
										保存草稿
									</button>
									<button type="button" onclick="saveSq('${submitType}');">
										提交申请
									</button>
									</logic:notEqual>
									</logic:notEqual>
									</logic:notEqual>
								</logic:equal>
								<logic:equal value="0" name="sqkg" >
									<logic:equal value="3" name="rs" property="shzt" >
									<button type="button" onclick="saveSq('${submitType}');">
										提交申请
									</button>
									</logic:equal>
								</logic:equal>
								<logic:equal value="1" name="sqkg">
									<logic:equal value="5" name="rs" property="shzt" >
									<button type="button" onclick="cancelZdy();">
										撤销申请
									</button>
									</logic:equal>
								</logic:equal>	
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
	
</html>