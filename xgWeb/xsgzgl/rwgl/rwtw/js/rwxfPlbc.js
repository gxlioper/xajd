/**
	 * ��������
	 * @return
	 */
	function savePlbc(){
		
		if (jQuery.trim(jQuery("#xn").val()) == ""){
			showAlert("����ѡ��ѧ�꣡");
			return false;
		}
		if (jQuery.trim(jQuery("#xfbcsj").val()) == ""){
			showAlert("����ѡ������ѧ�Ѳ���ʱ�䣡");
			return false;
		}
		
		if (jQuery.trim(jQuery("#xfbcje").val()) == ""){
			showAlert("����������ѧ�Ѳ�����");
			return false;
		}
		
		if (parseFloat(jQuery("#xfbcje").val()) > parseFloat(99999)){
			showAlert("����ѧ�Ѳ������ܳ���99999Ԫ��");
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