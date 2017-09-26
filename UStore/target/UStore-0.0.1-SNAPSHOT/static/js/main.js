/*---------------------------------My JavaScipt code----------------------------*/
/*---------------------------------My JavaScipt code----------------------------*/

/*------------------------------------------------------------------------------------------------- */
/*-----------------------------------------------FB Login Start-------------------------------------- */
/*------------------------------------------------------------------------------------------------- */


  window.fbAsyncInit = function() {
    FB.init({
      appId      : '1729622057349051', // Set YOUR APP ID
      //channelUrl : '', // Channel File
      status     : true, // check login status
      cookie     : true, // enable cookies to allow the server to access the session
      xfbml      : true  // parse XFBML
    });
 
    }
 
    function Login()
    { 
    	
        FB.login(function(response) {
           if (response.authResponse) 
           {
                getUserInfo();
        	  
            } else 
            {
             console.log('User cancelled login or did not fully authorize.');
            }
         },{scope: 'email'});
 
    }
 
  function getUserInfo() {
         FB.api('/me', {fields: 'name,email'},function(response) {
          var name=response.name;
          var email=response.email;
          $.ajax({
        	  url:'saveSocialUser',
        	  type:'post',
        	  data:{"name":name,"email":email},
        	  success:function(){
        		 
        		 
        	  },
        	  error:function(){
        		  alert("in social user save error")
        	  }
          })
          
          window.location="signIn?name="+name+"";
          
    }); 
    
    }
   
    
    function fbLogout()
    {
    	alert("logout kartoy");
        FB.logout(function(){document.location.reload();});
    }
 
  // Load the SDK asynchronously
  (function(d){
     var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement('script'); js.id = id; js.async = true;
     js.src = "//connect.facebook.net/en_US/all.js";
     ref.parentNode.insertBefore(js, ref);
   }(document));
 
/*------------------------------------------------------------------------------------------------- */
/*-----------------------------------------------FB Login End-------------------------------------- */
/*------------------------------------------------------------------------------------------------- */


/*-- -------------------------------------------------------------------------------------------- --*/
/*-- ----------------------------------------Google login script--------------------------------- --*/
/*-- -------------------------------------------------------------------------------------------- --*/
  
   
  function Glogout()
  {
      gapi.auth.signOut();
      location.reload();
  }
  function Glogin() 
  {
    var myParams = {
      'clientid' : '687277864450-gv2si5rdj1qls3ldibbqfklf98cb6chm.apps.googleusercontent.com',
      'cookiepolicy' : 'single_host_origin',
      'callback' : 'loginCallback',
      'approvalprompt':'force',
      'scope' : 'https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.profile.emails.read'
    };
    gapi.auth.signIn(myParams);
  }
   
  function loginCallback(result)
  {
      if(result['status']['signed_in'])
      {
          var request = gapi.client.plus.people.get(
          {
              'userId': 'me'
          });
          request.execute(function (resp)
          {
              var email = '';
              if(resp['emails'])
              {
                  for(i = 0; i < resp['emails'].length; i++)
                  {
                      if(resp['emails'][i]['type'] == 'account')
                      {
                          email = resp['emails'][i]['value'];
                      }
                  }
              }
              /*
              var str = "Name:" + resp['displayName'] + "<br>";
              str += "Image:" + resp['image']['url'] + "<br>";
              str += "<img src='" + resp['image']['url'] + "' /><br>";
   
              str += "URL:" + resp['url'] + "<br>";
              str += "Email:" + email + "<br>";
              document.getElementById("profile").innerHTML = str; */
              
          	var name=resp['displayName'];
              var email1=email;
              alert(name+" "+email1);
              $.ajax({
            	  url:'saveSocialUser',
            	  type:'post',
            	  data:{"name":name,"email":email1},
            	  success:function(){
            		 
            		 
            	  },
            	  error:function(){
            		  alert("in social user save error")
            	  }
              })
              
              window.location="signIn?name="+name+"";
          });
   
      }
   
  }
  function onLoadCallback()
  {
      gapi.client.setApiKey('AIzaSyA0ljw13P3K95_2qcYf67LptCHOQGIctV0');
      gapi.client.load('plus', 'v1',function(){});
  }
   
 
        (function() {
         var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
         po.src = 'https://apis.google.com/js/client.js?onload=onLoadCallback';
         var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
       })();

