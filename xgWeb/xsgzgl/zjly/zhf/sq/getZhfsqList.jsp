<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/zhf/js/zhfsq.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"�ۺ����ʼƷ���Ŀ��ϸ��",
				pager:"pager",
				url:"zhf_sq.do?method=getZhfsqList&type=query",
				colList : [
							{ label : 'key', name : 'id', index : 'id',key : true, hidden : true },
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '10%',formatter:xhLink },
							{ label : '����', name : 'xm', index : 'xm', width : '8%' },
							{ label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '15%'  },
							{ label : 'רҵ', name : 'zymc', index : 'zymc', width : '10%' },
							{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '10%' },
							{ label : '������Ŀ', name : 'xmmkmc', index : 'xmmkmc', width : '10%' },
							{ label : '�Ʒ���Ŀ', name : 'jfxmmc', index : 'jfxmmc', width : '10%' },
							{ label : '����', name : 'fs', index : 'fs', width : '10%' },
							{ label : '����״̬', name : 'shztmc', index : 'shztmc', width : '8%' },
							{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true}					
							],
				sortname: "shzt",
			 	sortorder: "asc",
			 	radioselect:false
		}	
				
		jQuery(function(){
			var map = {};
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function query(){
			var map = {};
			map["cxzd"] = jQuery("#cxzd").val();
			jQuery("#dataTable").reloadGrid(map);
		}
			
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/zhf_sq" onsubmit="return false;">
		<input type="hidden" id="sqkg" value="${sqkg}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sq">
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >����</a>
						</li>
						<li id="li_xg">
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li id="li_sc">
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >ɾ��</a>
						</li>
						</logic:equal>
					</ul>
					
				</div>
			    <!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th width="15%" style="margin-left:20px">
								�Ʒ���Ŀ����
							</th>
							<td>
								<input type="text" id="cxzd" />
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go"
										onclick="query()">
										�� ѯ
									</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>			
			</div>
			</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�ۺ����ʼƷ���Ŀ��ϸ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
