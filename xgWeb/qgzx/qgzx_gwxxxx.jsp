<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xgutil.js"></script>
</head>
<body onload="stringFormat(['bz','sqdwyj','sqbg','ryyq','gznr'],40)">		
	<html:form action="/qgzxLogic" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em>
				<a>
					<%--��ɳ����--%>
					<logic:equal value="10827" name="xxdm">
						ѧ���幤 - ��λ���� - ��λ��ϸ��Ϣ
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						�ڹ���ѧ - ��λ���� - ��λ��ϸ��Ϣ
					</logic:notEqual>
				</a>
			</p>
		</div>
		
		<logic:empty name="rs">
			<br />
			<br />
			<p align="center">
				�д�������
			</p>
		</logic:empty>
		<logic:notEmpty name="rs">
			<div class="tab">
		  	<table width="100%" border="0" class="formlist">
				<thead>
					<tr align="center">
						<th colspan="4">
							<span>��λ��Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>У��</th>
					<td>
						${rs.xqmc}
					</td>
					<th>��λ����</th>
					<td>
						${rs.gwdm}
					</td>
				</tr>
				<tr>
					<th>��λ����</th>
					<td>
						${rs.gwxzmc}
					</td>
					<th>���˵�λ</th>
					<td>
						${rs.yrdwmc}
					</td>
				</tr>
				<tr>
					<th>ѧ��</th>
					<td>
						${rs.xn}
					</td>
					<th>���</th>
					<td>
						${rs.nd}
					</td>

				</tr>
				<tr>
					<th>������ʼ����</th>
					<td>
						${rs.gzksrq}
					</td>
					<th>������������</th>
					<td>
						${rs.gzjsrq}
					</td>
				</tr>
				<!-- �㽭��ҵ��ѧ֮��ѧԺ -->

					<tr>
						<th>
							���뿪ʼʱ��
						</th>
						<td>
							${rs.sqkssj }
						</td>
						<th>
							�������ʱ��
						</th>
						<td>
							${rs.sqjssj }
						</td>
					</tr>

				<tr>
					<th>��������</th>
					<td>
						<!-------------����Ƽ�ѧԺ----------------->
						<logic:equal value="11551" name="xxdm">								
							${rs.sqsyrs}
						</logic:equal>
						<logic:notEqual value="11551" name="xxdm">
							${rs.xyrs}
						</logic:notEqual>
					</td>
					<th>ʹ����������</th>
					<td height="22" align="left">
						<!-------------����Ƽ�ѧԺ----------------->
						<logic:equal value="11551" name="xxdm">
							${rs.sqsyknss}
						</logic:equal>
						<logic:notEqual value="11551" name="xxdm">
							<!-- ��ɳ���� -->
							<logic:equal value="10827" name="xxdm">
								${xyknsrs}
							</logic:equal>
							<logic:notEqual value="10827" name="xxdm">
								${rs.syknss}
							</logic:notEqual>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th>�Ƴ귽ʽ</th>
					<td>
						<logic:notEqual value="11417" name="xxdm">
							${rs.jcfsmc}
						</logic:notEqual>
						<logic:equal value="11417" name="xxdm">								
							��Сʱ
						</logic:equal>
					</td>
					<logic:notEqual value="11417" name="xxdm">
						<th>���鱨���׼</th>
						<td>
							<!-- ����Ƽ�ѧԺ -->
							<logic:equal value="11551" name="xxdm">
									${rs.spbcbz}
							</logic:equal>
							<logic:notEqual value="11551" name="xxdm">
								${rs.jybcbz}
							</logic:notEqual>
							Ԫ
						</td>
					</logic:notEqual>
					<logic:equal value="11417" name="xxdm">
						<th>�����׼</th>
						<td>
							${mxsbc}Ԫ/Сʱ
						</td>
					</logic:equal>
				</tr>
				<%--������Ƽ�ѧԺ--%>
				<logic:notEqual value="11551" name="xxdm">
					<%--���㽭����ְҵ����ѧԺ--%>
					<logic:notEqual value="12861" name="xxdm">
						<tr>
							<th>����ʱ��</th>
							<td>
								${rs.gzsj}
							</td>
							<%--�Ǳ������ϴ�ѧ--%>
							<logic:notEqual value="11417" name="xxdm">
								<th>��ϵ�绰</th>
								<td >
									${rs.lxdh}
								</td>
							</logic:notEqual>
							<%--�������ϴ�ѧ--%>
							<logic:equal value="11417" name="xxdm">
								<th>��ϵ��</th>
								<td>
									${rs.lxr}
								</td>
							</logic:equal>
						</tr>
					</logic:notEqual>

					<!--�й����ʴ�ѧ-->
					<logic:equal value="10491" name="xxdm">
						<tr>
							<th>������</th>
							<td colspan="3">
								${rs.fzr}
							</td>
						</tr>
					</logic:equal>
					<!--end�й����ʴ�ѧ-->

					<%--�㽭����ְҵ����ѧԺ--%>
					<logic:equal value="12861" name="xxdm">
						<tr>
							<th>��ϵ�绰</th>
							<td colspan="3">
								${rs.lxdh}
							</td>
						</tr>
						<tr>
							<th>����ʱ��</th>
							<tdcolspan="3">
								${rs.gzsj}	
							</td>
						</tr>
					</logic:equal>
					<%--�й����ʴ�ѧ--%>
					<logic:equal value="10491" name="xxdm">
						<tr>
							<th>�����ص�</th>
							<td colspan="3">
								${rs.gzdd}
							</td>
						</tr>
					</logic:equal>
					<!-- ����ɽ��ѧ -->
					<logic:equal value="10419" name="xxdm">
						<tr>
							<th>�����ص�</th>
							<td colspan="3">
								${rs.gzdd}
							</td>
						</tr>
						<tr>
							<th>ѧ�������������</th>
							<td colspan="3">
								<logic:iterate id="jgsrs" name="jgsshList" indexId="index">
									<input type="checkbox" value=""
										id="<bean:write name="jgsrs"  property="key"/>"
										name="<bean:write name="jgsrs" property="key"/>" />
									<bean:write name="jgsrs" property="columnName" />
								</logic:iterate>
							</td>
						</tr>
					</logic:equal>
					<!-- ����ɽ��ѧ (�Ƿ�����ʾ��ϸ����) -->
					<logic:present name="jgsTempSQL">
						<input type="hidden" id="jgsTempSQLNum" value="${jgsTempSQLNum}" />
						<logic:iterate id="jgsSql" name="jgsTempSQL" indexId="index">
							<input type="hidden" id="tmpkey${index}"
								value="<bean:write name="jgsSql"/>" />
						</logic:iterate>
						<script>
						var length = document.getElementById('jgsTempSQLNum').value;
						for(var i=0; i<parseInt(length); i++){
							var id =  document.getElementById("tmpkey"+i).value;
							if(id == "1"){
								document.getElementById("key" + i).checked="checked";
							}
							
						}
					</script>
					</logic:present>
					<%--�㽭��ѧ������ѧԺ--%>
					<logic:equal value="13022" name="xxdm">
						<tr>
							<th>ÿ���ڹ���ѧʱ��</th>
							<td>
								${rs.myqgzxsj}
							</td>
							<th>������</th>
							<td>
								${rs.fzr}
							</td>
						</tr>
						<tr>
							<th>����Ҫ��</th>
							<td>
								${rs.tsyq}
							</td>
							<th>�Ƿ���ѧ�����ڹ���ѧ���Ĵ�Ϊ��Ƹ</th>
							<td>
								${rs.dwzp}
							</td>
						</tr>
						<tr>
							<th>��Ƹ����ʱ��</th>
							<td colspan="3">
								${rs.mssj}
							</td>
						</tr>
						<tr>
							<th>��Ա��ʵ���</th>
							<td colspan="3">
								${rs.rqlsqk}
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th>��������</th>
						<td colspan="3" id="gznr">
							${rs.gznr}
						</td>
					</tr>
				</logic:notEqual>

				<logic:equal value="11551" name="xxdm">
					<tr>
						<th>��ϵ��</th>
						<td>
							${rs.lxr}
						</td>
						<th>��ϵ�绰</th>
						<td>
							${rs.lxdh}
						</td>
					</tr>
					<tr>
						<th>�Գﾭ��</th>
						<td>
							${rs.zcjf}
						</td>
						<th></th>
						<td>
						</td>
					</tr>

					<tr>
						<th>����ʱ��</th>
							<td colspan="3">
								<table id="tbSj" class="formlist">
									<tbody>									
									<tr>
										<td align="center">
											ʱ��
										</td>
										<td>
											����һ
										</td>
										<td>
											���ڶ�
										</td>
										<td>
											������
										</td>
										<td>
											������
										</td>
										<td>
											������
										</td>
										<td>
											������
										</td>
										<td>
											������
										</td>
									</tr>
									<logic:iterate id="kxsj" name="whkxList">
										<tr>
											<td>
												${kxsj.sj}
											</td>
											<td>
												<input type="checkbox" name="${kxsj.sjIndex}1" value="1" id="${kxsj.sjIndex}1"/>
											</td>
											<td>
												<input type="checkbox" name="${kxsj.sjIndex}2" value="1" id="${kxsj.sjIndex}2"/>
											</td>
											<td>
												<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
											</td>
											<td>
												<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
											</td>
											<td>
												<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
											</td>
											<td>
												<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
											</td>
											<td>
												<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
											</td>
										</tr>
									</logic:iterate>
								</table>
							</td>
							<!-- begin ��ȡ�Ѿ����ڵĿ���ʱ����Ϣ -->
							<logic:present name="kxbz">
								<input type="hidden" id="kxbzNum" value="<bean:write name="kxbzNum"/>" />
								<logic:iterate id="kxinfo" name="kxbz" indexId="index">
									<input type="hidden" id="kxinfo${index}" value="${kxinfo.xq}${kxinfo.sj}" />
								</logic:iterate>
								<script>
									var length = document.getElementById('kxbzNum').value;
									for(var i=0; i<parseInt(length); i++){
										var id =  document.getElementById("kxinfo"+i).value;
										document.getElementById(id).checked="checked";
									}
								</script>
							</logic:present>
							<!-- end ��ȡ�Ѿ����ڵĿ���ʱ����Ϣ -->
					</tr>
				</logic:equal>
				<tr>
					<th>��ԱҪ��</th>
					<td colspan="3" id="ryyq">
						${rs.ryyq}
					</td>
				</tr>
				<logic:notPresent name="showshgc">
					<%--����Ƽ�ѧԺ--%>
					<logic:notEqual value="11551" name="xxdm">
						<%--�㽭����ְҵ����ѧԺ--%>
						<logic:notEqual value="12861" name="xxdm">
							<tr>
								<th>���뱨��</th>
								<td colspan="3" id="sqbg">
									${rs.sqbg}
								</td>
							</tr>
						</logic:notEqual>
					</logic:notEqual>
				</logic:notPresent>
				<%--����Ƽ�ѧԺ--%>
				<logic:equal value="11551" name="xxdm">
					<tr>
						<th>����ԭ�򼰵�λ���</th>
						<td colspan="3"/>
							${rs.sqdwyj}
						</td>
					</tr>
				</logic:equal>
				<%--end����Ƽ�ѧԺ--%>
				<!--������Ƽ�ѧԺ-->
				<logic:notEqual value="11551" name="xxdm">
					<!--�ǻ�����ѧԺ-->
					<logic:notEqual value="11049" name="xxdm">
						<tr>
							<th>���뵥λ���</th>
							<td colspan="3" id="sqdwyj">
								${rs.sqdwyj}
							</td>
						</tr>
					</logic:notEqual>
					<!--end�ǻ�����ѧԺ-->	
				</logic:notEqual>
				<!--end������Ƽ�ѧԺ-->
				<tr>
					<th>��ע</th>
					<td colspan="3" id="bz">
						${rs.bz}
					</td>
				</tr>
				</tbody>
				<!--��������Ժ�Զ����ֶ���Ϣ-->
				<logic:notEmpty name="filedList">				
				<thead>
				<tr>
					<th style="cursor:hand" colspan="4">
						<span>���븽����Ϣ</span>
					</th>
				</tr>
				</thead>
				<tbody>
				<input type="hidden" id="rsNum" name="rsNum" value="<bean:write name="rsNum"/>"/>
				<logic:iterate id="filed" name="filedList" indexId="index">
					<tr>
						<td>								
							<bean:write name="filed" property="zdmc"/>��
						</td>
						<td colspan="3">
							<bean:write name="rs" property="${filed.zddm}"/>
						</td>							
					</tr>
				</logic:iterate>
				
				</tbody>
				</logic:notEmpty>
				<!--end��������Ժ�Զ����ֶ���Ϣ-->

				<!--��ʾ�μӸ�λ��ѧ���б�-->
				<%@ include file="/qgzx/gwxsxx.jsp"%>			
			</table>
		</logic:notEmpty>
	</html:form>
</body>
</html>
