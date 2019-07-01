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
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxzhcp/zcwh/js/zcwh.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					caption:"����ѧ���б� ",
					pager:"pager",
					url:"xpjpy_zcwh.do?method=zcwhView&doType=query" + "&zcxmdmForTop=" + jQuery("#zcxmdmForTop").val(),
					params:{"id":jQuery("#id").val()},
					multiselect:false
				};
					
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
					colList.push(json);
				});
				gridSetting["colList"] = colList;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
		</script>
	</head>

	<body>
		<html:form action="/xpjpy_zcwh">
			<html:hidden property="id" styleId="id"/>
			<input type="hidden" value="${cssz.xn}" id="xn" name="xn" />
			<input type="hidden" value="${zcwhForm.id }" id="id" name="id" />
			<input type="hidden" value='${zcxmdmForTop}' id='zcxmdmForTop' name ='zcxmdmForTop'/>
		
			<logic:present name="zcxmList">
				<logic:iterate id="z" name="zcxmList">
					<font style="display:none;" xmdm="${z.xmdm }" xmmc="${z.xmmc }" name="zcxm"></font>
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
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="doQuery();">
										�� ѯ
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
					<span><font color="blue">${cssz.xn}&nbsp;&nbsp;${xyxxMap.xymc }</font>&nbsp;&nbsp;����ѧ���б� </span>
				</h3>
				<div style="overflow: auto;">
					<table id="dataTable" ></table>
				</div>
				<div id="pager"></div>
			</div>
			<div style="height: 30px"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
								<div class="btn">
									<button type="button" onclick="iFClose();">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>
