<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		function searchRs(){
			allNotEmpThenGo('/xgxt/zhcpPdbx.do?method=pdbxCx');	
		}
		
		//��ʦȷ������
		function jsqrpf(flag){
			var pkValue=$("pkValue").value;
			refreshForm("/xgxt/zhcpPdbx.do?method=pdbxQd&doType=jsqr&pkValue="+pkValue+"&jsqr="+flag);
		}
		</script>
	</head>
	<body onload="">
		
		<html:form action="/zhcpPdbx" method="post">
<%--			<div class="tab_cur" >--%>
<%--				<p class="location">--%>
<%--					<em>���ĵ�ǰλ��:</em><a>${title }</a>--%>
<%--				</p>--%>
<%--			</div>--%>
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}"/>
			<table class="formlist" border="0" align="center">

				<!-- ����������Ϣ -->
				<thead>
					<tr>
						<th colspan="4">
							<span>��������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="16%">
							ѧ��
						</th>
						<td width="34%">
							${stuInfo.xh }
						</td>
						<th width="16%">
							����
						</th>
						<td width="34%">
							${stuInfo.xm }
						</td>
					</tr>
					<tr>
						<th>
							�Ա�
						</th>
						<td>
							${stuInfo.xb }
						</td>
						<th>
							�꼶
						</th>
						<td>
							${stuInfo.nj }
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="lable.xb" />
						</th>
						<td>
							${stuInfo.zymc }
						</td>
						<th>
							רҵ
						</th>
						<td>
							${stuInfo.zymc }
						</td>
					</tr>
					<tr>
						<th>
							�༶
						</th>
						<td>
							${stuInfo.bjmc }
						</td>
						<th>
							
						</th>
						<td>
							
						</td>
					</tr>
				</tbody>
			</table>
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<div class="formbox" style='width:98%;height:300px;overflow-y:auto;overflow-x:hidden'>
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty>  
						
					</span>
				</h3>

				<logic:notEmpty name="rs">
				<div class="con_overlfow" >
					<table summary="" class="dateline" align="left" width="97%">
						<thead>
							<tr align="center" style="cursor:hand">
								
								<logic:iterate id="tit" name="topTr" offset="2" length="2">
									<td id="<bean:write name="tit" property="en"/>"
										 nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<logic:iterate id="tit" name="topTr" offset="5">
									<td id="<bean:write name="tit" property="en"/>"
										 nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<logic:iterate id="tit" name="topTr" offset="4" length="1">
									<td id="<bean:write name="tit" property="en"/>"
										 nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s" >
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
									
									<logic:iterate id="v" name="s" offset="2"  length="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
									
									<logic:iterate id="v" name="s" offset="5" >
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
									
									<logic:iterate id="v" name="s" offset="4" length="1">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</div>
					
				</logic:notEmpty>
			</div>
			<table class="formlist" style="margin-bottom:4px">
			<thead>
				<tr>
					<td>
						<div class="btn">
							<button type="button" id="btn_bj" onclick="jsqrpf('��')">
								ȷ ��
							</button>
							<button type="button" id="btn_qd" style="display:none" onclick="jsqrpf('��')">
								ȡ��ȷ��
							</button>
							<button type="button" id="btn_bc" onclick="jsqrpf('�˻�')"'>
								�˻�����
							</button>
							<button type="button" id="btn_bc" onclick='Close();return false;'>
								�� ��
							</button>
						</div>
					</td>
				</tr>
			<thead>
		</table>
		</html:form>
		<logic:present name="message">
				
			<script language="javascript">
				alert('${message}!');
			</script>
			
			<script language="javascript">			
				if(window.dialogArguments){
					if(window.dialogArguments.document.getElementById("search_go")){
						dialogArgumentsQueryChick();
					}
					window.close();
				}
			</script>
		</logic:present>
	</body>
</html>
