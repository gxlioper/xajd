<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		function save(){
			if(filedNotNull(['spbcbz','sqsyrs','sqsyknss'])){
				//�ж��������Ƿ���ڱ�׼	
				if (Math.round(parseFloat(val('sqsyrs'))*parseFloat(val('knsbl'))/100)>parseFloat(val('sqsyknss'))) {
					alert("ʹ�������������ڱ�׼��");
					return false;
				}
				if(parseInt(val('sqsyrs'))<parseInt(val('sqsyknss'))){
					alert("ʹ����������������Ҫ������");
					return false;
				}	
				refreshForm('/xgxt/postChkOne.do?act=save');
			}else{
				alert('�뽫��*�ŵ���Ŀ��д������');
				return false;
			}
		}
	</script>
</head>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<html:form action="/data_search" method="post">
			<input type="hidden" name="pkVal" value="<bean:write name="rs" property="gwdm||gwsbsj"/>" />
			<input type="hidden" name="xhStr" value="<bean:write name="xhStr" />" />
			<input type="hidden" name="xxyjStr" value="" />
			<input type="hidden" name="knsbl" id="knsbl" value="${knsbl}" />
			<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em>
				<a>
					<%--��ɳ����--%>
					<logic:equal value="10827" name="xxdm">
						ѧ���幤 - ��� - ��λ��� - �������
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						�ڹ���ѧ - ��� - ��λ��� - �������
					</logic:notEqual>
				</a>
			</p>
		</div>
		<div class="tab">
		  <table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>������λ���</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>У��</th>
					<td>
						<bean:write name="rs" property="xqmc" />
					</td>
					<th>���</th>
					<td>
						<bean:write name="rs" property="nd" />
					</td>
				</tr>
				<tr>
					<th>��λ����</th>
					<td>
						<bean:write name="rs" property="gwdm" />
					</td>
					<th>ѧ��</th>
					<td>
						<bean:write name="rs" property="xn" />
					</td>
				</tr>
				<tr>
					<th>���˵�λ</th>
					<td>
						<bean:write name="rs" property="yrdwmc" />
					</td>
					<th>ѧ��</th>
					<td>
						<bean:write name="rs" property="xueqimc" />
					</td>
				</tr>
				<tr>
					<th>�걨ʱ��</th>
					<td>
						<bean:write name="rs" property="gwsbsj" />
					</td>
					<th>��λ����</th>
					<td>
						<bean:write name="rs" property="gwxzmc" />
					</td>
				</tr>
				<tr>
					<th>������ʼʱ��</th>
					<td>
						<bean:write name="rs" property="gzksrq" />
					</td>
					<th>�Ƴ��׼</th>
					<td>
						<bean:write name="rs" property="jybcbz" />Ԫ
						(<bean:write name="rs" property="jcfsmc" />)
					</td>
				</tr>
				<tr>
					<th>��������ʱ��</th>
					<td>
						<bean:write name="rs" property="gzjsrq" />
					</td>
					<th>��Ҫ����</th>
					<td>
						<bean:write name="rs" property="xyrs" />
					</td>
				</tr>
				<!-- �㽭��ҵ��ѧ֮��ѧԺ -->
				<logic:equal value="13275" name="xxdm">
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
				</logic:equal>
				<tr>
					<th>ʹ����������</th>
					<td>
						<bean:write name="rs" property="syknss" />
					</td>
					<th>����ʱ��</th>
					<td>
						<bean:write name="rs" property="gzsj" />
					</td>
				</tr>
				<!-- ����Ƽ�ѧԺ -->
				<logic:equal value="11551" name="xxdm">
					<tr>
						<th>����ʱ��</th>
						<td colspan="3">
							<logic:present name="whkxList">
								<table id="tbSj" class="formlist">
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
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
											</td>
										</tr>
									</logic:iterate>
								</table>
								<!-- begin ��ȡ�Ѿ����ڵĿ���ʱ����Ϣ -->
								<logic:present name="kxbz">
									<input type="hidden" id="kxbzNum"
										value="<bean:write name="kxbzNum"/>" />
									<logic:iterate id="kxinfo" name="kxbz" indexId="index">
										<input type="hidden" id="kxinfo${index}"
											value="${kxinfo.xq}${kxinfo.sj}" />
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
							</logic:present>
						</td>
					</tr>
					<tr>
						<th>�ܾ���</th>
						<td colspan="3">
							<bean:write name="rs" property="zjf" />
						</td>
					</tr>
					<tr>
						<th>�Գﾭ��</th>
						<td colspan="3">
							<bean:write name="rs" property="zcjf" />
						</td>
					</tr>
				</logic:equal>
				<%--������Ƽ�--%>
				<logic:notEqual value="11551" name="xxdm">
					<%--�Ϻ����̼�����ѧ--%>
					<logic:equal value="10856" name="xxdm">
						<tr>
						<th>����ʱ��</th>
						<td colspan="3">
							<logic:present name="whkxList">
								<table id="tbSj" class="formlist">
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
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}1" value="��" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}2" value="��" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}3" value="��" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}4" value="��" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}5" value="��" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}6" value="��" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}7" value="��" />
											</td>
										</tr>
									</logic:iterate>
								</table>
								<!-- begin ��ȡ�Ѿ����ڵĿ���ʱ����Ϣ -->
								<logic:present name="kxbz">
									<input type="hidden" id="kxbzNum"
										value="<bean:write name="kxbzNum"/>" />
									<logic:iterate id="kxinfo" name="kxbz" indexId="index">
										<input type="hidden" id="kxinfo${index}"
											value="${kxinfo.xq}${kxinfo.sj}" />
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
							</logic:present>
						</td>
					</tr>
						<tr>
							<th>�ܾ���</th>
							<td colspan="3">
								<bean:write name="rs" property="zjf" />
							</td>
						</tr>
					</logic:equal>
					<%--���Ϻ����̼�����ѧ--%>
					<logic:equal value="10491" name="xxdm">
						<tr>
		<%--							<th>�ܾ���</th>--%>
		<%--							<td>--%>
		<%--								<bean:write name="rs" property="zjf" />--%>
		<%--							</td>--%>
							<th>������</th>
							<td colspan="3">
								<bean:write name="rs" property="fzr" />
							</td>
						</tr>
					</logic:equal>
					</logic:notEqual>
				<tr>
					<th><span class="red">*</span>���������׼</th>
					<td>
						<html:text name="rs" property="spbcbz" style="width:130px" maxlength="20" onkeyup="value=value.replace(/[^(\d|\.)]/g,'') "></html:text>
						Ԫ
						(<bean:write name="rs" property="jcfsmc" />)
					</td>
					<th><span class="red">*</span>����ʹ������</th>
					<td>
						<html:text name="rs" property="sqsyrs" style="width:130px" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						��
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>������������</th>
					<td>
						<html:text name="rs" property="sqsyknss" style="width:130px" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						��
					</td>
					<th>���</th>
					<td>
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>��������</th>
					<td colspan="3">
						<bean:write name="rs" property="gznr" />
					</td>
				</tr>
				<%--�й����ʴ�ѧ--%>
				<logic:equal value="10491" name="xxdm">
					<tr>
						<th>������ַ</th>
						<td colspan="3">
							<bean:write name="rs" property="gzdd" />
						</td>
					</tr>
				</logic:equal>
				<!--�ǻ�����ѧԺ-->
				<logic:notEqual value="11049" name="xxdm">
					<tr>
						<th>���˵�λ���</th>
						<td colspan="3">
							<bean:write name="rs" property="sqdwyj" />
						</td>
					</tr>
				</logic:notEqual>
				<!--end�ǻ�����ѧԺ-->
				<%--����ɽ��ѧ--%>
				<logic:equal value="10419" name="xxdm">
					<tr>
						<th>��λҪ��</th>
						<td colspan="3">
							<bean:write name="rs" property="gwyq" />
						</td>
					</tr>
				</logic:equal>
				<%--��������Ժ�Զ����ֶ�--%>
				<logic:notEmpty name="filedList">
					<thead>
						<tr>
							<th style="cursor:hand" colspan="4">
								<span>���븽����Ϣ</span>
							</th>
						</tr>
					</thead>
					<input type="hidden" id="rsNum" name="rsNum" value="<bean:write name="rsNum"/>" />
					<logic:iterate id="filed" name="filedList" indexId="index">
						<tr>
							<td>
								<bean:write name="filed" property="zdmc" />
								��
							</td>
							<td colspan="3">
								<bean:write name="rs" property="${filed.zddm}" />
							</td>
						</tr>
					</logic:iterate>
				</logic:notEmpty>
				<tr>
					<th>ѧ�������</th>
					<td colspan="3">
						<html:textarea name="rs" property="xgbyj" cols="60" rows="3" styleId="xscyj" onblur="chLeng(this,'150')"></html:textarea>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������;<span class="red">�������������ܵ���${knsbl}%</span></div>
			          <div class="btn">
			            <button type="button" class="button2"
							onclick="save();return false;"
							style="width:80px" id="buttonSave">
							�� ��
						</button>
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							�� ��
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</div>

		</html:form>
		<logic:equal value="view" name="result">
			<script>
				alert("�����ɹ�!");
				//tipsWindown("���������","id:testID","340","120","true","","true","id");
				Close();
				if(window.dialogArguments){
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:equal>
	</body>
</html>
