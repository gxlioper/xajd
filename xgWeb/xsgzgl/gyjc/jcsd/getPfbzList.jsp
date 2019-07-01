<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/jcsd/js/pfbz.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"",
				pager:"pager",
				url:"gyjc_pfbz.do?method=getPfbzList&type=query",
				colList:[
					{label:'��Ŀ/Ҫ��',name:'wsqkyq', index: 'wsqkyq',width:'80%'},
					{label:'���',name:'xh', index: 'xh',width:'20%'},
					{label:'guid',name:'guid', index: 'guid',hidden:true},
					{label:'fjid',name:'fjid', index: 'fjid',hidden:true}
				],
				params:{jjlx:"1"},
				radioselect:false,
				pageList:[10000]
		}

		var gridSetting1 = {
				caption:"",
				pager:"pager",
				url:"gyjc_pfbz.do?method=getPfbzList&type=query",
				colList:[
					{label:'��Ŀ/Ҫ��',name:'wsqkyq', index: 'wsqkyq',width:'80%'},
					{label:'���',name:'xh', index: 'xh',width:'20%'},
					{label:'guid',name:'guid', index: 'guid',hidden:true},
					{label:'fjid',name:'fjid', index: 'fjid',hidden:true}
				],
				params:{jjlx:"2"},
				radioselect:false,
				pageList:[10000]
		}

		var gridSetting2 = {
				caption:"",
				pager:"pager",
				url:"gyjc_pfbz.do?method=getPfbzList&type=query",
				colList:[
					{label:'��Ŀ/Ҫ��',name:'wsqkyq', index: 'wsqkyq',width:'80%'},
					{label:'���',name:'xh', index: 'xh',width:'20%'},
					{label:'guid',name:'guid', index: 'guid',hidden:true},
					{label:'fjid',name:'fjid', index: 'fjid',hidden:true}
				],
				params:{jjlx:"3"},
				radioselect:false,
				pageList:[10000]
		}
			
		jQuery(function(){
			var map = {};
			map["jjlx"]="1";
			map["xydm"] = jQuery("#xydm").val();
			map["js"] = jQuery("#userType").val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		</script>
	</head>

	<body>
		<html:form action="/gyjc_jcsd">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="xydm" value="${xydm}"/>
			<input type="hidden" id="userType" value="${userType}"/>
			<input type="hidden" id="jjlx" value="1"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_fh" onclick="fhjcsd();return false;"  >����</a>
						</li>	
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >����</a>
						</li>	
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" 
							   class="btn_xg">�޸�</a>
						</li>	
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" 
							   class="btn_sc">ɾ��</a>
						</li>	
						
					</ul>
				</div>
					<div class="searchtab">
				<table width="100%" border="0">
					<tbody><tr>
						<th width="12%">��Ŀ/Ҫ��</th>
						<td>
							<input type="text" id="wsqkyq" name="wsqkyq" maxleng="20" onkeypress="if(pressEnter(event)){searchRs();return false;}">
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="searchRs()">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>					
				</tbody></table>
			</div>
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>�������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'2');"><span>��ȫ���</span></a></li>
			         <li><a href="javascript:void(0);" onclick="selectTab(this,'3');"><span>���ɼ��</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���ֱ�׼�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
