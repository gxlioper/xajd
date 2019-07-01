<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"��ѯ�б�",
					pager:"pager",
					url:"rcsw_zxzx_xszxzxgl.do?method=xszxzxManage&type=query",
					colList:[
						{label:'key',name:'zxid', index: 'zxid',key:true ,hidden:true},
					   {label:'��ѯ���',name:'bkmc', index: 'bkmc',width:'15%'},
					   {label:'��ѯ����',name:'zxzt', index: 'zxzt',width:'46%',formatter:zxztLink},
					   {label:'��ѯʱ��',name:'zxsjtemp', index: 'zxsjtemp',width:'17%'},
					   {label:'��ѯ��',name:'zxrxm', index: 'zxrxm',width:'12%'},
					   {label:'�ظ�״̬',name:'sfhfmc', index: 'sfhfmc',width:'10%'}
					],
					sortname: "sfzd desc,sfhf asc,zxsj",
				 	sortorder: "desc"
			};
			var gridSettingCjwt = {
					caption:"��ѯ�б�",
					pager:"pager",
					url:"rcsw_zxzx_xszxzxgl.do?method=xszxzxManage&type=query",
					colList:[
						{label:'key',name:'zxid', index: 'zxid',key:true ,hidden:true},
					   {label:'��ѯ���',name:'bkmc', index: 'bkmc',width:'15%'},
					   {label:'��ѯ����',name:'zxzt', index: 'zxzt',width:'85%',formatter:zxztLink}
					],
					sortname: "sfzd desc,zxsj",
				 	sortorder: "desc"
			};
			var gridSettingWdzx = {
					caption:"��ѯ�б�",
					pager:"pager",
					url:"rcsw_zxzx_xszxzxgl.do?method=xszxzxManage&type=query",
					colList:[
						{label:'key',name:'zxid', index: 'zxid',key:true ,hidden:true},
					   {label:'��ѯ���',name:'bkmc', index: 'bkmc',width:'15%'},
					   {label:'��ѯ����',name:'zxzt', index: 'zxzt',width:'55%',formatter:zxztLink},
					   {label:'��ѯʱ��',name:'zxsjtemp', index: 'zxsjtemp',width:'17%'},
					   {label:'�ظ�״̬',name:'sfhfmc', index: 'sfhfmc',width:'10%'}
					],
					sortname: "sfhf asc,zxsj",
				 	sortorder: "desc"
			};
			jQuery(function(){
				var map = new Array();
					map["tab"] = "all";
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);	
				jQuery("#dataTable").css("width","99.9%");// ����ҳǩ�л�ʱ,������	
			});
			function query(){
				var map = new Array();
				var tab = jQuery("#tab").val();
				map["tab"] = tab;
				map["bkid"] = jQuery("#bkid").val();
				map["zxzt"] = jQuery("#zxzt").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			function selectTab(obj,tab){
				jQuery("#bkid").val("");
				jQuery("#zxzt").val("");
				jQuery("#tab").val(tab);
				var map = new Array();
					map["tab"] = tab;
				if(tab == "cjwt"){
					gridSettingCjwt["params"] = map;
					jQuery("#dataTable").initGrid(gridSettingCjwt);
				}else if(tab == "wdzx"){
					gridSettingWdzx["params"] = map;
					jQuery("#dataTable").initGrid(gridSettingWdzx);
				}else{			
					gridSetting["params"] = map;
					jQuery("#dataTable").initGrid(gridSetting);
				}
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
				query();
			}
			function xszxzxView(zxid) {
				var tab = jQuery("#tab").val();
				var url = "rcsw_zxzx_xszxzxgl.do?method=viewXszxzx&zxid=" + zxid;
				if(tab == 'cjwt'){
					url = "rcsw_zxzx_cjwtszgl.do?method=viewCjwtsz&zxid=" + zxid + "&tab=" + tab;
				}
				showDialog("�鿴��ѯ", 750,300, url);
			}
			function zxztLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='xszxzxView(\"" + rowObject["zxid"] + "\");'>" + cellValue + "</a>";
			}
			function add(){
				var url = "rcsw_zxzx_xszxzxgl.do?method=addXszxzx";
				var title = "������ѯ";
				showDialog(title,750,240,url);
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<input type="hidden" value="all" id="tab"/>
		<html:form action="/rcsw_zxzx_xszxzxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
							<li>
								<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">��ѯ</a>
							</li>
					</ul>
				</div>
				</logic:equal>
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'all');"><span>������ѯ</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'cjwt');"><span>��������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'wdzx');"><span>�ҵ���ѯ</span></a></li>
			      </ul>
			    </div>
				<!-- �������� -->	
				<div class="searchtab">
					<table width="100%" border="0">
						<tr>
							<th width="12%">��ѯ����</th>
							<td width="5%">
								<input type="text" id="zxzt" onkeypress="if(pressEnter(event)){query();return false;}"/>
							</td>
							<th width="12%">
								��ѯ���
							</th>
							<td width="5%">
								<html:select property="bkid" styleId="bkid" style="width:150px;">
									<html:option value=""></html:option>
									<html:options collection="zxbkszList" property="bkid" labelProperty="bkmc" />
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
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��ѯ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
