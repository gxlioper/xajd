<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/qgzxFunction.js"></script>
	</head>
	<body>
		<html:form action="/data_search" method="post">
			<input type="hidden" id="realTable" value="${realTable}" />
			<input type="hidden" id="tableName" value="${tableName}" />
			<input type="hidden" id="pk" value="${pk}" />
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ѧ���� - ���� - ��ҵ����Ϣ�����ѯ</a>
				</p>
			</div>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<th align="center">
								ѧ�ţ�
								<bean:write name="userName" scope="session" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								������
								<bean:write name="userNameReal" scope="session" />
							</th>
						</tr>
					</thead>
			</table>
			
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
							��ѯ���&nbsp;&nbsp;<font color="blue">��¼����${rsNum }</font> 
						</span>
					</h3>
					<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="2" scope="request">
									<td id="${tit.en}" onclick="tableSort(this)">
										${tit.cn}
									</td>
								</logic:iterate>
							</tr>
						</thead>
							
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
								<logic:iterate id="v" name="s" offset="2">
									<td align="center">
										${v }
									</td>
								</logic:iterate>							
							</tr>
						</logic:iterate>
					</table>
			</logic:notEmpty>
			</div>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
		</html:form>
	</body>
</html>
