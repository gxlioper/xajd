<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
						<tr>
							<td>
								<div align="center">
									��
									<br />
									&nbsp;
									<br />
									��
									<br />
									&nbsp;
									<br />
									��
									<br />
									&nbsp;
									<br />
									��
								</div>
							</td>
							<td colspan="5">
								<table width="100%">
									<!-- ���շ���Ϣ -->
									<logic:empty name="sfList">
										<tr>
											<td align="center" colspan="5">
												���շ���Ϣ
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
									<!-- ���շ���Ϣ -->
									<logic:notEmpty name="sfList">
										<tr>
											<td>
												�շ���Ŀ
											</td>
											<td>
												���ñ�׼
											</td>
											<td>
												������
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

