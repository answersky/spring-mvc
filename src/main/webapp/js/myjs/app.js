var app = angular.module('plunker', []);

app.controller('MainCtrl', function($scope) {
    $scope.name = 'World';
    $scope.employees =[{id:101, name:'John', phone:'555-1276'},
        {id:102, name:'Mary', phone:'800-1233'},
        {id:103,name:'Mike', phone:'555-4321'},
        {id:104,name:'Adam', phone:'555-5678'},
        {id:105,name:'Julie', phone:'555-8765'},
        {id:106,name:'Juliette', phone:'555-5678'}];
    $scope.showEdit = true;
    $scope.master = {};
});


app.directive("edit",function($document){
    return{
        restrict: 'AE',
        require: 'ngModel',
        link: function(scope,element,attrs,ngModel){
            element.bind("click",function(){
                var id = "txt_name_" +ngModel.$modelValue.id;
                scope.$apply(function(){
                    angular.copy(ngModel.$modelValue,scope.master);
                    //console.log(scope.master);
                });
                //console.log(id);
                var obj = $("#"+id);
                obj.removeClass("inactive");
                obj.addClass("active");
                obj.removeAttr("readOnly");
                scope.$apply(function(){
                    scope.showEdit = false;
                })
            });
        }
    }
});



app.directive("update",function($document){
    return{
        restrict: 'AE',
        require: 'ngModel',
        link: function(scope,element,attrs,ngModel){
            element.bind("click",function(){
                alert(ngModel.$modelValue + " is updated, Update your value here.");
                var id = "txt_name_" +ngModel.$modelValue.id;
                var obj = $("#"+id);
                obj.removeClass("active");
                obj.addClass("inactive");
                obj.attr("readOnly",true);
                scope.$apply(function(){
                    scope.showEdit = true;
                })
            })
        }
    }
});



app.directive("cancel",function($document){
    return{
        restrict: 'AE',
        require: 'ngModel',
        link: function(scope,element,attrs,ngModel){
            element.bind("click",function(){
                scope.$apply(function(){
                    angular.copy(scope.master,ngModel.$modelValue);
                    //console.log(ngModel.$modelValue);
                });

                var id = "txt_name_" +ngModel.$modelValue.id;
                var obj = $("#"+id);
                obj.removeClass("active");
                obj.addClass("inactive");
                obj.prop("readOnly",true);
                scope.$apply(function(){
                    scope.showEdit = true;
                })
            })
        }
    }
});



app.directive("delete",function($document){
    return{
        restrict:'AE',
        require: 'ngModel',
        link:function(scope, element, attrs,ngModel){
            element.bind("click",function(){
                var id = ngModel.$modelValue.id;
                alert("delete item where employee id:=" + id);
                scope.$apply(function(){
                    for(var i=0; i<scope.employees.length; i++){
                        if(scope.employees[i].id==id){
                            console.log(scope.employees[i]);
                            scope.employees.splice(i,1);
                        }
                    }
                    console.log(scope.employees);
                })
            })
        }
    }
});