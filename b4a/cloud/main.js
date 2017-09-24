Parse.Cloud.define("getStatus", function(request, response) {
    const query = new Parse.Query("delivery");
    // query.equalTo("receiver", request.params.receiver);
    // query.equalTo("receiver", "BrNVyfKYDt")

    query.equalTo("objectId", "ZSscDRYIMl")
         .find()
         .then((results) => {
             if (results.length == 1) {
               var delivery = results[0];
               response.success(results[0].get("status"));
             }else {
               response.success("no data");
             }
          })
         .catch(() => {
            response.error("delivery lookup failed");
          });
});

Parse.Cloud.define("postStatus", function(request, response) {
    const query = new Parse.Query("delivery");
    query.equalTo("objectId", "ZSscDRYIMl")
         .first()
         .then((delivery) => {
           delivery.save({
              status: "STATUS_1_STARTED"
            }).then(function(gameTurnAgain) {
              response.success("no data");
            }, function(error) {
              response.success("error 2");
            });
         })
         .catch(() => {
           response.error("delivery lookup failed");
         })
});
