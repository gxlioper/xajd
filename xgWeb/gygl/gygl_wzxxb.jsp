<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/xsgyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='js/check.js'></script>					
	</head>
	<body onload="checkWinType();">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 信息维护 - 外住学生管理 </a>
			</p>
		</div>
		<!-- 标题 end-->
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/stuOutputInfo" method="post">
		    <input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="disableEle" name="disableEle"
				value="xm-xb-nj-zymc-bjmc" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url"
				value="/stuOutputInfo.do" />
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
			<input type="hidden" id="doType" name="doType"
				value="<bean:write name="doType" scope="request"/>" />
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
				    	alert("您输入的学号无效!");
				    </script>
				</logic:equal>				
				<!-- 外住学生信息维护 -->
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="4">
								<span>外住学生信息维护</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr>
							<th>
							学号
							</th>
							<td align="left">
							<html:text name='rs' property="xh" readonly="true" onblur="dctStuXh()"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<logic:equal name="doType" value="add">
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" style="" >
									选择
								</button>
							</logic:equal>
							</td>
							<th align="right">
								外住开始学年
							</th>
							<td align="left">
								<html:select name="rs" property="xn" styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
						    <th align="right">
								姓名
							</th>
							<td align="left">
							<html:text name="rs" property="xm" styleId="xm" readonly="true"></html:text>								
							</td>						
							<th align="right">
								外住开始学期
							</th>
							<td align="left">
							  <html:select name="rs" property="xq" styleId="xq">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								年级
							</th>
							<td align="left">
							    <html:text name="rs" property="nj" styleId="nj" readonly="true"></html:text>									
							</td>
							 <th align="right">
								外住开始日期
							</th>
							<td align="left">
                                   <html:text name="rs" property="wzksrq" readonly="true"
									onclick="return showCalendar('wzksrq','y-mm-dd');"
									onblur="dateFormatChg(this)" style="cursor:hand " property="wzksrq"/>							</td>
						</tr>
						<tr>
							<th align="right">
								性别
							</th>
							<td align="left">
							 <html:text name="rs" property="xb" styleId="xb" readonly="true"></html:text>	
							</td>
							<th align="right">
								计划外住时间
							</th>
							<td align="left">
								<html:text name='rs' property="jhwzsj" maxlength="20" styleId="jhwzsj" style="width:80px"/><span style="color: red">如12个月</span>						
							</td>
						</tr>
						
						<tr>						   
							<th align="right">
								专业
							</th>
							<td align="left">
							    <html:text name="rs" property="zymc" styleId="zymc" readonly="true"></html:text>	
							</td>
							<th align="right">
								外住类型
							</th>
							<td align="left">
								<html:select name="rs" property="wzlxdm" styleId="wzlxdm">
									<html:options collection="wzlxList" property="wzlxdm" labelProperty="wzlxmc" />
								</html:select>
							</td>
						</tr>
						<tr>						   
							<th align="right">
								班级
							</th>
							<td align="left">
							 <html:text name="rs" property="bjmc" styleId="bjmc" readonly="true"></html:text>	
							</td>							
							<th align="right">
								外住地址
							</th>
							<td align="left">
								<html:text name="rs" property="wzdz" styleId="wzdz" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								手机号码
							</th>
							<td align="left">
								<html:text property="sjhm" maxlength="30" value="${rs.sjhm}" onkeyup="checkInputData(this);"></html:text>
							</td>							
							<th align="right">
								是否征得家长同意
							</th>
							<td align="left">
								<html:select property="jzsfty" styleId="jzsfty" value="${rs.jzsfty}"> 
									<html:option value=""></html:option>
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								固定电话
							</th>
							<td align="left">
								<bean:write name="rs"  property="lxdh"/>
							</td>
							<th align="right">
								
							</th>
							<td align="left">
								
							</td>
						</tr>						
						<tr>						   
							<th align="right">
								电子邮箱
							</th>
							<td align="left">
								<bean:write name="rs"  property="lxdzxx"/>
							</td>							
							<th align="right">
								
							</th>
							<td align="left">								
							</td>
						</tr>								
						<tr>
							<th align="right">
								外住原因<br/><font color="red">(限200字内)</font>
							</th>
							<td align="left" colspan="3">
							    <html:textarea  name="rs" property="wzyy" styleId="wzyy" rows="6"  cols="75" onblur="chLeng(this,200);"></html:textarea>								
							</td>
						</tr>
						</tbody>
						<tfoot>
						<tr bgcolor="EEF4F9" align="center">
							<td colspan="4">
								<div class="btn">
									<logic:notEqual value="view" name="doType">
										<button id="buttonSave" 
											onclick="wzDataSave()">
											保存
										</button>
									</logic:notEqual>
									<button id="buttonClose" onclick="Close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
						</tfoot>
					</table>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="ok" name="result">
			<script>
				alert("操作成功!");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("操作失败,请检查输入数据！");
			</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
	     function wzDataSave(){
            if(mstFill("xh-xn-xq-wzksrq-jhwzsj-wzlxdm-wzdz-wzyy-jzsfty"))
            { 
               if($('wzyy').value.length>200){               
                  alert('外住原因字数超出200字！');
                  return false;
               }else{		                              
                    var xn = $("xn").value;
                    var xq = $("xq").value;
                    var xh = $("xh").value;
                    var wzksrq = $("wzksrq").value;               
                    pkV = xn+xq+xh+wzksrq;
                    var doType = $("doType").value;
                      getSztzData.getInfoEx("gygl_xswzxxb","xn||xq||xh||wzksrq",pkV,"1=1",function(str){
		               if(str){		         	
		                  if(confirm("该学年、学期、该生此开始日期，外住相关信息已存在！\n\n确定要保存？\n\n点击\"确定\"，保存数据并更改已存在信息；\n点击\"取消\"，将放弃更改！")){
		                    refreshForm('/xgxt/OutputstuinfoSave.do');
		                    $("buttonSave").disabled=true;                          
		                  }else{
		                     return false;
		                  }	          			              
		               }else{
		                  if(confirm("确定要保存？\n\n点击\"确定\"，保存信息；\n点击\"取消\"，将放弃保存！")){
		                    refreshForm('/xgxt/OutputstuinfoSave.do'); 
		                    $("buttonSave").disabled=true;                             	                  
		                  }else{
		                     return false;
		                  }	
 		               }
                      }); 
               }
          }    
       }
       function mstFill(mustFill){
           var eles = mustFill.split("-");
	       for (i = 0; i < eles.length; i++) {
		      if($(eles[i])){
			     if (document.getElementById(eles[i]).value == "") {
				    alert("所有选项不得为空！");
				    return false;
			     }
		      }
	       }
	       return true;
       }
	</script>
</html>
