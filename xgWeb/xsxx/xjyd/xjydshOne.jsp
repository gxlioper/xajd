<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- ͷ�ļ� -->
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/stuinfoFunction.js"></script>
<script type="text/javascript" src="js/xsxx/xjyd.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getXjydInfo.js'></script>
<script>
	function save(shzt){
		refreshForm('xjyd.do?method=xjydShOne&doType=save&shjg=' + shzt);
	}
	
</script>
</head>

<body>
	<html:form action="/xjyd.do" method="post">
		<input type="hidden" name="pkValue" value="${pkValue }" id="pkValue"/>
		<!--�û���Ϣ-->
		<%@ include file="/xsxx/yhxx.jsp"%>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title}</a>
			</p>
		</div>
		<div class="tab">
			<table class="formlist" width="100%">
				<thead>
					<tr>
						<th colspan="4" onclick="document.all.jbxx.style.display=(document.all.jbxx.style.display =='none')?'':'none'"> 
							<span>������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody id="jbxx">
					<tr>
						<th width="24%">ѧ��</th>
						<td width="26%">
							${rs.xh}				
						</td>
						<th>�Ա�</th>
						<td>${rs.xb }</td>
					</tr>
					<tr>
						<th width="24%">����</th>
						<td width="26%">
							${rs.xm }
						</td>
						<th>��������</th>
						<td>${rs.csrq }</td>
					</tr>
					<tr>
						<th>�ֻ�����</th>
						<td>${rs.sjhm }</td>
						<th>�̶��绰</th>
						<td>${rs.lxdh }</td>
					</tr>
					<tr>
						<th>�춯���</th>
						<td>
							${rs.ydxh}					
						</td>
						<th>�����ĺ�</th>
						<td>
							${rs.clwh}
						</td>
					</tr>					
					<tr>
						<th>�춯���</th>
						<td>
							${rs.ydlbmc}
							<input type="hidden" id="ydlbdm" name="ydlbdm" value="${rs.ydlbm}"/>
						</td>
						<th>�춯����</th>
						<td>
							${rs.ydrq}
						</td>
					</tr>
					<tr>	
						<th>�춯˵��</th>
						<td>
							${rs.ydsm}
						</td>
						<th>�춯��ֹ����</th>
						<td>
							${rs.ydjzrq}
						</td>
					</tr>
					<tr>
						<th>�춯ԭ��</th>
						<td colspan="3">  
							${rs.ydyy}
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4" onclick="document.all.xjxx.style.display=(document.all.xjxx.style.display =='none')?'':'none'"> 
							<span>ѧ����Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody id="xjxx">
					<tr>
						<td colspan="2">
							<table class="formlist" style="width:100%;height:100%"> 
								<thead>
									<tr>
										<td colspan="2" align="center"><b>�춯ǰ��Ϣ</b></td>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th height="20px">�꼶</th>
										<td width="70%">
											${rs.ydqnj}
										</td>
									</tr>
									<tr>
										<th height="20px"><bean:message key="lable.xsgzyxpzxy" /></th>
										<td>
											${rs.ydqxymc}
										</td>
									</tr>
									<tr>
										<th height="20px">רҵ</th>
										<td>
											${rs.ydqzymc}
										</td>
									</tr>
									<tr>
										<th height="20px">�༶</th>
										<td>
											${rs.ydqbjmc}
										</td>
									</tr>
									<tr>
										<th height="20px">ѧ��</th>
										<td>
											${rs.ydqxz}
										</td>
									</tr>
									<tr>
										<th height="20px">ѧ��״̬</th>
										<td>
											${rs.ydqxjztm}
										</td>
									</tr>
									<tr>
										<th height="20px">�Ƿ���У</th>
										<td>
											${rs.ydqsfzx}
										</td>
									</tr>
								</tbody>
							</table>
						</td>
						<td colspan="2">
							<table class="formlist">
								<thead>
									<tr>
										<td colspan="2" align="center"><b>�춯����Ϣ</b></td>
									</tr>
								</thead>
								<tbody>
									<tr height="20px">
										<th>�꼶</th>
										<td>
											${rs.ydhnj}						
										</td>
									</tr>
									<tr height="20px">
										<th><bean:message key="lable.xsgzyxpzxy" /></th>
										<td>
											${rs.ydhxymc}
										</td>
									</tr>
									<tr height="20px">
										<th>רҵ</th>
										<td>
											${rs.ydhzymc}
										</td>
									</tr>
									<tr height="20px">
										<th>�༶</th>
										<td>
											${rs.ydhbjmc}
										</td>
									</tr>
									<tr height="20px">
										<th>ѧ��</th>
										<td>
											${rs.ydhxz}
										</td>
									</tr>
									<tr height="20px">
										<th>ѧ��״̬</th>
										<td>
											${rs.ydhxjztm}
										</td>
									</tr>
									<tr height="20px">
										<th>�Ƿ���У</th>
										<td>
											${rs.ydhsfzx}
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>					
				</tbody>
				<thead>
					<tr>
						<th colspan="4" onclick="document.all.shxx.style.display=(document.all.shxx.style.display =='none')?'':'none'"> 
							<span>�����Ϣ</span>
						</th>
					</tr>
				</thead>
				<logic:notEqual value="view" name="doType">
					<tbody id="shxx" style="display:none">
						<tr>
							<th>
								${rs.xtgwmc}������
								<br />
								<font color="red"><��250��>
								</font>
							</th>
							<td colspan="3"> 
								<html:hidden property="xtgwid" name="rs"/>
								<html:textarea property="shyj" name="rs" cols="60" rows="6" onblur="chLeng(this,250)"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>�����</th>
							<td> 
								${userNameReal}
							</td>
							<th>���ʱ��</th>
							<td> 
								${dqsj}
							</td>
						</tr>
					</tbody>
				</logic:notEqual>
				<logic:equal value="view" name="doType">
					<tbody id="shxx" style="display:none">
						<logic:iterate id="v" name="shxxList">
						<tr>
							<th>
								${v.xtgwmc}���
							</th>
							<td colspan="3"> 
								${v.shzt}
							</td>
						</tr>
						<tr>
							<th>
								${v.xtgwmc}������
							</th>
							<td colspan="3"> 
								${v.shyj}
							</td>
						</tr>
						<tr>
							<th>�����</th>
							<td> 
								${v.shr}
							</td>
							<th>���ʱ��</th>
							<td> 
								${v.shsj}
							</td>
						</tr>
						</logic:iterate>
					</tbody>
				</logic:equal>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
						<div class="buttontool">
							<logic:notEqual value="view" name="doType">
								<button type="button" class="" onclick="save('ͨ��');" >
									ͨ&nbsp;&nbsp;��
								</button>
								<button type="button" class="" onclick="save('��ͨ��');" >
									��ͨ��
								</button>
								<button type="button" class="" onclick="save('�˻�')" >
									��&nbsp;&nbsp;��
								</button>
							</logic:notEqual>
							
							<button type="button" class="" onclick="Close();return false;" >
								��&nbsp;&nbsp;��
							</button>
						</div>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>		
		</div>	

		<logic:present name="result">
			<intput type="hidden" id="message" value="${message}"/>
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</html:form>
</body>
</html>