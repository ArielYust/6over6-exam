package com.yust.ariel.a6over6.fragments;

import android.content.Context;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.yust.ariel.a6over6.MainActivity;
import com.yust.ariel.a6over6.data.Quaternion;
import com.yust.ariel.a6over6.providers.ImprovedOrientationSensorProvider;
import com.yust.ariel.a6over6.providers.OrientationProvider;
import com.yust.ariel.a6over6.renderers.CubeRenderer;

/**
 * Created by Ariel Yust on 11-Mar-17.
 */
@Deprecated
public class CameraFragment extends BaseFragment {
    /**
     * The surface that will be drawn upon
     */
    private GLSurfaceView mGLSurfaceView;

    private SurfaceView mSurfaceView;

    /**
     * The class that renders the cube
     */
    private CubeRenderer mRenderer;
    /**
     * The current orientation provider that delivers device orientation.
     */
    private OrientationProvider currentOrientationProvider;

    /*.....................................Life.Cycle.Methods.....................................*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        currentOrientationProvider.start();
//        mGLSurfaceView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        currentOrientationProvider.stop();
//        mGLSurfaceView.onPause();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        currentOrientationProvider = new ImprovedOrientationSensorProvider(
                (SensorManager) getActivity()
                        .getSystemService(MainActivity.SENSOR_SERVICE)
        );

        /* Create our Preview view and set it as the content of the fragment */
        mRenderer = new CubeRenderer();
        Quaternion startingQuaternion = new Quaternion();
        currentOrientationProvider.getQuaternion(startingQuaternion);
        mRenderer.setOrientationProvider(currentOrientationProvider);
        mGLSurfaceView = new GLSurfaceView(getActivity());
        mGLSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        mGLSurfaceView.setRenderer(mRenderer);
        mRenderer.toggleShowCubeInsideOut();

        return mGLSurfaceView;
    }
}
