
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
	    function grjlquerygo(){
		 	document.forms[0].action = "/xgxt/grjlquerysh.do?act=go&doType=query";
		 	document.forms[0].submit();
        }
		
		function viewMoreinfo(doType){
		var url ="/xgxt/grjlViewMoreinfo.do?doType=view&pkValue=";
		var pkValue ="";
		
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 840, 600);
		 }
		}
		
		
		function grjlUpdate(doType){
		var url ="/xgxt/grjlUpdate.do?pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
			 } else {
			if (confirm("ȷ��Ҫ�޸ĸ���������")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 840, 600);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
		function JyglGrjlDel(doType2){
		var url = "/xgxt/JyglGrjlDel.do?doType2=del&doType=query&act=go&pkValue=";
		var pkValue = "";
			
		if (doType2 == "del") {
		   if (curr_row == null) {
			alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
			return false;
		    } else {
			if (confirm("ȷ��Ҫɾ������������")) {
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += pkValue;
				refreshForm(url);
				return true;
			} else {
				return false;
			}
		  }
		   return;
	      }
		}
		
		function grjlsh(doType){
			var url ="/xgxt/grjlshwindow.do?doType=shenhe&pkValue=";
			var pkValue ="";
		
			if (doType == "shenhe") {
		   		if (curr_row == null) {
					alert("��ѡ��Ҫ��˵����ݣ�\n��������Ӧ���У�");
					return false;
		    	} else {	 
		   		 	pkValue = curr_row.getElementsByTagName("input")[0].value;
		    		url += pkValue;
		    		showTopWin(url, 840, 600);
		    		return true;
		    	}
		 	}else{
		    	return true;
		 	}
		}
    /*
	ȫ��ѡ��
	*/
      function chec(){
         for(i=0;i<document.getElementsByName("pk").length;i++){
      	    document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
         }
      }		
	
	/*
	�������ͨ��
	*/
	 function pass(url){
	   var RowsStr="!!#!!";
	
	   for (i=0; i<document.getElementsByName("pk").length; i++){
		 if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	 }
	   }
	   document.forms[0].pkstring.value = RowsStr;
	
	   if (RowsStr=="!!#!!"){
		  alert("��ѡ��Ҫ������˵ļ�¼��");
		  return false;
	   }
	
	   if (!confirm("ȷ��Ҫͨ����ѡ��¼��")){
		  return false;
	   }
	   document.forms[0].action=url;
       document.forms[0].submit();
    }
    
    /*
	������˷��
	*/
    function notpass(url){
	var RowsStr="!!#!!";
	
	  for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	  }
	  }
	  document.forms[0].pkstring.value = RowsStr;
	
	  if (RowsStr=="!!#!!"){
		alert("��ѡ��Ҫ������˵ļ�¼��");
		return false;
	  }
	
	  if (!confirm("ȷ��Ҫ�����ѡ��¼��")){
		return false;
	  }
	document.forms[0].action=url;
    document.forms[0].submit();
   }
    function dataExportzjlg(){
    	document.forms[0].action = "/xgxt/dataExportzjlg.do";
    	document.forms[0].target = "_blank";
    	document.forms[0].submit();
    	document.forms[0].target = "_self";
    }
	</script>
	</head>
	<body>
		<html:form action="/data_search" method="post">
			<input type="hidden" name="pkstring" value="" />
			<logic:notEmpty name="who">
				<input type="hidden" id="ldgxfdy" name="ldgxfdy" value="<bean:write name="who"/>" />
			</logic:notEmpty>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ѧ����Ϣ - ���˼������</a>
				</p>
			</div>
			
			<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_shtg" onclick="pass('/xgxt/grjlshallpass.do?doType=query&doType3=pass&act=go')">����ͨ��</a></li>
						<li><a href="#" class="btn_shbtg" onclick="notpass('/xgxt/grjlshallpass.do?doType=query&doType3=notpass&act=go')">�������</a></li>
						<li><a href="#" class="btn_sh" onclick="grjlsh('shenhe')">���</a></li>
						<li><a href="#" class="btn_xg" onclick="grjlUpdate('update')">�޸�</a></li>
						<li><a href="#" class="btn_sc" onclick="JyglGrjlDel('del')">ɾ��</a></li>
						<li><a href="#" class="btn_dc" onclick="dataExportzjlg()">����</a></li>							
					</ul>
				</div>
			</div>
			
			<div class="searchtab">		
				<table width="100%" class="">
					<tbody>
						<tr>
							<logic:equal name="who" value="teacher">
								<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td><html:select name="rs1" property="xymc" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xymc"
											labelProperty="xymc" />
									</html:select></td>
							</logic:equal>
							<logic:equal name="who" value="fudaoyuan">
								<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td><html:text name="rs1" property="xymc" style="width:150px"
										readonly="true" /></td>
							</logic:equal>
							<th>ѧУ���</th>
							<td><html:select name="rs1" property="xxsh" style="width:127px">
								<html:option value=""></html:option>
								<html:option value="δ���">δ���</html:option>
								<html:option value="��ͨ����">��ͨ����</html:option>
								<html:option value="δͨ��X">δͨ��X</html:option>
							</html:select></td>
							<th>����ʱ��</th>
							<td><html:select name="rs1" property="xjsj" style="width:150px">
								<html:option value=""></html:option>
								<html:option value="-1">����</html:option>
								<html:option value="-2">������</html:option>
								<html:option value="-7">һ����</html:option>
								<html:option value="-15">������</html:option>
								<html:option value="-30">һ����</html:option>
								<html:option value="-90">������</html:option>
								<html:option value="-180">������</html:option>
								<html:option value="-365">һ����</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td><html:text name="rs1" property="xsxh" style="width:150px" /></td>
							<th>�� ��</th>
							<td><html:text name="rs1" property="name" /></td>
							<th>�Ա�</th>
							<td><html:select name="rs1" property="xb" style="width:60px">
									<html:option value=""></html:option>
									<html:option value="��">
										��
									</html:option>
									<html:option value="Ů">
										Ů
									</html:option>
								</html:select></td>
						</tr>
						<tr>
							<th>�꼶</th>
							<td><html:select name="rs1" property="nj" style="width:80px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<td colspan="4">
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
							<div class="btn">
								<button id="query_go" onclick="grjlquerygo()">
									��ѯ
								</button>
								 <button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
									����
								 </button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			
			<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
							<font color="red">δ�ҵ��κμ�¼��</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
						</span>
					</h3>
					<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')"/>
								</td>
								<logic:notEqual value="12453" name="xxdm" scope="session">
								<logic:iterate id="tit" name="topTr" offset="1" length="9">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								</logic:notEqual>
								<logic:equal value="12453" name="xxdm" scope="session">
								<logic:iterate id="tit" name="topTr" offset="1" length="8">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<logic:equal value="xx" name="userType" scope="session">
									<logic:iterate id="tit" name="topTr" offset="9" length="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								</logic:equal>
								<logic:iterate id="tit" name="topTr" offset="10" length="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								</logic:equal>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="viewMoreinfo('view')">
								<td align="center">
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="pk"
											value="<bean:write name="v"/>" />
									</logic:iterate>
								</td>
								<logic:equal value="12453" name="xxdm" scope="session">
								<logic:iterate id="v" name="s" offset="1" length="8">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:equal value="xx" name="userType" scope="session">
									<logic:iterate id="v" name="s" offset="9" length="1">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								</logic:equal>
								
									<logic:iterate id="v" name="s" offset="10">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								</logic:equal>
								<logic:notEqual value="12453" name="xxdm" scope="session">
								<logic:iterate id="v" name="s" offset="1" length="9">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								</logic:notEqual>
							</tr>
						</logic:iterate>
					</table>
			</logic:notEmpty>
			</div>

			<logic:notEmpty name="delete">
				<logic:equal name="delete" value="ok">
					<script>
                       alert("ɾ���ɹ���");
                    </script>
				</logic:equal>
				<logic:equal name="delete" value="no">
					<script>
                      alert("ɾ��ʧ�ܣ�");
                    </script>
				</logic:equal>
			</logic:notEmpty>

			<logic:notEmpty name="allpass">
				<logic:equal name="allpass" value="ok">
					<script>
                       alert("����ͨ���ɹ���");
                    </script>
				</logic:equal>
				<logic:equal name="allpass" value="no">
					<script>
                      alert("����ͨ��ʧ�ܣ�");
                    </script>
				</logic:equal>
			</logic:notEmpty>
			
			<logic:notEmpty name="allnotpass">
				<logic:equal name="allnotpass" value="ok">
					<script>
                       alert("��������ɹ���");
                    </script>
				</logic:equal>
				<logic:equal name="allnotpass" value="no">
					<script>
                      alert("�������ʧ�ܣ�");
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
