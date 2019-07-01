/**
	 * 批量保存
	 * @return
	 */
	function savePlbc(){
		
		if (jQuery.trim(jQuery("#xn").val()) == ""){
			showAlert("请先选择学年！");
			return false;
		}
		if (jQuery.trim(jQuery("#xfbcsj").val()) == ""){
			showAlert("请先选择入伍学费补偿时间！");
			return false;
		}
		
		if (jQuery.trim(jQuery("#xfbcje").val()) == ""){
			showAlert("请输入入伍学费补偿金额！");
			return false;
		}
		
		if (parseFloat(jQuery("#xfbcje").val()) > parseFloat(99999)){
			showAlert("入伍学费补偿金额不能超过99999元！");
			return false;
		}
		
		var url="rwgl_rwxfbcgl.do?method=savePlbc";
		ajaxSubFormWithFun("rwxfbcglForm",url,function(data){
	    	  
	    	  if (data["success"] == "false"){
	    		  showAlert(data["message"]);
	    	  } else {
	    		  showAlert(data["message"],{},{"clkFun":function(){
	        			if (parent.window){
	     				 refershParent();
	        			}
	      		  }});
	    	  }
	    	  
	      });
		/*
		jQuery.post(
			"rwgl_rwxfbcgl.do?method=savePlbc",
			{xh:jQuery("#xh").val(),
			 xn:jQuery("#xn").val(),
			 xfbcsj:jQuery("#xfbcsj").val(),
			 xfbcje:jQuery("#xfbcje").val(),
			 bz:jQuery("#bz").val(),
			 guid:jQuery("#guid").val()
			},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
				refershParent();
			},
			'json'
		);
		*/
	}