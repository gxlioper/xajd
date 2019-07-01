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
		function cpwjDaSave(){
			var stid=document.getElementsByName("hidden_stid");//试题id
			var stmc=document.getElementsByName("hidden_stmc");//试题名称
			var stlx=document.getElementsByName("hidden_stlx");//试题类型

			var temp;//临时用户获取
			if(stid&&stid.length>0){
				for(var i=0;i<stid.length;i++){
					temp=document.getElementsByName("st_"+stid[i].value);
					
					if(temp&&temp.length>0){
						if(stlx[i].value=="1"){//单选题
							var b=false;
							for(var j=0;j<temp.length;j++){
								if(temp[j].checked){
									b=true;
								}
							}
							if(!b){
								alert("请选择"+stmc[i].value);
								return false;
							}
						}else if(stlx[i].value=="2"){//简答题
							if(temp[0].value.trim()==""){
								alert("请填写"+stmc[i].value);
								return false;
							}
						}
					}else{
						//如果为空数据异常
					}
				}
			}
			confirmInfo("问卷提交后将不可以修改，确定提交吗？",function(data){
				if("ok"==data){
					saveData('bjlh_fdycpwj.do?method=cpwjglWjlr&doType=save','');
				}
			});
		}
	</script>
</head>
<body onload="">
	<html:form action="/bjlh_fdycpwj" method="post">
		<html:hidden property="wjid" name="rs" styleId="wjid"/>		
		<div class="tab_cur" >
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<div class="tab">
			<table class="formlist" width="95%">
				<thead>
					<tr>
						<th><span>辅导员测评问卷：${rs.wjmc}</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							学年：${xn}
							&nbsp;&nbsp;
						          所在部门:${fdyInfo.xymc }
						    &nbsp;&nbsp;
						          辅导员姓名:${fdyInfo.xm }
						    &nbsp;&nbsp;
						</td>
					</tr>
				<logic:present name="stList">
					<logic:iterate id="st" name="stList">
					<tr>
						<td>
							<input type="hidden" name="hidden_stid" value="${st.stid}" />
							<input type="hidden" name="hidden_stmc" value="${st.stmc}" />
							<input type="hidden" name="hidden_stlx" value="${st.stlx}" />
							${st.r}、<bean:write name="st" property="stmc" format="true"/>
						<br/><br/>
							${st.xxHtml }
						</td>
					</tr>
					</logic:iterate>
				</logic:present>
				</tbody>
				<tfoot>
					<tr>
					  	<td>
					  		<div class="bz">"<span class="red">*</span>"为必填项</div>
						    <div class="btn">
								<logic:equal value="false" name="xswjstsfzd">
				            		<button type="button" name="保存" id="buttonSave" onclick="cpwjDaSave();return false;">保存</button>
			          			</logic:equal>
			          			<logic:equal value="true" name="xswjstsfzd">
<%--				            	<button type="button" name="保存" id="buttonSave" onclick="alertInfo('问卷已作答，不可以重做！');return false;">保存</button>--%>
			          			</logic:equal>
								<button type="button" name="重置" type="reset">重 置</button>	
						    </div>
					    </td>
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
