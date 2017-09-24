Parse.Cloud.define("status", function(request, response) {
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
