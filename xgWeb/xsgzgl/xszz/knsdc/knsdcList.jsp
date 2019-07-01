<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsdc/js/knsdclist.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			function newChgCode(obj){
				allNotEmpThenGo(obj.id);
			}
		</script>
	</head>
	
	<body>
	<html:form action="/xszz_knsdc" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	
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
			<!-- �Ϻ��������Ի�-->
			<logic:equal value="10277" name="xxdm">
			<div class="compTab" id="card">
				<div class="comp_title">
					<ul>
						<li class="ha"><a href="#" onclick="newChgCode(this);return false;" id="xszz_knsdc.do?method=dcwhList"><span>���ѵ���</span></a></li>
						<li ><a href="#" onclick="newChgCode(this);return false;" id="xszz_knsdc.do?method=knyyList"><span>����ԭ��</span></a></li>
						
					</ul>
				</div>
			</div>	
			</logic:equal>
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">��������</th>
						<td>
							<input type="text" id="dcmc" name="dcmc" maxleng="20" 
							onkeypress="if(pressEnter(event)){query();return false;}"/>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		</div>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ����ά���б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
		</html:form>
	</body>
</html>
