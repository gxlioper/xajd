<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
	<script type="text/javascript">
	function thisCommit(){
		var yesNo = document.getElementById("yesNo").value;
		var userName = document.getElementById("userName").value;
		var xxdm = document.getElementById("xxdm").value;
		var pkString = document.getElementById("pkVal").value + "!!SplitOneSplit!!";
		var userType = document.getElementById("userType").value;
		if(yesNo!=null && yesNo=='ͨ��'){
			cqkjFunc.checkPostStuAudi(pkString,userName,function(data){
				if(data!=null && data.length>0){
					var message = "";
					for (i=0; i<data.length; i++){
						if(data[i]!=null && data[i]!=""){
							message = message+"\n" + data[i];
						}
					}
					if(message!=""){
						alert("�޷����ͨ����"+message);
						return false;
					}else{
						showTips();
						refreshForm('/xgxt/postStuChkOne.do?act=save');
						if($("buttonSave")){$("buttonSave").disabled=true;}
					}						
				}
			});				
		}else{
			showTips('���������У����Ժ�......');
			refreshForm('/xgxt/postStuChkOne.do?act=save');
			if($("buttonSave")){$("buttonSave").disabled=true;}
		}
	}
	</script>
</head>
<body onload="checkWinType();document.all('buttonClose').focus()">
	<html:form action="/qgzx_gwsqwh" method="post">
		<input type="hidden" name="pkVal" value="<bean:write name="rs" property="xh||gwdm||sqsj"/>" id="pkVal" />
		<input type="hidden" name="gwdm" value="<bean:write name="rs" property="gwdm"/>" />
		<input type="hidden" name="xh" value="<bean:write name="rs" property="xh"/>" />
		<input type="hidden" name="pk" value="<bean:write name="rs" property="pk"/>" id="pk"/>
		<input type="hidden" id="userName" name="userName" value="<bean:write name="userName"/>" />
		<input type="hidden" id="userType" name="userType" value="<bean:write name="userType"/>" />
		<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		
		<div class="tab"/>
		<table class="formlist" width="93%">
			<%@include file="gwxx.jsp" %>
			<%@include file="xsxx.jsp" %>
			<%@include file="sqxxview.jsp" %>
			<%@include file="shxx.jsp" %>
			
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
		          	<logic:equal value="xy" name="userType" scope="request">
		          		<logic:notEqual value="ͨ��" name="rs" property="xxyj">
		          			<logic:notEqual value="��ͨ��" name="rs" property="xxyj">
		          				<button type="button" class="button2" onclick="thisCommit();" style="width:80px"
									id="buttonSave">
									�� ��
								</button>
		          			</logic:notEqual>
		          		</logic:notEqual>
		          	</logic:equal>
		          	<logic:equal value="xx" name="userType" scope="request">
		          		<button type="button" class="button2" onclick="thisCommit();" style="width:80px"
							id="buttonSave">
							�� ��
						</button>
		          	</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
	</html:form>
	
	<!--������ʾ��Ϣ-->
		<logic:notEmpty name="flag">
			<logic:equal value="true" name="flag">
				<logic:notEmpty name="mes">
					<input type="hidden" id="mes" name="mes" value="${mes}"/>
					<script>
						alert(document.getElementById('mes').value);
					</script>
				</logic:notEmpty>
				<logic:empty name="mes">
					<script language="javascript">
						alert("�����ɹ���");
					</script>
				</logic:empty>
				<script language="javascript">				
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>

			<logic:notEqual value="true" name="flag">
				<logic:notEmpty name="mes">
					<input type="hidden" id="mes" name="mes" value="${mes}"/>
					<script>
						alert(document.getElementById('mes').value);
					</script>
				</logic:notEmpty>
			<logic:empty name="mes">
				<script language="javascript">
					alert("����ʧ�ܣ�");
				</script>
			</logic:empty>
			<script language="javascript">
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
			</logic:notEqual>
		</logic:notEmpty>
		<!--end������ʾ��Ϣ-->
		
		<!--�������������ʾ-->
		<logic:equal value="view" name="result">
			<logic:present name="hasSQ">
				<logic:match value="have" name="hasSQ">
					<script language="javascript">
			         	alert("��ѧ������������Ŀͨ����ˣ�");
			         	Close();
						window.dialogArguments.document.getElementById('search_go').click();
	         		</script>
				</logic:match>
				<logic:match value="notHave" name="hasSQ">
					<script language="javascript">
			         	alert("�����ɹ���");
						Close();
						window.dialogArguments.document.getElementById('search_go').click();
	         		</script>
				</logic:match>
			</logic:present>
		</logic:equal>
		<!--end�������������ʾ-->

		<!--����������Ϣ��ʾ-->
		<logic:equal value="full" name="result">
			<script>
				alert("����������");
			</script>
		</logic:equal>
		<logic:equal value="knsfull" name="result">
			<script>
				alert("����������������");
			</script>
		</logic:equal>
		<!--end����������Ϣ��ʾ-->
</body>
</html>
