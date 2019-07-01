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
	<script type="text/javascript" src="js/check.js"></script>
	<script language="javascript">	     
      function sqSave(){
     	
     	var url="/xgxt/xsxxCslgXsjl.do?method=xxjlWh&doType=save";
     	
        saveXxjl(url);
     }
     jQuery(function(){
		onShow();
	})

</script>
</head>
	<%--		<input type="hidden" id="printpk" value="${printpk }"/>--%>
	<body >
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		<script type="text/javascript" src="js/xsxx/cslg/xxjlWh.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		   <script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		   <html:form action="/nbty_rych" method="post">
			<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj-jg-mzmc" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc-jg-mzmc" />
			<input type="hidden" id="url" name="url" value="/xsxxCslgXsjl.do?method=xxjlWh" />
			<input type="hidden" id="viewName" name="viewName" value="view_nbty_xsrychb" />
			<input type="hidden" id="tabName" name="tabName" value="nbty_xsrychb" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="save_xn"  value="${xn}"/>
            <input type="hidden" name="save_xq"  value="${xq}"/>
            <input type="hidden" name="save_nd"  value="${nd}"/>
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="save_mzmc" id="save_mzmc" value="${rs.mzmc }"/>
			<input type="hidden" name="save_jg" id="save_jg" value="${rs.jg}"/>
			<input type="hidden" name="save_sbsj" id="save_sbsj" value="${nowTime}"/>
			<input type="hidden" name="doType" id="doType" value="${doType}"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>申请表填写</span></th>
			        </tr>
			    </thead>
				<tbody>
				<tr style="height:22px">
					<th align="right" style="width: 10%">
						<font color="red">*</font>学号
					</th>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="left">
						<logic:equal name="doType"	value="add">
							<html:text  property="xh" styleId="xh"
								onblur="dctStuXh()" name="rs"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<html:hidden name='rs' property="save_xh" value="${rs.xh}" />
						</logic:equal>
						<logic:notEqual name="doType"	value="add">
							<html:text  property="xh" styleId="xh" readonly="true"
								onblur="dctStuXh()" name="rs"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<html:hidden name='rs' property="save_xh" value="${rs.xh}" />
						</logic:notEqual>
						<logic:equal name="doType" value="add">
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</logic:equal>
						<logic:equal name="doType" value="save">
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</logic:equal>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="left">
						<logic:notEmpty name="rs"  scope="request">
							<html:text  property="xh" value="${userName }" readonly="true"/>
							<html:hidden name='rs' property="save_xh" value="${userName}" />
						</logic:notEmpty>
						</td>
					</logic:equal>

					<th align="right" style="width: 10%">
						学年
					</th>
					<td align="left" style="width: 40%">
						${rs.xn }
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						姓名
					</th>
					<td align="left">
						${rs.xm}
					</td>
					<th align="right">
						学期
					</th>
					<td align="left">
						${rs.xqmc }
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						性别
					</th>
					<td align="left">
					<logic:notEmpty name="rs">
						${rs.xb }
					</logic:notEmpty>
					</td>
					<th align="right">
						<font color="red"></font>年度
					</th>
					<td align="left">
						${rs.nd }
					</td>		
				</tr>
				<tr style="height:22px">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						${rs.xymc}
					</td>
					<th align="right">
						专业
					</th>
					<td align="left">
						${rs.zymc }
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						班级
					</th>
					<td align="left">
						${rs.bjmc }
					</td>
					<th></th>
					<td></td>
				</tr>
				</tbody>
			</table>
			</div>
			
			    <h3 class="datetitle_01">
			    <span>
			    	学习经历
			    </span>
			    </h3>
			
				<p>
					<logic:equal value="view" name="doType">
						<button type="button"  value="+" >+</button>
						<button type="button" value="-" >-</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
						<input type="text" name="numAdd" id="numAdd1" style="width: 20px">
						&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
						<input type="text" name="numDel" id="numDel1" style="width: 20px">
						&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:equal>
					
					<logic:notEqual value="view" name="doType">
						<button type="button" value="+"
							onclick="trAdd('flag1','add','numAdd1','rzqk');"
							>+</button>
						<button type="button" value="-" onclick="trDel('flag1','delRow1');"
							>-</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
						<input type="text" name="numAdd" id="numAdd1" style="width: 20px"
							onblur="trAdd('flag1','madd','numAdd1','rzqk')">
						&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
						<input type="text" name="numDel" id="numDel1" style="width: 20px"
							onblur="trDelAll('flag1','numDel1')">
						&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
				</p>
				<div class="tab">
			 		 <table width="100%"  border="0" class="formlist">
				  			 <tfoot>
						      <tr>
						        <td><div class="bz">"<span class="red">*</span>"为必填项</div>
						          
						          <div class="btn">
						            <logic:equal name="writeAble" value="yes">
						            	<logic:notEqual name="doType" value="view">
										<button type="button"  id="buttonSave" onclick="sqSave();" >
											保  存 
										</button>
										</logic:notEqual>
									</logic:equal>
									<logic:notEqual name="doType" value="add">
										<logic:notEqual name="doType" value="save">
										<button type="button" onclick="Close();return false;">
											关  闭
										</button>
										</logic:notEqual>
									</logic:notEqual>
						          </div>
						          </td>
						      </tr>
						    </tfoot>
						    <tbody>
						    <tr><td>
						    <div class="formbox">
				  				<table summary="" class="datelist" align="" width="100%">
									<!-- 打印时第一行不显示- -->
								
									<thead>
										<tr>
											<td align="center" nowrap="nowrap" style='width:6%'>
												选定删除行
											</td>
											<td align="center" nowrap="nowrap" style='width:17%'>
												起始年月
											</td>
											<td align="center" nowrap="nowrap" style='width:17%'>
												终止年月
											</td>
											<td align="center" nowrap="nowrap" style='width:17%'>
												学校或单位名称
											</td>
											<td align="center" nowrap="nowrap" style='width:20%'>
												证明人
											</td>
											<td align="center" nowrap="nowrap" style='width:20%'>
												备注
											</td>
										</tr>
									</thead>
									<tbody width="100%" align="center" class="tbstyle" id="flag1">
									</tbody>
								</table>
						</div>
						</td></tr>
						</tbody>
			<logic:equal name="result" value="true">
				<script>
			          alert("操作成功！");
			        </script>
			</logic:equal>
			<logic:equal name="result" value="false">
				<script>
			          alert("操作失败！");
			    </script>
			</logic:equal>
			<logic:present name="result">
		<input type="hidden" id="message" value="${message}"/>
			<script>
				if(opener){
			 		opener.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
		</html:form>
	</body>
</html>

