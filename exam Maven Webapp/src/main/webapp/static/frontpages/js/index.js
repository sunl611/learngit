$(function(){
//	这是关于公告栏的脚本
	var curIndex = 0, //当前index
      listLen = $(".List li").length; //图片总数
     // 定时器自动变换2.5秒每次
  var autoChange = setInterval(function(){ 
    if(curIndex < listLen-1){ 
      curIndex ++; 
    }else{ 
      curIndex = 0;
    }
    //调用变换处理函数
    changeTo(curIndex); 
  },2500);

  //对右下角按钮index进行事件绑定处理等
  $(".indexList").find("li").each(function(item){ 
    $(this).hover(function(){ 
      clearInterval(autoChange);
      changeTo(item);
      curIndex = item;
    },function(){ 
      autoChangeAgain();
    });
  });
  //清除定时器时候的重置定时器--封装
  function autoChangeAgain(){ 
      autoChange = setInterval(function(){ 
      if(curIndex < listLen-1){ 
        curIndex ++;
      }else{ 
        curIndex = 0;
      }
    //调用变换处理函数
      changeTo(curIndex); 
    },2500);
    }
  function changeTo(num){ 
    var goLeft = num * 800;
    $(".List").animate({left: "-" + goLeft + "px"},500);
    $(".indexList").find("li").removeClass("indexOn").eq(num).addClass("indexOn");
  };
  
    
//  关闭
	$(".close_btn").hover(function () {
		$(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) }).on('click', function () {
		$(".modal").fadeOut("fast");
		$("#mask").css({ display: 'none' });
	});
	
	//这是关于弹出窗的脚本
	$("#test").click(function(){
		$(".modal").css("visibility","visible");
	});
	$(".menu_title").toggle(function(){
		$(this).next("div").css("visibility","visible");
		$(this).parent("div").siblings("div").find(".jScrollbar3").css("visibility","hidden");
	},function(){
		$(this).next("div").css("visibility","hidden");
	});
	$(".jScrollbar_mask input").toggle(function(){
		$(this).attr("checked","checked");
	},function(){
		$(this).removeAttr("checked");
	});
	
});


