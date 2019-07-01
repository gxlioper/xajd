<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>	
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
	
		
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/js/check.js'></script>
		<script type="text/javascript">

		//保存 
		function save(){
			var xh = $("xh").value;
			var cfyymc = $("cfyydm").value;
			var cflbdm = $("cflbdm").value;
			var cfid = $("cfid").value;
			var cfwh = $("cfwh").value;
			var cfsj = $("cfsj").value;
			var wjsj = $("wjsj").value;
			var fjmc = $("fj").value;
			if (fjmc != null && fjmc != "") {
				var hz = fjmc.substr(fjmc.lastIndexOf(".")+1,fjmc.length);
				if (hz!='doc'&&hz!='rar'&&hz!='pdf'&&hz!='bmp'&&hz!='jpg'&&hz!='gif'&&hz!='png'){
					alertError("上传文件类型只能为：doc,rar,pdf,bmp,jpg,gif,png");
					return false;
				}
			}
			jQuery("#fjmc").val($("fj").value);
			if(""==xh||""==cfyymc||""==cflbdm||""==wjsj||cfwh==""||cfsj==""){
				alertError("请将带*的项目填写完整！");
				return false;
			}
			
			// 验证处分在结果库当中是否存在 （验证条件：学号、处分类别、处分时间
			jQuery.post("wjcf_cfsbgl.do?method=checkExistCfjg", {
				xh:xh,
				cflbdm:cflbdm,
				wjsj:wjsj,
				cfid:cfid
			}, function(data) {
				if(data ==null || data){
					showAlert("该学生在"+wjsj+"的违纪已在处分结果中存在！");
					return false;
				}else{
				     var url = "saveWjcfsjXg.do";
				      ajaxSubFormWithFun("cfsjwhForm",url,function(data){
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
			},"json");
		}
		
		function showCfqxFlag(cflbdm){
			if(cflbdm==""){return false;}
			var cfqx = "";
			jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
				jQuery("#showCfqx").html(data["message"]);
			},'json');
		}
		function updateInit(){
			var cflbdm = jQuery("#cflbdm").val();
			showCfqxFlag(cflbdm);
		}
		</script>
		
	</head>
	<body onload="updateInit();">
		<html:form action="/wjcfCfshwh_cfsjwh" method="post" enctype='multipart/form-data' styleId="cfsjwhForm">
					<input type="hidden" name="cfid" id="cfid" value="${rs.cfid }"/>
					<input type="hidden" name="xh" id="xh" value="${rs.xh }"/>
					<input type="hidden" name="message" id="message" value="${message }">	
								<input type="hidden" id="text" value="<bean:message key="wjcf.text" />"/>
					<html:hidden property="fjmc" styleId="fjmc"/>		
				<div  style="width:100%;height:460px;overflow-x:hidden;overflow-y:auto;">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>学生信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>学号
						</th>
						<td align="left" width="30%">
							${rs.xh }
						</td>
						<th align="right" width="20%">
							姓名
						</th>
						<td align="left">
							${rs.xm}
						</td>
					</tr>
					<tr>
						<th align="right">
							性别
						</th>
							<td align="left">
							${rs.xb}
						</td>
						<th align="right">
							年级
						</th>
							<td align="left">
							${rs.nj}
						</td>
					</tr>
					<tr>
						<th align="right">
							<bean:message key="lable.xb" />
						</th>
							<td align="left">
							${rs.xymc}
						</td>
						<th align="right">
							专业
						</th>
							<td align="left">
							${rs.zymc}
						</td>
					</tr>
					<tr>
						<th align="right">
							班级
						</th>
						<td align="left">
							${rs.bjmc}
						</td>
						<th align="right">
							政治面貌
						</th>
							<td align="left">
							${rs.zzmmmc}
						</td>
						
					</tr>
					
					</tbody>
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>处分上报信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>处分学年
						</th>
						<td align="left" width="30%">
							<html:select property="xn" styleId="xn" style="width:140px" value="${rs.xn }">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
							</html:select>
						</td>
						<th align="right" width="20%">
							<font color="red">*</font>处分学期
						</th>
						<td align="left" width="30%">
							<html:select property="xq" styleId="xq" style="width:140px" value="${rs.xq }">
								<html:option value=""></html:option>
								<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right">
							<font color="red">*</font>处分原因
						</th>
						<td align="left">
							<html:select property="cfyydm" styleId="cfyydm" style="width:140px" value="${rs.cfyymc }">
								<html:option value=""></html:option>
								<html:options collection="cfyyList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
						<th align="right">
							<font color="red">*</font>处分类别
						</th>
						<td align="left">
							<html:select property="cflbdm" styleId="cflbdm" style="width:140px" value="${rs.cflbmc }" onchange="showCfqxFlag(this.value);">
								<html:option value=""></html:option>
								<html:options collection="cflbList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
					</tr>
					&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqx" style="color: red"></span>
					<tr>
						<th align="right">
							<font color="red">*</font>违纪时间
						</th>
						<td align="left" colspan="3">
							<html:text property="wjsj" styleId="wjsj"
								style="cursor:hand;"
								onclick="return showCalendar('wjsj','y-mm-dd');" value="${rs.wjsj }"/>
						</td>
					</tr>
					<tr>
						<th align="right" >
							处理决定书面材料或附件
							<br/>(限doc,rar,图片格式)
						</th>
						<td align="left" colspan="3">
							<html:file  property='fj' styleId ="fj" style="width:99%" />
						</td>
					</tr>
					<tr>
						<th align="right">
							违纪事实经过
						</th>
						<td align="left" colspan="3" >
								<html:textarea  property='wjssjg' style="width:600px"
								rows='4'  onblur="checkLen(this,1000)" value="${rs.wjssjg}"></html:textarea>
						</td>
					</tr>
					
					<tr>
						<th align="right">
							<font color="red">*</font>处分文号
						</th>
						<td align="left">
							<html:text property="cfwh" styleId="cfwh" maxlength="30" value="${rs.cfwh }"></html:text>
						</td>
						<th align="right">
							<font color="red">*</font>处分时间
						</th>
						<td align="left">
							<html:text property="cfsj" styleId="cfsj"
								style="cursor:hand;"
								onclick="return showCalendar('cfsj','y-mm-dd');" value="${rs.cfsj }"/>
						</td>
					</tr>
					
						<tr>
						<th align="right">
							备注
						</th>
						<td align="left" colspan="3" >
								<html:textarea  property='bz' style="width:600px"
								rows='4' onblur="checkLen(this,1000)" value="${rs.bz}"></html:textarea>
						</td>
					</tr>
					</tbody>
					
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>已受处分情况</span>
								</th>
							</tr>
						</thead>
						
						<tr>
						<td colspan="4">
							<table class="formList" width="100%">
								<thead align="left">
									<tr align="left">
										<td ><b>学年</b></td>
										<td ><b>学期</b></td>
										<td><b>处分类别</b></td>
										<td ><b>处分原因</b></td>
										<td ><b>处分时间</b></td>
										<td ><b>处分文号</b></td>
									</tr>
								</thead>
								<tbody align="left">
							<logic:notEmpty name="yscfqkList">
							<logic:iterate name="yscfqkList" id="s">
										<tr  style="cursor:hand">
										<td >
												${s.xn}
											</td>
											<td >
												${s.xqmc}
											</td>
											<td >
												${s.cflbmc}
											</td>
											<td >
												${s.cfyymc}
											</td>
											<td>
												${s.cfsj}
											</td>
											<td>
												${s.cfwh}
											</td>
										</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="yscfqkList">
									<tr style="height:22px">
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
										</tr>
							</logic:empty>
							</tbody>
							</table>
							</td>
				</tr>
						
						<tbody>
					
					</tbody>
				</table>
			</div>
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button"  onclick="save();return false;" id="buttonSave">
									保 存
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button"  onclick="Close();return false;" id="buttonClose">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>
