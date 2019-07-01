<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
		
		function xsgbDgsh(shzt){
			var url="/xgxt/cdtyXsst.do?method=xsstgbDgsh&doType=dgsh&shzt="+shzt;
			refreshForm(url);
		}
		
		
		function updateZxdkSq(){
			
			var url="/xgxt/zxdk_xnmz.do?method=zxdkModi&doType=save";
			refreshForm(url);
		}
		
		function displayTbody(id){
			if($(id).style.display=="none"){
				$(id).style.display="";
			}else{
				$(id).style.display="none"
			}
		}
		
		function setXsxxb(){
			$("tableName").value="xg_view_xsh_cygl";
			$("realTable").value="xg_view_xsh_cygl";
		}
		
		
		function saveStgb(){
			
			if($("xh") && $("xh").value==""){
				alertInfo("学号不能为空!");
				return false;
			}
			if($("stdm") && $("stdm").value==""){
				alertInfo("社团不能为空!");
				return false;
			}
			if($("zwdm") && $("zwdm").value==""){
				alertInfo("社团职务不能为空!");
				return false;
			}
			
			if(checkStgb()){
				var url="/xgxt/cdtyXsst.do?method=xsstgbSq&doType=save";
				refreshForm(url);
			}
		}
		
		function checkStgb(){
			var url="cdtyAjax.do?method=checkStgb"
			jQuery.get(url,function(result){
						if(!result=="true"){
							alertInfo("已存在社团干部审核记录,不能重复申请!");
							return false;
						}
					}
				);
		}
		</script>
	</head>
	<body onload="">
		<html:form action="/cdtyXsst" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="rtsj" id="rtsj" value="${rtsj}" />
			<input type="hidden" name="url" id="url"
				value="cdtyXsst.do?method=xsstgbSq" />
			<!-- 隐藏域 -->
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<logic:equal name="doType" value="add">
				<logic:equal name="rs" property="sfysq" value="ysq">
					<div id="xxPrompt" class="prompt">
						<h3>
							<span>提示：</span>
						</h3>
						<p>
							已有申请信息，无法重复申请！
						</p>
						<a class="close" title="隐藏"
							onclick="this.parentNode.style.display='none';"></a>
					</div>
				</logic:equal>
			</logic:equal>
			<div class="tab">
				<table width="100%" border="0" class="formlist">

					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:equal name="doType" value="add">
										<button class="button2" id="btn_bc" onclick="saveStgb()">
											保 存
										</button>
									</logic:equal>
									<logic:equal name="doType" value="update">
										<logic:notEqual name='rs' property="sfsh" value="ysh">
											<button class="button2" id="btn_bc" onclick="updateZxdkSq()">
												保 存
											</button>
										</logic:notEqual>
									</logic:equal>
									<logic:equal name="doType" value="dgsh">
										<logic:notEqual name='rs' property="sfsh" value="ysh">
											<button class="button2" id="btn_bc" onclick="xsgbDgsh('通过')">
												通 过
											</button>
											<button class="button2" id="btn_bc" onclick="xsgbDgsh('不通过')">
												不通过
											</button>
										</logic:notEqual>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead onclick="displayTbody('tbody_jbxx')">
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>

					<tbody id="tbody_jbxx">
						<tr>
							<th style="width:16%">
								<font color="red">*</font>学号
							</th>
							<td style="width:34%">
								<html:text property="xh" styleId="xh" readonly="true"
									value="${rs.xh }" />
								<logic:equal name="doType" value="add">
									<button class="btn_01" id="" onclick="setXsxxb();sendXx()">
										选 择
									</button>
								</logic:equal>
							</td>
							<th style="width:16%">
								姓名
							</th>
							<td style="width:34%">
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								性别
							</th>
							<td style="width:34%">
								${rs.xb }
							</td>
							<th style="width:16%">
								年级
							</th>
							<td style="width:34%">
								${rs.nj }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								<bean:message key="lable.xb" />
							</th>
							<td id="" style="width:34%">
								${rs.xymc }
							</td>
							<th style="width:16%">
								专业
							</th>
							<td id="" style="width:34%">
								${rs.zymc }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								班级
							</th>
							<td style="width:34%">
								${rs.bjmc }
							</td>
							<th style="width:16%">
								<font color="red">*</font>社团
							</th>
							<td style="width:34%">
								<html:select property="stdm" styleId="stdm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="stList" property="stdm"
										labelProperty="stv" />
								</html:select>
							</td>
						</tr>
						<tr>

							<th style="width:16%">
								<font color="red">*</font>社团职务
							</th>
							<td style="width:34%">
								<html:select property="zwdm" styleId="zwdm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="stgbList" property="dm"
										labelProperty="gbmc" />
								</html:select>
							</td>
							<th style="width:16%">
							</th>
							<td style="width:34%">
							</td>
						</tr>
					</tbody>
				</table>
			</div>


			<%@ include file="/comm/other/tsxx.jsp"%>
			<logic:equal name="doType" value="add">
				<logic:equal name="rs" property="sfysq" value="ysq">
					<script type="text/javascript">
						$("btn_bc").disabled="true";
					</script>
				</logic:equal>
			</logic:equal>
		</html:form>
	</body>
</html>
