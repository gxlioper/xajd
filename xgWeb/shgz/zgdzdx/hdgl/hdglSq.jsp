<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
	function saveHdsq(obj){
		var hddm = $("hddm").value;
		var flg = false;
		if(hddm == ""){
			alert("所申请活动名称不能为空，请确认");
			return false;
		}
		var url = '/xgxt/zgddShgzHdgl.do?method=hdglHdsq&doType=save';
		dwr.engine.setAsync(false);
		getHdglDAO.getZdyZd(hddm,function (data){
			if(data != null && data.length>0){
				for(var i=0;i<data.length;i++){
					var id = data[i].zd;
					if($(id)){
						if($(id).value != ""){
							url+="&"+id+"="+$(id).value;
						}
					}
				}
			}else{
				alert("该活动还未定义字段，请稍候申请！！");
				flg=true;
			}
		});
		dwr.engine.setAsync(true);
		if(flg){
			return false;
		}
		if (confirm("提交申请后无法再次修改，确认本次申请？\n点击\"确定\"，提交申请。\n点击\"取消\"，将放弃申请！")) {
			showTips('处理数据中，请等待......');
			refreshForm(url);
			obj.disabled=true;
		}
	}
	
	function chHd(){	
		var hddm = $("hddm").value;
		var xq = $("xqV").value;
		var tabobj1 = document.getElementById("hdnrtext");
		var tabobj2 = document.getElementById("hdnrarea");
		var leng1 = tabobj1.rows.length;
		var leng2 = tabobj2.rows.length;
		
		
		getHdglDAO.inSj(hddm,function(data){
			if(data>0 || hddm == ""){
			$("btndiv").style.display='';
			if(leng1 > 1){
				for(var i=leng1;i>1;i--){
					tabobj1.deleteRow(i-1);
				}
			}
			
			if(leng2 > 1){
				for(var i=leng2;i>1;i--){
					tabobj2.deleteRow(i-1);
				}
			}
			
			onShow();
			tabobj1 = document.getElementById("hdnrtext");
	
			if (tabobj1.rows.length == 1){
			dwr.engine.setAsync(false);
			getHdglDAO.getZdyZd(hddm,function (data){
				var hdnr1 = new Array();
				for(var i=0;i<data.length;i++){
					
					if (data[i].zdlx == "003"){
					
						var hdnr2 = new Array();
						
						hdnr2[0]=function(da){
							    return "<div align='right'>"+data[i].zdm+"：</div>";
							    }
							    
						hdnr2[1]=function(da){
							    return "<textarea type='text' style='width:100%' id='"
							    +data[i].zd
							    +"' name='"
							    +data[i].zd
							    +"'rows='5' onblur='chLeng(this,500)'/>";	    	
							    }
						
						DWRUtil.addRows("hdnrarea",[''],hdnr2,{escapeHtml:false});
						
					}else{
						if(hdnr1.length == 0){
							if (data[i].zdlx == "002"){
								hdnr1[0]= document.createElement("div");
								hdnr1[0].align="right"
								hdnr1[0].innerHTML=data[i].zdm+"：";
								
								hdnr1[1]= document.createElement("input");
							    hdnr1[1].style.cursor="hand;";
								hdnr1[1].name=data[i].zd;
								hdnr1[1].id=data[i].zd;
								hdnr1[1].onclick=function(){gettime(this.id)};
								hdnr1[1].onblur=function(){dateFormatChg(this)}
								hdnr1[1].readOnly="ture";
							}else{
								hdnr1[0]= document.createElement("div");
								hdnr1[0].align="right"
								hdnr1[0].innerHTML=data[i].zdm+"：";
								
								hdnr1[1]= document.createElement("input");
								hdnr1[1].maxLength="20";
								hdnr1[1].name=data[i].zd;
								hdnr1[1].id=data[i].zd;
							}
						continue;
						}else{
							if (data[i].zdlx == "002"){
							
								hdnr1[2]= document.createElement("div");
								hdnr1[2].align="right"
								hdnr1[2].innerHTML=data[i].zdm+"：";
								
								hdnr1[3]= document.createElement("input");
								hdnr1[3].style.cursor="hand;";
								hdnr1[3].name=data[i].zd;
								hdnr1[3].id=data[i].zd;
								hdnr1[3].onclick=function(){gettime(this.id)};
								hdnr1[3].onblur=function(){dateFormatChg(this)}
								hdnr1[3].readOnly="ture";
							}else{
								hdnr1[2]= document.createElement("div");
								hdnr1[2].align="right"
								hdnr1[2].innerHTML=data[i].zdm+"：";
								
								hdnr1[3]= document.createElement("input");
								hdnr1[3].maxLength="20";
								hdnr1[3].name=data[i].zd;
								hdnr1[3].id=data[i].zd;			
							}
							DWRUtil.addRows("hdnrtext",['dd'],hdnr1);
							hdnr1 = new Array();
						}
						
					}
					
				}
				if(hdnr1.length != 0){
						hdnr1[2]="";
						hdnr1[3]="";
						DWRUtil.addRows("hdnrtext",['dd'],hdnr1);
			    }
			});
			dwr.engine.setAsync(true);
			}
			}
			else{
				alert("目前不在申请时间内，无法申请");
				$("btndiv").style.display='none';
			}
		});
	}
	
	function gettime(id){
		return showCalendar(id,'y-mm-dd');
	}
	
	function onShow(){
		var hddm = $("hddm").value;
		var xh = $("xh").value;
		var xn = $("xn").value;
		var xq = $("xq").value;
		
		dwr.engine.setAsync(false);
		getHdglDAO.getZdyZdz(hddm,xh,xn,xq,function (data){
			var hdnr1 = new Array();
			for(var i=0;i<data.length;i++){
				var zdz="";
				if(data[i].zdz !=""&&data[i].zdz!=null){
					zdz=data[i].zdz;
				}
				if (data[i].zdlx == "003"){
				
					var hdnr2 = new Array();
					
					hdnr2[0]=function(da){
						    return "<div align='right'>"+data[i].zdm+"：</div>";
						    }
						    
					hdnr2[1]=function(da){
						    return "<textarea type='text' style='width:100%' id='"
						    +data[i].zd
						    +"' name='"
						    +data[i].zd
						    +"'rows='5' onblur='chLeng(this,500)'>"
						    +zdz
						    +"</textarea>";	    	
						    }
					
					DWRUtil.addRows("hdnrarea",[''],hdnr2,{escapeHtml:false});
					
				}else{
					if(hdnr1.length == 0){
						if (data[i].zdlx == "002"){
							hdnr1[0]= document.createElement("div");
							hdnr1[0].align="right"
							hdnr1[0].innerHTML=data[i].zdm+"：";
							
							hdnr1[1]= document.createElement("input");
						    hdnr1[1].style.cursor="hand;";
							hdnr1[1].name=data[i].zd;
							hdnr1[1].id=data[i].zd;
							hdnr1[1].onclick=function(){gettime(this.id)};
							hdnr1[1].onblur=function(){dateFormatChg(this)}
							hdnr1[1].value=zdz;
						}else{
							hdnr1[0]= document.createElement("div");
							hdnr1[0].align="right"
							hdnr1[0].innerHTML=data[i].zdm+"：";
							
							hdnr1[1]= document.createElement("input");
							hdnr1[1].maxLength="20";
							hdnr1[1].name=data[i].zd;
							hdnr1[1].id=data[i].zd;
							hdnr1[1].value=zdz;
						}
					continue;
					}else{
						if (data[i].zdlx == "002"){
						
							hdnr1[2]= document.createElement("div");
							hdnr1[2].align="right"
							hdnr1[2].innerHTML=data[i].zdm+"：";
							
							hdnr1[3]= document.createElement("input");
							hdnr1[3].style.cursor="hand;";
							hdnr1[3].name=data[i].zd;
							hdnr1[3].id=data[i].zd;
							hdnr1[3].onclick=function(){gettime(this.id)};
							hdnr1[3].onblur=function(){dateFormatChg(this)}
							hdnr1[3].value=zdz;
						}else{
							hdnr1[2]= document.createElement("div");
							hdnr1[2].align="right"
							hdnr1[2].innerHTML=data[i].zdm+"：";
							
							hdnr1[3]= document.createElement("input");
							hdnr1[3].maxLength="20";
							hdnr1[3].name=data[i].zd;
							hdnr1[3].id=data[i].zd;		
							hdnr1[3].value=zdz;	
						}
						DWRUtil.addRows("hdnrtext",['dd'],hdnr1);
						hdnr1 = new Array();
					}
				}
			}
			if(hdnr1.length != 0){
				DWRUtil.addRows("hdnrtext",['dd'],hdnr1);
			}
		});
		dwr.engine.setAsync(true);
	}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="onShow()">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getHdglDAO.js'></script>
		<html:form action="/zgddShgzHdgl" method="post">
				<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="title" /></a>
				</p>
			</div>
		<logic:notEmpty name="rs1">
				<input type="hidden" id="xqV" name=xqV value="<bean:write name="xqV"/>"/>
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="msg" name="msg" value="${msg}"/>
				<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>活动申请</span></th>
			        </tr>
			    </thead>
		 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          <logic:notEqual name="doType" value="view">
			          <div class="" id="btndiv">
					<button class=""
						onclick="saveHdsq(this);"
						style="width:80px" id="buttonSave">
						提交申请
					</button>
					</div>
				</div>
					</logic:notEqual>
					
					</div>
				</td>
			      </tr>
			    </tfoot>
					<tbody>
						<tr>
							<td align="right" style="width:15%">
								学号
							</td>
							<td align="left" style="width:35%">
								<bean:write name="rs1" property="xh"/>
								<input type="hidden" id="xh" value="<bean:write name="rs1" property="xh"/>"/>
							</td>
							<td align="right" style="width:15%">
								姓名
							</td>
							<td align="left">
								<bean:write name="rs1" property="xm"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								性别
							</td>
							<td align="left">
								<bean:write name="rs1" property="xb"/>
							</td>
							<td align="right">
								民族
							</td>
							<td align="left">
								<bean:write name="rs2" property="mzmc"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />
							</td>
							<td align="left">
								<bean:write name="rs1" property="xymc"/>
							</td>
							<td align="right">
								专业
							</td>
							<td align="left">
								<bean:write name="rs1" property="zymc"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								班级
							</td>
							<td align="left">
								<bean:write name="rs1" property="bjmc"/>
							</td>
							<td align="right">
								活动名称
							</td>
							<td align="left">
								<html:select property="hddm" 
									onchange="chHd()">
									<html:option value=""></html:option>
									<html:options collection="hdList" property="hddm"
										labelProperty="hdmc" />
								</html:select>	
							</td>
						</tr>
						<tr>
							<td align="right">
								学年
							</td>
							<td align="left">
								<html:select property="xn" style="" disabled="true">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>	
							</td>
							<td align="right">
								学期
							</td>
							<td align="left">
								<html:select property="xq" style="width:80px" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>	
							</td>
						</tr>
						<tr>
							<td align="right">
								学生备注
							</td>
							<td align="left" colspan="3">
							<bean:write  name="rs1" property="bz"/>
							</td>
						</tr>			
					</table>
					<table width="100%" class="formlist">
						<tbody width="100%" class="tbstyle" id="hdnrtext">
							<tr>
							<td style="width:15%"></td>
							<td style="width:35%"></td>
							<td style="width:15%"></td>
							<td></td>
							</tr>
						</tbody>
					</table>
					<table width="100%" class="formlist">
						<tbody width="100%" class="tbstyle" id="hdnrarea">
							<tr>
							<td style="width:15%"></td>						
							<td style="width:85%"></td>
							</tr>
						</tbody>
					</table>	
					</tbody>		
				</table>
				</div>
			</logic:notEmpty>
			<logic:present name="result">
				<logic:equal name="result" value="yes">
					<script>
				    alert("申请成功！");
				    dialogArgumentsQueryChick();
					Close();
				    </script>
				</logic:equal>
				<logic:equal name="result" value="no">
					<script>
				    alert("提交失败！");
				    </script>				
				</logic:equal>
			</logic:present>
			<logic:present name="msg">
				<script>
					alert(''+document.getElementById('msg').value);
				</script>
			</logic:present>
		</html:form>
	</body>
</html>
