<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
		
	</script>
</head>
<body>
	<html:form action="/gyglnew_tsgl" method="post">
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>������Ϣ�鿴</a>
			</p>
		</div>
		
		<div class="tab">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>ѧ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="16%">
					ѧ��
				</th>
				<td width="34%">
					<bean:write name="map" property="xh"/>
				</td>
				<th align="right" width="16%">
					����
				</th>
				<td width="34%">
					<bean:write name="map" property="xm"/>
				</td>
			</tr>
			<tr>
				<th align="right" width="16%">
					�Ա�
				</th>
				<td width="34%">
					<bean:write name="map" property="xb"/>
				</td>
				<th align="right" width="16%">
					�꼶
				</th>
				<td width="34%">
					<bean:write name="map" property="nj"/>
				</td>
			</tr>
			<tr>
				<th align="right" width="16%">
					<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td width="34%">
					<bean:write name="map" property="xymc"/>
				</td>
				<th align="right" width="16%">
					רҵ
				</th>
				<td width="34%">
					<bean:write name="map" property="zymc"/>
				</td>
			</tr>
			
			<tr>
				<th>�༶</th>
				<td>${map.bjmc }</td>
				<th align="right" width="16%">
					��ǰס������
				</th>
				<td align="left" width="34%">
					<font color="blue">
						<logic:notEmpty name="map" property="dqldmc">
							<bean:write name="map" property="dqldmc"/>_
							<bean:write name="map" property="dqqsh"/>_
							<bean:write name="map" property="dqcwh"/>
						</logic:notEmpty>					
						<logic:empty name="map" property="dqldmc">
							��
						</logic:empty>
					</font>
				</td>
			</tr>
			</tbody>			
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>������Ϣ</span>
					</th>
				</tr>
			</thead>
			
			<tbody>
			<tr>
				<th align="right" width="16%">
					����ԭ��				
				</th>
				<td align="left" width="34%">
					<bean:write name="map" property="tsyy"/>
				</td>
				<th align="right" width="16%">
					��������
				</th>
				<td align="left" width="34%">
					<font color="blue">
						<bean:write name="map" property="ldmc"/>_
						<bean:write name="map" property="qsh"/>_
						<bean:write name="map" property="cwh"/>
					</font>
				</td>
			</tr>
			<tr>
				<th>��סʱ��</th>
				<td>${map.rzsj }</td>
				<th align="right" width="16%">
					����ʱ��				
				</th>
				<td align="left" width="34%">
					<bean:write name="map" property="tssj"/>
				</td>
			</tr>
			<tr>
				<th align="right" width="16%">
					���޲�����
				</th>
				<td align="left" width="34%">
					<bean:write name="map" property="tsczr"/>
				</td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>
					��ע
				</th>
				<td colspan="3">
					<bean:write name="map" property="bz"/>
				</td>
			</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz"></div>
			          <div class="btn">
			            <button type="button"  name="�ر�" id="buttonClose" onclick="Close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
</body>
</html>
