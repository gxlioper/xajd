<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
	</head>
	<body>
		<html:form action="/njjsXjbj" method="post">
			<input type="hidden" id="pkValue" name="pkValue"
				value="${rs.pkValue}" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:notEqual value="view" name="doType">
										<button class="button2"
											onclick="saveData('njjsXjbj.do?method=xjbjDgsh&doType=save','')">
											保 存
										</button>
									</logic:notEqual>
									<button class="button2" onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>优秀班集体申请</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								班级名称
							</th>
							<td width="34%">
								<html:select property="save_bjdm" styleId="bj" 
											 style="width:200px" value="${rs.bjdm }" 
											 disabled="true">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
								</html:select>
							</td>
							<th width="16%">
								优秀班级类型
							</th>
							<td width="34%">
								<html:select property="save_yxlx" 
											 styleId="yxlx" 
											 value="${rs.yxlx }" 
											 disabled="true">
									<html:options collection="xjlxList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								班级人数
							</th>
							<td>
								${rs.bjrs }
							</td>
							<th>
								班长学号
							</th>
							<td>
								${rs.bzxh }
							</td>
						</tr>
						<tr>
							<th>
								班主任
							</th>
							<td>
								${rs.bzrzgh }
							</td>
							<th>
							</th>
							<td>
							</td>
						</tr>
						<tr>
							<th>
								申请人
							</th>
							<td>
								${rs.sqr }
							</td>
							<th>
								申请时间
							</th>
							<td>
								${rs.sqsj }
							</td>
						</tr>
						<tr>
							<th>
								主要事迹
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.zysj }
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.bz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:present name="shxx">
							<logic:iterate id="s" name="shxx">
								
								<logic:equal value="${xjbjForm.shgw }" name="s" property="mc">
									<tr>
										<th>
											审核步骤
										</th>
										<td>
											${s.xh }
										</td>
										<th>
											${s.mc }
											<html:hidden property="shgw" value="${s.mc }"/>
										</th>
										<td>
											<html:select property="shjg" value="${s.shzt }">
												<html:options collection="shztList" property="en" labelProperty="cn"/>
											</html:select>
										</td>
										<tr>
											<th>审核时间</th>
											<td>
												<html:text property="shsj" value="${shsj }"></html:text>
											</td>
											<th>审核人</th>
											<td><html:text property="shr" value="${userNameReal }" readonly="true"></html:text></td>
										</tr>
										<tr>
											<th>审核意见</th>
											<td colspan="3" style="word-break:break-all;">
												<html:textarea property="shyj" value="${s.shyj }" style="width:95%" rows="5"></html:textarea>
											</td>
										</tr>
								</logic:equal>
								<logic:notEqual value="${xjbjForm.shgw }" name="s" property="mc">
									<tr>
										<th>
											审核步骤
										</th>
										<td>
											${s.xh }
										</td>
										<th>
											${s.mc }
										</th>
										<td>
											${s.shzt }
										</td>
									</tr>
									<tr>
										<th>审核时间</th>
										<td>${s.shsj }</td>
										<th>审核人</th>
										<td>${s.shr }</td>
									</tr>
									<tr>
										<th>审核意见</th>
										<td colspan="3" style="word-break:break-all;">
											${s.shyj }
										</td>
									</tr>
								</logic:notEqual>
							</logic:iterate>
						</logic:present>
					</tbody>
				</table>
		</html:form>
		<logic:present name="message">
			<script>
				alert('${message}');
				if(window.dialogArguments){
			 		dialogArgumentsQueryChick();
			 		window.close();
			 	}
			</script>
		</logic:present>
	</body>
</html>
