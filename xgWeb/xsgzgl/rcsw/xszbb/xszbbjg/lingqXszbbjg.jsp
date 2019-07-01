<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script	type="text/javascript">
		function saveForm(){
			
			var sflq = jQuery("[name=sflq]:checked").val();
			
			if(undefined== sflq){
				showAlert("请选择是否录取");
				return false;
			}
			var lqsj=jQuery("#lqsj").val();
			if("1"==sflq&&lqsj==""){
				showAlert("请选择领取时间");
				return false;				
			}
			var bz=jQuery("#bz").val();
			var api = frameElement.api;
			var W = api.opener;
			var bbjgid="";
			var rows = W.jQuery("#dataTable").getSeletRow();
			jQuery.each(rows,function(i,row){
				bbjgid+=row["bbjgid"];
				bbjgid+=",";
			});
			
			jQuery.post(
				"rcsw_xszbb_bbjggl.do?method=lingqXszbbjg&type=save",
				{
					bbjgid:bbjgid,
					sflq:sflq,
					lqsj:lqsj,
					bz:bz,
				},function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						W.jQuery("#dataTable").reloadGrid();
						iFClose();
					}});
				},
				'json'
			);
		}


		//更新是否领取
		function changeLqsj(){
			var sflq = jQuery("[name=sflq]:checked").val();
			if("1"==sflq){
				jQuery("#lqsjTr").show();
				jQuery("#lqbzTr").show();
			}else if("0"==sflq){
				jQuery("#lqsjTr").hide();
				jQuery("#lqbzTr").hide();
			}
		}

		  jQuery(function(){
			  changeLqsj();
			})
		</script>
		
	</head>
	<body>		
		<html:form action="/rcsw_xszbb_bbjggl" method="post" styleId="xszbbjgForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="2"><span>领取登记</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="30%"><span class="red">*</span>是否已领取</th>
<%--						<td>--%>
<%--						   <logic:present name="kgList">--%>
<%--									<logic:iterate id="o" name="kgList" >--%>
<%--										<html:radio property="sflq" onclick="changeLqsj();" name = "sflq" value="${o.dm}">${o.mc}</html:radio>--%>
<%--									</logic:iterate>								--%>
<%--								</logic:present>--%>
<%--						</td>--%>
						<td>
							<html:radio property="sflq" onclick="changeLqsj();" value="1"  styleId="sflq1" ><label for="sflq1" style='cursor:pointer' >是</label>
							</html:radio>
							<html:radio property="sflq" onclick="changeLqsj();" value="0" styleId="sflq0" ><label for="sflq0" style='cursor:pointer' >否</label>
							</html:radio>
						</td>
						</tr>
						<tr id="lqsjTr">
							<th ><span class="red">*</span>领取时间</th>
							<td>
								<html:text property="lqsj" styleId="lqsj" style="width:80px" 
									onfocus="showCalendar('lqsj','yyyy-MM-dd','','','210','10');" />
							</td>
						</tr>
						<tr id="lqbzTr">
							<th >
								备注&nbsp;
								<br />
								<font color="red">(限50字)</font>	
							</th>
							<td>
								<html:textarea rows="4" property="bz" styleId="bz" style="width:97%" onblur="checkLen(this,50);"></html:textarea>
							</td>
						</tr>
				</tbody>
				</table>
				</div>
				<div style="height:30px"></div>
				<div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 5;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();">
										关 闭
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

