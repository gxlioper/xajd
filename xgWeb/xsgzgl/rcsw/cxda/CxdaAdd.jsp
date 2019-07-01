<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/cxda/js/cxda.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#rownum").keydown(function(event){
                   if(event.keyCode == 13){ 
                         if(addrow() == false){
                            return false;
                         }
                     } 
             }); 
		});
		function addrow(){
			var rownum1=jQuery("#tbody_self tr").length;
			var num = jQuery("#rownum").val();
			if(jQuery("#xn").val() == ""){
				showAlert("学年学期不可为空，请选择！");
			    return false;
			}
			
			if(jQuery("#rownum").val() == ""){
				showAlert("行数不可为空，请输入行数！");
			    return false;
			}
			if(parseInt(rownum1)+ parseInt(num) > 100){
				showAlert("行数累计不能超过100，请确认！");
			    return false;
			}
			for(i=1;i<=num;i++){
				var num2 =rownum1+i;
				var path = '${path}';
				var html = 	"<tr style='height:22px'>"+
				"<td id='row' style='text-align:center'>"+num2+"</td>"+
				"<td>"+"<input type='text' name='xh' readonly='readonly' style='width:120px;float:left'/>"+
				"<button class='btn_01' type='button'  onclick=showDialog('请选择一个学生',800,500,'xsxx_xsgl.do?method=showStudents&goto="+
				 path+"&fzrow="+num2+"');>选择</button>"+
				"</td>" +"<td>"+"</td>"+"<td>"+"</td>"+"<td>"+
				"<input type='text' name='cxfs' onkeyup='checkInput(this)' onblur='checkfs(this)' maxlength='3' style='width:60px;text-align:center'/>"+
				"</td>"+"<td>"+"<textarea  name='bz' onkeyup='' style='width:180px;height:18px;overflow-y:hidden'/>"+
				/*"<input type='text' name='bz'  style='width:180px;' />"+*/
			    "</td>"+
				"<td>"+
			    "<a href='javascript:void(0);' style='width:70px;height:18px;background-color:#8ABEE9;display:block;margin-left:10px;text-align:center;line-height:18px; cursor: pointer;color:#FFFFFF' onclick='del("+num2+")'>删 除</a>"+
				"</td>"+
				"</tr>";
			    jQuery('#tbody_self').append(html);
			}
		  
		}
		function del(i){
			var path = '${path}';
			var delrow = i-1;
			 jQuery("#tbody_self tr:eq("+delrow+")").remove();
			 jQuery("#tbody_self tr").each(function(b){
			    var test = jQuery(this).find("td:eq(0)").text();
				if(test>i){//onkeyup=checkInputLxfx(this)
				    var t= b+1;
				    var xh = jQuery(this).find("td:eq(1) input").val();
				    jQuery(this).find("td:eq(0)").text(t);
				    jQuery(this).find("td:eq(1)").html("<input readonly type='text' name='xh' value='"+ xh+"' style='width:120px;float:left' />"+
						    "<button class='btn_01' type='button'  onclick=showDialog('请选择一个学生',800,500,'xsxx_xsgl.do?method=showStudents&goto="
					 +path+"&fzrow="+t+"');>选择</button>");
				    jQuery(this).find("td:eq(6)").html("<a href='javascript:void(0);' style='width:70px;height:18px;background-color:#8ABEE9;display:block;margin-left:10px;text-align:center;line-height:18px; cursor: pointer;color:#FFFFFF' onclick='del("+(t)+")'>"+"删除"+"</a>");
				}
			 })
		}
		</script>
		<style type = "text/css">
		 .div_tool{background-color:#E8F0FB;height:34px;}
		 .rowdivide{width:100%;height:2px;}
		 .btn_self{width:70px;height:24px;color:#FFFFFF;position:absolute;left:260px;top:5px;background-color:#8ABEE9;border:1px solid #8ABEE9 !important;text-align: center;
                    cursor: pointer;font-family:"宋体";font-size:12px;}
		 .btn_add {
          	display:block;
          	height:34px;
          	width:20px;
          	font-weight:bold;
          	color:#8ABEE9;
          	position:absolute;left:220px;top:0px;
          }
          
          .btn_del{
          	display:block;
          	height:34px;
          	width:20px;
          	font-weight:bold;
            color:#8ABEE9;
          	position:absolute;left:80px;top:0px;
          }
		</style>
	</head>
	<body>
		<html:form action="/rcsw_cxda" method="post" styleId="CxdaForm" onsubmit="return false;">
		  <html:hidden property="id"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>学年</th>
							<td>
								<html:select   property="xn" styleId="xn"  style="width:150px;">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
							<th><font color="red">*</font>学期</th>
							<td>
								<html:select   property="xq" styleId="xq"  style="width:150px;">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
					<div class="main_box">
						 <div class="rowdivide"></div>
					     <div class="div_tool" style="width:100%;overflow-x:auto;overflow-y:hidden;line-height:34px;font-size:13px;position:relative;">
					     	<lable style="font-size:12px;float:left;font-weight:bold" >&nbsp;&nbsp;&nbsp;&nbsp;插入空行</lable>
					     	 <a href="javascript:void(0);" style="text-decoration:none;" class="btn_del"  onclick="delrowclick();return false">－</a>
					     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="rownum" id="rownum" onkeyup="checkInput(this)" onblur="checkrownum();return false" style = "width:100px;height:12px;text-align:center;"/>
					     	<a href="javascript:void(0);" class="btn_add" style="text-decoration:none;" onclick="addrowclick();return false">＋</a>	
					     	&nbsp;&nbsp;<input type="button" onclick="addrow()" class="btn_self" value="确 定"/>
					     </div>
					      <div class="div_table_self" style="height:300px;overflow-y:auto">
					      	<table width="100%" border="0" class="dateline" >
					      		<thead>
					      			<tr>
					      				<th style="width:50px;text-align:center" >序号</th>
					      				<th style="width:200px;text-align:center" ><span class="red">*</span>学号</th>
					      				<th style="width:70px;text-align:center">姓名</th>
					      				<th style="width:120px;text-align:center">班级</th>
					      				<th style="width:70px;text-align:center" ><span class="red">*</span>诚信分数</th>
					      				<th style="width:200px;text-align:center">备注<font color="red">(限500字)</font></th>
					      				<th style="text-align:center">操作</th>
					      			</tr>
					      		</thead>
					      		<tbody id="tbody_self">
					      			
					      		</tbody>
					      	</table>
					      </div>
					</div>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveCxdaAdd();return false;">
										保    存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
	
</html>