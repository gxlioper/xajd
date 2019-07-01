<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript" src="js/Function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script language="javascript">	     
	     function sqSave(){
	        refreshForm("/xgxt/xljkMjxyXlwj.do?method=xlwjSb&doType=save");
	        if($("buttonSave")){
	          $("buttonSave").disabled=true;
	        }
	     }
	     function sqPrint(){
	        window.open('xljkMjxyXlwj.do?method=xlwjyjb&pkValue=${pkValue}');
	       }	
	</script>
	</head>
	<body>
		   <html:form action="/xljkMjxyXlwj" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" id="url" name="url" value="/xljkMjxyXlwj.do?method=xlwjSb&doType=add" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc-jg-mzmc" />
			<logic:equal name="doType" value="add">
				<input type="hidden" id="save_sbsj" name="save_sbsj" value="${nowTime }"/>
			</logic:equal>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<logic:present name="result">
			<script>
				alert(document.getElementById('message').value);
				if(opener){
			 		opener.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
			</logic:present>
			<div class="tab">
				<table width="100%"  border="0" class="formlist">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>填写申请表</b>
						</td>
					</tr>
				</thead>
				<tbody>
				<tr style="height:22px">
					<th align="right" style="width: 15%">
						<logic:equal name="doType" value="add">
						<font color="red">*</font>
						</logic:equal>
						学号
					</th>
					<td style="width: 35%">
						<logic:equal name="doType" value="add">
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
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text property="xh" value="${rs.xh }" readonly="true"/>
							<input type="hidden" name="save_xh" id="save_xh" value="${rs.xh }"/>
						</logic:notEqual>
						<logic:equal name="doType" value="add">
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</logic:equal>
					</td>
					<th style="width: 15%">
						姓名
					</th>
					<td style="width: 35%">
						${rs.xm }
					</td>
				</tr>
				
				<tr style="height:22px">
					<th>
						性别
					</th>
					<td>
					   ${rs.xb }
					</td>
					<th>
						出生年月
					</th>
					<td>
						${rs.csrq }
						
					</td>
				</tr>
				<tr>
				<th>
					系别
				</th>
				<td>
					 ${rs.xymc }
				</td>
				<th>
					年级专业
				</th>
				<td>
					${rs.nj }
					${rs.zymc }
				</td>
				</tr>
				<tr>
				<th>
					班级
				</th>
				<td>
					${rs.bjmc }
				</td>
				<th>
					联系电话
				</th>
				<td>
					${rs.lxdh }
				</td>
				</tr>
				<tr>
				<th>
					家庭住址
				</th>
				<td>
					${rs.jtdz }
				</td>
				<th>
					家庭联<br>系电话
				</th>
				<td>
					${rs.lxdh1 }
				</td>
				</tr>
				<logic:notEqual name="doType" value="add">
				<th>
					申报时间
					<input type="hidden" id="save_sbsj" name="save_sbsj" value="${nowTime }"/>
				</th>
				<td>
					${rs.sbsj }
				</td>
				<th></th>
				<td></td>
				</logic:notEqual>
				<tr >
					<th align="right">
						基本情况
						<br />
						<span class="style1">(限500字)&nbsp;</span>
					</th>
					<td colspan="3" align="left">
						<html:textarea rows="8" cols="76" style="width:98%" property="save_jbqk" value="${rs.jbqk}"  onblur="chLeng(this,500)"/>
					</td>
				</tr>
				</tbody>
				 <tfoot>
			      <tr>
			        <td colspan="6">
			         <logic:equal name="doType" value="yes">
			        <div class="bz"> "<span class="red">*</span>"为必填项</div>
			        </logic:equal>
			          <div class="btn">
			          	<logic:equal name="writeAble" value="yes">
				          	<logic:equal name="doType" value="add">
								<button  id="buttonSave" onclick="sqSave();" style="width:80px">
									保  存 
								</button>
							</logic:equal>
							<logic:equal name="doType" value="modi">
								<button  id="buttonSave" onclick="refreshForm('/xgxt/xljkMjxyXlwj.do?method=xlwjOne&doType=update')" style="width:80px">
										保  存 
								</button>
							</logic:equal>
						</logic:equal>
						<button class="button2" onclick="sqPrint()" style="width:80px">
							打  印
						</button>   
			          </div>
			          </td>
			      </tr>
			    </tfoot>
			</table>
			
			</div>
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
			<logic:equal name="isExist" value="no">
				<script>
			        alert("该荣誉称号已申请,且已通过相关部门审核\n或正在审核中,不能再次申请！");			    
			        </script>
			</logic:equal>
			<logic:equal name="pass" value="no">
				<script>
			        alert("该生不满足该项荣誉称号申请条件！");			    
			        </script>
			</logic:equal>
		</html:form>
	</body>


</html>

