<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/gyglGywh.js"></script>	
		<script type='text/javascript' src='/xgxt/dwr/interface/getCommGygl.js'></script>
		<script type="text/javascript">
		function save(){
			if($("lddm").value==""){
				alert("楼栋代码不能为空!");
				return false;
			}
			if($("ldmc").value==""){
				alert("楼栋名称不能为空!");
				return false;
			}
			var cs=document.getElementsByName("cs");
			var blog=false;
			for(i=0;i<cs.length;i++){
				if(cs[i].checked){
					blog=true;
					break;
				}
			}
			if(!blog){
				alert("楼层不能为空!");
				return false;
			}
			var url="/xgxt/gyglGywh.do?method=ldxxwh&doType=save";
			refreshForm(url);
		}
		function modi(){
			var url="/xgxt/gyglGywh.do?method=ldxxwh&doType=modi";
			refreshForm(url);
		}
		
		function loadXqxx(){
			var xqdmArr=document.getElementsByName('xqdmArr');
			var xqmcArr=document.getElementsByName('xqmcArr');
			var xqdmHid=$("xqdmHid").value;
			var len=xqdmArr.length;
			var html="<div style='width:98%;height='10px';overflow:scroll;overflow-x:hidden'><table width='100%'><tr>";
			for(i=1;i<=len;i++){
				html+="<td width='33%'>";
				if(xqdmHid==xqdmArr[i-1].value){
					html+="<input type='radio' name='xqdm' id='xqxx_"+i+"' checked='true' value='"+xqdmArr[i-1].value+"'  onclick=\"changeYqxx(\'"+xqdmArr[i-1].value+"\')\">"+xqmcArr[i-1].value;
				}else{
					html+="<input type='radio' name='xqdm' id='xqxx_"+i+"'  value='"+xqdmArr[i-1].value+"'  onclick=\"changeYqxx(\'"+xqdmArr[i-1].value+"\')\">"+xqmcArr[i-1].value;
				}
				html+="</td>";
				if(i%3==0){
					html+="</tr><tr>";
				}
			}
			
			if(len%3!=0){
				for(i=1;i<=3-(len%3);i++){
					html+="<td></td>"
				}
			}
			html+="</tr></table></div>";
			$("ssxq").innerHTML=html;
		}
		
		function loadYqxx(){
			var yqdmArr=document.getElementsByName('yqdmArr');
			var yqmcArr=document.getElementsByName('yqmcArr');
			var yqdmHid=$("yqdmHid").value;
			var len=yqdmArr.length;
			var html="<div style='width:98%;height='10px';overflow:scroll;overflow-x:hidden'><table width='100%'><tr>";
			for(i=1;i<=len;i++){
				html+="<td width='33%'>";
				if(yqdmHid==yqdmArr[i-1].value){
					html+="<input type='radio' name='yqdm' checked='true' id='yqxx_"+i+"' value='"+yqdmArr[i-1].value+"'>"+yqmcArr[i-1].value;
				}else{
					html+="<input type='radio' name='yqdm' id='yqxx_"+i+"' value='"+yqdmArr[i-1].value+"'>"+yqmcArr[i-1].value;
				}
				
				html+="</td>";
				if(i%3==0){
					html+="</tr><tr>";
				}
			}
			
			if(len%3!=0){
				for(i=1;i<=3-(len%3);i++){
					html+="<td></td>"
				}
			}
			html+="</tr></table></div>";
			$("ssyq").innerHTML=html;
		}
		
		function changeYqxx(xqdm){
			if($("czyq").value=="是"){
				dwr.engine.setAsync(false);
				gyglGywh.getYqxxList(xqdm,function(data){
					var html="<div style='width:98%;height='10px';overflow:scroll;overflow-x:hidden'><table width='100%'><tr>";
					var len=data.length;
					for(i=1;i<=len;i++){
						dm=""+data[i-1].dm;
						mc=""+data[i-1].mc;
						html+="<td width='33%'>";
						html+="<input type='radio' name='yqdm' id='yqxx_"+i+"' value='"+dm+"' >"+mc;
						html+="</td>";
						if(i%3==0){
							html+="</tr><tr>";
						}
					}
					
					if(len%3!=0){
						for(i=1;i<=3-(len%3);i++){
							html+="<td></td>"
						}
					}
					html+="</tr></table></div>";
					$("ssyq").innerHTML=html;
				});
				 dwr.engine.setAsync(true);
			}
		}
		
		function loadXqYq(){
			if($("czxq").value=="是"){
				loadXqxx();
			}
			
			if($("czyq").value=="是"){
				loadYqxx();
			}
		}
		
		function checkPk(tableName,zdm,zdz,dis){
			
			dwr.engine.setAsync(false);
			getCommGygl.checkPkValue(tableName,zdm,zdz,function(data){
				
				if(data==false){
					if($(zdm+"Prompcon")){
						$(zdm+"Prompcon").style.display="";
						if(dis=="yes"){
							$("btn_bc").disabled="true";
						}
					}
				}else{
					if($(zdm+"Prompcon")){
						$(zdm+"Prompcon").style.display="none";
						if(dis=="yes"){
							$("btn_bc").disabled="";
						}
					}
				}
			});
			dwr.engine.setAsync(true);
		}
		
		</script>
