$(document).ready(function(){
    jQuery.support.cors = true;
   
    if($.cookie("server_url") == null){
        $.cookie("server_url", "http://localhost:8080/pa165/rest/", { path: '/', expires: 28 });
    }
    var server_url = $.cookie("server_url");
    
    $("#settings_url").val(server_url);
    
    $("#settings_save").live("click",function(e){
       e.preventDefault();
       if($("#settings_url").val().length > 0){
           $.cookie("server_url", null, {path: '/'});
           $.cookie("server_url", $("#settings_url").val(), { path: '/', expires: 28 });
           window.location.reload();
       }else{
           $("#settings_url").addClass("error");
       }
    });
    
    
    
    
    $("#destination_by_id_form").live("submit",function(e){
        e.preventDefault();
        var id = $("#destination_by_id_input").val();
        var patt=/\D/; // NON-DIGIT
        var result=patt.test(id);
        if(result){
            //FOUND NON DIGIT
            $("#destination_by_id_input").addClass("error");
        }else{
            
            $("#destination_by_id_input").removeClass("error");
            $.ajax({
                type: "GET",
                url: server_url+"destination/getid/"+id,
                beforeSend: function(){
                  $("#destination_by_id_table_placeholder").html("Loading...");  
                },
                success: function(data){
                    obj = $.parseJSON(data);
                    $("#destination_by_id_table_placeholder").html("<div rel=\""+obj.id+"\"><br/>ID: "+ obj.id + "<br/>City: "+obj.city+"<br/>Country: "+obj.country+"<br/>Delete: <a href=\"#\" class=\"delete_destination\" rel=\""+obj.id+"\">Delete</a></div>");
                    if(obj.length == 0){
                        $("#destination_by_id_table_placeholder").html("No destination found");
                    }
                },
                error: function(jqXHR, textStatus, errorThrown){
                    $("#destination_by_id_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
                },
                statusCode: {
                    400: function() {
                      $("#destination_by_id_error").html("Destination was not found or there was problem with database.").addClass("show");
                    },
                    404: function(jqXHR, textStatus, errorThrown){
                        $("#destination_by_id_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
                    } 
                }
               
            });
            
        }
    });
    
    $(".delete_destination").live("click",function(e){
        e.preventDefault();
        id = $(this).attr("rel");
        $.ajax({
            type: "DELETE",
            url: server_url+"destination/delete/"+id,
            success: function(data){
                $("div[rel="+id+"]").hide("slow",function(){
                   $("div[rel="+id+"]").remove();
                });
            },
            error: function(jqXHR, textStatus, errorThrown){
                alert("Error while removing: "+textStatus+" : "+errorThrown + ". Destination doesnt exists or is used by some other entity.");
            }
        });
    });
    
    $("#load_all_destinations").live("click",function(e){
       e.preventDefault();
       $.ajax({
            type: "GET",
            url: server_url+"destination/all/",
            beforeSend: function(){
              $("#all_destinations_table_placeholder").html("Loading...");  
            },
            success: function(data){
                arr = $.parseJSON(data);
                $("#all_destinations_table_placeholder").html("");
                $("#all_destinations_error").html("").addClass("hide");
                $.each(arr, function(k,obj){
                    $("#all_destinations_table_placeholder").append("<hr/><br/><div rel=\""+obj.id+"\">ID: "+ obj.id + "<br/>City: "+obj.city+"<br/>Country: "+obj.country+"<br/>Delete: <a href=\"#\" class=\"delete_destination\" rel=\""+obj.id+"\">Delete</a></div>");
                });
                if(arr.length == 0){
                    $("#destination_by_city_placeholder").html("No destination found");
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                $("#all_destinations_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
            },
            statusCode: {
                400: function() {
                  $("#all_destinations_error").html("Destination was not found or there was problem with database.").addClass("show");
                },
                404: function(jqXHR, textStatus, errorThrown){
                    $("#all_destinations_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
                } 
            }

        });
    });
    
    $("#destination_by_city_form").live("submit",function(e){
       e.preventDefault();
       var city = $("#destination_by_city_input").val();
       $.ajax({
            type: "GET",
            url: server_url+"destination/city/"+city,
            beforeSend: function(){
              $("#destination_by_city_placeholder").html("Loading...");  
            },
            success: function(data){
                arr = $.parseJSON(data);
                $("#destination_by_city_placeholder").html("");
                $("#destination_by_city_error").html("").addClass("hide");
                $.each(arr, function(k,obj){
                    $("#destination_by_city_placeholder").append("<hr/><br/><div rel=\""+obj.id+"\">ID: "+ obj.id + "<br/>City: "+obj.city+"<br/>Country: "+obj.country+"<br/>Delete: <a href=\"#\" class=\"delete_destination\" rel=\""+obj.id+"\">Delete</a></div>");
                });
                if(arr.length == 0){
                    $("#destination_by_city_placeholder").html("No destination found");
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                $("#destination_by_city_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
            },
            statusCode: {
                400: function() {
                  $("#destination_by_city_error").html("Destination was not found or there was problem with database.").addClass("show");
                },
                404: function(jqXHR, textStatus, errorThrown){
                    $("#destination_by_city_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
                } 
            }

        });
    });
    
    $("#destination_by_country_form").live("submit",function(e){
       e.preventDefault();
       var country = $("#destination_by_country_input").val();
       $.ajax({
            type: "GET",
            url: server_url+"destination/country/"+country,
            beforeSend: function(){
              $("#destination_by_cuntry_placeholder").html("Loading...");  
            },
            success: function(data){
                arr = $.parseJSON(data);
                $("#destination_by_country_placeholder").html("");
                $("#destination_by_country_error").html("").addClass("hide");
                $.each(arr, function(k,obj){
                    $("#destination_by_country_placeholder").append("<hr/><br/><div rel=\""+obj.id+"\">ID: "+ obj.id + "<br/>City: "+obj.city+"<br/>Country: "+obj.country+"<br/>Delete: <a href=\"#\" class=\"delete_destination\" rel=\""+obj.id+"\">Delete</a></div>");
                });
                if(arr.length == 0){
                    $("#destination_by_country_placeholder").html("No destination found");
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                $("#destination_by_country_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
            },
            statusCode: {
                400: function() {
                  $("#destination_by_country_error").html("Destinations was not found or there was problem with database.").addClass("show");
                },
                404: function(jqXHR, textStatus, errorThrown){
                    $("#destination_by_country_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
                } 
            }

        });
    });
    
    
    
    $("#destination_add_new_form").live("submit",function(e){
        e.preventDefault();
        var city = $("#destination_add_new_city_input").val();
        var country = $("#destination_add_new_country_input").val();
        var newdest = {"city": city, "country": country};
        $.ajax({
            type: "POST",
            url: server_url+"destination/create/",
            contentType: "application/json",
            dataType: "json",
            data: $.toJSON(newdest)
            ,
            beforeSend: function(){
              $("#destination_add_new_error").html("Saving...");  
            },
            success: function(){
                alert("Create succesfull");
                $("#destination_add_new_city_input").val("");
                $("#destination_add_new_country_input").val("");
                $("#destination_add_new_error").html("").addClass("hide"); 
            },
            error: function(jqXHR, textStatus, errorThrown){
                $("#destination_add_new_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
            },
            statusCode: {
                400: function() {
                  $("#destination_add_new_error").html("Destination was not found or there was problem with database.").addClass("show");
                },
                404: function(jqXHR, textStatus, errorThrown){
                    $("#destination_add_new_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
                } 
            }

        });
    })
    
    
    
    
    
    
    $("#destination_edit_load_info").live("click",function(e){
        e.preventDefault();
        var id = $("#destination_edit_id_input").val();
        var patt=/\D/; // NON-DIGIT
        var result=patt.test(id) || (id.length == 0);
        if(result){
            //FOUND NON DIGIT
            $("#destination_edit_id_input").addClass("error");
        }else{
            $("#destination_edit_id_input").removeClass("error");
            $.ajax({
                method: "GET",
                url: server_url+"destination/getid/"+id,
                beforeSend: function(){
                  $("#destination_edit_error").html("Loading...");  
                },
                success: function(data){
                    $("#destination_edit_error").hide().html(""); 
                    obj = $.parseJSON(data);
                     
                    if(obj.length == 0){
                        $("#destination_edit_error").html("No destination found").show();
                    }else{
                         $("#destination_edit_city_input").val(obj.city);
                         $("#destination_edit_country_input").val(obj.country);
                    }
                },
                error: function(jqXHR, textStatus, errorThrown){
                    $("#destination_edit_city_input").val("");
                    $("#destination_edit_country_input").val("");
                    $("#destination_edit_id_input").val("");
                    $("#destination_edit_error").html("Error while loading: "+textStatus+" : "+errorThrown).show();
                },
                statusCode: {
                    400: function() {
                      $("#destination_edit_error").html("Destination was not found or there was problem with database.").show();
                    },
                    404: function(jqXHR, textStatus, errorThrown){
                        $("#destination_edit_error").html("Error while loading: "+textStatus+" : "+errorThrown).show();
                    } 
                }
            });
        }
    });
    
    
    $("#destination_edit_form").live("submit",function(e){
        e.preventDefault();
        var id = $("#destination_edit_id_input").val();
        var city = $("#destination_edit_city_input").val();
        var country = $("#destination_edit_country_input").val();
        var newplane = {"id": id, "city": city, "country": country};
        
        var patt=/\D/; // NON-DIGIT
        var result=patt.test(id);
        if(result){
            //FOUND NON DIGIT
            $("#destination_edit_id_input").addClass("error");
        }else{
            $("#destination_edit_id_input").removeClass("error");
            
            $.ajax({
                type: "POST",
                url: server_url+"destination/update/",
                contentType: "application/json",
                dataType: "json",
                data: $.toJSON(newplane)
                ,
                beforeSend: function(){
                  $("#destination_edit_error").html("Saving...");  
                },
                success: function(){
                    alert("Update succesfull");
                    $("#destination_edit_error").html("").hide();  
                },
                error: function(jqXHR, textStatus, errorThrown){
                    $("#destination_edit_error").html("Error while updating: "+textStatus+" : "+errorThrown).show();
                },
                statusCode: {
                    400: function() {
                      $("#destination_edit_error").html("Destination was not found or there was problem with database.").show();
                    },
                    404: function(jqXHR, textStatus, errorThrown){
                        $("#destination_edit_error").html("Error while updating: "+textStatus+" : "+errorThrown).show();
                    } 
                }


            });
        }
    });
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /************ PLANES : ***************/
    
    
    
   
    $("#plane_by_id_form").live("submit",function(e){
        e.preventDefault();
        var id = $("#plane_by_id_input").val();
        var patt=/\D/; // NON-DIGIT
        var result=patt.test(id);
        if(result){
            //FOUND NON DIGIT
            $("#plane_by_id_input").addClass("error");
        }else{
            $("#plane_by_id_input").removeClass("error");
            $.ajax({
                method: "GET",
                url: server_url+"plane/getid/"+id,
                beforeSend: function(){
                  $("#planes_by_id_table_placeholder").html("Loading...");  
                },
                success: function(data){
                    
                    obj = $.parseJSON(data);
                    $("#planes_by_id_table_placeholder").html("<div rel=\""+obj.id+"\" data-producer=\""+obj.producer+"\" data-type=\""+obj.type+"\" data-seats=\""+obj.numberSeats+"\"><br/>ID: "+ obj.id + "<br/>Producer: "+obj.producer+"<br/>Type: "+obj.type+"<br/>Seats: "+obj.numberSeats+"<br/>Delete: <a href=\"#\" class=\"delete_plane\" rel=\""+obj.id+"\">Delete</a></div><br/></div>");
                    if(obj.length == 0){
                        $("#planes_by_id_table_placeholder").html("No plane found");
                    }
                },
                error: function(jqXHR, textStatus, errorThrown){
                    $("#plane_by_id_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
                },
                statusCode: {
                    400: function() {
                      $("#plane_by_id_error").html("Plane was not found or there was problem with database.").addClass("show");
                    },
                    404: function(jqXHR, textStatus, errorThrown){
                        $("#plane_by_id_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
                    } 
                }
            });
        }
    });
    
    $("#load_all_planes").live("click",function(e){
       e.preventDefault();
       $.ajax({
            type: "GET",
            url: server_url+"plane/all/",
            beforeSend: function(){
              $("#all_planes_table_placeholder").html("Loading...");  
            },
            success: function(data){
                arr = $.parseJSON(data);
                $("#all_planes_table_placeholder").html("");
                $("#all_planes_error").html("").addClass("hide");
                $.each(arr, function(k,obj){
                    $("#all_planes_table_placeholder").append("<div rel=\""+obj.id+"\" data-producer=\""+obj.producer+"\" data-type=\""+obj.type+"\" data-seats=\""+obj.numberSeats+"\"><br/>ID: "+ obj.id + "<br/>Producer: "+obj.producer+"<br/>Type: "+obj.type+"<br/>Seats: "+obj.numberSeats+"<br/>Delete: <a href=\"#\" class=\"delete_plane\" rel=\""+obj.id+"\">Delete</a></div><br/></div>");
                });
                if(arr.length == 0){
                    $("#all_planes_table_placeholder").html("No plane found");
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                $("#all_planes_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
            },
            statusCode: {
                400: function() {
                  $("#all_planes_error").html("Plane was not found or there was problem with database.").addClass("show");
                },
                404: function(jqXHR, textStatus, errorThrown){
                    $("#all_planes_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
                } 
            }

        });
    });
    
    
    
    
    
    
    
    
    
    
    
    $("#planes_by_producer").live("submit",function(e){
       e.preventDefault();
       var param = $("#plane_by_producer_input").val();
       $.ajax({
            type: "GET",
            url: server_url+"plane/producer/"+param,
            beforeSend: function(){
              $("#planes_by_producer_table_placeholder").html("Loading...");  
            },
            success: function(data){
                arr = $.parseJSON(data);
                $("#planes_by_producer_table_placeholder").html("");
                $("#plane_by_producer_error").html("").addClass("hide");
                $.each(arr, function(k,obj){
                    $("#planes_by_producer_table_placeholder").append("<div rel=\""+obj.id+"\" data-producer=\""+obj.producer+"\" data-type=\""+obj.type+"\" data-seats=\""+obj.numberSeats+"\"><br/>ID: "+ obj.id + "<br/>Producer: "+obj.producer+"<br/>Type: "+obj.type+"<br/>Seats: "+obj.numberSeats+"<br/>Delete: <a href=\"#\" class=\"delete_plane\" rel=\""+obj.id+"\">Delete</a></div><br/></div>");
                });
                if(arr.length == 0){
                    $("#planes_by_producer_table_placeholder").html("No plane found");
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                $("#plane_by_producer_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
            },
            statusCode: {
                400: function() {
                  $("#plane_by_producer_error").html("Plane was not found or there was problem with database.").addClass("show");
                },
                404: function(jqXHR, textStatus, errorThrown){
                    $("#plane_by_producer_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
                } 
            }

        });
    });
    
    
    
    
    
    $("#planes_by_type").live("submit",function(e){
       e.preventDefault();
       var param = $("#plane_by_type_input").val();
       $.ajax({
            type: "GET",
            url: server_url+"plane/type/"+param,
            beforeSend: function(){
              $("#planes_by_type_table_placeholder").html("Loading...");  
            },
            success: function(data){
                arr = $.parseJSON(data);
                $("#planes_by_type_table_placeholder").html("");
                $("#plane_by_type_error").html("").addClass("hide");
                $.each(arr, function(k,obj){
                    $("#planes_by_type_table_placeholder").append("<div rel=\""+obj.id+"\" data-producer=\""+obj.producer+"\" data-type=\""+obj.type+"\" data-seats=\""+obj.numberSeats+"\"><br/>ID: "+ obj.id + "<br/>Producer: "+obj.producer+"<br/>Type: "+obj.type+"<br/>Seats: "+obj.numberSeats+"<br/>Delete: <a href=\"#\" class=\"delete_plane\" rel=\""+obj.id+"\">Delete</a></div><br/></div>");
                });
                if(arr.length == 0){
                    $("#planes_by_type_table_placeholder").html("No plane found");
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                $("#plane_by_type_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
            },
            statusCode: {
                400: function() {
                  $("#plane_by_type_error").html("Plane was not found or there was problem with database.").addClass("show");
                },
                404: function(jqXHR, textStatus, errorThrown){
                    $("#plane_by_type_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
                } 
            }

        });
    });
    
    
    
    $("#planes_by_number_of_seats").live("submit",function(e){
       e.preventDefault();
       var param = $("#plane_by_seats_input").val();
       $.ajax({
            type: "GET",
            url: server_url+"plane/seats/"+param,
            beforeSend: function(){
              $("#planes_by_seats_table_placeholder").html("Loading...");  
            },
            success: function(data){
                arr = $.parseJSON(data);
                $("#planes_by_seats_table_placeholder").html("");
                $("#plane_by_seats_error").html("").addClass("hide");
                $.each(arr, function(k,obj){
                    $("#planes_by_seats_table_placeholder").append("<div rel=\""+obj.id+"\" data-producer=\""+obj.producer+"\" data-type=\""+obj.type+"\" data-seats=\""+obj.numberSeats+"\"><br/>ID: "+ obj.id + "<br/>Producer: "+obj.producer+"<br/>Type: "+obj.type+"<br/>Seats: "+obj.numberSeats+"<br/>Delete: <a href=\"#\" class=\"delete_plane\" rel=\""+obj.id+"\">Delete</a></div><br/></div>");
                });
                if(arr.length == 0){
                    $("#planes_by_seats_table_placeholder").html("No plane found");
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                $("#plane_by_seats_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
            },
            statusCode: {
                400: function() {
                  $("#plane_by_seats_error").html("Plane was not found or there was problem with database.").addClass("show");
                },
                404: function(jqXHR, textStatus, errorThrown){
                    $("#plane_by_seats_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
                } 
            }

        });
    });
    
    
    
     $("#planes_by_min_number_seats").live("submit",function(e){
       e.preventDefault();
       var param = $("#plane_by_min_seats_input").val();
       $.ajax({
            type: "GET",
            url: server_url+"plane/greaterseats/"+param,
            beforeSend: function(){
              $("#planes_by_min_seats_table_placeholder").html("Loading...");  
            },
            success: function(data){
                arr = $.parseJSON(data);
                $("#planes_by_min_seats_table_placeholder").html("");
                $("#plane_by_min_seats_error").html("").addClass("hide");
                $.each(arr, function(k,obj){
                    $("#planes_by_min_seats_table_placeholder").append("<div rel=\""+obj.id+"\" data-producer=\""+obj.producer+"\" data-type=\""+obj.type+"\" data-seats=\""+obj.numberSeats+"\"><br/>ID: "+ obj.id + "<br/>Producer: "+obj.producer+"<br/>Type: "+obj.type+"<br/>Seats: "+obj.numberSeats+"<br/>Delete: <a href=\"#\" class=\"delete_plane\" rel=\""+obj.id+"\">Delete</a></div><br/></div>");
                });
                if(arr.length == 0){
                    $("#planes_by_min_seats_table_placeholder").html("No plane found");
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                $("#plane_by_min_seats_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
            },
            statusCode: {
                400: function() {
                  $("#plane_by_min_seats_error").html("Plane was not found or there was problem with database.").addClass("show");
                },
                404: function(jqXHR, textStatus, errorThrown){
                    $("#plane_by_min_seats_error").html("Error while loading: "+textStatus+" : "+errorThrown).addClass("show");
                } 
            }

        });
    });
    
    $("#plane_add_new_form").live("submit",function(e){
        e.preventDefault();
        var producer = $("#plane_add_new_producer_input").val();
        var type = $("#plane_add_new_type_input").val();
        var seats = $("#plane_add_new_seats_input").val();
        var newplane = {"producer": producer, "type": type, "numberSeats": seats};
        
        var patt=/\D/; // NON-DIGIT
        var result=patt.test(seats);
        if(result){
            //FOUND NON DIGIT
            $("#plane_add_new_seats_input").addClass("error");
        }else{
            $("#plane_add_new_seats_input").removeClass("error");
            $.ajax({
                type: "POST",
                url: server_url+"plane/create/",
                contentType: "application/json",
                dataType: "json",
                data: $.toJSON(newplane)
                ,
                beforeSend: function(){
                  $("#plane_add_new_error").html("Saving...");  
                },
                success: function(){
                    alert("Create succesfull");
                   
                    $("#plane_add_new_producer_input").val("");
                    $("#plane_add_new_type_input").val("");
                    $("#plane_add_new_seats_input").val("");
                    $("#plane_add_new_error").html("").addClass("hide");  
                },
                error: function(jqXHR, textStatus, errorThrown){
                    $("#plane_add_new_error").html("Error while creating: "+textStatus+" : "+errorThrown).addClass("show");
                },
                statusCode: {
                    400: function() {
                      $("#plane_add_new_error").html("Plane was not found or there was problem with database.").addClass("show");
                    },
                    404: function(jqXHR, textStatus, errorThrown){
                        $("#plane_add_new_error").html("Error while creating: "+textStatus+" : "+errorThrown).addClass("show");
                    } 
                }


            });
        }
    });
    
    
    
    
    
    $(".delete_plane").live("click",function(e){
        e.preventDefault();
        id = $(this).attr("rel");
        $.ajax({
            type: "DELETE",
            url: server_url+"plane/delete/"+id,
            success: function(data){
                $("div[rel="+id+"]").hide("slow",function(){
                   $("div[rel="+id+"]").remove();
                });
            },
            error: function(jqXHR, textStatus, errorThrown){
                alert("Error while removing: "+textStatus+" : "+errorThrown + ". Plane doesnt exists or is used by some other entity.");
            }
        });
    });
    
    
    
    $("#plane_edit_load_info").live("click",function(e){
        e.preventDefault();
        var id = $("#plane_edit_id_input").val();
        var patt=/\D/; // NON-DIGIT
        var result=patt.test(id) || (id.length == 0);
        if(result){
            //FOUND NON DIGIT
            $("#plane_edit_id_input").addClass("error");
        }else{
            $("#plane_edit_id_input").removeClass("error");
            $.ajax({
                method: "GET",
                url: server_url+"plane/getid/"+id,
                beforeSend: function(){
                  $("#plane_edit_error").html("Loading...");  
                },
                success: function(data){
                    $("#plane_edit_error").hide().html(""); 
                    obj = $.parseJSON(data);
                     
                    if(obj.length == 0){
                        $("#plane_edit_error").html("No plane found").show();
                    }else{
                         $("#plane_edit_producer_input").val(obj.producer);
                         $("#plane_edit_type_input").val(obj.type);
                         $("#plane_edit_seats_input").val(obj.numberSeats);
                    }
                },
                error: function(jqXHR, textStatus, errorThrown){
                    $("#plane_edit_id_input").val("");
                    $("#plane_edit_producer_input").val("");
                    $("#plane_edit_type_input").val("");
                    $("#plane_edit_seats_input").val("");
                    $("#plane_edit_error").html("Error while loading: "+textStatus+" : "+errorThrown).show();
                },
                statusCode: {
                    400: function() {
                      $("#plane_edit_error").html("Plane was not found or there was problem with database.").show();
                    },
                    404: function(jqXHR, textStatus, errorThrown){
                        $("#plane_edit_error").html("Error while loading: "+textStatus+" : "+errorThrown).show();
                    } 
                }
            });
        }
    });
    
    
    $("#plane_edit_form").live("submit",function(e){
        e.preventDefault();
        var id = $("#plane_edit_id_input").val();
        var producer = $("#plane_edit_producer_input").val();
        var type = $("#plane_edit_type_input").val();
        var seats = $("#plane_edit_seats_input").val();
        var newplane = {"id": id, "producer": producer, "type": type, "numberSeats": seats};
        
        var patt=/\D/; // NON-DIGIT
        var result=patt.test(seats) && patt.test(id)
        if(result){
            //FOUND NON DIGIT
            if(patt.test(seats)){
                $("#plane_edit_seats_input").addClass("error");
            }
            if(patt.test(id)){
                $("#plane_edit_id_input").addClass("error");
            }
        }else{
            $("#plane_edit_seats_input").removeClass("error");
            $("#plane_edit_id_input").removeClass("error");
            
            $.ajax({
                type: "POST",
                url: server_url+"plane/update/",
                contentType: "application/json",
                dataType: "json",
                data: $.toJSON(newplane)
                ,
                beforeSend: function(){
                  $("#plane_edit_error").html("Saving...").show();  
                },
                success: function(){
                    alert("Update succesfull");
                    
                    $("#plane_edit_id_input").val("");
                    $("#plane_edit_producer_input").val("");
                    $("#plane_edit_type_input").val("");
                    $("#plane_edit_seats_input").val("");
                    $("#plane_edit_error").html("").hide();  
                },
                error: function(jqXHR, textStatus, errorThrown){
                    $("#plane_edit_error").html("Error while updating: "+textStatus+" : "+errorThrown).show();
                },
                statusCode: {
                    400: function() {
                      $("#plane_edit_error").html("Plane was not found or there was problem with database.").show();
                    },
                    404: function(jqXHR, textStatus, errorThrown){
                        $("#plane_edit_error").html("Error while updating: "+textStatus+" : "+errorThrown).show();
                    } 
                }


            });
        }
    });
    
});
     