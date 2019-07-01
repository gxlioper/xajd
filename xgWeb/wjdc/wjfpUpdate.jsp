<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript"  src="js/pjpy/pjpyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getWjdcDAO.js'></script>
		<script language="javascript"  src="js/wjdc/wjdc.js"></script>
		<script language="javascript"  src="js/wjdc/wjdcMk.js"></script>
		<script language="javascript">
		function getFpbj(){
			var tableName = "view_wjdc_wjfp"; 
			var dm = "bjdm"; 
			var mc = "bjmc";
			var msg = "";
			var pk = "id";
			var pkValue = $("id").value;
			var obId = "bjR";
			if(pkValue == ""){
				pk = "";
			}
				
			getPjpyInfo.getPjpyList(tableName, dm, mc, msg, pk, pkValue,function(data){
				if(data!=null){
					DWRUtil.removeAllOptions(obId);
					DWRUtil.addOptions(obId,data,"dm","mc");
					$(obId).options[0] = null;
				}
			});
		}
		</script>
	</head>
	<body onload="getWjList()">
		<html:form action="/wjdc">
			<!-- 隐藏域 -->
			<%@ include file="/wjdc/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			
			
			
			<div class="tab">
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button id="buttonSave" onclick="saveWjfp()">
										保 存
									</button>
									<button d="buttonClose" onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									问卷分配
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
				<tr>
					<th width="14%">
						年级
					</th>
					<td width="35%">
						<html:select property="nj" style="" onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj" />
						</html:select>
					</td>
					<th width="14%">
						学年
					</th>
					<td width="35%">
						<html:hidden property="xn"/>
						<html:select property="xn" style="width:120px" styleId="xn" disabled="true">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td>
						<html:select property="xydm" style="" styleId="xy" onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						</html:select>		
					</td>
					<th>
						年度
					</th>
					<td>
						<html:hidden property="nd"/>
						<html:select property="nd" style="" styleId="nd" disabled="true">
							<html:options collection="ndList" property="nd" labelProperty="nd" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						专业
					</th>
					<td>
						<html:select property="zydm" style="" styleId="zy" onchange="initBjList();">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc" />
						</html:select>
					</td>
					<th>
						学期
					</th>
					<td>
						<html:hidden property="xq"/>
						<html:select property="xq" style="" styleId="xq" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>问卷
					</th>
					<td>
						<html:select property="id" style="" styleId="id" onchange="getFpbj()">
							<html:options collection="wjList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
					<th>
						模块类型
					</th>
					<td>
						<html:select property="queryequals_mklx" style="" styleId="mklx" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="mklxList" property="en" labelProperty="cn" />
						</html:select>
					</td>
				</tr>
			</tbody>
			<thead>
				<tr>
					<td colspan="4">
						<span>
							<font color="blue">提示：不选择班级的话，可以根据所选条件进行批量设置;按Shift或者Ctrl可以进行批量选择班级.</font>
						</span>
					</td>
				</tr>
			</thead>
			<tbody>	
				<tr>
					<td colspan="4">
						<table width="100%">
							<tr>
								<td width="7%" align="right">
									班<br/><br/><br/>级
								</td>
								<td width="42%">
									<html:select property="bjdm" style="width:100% " multiple="multiple"
										styleId="bj" size="12" ondblclick="" onmouseover="">
									</html:select>
								</td>
								<td width="2%">
									<button id="addBj" onclick="addAllRightFrame('bj','bjR')" class="btn_01">
										&gt;&nbsp;&gt;
									</button>
									<br />
									<br />
									<br />
									<button class="btn_01" id="delBj" onclick="addAllLeftFrame('bj','bjR')">
										&lt;&nbsp;&lt;
									</button>
								</td>
								<td width="7%" align="right">
									设<br/>置<br/>班<br/>级
								</td>
								<td width="42%">
									<html:select property="fpbj" style="width:100%" styleId="bjR" multiple="multiple"
										size="12" ondblclick="" onmouseover="">
									</html:select>		
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<!-- 提示信息 -->
		<%@ include file="other/tsxx.jsp"%>
		<!-- 提示信息 end-->
		</html:form>
	</body>
</html>
