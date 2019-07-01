<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">
	

	jQuery(function(){
	
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.id}&tt="+new Date().getTime());
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
		// ======== 评定分值 begin========
		var fzlxmc = '${xwlbxx[0].rcxwfzlxmc}';
		var fzlx = '${xwlbxx[0].rcxwfzlx}';
		var fzsfgd='${xwlbxx[0].fzsfgd}';
		var fzqj = '${xwlbxx[0].fzqj}';
		var zgfz = '${xwlbxx[0].rcxwlbzgfz}';
		var zdfz = '${xwlbxx[0].rcxwlbzdfz}';
		var shfz = '${rs.shfz}';
		var html = "";
		html += "<input type='hidden' id='shfzsfgd' value='"+fzsfgd+"'/>";
		html += "<input type='hidden' name='rcxwfzlx' value='"+fzlx+"'/>";
		if(fzsfgd!=null && fzsfgd=="gd"){
			html += shfz+"<font color=\"blue\">（"+fzlxmc+"，固定值）</font><input type='hidden' name='shfz' id='shfz' value='"+shfz+"'/>";
			html += "<input type='hidden' id='shzgfz' value='"+shfz+"!!"+shfz+"'/>";
		}else {
			html += "<input type='text' name='shfz' id='shfz' style=\"width:50px\" onkeyup=\"checkInputNum(this)\" maxlength='8' value='"+shfz+"'/><font color=\"blue\">（"+fzlxmc+"，范围"+zdfz+"-"+zgfz+"）</font>"; 
			html += "<input type='hidden' id='shzgfz' value='"+zdfz+"!!"+zgfz+"'/>";
		}
		jQuery("#shfzTd").html(html);
		// ======== 评定分值 end========
	});


	
	function saveShzt(){
		var shzt = jQuery("#shjg").val();
		if(jQuery("#shjg").val() == "0"){
			showAlertDivLayer("请选择审核状态！");
			return false;
		}
		// ======== 评定分值 begin========
		var shfz = jQuery('#shfz').val();
		var shzgfz = jQuery('#shzgfz').val();
		if(shfz==''){
			showAlert("评定分值不能为空！");
			return false;
		}
		var arr = shzgfz.split("!!");
		if(arr[0]!=arr[1]){
			if(parseFloat(shfz)<parseFloat(arr[0])||parseFloat(shfz)>parseFloat(arr[1])){
				showAlert("评定分值必须在分值范围内！");
				return false;
			}
		}
		// ======== 评定分值 end========
		var shyj = jQuery("#shyj").val();
		if (jQuery.trim(shyj) == ""){
			showAlertDivLayer("请填写审核意见！");
			return false;
		}
		
		var message;
		if(jQuery("#shjg").val() == "1"){
			message = "通过";
		}
		if(jQuery("#shjg").val() == "2"){
			message = "不通过";
		}
		if(jQuery("#shjg").val() == "3"){
			message = "退回";
		}
		showConfirmDivLayer("您确定" + message + "该申请吗？",{"okFun":function(){
			var url = "rcsw_rcxwwh_rcxwshgl.do?method=rcxwDgsh&type=save";
			ajaxSubFormWithFun("rcxwshForm",url,function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
			});
		}});
	}
	
	</script>
