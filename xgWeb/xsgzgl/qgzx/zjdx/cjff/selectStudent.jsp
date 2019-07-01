<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	</head>
<tbody>
<tr>
	<th width="16%">
	<logic:equal name="type" value="add">
		<font class="red">*</font>
	</logic:equal>
	学号</th>
	<td width="34%">
		<logic:equal name="type" value="add">
			<input type="text" name="xh" value="${jbxx.xh }" id="xh" style="width:120px;" />
		<script type="text/javascript">
			jQuery(function(){
			 var gotoPath="${path}";
			 
			jQuery("#xh").keydown(function(event){
				     if(13==event.keyCode){
				      var xh=jQuery("#xh").val();
					  selectStudent(xh,gotoPath);
					
					}
				});
			  jQuery("#xh").blur(function(){
				     var xsxh=jQuery("#xh").val();
					selectStudent(xsxh,gotoPath)
				});
				function selectStudent(xsxh,gotoPath){
				// ===== 如果地址是GBK编码的（'%3D'表示'='），则进行解码 begin=========
				if(gotoPath.indexOf('%3D') >= 0){
					gotoPath = decodeURIComponent(gotoPath,'gbk');
				}
				// ===== 如果地址是GBK编码的（'%3D'表示'='），则进行解码 end=========
				if (gotoPath.split("?").length > 1){
					gotoPath = gotoPath+"&xh="+xsxh;
				} else {
					gotoPath = gotoPath+"?xh="+xsxh;
				}
				var api = frameElement.api;
					if (api){
						api.reload(api.get('parentDialog') ,gotoPath);
					} else{
						var W = api.opener;
						W.location=gotoPath;
				} 
				}
			});
		</script>
		<button class="btn_01" type="button"  
										onclick="showDialog('请选择一个学生',800,500,'xsxx_xsgl.do?method=showStudentsCjffAdd&goto=${path}');">选择</button>
		</logic:equal>
		<logic:notEqual name="type" value="add">
				<a href="javascript:void(0);" class="name" 
						   onclick="showDialog('学生详细信息',700,500,'xsxx_tygl.do?method=ckZxsxx&xh=${jbxx.xh }')"
						   style="margin-left: 1px;"
						 >${jbxx.xh }</a>
			<input type="hidden" name="xh" value="${jbxx.xh }" id="xh"/>
		</logic:notEqual>
		
	</td>
	<th width="16%">
<%--	<logic:equal name="type" value="add">--%>
<%--		<font class="red">*</font>--%>
<%--	</logic:equal>--%>
	姓名</th>
	<td width="34%">
		<%-- <logic:equal name="type" value="add">
			<input type="text" name="xm" value="${jbxx.xm }" id="xm" style="width:120px;" readonly="true"/>
		</logic:equal>
		<logic:notEqual name="type" value="add">
			${jbxx.xm }
			<input type="hidden" name="xm" value="${jbxx.xm }" id="xm" />
		</logic:notEqual> --%>
		${jbxx.xm }
		<input type="hidden" name="xm" value="${jbxx.xm }" id="xm" />
	</td>
</tr>
<tr>
	<th width="16%">性别</th>
	<td width="34%">${jbxx.xb}</td>
	<th width="16%">身份证号</th>
	<td width="34%">${jbxx.sfzh}</td>
</tr>
<tr>
	<th width="16%">年级</th>
	<td width="34%">${jbxx.nj}</td>
	<th width="16%">学院</th>
	<td width="34%">${jbxx.xymc}</td>
</tr>
<tr>
	<th width="16%">专业</th>
	<td width="34%">${jbxx.zymc}</td>
	<th width="16%">班级</th>
	<td width="34%">${jbxx.bjmc}</td>
</tr>
<tr>
	<th width="16%">政治面貌</th>
	<td width="34%">${jbxx.zzmmmc}</td>
	<th width="16%">联系电话</th>
	<td width="34%">${jbxx.sjhm}</td>
</tr>
</tbody>