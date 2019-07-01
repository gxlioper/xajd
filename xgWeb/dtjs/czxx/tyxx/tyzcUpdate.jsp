<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/dtjs/dtjsFunction.js"></script>
		<script type="text/javascript">
		function saveTyzc(){
			var url="czxxDtjsTyxx.do?method=tyzcAdd&doType=save";
			
			if(null==$("#xh").val()|| $("#xh").val()==""){
				alertInfo("学号不能为空!");
				return false;
			}
			showTips("保存中,请稍候...");
			refreshForm(url);
		}
		
		function updateTyzc(){
			var url="czxxDtjsTyxx.do?method=tyzcUpdate&doType=save";
			showTips("保存中,请稍候...");
			refreshForm(url);
		}
		</script>
	</head>
	<body onload="">
		<html:form action="/czxxDtjsTyxx" method="post">
			<!-- 隐藏域 -->
			<input type="hidden" name="url" id="url"
				value="/xgxt/czxxDtjsTyxx.do?method=tyzcAdd" />
			<input type="hidden" name="sfysq" id="sfysq" value='${rs.sfysq}' />
			<!-- 修改时 -->
			<input type="hidden" name="shzt" id="shzt" value='${rs.shzt}' />
			<input type="hidden" name="doType" id="doType" value='${doType}' />
			<input type="hidden" name="tableName" id="tableName" value='${tableName}' />
			<input type="hidden" name="zcsj" id="zcsj" value='${zcsj}' />
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
										<button type="button" class="button2" id="btn_bc" onclick="saveTyzc()">
											保 存
										</button>
									</logic:equal>
									<logic:equal name="doType" value="update">
										<logic:notEqual name='rs' property="sfsh" value="ysh">
										<button type="button" class="button2" id="btn_bc" onclick="updateTyzc()">
											保 存
										</button>
										</logic:notEqual>
									</logic:equal>
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
								<html:text property="xh" styleId="xh" readonly="true"
									value="${rs.xh }" />
								<logic:equal name="doType" value="add">
									<button type="button" class="btn_01" id="" onclick="sendXx();return false;">
										选 择
									</button>
								</logic:equal>
							</td>
							<th style="width:16%">
								学年
							</th>
							<td style="width:34%">
								<logic:equal name="doType" value="update">
								${rs.xn}
								<html:hidden property="xn" value="${rs.xn}"/>
								</logic:equal>
								<logic:equal name="doType" value="view">
								${rs.xn}
								<html:hidden property="xn" value="${rs.xn}"/>
								</logic:equal>
								<logic:equal name="doType" value="add">
								${xn}
								<html:hidden property="xn" value="${xn}"/>
								</logic:equal>
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
								性别
							</th>
							<td style="width:34%">
								${rs.xb}
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
								备注
							</th>
							<td  colspan="3">
								<html:textarea name="rs" property="bz" styleId="bz" rows="4" style="word-break:break-all;width:99%" onblur="chLeng(this,250)">
								</html:textarea>
							</td>
							
						</tr>	
					</tbody>
				</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
