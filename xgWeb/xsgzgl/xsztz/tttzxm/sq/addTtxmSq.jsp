<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/comm/js/comm.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/sq/js/tttzxm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
	
			jQuery(function(){
				jQuery("#selhd").unbind('click').bind('click', function(){
				  var url = "ttxm_comm.do?method=getXmSelect";
				  showDialog('请选择项目',770,550,url);
			   });
			});
		</script>
		<style type = "text/css">
			#xxdz{width:250px;}
			.fontstyl{float:left}
			.fontstyl1{margin-left:5px}
			#autotable table th{text-align:left} 
			#autotable table td{text-align:left} 
		</style>
	</head>
	<body>
		<html:form action="/ttxm_sq" method="post" styleId="TttzxmForm">
			<input type="hidden" id="xmdm" name="xmdm"/>
			<input type="hidden" name="xn"/>
			<input type="hidden" name="xq"/>
			<input type="hidden" id="usertype" value="${usertype}"/>
			<logic:equal name="usertype" value="stu" >
				<input type="hidden" name="dzxh" id="dzxh" value="${xsmap.xh}"/>
			</logic:equal>
			<logic:notEqual name="usertype" value="stu" >
				<input type="hidden" id="dzxh" name="dzxh"/>
			</logic:notEqual>
			<input type="hidden" name="splc" id="splc"/>
			<input type="hidden" name="xmdm" id="xmdm"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>项目信息</span>
                               <a  style="text-align: left;" onclick="showPfzmx(this,'orign');" class="up"
									href="javascript:void(0);"> <font color="blue">点击展开/收起</font>
								</a>
							</th>
						</tr>
					</thead>
					<tbody id="autotbody">
						<tr>
							<th width="20%"><font color="red">*</font>项目名称</th>
							<td width="30%">
								<input type="text" name="xmmc" value="" id="xmmc" style="width:120px;"  readonly="readonly"/>
								<button class="btn_01" id="selhd" type="button" >选择</button>
							</td>
							<th width="20%">项目级别</th>
							<td width="30%" id="xmjbmc" >
							  
                             </td>
						</tr>
						<tr>
							<th>学年</th>
							<td id="xn" >
							</td>
							<th>学期</th>
							<td id="xq" >
							</td>
						</tr>
						<tr>
							<th>申报部门</th>
							<td id="sbbmmc" >
							</td>
							<th>联系方式</th>
							<td id="lxdh" name="lxdh">
							</td>
						</tr>
						<tr>
							<th>所属科目</th>
							<td id="sskmmc" name="sskmmc">
							</td>
							<th>基础学分</th>
							<td id="jcxf" name="jcxf">
							</td>
						</tr>
						<tr>
							<th>可参与团队数</th>
							<td id="kcyrs" name="kcyrs">
							</td>
							<th>已申请团队数</th>
							<td id="sqrs" name="sqrs">
							</td>
						</tr>
						<tr>
							<th>已通过团队数</th>
							<td id="tgrs" name="tgrs">
							</td>
							<th>活动开始时间</th>
							<td id="xmkssj" name="xmkssj">
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>成员信息 </span> 
								<logic:equal name="usertype" value="stu" >
									<label  id="cyrs" style="margin-left:10px">1人</label>
									<button type="button" style="margin-top:2px;margin-left:30px" onclick="addRow();return false;">增加</button>
									<button type="button" onclick="delRow();return false;">删除</button>
								</logic:equal>
								<logic:notEqual name="usertype" value="stu">
									<label id="cyrs" style="margin-left:10px">0人</label>
									<button type="button" style="margin-top:2px;margin-left:30px" onclick="addRowDialog();return false;">增加</button>
									<button type="button" onclick="delRow();return false;">删除</button>
								</logic:notEqual>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr colspan="4">
							<td width="100%" colspan="4">
								<div width="100%" id="autotable">
								<table width="100%" id="tablebody">
									<logic:equal name="usertype" value="stu" >
									<tr>
										<th width="5%"><input type="checkbox" onclick="selectAll(this);" name="chkall"/></th>
										<th width="10%"><font color="red">*</font>角色</th>
									    <th width="20%"><font color="red">*</font>学号</th>
									    <th width="20%"><font color="red">*</font>姓名</th>
									    <th width="20%"><font color="red">*</font>学院</th>
									    <th width="20%"><font color="red">*</font>班级</th>
									</tr>
									<tr>
										<td></td>
									    <td>队长</td>
									    <td width="20%">${xsmap.xh}<input type="hidden" name="xh" value="${xsmap.xh}"/></td>
									    <td width="20%">${xsmap.xm}</td>
									    <td width="20%">${xsmap.xymc}</td>
									    <td width="20%">${xsmap.bjmc}</td>
									</tr>
									</logic:equal>
									<logic:notEqual name="usertype" value="stu" >
									<tr>
										<th width="5%"><input type="checkbox" onclick="selectAll(this);" name="chkall"/></th>
										<th width="10%"><font color="red">*</font>队长</th>
									    <th width="20%"><font color="red">*</font>学号</th>
									    <th width="20%"><font color="red">*</font>姓名</th>
									    <th width="20%"><font color="red">*</font>学院</th>
									    <th width="20%"><font color="red">*</font>班级</th>
									 </tr>
									</logic:notEqual>
									
								</table>
							</div>
							</td>
							
						</tr>
						<tr>
							
						</tr>
						<tr>
							<th width="20%"><font color="red">*</font>团队名称</th>
							<td colspan="3">
								<html:text property="tdmc" maxlength="15"  styleId="tdmc"/>
							</td>
						</tr>
						<tr>
							<th width="20%"><font color="red">*</font>申请理由<br/><font color="red"><限500字></font></th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" 
								   style="width:99%;" rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" id="bc" onclick="saveTtxmSq('save');">
										保存草稿
									</button>
									<button type="button" id="tjsq" onclick="saveTtxmSq('submit');">
										提交申请
									</button>
									<button type="button" onclick="iFClose();">
										关闭
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