<%--		<style>--%>
<%--		.include_tab{border-collapse:collapse;border:0px;}--%>
<%--		.include_tab td{border-top:0;bordedr-right:1px solid red!importalt;border-bottom:0;border-left:0;}--%>
<%--		.include_tab_r{}--%>
<%--		</style>--%>
	</head>
	<body onload="loadXqYq()">
		<html:form action="/gyglGywh" method="post">
			<input type="hidden" name="czxq" id="czxq" value="${czxq }"/>
			<input type="hidden" name="czyq" id="czyq" value="${czyq }"/>
			<input type="hidden" name="yqdmHid" id="yqdmHid" value="${rs.yqdm}"/>
			<input type="hidden" name="xqdmHid" id="xqdmHid" value="${rs.xqdm}"/>
			<logic:iterate name="xqList" id="xqxx" indexId="index">
				<input type="hidden" name="xqdmArr" value="${xqxx.dm}"/>
				<input type="hidden" name="xqmcArr" value="${xqxx.mc}"/>
			</logic:iterate>
			<logic:iterate name="yqList" id="yqxx" indexId="index">
				<input type="hidden" name="yqdmArr" value="${yqxx.dm}"/>
				<input type="hidden" name="yqmcArr" value="${yqxx.mc}"/>
			</logic:iterate>
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<!-- 提示信息 仅在修改时提示 end-->
			<logic:equal name="doType" value="update">
			<div class="prompt" id="div_help">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					已有人入住的楼栋不可修改楼栋性别限制。<br/>	
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			</logic:equal>
			<!-- 提示信息 end-->		
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span><bean:message key="lable.ld" />信息</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									<font color="blue">
										注：带"<font color="red">*</font>"的信息必须录入
									</font>
								</div>
								<div class="btn">
									<logic:equal name="doType" value="add">
										<button class="button2" id="btn_bc" onclick="save();return false;">
											保 存
										</button>
									</logic:equal>
									<logic:equal name="doType" value="update">
										<button class="button2" id="btn_bc" onclick="modi()">
											保 存
										</button>
									</logic:equal>
									<button class="button2" onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th style="width:20%">
								<font color="red">*</font>
								<bean:message key="lable.ld" />代码
							</th>
							<td  style="width:80%">
							<logic:equal name="doType" value="add">
								<html:text name="rs" property="lddm" styleId="lddm" maxlength="10" onkeyup="checkPk('sslddmb','lddm',this.value,'yes')"/>
								
								<span class="msg_prompt2" id="lddmPrompcon" style="display:none">
					              	<em class="prompcon">
					                	楼栋代码已经存在,请重新填写
					                </em>
					            </span>
							</logic:equal>
							<logic:equal name="doType" value="update">
								<input type="text" name="ldxxwh" id="ldxxwh" disabled="true" value="${rs.lddm}"/>
								<html:hidden name="rs" property="lddm" styleId="lddm" />
							</logic:equal>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>
								<bean:message key="lable.ld" />名称
							</th>
							<td>
								<html:text name="rs" property="ldmc" styleId="ldmc" maxlength="25" onkeyup="checkPk('sslddmb','ldmc',this.value,'no')"/>
								<span class="msg_prompt2" id="ldmcPrompcon" style="display:none">
					              	<em class="prompcon">
					                	楼栋名称已经存在!
					                </em>
					            </span>
							</td>
						</tr>
						<logic:equal name="czxq" value="是">
						<tr>
							<th>
								所属<bean:message key="lable.xiaoqu" />
							</th>
							<td colspan="3" id="ssxq">
								
							</td>
						</tr>
						</logic:equal>
						<logic:equal name="czyq" value="是">
						<tr>
							<th>
								所属<bean:message key="lable.yuanqu" />
							</th>
							<td colspan="3" id="ssyq">
								
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th>
								<font color="red">*</font><bean:message key="lable.cs" />总数
							</th>
							<td colspan="3">
								<div style="width:98%;height:70px;overflow-y:auto;overflow-x:hidden">
									<%
										int lcsz=Integer.parseInt(request.getAttribute("lcsz").toString());
										HashMap<String,String>rs=(HashMap<String,String>)request.getAttribute("rs");
										for(int i=1;i<=lcsz;i++){
											if(!Base.isNull(rs.get("cs")) && rs.get("cs").equals(String.valueOf(i))){
											%>
												<input type="radio" name="cs" checked="true" id="cs_<%=i%>" value="<%=i%>"/><%=i<10?"0"+i:i%>层
											<%
											}else{
											%>
												<input type="radio" name="cs" id="cs_<%=i%>" value="<%=i%>"/><%=i<10?" "+i:i%>层
											<%
											}
											if(i%5==0){
											%>
												<br/>
											<%}
										}
									 %>
								</div>
							</td>
						</tr>
						<tr>
							<th>
								楼栋性别限定
							</th>
							<td colspan="3">
							
							<!-- 增加操作 可直接维护性别 -->
							<logic:equal name="doType" value="add">
									<html:radio name="rs" property="xbxd" value="男"/>男
									<html:radio name="rs" property="xbxd" value="女"/>女
									<html:radio name="rs" property="xbxd" value="混合"/>混合
							</logic:equal>
							
							<!-- 修改操作 根据入住情况判断是否可以维护性别 -->
							<logic:equal name="doType" value="update">
								<logic:equal name="rs" property="rzqk" value="无入住">
									<html:radio name="rs" property="xbxd" value="男"/>男
									<html:radio name="rs" property="xbxd" value="女"/>女
									<html:radio name="rs" property="xbxd" value="混合"/>混合
								</logic:equal>
								<logic:notEqual name="rs" property="rzqk" value="无入住">
									${rs.xbxd}
									<html:hidden name="rs" property="xbxd"/>
								</logic:notEqual>
							</logic:equal>
							<font color="blue">&nbsp;&nbsp;提示:楼栋性别限定用于限制楼栋内寝室的性别。</font>
							</td>
						</tr>
						<tr>
							<th>
								备注
								</br>
								(限制录入500字)
							</th>
							<td colspan="3">
								<html:textarea name="rs" rows="5" style="width:98%"
									onblur="chLeng(this,500)"
									property="bz" styleId="bz"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>


			<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alert("操作失败！");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alert("操作成功！");
					</script>
				</logic:equal>
				<script language="javascript">			
					if(window.dialogArguments){
						if(window.dialogArguments.document.getElementById("search_go")){
							dialogArgumentsQueryChick();
						}
						window.close();
					}
				</script>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
