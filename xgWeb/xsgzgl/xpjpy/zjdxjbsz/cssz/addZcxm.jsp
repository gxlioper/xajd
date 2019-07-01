<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<%@ include file="/syscommon/head.ini"%>
	<%@ include file="/syscommon/autocomplete.ini"%>
	<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script language="javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="xsgzgl/xpjpy/zjdxjbsz/cssz/js/cssz.js"></script>
	<script type="text/javascript">
		jQuery(function(){
			jQuery("#zxfzTr").hide();
			jQuery("#zdfzTr").hide();
			jQuery("#pxTr").hide();
			jQuery("#xmdjTable").hide();
		});
	</script>
  </head>
  <body>
		<html:form action="/xpjpy_cssz" method="post" styleId="zjdxCsszForm" >
			<input type="hidden" id="objStr" name="objStr"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th><span class="red">*</span>综测项目</th>
							<td>
								<html:text property="xmmc" styleId="xmmc" style="width:155px;" maxlength="10" styleClass="text_nor"></html:text>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>父级项目</th>
							<td>
				    			<html:select property="fjdm" styleId="fjdm" style="width:155px">
				    				<html:option value="">请选择</html:option>
				    				<html:option value="top">顶级</html:option>
				    				<html:options collection="yjxmlist" property="dm" labelProperty="mc" />
				    			</html:select>
				    		</td>
						</tr>
						<tr>
							<th><span class="red">*</span>项目类型</th>
							<td>
								<input type="radio" id="xmlx" name="xmlx" value="0" onclick="changeZcxm();"/>等级
								<input type="radio" id="xmlx" name="xmlx" value="1" onclick="changeZcxm();"/>分值
							</td>
						</tr>
						<tr id="zxfzTr">
							<th><span class="red">*</span>最小分值</th>
							<td>
								<html:text property="zxfz" styleId="zxfz"  maxlength="4" onkeyup="checkInput(this)"></html:text>
							</td>
						</tr>
						<tr id="zdfzTr">
							<th><span class="red">*</span>最大分值</th>
							<td>
								<html:text property="zdfz" styleId="zdfz"  maxlength="4" onkeyup="checkInput(this)"></html:text>
							</td>
						</tr>
						<tr id="pxTr">
							<th>排序</th>
							<td>
								<html:text property="px" styleId="px"  maxlength="3" onkeyup="checkInput(this)"></html:text>
							</td>
						</tr>
				</table>
				<div style="height:1px"></div>
				<table width="100%" border="0" class="formlist" id="xmdjTable">
					<thead>
						<tr>
							<th colspan="3">
								<span>项目类型等级设置</span>
								<img src="xsxx/fbgl/images/add-icons-1.gif" alt="增加" onclick="addRow();return false;">
								<img src="xsxx/fbgl/images/delete-icons-2.gif" alt="删除" onclick="delRow();return false;">
							</th>
						</tr>
						<tr>
							<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td><font color="red">*</font>名称</td>
							<td>排序</td>
						</tr>
					</thead>
					<tbody id="tbody_zcxmLx">
						<tr>
							<td>
								<input type="checkbox" />
							</td>
							<td>
								<input type="text" name="mc" maxlength="10"/>
							</td>
							<td >
							    <input type="text" name="px" maxlength="3" onkeyup="checkInput(this)"/>
							</td>																			
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForAdd();">
										确 定
									</button>
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>