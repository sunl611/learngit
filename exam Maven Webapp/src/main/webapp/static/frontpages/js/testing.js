$(function () {
    $('#demoModal').modal('show');
    var ques = document.getElementsByClassName("ques").length;
    var mins = 10;
    var str = "本次考试共有" + ques + "道小题。答题时间为" + mins + "分钟。确定开始答卷吗？"
    $("#doyouready").html(str);
    // if(!(confirm(str))){
    //     window.history.back(-1); 
    // }
    /*计时*/
    var intDiff = parseInt( turnToSeconds(mins));//倒计时总秒数量
    var leftime = true;
    function timer(intDiff){
    	window.setInterval(function(){
    	var day=0,
    		hour=0,
    		minute=0,
    		second=0;//时间默认值		
    	if(intDiff > 0){
    		day = Math.floor(intDiff / (60 * 60 * 24));
    		hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
    		minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
    		second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
    	}else{
    		leftime = false;
            /*时间到自动挡提交试卷*/
            if(!leftime){
                // $("#button").attr("onclick","");
                // alert("时间到！系统将自动提交试卷！")
                // $("#button").click();
                $("#ModalTimeOut").modal('show');
                leftime = "hasDone";
            }
    	}
    	if (minute <= 9) minute = '0' + minute;
    	if (second <= 9) second = '0' + second;
    	$('#hour_show').html('<s id="h"></s>'+hour+'时');
    	$('#minute_show').html('<s></s>'+minute+'分');
    	$('#second_show').html('<s></s>'+second+'秒');
    	intDiff--;
    	}, 1000);
    } 
    function turnToSeconds(mins){
    	return mins * 60;
    }
    /*开始答卷后触发*/
    $("#start").on('click',function(){
        timer(intDiff);
    });

    /*单选题*/
    $(".choice").on('click',checkChoice);
    function checkChoice(){
        var inputs = this.getElementsByTagName("input");
        var buttons = document.getElementsByClassName("buttons");
        var index = inputs[0].getAttribute("name");
        for(var i=0;i<inputs.length;i++){
            if(inputs[i].checked){
                buttons[index-1].setAttribute("class","buttons already");
            }
         }
        queDoneNumber();
    }
    /*多选题*/
    $(".multi-choice").on('click',checkMulitchoice);
    function checkMulitchoice(){
        var inputs = this.getElementsByTagName("input");
        var index = inputs[0].getAttribute("name");
        var buttons = document.getElementsByClassName("buttons");
        var isChecked = 0;
         for(var i=0;i<inputs.length;i++){
            if(inputs[i].checked){
                buttons[index-1].setAttribute("class","buttons already");
                break;
            }else{
                buttons[index-1].setAttribute("class","buttons");
             }
          }
         queDoneNumber();
    }
    /*简答题*/
    $(".shortanswer").on('keyup',checkShortanswer);
    function checkShortanswer(){
        var texts = this.getElementsByTagName("input");
        var index = texts[0].getAttribute("name");
        var buttons = document.getElementsByClassName("buttons");
        if(texts[0].value.length){
            buttons[index-1].setAttribute("class","buttons already");
        }else{
            buttons[index-1].setAttribute("class","buttons");
        };
        queDoneNumber();
    }
    /*已完成的题目数量及提交按钮*/
    var queTotal = document.getElementsByClassName("ques").length;
    $("#que-total").html(queTotal);
    function queDoneNumber(){
        var count = 0; 
        var buttons = document.getElementsByClassName("buttons");
        for(var k=0;k<buttons.length;k++){
            if(buttons[k].getAttribute("class") == "buttons already"){
                ++count;
            }
        }
        $("#que-already").html(count);

        var queLeft = buttons.length - count;
        var str1 = "已完成" + count + "道题。还有" + queLeft + "道题未完成。确认要交卷吗？" ;
        $("#whensubmit").html(str1);
    }
    queDoneNumber();
    /*为每道题div增加id属性*/
    function addId(){
        var ques = document.getElementsByClassName("ques");
        for(var i=0;i < ques.length;i++){
            var index = ques[i].getAttribute("index");
            var id = "que" + index;
            ques[i].setAttribute("id",id);
        }
    }
    addId();

    /*为每个按钮增加导航*/
    function addHref(){
        var buttons = document.getElementsByClassName("buttons");
        for(var i=0;i < buttons.length;i++){
            var index = buttons[i].getAttribute("index");
            var href = "#que" + index;
            buttons[i].setAttribute("href",href);
        }
    }
    addHref();
    // $(".buttons").click(function(){
    //     var h1 = $(".ques").eq(0).offset().top;
    //     var index=$(this).index();
    //     var hx=$(".ques").eq(index-2).offset().top;
    //     var move;
    //     if(h1>0&&hx>0){
    //         move=hx-h1;
    //     }else if(h1<0&&hx>0){
    //         move=hx-h1;
    //     }else if(h1<0&&hx<0){
    //         move=Math.abs(h1)-Math.abs(hx);
    //     }
    //     $("#left-maincontent").scrollTop(move);
    //     console.log(h1);
    //     console.log(hx);
    // });
    
    /*单选、多选后添加样式*/
    $(":radio").on("click",function(){
        $(this).parent("label").addClass("selected")
               .siblings().removeClass("selected");

    });
    $(":checkbox").on("click",function(){
        var hascheck = $(this).is(":checked");
        if(!(hascheck)){
             $(this).parent("label").removeClass("selected");
        }else{
            $(this).parent("label").addClass("selected");
        }
    });

    /*调整高度*/
    var winHeight = $(document).height();
    var leftHeight = winHeight - 226;
    $("#left-maincontent").height(leftHeight);
    $("#right-bottom").height(leftHeight);
    $("#button").css("top",winHeight-60);
    /*调整宽度*/
    var winWidth = $(document).width();
    var leftWidth = winWidth * 0.6;
    $("#sign").css("margin-left",leftWidth);
});

// $(function(){
//     console.log($(".buttons").eq(0));
// })