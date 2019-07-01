<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>

	<script language="javascript" src="js/qtsjFunction.js"></script>
	
	<script type="text/javascript">	
	 	 function autoFillTeaInfo(cod){
			if(cod == 13){
				var url = '/xgxt/yhwhManage.do?method=yhqxfpManage';
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		}
		
		function checkOne(obj){
			var id = obj.id;
			var ids = id.split('_');
			
			if(ids[1] == 'yy'){
				var oid = ids[0] + "_gl";
				
				if($(oid)){
					$(oid).checked = "";	
				}
			}else{
				var oid = ids[0] + "_yy";
				if($(oid)){
					$(oid).checked = "";
				}
			}
		}
		 
	</script>
	</head>
	
	<body>
		<html:form action="/yhwhManage">
			<input type="hidden" name="userName" id="userName" value="${user.userName }"/>
			<input type="hidden" id="url" value="/xgxt/yhwhManage.do?method=xsqxfpManage"/>			
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>系统维护-权限维护-单个学生授权</a>
				</p>
			</div>
			
			<div class="compTab" id="card" style="width: 100%">
				<div class="comp_title" id="div1">
					<ul id="ul1">
						<li id="li">
							<a href="#" onclick="refreshForm('/xgxt/yhwhManage.do?method=yhqxfpManage');return false;" id="">
								<span>单个用户授权</span>
							</a>
						</li>
						<li id="li">
							<a href="#" onclick="refreshForm('/xgxt/yhwhManage.do?method=jsBatchManage');return false;" id="">
								<span>角色批量授权于用户</span>
							</a>
						</li>
						<li id="li" class="ha">
							<a href="#" onclick="refreshForm('/xgxt/yhwhManage.do?method=xsqxfpManage');return false;" id="">
								<span>单个学生授权</span>
							</a>
						</li>
						<li id="li">
							<a href="#" onclick="refreshForm('/xgxt/yhwhManage.do?method=xsBatchManage');return false;" id="">
								<span>角色批量授权于学生</span>
							</a>
						</li>
					</ul>				
				</div>
			</div>		
						
			<div class="toolbox" style="display: none">
			  <!-- 按钮 -->
			  <div class="buttonbox">
			    <ul>
			      <li> <a id="add" class="btn_zj" onclick="return false;"> 新 增 </a> </li>
			      <li> <a id="search" class="btn_cx" onclick="return false;"> 查 询 </a> </li>
			      <li> <a id="all" onclick="showAllGrgz()" class="btn_qb" onclick="return false;"> 全 部 </a> </li>
			      <li> <a id="delete" class="btn_sc" onclick="return false;"> 删 除 </a> </li>
			    </ul>
			  </div>
			  <!-- 按钮 -->
			  <p class="toolbox_fot"><em></em> </p>
			</div>
		    
			<div class="rightframe04"><!--当左边栏目导航隐藏时调用rightframe04_hidden这个class名-->
			 	<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th width="15%">学号</th>
							<td width="30%">
								<html:text property="yhm" styleId="yhm" value="${rs.yhm}"
									onkeypress="autoFillStuInfo(event.keyCode,this)"></html:text>
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
							</td>
							<th width="15%">姓名</th>
							<td width="35%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>学生类型</th>
							<td></td>
							<th>学生类别</th>
							<td></td>
						</tr>
						<tr>
							<th><bean:message key="lable.xb" /></th>
							<td>
							</td>
							<th>专业</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>班级</th>
							<td></td>
							<th></th>
							<td></td>
						</tr>
					</tbody>		
					<thead>
						<tr><th colspan="4"><span>角色信息</span></th></tr>
					</thead>
					<tbody>
						<tr>
						<th>拥有角色</th>
						<td colspan="3">
							<logic:present name="jsList">
								<logic:iterate id="js" name="jsList" indexId="index">
									${js.jsmc }
									<%	
										int size = (Integer)request.getAttribute("size");
										if(index<size-1){ %>
										,
									<%} %>
								</logic:iterate>
							</logic:present>
						</td>
						</tr>
					</tbody>
					<tfoot>
					<tr>
						<td colspan="4" id="footId">
							<div align="right">
							<button type="button" name="保存" class="" onclick="saveDataShowTips('/xgxt/yhwhManage.do?method=yhqxfpManage&doType=save','yhm','正在保证，请稍候！');">
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
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
