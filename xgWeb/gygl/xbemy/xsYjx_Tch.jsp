<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>ѧ�������</a>
			</p>
		</div>
		<!-- ���� end-->
		<FORM action="/XsgyglDispatch.do?method=xsYjx" method="post">
			<button class="button2" style="display: none;height:0px"
				id="search_go"
				onclick="refreshForm('/xgxt/XsgyglDispatch.do?method=xsYjx')"></button>
			<!-- ҳǩ end-->
			<div class="toolbox">
			<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ����б�&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr>
								<td onclick="tableSort(this)" align="center">
									����
								</td>
								<td onclick="tableSort(this)" align="center">
									������
								</td>
								<td onclick="tableSort(this)" align="center">
									����ʱ��
								</td>
								<td onclick="tableSort(this)" align="center">
									���޻ظ�
								</td>
								<td align="center">
									����
								</td>
							</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<logic:iterate id="list" name="rs">
							<tr onmouseover="rowOnClick(this)">
								<td>
									<a
										href="/xgxt/XsgyglDispatch.do?method=viewYjXx&id=<bean:write name="list" property="id"/>"
										target=_black><bean:write name="list" property="title" />
									</a>
								</td>
								<td>
									<bean:write name="list" property="xh" />
								</td>
								<td>
									<bean:write name="list" property="pubdate" />
								</td>
								<td>
									<bean:write name="list" property="ywhf" />
								</td>
								<logic:equal value="no" name="writeAble">
								<td></td>
								</logic:equal>
								<logic:equal value="yes" name="writeAble">
									<td>
								<logic:equal value="��" name="list" property="ywhf">
									<a href="#"onclick="showOpenWindow('/xgxt/XsgyglDispatch.do?method=yjHf&act=add&id=<bean:write name="list" property="id"/>',800,500)">
												�ظ�</a>/
       							</logic:equal>
								<logic:equal value="��" name="list" property="ywhf">
									<a href="#"onclick="showOpenWindow('/xgxt/XsgyglDispatch.do?method=yjHf&act=modi&id=<bean:write name="list" property="id"/>',800,500)">
												�޸Ļظ�</a>/
       							</logic:equal>
									<a href="#" onclick="if(confirm('ȷʵҪɾ�����������')){location='/xgxt/XsgyglDispatch.do?method=xsYjx&doType=del&id=<bean:write name="list" property="id"/>';}">ɾ��</a>
									</td>
								</logic:equal>
							</tr>
						</logic:iterate>
							<!--���� end-->
						</table>
						<!--��ҳ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=gyglTyForm"></jsp:include>
						<!--��ҳend-->
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>
		</FORM>
	</body>
</html>
<logic:equal value="no" name="writeAble">
<script language="javascript" >
alert("��ֻ�ж�Ȩ�ޣ�");
</script>
</logic:equal>
