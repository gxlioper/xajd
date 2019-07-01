<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a><bean:write name="title" />
				</a>
			</p>
		</div>

		<html:form action="/pjpyxfjs" method="post">

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>班级学风抽查</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button"
										onclick="refreshForm('pjpyxfjs.do?method=bjccqkshxx&type=save')">
										保 存
									</button>
									<button type="button" onclick="window.close();return false;" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								学年
							</th>
							<td align="left">
								${model.xn}
								<input type="hidden" value="${model.xn}" name="xn" />
								<input type="hidden" value="${model.pk}" name="pk" />
							</td>
							<th>
								抽查日期
							</th>
							<td align="left">
								${model.ccrq}
								<input type="hidden" value="${model.ccrq}" name="ccrq" />
							</td>
						</tr>
						<tr>
							<th>
								学期
							</th>
							<td align="left">
								${model.xqmc}
								<input type="hidden" value="${model.xq}" name="xq" />
							</td>
							<th>
								抽查类型
							</th>
							<td align="left">
								${model.jclxmc}
								<input type="hidden" value="${model.jclxdm}" name="jclxdm" />
							</td>
						</tr>
						<tr>
							<th>
								班级
							</th>
							<td align="left">
								${model.bjmc}
								<input type="hidden" value="${model.bjdm}" name="bjdm" />
							</td>
							<th>
								应到人数
							</th>
							<td align="left">
								${model.ydrs}
							</td>
						</tr>
						<tr>
							<th>
								实到人数
							</th>
							<td align="left">
								${model.sdrs}
							</td>
							<th>
								缺勤人数
							</th>
							<td align="left">
								${model.qqrs}
							</td>
						</tr>
						<tr>
							<th>
								除缺勤外的违纪人数
								<div align="center">
									(如:吃饭,睡觉等...)
								</div>
							</th>
							<td align="left">
								${model.wjrs}
							</td>
							<th>
								规定处理时间
							</th>
							<td align="left">
								${model.fdyclsj}
							</td>
						</tr>
						<tr>
							<th>
								辅导员处理时间
								
							</th>
							<td align="left">
								${model.fdysjclsj}
							</td>
							<th>
								学校审核
							</th>
							<td align="left">
								<html:select property="xxsh" name="model">
									<html:option value="通过">通过</html:option>
									<html:option value="待批复">待批复</html:option>
									<html:option value="未审核">未审核</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								学校备注
							</th>
							<td align="left" colspan="3" style="word-break:break-all;">
								${model.bz}
							</td>
						</tr>
						<tr>
							<th>
								辅导员备注
							</th>
							<td align="left" colspan="3" style="word-break:break-all;">
								${model.fdyclbz}
							</td>
						</tr>
						<logic:notEmpty name="xsList">
							<tr>
								<td colspan="4">
									<table class="dataline"  width="100%" id="tTb">
										<tr>
											<td>
												<div class="mid_box">
													<table align="center" style="width:100%" id="rsT"
														class="tbstyle">
														<thead style="height: 23px">
															<tr>
																<td nowrap="nowrap">
																	学号
																</td>
																<td nowrap="nowrap">
																	姓名
																</td>
																<td nowrap="nowrap">
																	违纪
																</td>
																<td nowrap="nowrap">
																	旷课节数
																</td>
																<td nowrap="nowrap">
																	请假
																</td>
																<td nowrap="nowrap">
																	备注
																</td>
															</tr>
														</thead>
														<tbody width="100%" class="tbstyle" id="flag">
															<logic:iterate name="xsList" id="s">
																<tr>
																	<logic:iterate id="v" name="s" offset="1" length="6">
																		<td align="left">
																			${v}
																		</td>
																	</logic:iterate>
																</tr>
															</logic:iterate>
														</tbody>
													</table>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</logic:notEmpty>
						</tbody>
					</table>
					</div>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功！");
				Close();
				if(window.dialogArguments){
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败！");
			</script>
		</logic:equal>
	</body>
</html>
