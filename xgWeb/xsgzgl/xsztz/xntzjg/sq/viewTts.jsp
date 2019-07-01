<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/zjcm/wsjc/wsflr/js/wsflr.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xntzjg/sq/js/jcftzsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<script type="text/javascript">
		function query(){
			var map = {};
			map["cxmc"] = jQuery("#cxmc").val();
			jQuery("#dataTable").reloadGrid(map);
		}
		jQuery(function(){
			var gridSetting = {
				caption:"����ѧ���б� ",
				pager:"pager",
				url:"jcftz_sq.do?method=TtrenDing&type=query&xmdm=${xmdm}"
			};
				
			var colList=[
				   {label:'key',name:'ttjgid', index: 'ttjgid',hidden:true,key:true},
				   {label:'�Ŷ�����',name:'tdmc', index: 'tdmc',width:'10%'},
				   {label:'�ӳ�����',name:'xm', index: 'xm',width:'10%'},
				   {label:'��Ա��',name:'cys', index: 'cys',width:'5%'},
				   {label:'�ӳ���</br>��Ժϵ',name:'xymc', index: 'xymc',width:'10%'},
				   {label:'��Ŀ</br>������',name:'jcxf', index: 'jcxf',width:'8%'},
				   {label:'�ύ״̬',name:'xfrdsqzt', index: 'xfrdsqzt',hidden:true},
				   {label:'jxdm',name:'jxdm', index: 'jxdm',hidden:true},
				   {label:'sfqq',name:'sfqq', index: 'sfqq',hidden:true},
				   {label:'jxmc',name:'jxmc', index: 'jxmc',hidden:true}
				];
						
				var json = {label:'������</br>������',
							name:"tzhjcf",
							index:"tzhjcf"							
							};
					json["formatter"] = function(cell,rowObject){					
							var html="";													
							if(rowObject["xfrdsqzt"] == '1'){
								return cell;
							}else{
								html+=cell==null ? rowObject["jcxf"] : cell;
								return html;
							}																		
					}				
			colList.push(json);
			colList.push({label:'��ý���',	name:"jxmc",index:"jxmc"	});
			colList.push({label:'�Ƿ�</br>ȱ��',	name:"sfqqmc",index:"sfqqmc"});
			colList.push({label:'���</br>��ѧ��',name:'zf', index: 'zf',width:'7%'});
			//colList.push({label:'�ύ״̬',name:'tjztmc', index: 'tjztmc',width:'7%'});
			
			<logic:equal name="xxdm" value="13627">
			colList.push({label:'��ע1',name:'bz1',index:'bz1',width:'10%'});
			colList.push({label:'��ע2',name:'bz2',index:'bz2',width:'10%'});
			colList.push({label:'��ע3',name:'bz3',index:'bz3',width:'10%'});
			colList.push({label:'��ע4',name:'bz4',index:'bz4',width:'10%'});
			colList.push({label:'��ע5',name:'bz5',index:'bz5',width:'10%'});
			</logic:equal>
			
			gridSetting["colList"] = colList;
			jQuery("#dataTable").initGrid(gridSetting);
			jQuery(".nowrap").find("th").eq(8).append("<font color='red'>*</font>");
		});
		</script>
		
		<style>
			/*���ղ���Ŀ����ʱ����ҳ�б����ʾ��ȫ�������б��������ʾ*/
			.formbox {overflow:scroll}
			.formbox table tbody tr td{white-space:nowrap;}
		</style>
		
	</head>

	<body>	
	<html:form action="/jcftz_sq" method="post">	
		<div class="toolbox">
			<!-- ��ť -->
			<input type="hidden" id="jgid" value="${rs.jgid}" />
			<input type="hidden" id="xmdm" value="${xmdm}" />
			<input type="hidden" id="csms" value="${rs.csms}" />
			<%@ include file="/comm/hiddenValue.jsp"%>				
			<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th width="15%">
								ѧ��/����
							</th>
							<td>
								<input type="text" id="cxmc"
									onkeypress="if(event.keyCode==13){query();}" />
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go"
										onclick="query()">
										�� ѯ
									</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
		</div>
		<div class="formbox" style="overflow: auto;">
			<!--����start-->
			<h3 class="datetitle_01">
				<span><font color="blue">${rs.xn}&nbsp;${rs.xqmc}&nbsp;${rs.xmmc}</font> </span>
			</h3>
			<div style="overflow: auto;">
				<table id="dataTable" ></table>
			</div>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
