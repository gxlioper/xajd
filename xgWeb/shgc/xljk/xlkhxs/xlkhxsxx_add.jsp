<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsfunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript">
	function xlkhxsxx_save(){
		var xh=document.all["xh"].value;
		if ( xh==""){
			alert ("请根据选择按键选择好学生信息！");
			document.all["xh"].focus();
			return false;
		}
		var zdgcdxdm=document.all["zdgcdxdm"].value;
		if ( zdgcdxdm==""){
			alert ("请选择是否重点观察对象！");
			document.all["zdgcdxdm"].focus();
			return false;
			}
		document.all["add_flag"].value="yes";
		underDealWith();
		refreshForm('/xgxt/xljk_xlkhxs.do?act=xljk_xlkhxs&doType=xlkhxs_add');
	}	
	</script>
	</head>
	<body>
		<html:form action="/xljk_xlkhxs" method="post">
			<input type="hidden" id="add_flag" name="add_flag" value="no" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置11:</em><a>心理健康 - 心理困惑学生管理 - 心理困惑学生信息 - 增加学生信息</a>
				</p>
			</div>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="6"><span>心理困惑学生</span></th>
			        </tr>
			    </thead>
				 <tfoot>
			      <tr>
			        <td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button name="提交" onclick="xlkhxsxx_save()" id="buttonSave">保 存</button>
			            <button name="关闭"  onclick="Close();return false;"  id="buttonClose">关 闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
				<tbody>
				<tr style="height:22px" name="aa" id="a1">
					<th align="right" colspan="2" nowrap>
						 学 号
					</th>
					<td align="left">
						<html:text property="xh" styleId="xh" onblur="" onkeypress=""
							readonly="true" />
						<button
							onclick="showTopWin('/xgxt/xljk_xlkhxs.do?act=xljk_xlkhxs&doType=stu_info',750,550);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
					</td>

					<th align="right">
						姓 名 
					</th>
					<td align="left" colspan="2">
						<html:text property="xm" styleId="xm" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px" name="aa" id="a2">
					<th align="right" colspan="2">
						性 别
					</th>
					<td align="left">
						<html:text property="xb" styleId="xb" readonly="true" />
					</td>
					<th align="right">
						班 级
					</th>
					<td align="left" colspan="2">
						<html:text property="bjmc" styleId="bjmc" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px" name="aa" id="a3">
					<th align="right" colspan="2">
						学 院
					</th>
					<td align="left">
						<html:text property="xymc" styleId="xymc" readonly="true" />
					</td>
					<th align="right">
						重点观察对象
					</th>
					<td align="left" colspan="2">
						<html:select property="zdgcdxdm" style="width:80px" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="zdgcdxList" property="DMH"
								labelProperty="DMMC" />
						</html:select>
					</td>
				</tr>
			</table>
			
			<div id="tmpdiv"></div>
		</html:form>
	</body>
	<logic:notEmpty name="message">
		<logic:equal name="message" value="insert_success">
			<script>
				alert("增加成功!");
				window.dialogArguments.document.getElementById("search_go1").click();
				Close();
			</script>
		</logic:equal>
		<logic:equal name="message" value="insert_fail">
			<script>
				alert("增加失败!");
				document.getElementById("tmpdiv").innerHTML = "";
			</script>
		</logic:equal>
		<logic:equal name="message" value="xh_exits">
			<script>alert("该学生已经存在！增加失败！")</script>
		</logic:equal>
	</logic:notEmpty>
</html>
