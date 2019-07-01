<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/khgl/jgcx/jgcx/js/jgcx.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" >
		jQuery(function() {
			jQuery("#btn_fh").bind("click",function(){
				document.location.href='khgljgcx.do?method=jgcxList';
				
			});	
			var gridSetting = {
					caption : "���˶����б�",
					pager : "pager",
					radioselect:true,
					url : "khgljgcx.do?method=xmjgListOfSdty&type=query",
					sortname : "pm",
					sortorder : "asc"
				};
			var colList = [ 
						   {label : 'xydm',name : 'xydm',index : 'xydm',hidden:true},
						   {label : 'ѧ��',name : 'xh',index : 'xh',width : '20%'}, 
						   {label : '����',name : 'xm',index : 'xm',width : '15%'}, 
						   {label : '�Ա�',name : 'xb',index : 'xb',width : '5%'}, 
						   {label : '�꼶',name : 'nj',index : 'nj',width : '10%'}, 
						   {label : 'ѧԺ',name : 'xymc',index : 'xymc',width : '25%'},
						   {label : '�༶',name : 'bjmc',index : 'bjmc',width : '25%'}
						   ];
			var pfz = jQuery("font[name=pfz]");
			jQuery.each(pfz,function(i,n){
				var json = {label:jQuery(n).attr("pfzmc"),
							name:"fs"+i,
							index:"fs"+i
							}
				colList.push(json);
				});
			colList.push({label : '�ܷ�',name : 'zf',index : 'zf',width : '5%'});
			colList.push({label : 'ѧԺ����',name : 'xypm',index : 'xypm',width : '5%'});
			colList.push({label : 'ȫУ����',name : 'pm',index : 'pm',width : '5%'});
			gridSetting["colList"] = colList;
				var map = {xmid:jQuery("#xmid").val(),khlx:jQuery("#khlx").val()};
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});

			//�߼���ѯ
			function searchRs(){
				var map = getSuperSearch();
				map["xmid"] = jQuery("#xmid").val();
				map["khlx"] = jQuery("#khlx").val();
				jQuery("#dataTable").reloadGrid(map);
			}
					
		</script>
	</head>
	<body>
	<html:form action="/khgljgcx" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" name="xmid" id="xmid" value=${model.xmid} />
	<input type="hidden" name="khlx" id="khlx" value=${model.khlx} />
	<input type="hidden" name="dclb" id="dclb" value='xs' />
	<logic:present name="pfzList">
			<logic:iterate id="z" name="pfzList">
				<font style="display:none;" pfzid="${z.pfzid }"  xmszid="${z.xmszid }" pfzmc="${z.pfzmc }"
				       name="pfz"></font>
			</logic:iterate>
		</logic:present>
	<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a id="btn_fh" class="btn_fh"> �� �� </a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="exportConfigOfSdty();return false;" class="btn_dc">����</a>
							<a href="javascript:void(0);" onclick="dftj();return false;" class="btn_dc">���ͳ��</a>
						</li>
						</logic:equal>
					</ul>
				</div>
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
	</html:form>
			<div class="main_box">
					<h3 class=datetitle_01>
						<span>��ǰ������ĿΪ��<font color="blue">${xmInfo.xmmc }</font></span>
					</h3>
					<div class="con_overlfow">
						<table id="dataTable" ></table>
						<div id="pager"></div>
					</div>
				</div>		
	</body>
</html>
