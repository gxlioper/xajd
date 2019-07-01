<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xljkwzdx/xlzxnew/ybgl/ybsq/js/ybsq.js"></script>
		<script type="text/javascript">
		


	
	jQuery(function() {
		var gridSetting = {
				pager : "pager",
				url : "xlzxnew_ybsb.do?method=seachYbsqCxList",
				colList : [
						{ label : 'sbid', name : 'sbid', index : 'sbid',key : true, hidden : true },
						{ label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '30%',formatter:link},
						{ label : 'ѧ��', name : 'xn', index : 'xn', width : '20%' },
						{ label : '�·�', name : 'yfmc', index : 'yfmc', width : '20%' },
						{ label : '��д��', name : 'xm', index : 'xm', width : '20%'},
						{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '20%'},
						{ label : 'sjly', name : 'sjly', index : 'sjly', hidden : true },
						{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
						{ label : 'splcid', name : 'splcid', index : 'splcid', hidden : true }
						],
				 };
		 var map = getSuperSearch();
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
		if('${cssz.splc}'==null||'${cssz.splc}'==''){
			jQuery("#prompt_isopen").show();
			jQuery("#prompt_null_isopen").show();
			return false;
		}
	});

</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<div class="prompt" id="prompt_isopen" style="display:none;">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p id="prompt_null_isopen" style="display:none;">
				�������δ���û�����ְ�ڼ��ڣ�����ϵ����Ա��
			</p>
			<p id="prompt_false_isopen" style="display:none;">
				<bean:message key="lable.dqwkfsq_prompt" />
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/xlzxnew_ybjg" >
			<input type="hidden" id="query_type" value="0"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<logic:notEmpty name="cssz">
						<li>
							<a href="javascript:void(0);" class="btn_zj"
								onclick="sb();return false;">�ϱ�</a>
						</li>
						<li>
							<a href="javascript:void(0);"
								onclick="update();return false;" class="btn_xg"
							>�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
						</li>
						</logic:notEmpty>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="lcinfo();return false;" class="btn_cs">���̸���</a>
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
				<span>��ѯ���&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
