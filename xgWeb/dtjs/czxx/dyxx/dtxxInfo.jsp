<%@ page language="java" contentType="text/html; charset=GBK"%>
				<tr>
					<td colspan="4" align="center" width="750px">
						<logic:empty name="tyList">
							���������ż�¼
						</logic:empty>
						<logic:notEmpty name="tyList">
						<fieldset>
							<h3 class="datetitle_01">
				   			 <span>
								�������Ŵ����� <bean:write name="tyNum" />
							</span>
							</h3>
							<table id="rsTable" class="datelist" width="740px">
								<thead>	
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTnty" offset="0">
											<td id="<bean:write name="tit" property="en"/>" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="tyList" id="s" indexId="index">
									<tr>
										<logic:iterate id="v" name="s" offset="0">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
						</logic:notEmpty>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center" width="750px" >
						<logic:empty name="pxList">
							����ѵ��¼
						</logic:empty>
						<logic:notEmpty name="pxList">
						<fieldset>
							<legend>
								��ѵ������ <bean:write name="pxNum" />
							</legend>
							<table id="rsTable" summary="" class="datelist" width="740px">
								<thead>	
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topDkpx" offset="0">
											<td id="<bean:write name="tit" property="en"/>" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="pxList" id="s" indexId="index">
									<tr>
										<logic:iterate id="v" name="s" offset="0">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
						</logic:notEmpty>
					</td>
				</tr>
				
				<tr>
					<td colspan="4" align="center" width="750px" >
						<logic:empty name="hbList">
							��˼��㱨��¼
						</logic:empty>
						<logic:notEmpty name="hbList">
						<fieldset style="width:100%">
							<legend>
								˼��㱨������ <bean:write name="hbNum" />
							</legend>							
							<table id="rsTable" class="dateline" width="740px">
								<thead>	
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topSxhb" offset="0">
											<td id="<bean:write name="tit" property="en"/>" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="hbList" id="s" indexId="index">
									<tr>
										<logic:iterate id="v" name="s" offset="0" length="3">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<td align="left">
											<logic:iterate id="v" name="s" offset="3" length="1">
												<a href="czxxDtjsDyxx.do?method=downLoadFile&dir=<bean:write name="v" />" target="_blank" />
													��������
												</a>
											</logic:iterate>
										</td>
									</tr>
								</logic:iterate>
							</table>						
						</fieldset>
						</logic:notEmpty>
					</td>
				</tr>