/*------------------------------------------------------------------------------------------------- */
/*-----------------------------------------------Google Login End---------------------------------- */
/*------------------------------------------------------------------------------------------------- */

var arr=[];

function dispItem(id)
    {
    	
    	$("#bodyContent").empty();
    	$("#promoDiv").empty();
    	$("#sliderDiv").empty();
    	$("#itemListTable").empty();
    	$("#catList").empty();
    	
    	
	 	$.ajax({
	 		url:'displayItems',
	 		type:'post',
	 		data:{id:id},
	 		
	 		success:function(itemList){
	 			/*alert("aala success madhe list ghevun");*/
	 			var itemInfo=' ';
	 			$("#itemListTable").empty();
	 			$("#cartList").empty();
	 			$.each(itemList,function(index, item){
	 				
	 				itemInfo="<tr><td><img src='static/img/"+item.imageName+"' onload=isOutOfStock("+item.quantity+") ><p class='"+item.quantity+"' style='color:red;'><span></span></p></td><td>"+item.itemName+"</td></tr><br><tr><td><button onclick=addToCart('"+item.itemId+"') class='bt"+item.quantity+"'>ADD TO CART</button></td></tr><tr><td></td></tr>";
	 				$("#itemListTable").append(itemInfo);
	 			})
	 		},
	 		
	 		error:function(){
	 			alert("dispItem function madhe error");
	 		}
	 	})
    }

function addToCart(id)
	{
		$.ajax({
			url:'addToCart',
			type:'post',
			data:{"id":id},
			success:function(msg){
				alert(msg);
			},
			error:function(){categoryList
				alert("in add to cart error alert");
			}
		})
	}

function removeItemFromCart(id)
	{
		$.ajax({
			url:'removeItemFromCart',
			type:'post',
			data:{"id":id},
			success:function(msg){
				location.reload();
			},
			error:function(){
				alert("in remove fcategoryListrom cart error alert");
			}
		})
	}




function subTotal(id,price)
	{
		var prevSubtotal=parseInt($("#subtotal"+id+"").text());
		var quantity=$("#"+id+"").val();
		var subtotal=quantity*price;
		$("#subtotal"+id+"").text(subtotal);
		estimateTotal(subtotal,prevSubtotal);
		
	}

function estimateTotal(subtotal,prevSubtotal)
	{
		var total=parseInt($("#estimatedTotal").text());
		total=total+subtotal-prevSubtotal;
		$("#estimatedTotal").text(total);
	}
var myMap=new Map();

function checkout()
	{
		var id,quantity;
		$(".quantityValue").each(function(){
			id=$(this).attr("id");
			quantity=parseInt($(this).val());
			myMap[id]=quantity;
		})
		
		var estimatedTotal=$("#estimatedTotal").text();
		myMap['estimatedTotal']=estimatedTotal;
		$.ajax({
			url:"checkOut",
			type:'post',
			data:myMap,
			success:function(response){	
				window.location="orderPage";
			},
			error:function(){
				alert("checkout error")
			}
		})	
	}

function placeOrder(){
	/*var deliveryAddress=$("#deliveryAddress").val();*/
	var deliveryAddress=$('input[name=delAddress]:checked').val();
	alert(deliveryAddress)
	$.ajax({
		url:'placeOrder',
		type:'post',
		data:{"deliveryAddress":deliveryAddress},
		success:function(response){
			alert("order place="+response)
			window.location="orderInvoice";
		},
		error:function()
		{
			alert("in place order error function")
		}
	})
	
}

