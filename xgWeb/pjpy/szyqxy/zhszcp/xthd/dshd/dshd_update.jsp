<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getSzPjpyDAO.js'></script>
		<script language="javascript" src="js/sztzFunction.js"></script>

		<script type="text/javascript">
var count = 1;
 function trAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    count=len;     
	var cellfu =[
	function(data){
	return count+ "<input type='hidden' style='width:80px'  name='xuh' value='xuh'>";
	},
	function(data){	    
	return "<input type='text' style='width:120px'  name='dsmc' id='dsmc" + count + "' maxlength='20''>";
    },	
    function(data){	    
    	return "<input type='text' style='width:80px' name='dsrq' id='dsrq" + count + "' maxlength='20' readonly='true' onblur='dateFormatChg(this)' style='cursor:hand;' onclick='time(this.id)'>";
        },
        function(data){
        return "<textarea rows='3' cols='32' id='dsxd" + count + "' name='dsxd'></textarea>";
        },
        function(data){    
    	return "<select style='width:60px' id='sfhj" + count + "' name='sfhj'><option value='��'>��</option><option value='��'>��</option></select>";	 
        },
    function(data){    
    	return "<select style='width:60px' id='jjf" + count + "' name='jjf'><option value='�ӷ�'>�ӷ�</option><option value='����'>����</option></select>";	 
    },
    function(data){    
    	return "<input type='text' style='width:60px' id='pf" + count + "' name='pf' onkeypress='return sztzNumInputValue(this,6,event)' onblur='onlyNumInput(this)' maxlength='200'>";	 
     },
     function(data){    
     	return "<input type='text' style='width:60px' id='xxsh" + count + "' readonly='true' name='xxsh' maxlength='10' value='δ���'>";	 
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
function time(id){
	return showCalendar(id,'y-mm-dd');
}
function trDel(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    if(confirm("ȷ��Ҫɾ����"+(length)+"�У�")){      
    	if($("xxsh"+length).value!="δ���"){
            alert("�ü�¼�Ѿ�����ز�����ˣ����ܽ���ɾ��������");   
            return false;
          }else{
            tabobj.deleteRow(tabobj.rows.length-1);    
          }       
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
    if(confirm("ȷ��Ҫɾ�����"+num+"�У�")){
      	 for(i=1;i<=num;i++){           
               if($("xxsh"+length).value!="δ���"){
                 alert("���Ѿ�����ز�����˵ļ�¼�����ܽ���ɾ��������");   
                 return false;
               }
               length--;
            } 
         for(i=1;i<=num;i++){
            length--;
            tabobj.deleteRow(tabobj.rows.length-1);
         }
      }
    $("numDel").value = "";
}	
function fillValue(obj){
		var xm= "xm" + obj.id.replace("xh","");
		var bj= "bj" + obj.id.replace("xh","");
  		if(obj.value==""){
  		  return false;
  		}	
		getSztzData.getXsInfo(obj.value,function(data){
		if(data!=null){
			$(xm).value=data[0];
			$(bj).value=data[1];
		}else{
			alert("��ѧ�Ų����ڣ���������ȷѧ�ţ�");
			obj.value="";
			obj.focus();
			}
		});

}
function ztbhSave(the_tab){
   var tabobj = document.getElementById(the_tab);
    var rowLen = tabobj.rows.length;
    var xh ="";
    var xm ="";
    var bj = "";
    var bhzt ="";
    var bhnr = "";
    var bhbx = "";
    var fs = "";
    var bz = "";
    var xn = "";
    var xq = "";
    if($("xh")){
        xh = $("xh").value; 
        if(xh==""){
            alert("��ȷ���������ѧ����");
            return false;
        }
     }
    if($("xn")){
       xn = $("xn").value; 
       if(xn==""){
           alert("ѧ�겻��Ϊ�գ�");
           return false;
       }
    }
    if($("xq")){
       xq = $("xq").value;
       if(xq==""){
           alert("ѧ�ڲ���Ϊ�գ�");
           return false;
       }
    }
    var pkValue = xn+xq+xh;
    var nullCout = 0;
    if(rowLen == 0){ 
		alert("�����������Ϊ�գ���");
		return false;
	}
    for(var i=1;i<=rowLen;i++){
 	   if($("dsmc"+i)){
			if($("dsmc"+i).value == ""){
				alert("��"+i+"��,��������Ϊ�գ���ȷ��");
				return false;
				}
 		}
			if($("dsrq"+i)){
    			if($("dsrq"+i).value == ""){
    				alert("��"+i+"��,���ڲ���Ϊ�գ���ȷ��");
    				return false;
    			}
			}
			if($("dsxd"+i)){
    			if($("dsxd"+i).value == ""){
    				alert("��"+i+"��,�����ĵò���Ϊ�գ���ȷ��");
    				return false;
    			}
    			if($("dsxd"+i).value.length>150){
    				alert("��"+i+"��,�����ĵ���������ֻ����150������ȷ��");
    				return false;
    			}
			}
 }
        //alert(rowLen);
        //return false;
        refreshForm('/xgxt/pjpyszyqwh.do?method=szyc_dshdUpdate&doType=update');
         $("buttonSave").disabled=true;
}

function onShow(){
	var xh = $("xh").value;
	var xn = $("xn").value;
	var xq = $("xq").value;
	if(xh != ""){
		dwr.engine.setAsync(false);
		getSzPjpyDAO.dao_getXsXthdDwr(xh,xn,xq,"dshd",function(data){
			if(data !=null && data.length>0){
				$("numAdd").value=data.length;
				trAdd('flag','madd');    
				for(var i=1;i<=data.length;i++){
					if($("dsmc"+i)){
						$("dsmc"+i).value = data[i-1].dsmc;
					}
					if($("dsrq"+i)){
						$("dsrq"+i).value = data[i-1].dsrq;
					}
					if($("dsxd"+i)){
						$("dsxd"+i).value = data[i-1].dsxd;
					}
					if($("sfhj"+i)){
						$("sfhj"+i).value = data[i-1].sfhj;
					}
					if($("jjf"+i)){
						$("jjf"+i).value = data[i-1].jjf;
					}
					if($("pf"+i)){
						$("pf"+i).value = data[i-1].pf;
					}
					if($("xxsh"+i)){
						$("xxsh"+i).value = data[i-1].xxsh;
					}
				}
			}		
		});
		dwr.engine.setAsync(true);
	}
}

		
jQuery(function(){
	onShow();
})
	</script>
	</head>
	<body >
		<html:form action="/pjpyszyqwh">
			<input id="url" name="url" type="hidden"
				value="/pjpy/szyqxy/zhszcp/zznl_Add.jsp" />
			<input id="getStuInfo" name="getStuInfo" type="hidden" value="xh" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�����޸�</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:notEqual name="doType" value="view">
										<button type="button" name="�ύ" onclick="ztbhSave('flag');">
											����
										</button>
									</logic:notEqual>
									<button type="button" name="�ر�" onclick="window.close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								<logic:notEqual name="userType" value="stu" scope="session">
									<html:text name='rs' property="xh" styleId="xh" readonly="true" />
								</logic:notEqual>
								<logic:equal name="userType" value="stu" scope="session">
									<html:text name='rs' property="xh" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this)"
										readonly="true" />
								</logic:equal>
							</td>
							<th>
								����
							</th>
							<td>
								<html:text name='rs' property="xm" styleId="xm" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								<html:text name='rs' property="xn" styleId="xn" readonly="true" />
							</td>
							<th>
								ѧ��
							</th>
							<td>
								<html:text name='rs' property="xq" styleId="xq" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />

							</th>
							<td>
								<html:text name='rs' property="xymc" styleId="xymc"
									readonly="true" />
							</td>
							<th>
								רҵ
							</th>
							<td>
								<html:text name='rs' property="zymc" styleId="zymc"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td>
								<html:text name='rs' property="bjmc" styleId="bjmc"
									readonly="true" />
							</td>
							<th>
							</th>
							<td align="left">
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<!-- ��ѯ�õ�����������ʾ���� -->
								<input  value="+" onclick="trAdd('flag','add')" />
								<input  value="-" onclick="trDel('flag')" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
								<input type="text" name="numAdd" id="numAdd" style="width: 50px"
									onblur="trAdd('flag','madd')" />
								&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
								<input type="text" name="numDel" id="numDel" style="width: 50px"
									onblur="trDelAll('flag')" />
								&nbsp;��
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<table align="center" style="width: 100%" id="rsT"
									class="tbstyle">
									<!-- ��ӡʱ��һ�в���ʾ- -->
									<thead style="height: 23px">
										<tr>
											<td nowrap="nowrap">
												���
											</td>
											<td nowrap="nowrap">
												����
											</td>
											<td nowrap="nowrap">
												����
											</td>
											<td nowrap="nowrap">
												�����ĵ�
												<font color="red">(��300����)</font>
											</td>
											<td nowrap="nowrap">
												�Ƿ��
											</td>
											<td nowrap="nowrap">
												�Ӽ���
											</td>
											<td nowrap="nowrap">
												����
											</td>
											<td nowrap="nowrap">
												ѧУ���
											</td>
										</tr>
									</thead>
									<tbody width="100%" class="tbstyle" id="flag">
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
				<logic:present name="done">
					<logic:equal value="yes" name="done">
						<script>
				alert('�����ɹ���');
				</script>
					</logic:equal>
					<logic:equal value="no" name="done">
						<script>
				alert('����ʧ�ܣ�');
				</script>
					</logic:equal>
				</logic:present>
		</html:form>
	</body>
</html>
