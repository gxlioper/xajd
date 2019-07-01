<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		function stringFormat(){
			var element = ['kcjqgzxsj','sqly','bz'];
			for(var i=0; i<element.length; i++){
				if(ele(element[i])){
					ele(element[i]).innerHTML = formatContentWidth(ele(element[i]).innerText,30);
				}
			}
		}
	</script>
</head>
<body onload="stringFormat()">
	<html:form action="/data_search" method="post">
		<input type="hidden" id="doType" name="doType" value="<bean:write name="doType" scope="request"/>" />
		<input type="hidden" id="pkValue" name="pkValue" value="<bean:write name="pkValue" scope="request"/>" />
		<input type="hidden" id="disableEle" name="disableEle" value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url" value="/sjcz/xsgwxxb.jsp" />
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�ڹ���ѧ - ���� - ��������ѯ - ��ϸ��Ϣ</a>
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
			<logic:equal name="rs" property="stuExists" value="no">
				<script>
				    alert("�������ѧ����Ч!");
				</script>
			</logic:equal>
			<div class="tab">
		  	<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>��λ��Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th><span class="red">*</span>ѧ�ţ�
					</td>
					<td align="left">
						${rs.xh}
					</td>
					<th>
						<logic:equal value="11551" name="xxdm">
							<span class="red">*</span>�����λ
						</logic:equal>
						<logic:notEqual value="11551" name="xxdm">
							<span class="red">*</span>��λ����
						</logic:notEqual>						
					</th>
					<td>
						${rs.gwdm}
					</td>
				</tr>
				<tr>
					<th>����</th>
					<td>
						${rs.xm}
					</td>
					<th>���</th>
					<td>
						${rs.nd}
					</td>
				</tr>
				<tr>
					<th>�Ա�</th>
					<td>
						${rs.xb}
					</td>
					<th>ѧ��</th>
					<td>
						${rs.xn}
					</td>
				</tr>
				<tr>
					<th>�꼶</th>
					<td>
						${rs.nj}
					</td>
					<th>ѧ��</th>
					<td>
						${rs.xqmc}
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						${rs.xymc}
					</td>
					<th>��ϵ�绰</th>
					<td>
						${rs.lxdh}
					</td>
				</tr>
				<tr>
					<th>רҵ</th>
					<td>
						${rs.zymc}
					</td>
					<logic:notEqual value="11551" name="xxdm">
					<!--�㽭�Ƽ�ѧԺ-->
					<logic:equal value="11057" name="xxdm">
					<th>��ͥ��ַ</th>
					<td id="kcjqgzxsj">
						${rs.jtdz}
					</td>
					</logic:equal>
					<logic:notEqual value="11057" name="xxdm">
					<th>�ɲμ��ڹ���ѧʱ��</th>
					<td id="kcjqgzxsj">
						${rs.kcjqgzxsj}
					</td>
					</logic:notEqual>
					</logic:notEqual>
					<logic:equal value="11551" name="xxdm">
					<th>������</th>
					<td>${rs.fzr}</td>
					</logic:equal>
				</tr>
				<tr style="height:22px">
					<th>�༶</th>
					<td>
						${rs.bjmc}
					</td>
					<th>�Ƿ�������</th>
					<td>
					    ${rs.sfpks}
					</td>
				</tr>
				<logic:equal value="10690" name="xxdm" scope="session">
				<tr>
					<th>������ò</th>
					<td>
						${rs.zzmmmc}
					</td>
					<th>��ͥ��ַ</th>
					<td>
						${rs.jtdz}
					</td>
				</tr>
				
				<tr>
					<th>����</th>
					<td>
						${rs.mzmc}
					</td>
					<th>��ͥ�绰</th>
					<td>
						${rs.jtdh}						
					</td>
				</tr>
				<tr>
				  <th>������ѧ����</th>
				  <td>${rs.gjzxdk}</td>
				  <th>��ͥ�ʱ�</th>
				  <td>${rs.jtyb}</td>
			  </tr>
				<tr>
				  <th>�����������</th>
				  <td>${rs.zzqk}</td>
				  <th>��ͥ��Ա��Ϣ</th>
				  <td>${rs.jtcy}</td>
			  </tr>
				<tr>
				  <th>ƶ���ȼ�</th>
				  <td>${rs.pkdj}</td>
				  <th>��ͥ������</th>
				  <td>${rs.jtysr}</td>
			  </tr>
				<tr>
				  <th>�Ը�</th>
				  <td>${rs.xg} <br/> (����Ϊ1������Ϊ10)</td>
				  <th>�س�</th>
				  <td>${rs.yhtc}</td>
			  </tr>
				<tr>
				  <th>�Ͷ�����</th>
				  <td>${rs.gzjl}</td>
				  <th>�Ͷ�����</th>
				  <td>${rs.ldyx}</td>
			  </tr>
			  </logic:equal>
			   <!-- ������ͨ -->
			  	<logic:equal value="12752" name="xxdm" scope="session">
			  		<tr>
			  			<th>
			  				����Ա���
			  			</th>
			  			<td colspan="3">
			  				${rs.fdyyj}
			  			</td>
			  		</tr>
			  	</logic:equal>
			  	<!-- �㽭��ְͨҵ����ѧԺ-->
			  	<logic:equal value="12036" name="xxdm" scope="session">
			  		<tr>
			  			<th>
			  				����Ա���
			  			</th>
			  			<td colspan="3">
			  				${rs.fdyyj}
			  			</td>
			  		</tr>
			  	</logic:equal>
			  	<logic:equal value="11654" name="xxdm" scope="session">
			  		<tr>
			  			<th>
			  				����Ա���
			  			</th>
			  			<td colspan="3">
			  				${rs.fdyyj}
			  			</td>
			  		</tr>
			  	</logic:equal>
				<tr>
					<logic:equal value="13022" name="xxdm" scope="session">
					<th><bean:message key="lable.xsgzyxpzxy" />���</th>
					<td>
					    ${rs.xyyj}
					</td>
					</logic:equal>
					<logic:equal value="12862" name="xxdm" scope="session">
					<th>�ڹ�����Ա���</th>
					<td colspan="3">
					    ${rs.xxyj}
					</td>
					</logic:equal>
					<logic:notEqual value="12862" name="xxdm" scope="session">
						<logic:notEqual value="13022" name="xxdm" scope="session">
						<th> ���˵�λ���</th>
						<td>
						    ${rs.xyyj}
						</td>
						</logic:notEqual>
						<th>ѧУ���</th>
						<td>
							${rs.xxyj}
						</td>
					</logic:notEqual>
				</tr>
				<%--��������--%>
				<logic:equal value="10690" name="xxdm" scope="session">
					<tr>
						<th>ѧ�������</th>
						<td >${rs.xscyj}</td>
						<th>����Ա���</th>
						<td>${rs.fdyyj}</td>
					</tr>
					<tr>
						<th>���</th>
						<td>${rs.bh}</td>
						<th>����</th>
						<td>${rs.gh}</td>
					</tr>
				</logic:equal>
				<tr>
					<th>��������</th>
					<td colspan="3" id="sqly">
						${rs.xssq}
					</td>
				</tr>
				<logic:equal value="10690" name="xxdm">
				<tr>
					<th>ѧУ������</th>
					<td colspan="3">
						${rs.xxshyj}
					</td>
				</tr>
				</logic:equal>
				<tr>
					<th>��ע</th>
					<td colspan="3" id="bz">
						${rs.bz}
					</td>
				</tr>
				<logic:equal value="11551" name="xxdm">
				<tr>
				<th>�ɲμ��ڹ���ѧʱ��</th>
				<td colspan="3">
				<logic:notEmpty name="kxList">
						<table border="0" cellpadding="0" cellspacing="0" align="center" class="formlist">
							<tr>
								<td align="center">ʱ��</td>
								<td>����һ</td>
								<td>���ڶ�</td>
								<td>������</td>
								<td>������</td>
								<td>������</td>
								<td>������</td>
								<td>������</td>
							</tr>								
							<logic:iterate id="kxsj" name="kxList">
							<tr>
								<td>${kxsj.sj}</td>
								<td align="center"><input type="checkbox" name="${kxsj.sjIndex}1" value="1"/><input type="hidden" name="index${kxsj.sjIndex}1" value="${kxsj.mon}"/></td>
								<td align="center"><input type="checkbox" name="${kxsj.sjIndex}2" value="1"/><input type="hidden" name="index${kxsj.sjIndex}2" value="${kxsj.tue}"/></td>
								<td align="center"><input type="checkbox" name="${kxsj.sjIndex}3" value="1"/><input type="hidden" name="index${kxsj.sjIndex}3" value="${kxsj.wed}"/></td>
								<td align="center"><input type="checkbox" name="${kxsj.sjIndex}4" value="1"/><input type="hidden" name="index${kxsj.sjIndex}4" value="${kxsj.thu}"/></td>
								<td align="center"><input type="checkbox" name="${kxsj.sjIndex}5" value="1"/><input type="hidden" name="index${kxsj.sjIndex}5" value="${kxsj.fri}"/></td>
								<td align="center"><input type="checkbox" name="${kxsj.sjIndex}6" value="1"/><input type="hidden" name="index${kxsj.sjIndex}6" value="${kxsj.sat}"/></td>
								<td align="center"><input type="checkbox" name="${kxsj.sjIndex}7" value="1"/><input type="hidden" name="index${kxsj.sjIndex}7" value="${kxsj.sun}"/></td>
								
							</tr>
							</logic:iterate>
							</table>
							<script>
									for(var i=0;i<7;i++){
										for(var j=1;j<8;j++){
										 	if(document.getElementById("index"+i+j)){
										 		if(document.getElementById("index"+i+j).value==1){
													document.getElementById(i+""+j).checked="checked";
											    }
										 	}
										}
									}
								</script>
							</logic:notEmpty>
				</td>
				</tr>
				</logic:equal>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			            <button type="button" class="button2" id="btn_gb"
							onclick="Close();return false;"
							style="width:80px">
							�� ��
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
		</div>
		</logic:notEmpty>
	</html:form>
</body>
</html>