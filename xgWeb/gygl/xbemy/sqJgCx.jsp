<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/wjcfFuction.js"></script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ���� - ��������ѯ</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/data_search" method="post">
			<input type="hidden" id="xh" name="xh"
				value="<bean:write name="userName" scope="session"/>" />
			<logic:present name="isNotStu">
				<p>
				<p>
				<p>
				<p>
				<p>
				<p>
				<div align="center">
					<font color="red">��ҳ��ֻ��ѧ���û����Է���!</font>
				</div>
			</logic:present>
			<logic:notPresent name="isNotStu">
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<td align="left">
								<span>
								ѧ�ţ�
								<bean:write name="userName" scope="session" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								������
								<bean:write name="userNameReal" scope="session" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								�����������
								<bean:write name="numCout" scope="request" />
								</span>
							</td>
						</tr>
					</thead>
				</table>
				<!-- ��ѯ���-->
				<logic:equal value="0" name="numCout">
							<br />
							<br />
							<p align="center">
								δ�ҵ��κμ�¼��
							</p>
						</logic:equal>
						<logic:notEqual value="0" name="numCout">
							<%--							<div style="overflow-x:auto;overflow-y:auto;width:800px;">--%>
							<logic:notEmpty name="rsWx">
							<div class="formbox">
								<h3 class="datetitle_01">
									<span> ��Ԣά������&nbsp;&nbsp; </span>
								</h3>
									<table width="99%" id="rsTable" class="dateline">
										<thead>
											<tr align="center" style="">
												<logic:iterate id="tit" name="topTrWx" offset="1">
													<td id="<bean:write name="tit" property="en"/>" nowrap>
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
											</tr>
										</thead>
										<logic:iterate name="rsWx" id="s">
											<tr
												style="background-color:
                                        <logic:iterate id="v" name="s" offset="0" length="1">
                                        <bean:write name="v"/>
                                        </logic:iterate>
                                        ">
												<logic:iterate id="v" name="s" offset="1">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</table>
								</div>
							</logic:notEmpty>
							<logic:notEmpty name="rsYd">
							<div class="formbox">
								<h3 class="datetitle_01">
									<span> �����춯����&nbsp;&nbsp; </span>
								</h3>
									<table width="99%" id="rsTable" class="dateline">
										<thead>
											<tr align="center" style="">
												<logic:iterate id="tit" name="topTrYd" offset="1">
													<td id="<bean:write name="tit" property="en"/>" nowrap>
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
											</tr>
										</thead>
										<logic:iterate name="rsYd" id="s">
											<tr
												style="background-color:
                                        <logic:iterate id="v" name="s" offset="0" length="1">
                                        <bean:write name="v"/>
                                        </logic:iterate>
                                        ">
												<logic:iterate id="v" name="s" offset="1">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</table>
								</div>
							</logic:notEmpty>
							<logic:notEmpty name="rsLx">
								<div class="formbox">
									<h3 class="datetitle_01">
										<span> ������У����&nbsp;&nbsp; </span>
									</h3>									
									<table width="99%" id="rsTable" class="dateline">
										<thead>
											<tr align="center" style="">
												<logic:iterate id="tit" name="topTrLx" offset="1">
													<td id="<bean:write name="tit" property="en"/>" nowrap>
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
											</tr>
										</thead>
										<logic:iterate name="rsLx" id="s">
											<tr
												style="background-color:
                                        <logic:iterate id="v" name="s" offset="0" length="1">
                                        <bean:write name="v"/>
                                        </logic:iterate>
                                        ">
												<logic:iterate id="v" name="s" offset="1">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
								</table>
								</div>
							</logic:notEmpty>
							<logic:notEmpty name="rsWz">
								<div class="formbox">
									<h3 class="datetitle_01">
										<span> ѧ���߶�(��ס)����&nbsp;&nbsp; </span>
									</h3>										
									<table width="99%" id="rsTable" class="dateline">
										<thead>
											<tr align="center" style="">
												<logic:iterate id="tit" name="topTrWz" offset="1">
													<td id="<bean:write name="tit" property="en"/>" nowrap>
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
											</tr>
										</thead>
										<logic:iterate name="rsWz" id="s">
											<tr
												style="background-color:
                                        <logic:iterate id="v" name="s" offset="0" length="1">
                                        <bean:write name="v"/>
                                        </logic:iterate>
                                        ">
												<logic:iterate id="v" name="s" offset="1">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</table>
								</div>
							</logic:notEmpty>
							<logic:notEmpty name="rsJswmhd">
								<div class="formbox">
									<h3 class="datetitle_01">
										<span> ѧ���������������&nbsp;&nbsp; </span>
									</h3>									
									<table width="99%" id="rsTable" class="dateline">
										<thead>
											<tr align="center" style="">
												<logic:iterate id="tit" name="topTrJswmhd" offset="1">
													<td id="<bean:write name="tit" property="en"/>" nowrap>
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
											</tr>
										</thead>
										<logic:iterate name="rsJswmhd" id="s">
											<tr
												style="background-color:
                                        <logic:iterate id="v" name="s" offset="0" length="1">
                                        <bean:write name="v"/>
                                        </logic:iterate>
                                        ">
												<logic:iterate id="v" name="s" offset="1">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</table>
								</div>
							</logic:notEmpty>
							<%--							</div>--%>
						</logic:notEqual>
				<!-- ��ѯ��� end-->
			</logic:notPresent>
		</html:form>
	</body>
</html>
