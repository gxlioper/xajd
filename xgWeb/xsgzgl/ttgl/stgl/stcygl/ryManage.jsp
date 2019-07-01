<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		var gridSetting = {
					caption:"������Ա�б�",
					pager:"pager",
					url:"ttgl_stcygl.do?method=ryManage&type=query",
					colList:[      
				           {label:'key',name:'guid', index: 'guid',hidden:true,key:true},
						   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
						   {label:'����',name:'xm', index: 'xm',width:'7%'},
						   {label:'��Ժ',name:'symc', index: 'symc',width:'10%'},
						   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'10%'},
						   {label:'רҵ',name:'zymc', index: 'zydm',width:'10%'},
						   {label:'�༶',name:'bjmc', index: 'bjdm',width:'10%'},
						   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'10%'},
						   {label:'����',name:'tnzw', index: 'tnzw',width:'8%'},
						   {label:'״̬',name:'shzt', index: 'shzt',width:'8%'}
						],
						params:{fpzt:"dsh",jgid:'${jgid}'},
						sortname: "nj,xymc,zymc,bjmc",
					 	sortorder: "asc"
				};

		var gridSetting2 = {
					caption:"������Ա�б�",
					pager:"pager",
					url:"ttgl_stcygl.do?method=ryManage&type=query",
					colList:[      
				           {label:'key',name:'guid', index: 'guid',hidden:true,key:true},
						   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
						   {label:'����',name:'xm', index: 'xm',width:'7%'},
						   {label:'��Ժ',name:'symc', index: 'symc',width:'10%'},
						   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'10%'},
						   {label:'רҵ',name:'zymc', index: 'zydm',width:'10%'},
						   {label:'�༶',name:'bjmc', index: 'bjdm',width:'10%'},
						   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'10%'},
						   {label:'����',name:'tnzw', index: 'tnzw',width:'8%'},
						   {label:'״̬',name:'shzt', index: 'shzt',width:'8%'}
						],
						params:{fpzt:"ysh",jgid:'${jgid}'},
						sortname: "nj,xymc,zymc,bjmc",
					 	sortorder: "asc"
				};
				
		jQuery(function(){
			var map = getSuperSearch();
			map["glzt"]="dsh";
			map["jgid"]='${jgid}';
			gridSetting["params"]=map;
			jQuery("#dataTable").initGrid(gridSetting);
		});	
			function View(guid) {
			showDialog("�鿴������Ϣ", 790,300, "ttgl_stcygl.do?method=viewsqxx&guid=" + guid);
		}

		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='View(\""+ rowObject["guid"] + "\");'>" + cellValue
					+ "</a>";
		}
		//�л�Tabҳ
		function qh(obj, shzt) {
			jQuery("#glzt").val(shzt);
			if (shzt == "dsh") {
				document.getElementById("tg").style.display='block';
				document.getElementById("jj").style.display='none';
				jQuery("#dataTable").initGrid(gridSetting);
			} else {
				document.getElementById("jj").style.display='block';
				document.getElementById("tg").style.display='none';
				jQuery("#dataTable").initGrid(gridSetting2);
			}
			jQuery(".ha").removeClass("ha");
			jQuery(obj).parent().addClass("ha");
		}
		
		
		function searchRs(){
			var map = getSuperSearch();
			var glzt = jQuery("#glzt").val();
			if(glzt=="dsh"){
				map["glzt"]="dsh";
			}else{
				map["glzt"]="ysh";
			}
			map["sydm"]='${sydm}';
			jQuery("#dataTable").reloadGrid(map);
		}

//����
function sh(shzt){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ��˵���Ա��");
		return false;
	}
	showConfirmDivLayer("��ȷ��Ҫ�����ѡ����Ա��", {
		"okFun" : function() {
			jQuery.post("ttgl_stcygl.do?method=saveSh", {
				guids : ids.toString(),
				shzt : shzt
			},
			function(data){
				if(data["message"]=="����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								var api = frameElement.api,W = api.opener;
								jQuery(W.document).find('#search_go').click();
								jQuery("#dataTable").initGrid(gridSetting);
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	}
			}, 'json');
		}
	});
}
		</script>
	</head>

	<body>
		<html:form action="/ttgl_stcygl">
			<input type="hidden" id="glzt" value="dsh"/> 
			<input type="hidden" id="jgid" value="${jgid}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%">
	        <li class="ha"><a href="javascript:void(0);" onclick="qh(this,'dsh');"><span>�����</span></a></li>
	        <li><a href="javascript:void(0);" onclick="qh(this,'ysh');"><span>�����</span></a></li>
	      </ul>
	    </div>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>������Ա����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow" style="height: 365px;overflow-y: auto;">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
		
		<div style="height:30px;"></div>
			 <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td>
							<div id ="jj" style="display:none" class="btn">
								<button type="button" name="�� ��" onclick="iFClose();">
									�� ��
								</button>
							</div>
							<div id="tg" class="btn">
								<button type="button" onclick="sh('1');">
									ͬ��
								</button>
								<button  type="button" onclick="sh('2');">
									�ܾ�
								</button>
								<button type="button" name="�� ��" onclick="iFClose();">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
	</body>
</html>
