package com.yust.ariel.a6over6.fragments;

import android.app.Activity;
import android.widget.Toast;
import android.app.Fragment;

/**
 * Created by Ariel Yust on 12-Mar-17.
 */

public class BaseFragment extends Fragment {
    /**
     * Shows a {@link Toast} on the UI thread.
     *
     * @param text The message to show
     */
    protected void showToast(final String text) {
        final Activity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
