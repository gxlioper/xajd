<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="xsgzgl.gygl.comm.GyglNewInit"%>
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
		function getQsh(){
			jQuery.setAjax({async:false});

			jQuery.getJSON('',{lddm:jQuery('#lddm').val()},function(){
				
			});

			jQuery.setAjax({async:true});
		}
	</script>
</head>
<body>
	
	<html:form action="/gyglnew_cwgl" method="post">
		<input type="hidden" id="refreshParent" value="true"/>
		<input type="hidden" name="pkValue" value="${rs.pkValue }"/>	
		<%--<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��λ��Ϣ�޸�</a>
			</p>
		</div>		
		--%><div class="prompt" id="span_qsh" style="display: none">
	       <h3><span>��ʾ��</span></h3>
	       <p>�����Һ��ڱ�¥�����Ѵ��ڣ�<br/></p>
	   	</div>
		
		<div style='tab;width:100%;height:460px;overflow-x:hidden;overflow-y:auto;'>
		<div class="tab" style="overflow-y:auto; overflow-x:hidden">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>��λ��Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="16%">
					¥������
				</th>
				<td align="left" width="34%" nowrap="nowrap">
					${rs.ldmc }
				</td>
				
				<th  width="16%">
					���Һ�				
				</th>
				<td  width="34%">
					${rs.qsh }
				</td>
			</tr>

			<tr>
				<th>��λ��</th>
				<td>
					${rs.cwh }
				</td>
				<th>���ҵ绰</th>
				<td>${rs.qsdh }</td>
			</tr>
			<tr>
				<th>�շѱ�׼</th>
				<td>${rs.sfbz }</td>
				<th>�����꼶</th>
				<td>${rs.nj }</td>
			</tr>
			<tr>
				<th>����<bean:message key="lable.xsgzyxpzxy" /></th>
				<td>${rs.xymc }</td>
				
				<th><%if(GyglNewInit.CWFPDX!=null&&"bjdm".equals(GyglNewInit.CWFPDX)){ %>�����༶<%}else if(GyglNewInit.CWFPDX!=null&&"zydm".equals(GyglNewInit.CWFPDX)){%>����רҵ<%} %></th>
				<td><%if(GyglNewInit.CWFPDX!=null&&"bjdm".equals(GyglNewInit.CWFPDX)){ %>${rs.bjmc }<%}else if(GyglNewInit.CWFPDX!=null&&"zydm".equals(GyglNewInit.CWFPDX)){%>${rs.zymc }<%} %></td>
			</tr>

			<tr>
				<th>
					��ע
				</th>
				<td colspan="3" style="word-break:break-all;width:100%">
					<html:textarea property='bz' style="width:95%" styleId="bz" rows='3' value="${rs.bz}" readonly="true"/>
				</td>
			</tr>
			</tbody>
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>��λ����</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>�Ƿ���</th>
					<td>
						${rs.sfbl }
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>
						������ע
					</th>
					<td colspan="3" style="word-break:break-all;width:100%">
						<html:textarea property='bz' style="width:95%" styleId="bz" rows='2' value="${rs.blbz}" readonly="true"/>
					</td>
				</tr>
			</tbody>
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>ס��ѧ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>����</th>
					<td>
						${rs.xm }
					</td>
					<th>ѧ��</th>
					<td>${rs.xh }</td>
				</tr>
				<tr>
					<th>�꼶</th>
					<td>
						${rs.xsnj }
					</td>
					<th><bean:message key="lable.xb" /></th>
					<td>${rs.xsxymc }</td>
				</tr>
				<tr>
					<th>רҵ</th>
					<td>
						${rs.xszymc }
					</td>
					<th>�༶</th>
					<td>${rs.xsbjmc }</td>
				</tr>
				<tr>
					<th>��סʱ��</th>
					<td>${rs.rzsj }</td>
					<th>��סԭ��</th>
					<td>${rs.rzyymc }</td>
				</tr>
				<tr>
					<th>��ϵ��ʽ</th>
					<td colspan="3">${rs.lxfs }</td>
				</tr>
			</tbody>
			</table>
			</div>
			<div class="border_01 formbox">
			<!--���й������� ����start-->
			<h3 class="datetitle_01">
				<span>����Ա��������Ϣ</span>
			</h3>
				<div style="height: 150px;overflow-y:auto; overflow-x:hidden">
					<table width="100%" class="datelist tablenowrap" border="1">
						<thead>
							<tr>
								<th>
									ְ����
								</th>
								<th>
									����
								</th>
								<th>
									��ϵ�绰
								</th>
								<th>
									ְλ
								</th>
								
							</tr>
						</thead>
						<tbody>
							<logic:iterate id="fdy" name="fdyList">
								<tr class="alt">
									<td>${fdy.zgh}</td>
									<td>${fdy.xm}</td>
									<td>${fdy.lxdh}</td>
									<td>${fdy.zw}</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
			</div>
		<div>
		<table width="97%" class="formlist">
			<tfoot>
					<tr align="right">
						<td colspan="5">
							<div class="btn">
								<button type="button" name="�ر�" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		</div>
	</html:form>
</body>
</html>
