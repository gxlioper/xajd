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
			var gridSetting = {
					caption : "���˶����б�",
					pager : "pager",
					/* radioselect:true, */
					url : "khgljgcx.do?method=xmjgList&type=query",
					colList : [ 
						{label : 'bmdm',name : 'bmdm',index : 'bmdm',hidden:true},
						{label : 'ְ����',name : 'zgh',index : 'zgh',width : '20%'}, 
						{label : '����',name : 'xm',index : 'xm',width : '15%'}, 
						{label : '�Ա�',name : 'xbmc',index : 'xb',width : '15%'}, 
						{label : '���ڲ���',name : 'bmmc',index : 'bmmc',width : '20%'}
						<logic:notEqual value="xy" name="userType">
						,{label : '�ܷ�',name : 'zf',index : 'zf',width : '5%'}
						,{label : '����',name : 'pm',index : 'pm',width : '5%'}
						</logic:notEqual>
					   ],
					sortname : "pm",
					sortorder : "asc"
				}

			jQuery(function() {
				jQuery("#btn_fh").bind("click",function(){
					document.location.href='khgljgcx.do?method=jgcxList';
					
				});
				
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
	<input type="hidden" name="dclb" id="dclb" value='js' />
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
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="dftj();return false;" class="btn_dc">���ͳ��</a>
						</li>
						<!-- �㽭��ҵ��ʦѧԺ���Ի����ܣ�ѧ���԰����δ�ֵ�����ֻ�п�����Ŀ��ѧ���԰����εĴ�ֲų��ð�ť -->
						<logic:equal name="xxdm" value="33333">
							<li>
								<a href="javascript:void(0);" onclick="xsdbzrhzDy();return false;" class="btn_dc">ѧ���԰����λ��ܴ�ӡ</a>
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
