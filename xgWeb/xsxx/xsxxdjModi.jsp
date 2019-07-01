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
	<script language="javascript" src="js/function.js"></script>	
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		function trAdd(the_tab,type){
		    var len = document.getElementById(the_tab).rows.length+1;
		    var num = $("numAdd").value;
		    count=len;     
			var cellfu =[	
			function(data){
				return count+ "<input type='hidden' style='width:50px'  name='_xuh' value='"+count+"'>";
			},				 
			function(data){
		     var htmltext = "<input  name='saveb_kssj' id='kssj" + count + "' style='width:100px' onclick='return showCalendar(\"kssj" +count+ "\",\"y-mm-dd\");'/>";	  		
				return htmltext;
		    }, 
		    function(data){
		     var htmltext = "<input  name='saveb_jssj' id='jssj" + count + "' style='width:100px' onclick='return showCalendar(\"jssj" +count+ "\",\"y-mm-dd\");'/>";		  		
				return htmltext;
		    },      	
		    function(data){
		        var htmltext = "<input  name='saveb_szdw' id='szdw" + count + "' maxLength='100'/>";	  	      		
		   	    return htmltext;
		    },       
		    function(data){
		 	    var htmltext = "<input  name='saveb_drzw' id='drzw" + count + "'  maxLength='100' style='width:40px'/>";	  		
		   	    return htmltext;
		    },
		    function(data){
		         var htmltext = "<input  name='saveb_zmr' id='zmr" + count + "'  maxLength='25' style='width:40px'/>";	  		
		   	    return htmltext;
		    }    
			];	
			if(type=='add'){
		      DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});		      
		    }else{
		       if(num==""||num==null){	
		           return false;
		       }
		       for(i=count;i<=num;i++){          
		          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});		         
		          count++;
		       }
		       $("numAdd").value = "";
		    }        
		}
		
		function trDel(the_tab){
		    var tabobj = document.getElementById(the_tab);
		    var length = tabobj.rows.length;   
		    if(length==0){
		       return false;
		    }
		    if(confirm("确定要删除第"+(length)+"行？")){       
		         tabobj.deleteRow(tabobj.rows.length-1);                
		    }
		}
		
		function trDelAll(the_tab){
		    var tabobj = document.getElementById(the_tab);
		    var length = tabobj.rows.length;
		    var num = $("numDel").value; 
		    if(length==0){
		       $("numDel").value = "";
		       return false;     
		    }
		    if(num==""||num==null){	
		       return false;
		    }
		    if(num>length){
		      num = length;
		    }
		    if(confirm("确定要删除最后"+num+"行？")){ 
		         for(i=1;i<=num;i++){                     
		            length--;
		         }
		       for(i=1;i<=num;i++){
		          length--;
		          tabobj.deleteRow(tabobj.rows.length-1);
		       }
		    }
		    $("numDel").value = "";
		}
		
		function commitData(){
			//检测学习经历信息是否填写完整
			var tabobj = document.getElementById('flag');
			var rowLen = tabobj != null || tabobj != undefined ? tabobj.rows.length : 0;
			for(var i=1;i<=rowLen;i++){
				if($("kssj" + i) && $("kssj"+i).value != ""){
				    if($("jssj"+i) && $("jssj"+i).value == ""){
						alert("第"+i+"行结束时间不能为空！");
						return false;
					}
					if($("szdw"+i) && $("szdw"+i).value == ""){
						alert("第"+i+"行所在学校 不能为空！");
						return false;
					}
					if($("drzw"+i) && $("drzw"+i).value == ""){
						alert("第"+i+"行担任职务不能为空！");
						return false;
					}
					if($("zmr"+i) && $("zmr"+i).value == ""){
						alert("第"+i+"行证明人不能为空！");
						return false;
					}	
				}
			}
			saveData('xsxxdj.do?method=xsxxdjModi&type=save','xh');
		}
		
		function loadXsxxjl(){
			getStuDetails.getXsxxjl(val('save_xh'),function(data){
				if(data !=null && data.length>0){
					$("numAdd").value=data.length;
					trAdd('flag','madd');  
					for(var i=1;i<=data.length;i++){
						if($("kssj"+i)){
							var _kssj = data[i-1].kssj;
							_kssj = _kssj == null || _kssj=="null" ? "" : _kssj;
							$("kssj"+i).value = _kssj ;
						}
						if($("jssj"+i)){
							var _jssj = data[i-1].jssj;
							_jssj = _jssj == null || _jssj=="null" ? "" : _jssj;
							$("jssj"+i).value = _jssj ;
						}					
						if($("szdw"+i)){
							var _szdw = data[i-1].szdw;
							_szdw = _szdw == null ? "" : _szdw;
							$("szdw"+i).value = _szdw;
						}
						if($("drzw"+i)){
							var _drzw = data[i-1].drzw;
							_drzw = _drzw == null ? "" : _drzw;
							$("drzw"+i).value = _drzw;
						}
						if($("zmr"+i)){
							var _zmr = data[i-1].zmr;
							_zmr = _zmr == null ? "" : _zmr;
							$("zmr"+i).value = _zmr;
						}				
					}
				}
			});
		}
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();loadXsxxjl();">		
		<html:form action="/xsxxdj.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生信息 - 学生信息 - 学生信息登记 - 修改
				</div>
			</div>
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("您输入的学号无效!");
    				</script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xh-xm-xb-xy-nj-zy-bj-xz-sfzh-sjhm" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc-sfzh-sjhm-xz" />
				<fieldset>
					<legend>
						学生信息维护
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td colspan="4" align="center">
									学生信息登记
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left">
								<html:text name='rs' 
								           property="save_xh" 
								           readonly="readonly"
									       styleId="xh"/>								
							</td>
							<td align="right">
								出生地：
							</td>
							<td align="left">
								<html:text name='rs' property="save_csd" styleId="csd" maxlength="50"/>								
							</td>
						</tr>
						<tr>
							<td align="right">
								姓名：
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" disabled="true"/>
							</td>
							<td align="right">
								血型：
							</td>
							<td align="left">
								<html:select property="save_xuex" styleId="xuex" name="rs">
								<html:option value=""></html:option>
								<html:option value="A">A</html:option>
								<html:option value="B">B</html:option>
								<html:option value="O">O</html:option>
								<html:option value="AB">AB</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								性别：
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" disabled="true" />
							</td>
							<td align="right">
								是否是团员：
							</td>
							<td align="left">
								<html:radio property="save_sfsty" value="是" name="rs">是</html:radio>
								<html:radio property="save_sfsty" value="否" name="rs">否</html:radio>
							</td>
						</tr>
						<tr>
							<td align="right">
								年级：
							</td>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" disabled="true" />
							</td>
							<td align="right">
								入团时间：
							</td>
							<td align="left">
								<html:text name='rs' property="save_jrgqtsj" styleId="jrgqtsj"
										   onblur="dateFormatChg(this)" 
									       style="cursor:hand;"
									       onclick="return showCalendar('jrgqtsj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" disabled="true" />
							</td>
							<td align="right">
								推优时间：
							</td>
							<td align="left">
								<html:text name='rs' property="save_tytysj" styleId="tytysj"
										   onblur="dateFormatChg(this)" 
									       style="cursor:hand;"
									       onclick="return showCalendar('tytysj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<td align="right">
								专业：
							</td>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" disabled="true" />
							</td>
							<td align="right">
								转预备党员时间：
							</td>
							<td align="left">
								<html:text name='rs' property="save_zybdysj" styleId="zybdysj"
										   onblur="dateFormatChg(this)" 
									       style="cursor:hand;"
									       onclick="return showCalendar('zybdysj','y-mm-dd');" />
							</td>	
						</tr>
						<tr>
							<td align="right">
								班级：
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" disabled="true" />
							</td>
							<td align="right">
								入学前健康状况：
							</td>
							<td align="left">
								<html:text name='rs' property="save_rxqjkzk" styleId="rxqjkzk" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								学制：
							</td>
							<td align="left">
								<html:text name='rs' property="xz" styleId="xz" disabled="true" />
							</td>
						    <td align="right">
								入学后健康状况：
							</td>
							<td align="left">
								<html:text name='rs' property="save_rxhjkzk" styleId="rxhjkzk" maxlength="50"/>
							</td>	
						</tr>
						<tr>
							<td align="right">
								身份证号：
							</td>
							<td align="left">
								<html:text name='rs' property="sfzh" styleId="sfzh" disabled="true" />
							</td>
							<td align="right">
								以往病史：
							</td>
							<td align="left">
								<html:text name='rs' property="save_ywbs" styleId="ywbs" maxlength="500"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								手机号码：
							</td>
							<td align="left">
								<html:text name='rs' property="sjhm" styleId="sjhm" disabled="true" />
							</td>
							 <td align="right">
								手机短号：
							</td>
							<td align="left">
								<html:text name='rs' property="save_sjdh" styleId="sjdh" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>
							</td>
						</tr>
						<tr>
							<td align="right">
								QQ号码：
							</td>
							<td align="left">
								<html:text name='rs' property="save_qqhm" styleId="qqhm" maxlength="15" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>
							</td>
							 <td align="right">
								宗教信仰：
							</td>
							<td align="left">
								<html:text name='rs' property="save_zjxy" styleId="zjxy" maxlength="50"/>
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								通讯地址：
							</td>
							<td colspan="3">
								<html:text name='rs' property="save_txdz" styleId="txdz" maxlength="50" style="width:100%"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								实习医院：
							</td>
							<td align="left">
								<html:text name='rs' property="save_sxyy" styleId="sxyy" maxlength="150" />
							</td>
							 <td align="right">
								实习住宿地址：
							</td>
							<td align="left">
								<html:text name='rs' property="save_sxzsdz" styleId="sxzsdz" maxlength="300"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								专业技能证书：
							</td>
							<td align="left">
								<html:text name='rs' property="save_zyjnzs" styleId="zyjnzs" maxlength="300" />
							</td>
							 <td align="right">
								户口是否迁入：
							</td>
							<td align="left">
								<html:select name="rs" property="save_hksfqr" styleId="hksfqr">
									<html:option value=""></html:option>
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								奖惩情况：
							</td>
							<td colspan="3">
								<html:text name='rs' property="save_jcqk" styleId="jcqk" maxlength="300" style="width:100%"/>
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								自我介绍：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='save_zwjs' style="width:99%" rows='7'  onblur="chLeng(this,1000)"/>
							</td>
						</tr>
					</table>
					<fieldset>
						<legend>
							<p>
								<!-- 查询得到的数据量显示区域 -->
								<input  value="+" onclick="trAdd('flag','add')" style="width: 20px"/>
								<input  value="-" onclick="trDel('flag')" style="width: 20px"/>
								&nbsp;&nbsp;<font color="blue">主要学习经历</font>&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
								<input type="text" name="numAdd" id="numAdd" style="width: 20px"
									onblur="trAdd('flag','madd')">
								&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
								<input type="text" name="numDel" id="numDel" style="width: 20px"
									onblur="trDelAll('flag')">
								&nbsp;行
							</p>
						</legend>
						<table class="tbstyle" align="center" width="93%" id="tTb">
							<tr>
								<td>
									<div class="mid_box">
										<table align="center" style="width: 100%" id="rsT" class="tbstyle">
											<!-- 打印时第一行不显示- -->
											<thead style="height: 23px">
												<tr>
													<td nowrap="nowrap">
														序号
													</td>										
													<td nowrap="nowrap">
														开始时间
													</td>		
													<td nowrap="nowrap">
														结束时间
													</td>									
													<td nowrap="nowrap">
														所在学校
													</td>
													<td nowrap="nowrap">
														担任职务
													</td>
													<td nowrap="nowrap">
														证明人
													</td>																																									
												</tr>
											</thead>
											<tbody width="100%" class="tbstyle" id="flag">
											
											</tbody>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</fieldset>
					<div class="buttontool" align="center">
						<logic:notEqual value="view" name="type">
						<button type="button" class="button2" onclick="commitData()"
							style="width:80px" id="buttonSave">
							保 存
						</button>						
						&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:notEqual>
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
					</div>
				</fieldset>
		</html:form>
		<logic:present name="result">
			<intput type="hidden" id="message" value="${message}"/>
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</body>
</html>
