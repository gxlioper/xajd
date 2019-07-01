<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/gyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>		
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 信息维护 - 楼长信息</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zjjjzy_Gygl" method="post">
		<input type="hidden" id="url" name="url" value="/zjjjzy_Gygl.do?method=lzAdd" />			
		<input type="hidden" id="getStuInfo" name="getStuInfo"value="xm-xb-nj-xymc-zymc-bjmc" />					
			<!-- 楼长信息 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>楼长信息</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr>
						
                        <th height="22" align="right">
							<font color="red">*</font>楼长：
						</th>
						<td height="22" align="left">
							<html:text property="lz" styleId="xh"  readonly="true"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button onclick="showTopWin('/xgxt/stu_LdQsInfo.do',750,550);"
								class="btn_01" id="buttonFindStu" >
								选择
							</button>
						</td>
						<th height="22" align="right">
							<font color="red">*</font>楼栋：
						</th>
						<td height="22" align="left">
							<html:select property="lddm" style="width:120px"
										onchange="" styleId="lddm">										
									<html:option value="">--请选择--</html:option>
									<html:options collection="ldList" property="dm"
											labelProperty="mc" />
							</html:select>								
						</td>						
					</tr>
					<tr>
					    <th height="22" align="right">
							姓名：
						</th>
						<td height="22" align="left">
							<bean:write name="rs" property="xm"/>
						</td>						
						<th height="22" align="right">
							<font color="red">*</font>任职日期：
						</th>
						<td height="22" align="left">
						<html:text  property="rzrq" styleId="rzrq"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('rzrq','y-mm-dd');" readonly="true" />
					    </td>												
					</tr>	
					<tr>						
						<th height="22" align="right">
							性别：
						</th>
						<td height="22" align="left">
							<bean:write name="rs" property="xb"/>
						</td>
  						
						<th height="22" align="right">
							联系方式：
						</th>
						<td height="22" align="left">
						<html:text property="lxdh" maxlength="25"></html:text>						
						</td>						
					</tr>				
					<tr>
					<th height="22" align="right">
                         住址：
					</th>					
                    <td height="22" align="left">
							<html:text name="rs" property="zz" maxlength="50"></html:text>
				    </td>							                        					
					<th height="22" align="right">
							楼长邮箱：
						</th>
					<td height="22" align="left">
							<html:text property="dzyx" maxlength="25"></html:text>
						</td>							
					</tr>															
					<tr align="left">
							<th align="right">
								备注：<br>
								(限200字内)
							</th>
							<td colspan="4">
								<html:textarea  property='bz' style="width:400px"
									rows='5' />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr bgcolor="EEF4F9" align="center">
							<td colspan="4">
								<div class="btn">
									<logic:notEqual name="doType" value="view">
										<button id="buttonSave" onclick="lzAddSave('lddm-lz-rzrq')"
											style="width: 80px">
											保	存
										</button>
									</logic:notEqual>
									&nbsp;&nbsp;
									<button id="buttonClose" onclick="Close();return false;"
										style="width: 80px">
										关	闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>				
		</html:form>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
				alert("操作成功！");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
				alert("操作失败,或系统中已存在与带\"*\"号项目相同的记录，请检查输入的数据后再提交！");
			</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
	     function lzAddSave(mustFill){	           
	          var eles = mustFill.split("-");
	          for (i = 0; i < eles.length; i++) {
		           if (document.getElementById(eles[i]).value == "") {
			           alert("请将带\"*\"号的项目输入完整！");
			           return false;
		           }		
	          }
	          if($("bz").value.length>200){
	              alert("备注字数不能超过200字！");
	              return false;
	          }
	          var lz = $("lz").value;
              var dzyx = document.getElementById('dzyx').value;
	          if(!isEmail(dzyx) && dzyx!=""){
		          alert("请输入正确的电子邮箱!");
		          return false;
	          }
	           var pkValue=$("lddm").value+lz;
	           getSztzData.getInfoEx("lzxxb","lddm||lz",pkValue,"sfzz='是'",function(str){
		         if(str){		         	
		            alert("该生目前正任职该楼楼长，不能重复添加！");		           		          			        
		         }else{
                    refreshForm("/xgxt/zjjjzy_Gygl.do?method=lzAdd&doType=Save");                
		         }
    	       });	 	           
	     }
	</script>
</html>
