<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="js/BatAjax.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>ѧ�������</a>
			</p>
		</div>
		<!-- ���� end-->
		<FORM method="POST" name="myform" action="/XsgyglDispatch.do?method=xsYjx">
		<input type="hidden" name="id" id="id" value="<bean:write name="id" scope="request"/>">
		<input type="hidden" name="act" id="act" value="<bean:write name="act" scope="request"/>">
		<input type="hidden" name="content" id="content" value="<bean:write name='rs' property='content' />"></input>
			<!-- ����ظ� -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>����ظ�</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<TR>
						<TD align=right width="100">
							�ظ����⣺
						</TD>
						<TD align=left colspan="3">
							<html:text name="rs" property="title" styleId="title" style="width:600px" maxlength="50" readonly="true"></html:text>
						</TD>
					</TR>
					<TR>
						<TD align=right width="100">
							�ظ����ݣ�<br />
							(��2000�ַ�)
						</TD>
						<TD colspan="3">
							<input type="hidden" name="content1" id="content1"
								value="<bean:write name='rs' property='content' />"></input>
							<iframe id="eWebEditor1" src="BatEditor" frameborder="0"
								scrolling="no" width="100%" height="350"></iframe>
						</TD>
					</TR>
					</tbody>
					<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
									<button id="buttonSave" 
										onclick="yjhf()"
										style="width: 80px">
										�ظ�
									</button>
								&nbsp;&nbsp;
								<button id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									��	��
								</button>
							</div>
						</td>
					</tr>
					</tfoot>
				</TABLE>
			</fieldset>			 
		</FORM>
		<logic:equal value="yes" name="done">
			<script>
				alert("�����ɹ�!");
				opener.document.getElementById("search_go").click();
      			window.close();
			</script>
		   </logic:equal>
		   <logic:equal value="no" name="done">
			<script>
				alert("����ʧ��!");
				Close();
			</script>
		</logic:equal>	
	</body>
  <script type="text/javascript">
	   function yjhf(){
	   		$("content1").value= window.eWebEditor1.getHTML();
	   		$("content").value = $("content1").value;
	       if($('content').value==''){
	          alert('�ظ����ݲ���Ϊ�գ�');
	          return false;
	       }	       
	       if($('content').value != null){
	         	if($('content').value.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("�����ַ������ܴ���2000�ַ���Ŀǰ�ַ���Ϊ:"+$('content').value.replace(/[^\u0000-\u00ff]/g, "**").length);
	          		 return false;
	       		 }
	       	}
	       refreshForm('/xgxt/XsgyglDispatch.do?method=yjHf&doType=save')	        	    
	   }
	
	</script>
	
</html>
