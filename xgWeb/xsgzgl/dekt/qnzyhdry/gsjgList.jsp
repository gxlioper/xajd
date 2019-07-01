<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dekt/qnzyhd/js/gsjg.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"־Ը�߷���ʱ�б�",
				pager:"pager",
				url:"zyhdry.do?method=gsjgList&type=query",
				colList : [
							{ label : 'id', name : 'id', index : 'id',key : true, hidden : true },
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '10%',formatter:xhLink },
							{ label : '����', name : 'xm', index : 'xm', width : '7%' },
							{ label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '15%' },
							{ label : 'רҵ', name : 'zymc', index : 'zymc', width : '15%' },
							{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '15%' },
							{ label : '�����', name : 'hdmc', index : 'hdmc', width : '10%' },
							{ label : '��������', name : 'fwlxmc', index : 'fwlxmc', width : '10%' },
							{ label : '����ʱ', name : 'gs', index : 'gs', width : '10%' }
							],
				sortname: "xh",
			 	sortorder: "desc",
			 	radioselect:false
		}
			
		jQuery(function(){			
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
			jQuery("#pf").bind({click:function(){
				pf();
			}})
		});
	
			
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/zyhdry">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li id="li_sh">
							<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
							<logic:equal value="10346" name="xxdm">
								<a href="javascript:void(0);" class="btn_sz" id="pf">����</a> 
							</logic:equal>
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
				<span>
					־Ը�߷���ʱ�б�&nbsp;&nbsp;
					<logic:present name="xf">
						<logic:notEmpty name="xf">
							��ѧ�֣�
							<font color="red">
								${xf}
							</font>
						</logic:notEmpty>
					</logic:present>
				</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
