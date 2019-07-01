<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/xwhj/dmwh/jxlb/js/jxlb.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery("#search_go").bind("click",query);
				jQuery("#jxlbmc").bind("keypress",function(event){
					if(event.keyCode==13||"13"==event.keyCode){
						query();
						return false;
					}
				});
			});
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xpj_jxlb">
		<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
				</ul>
			</div>
			</logic:equal>
			<div class="comp_title" id="comp_title">
		      <ul style="width:90%" id="tabUl">
				<li class="ha" ><a href="javascript:void(0);"><span>�������</span></a></li>
				<li ><a href="javascript:void(0);" onclick="goJxdj();return false;"><span>����ȼ�</span></a></li>
				<li ><a href="javascript:void(0);" onclick="goJxmc();return false;"><span>��������</span></a></li>
		      </ul>
		    </div>
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">�������</th>
						<td>
							<input type="text" id="jxlbmc" name="jxlbmc" maxleng="20" />
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> �������ά���б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
