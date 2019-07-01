<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/kqsh.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption : "${gnmkmc }�б�",
				pager : "pager",
				url : "zwzxKqsh.do?method=getKqshList&type=query",
				colList : [
							{ label : 'key', name : 'sqid', index : 'sqid',key : true, hidden : true },
							{ label : 'bjdm', name : 'bjdm', index : 'bjdm',hidden : true },
							{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
							{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
							{ label : 'ѧ��', name : 'xn', index : 'xn', width : '10%' },
							{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '5%' },
							{ label : '�꼶', name : 'nj', index : 'nj', width : '10%' },
							{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '18%' },
							{ label : 'רҵ', name : 'zymc', index : 'zymc', width : '18%' },
							{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '15%',formatter : bjmcLink },
							{ label : '${cclxTitle}', name : 'cclxmc', index : 'cclxmc', width : '5%' },
							{ label : '${ccrqTitle}', name : 'ccrq', index : 'ccrq', width : '5%' },
							<logic:equal name="xxdm" value="2297">
								{ label : 'Ӧ������', name : 'ydrsszly', index : 'ydrsszly', width : '5%' },
							</logic:equal>
							<logic:notEqual name="xxdm" value="2297">
								{ label : 'Ӧ������', name : 'ydrs', index : 'ydrs', width : '5%' },
							</logic:notEqual>
							{ label : 'ʵ������', name : 'sdrs', index : 'sdrs', width : '5%' },
							<logic:equal name="xxdm" value="12970">
							{ label : 'δ��У����', name : 'qqrs', index : 'qqrs', width : '5%' },
							</logic:equal>
							<logic:notEqual name="xxdm" value="12970">
							{ label : 'ȱ������', name : 'qqrs', index : 'qqrs', width : '5%' },
							</logic:notEqual>
							{ label : '���ɷ�', name : 'jlf', index : 'jlf',hidden : true },
							{ label : '����ʱ��', name : 'sqsj', index : 'sqsj', width : '15%' },
							{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '15%' },
							{ label : '���״̬', name : 'shzt', index : 'shzt', hidden : true},
							{label:'���Id',name:'shid', index: 'shid',hidden:true},
							{label:'��λId',name:'gwid', index: 'gwid',hidden:true}
							],
					params:{shzt:"dsh"},
					sortname : "sqsj",
				    sortorder : "desc" 
					    };
			var gridSetting2 = {
					caption : "${gnmkmc }�б�",
					pager : "pager",
					url : "zwzxKqsh.do?method=getKqshList&type=query",
					colList : [
								{ label : 'key', name : 'sqid', index : 'sqid',key : true, hidden : true },
								{ label : 'bjdm', name : 'bjdm', index : 'bjdm',hidden : true },
								{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
								{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
								{ label : 'ѧ��', name : 'xn', index : 'xn', width : '10%' },
								{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '5%' },
								{ label : '�꼶', name : 'nj', index : 'nj', width : '10%' },
								{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '18%' },
								{ label : 'רҵ', name : 'zymc', index : 'zymc', width : '18%' },
								{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '15%',formatter : bjmcLink },
								{ label : '${cclxTitle}', name : 'cclxmc', index : 'cclxmc', width : '5%' },
								{ label : '${ccrqTitle}', name : 'ccrq', index : 'ccrq', width : '5%' },
								<logic:equal name="xxdm" value="2297">
								   { label : 'Ӧ������', name : 'ydrsszly', index : 'ydrsszly', width : '5%' },
							    </logic:equal>
							    <logic:notEqual name="xxdm" value="2297">
								   { label : 'Ӧ������', name : 'ydrs', index : 'ydrs', width : '5%' },
							    </logic:notEqual>
								{ label : 'ʵ������', name : 'sdrs', index : 'sdrs', width : '5%' },
								{ label : 'ȱ������', name : 'qqrs', index : 'qqrs', width : '5%' },
								{ label : '���ɷ�', name : 'jlf', index : 'jlf',hidden : true },
								{ label : '����ʱ��', name : 'sqsj', index : 'sqsj', width : '15%' },
								{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '15%' },
								{ label : '���״̬', name : 'shzt', index : 'shzt', hidden : true},
								{ label:'���Id',name:'shid', index: 'shid',hidden:true},
								{ label:'��λId',name:'gwid', index: 'gwid',hidden:true}
								],
						params:{shzt:"ysh"},
						sortname : "sqsj",
					    sortorder : "desc" 
						    };
			jQuery(function(){
				var map = getSuperSearch();
				map["shzt"]="dsh";
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/zwzxKqsh">
		<input type="hidden" name="shkg" id="shkg" value="${shkg}"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<input type="hidden" id="gnmkmc" value="${gnmkmc}"/>
			<input type="hidden" id="shzt" value="dsh"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="kqsh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelSh();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="splcInfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<logic:equal name="xxdm" value="12970">
				<h3 class=datetitle_01>
					<span>���ڷ�Уѧ��������Ϣ&nbsp;&nbsp; </span>
				</h3>
			</logic:equal>
			<logic:notEqual name="xxdm" value="12970">
				<h3 class=datetitle_01>
					<span>${gnmkmc }�б�&nbsp;&nbsp; </span>
				</h3>
			</logic:notEqual>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
