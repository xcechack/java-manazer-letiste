$(document).ready(function(){
	$('.show_tooltip').tooltip();
	//$('.typeahead').typeahead();
	
	$(".show_edit_flight_dialog").click(function(e){
		e.preventDefault();
		internal_id = $(this).attr("id");
		
		flight_identifier = $("tr.flight[data-internal-id="+internal_id+"] td.flight_identifier").attr("data-flight-identifier");
		$("input[name='edit_flight_identifier']").val(flight_identifier);
		$("input[name='flight_id']").val(internal_id);
		
		dep_day = $("tr.flight[data-internal-id="+internal_id+"] td.departure").attr("data-departure-day");
		dep_month = $("tr.flight[data-internal-id="+internal_id+"] td.departure").attr("data-departure-month");
		dep_year = $("tr.flight[data-internal-id="+internal_id+"] td.departure").attr("data-departure-year");
		dep_hour = $("tr.flight[data-internal-id="+internal_id+"] td.departure").attr("data-departure-hour");
		dep_minute = $("tr.flight[data-internal-id="+internal_id+"] td.departure").attr("data-departure-minute");
		
                arr_day = $("tr.flight[data-internal-id="+internal_id+"] td.arrival").attr("data-arrival-day");
		arr_month = $("tr.flight[data-internal-id="+internal_id+"] td.arrival").attr("data-arrival-month");
		arr_year = $("tr.flight[data-internal-id="+internal_id+"] td.arrival").attr("data-arrival-year");
		arr_hour = $("tr.flight[data-internal-id="+internal_id+"] td.arrival").attr("data-arrival-hour");
		arr_minute = $("tr.flight[data-internal-id="+internal_id+"] td.arrival").attr("data-arrival-minute");
		
                plane = $("tr.flight[data-internal-id="+internal_id+"] td.plane").attr("data-plane-id");
                    
                dep_city = $("tr.flight[data-internal-id="+internal_id+"] td.departure_dest").attr("data-departure-dest-city");
                dep_country = $("tr.flight[data-internal-id="+internal_id+"] td.departure_dest").attr("data-departure-dest-country");
                
                arr_city = $("tr.flight[data-internal-id="+internal_id+"] td.arrival_dest").attr("data-arrival-dest-city");
                arr_country = $("tr.flight[data-internal-id="+internal_id+"] td.arrival_dest").attr("data-arrival-dest-country");
                
                
		$("select[name='edit_dep_day'] option").removeAttr("selected");
		$("select[name='edit_dep_day'] option[value='"+dep_day+"']").attr("selected","selected");
		$("select[name='edit_dep_month'] option").removeAttr("selected");
		$("select[name='edit_dep_month'] option[value='"+dep_month+"']").attr("selected","selected");
		$("select[name='edit_dep_year'] option").removeAttr("selected");
		$("select[name='edit_dep_year'] option[value='"+dep_year+"']").attr("selected","selected");
		$("select[name='edit_dep_hour'] option").removeAttr("selected");
		$("select[name='edit_dep_hour'] option[value='"+dep_hour+"']").attr("selected","selected");
		$("select[name='edit_dep_minute'] option").removeAttr("selected");
		$("select[name='edit_dep_minute'] option[value='"+dep_minute+"']").attr("selected","selected");
		
                $("select[name='edit_arr_day'] option").removeAttr("selected");
		$("select[name='edit_arr_day'] option[value='"+arr_day+"']").attr("selected","selected");
		$("select[name='edit_arr_month'] option").removeAttr("selected");
		$("select[name='edit_arr_month'] option[value='"+arr_month+"']").attr("selected","selected");
		$("select[name='edit_arr_year'] option").removeAttr("selected");
		$("select[name='edit_arr_year'] option[value='"+arr_year+"']").attr("selected","selected");
		$("select[name='edit_arr_hour'] option").removeAttr("selected");
		$("select[name='edit_arr_hour'] option[value='"+arr_hour+"']").attr("selected","selected");
		$("select[name='edit_arr_minute'] option").removeAttr("selected");
		$("select[name='edit_arr_minute'] option[value='"+arr_minute+"']").attr("selected","selected");
		
                $("select[name='edit_plane'] option").removeAttr("selected");
		$("select[name='edit_plane'] option[value='"+plane+"']").attr("selected","selected");
		
                $("input[name='edit_dep_city']").val(dep_city);
		$("input[name='edit_dep_country']").val(dep_country);
                
                $("input[name='edit_arr_city']").val(arr_city);
		$("input[name='edit_arr_country']").val(arr_country);
                
		$('#edit_flight_modal').modal();
			
	});
        $(".show_edit_destination_dialog").click(function(e){
		e.preventDefault();
		internal_id = $(this).attr("data-internal-id");
                
                city = $("tr.destination[data-internal-id="+internal_id+"] td.city").attr("data-dest-city");
                country = $("tr.destination[data-internal-id="+internal_id+"] td.country").attr("data-dest-country");
                
                $("input[name='edit_dest_city']").val(city);
		$("input[name='edit_dest_country']").val(country);
                $("input[name='destination_id']").val(internal_id);
                
		$('#edit_destination_modal').modal();
			
	});
        
        $(".show_edit_plane_dialog").click(function(e){
		e.preventDefault();
		internal_id = $(this).attr("data-internal-id");
                
                producer = $("tr.plane[data-internal-id="+internal_id+"] td.producer").attr("data-producer");
                type = $("tr.plane[data-internal-id="+internal_id+"] td.type").attr("data-type");
                capacity = $("tr.plane[data-internal-id="+internal_id+"] td.seats").attr("data-seats");
                maxStewardess = $("tr.plane[data-internal-id="+internal_id+"] td.stewardess").attr("data-stewardess");
                
                $("input[name='edit_plane_producer']").val(producer);
		$("input[name='edit_plane_type']").val(type);
                $("input[name='edit_plane_stewards']").val(maxStewardess);
                $("input[name='edit_plane_seats']").val(capacity);
                
                $("input[name='plane_id']").val(internal_id);
                
		$('#edit_plane_modal').modal();
			
	});
        
        $(".show_edit_steward_dialog").click(function(e){
		e.preventDefault();
		internal_id = $(this).attr("data-internal-id");
                
                name = $("tr.steward[data-internal-id="+internal_id+"] td.name").attr("data-name");
                surname = $("tr.steward[data-internal-id="+internal_id+"] td.surname").attr("data-surname");
                birthnumber = $("tr.steward[data-internal-id="+internal_id+"] td.birthnumber").attr("data-birthnumber");
                sex = $("tr.steward[data-internal-id="+internal_id+"] td.sex").attr("data-sex");
                
                $("input[name='edit_name']").val(name);
		$("input[name='edit_surname']").val(surname);
		$("input[name='edit_birthnumber']").val(birthnumber);
                $("input[name='steward_id']").val(internal_id);
                $("select[name='edit_sex'] option").removeAttr("selected");
		$("select[name='edit_sex'] option[value='"+sex+"']").attr("selected","selected");
                
		$('#edit_steward_modal').modal();
			
	});
        
        $(".toolboxDelete").click(function(e){
           e.preventDefault();
           var href = $(this).attr("href");
           var this_id = $(this).attr("rel");
           $.ajax({
		type : "get",
		url : href,
		success : function(data){
                    if($.trim(data) == "OK"){
                        $("tr[data-internal-id="+this_id+"]").fadeOut("slow",function(){
                            $(this).remove();
                        });
                    }else{
                        var alertBox = $(".alertOuterContent");
                        $(".alertInnerContent").html(data)
                        alertBox.fadeIn();
                        var body = $("body");
                        body.scrollTop(
                            alertBox.offset().top - body.offset().top + body.scrollTop()
                        );
                    }
		}
            });
        });
	
})
