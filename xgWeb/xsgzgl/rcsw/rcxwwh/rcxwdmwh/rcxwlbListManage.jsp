<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/rcsw/rcxwwh/rcxwdmwh/js/rcxwlbManage.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
            var xxdm = "${xxdm}";
		function add(){
			var url = "rcsw_rcxwwh_rcxwdmwhgl.do?method=addRcxwlb";
			var title = "������Ϊ���";
            if("13431" == xxdm) title="���Ӽӷ����";
			showDialog(title,700,250,url);
		}
		
		function update(){
			var rows = jQuery("#dataTable").getSeletRow();

			if (rows.length != 1){
				showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			} else {
				jQuery.post("rcsw_rcxwwh_rcxwdmwhgl.do?method=checkRcxwlb",{rcxwlbdm:rows[0]["rcxwlbdm"]},function(data){
					if(data["message"] == ""){
						var url = 'rcsw_rcxwwh_rcxwdmwhgl.do?method=updateRcxwlb&rcxwlbdm='+rows[0]["rcxwlbdm"];
						var title = "�޸���Ϊ���";
                        if("13431" == xxdm) title="�޸ļӷ����";
						showDialog(title,700,250,url);
					}else{
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}
				},'json');
			}
		}
		
		jQuery(function(){
			titleXwdlmc();
		})
		
		function titleLoad(id){
			if(jQuery("#"+id)){
			jQuery("#"+id).children("option").each(function(){
				jQuery(this).attr("title",jQuery(this).text());
			});
			}
		}

		function titleXwdlmc(){

			setTimeout("titleLoad('rcxwlbdlmc')",500);
		}

		function updateSfqy(sfqy){
			var msg = "����";
			if(sfqy == '0'){
				msg = "ͣ��";
			}
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0){
				showAlertDivLayer("��ѡ����Ҫ"+msg+"�ļ�¼��");
			} else {
				showConfirmDivLayer("��ȷ��Ҫ"+msg+"ѡ��ļ�¼��",{"okFun":function(){
						jQuery.post("rcsw_rcxwwh_rcxwdmwhgl.do?method=sfqyRcxwlb",{values:ids.toString(),sfqy:sfqy},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
				}});
				
			}
		}
		
		</script>
	</head>
	<body>
	<html:form action="/rcsw_rcxwwh_rcxwdmwhgl" method="post">
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
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
					<li><a href="javascript:void(0);" onclick="updateSfqy('1');" class="btn_shtg">����</a></li>						
					<li><a href="javascript:void(0);" onclick="updateSfqy('0');" class="btn_shbtg">ͣ��</a></li>
				</ul>
			</div>
			</logic:equal>
			<div class="compTab" id="card">
				<div class="comp_title">
					<ul>
						<li >
							<a href="#" onclick="newChgCode(this);return false;" id="rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwdlManage">
								<logic:notEqual name="xxdm" value="13431">
									<span>��Ϊ����</span>
								</logic:notEqual>
								<logic:equal name="xxdm" value="13431">
									<span>�ӷִ���</span>
								</logic:equal>
							</a>
						</li>
						<li class="ha">
							<a href="#" onclick="newChgCode(this);return false;" id="rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwlbManage">
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
						<th width="10%">
							<logic:notEqual name="xxdm" value="13431">
								<span>������Ϊ����</span>
							</logic:notEqual>
							<logic:equal name="xxdm" value="13431">
								<span>�����ӷִ���</span>
							</logic:equal>
						</th>
						<td width="8%">
							<html:select property="rcxwlbdlmc" styleId="rcxwlbdlmc" style="width:130px" >
									<html:option value=""></html:option>
									<html:options collection="xwdlList" property="rcxwlbdlmc" labelProperty="rcxwlbdlmc" />
							</html:select>
						</td>
						<th width="10%" >
							<logic:notEqual name="xxdm" value="13431">
								<span>��Ϊ�������</span>
							</logic:notEqual>
							<logic:equal name="xxdm" value="13431">
								<span>�ӷ��������</span>
							</logic:equal>
						</th>
						<td width="8%">
							<input type="text" id="rcxwlbmc" name="rcxwlbmc" maxleng="20" 
							   onkeypress="if(pressEnter(event)){query();return false;}"
							/>
						</td>
						<th width="10%">
							����״̬
						</th>
						<td width="8%">
							<html:select property="sfqy" style="width:130px" styleId="sfqy">
								<html:option value="">--��ѡ��--</html:option>
								<html:option value="1">����</html:option>
								<html:option value="0">ͣ��</html:option>
							</html:select>
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
