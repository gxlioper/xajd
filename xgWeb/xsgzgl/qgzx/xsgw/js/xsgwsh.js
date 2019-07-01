var gridSetting = {
		caption:"学生岗位申请列表",
		pager:"pager",
		url:"qgzx_xsgwsh.do?method=xsgwshCx&type=query",
		colList:[
		   {label:'',name:'sqbh', index: 'sqbh',key:true,hidden:true},
		   {label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
		   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
		   {label:'类别',name:'pycc', index: 'pycc',width:'7%',formatter:function(value,cell){
               if(value == "1"){
                   return "博士生";
               } else if(value == "2"){return "硕士生";} else if(value == "3"){return "本科生"}
               else if(value == "4"){
                   return "专科生"
               } else if(value == "9"){
                   return "其他";
               } else {
                   return value;
               }
           }},
            {label:'行政班级',name:'bjmc', index: 'bjmc',width:'12%'},
            {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'12%'},
		   {label:'岗位名称',name:'gwmc', index: 'gwmc',width:'15%'},
            {label:'用人单位',name:'yrdwmc', index: 'yrdwmc',width:'18%'},
            {label:'申请时间',name:'sqsj', index: 'sqsj',width:'18%'},
		   {label:'审批状态',name:'shzt', index: 'shzt',width:'18%'},
		   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
		   {label:'shid',name:'shid', index: 'shid',hidden:true},
		   {label:'审批流程',name:'splc', index: 'splc',hidden:true}
		],
		params:{shlx:"dsh"},
		sortname: "sqsj",
	 	sortorder: "desc"
	};
	var gridSetting2 = {
		caption:"学生岗位申请列表",
		pager:"pager",
		url:"qgzx_xsgwsh.do?method=xsgwshCx&type=query",
		colList:[
            {label:'',name:'sqbh', index: 'sqbh',key:true,hidden:true},
            {label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
            {label:'姓名',name:'xm', index: 'xm',width:'8%'},
            {label:'类别',name:'pycc', index: 'pycc',width:'7%',formatter:function(value,cell){
                if(value == "1"){
                    return "博士生";
                } else if(value == "2"){return "硕士生";} else if(value == "3"){return "本科生"}
                else if(value == "4"){
                    return "专科生"
                } else if(value == "9"){
                    return "其他";
                } else {
                    return value;
                }
            }},
            {label:'行政班级',name:'bjmc', index: 'bjmc',width:'12%'},
            {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'12%'},
            {label:'岗位名称',name:'gwmc', index: 'gwmc',width:'15%'},
            {label:'用人单位',name:'yrdwmc', index: 'yrdwmc',width:'18%'},
            {label:'申请时间',name:'sqsj', index: 'sqsj',width:'18%'},
            {label:'审批状态',name:'shzt', index: 'shzt',width:'18%'},
            {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
            {label:'shid',name:'shid', index: 'shid',hidden:true},
            {label:'审批流程',name:'splc', index: 'splc',hidden:true}
		  
		],
		params:{shlx:"ysh"},
		sortname: "shsj",
	 	sortorder: "desc"
	};
	
function query(obj,shlx){
	jQuery("#comp_title li").removeClass();
	jQuery(obj).parent().attr("class","ha");
	jQuery("#shlx").val(shlx);
	if(shlx=='ysh'){
		jQuery("#btn_sh").hide();
		jQuery("#btn_qxsh").show();
		jQuery("#dataTable").initGrid(gridSetting2);
	}else{
		jQuery("#btn_sh").show();
		jQuery("#btn_qxsh").hide();
		jQuery("#dataTable").initGrid(gridSetting);
	}
	searchRs();
}

function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='seeInfo(\""+row["sqbh"]+"\",\""+row["xh"]+"\")'>"+cellValue+"</a>";
}

//审核信息查看
function seeInfo(sqbh,xh){
	if(sqbh==""){
		alertInfo("请您选择一条要查看的岗位申请！");
	} else {
		showDialog("学生岗位审核",765,500,"qgzx_xsgwsh.do?method=xsgwSh&type=ck&xh="+xh+"&sqbh="+sqbh,{
			close:function(){
			}
		});
	}
}

function plsh(shzt){
	var ids = jQuery("#dataTable").getSeletIds();
//	var shgws="";
//	var message="";
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要审核的记录！");
	} else {
//		var rows = jQuery("#dataTable").getSeletRow();
//		for(var i=0;i<ids.length;i++){
//			shgws+=rows[i]["gwid"];
//			message+=rows[i]["xh"]+"-"+rows[i]["xm"]+"申请的<font color='red'>["+rows[i]["gwmc"]+"]</font>岗位";
//			if(i+1!=ids.length){
//				shgws+=",";
//				message+=",";
//			}
//		}
//		showDialog("批量审核",600,300,"qgzx_xsgwsh.do?method=plsh&ids="+ids+"&shgws="+shgws+"&message="+message+"&shzt="+shzt);
		showDialog("批量审核",600,300,"qgzx_xsgwsh.do?method=plsh");
	}
}

function savePlsh(shzt,shyj){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var shgws="";
	var bmlbs="";
	var message="";
	for(var i=0;i<ids.length;i++){
		shgws+=rows[i]["gwid"];
		bmlbs+=rows[i]["bmlbdm"];
		message+=rows[i]["xh"]+"-"+rows[i]["xm"]+"申请的<font color='red'>["+rows[i]["gwmc"]+"]</font>岗位";
		if(i+1!=ids.length){
			shgws+=",";
			bmlbs+=",";
			message+=",";
		}
	}
	jQuery.post("qgzx_xsgwsh.do?method=plsh",{ids:ids.toString(),shgws:shgws,bmlbs:bmlbs,shyj:shyj,shzt:shzt,message:message,type:'save'},function(data){
		//testAlert(data);
		var isHaveError=false;
		var isBtg=false;
		var cgts=data[data.length-1]["cgts"];
		var message="<font color='green'>成功审核通过["+cgts+"]条数据!</font></br>";
		for(var i=0;i<data.length-1;i++){
			message+=data[i]["message"];
			message+="</br>";
			if(data[i]["checktype"]=="btg"){
				isBtg=true;
			}
			if(data[i]["checktype"]=="rowerror"){
				isHaveError=true;
			}
		}
		if(!isBtg){
			if(isHaveError){//如果是行验证错误
				message+="不满足条件，请检查！";
			}else{
				message="<font color='red'>"+message+"</font>";
			}
		}
		showAlertDivLayer(message,{},{"clkFun":function(){
			jQuery("#dataTable").reloadGrid();
		}});
	},'json');
}

function go_sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shlx = jQuery("#shlx").val();
	if(shlx=="ysh"){
		showAlertDivLayer("已处理记录不能再次审核");
		return false;
	}else if (rows.length != 1){
		plsh();
	} else {
		showDialog("学生岗位审核",765,530,"qgzx_xsgwsh.do?method=xsgwSh&sqbh="+rows[0]["sqbh"]+"&shid="+rows[0]["shid"]+"&gwid="+rows[0]["gwid"]+"&xh="+rows[0]["xh"]+"&bmlb="+rows[0]["bmlbdm"]+"&tt="+new Date().getTime(),{
			close:function(){
			}
		});
	}
}
/*
 * 撤销
 */
