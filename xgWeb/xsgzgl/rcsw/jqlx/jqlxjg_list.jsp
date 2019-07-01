<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/jqlx/js/jqlxjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"ѧ��������У����б�",
				pager:"pager",
				url:"rcsw_jqlxjg.do?&type=query",
				colList:[
				   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
				   {label:'sjlx',name:'sjlx', index: 'sjlx',hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'6%'},
				   {label:'����',name:'mzmc', index: 'mz',width:'6%'},
				   {label:'�����༶',name:'bjmc', index: 'bjdm',width:'14%'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'14%'},
				   {label:'ѧ��',name:'xn', index: 'xn',width:'11%'},
				   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'6%'},
				   {label:'��У��ʼʱ��',name:'lxkssj', index: 'lxkssj',width:'12%'},
				   {label:'��У��ֹʱ��',name:'lxjzsj', index: 'lxjzsj',width:'12%'},

				<logic:equal value="10026" name="xxdm">
					{label:'У������',name:'xxxqmc', index: 'lsxq',width:'6%'},
				</logic:equal>

			    <logic:equal value="10344" name="xxdm">
			    	{label:'����԰��',name:'lxxqmc', index: 'lxxqmc',width:'6%'},
			    	{label:'��������¥��',name:'lxldmc', index: 'lxldmc',width:'6%'},
			    	{label:'�������Һ�',name:'lxqs', index: 'lxqs',width:'6%'},
			    </logic:equal>

				<logic:notEqual value="10344" name="xxdm">
				   {label:'¥������',name:'ldmc', index: 'ldmc',width:'6%'},
				   {label:'���Һ�',name:'qsh', index: 'qsh',width:'6%'},
				</logic:notEqual>

				<logic:notEqual value="10026" name="xxdm">
				<logic:notEqual value="10344" name="xxdm">
				   {label:'��λ��',name:'cwh', index: 'cwh',width:'6%'},
				</logic:notEqual>
				</logic:notEqual>

				   {label:'������Դ',name:'sjlymc', index: 'sjlymc',hidden:true},
				   {label:'¥������',name:'lddm', index: 'lddm',hidden:true}
				],
				sortname: "sqsj",
			 	sortorder: "desc"
			}

			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function drjqlx(){
				var realTable = "";
				var tableName = "";
				var sty = "toolbar=no,location=no,directories=no,status=yes";
				sty += ",menubar=no,scrollbars=yes,resizable=yes,width=600,height=400,top=100";
				sty += ",left=200";
				if($("realTable")) realTable = document.getElementById("realTable").value;
				if($("tableName")) tableName = document.getElementById("tableName").value;
				url = 'rcsw_jqlx.do?method=importData';
				url += "&realTable=" + realTable;
				url += "&tableName=" + tableName;
				//window.open(url,'',sty);
				showDialog('��������', 600, 300, url);
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_rcxwwh_rcxwxxwhgl">		
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">����</a>
						</li>
						<logic:equal name="writeAble" value="yes">	
							<li><a href="javascript:void(0);" onclick="drjqlx();return false;" id="btn_dr" class="btn_dr">��У���ҵ���</a></li>
						</logic:equal>
						<!-- �Ĵ�ְҵ����ѧԺ��ӡ����� begin-->
						<logic:equal value="12970" name="xxdm">
							<li>
								<a href="javascript:void(0);" onclick="printjqlxsqb('rcsw_jqlx.do?method=printjqlxsqb');return false;" class="btn_down">���������</a>
							</li>
						</logic:equal>
						<!-- �Ĵ�ְҵ����ѧԺ��ӡ����� end-->
						<logic:equal value="12309" name="xxdm">
							<li><a href="javascript:void(0);" onclick="printjqlxsqb('rcsw_jqlx.do?method=printLstxz');return false;" class="btn_down">������ʱͨ��֤</a></li>
						</logic:equal>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>ѧ��������У����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
