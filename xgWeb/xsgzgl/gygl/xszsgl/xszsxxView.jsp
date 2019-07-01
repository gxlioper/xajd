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
		
		<div class="tab" style="overflow-y:auto; overflow-x:hidden">
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
					<th width="16%">����</th>
					<td width="34%">
						${rs.xm }
					</td>
					<th width="16%">ѧ��</th>
					<td width="34%">${rs.xh }</td>
				</tr>
				<tr>
					<th>�꼶</th>
					<td>
						${rs.nj }
					</td>
					<th><bean:message key="lable.xb" /></th>
					<td>${rs.xymc }</td>
				</tr>
				<tr>
					<th>רҵ</th>
					<td>
						${rs.zymc }
					</td>
					<th>�༶</th>
					<td>${rs.bjmc }</td>
				</tr>
				<tr>
					<th>�Ƿ�סУ</th>
					<td>${rs.sfzs }</td>
					<logic:equal name="xxdm" value="13798">
						<th>����Ա</th>
						<td>${rs.fdyxm}</td>
					</logic:equal>
					<logic:notEqual name="xxdm" value="13798">
						<th>��ϵ��ʽ</th>
						<td>${rs.sjhm}</td>			
					</logic:notEqual>
				</tr>
			</tbody>
			<logic:equal value="��" name="rs" property="sfzs">
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
					<th>�����꼶</th>
					<td>${rs.cwnj }</td>
				</tr>
				<tr>
					<th>����<bean:message key="lable.xsgzyxpzxy" /></th>
					<td>${rs.cwxymc }</td>
					
					<th><%if("bjdm".equals(GyglNewInit.CWFPDX)){ %>�����༶<%} %></th>
					<td><%if("bjdm".equals(GyglNewInit.CWFPDX)){ %>${rs.cwbjmc }<%} %></td>
				</tr>
	
				<tr>
					<th>
						��ע
					</th>
					<td colspan="3" style="word-break:break-all;width:100%">
						<html:textarea property='bz' style="width:95%" styleId="bz" rows='7' value="${rs.bz}" readonly="true"/>
					</td>
				</tr>
				</tbody>
			</logic:equal>
			<logic:equal value="��" name="rs" property="sfzs">
				<thead>
					<tr style="height:22px">
						<th colspan="4">
							<span>�߶���ע</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							�߶���ע
						</th>
						<td colspan="3" style="word-break:break-all;width:95%;height:40px">
							<%--<html:textarea property='bzxbz' style="width:95%" styleId="bzxbz" rows='3' value="${rs.bzxbz}" readonly="true"/>
						--%>
						${rs.bzxbz}
						</td>
					</tr>
				</tbody>
			</logic:equal>
			</table>
			</div>
		<table width="97%">
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
	</html:form>
</body>
</html>