function cxshnew(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要撤销审核的记录！");
	} else {
		splc_cx_new(rows[0]["splc"],rows[0]["shid"]);
	}
}
/*
 * 审批流程撤销
 * shid 审核id
 * splc 审批流程id 
 */
function splc_cx_new(splc,shid){
	//最后一级撤销审核后调用的路径
	var cancelPath = jQuery("#cancelPath").val();
	confirmInfo("您确定要撤销操作吗?",function(ty){
		if(ty=="ok"){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
					jQuery.post("qgzx_xsgwsh.do?method=cxRollBack",{shid:data["shid"]},function(){
						// 判断是否最后一级撤销(1:最后一级撤销成功）
						if("1" == data["cancelFlg"]){
							jQuery.post(cancelPath,{splc:splc,shid:shid},function(result){
								showAlertDivLayer(result["message"],{},{"clkFun":function(){
									jQuery("#dataTable").reloadGrid();
								}});
							},'json');
						}else{
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								jQuery("#dataTable").reloadGrid();
							}});
						}
					});
			},'json');
		}
	});
}
function searchRs(){
	var map = getSuperSearch();
	map["shlx"] = jQuery.trim(jQuery("#shlx").val());
	jQuery("#dataTable").reloadGrid(map);
}
/**
 * 保存审核操作
 * @param shzt
 * @param message
 * @return
 */
function save_sh(){
	var shzt=jQuery("#shjg").val();
	jQuery("#shzt").val(shzt);
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("请填写审核意见！");
		return false;
	}
	if (jQuery("#shyj").val().length>200){
		showAlertDivLayer("审核意见不能超过200字");
		return false;
	}
	if (jQuery("#splc").val() == "" || jQuery("#sqbh").val() == ""||  jQuery("#gwid").val() == ""){
		showAlertDivLayer("系统异常请稍后");
		return false;
	}
	if(shzt=="1"||shzt==1){//如果选择审核通过需要进行验证
		if(!yzgwsh()){
			return false;
		}
	}
	//提交审核
	showConfirmDivLayer("您确定审核该申请吗？",{"okFun":function(){
		var url = "qgzx_xsgwsh.do?method=xsgwSh&type=save&tt="+new Date();
		ajaxSubFormWithFun("demoForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				//if (parent.window){
					refershParent();
				//}
			}});
		});
	}});
	
}
//审核前验证
function yzgwsh(){
	
	var flg = false;
    var a =  jQuery("#gwdm").val();
	jQuery.ajax({
		   type: "POST",
		   url: "qgzx_xsgwsh.do?method=yzgwsh",
		   data: {xh:jQuery("#xh").val(),
			      gwdm:jQuery("#gwdm").val(),
			      splc:jQuery("#splc").val(),
			      bmlb:jQuery("#bmlb").val(),
			      sqbh:jQuery("#sqbh").val()
		   		},
		   dataType: "json",
		   async: false,
		   success: function(data){
			   var message = data["message"];
				if(message!="true"&&message!=""){
					showAlert(message);
				}else{
					flg = true;
				}
		   }
		});
	return flg;
}

//审批流查看
function lcgz(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		alertInfo("请选择一条记录！");
	} else {
		//showWindow("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+ids.toString(),null);
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqbh']+"&splc="+rows[0]['splc']);
	}
}