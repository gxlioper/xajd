<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dekt/qnzyhd/js/qnzyhd.js"></script>
		<script type="text/javascript">
		function searchRs() {
			var map = getSuperSearch();
			var shzt = jQuery("#shzt").val();
			if (null!=shzt&&shzt != "") {
				map["shzt"] = shzt;
			}else{
				map["shzt"] = "dsh";
			}
			jQuery("#dataTable").reloadGrid(map);
		}
		
		var gridSetting = {
				caption:"־Ը�߷�����������б�",
				pager:"pager",
				url:"zyhd.do?method=hdfbshList&type=query",
				colList : [
							{ label : 'hdid', name : 'hdid', index : 'hdid',key : true, hidden : true },
							{ label : '�����', name : 'hdmc', index : 'hdmc', width : '15%' },
							{ label : '��������', name : 'fwlxmc', index : 'fwlxmc', width : '10%' },
							{ label : '��������ʱ', name : 'jbfwgs', index : 'jbfwgs', width : '10%' },
							{ label : '��ʼʱ��', name : 'hdkssj', index : 'hdkssj', width : '10%' },
							{ label : '�������', name : 'fwdx', index : 'fwdx', width : '10%' },
							{ label : '�޶�����', name : 'xdrs', index : 'xdrs', width : '10%' },
							{ label : '������', name : 'hdfzrxm', index : 'hdfzrxm', width : '10%' },
							{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '18%' },
							{ label : 'rs', name : 'rs', index : 'rs', hidden : true }
							],
				params:{shzt:"dsh"},
			 	radioselect:false
		}

		var gridSetting2 = {
				caption:"־Ը�߷�����������б�",
				pager:"pager",
				url:"zyhd.do?method=hdfbshList&type=query",
				colList : [
							{ label : 'hdid', name : 'hdid', index : 'hdid',key : true, hidden : true },
							{ label : '�����', name : 'hdmc', index : 'hdmc', width : '15%' },
							{ label : '��������', name : 'fwlxmc', index : 'fwlxmc', width : '10%' },
							{ label : '��������ʱ', name : 'jbfwgs', index : 'jbfwgs', width : '10%' },
							{ label : '��ʼʱ��', name : 'hdkssj', index : 'hdkssj', width : '10%' },
							{ label : '�������', name : 'fwdx', index : 'fwdx', width : '10%' },
							{ label : '�޶�����', name : 'xdrs', index : 'xdrs', width : '10%' },
							{ label : '������', name : 'hdfzrxm', index : 'hdfzrxm', width : '10%' },
							{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '18%' },
							{ label : 'rs', name : 'rs', index : 'rs', hidden : true }
							],
				params:{shzt:"ysh"},
			 	radioselect:false
		}
			
		jQuery(function(){
			var map = getSuperSearch();
			map["shzt"]="dsh";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
	
			
		//�������
		function cancelSh() {
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length == 0) {
				showAlertDivLayer("��ѡ����Ҫ�����ļ�¼")
				return false;
			}
			var flg = true;
			var guid = new Array();
			jQuery.each(rows, function(i, row) {
				if(row["rs"] > 0){
					flg = false;
					return false;
				}
				guid.push(row["hdid"]);
			});
			if(!flg){
				showAlertDivLayer("���ڻ����ѧ�����������ܳ�����")
				return false;
			}
			showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
				"okFun" : function() {
					jQuery.post("zyhd.do?method=cancelSh", {
						ids : guid,
					}, function(data) {		
						showAlertDivLayer(data["message"], {}, {
							"clkFun" : function() {
								jQuery("#dataTable").reloadGrid();
							}
						});
					}, 'json');
				}
			})	
		}

		// �������
		function savePlsh(shzt, shyj) {
			var rows = jQuery("#dataTable").getSeletRow();
			var guid = new Array();
			jQuery.each(rows, function(i, row) {
				guid.push(row["hdid"]);
			});
			jQuery.post("zyhd.do?method=sbPlsh&type=save", {
				shzt : shzt,
				ids : guid,
				shyj:shyj
			}, function(data) {
				
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function() {
						jQuery("#dataTable").reloadGrid();
					}
				});
			}, 'json');
		}
				
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/zyhdry">
			<input type="hidden" id="shzt" value="dsh"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li id="li_sh">
								<a href="javascript:void(0);" onclick="sbsh();return false;" 
								   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
								   class="btn_sh">���</a>
							</li>
							<li id="li_qx" style="display: none;">
								<a href="javascript:void(0);" onclick="cancelSh();return false;" 
								   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
								   class="btn_qxsh">����</a>
							</li>
						</logic:equal>						
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>־Ը�߷�����������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
