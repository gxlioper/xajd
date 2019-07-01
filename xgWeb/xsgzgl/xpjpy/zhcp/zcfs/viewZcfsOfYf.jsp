<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<style>
			/*���ղ���Ŀ����ʱ����ҳ�б����ʾ��ȫ�������б��������ʾ*/
			.formbox table tbody tr td{white-space:nowrap;}
		</style>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zhcp/zcfs/js/zcfs.js"></script>
		<script type="text/javascript">
			function getGrid(){
				zcyf = jQuery("#zcyf").val();//ѡ���ܷ�
				var gridSetting = {
					caption:"����ѧ���б� ",
					pager:"pager",
					url:"xpj_zcfs_yf.do?method=viewZcfs&doType=query",
					params:{"id":jQuery("#id").val(),"bjdm":"${bjxxMap.bjdm }","xn":jQuery("#xn").val(),"xq":jQuery("#xq").val(),"zcyf":jQuery("#zcyf").val()},
					multiselect:false
				};
				if('${flag}' == "aytjzcf"){
					gridSetting["url"]="xpj_zcfs.do?method=viewZcfsOfyf&doType=query";
				}
					
				var zcxm = jQuery("font[name=zcxm]");
				var colList=[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'15%'},
					   {label:'����',name:'xm', index: 'xm',width:'15%'}
					];
		
				jQuery.each(zcxm,function(i,n){
					var json = {label:jQuery(n).attr("xmmc"),
								name:"fs"+i,
								index:"fs"+i
							};
					
					if("all"!=zcyf&&(jQuery(n).attr("xmlx") == "3" || jQuery(n).attr("jktb") !="")){
						json["hidden"]=true;
						}
					colList.push(json);
				});
				gridSetting["colList"] = colList;
				return gridSetting;
			}
			jQuery(function(){
				jQuery("#zcyf").change(function(){
					var gridSetting = getGrid();
					jQuery("#dataTable").initGrid(gridSetting);
					});
				var gridSetting = getGrid();
				jQuery("#dataTable").initGrid(gridSetting);
				});
			
		</script>
	</head>

	<body>
		<html:form action="/xpj_zcfs_yf">
		<html:hidden property="id" styleId="id"/>
		<input type="hidden" value="${cssz.xn }" id="xn" name="xn" />
		<input type="hidden" value="${cssz.xq }" id="xq" name="xq" />
		<input type="hidden" value="${bjxxMap.bjdm }" id="bjdm" name="bjdm" />
		<input type="hidden" value="${xpjZcfsModel.id }" id="id" name="id" />
		<input type="hidden" value="${szyf }" id="szyf" name="szyf" />
		<logic:present name="zcxmList">
			<logic:iterate id="z" name="zcxmList">
				<font style="display:none;" xmdm="${z.xmdm }" xmmc="${z.xmmc }" name="zcxm" xmlx="${z.xmlx }" jktb="${z.jktb }"></font>
			</logic:iterate>
		</logic:present>
		
		
		<div class="toolbox">
			<!-- �������� -->	
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">ѧ�� / ����</th>
						<td>
							<input type="text" id="xh" name="xh" maxleng="20" onkeypress="if(event.keyCode==13){doQuery();}"/>
						</td>
						<th width="12%">�۲��·�</th>
						<td>
							<html:select property="zcyf" styleId="zcyf" style="width:150px">
							<html:option value="all">�ܷ�</html:option>
							<html:options collection="zcyfList" property="zcyf"
								labelProperty="zcyfmc" />
							</html:select>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="doQuery();">
									�� ѯ
								</button>
								<button type="button" class="btn_cx" onclick="exportZcfOfYf();">
									�� ��
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
			<!-- �������� end-->
		</div>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span><font color="blue">${cssz.zqmc}&nbsp;${bjxxMap.bjmc }��</font>����ѧ���б� </span>
			</h3>
		
			<div style="overflow: auto;">
				<table id="dataTable" ></table>
			</div>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
