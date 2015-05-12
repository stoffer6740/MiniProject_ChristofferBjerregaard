/**
 * Created by Christoffer on 11-05-2015.
 */
mini_project.service('CountryService', function ($state) {
    var id;
    var name;
    return {
        getId: function () {
            // Redirects to frontpage, if no ID is saved.
            if(!id && id != 0) {
                $state.go('countries')
            }
           return id;
        },
        setId: function (newId) {
            return id = newId;
        },
        getName: function () {
            return name;
        },
        setName: function (newName) {
            return name = newName;
        }
    }
});