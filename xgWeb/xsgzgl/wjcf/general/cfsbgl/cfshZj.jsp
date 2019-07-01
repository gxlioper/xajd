<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>	
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type='text/javascript' src='/xgxt/js/check.js'></script>
		<script type="text/javascript">

		//通过
		function tongguo(){
			confirmInfo("确定通过吗？",function(ta){
				if(ta=="ok"){
					var cfId = $("cfId").value;
					var sfZgj = $("sfZgj").value; 
					var xh = $("xh").value;
					var spgwId = $("spgwId").value;
					var cflbdm = $("cflbdm").value;
					var xcsZt = $("xcsZt").value;
					var shyj = $("shyj").value;
					if(sfZgj=="true"){
						var cfwh = $("cfwh").value;
						var cfsj = $("cfsj").value; 
						if(cfwh==""||cfsj==""){
							showAlertDivLayer("请将带*的项目填写完整！");
							return false;
						}else{
							var url = "general_wjcf_cfsh_ajax.do?method=saveCfsh&doType=tg";
							//参数
						 	var parameter = {
						 		"cfId":cfId,
						 		"xh":xh,
						 		"spgwId":spgwId,
						 		"cflbdm":cflbdm,
						 		"xcsZt":xcsZt,
						 		"cfwh":escape(cfwh),
						 		"cfsj":cfsj,
						 		"shyj":escape(shyj)
							};
							jQuery.post(url,
								parameter,
								function(result){
									 showAlert("操作成功",{},{"clkFun":function(){
						    				if (parent.window){
						    					refershParent();
						    				}
						    			}});
							});
							}
					}else{
						var url = "general_wjcf_cfsh_ajax.do?method=saveCfsh&doType=tg";
						//参数
					 	var parameter = {
					 		"cfId":cfId,
					 		"xh":xh,
					 		"spgwId":spgwId,
					 		"cflbdm":cflbdm,
					 		"xcsZt":xcsZt,
					 		"shyj":escape(shyj)
						};
						jQuery.post(url,
								parameter,
								function(result){
							 	showAlert("操作成功",{},{"clkFun":function(){
				    				if (parent.window){
				    					refershParent();
				    				}
				    			}});
						});
						}
					}
			});
		}

		//通过并提交
		function tongguoBtj(){
			var cfId = $("cfId").value;
			var sfZgj = $("sfZgj").value; 
			var xh = $("xh").value;
			var spgwId = $("spgwId").value;
			var cflbdm = $("cflbdm").value;
			var xcsZt = $("xcsZt").value;
			var shyj = $("shyj").value;
			
				var cfwh = $("cfwh").value;
				var cfsj = $("cfsj").value; 
				if(cfwh==""||cfsj==""){
					showAlertDivLayer("请将带*的项目填写完整！");
					return false;
				}else{
					var url = "general_wjcf_cfsh_ajax.do?method=saveCfsh&doType=tg";
					//参数
				 	var parameter = {
				 		"cfId":cfId,
				 		"xh":xh,
				 		"spgwId":spgwId,
				 		"cflbdm":cflbdm,
				 		"xcsZt":xcsZt,
				 		"cfwh":escape(cfwh),
				 		"cfsj":cfsj,
				 		"shyj":escape(shyj)
					};
					jQuery.post(url,
						parameter,
						function(result){
							var url = "general_wjcf_cfsh_ajax.do?method=zjtjSh";
									//参数
								var parameter = {
								 	"cfId":cfId
								};
								jQuery.post(url,
										parameter,
										function(result){
									 	showAlert("操作成功",{},{"clkFun":function(){
						    				if (parent.window){
						    					refershParent();
						    				}
						    			}});
								});
					});
					}
		}
		
		//不通过
		function butongguo(){
			confirmInfo("确定不通过吗？",function(tag){
				if(tag=="ok"){
					var cfId = $("cfId").value;
					var xh = $("xh").value;
					var spgwId = $("spgwId").value;
					var cflbdm = $("cflbdm").value;
					var xcsZt = $("xcsZt").value;
					var shyj = $("shyj").value;
					var url = "general_wjcf_cfsh_ajax.do?method=saveCfsh&doType=btg";
					//参数
				 	var parameter = {
				 		"cfId":cfId,
				 		"xh":xh,
				 		"spgwId":spgwId,
				 		"cflbdm":cflbdm,
				 		"xcsZt":xcsZt,
				 		"shyj":escape(shyj)
					};
					jQuery.post(url,
							parameter,
							function(result){
							showAlert("操作成功",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
						});
				}
			});
		}

		//退回
		function tuihui(){
			confirmInfo("确定退回吗？",function(tag){
				if(tag=="ok"){
					var cfId = $("cfId").value;
					var xh = $("xh").value;
					var spgwId = $("spgwId").value;
					var cflbdm = $("cflbdm").value;
					var xcsZt = $("xcsZt").value;
					var shyj = $("shyj").value;
					var url = "general_wjcf_cfsh_ajax.do?method=saveCfsh&doType=th";
					//参数
				 	var parameter = {
				 		"cfId":cfId,
				 		"xh":xh,
				 		"spgwId":spgwId,
				 		"cflbdm":cflbdm,
				 		"xcsZt":xcsZt,
				 		"shyj":escape(shyj)
					};
					jQuery.post(url,
							parameter,
							function(result){
							showAlert("操作成功",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
						});
				}
			});
		}

		//下载
		function fjxz(){
			var url="general_wjcf_cfsb_ajax.do?method=fjxz";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		</script>
	</head>
	<body >
		<html:form action="/general_wjcf_cfsb_ajax" method="post">
					<input type="hidden" name="url" id="url" value="general_wjcf_cfsb_ajax.do?method=zjWjcf">	
					<input type="hidden" name="tableName" id="tableName" value="view_xsjbxx">	
					<input type="hidden" name="xh" id="xh" value="${rs.xh}"/>
					<input type="hidden" name="cfId" id="cfId" value="${wjsb.cfid}"/>
					<input type="hidden" name="fjmc" id="fjmc" value="${wjsb.fjmc}"/>
					<input type="hidden" name="message" id="message" value="${message }">	
					<input type="hidden" name="doType" id="doType"  >	
					<input type="hidden" name="sfZgj" id="sfZgj"  value="${sfZgj }">	
					<input type="hidden" name="spgwId" id="spgwId"  value="${spgw}">	
					<input type="hidden" name="cflbdm" id="cflbdm"  value="${cflbdm}">	
					<input type="hidden" name="xcsZt" id="xcsZt"  value="${cfsh.shzt}">	
					<input type="hidden" name="tgCz" id="tgCz"  value="${tgCz}">	
				
				<div  style="width:100%;height:810px;overflow-x:hidden;overflow-y:auto;">
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
						<td align="right" width="18%">
							学号：
						</td>
						<td align="left" width="28%">
							${rs.xh}
						</td>
						<td align="right" width="20%">
							姓名：
						</td>
						<td align="left" width="28%">
							${rs.xm}
						</td>
					</tr>
					<tr>
						<td align="right">
							性别：
						</td>
							<td align="left">
							${rs.xb}
						</td>
						<td align="right">
							年级：
						</td>
							<td align="left">
							${rs.nj}
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xb" />：
						</td>
							<td align="left">
							${rs.xymc}
						</td>
						<td align="right">
							专业：
						</td>
							<td align="left">
							${rs.zymc}
						</td>
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							${rs.bjmc}
						</td>
						<td align="right">
							政治面貌：
						</td>
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
						<td align="right" width="20%">
							处分学年：
						</td>
						<td align="left" width="30%">
							${wjsb.xn}
						</td>
						<td align="right" width="20%">
							处分学期：
						</td>
						<td align="left" width="30%">
							${wjsb.xqmc}
						</td>
					</tr>
					<tr>
						<td align="right">
							处分原因：
						</td>
						<td align="left">
							${wjsb.cfyymc}
							
						</td>
						<td align="right">
							处分类别：
						</td>
						<td align="left">
							${wjsb.cflbmc}
						</td>
					</tr>
					<tr>
						<td align="right">
							违纪时间：
						</td>
						<td align="left">
							${wjsb.wjsj}
						</td>
						<td align="right">
							处理决定书面材料或附件：
						</td>
						<td align="left">
							<logic:notEmpty name="wjsb" property="fjmc">
								<a  href="#" onclick="fjxz();return false;" class="name">下载附件</a>
							</logic:notEmpty>
						</td>
					</tr>
						<tr>
						<td align="right">
							违纪事实经过：
						</td>
							<td align="left" colspan="3" style="word-break:break-all;width:100%" >
								${wjsb.wjssjg}
						</td>
					</tr>
						<tr>
						<td align="right">
							备注：
						</td>
							<td align="left" colspan="3" style="word-break:break-all;width:100%" >
								${wjsb.bz}
						</td>
						</td>
					</tr>
					<thead>
							<tr>
								<th colspan="4">
									<span>审核信息</span>
								</th>
							</tr>
						</thead>
						<logic:equal name ="sfZgj" value="true">
							<tr>
								<td align="right">
									<font color="red">*</font>处分文号：
								</td>
								<td align="left"  >
									<html:text property='cfwh' styleId="cfwh" maxlength="30" value="${cfsh.cfwh}"/>
								</td>
								<td align="right">
									<font color="red">*</font>处分时间：
								</td>
								<td align="left">
								<html:text property="cfsj" styleId="cfsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('cfsj','y-mm-dd');" value="${cfsh.cfsj}"/>
							</td>
							</tr>
						</logic:equal>
						<tr>
							<td align="right" width="15%">
							审核意见：<br/>
							<font color="red"><B>(限1000字)</B></font>
						</td>
						<td align="left" colspan="3" >									
								<textarea  id="shyj" name="shyj"  value="${cfsh.shyj}" rows="5" onblur="checkLen(this,1000)" style="width:600px"></textarea>
								
						</td>
					</tr>
					</tbody>
					
					<table width="100%" border="0" class="formlist">
							<thead>
							<tr>
								<th colspan="4">
									<span>处分审核情况</span>
								</th>
							</tr>
						</thead>
						<tbody>
						<tr>
						<td colspan="5">
							<table class="formList" width="100%">
								
								<tbody align="center">
							<logic:notEmpty name="cfshList">
							<logic:iterate name="cfshList" id="s">
										<tr >
												<th>
												岗位名称
												</th>
												<td align="left">
												${s.gwmc}
												</td>
												<th>
												审核状态
												</th>
												<td align="left">
												
												<logic:equal name="s" property="shzt" value="tg">
												<p><img src="<%=stylePath%>images/ico_shtg.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="s" property="shzt" value="wsh">
													<p><img src="<%=stylePath%>images/ico_dsh.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="s" property="shzt" value="btg">
													<p><img src="<%=stylePath%>images/ico_shbtg.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="s" property="shzt" value="th">
													<p><img src="<%=stylePath%>images/ico_shth.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="s" property="shzt" value="xcs">
													<p><img src="<%=stylePath%>images/ico_shxcs.gif" width="52" height="18" /></p>
												</logic:equal>
												</td>
												<th>
												审核时间
												</th>
											<td align="left">
												${s.shsj}
											</td>
											<th>
												审核人
											</th>
											<td align="left">
												${s.shrxm}
											</td>
											</tr>
											<tr>
											<th>
												审核意见
											</th>
											<td colspan="7" style="word-break:break-all;width:100%" align="left" >
												${s.shyj}
											</td>
										</tr>
								</logic:iterate>
							</logic:notEmpty>
					</tbody>
					</table>
					</td>
					</tr>
					</tbody>
					</table>
					
					<table width="100%" border="0" class="formlist">
					
						<thead>
							<tr>
								<th colspan="4">
									<span>已受处分情况</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<td colspan="4">
							<table class="formList" width="100%">
								<thead align="center">
									<tr align="center">
										<td width="15%"><b>学年</b></td>
										<td width="10%"><b>学期</b></td>
										<td width="10%"><b>处分类别</b></td>
										<td width="20%"><b>处分原因</b></td>
										<td width="20%"><b>处分时间</b></td>
										<td width="25%"><b>处分文号</b></td>
									</tr>
								</thead>
								<tbody align="center">
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
					</tbody>
					</table>
					
				</table>
				</table>
			</div>
			</div>
			<table width="100%" border="0" class="formlist" >
					
				<tfoot>
					<tr>
						<td colspan="4">
							
							<div class="bz">
								"<span class="red">*</span>"为必填项
								
							</div>
							<div class="btn">
							<logic:equal property="sftj" name ="cfsh" value="0">
								
									<button type="button" onclick="tongguo();return false;" >
											通过
										</button>
									<logic:equal name ="sfZgj" value="true">
									<button type="button"  onclick="tongguoBtj();return false;">
											通过并提交
										</button>
									</logic:equal>
									<button type="button"    onclick="butongguo();return false;" >
										不通过
									</button>
									
									<logic:equal name ="sfDyj" value="false">
										
										<button type="button"    onclick="tuihui();return false;" >
											退回
										</button>
									</logic:equal>
								
								</logic:equal>
								<button type="button"  onclick="Close();return false;" >
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
				</input>
			
		</html:form>
		
	</body>
</html>
