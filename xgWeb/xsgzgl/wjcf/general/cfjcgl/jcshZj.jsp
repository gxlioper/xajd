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
			confirmInfo("确定通过吗？",function(tag){
				if(tag=="ok"){
			var cfId = $("cfId").value;
			var sfZgj = $("sfZgj").value; 
			var spgwId = $("spgwId").value;
			var shyj = $("shyj").value;
			if(sfZgj=="true"){
				var jcwh = $("jcwh").value;
				var jcsj = $("jcsj").value; 
				if(jcwh==""||jcsj==""){
					alertError("请将带*的项目填写完整！");
					return false;
				}else{
					var url = "general_wjcf_cfjcsh_ajax.do?method=saveCfjcsh&doType=tg";
					//参数
				 	var parameter = {
				 		"cfId":cfId,
				 		"spgwId":spgwId,
				 		"jcwh":escape(jcwh),
				 		"jcsj":jcsj,
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
				var url = "general_wjcf_cfjcsh_ajax.do?method=saveCfjcsh&doType=tg";
				//参数
			 	var parameter = {
			 		"cfId":cfId,
			 		"spgwId":spgwId,
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
			confirmInfo("确定通过吗？",function(tag){
				if(tag=="ok"){
			var cfId = $("cfId").value;
			var sfZgj = $("sfZgj").value; 
			var spgwId = $("spgwId").value;
			var shyj = $("shyj").value;
			var jcwh = $("jcwh").value;
			var jcsj = $("jcsj").value; 
				if(jcwh==""||jcsj==""){
					alertError("请将带*的项目填写完整！");
					return false;
				}else{
					var url = "general_wjcf_cfjcsh_ajax.do?method=saveCfjcsh&doType=tg";
					//参数
				 	var parameter = {
				 		"cfId":cfId,
				 		"spgwId":spgwId,
				 		"jcwh":escape(jcwh),
				 		"jcsj":jcsj,
				 		"shyj":escape(shyj)
					};
					jQuery.post(url,
						parameter,
						function(result){
						var url = "general_wjcf_cfjcsh_ajax.do?method=zjtjSh";
									//参数
								var parameter = {
								 		"cfId":cfId,
								 		"jcwh":escape(jcwh),
								 		"jcsj":jcsj
							};
								jQuery.post(url,
									parameter,
									function(result){
									 showAlert("操作成功",{},{"clkFun":function(){
						    				if (parent.window){
						    					refershParent();
						    				}
						    			}});
									}
									);
					});
					}
			
				}
			});
		}
		
		//不通过
		function butongguo(){

			confirmInfo("确定不通过吗？",function(tag){
				if(tag=="ok"){
					var cfId = $("cfId").value;
					var spgwId = $("spgwId").value;
					var shyj = $("shyj").value;
					var url = "general_wjcf_cfjcsh_ajax.do?method=saveCfjcsh&doType=btg";
					//参数
				 	var parameter = {
				 		"cfId":cfId,
				 		"spgwId":spgwId,
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
					var spgwId = $("spgwId").value;
					var shyj = $("shyj").value;
					var url = "general_wjcf_cfjcsh_ajax.do?method=saveCfjcsh&doType=th";
					//参数
				 	var parameter = {
				 		"cfId":cfId,
				 		"spgwId":spgwId,
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
		function fjxz(type){
			var url="";
			if(type=="sbfj"){
				var sbfj = $("sbfj").value;
				$("fjmc").value=sbfj;
				url="general_wjcf_cfjc_ajax.do?method=fjxz";
				}
			if(type=="ssfj"){
				var ssfj = $("ssfj").value;
				$("fjmc").value=ssfj;
				url="general_wjcf_cfjc_ajax.do?method=ssFjxz";
				}
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		</script>
	</head>
	<body >
		<html:form action="/general_wjcf_cfjcsh_ajax" method="post">
					<input type="hidden" name="url" id="url" value="general_wjcf_cfsb_ajax.do?method=zjWjcf">	
					<input type="hidden" name="tableName" id="tableName" value="view_xsjbxx">	
					<html:hidden property="xh" styleId="xh" value="${rs.xh}" />
					<html:hidden property="cfId" styleId="cfId" value="${wjsb.cfid}" />
					<input type="hidden" name="message" id="message" value="${message }">	
					<input type="hidden" name="doType" id="doType"  >	
					<input type="hidden" name="sfZgj" id="sfZgj"  value="${sfZgj }">	
					<html:hidden property="spgwId" styleId="spgwId" value="${spgw}" />
					<input type="hidden" name="fjmc"  id="fjmc"  value=""/>
					<input type="hidden" id="sbfj" name="sbfj" value="${wjsb.fjmc}"/>
					<input type="hidden" id="ssfj" name="ssfj" value="${wjsb.ssfjmc}"/>
				<div  style="width:100%;height:800px;overflow-x:hidden;overflow-y:auto;">
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
							学院：
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
									<span>处分信息</span>
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
								<a  href="#" onclick="fjxz('sbfj');return false;" class="name">下载附件</a>
							</logic:notEmpty>
						</td>
					</tr>
						<tr>
						<td align="right" width="30%">
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
									<span>处分申诉信息</span>
								</th>
							</tr>
						</thead>
					<tr>
						<td align="right">
							处分申诉申请学年
						</td>
						<td align="left">
							${wjsb.xn}
						</td>
						<td align="right">
							处分申诉申请学期：
						</td>
						<td align="left">
							${wjsb.xqmc}
						</td>
					</tr>
					<tr>
					<td align="right">
							处分申诉申请时间
						</td>
						<td align="left" >
							${cfss.sqsj}
						</td>
						<td align="right">
							申述书面材料或附件：
						</td>
						<td align="left">
							<logic:notEmpty name="wjsb" property="ssfjmc">
								<a  href="#" onclick="fjxz('ssfj');return false;" class="name">下载附件</a>
							</logic:notEmpty>
						</td>
					</tr>
						<tr>
					<td align="right">
							处分申诉申请理由
						</td>
						<td align="left" colspan="3" style="word-break:break-all;width:100%">
							${cfss.sqly}
						</td>
					</tr>
					<thead>
							<tr>
								<th colspan="4">
									<span>申请解除信息</span>
								</th>
							</tr>
						</thead>
					<tr>
						<td align="right">
							处分解除申请学年
						</td>
						<td align="left">
							${wjsb.xn}
						</td>
						<td align="right">
							处分解除申请学期：
						</td>
						<td align="left">
							${wjsb.xqmc}
						</td>
					</tr>
					<tr>
					<td align="right">
							处分解除申请时间
						</td>
						<td align="left" colspan="3">
							${cfjc.sqsj}
						</td>
					</tr>
						<tr>
					<td align="right">
							处分解除申请理由
						</td>
						<td align="left" colspan="3" style="word-break:break-all;width:100%">
							${cfjc.sqly}
						</td>
					</tr>
					<thead>
							<tr>
								<th colspan="4">
									<span>审核解除信息</span>
								</th>
							</tr>
						</thead>
						<logic:equal name ="sfZgj" value="true">
							<tr>
								<td align="right">
									<font color="red">*</font>处分解除文号：
								</td>
								<td align="left"  >
									<html:text property='jcwh' styleId="jcwh" maxlength="30" value="${cfsh.jcwh}"/>
								</td>
								<td align="right">
									<font color="red">*</font>处分解除时间：
								</td>
								<td align="left">
								<html:text property="jcsj" styleId="jcsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('jcsj','y-mm-dd');" value="${cfsh.jcsj}"/>
							</td>
							</tr>
						</logic:equal>
						<tr>
							<td align="right" width="15%">
							审核意见：<br/>
							<font color="red"><B>(限1000字)</B></font>
						</td>
						<td align="left" colspan="3" >
									<html:textarea  property='shyj' styleId='shyj'  style="width:600px"
									rows='5' value="${cfsh.shyj}" onblur="checkLen(this,1000)"/>
						</td>
					</tr>
					</tbody>
					
					<table width="100%" border="0" class="formlist">
							<thead>
							<tr>
								<th colspan="4">
									<span>处分解除审核情况</span>
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
											<td colspan="7" style="word-break:break-all;width:100%" align="left">
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
								
									<button type="button"  id="btn_tg" onclick="tongguo();return false;" id="buttonSave">
											通过
										</button>
									<logic:equal name ="sfZgj" value="true">
									<button type="button"  id="btn_tg" onclick="tongguoBtj();return false;" id="buttonSave">
											通过并提交
										</button>
									</logic:equal>	
									<button type="button"  id="btn_btg"  onclick="butongguo();return false;" id="buttonSave">
										不通过
									</button>
									
									<logic:equal name ="sfDyj" value="false">
										<button type="button"  id="btn_th"  onclick="tuihui();return false;" id="buttonSave">
											退回
										</button>
									</logic:equal>
								
								</logic:equal>
								<button type="button"  onclick="Close();return false;" id="buttonClose">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
				</input>
			
		<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
