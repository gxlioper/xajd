<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/khgl/khdxgl/pfzgl/js/pfzwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"��ѯ���",
				pager:"pager",
				rowNum:10,
				url:"khglPfzgl.do?method=showKhdx&type=query&pfzid="+'${pfzid}'+"&khdxid="+'${khdxid}'+"&pflx="+'${pflx}',
				colList:[
						   {label:'ְ����',name:'zgh', index: 'zgh',width:'10%',key:true},
						   {label:'����',name:'xm', index: 'xm',width:'10%'},
						   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
						   {label:'��������',name:'bmmc', index: 'bmmc',width:'20%'},
						   {label:'�û����',name:'yhsf', index: 'yhsf',width:'10%'},
						   {label:'���ֳ�Ա',name:'pfcys', index: 'pfcys',width:'10%',formatter:pfcyTeaLink}
						],
				sortname: "zgh",
			 	sortorder: "asc",
			}
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
		</script>
	</head>

	<body>
		<html:form method="post" action="/khglPfzgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id ="khdxid" value="${khdxid}"/>
			<input type="hidden" id ="pfzid" value="${pfzid}"/>
			<!-- pflxΪ1ʱ�����뿼����Ϊ�Ķ���Ϊѧ�� -->
			<input type="hidden" id ="pflx" value="${pflx}"/>
			<!-- khlxΪ1ʱ����������Ϊѧ��(ѧ��)������Ϊ��ʦ(ְ����) -->
			<input type="hidden" id ="khlx" value="${khlx}"/>
			<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			</div>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
				<ul>	
						<li>
							<a id="btn_fh" class="btn_fh"> �� �� </a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="pfcySz();return false;" class="btn_sz">�������Ա����</a>
						</li>
						<logic:equal name="xxdm" value="10504">
						<logic:equal name="pflx" value="1">
							<li>
								<a href="javascript:void(0);" onclick="pfcybjSz();return false;" class="btn_sz">�������Ա(�༶)����</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="pfcydr();return false;" class="btn_dr">���ֳ�Ա����</a>
							</li>
						</logic:equal>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="qkpfr();return false;" class="btn_sz">���������</a>
						</li>
				</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="toolbox">
					<!--����start-->
			<h3 class="datetitle_01">
				<span> ���˶���(��ʦ)�б�
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
