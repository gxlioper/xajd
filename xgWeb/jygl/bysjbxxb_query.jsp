<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
	function jyxyxxsh(doType){
			var url ="/xgxt/JyglViewMoreinfo.do?doType=view&shenhe=shenhe&pkValue=";
			var pkValue ="";
		
			if (doType == "shenhe") {
		   		if (curr_row == null) {
					alert("��ѡ��Ҫ��˵����ݣ�\n��������Ӧ���У�");
					return false;
		    	} else {	 
		   		 	pkValue = curr_row.getElementsByTagName("input")[0].value;
		    		url += pkValue;
		    		showTopWin(url, 800,600);
		    		return true;
		    	}
		 	}else{
		    	return true;
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
    
    
	function querygo(){
		 	document.forms[0].action = "/xgxt/bysjbxxquerydododo.do?act=go&doType=query";
		 	document.forms[0].submit();
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
	����ɾ��
	*/
      function del(url){
	    var RowsStr="!!#!!";
	
	    for (i=0; i<document.getElementsByName("pk").length; i++){
    	   if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	   }
	    }
	    document.forms[0].pkstring.value = RowsStr;
	
	       if (RowsStr=="!!#!!"){
	         alert("��ѡ��Ҫ����ɾ���ļ�¼��");
		   return false;
    	}
	
	    if (!confirm("ȷ��Ҫɾ����ѡ��¼��")){
		   return false;
	    }
	    document.forms[0].action=url;
        document.forms[0].submit();
     }

		function bysjbxxbdel(doType){
		var url = "/xgxt/JyglBysjbxxbDel.do?doType=del&doType2=query&pkValue=";
		var pkValue = "";
			
		if (doType == "del") {
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
		
		
		function viewMoreinfo(doType){
		var url ="/xgxt/JyglViewMoreinfo.do?doType=view&pkValue=";
		var pkValue ="";
		
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url,800, 600);
		 }
		}
		
		
		function bysjbxxbupdate(doType){
		var url ="/xgxt/turnJyglInfoUpdate.do?doType=update&pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
			 } else {
			if (confirm("ȷ��Ҫ�޸ĸ���������")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 800, 600);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
		
		
		function jyglxsjbxxbDataExport() {
	       document.forms[0].action = "/xgxt/jyglxsjbxxbDataExport.do?tableName=jygl_xsjbxxb";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
		</script>
	</head>

	<body>
		<html:form action="/data_search" method="post">
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name='realTable' scope="request" />" />
			<input type="hidden" name="pkstring" value="" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>��ҵ���� - ѧ����Ϣ - ѧ����Ϣ��ѯ</a>
				</p>
			</div>
			<div class="toolbox">
					<div class="buttonbox">
						<ul>
							<logic:equal name="who" value="teacher">
								<logic:equal value="10491" name="xxdm">
									<li>
										<a href="#" onclick="jyxyxxsh('shenhe')" class="btn_sh">
											������� </a>
									</li>
									<li>
										<a href="#" onclick="pass('/xgxt/xsxxsh.do?doType3=pass')"
											class="btn_shtg"> ͨ�� </a>
									</li>
									<li>
										<a href="#"
											onclick="notpass('/xgxt/xsxxsh.do?doType3=notpass')" class="btn_shtg">
											��ͨ�� </a>
									</li>
								</logic:equal>
							</logic:equal>
							<logic:equal value="12453" name="xxdm" scope="session">
								<a href="#" onclick="jyxyxxsh('shenhe')" class="btn_sh">
									������� </a>
							</logic:equal>
							<logic:equal name="who" value="teacher">
								<logic:equal value="10355" name="xxdm">
									<li>
										<a href="#" onclick="jyxyxxsh('shenhe')" class="btn_sh">
											������� </a>
									</li>
									<li>
										<a href="#" onclick="pass('/xgxt/xsxxsh.do?doType3=pass')"
											class="btn_shtg"> ͨ�� </a>
									</li>
									<li>
										<a href="#" onclick="notpass('/xgxt/xsxxsh.do?doType3=notpass')" class="btn_shtg">
											��ͨ�� </a>
									</li>
								</logic:equal>
							</logic:equal>
							<li>
								<a href="#"
									onclick="del('/xgxt/JyglBysjbxxbDelall.do?doType2=query&doType=delall')"
									class="btn_sc"> ɾ�� </a>
							</li>
							<li>
								<a href="#" onclick="bysjbxxbupdate('update')" class="btn_xg">
									�޸� </a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData();" class="btn_dr"> ���� </a>
							</li>
							<li>
								<a href="#" onclick="jyglxsjbxxbDataExport()" class="btn_dc">
									���� </a>
							</li>
						</ul>
					</div>

				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<input type="hidden" name="go" value="a" />
									<div class="btn">
										<button class="btn_cx" id="search_go" onclick="querygo()">
											��ѯ
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											����
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<td colspan="6">
									<logic:equal name="who" value="teacher">
										<bean:message key="lable.xsgzyxpzxy" />
										<html:select name="rs1" property="xymc" style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xymc"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<logic:equal name="who" value="fudaoyuan">
										<bean:message key="lable.xsgzyxpzxy" />
										<html:text name="rs1" property="xymc" style="width:150px"
											readonly="true" />
									</logic:equal>
								</td>
							</tr>
							<tr>
								<th>��ѧ���</th>
								<td><html:select name="rs1" property="nd" style="width:100px">
										<html:option value=""></html:option>
										<html:options collection="ndList" property="nj"
											labelProperty="nj" />
									</html:select></td>
								<th>��ҵ���</th>
								<td><html:select name="rs1" property="bynd" style="width:120px">
										<html:option value=""></html:option>
										<html:option value="2006">
										2006
									</html:option>
										<html:option value="2007">
										2007
									</html:option>
										<html:option value="2008">
										2008
									</html:option>
										<html:option value="2009">
										2009
									</html:option>
										<html:option value="2010">
										2010
									</html:option>
										<html:option value="2011">
										2011
									</html:option>
										<html:option value="2012">
										2012
									</html:option>
										<html:option value="2013">
										2013
									</html:option>
										<html:option value="2014">
										2014
									</html:option>
										<html:option value="2015">
										2015
									</html:option>
									</html:select></td>
									<th>ѧ�����</th>
									<td><html:select name="rs1" property="xslb" style="width:80px">
											<html:option value=""></html:option>
											<html:option value="ר����">
											ר����
										</html:option>
											<html:option value="������">
											������
										</html:option>
											<html:option value="�о���">
											�о���
										</html:option>
										</html:select>
									</td>
								</tr>
							<tr>
								<th>ѧ��</th>
								<td><input type="text" name="xsxh"
										value="<bean:write name="rs1" property="xsxh"/>" /></td>
								<th>����</th>
								<td><input type="text" name="name"
										value="<bean:write name="rs1" property="name"/>" /></td>
								<th>�Ա�</th>
								<td><html:select name="rs1" property="xbdm" style="width:80px">
										<html:option value=""></html:option>
										<html:option value="1">
										��
									</html:option>
										<html:option value="2">
										Ů
									</html:option>
									</html:select>
								</td>
							</tr>
							<logic:equal value="10355" name="xxdm">
									<tr>
									<th>���</th>
									<td><html:select name="rs1" property="sfsh" style="width:80px">
											<html:option value=""></html:option>
											<html:option value="1">δ���</html:option>
											<html:option value="2">��ͨ��</html:option>
											<html:option value="3">δͨ��</html:option>
										</html:select>
									</td>
									</tr>
									<td colspan="4"></td>
									</logic:equal>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp;
						<logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> 
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>
				<logic:notEmpty name="rs">
					<table summary="" class="dateline" id="rsTable" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="qbxz" value="all"
											onclick="chec('qbxz')" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="2" length="12">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<logic:equal value="12453" name="xxdm" scope="session">
										<logic:equal value="xx" name="userType">
											<td id="xxsh" onclick="tableSort(this)">
												ѧУ���
											</td>
										</logic:equal>
									</logic:equal>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand"
										ondblclick="viewMoreinfo('view')">
										<td align="center">
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
												<input type="checkbox" name="pk"
													value="<bean:write name="v"/>" />
											</logic:iterate>
										</td>
										
										<logic:iterate id="v" name="s" offset="2" length="12">
											<td align="center">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										
										<logic:equal value="12453" name="xxdm" scope="session">
											<logic:equal value="xx" name="userType">
												<logic:iterate id="v" name="s" offset="13" length="1">
													<td align="center">
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</logic:equal>
										</logic:equal>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						 <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
				</logic:notEmpty>
				</div>
				
				<logic:notEmpty name="delete">
					<logic:equal name="delete" value="ok">
						<script>
                      alert("ɾ���ɹ�!");
                    </script>
					</logic:equal>
					<logic:equal name="delete" value="no">
						<script>
                      alert("ɾ��ʧ��");
                    </script>
					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="delall">
					<logic:equal name="delall" value="ok">
						<script>
                      alert("����ɾ���ɹ�!");
                    </script>
					</logic:equal>
					<logic:equal name="delall" value="no">
						<script>
                      alert("����ɾ��ʧ��");
                    </script>
					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="allpass">
					<logic:equal name="allpass" value="ok">
						<script>
                       alert("����ͨ���ɹ���");
                       document.getElementById('query_go').click();
                    </script>
					</logic:equal>
					<logic:equal name="allpass" value="no">
						<script>
                      alert("����ͨ��ʧ�ܣ�");
                      document.getElementById('query_go').click();
                    </script>
					</logic:equal>
				</logic:notEmpty>

				<logic:notEmpty name="allnotpass">
					<logic:equal name="allnotpass" value="ok">
						<script>
                       alert("��������ɹ���");
                       document.getElementById('query_go').click();
                    </script>
					</logic:equal>
					<logic:equal name="allnotpass" value="no">
						<script>
                      alert("�������ʧ�ܣ�");
                      document.getElementById('query_go').click();
                    </script>
					</logic:equal>
				</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
