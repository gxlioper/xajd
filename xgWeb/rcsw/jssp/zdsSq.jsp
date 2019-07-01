<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script type='text/javascript' src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/xljk/xlkhxsWh.js"></script>
	<script language="javascript">	
     function sqSave(){
     	if($("xh").value=="" ){
     		alert("请输入必填项！");
     		return false;
     	}
     	var url="/xgxt/jsspZds.do?method=zdsSq&doType=save";
         refreshForm(url);
        if($("buttonSave")){
          $("buttonSave").disabled=true;
        }
     }
     
     function modi(url){
			var url="/xgxt/jsspZds.do?method=zdsOne&doType=update";
			 refreshForm(url);
	        if($("buttonSave")){
	          $("buttonSave").disabled=true;
	        }
		}
		
	function audi(url){
			var url="/xgxt/jsspZds.do?method=zdsOne&doType=shone";
			 refreshForm(url);
	        if($("buttonSave")){
	          $("buttonSave").disabled=true;
	        }
		}
		
		
	jQuery(function(){
		onShow();
	})
</script>
</head>
	<body >
		   <html:form action="jsspZds" method="post">
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/jsspZds.do?method=zdsSq&doType=add" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" id="doType" name="doType" value="${doType}" />
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}" />
			
			<div class="tab_cur">
				<p class="location">
					<logic:notEqual name="doType" value="audi">
					<em>您的当前位置:</em><a>${title }</a>
					</logic:notEqual>
					<logic:equal name="doType" value="audi">
					<em>您的当前位置:</em><a>日常事务-事务申请-走读生审核</a>
					</logic:equal>
				</p>
			</div>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>申请表填写</span></th>
			        </tr>
			    </thead>
				 <tfoot>
			      <tr>
			        <td colspan="4">
			        <logic:equal name="doType" value="add">
			          <div class="bz">"<span class="red">*</span>"为必填项</div>
			        </logic:equal>
			          <div class="btn">
			          	<logic:equal name="writeAble" value="yes">
				          	<logic:equal name="doType" value="add">
								<button type="button"  id="buttonSave" onclick="sqSave();" style="width:80px">
									提交申请
								</button>
							</logic:equal>
							<logic:equal name="doType" value="modi">
								<logic:notEqual name="write" value="disabled">
								<button type="button"  id="buttonSave" onclick="modi()" style="width:80px">
									保  存 
								</button>
								</logic:notEqual>
							</logic:equal>
							<logic:equal name="doType" value="audi">
								<logic:notEqual name="write" value="disabled">
								<button type="button"  id="buttonSave" onclick="modi()" style="width:80px">
									保  存 
								</button>
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual name="doType" value="add">
								<button type="button"  id="buttonSave" onclick="Close();return false;" style="width:80px">
									关  闭
								</button>
							</logic:notEqual>
						</logic:equal>
			          </div>
			          </td>
			      </tr>
			    </tfoot>
				<tbody>
					<tr>
						<th>
							<logic:equal name="doType" value="add">
							<font color="red">*</font>
							</logic:equal>
							学号
						</th>
						<td>
							<logic:notEqual name="userType" value="stu">
							<logic:notEmpty name="rs" scope="request">
								<html:text  property="xh" styleId="xh"
									onblur="dctStuXh()" name="rs"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<html:hidden name='rs' property="save_xh" value="${rs.xh}" />
							</logic:notEmpty>
							<logic:empty name="rs" scope="request">
								<html:text  property="xh" styleId="xh" 
									onblur="dctStuXh()" 
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
							</logic:empty >
							</logic:notEqual>
							
							<logic:equal name="userType" value="stu">
								<input  type="text" name="sxh" id="sxh" value="${userName }" readonly="true"/>
								<html:hidden  property="save_xh" styleId="xh" value="${userName }"/>
							</logic:equal>
							<logic:equal name="doType" value="add">
								<logic:notEqual name="userType" value="stu">
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
								</logic:notEqual>
							</logic:equal>
						</td>
						<th>
							姓名
						</th>
						<td>
							<input type="text" name="xm" value="${rs.xm }" readonly="true"/>
						</td>
					</tr>
					<tr>
						<th>
							性别
						</th>
						<td>
							<input type="text" name="xb" value="${rs.xb }" readonly="true"/>
						</td>
						<th>
							<logic:equal name="doType" value="add">
							<font color="red">*</font>
							</logic:equal>
							申请时间
						</th>
						<td>
							<logic:equal name="doType" value="add">
								<input type="text" name="sqsj" value="${nowTime}" readonly="true"/>
								<html:hidden property="save_sqsj" value="${nowTime}"/>
							</logic:equal>
							<logic:notEqual name="doType" value="add">
								<input type="text" name="sqsj" value="${rs.sqsj}" readonly="true"/>
								<html:hidden property="save_sqsj" value="${rs.sqsj}"/>
							</logic:notEqual>
						</td>
					</tr>
					<tr>
						<th>
							年级
						</th>
						<td>
							<input type="text" name="nj" value="${rs.nj }" readonly="true"/>
						</td>
						<th>
							系
						</th>
						<td>
							<input type="text" name="xymc" value="${rs.xymc }" readonly="true"/>
						</td>
					</tr>
					<tr>
						<th>
							专业
						</th>
						<td>
							<input type="text" name="zymc" value="${rs.zymc }" readonly="true"/>
						</td>
						<th>
							班级
						</th>
						<td>
							<input type="text" name="bjmc" value="${rs.bjmc }" readonly="true"/>
						</td>
					</tr>
					<logic:equal name="doType" value="audi">
						<logic:equal name="userType" value="xy">
							<th>
								<bean:message key="lable.xb" />审核
							</th>
							<td>
								<html:select name="rs" property="save_xysh" styleId="xysh">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
								<html:hidden property="save_xyshsj" value="${nowTime }"/>
							</td>
						</logic:equal>
						<logic:equal name="userType" value="xx">
							<th>
								学校审核
							</th>
							<td>
								<html:select name="rs" property="save_xxsh" styleId="xysh">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
								<html:hidden property="save_xxshsj" value="${nowTime }"/>
							</td>
						</logic:equal>
						<logic:equal name="userType" value="admin">
							<th>
								学校审核
							</th>
							<td>
								<html:select name="rs" property="save_xxsh" styleId="xysh">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
								<html:hidden property="save_xxshsj" value="${nowTime }"/>
							</td>
						</logic:equal>
						<th></th>
						<td></td>
					</logic:equal>
					<tr>
						<th>
							申请理由<br/>
							<font color="red">(500字)</font>
						</th>
						<td colspan="3">
							<html:textarea rows="3" cols="76"  style="word-break:break-all;"
							 property="save_sqly" value="${rs.sqly }" onblur="chLeng(this,500)"></html:textarea>
						</td>
					</tr>
					<tr>
						<th>
							备注
							<br/>
							<font color="red">(500字)</font>
						</th>
						<td colspan="3">
							<html:textarea rows="3" cols="76"  style="word-break:break-all;"
							 property="save_bz" value="${rs.bz }" onblur="chLeng(this,500)"></html:textarea>
						</td>
					</tr>
					<logic:equal name="doType" value="audi">
						<logic:equal name="userType" value="xy">
							<tr>
								<th>
									系审核意见
									<br/>
									<font color="red">(500字)</font>
								</th>
								<td colspan="3">
									<html:textarea rows="3" cols="76"  style="word-break:break-all;"
									 property="save_xyshyj" value="${rs.xyshyj }" onblur="chLeng(this,500)"></html:textarea>
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="userType" value="xx">
							<tr>
								<th>
									系审核意见
									<br/>
									<font color="red">(500字)</font>
								</th>
								<td colspan="3">
									<html:textarea rows="3" cols="76"  style="word-break:break-all;"
									 property="save_xyshyj" value="${rs.xyshyj }" onblur="chLeng(this,500)"></html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									学校审核意见
									<br/>
									<font color="red">(500字)</font>
								</th>
								<td colspan="3">
									<html:textarea rows="3" cols="76"  style="word-break:break-all;"
									 property="save_xxshyj" value="${rs.xxshyj }" onblur="chLeng(this,500)"></html:textarea>
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="userType" value="admin">
							<tr>
								<th>
									系审核意见
									<br/>
									<font color="red">(500字)</font>
								</th>
								<td colspan="3">
									<html:textarea rows="3" cols="76"  style="word-break:break-all;"
									 property="save_xyshyj" value="${rs.xyshyj }" onblur="chLeng(this,500)"></html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									学校审核意见
									<br/>
									<font color="red">(500字)</font>
								</th>
								<td colspan="3">
									<html:textarea rows="3" cols="76"  style="word-break:break-all;"
									 property="save_xxshyj" value="${rs.xxshyj }" onblur="chLeng(this,500)"></html:textarea>
								</td>
							</tr>
						</logic:equal>
					</logic:equal>
							<logic:equal name="doType" value="view">
							<logic:equal name="userType" value="xy">
							<tr>
								<th>
									系审核意见
									<br/>
									<font color="red">(500字)</font>
								</th>
								<td colspan="3">
									<html:textarea rows="3" cols="76"  style="word-break:break-all;"
									 property="save_xyshyj" value="${rs.xyshyj }" onblur="chLeng(this,500)"></html:textarea>
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="userType" value="xx">
							<tr>
								<th>
									系审核意见
									<br/>
									<font color="red">(500字)</font>
								</th>
								<td colspan="3">
									<html:textarea rows="3" cols="76"  style="word-break:break-all;"
									 property="save_xyshyj" value="${rs.xyshyj }" onblur="chLeng(this,500)"></html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									学校审核意见
									<br/>
									<font color="red">(500字)</font>
								</th>
								<td colspan="3">
									<html:textarea rows="3" cols="76"  style="word-break:break-all;"
									 property="save_xxshyj" value="${rs.xxshyj }" onblur="chLeng(this,500)"></html:textarea>
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="userType" value="admin">
							<tr>
								<th>
									系审核意见
									<br/>
									<font color="red">(500字)</font>
								</th>
								<td colspan="3">
									<html:textarea rows="3" cols="76"  style="word-break:break-all;"
									 property="save_xyshyj" value="${rs.xyshyj }" onblur="chLeng(this,500)"></html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									学校审核意见
									<br/>
									<font color="red">(500字)</font>
								</th>
								<td colspan="3">
									<html:textarea rows="3" cols="76"  style="word-break:break-all;"
									 property="save_xxshyj" value="${rs.xxshyj }" onblur="chLeng(this,500)"></html:textarea>
								</td>
							</tr>
						</logic:equal>
						</logic:equal>
				</tbody>
			</table>
			</div>
			<logic:notEqual name="dontSq" value="true">
				<logic:equal name="done" value="true">
					<script>
				          alert("申请成功！");
				        </script>
				</logic:equal>
				<logic:equal name="done" value="false">
					<script>
				          alert("申请失败！");
				    </script>
				</logic:equal>
			</logic:notEqual>
			<logic:equal name="dontSq" value="true" >
				<script>
				          alert("不在申请时间内！");
				</script>
			</logic:equal>
			<logic:present name="result">
		<input type="hidden" id="message" value="${message}"/>
		<logic:equal name="result" value="true">
		<script>
				alert(document.getElementById('message').value);
				if(opener){
			 		opener.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:equal>
		<logic:equal name="result" value="false">
		<script>
				alert(document.getElementById('message').value);
			</script>
		</logic:equal>
		</logic:present>
		</html:form>
	</body>
</html>

