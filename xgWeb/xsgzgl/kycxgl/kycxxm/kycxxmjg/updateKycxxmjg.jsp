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
		<script type="text/javascript" src="xsgzgl/kycxgl/kycxxm/js/kycxxm.js"></script>
		<script	type="text/javascript">
			function saveForm(type){
				var xn = jQuery("#xn").val();
				if (jQuery.trim(xn) == ""){
					showAlert("请选择学年！");
					return false;
				}
				var xmmc = jQuery("#xmmc").val();
				if (jQuery.trim(xmmc) == ""){
					showAlert("科研项目名称不能为空！");
					return false;
				}	
				var zdls = jQuery("#zdls").val();
				if (jQuery.trim(zdls) == ""){
					showAlert("请选择指导老师！");
					return false;
				}	
				var lbdm = jQuery("#lbdm").val();
				if (jQuery.trim(lbdm) == ""){
					showAlert("请选择科研类别！");
					return false;
				}	
				var xmsqrxm = jQuery("#xmsqrxm").val();
				if (jQuery.trim(xmsqrxm) == ""){
					showAlert("项目申请人不能为空！");
					return false;
				}	
				var xmsqsj = jQuery("#xmsqsj").val();
				if (jQuery.trim(xmsqsj) == ""){
					showAlert("申请时间不能为空！");
					return false;
				}	
				var xbjf = jQuery("#xbjf").val();
				if (jQuery.trim(xbjf) == ""){
					showAlert("下拨经费不能为空！");
					return false;
				}
				var url = "kycxgl_kycxxm_kycxxmjggl.do?method=updateKycxxmjg&type="+type;
		      	ajaxSubFormWithFun("kycxxmjgForm",url,function(data){
		    	 if(data["message"]=="保存成功！"){
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
				// =========== 审核流数据只允许修改学生 begin ==========
				if(jQuery("#sjly").val() == '1'){
					jQuery("select,textarea,input:not(input:hidden)").attr("disabled","disabled");
					jQuery("#zdlsxm_btn").attr("disabled","disabled");
					jQuery("input[type=checkbox]").attr("disabled",false);
				}
				// =========== 审核流数据只允许修改学生 begin ==========
			});
		</script>
	</head>
	<body>
		<html:form action="/kycxgl_kycxxm_kycxxmjggl" method="post" styleId="kycxxmjgForm">
			<html:hidden property="jgid" styleId="jgid" />
			<html:hidden property="sjly" styleId="sjly" />
			<logic:equal value="1" name="rs" property="sjly">
			<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					审核流程数据只允许修改<font color="red">科研项目成员</font>
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			</logic:equal>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>科研项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="15%"><font class="red">*</font>学年</th>
							<td width="35%">
								<html:select property="xn" styleId="xn" style="width:130px" value="${rs.xn}">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th width="15%"><span class="red">*</span>科研项目名称</th>
							<td width="35%">
								<html:text name="rs" property="xmmc" styleId="xmmc" maxlength="50"/>
							</td>
					    </tr>
					    <tr>
							<th><font class="red">*</font>指导老师</th>
							<td>
								<input type="text" readonly="true" id="zdlsxm" value="${rs.zdlsxm }"/>
								<button id="zdlsxm_btn" class="btn_01" type="button" onclick="showDialog('请选择指导老师',680,480,'szdw_fdyjtff.do?method=showFdysNotF5');return false;">选择</button>
								<input type="hidden" name="zdls" id="zdls" value="${rs.zdls }"/>
							</td>
							<th><font class="red">*</font>科研类别</th>
							<td>
								<html:select property="lbdm" styleId="lbdm" value="${rs.lbdm}">
									<html:options collection="lbList" property="lbdm" labelProperty="lbmc" />
								</html:select>
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>项目申请人</th>
							<td>
								<html:text name="rs" property="xmsqrxm" styleId="xmsqrxm" maxlength="10"/>
							</td>
							<th><span class="red">*</span>申请时间</th>
							<td>
								<html:text name="rs" property="xmsqsj" styleId="xmsqsj" onclick="return showCalendar('xmsqsj','yyyy-MM-dd');" readonly="true" ></html:text>
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>下拨经费（元）</th>
							<td colspan="3">
								<html:text name="rs" property='xbjf' styleId="xbjf" maxlength="20" onblur="checkMoneyForBlur(this)"
							onkeyup="checkMoneyForKeyup(this)"/>
							</td>
					    </tr>
					    <tr>
							<th>附件信息</th>
							<td colspan="3">
								<html:hidden property="fjxx" styleId="fjxx" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery.MultiUploader({
											maxcount : 3,
											//后缀
											accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
											//最大文件大小 单位M
											maxsize: 10,
											//存放附件的隐藏域的id
											elementid : 'fjxx'
											});
									});
								</script>
							</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>科研项目成员</span>
							</th>
						</tr>
					</thead>
				 </table>
				</div>
				<div>
					<table width="100%" border="0" class="datelist" style="margin:2px auto;">
						<thead>
							<tr>
								<td colspan="9">
								<button type="button" onclick="addKycxxmcy();return false;" class="btn_01">增加学生</button>
								<button type="button" onclick="delKycxxmcy();return false;" class="btn_01">删除学生</button>
								</td>
							</tr>
							<tr>
								<td width='5%'><input type="checkbox" name="selectAll" onclick="selectAllKycxxmcy(this);" /></td>
								<td width='11%'>学号</td>
								<td width='11%'>姓名</td>
								<td width='6%'>性别</td>
								<td width='6%'>年级</td>
								<td width='15%'><bean:message key="lable.xb" /></td>
								<td width='16%'>专业</td>
								<td width='20%'>班级</td>
								<td width='15%'>联系方式</td>
							</tr>
						</thead>
						<tbody id="tbody_kycxxm_xs">
							<logic:notEmpty name="kycxxmcyList">
								<logic:iterate id="kycxxmcy" name="kycxxmcyList">
									<tr>
										<td><input type='checkbox'/></td>
										<td><input type='hidden' name='xhArr' value='${kycxxmcy.xh }' />${kycxxmcy.xh }</td>
										<td>${kycxxmcy.xm }</td>
										<td>${kycxxmcy.xb }</td>
										<td>${kycxxmcy.nj }</td>
										<td>${kycxxmcy.xymc }</td>
										<td>${kycxxmcy.zymc }</td>
										<td>${kycxxmcy.bjmc }</td>
										<td>${kycxxmcy.lxfs }</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</table>
				</div>
				<div style="height: 40px"></div>
				<div>
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">"<span class="red">*</span>"为必填项</div>
									<div class="btn">
										<button type="button" type="button" onclick="saveForm('save');">
											保存
										</button>
										<button type="button" type="button" onclick="iFClose();">
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

