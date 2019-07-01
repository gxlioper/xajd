<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
		function saveAuditing(){
			var xxdm = document.getElementById('xxdm').value;
			if(xxdm == "10491"){
				//中国地质大学
				refreshForm('qgzxZgdzdx.do?method=saveWorkPayAudit')
			}else{
				refreshForm('whlggwgl.do?method=saveAudit')
			}
		}
	</script>
</head>
<body onload="checkWinType();">
		<html:form action="/qgzxZgdzdx.do" method="post">
			<input type="hidden" id="mes" name="mes" value="${mes}" />	
			<input type="hidden" id="gwdm" name="gwdm" value="<bean:write name="rs" property="gwdm"/>" />				
			<input type="hidden" id="gwsbsj" name="gwsbsj" value="<bean:write name="rs" property="gwsbsj"/>" />
			<input type="hidden" id="xn" name="xn" value="<bean:write name="rs" property="xn"/>" />
			<input type="hidden" id="nd" name="nd" value="<bean:write name="rs" property="nd"/>" />
			<input type="hidden" id="xq" name="xq" value="<bean:write name="rs" property="xq"/>" />
			<input type="hidden" id="yf" name="yf" value="<bean:write name="rs" property="yf"/>" />
			<input type="hidden" id="pkValue" name="pkValue" value="<bean:write name="rs" property="gwdm"/><bean:write name="rs" property="gwsbsj"/>" />
			<input type="hidden" id="jybcbz" name="jybcbz" value="<bean:write name="rs" property="jybcbz"/>" />
			<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
			<input type="hidden" id="myzgbc" name="myzgbc" value="${map.myzgbc}" />
			<input type="hidden" id="count" name="count" value="${count}" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>勤工助学 - 酬金发放 - 酬金发放审核 - 审核</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" class="formlist" id="rsT">
					<thead>
						<tr>
							<th colspan="2">
								学年:
								${rs.xn}
								&nbsp;&nbsp;&nbsp; 年度:
								${rs.nd}
								&nbsp;&nbsp;&nbsp; 学期:
								${rs.xq}
								&nbsp;&nbsp;&nbsp; 月份:
								${rs.yf}
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th>用人单位</th>
						<td>
							<bean:write name="rs" property="yrdwmc" />
						</td>
					</tr>
					<tr>
						<th>所属部门</th>
						<td>
							<bean:write name="rs" property="xymc" />
						</td>
					</tr>
					<tr>
						<th>工作内容</th>
						<td>
							<bean:write name="rs" property="gznr" />
						</td>
					</tr>
					<tr>
						<th>岗位性质</th>
						<td>
							<bean:write name="rs" property="gwxzmc" />
						</td>
					</tr>
					<tr>
						<th>计酬标准</th>
						<td>
							<bean:write name="rs" property="spbcbz" />
							<input type="hidden" id="jybcbz"
								value="<bean:write name="rs" property="spbcbz" />" />
							<bean:write name="rs" property="jcfs" />
						</td>
					</tr>
					<tr>
						<th>
							工
							<br />
							作
							<br />
							人
							<br />
							员
							<br />
							情
							<br />
							况
						</th>
						<td valign="top">
							<table width="100%" class="formlist" >
								<thead>
									<tr>
										<th>学号</th>
										<th>姓名</th>
										<th>班级</th>
										<th>年度</th>
										<th>月份</th>
										<th>工作时间(单位:
												<bean:write name="rs" property="gzsjdw" />
												)
										</th>
										<th>金额</th>
										<th>备注</th>
										<th>发放类型</th>
										<th>学校审核</th>
									</tr>
								</thead>
								<tbody>
								<logic:empty name="stuList">
									<tr>
										<td colspan="10" align="center">
											无记录!
										</td>
									</tr>
								</logic:empty>
								<logic:notEmpty name="stuList">
									<logic:iterate name="stuList" id="stuList" indexId="index">
										<tr>
											<td>
												<div align="center">
													<input type="text" name="xh${index}"
														value="<bean:write name="stuList" property="xh"/>"
														style="width:95%" readonly />
												</div>
											</td>
											<td>
												<div align="center">
													<bean:write name="stuList" property="xm" />
												</div>
											</td>
											<td>
												<div align="center">
													<bean:write name="stuList" property="bjmc" />
												</div>
											</td>
											<td>
												<div align="center">
													<input type="hidden" id="nd${index}" name="nd${index}"
														value="<bean:write name="stuList" property="nd"/>"/>
													<bean:write name="stuList" property="nd"/>
												</div>
											</td>
											<td>
												<div align="center">
													<input type="hidden" id="yf${index}" name="yf${index}"
														value="<bean:write name="stuList" property="yf"/>"/>
													<bean:write name="stuList" property="yf"/>
												</div>
											</td>
											<td>
												<div align="center">
													<input type="hidden" id="gzsj${index}" name="gzsj${index}"
														value="<bean:write name="stuList" property="gzsj"/>"/>
													<bean:write name="stuList" property="gzsj"/>
												</div>
											</td>											
											<td>
												<div align="center">
													<input type="text" id="cjje${index}" name="cjje${index}"
														value="<bean:write name="stuList" property="cjje"/>" style="width:100%"/>
												</div>
											</td>
											<td>
												<div align="center">
													<input type="hidden" name="bz${index}"
														value="<bean:write name="stuList" property="bz"/>"/>
													<bean:write name="stuList" property="bz"/>
												</div>
											</td>
											<td>
												<div align="center">
													<bean:write name="stuList" property="fflx" />
												</div>
											</td>
											<td>
												<div align="center">
													<input name="xxsh${index}" type="checkbox" 
													value="1" <bean:write name="stuList" property="xxsh" />>
												</div>
											</td>
										</tr>
									</logic:iterate>
								</logic:notEmpty>
								</tbody>
							</table>
						</td>
					</tr>
				  </tbody>
				  <tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			            <button type="button" class="button2"
							onclick="saveAuditing()"
							style="width:80px" id="buttonSave">
							保 存
						</button>
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
				</table>
			</div>
			<logic:present name="result">
				<logic:equal name="result" value="true">
					<logic:notEmpty name="mes">
						<script>
				    	alert(document.getElementById("mes").value);
				    </script>
					</logic:notEmpty>
					<logic:empty name="mes">
						<script>
					    alert("操作成功！");
					    Close();
					    window.dialogArguments.document.getElementById('search_go').click();
					    </script>
					</logic:empty>
				</logic:equal>
				<logic:equal name="result" value="false">
					<logic:notEmpty name="mes">
						<script>
				    	alert(document.getElementById("mes").value);
				    </script>
					</logic:notEmpty>
					<logic:empty name="mes">
					<script>
				    	alert("操作失败！");
				    </script>
					</logic:empty>					
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>

