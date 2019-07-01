<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<base target="_self">
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" />
	<script language="javascript" src="js/lrh_new_js.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/sjstProcFun.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript">
		function sjst_add(){
			showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=sjst_st_search',820,550);
		}
	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();">
		<html:form action="/xljk_xlcs_tkwh" method="post">
		<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>������ - ������� - ���ά�� - ���ά�� - �Ծ�����ά��</a>
				</p>
			</div>
			
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="sjlsh" name="sjlsh"
					value="<bean:write name="rs" property="sjlsh"/>" />
					
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>���ά��</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button class="" onclick="lrh_StSjDoSave()" id="buttonSave">
										����
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button class="" onclick="Close();return false;" 
										id="buttonClose">
										�ر�
									</button>
									</div>
								</td>
							</tr>
						</tfoot>		
				
					<tbody>
						<tr>
							<td align="center" colspan="2">
								<font color="red">*</font>�Ծ���
								<bean:write name="rs" property="sjm" />
							</td>
						</tr>
						<tr>
							<td align="center">
								��ѡ�����б�(���----��������)
								<br>
								<font color="red">��ʾ��סCtrl��(���������������ƶ�)<br>�ɽ��ж�ѡ</font>
							</td>
							<td align="center">
								��ѡ������:
								<div id="count">
									<bean:write name="stNum" scope="request" />
								</div>
							</td>
						</tr>
						<tr>
							<td align="center" width="70%">
								<html:select property="yxstlb" styleId="yxstlb" size="20"
									style="width: 90%" multiple="multiple">
									<logic:notEmpty name="stList">
										<html:options collection="stList" property="stlsh"
											labelProperty="stlshstxh" />
									</logic:notEmpty>
								</html:select>
							</td>
							<td align="center" valign="middle" width="30%">
								<br>
								<button onclick="sjst_add()" class="btn_zj" title="����Ծ���������">
									+ ����
								</button>
								<br>
								<br>
								<br>
								<br>
								<button onclick="lrh_updateStList('del')" class="btn_sc" title="ɾ�����Ծ�������">
									- ɾ��
								</button>							
								<br>
								<br>
								<br>
								<br>
								<button onclick="lrh_updateStList('clear')" class="btn_hsz"
									" title="��ո��Ծ���������">
									�� ���
								</button>
								<br>
								<br>
								<br>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
