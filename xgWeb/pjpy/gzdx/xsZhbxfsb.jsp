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
		<base target="_self" />
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDyInfo.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type="text/javascript">
      function add(){
        var len = document.getElementById("tab").rows.length;
        //var num = $("numAdd").value;
        var count=len;  
        cellfu = [
             function(data){
	          return  count;
	         },
             function(data){
              return "<select name='pllb' id='lb" + count + "' onchange='zhbxChange(" + count + ")' ><option value='' ></option><option value='xw' >行为表现</option><option value='tc'>突出表现</option></select>"
	         },
	         function(data){
	          return"<select  name='pldm'  id='dm" + count + "'><option value='' ></option></select>"
	         },
	         function(data){
	         return "<textarea name='plnr' rows=1  cols=80  onpropertychange='this.style.posHeight=this.scrollHeight' id='nr" + count + "' >";
	         },
	         function(data){
	           return "";
	         }
          ];
          DWRUtil.addRows("bxrow",[''],cellfu,{escapeHtml:false});         
          objId = "dm"+count;
		  dwr.engine.setAsync(false);
		    getStuDyInfo.getZhbx("",function(data4){
		      DWRUtil.removeAllOptions(objId);
			  DWRUtil.addOptions(objId,data4,"dm","mc");	
			});
		  dwr.engine.setAsync(true);        
          count++;
      }
      function zhbxChange(str){
         var lb = $("lb"+str).value;
         var dm = "dm"+str;
         objId = dm;
		  dwr.engine.setAsync(false);
		    getStuDyInfo.getZhbx(lb,function(data4){
		      DWRUtil.removeAllOptions(objId);
			  DWRUtil.addOptions(objId,data4,"dm","mc");	
			});
		  dwr.engine.setAsync(true);    
      }
      function delRow(){
        var tabobj = document.getElementById("tab");
    	var length = tabobj.rows.length;   
    	if(length==1){
      		return false;
    	}
   		if(confirm("确定要删除行？\n(从最后行开始)")){      
       		tabobj.deleteRow(tabobj.rows.length-1);
    	}
      }
      function dataSave(){
         var strv=$("strVal").value;
         var xh = $("xh").value;
         var xn = $("xn").value;
         var act  = $("act").value;
         if(act!="view"&&act!="modi"){
             var exist = "false";
             dwr.engine.setAsync(false); 
             getSztzData.getInfoEx("zhszbxfb","xh||xn",xh+xn," fs is not null",function(bol){
                if(bol){
                   exist="true";
                }             
             });
             dwr.engine.setAsync(true); 
             if(exist=="true"){
                alert("该生该学年表现分申报已经相关老师打分，不能再进行申报或修改！");
                return false;
             }
         }
         var len = document.getElementById("bxrow").rows.length;
         var xh ="";
         var lb ="";
         var dm ="";
         var nr ="";
         if($("xh")){
             xh = $("xh").value; 
             if(xh==""){
                alert("学号不能为空！");
                return false;
             }
         } 
         var num = 1; 
         for(var j=1;j<len;j++){
            lb = document.getElementById("bxrow").rows[j].cells[1].getElementsByTagName('select')[0].value;        
            dm = document.getElementById("bxrow").rows[j].cells[2].getElementsByTagName('select')[0].value;  
            nr = document.getElementById("bxrow").rows[j].cells[3].getElementsByTagName('textarea')[0].value;
            
            if(lb==""||lb=='null'){
                alert("第"+num+"行‘表现类别’为空！");
                return false;
            }       
            if(dm==""||dm=='null'){
               alert("第"+num+"行‘表现’为空！");
               return false;     
            }                        
            if(nr.length>1000){
               alert("第"+num+"行‘具体内容’字数过大！(限1000字)");
               return false;
            }  
            num++;                                          
       }
         for(j=1;j<len;j++){
           var lbV1 = document.getElementById("bxrow").rows[j].cells[1].getElementsByTagName('select')[0].value;        
           var dmV1 = document.getElementById("bxrow").rows[j].cells[2].getElementsByTagName('select')[0].value;           
           var lbV2 = "";
           var dmV2 = "";
           for(i=1;i<len;i++){
                 lbV2 = document.getElementById("bxrow").rows[i].cells[1].getElementsByTagName('select')[0].value;        
                 dmV2 = document.getElementById("bxrow").rows[i].cells[2].getElementsByTagName('select')[0].value; 
                 if((lbV1==lbV2)
                         &&(dmV1==dmV2)
                         &&(j!=i)){
                    alert("第"+j+"行‘表现类别’和‘表现’值\n\n与第"+i+"行‘表现类别’和‘表现’值与相同，\n\n不能提交相同值，请核对后再提交！");
                    return false;
                 }
           }
         }
         refreshForm('/xgxt/gzdxPjpy.do?method=xsZhbxfsb&doType=save')
      }
     function reSet(){
         var url="/xgxt/pjpy_gzdx_xsZhbxfsb.do?act=";
         url+=$('act').value;
         url+="&pkValue=";
         url+=$('pkValue').value;
         url+="&xh=";
         url+=$('xh').value;
         url+="&xn=";
         url+=$('xn').value;
         refreshForm(url);
     }
    </script>
	<body >
		<html:form action="/gzdxPjpy" method="post">
			<input type="hidden" id="url" name="url"
				value="/gzdxPjpy.do?method=xsZhbxfsb" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-nj-xymc-zymc-bjmc" />
			<input type="hidden" id="strVal" name="strVal"
				value="<bean:write name="rsVal"/>" />
			<input type="hidden" id="xn" name="xn"
				value="<bean:write name="xn"/>" />	
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act"/>" />	
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue"/>" />			
			<div class="title">
				<div class="title_img" id="title_m">
					${clin }
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr align="center">
						<td height="22" colspan="4">
							综合表现分申报
						</td>
					</tr>
				</thead>
				<tr>
					<td height="22" align="right">
						<font color="red">*</font>学号：
					</td>
					<td height="22" align="left">
						<html:text name="rs" property="xh" styleId="xh" readonly="true"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<logic:notEqual value="student" name="userOnLine" scope="session">
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</logic:notEqual>
					</td>
					<td height="22" align="right">
						学年：
					</td>
					<td height="22" align="left">
						<bean:write name="pjpyGzdxActionForm" property="xn" />
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						姓名：
					</td>
					<td height="22" align="left">
						${rs.xm }
					</td>
					<td height="22" align="right">
						性别：
					</td>
					<td height="22" align="left">
						${rs.xb }
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						年级：
					</td>
					<td height="22" align="left">
						${rs.nj }
					</td>
					<td height="22" align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td height="22" align="left">
						${rs.xymc }
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						专业：
					</td>
					<td height="22" align="left">
						${rs.zymc }
					</td>
					<td height="22" align="right">
						班级：
					</td>
					<td height="22" align="left">
						${rs.bjmc}
					</td>
				</tr>
			</table>
			<fieldset>
				<legend>
					<font color="blue">综合表现维护</font>
					<input  value="+" title="增加行"
						style="height:18px;width:20px" onclick="add()">
					&nbsp;
					<input  value="-" title="删除行"
						style="height:18px;width:20px" onclick="delRow()">
				</legend>
				<table width="100%" class="tbstyle">
					<tbody>
						<tr>
							<td>
								<table width="100%" class="tbstyle" id="tab">
									<tbody width="100%" class="tbstyle" id="bxrow">									
										<tr>
										   <td>
												序号
											</td>
											<td>
												表现类别
											</td>
											<td>
												表现
											</td>
											<td>
												具体内容
											</td>
											<td>
												评分
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</fieldset>
			<br />
			<logic:notEqual value="view" name="act">
			<div class="buttontool" id="button" align="center">
				<button class="button2" onclick="dataSave()"
					style="width:80px" id="buttonSave" >
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="reSet()" style="width:80px"
					id="buttonClose">
					重 填
				</button>
			</div>
			</logic:notEqual>
		</html:form>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
				alert("操作成功！");
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
				alert("操作失败!");
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:equal>
	</body>
