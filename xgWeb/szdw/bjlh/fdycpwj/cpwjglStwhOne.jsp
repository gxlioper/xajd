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
			$("xxgs").disabled="";
			$("dhxxgs").disabled="";
			if($("stmc").value.trim()==""){
				alertInfo("请输入试题名称！");
				return false;
			}
			if($("stlx").value=="1"){
				if($("xxgs").value<=1){
					alertInfo("单选题的选项个数必须大于1！");
					return false;
				}else{
					var xxnrs=document.getElementsByName("xxnrs");
					for(var i=0;i<xxnrs.length;i++){
						if(xxnrs[i].value.trim()==""){
							alertInfo("选项内容不可以为空！");
							return false;
						}
					}
				}
			}
			if($("xssx").value==""){
				alertInfo("请输入显示顺序！");
				return false;
			}
			saveData('bjlh_fdycpwj.do?method=cpwjglStwhOne&doType=${doType}_save','');
		}
		//试题维护
		function cpwjStwh(stid,type){
			$("stid").value=stid;
			saveData('bjlh_fdycpwj.do?method=cpwjglStwh&doType='+type,'');
		}
		
		//创建试题选项
		function createStxx(){
			var stlx=$("stlx").value;//试题类型
			var xxgs=$("xxgs").value;//选项个数
			if(stlx==2){//问答题
				$("xxgs").disabled="disabled";
				$("dhxxgs").disabled="disabled";
				$("table_stxx").style.display="none";
				return false;				
			}
			//单选题
			$("xxgs").disabled="";
			$("dhxxgs").disabled="";
			$("dhxxgs").value=xxgs;
			$("table_stxx").style.display="";
			
			var table_stxx=$("table_stxx");
			var table_tr=table_stxx.getElementsByTagName("TR");
			var tr_parent;
			if(table_stxx.getElementsByTagName("TBODY").length==1){
				tr_parent=table_stxx.getElementsByTagName("TBODY")[0];
			}else{
				tr_parent=table_stxx;
			}
			//增加选项
			for(var i=table_tr.length;i<xxgs;i++){
				var tr=document.createElement("TR");
				var td1=document.createElement("TD");
				td1.innerHTML=String.fromCharCode("A".charCodeAt()+i);
				var td2=document.createElement("TD");
				td2.innerHTML="<input type='text' name='xxnrs' maxlength='250'></input>";
				tr.appendChild(td1);
				tr.appendChild(td2);
				tr_parent.appendChild(tr);
			}
			//删除多余的选项
			var trs=tr_parent.getElementsByTagName("TR");
			for(var i=table_tr.length-1;i>=xxgs;i--){
					tr_parent.removeChild(trs[i]);
			}
		}
	</script>
</head>
<body onload="">
	<html:form action="/bjlh_fdycpwj" method="post">
		<input type="hidden" name="wjid" id="wjid" value="${rs.wjid}" />
		<input type="hidden" name="stid" id="stid" value="${rs.stid}" />
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
						<span>测评问卷试题维护</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="16%">
					<font color="red">*</font>试题名称
				</th>
				<td align="left" width="30%" nowrap="nowrap" colspan="3">
						<html:text property="stmc" name="rs" styleId="stmc" maxlength="250" style="width:500px;"></html:text>
				</td>
			</tr>

			<tr>
				<th width="16%"><font color="red">*</font>试题类型</th>
				<td width="38%">
						<html:select property="stlx" name="rs" styleId="stlx" style="width:80px;" onchange="createStxx()">
							<html:option value="1">单选题</html:option>
							<html:option value="2">问答题</html:option>
						</html:select>
				</td>
				<th width="16%">
					<font color="red">*</font>选项个数				
				</th>
				<td width="34%">
					<html:select property="xxgs" name="rs" styleId="xxgs" style="width:80px;" onchange="createStxx()">
						<html:option value="0">0</html:option>
						<html:option value="1">1</html:option>
						<html:option value="2">2</html:option>
						<html:option value="3">3</html:option>
						<html:option value="4">4</html:option>
						<html:option value="5">5</html:option>
						<html:option value="6">6</html:option>
						<html:option value="7">7</html:option>
						<html:option value="8">8</html:option>
					</html:select>
				</td>
			</tr>
			<tr>
				<th>
					<font color="red">*</font>单行选项个数
				</th>
				<td>
					<html:select property="dhxxgs" name="rs" styleId="dhxxgs" style="width:80px;">
						<html:option value="1">1</html:option>
						<html:option value="2">2</html:option>
						<html:option value="3">3</html:option>
						<html:option value="4">4</html:option>
						<html:option value="5">5</html:option>
						<html:option value="6">6</html:option>
						<html:option value="7">7</html:option>
						<html:option value="8">8</html:option>
					</html:select>
				</td>
				<th>
					<font color="red">*</font>显示顺序
				</th>
				<td>
					<html:text property="xssx" name="rs" styleId="xssx"  style="width:77px;" maxlength="3" onkeyup="checkInputData(this);"></html:text>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<table id="table_stxx" width="95%">
						<logic:present name="xxList">
							<logic:iterate id="xx" name="xxList">
								<tr>
									<td>
										${xx.zfbm}
									</td>
									<td>
										<input type="text" name="xxnrs" maxlength="250" value="${xx.xxnr}" />
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
				</td>
			</tr>
			<tr>
			<td colspan="4">
<%--				<table width="95%">--%>
<%--					<tr>--%>
<%--						<th>试题</th>--%>
<%--						<th>类型</th>--%>
<%--						<th>操作</th>--%>
<%--					</tr>--%>
<%--					<logic:present name="stxxList">--%>
<%--						<logic:iterate id="stxx" name="stxxList">--%>
<%--							<tr>--%>
<%--								<td>${stxx.stmc}</td>--%>
<%--								<td>${stxx.stlxmc}</td>--%>
<%--								<td><a href="#" onclick="cpwjStwh('','add')">增加</a>/--%>
<%--								<a href="#" onclick="cpwjStwh('${stxx.stid}','update')">修改</a>/--%>
<%--								<a href="#" onclick="confirmInfo('确定删除吗？',function(data){if(data=='ok'){cpwjStwh('${stxx.stid}','del');}})">删除</a></td>--%>
<%--							</tr>--%>
<%--						</logic:iterate>						--%>
<%--					</logic:present>--%>
<%--				</table>--%>
			</td>
			</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button" name="提交" id="buttonSave"  onclick="cpwjStwhSave()">保存</button>
			            <button type="button" name="关闭" id="buttonClose" onclick="window.close();return false;">返回</button>
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
