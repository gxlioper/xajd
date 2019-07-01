<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/comm/searchTj.js"></script>
		<script language="javascript" defer="defer">		
		//保存军训编制
		function saveJxbz(tag){
			if(tag=="ok"){
				var bzmc = $("bzmc").value;
				if(bzmc == ""){
					alertError("建制名称不能为空，请确认");
					return false;
				}
				
				var url="jxglJxbz.do?method=saveJxbz";
				
				var czlx=$("czlx").value;
				var bzdm=$("bzdm").value;
				var bzmc=$("bzmc").value;
				var bzdj=$("bzdj").value;
				var sjdm=$("sjdm").value;
				var jsdm=$("jsdm").value;
				var jgbh=$("jgbh").value;
				var xn=$("xn").value;

				//参数
			 	var parameter = {
			 		"czlx":czlx,
			 		"bzdm":bzdm,
			 		"bzmc":escape(bzmc),
			 		"bzdj":bzdj,
			 		"sjdm":sjdm,
			 		"jsdm":escape(jsdm),
			 		"jgbh":escape(jgbh),
			 		"xn":xn
				};

			 	$("divWaiting").style.display="none";
				$("divDisable").style.display="none";

				jQuery.ajaxSetup({async:false});
				jQuery.post(url,parameter,function(result){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					doSuccess(result);
				});
				jQuery.ajaxSetup({async:true});
			}
		}
		</script>
	</head>
	<body onload="" >	
		
		<html:form action="/jxglJxbz">
		
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="czlx" id="czlx" value="${czlx }"/>
			<input type="hidden" id="xn" value="${rs.xn }" />
			<input type="hidden" id="bzdm" value="${rs.bzdm }" />	
			<input type="hidden" id="bzdj" value="${rs.bzdj }" />
			<input type="hidden" id="sjdm" value="${rs.sjdm }" />
			<!-- 模板导出Div-->
			
			<div class="tab">
				<table class="formlist">
					<thead>
						<td colspan="2"><span>编制维护</span></td>
					</thead>
					<tbody>
						<tr>
							<th>
								学年
							</th>
							<td>
								<html:select name="rs" property="xn" disabled="true" style="width:200px">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								编制级别
							</th>
							<td>
								<html:select name="rs" property="bzdj" disabled="true" style="width:200px">
									<html:options collection="jxbzdjList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								上级编制
							</th>
							<td>
								<html:select name="rs" property="sjdm" disabled="true" style="width:200px">
									<html:option value="">无上级编制</html:option>
									<html:options collection="jxbzList" property="bzdm" labelProperty="bzmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>
								建制名称
							</th>
							<td>
								<html:text name="rs" property="bzmc" styleId="bzmc" style="width:200px" maxlength="10"/>
								<font color="red">(限录入10字)</font>
							</td>
						</tr>
						<tr>
							<th>
								教官名称
							</th>
							<td>
								<html:text name="rs" property="jsdm" styleId="jsdm" style="width:200px" maxlength="10"/>
								<font color="red">(限录入10字)</font>
							</td>
						</tr>
						<tr>
							<th>
								指导老师名称
							</th>
							<td>
								<html:text name="rs" property="jgbh" styleId="jgbh" style="width:200px" maxlength="10"/>
								<font color="red">(限录入10字)</font>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<!-- 确定 -->
									<logic:notEqual name="czlx" value="self">
									<button type="button" onclick="confirmInfo('请确认您所维护的编制?',saveJxbz);">
										确 定
									</button>
									</logic:notEqual>
									
									<!-- 关闭 -->
									<button type="button" onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>	

			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
			