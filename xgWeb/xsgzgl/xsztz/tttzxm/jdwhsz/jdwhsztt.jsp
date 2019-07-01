<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/jdwhsz/js/jdwhsz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "grttxm_jdsz.do?method=getJdszTtList&type=query",
				colList : [ {
					label : 'key',
					name : 'jdwhid',
					index : 'jdwhid',
					key : true,
					hidden : true
				}, {
					label : '�Ŷ�����',
					name : 'tdmc',
					index : 'tdmc',
					width : '10%',
					formatter:ttsqLink
				}, {
					label : '�ӳ�ѧ��',
					name : 'xh',
					index : 'xh',
					width : '10%'
				}, {
					label : '�ӳ�����',
					name : 'xm',
					index : 'xm',
					width : '10%'
				},{
					label : '�׶η�',
					name : 'jbf',
					index : 'jbf',
					width : '10%',
					formatter : jbfLink
				},{
					label : '�ʱ��',
					name : 'hdsc',
					index : 'hdsc',
					width : '35%',
					formatter : hdscLink
				},{
					label : '��ע',
					name : 'bz',
					index : 'bz',
					formatter : bzLink
				},
				{
					name : 'jdcy',
					index : 'jdcy',
					hidden : true
				},
				{
					name : 'ttjgid',
					index : 'ttjgid',
					hidden : true
				},
				{
					name : 'xmdm',
					index : 'xmdm',
					hidden : true
				}
				],
				radioselect:false
			}
			var map = getSuperSearch();
			map['jdid']=jQuery('#jdid').val();
			map['xh']=jQuery('#xh').val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="jdid" id="jdid" value="${jdid}"/>
		<input type="hidden" name="jdf" id="jdf" value="${jdf}"/>
		<input type="hidden" name="xmdm" id="xmdm" value="${xmdm}"/>
		<div class="tab_cur">
		</div>
		<html:form action="/cxdd_pysb" onsubmit="return false;" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sqkg" id="sqkg" value="${sqkg}"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
                             <a href="javascript:void(0);" onclick="document.location.href='sztz_grttxm_jdsz.do';" class="btn_fh">����</a>
                        </li>
						<li>
                             <a href="javascript:void(0);" onclick="addTtcy();" class="btn_zj">����</a>
                        </li>
                        <li>
                             <a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a>
                        </li>
					</ul>
				</div>
				<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="16%">�ӳ�ѧ�� /�ӳ�����</th>
						<td>
							<input type="text" id="xh" name="xh" maxleng="20" onkeypress="if(event.keyCode==13){doQuery();}"/>
						</td>
						<td>
							<div class="btn">
								<button type="button"  class="btn_cx" id="search_go" onclick="doQuery();">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		
				<!-- �������� -->	
				<div style="display:none">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
				
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span><font color = "blue">${xmmc}</font> ${jdmc}&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
