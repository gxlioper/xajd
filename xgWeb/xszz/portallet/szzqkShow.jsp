<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<html>
	<body>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã�${title }
			</div>
		</div>
		<fieldset>
				<legend>
					ѧ��������Ϣ
				</legend>
				<table width="100%" class="tbstyle">
					<tr>
						<td align="right">
							ѧ�ţ�
						</td>
						<td>
							${stuIfno.xh }
						</td>
						<td align="right">
							������
						</td>
						<td>
							${stuIfno.xm }
						</td>
					</tr>
					<tr>
						<td align="right">
							�꼶��
						</td>
						<td>
							${stuIfno.nj }
						</td>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td>
							${stuIfno.xymc }
						</td>
					</tr>
					<tr>
						<td align="right">
							רҵ��
						</td>
						<td>
							${stuIfno.zymc }
						</td>
						<td align="right">
							�༶��
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
							�ڹ���ѧ
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
							��������
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
							��ѧ����
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
							���ҽ�ѧ��
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
							������ѧ��
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
							������ѧ��
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
							����������
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
							������־��ѧ��
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