<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- 头文件 -->
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function save(){
			
			var len=jQuery("input[type=checkbox]:checked").length;
	
			if(len==0){
				alertInfo("请勾选归档资料!",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}else{
				refreshForm('stu_info_add.do?method=datumCommitSignle&doType=save');
				BatAlert.showTips('正在执行操作，请等待...');
			}
			
		}
		</script>
	</head>
	<body onload="checkWinType();">
		<html:form action="/data_search" method="post">
			<input type="hidden" id="rsNum" name="rsNum" value="${rsNum}" />
			<input type="hidden" id="doType" name="doType" value="" />
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<input type="hidden" name="message" id="message" value="操作成功"/>
				</logic:equal>
				<logic:equal value="false" name="result">
					<input type="hidden" name="message" id="message" value="操作失败"/>
				</logic:equal>
			</logic:present>
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>学生信息 - 归档资料 - 归档资料提交</a>
				</p>
			</div>
			<div class="tab"
				style="width:100%;height:550px;overflow-x:hidden;overflow-y:hidden;">
				<table width="100%" border="0" class="formlist" id="rsT">
					<thead>
						<tr>
							<th colspan="2">
								<span>单个学生提交归档资料</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								学号
							</th>
							<td width="79%">
								${rs.xh}
								<input type="hidden" name="xh" id="xh" value="${rs.xh}" />
							</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td>
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								${rs.xb}
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc}
							</td>
						</tr>
						<tr>
							<th>
								专业名称
							</th>
							<td>
								${rs.zymc}
							</td>
						</tr>
						<tr>
							<th>
								班级名称
							</th>
							<td>
								${rs.bjmc}
							</td>
						</tr>
						<logic:equal name="xxdm" value="11600">
							<tr>
								<th>
									是否提交
								</th>
								<td>
									<html:select name="rs" property="sftj" styleId="sftj"
										value="${rs.sftj}">
										<html:option value=""></html:option>
										<html:option value="已提交">已提交</html:option>
										<html:option value="未提交">未提交</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									档案在校情况
								</th>
								<td>
									<html:select name="rs" property="dazxqk" property="dazxqk"
										value="${rs.dazxqk}">
										<html:option value="">未设置</html:option>
										<html:option value="在校">在校</html:option>
										<html:option value="不在校">不在校</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									备注
									<br />
									<font color="red">(限制字数250)</font>
								</th>
								<td>
									<textarea name='bz' id="bz"
										style="word-break:break-all;width:80%"
										onblur="chLeng(this,250);" rows='3' type="_moz">${rs.bz}</textarea>
								</td>
							</tr>
						</logic:equal>

						<%--西北二民院--%>
						<logic:equal value="11407" name="xxdm">
							<tr>
								<th>
									到档情况
								</th>
								<td>
									<html:select property="ddqkdm" name="rs" styleId="ddqkdm">
										<html:option value=""></html:option>
										<html:options collection="ddqkList" property="dddm"
											labelProperty="ddmc" />
									</html:select>
								</td>
							</tr>
						</logic:equal>
					</tbody>
					<thead>
						<tr>
							<th colspan="2">
								<span>归档资料列表</span>
							</th>
						</tr>
					</thead>


					<tbody>
						<tr >
							<td colspan="2">
								<div class="tab"
									style="width:99%;height:130px;overflow-x:hidden;overflow-y:auto;">
									<table  width="99%">
										<logic:iterate id="gdzl" name="gdzlList" indexId="index">
											<tr>
												<td align="right">
													<input type="checkbox" id="zldm${index}"
														name="zldm${index}" value="${gdzl.zldm}"
														<logic:equal value="true" name="gdzl" property="sftj">checked="checked"</logic:equal> />
												</td>
												<%--西北二民院--%>
												<logic:equal value="11407" name="xxdm">
													<td>
														<span class="red">${gdzl.zlmc}</span>
													</td>
												</logic:equal>

												<logic:notEqual value="11407" name="xxdm">
													<td>
														<span class="red">${gdzl.zlmc}</span>
													</td>
												</logic:notEqual>

												<%--西北二民院--%>
												<logic:equal value="11407" name="xxdm">
													<th>
														资料张数
													</th>
													<td>
														<input type="text" id="zlzs${index}" name="zlzs${index}"
															style="width:80px" />
														张
													</td>
												</logic:equal>
											</tr>
										</logic:iterate>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:notEqual value="view" name="doType">
										<button
											onclick="save();return false;"
											style="width:80px" id="buttonSave">
											提 交
										</button>
									</logic:notEqual>
									<button onclick="Close();return false;" style="width:80px">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

