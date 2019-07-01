<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
			<script type='text/javascript' src='/xgxt/dwr/interface/getRule.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>		
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript">
	
	function initClassSimpleNameRule(){
		var totalNum=0;
		getRule.getClassSimpleNameRule(function(data){
		
		for(var i=0;i<data.length;i++){
		if(data[i].zdmc==""){
			
		}else{
			//�ֶ�����
			document.getElementById("zdmc"+i).value=data[i].zdmc;			
			//��ʼλ
			if(data[i].qsw==null ||data[i].qsw==""){
				document.getElementById("qsw"+i).value="";
			}else{
				document.getElementById("qsw"+i).value=data[i].qsw;
			}
			//λ��
			if(data[i].ws=="null" || data[i].ws==null || data[i].ws==""){
				document.getElementById("ws"+i).value="";
			}else{
				document.getElementById("ws"+i).value=data[i].ws;
			}
			//�Ƿ�ȫ�� 
			document.getElementById("sfqb"+i).value=data[i].sfqb;
			//����
			if(data[i].zdmc=="cl"){
				//��ʼλ
				document.getElementById("qsw"+i).disabled="disabled";
				document.getElementById("qsw"+i).value="";
				//λ�� 
				document.getElementById("ws"+i).disabled="disabled";
				document.getElementById("ws"+i).value="";
				//�Ƿ�ȫ�� 				
				document.getElementById("sfqb"+i).disabled="disabled";
				//���� 
				document.getElementById("cl"+i).disabled=false;
				document.getElementById("cl"+i).value=data[i].cl;
			}
		}	
		}	
	});	
}
	
	//�˲����
function checkRule(){
	var num = parseInt(document.getElementById("num").value);	
	for(var i=0;i<num;i++){
		if(document.getElementById("zdmc"+i).value==""){
		}else{
			if(document.getElementById("sfqb"+i).value=="��" && document.getElementById("qsw"+i).value=="" && document.getElementById("zdmc"+i).value!="cl"){
				alert("��ʼλ����Ϊ��!");
				return false;
			}
			if(document.getElementById("sfqb"+i).value=="��" && document.getElementById("ws"+i).value=="" && document.getElementById("zdmc"+i).value!="cl"){
				alert("λ������Ϊ��!");
				return false;
			}
			if(document.getElementById("cl"+i).disabled==false){
				if(document.getElementById("cl"+i).value==""){
					alert("�����볣��!");
					return false;
				}
			}
		}
	}
	alert("�˲�ͨ��!");
	return true;
}
function checkCl(obj){
	var id = obj.id;
	var index = id.substring(parseInt(id.length)-1,id.length);
	if(document.getElementById(id).value=="cl"){
		//��ʼλ
		document.getElementById("qsw"+index).disabled="disabled";
		document.getElementById("qsw"+index).value="";
		//λ�� 
		document.getElementById("ws"+index).disabled="disabled";
		document.getElementById("ws"+index).value="";
		//�Ƿ�ȫ�� 				
		document.getElementById("sfqb"+index).disabled="disabled";
		//���� 
		document.getElementById("cl"+index).disabled=false;
	}else{
		document.getElementById("cl"+index).disabled="disabled";
		document.getElementById("qsw"+index).disabled=false;
		document.getElementById("ws"+index).disabled=false;
		document.getElementById("sfqb"+index).disabled=false;
		document.getElementById("cl"+index).value="";
	}
}
	</script>	
	</head>
	<body onload="initClassSimpleNameRule()">
			<html:form action="/arrangeClass.do" method="post">
			<input type="hidden" id="num" name="num" value="<bean:write name="zcxNum"/>"/>
				<div class="tab_cur" id="jd">
					<p class="location" id="title_m">
						<em>���ĵ�ǰλ��:</em><a>
						�ְ��ѧ�� - �������� - �༶��ƹ�������
						</a>
					</p>
				</div>
				<div class="tab">
					<table width="100%"  border="0" class="formlist">
					 <thead>
	    				<tr>
	        				<th colspan="6"><span>�༶��ƹ���</span></th>
	        			</tr>
	   				 </thead>			
						<tbody>
							<tr>							
								<th>�༶������������</th>								
								<th>��ʼλ</th>
								<th>λ��</th>
								<th>�Ƿ�ȫ��</th>
								<th>����</th>
							</tr>
							<logic:iterate id="zcx" name="zcxList" indexId="index">
							<tr><td>	
									<input id="xh${index}" name="xh${index}" type="hidden" value="${index+1}"/>	
									<select id="zdmc${index}" name="zdmc${index}" onchange="checkCl(this)">
										<option value=""></option>
										<logic:iterate id="v" name="zcxList">
											<option value="${v.zcxdm}">${v.zcxmc}</option>
										</logic:iterate>
									</select>
								</td>								
								<td><input id="qsw${index}" name="qsw${index}"/></td>
								<td><input id="ws${index}" name="ws${index}"/></td>
								<td>
									
									<select name="sfqb${index}" id="sfqb${index}">
									<option value="��">��</option>
									<option value="��">��</option>
									</select> 
								</td>
								<td><input id="cl${index}" name="cl${index}" disabled="disabled"/></td>
							</tr>
							</logic:iterate>
						</tbody>
						<tfoot>
						
						<tr>
						<td colspan="5" align="center">
							<div class="btn">
								<button class="button2" onclick="checkRule()">
									�˲����
								</button>
								<button class="button2" onclick="if(checkRule()) refreshForm('arrangeClass.do?method=saveClassSimpleNameRule');">
									 �������
								</button>
								<button class="button2" onclick="if(checkRule()) refreshForm('arrangeClass.do?method=modifyClassSimpleName');">
									 ���°༶���
								</button>															
								<button class="button2" onclick="Close();return false;">
									�� ��
								</button>
							</div>	
						</td>
						</tr>
						</tfoot>
					</table>					
					</div>
						<logic:notEmpty name="result">
						<logic:equal value="true" name="result">
							<script>
								alert("�����ɹ���");						
							</script>
						</logic:equal>
						<logic:equal name="result" value="false">
							<script>
								alert("����ʧ��!");
							</script>
						</logic:equal>
					</logic:notEmpty>
					
			</html:form>			
	</body>
</html>
