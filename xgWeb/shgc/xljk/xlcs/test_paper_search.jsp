<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
	function xljk_tkwh_view(){
		var sjlsh = curr_row.cells[0].innerText;
		showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=View&sjlsh='+sjlsh,500,400);
	}
	
	function xljk_paper_add(){
		showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=Insert',500,400);
	}
	
	function xljk_paper_modi(){
		if (curr_row == null) {
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
		}else{
			pkValue =curr_row.cells[0].innerText;
			url="/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=Modi&sjlsh="+pkValue;
			showTopWin(url,500,400);
		}
	}
	
	function xljk_paper_del(){
		if (curr_row == null) {
			alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
			return false;
		}else if (confirm("ȷ��Ҫɾ������������")) {
			pkValue =curr_row.cells[0].innerText;
			url="/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=Del&sjlsh="+pkValue;
			underDealWith();
			refreshForm(url);
		}
	}
	</script>
	</head>
	<body>
		<html:form action="/xljk_xlcs_tkwh" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${tips}</a>
				</p>
			</div>

			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="buttonxsbj" name="buttonxsbj" value="" />

			<div class="toolbox">
				<!-- ��ť -->

				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" class="btn_zj" onclick="xljk_paper_add()"> �����Ծ� </a>
							</li>
							<li>
								<a href="#" class="btn_xg" onclick="xljk_paper_modi()"> �޸��Ծ� </a>
							</li>
							<li>
								<a href="#" class="btn_sc" onclick="xljk_paper_del()"> ɾ���Ծ� </a>
							</li>
							<li>
								<a href="#" class="btn_sz"
									onclick="showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=st_index',820,550)"> ���ά�� </a>
							</li>
							<li>
								<a href="#" class="btn_sz"
									onclick="showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=zjwh',730,550)"> ���ά�� </a>
							</li>
						</ul>
					</div>
				</logic:equal>


				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="12">
									<div class="btn">
										<button class="" style="" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=search')">
											��ѯ
										</button>
										<input type="hidden" name="go" value="a" />
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											����
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr align="left">
								<th align="left">
									�Ծ���
								</th>
								<td >
									<html:select property="sjlsh" style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="sjList" property="SJLSH"
											labelProperty="SJM" />
									</html:select>
&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<th>&nbsp;&nbsp;&nbsp;</th>
								<td>&nbsp;&nbsp;&nbsp;</td>
								<th>&nbsp;&nbsp;&nbsp;</th>
								<td>&nbsp;&nbsp;&nbsp;</td>
								<th>&nbsp;&nbsp;&nbsp;</th>
								<td>&nbsp;&nbsp;&nbsp;</td>
								<th>&nbsp;</th>
								<td>&nbsp;</td>
								<th>&nbsp;</th>
								<td>&nbsp;</td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rs">
			 		��ǰ�Ծ�����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">
	
						<table summary="" class="dateline" align="center"
							width="100%">
							<thead>
								<tr align="" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand"
										ondblclick="xljk_tkwh_view()">
										<td>
											<bean:write name="s" property="sjlsh" />
										</td>
										<td>
											<bean:write name="s" property="sjm" />
										</td>
										<td>
											<bean:write name="s" property="sjxd" />
										</td>
										<td>
											<bean:write name="s" property="sjxsbj" />
										</td>
										<td>
											<bean:write name="s" property="jrsj" />
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
					<!--��ҳ��ʾ-->

					<!--��ҳ��ʾ-->
				</logic:notEmpty>
			</div>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="del success">
					<script>
						alert("ɾ���ɹ�!");
					</script>
				</logic:equal>
				<logic:equal name="message" value="no">
					<script>
					alert("ɾ��ʧ��!");
					document.getElementById("tmpdiv").innerHTML = "";
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>

	</body>
</html>
