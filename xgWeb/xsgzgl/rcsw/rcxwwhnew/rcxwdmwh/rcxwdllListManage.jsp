<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		  var xxdm = jQuery("#xxdm").val();

		  var gridSetting;

		  if(xxdm == '10704'){
			  gridSetting = {
				  caption:"�ۺϲ��������б�",
					pager:"pager",
					url:"rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwdlManage&type=query",
					colList:[
					   {label:'�ۺϲ����������',name:'rcxwlbdldm', index: 'rcxwlbdldm',key:true,width:'14%'},
					   {label:'�ۺϲ�����������',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'25%'},
					   {label:'�����ۺϲ������',name:'rcxwlbmc', index: 'rcxwlbmc',width:'24%'},
					   {label:'�������',name:'lcxx', index: 'lcxx',width:'27%'},
					   {label : '���뿪��',name : 'sqkg',index : 'sqkg',width : '10%',formatter:setSqkg},
					   {label : '���뿪ʼʱ��',name : 'sqkssj',index : 'sqkssj',hidden : true},
					   {label : '�������ʱ��',name : 'sqjssj',index : 'sqjssj',hidden : true}
					],
					sortname: "rcxwlbdldm",
				 	sortorder: "asc"
			  }
		  }else{
			  gridSetting = {
					caption:"��Ϊ�����б�",
					pager:"pager",
					url:"rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwdlManage&type=query",
					colList:[
					   {label:'��Ϊ�������',name:'rcxwlbdldm', index: 'rcxwlbdldm',key:true,width:'12%'},
					   {label:'��Ϊ��������',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'21%'},
					   {label:'������Ϊ���',name:'rcxwlbmc', index: 'rcxwlbmc',width:'21%'},
					   {label:'�������',name:'lcxx', index: 'lcxx',width:'25%'},
                        <logic:equal value="10699" name="xxdm">
                        {label:'����ѧԺ',name:'ssxymc', index: 'ssxymc',width:'20%'},
                        {label:'ssxydm',name:'ssxydm', index: 'ssxydm',hidden : true},
                        </logic:equal>
					   {label : '���뿪��',name : 'sqkg',index : 'sqkg',width : '10%',formatter:setSqkg},
					   {label : '���뿪ʼʱ��',name : 'sqkssj',index : 'sqkssj',hidden : true},
					   {label : '�������ʱ��',name : 'sqjssj',index : 'sqjssj',hidden : true}
					],
					sortname: "rcxwlbdldm",
				 	sortorder: "asc"
			}
		  }
		  
		   
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function query(){
				var map = {};
				map["rcxwlbdlmc"] = jQuery("#rcxwlbdlmc").val();
				map["rcxwlbdm"] = jQuery("#rcxwlbdm").val();
                if(xxdm = '10704')
                	map["ssxydm"] = jQuery("#ssxydm").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			function add(){
				var url = "rcsw_rcxwwhnew_rcxwdmwhgl.do?method=addRcxwdl";
				var title; 
				if(xxdm = '10704'){
					title = "�����ۺϲ�������";
				}else{
					title = "������Ϊ����";
				}
				showDialog(title,470,200,url);
			}
			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
					return false;
				}
                var userDep = jQuery("#userDep").val();
                if(rows[0]["ssxydm"] != userDep){
                    if("qx" == userDep){
                        showAlertDivLayer("ֻ���޸�����ѧԺΪȫУ�ļ�¼��");
                    }else{
                        showAlertDivLayer("ֻ���޸�����ѧԺΪ��ѧԺ�ļ�¼��");
                    }
                    return false;
                }
                var url = 'rcsw_rcxwwhnew_rcxwdmwhgl.do?method=updateRcxwdl&rcxwlbdldm='+rows[0]["rcxwlbdldm"];
                var title;
                if(xxdm = '10704'){
                    title = "�޸��ۺϲ�������";
                }else{
                    title = "�޸���Ϊ����";
                }
                showDialog(title,470,250,url);
			}
			function del(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
							jQuery.post("rcsw_rcxwwhnew_rcxwdmwhgl.do?method=delRcxwdl",{values:ids.toString()},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
				}
			}
			function newChgCode(obj){
				allNotEmpThenGo(obj.id);
			}
			/*
			 *���뿪�� 
			 */
			function setSqkg(cellValue,rowObject){
				var rcxwlbdldm = rowObject.rcxwlbdldm;
				var value = "δ����";
				var status = '0';
				var color;
				if(cellValue == '1'){
					var currDate = jQuery("#currDate").val();
					if(rowObject.sqkssj != null && currDate < rowObject.sqkssj || rowObject.sqjssj != null && currDate > rowObject.sqjssj ){
					}else{
						value = "�ѿ���";
						status = '1';
					}
				}
				value = setColor(value,status);
				value = "<a  href='javascript:void(0);' onclick='return sjkg(\""+rcxwlbdldm+"\");' >"+value+"</a>";
				return value;
			}
			//���������ʽӰ�죬��ɫ����д��Ԫ����
			function setColor(value,status){
				var color;
				if(status == '1'){
					color = "#004400";
				}else{
					color = "red";
				}
				return value = "<font color='"+color+"'>" + value + "</font>";
			}
			/*
			 * ʱ�俪��
			 */
			function sjkg(rcxwlbdldm) {
				if(rcxwlbdldm == null){//�����ť
					var rows = jQuery("#dataTable").getSeletRow();
					if (rows.length != 1) {
						showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
						return false;
					}
					rcxwlbdldm = rows[0]["rcxwlbdldm"];
				}
				var url = 'rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwlbdldmSjkg&rcxwlbdldm=' + rcxwlbdldm;
				var title = "����ʱ�����";
				showDialog(title, 600, 230, url);
			}
		</script>
	</head>
	<body>
	<input type="hidden" id="xxdm" value="${xxdm}" />
	<input type="hidden" id="userDep" value="${userDep}"/>
	<html:form action="/rcsw_rcxwwhnew_rcxwdmwhgl" method="post">
		<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
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
						<li>
							<a href="#" onclick="newChgCode(this);return false;" id="rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwlbManage">
								<span>
									<logic:equal value="10704" name="xxdm">
										�ۺϲ������
									</logic:equal>
									<logic:notEqual value="10704" name="xxdm">
										��Ϊ���
									</logic:notEqual>
								</span>
							</a>
						</li>
						<li class="ha">
							<a href="#" onclick="newChgCode(this);return false;" id="rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwdlManage">
								<span>
									<logic:equal value="10704" name="xxdm">
										�ۺϲ�������
									</logic:equal>
									<logic:notEqual value="10704" name="xxdm">
										��Ϊ����
									</logic:notEqual>									
								</span>
							</a>
						</li>
						<li>
							<a href="#" onclick="newChgCode(this);return false;" id="rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwxlManage">
								<span>
									<logic:equal value="10704" name="xxdm">
										�ۺϲ���С��
									</logic:equal>
									<logic:notEqual value="10704" name="xxdm">
										��ΪС��
									</logic:notEqual>									
								</span>
							</a>
						</li>
					</ul>
				</div>
			</div>	
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
						<th width="10%">
							<logic:equal value="10704" name="xxdm">
								�����ۺϲ������
							</logic:equal>
							<logic:notEqual value="10704" name="xxdm">
								������Ϊ���
							</logic:notEqual>
						</th>
						<td width="5%">
							<select id="rcxwlbdm" style="width: 150px;">
								<option value=""></option>
								<logic:iterate id="rcxwlbByYhsq" name="rcxwlbListByYhsq" >
									<option value="${rcxwlbByYhsq.rcxwlbdm }" title="${rcxwlbByYhsq.rcxwlbmc }">${rcxwlbByYhsq.rcxwlbmc }</option>
								</logic:iterate>
							</select>
						</td>
						<th width="10%">
							<logic:equal value="10704" name="xxdm">
								�ۺϲ�����������
							</logic:equal>
							<logic:notEqual value="10704" name="xxdm">
								��Ϊ��������
							</logic:notEqual>							
						</th>
						<td width="5%">
							<input type="text" id="rcxwlbdlmc" name="rcxwlbdlmc" maxleng="20" 
							onkeypress="if(pressEnter(event)){query();return false;}"
							/>
						</td>
						<th width="10%">
							����ѧԺ
						</th>
						<td width="5%">
							<html:select property="ssxydm" style="width:180px" styleId="ssxydm">
								<html:option value=""> </html:option>
								<html:option value="qx">ȫУ</html:option>
								<html:options collection="bmList" property="bmdm" labelProperty="bmmc"/>
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
					<span>
						<logic:equal value="10704" name="xxdm">
							�ۺϲ��������б�
						</logic:equal>
						<logic:notEqual value="10704" name="xxdm">
							��Ϊ�����б�
						</logic:notEqual>					
					 </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
