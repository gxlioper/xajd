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
				<em>���ĵ�ǰλ��:</em><a><bean:write name="title" />
				</a>
			</p>
		</div>

		<html:form action="/pjpyxfjs" method="post">


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�༶ѧ����</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" id="buttonClose" onclick="window.close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								ѧ��
							</th>
							<td align="left">
								${model.xn}
								<input type="hidden" value="${model.xn}" name="xn" />
							</td>
							<th>
								�������
							</th>
							<td align="left">
								${model.ccrq}
								<input type="hidden" value="${model.ccrq}" name="ccrq" />
							</td>
						</tr>
						<tr>
							<th>
								ѧ��
							</th>
							<td align="left">
								${model.xqmc}
								<input type="hidden" value="${model.xq}" name="xq" />
							</td>
							<th>
								�������
							</th>
							<td align="left">
								${model.jclxmc}
								<input type="hidden" value="${model.jclxdm}" name="jclxdm" />
							</td>
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td align="left">
								${model.bjmc}
								<input type="hidden" value="${model.bjdm}" name="bjdm" />
							</td>
							<th>
								Ӧ������
							</th>
							<td align="left">
								${model.ydrs}
							</td>
						</tr>
						<tr>
							<th>
								ʵ������
							</th>
							<td align="left">
								${model.sdrs}
							</td>
							<th>
								ȱ������
							</th>
							<td align="left">
								${model.qqrs}
							</td>
						</tr>
						<tr>
							<th>
								��ȱ�����Υ������
								<div align="center">
									(��:�Է�,˯����...)
								</div>
							</th>
							<td align="left">
								${model.wjrs}
							</td>
							<th>
								�涨����ʱ��
							</th>
							<td align="left">
								${model.fdyclsj}
							</td>
						</tr>
						<tr>
							<th>
								����Ա����ʱ��
							</th>
							<td align="left">
								${model.fdysjclsj}
							</td>
							<th>
								ѧУ���
							</th>
							<td align="left">
								${model.xxsh}
							</td>
						</tr>
						<tr>
							<th>
								ѧУ��ע
							</th>
							<td align="left" colspan="3" style="word-break:break-all;">
								${model.bz}
							</td>
						</tr>
						<tr>
							<th>
								����Ա��ע
							</th>
							<td align="left" colspan="3" style="word-break:break-all;">
								${model.fdyclbz}
							</td>
						</tr>
						<logic:notEmpty name="xsList">
							<tr>
								<td colspan="4">
									<table class="tbstyle" align="center" width="100%" id="tTb">
										<tr>
											<td>
												<div class="mid_box">
													<table align="center" style="width:100%" id="rsT"
														class="dateline">
														<thead>
															<tr>
																<td nowrap="nowrap">
																	ѧ��
																</td>
																<td nowrap="nowrap">
																	����
																</td>
																<td nowrap="nowrap">
																	Υ��
																</td>
																<td nowrap="nowrap">
																	���ν���
																</td>
																<td nowrap="nowrap">
																	���
																</td>
																<td nowrap="nowrap">
																	��ע
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
	</body>
</html>
