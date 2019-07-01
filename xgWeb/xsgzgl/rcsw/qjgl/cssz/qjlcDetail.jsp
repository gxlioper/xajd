<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/pjpy/szgyyq/pjpy_szgyyq.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//保存请假流程
		function saveQjlc(tag){
			
			if(tag == "ok"){
				//请假名称
				var lxmc = $("lxmc").value;
				if(lxmc == ""){
					alertError("类型名称不能为空，请确认");
					return false;
				}
				
				var qjlx = jQuery("[name=qjlx]:checked").eq(0).val();
				if(qjlx == ""){
					alertError("请假类型不能为空，请确认");
					return false; 
				}
				
				//最大最小天数
				var mints = $("mints").value;
				var maxts = $("maxts").value;
				
				if(mints == ""){
					alertError("大于某天不能为空，请确认");
					return false;
				}else if(maxts == ""){
					alertError("小于等于某天不能为空，请确认");
					return false;
				}else if(parseInt(mints) > parseInt(maxts)){
					alertError("【大于某天】不能大于【小于某天】，请确认");
					return false;
				}
				
				//流程
				var lcid = $("lcid").value;
				if(lcid == ""){
					alertError("请为该类型的请假选择审核流程！");
					return false;
				}
				
				var id = $("id").value;
				
				var url="rcsw_qjgl.do?method=saveQjlc";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
					
				//参数
			 	var parameter = {
					"id":id,
					"lxmc":escape(lxmc),
					"mints":mints,
					"maxts":maxts,
					"lcid":lcid
					//,"qjlx":qjlx
				};
				
				jQuery.post(url,parameter,function(result){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					if(result=='-999'){
						alertError("最小天数或者最大天数输入有误，与其他流程存在交集，请核对");
					}else{
						 showAlert(result,{},{"clkFun":function(){
			    				if (parent.window){
			    					refershParent();
			    				}
			    			}});
					}
					
				});
			}
		}
		</script>
	</head>
	<body onload="" >
	
		<!-- 标题 -->
		<%--<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>--%>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" >
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.审核流是在<font color="blue">系统维护 - 审批流程维护中维护的</font>。</br>
				2.仅展现所属模块为<font color="blue">日常事务</font>的审核流。
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/rcsw_qjgl" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden name="rs" property="id" styleId="id"/>
			<!-- 隐藏域 end-->
			
			<!-- 学生基本信息 -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="4">
							<span>请假流程维护</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="30%">
							<font color="red">*</font>请假名称
						</th>
						<td width="">
							<html:text name="rs" property="lxmc" 
								style="width:80%"
								styleId="lxmc" maxlength="20"/>
						</td>
					</tr>
					
					<tr>
						<th>
							<font color="red">*</font>请假天数
						</th>
						<td>
							大于
							<html:text name="rs" property="mints" styleId="mints"
								onkeyup="checkInputNum(this)" 
								onblur="checkInputNum(this)" maxlength="5" 
								style="width : 30px;ime-mode:disabled;"
							/>天
							
							小于等于
							<html:text name="rs" property="maxts" styleId="maxts"
								onkeyup="checkInputNum(this)" 
								onblur="checkInputNum(this)" maxlength="5" 
								style="width : 30px;ime-mode:disabled;"
							/>天
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>请假流程
						</th>
						<td>
							<html:hidden name="rs" property="lcid" styleId="lcid"/>
							<div style="width:100%;height:130px;overflow-x:hidden;overflow-y:auto;">
								<logic:iterate name="splcList" id="splcRs">
									<input type="radio" name="rad_lcid" 
										onclick="$('lcid').value=this.value"
										value="${splcRs.dm }"
										<logic:equal name="rs" property="lcid" value="${splcRs.dm }">checked="checked"</logic:equal>
									/>${splcRs.mc}
										
									<br/>
								</logic:iterate>
							</div>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">
								<!-- 保存 -->
								<button type="button" onclick="saveQjlc('ok');return false;">
									<bean:message key="lable.btn_bc_space" />
								</button>
								<!-- 关闭-->
								<button type="button" onclick="Close();return false;">
									<bean:message key="lable.btn_gb_space" />
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 学生基本信息 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxxNew.jsp"%>
			</div>

		</html:form>
	</body>
</html>