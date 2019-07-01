<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"��ѯ���",
				pager:"pager",
				multiselect:true,
				rowNum:10,
				url:"gyglnew_bxlbrywh.do?method=bxlbList&type=query",
				colList:[
				   {label:'�������ʹ���',name:'bxdm', index: 'bxdm',width:'30%',key:true},
				   {label:'������������',name:'bxmc', index: 'bxmc',width:'30%'},
				   {label:'ά������',name:'ryrs', index: 'ryrs',width:'30%',formatter:setQd}
				],
				sortname: "bxdm",
			 	sortorder: "asc"
			}
			
			jQuery(function(){
				searchRs();
			});
			
			function searchRs(){
				var map = getSuperSearch();	
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			}

			function setQd(cellValue, rowObject) {
				var bxdm = rowObject.bxdm;
				if (bxdm == null) {
					bxdm = "";
				}
				html = "<a  href='javascript:void(0);'  class='name' onclick='viewQd(\"" + bxdm
						+ "\");return false;' >" + cellValue + "</a>";
				return html;
			}

			function viewQd(ids){
				var url = "gyglnew_bxlbrywh.do?method=ViewBxlbYhList&bxlb="+ids;
				var title = "��Աά��";
				showDialog(title,800,505,url);
			}

			function rywh(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length != 1){
					showAlertDivLayer("��ѡ����ά���ı������");
					return false;
				} 
				var url = "gyglnew_bxlbrywh.do?method=getBxlbYhList&bxlb="+ids.toString();
				var title = "��Աά��";
				showDialog(title,800,505,url);
			}
						
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gyglnew_bxlbrywh">
			<div class="toolbox">
				<div class="buttonbox">
				  <ul>
					<logic:equal value="yes" name="writeAble">
						<li>
							<a href="javascript:void(0);" onclick="rywh();return false;" class="btn_sz" >��Աά��</a>
						</li>
					</logic:equal>
				  </ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div>
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ��������б�
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
		<div id="pager"></div>
	</body>
</html>