</head>
<body>
	<html:form action="/rcsw_rcxwwh_rcxwshgl" method="post" styleId="rcxwshForm">
			
			<html:hidden name="model" property="id" styleId="id"/>
			<html:hidden name="model" property="xh" styleId="xh"/>		
			<html:hidden name="model" property="splc" styleId="splc"/>
			<html:hidden name="model" property="rcxwlbdldm" styleId="rcxwlbdldm"/>
			<html:hidden name="model" property="rcxwlbdm" styleId="rcxwlbdm"/>
			<html:hidden name="model" property="xn" styleId="xn"/>
			<html:hidden name="model" property="xq" styleId="xq"/>
			<html:hidden name="model" property="fz" styleId="fz"/>
			<html:hidden name="model" property="rcxwjlsj" styleId="rcxwjlsj"/>
			<html:hidden name="model" property="bz" styleId="bz"/>
			<html:hidden name="model" property="shid" styleId="shid"/>
			<html:hidden name="model" property="jlr" styleId="jlr"/>
			<html:hidden name="model" property="fssj" styleId="fssj"/>
			<html:hidden name="model" property="gfly" styleId="gfly"/>
			
		<div style="overflow-x:hidden;overflow-y:auto;">
			
		<table class="formlist" width="95%">
			<thead>
				<tr>
					<th colspan="4">
						<span>学生信息</span>
					</th>
				</tr>
			</thead>
			<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
			</table>
			
			<table width="100%" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>

							<logic:equal name="xxdm" value="13431">
								加分记录信息
							</logic:equal>
							<logic:notEqual name="xxdm" value="13431">
								行为记录信息
							</logic:notEqual>
						</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					学年
				</th>
				<td align="left" width="30%">
					${rs.xn}
				</td>
				<th align="right" width="20%">
					学期
				</th>
				<td align="left" width="30%">
					${rs.xqmc}
				</td>
			</tr>
			<tr>
				<th >
					<logic:equal name="xxdm" value="13431">
						加分大类
					</logic:equal>
					<logic:notEqual name="xxdm" value="13431">
						行为大类
					</logic:notEqual>
				</th>
				<td >
					${rs.rcxwlbdlmc}
				</td>
				<th >
					<logic:equal name="xxdm" value="13431">
						加分类别
					</logic:equal>
					<logic:notEqual name="xxdm" value="13431">
						行为类别
					</logic:notEqual>
				</th>
				<td  >
					${rs.rcxwlbmc}
				</td>
			</tr>
			<tr>	
				<th>
				   	评分说明
				</th>
				<td colspan="3" style="word-break:break-all;" width="650px">
					${rs.rcxwlbbz}
				</td>
			</tr>
			<tr>
				<th>评定分值</th>
				<td>${rs.fz }</td>							
				<th >发生时间</th>
				<td >
					${rs.fssj}
				</td>
		    </tr>
		    <tr>
				<th>记录人</th>
				<td>${rs.jlrxm }</td>							
				<th >记录时间</th>
				<td >
					${rs.rcxwjlsj}
				</td>
		    </tr>
			<logic:equal value="13431" name="xxdm">
				<tr>
					<th>附件</th>
					<td colspan="3" id="fileTd">
						<logic:notEmpty name="rs" property="fjlj">
							<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwh_rcxwxxwhgl.do?method=downloadFile&id=${rs.id }');return false;" class="name">下载</a>&nbsp;${rs.fjmc}
						</logic:notEmpty>
					</td>
				</tr>
			</logic:equal>
		    <logic:equal value="13022" name="xxdm">
		    <tr>
		    	<th>附件</th>
		    	<td colspan="3" id="fileTd">
		    		<logic:notEmpty name="rs" property="fjlj">
		    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwh_rcxwxxwhgl.do?method=downloadFile&id=${rs.id }');return false;" class="name">下载</a>&nbsp;${rs.fjmc}
		    		</logic:notEmpty>
		    	</td>
		    </tr>
		    </logic:equal>
		    <logic:equal value="10344" name="xxdm">
		    <tr>
		    	<th>附件</th>
		    	<td colspan="3" id="fileTd">
		    		<logic:notEmpty name="rs" property="fjlj">
		    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwh_rcxwxxwhgl.do?method=downloadFile&id=${rs.id }');return false;" class="name">下载</a>&nbsp;${rs.fjmc}
		    		</logic:notEmpty>
		    	</td>
		    </tr>
		    </logic:equal>
		    <logic:equal value="13871" name="xxdm">
		    <tr>
		    	<th>附件</th>
		    	<td colspan="3" id="fileTd">
		    		<logic:notEmpty name="rs" property="fjlj">
		    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwh_rcxwxxwhgl.do?method=downloadFile&id=${rs.id }');return false;" class="name">下载</a>&nbsp;${rs.fjmc}
		    		</logic:notEmpty>
		    	</td>
		    </tr>
		    </logic:equal>
		     <logic:equal value="70002" name="xxdm">
		    <tr>
		    	<th>附件</th>
		    	<td colspan="3" id="fileTd">
		    		<logic:notEmpty name="rs" property="fjlj">
		    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwh_rcxwxxwhgl.do?method=downloadFile&id=${rs.id }');return false;" class="name">下载</a>&nbsp;${rs.fjmc}
		    		</logic:notEmpty>
		    	</td>
		    </tr>
		    </logic:equal>
		    <tr>	
					<th>
						给分理由
					</th>
					<td colspan="3" style="word-break:break-all;" width="650px">
						${rs.gfly }
					</td>
			</tr>
			<tr>	
					<th>
						备注
					</th>
					<td colspan="3" style="word-break:break-all;" width="650px">
						${rs.bz }
					</td>
			</tr>
			</tbody>
			</table>
			<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="4">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>				
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
					
					</tbody>
			</table>
			<table width="100%" class="formlist">
			<tbody>
			<tr>
				<th width="20%">
					<font color="red">*</font>审核结果
				</th>
				<td id="shjgSpan" width="30%">
					
				</td>
				<th width="20%">
					<font color="red">*</font>评定分值
				</th>
				<td width="30%" id="shfzTd">
				</td>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> 审核意见
					<br />
					<font color="red">限200字</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=rcxw&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:95%;margin-top:5px" onblur="checkLen(this,200);">${rs.shyj}</textarea>
				</td>
			</tr>
			
			</tbody>
			</table>
			
		</div>
		<table width="100%" border="0" class="formlist">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="保存"  onclick="saveShzt();return false;">
									保 存
								</button>
								<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
