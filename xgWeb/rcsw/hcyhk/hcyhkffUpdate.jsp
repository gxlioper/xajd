<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function saveHcyhkff(){
			var url="hcyhkff.do?method=hcyhkffUpdate&doType=save";
			
			if($("xh") && $("xh").value==""){
				alertInfo("学号不能为空!",function(){});
				return false;
			}
			
			if($("qdz") && $("qdz").value==""){
				alertInfo("起点站不能为空!",function(){});
				return false;
			}
			
			if($("zdz") && $("zdz").value==""){
				alertInfo("终点站不能为空!",function(){});
				return false;
			}
			
			showTips("保存中,请稍候...");
			refreshForm(url);
		}
		
		function updateHcyhkff(){
			var url="hcyhkff.do?method=hcyhkffOne&doType=save";
			
			if($("qdz") && $("qdz").value==""){
				alertInfo("起点站不能为空!");
				return false;
			}
			
			if($("zdz") && $("zdz").value==""){
				alertInfo("终点站不能为空!");
				return false;
			}
			showTips("保存中,请稍候...");
			refreshForm(url);
		}
		
		function setTextRed(){
			if($("doType") && $("doType").value=="view"){
			 jQuery('input[type=text]').attr('readonly',true);
			}
		}
		function checkbz(obj,len){
			var bz=obj.value;
			if(bz.length>len){
				obj.value=bz.substring(0,len);
			}
		}
		jQuery(function(){
			/*if(jQuery("#doType").val()=="view"){
				jQuery("#qdz").attr("disabled",true);
				jQuery("#zdz").attr("disabled",true);
			}*/
		})
		</script>
	</head>
	<body onload="setTextRed()">
		<html:form action="/hcyhkff" method="post">
			<!-- 隐藏域 -->
			<input type="hidden" name="url" id="url"
				value="/xgxt/hcyhkff.do?method=hcyhkffUpdate" />
			<input type="hidden" name="doType" id="doType" value='${doType}' />
			<input type="hidden" name="tableName" id="tableName" value='xg_view_rcsw_wffhcyhk' />
			<input type="hidden" name="pkValue" id="pkValue" value='${rs.pkValue}' />
			<input type="hidden" name="message" id="message" value='${message}' />
			<!-- 隐藏域 -->
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">

					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:equal name="doType" value="add">
										<button type="button" class="button2" id="btn_bc" onclick="saveHcyhkff()">
											保 存
										</button>
									</logic:equal>
									<logic:equal name="doType" value="update">
										<button type="button" class="button2" id="btn_bc" onclick="updateHcyhkff()">
											保 存
										</button>
									</logic:equal>
									<button type="button" class="button2" id="btn_gb" onclick="Close();return false;">
											关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead >
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<th style="width:16%">
								<font color="red">*</font>学号
							</th>
							<td style="width:34%">
								<logic:equal name="doType" value="add">
									<logic:empty name="xh">
										<html:text property="xh" styleId="xh" readonly="true"
										value="${rs.xh }" />
										<button type="button" class="btn_01" id="" onclick="sendXx();return false;">
											选 择
										</button>
									</logic:empty>
									<%--<logic:notEmpty name="xh">
										${rs.xh }
										<html:hidden property="xh" styleId="xh" value="${rs.xh }" />
									</logic:notEmpty>
								--%></logic:equal>
									<logic:notEmpty name="xh">
										${rs.xh }
										<html:hidden property="xh" styleId="xh" value="${rs.xh }" />
									</logic:notEmpty>
								
							</td>
							<th style="width:16%">
								学年
							</th>
							<td style="width:34%">
								${xn}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								姓名
							</th>
							<td style="width:34%">
								${rs.xm}
							</td>
							<th style="width:16%">
								学期
							</th>
							<td style="width:34%">
								${xqmc}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								性别
							</th>
							<td style="width:34%">
								${rs.xb}
							</td>
							<th style="width:16%">
								年度
							</th>
							<td style="width:34%">
								${nd}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								年级
							</th>
							<td style="width:34%">
								${rs.nj}
							</td>
							<th style="width:16%">
								<bean:message key="lable.xb" />
							</th>
							<td style="width:34%">
								${rs.xymc}
							</td>
						</tr>	
						<tr>
							<th style="width:16%">
								专业
							</th>
							<td style="width:34%">
								${rs.zymc}
							</td>
							<th style="width:16%">
								班级
							</th>
							<td style="width:34%">
								${rs.bjmc}
							</td>
						</tr>	
						<tr>
							<th style="width:16%">
								<font color="red">*</font>起点站
							</th>
							<td style="width:34%">
							<logic:notEqual name="doType" value="view">
								<html:text property="qdz" styleId="qdz" value="${rs.qdz }" maxlength="10"/>
							</logic:notEqual>
							<logic:equal name="doType" value="view">
								${rs.qdz }
							</logic:equal>
							</td>
							<th style="width:16%">
								<font color="red">*</font>终点站
							</th>
							<td style="width:34%">
							<logic:notEqual name="doType" value="view">
								<html:text property="zdz" styleId="zdz" value="${rs.zdz }" maxlength="10"/>
							</logic:notEqual>
							<logic:equal name="doType" value="view">
								${rs.zdz  }
							</logic:equal>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								家庭地址<br/>
								<logic:notEqual name="doType" value="view">
								(限25字)
								</logic:notEqual>
							</th>
							<td colspan="3">
								<!-- 
								<html:textarea name='rs' property='jtdz' styleId="jtdz" style="word-break:break-all;width:99%" onblur="chLeng(this,25);" rows='4' />
								 -->
							<logic:notEqual name="doType" value="view">
								<html:text property="jtdz" styleId="jtdz" value="${rs.jtdz }" maxlength="25" style="width:350px;"/>
							</logic:notEqual>
							<logic:equal name="doType" value="view">
								${rs.jtdz}
							</logic:equal>
							</td>
						</tr>		
						<tr>
							<th style="width:16%">
								备注<br/>
								<logic:notEqual name="doType" value="view">
								(限60字)
								</logic:notEqual>
							</th>
							<td colspan="3">
							<logic:notEqual name="doType" value="view">
									<html:textarea name='rs' property='bz' styleId="bz" style="word-break:break-all;width:99%" onkeyup="checkbz(this,60);"
										rows='4' />
							</logic:notEqual>
							<logic:equal name="doType" value="view">
								${rs.bz}
							</logic:equal>
							</td>
						</tr>	
					</tbody>
				</table>
			</div>

			
			<%@ include file="/comm/other/tsxx.jsp"%>
			
		</html:form>
	</body>
</html>
