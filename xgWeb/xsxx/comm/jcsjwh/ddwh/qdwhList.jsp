<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsxx/comm/jcsjwh/js/ddwh.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"��ѯ���",
				pager:"pager",
				multiselect:true,
				rowNum:10,
				url:"zjjcddwh.do?method=qdwhList&type=query",
				colList:[
				   {label:'��Ӵ���',name:'bjdm', index: 'bjdm',width:'20%',key:true,hidden:true},
				   {label:'�꼶',name:'nj', index: 'nj',width:'20%'},			
				   {label:'ѧԺ����',name:'xymc', index: 'xymc',width:'20%'},	
				   {label:'רҵ����',name:'zymc', index: 'zymc',width:'20%'},
				   {label:'��������',name:'bjmc', index: 'bjmc',width:'20%'}
				],
				sortname: "bjdm",
			 	sortorder: "asc"
			}

			jQuery(function(){
				searchRs();
			});

			//ҳǩ�л�
			function selectTab(flag){
				jQuery("#flag").val(flag);
				jQuery(".ha").removeClass("ha");
				if("0"==flag){
					jQuery("#wjr").addClass("ha");
					jQuery("#wjrbutton").css({"display":""});
					jQuery("#yjrbutton").css({"display":"none"});
				}else{
					jQuery("#yjr").addClass("ha");
					jQuery("#wjrbutton").css({"display":"none"});
					jQuery("#yjrbutton").css({"display":""});
				}
				var map = getSuperSearch();	
				var dddm = jQuery("#dddm").val();
				map["flag"] = flag;
				map["dddm"] = dddm;
				gridSetting["params"] = map;
				jQuery("#dataTable").reloadGrid(map);
			}
			
			function searchRs(){
				var map = getSuperSearch();	
				var flag = jQuery("#flag").val();
				var dddm = jQuery("#dddm").val();
				map["flag"] = flag;
				map["dddm"] = dddm;
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			}
		</script>
	</head>

	<body>
		<html:form action="/zjjcddwh">
		    <html:hidden property="flag" styleId="flag" />
		    <html:hidden property="dddm" styleId="dddm" />
			<div class="toolbox">
				<div class="buttonbox">
				  <ul>
						<li id="wjrbutton">
							<a href="javascript:void(0);" onclick="saveQd();return false;" class="btn_zj" >����</a>
						</li>
						<li style="display:none;" id="yjrbutton">
							<a href="javascript:void(0);" onclick="delQd();return false;" class="btn_xg" >ɾ��</a>
						</li>
				  </ul>
				</div>
			<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
		    <!-- ��ť -->
			<div class="comp_title" id="comp_title">
		      <ul style="width:70%">
		        <li class="ha" id="wjr"><a href="javascript:void(0);" onclick="selectTab('0');"><span>δ����</span></a></li>
		        <li id="yjr"><a href="javascript:void(0);" onclick="selectTab('1');"><span>�Ѽ���</span></a></li>
		      </ul>
		    </div>
			</div>
		</html:form>
		<div>
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ������Ϣ�б�
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
		<div id="pager"></div>
	</body>
</html>
