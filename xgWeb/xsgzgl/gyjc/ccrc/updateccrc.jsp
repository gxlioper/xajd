<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/ccrc/js/ccrc.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		
		
		jQuery(function(){
		var ccid = jQuery("#ccid").val();
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "gyjc_ccrcsz.do?method=todayCc&type=query&ccid="+ccid,
				colList : [  {
					label : 'key',
					name : 'ccid',
					index : 'ccid',
					hidden : true
				},{
					label : 'key2',
					name : 'zgh',
					index : 'zgh',
					hidden : true
				},{
					label : '���',
					name : 'xh',
					index : 'xh',
					width : '10%'
				}, {
					label : '��ǰ��ѡ���ң�����',
					name : 'qsnum',
					index : 'qsnum',
					width : '20%',
					formatter : qsLink
				}, {
					label : '�����',
					name : 'xm',
					index : 'xm',
					width : '20%',
					formatter : ccrLink
				}
				],
				sortname : "xh",
				sortorder : "asc"
				
			}
			
			jQuery("#dataTable").initGrid(gridSetting);
		})
			
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/gyjc_ccrcsz" method="post" styleId="CcrcForm">
			<html:hidden property="ccid" styleId="ccid"/>
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom:0px;" >
			<div class="toolbox">
			<div class="buttonbox">
					<ul>
						<li id="li_fh">
							<a href="javascript:void(0);" class="btn_fh" onclick="fhjcsd();return false;">����</a>
						</li>
						<li id="li_bc">
							<a href="javascript:void(0);" class="btn_xg" onclick="saveCcrcUpdate();return false;">�޸ļ������</a>
						</li>
							

					</ul>
				</div>
				</div>
			
		
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>����ճ�</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>${xn}
								<html:hidden property="xn" styleId="xn" />
							</td>
							<th>ѧ��</th>
							<td>${xqmc}
								<html:hidden property="xqdm" styleId="xqdm" />
							 </td>
						</tr>
						<tr>
							<th><font color="red">*</font>�������</th>
							<td>
								<html:text property="jcrq"  styleId="jcrq" onclick="return showCalendar('jcrq','y-mm-dd');" />
							</td>
							<th></th>
							<td>
							</td>
						</tr>
						
					</tbody>
					<input type="hidden" class="btn_cx" id="search_go" onclick="searchRs()">
				</table>
				
				<div class="toolbox">
			<div class="buttonbox">
					<ul>
					
						<li>
							<a href="javascript:void(0);" class="btn_sz" onclick="fpqs();return false;">����������</a>
						</li>

					</ul>
				</div>
				</div>
				
				<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
				</div>
				<%--;height:520px --%>
			<!--
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" id="update" onclick="saveCcrcUpdate();">
										��    ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			-->
		</html:form>
	</body>
	
</html>