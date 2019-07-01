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
	
	<body style="padding-right:20px;">
		<html:form action="/yhwhManage">
			<input type="hidden" name="userName" id="userName" value="${userName }"/>
			<input type="hidden" name="pkValue" id="yhm" value="${param.pkValue }" />
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ϵͳά��-Ȩ��ά��-�û���Ȩ</a>
				</p>
			</div>
			
			<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_fh" id="btn_fh" onclick="refreshForm('/xgxt/yhwhManage.do?method=yhwhManage&go=go');return false;">����</a></li>
						<li><a href="#" class="btn_qx" id="btn_qx" onclick="selectAll();return false;">ȫѡ</a></li>
						<li><a href="#" class="btn_sx" id="btn_sx" onclick="czBtn();return false;">����</a></li>
						<li><a href="#" class="btn_zj" id="btn_zj" onclick="saveDataShowTips('/xgxt/yhwhManage.do?method=yhqxfpManage&doType=save','yhm','���ڱ�֤�����Ժ�');return false;">����</a></li>
					</ul>
				</div>
			</div>		
		    
		 	<table style="width: 795px" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>�û���Ϣ</span>
						</th>
					</tr>
				</thead>
				
				<tbody>
					<tr>
						<th width="15%">�û���</th>
						<td width="30%">
							${rs.yhm }
						</td>
						<th width="15%">����</th>
						<td>
							${rs.xm }
						</td>
					</tr>
					<tr>
						<th>���ڲ���</th>
						<td>
							${rs.bmmc }
						</td>
						<th> </th>
						<td>&nbsp;
							
						</td>
					</tr>
					<tr>
						<th>ӵ�н�ɫ</th>
						<td colspan="3">
							<logic:present name="jsList">
								<logic:iterate id="js" name="jsList" indexId="index">
									${js.jsmc }
									<%	
										int size = (Integer)request.getAttribute("size");
										if(index<size-1){ %>
										,
									<%} %>
								</logic:iterate>
							</logic:present>
						</td>
					</tr>
				</tbody>		
		</table>
		<%@include file="gnmkzx.jsp"%>
	
		<div id="tmpdiv1"></div>
			<logic:present name="message">
				<input type="hidden" id="message" value="${message }"/>
					<script>
						alert($('message').value);
						dialogArgumentsQueryChick();
						window.close();
					</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
