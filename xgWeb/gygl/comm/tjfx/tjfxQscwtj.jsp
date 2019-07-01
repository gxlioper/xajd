<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
				
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglTjfx.do?method=tjfxQscwtj');
		}
		
		function setPath(){
			$("path").value="gygl_tjfx_jjtj.do";
		}
		
		function exportTj(){
			document.forms[0].action = '/xgxt/gyglTjfx.do?method=tjfxQscwtj&doType=exp';
			document.forms[0].submit();
		}
		
		</script>
	</head>
	<body>

		<html:form action="/gyglTjfx" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>	
						<li><a href="#" class="btn_dc" onclick="setPath();setSearchTj();exportTj();return false;">��������</a></li>
					</ul>
				</div>
				<div class="compTab" id="card" style="position:relative">
					<div class="comp_title" id="div1">
						<ul id="ul1">					
							<li id="qscwtj" class="ha">
								<a href="#" target="_self">
									<span>¥�����Ҵ�λͳ��</span>
								</a>
							</li>
							<li id="njfbtj">
								<a href="/xgxt/gyglTjfx.do?method=tjfxNjfbtj" target="_self">
									<span>¥�������꼶�ֲ�ͳ��</span>
								</a>
							</li>
							<li id="rzrytj">
								<a href="/xgxt/gyglTjfx.do?method=tjfxQsrytj" target="_self">
									<span>¥��������ס��Աͳ��</span>
								</a>
							</li>
						</ul>	
					</div>
				</div>
							
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; 
						<logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty>  
						<logic:notEmpty name="rs">
							<font color="blue"></font>	
						</logic:notEmpty>
					</span>
				</h3>
				<logic:notEmpty name="rs">
				<div class="con_overlfow" >
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<div>
							<tr align="center" style="cursor:hand">
								<td colspan="${ldnum}">
									<div align="center"><bean:message key="lable.ld" /></div>
								</td>
								<logic:notEmpty name="xylist">
									<logic:iterate name="xylist" id="b" >
										<td colspan="5" >
											<div align="center"><bean:write name="b" property="xymc"/></div>
										</td>
									</logic:iterate>
								</logic:notEmpty>
								<td colspan="5">
									<div align="center"><bean:message key="lable.xb" />�ܼ�</div>
								</td>
							</tr>
							<tr>
								<logic:equal name="czxq" value="��">
									<td><bean:message key="lable.xiaoqu" /></td>
								</logic:equal>
								<logic:equal name="czyq" value="��">
									<td><bean:message key="lable.yuanqu" /></td>
								</logic:equal>
								<td><bean:message key="lable.ld" /></td>
								<td>��<br/>��λ��</td>
								<td>��<br/>������</td>
								<td>δ����<br/>���Ҵ�λ��</td>
								<td>δ����<br/>������</td>
								<logic:notEmpty name="xylist">
									<logic:iterate name="xylist" id="b" >
											<td>����<br/>��λ��</td>
											<td>����<br/>��λ��</td>
											<td>����<br/>��λ��</td>
											<td>��<br/>��λ��</td>
											<td>��<br/>������</td>
									</logic:iterate>
								</logic:notEmpty>								
								<td>����<br/>��λ��</td>
								<td>����<br/>��λ��</td>
								<td>����<br/>��λ��</td>
								<td>��<br/>��λ��</td>
								<td>��<br/>������</td>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr>
									<logic:equal name="czxq" value="��">
										<logic:iterate id="v" name="s" offset="0" length="1" >
											<td nowrap>
												${v }
											</td>
										</logic:iterate>
									</logic:equal>
									<logic:equal name="czyq" value="��">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<td nowrap>
												${v }
											</td>
										</logic:iterate>
									</logic:equal>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<td nowrap>
											${v }
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="3" >
										<td nowrap>
											<logic:greaterThan value="0" name="v">
												<font color="red">${v }</font>
											</logic:greaterThan>
											<logic:equal value="0" name="v">
												${v }
											</logic:equal>											
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<logic:iterate name="zjrs" id="zjs" indexId="index">
								<tr>									
									<logic:iterate id="zjv" name="zjs" offset="0" length="1">
										<td colspan="${ldnum-4}">
											${zjv }
										</td>
									</logic:iterate>
									<logic:iterate id="zjv" name="zjs" offset="1" >
										<td nowrap>
											<logic:greaterThan value="0" name="zjv">
												<font color="red">${zjv }</font>
											</logic:greaterThan>
											<logic:equal value="0" name="zjv">
												${zjv }
											</logic:equal>	
										</td>
									</logic:iterate>							
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</div>
				</logic:notEmpty>
			</div>
		</html:form>
		<logic:present name="msg">
			<script>
				alert("<bean:write name="msg"/>");
			</script>
		</logic:present>
	</body>
</html>
