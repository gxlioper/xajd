<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ϵͳά�� - ���ݱ��� - ϵͳ����</a>
			</p>
		</div>
	
	
		<html:form action="/log_search" method="post">
		
		<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<logic:iterate name="rs" id="s">
						<table summary="" class="dateline" width="100%">
							<tbody>
								<tr onclick="rowOnClick(this)">
									<th>����ʱ��</th>
									<td>
										<bean:write name="s" property="BFSJ" />
										<input type="hidden" name="bfsj" id="bfsj" value="<bean:write name="s" property="BFSJ" />"/>
									</td>
									<th>�����ļ���</th>
									<td><bean:write name="s" property="BFWJM" /></td>
									<th>������</th>
									<td>
										<bean:write name="s" property="BFR" />
										<input type="hidden" name="bfr" id="bfr" value="<bean:write name="s" property="BFR" />"/>
									</td>
									<td>
										<button type="button" onclick="refreshForm('/xgxt/data_backup.do?act=del&pkValue='+'<bean:write name='s' property='BFR' />'+'<bean:write name='s' property='BFSJ' />')">
											ɾ ��
										</button>
									</td>
								</tr>
								<tr>	
									<th>����˵��</th>
									<td colspan="6">
										<bean:write name="s" property="BFSM" />
									</td>
								</tr>
							</tbody>
						</table>
					</logic:iterate>
				</logic:notEmpty>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���ݱ���</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="��  ��" onclick="if(confirm('ȷ��Ҫ������')){refreshForm('/xgxt/data_backup.do?act=save')}return false;">
										��  ��
									</button>
									<button type="button" name="��������" onclick="showTopWin('/xgxt/bak_set.do',400,310);">
										��������
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="20%">�±��ݣ�����˵��</th>
							<td>
								<textarea name="newBakInfo" style="width:100%" rows="3"
								onfocus="if(this.value=='��ʱ����')this.value=''"
								onblur="if(this.value=='')this.value='��ʱ����'" type="_moz">��ʱ����</textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>
		</html:form>
	</body>
</html>
