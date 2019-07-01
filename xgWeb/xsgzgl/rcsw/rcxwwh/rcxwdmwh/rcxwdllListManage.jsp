<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/rcxwwh/rcxwdmwh/js/rcxwdlManage.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>

		<script type="text/javascript">
         var xxdm = "${xxdm}";
		function add(){
			var url = "rcsw_rcxwwh_rcxwdmwhgl.do?method=addRcxwdl";
			var title = "������Ϊ����";

			if("13431" == xxdm) title="���Ӽӷִ���";
			showDialog(title,470,180,url);
		}
		
		function update(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			} else {
				jQuery.post("rcsw_rcxwwh_rcxwdmwhgl.do?method=checkRcxwdl",{rcxwlbdldm:rows[0]["rcxwlbdldm"]},function(data){
					if(data["message"] == ""){
						var url = 'rcsw_rcxwwh_rcxwdmwhgl.do?method=updateRcxwdl&rcxwlbdldm='+rows[0]["rcxwlbdldm"];
						var title = "�޸���Ϊ����";
                        if("13431" == xxdm) title="�޸ļӷִ���";
						showDialog(title,470,180,url);
					}else{
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}
				},'json');
				
			}
		}
		</script>
		
	</head>
	<body>
	<html:form action="/rcsw_rcxwwh_rcxwdmwhgl" method="post">
		<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/>
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
					<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
					</logic:equal>
				</ul>
			</div>
			</logic:equal>
			<div class="compTab" id="card">
				<div class="comp_title">
					<ul>
						<li class="ha">
							<a href="#" onclick="newChgCode(this);return false;"
							   		id="rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwdlManage">
								<logic:notEqual name="xxdm" value="13431">
									<span>��Ϊ����</span>
								</logic:notEqual>
								<logic:equal name="xxdm" value="13431">
									<span>�ӷִ���</span>
								</logic:equal>
							</a>
						</li>
						<li>
							<a href="#" onclick="newChgCode(this);return false;"
							   		id="rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwlbManage">
								<logic:notEqual name="xxdm" value="13431">
									<span>��Ϊ���</span>
								</logic:notEqual>
								<logic:equal name="xxdm" value="13431">
									<span>�ӷ����</span>
								</logic:equal>
							</a>
						</li>
					</ul>
				</div>
			</div>	
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">
							<logic:notEqual name="xxdm" value="13431">
								��Ϊ��������
							</logic:notEqual>
							<logic:equal name="xxdm" value="13431">
								�ӷִ�������
							</logic:equal>
						</th>
						<td width="5%">
							<input type="text" id="rcxwlbdlmc" name="rcxwlbdlmc" maxleng="20" 
							onkeypress="if(pressEnter(event)){query();return false;}"
							/>
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
					<logic:equal name="xxdm" value="13815">	
						<span>����ѧ�ִ���ά���б�&nbsp;&nbsp; </span>
					</logic:equal>
					<logic:notEqual name="xxdm" value="13815">
						<logic:notEqual name="xxdm" value="13431">
							<span>�ճ���Ϊ����ά���б�&nbsp;&nbsp; </span>
						</logic:notEqual>
					</logic:notEqual>
					<logic:equal name="xxdm" value="13431">
						<span>�ӷ��������ά���б�&nbsp;&nbsp; </span>
					</logic:equal>
				</h3>
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
