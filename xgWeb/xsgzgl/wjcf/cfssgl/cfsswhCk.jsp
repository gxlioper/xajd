<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript'
			src='dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<style>
		.hh{
			word-wrap:break-word;
		    word-break:break-all;
		    -moz-binding: url('./wordwrap.xml#wordwrap');
		    overflow: hidden;
     	}
		</style>
		<script type="text/javascript">
		//����
		function xzSsfj(downType){
			var url="";
			if(downType=="ssfj"){
				url="wjcfCfssgl_cfsswh.do?method=cfssfjXz";
			}else if(downType=="cffj"){
				url="wjcfCfssgl_cfsswh.do?method=cffjXz";
			}else{
				return false;
			}
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
				
			
		}
		</script>
	</head>
	<body>
		<html:form action="/wjcfCfssgl_cfsswh.do?method=cfsswhCk" method="post">
			<input type="hidden" name="cfid" value="${pkValue }"/>
			<logic:present name="rs">
			<input type="hidden" name="cffjmc" value="${rs.cffjmc }"/>
			<input type="hidden" name="ssfjmc" value="${rs.ssfjmc }"/>
			</logic:present>
			<%--<div class="tab_cur" >
					<p class="location">
						<em>���ĵ�ǰλ��:</em><a>${title } - �鿴</a>
					</p>
			</div>1--%>
			<%--<div class="prompt" id="span_qsh" style="display: none">
		       <h3><span>ϵͳ��ʾ��</span></h3>
		       <p>1<br/></p>
		   	</div>
			--%><div  style="width:100%;height:410px;overflow-x:hidden;overflow-y:auto;">	
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:20%">
								ѧ��
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xh"/>
								</logic:present>
							</td>
							<th style="width:20%">
								����
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xm"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								�Ա�
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xb"/>
								</logic:present>
							</td>
							<th style="width:20%">
								�꼶
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="nj"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								<bean:message key="lable.xb" />
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xymc"/>
								</logic:present>
							</td>
							<th style="width:20%">
								רҵ
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="zymc"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								�༶
							</th>
							<td style="width:80%" colspan="3" class="hh">
								<logic:present name="rs">
									<bean:write name="rs" property="bjmc"/>
								</logic:present>
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:20%">
								����ѧ��
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xn"/>
								</logic:present>
							</td>
							<th style="width:20%">
								����ѧ��
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xq"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								�������
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="cflbmc"/>
								</logic:present>
							</td>
							<th style="width:20%">
								����ԭ��
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="cfyymc"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								�����ĺ�
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="cfwh"/>
								</logic:present>
							</td>
							<th style="width:20%">
								����ʱ��
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="cfsj"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								Υ��ʱ��
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="wjsj"/>
								</logic:present>
							</td>
							<th style="width:20%">
								�������������ϻ򸽼�
							</th>
							<td style="width:30%">
								<logic:notEmpty name="rs" property="cffjmc">
									<logic:notEqual name="rs" property="cffjmc" value="">
									<a href="#" onclick="xzSsfj('cffj');return false;"  class="name">���ظ���</a>
									</logic:notEqual>
									</logic:notEmpty>
									<logic:empty name="rs" property="cffjmc">
									<logic:equal name="rs" property="cffjmc" value="">
									���ϴ����ָ���
									</logic:equal>
									</logic:empty>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								Υ����ʵ����
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								<logic:present name="rs">
									<bean:write name="rs" property="wjssjg"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								��ע
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								<logic:present name="rs">
									<bean:write name="rs" property="bz"/>
								</logic:present>
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:20%">
								����ʱ��
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="sqsj"/>
								</logic:present>
							</td>
							<th style="width:20%">
								֤�����ϻ򸽼�
							</th>
							<td style="width:30%">
								<logic:present name="rs">
								${ssfjmc }
									<logic:notEmpty name="rs" property="ssfjmc">
									<logic:notEqual name="rs" property="ssfjmc" value="">
									<a href="#" onclick="xzSsfj('ssfj');return false;" class="name">���ظ���</a>
									</logic:notEqual>
									</logic:notEmpty>
									<logic:empty name="rs" property="ssfjmc">
									<logic:equal name="rs" property="ssfjmc" value="">
									���ϴ����߸���
									</logic:equal>
									</logic:empty>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								��������
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								<logic:present name="rs">
									<bean:write name="rs" property="sqly"/>
								</logic:present>
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:present name="cfshxxList">
						<logic:iterate id="cf" name="cfshxxList" indexId="ind">
						<tr>
							<th style="width:20%">
								<bean:write name="cf" property="mc"/>���ʱ��
							</th>
							<td style="width:30%">
								<bean:write name="cf" property="shsj"/>
							</td>
							<th style="width:20%">
								<bean:write name="cf" property="mc"/>�����
							</th>
							<td style="width:30%">
								<bean:write name="cf" property="shr"/>
							</td>
						</tr>
						<tr>
							<th  align="right" width="20%">
								<bean:write name="cf" property="mc"/>��˽��
							</th>
							<td style="width:30%">
								<bean:write name="cf" property="shjg"/>
							</td>
							<logic:notEmpty name="cf" property="cfggw">
								<th align="right" width="20%">
									���ָ���Ϊ
								</th>
								<td align="left" width="30%">
									${cf.cfggw }
								</td>
		
							</logic:notEmpty>
							<logic:empty name="cf" property="cfggw">
								<th align="right" width="20%">
								
								</th>
								<td align="left" width="30%">
				
								</td>
							</logic:empty>
						</tr>
						<tr>
							<th style="width:20%">
								<bean:write name="cf" property="mc"/>������
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								<bean:write name="cf" property="shyj"/>
							</td>
						</tr>
						</logic:iterate>
						</logic:present>
						<tr>
							<th style="width:20%">
								�����ĺ�
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="sswh"/>
								</logic:present>
							</td>
							<th style="width:20%">
								����ʱ��
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="sssj"/>
								</logic:present>
							</td>
						</tr>
					</tbody>
				</table>
				
			</div>
			<div style="height: 15px"></div>
			<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button"  class="button2"  onclick="Close();return false;">
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