function addNewAddress()
{
	$("#deliveryAddress").show();
	$("#saveAddress").show();
	$("input[type=radio]").attr('disabled', true);
}

function saveAddress(){
	var deliveryAddress=$("#deliveryAddress").val();
	alert(deliveryAddress)
	$.ajax({
		url:'saveAddress',
		type:'post',
		data:{"deliveryAddress":deliveryAddress},
		success:function(response){
			alert("Address Saved="+response)
			window.location="orderPage";
		
		},
		error:function()
		{
			alert("in save address error function")
		}
	})
}

function removeItem(id)
{
	alert("in remove")
	$.ajax({
		url:'removeItem',
		type:'post',	
		data:{"id":id},
		success:function(){
			alert("item removed");
			window.location="viewRemoveItem";
		},
		error:function(){
			alert("item remove error function")
		}
	})
}

function updateItem(id)
{
	$.ajax({
		url:'updateItem',
		type:'post',
		data:{"id":id},
		success:function(item){
			$("#updateItemId").val(item.itemId);
			$("#updateItemName").val(item.itemName);
			$("#updateCompanyName").val(item.companyName);
			$("#updatePrice").val(item.price);
			$("#updateImageName").val(item.imageName);
			
		},
		error:function(){
			alert("item remove error function")
		}
	})
}

function showMyOrders()
{
	var userId=parseInt($("#user").attr("name"));
	
	$.ajax({
		url:'showMyOrders',
		type:'post',
		data:{"userId":userId},
		success:function(list)
		{
			
			$("#orderlist").empty();
			var disp11='<tr><td><b>Order ID</b></td><td><b>Order Amount</b></td><td></td></tr>';
			$.each(list,function(index,item)
					{             	 			
						disp11+="<tr><td>"+item.orderId+"</td><td>"+item.orderAmount+"</td><td><a onclick='viewOrderDetails("+item.orderId+")'>View Order Details</a></td></tr>";
						
					})
					$("#orderlist").append(disp11);	
		},
		error:function(){
			alert("in showMyOrders error Alert");
			}
	})
}

function viewOrderDetails(orderId)
{
	$.ajax({
		url:'getOrderItems',
		type:'post',
		data:{"orderId":orderId},
		success:function(itemList)
		{
			$("#orderitemlist").empty();
			var disp21='<tr><td><b>Item ID</b></td><td><b>Item Image</b></td><td><b>Item Name</b></td><td><b>Quantity</b></td><td><b>Price</b></td><td><b>Total</b></td></tr>';
			$.each(itemList,function(index,item)
					{             	 			
						disp21+="<tr><td>"+item.orderItemId+"</td><td><img src='static/img/"+item.item.imageName+"'/></td><td>"+item.item.itemName+"</td><td>"+item.quantity+"</td><td>"+item.item.price+"</td><td>"+item.subTotal+"</td></tr>";
					})
			$("#orderitemlist").append(disp21);	
		},
		error:function(){alert("in vieworder items error function")}
	})
}

function isOutOfStock(quantity)
{
	/*"+item.quantity+"*/
	//alert(quantity);
	if(quantity==0)
	$(".0").text("OUT OF STOCK");
	else
	$("."+quantity).text(quantity+" Available");
	/*var elements=document.getElementsByClassName(".0")
	elements.style.color="Red";*/
	//if(quantity==0)
	$(".bt0").prop("disabled",true);
	
}

/*---------------------------------My JavaScipt code Ends----------------------------*/
/*---------------------------------My JavaScipt code Ends----------------------------*/


