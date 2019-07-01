<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gyjlxxwh/js/gyjlxxwh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	<script	type="text/javascript">
		function getJllbList(obj){
			jQuery.post('gyglnew_gyjlgl.do?method=getJllbList',{jldldm:obj.value},function(data){
					var option = "";
					for(var i = 0; i < data.length; i++){
						option +="<option value='"+data[i].jllbdm+"'>"+data[i].jllbmc+"</option>";
					}
					jQuery('#jllbdm').empty().append(option);			
			},'json');
		}
	</script>
</head>
<body>
	<html:form action="/gyjl_gyjlwh" method="post" styleId="gyjlwhForm" onsubmit="return false">	
		<input type="hidden" id="username" name="username" value="${username}"/>
		<input type="hidden" name="objStr" id="objStr"/>
		<html:hidden property="wjxn" styleId="wjxn" value="${xn}"/>
		<html:hidden property="wjxq" styleId="wjxq" value="${xq}"/>
		<div class="tab" style="width:100%;overflow-x:hidden;overflow-y:auto;" >
		<table class="formlist" border="0" width="100%">	
			<thead>
				<tr>
					<th colspan="4">
					<span>公寓纪律信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					<font color="red">*</font>违纪学年
				</th>
				<td align="left" width="30%">
					${xn}
				</td>
					<th align="right" width="20%">
						<font color="red">*</font>违纪学期
					</th>
				<td align="left" width="30%">
					${xqmc}
				</td>
			</tr>
			<tr>
				<th align="right" width="20%">
					<font color="red">*</font>纪律大类
				</th>
				<td align="left" width="30%">
					<html:select property="jldldm" styleId="jldldm" style="width:140px" onchange="getJllbList(this)">
					<html:options collection="jldlList" labelProperty="jldlmc" property="jldldm"/>
					</html:select>
				</td>
				<th align="right" width="20%">
					<font color="red">*</font>纪律类别				
				</th>
				<td align="left" width="30%" >
					<html:select property="jllbdm" styleId="jllbdm" style="width:140px" >
					<html:options collection="jllbList" labelProperty="jllbmc" property="jllbdm"/>
					</html:select>
				</td>
			</tr>
			<tr>
					<th width="20%">
						<font color="red">*</font>违纪时间				
					</th>
					<td align="left" width="30%" colspan="3">
						<html:text property="wjsj" styleId="wjsj" style="width:140px" onkeypress="onlyBackSpace(this,event);"
							onclick="return showCalendar(this.id,'yyyy-MM-dd')"  readonly="true"></html:text>
					</td>
			</tr>
			<tr>
					<th>
						备注
						<br /><font color="red">(限制在100字内)</font>
					</th>
					<td colspan="4">
						<html:textarea property='bz' style="width:98%" styleId="bz" rows='3' onblur="chLeng(this,100);"/>
					</td>
			</tr>
			</tbody>
			
			<thead>
				<tr>
					<th colspan="4">
						<span>违纪信息 <font color='red'>寝室搜索格式：1号楼-101-1</font></span>
						
					</th>
				</tr>
				<tr>
					<td colspan="6">
					<font>学号</font>
					<input type="text" id="cxzd" name="cxzd" maxleng="20"/>
					<font>楼栋-寝室号-床位号</font>
					<input type="text" id="qsxx" name="qsxx" maxleng="50" width="200px"/>
					<font<b>同寝</b></font><input id="tqs" class="checkbox" type="checkbox" name="tqs" value="Y"/>
					<button type="button" onclick="addWjxs();return false;" class="btn_01">增加</button>
					<button type="button" onclick="delWjxs();return false;" class="btn_01">删除</button>
					</td>
				</tr>
			</thead>
			
				<table id="xs_body" border="0" width="100%" class="datelist">
					<thead>
						<tr>
							<th>
								<input name="th" type="checkbox" onclick="selectAll(this);"/>
							</th>
							<th>
								学号
							</th>
							<th>
								姓名
							</th>
							<th>
								性别
							</th>
							<th>
								班级名称
							</th>
							<th>
								住宿寝室
							</th>
							<th>
								违纪时间
							</th>
							<th>
								纪律类别
							</th>
							<th>
								操作员
							</th>
						</tr>
					</thead>
					<tbody id="tbody_wjxs">
					</tbody>
			</table>
			
			</table>
		</div>
			<div style="height: 28px"></div>
			<div>
			<table width="100%" border="0" class="formlist"
				style="position: fixed; _position: absolute; bottom: 0;">
			<tfoot>
			      <tr style="height:22px">
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button"  name="提交" id="buttonSave" onclick="saveWjxx('save');return false;">保存</button>
			            <button type="button"  name="关闭" id="buttonClose" onclick="Close();return false;">关闭</button>
			          </div></td>
			      </tr>
			 </tfoot>
			</table>
		</div>
	</html:form>
</body>
</html>