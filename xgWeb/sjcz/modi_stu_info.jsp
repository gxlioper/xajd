<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>	
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script>
		function change(){
			var num=document.getElementById("num").value;
			for(var i=1;i<(num*1+1);i++){
				document.getElementById("jtcy"+i).style.display='';
			}
			for(var j=1;j<11-num;j++){							
				document.getElementById("jtcy"+(num*1+j)).style.display='none';
			}
		}
		
		function initPage(){
				document.getElementById("num").value=1;
				change();
		}
		
		function checkPage(){
			var ssbh = document.getElementById("ssbh").value;
			var qsdh = document.getElementById("qsdh").value;
			var flag = false;
			getStuDetails.checkQsdhAndQsbh(ssbh,qsdh,function(data){
				if(data==""){
					flag = true;
					refreshForm('stu_info_add.do?method=modiStuInfo&act=save&jg='+ document.getElementById('jgdb').value) ;
				}else{
					alert(data+",��˶�׼ȷ��");
					flag =  false;
				}
			});			
			return flag;
		}
		
	function loadShi(){
		var shen = document.getElementById("jgshen").value;	
		getStuDetails.getShiList(shen,function(data){
			if (data.shiList != null) {
					var objId = "jgshi";				
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.shiList,"shidm","shimc");
					}
				}else{
					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
				}	
			if (data.xianList !=null){
				var objId = "jgxian";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.xianList,"xiandm","xianmc");
					}
			}
		});
	}

	function loadXian(){
		var shi = document.getElementById("jgshi").value;	
		getStuDetails.getXianList(shi,function(data){
			if (data != null) {
					var objId = "jgxian";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);							
						DWRUtil.addOptions(objId,data,"xiandm","xianmc");
					}
				}else{
					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
				}		
		});
	}
	function loadInit(){
		var xxdm = document.getElementById("xxdm").value;
		if(xxdm == "10690"){
			var sfdkVal = document.getElementById("sfdkVal").value;
			if(sfdkVal =="" || sfdkVal==null){
				document.getElementById("sfdkf").checked=true;
			}
		}
	
	}
	
	function saveInfo(){
		var xxdm = document.getElementById("xxdm").value;
		document.getElementById("jg").value="";
		if(xxdm == "10690"){//��������<bean:message key="lable.xsgzyxpzxy" />
			var jgs = document.getElementById("jgshen").value;
			var jgshi = document.getElementById("jgshi").value;
			var jgx = document.getElementById("jgxian").value;
			var jg = document.getElementById("jg").value;
			if(jgs!="" && jgs!=null){
				jg += document.getElementById("jgshen").options[document.getElementById("jgshen").selectedIndex].text;
			}			
			if(jgshi!="" && jgshi!=null){
				jg += document.getElementById("jgshi").options[document.getElementById("jgshi").selectedIndex].text;
			}
			if(jgx!="" && jgx!=null){
				jg += document.getElementById("jgxian").options[document.getElementById("jgxian").selectedIndex].text;
			}
		}
		if(xxdm == "10856"){//�Ϻ�����
			if($("sfzh")){
			var sfzh = document.getElementById('sfzh').value;
			if(sfzh!=""){
				if(!checkSfzh(sfzh)){
					return false;
				}
			}
	}
		}
		document.getElementById("jg").value=jg;
		refreshForm('stu_info_add.do?method=modiStuInfo&act=save');
	}
	</script>
</head>
<body onload="loadInit();">
	<html:form action="/stu_info_add.do" method="post">
		<input type="hidden" id="url" name="url" value="/stu_info_add.do?method=modiStuInfo" />
		<input type="hidden" id="disableEle" name="disableEle" value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc-xh" />
		<input type="hidden" id="sfdkVal" value="${rs.sfdk}" />
		<input type="hidden" id="xxdm" value="${xxdm}" />
		<html:hidden property="jg" styleId="jg" />
		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em>
				<a>
					<logic:equal name="userOnLine" value="student" scope="session">
						ѧ����Ϣ - ��Ϣ�޸� - �޸ĸ�����Ϣ				
					</logic:equal>
					<logic:equal name="userOnLine" value="teacher" scope="session">				
						ѧ����Ϣ - ��Ϣ�޸� - �޸�ѧ����ͥ��Ϣ				
					</logic:equal>
				</a>
			</p>
		</div>
		<logic:equal name="userType" value="admin" scope="session">
			<br />
			<br />
			<br />
			<p align="center">
				��ҳ��ֻ��ѧ����<bean:message key="lable.xsgzyxpzxy" />�û����Է���
			</p>
		</logic:equal>

		<logic:equal name="userType" value="xx" scope="session">
			<br />
			<br />
			<br />
			<p align="center">
				��ҳ��ֻ��ѧ����<bean:message key="lable.xsgzyxpzxy" />�û����Է���
			</p>
		</logic:equal>
	
		<logic:equal name="sqsjFlag" value="1">
				<script>
		   			 alert("�����趨ʱ�䷶Χ��,�ݲ������޸�!");
		    		 location.href="about:blank";
	  			 	</script>
			</logic:equal>

		<!--ѧ����½-->
		<logic:equal name="userOnLine" value="student" scope="session">
			

			<logic:present name="rs">
			<!--ѧ����Ϣ�޸Ĳ�����ʱ��-->
			<%@ include file="/xsxx/common/xsxx_xxxg_ssxg.jsp"%>				
			</logic:present>
		</logic:equal>
		<!--endѧ����½-->
		<!--��ʦ��½-->
		<%@ include file="/xsxx/common/xsxx_xxxg_jsxg.jsp"%>
		<logic:equal name="dataSaved" value="ok" scope="request">
			<script>
 				alert("����ɹ���");
 			</script>
		</logic:equal>

		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��!");
			</script>
		</logic:equal>

	</html:form>
</body>
</html>
