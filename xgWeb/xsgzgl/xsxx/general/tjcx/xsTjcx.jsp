<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!-- ���ߣ�qph. ��ע�������˵�ҳ�������ע��ȥ���Ǻ��ˣ��� -->

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
			jQuery(function(){
				var njid = jQuery("#nj").val();
				var xyid = jQuery("#xydm").val();
				var zyid = jQuery("#zydm").val();

				jQuery("#btn_fh").bind("click",function(){
					document.location.href="general_xsxx_tjcx.do?method=tjcxByBj&nj="+njid+"&xydm="+xyid+"&zydm="+zyid;
				});
				
			});

			function cxXs(){
				var njid = jQuery("#nj").val();
				var xyid = jQuery("#xydm").val();
				var zyid = jQuery("#zydm").val();
				var bjid = jQuery("#bjdm").val();
				allNotEmpThenGo("general_xsxx_tjcx.do?method=tjxsByBjdm&bjdm="+bjid+"&nj="+njid+"&xydm="+xyid+"&zydm="+zyid);
				}

			function sjDc(){
				var url = "general_xsxx_tjcx.do?method=dcsjByType&type=xs";
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";	
			}
			
		</script>
	</head>

	<body>
		<html:form action="/general_xsxx_tjcx" method="post">
		<html:hidden property="nj" name="xsxxTjcxForm" styleId="nj"/>
		<html:hidden property="xydm" name="xsxxTjcxForm" styleId="xydm"/>
		<html:hidden property="zydm" name="xsxxTjcxForm" styleId="zydm"/>
		<html:hidden property="bjdm" name="xsxxTjcxForm" styleId="bjdm"/>
		<input type="hidden" id="search_go" onclick="cxXs()"></input>
		<!-- �๦�ܲ����� -->
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<li>
						<a id="add" onclick="sjDc();" class="btn_dc"> �� �� </a>
					</li>
					<li>
						<a id="btn_fh" class="btn_fh"> �� �� </a>
					</li>
				</ul>
			</div>
		</div>
		
		<div  style="width:100%;height:450px;overflow-x:hidden;overflow-y:auto;">
				<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rsList">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> 
						<%
						ArrayList<String[]> rsList = (ArrayList<String[]>)request.getAttribute("rsList");
						String[] map = rsList.get(0);
						%>
						<logic:notEmpty name="rsList">
							�꼶:<font color="blue"><%=map[7]%></font>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xb" />:<font color="blue"><%=map[8]%></font>&nbsp;&nbsp;&nbsp;&nbsp;
							רҵ:<font color="blue"><%=map[9]%></font>&nbsp;&nbsp;&nbsp;&nbsp;�༶:<font color="blue"><%=map[10]%></font>
						</logic:notEmpty> 
				</h3>
				<logic:notEmpty name="rsList">
					<table summary="" class="dateline" align="" width="100%">
					<thead>
							<tr align="center" style="cursor:hand">
								<td>ѧ��</td>
								<td>����</td>
								<td>�Ա�</td>
								<td>���֤��</td>
								<td>����</td>
								<td>������ò</td>
								<td>��ϵ�绰</td>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rsList">
								<logic:iterate name="rsList" id="s" indexId="index">
								<tr>
									<logic:iterate id="v" name="s" offset="0" length="7">
									<td align="left">
									${v}
									</td>
								</logic:iterate>
								</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</table>
					</logic:notEmpty>
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=xsxxTjcxForm"></jsp:include>
					<script type="text/javascript">
						$('choose').className="hide";
					</script>	
			</div>
		</html:form>
	</body>
</html>
