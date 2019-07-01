<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	<script type='text/javascript' src="js/comm/message.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
		function getQsh(){
			jQuery.setAjax({async:false});

			jQuery.getJSON('',{lddm:jQuery('#lddm').val()},function(){
				
			});

			jQuery.setAjax({async:true});
		}

		function saveRzxx(){
			if(jQuery('#xh').val()==""){
				alertError("请选择入住学生的学号！");
				return false;
			}
			if(jQuery('#rzsj').val()==""){
				alertError("请选择入住时间！");
				return false;
			}
			if(jQuery('#xb').val()!=jQuery('#cwxb').val()){
				alertError("床位性别与学生性别不一致，无法入住！");
				return false;
			}
			var comfirmValue="";

			if(jQuery('#xydm').val()=="" || jQuery('#nj').val()==""){
				alertError("入住学生的年级、<bean:message key="lable.xsgzyxpzxy" />信息不完整，无法入住！");
				return false;
			}else if(jQuery('#cwxydm').val()=="" && jQuery('#cwnj').val()==""){
				comfirmValue="该床位未分配，学生入住时系统会自动将床位分配至学生所属<bean:message key="lable.xsgzyxpzxy" />年级，确认入住？";
			}else if((jQuery('#xydm').val()!=jQuery('#cwxydm').val()) 
					||(jQuery('#nj').val()!=jQuery('#cwnj').val())){
				comfirmValue="入住学生的年级<bean:message key="lable.xsgzyxpzxy" />与床位的所属年级<bean:message key="lable.xsgzyxpzxy" />不一致，学生入住时系统会自动将床位的所属修改为入住学生的年级<bean:message key="lable.xsgzyxpzxy" />，确认入住？";
			}else{
				comfirmValue="确认入住？";
			}
			confirmInfo(comfirmValue, function(tag){
				if(tag == 'ok'){
					document.forms[0].action = "gyglnew_cwgl.do?method=cwwhModi&doType=ruzhu";
					document.forms[0].submit();
				}
			});
		}
		
		function getStu(){
			var url = 'gyglnew_cwgl.do?method=getStuInfo';
			if($('cwxb')){
				url += '&xb='+encodeURI(encodeURI($('cwxb').value));
			}
			
			if($('cwxydm')){
				url += '&xydm='+$('cwxydm').value;
			}
			
			if($('cwnj')){
				url += '&nj='+$('cwnj').value;
			}
			
			//showTopWin(url,800,600);
			showDialog('', 800,500,url);
		}
		
		
	</script>
</head>
<body>
	
	<html:form action="/gyglnew_cwgl" method="post">
		<input type="hidden" id="refreshParent" value="true"/>
		<input type="hidden" name="pkValue" value="${param.pkValue }"/>	
		<input type="hidden" id="tableName" name="tableName" value="view_xg_gygl_new_wzsxsxx"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh"/>
		<input type="hidden" id="url" name="url" value="gyglnew_cwgl.do?method=cwwhModi"/>
		<%--
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>床位信息修改</a>
			</p>
		</div>	
		<div class="prompt" id="span_qsh" style="display: none">
	       <h3><span>提示：</span></h3>
	       <p>该寝室号在本楼栋下已存在！<br/></p>
	   	</div>	
		--%>
		
		<div class="tab" style="width:100%;height:400px;overflow-x:hidden;overflow-y:auto;">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>床位信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="16%">
					<font color="red">*</font>楼栋名称
				</th>
				<td align="left" width="34%" nowrap="nowrap">
					${rs.ldmc }
				</td>
				
				<th  width="16%">
					<font color="red">*</font>寝室号				
				</th>
				<td  width="34%">
					${rs.qsh }
				</td>
			</tr>

			<tr>
				<th>床位号</th>
				<td>
					${rs.cwh }
				</td>
				<th>寝室性别</th>
				<td>
					${rs.qsxb }
					<input type="hidden" name="cwxb" id="cwxb" value="${rs.qsxb }"/>
				</td>
			</tr>
			<tr>
				<th>所属<bean:message key="lable.xsgzyxpzxy" /></th>
				<td>
					${rs.xymc }
					<input type="hidden" name="cwxydm" id="cwxydm" value="${rs.xydm }"/>
				</td>
				<th>所属年级</th>
				<td>
					${rs.nj }
					<input type="hidden" name="cwnj" id="cwnj" value="${rs.nj }"/>
				</td>
			</tr>
			<tr>
				<th>学号</th>
				<td>
					<input type="text" name="yc" value="" style="display: none" />	
					<input type="text" name="xh" value="<bean:write name="stuInfo" property="xh"/>" 
						readonly="readonly" id="xh" />	
					<input  style="display: none" onclick="refreshForm('gyglnew_cwgl.do?method=cwwhModi');" id="disbutton"/>						
					<button type="button" onclick="getStu();" class="btn_01" id="buttonFindStu">
						选择
					</button>					
				</td>
				<th>姓名</th>
				<td>
					${stuInfo.xm }
				</td>
			</tr>
			<tr>			
				<th>性别</th>
				<td>
					${stuInfo.xb }
					<input type="hidden" name="xb" id="xb" value="${stuInfo.xb }"/>				
				</td>
				<th>年级</th>
				<td>
					${stuInfo.nj }
					<input type="hidden" name="nj" id="nj" value="${stuInfo.nj }"/>					
				</td>
			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>
					${stuInfo.xymc }
					<input type="hidden" name="xydm" id="xydm" value="${stuInfo.xydm }"/>				
				</td>
				<th>专业</th>
				<td>
					${stuInfo.zymc }
					<input type="hidden" name="zydm" id="zydm" value="${stuInfo.zydm }"/>
					<%--<html:text name="stuInfo" property="zymc" readonly="readonly"></html:text>--%>
				</td>
			</tr>
			<tr>
				<th>班级</th>
				<td>
					${stuInfo.bjmc }
					<%--<html:text name="stuInfo" property="bjmc" readonly="readonly"></html:text>--%>		
					<input type="hidden" name="bjdm" id="bjdm" value="${stuInfo.bjdm }"/>				
				</td>
				<th><font color="red">*</font>入住时间</th>
				<td>
					<input type="text" id="rzsj" name="rzsj" onkeypress="onlyBackSpace(this,event);"
										onclick="return showCalendar(this.id,'yyyy-MM-dd')"/>					
				</td>
			</tr>
			<tr >
								<th >
									入住原因
								</th>
								<td>
									<html:select property="rzyy" styleId="rzyy">
										<html:optionsCollection name="rzyylist" label="rzyymc" value="rzyydm"/>
									</html:select>
								</td>
							</tr>
			<tr>
				<th>
					备注
					<br /><font color="red">(限制在500字内)</font>
				</th>
				<td colspan="3" width="84%">
					<html:textarea property='bz' style="width:95%" styleId="bz" rows='7' value="${rs.bz}" onblur="chLeng(this,500);"/>
				</td>
			</tr>
			</tbody>
		</table>
		</div>
		<table class="formlist" width="95%">
					<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button" name="提交" id="buttonSave" onclick="saveRzxx();return false;">保存</button>
			            <button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">			
			showAlert("保存成功",{},{"clkFun":function(){
 				if (parent.window){
 					refershParent();
 				}
 			}});
			
		</script>
	</logic:present>
</body>
</html>
