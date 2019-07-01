<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zhdj/jjfzbfgl/js/jjfzbfgl.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "sgygc_jjfzbfgl.do?method=getJjfz&type=query",
				colList : [
                    {label : 'key', name : 'xb', index : 'xb', hidden : true},
                    {label : 'key1', name : 'sjhm', index : 'sjhm', hidden : true},
                    {label : 'key3', name : 'ssld', index : 'ssld', hidden : true},
                    {label : 'key4', name : 'lxdh', index : 'lxdh', hidden : true},
                    {label : 'key5', name : 'ssbh', index : 'ssbh', hidden : true},
                    {label : 'ѧ��', name : 'xh', index : 'xh', hidden : true},
				    {label : '����', name : 'xm', index : 'mc', width : '10%'},
                    {label : '�Ա�', name : 'xb', index : 'xb', width : '10%'},
					{label : 'ѧԺ����', name : 'xymc', index : 'xymc', width : '20%'},
                    {label : 'רҵ', name : 'zymc', index : 'zymc', width : '10%'},
                    {label : '�༶', name : 'bjmc', index : 'bjmc', width : '10%'},
				],
				sortname : "xh",
				sortorder : "asc",
                radioselect:true
				
			}
			var map = getSuperSearch();
			map["xydm"]=jQuery("#xydm").val();
			map["js"]=jQuery("#js").val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<html:form action="/sgygc_jjfzbfgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						
						
						<li>
							<a href="javascript:void(0);" class="btn_sz" onclick="choseJjfz();return false;">ѡ����Ա</a>
						</li>
						
						
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��Ա&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
