<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body
		onload="checkWinType();title_m.innerHTML = window.dialogArguments.document.all('title_m').innerHTML">
		<script language="javascript" src="js/function.js"></script>
		<html:form action='/uploadFile.do' method='post'
			enctype='multipart/form-data'>
			<logic:present name="moditag">
				<input type="hidden" id="moditag" name="moditag"
					value="<bean:write name="moditag"/>" />
			</logic:present>
			<logic:notPresent name="moditag">
				<input type="hidden" id="moditag" name="moditag" value="" />
			</logic:notPresent>
			<div class="tab_cur">
			<div class="location">
				<div class="title_img" id="title_m"></div>
			</div>
			</div>
			
			
			<logic:notEmpty name="dataImported">
				<script>
			alert("�����ɹ���");
			window.close();
			</script>
			</logic:notEmpty>
<%--			<div id="search_m">--%>
<%--				<div class="searchcontent">--%>
					<div class="tab">
			  			<table width="100%"  border="0" class="formlist">
						<thead>
					    	<tr>
					        	<th colspan="4"><div align="center"><span>��ѡ��Ҫ������ļ�</span></div></th>
					        </tr>
					    </thead>
					     <tfoot>
					      <tr>
					        <td colspan="4">
					          <div class="btn">
					          	<button type="button" onclick="checkFile()">
									�ϴ�
								</button>
					           <button type="button"  onclick="Close();return false;">
									�ر�
								</button>
					      </tr>
					    </tfoot>
					    
					    <tbody>
						<tr>
							<td align="center">
								<input type="file" name="file" id="file" value="" />
							</td>							
						</tr>							
						<tr>
						<td align="center">						
							<html:radio property="drms" value="1">����������</html:radio>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio property="drms" value="0">���ݸ���</html:radio>
						</td>
						</tr>
					</table>
					<br />
<%--				</div>--%>
<%--			</div>--%>
		</html:form>
	</body>
</html>

