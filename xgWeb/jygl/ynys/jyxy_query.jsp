<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	     function refreshtheweb(){   
	        document.forms[0].action = "JyxyDataSearch.do";
		 	document.forms[0].submit();
	     }
	     function querrytheinfo(){   
	        document.forms[0].action = "JyxyDataSearch.do?act=go";
		 	document.forms[0].submit();
	     }
	     
	     function viewMoreinfo(doType){
		    var url ="JyglJyxyViewMoreinfo.do?doType=view&pkValue=";
		    var pkValue ="";
		
		    if (doType == "view"){
		       pkValue = $("pk").value;
		       url += pkValue;
		       showTopWin(url, 680, 400);
		 }
		}
		
		 function deltheinfo(){  
		     var pkValue = $("pk").value; 
		     if (curr_row == null) {
			      alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
			      return false;
		    } else {
			   if (confirm("ȷ��Ҫɾ������������")) {
                      document.forms[0].action = "JyxyDataSearch.do?doType2=del&act=go&pkValue="+pkValue;
		 	          document.forms[0].submit();
				return true;
			} else {
				return false;
			}   
			}
       
	     }
	     
	     function jyxyupdate(doType){
		    var url ="savejyxyupdate.do?pkValue=";
		    var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
			 } else {
			if (confirm("ȷ��Ҫ�޸ĸ���������")) {
				 pkValue = $("pk").value;
		         url += pkValue;
		         showTopWin(url, 760, 480);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}

	</script>
	<body>
		<html:form action="/JyxyDataSearch" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ҵ���� - ��ҵЭ�鷽�� - ��ҵЭ���ѯ
				</div>
			</div>
			<fieldset>
				<legend>
					�� ѯ
				</legend>

				<table width="100%" class="tbstyle">
					<thead>
						<tr style="cursor:hand">
							<td>
								ѧ�ţ�
								<input type="text" name="xh"
									value="<bean:write name="rs1" property="xh"/>" />
								&nbsp;&nbsp;&nbsp;&nbsp;������
								<input type="text" name="xm"
									value="<bean:write name="rs1" property="xm"/>"
									style="width:95px" />
								&nbsp;&nbsp;&nbsp;&nbsp;�Ա�
								<html:select name="rs1" property="xb" style="width:60px">
									<html:option value=""></html:option>
									<html:option value="��">
										��
									</html:option>
									<html:option value="Ů">
										Ů
									</html:option>
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;�꼶��
								<html:select name="rs1" property="nj" style="width:150px"
									onchange="refreshtheweb();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<button class="button2" style="height:40px" id="query_go"
									onclick="querrytheinfo();">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name="who" value="xx">
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select name="rs1" property="xydm" style="width:150px"
										onchange="refreshtheweb();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:equal name="who" value="xy">
							    <bean:message key="lable.xsgzyxpzxy" />��
						        <html:select name="rs1" property="xydm"
										style="width:150px" disabled="true"
										onchange="refreshtheweb();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									<html:hidden name="rs1" property="xydm" />
								</logic:equal>
								&nbsp;&nbsp;&nbsp; רҵ��
								<html:select name="rs1" property="zydm" style="width:150px"
									onchange="refreshtheweb();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;&nbsp; �༶��
								<html:select name="rs1" property="bjdm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<font color="blue">��ʾ��˫��һ�в鿴��ϸ��������ͷ��������</font>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="viewMoreinfo('view')" align="center">
								<td>
								<input type="hidden" name="pk"
									value="<bean:write name="s" property="xh" />" />
									<bean:write name="s" property="�к�" />
								</td>
								<td>
									<bean:write name="s" property="xh" />
								</td>
								<td>
									<bean:write name="s" property="xm" />
								</td>
								<td>
									<bean:write name="s" property="xb" />
								</td>
								<td>
									<bean:write name="s" property="xymc" />
								</td>
								<td>
									<bean:write name="s" property="zymc" />
								</td>
								<td>
									<bean:write name="s" property="bjmc" />
								</td>
								<td>
									<bean:write name="s" property="fbsj" />
								</td>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>



			<div class="buttontool" id="btn"
				style="position: absolute;left:0px;top:100px" width="100%">
				<button class="button2" onclick="jyxyupdate('update')"
					style="width:80px">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="deltheinfo();" style="width:80px">
					ɾ ��
				</button>
			</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
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
	</body>
</html>

