<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/rcsw/xsgzzb/xsgzzbcssz/js/cssz.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		function searchRs() {
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		jQuery(function() {
			jQuery("#dataTable").initGrid(gridSetting);
		});
		</script>
		
	</head>
	<body>
	<html:form action="/rcsw_xsgzzb_csszgl" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" id="tabType" value="stlb"/>
	<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
				</ul>
			</div>
			</logic:equal>
				
			<!--  
			<div class="comp_title" id="comp_title">
			
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'stlb');"><span>�ϱ��ļ�����</span></a></li>
			      </ul>
			     
			</div>
			 -->
		</div>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tr>
							<th id="stlb_th" width="12%">�ļ���������</th>
							<td id="stlb_td" width="12%">
								<input type="text" name="wjlxmc" id="wjlxmc" onkeydown="keydown_search(event)" style="width:150px;"/>
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="setCssz_url();searchRs();return false"">
										�� ѯ
									</button>
								</div>
							</td>
						</tr>					
					</table>
				</div>
				<div style="display:none">
						<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
			<div class="formbox">
			<!--����start-->
				<h3 class="datetitle_01">
					<span> �ϱ��ļ������б� </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
