<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/wsbz/dmwh/js/dmwh.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "����Ϊά����ѯ�б�",
				pager : "pager",
				url : "wsbz_dmwh.do?method=getDmwhCx&type=query",
				colList : [ {
					label : 'key',
					name : 'fddm',
					index : 'fddm',
					key : true,
					hidden : true
				}, {
					label : 'ά���ֶ�����',
					name : 'fdmc',
					index : 'fdmc',
					width : '10%',
					formatter:xhLink
				}, {
					label : '�ලʱ��',
					name : 'sj',
					index : 'sj',
					width : '10%'
				},{
					label : '�ල�ص�',
					name : 'dd',
					index : 'dd',
					width : '10%'
				},{
					label : '����',
					name : 'rs',
					index : 'rs',
					width : '10%'
				}],
				sortname : "fdmc",
				sortorder : "asc"
			}
			var map = {};
			map['fdmc']=jQuery('#fdmc').val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/wsbz_dmwh"  onsubmit="return false;" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sqkg" id="sqkg" value="${sqkg}"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="set();return false" class="btn_xg" >ȫ������</a>
						</li>
						</logic:equal>
					</ul>
				</div>
				<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">ά���ֶ�����</th>
						<td>
							<input type="text" id="fdmc" name="fdmc" maxleng="20" onkeypress="if(event.keyCode==13){doQuery();}"/>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go"  onclick="doQuery();">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>����Ϊά����ѯ�б�&nbsp;&nbsp; </span><font></font corlor="red">�����飺�Դ������ͳһ���� ��ͬʱ��������ɾ�����¼�¼����</font>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
