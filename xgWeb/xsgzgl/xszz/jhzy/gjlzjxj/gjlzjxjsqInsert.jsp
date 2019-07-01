<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		function onShow(){ 

			var isKns = jQuery("#isKns").val();
			var xh = jQuery("#xh").val();
			var shzt = jQuery("#shzt").val();
	
			
			if(xh!="" && isKns=="false"){
				alertError("必须为本学年<font color='blue'>困难生</font>的学生，才可以进行国家励志奖学金申请，请您确认^_^||");
				jQuery("#buttonSave").attr("disabled","disabled");
				return false;
			}else if(xh!="" && (shzt == "shz" || shzt == 'tg' || shzt == 'btg')){
				alertError("该学生的申请记录已经进入审核阶段，无法重复申请，请您关闭本页面，点击<font color='blue'>流程跟踪</font>进行查看目前的进展^_^||");
				jQuery("#buttonSave").attr("disabled","disabled");
				return false;
			}		
		}
		
		//保存困难生认定
		function saveKnsrdSq(){
			var array = jQuery(".bcClass");
			var flag = true;
			jQuery(array).each(function (i,n) {
				if (jQuery(n).val()=="" || jQuery(n).val()==null) {
					flag = false;
					alertError("带*号字段必须填写！");
					return false;	
				}
			});
			
			var bxkms = jQuery('#bxkms').val();
			var jgms = jQuery('#jgms').val();
			if (parseInt(bxkms) < parseInt(jgms)) {
				alertError("必修课门数填写有误，不能小于及格门数！");
				flag = false;
				return false;	
			}
			
			if (flag) {
				refreshForm('jhzyGjlzjxj.do?method=gjlzjxjsqInsert&act=save');
			}
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
		<html:form action="/jhzyGjlzjxj" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="url" name="url" value="jhzyGjlzjxj.do?method=gjlzjxjsqInsert"/>
			<input type="hidden" id="lx" name="lx" value="学生"/>

			

			<div style="width:100%;height:630px;overflow-x:hidden;overflow-y:auto;">
			<table class="formlist" border="0" align="center" style="width: 100%">
				<tr style="height: 23px">
					<td align="center" colspan="4">
						<font size="5">
							${zzxn }学年国家励志奖学金申请
						</font>
					</td>
				</tr>
			</table>
				<input type="hidden" name="isKns" id="isKns" value="${isKns }"/>
				<input type="hidden" name="shzt" id="shzt" value="${rs.shzt }"/>
				<table width="100%" border="0" class="formlist">
					<!-- 学生基本信息 begin-->
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr >
							<th align="right" width="20%">
								<font color="red">*</font>学号
							</th>
							<td align="left" width="30%">
								<logic:equal name="userType" value="stu">
									<input type="hidden" id="xh" name="xh" value="${xh }"/>
									${xh }
								</logic:equal>
								<logic:notEqual name="userType" value="stu">
									<input type="text" id="xh" name="xh" class="bcClass" readonly="readonly" value="${rs.xh }"/>
									<button type="button" onclick="sendXx();return false;"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</logic:notEqual>	
							</td>
							<th align="right" width="20%">
								姓名
							</th>
							<td align="left" width="30%">
								${rs.xm }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								性别
							</th>
							<td align="left" width="">
								${rs.xb }
							</td>
							<th align="right" width="">
								学制
							</th>
							<td align="left" width="">
								${rs.xz }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								年级
							</th>
							<td align="left" width="">
								${rs.nj }
							</td>
							<th align="right" width="">
								<bean:message key="lable.xb" />
							</th>
							<td align="left" width="">
								${rs.xymc }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								专业
							</th>
							<td align="left" width="">
								${rs.zymc }
							</td>
							<th align="right" width="">
								班级
							</th>
							<td align="left" width="">
								${rs.bjmc}
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								民族
							</th>
							<td align="left" width="">
								${rs.mzmc }
							</td>
							<th align="right" width="">
								政治面貌
							</th>
							<td align="left" width="">
								${rs.zzmmmc }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								身份证号
							</th>
							<td align="left" width="">
								${rs.sfzh }
							</td>
							<th align="right" width="">
								出生年月
							</th>
							<td align="left" width="">
								${rs.csrq }
							</td>
						</tr>
					</tbody>
					<!-- 学生基本信息end -->
					
					<!-- 困难生信息 begin-->
					<thead>
						<tr>
							<th colspan="4">
								<span>学习情况</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						<th align="right" width="20%">
								<font color="red">*</font>成绩排名<br/>（名次/总人数）
							</th>
							<td align="left" width="30%" >
								<html:text property="cjpm" styleClass="bcClass" styleId="cjpm" maxlength="4" value="${rs.cjpm}" style="width:55px" onkeyup="checkInputData(this)"></html:text>
								/${bjzrs }

							</td>
							<th align="right" width="20%">
								<font color="red">*</font>考试情况
							</th>
							<td align="left" width="30%" >
								必修课&nbsp;&nbsp;&nbsp;<html:text property="bxkms" styleClass="bcClass" value="${rs.bxkms}" styleId="bxkms" maxlength="2" style="width:50px" onkeyup="checkInputData(this)"></html:text>（门）
								<br/>及格以上<html:text property="jgms" styleClass="bcClass" styleId="jgms" value="${rs.jgms}" maxlength="2" style="width:50px" onkeyup="checkInputData(this)"></html:text>（门）
							</td>
							
						</tr>
						<tr>
							<th align="right" width="20%">
								<font color="red">*</font>实行综合考评排名
							</th>
							<td align="left" width="30%" >
								<input type="radio" name="sxzhkppm" id="sxzhkppm" class="bcClass" value="是" <logic:equal value="是" name="rs" property="sxzhkppm" >checked</logic:equal>/>是
								<input type="radio" name="sxzhkppm" id="sxzhkppm" class="bcClass" value="否" <logic:equal value="否" name="rs" property="sxzhkppm" >checked</logic:equal>/>否
							</td>
							<th align="right" width="20%">
								<font color="red">*</font>如是，排名<br/>（名次/总人数）
							</th>
							<td align="left" width="30%" >
								<html:text property="zhkppm" styleId="zhkppm" maxlength="4"  value="${rs.zhkppm}"
								styleClass="bcClass" style="width:55px" onkeyup="checkInputData(this)"></html:text>/${bjzrs }
				
							</td>
						</tr>
						</tbody>
						</table>
					
						<table width="100%" border="0" class="formlist">
						<!-- 学生基本信息 begin-->
						<thead>
							<tr>
								<th colspan="3">
									<span>大学期间主要获奖情况</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							<th style="width:20%">
								<div align="center"><font color="red">*</font>获奖日期</div>
							</th>
							<th style="width:40%">
								<div align="center"><font color="red">*</font>获奖名称</div> 
							</th>
							<th style="width:40%">
								<div align="center"><font color="red">*</font>颁奖单位</div>
							</th>
							</tr>
							<tr>
								<td align="center">
									<html:text property="hjsj1" styleId="hjsj1" onclick="return showCalendar('hjsj1','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;width:90px" value="${rs.hjsj1}" styleClass="bcClass"></html:text>
								</td>
								<td align="center">
									<html:text property="hjmc1" styleId="hjmc1" style="width:200px" value="${rs.hjmc1}" styleClass="bcClass" maxlength="30"></html:text>
								</td>
								<td align="center">
									<html:text property="bjdw1" styleId="bjdw1" style="width:200px" value="${rs.bjdw1}" styleClass="bcClass" maxlength="30"></html:text>
								</td>
							</tr>
							<tr>
								<td align="center">
									<html:text property="hjsj2" styleId="hjsj2"  onclick="return showCalendar('hjsj2','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;width:90px" value="${rs.hjsj2}" styleClass="bcClass"></html:text>
								</td>
								<td align="center">
									<html:text property="hjmc2" styleId="hjmc2" style="width:200px" value="${rs.hjmc2}" styleClass="bcClass" maxlength="30"></html:text>
								</td>
								<td align="center">
									<html:text property="bjdw2" styleId="bjdw2" style="width:200px" value="${rs.bjdw2 }" styleClass="bcClass" maxlength="30"></html:text>
								</td>
							</tr>
							<tr>
								<td align="center">
									<html:text property="hjsj3" styleId="hjsj3"  onclick="return showCalendar('hjsj3','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;width:90px" styleClass="bcClass" value="${rs.hjsj3}"></html:text>
								</td>
								<td align="center">
									<html:text property="hjmc3" styleId="hjmc3" style="width:200px" styleClass="bcClass" maxlength="30" value="${rs.hjmc3}"></html:text>
								</td>
								<td align="center">
									<html:text property="bjdw3" styleId="bjdw3" style="width:200px" styleClass="bcClass" maxlength="30" value="${rs.bjdw3 }"></html:text>
								</td>
							</tr>
							<tr>
								<td align="center">
									<html:text property="hjsj4" styleId="hjsj4"  onclick="return showCalendar('hjsj4','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;width:90px" styleClass="bcClass" value="${rs.hjsj4}"></html:text>
								</td>
								<td align="center">
									<html:text property="hjmc4" styleId="hjmc4" style="width:200px" styleClass="bcClass" maxlength="30" value="${rs.hjmc4}"></html:text>
								</td>
								<td align="center">
									<html:text property="bjdw4" styleId="bjdw4" value="${rs.bjdw4}" style="width:200px" styleClass="bcClass" maxlength="30"></html:text>
								</td>
							</tr>
						</tbody>
						</table>
						
						<table width="100%" border="0" class="formlist">
						<tbody>
						<tr>
							<th align="right" width="20%" >
								<font color="red">*</font>申请理由
								<br/><font color="red">(限制字数200)</font>
							</th>
							<td align="left" width="" colspan="3">
								<textarea rows="3" id="sqly" cols="" name="sqly" class="bcClass"
									onblur="chLeng(this,200);"
									style="word-break:break-all;width:99%" >${rs.sqly }</textarea>
							</td>
						</tr>
						
					</tbody>
				
					<!-- 困难生信息 end-->			
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									
									<button type="button" name="保存" onclick="saveKnsrdSq();return false;" id="buttonSave">
										保 存
									</button>
								
									<button type="button" name="关闭" onclick="Close();return false;" id="buttonClose">关 闭</button>					           
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>