<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<html>
	<body>
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：${title }
			</div>
		</div>
		<fieldset>
				<legend>
					学生基本信息
				</legend>
				<table width="100%" class="tbstyle">
					<tr>
						<td align="right">
							学号：
						</td>
						<td>
							${stuIfno.xh }
						</td>
						<td align="right">
							姓名：
						</td>
						<td>
							${stuIfno.xm }
						</td>
					</tr>
					<tr>
						<td align="right">
							年级：
						</td>
						<td>
							${stuIfno.nj }
						</td>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td>
							${stuIfno.xymc }
						</td>
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td>
							${stuIfno.zymc }
						</td>
						<td align="right">
							班级：
						</td>
						<td>
							${stuIfno.bjmc }
						</td>
					</tr>
				</table>
		</fieldset>
			
			
			<logic:notEmpty name="cjffRs">
				<fieldset>
					<legend>
							勤工助学
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
					   <thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="cjffTitle" offset="0" scope="request">
									<td id="${tit.en }"onclick="tableSort(this)" nowrap>
										${tit.cn }
									</td>
								</logic:iterate>
							</tr>
								</thead>
								<logic:iterate name="cjffRs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;">
										<logic:iterate id="v" name="s" offset="0">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
						</table>
				</fieldset>
			</logic:notEmpty>
			
			<logic:notEmpty name="jxjRs">
				<fieldset>
					<legend>
							评奖评优
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
					   <thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="jxjTitle" offset="0" scope="request">
									<td id="${tit.en }"onclick="tableSort(this)" nowrap>
										${tit.cn }
									</td>
								</logic:iterate>
							</tr>
								</thead>
								<logic:iterate name="jxjRs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;">
										<logic:iterate id="v" name="s" offset="0">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
						</table>
				</fieldset>
			</logic:notEmpty>
			
			<logic:notEmpty name="zxjRs">
				<fieldset>
					<legend>
							助学贷款
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
					   <thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="zxjTitle" offset="0" scope="request">
									<td id="${tit.en }"onclick="tableSort(this)" nowrap>
										${tit.cn }
									</td>
								</logic:iterate>
							</tr>
								</thead>
								<logic:iterate name="zxjRs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;">
										<logic:iterate id="v" name="s" offset="0">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
						</table>
				</fieldset>
			</logic:notEmpty>
			
			<logic:notEmpty name="zzjxjRs">
				<fieldset>
					<legend>
							国家奖学金
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
					   <thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="zzjxjTitle" offset="0" scope="request">
									<td id="${tit.en }"onclick="tableSort(this)" nowrap>
										${tit.cn }
									</td>
								</logic:iterate>
							</tr>
								</thead>
								<logic:iterate name="zzjxjRs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;">
										<logic:iterate id="v" name="s" offset="0">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
						</table>
				</fieldset>
			</logic:notEmpty>
			
			
			<logic:notEmpty name="zzzxjRs">
				<fieldset>
					<legend>
							国家助学金
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
					   <thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="zzzxjTitle" offset="0" scope="request">
									<td id="${tit.en }"onclick="tableSort(this)" nowrap>
										${tit.cn }
									</td>
								</logic:iterate>
							</tr>
								</thead>
								<logic:iterate name="zzzxjRs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;">
										<logic:iterate id="v" name="s" offset="0">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
						</table>
				</fieldset>
			</logic:notEmpty>
			
			
			<logic:notEmpty name="wszxjRs">
				<fieldset>
					<legend>
							外设助学金
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
					   <thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="wszxjTitle" offset="0" scope="request">
									<td id="${tit.en }"onclick="tableSort(this)" nowrap>
										${tit.cn }
									</td>
								</logic:iterate>
							</tr>
								</thead>
								<logic:iterate name="wszxjRs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;">
										<logic:iterate id="v" name="s" offset="0">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
						</table>
				</fieldset>
			</logic:notEmpty>
			
			<logic:notEmpty name="knbzRs">
				<fieldset>
					<legend>
							困难生补助
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
					   <thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="knbzTitle" offset="0" scope="request">
									<td id="${tit.en }"onclick="tableSort(this)" nowrap>
										${tit.cn }
									</td>
								</logic:iterate>
							</tr>
								</thead>
								<logic:iterate name="knbzRs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;">
										<logic:iterate id="v" name="s" offset="0">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
						</table>
				</fieldset>
			</logic:notEmpty>
			
				<logic:notEmpty name="knbzRs">
				<fieldset>
					<legend>
							国家励志奖学金
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
					   <thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="gjlzTitle" offset="0" scope="request">
									<td id="${tit.en }"onclick="tableSort(this)" nowrap>
										${tit.cn }
									</td>
								</logic:iterate>
							</tr>
								</thead>
								<logic:iterate name="gjlzRs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;">
										<logic:iterate id="v" name="s" offset="0">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
						</table>
				</fieldset>
			</logic:notEmpty>
	</body>
</html>