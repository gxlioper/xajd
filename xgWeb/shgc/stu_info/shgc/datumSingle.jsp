<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- ͷ�ļ� -->
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function save(){
			
			var len=jQuery("input[type=checkbox]:checked").length;
	
			if(len==0){
				alertInfo("�빴ѡ�鵵����!",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}else{
				refreshForm('stu_info_add.do?method=datumCommitSignle&doType=save');
				BatAlert.showTips('����ִ�в�������ȴ�...');
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
					<input type="hidden" name="message" id="message" value="�����ɹ�"/>
				</logic:equal>
				<logic:equal value="false" name="result">
					<input type="hidden" name="message" id="message" value="����ʧ��"/>
				</logic:equal>
			</logic:present>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - �鵵���� - �鵵�����ύ</a>
				</p>
			</div>
			<div class="tab"
				style="width:100%;height:550px;overflow-x:hidden;overflow-y:hidden;">
				<table width="100%" border="0" class="formlist" id="rsT">
					<thead>
						<tr>
							<th colspan="2">
								<span>����ѧ���ύ�鵵����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="79%">
								${rs.xh}
								<input type="hidden" name="xh" id="xh" value="${rs.xh}" />
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td>
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
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
								רҵ����
							</th>
							<td>
								${rs.zymc}
							</td>
						</tr>
						<tr>
							<th>
								�༶����
							</th>
							<td>
								${rs.bjmc}
							</td>
						</tr>
						<logic:equal name="xxdm" value="11600">
							<tr>
								<th>
									�Ƿ��ύ
								</th>
								<td>
									<html:select name="rs" property="sftj" styleId="sftj"
										value="${rs.sftj}">
										<html:option value=""></html:option>
										<html:option value="���ύ">���ύ</html:option>
										<html:option value="δ�ύ">δ�ύ</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									������У���
								</th>
								<td>
									<html:select name="rs" property="dazxqk" property="dazxqk"
										value="${rs.dazxqk}">
										<html:option value="">δ����</html:option>
										<html:option value="��У">��У</html:option>
										<html:option value="����У">����У</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									��ע
									<br />
									<font color="red">(��������250)</font>
								</th>
								<td>
									<textarea name='bz' id="bz"
										style="word-break:break-all;width:80%"
										onblur="chLeng(this,250);" rows='3' type="_moz">${rs.bz}</textarea>
								</td>
							</tr>
						</logic:equal>

						<%--��������Ժ--%>
						<logic:equal value="11407" name="xxdm">
							<tr>
								<th>
									�������
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
								<span>�鵵�����б�</span>
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
												<%--��������Ժ--%>
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

												<%--��������Ժ--%>
												<logic:equal value="11407" name="xxdm">
													<th>
														��������
													</th>
													<td>
														<input type="text" id="zlzs${index}" name="zlzs${index}"
															style="width:80px" />
														��
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
											�� ��
										</button>
									</logic:notEqual>
									<button onclick="Close();return false;" style="width:80px">
										�� ��
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

