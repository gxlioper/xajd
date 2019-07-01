<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>

	<script type='text/javascript' src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript">	
		 function checAll(name){
			var checs = document.getElementsByName(name);
			var ch = $('cheAll');

			if(ch.checked){
				for(var i=0; i<checs.length; i++){
					if(!checs[i].checked){
						checs[i].checked = "checked"
					}
				}
			}else {
				for(var i=0; i<checs.length; i++){
					if(checs[i].checked){
						checs[i].checked = ""
					}
				}
			}
	 	 }

		 function revAll(name){
	 		var checs = document.getElementsByName(name);
	
			for(var i=0; i<checs.length; i++){
				checs[i].checked = (checs[i].checked ? "" : "checked");
			}
	 	 }

	 	 function hid_tr(s_tr,h_tr){
		 	 document.getElementById(s_tr).style.display = "";
		 	 document.getElementById(h_tr).style.display = "none";
	 	 }

	 	 function loadRsfp(){
			var fpfs = document.getElementsByName('fpfs');

			for(var i=0;i<fpfs.length;i++){
				if(fpfs[i].checked == true){
					fpfs[i].click();
				}
			}
		 }
		 
		 function checkSave(){
		 	var flag = false;
		 	var ches = document.getElementsByName('params');
		 	
		 	for(var i=0; i<ches.length; i++){
		 		if(ches[i].checked){
		 			flag = true;
		 			break;
		 		}
		 	}
		 	
		 	if(flag){
		 		refreshForm('pjpy_comm_rssz.do?method=saveRssz');
		 	}else {
		 		alert('请先勾选所需设置部门！');
		 	}
		 }
	</script>
	</head>
	
	<body onload="loadRsfp();">
		<html:form action="/pjpy_comm_rssz">
			<input type="hidden" name="szfw" value="${szfw }"/>
			<input type="hidden" name="xmdm" value="${xmdm }"/>
			<input type="hidden" id="userStatus" value="${user.userStatus }"/>
			<input type="hidden" id="userName" value="${user.userName }"/>
			<input type="hidden" id="userDep" value="${user.userDep }"/>
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			 <table class="formlist" style="margin-bottom:4px" >
    			<thead>
					<tr>
						<td >
							<span>
								<font color="blue">
								注：设置范围为
								<logic:equal name="szfw" value="xy"><bean:message key="lable.xb" /></logic:equal>
								<logic:equal name="szfw" value="zy">专业</logic:equal>
								<logic:equal name="szfw" value="bj">班级</logic:equal>
								<logic:equal name="szfw" value="nj">年级</logic:equal>
								</font>
							</span>	
						</td>
					</tr>
				</thead>
			</table>
			
			<div class="leftframe04">
				<%@include file="bmTree.jsp" %>
		    </div>
		    
		    <div class="rightframe04">
			   	<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>详细设置</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th>评奖学年</th>
							<td>
								<html:select property="pjxn" style="width: 120px" styleId="xn" disabled="true">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
							<th>评奖学期</th>
							<td>
								<html:select property="pjxq" style="width: 120px" styleId="xq" disabled="true">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>评奖年度</th>
							<td>
								<html:select property="pjnd" style="width: 120px" styleId="nd" disabled="true">
									<html:options collection="ndList" property="nd" labelProperty="nd" />
								</html:select>
							</td>
							<th>项目名称</th>
							<td>
								<html:select property="xmdm" style="width:150px" disabled="true">
									<html:options collection="pjxmList" property="xmdm" labelProperty="xmmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>设置范围</th>
							<td>
								<html:select property="szfw" disabled="true">
									<html:option value="xy"><bean:message key="lable.xb" /></html:option>
									<html:option value="zy">专业</html:option>
									<html:option value="bj">班级</html:option>
									<html:option value="nj">年级</html:option>
								</html:select>
							</td>
							<th></th><td></td>
						</tr>
					</tbody>
					<thead>
						<tr><th colspan="4"><span>分配信息</span></th></tr>
					</thead>
					
					<tbody>
						<tr>
							<th>分配方式</th>
							<td>
								<label><html:radio property="fpfs" value="bl" onclick="hid_tr('tr_bl','tr_rs');"/>按比例分配</label>
<!--								<label><html:radio property="fpfs" value="rs" onclick="hid_tr('tr_rs','tr_bl');"/>按人数分配</label>-->
							</td>
							<td colspan="2">
								<logic:present name="rssx">
									<font color="red">注：该项目人数上限为${rssx }</font>
								</logic:present>
								
								<logic:notPresent name="rssx">
									<font color="red">注：该项目无人数上限</font>
								</logic:notPresent>
							</td>
						</tr>
						<tr id="tr_bl">
							<th>比例</th>
							<td colspan="3"><html:text property="bl"></html:text>%</td>
						</tr>
						<tr id="tr_rs" style="display: none"> 
							<th>人数</th>
							<td colspan="3"><html:text property="rs"></html:text>人</td>
						</tr>
					</tbody>
					<tfoot>
					<tr>
						<td colspan="4" id="footId">
							<div align="right">
							<button type="button" name="保存" class="" onclick="checkSave();">
								保 存
							</button>
							<button type="button" name="取消" class="" onclick="window.close();return false;">
								关 闭
							</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
		    </div>
			<div id="tmpdiv1"></div>
			<logic:present name="message">
				<input type="hidden" id="message" value="${message }"/>
					<script>
						alert($('message').value);
						dialogArgumentsQueryChick();
						window.close();
					</script>
			</logic:present>
		</html:form>
	</body>
</html>