<script type="text/javascript">
 window.onload=function loadData(){
           var strVal = document.forms[0].strVal.value;
           if(strVal==""||strVal==null){
             return false;
           }
           var rowRecords = strVal.split("#");
           
           for(i=0;i<rowRecords.length;i++){
              var colRecords = rowRecords[i].split("!!");
              var count=i+1;  
              cellfu = [
                 function(data){
	                return  count;
	             },
                 function(data){
                    return "<select name='pllb' id='lb" + count + "' onchange='zhbxChange(" + count + ")' ><option value='' ></option><option value='xw' >行为表现</option><option value='tc'>突出表现</option></select>"
	             },
	             function(data){
	                return"<select  name='pldm'  id='dm" + count + "'><option value='' ></option></select>"
	             },
	             function(data){
	                return "<textarea name='plnr' rows=1  cols=80  onpropertychange='this.style.posHeight=this.scrollHeight' id='nr" + count + "' >";
	             },
	             function(data){
	                return "<div  id='fs" + count + "'></div>";
	             }
              ];
              DWRUtil.addRows("bxrow",[''],cellfu,{escapeHtml:false});         
              objId = "dm"+count;
		      dwr.engine.setAsync(false);
		      getStuDyInfo.getZhbx(colRecords[0],function(data4){
		         DWRUtil.removeAllOptions(objId);
			     DWRUtil.addOptions(objId,data4,"dm","mc");	
			  });
		      dwr.engine.setAsync(true); 
		      $("lb"+count).value=colRecords[0];
		      $("dm"+count).value=colRecords[1];
		      $("nr"+count).value=colRecords[2]; 
		      $("fs"+count).innerText=colRecords[3];       
              count++;
           }
      }
      </script>
</html>
