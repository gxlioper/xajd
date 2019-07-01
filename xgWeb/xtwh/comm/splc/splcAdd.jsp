<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getCommGygl.js'></script>
		<script type="text/javascript">
		function scbz(){
			var sum = $("jscmdm").value;
			if(sum!=''){
				var html="<div style='width:99%;height:250px;overflow-y:auto;overflow-x:hidden'>";
				html+="<table width='100%' border='0' class='formlist'>";
				for(i=1;i<=sum;i++){
					html+="<tr><td align='center' style='width:11%;height:22px;'>第";
					html+=$("jscmdm").options[i-1].text;
					html+="级审核</td><td style='width:30%;'> <input type='radio' name=shbz"+i+" onclick=\"changeLx('"+i+"','1')\"  > 选择通用岗位";
					html+="<input type='radio' name=shbz"+i+" onclick=\"changeLx('"+i+"','2')\"> 新增自定义岗位</td><td id='shtd"+i+"'  style='width:59%;'> ";
					html+="<input type='hidden' id='hid_"+i+"' name='hidArr'>";
					html+="</td>";
				}
				html+="</table>";
				html+="</div>";
				$("rsTable").innerHTML=html;
			}
		}
		
		function changeLx(num,lx){
			var html = "";
			if(lx=='1'){
				html+="<select name='lcgwdms"+num+"' id='lcgwdms"+num+"' style='width:150px' onchange='inputlcgwdm("+num+")'>";
				html+=$('spgwlist').innerHTML;
				html+="</select>";
				html+="<input name='lcgwdm"+num+"' id='lcgwdm"+num+"' type='hidden' />";
			}else{
				html+="请录入新岗位名称：<input type='text' id = lcgw"+num+" style='width:120px' name = lcgw"+num+" onblur='checkLen(this,32)'/>  ";
				
			}
			html+="数据范围限定：<select name='sqgwzdms"+num+"' id='spgwzdms"+num+"' style='width:120px' onchange='inputspgwzdm("+num+")'>";
			html+=$('spgwzlist').innerHTML;
			html+="</select>";
			html+="<input type='hidden' id='hid_"+num+"' name='hidArr'>"
			html+="<input name='spgwzdm"+num+"' id='spgwzdm"+num+"' type='hidden' />";
			$("shtd"+num).innerHTML=html;
			if($("hid_"+num)){
				$("hid_"+num).value=lx
			}
		}
		function inputlcgwdm(num){
			$("lcgwdm"+num).value=$("lcgwdms"+num).options[$("lcgwdms"+num).selectedIndex].value;
		}
		function inputspgwzdm(num){
			$("spgwzdm"+num).value=$("spgwzdms"+num).options[$("spgwzdms"+num).selectedIndex].value;
		}
		
		function checkNumBer(obj){
			obj.value=obj.value.replace(/[^\d]/g,'');
		}
		
		function saveSplc(){
			var hidArr=document.getElementsByName("hidArr");
			var spgwzdmflg = false;
			for(i=1;i<=hidArr.length;i++){
				if(hidArr[i-1].value=='1'){
					if($("lcgwdms"+i).value==""){
						alertError("请选择第"+i+"级审核岗位!");
						return false;
					}
				}else if(hidArr[i-1].value=='2'){
					if($("lcgw"+i).value==""){
						alertError("请输入第"+i+"级审核岗位名称!");
						return false;
					}
				}else {
					alertError("请选择第"+i+"级审核岗位类型!");
					return false;
				}
				if($("spgwzdm"+i).value!=""){
					spgwzdmflg = true;
				}
				for(j=i+1;j<=hidArr.length;j++){
					if($("lcgwdm"+i) && $("lcgwdm"+j)){
						if($("lcgwdm"+i).value==$("lcgwdm"+j).value){
							alert("第"+i+"级审核岗位与第"+j+"级审核岗位相同!");
							return false;
						}
					}
				}
			}
			
			
			
			if($("lcmc") && $("lcmc").value==""){
				alertError("流程名称不能为空!");
				return false;
			}
			if($("djlx") && $("djlx").value==""){
				alertError("所属模块不能为空!");
				return false;
			}
			if($("lcmc") && $("lcmc").value==""){
				alertError("流程名称不能为空!");
				return false;
			}
			if($("ms") && $("ms").value==""){
				alertError("流程描述不能为空!");
				return false;
			}

			if(spgwzdmflg){
				showConfirm("注意：已有岗位设定数据范围限定，该岗位内用户授予是否确定初始化？",{"okFun":function(){
			    	 refreshForm('splcNew.do?method=splcSave&type=csh');
				 },"cancelFun":function(){
					 refreshForm('splcNew.do?method=splcSave');
					}});
			} else {
				refreshForm('splcNew.do?method=splcSave');
			}
			
			//refreshForm('splcNew.do?method=splcSave');
		}
		
		
		
		function checkPk(tableName,zdm,zdz,dis){
			
			dwr.engine.setAsync(false);
			getCommGygl.checkPkValue(tableName,zdm,zdz,function(data){
				
				if(data==false){
					if($(zdm+"Prompcon")){
						$(zdm+"Prompcon").style.display="";
						if(dis=="yes"){
							$("btn_bc").disabled="true";
						}
					}
				}else{
					if($(zdm+"Prompcon")){
						$(zdm+"Prompcon").style.display="none";
						if(dis=="yes"){
							$("btn_bc").disabled="";
						}
					}
				}
			});
			dwr.engine.setAsync(true);
		}
		</script>
		<%--		<style>--%>
		<%--		.include_tab{border-collapse:collapse;border:0px;}--%>
		<%--		.include_tab td{border-top:0;bordedr-right:1px solid red!importalt;border-bottom:0;border-left:0;}--%>
		<%--		.include_tab_r{}--%>
		<%--		</style>--%>
	</head>
	<body>
		<html:form action="/splcNew" method="post">
			<input type="hidden" name="cxjg" id="cxjg" />
			<html:select property="spgwdm" styleId="spgwlist"
				style="display: none;">
				<html:option value=""></html:option>
				<html:options collection="gdgwList" property="id" labelProperty="mc" />
			</html:select>
			<html:select property="spgwzdm" styleId="spgwzlist"
				style="display: none;">
				<html:option value=""></html:option>
				<html:option value="bzr">班主任库</html:option>
				<html:option value="fdy">辅导员库</html:option>
				<html:option value="bzrfdy">班主任+辅导员库</html:option>
			</html:select>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
				</p>
			</div>
			
			<!-- 提示信息 end-->
					<div id="div_help" class="prompt" style="display: none">
						<h3>
							<span>提示：</span>
						</h3>
						<p>
							<span>
								1.通用岗位：可以多个审批流同时使用，调整<font color="red">用户授予</font>，其他审批流当中使用当前通用岗位的人员也会随之发生变化。
							<br />
								2.自定义岗位：只会存在当前审核流当中，调整<font color="red">用户授予</font>，不会对其他审核流当中的审核人员产生影响。
							</span>
						</p>
						<a class="close" title="隐藏"
						   onclick="this.parentNode.style.display='none';"></a>
					</div>
					<!-- 提示信息 end-->
			<div class="tab" id="dgncz" style="width:100%;height:320px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>流程基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:16%">
								<font color="red">*</font>流程名称
							</th>
							<td colspan="3" id="p_ld" style="width:84%">
								<input type="text" name="lcmc" id="lcmc" style="width: 60%"
									maxlength="32"  onblur="checkPk('xg_xtwh_splc','mc',this.value,'yes')" onkeydown="if(event.keyCode==13)return false;"/>
								<span class="msg_prompt2" id="mcPrompcon" style="display:none">
									<em class="prompcon">
					                	流程名称已经存在,请重新填写
					                </em>
					            </span>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								<font color="red">*</font>所属模块
							</th>
							<td id="p_ld" style="width:34%">
								<html:select property="djlx" styleId="djlx" style="width:190px">
									<html:option value=""></html:option>
									<html:options collection="ssmkList" property="mkdm"
										labelProperty="mkmc" />
								</html:select>
							</td>
							<th style="width:16%">
								<font color="red">*</font>流程分几步
							</th>
							<td id="p_ld" style="width:84%">
								<select name="jscmdm" id="jscmdm" style="width:150px"
									onchange="scbz()">

									<option value="1">
										一
									</option>
									<option value="2">
										二
									</option>
									<option value="3">
										三
									</option>
									<option value="4">
										四
									</option>
									<option value="5">
										五
									</option>
									<option value="6">
										六
									</option>
									<option value="7">
										七
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								<font color="red">*</font>流程说明
							</th>
							<td colspan="3" id="p_ld" style="width:84%">
								<textarea rows="2" name="ms" id="ms"
									style="word-break:break-all;width:95%"
									onblur="checkLen(this,64)" /></textarea>
							</td>
						</tr>
					<thead>
						<tr>
							<th colspan="4">
								<span> 流程维护 </span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div id="rsTable" style="width:100%;height:250px;">
									<table style="width:100%;height:200px" border="0">
										<tr>
											<td align="center" valign="middle">
												<span><font color="red">请先选择审批步骤数！</font> </span>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" class=""
										onclick="saveSplc();return false;"
										id="btn_bc">
										保存并进入下一步
									</button>
									<button type="button" class="" onclick="Close();return false;" id="btn_fh">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					</table>
			<!-- 提示信息 -->
			<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alert("操作失败！");
					</script>
				</logic:equal>
			</logic:notEmpty>
			<script language="javascript"  defer="defer">
				jQuery(document).ready(function(){
					var sum = $("jscmdm").value;
					if(sum!=''){
						var html="<div style='width:99%;height:250px;overflow-y:auto;overflow-x:hidden'>";
						html+="<table width='100%' border='0' class='formlist'>";
						for(i=1;i<=sum;i++){
							html+="<tr><td align='center' style='width:11%;height:22px;'>第";
							html+=$("jscmdm").options[i-1].text;
							html+="级审核</td><td style='width:30%;'> <input type='radio' name=shbz"+i+" onclick=\"changeLx('"+i+"','1')\" > 选择通用岗位";
							html+="<input type='radio' name=shbz"+i+" onclick=\"changeLx('"+i+"','2')\"> 新增自定义岗位</td><td id='shtd"+i+"'  style='width:59%;'> ";
							html+="<input type='hidden' id='hid_"+i+"' name='hidArr'>";
							html+="</td>";
						}
						html+="</table>";
						html+="</div>";
						$("rsTable").innerHTML=html;
					}
				});
			</script>
		</html:form>
	</body>
</html>
