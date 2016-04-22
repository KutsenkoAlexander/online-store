angular.module('monolitApp.navigation', [])

    .directive('searchDirective', function(){
        return {
            link: function (scope, element, attr, ctrl) {
                $('#inpt_search_2').autocomplete({
                    source: function (request, response) {
                        $.ajax({
                            url: 'http://localhost:8080/product/search',
                            dataType: 'json',
                            data: {
                                name: request.term
                            },
                            success: function (data) {
                                if(data.length > 0) {
                                    $('#inpt_search_2').css("color", "#222222");
                                    response($.map(data, function (item) {
                                        return {
                                            id: item.idProduct,
                                            value: item.title
                                        }
                                    }));
                                } else {
                                    $('#inpt_search_2').val($('#inpt_search_2').val() + '');
                                    $('.ui-widget-content').css("display","none");
                                }
                            }
                        });
                    },
                    select: function (event, ui) {
                        $(location).attr('href', '#/catalog/products/product/'+ui.item.id);
                        $("#inpt_search_2").val('');
                        return false;
                    },
                    minLength: 2
                });
            }
        }
    });
