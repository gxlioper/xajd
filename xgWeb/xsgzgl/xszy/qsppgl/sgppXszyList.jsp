<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/xszygl/js/xszygl.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/qsppgl/js/qsppgl.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "����֮���б�",
				pager : "pager",
				multiselect:false,
				url : "qsppgl.do?method=getSgppXszyList&type=query",
				colList : [
							{ label : 'key', name : 'id', index : 'id',key : true, hidden : true },
							{ label : 'dwdm', name : 'dwdm', index : 'dwdm', hidden : true },
							{ label : 'nj', name : 'nj', index : 'nj', hidden : true },
							{ label : 'ְ����', name : 'zgh', index : 'zgh', width : '8%', formatter:zghLink},
							{ label : '����', name : 'xm', index : 'xm', width : '8%' },
							{ label : '�Ա�', name : 'xb', index : 'xb', width : '5%' },
							{ label : '��������', name : 'bmmc', index : 'bmmc', width : '12%' },
							{ label : '������ò', name : 'zzmmmc', index : 'zzmmmc', width : '10%' },
							{ label : '��ϵ�绰', name : 'lxdh', index : 'lxdh', width : '10%'},
							{ label : 'ְ��ְ��', name : 'zwzc', index : 'zwzc', width : '5%' },
							{ label : '����Ҫ��', name : 'dlyq', index : 'dlyq', width : '10%' },
							{ label : '��ƥ��������', name : 'ppqss', index : 'ppqss', width : '5%' },
							{ label : '��Ժϵ���', name : 'kyxbj', index : 'kyxbj', width : '10%' ,formatter:kyxbjFormatter},
							{label:'����',name:'', index: '',width:'10%',noSort:true,formatter:ppConfLink}
							],
					sortname : "ppqss", sortorder : "asc" }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function ppConfLink(cellValue, rowObject) {
			return "<label class='btn_01' onclick='saveSdpp(\""
					+ rowObject["zgh"] + "\",\"" + rowObject["nj"] + "\",\"" + rowObject["ppqss"] + "\");'>"
					+ "ȷ��</label>";
		}

		//����֮��ƥ��ȷ��
		function saveSdpp(zgh,nj,ppqss){ 
			var api = frameElement.api,W = api.opener;
			
			if(0!=ppqss){
				
				showConfirmDivLayer("������֮����ƥ�����ң��Ƿ��ٴ�ƥ�䣿", {
					"okFun" : function() {
					W.saveSdpp(zgh,nj);
					closeDialog();
					}
				});
			}
			else{
				W.saveSdpp(zgh,nj);
				closeDialog();
			}
			
				
			
		}
		</script>
	</head>

	<body>
		<html:form action="/xszygl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="nj" styleId="nj"/>
			<html:hidden property="lddm" styleId="lddm"/>
			<html:hidden property="qsh" styleId="qsh"/>
			<html:hidden property="qsh" styleId="ldmc"/>
			<div>
				<h3 class=datetitle_01>
				<span>¥����${qsppForm.ldmc} &nbsp;���ң�${qsppForm.qsh}(${qsppForm.qsxb})&nbsp;��ס������${qsppForm.rzrs}</span>
			   </h3>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span><font color="blue">${nj }������֮����Ϣ</font> </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
