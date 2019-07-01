<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
						<tr>
							<td>
								<div align="center">
									收
									<br />
									&nbsp;
									<br />
									费
									<br />
									&nbsp;
									<br />
									情
									<br />
									&nbsp;
									<br />
									况
								</div>
							</td>
							<td colspan="5">
								<table width="100%">
									<!-- 无收费信息 -->
									<logic:empty name="sfList">
										<tr>
											<td align="center" colspan="5">
												无收费信息
												<br />
												<br />
												<br />
												<br />
												<br />
												<br />
												<br />
											</td>
										</tr>
									</logic:empty>
									<!-- 有收费信息 -->
									<logic:notEmpty name="sfList">
										<tr>
											<td>
												收费项目
											</td>
											<td>
												费用标准
											</td>
											<td>
												减免金额
											</td>
										</tr>
										<logic:iterate name="sfList" id="sf" indexId="num">
											<tr>
												<td>
													${sf.xfsfxm }&nbsp;&nbsp;&nbsp;&nbsp;
												</td>
												<td>
													${sf.xfyjje }&nbsp;&nbsp;&nbsp;&nbsp;
												</td>
												<td>
													${sf.xfjmje }&nbsp;&nbsp;&nbsp;&nbsp;
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
							</td>
						</tr>

