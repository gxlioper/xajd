<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
				
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglTjfx.do?method=tjfxNjfbtj&doType=query');
		}
		
		function printNjfbtj(){
			url='/xgxt/gyglTjfx.do?method=printNjfbtj'; 
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
			
		}
		
		function loadTitle(){
			$("input_mhcx_msg").style.left='130px';
			$("input_mhcx_msg").style.top='117px';
		}
		</script>
	</head>
	<body onload="loadTitle()">

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
						<li><a href="#" class="btn_dc" onclick="printNjfbtj();return false;">��������</a></li>
					</ul>
				</div>
				<div class="compTab" id="card" style="position:relative">
					<div class="comp_title" id="div1">
						<ul id="ul1">					
							<li id="qscwtj" >
								<a href="/xgxt/gyglTjfx.do?method=tjfxQscwtj" target="_self">
									<span>¥�����Ҵ�λͳ��</span>
								</a>
							</li>
							<li id="njfbtj" class="ha">
								<a href="#" target="_self">
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
				<logic:notEmpty name="tjxxList">
				<div class="con_overlfow" >
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							
							<tr>
								<td colspan="${xyCols}" nowrap="nowrap">
									<div align="center"><bean:message key="lable.ld" /></div>
								</td>
								<td colspan="${njlen}">
									<div align="center">�ܼ�</div>
								</td>
								<logic:iterate name="xyxxList" id="xy">
								<td colspan="${njlen}"  nowrap="nowrap">
									<div align="center">${xy.xymc}</div>
								</td>
								</logic:iterate>
							</tr>
							<tr>
								<logic:equal name="czxq" value="��">
									<td nowrap="nowrap"><bean:message key="lable.xiaoqu" /></td>
								</logic:equal>
								<logic:equal name="czyq" value="��">
									<td nowrap="nowrap"><bean:message key="lable.yuanqu" /></td>
								</logic:equal>
								<td nowrap="nowrap"><bean:message key="lable.ld" />����</td>
								<logic:iterate id="nj" name="njxxList" >
									<td>
										${nj.nj}
									</td>
								</logic:iterate>
								<td >�ܼ�</td>
								<logic:iterate id="xy" name="xyxxList">
									<logic:iterate id="nj" name="njxxList" >
										<td>
											${nj.nj}
										</td>
									</logic:iterate>
									<td>
										�ܼ�
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="tjxxList" id="tjxx">
							<tr>
								<logic:equal name="czxq" value="��">
									<td nowrap="nowrap">
										<logic:iterate name="tjxx" id="tjxxArr" offset="0" length="1">
											${tjxxArr}
										</logic:iterate>
									</td>
								</logic:equal>
								<logic:equal name="czyq" value="��">
									<td nowrap="nowrap">
										<logic:iterate name="tjxx" id="tjxxArr" offset="1" length="1">
											${tjxxArr}
										</logic:iterate>
									</td>
								</logic:equal>
								<logic:iterate name="tjxx" id="tjxxArr" offset="3">
									<td nowrap="nowrap">
										${tjxxArr}
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
