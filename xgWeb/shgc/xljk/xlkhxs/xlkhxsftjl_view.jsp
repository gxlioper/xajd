<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsfunction.js"></script>
	<script type="text/javascript">
	function jd(){
			if($("jd")){
				$("jd").focus();
			}
		}
	</script>
	</head>
	<body onload="jd()">
		<html:form action="/xljk_xlkhxsftjl" method="post">
		<input type="hidden" id="add_flag" name="add_flag" value="no" />	
		<div class="tab_cur" id="jd">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<div class="tab">
		  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="6"><span>查看心理困惑学生访谈记录</span></th>
			        </tr>
			    </thead>
				 <tfoot>
			      <tr>
			        <td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
				         <button   onclick="Close();return false;" style="width:80px">
							关 闭
						</button>
			          </div></td>
			      </tr>
			    </tfoot>
				<tbody>
					<tr style="height:22px">
							<th align="right" colspan="2" nowrap="nowrap">
								<font color="red">*</font>被访学生学号
							</th>
							<td align="left">
								<html:text  property="xh" styleId="xh"
									onblur="" onkeypress=""  readonly="true"/>
							</td>
							
						<th align="right">
							姓名
						</th>
						<td align="left">
							<html:text  property="xm" styleId="xm" readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right" colspan="2" readonly="true" >
							性别
						</th>
						<td align="left">
							<html:text  property="xb" styleId="xb" readonly="true"/>
						</td>
						<th align="right" >
							<bean:message key="lable.xb" />
						</th>
						<td align="left">
							<html:text  property="xymc" styleId="xymc"  readonly="true" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right" colspan="2">
							班级
						</th>
						<td align="left">
							<html:text  property="bjmc" styleId="bjmc" readonly="true"/>
						</td>
						<th align="right" nowrap="nowrap">
							<font color="red">*</font>访谈日期
						</th>
						<td align="left">
							<html:text style="width:85px;" property="ftsj" styleId="bjmc" readonly="true"/>
						</td>
					</tr>
					<tr>
						<th align="right" colspan="2">
							<font color="red">*</font>访谈地点
						</th>
						<td align="left">
							<html:text  property="ftdd" readonly="true" />
						</td>
						<th align="right">
						    <font color="red">*</font> 访谈人
						</th>
						<td align="left" colspan="4">
							<html:text  property="ftr"  readonly="true"/>
						</td>
					</tr>	
					<tr>
						<th align="right" colspan="2">
							手机号码
						</th>
						<td align="left">
							<html:text  property="sjhm" readonly="true" />
						</td>
						<td align="right">
						</td>
						<td align="left" colspan="4">
						</td>
					</tr>
				  <tr>
				  		<th align="right" colspan="2"> <font color="red">*</font>访谈记录</th>
                    	<td  align="left" colspan="6" style="word-break:break-all;"><html:textarea rows="20"  style="width:98%" property="ftjl" styleId="a" readonly="true"/></td>
				  </tr>		
				   <tr>
				  		<th align="right" colspan="2"> 备注 </th>
                    	<td  align="left" colspan="6" style="word-break:break-all;"><html:textarea rows="3"  style="width:98%" property="bz" styleId="a" readonly="true"/></td>
				  </tr>
				</tbody>
				</table>
				</div>
		</html:form>
	</body>
</html>