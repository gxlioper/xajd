<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>

	<script language="javascript" src="js/qtsjFunction.js"></script>
	
	<script type="text/javascript">	
		function checkOne(obj){
			var id = obj.id;
			var ids = id.split('_');
			
			if(ids[1] == 'yy'){
				var oid = ids[0] + "_gl";
				
				if($(oid)){
					$(oid).checked = "";	
				}
			}else{
				var oid = ids[0] + "_yy";
				if($(oid)){
					$(oid).checked = "";
				}
			}
		}
		
	</script>
	</head>
	
	<body>
		<html:form action="/jswhManage">
			<input type="hidden" name="userName" id="userName" value="<bean:write name= "userName" scope="session" />"/>
			<input type="hidden" name="pkValue" value="${param.pkValue }" />
			
			<html:hidden property="jsmc"/>
			<html:hidden property="jscmdm"/>
			<html:hidden property="sfqy"/>
			<html:hidden property="jslxdm"/>
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ϵͳά��-Ȩ��ά��-��ɫ��Ȩ</a>
				</p>
			</div>
			<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_fh" id="btn_fh" onclick="refreshForm('/xgxt/jswhManage.do?method=jswh&go=go');return false;">����</a></li>
						<li><a href="#" class="btn_qx" id="btn_qx" onclick="selectAll();return false;">ȫѡ</a></li>
						<li><a href="#" class="btn_sx" id="btn_sx" onclick="czBtn();return false;">����</a></li>
						<li><a href="#" class="btn_zj" id="btn_zj" onclick="saveDataShowTips('/xgxt/jswhManage.do?method=jssqManage&doType=save','yhm','���ڱ�֤�����Ժ�');return false;">����</a></li>
					</ul>
				</div>
			</div>		    
			<div>
			 	<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ɫ��Ϣ</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th width="15%">��ɫ��</th>
							<td width="30%">
								${rs.jsmc }
							</td>
							<th width="15%">
								��ɫ����
							</th>
							<td align="left" width="30%">
								${rs.jslxmc }
							</td>
						</tr>
						<tr>
							<th >
								��ɫ������Χ
							</th>
							<td align="left">
								${rs.jscmmc }
							</td>
							<th>
							</th>
							<td align="left">
							</td>
						</tr>
						<tr>
							<th>
								��ɫ˵��
							</th>
							<td align="left" colspan="3">
								${rs.jssm }
							</td>
						</tr>
					</tbody>		
			</table>
			</div>
			<%@include file="../yhgl/gnmkzx.jsp"%>
			<div id="tmpdiv1"></div>
			<logic:present name="message">
				<input type="hidden" id="message" value="${message }"/>
					<script>
						alert($('message').value);
					</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
