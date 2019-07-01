<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/axcs/wpsq/js/wpsq.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		//��ʼ��
		jQuery(document).ready(function(){ 
			searchRs();
		});
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/axcswpsqxs" method="post" styleId="WpsqForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
				<input type="hidden" name ="sqzt" id = "sqzt"/>
				<input type="hidden" name ="xh" id = "xh" value="${xh}"/>
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wsq');"><span>δ����</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysq');"><span>������</span></a></li>
			      </ul>
			    </div>
				<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						
						<th class="xnSea" width="10%">ѧ��</th>
						<td class="xnSea" >
							<html:select property="xn" styleId="xn">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>
						</td>
						<th width="10%" >��Ʒ���</th>
						<td>
							<html:select property="xmlb" styleId="xmlb">
							<option value=""></option>
							<html:options collection="wplbList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
						<th width="10%" >��Ʒ����</th>
						<td>
							<input type="text" id="xmmc" name="xmmc" maxleng="20" 
							   onkeypress="if(pressEnter(event)){searchRs();return false;}"
							/>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="searchRs()">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>					
				</table>
				
			</div>
			<div style="width:102%;overflow-x:auto;overflow-y:hidden;">
			<table width="100%" border="0" class="formlist">
					<tbody id="sqTbody">
					</tbody>
				</table>
				</div>
		</html:form>
		
	</body>
</html>
