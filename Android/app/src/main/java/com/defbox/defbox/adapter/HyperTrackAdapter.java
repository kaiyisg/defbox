package com.defbox.defbox.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.defbox.defbox.util.DialogBuilder;
import com.hypertrack.lib.HyperTrack;
import com.hypertrack.lib.callbacks.HyperTrackCallback;
import com.hypertrack.lib.models.ErrorResponse;
import com.hypertrack.lib.models.SuccessResponse;
import com.hypertrack.lib.models.User;

/**
 * Created by kaiyi.lee on 9/24/17.
 */

public final class HyperTrackAdapter {

    private static Activity context;

    public static void initialize(Activity con) {
        context = con;
        HyperTrack.initialize(context, "pk_d0b6d3f0ed4a7e83690f54237a06867163f6d8bf");
    }

    /**
     * Call this method to check Location Settings before proceeding for User
     * Login
     */
    public static void checkForLocationSettings() {
        // Check for Location permission
        if (!HyperTrack.checkLocationPermission(context)) {
            HyperTrack.requestPermissions(context);
            return;
        }

        // Check for Location settings
        if (!HyperTrack.checkLocationServices(context)) {
            HyperTrack.requestLocationServices(context);
        }

        // Location Permissions and Settings have been enabled
        // Proceed with your app logic here i.e User Login in this case
        userLogin();
    }

    private static void userLogin() {
        /**
         * Get or Create a User for given lookupId on HyperTrack Server here to
         * login your user & configure HyperTrack SDK with this generated
         * HyperTrack UserId.
         * OR
         * Implement your API call for User Login and get back a HyperTrack
         * UserId from your API Server to be configured in the HyperTrack SDK.
         */
        HyperTrack.getOrCreateUser("kaiyi", "6504954534", "1",
                new HyperTrackCallback() {
                    @Override
                    public void onSuccess(@NonNull SuccessResponse successResponse) {
                        User user = (User) successResponse.getResponseObject();
                        // Handle createUser success here, if required
                        // HyperTrack SDK auto-configures UserId on createUser API call,
                        // so no need to call HyperTrack.setUserId() API

                        // On UserLogin success
                        onUserLoginSuccess();
                    }

                    @Override
                    public void onError(@NonNull ErrorResponse errorResponse) {
                        DialogBuilder.showToast(context, "there was an error getting user: "+errorResponse.getErrorMessage());
                    }
                });
    }

    /**
     * Call this method when user has successfully logged in
     */
    private static void onUserLoginSuccess() {
        HyperTrack.startMockTracking(new HyperTrackCallback() {
            @Override
            public void onSuccess(@NonNull SuccessResponse successResponse) {
                DialogBuilder.showToast(context, "login success");
            }

            @Override
            public void onError(@NonNull ErrorResponse errorResponse) {
                DialogBuilder.showToast(context, errorResponse.getErrorMessage());
            }
        });
    }

    public static void stop() {
        DialogBuilder.showToast(context, "tracking stopped");
        HyperTrack.stopMockTracking();
        HyperTrack.stopTracking();
    }


//    /**
//     * Construct a place object for Action's expected place
//     *
//     * @NOTE: Either the coordinates for the Action's location
//     * or it's address can be used to construct the expected place
//     */
//    Place expectedPlace = new Place().setLocation(latitude, longitude)
//            .setAddress(address)
//            .setName(name);
//
//    /**
//     * Create ActionParams object specifying the Visit Action parameters including
//     * ExpectedPlace, ExpectedAt time and Action's Lookup_id.
//     */
//    ActionParams actionParams = new ActionParamsBuilder().setExpectedPlace(expectedPlace)
//            .setType(Action.ACTION_TYPE_VISIT)
//            .setExpectedAt(expectedAtTimestamp)
//            .setLookupId(<ACTION_LOOKUP_ID_HERE>)
//            .build();
//
//    /**
//     * Call createAndAssignAction to assign Visit action to the current user
//     * configured in the SDK using the ActionParams created above.
//     */
//HyperTrack.createAndAssignAction(actionParams, new HyperTrackCallback() {
//        @Override
//        public void onSuccess(@NonNull SuccessResponse response) {
//            // Handle createAndAssignAction success here
//            Action action = (Action) response.getResponseObject();
//      ...
//        }
//
//        @Override
//        public void onError(@NonNull ErrorResponse errorResponse) {
//            // Handle createAndAssignAction error here
//            Toast.makeText(this, errorResponse.getErrorMessage(), Toast.LENGTH_SHORT).show();
//      ...
//        }
//    });
}
