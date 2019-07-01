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
		if(yesNo!=null && yesNo=='通过'){
			cqkjFunc.checkPostStuAudi(pkString,userName,function(data){
				if(data!=null && data.length>0){
					var message = "";
					for (i=0; i<data.length; i++){
						if(data[i]!=null && data[i]!=""){
							message = message+"\n" + data[i];
						}
					}
					if(message!=""){
						alert("无法审核通过："+message);
						return false;
					}else{
						showTips();
						refreshForm('/xgxt/postStuChkOne.do?act=save');
						if($("buttonSave")){$("buttonSave").disabled=true;}
					}						
				}
			});				
		}else{
			showTips('处理数据中，请稍候......');
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
				<em>您的当前位置:</em><a>${title }</a>
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
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		          	<logic:equal value="xy" name="userType" scope="request">
		          		<logic:notEqual value="通过" name="rs" property="xxyj">
		          			<logic:notEqual value="不通过" name="rs" property="xxyj">
		          				<button type="button" class="button2" onclick="thisCommit();" style="width:80px"
									id="buttonSave">
									保 存
								</button>
		          			</logic:notEqual>
		          		</logic:notEqual>
		          	</logic:equal>
		          	<logic:equal value="xx" name="userType" scope="request">
		          		<button type="button" class="button2" onclick="thisCommit();" style="width:80px"
							id="buttonSave">
							保 存
						</button>
		          	</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
	</html:form>
	
	<!--操作提示信息-->
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
						alert("操作成功！");
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
					alert("操作失败！");
				</script>
			</logic:empty>
			<script language="javascript">
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
			</logic:notEqual>
		</logic:notEmpty>
		<!--end操作提示信息-->
		
		<!--审核条件限制提示-->
		<logic:equal value="view" name="result">
			<logic:present name="hasSQ">
				<logic:match value="have" name="hasSQ">
					<script language="javascript">
			         	alert("该学生已有申请项目通过审核！");
			         	Close();
						window.dialogArguments.document.getElementById('search_go').click();
	         		</script>
				</logic:match>
				<logic:match value="notHave" name="hasSQ">
					<script language="javascript">
			         	alert("操作成功！");
						Close();
						window.dialogArguments.document.getElementById('search_go').click();
	         		</script>
				</logic:match>
			</logic:present>
		</logic:equal>
		<!--end审核条件限制提示-->

		<!--人数限制信息提示-->
		<logic:equal value="full" name="result">
			<script>
				alert("人数已满！");
			</script>
		</logic:equal>
		<logic:equal value="knsfull" name="result">
			<script>
				alert("困难生人数已满！");
			</script>
		</logic:equal>
		<!--end人数限制信息提示-->
</body>
</html>
