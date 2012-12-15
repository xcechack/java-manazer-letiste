$(document).ready(function(){

    if($.cookie("server_url") == null){
        $.cookie("server_url", "http://localhost:1080/rest/", { path: '/', expires: 28 });
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
            $.ajax({
                type: "GET",
                url: server_url+"destination/getid/"+id,
                crossDomain: true,
                beforeSend: function(){
                  $("#destination_by_id_table_placeholder").html("Loading...");  
                },
                success: function(data){
                    $("#destination_by_id_table_placeholder").html("Data: <br/>"+ data);  
                },
                error: function(jqXHR, textStatus, errorThrown){
                    $("#destination_by_id_error").html("Error while loading: "+textStatus).addClass("show");
                },
                statusCode: {
                    400: function() {
                      $("#destination_by_id_error").html("Destination was not found or there was problem with database.").addClass("show");
                    },
                    404: function(jqXHR, textStatus, errorThrown){
                        $("#destination_by_id_error").html("Error while loading: "+textStatus).addClass("show");
                    } 
                }
            });
        }
    });
    
    
    $("#plane_by_id_form").live("submit",function(e){
        e.preventDefault();
        var id = $("#plane_by_id_input").val();
        var patt=/\D/; // NON-DIGIT
        var result=patt.test(id);
        if(result){
            //FOUND NON DIGIT
            $("#plane_by_id_input").addClass("error");
        }else{
            $.ajax({
                type: "GET",
                url: server_url+"plane/getid/"+id,
                crossDomain: true,
                beforeSend: function(){
                  $("#plane_by_id_table_placeholder").html("Loading...");  
                },
                success: function(data){
                    $("#plane_by_id_table_placeholder").html("Data: <br/>"+ data);  
                },
                error: function(jqXHR, textStatus, errorThrown){
                    $("#plane_by_id_error").html("Error while loading: "+textStatus).addClass("show");
                },
                statusCode: {
                    400: function() {
                      $("#plane_by_id_error").html("Plane was not found or there was problem with database.").addClass("show");
                    },
                    404: function(jqXHR, textStatus, errorThrown){
                        $("#plane_by_id_error").html("Error while loading: "+textStatus).addClass("show");
                    } 
                }
            });
        }
    });
    
});
     