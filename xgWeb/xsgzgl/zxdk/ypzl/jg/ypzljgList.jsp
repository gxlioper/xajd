<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/ypzl/jg/js/ypzljg.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {};
			if("10511"==jQuery("#xxdm").val()){
				gridSetting = {
						caption : "��ʱ���Ѳ�������б�",
						pager : "pager",
						url : "ypzl_jg.do?method=getYpzljgList&type=query",
						colList : [
									{ label : 'jgid', name : 'jgid', index : 'jgid',key : true, hidden : true },
									{ label : 'ѧ��', name : 'xh', index : 'xh', width : '10%',formatter:xhLink },
									{ label : '����', name : 'xm', index : 'xm', width : '10%' },
									{ label : '�Ա�', name : 'xb', index : 'xb', width : '5%' },
									{ label : '�꼶', name : 'nj', index : 'nj', width : '10%' },
									{ label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '15%' },
									{ label : '�������', name : 'sqje', index : 'sqje', width : '10%' },
									{ label : '����ԭ�����', name : 'ytmc', index : 'ytmc', width : '10%' },
									{ label : '����ʱ��', name : 'sqsj', index : 'sqsj', width : '20%' },
									{ label : 'sjly', name : 'sjly', index : 'sjly', hidden : true },
									{ label : 'lylcywid', name : 'lylcywid', index : 'lylcywid', hidden : true}],
							sortname : "sqsj",
						    sortorder : "desc" }
			}else{
				gridSetting = {
					caption : "��ƽ������ѧ�����б�",
					pager : "pager",
					url : "ypzl_jg.do?method=getYpzljgList&type=query",
					colList : [
								{ label : 'jgid', name : 'jgid', index : 'jgid',key : true, hidden : true },
								{ label : 'ѧ��', name : 'xh', index : 'xh', width : '15%',formatter:xhLink },
								{ label : '����', name : 'xm', index : 'xm', width : '10%' },
								{ label : '�Ա�', name : 'xb', index : 'xb', width : '5%' },
								{ label : '�꼶', name : 'nj', index : 'nj', width : '10%' },
								{ label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '20%' },
								{ label : '������', name : 'sqje', index : 'sqje', width : '10%' },
								{ label : '����ʱ��', name : 'sqsj', index : 'sqsj', width : '20%' },
								{ label : 'sjly', name : 'sjly', index : 'sjly', hidden : true },
								{ label : 'lylcywid', name : 'lylcywid', index : 'lylcywid', hidden : true}],
						sortname : "sqsj",
					    sortorder : "desc" }
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/ypzl_jg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden"  id="gnmkmc" value="${gnmkmc}"/>
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
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr">����</a>
						</li>
						</logic:equal>
						
						<logic:equal value="10335" name="xxdm">
							<logic:equal value="zf01" name="userName">
								<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
							</logic:equal>
						</logic:equal>
						<logic:notEqual value="10335" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						</logic:notEqual>
						
							<logic:notEqual value="10511" name="xxdm">
							   <li><a href="javascript:void(0);" onclick="printypzlsqb('ypzl_sq.do?method=printypzlsqb');return false;" class="btn_down">���صǼǱ�</a></li>	
							</logic:notEqual>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>${gnmkmc}�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
