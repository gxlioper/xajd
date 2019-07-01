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
					<em>您的当前位置:</em><a>系统维护-权限维护-用户授权</a>
				</p>
			</div>
			
			<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_fh" id="btn_fh" onclick="refreshForm('/xgxt/yhwhManage.do?method=yhwhManage&go=go');return false;">返回</a></li>
						<li><a href="#" class="btn_qx" id="btn_qx" onclick="selectAll();return false;">全选</a></li>
						<li><a href="#" class="btn_sx" id="btn_sx" onclick="czBtn();return false;">重置</a></li>
						<li><a href="#" class="btn_zj" id="btn_zj" onclick="saveDataShowTips('/xgxt/yhwhManage.do?method=yhqxfpManage&doType=save','yhm','正在保证，请稍候！');return false;">保存</a></li>
					</ul>
				</div>
			</div>		
		    
		 	<table style="width: 795px" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>用户信息</span>
						</th>
					</tr>
				</thead>
				
				<tbody>
					<tr>
						<th width="15%">用户名</th>
						<td width="30%">
							${rs.yhm }
						</td>
						<th width="15%">姓名</th>
						<td>
							${rs.xm }
						</td>
					</tr>
					<tr>
						<th>所在部门</th>
						<td>
							${rs.bmmc }
						</td>
						<th> </th>
						<td>&nbsp;
							
						</td>
					</tr>
					<tr>
						<th>拥有角色</th>
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
