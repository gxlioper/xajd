<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script language="javascript" src="js/wjdc/wjdcMk.js"></script>
		<script language="javascript" src="js/wjdc/wjdc.js"></script>
		<script type="text/javascript">	
		function saveSt(){
			var stlx = $("stlx").value;
			var mklx = $("mklx").value;
			var url = "/xgxt/wjdc.do?method=";
			
			if( stlx == "questions"){//问答题
				saveUpdate('/xgxt/wjdc.do?method=stSave&doType=save','stmc-stlx');
			}else{//选择题
				saveXzt();
			}
		}
		
		//保存选择题
		function saveXzt(){
		
			var the_tab = "";
			var stlx = $("stlx").value;
			var stbh = $("stbh").value;
			var stmc = $("stmc").value;
			//oneChoose : 单选题
			//allChoose ：多选题
			if(stlx == "oneChoose"){
				the_tab = "one_flag";
			}else if(stlx == "allChoose"){
				the_tab = "all_flag";
			}else if(stlx == ""){
				alert("请确认试题类型!");
				return false;
			}
			
			if(stmc == ""){
				alert("请确认试题名称!");
				return false;
			}
			
			var tabobj = document.getElementById(the_tab);
			var rowLen = tabobj.rows.length;
			var nullCout = 0;
			
			if(rowLen == 0){
				alert("答案数目不能为空，请确认！");
				return false;
			}
			for(var i=1;i<=rowLen;i++){
				if($("damc"+stlx+i)){
					if($("damc"+stlx+i).value == ""){
						alert("第"+i+"行答案名称为空，请确定");
						return false;
					}
				}
			}
			saveUpdate('/xgxt/wjdc.do?method=stSave&doType=save','stmc-stlx');
		}
	</script>
	</head>
	<body onload="onShow()">
		<html:form action="/wjdc">
			<!-- 隐藏域 -->
			<html:hidden name="rs" property="mklx" />
			<html:hidden name="rs" property="jlsj" />
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
									<logic:notEqual name="doType" value="view">
										<button id="buttonSave" onclick="saveSt()">
											保 存
										</button>
									</logic:notEqual>
									<button onclick="window.close();return false;" id="buttonClose">
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
									基本信息
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th width="15%">
							试题编号
						</th>
						<td width="35%">
							<html:hidden name="rs" property="stbh" />
							${rs.stbh }
						</td>
						<th width="15%">
							<font color="red">*</font>试题类型
						</th>
						<td>
							<html:select name="rs" property="stlx" style="width:120px"
								styleId="stlx" onchange="showStDiv()">
								<html:options collection="stlxList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							试题所属
						</th>
						<td>
							<html:select name="rs" property="stss" style="width:120px"
								styleId="stss" onchange="">
								<html:options collection="stssList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<th>
							模块类型
						</th>
						<td>
							<html:select name="rs" property="mklx" style="" styleId="mklx"
								disabled="true">
								<html:option value=""></html:option>
								<html:options collection="mklxList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>题目
							<br/>
							<font color="red">(限500字)</font>
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="stmc" rows="5" styleId="stmc"
								onblur="chLeng(this,500)" style="width: 90%" />
						</td>
					</tr>
					<tr>
						<th>
							备注
							<br/>
							<font color="red">(限500字)</font>
						</th>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="bz" rows="5" styleId="bz"
								onblur="chLeng(this,500)" style="width: 90%" />
						</td>
					</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									试题内容（请选择试题类型）
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div id="oneChoose" style="display : none">
									<%@ include file="stxx/stOneChoose.jsp"%>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div id="allChoose" style="display : none">
									<%@ include file="stxx/stAllChoose.jsp"%>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div id="questions" style="display : none">
									<%@ include file="stxx/stQueChoose.jsp"%>
								</div>
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
