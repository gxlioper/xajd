<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		  
		 	String.prototype.replaceAll = function(s1,s2) { 
			    return this.replace(new RegExp(s1,"gm"),s2); 
			}
			function format(cellValue,rowObject){
				if(cellValue==null){
					return cellValue;
				}else{
					return cellValue.replaceAll('\\n','<br>');
				}
			}

			jQuery(function(){
				  var xxdm = jQuery("#xxdm").val();
				
				  var gridSetting;

				  if(xxdm == '10704'){
					gridSetting = {
						caption:"�ۺϲ���С���б�",
						pager:"pager",
						url:"rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwxlManage&type=query",
						colList:[
						   {label:'�ۺϲ���С�����',name:'rcxwlbxldm', index: 'rcxwlbxldm',key:true,width:'15%'},
						   {label:'�ۺϲ���С������',name:'rcxwlbxlmc', index: 'rcxwlbxlmc',width:'18%'},
						   {label:'�����ۺϲ�������',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'18%'},
						   {label:'�����ۺϲ������',name:'rcxwlbmc', index: 'rcxwlbmc',width:'18%'},
						   {label:'��ֵ����',name:'fzlxmc', index: 'fzlxmc',width:'7%'},
						   {label:'��ֵ',name:'fzqj', index: 'fzqj',width:'12%'},
						   {label:'����˵��',name:'rcxwlbbz', index: 'rcxwlbbz',width:'30%',formatter:format},
						   {label:'�����ۺϲ�������',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
						   {label:'����״̬',name:'sfqymc', index: 'sfqymc',width:'10%'}
						],
						sortname: "rcxwlbxldm",
					 	sortorder: "asc"
					 }
				  }else{
				    gridSetting = {
						caption:"��ΪС���б�",
						pager:"pager",
						url:"rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwxlManage&type=query",
						colList:[
						   {label:'��ΪС�����',name:'rcxwlbxldm', index: 'rcxwlbxldm',key:true,width:'10%'},
						   {label:'��ΪС������',name:'rcxwlbxlmc', index: 'rcxwlbxlmc',width:'14%'},
						   {label:'������Ϊ����',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'14%'},
						   {label:'������Ϊ���',name:'rcxwlbmc', index: 'rcxwlbmc',width:'14%'},
                           {label:'����ѧԺ',name:'ssxymc', index: 'ssxymc',width:'14%'},
						   {label:'��ֵ����',name:'fzlxmc', index: 'fzlxmc',width:'6%'},
						   {label:'��ֵ',name:'fzqj', index: 'fzqj',width:'6%'},
						   {label:'����˵��',name:'rcxwlbbz', index: 'rcxwlbbz',width:'15%',formatter:format},
						   {label:'������Ϊ����',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
                           {label:'����ѧԺ����',name:'ssxydm', index: 'ssxydm',hidden:true},
						   {label:'����״̬',name:'sfqymc', index: 'sfqymc',width:'7%'}
						],
						sortname: "rcxwlbxldm",
					 	sortorder: "asc"
					} 
				  }				
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["rcxwlbdldm"] = jQuery("#rcxwlbdldm").val();
				map["rcxwlbxlmc"] = jQuery("#rcxwlbxlmc").val();
				map["sfqy"] = jQuery("#sfqy").val();
                map["ssxydm"] = jQuery("#ssxydm").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			function add(){
				var url = "rcsw_rcxwwhnew_rcxwdmwhgl.do?method=addRcxwxl";
				var title;
				if(jQuery("#xxdm").val() == '10704'){
					title = "�����ۺϲ���С��";
				}else{
					title = "������ΪС��";
				}
				showDialog(title,700,310,url);
			}
			
			function update(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
					return false;
				}
                if(!checkSsxydm(rows[0]["ssxydm"])) return false;
                jQuery.post("rcsw_rcxwwhnew_rcxwdmwhgl.do?method=checkRcxwxl",{rcxwlbxldm:rows[0]["rcxwlbxldm"]},function(data){
                    if(data["message"] == ""){
                        var url = 'rcsw_rcxwwhnew_rcxwdmwhgl.do?method=updateRcxwxl&rcxwlbxldm='+rows[0]["rcxwlbxldm"];
                        var title;
                        if(jQuery("#xxdm").val() == '10704'){
                            title = "�޸��ۺϲ���С��";
                        }else{
                            title = "�޸���ΪС��";
                        }
                        showDialog(title,700,310,url);
                    }else{
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    }
                },'json');
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
                    var rows = jQuery("#dataTable").getSeletRow();
                    if(!checkSsxydm(rows[0]["ssxydm"])) return false;
					showConfirmDivLayer("��ȷ��Ҫ"+msg+"ѡ��ļ�¼��",{"okFun":function(){
							jQuery.post("rcsw_rcxwwhnew_rcxwdmwhgl.do?method=sfqyRcxwxl",{values:ids.toString(),sfqy:sfqy},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
				}
			}
			
			function del(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
							jQuery.post("rcsw_rcxwwhnew_rcxwdmwhgl.do?method=delRcxwxl",{values:ids.toString()},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
				}
			}
			function newChgCode(obj){
				allNotEmpThenGo(obj.id);
			}
			function  checkSsxydm(ssxydm) {
                var userDep = jQuery("#userDep").val();
                if(ssxydm != userDep){
                    if("qx" == userDep){
                        showAlertDivLayer("ֻѡ������ѧԺΪȫУ�ļ�¼��");
                    }else{
                        showAlertDivLayer("ֻ��ѡ������ѧԺΪ��ѧԺ�ļ�¼��");
                    }
                    return false;
                }
                return true;
            }
		</script>
	</head>
	<body>
	<input type="hidden" id="xxdm" value="${xxdm}" />
	<input type="hidden" name="userDep" id="userDep" value="${userDep}"/>
	<html:form action="/rcsw_rcxwwhnew_rcxwdmwhgl" method="post">
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
						<li>
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
						<li class="ha">
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
					<tr>
						<th width="12%">
							<logic:equal value="10704" name="xxdm">
								�����ۺϲ�������
							</logic:equal>
							<logic:notEqual value="10704" name="xxdm">									
								������Ϊ����
							</logic:notEqual>							
						</th>
						<td width="12%">
							<select id="rcxwlbdldm" style="width: 150px;">
								<option value=""></option>
								<logic:iterate id="rcxwlbdlByYhsq" name="rcxwlbdlListByYhsq" >
									<option value="${rcxwlbdlByYhsq.rcxwlbdldm }" title="${rcxwlbdlByYhsq.rcxwlbdlmc }">${rcxwlbdlByYhsq.rcxwlbdlmc }</option>
								</logic:iterate>
							</select>
						</td>
						<th width="12%">
							<logic:equal value="10704" name="xxdm">
								�ۺϲ���С������
							</logic:equal>
							<logic:notEqual value="10704" name="xxdm">									
								��ΪС������
							</logic:notEqual>							
						</th>
						<td width="12%">
							<input type="text" id="rcxwlbxlmc" onkeypress="if(pressEnter(event)){query();return false;}"
							/>
						</td>
						<td>
							<div style="margin-left: 50px;">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>
					<tr>
						<th width="10%">
							����ѧԺ
						</th>
						<td width="12%">
							<html:select property="ssxydm" style="width:150px" styleId="ssxydm">
								<html:option value=""> </html:option>
								<html:option value="qx">ȫУ</html:option>
								<html:options collection="bmList" property="bmdm" labelProperty="bmmc"/>
							</html:select>
						</td>
						<th width="10%">
							����״̬
						</th>
						<td width="12%">
							<html:select property="sfqy" style="width:150px" styleId="sfqy">
								<html:option value="">--��ѡ��--</html:option>
								<html:option value="1">����</html:option>
								<html:option value="0">ͣ��</html:option>
							</html:select>
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
							�ۺϲ���С���б�
						</logic:equal>
						<logic:notEqual value="10704" name="xxdm">
							��ΪС���б� 
						</logic:notEqual>
					</span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
