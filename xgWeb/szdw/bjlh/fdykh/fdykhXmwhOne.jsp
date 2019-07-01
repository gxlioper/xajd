<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
		function cpwjStwhSave(){
			if($("yjzb").value.trim()==""){
				alertInfo("请输入一级指标！");
				return false;
			}
			if($("ejzb").value.trim()==""){
				alertInfo("请输入二级指标！");
				return false;
			}
			if($("khnr").value.trim()==""){
				alertInfo("请输入考核内容！");
				return false;
			}
			if($("fzDown").value.trim()=="" && $("fzUp").value.trim()==""){
				alertInfo("请输入分值区间！");
				return false;
			}else{
				//if(Number($("fzDown").value.trim())>Number($("fzUp").value.trim())){
				//	alertInfo("请输入正确的分值区间！");
				//	return false;
				//}
			}
			
			if($("xssx").value==""){
				alertInfo("请输入显示顺序！");
				return false;
			}
			var fzqj =$("fzDown").value.trim()+"-"+ $("fzUp").value.trim();
			if($("fzDown").value.trim() == '' || $("fzUp").value.trim() == ''){
				fzqj = $("fzDown").value.trim() + $("fzUp").value.trim();
			}

			jQuery("#fzqj").val(fzqj);
			saveData('bjlh_fdykh.do?method=fdykhXmwhOne&doType=${doType}_save','');
		}		
	</script>
</head>
<body onload="">
	<html:form action="/bjlh_fdykh" method="post">
		<input type="hidden" name="khbid" id="khbid" value="${rs.khbid}" />
		<input type="hidden" name="xmid" id="xmid" value="${rs.xmid}" />
		<input type="hidden" name="fzqj" id="fzqj" />
		<%--<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>床位信息维护</a>
			</p>
		</div>		
		--%>
		
		<div class="tab">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>考核项目维护</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="16%">
					<font color="red">*</font>一级指标
				</th>
				<td align="left" width="30%" nowrap="nowrap" colspan="3">
						<html:text property="yjzb" name="rs" styleId="yjzb" maxlength="50" style="width:500px;"></html:text>
				</td>
			</tr>
			<tr>
				<th align="right" width="16%">
					<font color="red">*</font>二级指标
				</th>
				<td align="left" width="30%" nowrap="nowrap" colspan="3">
						<html:text property="ejzb" name="rs" styleId="ejzb" maxlength="50" style="width:500px;"></html:text>
				</td>
			</tr>
			<tr>
				<th align="right" width="16%">
					<font color="red">*</font>考核内容
				</th>
				<td align="left" width="30%" nowrap="nowrap" colspan="3">
						<html:textarea property="khnr" name="rs" styleId="khnr" style="width:500px;" cols="50" rows="5"></html:textarea>
				</td>
			</tr>
					
			<tr>
				<th width="16%"><font color="red">*</font>分值</th>
				<td width="38%">
					<input type="text" id="fzDown"  value="${rs.fzDown}"  style="width:77px;" maxlength="3" onkeyup="checkInputData(this);"/>
					-
					<input type="text" id="fzUp"  value="${rs.fzUp}" style="width:77px;" maxlength="3" onkeyup="checkInputData(this);"/>
					<br/>
					<span class="red">若只设任意1个值，则分值为固定值</span>
				</td>
				<th width="16%"><font color="red">*</font>评分类型</th>
				<td width="38%">
						<html:select property="pflx" name="rs" styleId="pflx" style="width:80px;">
							<html:option value="加分">加分</html:option>
							<html:option value="减分">减分</html:option>
						</html:select>
				</td>
			</tr>
			<tr>
				<th>
					<font color="red">*</font>显示顺序
				</th>
				<td colspan="3">
					<html:text property="xssx" name="rs" styleId="xssx"  style="width:77px;" maxlength="2" onkeyup="checkInputData(this);"></html:text>
				</td>
			</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button" name="提交" id="buttonSave"  onclick="cpwjStwhSave()">保存</button>
			            <button type="button" name="关闭" id="buttonClose" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="back">
		<script type="text/javascript">
			alertInfo("${back}",function(){
				if(window.dialogArguments){
					if(window.dialogArguments.document.getElementById("search_go")){
						dialogArgumentsQueryChick();
					}
					window.close();
				}
			});
		</script>
	</logic:present>
</body>
</html>