jQuery(document).ready(function($){ 
	$(".mainmenu-area").sticky({topSpacing:0});
    
	var sum=0;
	$('.subtotal').each(function(){
	    sum += parseFloat($(this).text());
	    
	});
	
	$("#estimatedTotal").text(sum);
	
    $('.product-carousel').owlCarousel({
        loop:true,
        nav:true,
        margin:20,
        responsiveClass:true,
        responsive:{
            0:{
                items:1,
            },
            600:{
                items:3,
            },
            1000:{
                items:5,
            }
        }
    });  
    
    $('.related-products-carousel').owlCarousel({
        loop:true,
        nav:true,
        margin:20,
        responsiveClass:true,
        responsive:{
            0:{
                items:1,
            },
            600:{
                items:2,
            },
            1000:{
                items:2,
            },
            1200:{
                items:3,
            }
        }
    });  
    
    $('.brand-list').owlCarousel({
        loop:true,
        nav:true,
        margin:20, 
        responsiveClass:true,
        responsive:{
            0:{
                items:1,
            },
            600:{
                items:3,
            },
            1000:{
                items:4,
            }
        }
    }); 
    
    
    
    
    
    
    
    
/*----------------------------- My Jquery Code -----------------------------*/
/*----------------------------- My Jquery Code -----------------------------*/   
    
    $("#mycategoryList").hide();
    $("#deliveryAddress").hide();
    $("#saveAddress").hide();
   
    
    $("#category").click(function(){
    	
    	$("#catList").empty();
    	 $.ajax({
             url: "category",
             type: 'POST',
             success: function(categoryList)
             {
                 var disp=' ';
                 $("#mycategoryList").show();
                 $.each(categoryList,function(indexcategoryList,item)
							{               	 			
								disp='<li class="list-group-item active" id="'+item.catId+'">'+item.categoryName+'</li>';
								$("#catList").append(disp);	
								
								$("#"+item.catId+"").click(function()
									{
							    	var	itemValue = item.catId;
							    	
							    	$.ajax({
							    		url: "product",
							    		type: 'post',
							    		data:{name:itemValue},
							    		
							    		success: function(productList)
							    		{
							    			$("#catList").empty();
							    			var disp1=' ';
							    			$.each(productList,function(index,item)
													{               	 			
														disp1='<li class="list-group-item active" id="'+item.productId+'" onclick="dispItem('+item.productId+');">'+item.productName+'</li>';
														$("#catList").append(disp1);	
													})
							    			
							    		},
							    		error: function()
							    		{
							    			alert("something went wrong in product list fetching");
							    		}
							    	});
							    	
							    });
							});
				
             },
             function()
             {
                 alert("something went wrong category list fetching");
             }
         });
    });
    
   
    
    $("#selectedCategory").change(function(){
    	var id = $(this).val();
    	$("#productList1").empty();
    	//alert(id);
    	$.ajax({
    		url:'productSelectList',
    		type:'post',
    		data:{id:id},
    		success:function(productList)
    		{
    			var prodList=' ';
    		
    			$.each(productList,function(index,item)
						{      
    						//alert(item.productName);
    						prodList="<option value='"+item.productId+"'>"+item.productName+"</option>";	
    						$("#productList1").append(prodList);
						})
    			
    		}
    	})
    })
	   
   
    
   
/*-----------------------------MyCode Ends-----------------------------------*/    
/*-----------------------------MyCode Ends-----------------------------------*/   
    
   
    
    
    
    
    
    
    
    // Bootstrap Mobile Menu fix
    $(".navbar-nav li a").click(function(){
        $(".navbar-collapse").removeClass('in');
    });    
    
    // jQuery Scroll effect
    $('.navbar-nav li a, .scroll-to-up').bind('click', function(event) {
        var $anchor = $(this);
        var headerH = $('.header-area').outerHeight();
        $('html, body').stop().animate({
            scrollTop : $($anchor.attr('href')).offset().top - headerH + "px"
        }, 1200, 'easeInOutExpo');

        event.preventDefault();
    });    
    
    // Bootstrap ScrollPSY
    $('body').scrollspy({ 
        target: '.navbar-collapse',
        offset: 95
    })      
});

